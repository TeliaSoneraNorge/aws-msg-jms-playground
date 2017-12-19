package pl.ene.awsmsgjmsplayground.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import pl.ene.awsmsgjmsplayground.model.Order;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


@Component
public class OrderMassageConverter extends MappingJackson2MessageConverter {

    @Override
    protected boolean supports(Class<?> clazz) {
        return Order.class == clazz;
    }
}