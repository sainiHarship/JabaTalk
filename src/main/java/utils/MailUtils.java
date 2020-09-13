package utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * Redundant email utils.
 * EMAIL_APIKEY & EMAIL_APISecret are paid for uniRest services.
 */
public class MailUtils {
    private static final String EMAIL_DOMAIN = "mail.io";
    private static final String EMAIL_APIKEY = "mail7_api_key";
    private static final String EMAIL_APISecret = "mail7_api_secret";
    private String emailid;

    public static String usernameGenerator(){

        String username = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        System.out. println("Random Username is :" + username);
        return username;
    }

    public static String getInbox(String username) throws UnirestException {

        HttpResponse<String> httpResponse = Unirest.get("https://api.mail7.io/inbox?apikey=" + EMAIL_APIKEY + "&apisecret=" + EMAIL_APISecret + "&to=" + username)
                .asString();
        System.out.println(httpResponse.getHeaders().get("Content-Type"));
        System.out.println(httpResponse.getBody());
        return httpResponse.getBody();
    }


}
