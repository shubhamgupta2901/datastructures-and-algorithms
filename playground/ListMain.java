package playground;

import datastructures.ArrayList;

public class ListMain {
    public static void main(String[] args) {
        ListMain demo = new ListMain();
        demo.testArrayList();
    }

    private void testArrayList(){
        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.size());
        System.out.println(arrayList.isEmpty());
        //System.out.println(arrayList.get(1));

        for(int i = 1; i<=10;i++)
            arrayList.add(i);

       // arrayList.add(7,100);
        arrayList.removeAtIndex(2);
        arrayList.removeElement(1);
        arrayList.add(19);

    }
}
