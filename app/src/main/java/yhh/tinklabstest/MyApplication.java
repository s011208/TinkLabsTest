package yhh.tinklabstest;

import android.app.Application;

import yhh.tinklabstest.component.ApplicationComponent;
import yhh.tinklabstest.component.DaggerApplicationComponent;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.create();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
