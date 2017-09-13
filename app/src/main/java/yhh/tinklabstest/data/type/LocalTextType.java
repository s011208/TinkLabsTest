package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

public class LocalTextType extends BaseLocalType {
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";

    private final String mText;
    private final String mTitle;

    public LocalTextType(@NonNull String imageURL, @NonNull String text, @NonNull String title) {
        super(imageURL);
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
