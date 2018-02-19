package pl.ene.awsmsgjmsplayground.controllers;

import com.amazon.sqs.javamessaging.AmazonSQSExtendedClient;
import com.amazon.sqs.javamessaging.ExtendedClientConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:application-test.properties")
//@ContextConfiguration("classpath:jms-sqs-messaging-test.xml")
public class MainControllerTest {

    private static final String SQSURL = "https://sqs.eu-west-1.amazonaws.com/456893923059/sqs-hackathon";

//    @Autowired
//    private NotificationMessagingTemplate orderMessagingTemplate;

    AmazonSQS sqs;

    static {
        System.setProperty("AWS_PROFILE", "dev-admin");
        System.out.println("!!!!!!");
        System.out.println(System.getProperties().toString());
    }

    @Before
    public void setUp() throws Exception {
        sqs = AmazonSQSClientBuilder.defaultClient();
    }

    /*@Test
    public void sendMessageLessThan256KB() {
        String payloadLessThan256 = "TEST_SEKRET";
        orderMessagingTemplate.convertAndSend(orderMessagingTemplate.getDefaultDestination(), payloadLessThan256);
    }*/

    /*@Test
    public void sendMessageMoreThan256KB() {
        String constant = "1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append(constant);
        }

        String payloadWithMoreThan256KB = sb.toString();
        System.out.println("!!!!!" +payloadWithMoreThan256KB.getBytes().length);

        orderMessagingTemplate.convertAndSend(orderMessagingTemplate.getDefaultDestination(), payloadWithMoreThan256KB);
    }*/

    //@Test
    public void sendLargeMessageToSQS() {
        String constant = "1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            sb.append(constant);
        }

         /*String s3BucketName = "vlada-test"
                + DateTimeFormat.forPattern("yyMMdd-hhmmss").print(new DateTime())*/
        ;
        String s3BucketName = "vlada-test180219-034149";

        // Create a new instance of the builder with all defaults (such as credentials and region) set
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        // Set the Amazon S3 bucket name, and set a lifecycle rule on the bucket to
        // permanently delete objects a certain number of days after each object's creation date.
        // Next, create the bucket, and enable message objects to be stored in the bucket.
        BucketLifecycleConfiguration.Rule expirationRule = new BucketLifecycleConfiguration.Rule();
        expirationRule.withExpirationInDays(14).withStatus("Enabled");
        BucketLifecycleConfiguration lifecycleConfig = new BucketLifecycleConfiguration().withRules(expirationRule);

        //s3.createBucket(s3BucketName);
        s3.setBucketLifecycleConfiguration(s3BucketName, lifecycleConfig);
        System.out.println("Bucket created and configured.");

        // Set the Amazon SQS extended client configuration with large payload support enabled.
        ExtendedClientConfiguration extendedClientConfig = new ExtendedClientConfiguration()
                .withLargePayloadSupportEnabled(s3, s3BucketName);


        AmazonSQS sqsExtended = new AmazonSQSExtendedClient(AmazonSQSClientBuilder.defaultClient(), extendedClientConfig);

        SendMessageRequest myMessageRequest = new SendMessageRequest(SQSURL, sb.toString());

        /*for (int i = 0; i < 20; i++) {
            sqsExtended.sendMessage(new SendMessageRequest(SQSURL, i + sb.toString()));
        }*/

        // Receive messages, and then return information about them.
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSURL);
        receiveMessageRequest.setMaxNumberOfMessages(10);
        ReceiveMessageResult receiveMessageResult = sqsExtended.receiveMessage(receiveMessageRequest);
        List<Message> messages = receiveMessageResult.getMessages();

        System.out.println("11111 " + messages.size());

        for (Message message : messages) {
            System.out.println("\nMessage received:");
            System.out.println("  ID: " + message.getMessageId());
            System.out.println("  Receipt handle: " + message.getReceiptHandle());
            System.out.println("  Message body (first 5 characters): " + message.getBody().substring(0, 5));
        }
    }




    //@Test
    public void produceLargeMessagesToSQS() {
        String constant = "1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            sb.append(constant);
        }

        String s3BucketName = "vlada-test180219-034149";

        // Create a new instance of the builder with all defaults (such as credentials and region) set
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        // Set the Amazon S3 bucket name, and set a lifecycle rule on the bucket to
        // permanently delete objects a certain number of days after each object's creation date.
        // Next, create the bucket, and enable message objects to be stored in the bucket.
        BucketLifecycleConfiguration.Rule expirationRule = new BucketLifecycleConfiguration.Rule();
        expirationRule.withExpirationInDays(14).withStatus("Enabled");
        BucketLifecycleConfiguration lifecycleConfig = new BucketLifecycleConfiguration().withRules(expirationRule);

        s3.setBucketLifecycleConfiguration(s3BucketName, lifecycleConfig);
        System.out.println("Bucket created and configured.");

        // Set the Amazon SQS extended client configuration with large payload support enabled.
        ExtendedClientConfiguration extendedClientConfig = new ExtendedClientConfiguration()
                .withLargePayloadSupportEnabled(s3, s3BucketName);

        AmazonSQS sqsExtended = new AmazonSQSExtendedClient(AmazonSQSClientBuilder.defaultClient(), extendedClientConfig);

        for (int i = 0; i < 20; i++) {
            sqsExtended.sendMessage(new SendMessageRequest(SQSURL, i + sb.toString()));
        }
    }

    //@Test
    public void consumeLargeMessagesFromSQS() {
        String s3BucketName = "vlada-test180219-034149";

        // Create a new instance of the builder with all defaults (such as credentials and region) set
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        // Set the Amazon S3 bucket name, and set a lifecycle rule on the bucket to
        // permanently delete objects a certain number of days after each object's creation date.
        // Next, create the bucket, and enable message objects to be stored in the bucket.
        BucketLifecycleConfiguration.Rule expirationRule = new BucketLifecycleConfiguration.Rule();
        expirationRule.withExpirationInDays(14).withStatus("Enabled");
        BucketLifecycleConfiguration lifecycleConfig = new BucketLifecycleConfiguration().withRules(expirationRule);

        //s3.createBucket(s3BucketName);
        s3.setBucketLifecycleConfiguration(s3BucketName, lifecycleConfig);
        System.out.println("Bucket created and configured.");

        // Set the Amazon SQS extended client configuration with large payload support enabled.
        ExtendedClientConfiguration extendedClientConfig = new ExtendedClientConfiguration()
                .withLargePayloadSupportEnabled(s3, s3BucketName);

        AmazonSQS sqsExtended = new AmazonSQSExtendedClient(AmazonSQSClientBuilder.defaultClient(), extendedClientConfig);

        // Receive messages, and then return information about them.
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSURL);
        receiveMessageRequest.setMaxNumberOfMessages(10);
        ReceiveMessageResult receiveMessageResult = sqsExtended.receiveMessage(receiveMessageRequest);
        List<Message> messages = receiveMessageResult.getMessages();

        System.out.println("11111 " + messages.size());

        for (Message message : messages) {
            System.out.println("\nMessage received:");
            System.out.println("  ID: " + message.getMessageId());
            System.out.println("  Receipt handle: " + message.getReceiptHandle());
            System.out.println("  Message body (first 5 characters): " + message.getBody().substring(0, 5));

            sqsExtended.deleteMessage(SQSURL, message.getReceiptHandle());
        }
    }
}