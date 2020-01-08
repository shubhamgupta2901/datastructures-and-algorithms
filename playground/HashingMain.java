package playground;

import datastructures.HashMap;
import datastructures.HashSet;

import java.util.Objects;

public class HashingMain {

    public static void main(String[] args) {
        HashingMain demo = new HashingMain();
        demo.testHashSet();
    }

    private void testHashMap(){
        int numOfEntries = 200000;
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

    private void testHashSet(){
        int numOfEntries = 2000000;
        HashSet set = new HashSet();
        String[] keys = new String[numOfEntries];
        for(int i = 0; i<numOfEntries; i++){
            keys[i] = generateRandomStrings();
        }

        for(int i = 0; i<numOfEntries; i++)
            set.add(keys[i]);

        for (int i = 0; i<keys.length; i++)
            System.out.println("Contains " + keys[i] + ": "+ set.contains(keys[i]));
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
