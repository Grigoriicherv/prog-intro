
import java.util.Arrays;

public class Intlist {
    int[] myArray = new int[10];
    int Repeats = 0;
    int quantityofNumb = 1;
    Intlist() {

    }

    public void addIdx(int count) {

        myArray[quantityofNumb] = count;

        if (quantityofNumb + 1 >= myArray.length) {
            myArray = Arrays.copyOf(myArray, myArray.length * 2);
        }
        quantityofNumb++;
        Repeats++;
    }

    public int getRepeats() {


        return Repeats;


    }

    public void addIdx2(int count, int countString) {

        myArray[quantityofNumb++] = countString;


        myArray[quantityofNumb++] = count;

        if (quantityofNumb + 2 >= myArray.length) {
            myArray = Arrays.copyOf(myArray, myArray.length * 2);
        }

        Repeats++;
    }

    public int getQuantityofNumb() {
        return quantityofNumb;
    }

    public String getStr(int i) {
        return myArray[i] + ":" + myArray[i + 1]+" ";
    }
    public String getStrNosp(int i) {
        return myArray[i] + ":" + myArray[i + 1];
    }

    public String getStr1(int i) {
        return myArray[i] + "";
    }

}
