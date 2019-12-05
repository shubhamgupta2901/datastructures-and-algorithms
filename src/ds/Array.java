public class Array {
    int [] arr;

    //Constuctor
    public Array(int size){
        arr = new int[size];
        for(int i = 0; i< size; i++){
            arr[i] = Integer.MIN_VALUE;
        }
    }

    //Traversing the array
    public void traverseArray(){
        for (int i =0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    public void insert(int index, int value){
        try {
            if(arr[index] == Integer.MIN_VALUE){
                arr[index] = value;
                System.out.println("Successfully inserted at index "+index+" value "+ value);
            } else {
                System.out.println("This index already has a value.");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid index to access array");
        }
    }

}
