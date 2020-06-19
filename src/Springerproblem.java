import java.util.*;

/**
 * The Springerproblem.
 */
public class Springerproblem {
    /**
     * The size of the playground.
     */
    private int fieldSize;
    /**
     * The start position field.
     */
    private Field startPosfield;
    /**
     * The solution typ classic or simple.
     */
    private int solutionType;
    /**
     * The Set of solutions, with all the possible solutions.
     */
    private Set<List<Field>> solutions;
    /**
     * The amount of solutions to be printed.
     */
    private int solutionAmount;

    /**
     * The playground.
     */
    private Field[][] fields;

    /**
     * moveNumber and cornerNumber responsible for checking if a possible solution was found.
     */
    private int moveNumber, cornerNumber;
    /**
     * The currently used path.
     */
    private List<Field> currentPath;

    /**
     * The constructor of the Springerproblem.
     * Used to create a playground, the corresponding Fields and initialise the variables, and to choose the solution typ
     *
     * @param fieldSize chess board size
     * @param solutionAmount how much solutions that the user want
     * @param solutionTyp if the user need the all solutions or some known.
     */
    public Springerproblem(int fieldSize, int solutionAmount,int solutionTyp) {
        this.fieldSize = fieldSize;
        this.solutionAmount = solutionAmount;
        this.solutionType = solutionTyp;

        this.moveNumber = 0;
        this.currentPath = new ArrayList<>();
        this.solutions = new HashSet<>();
        this.cornerNumber = 0;

        this.fields = new Field[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                fields[i][j] = new Field(i, j);
                if (i == 0 && j == fieldSize - 1 || i == 0 && j == 0 || i == fieldSize - 1 && j == 0 || i == fieldSize - 1 && j == fieldSize - 1) {
                    fields[i][j].markAsCorner();
                }
            }
        }
    }

    /**
     * Used to start the simulation, by providing a StartField in Chess notation and the version of the problem to be solved.
     *
     * @param column start Position in column
     * @param row start Position in row
     * @param version typ of the variant
     */
    public void start(char column, int row, Version version) {
        Field startField;
        startPosfield = getFieldFromChar(column, row);
        if ((startField = this.getFieldFromChar(column, row)) == null) {
            System.err.println("The StartField " + column + row + " doesn't exist. Please choose different.");
            System.exit(1);
        }
        if (version.equals(Version.S)) {
            this.findWayEasy(startField);
        } else if (version.equals(Version.C)) {
            this.findWayClassic(startField);
        }
        if(solutions.size() < solutionAmount && solutionType == 1){
          printSolutions(solutionAmount , solutionType);
            System.exit(6);
        }
        if (solutionType !=1) printSolutions(solutionAmount,solutionType);
    }

    /**
     * Used to print out the amount of solutions wanted.
     *
     * @param solutionAmount
     */
    private void printSolutions(int solutionAmount , int typ) {
        System.out.println("Solutions " + solutions.size());
        if (solutionAmount > solutions.size()) {
            System.err.println("Only " + solutions.size() + " possible solutions, not " + solutionAmount + " as desired.");
        }
        int i = 1;
        for (List<Field> list : solutions) {
            if (!(i > solutions.size()) && typ ==1 ?!(i > solutionAmount): true) {

                System.out.println("Solution " + i);
                System.out.print("\t");
                for (Field f : list) {
                    System.out.print(f.toString() + " - ");
                }
                System.out.print("\n");
                i++;
            }
        }
    }

    /**
     * Used to get all possible moves from a position.
     *
     * @param field
     * @return List<Field>
     */
    private List<Field> getPossibleMoves(Field field) {
        List<Field> result = new ArrayList<>();
        int relativeIndices[][] = {{-1, -2}, {1, -2}, {2, -1}, {1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {-2, -1}};
        for (int[] way : relativeIndices) {
            if (getField(field.getX() + way[0], field.getY() + way[1]) != null) {
                if (!(getField(field.getX() + way[0], field.getY() + way[1]).isVisited())) {
                    result.add(getField(field.getX() + way[0], field.getY() + way[1]));
                }
            }
        }
        return result;
    }

    /**
     * Used to get all possible moves from a position to get back to start position.
     *
     * @param field
     * @return List<Field>
     */
    private List<Field> getPossibleBack(Field field) {
        List<Field> result = new ArrayList<>();
        int relativeIndices[][] = {{-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};
        for (int[] delta : relativeIndices) {
            if (getField(field.getX() + delta[0], field.getY() + delta[1]) != null) {
                result.add(getField(field.getX() + delta[0], field.getY() + delta[1]));

            }
        }
        return result;
    }

    /**
     * Recursive function to find every solution to the Springerproblem the easy way.
     * Only need to provide the current Field.
     *
     * @param currentField
     */
    private void findWayEasy(Field currentField) {
        if (currentField.isCorner()) {
            this.cornerNumber++;
        }
        this.currentPath.add(currentField);
        currentField.markAsVisited();
        if (this.hasSolutionSimple() && filterSulotion(this.currentPath, startPosfield)) {
            List<Field> copy = new ArrayList<>(this.currentPath);

         this.solutions.add(copy);
            if (solutions.size() == solutionAmount && solutionType == 1 ) {
                printSolutions(solutionAmount ,solutionType);
                System.out.println("Simple variant has been used *exit code 5* ");
                System.exit(5);
            }




        } else {
            List<Field> possibleMoves = this.getPossibleMoves(currentField);
            for (Field newField : possibleMoves) {
                this.findWayEasy(newField);
            }
        }
        if (currentField.isCorner()) {
            this.cornerNumber--;
        }
        this.currentPath.remove(currentField);
        currentField.markAsUnvisited();
    }

    /**
     * Recursive function to find every solution to the Springerproblme the normal way.
     * Only need to provide the current Field.
     *
     * @param currentField
     */
    private void findWayClassic(Field currentField) {
        this.moveNumber++;
        this.currentPath.add(currentField);
        currentField.markAsVisited();
        if (this.hasSolutionClassic()) {
            if (filterSulotion(this.currentPath, startPosfield)) {
                List<Field> copy = new ArrayList<>(this.currentPath);

                this.solutions.add(copy);
                if (solutions.size() == solutionAmount && solutionType == 1) {
                    printSolutions(solutionAmount , solutionType);
                    System.out.println("Classic variant has been used *exit code 2* ");
                    System.exit(2);
                }
            }

        } else {
            List<Field> possibleMoves = this.getPossibleMoves(currentField);
            for (Field newField : possibleMoves) {
                this.findWayClassic(newField);
            }
        }
        this.moveNumber--;
        this.currentPath.remove(currentField);
        currentField.markAsUnvisited();
    }

    /**
     * Used to check if the current path is a solution in the normal version.
     *
     * @return
     */
    private boolean hasSolutionClassic() {
        return moveNumber == this.getFieldSize() * this.getFieldSize();
    }

    /**
     * Used to check if the current path is a solution in the easy version.
     *
     * @return
     */
    private boolean hasSolutionSimple() {
        return cornerNumber >= 4;
    }

    /**
     * Used to get all the solutions.
     *
     * @return
     */
//    private Set<List<Field>> getSolutions() {
//        return this.solutions;
//    }

    /**
     * Used to get a desired Field, by providing Chess notation.
     *
     * @param c
     * @param x
     * @return a Field
     */
    private Field getFieldFromChar(char c, int x) {
        int y = Field.getNumberFromChar(c);
        y--;
        x--;
        if (x >= 0 && x < this.fieldSize && y >= 0 && y < this.fieldSize) {
            return fields[x][y];
        } else {
            return null;
        }
    }

    /**
     * Used to get a desired Field, by providing two numbers.
     *
     * @param x
     * @param y
     * @return
     */
    private Field getField(int x, int y) {
        if (x >= 0 && x < this.fieldSize && y >= 0 && y < this.fieldSize) {
            return fields[x][y];
        } else {
            return null;
        }
    }

    /**
     * Used to get the field size.
     *
     * @return
     */
    private int getFieldSize() {
        return fieldSize;
    }

    public boolean filterSulotion(List<Field> path, Field startField) {
        List<Field> possibleBackField = getPossibleBack(startField);
        for (Field f : possibleBackField) {
            int s = path.size() - 1;
            if (path.get(s) == f) {
                return true;
            }
        }
        return false;
    }
}
