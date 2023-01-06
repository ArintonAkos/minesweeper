package View;

import Model.Cell;
import Utils.Assets;
import Utils.Colors;
import Utils.Constants;
import Utils.GraphicsExtensions;

import javax.swing.*;
import java.awt.*;

public class CellView extends JButton {
    private final Cell cell;

    public CellView(Cell cell) {
        super();
        this.cell = cell;

        reset();
        setFont(Constants.CELL_FONT);
    }

    public void reset() {
        setBackground(Colors.CELL_NOT_SHOWING);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (cell.isMarked()) {
            g.drawImage(Assets.FLAG_IMG, 0, 0, this.getWidth(), this.getHeight(), null);
            return;
        }

        if (cell.isShowing()) {
            if (cell.hasBomb()) {
                g.drawImage(Assets.BOMB_IMG, 0, 0, this.getWidth(), this.getHeight(), null);
            } else {
                if (cell.getSurroundingBombs() > 0) {
                    GraphicsExtensions.drawCenteredString(g, cell.getSurroundingBombs().toString(), getVisibleRect(), g.getFont(), Colors.SURROUNDING_BOMBS_NUMBER[cell.getSurroundingBombs() - 1]);
                }
            }

            setBorder(BorderFactory.createLoweredBevelBorder());
            setBackground(Colors.CELL_SHOWING);
        }
    }
}
