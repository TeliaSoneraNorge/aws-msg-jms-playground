package pl.ene.awsmsgjmsplayground.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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


    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderConsumer(TransactionTemplate transactionTemplate, ApplicationEventPublisher eventPublisher) {
        this.transactionTemplate = transactionTemplate;
        this.eventPublisher = eventPublisher;
    }



    public void onMessage(Message message) {
        // invoke business logic in new transaction using specified transaction manager
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                try {
                    TextMessage txtMessage = (TextMessage) message;

                    //TODO deserlialization to order
                    //ObjectMapper mapper =  new ObjectMapper();
                    //Order order =  mapper.readValue(body, Order.class);
                    //FIXME below only for test
                    Order order = new Order();
                    order.setContent(txtMessage.getText());
                    System.out.println("Order received:" + order);

                    //publish asynchronous event
                    eventPublisher.publishEvent(order);

                    if ("error".equalsIgnoreCase(txtMessage.getText())) throw new RuntimeException("Error processing message ...");
                    System.out.println("Finished order message consumption");
                    return null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
