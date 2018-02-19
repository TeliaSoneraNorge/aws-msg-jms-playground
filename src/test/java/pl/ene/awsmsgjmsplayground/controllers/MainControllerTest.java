package pl.ene.awsmsgjmsplayground.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:jms-sqs-messaging.xml")
public class MainControllerTest {

    @Autowired
    private NotificationMessagingTemplate orderMessagingTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sendMessage() {
    }
}