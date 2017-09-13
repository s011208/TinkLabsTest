package yhh.tinklabstest.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yhh.tinklabstest.data.DataKeeper;

@Module
public class ApplicationModule {

    private final DataKeeper mDataKeeper = new DataKeeper();

    public ApplicationModule() {
    }

    @Provides
    @Singleton
    public DataKeeper provideDataKeeper() {
        return mDataKeeper;
    }
}
