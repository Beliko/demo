package com.example.demo.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;
import com.example.demo.component.ADate;

@Component
public class ADateFactory extends AbstractFactoryBean<ADate> {

    public ADateFactory() {
        super.setSingleton(false);
    }

    @Override
    public Class<?> getObjectType() {
        return ADate.class;
    }

    @Override
    protected ADate createInstance() throws Exception {
        return new ADate(System.nanoTime());
    }
}
