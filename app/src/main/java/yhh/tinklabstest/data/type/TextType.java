package yhh.tinklabstest.data.type;

public class TextType extends BaseType {
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";

    private final String mText;
    private final String mTitle;

    public TextType(int type, String imageURL, String text, String title) {
        super(type, imageURL);
        mText = text;
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public String getTitle() {
        return mTitle;
    }
}
