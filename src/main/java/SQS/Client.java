package SQS;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;


// The following code is from:
// https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/standard-queues-getting-started-java.html


public class Client {

    public static void client(){

        final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).build();

        final CreateQueueRequest createQueueRequest = new CreateQueueRequest("Queue");
        final String queueURL = sqs.createQueue(createQueueRequest).getQueueUrl();

        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

        for (final Message message : messages ){

            System.out.println(message.getBody());
        }

        final String messageReceipt = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(queueURL, messageReceipt));
    }
}
