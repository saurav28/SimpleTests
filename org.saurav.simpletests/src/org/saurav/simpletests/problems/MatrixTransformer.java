package org.saurav.simpletests.problems;


/**
 * This class transforms a matrix from one form to another
 * If the input of matrix contains a "1" in its cell, then the whole row and column
 * containing the "1" is filled with ones.
 *
 */
public class MatrixTransformer {
	
	

    private int[][] matrix;
    private int rowNum;
    private int colNum;

    public MatrixTransformer(int[][] matrix, int rowNum, int colNum) {
        this.matrix = matrix;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] a = {{0, 0, 0, 0}, {0, 1, 0, 0},{0, 0, 0, 0},{0, 0, 0, 1,}}; //intialize the array
        MatrixTransformer mt = new MatrixTransformer(a, 4, 4);
        mt.transform();
        System.out.println(mt);
    }
    /**
     * Scans the ith row
     * @param i
     * @return
     */
    private int scanRow(int i) {
        int hasZero = 0;
        for(int k = 0;k < colNum;k++)
            if (matrix[i][k] == 1) {
                hasZero = 1;
                break;
            }

        return hasZero;
    }
    /**
     * Scans the jth column
     * @param j
     * @return
     */
    private int scanColumn(int j) {
        int hasZero = 0;
        for(int k = 0;k < rowNum;k++)
            if (matrix[k][j] == 1) {
                hasZero = 1;
                break;
            }

        return hasZero;
    }

    private void setRowToAllOnes(int i) {
        for(int k = 0; k < colNum;k++)
            matrix[i][k] = 1;
    }

    private void setColToAllOnes(int j) {
        for(int k = 0; k < rowNum;k++)
            matrix[k][j] = 1;
    }

    /**
     * The first row and columns holds the indicator whether the row and columns holds
     * a one or not.
     */
    public void transform() {
        int firstRow = scanRow(0);
        int firstCol = scanColumn(0);

        //scan each column and store the result in 1st row
        for(int k = 0;k < colNum;k++) {
            matrix[0][k] = scanColumn(k);
        }

        
        //scan each row and store the result in 1st column
        for(int k = 0;k < rowNum;k++) {
            matrix[k][0] = scanRow(k);
        }
        //check if the first column or first row itself contains a 1.
        matrix[0][0] = firstCol | firstRow;

        //Which ever row or column contains a 1, fill the whole row and column with ones
        for (int i = 1;i < rowNum;i++)
            for(int j = 1;j < colNum;j++)
                matrix[i][j] = matrix[0][j] | matrix[i][0];


        //if the first row has one then set all ones to the first row
        if (firstRow == 1) {
            setRowToAllOnes(0);
        }

        //if the first column has one then all ones to the first column
        if (firstCol == 1) 
            setColToAllOnes(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i< rowNum;i++) {
            for(int j = 0;j < colNum;j++) {
                sb.append(matrix[i][j] + ", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
  

}