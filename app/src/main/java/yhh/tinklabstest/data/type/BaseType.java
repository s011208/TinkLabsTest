package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

import java.util.UUID;

public class BaseType {
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_TEXT = 1;

    public static final String KEY_TYPE = "dataType";
    public static final String KEY_TYPE_IMAGE = "Image";
    public static final String KEY_TYPE_TEXT = "Text";

    public static final String KEY_IMAGE_URL = "imageUrl";

    private final int mType;
    private final String mImageURL;

    private final String mUUID;

    public BaseType(int type, String imageURL) {
        mType = type;
        mImageURL = "file:///android_asset/" + imageURL;
        mUUID = UUID.randomUUID().toString();
    }

    public String getImageURL() {
        return mImageURL;
    }

    public int getType() {
        return mType;
    }

    public String getUUID() {
        return mUUID;
    }
}
