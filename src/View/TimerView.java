package View;

import Model.Timer;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;

public class TimerView extends JLabel {
    private final Timer timer;

    public TimerView(Timer timer) {
        super(timer.getFormattedTime());
        this.timer = timer;

        setFont(Constants.HEADER_FONT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setText(timer.getFormattedTime());
    }
}
