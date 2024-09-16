public class Brick {
    private int row, col;
    private char[][] grid;

    public Brick(int row, int col) {
        this.row = row;
        this.col = col;
        this.grid = new char[row][col];

        // Initialize the grid with dots
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void write() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void addStone(Stone stone) {
        StoneType type = stone.getStoneType();

        if (type.equals(StoneType.LARGE) || type.equals(StoneType.MEDIUM) || type.equals(StoneType.SMALL)) {
            placeHorizontal(stone);
        } else if (type.equals(StoneType.VERTICAL)) {
            placeVertical(stone);
        } else if (type.equals(StoneType.ANGLE)) {
            placeAngle(stone);
        } else {
            System.out.println("Invalid orientation for stone placement!");
        }
    }

    // Place a horizontal stone
    private void placeHorizontal(Stone stone) {
        StoneType type = stone.getStoneType();

        // Check if position is valid
        for (int i = stone.getX(); i < stone.getX() + type.getSize(); i++) {
            if (i >= col || stone.getY() >= row) {
                System.out.println("Position out of bounds! Stone cannot be placed.");
                return;
            }
            if (grid[stone.getY()][i] == 'X') {
                System.out.println("Stone already exists at position (" + stone.getX() + ", " + stone.getY() + ")!");
                return;
            }
        }

        // Place the stone
        for (int i = stone.getX(); i < stone.getX() + type.getSize(); i++) {
            if (i < col && stone.getY() < row) {
                grid[stone.getY()][i] = 'X';
            }
        }
    }

    // Place a vertical stone
    private void placeVertical(Stone stone) {
        StoneType type = stone.getStoneType();

        // Check if position is valid
        for (int i = stone.getY(); i < stone.getY() + type.getSize(); i++) {
            if (stone.getX() >= col || i >= row) {
                System.out.println("Position out of bounds! Stone cannot be placed.");
                return;
            }
            if (grid[i][stone.getX()] == 'X') {
                System.out.println("Stone already exists at position (" + stone.getX() + ", " + stone.getY() + ")!");
                return;
            }
        }

        // Place the stone
        for (int i = stone.getY(); i < stone.getY() + type.getSize(); i++) {
            if (i < row && stone.getX() < col) {
                grid[i][stone.getX()] = 'X';
            }
        }
    }

    // Place an angle (L-shaped) stone
    private void placeAngle(Stone stone) {
        StoneType type = stone.getStoneType();
        int size = type.getSize();

        // Assuming an L-shape, we'll place `size` stones horizontally and `size` stones vertically forming an L.
        // Check if position is valid for the horizontal part
        for (int i = stone.getX(); i < stone.getX() + size; i++) {
            if (i >= col || stone.getY() >= row) {
                System.out.println("Position out of bounds! Stone cannot be placed.");
                return;
            }
            if (grid[stone.getY()][i] == 'X') {
                System.out.println("Stone already exists at position (" + stone.getX() + ", " + stone.getY() + ")!");
                return;
            }
        }

        // Check if position is valid for the vertical part
        for (int i = stone.getY(); i < stone.getY() + size; i++) {
            if (stone.getX() >= col || i >= row) {
                System.out.println("Position out of bounds! Stone cannot be placed.");
                return;
            }
            if (grid[i][stone.getX()] == 'X') {
                System.out.println("Stone already exists at position (" + stone.getX() + ", " + stone.getY() + ")!");
                return;
            }
        }

        // Place the horizontal part of the L-shape
        for (int i = stone.getX(); i < stone.getX() + size; i++) {
            if (i < col && stone.getY() < row) {
                grid[stone.getY()][i] = 'X';
            }
        }

        // Place the vertical part of the L-shape
        for (int i = stone.getY(); i < stone.getY() + size; i++) {
            if (i < row && stone.getX() < col) {
                grid[i][stone.getX()] = 'X';
            }
        }
    }

    // Check if the specified row is fully filled with 'X'
    public boolean isRowFilled(int rowIndex) {
        for (int j = 0; j < col; j++) {
            if (grid[rowIndex][j] != 'X') {
                return false;
            }
        }
        return true;
    }

    // Check if the specified column is fully filled with 'X'
    public boolean isColumnFilled(int colIndex) {
        for (int i = 0; i < row; i++) {
            if (grid[i][colIndex] != 'X') {
                return false;
            }
        }
        return true;
    }

    // Count how many rows and columns are fully filled with 'X'
    public boolean isFilledRowsAndColumns() {
        for (int i = 0; i < row; i++) {
            if (isRowFilled(i)) {
                return true;
            }
        }

        for (int j = 0; j < col; j++) {
            if (isColumnFilled(j)) {
                return true;
            }
        }
        return false;
    }

    // New implementation for rewriting filled rows/columns
    public void rewrite() {
        int count = 0;

        // Clear fully filled rows
        for (int i = 0; i < row; i++) {
            if (isRowFilled(i)) {
                for (int j = 0; j < col; j++) {
                    grid[i][j] = '.'; // Replace all characters in the filled row with dots
                }
                count++;
            }
        }

        // Clear fully filled columns
        for (int j = 0; j < col; j++) {
            if (isColumnFilled(j)) {
                for (int i = 0; i < row; i++) {
                    grid[i][j] = '.'; // Replace all characters in the filled column with dots
                }
                count++;
            }
        }

        // Output the rewritten grid
        write();

        // Print the total success count (total number of cleared rows and columns)
        System.out.println("Total success: " + count);
    }
}
