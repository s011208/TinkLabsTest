package yhh.tinklabstest.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import yhh.tinklabstest.R;


class TextTypeViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mText;

    TextTypeViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mTitle = itemView.findViewById(R.id.title);
        mText = itemView.findViewById(R.id.text);
    }

    ImageView getImageView() {
        return mImageView;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public TextView getText() {
        return mText;
    }
}
