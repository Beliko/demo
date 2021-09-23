package com.example.demo.component;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("prototype_component")
@Primary
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class APrototype{

    private final ADate mydate;
    private final ASingleton component;

    @Autowired
    public APrototype(ObjectFactory<ADate> mydate, @Qualifier("singleton_component") ASingleton singletonBean) {
        this.mydate = mydate.getObject();
        this.component = singletonBean;
    }

    public ADate getDate() {
        return mydate;
    }

    
    public ASingleton getComponent() {
        return component;
    }
    
    
}
