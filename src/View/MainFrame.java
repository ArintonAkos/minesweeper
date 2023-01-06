package View;

import Controller.GameGridController;
import Controller.HeaderController;
import Model.GameGrid;
import Model.Header;

import javax.swing.*;

public class MainFrame extends BaseFrame {
    private HeaderController headerController;
    private GameGridController gameGridController;

    public MainFrame() {
        super();
    }

    @Override
    protected void buildFrame() {
        JPanel mainPanel = new JPanel();
        Box[] boxes = new Box[2];

        getContentPane().add(mainPanel);
        boxes[0] = Box.createHorizontalBox();
        boxes[1] = Box.createHorizontalBox();
        Box.createGlue();
        mainPanel.add(boxes[0]);
        mainPanel.add(boxes[1]);

        Header header = new Header();
        HeaderPanel headerPanel = new HeaderPanel(header);
        this.headerController = new HeaderController(header, headerPanel);

        GameGrid gameGrid = new GameGrid();
        GameGridPanel gameGridPanel = new GameGridPanel(gameGrid);
        this.gameGridController = new GameGridController(gameGrid, gameGridPanel);

        boxes[0].add(headerPanel);
        boxes[1].add(gameGridPanel);

        resetGame();
    }

    public void resetGame() {
        this.headerController.resetHeader();
        this.gameGridController.resetGameGrid();
    }

    public HeaderController getHeaderController() {
        return headerController;
    }

    public GameGridController getGameGridController() {
        return gameGridController;
    }
}
