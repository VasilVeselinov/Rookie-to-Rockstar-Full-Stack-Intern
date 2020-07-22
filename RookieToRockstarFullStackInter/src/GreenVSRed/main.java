package GreenVSRed;

import java.util.Random;
import java.util.Scanner;

public class main {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 1000;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        // The user enters a width and height
        String[] line1;
        int widthX = 0;
        int heightY = 0;
        do {
            System.out.println("Please enter a width and height separated by comma (1,1000):");
            line1 = userInput.nextLine().split(",");
            if (line1.length < 2) {
                System.out.println("Please try again!");
                continue;
            }
            widthX = Integer.parseInt(line1[0].trim());
            heightY = Integer.parseInt(line1[1].trim());
        } while (widthX < MIN_SIZE || widthX > MAX_SIZE || heightY < MIN_SIZE || heightY > MAX_SIZE);
        // Creating a "Generation Zero" grid
        Cell[][] grid = createsGenerationZeroGrid(heightY, widthX);
        // Printing of the grid
        printsGenerationZeroGrid(grid);
        // The user enters coordinates of cell and number of the generations
        String[] nestLineForInput;
        int theXCoordinateOfTheCell = 0;
        int theYCoordinateOfTheCell = 0;
        int theNumberOfTheGenerations = 0;
        do {
            System.out.println("Please enter coordinates of cell and number of the generations separated by commas:");
            nestLineForInput = userInput.nextLine().split(",");
            if (nestLineForInput.length < 3) {
                System.out.println("Please try again!");
                continue;
            }
            theXCoordinateOfTheCell = Integer.parseInt(nestLineForInput[0].trim());
            theYCoordinateOfTheCell = Integer.parseInt(nestLineForInput[1].trim());
            theNumberOfTheGenerations = Integer.parseInt(nestLineForInput[2].trim());
        } while (theXCoordinateOfTheCell < 0
                || theXCoordinateOfTheCell >= widthX
                || theYCoordinateOfTheCell < 0
                || theYCoordinateOfTheCell >= heightY
                || theNumberOfTheGenerations <= 0
        );
// todo delete
//        grid = new Cell[][]{{new Cell(0), new Cell(0), new Cell(0)},
//                {new Cell(1), new Cell(1), new Cell(1)},
//                {new Cell(0), new Cell(0), new Cell(0)}};

        grid = new Cell[][]{{new Cell(1), new Cell(0), new Cell(0), new Cell(1)},
                {new Cell(1), new Cell(1), new Cell(1), new Cell(1)},
                {new Cell(0), new Cell(1), new Cell(0), new Cell(0)},
                {new Cell(1), new Cell(0), new Cell(1), new Cell(0)}};
        printsGenerationZeroGrid(grid);
        // Create of the Judge to take care of the rules
        Judge judge = new Judge(heightY, widthX, theYCoordinateOfTheCell, theXCoordinateOfTheCell);
        // Check for the green color of the cell
        judge.checkForGreenColorOfTheCell(grid);
        // Cycle for new generations
        for (int generation = 0; generation < theNumberOfTheGenerations; generation++) {
            // Creating of next generation by the four rules
            judge.createsTheNextGeneration(grid);
            // Check for the green color of the cell
            judge.checkForGreenColorOfTheCell(grid);
        }
        // The Judge shows the result
        System.out.println("result: " + judge.getCounterOfTheCellSelectedByTheUser());
    }

    private static Cell[][] createsGenerationZeroGrid(int heightY, int widthX) {
        Cell[][] grid = new Cell[heightY][widthX];
        Random randomGreenOrRed = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Cell(randomGreenOrRed.nextInt(2));
            }
        }
        return grid;
    }

    private static void printsGenerationZeroGrid(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].getColorAsInt());
            }
            System.out.println();
        }
    }
}
