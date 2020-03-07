package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    /**
     * Approach 1: Rejected
     * Fails for cases like:
     * [[2,3],[4,5],[6,7],[8,9],[1,10]]
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> mergedIntervals = new ArrayList<>();
        for(int i = 0; i<intervals.length; i++){
            int j;
            for(j = 0; j<mergedIntervals.size(); j++){
                int[] mergedInterval = merge(intervals[i], mergedIntervals.get(j));
                if(mergedInterval != null){
                    mergedIntervals.set(j,mergedInterval);
                    break;
                }
            }
            if(j == mergedIntervals.size())
                mergedIntervals.add(intervals[i]);
        }
        return mergedIntervals.toArray(new int[0][]);

    }

    private int[] merge(int[] interval1, int[] interval2){
        int a = interval1[0], b = interval1[1];
        int c = interval2[0], d = interval2[1];
        if(a<=c){
            if(b<c)
                return null;
            else if(b<d)
                return new int[]{a,d};
            else return new int[]{a,b};
        }else{
            if(d<a)
                return null;
            else if(d<b)
                return new int[]{c,b};
            else return new int[]{c,d};
        }
    }


    public int[][] merge1(int[][] intervals) {
        List<int[]> mIntervals = Arrays.asList(intervals);
        return null;
    }

    public static void main(String[] args) {

        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = new MergeIntervals().merge(intervals);
        System.out.println(result);
    }
}
