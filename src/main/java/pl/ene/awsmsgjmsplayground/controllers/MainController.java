package pl.ene.awsmsgjmsplayground.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ene.awsmsgjmsplayground.model.Order;
import pl.ene.awsmsgjmsplayground.sns.ProducerSns;


import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

@RestController
public class MainController {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    NotificationMessagingTemplate orderMessagingTemplate;

    @Autowired
    RawMessageCreator rawMessageCreator;

    @GetMapping(path = {"/"})
    public String home() {
        return "{result: hello world}";
    }


    @GetMapping(path = {"/send"})
    public ResponseEntity<String> sendMessage(@RequestParam String orderId, @RequestParam String orderState) {

        if (orderId == null || orderState == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Params validation failed, orderId and orderState params are mandatory");
        }

        Order order = new Order(new Integer(orderId), orderState, new Date());

        try {

            //Message<?> msg = rawMessageCreator.createRawMessage(order);

            HashMap<String, Object> customParams = new HashMap<>();
            customParams.put("traceID", "123456");
            orderMessagingTemplate.convertAndSend(orderMessagingTemplate.getDefaultDestination(), order, customParams);
            //producerSns.send(msg;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("sent failed: " + order);
        }

        return ResponseEntity.status(HttpStatus.OK).body("body sent: " + order);
    }

}
