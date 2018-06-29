package cn.candy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理器
 * 
 * @author jx003
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	// 前端控制器DispatcherServlet在进行HandlerMapping、调用HandlerAdapter执行Handler过程中，如果遇到异常就会执行此方法
	// handler最终要执行的Handler，它的真实身份是HandlerMethod
	// Exception ex就是接收到的异常信息
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 输出异常
		ex.printStackTrace();

		return new ModelAndView();
	}

}
