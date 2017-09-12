package yhh.tinklabstest.data.type;

import android.support.annotation.NonNull;

public class ImageType extends BaseType {
    public ImageType(@BaseTypeAnnotation int type, @NonNull String imageURL) {
        super(type, imageURL);
    }
}
