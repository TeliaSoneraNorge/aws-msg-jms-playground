package pl.ene.awsmsgjmsplayground.sns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.TextMessage;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class SnsHelperTest {

    @Spy
    SnsHelper snsHelper;

    @Mock
    TextMessage message;

    @Test
    public void isSNSMessage() throws Exception {
    }

    @Test
    public void getSNSMessageText() throws Exception {
        Mockito.when(snsHelper.isSNSMessage(Mockito.any())).thenReturn(true);
        String json = "{\r\n    \"Type\": \"Notification\",\r\n    \"MessageId\": \"123\",\r\n    \"Message\": {\r\n      \"orderId\": 1,\r\n      \"orderState\": \"pending\",\r\n      \"date\": 1513260338409\r\n    }\r\n  }";
        Mockito.when(message.getText()).thenReturn(json);
        //TODO
        String expected = "";
        String result = snsHelper.getSNSMessageText(message);
    }

}