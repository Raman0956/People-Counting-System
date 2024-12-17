package barrier;

import barrier.BarrierControl;

class TemporaryOpenState implements BarrierState {
    private BarrierControl barrierControl;

    public TemporaryOpenState(BarrierControl barrierControl) {
        this.barrierControl = barrierControl;
    }

    @Override
    public void handle() {
        System.out.println("Barrier temporarily open for personnel.");
    }
}