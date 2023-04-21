import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MyScanner implements AutoCloseable{
    final Reader streamfromFile;
    private int endPoint;
    private int startPoint = 0;
    private char[] token = new char[0];
    final char[] buffer = new char[100];
    private char[] tokenforStrings = new char[0];
    // for stream
    public MyScanner(InputStream stream) {

        streamfromFile = new InputStreamReader(stream, StandardCharsets.UTF_8);
        readingFile();
    }
    // for string
    public MyScanner(String str) {
        streamfromFile = new StringReader(str);
        readingFile();
    }
    // for file
    public MyScanner(File myfile) throws FileNotFoundException {

        streamfromFile = new InputStreamReader(new FileInputStream(myfile), StandardCharsets.UTF_8);
        readingFile();

    }
    private void readingFile(){
        try {
            endPoint = streamfromFile.read(buffer);
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private boolean isreadinBuffer(){
        try {
            endPoint = streamfromFile.read(buffer);
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
            return true;
        }
        return false;




    }
    // replace our letter with numb
    private char replaceLetter(char mychar) {
        if (Character.isLetter(mychar)) {
            int b = mychar;
            b = b - 49;
            return ((char) b);
        } else {
            return mychar;
        }

    }
    // replace our numb with letter
    private char replaceNumber(char mychar) {
        if (Character.isDigit(mychar)) {
            int b = mychar;
            b = b + 49;
            return ((char) b);
        } else {
            return mychar;
        }
    }
    // checking whether the start is greater than the end
    private boolean checkonPoint() {

        if (startPoint >= endPoint) {
            try {
                endPoint = streamfromFile.read(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (endPoint == -1) {
                return true;
            }
            startPoint = 0;
            return false;
        }
        return false;
    }
    // close stream
    public void close() {
        try {
            streamfromFile.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
    }
    private boolean skipAllSpaces(char[] token){
        int tempCount=0;
        while (Character.isWhitespace(token[tempCount])) {
            tempCount++;
        }
        this.token = Arrays.copyOfRange(token, tempCount, token.length);
        return tempCount <= token.length-1;
    }
    private boolean skipAllnotforWord(){
        while (!(Character.isLetter(buffer[startPoint]) || (buffer[startPoint] == '\'') ||
                (Character.getType(buffer[startPoint]) == 20))) {

            startPoint++;

            if (checkonPoint()) {
                return true;
            }

        }
        return false;
    }
    private boolean skipAllnotforWordnotforBuff(char[] token){
        int tempCount = 0;
        while (!(Character.isLetter(token[tempCount]) || (token[tempCount] == '\'') ||
                (Character.getType(token[tempCount]) == 20))) {

            tempCount++;



        }
        this.token = Arrays.copyOfRange(token, tempCount, token.length);
        return tempCount <= token.length-1;
    }
    // is exist next line if exist return
    public String nextLine() {

        if (tokenforStrings.length > 0) {
            String temp = new String(tokenforStrings);
            tokenforStrings = new char[0];
            return temp;
        } else if (hasNextLine("all")) {
            String temp = new String(tokenforStrings);
            tokenforStrings = new char[0];
            return temp;
        } else {
            throw new NullPointerException();
        }

    }
    // is exist next int if exist return
    public int nextInt() {

        if (token.length >= 1) {
            String temp = new String(token);
            token = new char[0];
            return Integer.parseInt(temp);

        } else if (hasNext("all")) {
            String temp = new String(token);
            token = new char[0];
            return Integer.parseInt(temp);

        } else {
            throw new NullPointerException();
        }

    }
    // is exist next mod for Abc if exist return
    public String nextLetter() {

        if (token.length >= 1) {
            String temp = new String(token);
            token = new char[0];
            return temp;

        } else if (hasNext("str")) {
            String temp = new String(token);
            token = new char[0];
            return temp;

        } else {
            throw new NullPointerException();
        }

    }
    // my has next only for numbers or letter that must become numbers
    public boolean hasNext(String letter) {

        if (token.length > 0 ){
            if(skipAllSpaces(token)){
                return true;
            }
        }
        // if token have something
        else if (tokenforStrings.length>0) {
            if(skipAllSpaces(tokenforStrings)){
                return true;
            }
        } else {
            token = new char[1];
        }

        if (checkonPoint()) {
            return false;
        }
        // skip all spaces
        while (Character.isWhitespace(buffer[startPoint])) {

            startPoint++;

            if (checkonPoint()) {
                return false;
            }

        }

        int amountofNumbers = 0;
        // checking on digit
        while (!Character.isWhitespace(buffer[startPoint])) {
            if (Character.isDigit(buffer[startPoint]) || buffer[startPoint] == '-') {
                if (letter.equals("all")) {
                    token[amountofNumbers] = buffer[startPoint];
                    amountofNumbers++;
                } else if (letter.equals("str")) {
                    token[amountofNumbers] = replaceNumber(buffer[startPoint]);
                    amountofNumbers++;
                }
            }

            startPoint++;
            if (startPoint >= endPoint) {

                if (isreadinBuffer()){
                    return false;
                }


                if (endPoint == -1 && amountofNumbers > 0) {
                    token = Arrays.copyOf(token, amountofNumbers);
                    return true;
                } else if (endPoint == -1) {
                    return false;

                }

                startPoint = 0;
            }

            if (amountofNumbers >= token.length) {
                token = Arrays.copyOf(token, token.length * 2);
            }

        }
        if (token.length > 0) {
            token = Arrays.copyOf(token, amountofNumbers);
            return true;
        }

        return false;

    }
    // in next line we are taking all while we don't meet separator
    public boolean hasNextLine(String letter) {
        // in token we have something
        if (tokenforStrings.length > 0) {
            return true;
        } else {
            // if token is empty make length 1
            tokenforStrings = new char[1];

        }
        int positionfortokenString = 0;
        // until we reached the end of the stream
        if (startPoint >= endPoint) {

            startPoint = startPoint - endPoint;
            if (isreadinBuffer()){
                return false;
            }


            if (endPoint == -1) {
                return false;
            }
            startPoint = 0;
        }
        // until we meet the string separator
        while (buffer[startPoint] != System.lineSeparator().charAt(0)) {
            if (letter.equals("all")) {
                tokenforStrings[positionfortokenString] = buffer[startPoint];
                positionfortokenString++;
                startPoint++;
            } else if (letter.equals("str")) {
                tokenforStrings[positionfortokenString] = replaceLetter(buffer[startPoint]);// replace
                positionfortokenString++;
                startPoint++;
            }

            if (positionfortokenString >= tokenforStrings.length) {
                tokenforStrings = Arrays.copyOf(tokenforStrings, tokenforStrings.length * 2);
            }
            // checking our position and end
            if (startPoint >= endPoint) {
                if (isreadinBuffer()){
                    return false;
                }

                if (endPoint == -1) {
                    if (positionfortokenString > 0) {
                        tokenforStrings = Arrays.copyOf(tokenforStrings, positionfortokenString);
                        return true;
                    } else {
                        return false;
                    }
                }
                startPoint = 0;
            }

        }
        // if begin of string is line separator
        if (positionfortokenString == 0) {
            tokenforStrings[0] = ' ';

        }
        // if token fo str isnt empty
        else {
            tokenforStrings = Arrays.copyOf(tokenforStrings, positionfortokenString);

        }
        startPoint = startPoint + System.lineSeparator().length();
        return true;

    }
    // return word
    public String nextWord() {
        if (token.length >= 1) {
            String temp = new String(token);
            token = new char[0];
            return temp;

        } else if (hasNextWord()) {
            String temp = new String(token);
            token = new char[0];
            return temp;

        } else {
            throw new NullPointerException();
        }
    }
        // checking whether we have word (for wordstat)
    public boolean hasNextWord() {
        if (token.length>0){
            if (skipAllnotforWordnotforBuff(token)){
                return true;
            }
        }
        // if token have something
        else if (tokenforStrings.length>0) {
            if (skipAllnotforWordnotforBuff(tokenforStrings)){
                return true;
            }
        } else {
            token = new char[1];
        }

        if (checkonPoint()) {
            return false;
        }
        // skip all that doesn't fit us
        if (skipAllnotforWord()){
            return false;
        }
        int amountofNumbers = 0;

        while (Character.isLetter(buffer[startPoint]) || (buffer[startPoint] == '\'') ||
                (Character.getType(buffer[startPoint]) == 20)) {

            if (Character.isLetter(buffer[startPoint])) {
                buffer[startPoint] = Character.toLowerCase(buffer[startPoint]);
            }
            token[amountofNumbers] = buffer[startPoint];
            amountofNumbers++;

            startPoint++;
            if (startPoint >= endPoint) {
                if (isreadinBuffer()){
                    return false;
                }
                if (endPoint == -1) {
                    token = Arrays.copyOf(token, amountofNumbers);
                    return true;
                }


                startPoint = 0;
            }
            //extension
            if (amountofNumbers >= token.length) {
                token = Arrays.copyOf(token, token.length * 2);
            }

        }

        if (token.length > 0) {
            token = Arrays.copyOf(token, amountofNumbers);
            return true;
        }

        return false;

    }
}
