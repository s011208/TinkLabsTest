package yhh.tinklabstest.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.MyApplication;
import yhh.tinklabstest.data.type.BaseType;

public class DataLoader implements LoadDataTask.Callback {
    private static final String TAG = "DataLoader";
    private static final boolean DEBUG = true;

    public static final String FILE_NAME_CITY_GUIDE = "city_guide";
    public static final String FILE_NAME_EAT = "eat";
    public static final String FILE_NAME_SHOP = "shop";

    public static final int DATA_TYPE_CITY_GUIDE = 0;
    public static final int DATA_TYPE_EAT = 1;
    public static final int DATA_TYPE_SHOP = 2;

    public interface Callback {
        void onStartLoading();

        void onFinishLoading();
    }

    private final WeakReference<Context> mContext;
    private WeakReference<Callback> mCallback;
    private final String mAssetsFileName;
    private final int mDataType;
    private final WeakReference<DataKeeper> mDataKeeper;

    public DataLoader(@NonNull Context context, @NonNull String assetsFileName) {
        mContext = new WeakReference<>(context);
        mAssetsFileName = assetsFileName;
        switch (assetsFileName) {
            case FILE_NAME_CITY_GUIDE:
                mDataType = DATA_TYPE_CITY_GUIDE;
                break;
            case FILE_NAME_EAT:
                mDataType = DATA_TYPE_EAT;
                break;
            case FILE_NAME_SHOP:
                mDataType = DATA_TYPE_SHOP;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        mDataKeeper = new WeakReference<>(((MyApplication) context.getApplicationContext()).getApplicationComponent().getDataKeeper());
    }

    public void setCallback(@NonNull Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    @Nullable
    private LoadDataTask getLoadDataTask(Context context, LoadDataTask.Callback cb, String fileName) {
        if (context == null) {
            if (DEBUG) {
                Log.w(TAG, "failed to load, context is null");
            }
            return null;
        }
        return new LoadDataTask(context, cb, fileName);
    }

    public void load() {
        LoadDataTask loadDataTask = getLoadDataTask(mContext.get(), this, mAssetsFileName);
        if (loadDataTask == null) {
            if (DEBUG) {
                Log.w(TAG, "failed to load, loadDataTask is null");
            }
            return;
        }
        loadDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        Callback callback = mCallback.get();
        if (callback == null) {
            if (DEBUG) {
                Log.w(TAG, "callback is null");
            }
            return;
        }
        callback.onStartLoading();
    }

    @Override
    public void onLoadFinish(List<BaseType> items) {
        Callback callback = mCallback.get();
        if (callback == null) {
            if (DEBUG) {
                Log.w(TAG, "callback is null");
            }
            return;
        }
        DataKeeper dataKeeper = mDataKeeper.get();
        if (dataKeeper != null) {
            dataKeeper.addData(mDataType, items);
            callback.onFinishLoading();
        }
    }

    @BaseType.BaseTypeAnnotation
    private int getDataType() {
        return mDataType;
    }

    @NonNull
    public List<BaseType> getData() {
        DataKeeper dataKeeper = mDataKeeper.get();
        if (dataKeeper != null) {
            return dataKeeper.getItems(getDataType());
        } else {
            return new ArrayList<>();
        }
    }
}
