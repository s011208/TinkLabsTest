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
import yhh.tinklabstest.data.type.ImageType;
import yhh.tinklabstest.data.type.TextType;
import yhh.tinklabstest.util.Utilities;

/**
 * Data loader
 */

public class LoadDataTask extends AsyncTask<Void, Void, List<BaseType>> {

    public interface Callback {
        void onLoadFinish(List<BaseType> items);
    }

    private final WeakReference<Context> mContext;
    private final WeakReference<Callback> mCallback;
    private final String mFileName;

    protected LoadDataTask(Context context, Callback cb, String fileName) {
        mContext = new WeakReference<>(context);
        mCallback = new WeakReference<>(cb);
        mFileName = fileName;
    }

    @Override
    protected List<BaseType> doInBackground(Void... voids) {
        List<BaseType> rtn = new ArrayList<>();
        final Context context = mContext.get();
        if (context == null) return rtn;
        try {
            rtn = load();
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

    private List<BaseType> load() throws IOException, JSONException {
        List<BaseType> rtn = new ArrayList<>();
        final String rawJSONArray = Utilities.readStringFromAssets(mContext.get(), mFileName);
        final JSONArray jsonArray = new JSONArray(rawJSONArray);
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String type = jsonObject.getString(BaseType.KEY_TYPE);
            if (BaseType.KEY_TYPE_IMAGE.equals(type)) {
                rtn.add(new ImageType(ImageType.TYPE_IMAGE, jsonObject.getString(ImageType.KEY_IMAGE_URL)));
            } else if (BaseType.KEY_TYPE_TEXT.equals(type)) {
                rtn.add(new TextType(TextType.TYPE_TEXT, jsonObject.getString(TextType.KEY_IMAGE_URL),
                        jsonObject.getString(TextType.KEY_TEXT), jsonObject.getString(TextType.KEY_TITLE)));
            } else {
                throw new UnsupportedOperationException();
            }
        }

        // shuffle list for fun
        Collections.shuffle(rtn);

        return rtn;
    }
}
