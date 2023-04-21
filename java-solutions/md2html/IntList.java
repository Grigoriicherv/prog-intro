package md2html;

import java.util.Arrays;

public class IntList {

    int[] myArray = new int[2];
    int position = 0;

    int ourIdx = 0;

    boolean nextIsClose;


    public void changeBool() {
        nextIsClose = !nextIsClose;
        ourIdx++;
    }

    public boolean getIsNextClose() {
        return nextIsClose;
    }

    public void addIdx(int idx) {
        myArray[position] = idx;
        position++;
        if (position >= myArray.length) {
            myArray = Arrays.copyOf(myArray, myArray.length * 2);
        }
    }

    public boolean isEndIdxexist(int idx) {
        return (idx < myArray[position - 1]);
    }

    public boolean isitExistinList(int idx) {
        return myArray[ourIdx] == idx;
    }

}
