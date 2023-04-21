package markup;

import java.util.List;


public class Strikeout extends Mark implements MarksandText{
    public Strikeout(List<MarksandText> list){
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder,"~", "~", list);

    }
    @Override
    public void toTex(StringBuilder builder) {
        super.toTex(builder, "\\textst{", "}", list);
    }
}
