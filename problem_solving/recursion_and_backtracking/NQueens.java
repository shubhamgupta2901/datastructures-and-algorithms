package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer A, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 *
 * Input Format:
 * First argument A is an integer n denoting the size of chessboard
 * 1 <= A <= 10
 *
 * Output Format:
 * Return an array consisting of all distinct solutions in which each element is a 2d char array representing a unique solution.
 *
 * Example:
 * A = 4
 * Output:
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class NQueens {

    ArrayList<ArrayList<String>> possibleBoards;
    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        //todo: handle edge cases
        if(a == 0)
            return possibleBoards;


        possibleBoards = new ArrayList<>();
        helper(createEmptyBoard(a), 0);
        return possibleBoards;
    }

    private void helper(ArrayList<String> board, int row){
        if(row == board.size()){
            ArrayList<String> newBoard = new ArrayList<>(board);
            possibleBoards.add(newBoard);
            return;
        }
        char[] boardRow = board.get(row).toCharArray();
        for(int i = 0; i<boardRow.length; i++){
            if(canPlace(board, row, i)){
                boardRow[i] = 'Q';
                board.set(row, new String(boardRow));
                helper(board, row+1);
                boardRow[i] = '.';
                board.set(row, new String(boardRow));
            }
        }
    }

    private boolean canPlace(ArrayList<String> board, int row, int column){
        // return true if Q can be placed at board[r][c]
        // board[r][c] -> board.get(row).charAt(c)
        //check for (vertical) column
        int R = board.size();
        int C= board.get(0).length();

        for(int r = 0; r<R; r++){
            if(board.get(r).charAt(column) == 'Q')
                return false;
        }

        int r, c;

        //check for Top left diagonal
        r = row-1;
        c = column-1;
        while(r>=0 && c>=0){
            if(board.get(r).charAt(c) == 'Q')
                return false;
            r--;
            c--;
        }
        //check for bottom right diagonal
        r = row+1;
        c = column+1;
        while(r<R && c<C){
            if(board.get(r).charAt(c) == 'Q')
                return false;
            r++;
            c++;
        }
        //check for bottom left diagonal

        r = row+1;
        c = column-1;
        while(r<R && c>=0){
            if(board.get(r).charAt(c) == 'Q')
                return false;
            r++;
            c--;
        }
        //check for top right diagonal
        r = row-1;
        c = column+1;
        while(r>=0 && c<C){
            if(board.get(r).charAt(c) == 'Q')
                return false;
            r--;
            c++;
        }

        return true;
    }

    private ArrayList<String> createEmptyBoard(int a){
        ArrayList<String> board = new ArrayList<String>();
        for(int i = 0; i<a; i++){
            char[] row = new char[a];
            for(int j = 0; j<a; j++)
                row[j] = '.';
            board.add(new String(row));
        }
        return board;
    }


}