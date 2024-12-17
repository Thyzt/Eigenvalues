import java.io.File;
import java.util.Scanner;

// Matrix Class
public class Matrix {

    // Variables
    private double[][] mat = new double[100][100];
    private int rowSize;
    private int colSize;

    /* Constructor */
    public Matrix() {

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mat[i][j] = 0;
            }
        }

        this.rowSize = 0;
        this.colSize = 0;

    }

    public Matrix(int newRowSize, int newColSize) {

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mat[i][j] = 0;
            }
        }

        this.rowSize = newRowSize;
        this.colSize = newColSize;

    }

    public Matrix(Matrix M) {

        this.rowSize = M.rowSize;
        this.colSize = M.colSize;

        for(int i = 0; i < this.rowSize; i++){
            for(int j = 0; j < this.colSize; j++) {
                this.mat[i][j] = M.mat[i][j];
            }
        }

    }

    /* Setter */
    public void setRowSize(int n) {
        this.rowSize = n;
    }

    public void setColSize(int n) {
        this.colSize = n;
    }

    /* Getter */
    public int getRowSize() {
        return this.rowSize;
    }

    public int getColSize() {
        return this.colSize;
    }

    private double power(double x, int power) {

        double result = 1.0;
        for(int i = 0; i < power; i ++) {
            result *= x;
        }
        return result;
    }

    /*
     *  MARK OPERATIONS
     */

    private void clear() {
        this.rowSize = 0;
        this.colSize = 0;
    }

    // Find a row with the max value in a particular column from a particular row
    private int getMaxAbsColIndex(int currentRow, int currentCol){

        // Initiate Maximum
        int maxRowIndex = currentRow;

        // Searching for max
        for (int i = currentRow; i < this.rowSize; i ++) {
            if (Math.abs(this.mat[i][currentCol]) > Math.abs(this.mat[maxRowIndex][currentCol])) {
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }

    // Swap two rows
    private void swapRow(int row1Index, int row2Index) {

        // Temporary row
        double[] tempRow = new double[this.colSize];

        // Swapping
        for (int j = 0; j < this.colSize; j++) {
            tempRow[j] = this.mat[row1Index][j];
        }
        for (int j = 0; j < this.colSize; j++) {
            this.mat[row1Index][j] = this.mat[row2Index][j];
        }
        for (int j = 0; j < this.colSize; j++) {
            this.mat[row2Index][j] = tempRow[j];
        }

    }

    // Pivoting Point
    private void moveUpPivot(int currentRow, int currentCol) {

        // Find max
        int pivotRow = this.getMaxAbsColIndex(currentRow, currentCol);

        // Swapping
        this.swapRow(currentRow, pivotRow);
    }

    // Divide Row
    private void divideRow(int i, double divider) {

        for(int j = 0; j < this.colSize; j++) {
            this.mat[i][j] /= divider;
        }

    }

    // Row Substraction (R1 - R2)
    private void substractRow(int i1, int i2, double multiply) {

        for(int j = 0; j < this.colSize; j++) {
            this.mat[i1][j] -= (this.mat[i2][j] * multiply);
        }

    }

    // Returns true if a row is zero row
    private boolean isZeroRow(int currentRow) {

        for(int i = 0; i < this.colSize; i++) {
            if (this.mat[currentRow][i] != 0) {
                return false;
            }
        }

        return true;

    }

    // Returns true if the matrix has a zero row
    private boolean hasZeroRow() {

        for(int i = 0; i < this.rowSize; i++) {
            if (this.isZeroRow(i) == true) {
                return true;
            }
        }

        return false;

    }

    // Returns true if a row is weird row
    private boolean isWeirdRow(int currentRow) {

        for(int i = 0; i < this.colSize - 1; i++) {
            if (this.mat[currentRow][i] != 0) {
                return false;
            }
        }

        if (this.mat[currentRow][this.colSize - 1] == 0) {
            return false;
        }

        return true;

    }

    // Returns true if the matrix has a weird row
    private boolean hasWeirdRow() {

        for(int i = 0; i < this.rowSize; i++) {
            if (this.isWeirdRow(i) == true) {
                return true;
            }
        }

        return false;

    }

    // Returns true if there are no solutions for the equation system
    private boolean isNoSolution() {

        return (this.hasWeirdRow());

    }

    // Returns true if there aren't enough equations to solve all variables
    private boolean isInfiniteSolution() {

        return (this.rowSize < (this.colSize - 1));

    }

    // Returns the index of leading one in a row
    private int getLeadingOneIndex(int currentRow) {

        boolean isZero = true;
        for (int i = 0; i < this.colSize - 1; i ++) {
            if (this.mat[currentRow][i] == 1.0) {
                return i;
            }
        }

        return -1;

    }

    // Returns the index of a row with leading one at a given column
    private int getRowIndexWithLeadingOneAt(int leadingOneIndex) {

        for (int i = 0; i < this.rowSize; i ++) {
            if (this.getLeadingOneIndex(i) == leadingOneIndex) {
                return i;
            }
        }

        return -1;

    }

    private void deleteRow(int currentRow) {

        for (int i = currentRow; i < this.rowSize - 1; i ++) {
            for (int j = 0; j < this.colSize; j ++) {
                this.mat[i][j] = this.mat[i + 1][j];
            }
        }
        this.rowSize --;

    }

    private void deleteZeroRows() {

        for (int i = this.rowSize - 1; i >= 0; i --) {
            if (isZeroRow(i)) {
                deleteRow(i);
            }
        }

    }

    private void gaussJordanElimination() {

        int currentBaseRow = 0;
        int currentBaseCol = 0;
        int currentRow;

        for (; (currentBaseRow < this.rowSize) && (currentBaseCol < this.colSize - 1); currentBaseRow ++) {

            // Choose pivot and move it up, skip if pivot is 0
            this.moveUpPivot(currentBaseRow, currentBaseCol);

            while ((this.mat[currentBaseRow][currentBaseCol] == 0) && (currentBaseCol < this.colSize - 1)) {
                currentBaseCol ++;
                this.moveUpPivot(currentBaseRow, currentBaseCol);
            }

            if (currentBaseCol > this.colSize - 2) {
                break;
            }

            double pivotDivider = this.mat[currentBaseRow][currentBaseCol];
            this.divideRow(currentBaseRow, pivotDivider);

            // Gauss Elimination
            for (currentRow = currentBaseRow + 1; currentRow < this.rowSize; currentRow ++) {
                double multiplier = this.mat[currentRow][currentBaseCol];
                substractRow(currentRow, currentBaseRow, multiplier);
            }

            // Jordan Elimination
            for (currentRow = currentBaseRow - 1; currentRow >= 0; currentRow --) {
                double multiplier = this.mat[currentRow][currentBaseCol];
                substractRow(currentRow, currentBaseRow, multiplier);
            }

            currentBaseCol ++;

        }
    }

    /*
     *  MARK READ
     */

    // Read Matrix from shell
    public void read() {

        Scanner scan = new Scanner(System.in);

        // Read Row
        System.out.println();
        System.out.print("Enter the number of rows: ");
        this.rowSize = scan.nextInt();

        // Read Column
        System.out.print("Enter the number of columns: ");
        this.colSize = scan.nextInt();

        // Read element
        System.out.println();
        System.out.println("Input the matrix: ");
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                this.mat[i][j] = scan.nextDouble();
            }
        }
    }

    public void readFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter the external file name: ");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.next().equals("ROW")) {
                this.rowSize = reader.nextInt();
            } else {
                System.out.println();
                System.out.println("Invalid external file format.");
                return;
            }
            if (reader.next().equals("COLUMN")) {
                this.colSize = reader.nextInt();
            } else {
                System.out.println();
                System.out.println("Invalid external file format.");
                return;
            }

            try {
                for (int row = 0; row < this.rowSize; row++) {
                       for (int col = 0; col < this.colSize; col++) {
                           this.mat[row][col] = reader.nextDouble() ;
                      }
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Invalid external file format.");
                this.clear();
                return;
            }

            reader.close();
            System.out.println();
            System.out.println("Successfully read from the file '" + fileName + "'.");

        } catch (Exception i) {
            System.out.println();
            System.out.println("Unable to read from the external file.");
        }

    }

    public void readForHilbert() {

        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter the dimensions of the Hilbert matrix: ");
        this.rowSize = scan.nextInt();
        this.colSize = this.rowSize + 1;

        for (int i = 0; i < this.rowSize; i ++) {
            for (int j = 0; j < this.colSize - 1; j ++) {
                this.mat[i][j] = 1.0 / (i + j + 1.0);
            }
        }

        for (int i = 0; i < this.rowSize; i ++) {
            this.mat[i][this.colSize - 1] = 1.0;
        }

    }

    public void readForHilbertFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter the external file name: ");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.next().equals("DIMENSION")) {
                this.rowSize = reader.nextInt();
                this.colSize = this.rowSize + 1;
            } else {
                System.out.println();
                System.out.println("Invalid external file format.");
                return;
            }

            for (int i = 0; i < this.rowSize; i ++) {
                for (int j = 0; j < this.colSize - 1; j ++) {
                    this.mat[i][j] = 1.0 / (i + j + 1.0);
                }
            }

            for (int i = 0; i < this.rowSize; i ++) {
                this.mat[i][this.colSize - 1] = 1.0;
            }

            reader.close();
            System.out.println();
            System.out.println("Successfully read from the file '" + fileName + "'.");

        } catch (Exception i) {
            System.out.println();
            System.out.println("Unable to read from the external file.");
        }

    }

    // Read Matrix from shell for interpolation
    public void readForInterpolation() {

        Scanner scan = new Scanner(System.in);
        double x, y;

        this.clear();

        // Read number of points
        while (this.rowSize < 1) {

            System.out.println();
            System.out.print("Enter the number of known points: ");
            this.rowSize = scan.nextInt();

            if (this.rowSize < 1) {
                System.out.println();
                System.out.println("The number of points must be greater than 0.");
            }

        }

        // Assign row size
        this.colSize = this.rowSize + 1;

        // Read points
        System.out.println();
        System.out.println("Input the points: ");
        for (int i = 0; i < this.rowSize; i++) {

            System.out.print("X" + (i + 1) + " = ");
            x = scan.nextDouble();
            System.out.print("f(X" + (i + 1) + ") = ");
            y = scan.nextDouble();

            for (int j = 0; j < this.colSize - 1; j ++) {
                this.mat[i][this.colSize - 2 - j] = power(x, j);
            }

            this.mat[i][this.colSize - 1] = y;

        }
    }

    public void readForInterpolationFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter the external file name: ");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            double x, y;
            String buffer;

            buffer = reader.next();
            if (buffer.equals("NUMBER")) {
                  this.rowSize = reader.nextInt();
                  this.colSize = this.rowSize + 1;
            } else {
                System.out.println();
                System.out.println("Invalid external file format.");
                return;
            }
            buffer = reader.nextLine();

            for (int i = 0; i < this.rowSize; i ++) {

                x = 0.0;
                y = 0.0;
                try {

                    x = reader.nextDouble();
                    y = reader.nextDouble();

                } catch (Exception ex) {
                    System.out.println();
                    System.out.println("Invalid external file format.");
                    return;
                }

                for (int j = 0; j < this.colSize - 1; j ++) {
                    this.mat[i][this.colSize - 2 - j] = power(x, j);
                }

                this.mat[i][this.colSize - 1] = y;

            }

            reader.close();
            System.out.println();
            System.out.println("Successfully read from the file '" + fileName + "'.");

        } catch (Exception e) {
            System.out.println();
            System.out.println("Unable to read from the external file.");
        }

    }

    public void readForInterpolationE() {

        Scanner scan = new Scanner(System.in);
