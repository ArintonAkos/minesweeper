package Model;

public class Timer {
    private boolean timerStarted = false;
    private int time = 0;

    public String getFormattedTime() {
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void increaseTime() {
        time++;
    }

    public void resetTime() {
        time = 0;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void startTimer() {
        this.timerStarted = true;
    }

    public void stopTimer() {
        this.timerStarted = false;
    }
}
