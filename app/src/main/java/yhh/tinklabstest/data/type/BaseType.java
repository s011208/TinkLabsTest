package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Base data type
 */
public class BaseType {

    public static final String KEY_TYPE = "dataType";
    public static final String KEY_TYPE_IMAGE = "Image";
    public static final String KEY_TYPE_TEXT = "Text";

    private final String mUUID;

    public BaseType() {
        mUUID = UUID.randomUUID().toString();
    }

    @NonNull
    public String getUUID() {
        return mUUID;
    }
}
