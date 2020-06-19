/**
 * The Field class.
 */
public class Field {
    /**
     * x and y coordinate of the Field.
     */
    private int x, y;
    /**
     * indicates if the Field is a corner.
     */
    private boolean isCorner;
    /**
     * indicates if the Field has already bin visited.
     */
    private boolean visited;

    /**
     * The constructor of Field.
     * User to create a Field instance, when provided with x and y coordinates.
     *
     * @param x x position
     * @param y y  position
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.isCorner = false;
    }



    /**
     * Returns the x coordinate.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Markes the Field as a corner.
     */
    public void markAsCorner() {
        this.isCorner = true;
    }

    /**
     * Checks if Field is corner.
     *
     * @return true if Field is corner, false otherwise
     */
    public boolean isCorner() {
        return isCorner;
    }

    /**
     * Checks if Field is allready visited.
     *
     * @return true if Field is visited, false otherwise
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Markes the Field as visited.
     */
    public void markAsVisited() {
        this.visited = true;
    }

    /**
     * Markes the Field as unvisited.
     */
    public void markAsUnvisited() {
        this.visited = false;
    }

    /**
     * toString
     *
     * @return string
     */
    public String toString() {
        return (x+1) + "" + getCharForNumber(y);
    }

    /**
     * Converts from char to corresponding number.
     * a return like 1
     * b return like 2
     * And so on.
     *
     * @param c read a char to get value in numbers
     * @return int
     */
    public static int getNumberFromChar(char c) {
        switch (c) {
            case ('a'):
                return 1;
            case ('b'):
                return 2;
            case ('c'):
                return 3;
            case ('d'):
                return 4;
            case ('e'):
                return 5;
            case ('f'):
                return 6;
        }
        return 0;
    }

    /**
     * Converts from number to corresponding char.
     * a -> 1
     * b -> 2
     * And so on.
     *
     * @param i
     * @return char
     */
    private String getCharForNumber(int i) {
        switch (i) {
            case (0):
                return "a";
            case (1):
                return "b";
            case (2):
                return "c";
            case (3):
                return "d";
            case (4):
                return "e";
            case (5):
                return "f";
        }
        return null;
    }
}
