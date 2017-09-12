package yhh.tinklabstest.views;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

import yhh.tinklabstest.R;
import yhh.tinklabstest.data.DataLoader;
import yhh.tinklabstest.data.type.BaseType;
import yhh.tinklabstest.views.recycler.RecyclerAdapter;

public class BaseFragment extends Fragment implements DataLoader.Callback {
    private static final String TAG = "BaseFragment";
    private static final boolean DEBUG = true;

    private DataLoader mDataLoader;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean mLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setDataLoader(@NonNull DataLoader dataLoader) {
        mDataLoader = dataLoader;
        mDataLoader.setCallback(this);
    }

    public void loadMoreData() {
        if (mDataLoader == null) {
            throw new RuntimeException("Cannot load data without data loader");
        }
        mDataLoader.load();
    }

    public void initData() {
        List<BaseType> items = mDataLoader.getData();
        if (items.isEmpty()) {
            loadMoreData();
        } else {
            if (mRecyclerAdapter != null) {
                mRecyclerAdapter.updateData(items);
            }
        }
    }

    @Override
    public void onStartLoading() {
        if (DEBUG) {
            Log.v(TAG, "onStartLoading");
        }
        if (mRecyclerView != null && mRecyclerAdapter != null) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerAdapter.startLoading();
                }
            });
        }
    }

    @Override
    public void onFinishLoading() {
        if (DEBUG) {
            Log.v(TAG, "onFinishLoading");
        }
        // Let's run a tiny delay for better UI
        new DelayTask(BaseFragment.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateLoadResult() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.updateData(mDataLoader.getData());
        }
        mLoading = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_base_view, null);

        mRecyclerAdapter = new RecyclerAdapter(getContext());
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    if (!mLoading) {
                        if ((mLinearLayoutManager.getChildCount() + mLinearLayoutManager.findFirstVisibleItemPosition()) >= mLinearLayoutManager.getItemCount()) {
                            mLoading = true;
                            loadMoreData();
                        }
                    }
                }
            }
        });

        if (mDataLoader != null) {
            mRecyclerAdapter.updateData(mDataLoader.getData());
        }

        return view;
    }

    private static class DelayTask extends AsyncTask<Void, Void, Void> {
        private static final long DELAY_TIME = 300;
        private final WeakReference<BaseFragment> mBaseFragment;

        private DelayTask(BaseFragment baseFragment) {
            mBaseFragment = new WeakReference<>(baseFragment);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mBaseFragment.get() == null) return;
            mBaseFragment.get().updateLoadResult();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
