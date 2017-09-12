package yhh.tinklabstest.components;

import javax.inject.Singleton;

import dagger.Component;
import yhh.tinklabstest.component.ApplicationComponent;
import yhh.tinklabstest.module.TestApplicationModule;

@Singleton
@Component(modules = TestApplicationModule.class)
public interface TestComponent extends ApplicationComponent {
}
