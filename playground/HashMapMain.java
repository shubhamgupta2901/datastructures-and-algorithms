package playground;

import datastructures.HashMap;

import javax.rmi.CORBA.Util;
import java.util.Objects;

public class HashMapMain {

    public static void main(String[] args) {
        HashMapMain demo = new HashMapMain();
        demo.testHashMap();
    }

    private void testHashMap(){
        int numOfEntries = Integer.MAX_VALUE-1;
        HashMap map = new HashMap();
        String[] keys = new String[numOfEntries];
        Objects[] values = new Objects[numOfEntries];
        for(int i = 0; i<numOfEntries; i++){
            keys[i] = generateRandomStrings();
        }

        //Print keys
//        for (String key : keys)
//            System.out.print(key + " ");
//        System.out.print("\n");

        for(int i = 0; i<numOfEntries; i++)
            map.put(keys[i], keys[i]);

        for (int i = 0; i<keys.length; i++)
            System.out.println(map.get(keys[i]) + " ");
    }

    private String generateRandomStrings(){
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
