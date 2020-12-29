package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.'
 * You may assume that there will be only one unique solution.
 *
 */
public class Sudoku {

    private ArrayList<HashSet<Character>> rowSets, columnSets;
    private ArrayList<ArrayList<HashSet<Character>>> gridSets;
    public void solveSudoku(ArrayList<ArrayList<Character>> sudoku) {
        prefillSets(sudoku);
        solve(sudoku,0,0);
    }

    private boolean solve(ArrayList<ArrayList<Character>> sudoku,int row, int column){
        if(isSolved())
            return true;

        for(int i = row; i<9; i++){
            for(int j = column; j<9; j++){
                if(sudoku.get(i).get(j) != '.')
                    continue;
                for(int k = 1; k<=9; k++){
                    Character candidateCell = toCharacter(k);
                    if(rowSets.get(i).contains(candidateCell))
                        continue;
                    else if(columnSets.get(j).contains(candidateCell))
                        continue;
                    else if(gridSets.get(i/3).get(j/3).contains(candidateCell))
                        continue;
                    sudoku.get(i).set(j,candidateCell);
                    rowSets.get(i).add(candidateCell);
                    columnSets.get(j).add(candidateCell);
                    gridSets.get(i/3).get(j/3).add(candidateCell);

                    int newColumn = column<8? column+1 : 0;
                    int newRow = column<8? row: row+1;
                    boolean successful = solve(sudoku,newRow, newColumn);
                    if(successful)
                        return true;

                    sudoku.get(i).set(j,'.');
                    rowSets.get(i).remove(candidateCell);
                    columnSets.get(j).remove(candidateCell);
                    gridSets.get(i/3).get(j/3).remove(candidateCell);

                }
                return false;
            }
        }
        return true;
    }

    private boolean isSolved(){
        for(HashSet<Character> set: rowSets){
            if(set.size()<9)
                return false;
        }
        return true;
    }

    private void prefillSets(ArrayList<ArrayList<Character>> sudoku){
        rowSets = new ArrayList<HashSet<Character>>();
        columnSets = new ArrayList<>();
        gridSets = new ArrayList<>();
        for(int i = 0; i<9; i++){
            HashSet<Character> set = new HashSet<>();
            rowSets.add(set);
        }
        for(int i = 0; i<columnSets.size(); i++){
            HashSet<Character> set = new HashSet<>();
            columnSets.add(set);
        }


        for(int i = 0; i<3;i++){
            ArrayList<HashSet<Character>> list = new ArrayList<>();
            gridSets.add(list);
            for(int j = 0; j<3; j++){
                HashSet<Character> set = new HashSet<>();
                gridSets.get(i).add(set);
            }
        }

        for(int i = 0; i<sudoku.size(); i++){
            for(int j = 0; j< sudoku.get(0).size(); j++){
                Character cell = sudoku.get(i).get(j);
                if( cell == '.')
                    continue;
                rowSets.get(i).add(cell);
                columnSets.get(j).add(cell);
                gridSets.get(i/3).get(j/3).add(cell);
            }
        }
    }

    private Character toCharacter(int n){
        return Character.forDigit(n, 10);
    }

    public static void main(String[] args) {
           
    }
}
