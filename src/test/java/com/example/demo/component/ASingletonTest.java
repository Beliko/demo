package com.example.demo.component;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ASingletonTest {

    private ASingleton singletonComponent;
    private ASingleton singletonComponent2;
    private ASingleton singletonBean;
    private ASingleton singletonBean2;
    @Autowired
    @Qualifier("singleton_component")
    private ObjectFactory<ASingleton> singletonComponentFactory;
    @Autowired
    @Qualifier("singleton_component")
    private ObjectFactory<ASingleton> singletonComponentFactory2;
    @Autowired
    @Qualifier("singleton_bean")
    private ObjectFactory<ASingleton> singletonBeanFactory;
    @Autowired
    @Qualifier("singleton_bean")
    private ObjectFactory<ASingleton> singletonBeanFactory2;
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ApplicationContext context;

    @BeforeEach
    void init() {
        singletonComponent = beanFactory.getBean("singleton_component", ASingleton.class);
        singletonComponent2 = beanFactory.getBean("singleton_component", ASingleton.class);
        singletonBean = beanFactory.getBean("singleton_bean", ASingleton.class);
        singletonBean2 = beanFactory.getBean("singleton_bean", ASingleton.class);
    }

    @Test
    void componentEsSingleton() {
        assertThat(context.isSingleton("singleton_component")).isTrue();
    }

    @Test
    void beanEsSingleton() {
        assertThat(context.isSingleton("singleton_bean")).isTrue();
    }

    @Test
    void factoryEsSingleton() {
        assertThat(context.isSingleton("singleton_factory")).isTrue();
    }

    @Test
    void conComponentElSingletonSeMantiene() {
        // Son el mismo objeto sin importar cuantas veces se pida uno nuevo
        assertThat(singletonComponent).isSameAs(singletonComponent2)
            .isSameAs(beanFactory.getBean("singleton_component"));
        // NO son el mismo objeto si se pide con diferente nombre
        assertThat(beanFactory.getBean("singleton_component")).isNotSameAs(beanFactory.getBean("singleton_bean"));
        // Seran el mismo objeto si pedimos la factory por el mismo nombre
        assertThat(singletonComponentFactory).isNotSameAs(singletonComponentFactory2);
        // Continuan siendo el mismo objeto
        assertThat(singletonComponentFactory.getObject()).isSameAs(singletonComponent).isSameAs(singletonComponent2);
        assertThat(singletonComponentFactory2.getObject()).isSameAs(singletonComponent).isSameAs(singletonComponent2);
        // Esto depende de como se construya el objeto
        assertThat(singletonComponent.getPrototypeComponent().getComponent()).isSameAs(singletonComponent)
            .isSameAs(singletonComponent2);
        assertThat(singletonComponent2.getPrototypeComponent().getComponent()).isSameAs(singletonComponent)
            .isSameAs(singletonComponent2);
        // Mantiene el mismo dato
        assertThat(singletonComponent.getDate()).isSameAs(singletonComponent2.getDate());
    }

    @Test
    void conBeanElSingletonSeMantiene() {
        assertThat(singletonBean).isSameAs(singletonBean2).isSameAs(beanFactory.getBean("singleton_bean"));
        assertThat(beanFactory.getBean("singleton_bean")).isNotSameAs(beanFactory.getBean("singleton_component"));
        assertThat(singletonBeanFactory).isNotSameAs(singletonComponentFactory2);
        assertThat(singletonBeanFactory.getObject()).isSameAs(singletonBean).isSameAs(singletonBean2);
        assertThat(singletonBeanFactory2.getObject()).isSameAs(singletonBean).isSameAs(singletonBean2);
        assertThat(singletonBean.getPrototypeComponent().getDate())
            .isNotSameAs(singletonBean2.getPrototypeComponent().getDate());
        assertThat(singletonBean.getPrototypeComponent().getComponent()).isNotSameAs(singletonBean)
            .isNotSameAs(singletonBean2);
        assertThat(singletonBean.getPrototypeComponent().getComponent()).isNotSameAs(singletonBean)
            .isNotSameAs(singletonBean2);
        assertThat(singletonBean.getPrototypeComponent()).isNotSameAs(singletonBean2.getPrototypeComponent());
        assertThat(singletonBean.getDate()).isSameAs(singletonBean2.getDate());
    }

    @Test
    void diferenteNombreSonDiferentes() {
        // al cambiar el nombre, cambia el objeto. "==" != "equasl"
        assertThat(beanFactory.getBean("singleton_bean")).isNotSameAs(beanFactory.getBean("singleton_component"));
        assertThat(singletonBean).isNotSameAs(singletonComponent);
        assertThat(singletonBean2).isNotSameAs(singletonComponent2);
    }
}
