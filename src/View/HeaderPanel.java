package View;

import Model.Header;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private final JLabel flagLabel;
    private final JButton newGameButton;

    public HeaderPanel(Header header) {
        super();

        this.flagLabel = new JLabel(header.getFlagCount().toString());
        this.newGameButton = new JButton("Reset Game");

        this.flagLabel.setFont(Constants.HEADER_FONT);
        setPreferredSize(new Dimension(800, 100));
        add(flagLabel);
        add(newGameButton);
    }

    public void setupActionListener(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
    }

    public JLabel getFlagLabel() {
        return flagLabel;
    }
}
