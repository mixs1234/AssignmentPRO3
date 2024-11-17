package dk.via.course_assignment_4;

import dk.via.course_assignment_4.service.Consumer;

public class SlaughterhouseProcessingServiceConsumer extends Consumer {

    public SlaughterhouseProcessingServiceConsumer(String queueName) {
        super(queueName);
    }

    @Override
    protected void processMessage(String message) {

    }
}
