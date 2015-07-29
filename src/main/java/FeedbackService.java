import java.util.HashMap;
import java.util.Map;

/**
 * Created by nimila on 7/29/15.
 */
public class FeedbackService {

    private static final Map<String, String> userMessages = new HashMap<String, String>();

    public static void addMessage(String number, String message) {
        userMessages.put(number, message);
    }

    public static Map<String, String> getMessages() {
        return userMessages;
    }

}
