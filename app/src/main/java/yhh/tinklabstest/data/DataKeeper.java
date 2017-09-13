package yhh.tinklabstest.data;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

/**
 * save all data and keep an instance where has application lifecycle
 */
public class DataKeeper {
    private final SparseArray<List<BaseType>> mDataSparseArray = new SparseArray<>();

    /**
     * Add data in memory
     */
    void addData(@DataLoader.TabType int type, @NonNull List<BaseType> items) {
        synchronized (mDataSparseArray) {
            List<BaseType> data = mDataSparseArray.get(type, new ArrayList<BaseType>());
            data.addAll(items);
            mDataSparseArray.put(type, data);
        }
    }

    /**
     * get data with type
     */
    @NonNull
    public List<BaseType> getItems(@DataLoader.TabType int type) {
        synchronized (mDataSparseArray) {
            return new ArrayList<>(mDataSparseArray.get(type, new ArrayList<BaseType>()));
        }
    }
}
