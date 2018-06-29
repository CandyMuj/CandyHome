package com.candy.commons.springhelp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>SpringContextHelper</code>
 * </p>
 * Description:
 *
 * @author jx003
 * @date 2018/06/29
 */
/*
 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
 * 标记为组件后在启动服务时，会将此类实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {

	/**
	 * Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 重写并初始化上下文
	 * @param applicationContext 应用上下文
	 * @throws BeansException bean异常
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// 初始化applicationContext
		SpringContextHelper.applicationContext = applicationContext;
	}

	/**
	 * 通过类获取
	 * @param clazz 注入的类
	 * @param <T> 返回类型
	 * @return 返回这个bean
	 * @throws BeansException bean异常
	 */
	public static <T> T getBean(Class<T> clazz) throws BeansException {
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 * 通过名字获取
	 * @param name 名字
	 * @param <T> 返回类型
	 * @return 返回这个bean
	 * @throws BeansException bean异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

}
