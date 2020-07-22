package GreenVSRed;

public class Judge {

    private static final int GREEN_COLOR = 1;
    private static final int RED_COLOR = 0;
    private final int heightY;
    private final int widthX;
    private final int theYCoordinateOfTheCell;
    private final int theXCoordinateOfTheCell;
    private int counterOfTheCellSelectedByTheUser = 0;

    public Judge(int heightY, int widthX, int theYCoordinateOfTheCell, int theXCoordinateOfTheCell) {
        this.heightY = heightY;
        this.widthX = widthX;
        this.theYCoordinateOfTheCell = theYCoordinateOfTheCell;
        this.theXCoordinateOfTheCell = theXCoordinateOfTheCell;
    }

    public int getCounterOfTheCellSelectedByTheUser() {
        return counterOfTheCellSelectedByTheUser;
    }

    public void createsTheNextGeneration(Cell[][] grid) {
        for (int row = 0; row < heightY; row++) {
            for (int cell = 0; cell < widthX; cell++) {
                Cell currentCell = grid[row][cell];
                if (currentCell.getColorAsInt() == RED_COLOR) {
                    int numberOfGreenCells = numberOfGreenCellsAroundTheCurrent(row, cell, grid);
                    // The first and second rule
                    if (numberOfGreenCells == 3 || numberOfGreenCells == 6) {
                        grid[row][cell].setHasChange(true); // change GREEN_COLOR
                    }
                } else {
                    int numberOfGreenCells = numberOfGreenCellsAroundTheCurrent(row, cell, grid);
                    // The third and fourth rule
                    if (numberOfGreenCells != 2 && numberOfGreenCells != 3 && numberOfGreenCells != 6) {
                        grid[row][cell].setHasChange(true); // change RED_COLOR
                    }
                }
            }
        }
        this.changeOfColor(grid);
    }

    private void changeOfColor(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].isHasChange()) {
                    if (grid[i][j].getColorAsInt() == GREEN_COLOR) {
                        grid[i][j].setColorAsInt(RED_COLOR);
                        grid[i][j].setHasChange(false);
                    } else {
                        grid[i][j].setColorAsInt(GREEN_COLOR);
                        grid[i][j].setHasChange(false);
                    }
                }
            }
        }
    }

    private static int numberOfGreenCellsAroundTheCurrent(int row, int cell, Cell[][] grid) {
        int counterOfGreenCells = 0;
        if (row - 1 >= 0 && cell - 1 >= 0) {
            if (grid[row - 1][cell - 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (row - 1 >= 0) {
            if (grid[row - 1][cell].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (row - 1 >= 0 && cell + 1 < grid[0].length) {
            if (grid[row - 1][cell + 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (cell - 1 >= 0) {
            if (grid[row][cell - 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (cell + 1 < grid[0].length) {
            if (grid[row][cell + 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (row + 1 < grid.length && cell - 1 >= 0) {
            if (grid[row + 1][cell - 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (row + 1 < grid.length) {
            if (grid[row + 1][cell].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        if (row + 1 < grid.length && cell + 1 < grid[0].length) {
            if (grid[row + 1][cell + 1].getColorAsInt() == GREEN_COLOR) {
                counterOfGreenCells++;
            }
        }
        return counterOfGreenCells;
    }

    public void checkForGreenColorOfTheCell(Cell[][] grid) {
        if (grid[this.theYCoordinateOfTheCell][this.theXCoordinateOfTheCell].getColorAsInt() == GREEN_COLOR) {
            this.counterOfTheCellSelectedByTheUser++;
        }
    }
}
