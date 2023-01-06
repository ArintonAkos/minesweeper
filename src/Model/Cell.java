package Model;

public class Cell {
    private boolean marked;
    private boolean showing;
    private boolean hasBomb;
    private int surroundingBombs;

    public Cell(boolean marked, boolean hasBomb, int surroundingBombs) {
        this.marked = marked;
        this.hasBomb = hasBomb;
        this.showing = false;
        this.surroundingBombs = surroundingBombs;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public Integer getSurroundingBombs() {
        return surroundingBombs;
    }

    public void setSurroundingBombs(int surroundingBombs) {
        this.surroundingBombs = surroundingBombs;
    }

    public void reset() {
        marked = false;
        showing = false;
        hasBomb = false;
        surroundingBombs = 0;
    }
}
