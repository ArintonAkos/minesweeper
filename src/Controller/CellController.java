package Controller;

import Model.Cell;
import View.CellView;
import View.GameGridPanel;
import View.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellController {
    private final Cell cell;
    private final CellView cellView;

    public CellController(Cell cell, CellView view) {
        this.cell = cell;
        this.cellView = view;

        this.setupListeners();
    }

    public void setupListeners() {
        this.cellView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!CellController.this.isGameStarted()) {
                    return;
                }

                CellController.this.startTimer();

                if (e.getButton() == MouseEvent.BUTTON1) { // Left click
                    CellController.this.revealCell();
                    CellController.this.invokeCellReveal();
                } else if (e.getButton() == MouseEvent.BUTTON3) { // Right click
                    CellController.this.markCell();
                }
            }
        });
    }

    public void markCell() {
        if (cell.isShowing()) {
            return;
        }

        HeaderController headerController = getMainFrame().getHeaderController();

        if (cell.isMarked()) {
            headerController.getHeader().increaseFlagCount();
        } else {
            if (headerController.getHeader().getFlagCount() <= 0) {
                return;
            }

            headerController.getHeader().decreaseFlagCount();
        }

        headerController.updateFlagCount();
        cell.setMarked(!cell.isMarked());
        cellView.repaint();
    }

    public void revealCell() {
        if (cell.isMarked()) {
            return;
        }

        cell.setShowing(true);
        cellView.repaint();
        getMainFrame().getGameGridController().updateGameState();
    }

    public void invokeCellReveal() {
        if (isGameStarted()) {
            GameGridPanel parentGrid = (GameGridPanel) cellView.getParent();
            parentGrid.revealSurroundingCells(cell);
        }
    }

    public void resetCell() {
        cell.reset();
        cellView.reset();
        cellView.repaint();
    }

    private boolean isGameStarted() {
        return getMainFrame().getHeaderController().getHeader().isStarted();
    }

    public void startTimer() {
        getMainFrame().getHeaderController().startGame();
    }

    private MainFrame getMainFrame() {
        return (MainFrame) cellView.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
    }
}
