package yhh.tinklabstest.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import yhh.tinklabstest.R;

class ImageTypeViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private ProgressBar mLoadingView;

    ImageTypeViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mLoadingView = itemView.findViewById(R.id.loading);
    }

    ImageView getImageView() {
        return mImageView;
    }

    ProgressBar getLoadingView() {
        return mLoadingView;
    }
}
