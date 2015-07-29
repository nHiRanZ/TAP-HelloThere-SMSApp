import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsReq;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nimila on 7/29/15.
 */
public class FeedbackListener implements MoSmsListener {
    @Override
    public void init() {

    }

    @Override
    public void onReceivedSms(MoSmsReq moSmsReq) {

        String message = moSmsReq.getMessage();
        String number = moSmsReq.getSourceAddress();

        FeedbackService.addMessage(number, message);
        try {
            sendResponse(moSmsReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SdpException e) {
            e.printStackTrace();
        }

    }

    private void sendResponse(MoSmsReq moSmsReq) throws MalformedURLException, SdpException {

        SmsRequestSender smsRequestSender = new SmsRequestSender(new URL("http://localhost:7000/sms/send"));

        MtSmsReq mtSmsReq = new MtSmsReq();
        mtSmsReq.setMessage("Hello " + moSmsReq.getSourceAddress() + ". Thank you for your reply.");
        mtSmsReq.setApplicationId(moSmsReq.getApplicationId());
        mtSmsReq.setPassword("password");

        mtSmsReq.setDestinationAddresses(Arrays.asList(moSmsReq.getSourceAddress()));

        smsRequestSender.sendSmsRequest(mtSmsReq);

    }
}
