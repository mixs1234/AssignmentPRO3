package dk.via.course_assignment_4;

import dk.via.course_assignment_4.business.SlaughterhouseProcessingSystemImpl;
import org.springframework.stereotype.Component;

@Component
public class SlaughterhouseProcessingService {

    private final SlaughterhouseProcessingSystemImpl system;
    private final SlaughterhouseProcessingServicePublisher publisher;
    private final SlaughterhouseProcessingServiceConsumer consumer;

    public SlaughterhouseProcessingService(SlaughterhouseProcessingSystemImpl system) {
        this.system = system;
        this.publisher = new SlaughterhouseProcessingServicePublisher("station_exchange");
        this.consumer = new SlaughterhouseProcessingServiceConsumer("station1_to_station2");
        Initialize();
    }

    private void Initialize() {
        consumer.startConsuming();
    }


}
