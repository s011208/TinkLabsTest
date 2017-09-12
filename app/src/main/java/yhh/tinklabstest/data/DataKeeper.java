package yhh.tinklabstest.data;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

/**
 * singleton
 */

class DataKeeper {
    private static DataKeeper sInstance;

    synchronized static DataKeeper getInstance() {
        if (sInstance == null) {
            sInstance = new DataKeeper();
        }
        return sInstance;
    }

    private final SparseArray<List<BaseType>> mDataSparseArray = new SparseArray<>();

    private DataKeeper() {
    }

    void addData(int type, List<BaseType> items) {
        synchronized (mDataSparseArray) {
            List<BaseType> data = mDataSparseArray.get(type, new ArrayList<BaseType>());
            data.addAll(items);
            mDataSparseArray.put(type, data);
        }
    }

    List<BaseType> getItems(int type) {
        synchronized (mDataSparseArray) {
            return new ArrayList<>(mDataSparseArray.get(type, new ArrayList<BaseType>()));
        }
    }
}
