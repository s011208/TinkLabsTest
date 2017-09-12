package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

public class TextType extends BaseType {
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";

    private final String mText;
    private final String mTitle;

    public TextType(@BaseTypeAnnotation int type, @NonNull String imageURL, @NonNull String text, @NonNull String title) {
        super(type, imageURL);
        mText = text;
        mTitle = title;
    }

    @NonNull
    public String getText() {
        return mText;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }
}
