
package markup;

import java.util.List;

public class Paragraph extends Mark {
    public Paragraph(List<MarksandText> list){
        super(list);
    }

    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder,"", "", list);
    }
    public void toTex(StringBuilder builder) {
        super.toTex(builder,"", "", list);
    }

}


