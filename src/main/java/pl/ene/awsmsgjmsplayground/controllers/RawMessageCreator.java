package pl.ene.awsmsgjmsplayground.controllers;

import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

@Component
public class RawMessageCreator {

    public RawMessageCreator() {
        super();
    }

    public Message<?> createRawMessage(Object o) {
        Message<Object> msg = new Message<Object>() {
            @Override
            public Object getPayload() {
                return o;
            }
            @Override
            public MessageHeaders getHeaders() {
                HashMap<String, Object> customParams = new HashMap<>();
                customParams.put("traceID", "123456");
                EmptyMessageHeaders emh = new EmptyMessageHeaders(customParams,MessageHeaders.ID_VALUE_NONE, -1L);
                return emh;
            }
        };
        return msg;
    }

    static class EmptyMessageHeaders extends MessageHeaders {

        public EmptyMessageHeaders(Map<String, Object> headers) {
            super(headers);
        }

        protected EmptyMessageHeaders(Map<String, Object> headers, UUID id, Long timestamp) {
            super(headers, id, timestamp);
        }
    }

}
