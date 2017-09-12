package yhh.tinklabstest.component;

import javax.inject.Singleton;

import dagger.Component;
import yhh.tinklabstest.module.ApplicationModule;
import yhh.tinklabstest.data.DataKeeper;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    DataKeeper getDataKeeper();
}
