package markup;

public class Text implements MarksandText{
    String str;
    public Text (String str){
        this.str = str;
    }
    @Override
    public void toMarkdown(StringBuilder builder) {
        builder.append(this.str);
    }
    @Override
    public void toTex(StringBuilder builder) {
        builder.append(this.str);
    }
}
