package pl.ajablonski.ecommerce.payu;

public class OrderCreateResponse {

    Status status;
    String redirectUri;
    String orderId;
    String extOrderId;
//    {
//        "status": {
//        "statusCode": "SUCCESS"
//    },
//        "redirectUri": "{payment_summary_redirection_url}",
//            "orderId": "WZHF5FFDRJ140731GUEST000P01",
//            "extOrderId": "{YOUR_EXT_ORDER_ID}"
//    }
    public String getRedirectedUri() {
        return redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }
}
