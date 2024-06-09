package pl.ajablonski.ecommerce.payu;

public class PayUCredentials {
    boolean sandbox;
    private final String clientId;
    private final String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public PayUCredentials(String clientId, String clientSecret, boolean sandbox) {
        this.sandbox = sandbox;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static PayUCredentials sandbox(String clientId, String clientSecret, boolean sandbox) {
        return new PayUCredentials(clientId, clientSecret, true);
    }

    public String getBaseUrl(){
        if (sandbox){
            return "https://secure.snd.payu.com";
        } else {
            return "https://secure.payu.com";
        }
    }
}
