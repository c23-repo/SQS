package SQS;

import com.amazonaws.AmazonClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Publisher {

    public static void publisher() {

        final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).build();

        try {
            final CreateQueueRequest createQueueRequest = new CreateQueueRequest();
            final String queueURL = sqs.createQueue(createQueueRequest).getQueueUrl();
            final String msg = "A random number of " + Math.floor(Math.random() * 100);

            sqs.sendMessage(new SendMessageRequest(queueURL, msg));

        } catch (final AmazonClientException amazonClientException){
            System.out.println("Error: " + amazonClientException.getMessage());

        }
    }

}
