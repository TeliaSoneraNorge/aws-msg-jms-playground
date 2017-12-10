package pl.ene.awsmsgjmsplayground.sqs;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class SpringMessageDrivenBean {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String body = ((TextMessage) message).getText();
                System.out.println("Message received: " + body);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

}
