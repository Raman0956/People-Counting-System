package store;

public interface CountTracker {
	void addObserver(CountObserver observer);
    void removeObserver(CountObserver observer);
    void notifyObservers();

}
