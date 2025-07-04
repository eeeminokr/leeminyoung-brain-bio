package csbrain.target.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.toolbox.plugins.Concat;


import csbrain.common.service.CommonService;
import csbrain.common.service.MbDmVO;
import csbrain.common.util.Constant;
import csbrain.data.service.DataService;
import csbrain.data.service.MbResesnsbVO;
import csbrain.main.service.MemberService;
import csbrain.main.service.MemberVO;
import csbrain.target.service.TargetService;
import csbrain.target.service.TargetVO;

@Controller
@RequestMapping("/include")


public class TargetController {

	/** Sevice Class */
	@Resource(name = "MemberService")
	private MemberService memberService;

	@Resource(name = "DataService")
	private DataService dataService;

	@Resource(name = "CommonService")
	private CommonService commonService;
	
	
	@Resource(name ="TargetService")
	private TargetService targetService;
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	
	@RequestMapping("/target/csbr1010_insertIdx_OpenPopUp.do")
	public ModelAndView csbr1010_insertObjectidx_openPopUp(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/target/csbr1010_insertIdx_OpenPopUp");
		mav.addObject("success","true");
		
		
		return mav;
		
	}
	

	
	
}
