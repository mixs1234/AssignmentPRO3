package dk.via.course_assignment_4.service;

import com.rabbitmq.client.*;

public abstract class Consumer {
    private Connection connection;
    private Channel channel;
    private final String queueName;

    public Consumer(String queueName) {
        this.queueName = queueName;
        initialize();
    }

    private void initialize() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("guest");
            factory.setPassword("guest");

            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize consumer: " + e.getMessage(), e);
        }
    }

    public void startConsuming() {
        try {
            System.out.println(" [*] Waiting for messages on queue: " + queueName);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                processMessage(message);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to start consuming messages: " + e.getMessage(), e);
        }
    }

    protected abstract void processMessage(String message);

    public void close() {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close consumer: " + e.getMessage(), e);
        }
    }
}

