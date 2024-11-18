package dk.via.course_assignment_4;


import dk.via.course_assignment_4.business.SlaughterhouseProcessingSystemImpl;
import dk.via.course_assignment_4.messaging.SlaughterhouseProcessingServiceConsumer;
import dk.via.course_assignment_4.messaging.SlaughterhouseProcessingServicePublisher;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.model.Part;
import dk.via.shared.model.Product;
import dk.via.shared.service.Observer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlaughterhouseProcessingService implements Observer {

    private final SlaughterhouseProcessingSystemImpl system;
    private final SlaughterhouseProcessingServicePublisher publisher;
    private final SlaughterhouseProcessingServiceConsumer consumer;


    public SlaughterhouseProcessingService(SlaughterhouseProcessingSystemImpl system) {
        this.system = system;
        this.publisher = new SlaughterhouseProcessingServicePublisher("station_exchange");
        this.consumer = new SlaughterhouseProcessingServiceConsumer("station1_to_station2");
        System.out.println("SlaughterhouseProcessingService created");
        Initialize();
    }

    private void Initialize() {
        consumer.addObserver(this);
        consumer.startConsuming();
    }

    @Override
    public void update(String message) {
        try {
            System.out.println("SlaughterhouseProcessingService received message: " + message);

            List<Part> parts = system.cutAnimal(message);
            String[] partIds = parts.stream()
                    .map(Part::getPartID)
                    .toArray(String[]::new);

            for (String id : partIds) {
                System.out.println("Part ID: " + id);
            }

            List<Product> products = system.produceProduct("Sausage", partIds);

            for (Product product : products) {
                publisher.publish("station3.product.registration", product.getProductID());
            }
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
}
