package Controller;

import Model.Cell;
import Model.GameGrid;
import Utils.Assets;
import Utils.Constants;
import Utils.MatrixMethods;
import View.CellView;
import View.GameGridPanel;
import View.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameGridController {
    private final GameGrid gameGrid;
    private final GameGridPanel gameGridPanel;

    public GameGridController(GameGrid gameGrid, GameGridPanel gameGridPanel) {
        this.gameGrid = gameGrid;
        this.gameGridPanel = gameGridPanel;

        for (int i = 0; i < gameGrid.getCells().length; i++) {
            for (int j = 0; j < gameGrid.getCells().length; j++) {
                gameGrid.getCells()[i][j] = new Cell(false, false, 0);
                gameGrid.getCellViews()[i][j] = new CellView(gameGrid.getCells()[i][j]);
                gameGrid.getCellControllers()[i][j] = new CellController(gameGrid.getCells()[i][j], gameGrid.getCellViews()[i][j]);

                gameGridPanel.add(gameGrid.getCellViews()[i][j]);
            }
        }

        initGameGrid();
    }

    private static synchronized void playSound(AudioInputStream audioInputStream) {
        new Thread(() -> {
            try {
                audioInputStream.reset();
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    private void initGameGrid() {
        MatrixMethods.generateBombs(gameGrid.getCells(), Constants.NR_OF_BOMBS);

        for (int i = 0; i < gameGrid.getCells().length; i++) {
            for (int j = 0; j < gameGrid.getCells().length; j++) {
                gameGrid.getCells()[i][j].setSurroundingBombs(MatrixMethods.getSurroundingBombsForCell(gameGrid.getCells(), i, j));
            }
        }
    }

    public void resetGameGrid() {
        for (int i = 0; i < gameGrid.getCells().length; i++) {
            for (int j = 0; j < gameGrid.getCells().length; j++) {
                gameGrid.getCellControllers()[i][j].resetCell();
            }
        }

        initGameGrid();
    }

    public void updateGameState() {
        if (MatrixMethods.hasClickedOnBomb(gameGrid.getCells())) {
            setGameOver();
        }

        if (MatrixMethods.hasClickedAllCorrectCells(gameGrid.getCells())) {
            setWinState();
        }
    }

    private void setGameOver() {
        getMainFrame().getHeaderController().stopGame();
        getMainFrame().getHeaderController().getHeader().stop();
        playSound(Assets.getLoseSound());
        JOptionPane.showMessageDialog(null, "Game Over! :(");
    }

    private void setWinState() {
        getMainFrame().getHeaderController().stopGame();
        getMainFrame().getHeaderController().getHeader().stop();
        playSound(Assets.getWinSound());
        JOptionPane.showMessageDialog(null, "Congratulations! You Won!");
    }

    private MainFrame getMainFrame() {
        return (MainFrame) gameGridPanel.getParent().getParent().getParent().getParent().getParent().getParent();
    }
}
