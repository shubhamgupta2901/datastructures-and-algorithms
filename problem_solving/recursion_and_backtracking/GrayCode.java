package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;

public class GrayCode {

    ArrayList<Integer> greyCodes;
    public ArrayList<Integer> grayCode(int a) {
        greyCodes = new ArrayList<Integer>();
        char[] input = new char[a];
        for(int i = 0; i<a;i++){
            input[i] = '0';
        }
        addToList(input);
        helper(input, a-1);
        return greyCodes;
    }
    private void helper(char[] arr, int index){
        if(index == 0)
            return;
        for(int i = index; i>=0; i--){
            arr[i] = arr[i] == '0' ? '1' : '0';
            addToList(arr);
            helper(arr,index-1);
            arr[i] = arr[i] == '0' ? '1' : '0';
        }
    }

    private void addToList(char[] arr ){
        String codeStr = new String(arr);
            int code = Integer.parseInt(codeStr,2);
        greyCodes.add(code);
    }

    public static void main(String[] args) {
        GrayCode driver = new GrayCode();
        ArrayList<Integer> output = driver.grayCode(2);
        for(Integer i : output)
            System.out.println(i);
    }
}
