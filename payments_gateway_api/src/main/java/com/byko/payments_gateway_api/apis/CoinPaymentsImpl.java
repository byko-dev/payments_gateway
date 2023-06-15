package com.byko.payments_gateway_api.apis;

import com.byko.payments_gateway_api.database.enums.PaymentMethod;
import com.byko.payments_gateway_api.database.schema.Invoice;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.pojos.coinpayments.create_payment.CreatePaymentResponse;
import com.byko.payments_gateway_api.pojos.coinpayments.transaction_details.TransactionDetailsResponse;
import com.byko.payments_gateway_api.services.InvoiceService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CoinPaymentsImpl implements CoinPayments {

    @Value("${coinpayments.private.key}")
    private String privateKey;

    @Value("${coinpayments.public.key}")
    private String publicKey;

    @Value("${backend.url}")
    private String APPLICATION_URL;
    private final String BASE_API_URL = "https://www.coinpayments.net/api.php";

    private RestTemplate restTemplate;
    private InvoiceService invoiceService;

    public CoinPaymentsImpl(RestTemplate restTemplate, InvoiceService invoiceService){
        this.restTemplate = restTemplate;
        this.invoiceService = invoiceService;
    }

    @Override
    public HttpEntity<?> getRequestBody(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("HMAC", generateHmac(body.toString()));

        HttpEntity<String> requestBody = new HttpEntity<>(body.toString(), headers);

        return requestBody;
    }

    @Override
    public String createPayment(Product product, String cryptocurrency, String buyerEmail) {
        String transactionId = UUID.randomUUID().toString();
        String IPN_URL = APPLICATION_URL + "/crypto/ipn?id=" + transactionId;

        String payload = getCreatePaymentPayload(product.getPrice(), cryptocurrency,
                /* buyer_email is required in payload */ buyerEmail, product.getName(), IPN_URL);

        ResponseEntity<CreatePaymentResponse> response = restTemplate.postForEntity(BASE_API_URL, getRequestBody(payload), CreatePaymentResponse.class);

        Invoice invoice = new Invoice();
        invoice.setPaymentId(response.getBody().getResult().getTxn_id());
        invoice.setProduct(product);
        invoice.setCryptocurrency(cryptocurrency);
        invoice.setPaid(false);
        invoice.setPaymentMethod(PaymentMethod.Crypto.toString());
        invoice.setTransactionId(transactionId);
        invoiceService.save(invoice);

        return response.getBody().getResult().getStatus_url();
    }

    @Override
    public boolean isPaymentPaid(String transactionId) {

        Invoice invoice = invoiceService.getByTransactionId(transactionId);

        ResponseEntity<TransactionDetailsResponse> response = restTemplate.postForEntity(BASE_API_URL,
                getRequestBody(getPaymentInfoRequestPayload(invoice.getPaymentId())), TransactionDetailsResponse.class);

        if(response.getBody().getResult().getCustomObjectKey().get(transactionId).getStatus_text().equals("Complete")){
            invoice.setPaid(true);
            invoiceService.save(invoice);
            return true;
        }

        return false;
    }

    private String generateHmac(String data){
        HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, privateKey);
        byte[] hmac = hmacUtils.hmac(data);
        return Hex.encodeHexString(hmac);
    }

    private String getCreatePaymentPayload(float amount, String cryptocurrency, String buyer_email, String item_name, String IPN_URL){
        return "version=1&cmd=create_transaction&amount=" + amount + "&currency1=EUR&currency2=" + cryptocurrency
                + "&buyer_email=" + buyer_email + "&item_name=" + item_name + "&ipn_url=" + IPN_URL
                + "&key=" + publicKey;
    }

    private String getPaymentInfoRequestPayload(String paymentId){
        return "version=1&cmd=get_tx_info_multi&txid=" + paymentId + "&full=1&key=" + publicKey;
    }
}
