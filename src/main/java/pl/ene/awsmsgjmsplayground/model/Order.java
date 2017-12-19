package pl.ene.awsmsgjmsplayground.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Order {

    private Integer orderId;
    private String orderState;
    private Date date;
    private String content;

    public Order() {
    }

    @JsonCreator
    public Order(@JsonProperty Integer orderId, @JsonProperty String orderState,  @JsonProperty Date date) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.date = date;

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderState='" + orderState + '\'' +
                ", date=" + date +
                ", content=" + content +
                '}';
    }
}

