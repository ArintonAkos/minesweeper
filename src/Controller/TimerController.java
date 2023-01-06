package Controller;

import Model.Timer;
import View.TimerView;

public record TimerController(Timer timer, TimerView timerView) implements Runnable {
    @Override
    public void run() {
        while (timer.isTimerStarted()) {
            timer.increaseTime();
            timerView.repaint();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // We don't throw exception, because when we interrupt the thread we don't want any exceptions
            }
        }
    }
}
