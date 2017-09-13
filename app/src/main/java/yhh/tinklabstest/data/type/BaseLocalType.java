package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

class BaseLocalType extends BaseType {
    public static final String KEY_IMAGE_URL = "imageUrl";
    private final String mImageURL;

    BaseLocalType(@NonNull String imageURL) {
        mImageURL = imageURL;
    }

    @NonNull
    public String getImageURL() {
        return mImageURL;
    }

}
