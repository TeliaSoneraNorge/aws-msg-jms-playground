package pl.ene.awsmsgjmsplayground.sns;

import com.amazonaws.services.sns.AmazonSNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProducerSns {


//    private String destinationName;
//    private NotificationMessagingTemplate notificationMessagingTemplate;
//    private MessageConverter messageConverter;
//
//    @Autowired
//    public ProducerSns(AmazonSNS amazonSns) {
//        this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSns);
//        this.notificationMessagingTemplate.setMessageConverter(mess);
//    }
//
//    public void send(Object message) {
//
//        Message<Object> msg = new Message<Object>() {
//            @Override
//            public Object getPayload() {
//                return message;
//            }
//            @Override
//            public MessageHeaders getHeaders() {
//                EmptyMessageHeaders emh = new EmptyMessageHeaders(new HashMap<>(),MessageHeaders.ID_VALUE_NONE, -1L);
//                return emh;
//            }
//        };
//
//        this.notificationMessagingTemplate.send(getDestinationName(), msg);
//        //this.notificationMessagingTemplate.convertAndSend(getDestinationName(), msg);
//
//    }
//
//
//
//
//    public String getDestinationName() {
//        return destinationName;
//    }
//
//    public void setDestinationName(String destinationName) {
//        this.destinationName = destinationName;
//    }
//
//    public NotificationMessagingTemplate getNotificationMessagingTemplate() {
//        return notificationMessagingTemplate;
//    }
//
//    public void setNotificationMessagingTemplate(NotificationMessagingTemplate notificationMessagingTemplate) {
//        this.notificationMessagingTemplate = notificationMessagingTemplate;
//    }
//
//    public MessageConverter getMessageConverter() {
//        return messageConverter;
//    }
//
//    public void setMessageConverter(MessageConverter messageConverter) {
//        this.messageConverter = messageConverter;
//    }
//
//    static class EmptyMessageHeaders extends MessageHeaders {
//
//        public EmptyMessageHeaders(Map<String, Object> headers) {
//            super(headers);
//        }
//
//        protected EmptyMessageHeaders(Map<String, Object> headers, UUID id, Long timestamp) {
//            super(headers, id, timestamp);
//        }
//    }
}

