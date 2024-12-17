package barrier;

import barrier.*;


public class BarrierControl {
    private BarrierState openState;
    private BarrierState closedState;
    private BarrierState temporaryOpenState;
    private BarrierState currentState;
    private boolean limitReached; // Flag to track if the capacity limit is reached

    public BarrierControl() {
        openState = new OpenState(this);
        closedState = new ClosedState(this);
        temporaryOpenState = new TemporaryOpenState(this);
        currentState = openState; // Default to open at start
        limitReached = false; // Default to not reached
    }

    public void setState(BarrierState state) {
        currentState = state;
        currentState.handle();
    }

    public void openBarrier() {
        if (limitReached) {
            // If the limit was previously reached, do not open the barrier
            return;
        }
        setState(openState);
      
    }

    public void closeBarrier() {
        if (!limitReached) {
            // Display message only when closing the barrier due to reaching the capacity limit
            
            limitReached = true;
        }
        setState(closedState);
    }

    // Reset the limitReached flag when the count falls below the capacity
    public void resetLimitReached() {
        limitReached = false;
    }

    public void temporaryOpenBarrier() {
        setState(temporaryOpenState);
    }

    // New method to check if the barrier is open
    public boolean isBarrierOpen() {
        return currentState == openState;
    }
}
