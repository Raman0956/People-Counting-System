package barrier;

class ClosedState implements BarrierState {
    private BarrierControl barrierControl;

    public ClosedState(BarrierControl barrierControl) {
        this.barrierControl = barrierControl;
    }

    @Override
    public void handle() {
        System.out.println("Barrier is closed due to capacity limit.");
    }
}