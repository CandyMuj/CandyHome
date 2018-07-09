package cn.candy.exception;

import java.io.IOException;

import javax.servlet.ServletException;
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

		// 获取异常错误的值栈数据
		StringBuffer sbuff = new StringBuffer();
		sbuff.append((ex.getClass() + ": " + ex.getMessage()).substring(6)).append("<br/>");
		StackTraceElement[] stackTraceElements = ex.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			sbuff.append("&emsp;&emsp;&emsp;").append(stackTraceElement).append("<br/>");
		}

		try {
			request.setAttribute("message", sbuff.toString());
			request.getRequestDispatcher("/front/outRes/jsp/info/exceptionError.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

		// 由于视图解析器的前缀的问题，这里设置会报错
		// ModelAndView modelAndView = new ModelAndView();
		// modelAndView.setViewName("/front/outRes/jsp/info/exceptionError");
		// modelAndView.setViewName("/front/outRes/jsp/info/exceptionError.jsp");

		return new ModelAndView();
	}

}
