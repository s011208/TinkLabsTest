package yhh.tinklabstest.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import yhh.tinklabstest.R;
import yhh.tinklabstest.data.DataLoader;

/**
 * Main Viewpager adapter
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int POSITION_CITY_GUIDE = 0;
    private static final int POSITION_EAT = 1;
    private static final int POSITION_SHOP = 2;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment baseFragment = (BaseFragment) super.instantiateItem(container, position);
        if (position == POSITION_CITY_GUIDE) {
            baseFragment.setDataLoader(new DataLoader(mContext, DataLoader.FILE_NAME_CITY_GUIDE));
        } else if (position == POSITION_EAT) {
            baseFragment.setDataLoader(new DataLoader(mContext, DataLoader.FILE_NAME_EAT));
        } else if (position == POSITION_SHOP) {
            baseFragment.setDataLoader(new DataLoader(mContext, DataLoader.FILE_NAME_SHOP));
        } else {
            throw new UnsupportedOperationException();
        }
        baseFragment.initData();
        return baseFragment;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = new BaseFragment();
        if (position == POSITION_CITY_GUIDE) {
            return baseFragment;
        } else if (position == POSITION_EAT) {
            return baseFragment;
        } else if (position == POSITION_SHOP) {
            return baseFragment;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_CITY_GUIDE) {
            return mContext.getString(R.string.tab_city_guide);
        } else if (position == POSITION_EAT) {
            return mContext.getString(R.string.tab_eat);
        } else if (position == POSITION_SHOP) {
            return mContext.getString(R.string.tab_shop);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
