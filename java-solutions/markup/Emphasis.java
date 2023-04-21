package markup;

import java.util.List;

public class Emphasis extends Mark implements MarksandText{
    public Emphasis(List<MarksandText> list){
        super(list);
    }
    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "*", "*", list);
    }
    @Override
    public void toTex(StringBuilder builder) {
        toTex(builder,"\\emph{", "}", list);
    }
}
