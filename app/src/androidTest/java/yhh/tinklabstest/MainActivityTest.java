package yhh.tinklabstest;

import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.ViewPager;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import yhh.tinklabstest.data.DataLoader;
import yhh.tinklabstest.data.type.BaseType;
import yhh.tinklabstest.rule.TestComponentRule;
import yhh.tinklabstest.util.RecyclerViewItemCountAssertion;
import yhh.tinklabstest.util.RecyclerViewMatcher;
import yhh.tinklabstest.util.TestDataSet;
import yhh.tinklabstest.util.ViewPagerIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private final TestComponentRule mComponent = new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public TestRule mChain = RuleChain.outerRule(mComponent).around(mActivity);

    @Test
    public void recyclerViewItemCount_is_7() throws JSONException {
        Mockito.when(mComponent.getDataKeeper().getItems(Mockito.anyInt()))
                .thenReturn(TestDataSet.constructFakeDataSet());

        mActivity.launchActivity(null);

        onView(allOf(isDisplayed(), withId(R.id.recycler_view)))
                .check(new RecyclerViewItemCountAssertion(7));
    }

    @Test
    public void recyclerViewItemCount_is_5() throws JSONException {
        List<BaseType> items = TestDataSet.constructFakeDataSet();
        items.remove(6);
        items.remove(5);

        Mockito.when(mComponent.getDataKeeper().getItems(Mockito.anyInt()))
                .thenReturn(items);

        mActivity.launchActivity(null);

        onView(allOf(isDisplayed(), withId(R.id.recycler_view)))
                .check(new RecyclerViewItemCountAssertion(5));
    }

    @Test
    public void recyclerViewItemText_is_expected() throws JSONException {
        Mockito.when(mComponent.getDataKeeper().getItems(Mockito.anyInt()))
                .thenReturn(TestDataSet.constructFakeDataSet());

        mActivity.launchActivity(null);

        onView(withRecyclerView(R.id.recycler_view)
                .atPositionOnView(0, R.id.title))
                .check(matches(withText("西門紅樓")));

        onView(withRecyclerView(R.id.recycler_view)
                .atPositionOnView(0, R.id.text))
                .check(matches(withText("西門町應該是每個人第一次遊台北都必定會去的行程之一，旅遊書總會形容這個地方是「年青人潮流勝地」，但多年前第一次去的時候感覺真的不太好，簡單來說這就是一個很MK的地方。")));
    }

    @Test
    public void swipeTab_to_left() throws JSONException {
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_CITY_GUIDE))
                .thenReturn(TestDataSet.constructFakeDataSet());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_EAT))
                .thenReturn(TestDataSet.constructFakeDataSet2());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_SHOP))
                .thenReturn(TestDataSet.constructFakeDataSet());

        mActivity.launchActivity(null);

        registerIdlingResources(
                new ViewPagerIdlingResource((ViewPager) mActivity.getActivity().findViewById(R.id.viewpager)));

        onView(withId(R.id.viewpager)).perform(swipeLeft());

        onView(allOf(isDisplayed(), withId(R.id.recycler_view)))
                .check(new RecyclerViewItemCountAssertion(1));
    }

    @Test
    public void clickTab_to_eat() throws JSONException {
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_CITY_GUIDE))
                .thenReturn(TestDataSet.constructFakeDataSet());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_EAT))
                .thenReturn(TestDataSet.constructFakeDataSet2());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_SHOP))
                .thenReturn(TestDataSet.constructFakeDataSet());

        mActivity.launchActivity(null);

        registerIdlingResources(
                new ViewPagerIdlingResource((ViewPager) mActivity.getActivity().findViewById(R.id.viewpager)));

        onView(withText(R.string.tab_eat)).perform(click());

        onView(allOf(isDisplayed(), withId(R.id.recycler_view)))
                .check(new RecyclerViewItemCountAssertion(1));
    }

    @Test
    public void keepAtTheSameTab_after_rotate() throws JSONException {
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_CITY_GUIDE))
                .thenReturn(TestDataSet.constructFakeDataSet());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_EAT))
                .thenReturn(TestDataSet.constructFakeDataSet2());
        Mockito.when(mComponent.getDataKeeper().getItems(DataLoader.DATA_TYPE_SHOP))
                .thenReturn(TestDataSet.constructFakeDataSet());

        mActivity.launchActivity(null);

        registerIdlingResources(
                new ViewPagerIdlingResource((ViewPager) mActivity.getActivity().findViewById(R.id.viewpager)));

        onView(withText(R.string.tab_eat)).perform(click());

        mActivity.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mActivity.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withText(R.string.tab_eat)).check(matches(isSelected()));
    }


    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
