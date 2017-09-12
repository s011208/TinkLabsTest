package yhh.tinklabstest.util;

import android.support.test.espresso.IdlingResource;
import android.support.v4.view.ViewPager;

public class ViewPagerIdlingResource implements IdlingResource {

    private boolean mIdle = true;

    private ResourceCallback mResourceCallback;

    public ViewPagerIdlingResource(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPagerListener());
    }

    @Override
    public String getName() {
        return "View Pager Idling Resource";
    }

    @Override
    public boolean isIdleNow() {
        return mIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.mResourceCallback = resourceCallback;
    }

    private class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            mIdle = (state == ViewPager.SCROLL_STATE_IDLE
                    // Treat dragging as mIdle, or Espresso will block itself when swiping.
                    || state == ViewPager.SCROLL_STATE_DRAGGING);
            if (mIdle && mResourceCallback != null) {
                mResourceCallback.onTransitionToIdle();
            }
        }
    }
}