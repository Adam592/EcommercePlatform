package pl.ajablonski.ecommerce.payu;

public class PayUCredentials {
    boolean sandbox;
    public static PayUCredentials sandbox(String clientId, String clientSecret, boolean sandbox) {
        return new PayUCredentials(clientId,clientSecret, sandbox);
    }

    public String getBaseUrl(){
        if (sandbox){
            return "https://secure.snd.payu.com";
        } else {
            return "https://secure.payu.com";
        }
    }
}
