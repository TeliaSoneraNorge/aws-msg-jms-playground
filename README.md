# aws-msg-jms-playground
Build application:

``` mvn clean install spring-boot:run ```

Send message to SNS queue:

```curl "http://localhost:8080/send?orderId=123&orderState=pending"```

Message is being sent to SNS via spring-cloud-aws library and consumed from SQS via spring-jms library
