package com.byko.payments_gateway_api.apis;

import com.byko.payments_gateway_api.database.enums.PaymentMethod;
import com.byko.payments_gateway_api.database.schema.Invoice;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.exceptions.BadRequestException;
import com.byko.payments_gateway_api.pojos.stripe.PriceResponse;
import com.byko.payments_gateway_api.pojos.stripe.ProductResponse;
import com.byko.payments_gateway_api.pojos.stripe.payment.PaymentResponse;
import com.byko.payments_gateway_api.services.InvoiceService;
import com.byko.payments_gateway_api.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class StripeApi implements PaymentImpl {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Value("${application.url}")
    private String APPLICATION_URL;

    private RestTemplate restTemplate;
    private ProductService productService;
    private InvoiceService invoiceService;

    public StripeApi(RestTemplate restTemplate, ProductService productService, InvoiceService invoiceService){
        this.restTemplate = restTemplate;
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    @Override
    public HttpEntity<?> getRequestBody(Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(secretKey);
        return new HttpEntity<>(body, headers);
    }

    public Product setProductIdRequest(Product product){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", product.getName());

        ResponseEntity<ProductResponse> response =
                restTemplate.postForEntity("https://api.stripe.com/v1/products", getRequestBody(requestBody), ProductResponse.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Stripe -> can't get productId");

        product.setStripeId(response.getBody().getId());
        return productService.save(product);
    }

    /* https://stripe.com/docs/api/prices/create */
    public Product setPriceToProductRequest(Product product){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add("unit_amount", parsePriceToRequest(product.getPrice()));
        requestBody.add("currency", "usd");
        requestBody.add("product", product.getStripeId());

        ResponseEntity<PriceResponse> response =
                restTemplate.postForEntity("https://api.stripe.com/v1/prices", getRequestBody(requestBody), PriceResponse.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Stripe -> can't get productId");

        product.setStripePriceId(response.getBody().getId());
        return productService.save(product);
    }

    /* https://stripe.com/docs/api/payment_links/payment_links/create?lang=curl */
    @Override
    public String createPayment(Product product) {
        if(product.getStripePriceId() == null){
            product = setProductIdRequest(product);
            product = setPriceToProductRequest(product);
        }

        String transactionId = UUID.randomUUID().toString();

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("line_items[0][price]", product.getStripePriceId());
        requestBody.add("line_items[0][quantity]", "1");
        requestBody.add("mode", "payment");
        requestBody.add("success_url", APPLICATION_URL + "/stripe/success?token=" + transactionId);
        requestBody.add("cancel_url", APPLICATION_URL + "/stripe/cancel");

        ResponseEntity<PaymentResponse> response =
                restTemplate.postForEntity("https://api.stripe.com/v1/checkout/sessions",
                        getRequestBody(requestBody), PaymentResponse.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Stripe -> can't get productId");

        /* save invoice to base */
        Invoice invoice = new Invoice();
        invoice.setPaymentMethod(PaymentMethod.Stripe);
        invoice.setPaymentId(response.getBody().getId());
        invoice.setProduct(product);
        invoice.setTransactionId(transactionId);

        invoiceService.save(invoice);

        return response.getBody().toString();
    }

    /* https://stripe.com/docs/api/checkout/sessions/retrieve */
    @Override
    public String isPaymentPaid(String transactionId) {
        Invoice invoice = invoiceService.getByTransactionId(transactionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);

        HttpEntity<?> httpEntity = new HttpEntity(headers);

        ResponseEntity<PaymentResponse> response =
                restTemplate.exchange("https://api.stripe.com/v1/checkout/sessions/" + invoice.getPaymentId(),
                        HttpMethod.GET, httpEntity, PaymentResponse.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Stripe -> can't get productId");

        if(response.getBody().getPayment_status().equals("paid")){
            invoice.setPaid(true);
            invoiceService.save(invoice);
        }

        return response.getBody().toString();
    }

    private String parsePriceToRequest(float price){
        return String.valueOf(price).split(".") + "00";
    }
}
