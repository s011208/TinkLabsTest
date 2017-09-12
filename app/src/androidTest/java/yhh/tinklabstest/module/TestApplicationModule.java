package yhh.tinklabstest.module;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yhh.tinklabstest.data.DataKeeper;

@Module
public class TestApplicationModule {

    @Provides
    @Singleton
    public DataKeeper providerDataKeeper() {
        return Mockito.mock(DataKeeper.class);
    }
}
