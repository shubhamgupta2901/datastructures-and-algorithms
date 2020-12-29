package problem_solving.recursion_and_backtracking;

public class VerticalAndHorizontalSums {

    int[] rowSum, columnSum;
    int target;
    int rows, columns;
    public int solve(int A, int[][] B, int C) {
        //todo:handle edge cases
        target = C;
        rows = B.length;
        columns = B[0].length;
        rowSum = new int[rows];
        columnSum = new int[columns];
        prefilSums(B);
        boolean possible = helper(B, 0, 0, A);
        return possible ? 1 : 0;
    }

    private boolean helper(int[][] matrix, int row, int column, int operations){
        if(operations<0 || row >= rows)
            return false;
        if(isValid())
            return true;
        int newColumn = column < columns-1 ? column+1 : 0;
        int newRow = column < columns-1 ? row : row+1;
        boolean possible = helper(matrix, newRow, newColumn, operations);
        if(possible)
            return true;

        updateSum(matrix, row, column);
        possible = helper(matrix, newRow, newColumn, operations-1);
        updateSum(matrix, row, column);
        return possible;
    }


    private boolean isValid(){
        for(int i = 0; i<rowSum.length; i++){
            if(rowSum[i]>target)
                return false;
        }

        for(int i = 0; i<columnSum.length; i++){
            if(columnSum[i]>target)
                return false;
        }
        return true;

    }
    private void updateSum(int[][] matrix, int row, int column){
        //update sum of rowSum[row] and columnSum[column] when matrix[row][column] is multiplied by -1;
        rowSum[row]  -=  2*matrix[row][column];
        columnSum[column] -=  2*matrix[row][column];
        matrix[row][column] = -1 * matrix[row][column];
    }

    private void prefilSums(int[][] matrix){
        //row[i] will have sum of all elements in matrix[i]
        //column[i] will have sum of all elements in matrix[][i]
        for(int i = 0; i< rows; i++){
            for(int j = 0; j<columns; j++){
                rowSum[i]+= matrix[i][j];
                columnSum[j]+= matrix[i][j];
            }
        }
    }

    public static void main(String[] args) {
        VerticalAndHorizontalSums driver = new VerticalAndHorizontalSums();
        int[][]matrix = new int[][]{{-4, 8, -3, 1},{-6, -1, 9, -6 },{-1, -8, 7, -6}};
        int value = driver.solve(2,matrix,8);
        System.out.println(value);
    }
}
