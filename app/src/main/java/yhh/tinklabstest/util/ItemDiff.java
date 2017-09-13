package yhh.tinklabstest.util;

import android.support.v7.util.DiffUtil;

import java.util.List;

import yhh.tinklabstest.data.type.BaseType;

/**
 * check the different between two lists and update for RecyclerView
 */
public class ItemDiff extends DiffUtil.Callback {
    private List<? extends BaseType> mOldList;
    private List<? extends BaseType> mNewList;

    public ItemDiff(List<? extends BaseType> oldList, List<? extends BaseType> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getUUID().equals(mNewList.get(newItemPosition).getUUID());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
