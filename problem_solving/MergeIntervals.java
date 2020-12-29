package problem_solving;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Problem Constraints
 * 1 <= |intervals| <= 105
 * Input Format
 * First argument is the vector of intervals
 * second argument is the new interval to be merged
 * Output Format
 * Return the vector of intervals after merging
 *
 * Example1:
 * Input: Given intervals [1, 3], [6, 9] insert and merge [2, 5] .
 * Output: [ [1, 5], [6, 9] ]
 *
 * Example2:
 * Input: Given intervals [1, 3], [6, 9] insert and merge [2, 6] .
 * Output [ [1, 9] ]
 */
public class MergeIntervals {

      public static class Interval {
            int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
      }


    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> output = new ArrayList<Interval>();
        if(intervals == null || intervals.size()==0){
            output.add(newInterval);
            return output;
        }
        int i=0;
        for(i = 0; i<intervals.size(); i++){
            Interval interval = intervals.get(i);
            if(newInterval == null){
                output.add(interval);
            }else if(canMergeIntervals(interval, newInterval)){
                newInterval = mergeIntervals(interval, newInterval);
            }else if(compareIntervals(newInterval, interval)<0){
                output.add(newInterval);
                output.add(interval);
                newInterval = null;
            }else{
                output.add(interval);
            }
        }

        if(newInterval!=null){
            output.add(newInterval);
        }
        return output;
    }

    //return true if two intervals can be merged
    public boolean canMergeIntervals(Interval i1, Interval i2){
        if(i2.start>=i1.start && i2.start<=i1.end)
            return true;
        else if(i1.start>=i2.start && i1.start<=i2.end)
            return true;
        return false;

    }

    //returns -1 if(i1<i2) 0 if (i1=i2) else 1
    public int compareIntervals(Interval i1, Interval i2){
        return i1.start-i2.start;
    }
    // input will be two mergeable intervals, output will be merged interval
    public Interval mergeIntervals(Interval i1, Interval i2){
        //if equal return same
        Interval mergedInterval = new Interval();
        mergedInterval.start = Math.min(i1.start, i2.start);
        mergedInterval.end = Math.max(i1.end, i2.end);
        return mergedInterval;
    }


    public static void main(String[] args) {
        MergeIntervals driver = new MergeIntervals();
        List<Interval> list = Arrays.asList(new Interval(6037774, 6198243), new Interval(6726550, 7004541), new Interval(8842554, 10866536), new Interval(11027721, 11341296), new Interval(11972532, 14746848), new Interval(16374805, 16706396), new Interval(17557262, 20518214), new Interval(22139780, 22379559), new Interval(27212352, 28404611), new Interval(28921768, 29621583), new Interval(29823256, 32060921), new Interval(33950165, 36418956), new Interval(37225039, 37785557), new Interval(40087908, 41184444), new Interval(41922814, 45297414), new Interval(48142402, 48244133), new Interval(48622983, 50443163), new Interval(50898369, 55612831), new Interval(57030757, 58120901), new Interval(59772759, 59943999), new Interval(61141939, 64859907), new Interval(65277782, 65296274), new Interval(67497842, 68386607), new Interval(70414085, 73339545), new Interval(73896106, 75605861), new Interval(79672668, 84539434), new Interval(84821550, 86558001), new Interval(91116470, 92198054),new Interval (96147808, 98979097));
        ArrayList<Interval> inputList = new ArrayList<>(list);
        Interval newInterval =  new Interval (36210193, 61984219);
        ArrayList<Interval> output = driver.insert(inputList, newInterval);
        System.out.println(output);
    }
}
