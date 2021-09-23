package com.example.demo.component;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class APrototypeTest {
    
    private ASingleton singletonComponent;
    private ASingleton singletonBean;
    private APrototype prototypeComponent;
    private APrototype prototypeBean;
    @Autowired
    private BeanFactory beanFactory;
    
    @BeforeEach
    void init() {
        singletonComponent = beanFactory.getBean("singleton_component", ASingleton.class);
        singletonBean = beanFactory.getBean("singleton_bean", ASingleton.class);
        prototypeComponent= beanFactory.getBean("prototype_bean", APrototype.class);
        prototypeBean= beanFactory.getBean("prototype_bean", APrototype.class);
    }
    
    @Test
    void sonSiempreDiferentes() {
        //el isSameAs funciona
        assertThat(prototypeBean).isSameAs(prototypeBean);
        assertThat(prototypeComponent).isSameAs(prototypeComponent);
        //Son siempre objetos diferentes sin importar como los pida
        assertThat(prototypeComponent).isNotSameAs(prototypeBean);
        assertThat(prototypeBean).isNotSameAs(prototypeComponent);
        assertThat(singletonComponent.getPrototypeComponent()).isNotSameAs(singletonBean.getPrototypeComponent())
        .isNotSameAs(prototypeComponent).isNotSameAs(prototypeBean);
    }
    
}
