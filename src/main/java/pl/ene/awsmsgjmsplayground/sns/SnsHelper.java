package pl.ene.awsmsgjmsplayground.sns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;


@Component
public class SnsHelper {

    public boolean isSNSMessage(TextMessage message) {
        //TODO imp[elementaqtion
        return true;
    }

    public String getSNSMessageText(TextMessage message) throws Exception {
        if (!isSNSMessage(message)) {
            return message.getText();
        }
        //extract "Message" field from SNS JSON
        String json = message.getText();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        JsonNode coordinatesNode = node.at("/Message");
        String coordinatesNodeAsString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(coordinatesNode);
        return coordinatesNodeAsString;
    }
}
