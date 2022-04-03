package ru.gb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class HelloSpring {
    public static void main(String[] args) {
        //System.out.println("Hello Spring");
/*
        MessageProvider messageProvider = new HelloSpringMessageProvider();
        ConsoleMessageRender messageRender = new ConsoleMessageRender();
        messageRender.setMessageProvider(messageProvider);
        messageRender.render();
*/

        /*
        MessageProvider messageProvider = MessageSupportFactory.getInstance().getMessageProvider();
        MessageRender messageRender = MessageSupportFactory.getInstance().getMessageRender();
        messageRender.setMessageProvider(messageProvider);
        messageRender.render();
         */

        /*
        MessageRender messageRender = MessageSupportFactory.getInstance().getMessageRender();
        messageRender.render();
         */
/*
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MessageRender render = context.getBean("render", MessageRender.class);
        MessageRender render1 = (MessageRender) context.getBean("render");
        MessageRender render2 = context.getBean( MessageRender.class);
        render.render();
 */

        /*
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
        context.close();
         */

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloConfiguration.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
        context.close();
    }
}
