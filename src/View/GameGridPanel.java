package View;

import Model.Cell;
import Model.GameGrid;
import Utils.Constants;
import Utils.MatrixMethods;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameGridPanel extends JPanel {
    private final GameGrid gameGrid;

    public GameGridPanel(GameGrid gameGrid) {
        super();
        this.gameGrid = gameGrid;
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(Constants.SIZE, Constants.SIZE));
    }

    public void revealSurroundingCells(Cell cell) {
        List<Point> surroundingCellIndexes = MatrixMethods.getSurroundingCellsIndexesWithoutBombs(gameGrid.getCells(), cell);

        for (Point surroundingCellIndex : surroundingCellIndexes) {
            gameGrid.getCellControllers()[surroundingCellIndex.x][surroundingCellIndex.y].revealCell();
        }
    }
}
