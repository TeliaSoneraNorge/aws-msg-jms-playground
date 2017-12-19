package pl.ene.awsmsgjmsplayground.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.ene.awsmsgjmsplayground.model.Order;

@Component
public class OrderEventListener {

    @EventListener
    public void handleOrderEvent(Order order){
        System.out.println("Start processing event order " + order);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop processing event order ");
    }
}
