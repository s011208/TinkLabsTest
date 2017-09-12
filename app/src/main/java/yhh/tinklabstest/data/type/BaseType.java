package yhh.tinklabstest.data.type;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.util.UUID;

public class BaseType {

    @IntDef({TYPE_IMAGE, TYPE_TEXT, TYPE_PROGRESS_BAR})
    public @interface BaseTypeAnnotation {
    }

    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_PROGRESS_BAR = 2;

    public static final String KEY_TYPE = "dataType";
    public static final String KEY_TYPE_IMAGE = "Image";
    public static final String KEY_TYPE_TEXT = "Text";

    public static final String KEY_IMAGE_URL = "imageUrl";

    private final int mType;
    private final String mImageURL;

    private final String mUUID;


    public BaseType(@BaseTypeAnnotation int type, @NonNull String imageURL) {
        mType = type;
        mImageURL = "file:///android_asset/" + imageURL;
        mUUID = UUID.randomUUID().toString();
    }

    @NonNull
    public String getImageURL() {
        return mImageURL;
    }

    @BaseTypeAnnotation
    public int getType() {
        return mType;
    }

    @NonNull
    public String getUUID() {
        return mUUID;
    }
}
