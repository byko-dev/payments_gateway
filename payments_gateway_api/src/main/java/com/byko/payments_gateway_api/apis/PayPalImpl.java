package com.byko.payments_gateway_api.apis;

import com.byko.payments_gateway_api.database.enums.PaymentMethod;
import com.byko.payments_gateway_api.database.schema.Invoice;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.exceptions.BadRequestException;
import com.byko.payments_gateway_api.pojos.paypal.*;
import com.byko.payments_gateway_api.services.InvoiceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

@Service
public class PayPalApi implements PaymentImpl{

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${frontend.url}")
    private String APPLICATION_URL;

    /* sandbox paypal url -> only for testing purpose */
    private final String BASE_API_URL = "https://api-m.sandbox.paypal.com";

    private RestTemplate restTemplate;
    private InvoiceService invoiceService;


    public PayPalApi(RestTemplate restTemplate, InvoiceService invoiceService){
        this.restTemplate = restTemplate;
        this.invoiceService = invoiceService;
    }

    private String generateAccessToken(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<AccessTokenResponse> accessTokenResponse = restTemplate.exchange(
                BASE_API_URL + "/v1/oauth2/token",
                HttpMethod.POST, httpEntity, AccessTokenResponse.class);

        if(!accessTokenResponse.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Can't generate paypal access token!");

        return accessTokenResponse.getBody().getAccess_token();
    }

    @Override
    public HttpEntity<?> getRequestBody(Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(generateAccessToken());

        /* required header, but is totally useless i think  */
        httpHeaders.set("PayPal-Request-Id", UUID.randomUUID().toString());

        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);

        return httpEntity;
    }

    @Override
    public String createPayment(Product product) {
        PaymentCreationRequest paymentRequest = new PaymentCreationRequest();
        paymentRequest.setIntent("CAPTURE");

        /*paymentId -> reference_id*/
        String paymentId = UUID.randomUUID().toString();

        paymentRequest.setPurchase_units(
                Arrays.asList(
                        new PurchaseUnit(paymentId,
                                new Amount("EUR", String.valueOf(product.getPrice())))));

        paymentRequest.setPayment_source(
                new PaymentSource(
                        new Paypal(
                                new ExperienceContext(
                                        "IMMEDIATE_PAYMENT_REQUIRED",
                                        "PAYPAL",
                                        /* your company name */
                                        "Your brand name",
                                        /* your company locate*/
                                        "pl-PL",
                                        "LOGIN",
                                        "NO_SHIPPING",
                                        "PAY_NOW",
                                        APPLICATION_URL + "/paypal/success",
                                        APPLICATION_URL + "/paypal/cancel"))));

        ResponseEntity<PaymentCreationResponse> paymentCreationResponse = restTemplate.exchange(
                BASE_API_URL + "/v2/checkout/orders",
                HttpMethod.POST, getRequestBody(paymentRequest), PaymentCreationResponse.class);

        if(!paymentCreationResponse.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Can't create paypal payment!");

        /* save invoice to base */
        Invoice invoice = new Invoice();
        invoice.setPaymentMethod(PaymentMethod.PayPal.toString());
        invoice.setPaymentId(paymentId);
        invoice.setProduct(product);
        invoice.setTransactionId(paymentCreationResponse.getBody().getId());

        invoiceService.save(invoice);

        /* returns link to payment */
        return paymentCreationResponse.getBody().getLinks().get(1).getHref();
    }

    @Override
    public boolean isPaymentPaid(String transactionId) {
        Invoice invoice = invoiceService.getByTransactionId(transactionId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(generateAccessToken());

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<PaymentCaptureResponse> responseEntity = restTemplate.exchange(
                BASE_API_URL + "/v2/checkout/orders/"+ transactionId,
                HttpMethod.GET, httpEntity, PaymentCaptureResponse.class);

        if(!responseEntity.getStatusCode().is2xxSuccessful())
            throw new BadRequestException("Can't checkout paypal payment!");

        if(responseEntity.getBody().getStatus().equals("APPROVED")){
            invoice.setPaid(true);
            invoiceService.save(invoice);
            return true;
        }

        return false;
    }
}
