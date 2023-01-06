package Controller;

import Model.Header;
import View.HeaderPanel;
import View.MainFrame;
import View.TimerView;

public class HeaderController {
    private final Header header;
    private final HeaderPanel headerPanel;
    private final TimerController timerController;
    private Thread timerThread;

    public HeaderController(Header header, HeaderPanel headerPanel) {
        this.header = header;
        this.headerPanel = headerPanel;

        TimerView timerView = new TimerView(header.getTimer());
        this.timerController = new TimerController(header.getTimer(), timerView);

        headerPanel.add(timerView);
        setupActionListener();
    }

    private void setupActionListener() {
        headerPanel.setupActionListener(e -> HeaderController.this.resetGame());
    }

    public void resetHeader() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }

        header.start();
        header.getTimer().resetTime();
        header.resetFlagCount();
        timerController.timer().stopTimer();
        timerController.timerView().repaint();
        headerPanel.getFlagLabel().setText(header.getFlagCount().toString());
    }

    public void startGame() {
        if (!timerController.timer().isTimerStarted()) {
            timerController.timer().startTimer();

            timerThread = new Thread(timerController);
            timerThread.start();
        }
    }

    public void stopGame() {
        timerController.timer().stopTimer();
    }

    public void resetGame() {
        MainFrame mainFrame = (MainFrame) headerPanel.getParent().getParent().getParent().getParent().getParent().getParent();
        mainFrame.resetGame();
    }

    public Header getHeader() {
        return header;
    }

    public void updateFlagCount() {
        headerPanel.getFlagLabel().setText(header.getFlagCount().toString());
        headerPanel.getFlagLabel().repaint();
    }
}
