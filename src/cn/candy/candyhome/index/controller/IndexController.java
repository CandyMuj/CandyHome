package cn.candy.candyhome.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.candy.commons.controller.SuperController;

@Controller
@RequestMapping("/index")
public class IndexController extends SuperController {

	/**
	 * 首页页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index() throws Exception {

		System.out.println(super.getUserInfo().getUserAccount().getUid());
		return "";
	}
	
}
