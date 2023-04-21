package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Md2Html {
    // access
    private BufferedReader reader;
    BufferedWriter writer;
    StringBuilder paragraph = new StringBuilder();

    static Map<String, String> dict = Map.of("*", "em>", "_", "em>", "**", "strong>", "__",
            "strong>", "--", "s>", "`", "code>", "++", "u>");

    public Md2Html(String in, String out) {

        try {
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.print(e.getMessage());
        }


        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.print(e.getMessage());
        }
    }

    public void parseText(StringBuilder result) {

        String line;
        try {
            line = reader.readLine();
            while (line != null) {
                if (line.equals("")) {
                    line = reader.readLine();
                    continue;
                }
                if (line.charAt(0) != '#' || (line.charAt(0) == '#' && Character.isLetter(line.charAt(1)))) {
                    while (!line.equals("")) {
                        paragraph.append(line).append("\r\n");
                        line = reader.readLine();

                        if (line == null) {
                            parseSomeLines("0", result, "p");
                            break;
                        }
                        if (paragraph.length() > 0 && line.equals("")) {
                            parseSomeLines("0", result, "p");
                            break;
                        }
                    }


                } else {
                    int numbofHashtag = 0;
                    while (line.charAt(numbofHashtag) == '#') {
                        numbofHashtag++;
                    }
                    line = line.substring(numbofHashtag + 1);

                    while (!line.equals("")) {
                        paragraph.append(line).append("\r\n");
                        line = reader.readLine();
                        if (line == null) {
                            String temp = String.valueOf(numbofHashtag);
                            parseSomeLines(temp, result, "h");
                            break;
                        }
                        if (paragraph.length() > 0 && line.equals("")) {
                            String temp = String.valueOf(numbofHashtag);
                            parseSomeLines(temp, result, "h");
                            break;
                        }
                    }

                }
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
        }
    }


    private void parseSomeLines(String numbofHashtag, StringBuilder result, String temp) throws IOException {
        if (numbofHashtag.equals("0")) {
            numbofHashtag = "";
        }

        result.append("<").append(temp).append(numbofHashtag).append(">");

        parseParagraph(result);
        result.replace(result.length() - 2, result.length(), "</").append(temp).append(numbofHashtag).
                append(">").append("\r\n");

        writer.write(result.toString());
        paragraph.setLength(0);
        result.setLength(0);
    }

    private void parseParagraph(StringBuilder result) {
        Map<String, IntList> allMarks = new HashMap<>();

        for (int i = 0; i < paragraph.length(); i++) {
            if (paragraph.charAt(i) == '\\') {
                i++;
                continue;

            }
            if (checkIsitopenchar(i)) {
                String openMark;
                if (dict.containsKey(openMark = paragraph.substring(i, i + 2))) {
                    i = fillMap(allMarks, openMark, i);
                } else if (dict.containsKey(openMark = paragraph.substring(i, i + 1))) {
                    i = fillMap(allMarks, openMark, i);
                }
            }
        }
        for (int i = 0; i < paragraph.length(); i++) {

            if (isitSymbolforRepl(i, result)) {
                continue;
            }
            if (checkIsitopenchar(i)) {
                String openMark;
                if (dict.containsKey(openMark = paragraph.substring(i, i + 2))) {
                    if (!allMarks.containsKey(openMark) || !allMarks.get(openMark).isitExistinList(i)) {
                        result.append(paragraph.charAt(i)).append(paragraph.charAt(i + 1));
                        i++;
                        continue;
                    }
                    i = fillResult(allMarks, openMark, i, result);
                } else {
                    openMark = paragraph.substring(i, i + 1);
                    if (!allMarks.containsKey(openMark) || !allMarks.get(openMark).isitExistinList(i)) {
                        result.append(paragraph.charAt(i));
                        continue;
                    }
                    i = fillResult(allMarks, openMark, i, result);
                }
            } else {
                result.append(paragraph.charAt(i));
            }
        }
    }

    private int fillMap(Map<String, IntList> allMarks, String openMark, int i) {
        int temp = 0;
        if (openMark.length() > 1) {
            temp = 1;
        }
        // getOrDefault
        if (allMarks.containsKey(openMark)) {
            allMarks.get(openMark).addIdx(i);
        } else {
            allMarks.put(openMark, new IntList());
            allMarks.get(openMark).addIdx(i);
        }
        return (i += temp);
    }

    private int fillResult(Map<String, IntList> allMarks, String openMark, int i, StringBuilder result) {
        int temp = 0;
        if (openMark.length() > 1) {
            temp = 1;
        }
        if (allMarks.get(openMark).isEndIdxexist(i) || allMarks.get(openMark).getIsNextClose()) {
            if (allMarks.get(openMark).getIsNextClose()) {
                result.append("</").append(dict.get(openMark));
                allMarks.get(openMark).changeBool();
                i += temp;
            } else {
                result.append("<").append(dict.get(openMark));
                allMarks.get(openMark).changeBool();
                i += temp;
            }
        } else {
            result.append(openMark);
        }
        return i;

    }

    private boolean isitSymbolforRepl(int i, StringBuilder result) {

        return switch (paragraph.charAt(i)) {
            case '\\':
                yield true;
            case '&':
                result.append("&amp;");
                yield true;
            case '<':
                result.append("&lt;");
                yield true;
            case '>':
                result.append("&gt;");
                yield true;
            default:
                yield false;
        };

    }

    private boolean checkIsitopenchar(int i) {
        return paragraph.charAt(i) == '*' || (paragraph.charAt(i) == '-' && paragraph.charAt(i + 1) == '-')
                || paragraph.charAt(i) == '_' || paragraph.charAt(i) == '`'
                || (paragraph.charAt(i) == '+' && paragraph.charAt(i + 1) == '+');
    }

    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        Md2Html md = new Md2Html(args[0], args[1]);
        md.parseText(result);

    }

}