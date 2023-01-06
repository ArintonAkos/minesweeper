package Model;

import Utils.Constants;

public class Header {
    private final Timer timer;
    private boolean started = false;
    private int flags = Constants.NR_OF_BOMBS;

    public Header() {
        this.timer = new Timer();
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }

    public Integer getFlagCount() {
        return flags;
    }

    public void increaseFlagCount() {
        flags++;
    }

    public void decreaseFlagCount() {
        flags--;
    }

    public void resetFlagCount() {
        flags = Constants.NR_OF_BOMBS;
    }

    public Timer getTimer() {
        return timer;
    }
}
