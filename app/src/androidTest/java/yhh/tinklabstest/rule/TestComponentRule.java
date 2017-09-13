package yhh.tinklabstest.rule;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import yhh.tinklabstest.MyApplication;
import yhh.tinklabstest.components.DaggerTestComponent;
import yhh.tinklabstest.components.TestComponent;
import yhh.tinklabstest.data.DataKeeper;

public class TestComponentRule implements TestRule {
    private TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        mTestComponent = DaggerTestComponent.create();
    }

    public DataKeeper getDataKeeper() {
        return mTestComponent.getDataKeeper();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                MyApplication application = (MyApplication) mContext.getApplicationContext();
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
