package yhh.tinklabstest;

import android.app.Application;

import yhh.tinklabstest.component.ApplicationComponent;
import yhh.tinklabstest.component.DaggerApplicationComponent;
import yhh.tinklabstest.module.ApplicationModule;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this)).build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
