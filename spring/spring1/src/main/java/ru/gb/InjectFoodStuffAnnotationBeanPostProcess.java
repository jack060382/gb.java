package ru.gb;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class InjectFoodStuffAnnotationBeanPostProcess implements BeanPostProcessor {

    String[] food = {"meat", "fish", "cookie", "apple"};

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        for (Field field : bean.getClass().getDeclaredFields()) {
            InjectFoodStaff annotation = field.getAnnotation(InjectFoodStaff.class);
            if (annotation != null) {
                Random random = new Random();
                String foodStuff = food[random.nextInt(food.length - 1)];
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, foodStuff);
            }
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
