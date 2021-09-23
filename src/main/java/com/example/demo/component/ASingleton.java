package com.example.demo.component;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("singleton_component")
@Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ASingleton {

    private final ADate singleDate;
    private final ObjectFactory<APrototype> prototypeBean;

    @Autowired
    public ASingleton(ObjectFactory<ADate> dateFactory, @Qualifier("prototype_component")ObjectFactory<APrototype> objectFactory) {
        this.singleDate = dateFactory.getObject();
        this.prototypeBean = objectFactory;
    }

    public ADate getDate() {
        return singleDate;
    }

    public APrototype getPrototypeComponent() {
        return prototypeBean.getObject();
    }
}
