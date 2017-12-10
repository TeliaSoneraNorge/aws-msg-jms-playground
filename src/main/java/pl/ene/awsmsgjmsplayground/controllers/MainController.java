package pl.ene.awsmsgjmsplayground.controllers;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RestController
public class MainController {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @GetMapping(path = {"/"})
    public String home() {
        return "{result: hello world}";
    }

    @GetMapping(path = {"/send"})
    public ResponseEntity<String> sendMessage(@RequestParam  String body ) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                final TextMessage msg = session.createTextMessage(body);
                return msg;
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body("body sent: " + body);
    }

}
