import java.util.Random;

public class Main {
    public static int lifeCount = 0;

    public static void main(String[] args) throws InterruptedException {
        Cell[][] gameOfLifeBoard = new Cell[10][100];
        Cell deadCell = new Cell(false);
        Cell aliveCell = new Cell(true);

        for (int r = 0; r < gameOfLifeBoard.length; r++) {
            for (int c = 0; c < gameOfLifeBoard[r].length; c++) {
                if ((r > 2 && r < 6) && (c > 48 && c < 55)) {
                    gameOfLifeBoard[r][c] = new Cell(true);
                    lifeCount++;
                } else {
                    gameOfLifeBoard[r][c] = new Cell(false);
                }
            }
        }
        while (lifeCount > 0) {
            printBoard(gameOfLifeBoard);
            cellCheck(gameOfLifeBoard);
            Thread.sleep(700);
            for (int i = 0; i < 50; ++i) {
                System.out.println();
            }
        }
    }
    public static void printBoard(Cell[][] gameOfLifeBoard) {
        for (Cell[] row : gameOfLifeBoard) {
            for (Cell cell : row) {
                System.out.print(cell.toString());
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void cellCheck(Cell[][] gameOfLifeBoard) {
        Cell[][] tempBoard = new Cell[gameOfLifeBoard.length][gameOfLifeBoard[0].length];
        for (int r = 0; r < gameOfLifeBoard.length; r++) {
            for (int c = 0; c < gameOfLifeBoard[r].length; c++) {
                int aliveNeighbors = countAliveNeighbors(gameOfLifeBoard, r, c);
                tempBoard[r][c] = new Cell(gameOfLifeRules(gameOfLifeBoard[r][c], aliveNeighbors));
            }
        }
        lifeCount = 0;
        for (int r = 0; r < gameOfLifeBoard.length; r++) {
            for (int c = 0; c < gameOfLifeBoard[r].length; c++) {
                gameOfLifeBoard[r][c] = tempBoard[r][c];
                if (gameOfLifeBoard[r][c].getAlive()) {
                    lifeCount++;
                }
            }
        }
    }
    public static int countAliveNeighbors(Cell[][] gameOfLifeBoard, int row, int column) {
        int neighborCount = 0;
        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        for (int[] offset : offsets) {
            int neighborRow = row + offset[0];
            int neighborCol = column + offset[1];
            if (neighborRow >= 0 && neighborRow < gameOfLifeBoard.length &&
                    neighborCol >= 0 && neighborCol < gameOfLifeBoard[neighborRow].length) {
                if (gameOfLifeBoard[neighborRow][neighborCol].getAlive()) {
                    neighborCount++;
                }
            }
        }
        return neighborCount;
    }
    public static boolean gameOfLifeRules(Cell cell, int aliveNeighbors) {
        if (cell.getAlive()) {
            return aliveNeighbors == 2 || aliveNeighbors == 3;
        } else {
            return aliveNeighbors == 3;
        }
    }
}
