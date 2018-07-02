package cn.candy.candyhome.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.candy.candyhome.index.mapper.ItemsMapper;
import cn.candy.candyhome.index.po.Items;

@SuppressWarnings("unused")
@Controller
public class TestController {

	@Autowired
	private ItemsMapper itemsMapper;

	@RequestMapping("/test")
	public String test(Model model, @Validated Items items, BindingResult bindingResult) {

		model.addAttribute("item", itemsMapper.selectByPrimaryKey(1));

		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError objectError : errors) {
				System.out.println("-------- > " + objectError.getDefaultMessage());
			}
		}

		return "itemsList";
	}

	@RequestMapping("/test1")
	public void test1() throws Exception{
		int a = 2/0;
//		System.out.println(DefaultSettings.get("abc"));
	}

	@RequestMapping("/test3")
	public void test3() {
		itemsMapper.selectByPrimaryKey(1);
		itemsMapper.selectByPrimaryKey(1);
	}

}
