package yhh.tinklabstest.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

public class DataLoader implements LoadDataTask.Callback {
    private static final String TAG = "DataLoader";
    private static final boolean DEBUG = true;

    public static final String FILE_NAME_CITY_GUIDE = "city_guide";
    public static final String FILE_NAME_EAT = "eat";
    public static final String FILE_NAME_SHOP = "shop";

    private static final int DATA_TYPE_CITY_GUIDE = 0;
    private static final int DATA_TYPE_EAT = 1;
    private static final int DATA_TYPE_SHOP = 2;

    public interface Callback {
        void onStartLoading();

        void onFinishLoading();
    }

    private final WeakReference<Context> mContext;
    private WeakReference<Callback> mCallback;
    private final String mAssetsFileName;
    private final int mDataType;
    private DataKeeper mDataKeeper = DataKeeper.getInstance();

    public DataLoader(Context context, String assetsFileName) {
        mContext = new WeakReference<>(context);
        mAssetsFileName = assetsFileName;
        if (FILE_NAME_CITY_GUIDE.equals(assetsFileName)) {
            mDataType = DATA_TYPE_CITY_GUIDE;
        } else if (FILE_NAME_EAT.equals(assetsFileName)) {
            mDataType = DATA_TYPE_EAT;
        } else if (FILE_NAME_SHOP.equals(assetsFileName)) {
            mDataType = DATA_TYPE_SHOP;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void setCallback(@NonNull Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    public void load() {
        Context context = mContext.get();
        if (context == null) {
            if (DEBUG) {
                Log.w(TAG, "failed to load, context is null");
            }
            return;
        }
        new LoadDataTask(context, this, mAssetsFileName).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
        mDataKeeper.addData(mDataType, items);
        callback.onFinishLoading();
    }

    private int getDataType() {
        return mDataType;
    }

    public List<BaseType> getData() {
        return mDataKeeper.getItems(getDataType());
    }
}
