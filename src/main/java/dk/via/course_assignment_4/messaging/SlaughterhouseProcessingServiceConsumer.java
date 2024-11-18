package dk.via.course_assignment_4.messaging;

import dk.via.shared.service.Consumer;
import dk.via.shared.service.Observer;

import java.util.ArrayList;
import java.util.List;

public class SlaughterhouseProcessingServiceConsumer extends Consumer {

    private final List<Observer> observers = new ArrayList<>();

    public SlaughterhouseProcessingServiceConsumer(String queueName) {
        super(queueName);
    }

    @Override
    protected void processMessage(String message) {
        notifyObservers(message);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
