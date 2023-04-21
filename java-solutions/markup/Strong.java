package markup;

import java.util.List;

public class Strong extends Mark implements MarksandText{
    public Strong(List<MarksandText> list){
        super(list);
    }
    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "__", "__", list);
    }
    @Override
    public void toTex(StringBuilder builder) {
        super.toTex(builder,"\\textbf{", "}", list);
    }
}
