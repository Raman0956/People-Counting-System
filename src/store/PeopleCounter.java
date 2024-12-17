package store;

import java.util.ArrayList;
import java.util.List;

public class PeopleCounter implements CountTracker {
    private List<CountObserver> observers = new ArrayList<>();
    private int entryCount = 0;
    private int exitCount = 0;
    
    @Override
    public void addObserver(CountObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(CountObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (CountObserver observer : observers) {
            observer.update(getCurrentCustomerCount());
        }
    }

    public void detectEntry() {
        entryCount++;
        notifyObservers();
    }

    public void detectExit() {
        exitCount++;
        notifyObservers();
    }

    public int getCurrentCustomerCount() {
        return entryCount - exitCount;
    }
}
