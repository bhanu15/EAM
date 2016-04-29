package com.rakuten.eam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.eam.model.User;
import com.rakuten.eam.service.UserService;


@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, User user){
		ModelAndView model = new ModelAndView("login");
		//LoginBean loginBean = new LoginBean();
		//model.addObject("loginBean", loginBean);
		return model;
	}
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user){
		ModelAndView model= null;

		boolean isValidUser = userService.authenticateUser(user);
		if(isValidUser){		
			request.setAttribute("user", user);
			model = new ModelAndView("welcome");
		}
		else{
			request.setAttribute("message", "Invalid credentials!!");
			model = new ModelAndView("login");	
		}
		return model;
	}
}
