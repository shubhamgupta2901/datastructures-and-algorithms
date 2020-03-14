package leetcode;

import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * Note:
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
    /**
     * Approach : We can use a Max Heap to find ‘K’ points closest to the origin.
     * While iterating through all points, if a point (say ‘P’) is closer to the origin
     * than the top point of the max-heap, we will remove that top point from the heap and add ‘P’
     * to always keep the closest points in the heap.
     *
     * Time Complexity: O(nlogK) where n is the number of points.
     * Space Complexity: O(K)
     */

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2)->compare(p2,p1));
        for(int i = 0; i<points.length; i++){
            if(i<K)
                queue.add(points[i]);
            else if(compare(points[i],queue.peek())<0){
                queue.remove();
                queue.add(points[i]);
            }
        }

        int[][] closestPoints = new int[K][2];
        for(int i = 0; i<K; i++)
            closestPoints[i] = queue.poll();
        return closestPoints;
    }

    private int compare(int[]point1, int[] point2){
        int distance1 = point1[0]*point1[0] + point1[1]*point1[1];
        int distance2 = point2[0]*point2[0] + point2[1]*point2[1];
        return distance1-distance2;
    }
}
