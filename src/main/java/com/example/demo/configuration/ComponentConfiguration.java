package com.example.demo.configuration;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.example.demo.component.ADate;
import com.example.demo.component.APrototype;
import com.example.demo.component.ASingleton;

@Configuration
public class ComponentConfiguration {


    @Bean("prototype_bean")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public APrototype prototypeComponent(ObjectFactory<ADate> aDate, @Qualifier("singleton_bean") ASingleton singletonComponent) {
        return new APrototype(aDate, singletonComponent);
    }
    
    @Bean("singleton_bean")
    public ASingleton getSingletonComponent(ObjectFactory<ADate> aDate, ObjectFactory<APrototype> factory) {
        return new ASingleton(aDate, factory);
    }
    
}
