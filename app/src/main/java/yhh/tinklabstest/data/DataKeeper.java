package yhh.tinklabstest.data;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

public class DataKeeper {
    private final SparseArray<List<BaseType>> mDataSparseArray = new SparseArray<>();

    void addData(@BaseType.BaseTypeAnnotation int type, @NonNull List<BaseType> items) {
        synchronized (mDataSparseArray) {
            List<BaseType> data = mDataSparseArray.get(type, new ArrayList<BaseType>());
            data.addAll(items);
            mDataSparseArray.put(type, data);
        }
    }

    @NonNull
    public List<BaseType> getItems(@BaseType.BaseTypeAnnotation int type) {
        synchronized (mDataSparseArray) {
            return new ArrayList<>(mDataSparseArray.get(type, new ArrayList<BaseType>()));
        }
    }
}
