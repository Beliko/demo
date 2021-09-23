package com.example.demo.factory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;
import com.example.demo.component.ADate;
import com.example.demo.component.APrototype;
import com.example.demo.component.ASingleton;

@Component("singleton_factory")
public class SingletonFactory extends AbstractFactoryBean<ASingleton> {

    private ObjectFactory<ADate> dateFactory;
    private ObjectFactory<APrototype> prototypeFactory;

    @Autowired
    public SingletonFactory(ObjectFactory<ADate> dateFactory,
        @Qualifier("prototype_factory") ObjectFactory<APrototype> prototypeFactory) {
        this.dateFactory = dateFactory;
        this.prototypeFactory = prototypeFactory;
        super.setSingleton(true);
    }

    @Override
    public Class<?> getObjectType() {
        return ASingleton.class;
    }

    @Override
    protected ASingleton createInstance() throws Exception {
        return new ASingleton(dateFactory, prototypeFactory);
    }
}
