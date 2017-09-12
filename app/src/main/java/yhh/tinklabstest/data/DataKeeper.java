package yhh.tinklabstest.data;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

/**
 * singleton
 */

public class DataKeeper {
    private final SparseArray<List<BaseType>> mDataSparseArray = new SparseArray<>();

    public DataKeeper() {
    }

    void addData(int type, List<BaseType> items) {
        synchronized (mDataSparseArray) {
            List<BaseType> data = mDataSparseArray.get(type, new ArrayList<BaseType>());
            data.addAll(items);
            mDataSparseArray.put(type, data);
        }
    }

    public List<BaseType> getItems(int type) {
        synchronized (mDataSparseArray) {
            return new ArrayList<>(mDataSparseArray.get(type, new ArrayList<BaseType>()));
        }
    }
}
