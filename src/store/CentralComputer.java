package store;

import barrier.*;

public class CentralComputer implements CountObserver {
    private static CentralComputer instance;
    private int currentCustomerCount = 0;
    private int maxCapacity;
    private BarrierControl barrierControl;

    // Private constructor for Singleton pattern
    private CentralComputer(BarrierControl barrierControl) {
        this.barrierControl = barrierControl;
    }

    // Singleton instance getter with BarrierControl parameter
    public static CentralComputer getInstance(BarrierControl barrierControl) {
        if (instance == null) {
            instance = new CentralComputer(barrierControl);
        }
        return instance;
    }

    // Update method called when customer count changes
    @Override
    public void update(int currentCustomerCount) {
        this.currentCustomerCount = currentCustomerCount;
        checkCapacity();
    }

    // Check if the barrier should be open or closed based on current count
    public void checkCapacity() {
        if (currentCustomerCount >= maxCapacity) {
            // Close the barrier when count meets or exceeds capacity
            barrierControl.closeBarrier();
        } else {
            // Reset limit flag and open the barrier when count is below max capacity
            barrierControl.resetLimitReached();
            barrierControl.openBarrier();
        }
    }

    // Getter for current customer count
    public int getCurrentCustomerCount() {
        return currentCustomerCount;
    }

    // Setter for maximum capacity and immediately check the barrier status
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        checkCapacity(); // Check if the barrier needs to be adjusted after setting new capacity
    }

    // Getter for maximum capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
