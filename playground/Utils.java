package playground;

import java.util.List;

public class Utils {

    public static <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()){
            System.out.println("Empty List");
            return;
        }

        for (T val : list)
            System.out.print(val + " ");
        System.out.print("\n");
    }
}
