package personnel;

public class StoreManager {
    private int maxCapacity;

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        System.out.println("Store capacity set to: " + maxCapacity);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
