package com.example.demo.factory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;
import com.example.demo.component.ADate;
import com.example.demo.component.APrototype;
import com.example.demo.component.ASingleton;

@Component("prototype_factory")
public class PrototypeFactory extends AbstractFactoryBean<APrototype> {

    private ObjectFactory<ADate> dateFactory;
    private ASingleton singletonBean;

    @Autowired
    public PrototypeFactory(ObjectFactory<ADate> dateFactory, @Qualifier("singleton_factory")ASingleton singletonBean) {
        this.dateFactory = dateFactory;
        this.singletonBean = singletonBean;
        super.setSingleton(false);
    }

    @Override
    public Class<?> getObjectType() {
        return APrototype.class;
    }

    @Override
    protected APrototype createInstance() throws Exception {
        return new APrototype(dateFactory, singletonBean);
    }
}
