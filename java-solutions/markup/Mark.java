package markup;

import java.util.ArrayList;
import java.util.List;

abstract class Mark {
    List<MarksandText> list = new ArrayList<>();
    public Mark (List<MarksandText> list){
        this.list.addAll(list);
    }
    public void toMarkdown(StringBuilder builder, String str1,
                           String str2, List<MarksandText> list){
        builder.append(str1);
        for (MarksandText  text: list){
            text.toMarkdown(builder);
        }
        builder.append(str2);


    }
    public void toTex(StringBuilder builder, String str1,
                      String str2, List<MarksandText> list){
        builder.append(str1);
        for (MarksandText  text: list){
            text.toTex(builder);
        }
        builder.append(str2);

    }
}