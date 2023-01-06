package Utils;

import Model.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixMethods {
    private static final int[] X = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] Y = new int[]{-1, 1, 0, -1, 1, 0, -1, 1};
    private static final Random r = new Random();

    public static void generateBombs(Cell[][] cells, int nrOfBombs) {
        List<Point> generatedBombIndexes = new ArrayList<>();

        for (int i = 0; i < nrOfBombs; i++) {
            Point point = generatePoint();

            while (containsPoint(generatedBombIndexes, point)) {
                point = generatePoint();
            }

            generatedBombIndexes.add(point);
        }

        generatedBombIndexes.stream()
                .forEach(point -> cells[point.x][point.y].setHasBomb(true));
    }

    private static Point generatePoint() {
        int i = r.nextInt(Constants.SIZE);
        int j = r.nextInt(Constants.SIZE);

        return new Point(i, j);
    }

    private static boolean containsPoint(List<Point> points, Point pointToFind) {
        return points.stream().anyMatch(point -> point.equals(pointToFind));
    }

    public static int getSurroundingBombsForCell(Cell[][] cells, int i, int j) {
        int surroundingBombs = 0;

        for (int k = 0; k < X.length; k++) {
            int ujX = i + X[k];
            int ujY = j + Y[k];

            if (ujX < 0 || ujX >= cells.length || ujY < 0 || ujY >= cells.length) {
                continue;
            }

            if (cells[ujX][ujY].hasBomb()) {
                surroundingBombs++;
            }
        }

        return surroundingBombs;
    }

    public static List<Point> getSurroundingCellsIndexesWithoutBombs(Cell[][] cells, Cell cell) {
        int i = 0;
        int j = 0;
        boolean found = false;

        while (i < cells.length && !found) {
            j = 0;

            while (j < cells.length && !found) {
                found = (cells[i][j] == cell);
                j++;
            }

            i++;
        }

        i--;
        j--;

        List<Point> surroundingCellIndexes = new ArrayList<>();
        boolean[][] visited = new boolean[cells.length][cells.length];

        floodFill(cells, visited, i, j, surroundingCellIndexes);

        return surroundingCellIndexes;
    }

    private static void floodFill(Cell[][] cells, boolean[][] visited, int i, int j, List<Point> visitedPoints) {
        if (visited[i][j] || cells[i][j].hasBomb()) {
            return;
        }

        visited[i][j] = true;
        visitedPoints.add(new Point(i, j));

        if (cells[i][j].getSurroundingBombs() > 0) {
            return;
        }

        for (int k = 2; k <= 5; k++) {
            int ujX = i + X[k];
            int ujY = j + Y[k];

            if (ujX < 0 || ujX >= cells.length || ujY < 0 || ujY >= cells.length) {
                continue;
            }

            floodFill(cells, visited, ujX, ujY, visitedPoints);
        }
    }

    public static boolean hasClickedOnBomb(Cell[][] cells) {
        boolean clickedOnBomb = false;

        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                if (cell.hasBomb() && cell.isShowing()) {
                    clickedOnBomb = true;
                    break;
                }
            }

            if (clickedOnBomb) {
                break;
            }
        }

        return clickedOnBomb;
    }

    public static boolean hasClickedAllCorrectCells(Cell[][] cells) {
        int clickedOnBombs = 0;
        int clickedOnCorrectCells = 0;
        int i = 0;
        int j;

        while (i < Constants.SIZE) {
            j = 0;

            while (j < Constants.SIZE) {
                if (cells[i][j].isShowing()) {
                    if (!cells[i][j].hasBomb()) {
                        clickedOnCorrectCells++;
                    } else {
                        clickedOnBombs++;
                        i = Constants.SIZE;
                        j = Constants.SIZE;
                    }
                }

                j++;
            }

            i++;
        }

        return (clickedOnCorrectCells == Constants.SIZE * Constants.SIZE - Constants.NR_OF_BOMBS) && (clickedOnBombs == 0);
    }
}
