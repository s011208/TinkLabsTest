package yhh.tinklabstest.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yhh.tinklabstest.data.DataKeeper;

@Module
public class ApplicationModule {

    Application mApplication;
    DataKeeper mDataKeeper = new DataKeeper();

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public DataKeeper provideDataKeeper() {
        return mDataKeeper;
    }

}
