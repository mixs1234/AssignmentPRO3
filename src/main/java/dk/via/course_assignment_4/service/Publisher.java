package dk.via.course_assignment_4.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class Publisher {
    private Connection connection;
    private Channel channel;
    private final String exchangeName;

    public Publisher(String exchangeName) {
        this.exchangeName = exchangeName;
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
            channel.exchangeDeclare(exchangeName, "topic", true);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize publisher: " + e.getMessage(), e);
        }
    }

    public void publish(String routingKey, String message) {
        try {
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish message: " + e.getMessage(), e);
        }
    }

    public void close() {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close publisher: " + e.getMessage(), e);
        }
    }
}

