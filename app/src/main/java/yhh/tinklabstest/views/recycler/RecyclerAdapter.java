package yhh.tinklabstest.views.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.R;
import yhh.tinklabstest.data.type.BaseType;
import yhh.tinklabstest.data.type.LocalImageType;
import yhh.tinklabstest.data.type.ProgressBarType;
import yhh.tinklabstest.data.type.LocalTextType;
import yhh.tinklabstest.util.ItemDiff;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_TEXT = 1;
    private static final int TYPE_PROGRESS_BAR = 2;

    private final List<BaseType> mBaseType = new ArrayList<>();
    private final WeakReference<Context> mContext;

    private final int mLargeImageWidth;
    private final int mLargeImageHeight;

    public RecyclerAdapter(Context context) {
        mContext = new WeakReference<>(context);
        mLargeImageWidth = context.getResources().getDisplayMetrics().widthPixels;
        mLargeImageHeight = context.getResources().getDimensionPixelSize(R.dimen.holder_type_large_image_height);
    }

    public void updateData(List<BaseType> newData) {
        final ItemDiff itemDiff = new ItemDiff(mBaseType, newData);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(itemDiff);
        mBaseType.clear();
        mBaseType.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    public void startLoading() {
        mBaseType.add(new ProgressBarType());
        notifyItemInserted(getItemCount() - 1);
    }

    @SuppressLint("InflateParams")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = mContext.get();
        if (context == null) return null;
        if (TYPE_IMAGE == viewType) {
            return new ImageTypeViewHolder(LayoutInflater.from(context).inflate(R.layout.holder_type_image, null));
        } else if (TYPE_TEXT == viewType) {
            return new TextTypeViewHolder(LayoutInflater.from(context).inflate(R.layout.holder_type_text, null));
        } else if (TYPE_PROGRESS_BAR == viewType) {
            return new LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.holder_type_loading, null));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Context context = mContext.get();
        if (context == null) return;
        final BaseType baseType = getItem(position);
        final int viewType = getItemViewType(position);
        if (TYPE_IMAGE == viewType) {
            final LocalImageType localImageType = (LocalImageType) baseType;
            final ImageTypeViewHolder viewHolder = (ImageTypeViewHolder) holder;
            viewHolder.getLoadingView().setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Uri.parse(localImageType.getImageURL()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(mLargeImageWidth, mLargeImageHeight)
                    .centerCrop()
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                            viewHolder.getLoadingView().setVisibility(View.INVISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            viewHolder.getLoadingView().setVisibility(View.INVISIBLE);
                            return false;
                        }
                    })
                    .into(viewHolder.getImageView());
        } else if (TYPE_TEXT == viewType) {
            LocalTextType localTextType = (LocalTextType) baseType;
            TextTypeViewHolder viewHolder = (TextTypeViewHolder) holder;
            Glide.with(context)
                    .load(Uri.parse(localTextType.getImageURL()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(viewHolder.getImageView());
            viewHolder.getText().setText(localTextType.getText());
            viewHolder.getTitle().setText(localTextType.getTitle());
        } else //noinspection StatementWithEmptyBody
            if (TYPE_PROGRESS_BAR == viewType) {
            // do nothing
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseType item = getItem(position);
        if (item instanceof LocalImageType) return TYPE_IMAGE;
        else if (item instanceof LocalTextType) return TYPE_TEXT;
        else if (item instanceof ProgressBarType) return TYPE_PROGRESS_BAR;
        else throw new UnsupportedOperationException();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ImageTypeViewHolder) {
            Glide.clear(((ImageTypeViewHolder) holder).getImageView());
        } else if (holder instanceof TextTypeViewHolder) {
            Glide.clear(((TextTypeViewHolder) holder).getImageView());
        }
    }

    @Override
    public int getItemCount() {
        return mBaseType.size();
    }

    private BaseType getItem(int position) {
        return mBaseType.get(position);
    }
}
