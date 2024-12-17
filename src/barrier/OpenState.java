package barrier;

import barrier.BarrierControl;

public class OpenState implements BarrierState{
	private BarrierControl barrierControl;

    public OpenState(BarrierControl barrierControl) {
        this.barrierControl = barrierControl;
    }

    @Override
    public void handle() {
        System.out.println("Barrier is open.");
    }
}
