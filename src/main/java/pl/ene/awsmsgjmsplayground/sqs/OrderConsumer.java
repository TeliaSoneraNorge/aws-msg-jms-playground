package pl.ene.awsmsgjmsplayground.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import pl.ene.awsmsgjmsplayground.model.Order;
import pl.ene.awsmsgjmsplayground.sns.SnsHelper;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class OrderConsumer {

    private final TransactionTemplate transactionTemplate;


    @Autowired
    public OrderConsumer(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;

    }

//    public void onMessage(Message message) {
//        // invoke business logic in new transaction using specified transaction manager
//        transactionTemplate.execute(new TransactionCallback<Object>() {
//            @Override
//            public Object doInTransaction(TransactionStatus transactionStatus) {
//                try {
//                    TextMessage txtMessage = (TextMessage) message;
//
//
//                    //ObjectMapper mapper =  new ObjectMapper();
//                    //Order o =  mapper.readValue(body, Order.class);
//
//                    System.out.println("Message received: " + txtMessage.getText());
//
//                    if ("error".equalsIgnoreCase(txtMessage.getText())) throw new RuntimeException("Error processing message ...");
//                    return null;
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//    }

    public void onMessage(Message message) {
        try {
        // invoke business logic in new transaction using specified transaction manager
                    TextMessage txtMessage = (TextMessage) message;
                    //ObjectMapper mapper =  new ObjectMapper();
                    //Order o =  mapper.readValue(body, Order.class);


            System.out.println("Message received: " + txtMessage.getText());
            if ("error".equalsIgnoreCase(txtMessage.getText())) throw new RuntimeException("Error processing message ...");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
