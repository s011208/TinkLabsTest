package yhh.tinklabstest.data;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;
import yhh.tinklabstest.data.type.LocalImageType;
import yhh.tinklabstest.data.type.LocalTextType;
import yhh.tinklabstest.util.Utilities;

/**
 * Data loader task
 */
public class LoadDataTask extends AsyncTask<String, Void, List<BaseType>> {

    interface Callback {
        void onLoadFinish(List<BaseType> items);
    }

    private final WeakReference<Context> mContext;
    private final WeakReference<Callback> mCallback;

    public LoadDataTask(Context context, Callback cb) {
        mContext = new WeakReference<>(context);
        mCallback = new WeakReference<>(cb);
    }

    @Override
    protected List<BaseType> doInBackground(String... strings) {
        List<BaseType> rtn = new ArrayList<>();
        final Context context = mContext.get();
        if (context == null) return rtn;
        try {
            rtn = generateList(loadRawData(strings));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    protected void onPostExecute(List<BaseType> baseTypes) {
        final Callback callback = mCallback.get();
        if (callback == null) return;
        callback.onLoadFinish(baseTypes);
    }

    /**
     * override if app want to load data from other resources
     */
    private String loadRawData(String... strings) throws IOException {
        return Utilities.readStringFromAssets(mContext.get(), strings[0]);
    }

    /**
     * override if app want to load data from other resources
     */
    public List<BaseType> generateList(String rawData) throws IOException, JSONException {
        List<BaseType> rtn = new ArrayList<>();
        final JSONArray jsonArray = new JSONArray(rawData);
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String type = jsonObject.getString(BaseType.KEY_TYPE);
            if (BaseType.KEY_TYPE_IMAGE.equals(type)) {
                rtn.add(new LocalImageType(jsonObject.getString(LocalImageType.KEY_IMAGE_URL)));
            } else if (BaseType.KEY_TYPE_TEXT.equals(type)) {
                rtn.add(new LocalTextType(jsonObject.getString(LocalTextType.KEY_IMAGE_URL),
                        jsonObject.getString(LocalTextType.KEY_TEXT), jsonObject.getString(LocalTextType.KEY_TITLE)));
            } else {
                throw new UnsupportedOperationException();
            }
        }

        // shuffle list for fun
        Collections.shuffle(rtn);

        // Because data is loaded in local, simulating a tiny delay for performing UI
        try {
            Thread.sleep((int) (Math.random() * 10000) % 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rtn;
    }
}
