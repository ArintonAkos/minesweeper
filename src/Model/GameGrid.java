package Model;

import Controller.CellController;
import Utils.Constants;
import View.CellView;

public class GameGrid {
    private final Cell[][] cells = new Cell[Constants.SIZE][Constants.SIZE];
    private final CellView[][] cellViews = new CellView[Constants.SIZE][Constants.SIZE];
    private final CellController[][] cellControllers = new CellController[Constants.SIZE][Constants.SIZE];

    public Cell[][] getCells() {
        return cells;
    }

    public CellView[][] getCellViews() {
        return cellViews;
    }

    public CellController[][] getCellControllers() {
        return cellControllers;
    }
}
