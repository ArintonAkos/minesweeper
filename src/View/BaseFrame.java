package View;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    public BaseFrame() {
        super();
        setBounds(0, 0, 800, 900);
        setResizable(false);

        buildFrame();

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    protected abstract void buildFrame();
}
