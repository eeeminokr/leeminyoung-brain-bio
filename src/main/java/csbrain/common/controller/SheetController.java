package csbrain.common.controller;

import static org.mockito.Matchers.intThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import csbrain.common.service.CommonService;
import csbrain.common.service.MbAhVO;
import csbrain.common.service.MbC1VO;
import csbrain.common.service.MbC2VO;
import csbrain.common.service.MbC3VO;
import csbrain.common.service.MbCdr1VO;
import csbrain.common.service.MbCdr2VO;
import csbrain.common.service.MbDgVO;
import csbrain.common.service.MbDiaVO;
import csbrain.common.service.MbDmVO;
import csbrain.common.service.MbDrVO;
import csbrain.common.service.MbEqVO;
import csbrain.common.service.MbFhVO;
import csbrain.common.service.MbFmhVO;
import csbrain.common.service.MbGdsVO;
import csbrain.common.service.MbHrsdVO;
import csbrain.common.service.MbIcVO;
import csbrain.common.service.MbKdsqVO;
import csbrain.common.service.MbKdsqvVO;
import csbrain.common.service.MbKiadlVO;
import csbrain.common.service.MbKmmse2VO;
import csbrain.common.service.MbLabVO;
import csbrain.common.service.MbMbVO;
import csbrain.common.service.MbMhVO;
import csbrain.common.service.MbMmseVO;
import csbrain.common.service.MbPeVO;
import csbrain.common.service.MbPhaVO;
import csbrain.common.service.MbSJVO;
import csbrain.common.service.MbShVO;
import csbrain.common.service.MbSmVO;
import csbrain.common.service.MbSmcqVO;
import csbrain.common.service.MbSpVO;
import csbrain.common.service.MbTestblodVO;
import csbrain.common.service.SheetService;
import csbrain.common.util.Constant;
import csbrain.data.service.DataService;
import csbrain.data.service.MbNrcdVO;
import csbrain.data.service.MbObjectVO;
import csbrain.main.service.MemberService;
import csbrain.main.service.MemberVO;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/include/sheet")
public class SheetController {

	@Resource(name = "MemberService") 
	private MemberService memberService;
	@Resource(name = "SheetService")
	private SheetService sheetService;
	@Resource(name = "CommonService")
	private CommonService commonService;
	@Resource(name = "DataService")
	private DataService dataService;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/view_mmseds.do")
	public ModelAndView view_mmseds(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbMmseVO> mmseds_list = sheetService.select_mb_mmseds_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_mmseds");
		mav.addObject("success", true);
		mav.addObject("mmseds_list", mmseds_list);

		return mav;
	}

	@RequestMapping("/view_kmmse.do")
	public ModelAndView view_kmmse(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbMmseVO> kmmse_list = sheetService.select_mb_kmmse_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_kmmse");
		mav.addObject("success", true);
		mav.addObject("kmmse_list", kmmse_list);

		return mav;
	}

	@RequestMapping("/view_mmsekc.do")
	public ModelAndView view_mmsekc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbMmseVO> mmsekc_list = sheetService.select_mb_mmsekc_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_mmsekc");
		mav.addObject("success", true);
		mav.addObject("mmsekc_list", mmsekc_list);

		return mav;
	}
	@RequestMapping("/view_kmmse2.do")
	public ModelAndView view_kmmse2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);                          
		String subject_idx = sessionMember.getIdx(); // login user idx                                              
		String userId = sessionMember.getId();                                                                      
		String Name = sessionMember.getName();                                                                      
		System.out.println(subject_idx+":loginuseridx");                                                            
		System.out.println(userId+":usrid");                                                                        
		System.out.println(Name+"::name");       
		

		
		String object_idx = request.getParameter("object_idx");                                                     
		String exam_num = request.getParameter("exam_num");                                                         
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");                                                      
		System.out.println(object_idx);                                                                             
		                                                                                                            
		Map<String, String> map = new HashMap<String, String>(2);                                                   
		map.put("objectIdx", object_idx);                                                                           
		map.put("examNum", exam_num);  
		
		List<MbKmmse2VO> kmmse2_list = sheetService.select_mb_kmmse2_list(map);
		MbKmmse2VO mbKmmse2VO = new MbKmmse2VO();
		for(MbKmmse2VO mbKmmse2VO1 : kmmse2_list ) {
			
			System.out.println(mbKmmse2VO1.getSelectionNum()+":::차수");
			mbKmmse2VO.setTestDay(mbKmmse2VO1.getTestDay());
		}
		String testDay = mbKmmse2VO.getTestDay();
		
		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_kmmse2");
		mav.addObject("idx", object_idx);
		mav.addObject("exam_id", exam_id);
		mav.addObject("action", action);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("kmmse2_list", kmmse2_list);

		return mav;
	}
	
	
	@RequestMapping("/view_eq.do")
	public ModelAndView view_eq(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);                          
		String subject_idx = sessionMember.getIdx(); // login user idx                                              
		String userId = sessionMember.getId();                                                                      
		String Name = sessionMember.getName();                                                                      
		System.out.println(subject_idx+":loginuseridx");                                                            
		System.out.println(userId+":usrid");                                                                        
		System.out.println(Name+"::name");       
		

		
		String object_idx = request.getParameter("object_idx");                                                     
		String exam_num = request.getParameter("exam_num");                                                         
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");                                                        
		System.out.println(object_idx);                                                                             
		                                                                                                            
		Map<String, String> map = new HashMap<String, String>(2);                                                   
		map.put("objectIdx", object_idx);                                                                           
		map.put("examNum", exam_num);  
		
		List<MbEqVO> eq_list = sheetService.select_mb_eq_list(map);
		MbEqVO mbEqVO = new MbEqVO();
		for(MbEqVO mbEqVO1 : eq_list ) {
			
			System.out.println(mbEqVO1.getSelectionNum()+":::차수");
			mbEqVO.setTestDay(mbEqVO1.getTestDay());
			
		}
		String testDay = mbEqVO.getTestDay();
		
		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_eq");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("eq_list", eq_list);

		return mav;
	}
	
	@RequestMapping(value="/view_saveeq.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_saveeq(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,
			@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
			@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		String exam_idS = request.getParameter("exam_id");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println(exam_idS+"::exam_idS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");

		System.out.println("subject_idx::::"+subject_idx);
		
		System.out.println(object_idx+":object_idx");
		
		System.out.println("잘넘어왔습니당");
		
		
		
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
			   

		            map.put("eq01" ,radioList.get(0));
		           	map.put("eq02" ,radioList.get(1));
		           	map.put("eq03" ,radioList.get(2));
		           	map.put("eq04" ,radioList.get(3));
		           	map.put("eq05" ,radioList.get(4));
				
					    
					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);						
					
					
					num = sheetService.update_mb_eq_newlist(map);
					
			}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbEqVO> eqlist =  sheetService.select_mb_eq_list(chkmap);
					
					
					if(eqlist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					  map1.put("objectIdx", idx);
					  map1.put("subjectIdx",subject_idx);			
			
			            map1.put("eq01" ,radioList.get(0));
			           	map1.put("eq02" ,radioList.get(1));
			           	map1.put("eq03" ,radioList.get(2));
			           	map1.put("eq04" ,radioList.get(3));
			           	map1.put("eq05" ,radioList.get(4)); 


						
						
					 map1.put("testDay",sdate);
					 map1.put("insertUserid",subject_idx);
					 map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_eq_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						
			            map2.put("eq01" ,radioList.get(0));
			           	map2.put("eq02" ,radioList.get(1));
			           	map2.put("eq03" ,radioList.get(2));
			           	map2.put("eq04" ,radioList.get(3));
			           	map2.put("eq05" ,radioList.get(4));

						
						map2.put("testDay",sdate);
				
						map2.put("insertUserid",subject_idx);
						map2.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_eq_newlist(map2);
				
						}
					
				  }
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);				
				   List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
				
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
	  			
		            map.put("eq01" ,radioList.get(0));
		           	map.put("eq02" ,radioList.get(1));
		           	map.put("eq03" ,radioList.get(2));
		           	map.put("eq04" ,radioList.get(3));
		           	map.put("eq05" ,radioList.get(4));
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_eq_editlist(map);
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
				
		            map1.put("eq01" ,radioList.get(0));
		           	map1.put("eq02" ,radioList.get(1));
		           	map1.put("eq03" ,radioList.get(2));
		           	map1.put("eq04" ,radioList.get(3));
		           	map1.put("eq05" ,radioList.get(4));
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_eq_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_eq");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
	}
			
			
			
			return mav;
	}	
	
	@RequestMapping("/view_pe.do")
	public ModelAndView view_pe(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);                          
		String subject_idx = sessionMember.getIdx(); // login user idx                                              
		String userId = sessionMember.getId();                                                                      
		String Name = sessionMember.getName();                                                                      
		System.out.println(subject_idx+":loginuseridx");                                                            
		System.out.println(userId+":usrid");                                                                        
		System.out.println(Name+"::name");       
		

		
		String object_idx = request.getParameter("object_idx");                                                     
		String exam_num = request.getParameter("exam_num");                                                         
		String exam_id = request.getParameter("exam_id");    
		String action = request.getParameter("action");
		System.out.println(object_idx);                                                                             
		                                                                                                            
		Map<String, String> map = new HashMap<String, String>(2);                                                   
		map.put("objectIdx", object_idx);                                                                           
		map.put("examNum", exam_num);  
		
		List<MbPeVO> pe_list = sheetService.select_mb_pe_list(map);
		MbPeVO mbPeVO = new MbPeVO();
		for(MbPeVO mbPeVO1 : pe_list ) {
			
			System.out.println(mbPeVO1.getSelectionNum()+":::차수");
			mbPeVO.setTestDay(mbPeVO1.getTestDay());
			
		}
		String testDay = mbPeVO.getTestDay();
		
		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_pe");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("pe_list", pe_list);

		return mav;
	}
	//ajax호출 view_kmmse2
		@RequestMapping(value="/view_savepe.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savepe(@RequestParam(value="rcolNmArr[]") List<String> rcolNmList,@RequestParam(value="radioArr[]") List<String> radioList,@RequestParam(value="textArr[]") List<String> textList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,
				@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
				@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="pe_bpsysavg")String pe_bpsysavg,@RequestParam(value="pe_bpdiaavg")String pe_bpdiaavg
				,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request, HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
			
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
			String exam_numS = request.getParameter("exam_num");
			String exam_idxS = request.getParameter("exam_idx");
			String exam_idS = request.getParameter("exam_id");
			System.out.println(exam_numS+"::exam_numS");
			System.out.println(exam_idxS+"::exam_idxS");
			System.out.println(exam_idS+"::exam_idS");
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(textList.size()+"::radioList사이즈크기");
			System.out.println("subject_idx::::"+subject_idx);
			
			System.out.println(object_idx+":object_idx");
			
			System.out.println("잘넘어왔습니당");
			
			
			
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
			try {	
				int num = 0;
			
				if(whichChk.equals("new")) {
					if(exam_num.equals("")) {
						Map<String, String> map	 = new HashMap<String, String>();
						String examNum1 = "1";
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examNum",examNum1);
						
						for(int i =0; i <radioList.size(); i++) {
							
							map.put(rcolNmList.get(i), radioList.get(i));
							
						}      
						for(int i =0; i <radioList.size(); i++) {
							
							map.put(rcolNmList.get(i), radioList.get(i));
							
						}
				
						map.put("peHeight",textList.get(0));
						map.put("peDwheight",textList.get(1));
						map.put("peWeight",textList.get(2));
						map.put("peBmi",textList.get(3));
						map.put("peWstcirin",textList.get(4));
						map.put("peButtomcirin",textList.get(5));     
						map.put("peBpsys",textList.get(6));
						map.put("peBpsys1",textList.get(7));                 
						map.put("peBpdia",textList.get(8));
						map.put("peBpdia1",textList.get(9));
						map.put("pePulse",textList.get(10));
						map.put("peBpsysavg",pe_bpsysavg);
						map.put("peBpdiaavg",pe_bpdiaavg);
						    
						map.put("testDay",sdate);
						map.put("insertUserid",subject_idx);
						map.put("updateUserid",subject_idx);						
						
						
						num = sheetService.update_mb_pe_newlist(map);
						
				}else if(!(exam_num.equals(""))) {
						Map<String, String> chkmap	 = new HashMap<String, String>();
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",exam_num);
						chkmap.put("examIdx",exam_idx);
						List<MbPeVO> pelist =  sheetService.select_mb_pe_list(chkmap);
						
						
						if(pelist.size() ==0 ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						  map1.put("objectIdx", idx);
						  map1.put("subjectIdx",subject_idx);			
							for(int i =0; i <radioList.size(); i++) {
								
								map1.put(rcolNmList.get(i), radioList.get(i));
								
							}
						    map1.put("peHeight",textList.get(0));
							map1.put("peDwheight",textList.get(1));
							map1.put("peWeight",textList.get(2));
							map1.put("peBmi",textList.get(3));
							map1.put("peWstcirin",textList.get(4));
							map1.put("peButtomcirin",textList.get(5));     
							map1.put("peBpsys",textList.get(6));
							map1.put("peBpsys1",textList.get(7));                 
							map1.put("peBpdia",textList.get(8));
							map1.put("peBpdia1",textList.get(9));
							map1.put("pePulse",textList.get(10));
						  map1.put("peBpsysavg",pe_bpsysavg);
						  map1.put("peBpdiaavg",pe_bpdiaavg);    


							
							
						 map1.put("testDay",sdate);
						 map1.put("insertUserid",subject_idx);
						 map1.put("updateUserid",subject_idx);
						
							num = sheetService.update_mb_pe_newlist(map1);
						}else {
							Map<String, String> map2	 = new HashMap<String, String>();
							int exam_num1 =  Integer.parseInt(exam_num);
							int exam_Addnum = exam_num1+1;
							String exam_Newnum = String.valueOf(exam_Addnum);
							map2.put("examNum",exam_Newnum);
							System.out.println("examnNum(int)::"+exam_num1);
							System.out.println("exam_Addnum(int)::"+exam_Addnum);
							System.out.println("exam_Newnum(String)::"+exam_Newnum);
						

							map2.put("objectIdx", idx);
							map2.put("subjectIdx",subject_idx);			
							map2.put("examIdx",exam_idx);
							for(int i =0; i <radioList.size(); i++) {
								
								map2.put(rcolNmList.get(i), radioList.get(i));
								
							}
						    map2.put("peHeight",textList.get(0));
							map2.put("peDwheight",textList.get(1));
							map2.put("peWeight",textList.get(2));
							map2.put("peBmi",textList.get(3));
							map2.put("peWstcirin",textList.get(4));
							map2.put("peButtomcirin",textList.get(5));     
							map2.put("peBpsys",textList.get(6));
							map2.put("peBpsys1",textList.get(7));                 
							map2.put("peBpdia",textList.get(8));
							map2.put("peBpdia1",textList.get(9));
							map2.put("pePulse",textList.get(10));
							map2.put("peBpsysavg",pe_bpsysavg);
							map2.put("peBpdiaavg",pe_bpdiaavg);

							
							map2.put("testDay",sdate);
					
							map2.put("insertUserid",subject_idx);
							map2.put("updateUserid",subject_idx);
							
							num = sheetService.update_mb_pe_newlist(map2);
					
							}
						
					  }
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
					
				}else if(whichChk.equals("edit")) {
					if(exam_num.equals("")) {
						Map<String, String> map	 = new HashMap<String, String>();
						String examNum1 = "1";
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examIdx",exam_idx);
						map.put("examNum",examNum1);
						for(int i =0; i <radioList.size(); i++) {
							
							map.put(rcolNmList.get(i), radioList.get(i));
							
						}
							map.put("peHeight",textList.get(0));
							map.put("peDwheight",textList.get(1));
							map.put("peWeight",textList.get(2));
							map.put("peBmi",textList.get(3));
							map.put("peWstcirin",textList.get(4));
							map.put("peButtomcirin",textList.get(5));     
							map.put("peBpsys",textList.get(6));
							map.put("peBpsys1",textList.get(7));                 
							map.put("peBpdia",textList.get(8));
							map.put("peBpdia1",textList.get(9));
							map.put("pePulse",textList.get(10));
						map.put("peBpsysavg",pe_bpsysavg);
						map.put("peBpdiaavg",pe_bpdiaavg);
						
						map.put("testDay",sdate);
					
						map.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_pe_editlist(map);
						
					}else if(!(exam_num.equals(""))) {	
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",exam_idx);
						map1.put("examNum",exam_num);
						for(int i =0; i <radioList.size(); i++) {
							
							map1.put(rcolNmList.get(i), radioList.get(i));
							
						}
							map1.put("peHeight",textList.get(0));
							map1.put("peDwheight",textList.get(1));
							map1.put("peWeight",textList.get(2));
							map1.put("peBmi",textList.get(3));
							map1.put("peWstcirin",textList.get(4));
							map1.put("peButtomcirin",textList.get(5));     
							map1.put("peBpsys",textList.get(6));
							map1.put("peBpsys1",textList.get(7));                 
							map1.put("peBpdia",textList.get(8));
							map1.put("peBpdia1",textList.get(9));
							map1.put("pePulse",textList.get(10));
						map1.put("peBpsysavg",pe_bpsysavg);
						map1.put("peBpdiaavg",pe_bpdiaavg);
					
						map1.put("testDay",sdate);
					
						map1.put("updateUserid",subject_idx);
						num = sheetService.update_mb_pe_editlist(map1);
					}
					
					
					
				}

				
				System.out.println(num +":사이즈");
				
				}catch(NumberFormatException e) {
					exam_num ="1";
					System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
				
			    //리턴값
		        Map<String, Object> result = new HashMap<String, Object>();
				ModelAndView mav = new ModelAndView();
		        //성공했다고 처리
		       result.put("code", "OK");
		       result.put("message", "등록에 성공 하였습니다.");
		       mav.setViewName("/include/sheet/view_pe");
				//mav.addObject("retVal", retVal);
				mav.addObject("code", "OK");
				if(whichChk.equals("new")) {
					
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}
				
				
				
				return mav;
		}
		
	
	
	//ajax호출 view_kiadl
	@RequestMapping(value="/view_savekiadl.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savekiadl(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,@RequestParam(value="tts")String tts,@RequestParam(value="kts")String kts,
			@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
			@RequestParam(value="exam_idx")String exam_idx
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		String exam_idS = request.getParameter("exam_id");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println(exam_idS+"::exam_idS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
		System.out.println("subject_idx::::"+subject_idx);
		System.out.println("kiadl_score::"+tts);
		System.out.println(object_idx+":object_idx");
		
		System.out.println("잘넘어왔습니당");
		
		
		
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					map.put("kiadl01" ,radioList.get(0)); 
					map.put("kiadl02" ,radioList.get(1)); 
					map.put("kiadl03" ,radioList.get(2)); 
					map.put("kiadl04" ,radioList.get(3)); 
					map.put("kiadl05" ,radioList.get(4)); 
					map.put("kiadl06" ,radioList.get(5)); 
					map.put("kiadl07" ,radioList.get(6)); 
					map.put("kiadl08" ,radioList.get(7)); 
					map.put("kiadl09" ,radioList.get(8)); 
					map.put("kiadl10" ,radioList.get(9)); 
					map.put("kiadl11" ,radioList.get(10));
					map.put("kiadlTotal" ,tts); 
					map.put("kiadlKscore" ,kts);	

					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kiadl_newlist(map);
					
			}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbKiadlVO> chkkiadllist =  sheetService.select_mb_kiadl_list(chkmap);
					
					
					if(chkkiadllist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					  map1.put("objectIdx", idx);
					  map1.put("subjectIdx",subject_idx);			
			
				        map1.put("kiadl01" ,radioList.get(0));             
				      	map1.put("kiadl02" ,radioList.get(1));             
				      	map1.put("kiadl03" ,radioList.get(2));             
				      	map1.put("kiadl04" ,radioList.get(3));             
				      	map1.put("kiadl05" ,radioList.get(4));             
				      	map1.put("kiadl06" ,radioList.get(5));             
				        map1.put("kiadl07" ,radioList.get(6));             
				        map1.put("kiadl08" ,radioList.get(7));             
				        map1.put("kiadl09" ,radioList.get(8));             
				        map1.put("kiadl10" ,radioList.get(9));             
				        map1.put("kiadl11" ,radioList.get(10));            
				    	map1.put("kiadlTotal" ,tts); 
						map1.put("kiadlKscore" ,kts);	
						
						
					 map1.put("testDay",sdate);
					 map1.put("insertUserid",subject_idx);
					 map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_kiadl_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						
			            map2.put("kiadl01" ,radioList.get(0)); 
			           	map2.put("kiadl02" ,radioList.get(1)); 
			           	map2.put("kiadl03" ,radioList.get(2)); 
			           	map2.put("kiadl04" ,radioList.get(3)); 
			           	map2.put("kiadl05" ,radioList.get(4)); 
			           	map2.put("kiadl06" ,radioList.get(5)); 
		              map2.put("kiadl07" ,radioList.get(6)); 
		              map2.put("kiadl08" ,radioList.get(7)); 
		              map2.put("kiadl09" ,radioList.get(8)); 
		              map2.put("kiadl10" ,radioList.get(9)); 
		              map2.put("kiadl11" ,radioList.get(10));
		          	map2.put("kiadlTotal" ,tts); 
					map2.put("kiadlKscore" ,kts);	
						
						map2.put("testDay",sdate);
						
						map2.put("insertUserid",subject_idx);
						map2.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_kiadl_newlist(map2);
				
						}
					
				  }
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
				
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
	  			
					map.put("kiadl01" ,radioList.get(0)); 
					map.put("kiadl02" ,radioList.get(1)); 
					map.put("kiadl03" ,radioList.get(2)); 
					map.put("kiadl04" ,radioList.get(3)); 
					map.put("kiadl05" ,radioList.get(4)); 
					map.put("kiadl06" ,radioList.get(5)); 
					map.put("kiadl07" ,radioList.get(6)); 
					map.put("kiadl08" ,radioList.get(7)); 
					map.put("kiadl09" ,radioList.get(8)); 
					map.put("kiadl10" ,radioList.get(9)); 
					map.put("kiadl11" ,radioList.get(10));	
					map.put("kiadlTotal" ,tts); 
					map.put("kiadlKscore" ,kts);	
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_kiadl_editlist(map);
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
				
			        map1.put("kiadl01" ,radioList.get(0));             
			      	map1.put("kiadl02" ,radioList.get(1));             
			      	map1.put("kiadl03" ,radioList.get(2));             
			      	map1.put("kiadl04" ,radioList.get(3));             
			      	map1.put("kiadl05" ,radioList.get(4));             
			      	map1.put("kiadl06" ,radioList.get(5));             
			        map1.put("kiadl07" ,radioList.get(6));             
			        map1.put("kiadl08" ,radioList.get(7));             
			        map1.put("kiadl09" ,radioList.get(8));             
			        map1.put("kiadl10" ,radioList.get(9));             
			        map1.put("kiadl11" ,radioList.get(10));            
			    	map1.put("kiadlTotal" ,tts); 
					map1.put("kiadlKscore" ,kts);	
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kiadl_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_kiadl");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("new","new");
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}
			
			
			
			return mav;
	}	
	
		
	
	//ajax호출 view_family
	@RequestMapping(value="/view_savefamily.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savefamily(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,
			@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
			@RequestParam(value="exam_idx")String exam_idx
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("MbFmhVO") MbFmhVO vo,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		String exam_idS = request.getParameter("exam_id");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println(exam_idS+"::exam_idS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
		System.out.println("subject_idx::::"+subject_idx);
		
		System.out.println(object_idx+":object_idx");
		
		System.out.println("잘넘어왔습니당");
		
		
		
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
				
					
					
					
		            map.put("f01A" ,radioList.get(0));
		           	map.put("f01B" ,radioList.get(1));
		           	map.put("f02A" ,radioList.get(2));
		           	map.put("f02B" ,radioList.get(3));
		           	map.put("f03A" ,radioList.get(4));
		           	map.put("f03B" ,radioList.get(5));

					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_fmh_newlist(map);
					
			}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbFmhVO> chkIclist =  sheetService.select_mb_fmh_list(chkmap);
					
					
					if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					  map1.put("objectIdx", idx);
					  map1.put("subjectIdx",subject_idx);			
			
			          map1.put("f01A" ,radioList.get(0));            
		           	  map1.put("f01B" ,radioList.get(1));
		           	  map1.put("f02A" ,radioList.get(2));
		           	  map1.put("f02B" ,radioList.get(3));
		           	  map1.put("f03A" ,radioList.get(4));
		           	  map1.put("f03B" ,radioList.get(5));
						
						
					 map1.put("testDay",sdate);
					 map1.put("insertUserid",subject_idx);
					 map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_fmh_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						
				     	map2.put("f01A" ,radioList.get(0));
				       	map2.put("f01B" ,radioList.get(1));
				       	map2.put("f02A" ,radioList.get(2));
				       	map2.put("f02B" ,radioList.get(3));
				       	map2.put("f03A" ,radioList.get(4));
				       	map2.put("f03B" ,radioList.get(5));
						
						map2.put("testDay",sdate);
						
						map2.put("insertUserid",subject_idx);
						map2.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_fmh_newlist(map2);
				
						}
					
				  }
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
				
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
	  			
					map.put("f01A" ,radioList.get(0));
					map.put("f01B" ,radioList.get(1));
					map.put("f02A" ,radioList.get(2));
					map.put("f02B" ,radioList.get(3));
				    map.put("f03A" ,radioList.get(4));
				    map.put("f03B" ,radioList.get(5));				
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_fmh_editlist(map);
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
				
			      map1.put("f01A" ,radioList.get(0));            
			      map1.put("f01B" ,radioList.get(1));
			      map1.put("f02A" ,radioList.get(2));
			      map1.put("f02B" ,radioList.get(3));
			      map1.put("f03A" ,radioList.get(4));
			      map1.put("f03B" ,radioList.get(5));
		      
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_fmh_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_family");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("new","new");
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}
			
			
			
			return mav;
	}	
	
	
	
	
	
	
	@RequestMapping("/view_ic.do")
	public ModelAndView view_ic(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		
		System.out.println(object_idx);
		System.out.println("action값::"+action);
		
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);

		
		
		
		List<MbIcVO> ic_list = sheetService.select_mb_ic_list(map);
		
		MbIcVO mbIcVO = new MbIcVO();
		for(MbIcVO mbIcVO1 : ic_list ) {
			
			System.out.println(mbIcVO1.getSelectionNum()+":::차수");
			mbIcVO.setTestDay(mbIcVO1.getTestDay());
			mbIcVO.setIcFdropDate(mbIcVO1.getIcFdropDate());
		}
		String testDay = mbIcVO.getTestDay();
		String icFdropDate = mbIcVO.getIcFdropDate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_Ic");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("icFdropDate",icFdropDate);
		mav.addObject("success", true);
		mav.addObject("ic_list", ic_list);

		return mav;
	}
	
	
	
	
	
	
	//ajax호출 view_family
	@RequestMapping(value="/view_saveIc.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_saveic(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,@RequestParam(value="ddate")String ddate,
		@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
		,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx                    
		String userId = sessionMember.getId();                                            
		String Name = sessionMember.getName();                                            
	   //String selectionNum = request.getParameter("selection_num");                   
		System.out.println("selectionNum::"+exam_num);                                    
		System.out.println("subject_idx::::"+subject_idx);                                
		                                                                                  
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		String exam_idS = request.getParameter("exam_id");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println(exam_idS+"::exam_idS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println("examId::"+exam_id);
		System.out.println("cdbs::"+cdbs);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");              
	                                                                 
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;

		try {
		MbIcVO mbIcVO;	
		int num = 0;
		//for(int i = 0; i <radioList.size(); i++) {
			
			

			//mbIcVO
	//	
			System.out.println(radioList.get(0)+":radioget(0)");
			System.out.println(radioList.get(1)+":radioget(1)");
			System.out.println(radioList.get(2)+":radioget(\2)");	
			
	//	}
		
		
			
		if(whichChk.equals("new")) {
			mbIcVO = new MbIcVO();
			
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);;
				map.put("icFCopy",radioList.get(0));
				map.put("icFsmpCopy",radioList.get(1));
				map.put("icFsmpdntCopy",radioList.get(2));
				map.put("testDay",sdate);
				map.put("icFdropDate",ddate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_ic_newlist(map);
			}else if(!(exam_num.equals(""))) {
				/*
				mbIcVO.setObjectIdx(idx);
				mbIcVO.setSubjectIdx(subject_idx);
				mbIcVO.setIcFCopy(radioList.get(0));
				mbIcVO.setIcFsmCopy(radioList.get(1));
				mbIcVO.setIcFsmpdntCopy(radioList.get(2));
				mbIcVO.setUpdateUserid(idx);
				mbIcVO.setTestDay(sdate);
				mbIcVO.setIcFdropDate(ddate);
				*/
				mbIcVO = new MbIcVO();
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				List<MbIcVO> chkIclist =  sheetService.select_mb_ic_list(chkmap);
				
				Map<String, String> map1	 = new HashMap<String, String>();
				if(chkIclist.size() ==0 ) {
					map1.put("examNum",exam_num);
					
				}else {
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map1.put("examNum",exam_Newnum);
					System.out.println("exam_num1(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				}

				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);			
				map1.put("examIdx",exam_idx);
				map1.put("icFCopy",radioList.get(0));
				map1.put("icFsmpCopy",radioList.get(1));
				map1.put("icFsmpdntCopy",radioList.get(2));
				map1.put("testDay",sdate);
				map1.put("icFdropDate",ddate);
				map1.put("insertUserid",subject_idx);
				map1.put("updateUserid",subject_idx);
			
				num = sheetService.update_mb_ic_newlist(map1);
			}
			
			   if(num> 0 ) {
			   Map<String, String> tmap	 = new HashMap<String, String>();
			   tmap.put("objectIdx", idx);
			   tmap.put("examId", exam_id);
			   tmap.put("cdbs", cdbs);
			  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
		
			  for(MbSJVO mbSJVO1 : maxNum){
				  	
				 mbSJVO.setExamNum(mbSJVO1.getExamNum());
				 maxNum1 = mbSJVO.getExamNum();
				 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
				 // System.out.println(maxNum1+"::maxnumselction값");
				 
			  }
			  
			  
		   }
			
		
		}else if(whichChk.equals("edit")) {
			mbIcVO = new MbIcVO();
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String selectionNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",exam_num);
				map.put("icFCopy",radioList.get(0));
				map.put("icFsmpCopy",radioList.get(1));
				map.put("icFsmpdntCopy",radioList.get(2));
				map.put("testDay",sdate);
				map.put("icFdropDate",ddate);
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_ic_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
				map1.put("icFCopy",radioList.get(0));
				map1.put("icFsmpCopy",radioList.get(1));
				map1.put("icFsmpdntCopy",radioList.get(2));
				map1.put("testDay",sdate);
				map1.put("icFdropDate",ddate);
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_ic_editlist(map1);
			}
			
			
			
		}
		
		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_Ic");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}		   
		
		
		return mav;
	}
	
	
	


	
	
	@RequestMapping(value="/view_from_object_to_dm.do" , method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_from_object_to_dm(@RequestParam(value="obj") String obj,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		System.out.println(action+"::action값");
		System.out.println(object_idx);
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		
		
		System.out.println("obj::"+obj);
		Map<String, String> map = new HashMap<String, String>();
		map.put("objectIdx", obj);
		
		List<MbObjectVO> object_list = memberService.select_from_object_to_dm(map);
		ModelAndView mav = new ModelAndView();
		MbObjectVO objVO = new MbObjectVO();
		for(MbObjectVO objVO1 : object_list) {
			
			mav.addObject("dm04", objVO1.getName());
			mav.addObject("dm13", objVO1.getMobile());  
			mav.addObject("dm14", objVO1.getTelNo()); 
			mav.addObject("dm15", objVO1.getAddress1());  
			mav.addObject("dm16", objVO1.getAddress2()); 
			mav.addObject("dm17", objVO1.getAddress3());  
			mav.addObject("dm18", objVO1.getAddress4()); 
			mav.addObject("dm06", objVO1.getGender());  
			mav.addObject("dm09", objVO1.getYearAni());
			mav.addObject("dm07R", objVO1.getDm07R());  
			mav.addObject("dm07A", objVO1.getDm07A());  
			mav.addObject("dm07B", objVO1.getDm07B());
			mav.addObject("dm07C", objVO1.getDm07C());
			mav.addObject("dm08", objVO1.getDm08());
			
		}
		mav.addObject("list", object_list);
		
		/*
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		List<MbDmVO> dm_list = sheetService.select_mb_dm_list(map);

		MbDmVO mbDmVO = new MbDmVO();
		for(MbDmVO mbDmVO1 : dm_list ) {
			
			System.out.println(mbDmVO1.getSelectionNum()+":::차수");
			mbDmVO.setTestDay(mbDmVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbDmVO.getTestDay();
		
			*/
		
		
		
		
		
		mav.addObject("code", "OK");
		mav.setViewName("/include/sheet/view_demography");
		mav.addObject("idx", object_idx);


		return mav;
	}
	
	@RequestMapping("/view_demography.do")
	public ModelAndView view_demography(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		System.out.println(action+"::action값");
		System.out.println(object_idx);
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		List<MbDmVO> dm_list = sheetService.select_mb_dm_list(map);

		MbDmVO mbDmVO = new MbDmVO();
		for(MbDmVO mbDmVO1 : dm_list ) {
			
			System.out.println(mbDmVO1.getSelectionNum()+":::차수");
			mbDmVO.setTestDay(mbDmVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbDmVO.getTestDay();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_demography");
		mav.addObject("idx", object_idx);
		mav.addObject("testDay",testDay);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("success", true);
		mav.addObject("dm_list", dm_list);


		return mav;
	}
	
	
	//ajax호출 view_savedemography(savedemography)
		@RequestMapping(value="/view_savedemography.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savedemography(@RequestParam(value="rcolNmArr[]") List<String> rcolNmList,@RequestParam(value="tcolNmArr[]") List<String> tcolNmList,@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="textArr[]") List<String> textList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,@RequestParam(value="dm07C") String dm07C,@RequestParam(value="dm13") String dm13,@RequestParam(value="dm14") String dm14
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num,
			@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,
			HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
		//	String exam_num = request.getParameter("exam_num");
		//	System.out.println(exam_num+"::exam_num");
		
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
			System.out.println(rcolNmList.get(3)+"::colNmList.get(0)");
			System.out.println(tcolNmList.get(3)+"::colNmList.get(0)");
			System.out.println(dm07C+"dm07Cdate");
			System.out.println(dm13+"dm13MOBILE");
			System.out.println(dm14+"dm14TELNO");
			
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					
					map.put("examNum",examNum1);
				
					map.put("dm07C", dm07C);
					map.put("dm13", dm13);
					map.put("dm14", dm14);
					
					for(int i =0; i <radioList.size(); i++) {
						
						map.put(rcolNmList.get(i), radioList.get(i));
						
					}
					
					for(int i =0; i <textList.size(); i++) {
						
						map.put(tcolNmList.get(i), textList.get(i));
						
					}
					
					
					/*
					map.put("dg01" ,radioList.get(0)); 
					map.put("dg02" ,radioList.get(1)); 
					map.put("dg031" ,radioList.get(2)); 
					map.put("dg032" ,radioList.get(3)); 
					map.put("dg033" ,radioList.get(4)); 
					map.put("dg034" ,radioList.get(5)); 
					map.put("dg035" ,radioList.get(6)); 
					map.put("dg036" ,radioList.get(7)); 
					map.put("dg037" ,radioList.get(8)); 
					map.put("dg038" ,radioList.get(9)); 
					map.put("dg039" ,radioList.get(10));
					map.put("dg0310" ,radioList.get(11));
					map.put("dg0311" ,radioList.get(12));
					map.put("dg0312" ,radioList.get(13));
					map.put("dg0313" ,radioList.get(14));
					map.put("dg0314" ,radioList.get(15));
					map.put("dg0315" ,radioList.get(16));
					*/
					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_dm_newlist(map);
				}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbDmVO> chkIclist =  sheetService.select_mb_dm_list(chkmap);
					
					
					if(chkIclist.size() ==0 ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);			
						map1.put("dm07C", dm07C);
						map1.put("dm13", dm13);
						map1.put("dm14", dm14);
						for(int i =0; i <radioList.size(); i++) {
							
							map1.put(rcolNmList.get(i), radioList.get(i));
							
						}
						
						for(int i =0; i <textList.size(); i++) {
							
							map1.put(tcolNmList.get(i), textList.get(i));
							
						}
						
						map1.put("testDay",sdate);
					
						map1.put("insertUserid",subject_idx);
						map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_dm_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						map2.put("dm07C", dm07C);
						map2.put("dm13", dm13);
						map2.put("dm14", dm14);
						
						for(int i =0; i <radioList.size(); i++) {
							
							map2.put(rcolNmList.get(i), radioList.get(i));
							
						}
						
						for(int i =0; i <textList.size(); i++) {
							
							map2.put(tcolNmList.get(i), textList.get(i));
							
						}
						
						
			            map2.put("testDay",sdate);
				
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_dm_newlist(map2);
				
						}
				  }
				
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					   List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
				   
				
			
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
					map.put("dm07C", dm07C);
					map.put("dm13", dm13);
					map.put("dm14", dm14);
					for(int i =0; i <radioList.size(); i++) {
						
						map.put(rcolNmList.get(i), radioList.get(i));
						
					}
					
					for(int i =0; i <textList.size(); i++) {
						
						map.put(tcolNmList.get(i), textList.get(i));
						
					}
					
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_dm_editlist(map);
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
					map1.put("dm07C", dm07C);
					map1.put("dm13", dm13);
					map1.put("dm14", dm14);
					
					for(int i =0; i <radioList.size(); i++) {
						
						map1.put(rcolNmList.get(i), radioList.get(i));
						
					}
					
					for(int i =0; i <textList.size(); i++) {
						
						map1.put(tcolNmList.get(i), textList.get(i));
						
					}
					
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_dm_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_demography");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			return mav;
		}
	
	
	@RequestMapping("/view_sm.do")
	public ModelAndView view_sm(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println();
		System.out.println(object_idx);
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");

		
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbSmVO> sm_list = sheetService.select_mb_sm_list(map);
		
		MbSmVO mbSmVO = new MbSmVO();
		for(MbSmVO mbSmVO1 : sm_list ) {
			
			System.out.println(mbSmVO1.getSelectionNum()+":::차수");
			mbSmVO.setTestDay(mbSmVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbSmVO.getTestDay();
	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_sm");
		mav.addObject("idx", object_idx);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("sm_list", sm_list);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		return mav;
	}
	
	
	//ajax호출 view_sm(흡연)
		@RequestMapping(value="/view_savesm.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savesm(@RequestParam(value="rcolNmArr[]") List<String> rcolNmList,@RequestParam(value="tcolNmArr[]") List<String> tcolNmList,@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="textArr[]") List<String> textList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num,@RequestParam(value="exam_idx")String exam_idx,HttpServletRequest request,
			@RequestParam(value="cdbs")String cdbs,
			HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
		//	String exam_num = request.getParameter("exam_num");
		//	System.out.println(exam_num+"::exam_num");
		
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println(idx+"::idx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");

				
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
			
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					for(int i =0; i <radioList.size(); i++) {
						
						map.put(rcolNmList.get(i), radioList.get(i));
					
						}
						
						for(int i =0; i <textList.size(); i++) {
					
					map.put(tcolNmList.get(i), textList.get(i));
					
						}


					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_sm_newlist(map);
				}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbSmVO> chkIclist =  sheetService.select_mb_sm_list(chkmap);
					
					
					if(chkIclist.size() ==0 ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);			
					
						for(int i =0; i <radioList.size(); i++) {
							
							map1.put(rcolNmList.get(i), radioList.get(i));
						
							}
							
							for(int i =0; i <textList.size(); i++) {
						
						map1.put(tcolNmList.get(i), textList.get(i));
						
							}

						
						map1.put("testDay",sdate);
					
						map1.put("insertUserid",subject_idx);
						map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_sm_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);	
					
					for(int i =0; i <radioList.size(); i++) {
						
						map2.put(rcolNmList.get(i), radioList.get(i));
					
						}
						
						for(int i =0; i <textList.size(); i++) {
					
					map2.put(tcolNmList.get(i), textList.get(i));
					
						}

					
					map2.put("testDay",sdate);
				
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_sm_newlist(map2);
				
						}
				  }
			
				   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
			   	
				
				
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx", exam_idx);
					map.put("examNum",examNum1);
					
					for(int i =0; i <radioList.size(); i++) {
						
						map.put(rcolNmList.get(i), radioList.get(i));
					
						}
						
						for(int i =0; i <textList.size(); i++) {
					
					map.put(tcolNmList.get(i), textList.get(i));
					
						}

					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_sm_editlist(map);
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx", exam_idx);
					map1.put("examNum",exam_num);

					
					for(int i =0; i <radioList.size(); i++) {
						
						map1.put(rcolNmList.get(i), radioList.get(i));
					
						}
						
						for(int i =0; i <textList.size(); i++) {
					
					map1.put(tcolNmList.get(i), textList.get(i));
					
						}

					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_sm_editlist(map1);
				}
				
				
				
			}
	
			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_sm");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			
			return mav;
		}
	
		
		
		@RequestMapping("/view_dr.do")
		public ModelAndView view_dr(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) throws Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
			System.out.println(subject_idx+":loginuseridx");
			System.out.println(userId+":usrid");
			System.out.println(Name+"::name");
			
			
			String object_idx = request.getParameter("object_idx");
			String exam_num = request.getParameter("exam_num");
			String exam_idx = request.getParameter("exam_idx");
			String exam_id = request.getParameter("exam_id");
			String action = request.getParameter("action");
			
			System.out.println(object_idx);
			System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
			System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
			Map<String, String> map = new HashMap<String, String>(3);
			map.put("objectIdx", object_idx);
			map.put("examNum", exam_num);
			map.put("examIdx", exam_idx);
			List<MbDrVO> dr_list = sheetService.select_mb_dr_list(map);
			
			MbDrVO mbDrVO = new MbDrVO();
			for(MbDrVO mbDrVO1 : dr_list ) {
				
				System.out.println(mbDrVO1.getSelectionNum()+":::차수");
				mbDrVO.setTestDay(mbDrVO1.getTestDay());
				//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
			}
			String testDay = mbDrVO.getTestDay();
		
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/include/sheet/view_dr");
			mav.addObject("idx", object_idx);
			mav.addObject("testDay",testDay);
			mav.addObject("action", action);
			mav.addObject("exam_id", exam_id);
			mav.addObject("success", true);
			mav.addObject("dr_list", dr_list);

			return mav;
		}
		
		//ajax호출 view_savedr
		@RequestMapping(value="/view_savedr.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savedr(@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="textArr[]") List<String> textList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,
				@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,
			HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
		//	String exam_num = request.getParameter("exam_num");
		//	System.out.println(exam_num+"::exam_num");
		
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
			System.out.println(radioList.get(2)+"::radioList.get(2)");
			System.out.println(textList.get(2)+"::colNmList.get(2)");

			
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					map.put("dr01", radioList.get(0));
					map.put("dr02a", radioList.get(1));						
					map.put("dr02d", radioList.get(2));			
					map.put("dr02f", radioList.get(3));
					map.put("dr02g", radioList.get(4));					
					
					
					map.put("dr01a", textList.get(0));	
					map.put("dr02b", textList.get(1));
					map.put("dr02c", textList.get(2));	
					map.put("dr02e", textList.get(3));
					map.put("dr02h", textList.get(4));
					map.put("dr02i", textList.get(5));
					map.put("dr02j", textList.get(6));
					

					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_dr_newlist(map);
				}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbDrVO> chkIclist =  sheetService.select_mb_dr_list(chkmap);
					
					
					if(chkIclist.size() ==0 ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);			
			
						map1.put("dr01", radioList.get(0));
						map1.put("dr02a", radioList.get(1));						
						map1.put("dr02d", radioList.get(2));			
						map1.put("dr02f", radioList.get(3));
						map1.put("dr02g", radioList.get(4));					
						
						
						map1.put("dr01a", textList.get(0));	
						map1.put("dr02b", textList.get(1));
						map1.put("dr02c", textList.get(2));	
						map1.put("dr02e", textList.get(3));
						map1.put("dr02h", textList.get(4));
						map1.put("dr02i", textList.get(5));
						map1.put("dr02j", textList.get(6));
						
						map1.put("testDay",sdate);
					
						map1.put("insertUserid",subject_idx);
						map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_dr_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						
						
						map2.put("dr01", radioList.get(0));
						map2.put("dr02a", radioList.get(1));						
						map2.put("dr02d", radioList.get(2));			
						map2.put("dr02f", radioList.get(3));
						map2.put("dr02g", radioList.get(4));					
						
						
						map2.put("dr01a", textList.get(0));	
						map2.put("dr02b", textList.get(1));
						map2.put("dr02c", textList.get(2));	
						map2.put("dr02e", textList.get(3));
						map2.put("dr02h", textList.get(4));
						map2.put("dr02i", textList.get(5));
						map2.put("dr02j", textList.get(6));
						
			            map2.put("testDay",sdate);
				
			            map2.put("insertUserid",subject_idx);
			            map2.put("updateUserid",subject_idx);
						num = sheetService.update_mb_dr_newlist(map2);
				
						}
				  }
				
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
				   
				
			
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);

					map.put("dr01", radioList.get(0));
					map.put("dr02a", radioList.get(1));						
					map.put("dr02d", radioList.get(2));			
					map.put("dr02f", radioList.get(3));
					map.put("dr02g", radioList.get(4));					
					
					
					map.put("dr01a", textList.get(0));	
					map.put("dr02b", textList.get(1));
					map.put("dr02c", textList.get(2));	
					map.put("dr02e", textList.get(3));
					map.put("dr02h", textList.get(4));
					map.put("dr02i", textList.get(5));
					map.put("dr02j", textList.get(6));
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_dr_editlist(map);
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
	
					map1.put("dr01", radioList.get(0));
					map1.put("dr02a", radioList.get(1));						
					map1.put("dr02d", radioList.get(2));			
					map1.put("dr02f", radioList.get(3));
					map1.put("dr02g", radioList.get(4));					
					
					
					map1.put("dr01a", textList.get(0));	
					map1.put("dr02b", textList.get(1));
					map1.put("dr02c", textList.get(2));	
					map1.put("dr02e", textList.get(3));
					map1.put("dr02h", textList.get(4));
					map1.put("dr02i", textList.get(5));
					map1.put("dr02j", textList.get(6));
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_dr_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리

	       mav.setViewName("/include/sheet/view_dr");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			return mav;
		}		
		
			
		//ajax호출 view_pha(신체활동)
				@RequestMapping(value="/view_savepha.do", method=RequestMethod.POST)
				@ResponseBody
				public ModelAndView view_savepha(@RequestParam(value="radioArr[]") List<String> radioList,
						@RequestParam(value="textArr[]") List<String> textList,@RequestParam(value="tcolNmArr[]") List<String> tcolNmList,
						@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
					,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
					@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,
					HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
					MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
					String subject_idx = sessionMember.getIdx(); // login user idx
					String userId = sessionMember.getId();
					String Name = sessionMember.getName();
				   //String selectionNum = request.getParameter("selection_num");
					System.out.println("selectionNum::"+exam_num);
					System.out.println("subject_idx::::"+subject_idx);
					
					String object_idx = request.getParameter("object_idx");
				//	String exam_num = request.getParameter("exam_num");
				//	System.out.println(exam_num+"::exam_num");
				
					System.out.println("잘넘어왔습니당");
					System.out.println(object_idx+":object_idx");
					System.out.println("examNum::"+exam_num);
					System.out.println("examIdx::"+exam_idx);
					System.out.println(idx+"::objectidx값 받아왔다 ");
					System.out.println("선택버튼:"+whichChk);
					System.out.println(sdate+"::sdate값 받아왔다");
					System.out.println(radioList.size()+"::radioList사이즈크기");

						
					MbSJVO mbSJVO = new MbSJVO();
					String maxNum1 = null;
					
					try {	
					int num = 0;
				
					if(whichChk.equals("new")) {
						if(exam_num.equals("")) {
							Map<String, String> map	 = new HashMap<String, String>();
							String examNum1 = "1";
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examNum",examNum1);
							
						    map.put("pha01"	, radioList.get(0));            
						    map.put("pha01d", radioList.get(1));             
							map.put("pha02"	, radioList.get(2));        		
							map.put("pha03"	, radioList.get(3));       			
							map.put("pha03d", radioList.get(4));       	    
							map.put("pha05"	, radioList.get(5));       		  
							map.put("pha06"	, radioList.get(6));       		  

					      	for(int i =0; i <textList.size(); i++) {
								
								map.put(tcolNmList.get(i), textList.get(i));
								
							}
	
							
							map.put("testDay",sdate);
							map.put("insertUserid",subject_idx);
							map.put("updateUserid",subject_idx);
							num = sheetService.update_mb_pha_newlist(map);
						}else if(!(exam_num.equals(""))) {
							Map<String, String> chkmap	 = new HashMap<String, String>();
							chkmap.put("objectIdx", idx);
							chkmap.put("examNum",exam_num);
							chkmap.put("examIdx",exam_idx);
							List<MbPhaVO> chkIclist =  sheetService.select_mb_pha_list(chkmap);
							
							
							if(chkIclist.size() ==0 ) {
								Map<String, String> map1	 = new HashMap<String, String>();
								map1.put("objectIdx", idx);
								map1.put("subjectIdx",subject_idx);			
				
								map1.put("pha01"	, radioList.get(0));            
								map1.put("pha01d", radioList.get(1));             
								map1.put("pha02"	, radioList.get(2));        		
								map1.put("pha03"	, radioList.get(3));       			
								map1.put("pha03d", radioList.get(4));       	    
								map1.put("pha05"	, radioList.get(5));       		  
								map1.put("pha06"	, radioList.get(6));       		  

							    for(int i =0; i <textList.size(); i++) {
										
									map1.put(tcolNmList.get(i), textList.get(i));
										
									}
							    
								map1.put("testDay",sdate);
							
								map1.put("insertUserid",subject_idx);
								map1.put("updateUserid",subject_idx);
							
								num = sheetService.update_mb_pha_newlist(map1);
							}else {
								Map<String, String> map2	 = new HashMap<String, String>();
								int exam_num1 =  Integer.parseInt(exam_num);
								int exam_Addnum = exam_num1+1;
								String exam_Newnum = String.valueOf(exam_Addnum);
								map2.put("examNum",exam_Newnum);
								System.out.println("examnNum(int)::"+exam_num1);
								System.out.println("exam_Addnum(int)::"+exam_Addnum);
								System.out.println("exam_Newnum(String)::"+exam_Newnum);
							

							map2.put("objectIdx", idx);
							map2.put("subjectIdx",subject_idx);			
							map2.put("examIdx",exam_idx);
							
							map2.put("pha01"	, radioList.get(0));            
							map2.put("pha01d", radioList.get(1));             
							map2.put("pha02"	, radioList.get(2));        		
							map2.put("pha03"	, radioList.get(3));       			
							map2.put("pha03d", radioList.get(4));       	    
							map2.put("pha05"	, radioList.get(5));       		  
							map2.put("pha06"	, radioList.get(6));       		  

						    for(int i =0; i <textList.size(); i++) {
									
								map2.put(tcolNmList.get(i), textList.get(i));
									
								}
							map2.put("testDay",sdate);
						
							map2.put("insertUserid",subject_idx);
							map2.put("updateUserid",subject_idx);
							num = sheetService.update_mb_pha_newlist(map2);
						
								}
						  }
						
						   if(num> 0 ) {
							   Map<String, String> tmap	 = new HashMap<String, String>();
							   tmap.put("objectIdx", idx);
							   tmap.put("examId", exam_id);
							   tmap.put("cdbs", cdbs);
							  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
						
							  for(MbSJVO mbSJVO1 : maxNum){
								  	
								 mbSJVO.setExamNum(mbSJVO1.getExamNum());
								 maxNum1 = mbSJVO.getExamNum();
								 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
								 // System.out.println(maxNum1+"::maxnumselction값");
								 
							  }
							  
							  
						   }
						
						
						
					}else if(whichChk.equals("edit")) {
						if(exam_num.equals("")) {
							Map<String, String> map	 = new HashMap<String, String>();
							String examNum1 = "1";
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examIdx",exam_idx);
							map.put("examNum",examNum1);
							
						    map.put("pha01"	, radioList.get(0));            
						    map.put("pha01d", radioList.get(1));             
							map.put("pha02"	, radioList.get(2));        		
							map.put("pha03"	, radioList.get(3));       			
							map.put("pha03d", radioList.get(4));       	    
							map.put("pha05"	, radioList.get(5));       		  
							map.put("pha06"	, radioList.get(6));       		  

					      	for(int i =0; i <textList.size(); i++) {
								
								map.put(tcolNmList.get(i), textList.get(i));
								
							}
	
					    	map.put("testDay",sdate);
							map.put("updateUserid",subject_idx);
							
							num = sheetService.update_mb_pha_editlist(map);
						}else if(!(exam_num.equals(""))) {	
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("subjectIdx",subject_idx);
							map1.put("examIdx",exam_idx);
							map1.put("examNum",exam_num);
		
							map1.put("pha01"	, radioList.get(0));            
							map1.put("pha01d", radioList.get(1));             
							map1.put("pha02"	, radioList.get(2));        		
							map1.put("pha03"	, radioList.get(3));       			
							map1.put("pha03d", radioList.get(4));       	    
							map1.put("pha05"	, radioList.get(5));       		  
							map1.put("pha06"	, radioList.get(6));       		  
							map1.put("testDay",sdate);
							
						    for(int i =0; i <textList.size(); i++) {
									
								map1.put(tcolNmList.get(i), textList.get(i));
									
								}
						    
						
							map1.put("updateUserid",subject_idx);
							num = sheetService.update_mb_pha_editlist(map1);
						}
						
						
						
					}
			
					
					System.out.println(num +":사이즈");
					
					}catch(NumberFormatException e) {
						exam_num ="1";
						System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
						
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				
				
					
				    //리턴값
			        Map<String, Object> result = new HashMap<String, Object>();
					ModelAndView mav = new ModelAndView();
			        //성공했다고 처리
			       result.put("code", "OK");
			       result.put("message", "등록에 성공 하였습니다.");
			       mav.setViewName("/include/sheet/view_pha");
					//mav.addObject("retVal", retVal);
					mav.addObject("code", "OK");
					if(whichChk.equals("new")) {
						
						mav.addObject("message", "신규에 등록에 성공 하였습니다.");
						mav.addObject("objIdx", idx);
						mav.addObject("exam_num", maxNum1);
						mav.addObject("result","new");
						}else if(whichChk.equals("edit")) {
						mav.addObject("result","edit");
						mav.addObject("message", "수정을 성공 하였습니다.");
						//mav.addObject("dataList", ja_data_list);
						}		   
					
					
					
					return mav;
				}
		
				@RequestMapping("/view_pha.do")
				public ModelAndView view_pha(HttpServletRequest request, HttpServletResponse response, HttpSession session,
						ModelMap model) throws Exception {
					MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
					String subject_idx = sessionMember.getIdx(); // login user idx
					String userId = sessionMember.getId();
					String Name = sessionMember.getName();
					System.out.println(subject_idx+":loginuseridx");
					System.out.println(userId+":usrid");
					System.out.println(Name+"::name");
					
					
					String object_idx = request.getParameter("object_idx");
					String exam_num = request.getParameter("exam_num");
					String exam_idx = request.getParameter("exam_idx");
					String exam_id = request.getParameter("exam_id");
					String action = request.getParameter("action");
					
					System.out.println(object_idx);
					System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
					System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
					Map<String, String> map = new HashMap<String, String>(3);
					map.put("objectIdx", object_idx);
					map.put("examNum", exam_num);
					map.put("examIdx", exam_idx);
					List<MbPhaVO> pha_list = sheetService.select_mb_pha_list(map);
					
					MbPhaVO mbPhaVO = new MbPhaVO();
					for(MbPhaVO mbPhaVO1 : pha_list ) {
						
						System.out.println(mbPhaVO1.getSelectionNum()+":::차수");
						mbPhaVO.setTestDay(mbPhaVO1.getTestDay());
						//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
					}
					String testDay = mbPhaVO.getTestDay();
				
					
					ModelAndView mav = new ModelAndView();
					mav.setViewName("/include/sheet/view_pha");
					mav.addObject("idx", object_idx);
					mav.addObject("action", action);
					mav.addObject("exam_id", exam_id);
					mav.addObject("testDay",testDay);
					mav.addObject("success", true);
					mav.addObject("pha_list", pha_list);

					return mav;
				}		
				//ajax호출 view_sp(수면)
				@RequestMapping(value="/view_savesp.do", method=RequestMethod.POST)
				@ResponseBody
				public ModelAndView view_sp(@RequestParam(value="radioArr[]") List<String> radioList,
						@RequestParam(value="textArr[]") List<String> textList,@RequestParam(value="tcolNmArr[]") List<String> tcolNmList,
						@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
					,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
					@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="cdbs", required=false)String cdbs,HttpServletRequest request,
					HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
					MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
					String subject_idx = sessionMember.getIdx(); // login user idx
					String userId = sessionMember.getId();
					String Name = sessionMember.getName();
				   //String selectionNum = request.getParameter("selection_num");
					System.out.println("selectionNum::"+exam_num);
					System.out.println("subject_idx::::"+subject_idx);
					
					String object_idx = request.getParameter("object_idx");
				//	String exam_num = request.getParameter("exam_num");
				//	System.out.println(exam_num+"::exam_num");
				
					System.out.println("잘넘어왔습니당");
					System.out.println(object_idx+":object_idx");
					System.out.println("examNum::"+exam_num);
					System.out.println("examIdx::"+exam_idx);
					System.out.println(idx+"::objectidx값 받아왔다 ");
					System.out.println("선택버튼:"+whichChk);
					System.out.println(sdate+"::sdate값 받아왔다");
					System.out.println(radioList.size()+"::radioList사이즈크기");

						
					MbSJVO mbSJVO = new MbSJVO();
					String maxNum1 = null;
					
					try {	
					int num = 0;
				
					if(whichChk.equals("new")) {
						if(exam_num.equals("")) {
							Map<String, String> map	 = new HashMap<String, String>();
							String examNum1 = "1";
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examNum",examNum1);
							
							map.put("sp01", radioList.get(0));
							map.put("sp03", radioList.get(1));
							map.put("sp05a", radioList.get(2));
							map.put("sp05b", radioList.get(3));
							map.put("sp05c", radioList.get(4));
							map.put("sp05d", radioList.get(5));
							map.put("sp05e", radioList.get(6));
							map.put("sp05f", radioList.get(7));
							map.put("sp05g", radioList.get(8));
							map.put("sp05h", radioList.get(9));
							map.put("sp05i", radioList.get(10));
							map.put("sp05jb", radioList.get(11));
							map.put("sp06", radioList.get(12));
							map.put("sp07", radioList.get(13));
							map.put("sp08", radioList.get(14));
							map.put("sp09", radioList.get(15));
							map.put("sp10", radioList.get(16));
							map.put("sp10a", radioList.get(17));
							map.put("sp10b", radioList.get(18));
							map.put("sp10c", radioList.get(19));
							map.put("sp10d", radioList.get(20));
							map.put("sp10e", radioList.get(21));
							
							/*
							map.put("sp01a",textList.get(0));
							map.put("sp01b",textList.get(1));
							map.put("sp02a",textList.get(2));
							map.put("sp02b",textList.get(3));
							map.put("sp03a",textList.get(4));
							map.put("sp03b",textList.get(5));
							map.put("sp04a",textList.get(6));
							map.put("sp04b",textList.get(7));
							map.put("sp05ja", textList.get(8));
							map.put("sp05e2", textList.get(9));
							*/
							
							for(int i =0; i <textList.size(); i++) {
								
								map.put(tcolNmList.get(i), textList.get(i));
								
							}
	
							
							
							map.put("testDay",sdate);
							map.put("insertUserid",subject_idx);
							map.put("updateUserid",subject_idx);
							num = sheetService.update_mb_sp_newlist(map);
						}else if(!(exam_num.equals(""))) {
							Map<String, String> chkmap	 = new HashMap<String, String>();
							chkmap.put("objectIdx", idx);
							chkmap.put("examNum",exam_num);
							chkmap.put("examIdx",exam_idx);
							List<MbSpVO> chkIclist =  sheetService.select_mb_sp_list(chkmap);
							
							
							if(chkIclist.size() ==0 ) {
								Map<String, String> map1	 = new HashMap<String, String>();
								map1.put("objectIdx", idx);
								map1.put("subjectIdx",subject_idx);			
				
								map1.put("sp01", radioList.get(0));
								map1.put("sp03", radioList.get(1));
								map1.put("sp05a", radioList.get(2));
								map1.put("sp05b", radioList.get(3));
								map1.put("sp05c", radioList.get(4));
								map1.put("sp05d", radioList.get(5));
								map1.put("sp05e", radioList.get(6));
								map1.put("sp05f", radioList.get(7));
								map1.put("sp05g", radioList.get(8));
								map1.put("sp05h", radioList.get(9));
								map1.put("sp05i", radioList.get(10));
								map1.put("sp05jb", radioList.get(11));
								map1.put("sp06", radioList.get(12));
								map1.put("sp07", radioList.get(13));
								map1.put("sp08", radioList.get(14));
								map1.put("sp09", radioList.get(15));
								map1.put("sp10", radioList.get(16));
								map1.put("sp10a", radioList.get(17));
								map1.put("sp10b", radioList.get(18));
								map1.put("sp10c", radioList.get(19));
								map1.put("sp10d", radioList.get(20));
								map1.put("sp10e", radioList.get(21));
								
								/*
								map1.put("sp01a",textList.get(0));
								map1.put("sp01b",textList.get(1));
								map1.put("sp02a",textList.get(2));
								map1.put("sp02b",textList.get(3));
								map1.put("sp03a",textList.get(4));
								map1.put("sp03b",textList.get(5));
								map1.put("sp04a",textList.get(6));
								map1.put("sp04b",textList.get(7));
								map1.put("sp05ja", textList.get(8));
								map1.put("sp05e2", textList.get(9));
								map1.put("pha01g",textList.get(5));
								*/
								
								for(int i =0; i <textList.size(); i++) {
									
									map1.put(tcolNmList.get(i), textList.get(i));
									
								}
		
								
								map1.put("testDay",sdate);
							
								map1.put("insertUserid",subject_idx);
								map1.put("updateUserid",subject_idx);
							
								num = sheetService.update_mb_sp_newlist(map1);
							}else {
								Map<String, String> map2	 = new HashMap<String, String>();
								int exam_num1 =  Integer.parseInt(exam_num);
								int exam_Addnum = exam_num1+1;
								String exam_Newnum = String.valueOf(exam_Addnum);
								map2.put("examNum",exam_Newnum);
								System.out.println("examnNum(int)::"+exam_num1);
								System.out.println("exam_Addnum(int)::"+exam_Addnum);
								System.out.println("exam_Newnum(String)::"+exam_Newnum);
							

							map2.put("objectIdx", idx);
							map2.put("subjectIdx",subject_idx);			
							map2.put("examIdx",exam_idx);
							map2.put("sp01", radioList.get(0));
							map2.put("sp03", radioList.get(1));
							map2.put("sp05a", radioList.get(2));
							map2.put("sp05b", radioList.get(3));
							map2.put("sp05c", radioList.get(4));
							map2.put("sp05d", radioList.get(5));
							map2.put("sp05e", radioList.get(6));
							map2.put("sp05f", radioList.get(7));
							map2.put("sp05g", radioList.get(8));
							map2.put("sp05h", radioList.get(9));
							map2.put("sp05i", radioList.get(10));
							map2.put("sp05jb", radioList.get(11));
							map2.put("sp06", radioList.get(12));
							map2.put("sp07", radioList.get(13));
							map2.put("sp08", radioList.get(14));
							map2.put("sp09", radioList.get(15));
							map2.put("sp10", radioList.get(16));
							map2.put("sp10a", radioList.get(17));
							map2.put("sp10b", radioList.get(18));
							map2.put("sp10c", radioList.get(19));
							map2.put("sp10d", radioList.get(20));
							map2.put("sp10e", radioList.get(21));

							/*
							map2.put("sp01a",textList.get(0));
							map2.put("sp01b",textList.get(1));
							map2.put("sp02a",textList.get(2));
							map2.put("sp02b",textList.get(3));
							map2.put("sp03a",textList.get(4));
							map2.put("sp03b",textList.get(5));
							map2.put("sp04a",textList.get(6));
							map2.put("sp04b",textList.get(7));
							map2.put("sp05ja", textList.get(8));
							map2.put("sp05e2", textList.get(9));
							*/
							for(int i =0; i <textList.size(); i++) {
								
								map2.put(tcolNmList.get(i), textList.get(i));
								
							}
	
							
							map2.put("testDay",sdate);
						
							map2.put("insertUserid",subject_idx);
							map2.put("updateUserid",subject_idx);
							num = sheetService.update_mb_sp_newlist(map2);
						
								}
						  }
						   if(num> 0 ) {
							   Map<String, String> tmap	 = new HashMap<String, String>();
							   tmap.put("objectIdx", idx);
							   tmap.put("examId", exam_id);
							   tmap.put("cdbs", cdbs);
							  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
						
							  for(MbSJVO mbSJVO1 : maxNum){
								  	
								 mbSJVO.setExamNum(mbSJVO1.getExamNum());
								 maxNum1 = mbSJVO.getExamNum();
								 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
								 // System.out.println(maxNum1+"::maxnumselction값");
								 
							  }
							  
							  
						   }
					}else if(whichChk.equals("edit")) {
						if(exam_num.equals("")) {
							Map<String, String> map	 = new HashMap<String, String>();
							String examNum1 = "1";
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examIdx",exam_idx);
							map.put("examNum",examNum1);
							map.put("sp01", radioList.get(0));
							map.put("sp03", radioList.get(1));
							map.put("sp05a", radioList.get(2));
							map.put("sp05b", radioList.get(3));
							map.put("sp05c", radioList.get(4));
							map.put("sp05d", radioList.get(5));
							map.put("sp05e", radioList.get(6));
							map.put("sp05f", radioList.get(7));
							map.put("sp05g", radioList.get(8));
							map.put("sp05h", radioList.get(9));
							map.put("sp05i", radioList.get(10));
							map.put("sp05jb", radioList.get(11));
							map.put("sp06", radioList.get(12));
							map.put("sp07", radioList.get(13));
							map.put("sp08", radioList.get(14));
							map.put("sp09", radioList.get(15));
							map.put("sp10", radioList.get(16));
							map.put("sp10a", radioList.get(17));
							map.put("sp10b", radioList.get(18));
							map.put("sp10c", radioList.get(19));
							map.put("sp10d", radioList.get(20));
							map.put("sp10e", radioList.get(21));
							
							/*
							map.put("sp01a",textList.get(0));
							map.put("sp01b",textList.get(1));
							map.put("sp02a",textList.get(2));
							map.put("sp02b",textList.get(3));
							map.put("sp03a",textList.get(4));
							map.put("sp03b",textList.get(5));
							map.put("sp04a",textList.get(6));
							map.put("sp04b",textList.get(7));
							map.put("sp05ja", textList.get(8));
							map.put("sp05e2", textList.get(9));
							*/
							for(int i =0; i <textList.size(); i++) {
								
								map.put(tcolNmList.get(i), textList.get(i));
								
							}
	
							
							
							map.put("testDay",sdate);
						
							map.put("updateUserid",subject_idx);
							
							num = sheetService.update_mb_sp_editlist(map);
						}else if(!(exam_num.equals(""))) {	
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("subjectIdx",subject_idx);
							map1.put("examIdx",exam_idx);
							map1.put("examNum",exam_num);
							map1.put("sp01", radioList.get(0));
							map1.put("sp03", radioList.get(1));
							map1.put("sp05a", radioList.get(2));
							map1.put("sp05b", radioList.get(3));
							map1.put("sp05c", radioList.get(4));
							map1.put("sp05d", radioList.get(5));
							map1.put("sp05e", radioList.get(6));
							map1.put("sp05f", radioList.get(7));
							map1.put("sp05g", radioList.get(8));
							map1.put("sp05h", radioList.get(9));
							map1.put("sp05i", radioList.get(10));
							map1.put("sp05jb", radioList.get(11));
							map1.put("sp06", radioList.get(12));
							map1.put("sp07", radioList.get(13));
							map1.put("sp08", radioList.get(14));
							map1.put("sp09", radioList.get(15));
							map1.put("sp10", radioList.get(16));
							map1.put("sp10a", radioList.get(17));
							map1.put("sp10b", radioList.get(18));
							map1.put("sp10c", radioList.get(19));
							map1.put("sp10d", radioList.get(20));
							map1.put("sp10e", radioList.get(21));
							
							/*
							map1.put("sp01a",textList.get(0));
							map1.put("sp01b",textList.get(1));
							map1.put("sp02a",textList.get(2));
							map1.put("sp02b",textList.get(3));
							map1.put("sp03a",textList.get(4));
							map1.put("sp03b",textList.get(5));
							map1.put("sp04a",textList.get(6));
							map1.put("sp04b",textList.get(7));
							map1.put("sp05ja", textList.get(8));
							map1.put("sp05e2", textList.get(9));
							map1.put("pha01g",textList.get(5));
							*/
							for(int i =0; i <textList.size(); i++) {
								
								map1.put(tcolNmList.get(i), textList.get(i));
								
							}
	
							
							
							
							map1.put("testDay",sdate);
						
							map1.put("updateUserid",subject_idx);
							num = sheetService.update_mb_sp_editlist(map1);
						}
						
						
						
					}
			
					
					System.out.println(num +":사이즈");
					
					}catch(NumberFormatException e) {
						exam_num ="1";
						System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
						
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				
				
					
				    //리턴값
			        Map<String, Object> result = new HashMap<String, Object>();
					ModelAndView mav = new ModelAndView();
			        //성공했다고 처리
			       result.put("code", "OK");
			       result.put("message", "등록에 성공 하였습니다.");
			       mav.setViewName("/include/sheet/view_sp");
					//mav.addObject("retVal", retVal);
					mav.addObject("code", "OK");
					if(whichChk.equals("new")) {
						
						mav.addObject("message", "신규에 등록에 성공 하였습니다.");
						mav.addObject("objIdx", idx);
						mav.addObject("exam_num", maxNum1);
						mav.addObject("result","new");
						}else if(whichChk.equals("edit")) {
						mav.addObject("result","edit");
						mav.addObject("message", "수정을 성공 하였습니다.");
						//mav.addObject("dataList", ja_data_list);
						}		   
					
					
					
					return mav;
				}
		
				@RequestMapping("/view_sp.do")
				public ModelAndView view_sp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
						ModelMap model) throws Exception {
					MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
					String subject_idx = sessionMember.getIdx(); // login user idx
					String userId = sessionMember.getId();
					String Name = sessionMember.getName();
					System.out.println(subject_idx+":loginuseridx");
					System.out.println(userId+":usrid");
					System.out.println(Name+"::name");
					
					
					String object_idx = request.getParameter("object_idx");
					String exam_num = request.getParameter("exam_num");
					String exam_idx = request.getParameter("exam_idx");
					String exam_id = request.getParameter("exam_id");
					String action = request.getParameter("action");
					
					
					System.out.println(object_idx);
					System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
					System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
					Map<String, String> map = new HashMap<String, String>(3);
					map.put("objectIdx", object_idx);
					map.put("examNum", exam_num);
					map.put("examIdx", exam_idx);
					List<MbSpVO> sp_list = sheetService.select_mb_sp_list(map);
					
					MbSpVO mbSpVO = new MbSpVO();
							
					for(MbSpVO mbSpVO1 : sp_list ) {
						
						System.out.println(mbSpVO1.getSelectionNum()+":::차수");
						mbSpVO.setTestDay(mbSpVO1.getTestDay());
						//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
					}
					String testDay = mbSpVO.getTestDay();
				
					
					ModelAndView mav = new ModelAndView();
					mav.setViewName("/include/sheet/view_sp");
					mav.addObject("idx", object_idx);
					mav.addObject("action", action);
					mav.addObject("exam_id", exam_id);
					mav.addObject("testDay",testDay);
					mav.addObject("success", true);
					mav.addObject("sp_list", sp_list);

					return mav;
				}			
	@RequestMapping("/view_family.do")
	public ModelAndView view_family(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx);
		            
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		
		List<MbFmhVO> fmh_list = sheetService.select_mb_fmh_list(map);
		
		MbFmhVO mbFmhVO = new MbFmhVO();
		
		
		
		for(MbFmhVO fmhVO : fmh_list ) {
			
			
			
			
			
			System.out.println(fmhVO.getSelectionNum()+":::차수");
			mbFmhVO.setTestDay(fmhVO.getTestDay());
			/*설문실행일 */
			
		
		}
	
		String testDay = mbFmhVO.getTestDay();
		 if(testDay == null || testDay == "") {
			 
			 testDay ="yy/mm/dd";
		 }
		
		
		
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_family");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("fmh_list", fmh_list);

		return mav;
	}
	
	//ajax호출 view_kmmse2
	@RequestMapping(value="/view_savekmmse2.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savekmmse2(@RequestParam(value="textArr[]") List<String> textList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,
			@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, 
			@RequestParam(value="exam_idx")String exam_idx,@RequestParam(value="ts")String ts,@RequestParam(value="tts")String tts
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request, HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		String exam_idS = request.getParameter("exam_id");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println(exam_idS+"::exam_idS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(textList.size()+"::radioList사이즈크기");
		System.out.println("subject_idx::::"+subject_idx);
		
		System.out.println(object_idx+":object_idx");
		
		System.out.println("잘넘어왔습니당");
		
		
		
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					map.put("kms01" ,textList.get(0)); 
					map.put("kms02" ,textList.get(1)); 
					map.put("kms03" ,textList.get(2)); 
					map.put("kms04" ,textList.get(3)); 
					map.put("kms05" ,textList.get(4)); 
					map.put("kms06a",textList.get(5)); 
					map.put("kms06b",textList.get(6)); 
					map.put("kms06c",textList.get(7)); 
					map.put("kms06d",textList.get(8)); 
					map.put("kms06e",textList.get(9)); 
					map.put("kms07",textList.get(10)); 
					map.put("kms06score",ts);          
					map.put("kmsAllscore",tts);        


					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kmmse2_newlist(map);
					
			}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbKmmse2VO> chkkmmse2list =  sheetService.select_mb_kmmse2_list(chkmap);
					
					
					if(chkkmmse2list.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					  map1.put("objectIdx", idx);
					  map1.put("subjectIdx",subject_idx);			
			
					  map1.put("kms01" ,textList.get(0));
					  map1.put("kms02" ,textList.get(1));
					  map1.put("kms03" ,textList.get(2));
					  map1.put("kms04" ,textList.get(3));
					  map1.put("kms05" ,textList.get(4));
					  map1.put("kms06a",textList.get(5));
					  map1.put("kms06b",textList.get(6));
					  map1.put("kms06c",textList.get(7));
					  map1.put("kms06d",textList.get(8));
					  map1.put("kms06e",textList.get(9));
					  map1.put("kms07",textList.get(10));
					  map1.put("kms06score",ts);         
					  map1.put("kmsAllscore",tts);       


						
						
					 map1.put("testDay",sdate);
					 map1.put("insertUserid",subject_idx);
					 map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_kmmse2_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
						
						map2.put("kms01" ,textList.get(0));
						map2.put("kms02" ,textList.get(1));
						map2.put("kms03" ,textList.get(2));
						map2.put("kms04" ,textList.get(3));
						map2.put("kms05" ,textList.get(4));
						map2.put("kms06a",textList.get(5));
						map2.put("kms06b",textList.get(6));
						map2.put("kms06c",textList.get(7));
						map2.put("kms06d",textList.get(8));
						map2.put("kms06e",textList.get(9));
						map2.put("kms07",textList.get(10));
						map2.put("kms06score",ts);         
						map2.put("kmsAllscore",tts);       

						
						map2.put("testDay",sdate);
				
						map2.put("insertUserid",subject_idx);
						map2.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_kmmse2_newlist(map2);
				
						}
					
				  }
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
				
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
	  			
					map.put("kms01" ,textList.get(0)); 
					map.put("kms02" ,textList.get(1)); 
					map.put("kms03" ,textList.get(2)); 
					map.put("kms04" ,textList.get(3)); 
					map.put("kms05" ,textList.get(4)); 
					map.put("kms06a",textList.get(5)); 
					map.put("kms06b",textList.get(6)); 
					map.put("kms06c",textList.get(7)); 
					map.put("kms06d",textList.get(8)); 
					map.put("kms06e",textList.get(9)); 
					map.put("kms07",textList.get(10)); 
					map.put("kms06score",ts);          
					map.put("kmsAllscore",tts);      
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_kmmse2_editlist(map);
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
				
					 map1.put("kms01" ,textList.get(0));
					  map1.put("kms02" ,textList.get(1));
					  map1.put("kms03" ,textList.get(2));
					  map1.put("kms04" ,textList.get(3));
					  map1.put("kms05" ,textList.get(4));
					  map1.put("kms06a",textList.get(5));
					  map1.put("kms06b",textList.get(6));
					  map1.put("kms06c",textList.get(7));
					  map1.put("kms06d",textList.get(8));
					  map1.put("kms06e",textList.get(9));
					  map1.put("kms07",textList.get(10));
					  map1.put("kms06score",ts);         
					  map1.put("kmsAllscore",tts);       
		      
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kmmse2_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_kmmse2");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}
			
			
			
			return mav;
	}
	
	
	
	
	
	
	
	@RequestMapping("/view_medical.do")
	public ModelAndView view_medical(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");	
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		
		
		
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbMhVO> mh_list = sheetService.select_mb_mh_list(map);

		MbMhVO mbMhVO = new MbMhVO();
		
		for(MbMhVO mbMhVO1 : mh_list) {
			mbMhVO.setTestDay(mbMhVO1.getTestDay());
			
		}
		String testDay = mbMhVO.getTestDay();
		
		System.out.println(testDay+":::설문실행일");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_medical");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("success", true);
		mav.addObject("mh_list", mh_list);
		mav.addObject("testDay", testDay);
		return mav;
	}

	
	//ajax호출 view_mh(질병력)
	@RequestMapping(value="/view_savemedical.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_mh(@RequestParam(value="radioArr[]") List<String> radioList, @RequestParam(value="textArr[]") List<String> textList,
								@RequestParam(value="idx") String idx, @RequestParam(value="sdate")String sdate, @RequestParam(value="cdbs")String cdbs, @RequestParam(value="whichChk")String whichChk, 
								@RequestParam(value="exam_id")String exam_id, @RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,HttpServletRequest request,
								HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
		
			
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
				
	            map.put("mh01P" ,radioList.get(0));
	           	map.put("mh01AP" ,radioList.get(1));
	           	map.put("mh01BP" ,radioList.get(2));
	           	map.put("mh01CP" ,radioList.get(3));
	           	map.put("mh01DP" ,radioList.get(4));
	           	map.put("mh01EP" ,radioList.get(5));
	           	
	          	map.put("mh06P" ,radioList.get(6));
	          	map.put("mh07P" ,radioList.get(7));
	          	map.put("mh08P" ,radioList.get(8));
	          	map.put("mh09P" ,radioList.get(9));
	          	map.put("mh10P" ,radioList.get(10));
	          	map.put("mh11P" ,radioList.get(11));
	          	map.put("mh12P" ,radioList.get(12));
	          	map.put("mh13P" ,radioList.get(13));
	          	map.put("mh14P" ,radioList.get(14));
	          	map.put("mh15P" ,radioList.get(15));
	          	map.put("mh16P" ,radioList.get(16));
	          	map.put("mh17P" ,radioList.get(17));
	          	map.put("mh18P" ,radioList.get(18));
	          	map.put("mh19P" ,radioList.get(19));
	          	map.put("mh01N" ,radioList.get(20));
	           	map.put("mh01AN" ,radioList.get(21));
	           	map.put("mh01BN" ,radioList.get(22));
	           	map.put("mh01CN" ,radioList.get(23));
	           	map.put("mh01DN" ,radioList.get(24));
	           	map.put("mh01EN" ,radioList.get(25));
	          	map.put("mh02N" ,radioList.get(26));
	          	map.put("mh03N" ,radioList.get(27));
	       		map.put("mhNew04AN" ,radioList.get(28));
	       		map.put("mhNew04BN" ,radioList.get(29));
	       		map.put("mhNew04CN" ,radioList.get(30));       	
	          	map.put("mh04N" ,radioList.get(31));
	          	map.put("mh05N" ,radioList.get(32));
	          	map.put("mh06N" ,radioList.get(33));
	          	map.put("mh07N" ,radioList.get(34));
	          	map.put("mh08N" ,radioList.get(35));
	          	map.put("mh11N" ,radioList.get(36));
	          	map.put("mh12N" ,radioList.get(37));
	          	map.put("mh13N" ,radioList.get(38));
	          	map.put("mh14N" ,radioList.get(39));
	          	map.put("mh15N" ,radioList.get(40));
	          	map.put("mh16N" ,radioList.get(41));
	          	map.put("mh17N" ,radioList.get(42));
	          	map.put("mh18N" ,radioList.get(43));
	          	map.put("mh21N" ,radioList.get(44));                              
	            map.put("mh20" , textList.get(0));

			
				
				
				
				
				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_mh_newlist(map);
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbMhVO> chkIclist =  sheetService.select_mb_mh_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
		            map1.put("mh01P" ,radioList.get(0));
		           	map1.put("mh01AP" ,radioList.get(1));
		           	map1.put("mh01BP" ,radioList.get(2));
		           	map1.put("mh01CP" ,radioList.get(3));
		           	map1.put("mh01DP" ,radioList.get(4));
		           	map1.put("mh01EP" ,radioList.get(5));
		          	map1.put("mh06P" ,radioList.get(6));
		          	map1.put("mh07P" ,radioList.get(7));
		          	map1.put("mh08P" ,radioList.get(8));
		          	map1.put("mh09P" ,radioList.get(9));
		          	map1.put("mh10P" ,radioList.get(10));
		          	map1.put("mh11P" ,radioList.get(11));
		          	map1.put("mh12P" ,radioList.get(12));
		          	map1.put("mh13P" ,radioList.get(13));
		          	map1.put("mh14P" ,radioList.get(14));
		          	map1.put("mh15P" ,radioList.get(15));
		          	map1.put("mh16P" ,radioList.get(16));
		          	map1.put("mh17P" ,radioList.get(17));
		          	map1.put("mh18P" ,radioList.get(18));
		          	map1.put("mh19P" ,radioList.get(19));
		          	map1.put("mh01N" ,radioList.get(20));
		           	map1.put("mh01AN" ,radioList.get(21));
		           	map1.put("mh01BN" ,radioList.get(22));
		           	map1.put("mh01CN" ,radioList.get(23));
		           	map1.put("mh01DN" ,radioList.get(24));
		           	map1.put("mh01EN" ,radioList.get(25));
		          	map1.put("mh02N" ,radioList.get(26));
		          	map1.put("mh03N" ,radioList.get(27));
		       		map1.put("mhNew04AN" ,radioList.get(28));
		       		map1.put("mhNew04BN" ,radioList.get(29));
		       		map1.put("mhNew04CN" ,radioList.get(30));       	
		          	map1.put("mh04N" ,radioList.get(31));
		          	map1.put("mh05N" ,radioList.get(32));
		          	map1.put("mh06N" ,radioList.get(33));
		          	map1.put("mh07N" ,radioList.get(34));
		          	map1.put("mh08N" ,radioList.get(35));
		          	map1.put("mh11N" ,radioList.get(36));
		          	map1.put("mh12N" ,radioList.get(37));
		          	map1.put("mh13N" ,radioList.get(38));
		          	map1.put("mh14N" ,radioList.get(39));
		          	map1.put("mh15N" ,radioList.get(40));
		          	map1.put("mh16N" ,radioList.get(41));
		          	map1.put("mh17N" ,radioList.get(42));
		          	map1.put("mh18N" ,radioList.get(43));
		          	map1.put("mh21N" ,radioList.get(44));                              
		            map1.put("mh20" , textList.get(0));
					
					
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_mh_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
		            map2.put("mh01P" ,radioList.get(0));
		           	map2.put("mh01AP" ,radioList.get(1));
		           	map2.put("mh01BP" ,radioList.get(2));
		           	map2.put("mh01CP" ,radioList.get(3));
		           	map2.put("mh01DP" ,radioList.get(4));
		           	map2.put("mh01EP" ,radioList.get(5));
		          	map2.put("mh06P" ,radioList.get(6));
		          	map2.put("mh07P" ,radioList.get(7));
		          	map2.put("mh08P" ,radioList.get(8));
		          	map2.put("mh09P" ,radioList.get(9));
		          	map2.put("mh10P" ,radioList.get(10));
		          	map2.put("mh11P" ,radioList.get(11));
		          	map2.put("mh12P" ,radioList.get(12));
		          	map2.put("mh13P" ,radioList.get(13));
		          	map2.put("mh14P" ,radioList.get(14));
		          	map2.put("mh15P" ,radioList.get(15));
		          	map2.put("mh16P" ,radioList.get(16));
		          	map2.put("mh17P" ,radioList.get(17));
		          	map2.put("mh18P" ,radioList.get(18));
		          	map2.put("mh19P" ,radioList.get(19));
		          	map2.put("mh01N" ,radioList.get(20));
		           	map2.put("mh01AN" ,radioList.get(21));
		           	map2.put("mh01BN" ,radioList.get(22));
		           	map2.put("mh01CN" ,radioList.get(23));
		           	map2.put("mh01DN" ,radioList.get(24));
		           	map2.put("mh01EN" ,radioList.get(25));
		          	map2.put("mh02N" ,radioList.get(26));
		          	map2.put("mh03N" ,radioList.get(27));
		       		map2.put("mhNew04AN" ,radioList.get(28));
		       		map2.put("mhNew04BN" ,radioList.get(29));
		       		map2.put("mhNew04CN" ,radioList.get(30));       	
		          	map2.put("mh04N" ,radioList.get(31));
		          	map2.put("mh05N" ,radioList.get(32));
		          	map2.put("mh06N" ,radioList.get(33));
		          	map2.put("mh07N" ,radioList.get(34));
		          	map2.put("mh08N" ,radioList.get(35));
		          	map2.put("mh11N" ,radioList.get(36));
		          	map2.put("mh12N" ,radioList.get(37));
		          	map2.put("mh13N" ,radioList.get(38));
		          	map2.put("mh14N" ,radioList.get(39));
		          	map2.put("mh15N" ,radioList.get(40));
		          	map2.put("mh16N" ,radioList.get(41));
		          	map2.put("mh17N" ,radioList.get(42));
		          	map2.put("mh18N" ,radioList.get(43));
		          	map2.put("mh21N" ,radioList.get(44));                              
		            map2.put("mh20" , textList.get(0));
					map2.put("testDay",sdate);
			
				map2.put("insertUserid",subject_idx);
				map2.put("updateUserid",subject_idx);
				num = sheetService.update_mb_mh_newlist(map2);
			
					}
			  }
			
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
			   		
		
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
	            map.put("mh01P" ,radioList.get(0));
	           	map.put("mh01AP" ,radioList.get(1));
	           	map.put("mh01BP" ,radioList.get(2));
	           	map.put("mh01CP" ,radioList.get(3));
	           	map.put("mh01DP" ,radioList.get(4));
	           	map.put("mh01EP" ,radioList.get(5));
	          	map.put("mh06P" ,radioList.get(6));
	          	map.put("mh07P" ,radioList.get(7));
	          	map.put("mh08P" ,radioList.get(8));
	          	map.put("mh09P" ,radioList.get(9));
	          	map.put("mh10P" ,radioList.get(10));
	          	map.put("mh11P" ,radioList.get(11));
	          	map.put("mh12P" ,radioList.get(12));
	          	map.put("mh13P" ,radioList.get(13));
	          	map.put("mh14P" ,radioList.get(14));
	          	map.put("mh15P" ,radioList.get(15));
	          	map.put("mh16P" ,radioList.get(16));
	          	map.put("mh17P" ,radioList.get(17));
	          	map.put("mh18P" ,radioList.get(18));
	          	map.put("mh19P" ,radioList.get(19));
	          	map.put("mh01N" ,radioList.get(20));
	           	map.put("mh01AN" ,radioList.get(21));
	           	map.put("mh01BN" ,radioList.get(22));
	           	map.put("mh01CN" ,radioList.get(23));
	           	map.put("mh01DN" ,radioList.get(24));
	           	map.put("mh01EN" ,radioList.get(25));
	          	map.put("mh02N" ,radioList.get(26));
	          	map.put("mh03N" ,radioList.get(27));
	       		map.put("mhNew04AN" ,radioList.get(28));
	       		map.put("mhNew04BN" ,radioList.get(29));
	       		map.put("mhNew04CN" ,radioList.get(30));       	
	          	map.put("mh04N" ,radioList.get(31));
	          	map.put("mh05N" ,radioList.get(32));
	          	map.put("mh06N" ,radioList.get(33));
	          	map.put("mh07N" ,radioList.get(34));
	          	map.put("mh08N" ,radioList.get(35));
	          	map.put("mh11N" ,radioList.get(36));
	          	map.put("mh12N" ,radioList.get(37));
	          	map.put("mh13N" ,radioList.get(38));
	          	map.put("mh14N" ,radioList.get(39));
	          	map.put("mh15N" ,radioList.get(40));
	          	map.put("mh16N" ,radioList.get(41));
	          	map.put("mh17N" ,radioList.get(42));
	          	map.put("mh18N" ,radioList.get(43));
	          	map.put("mh21N" ,radioList.get(44));                              
	            map.put("mh20" , textList.get(0));
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_mh_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			
	            map1.put("mh01P" ,radioList.get(0));
	           	map1.put("mh01AP" ,radioList.get(1));
	           	map1.put("mh01BP" ,radioList.get(2));
	           	map1.put("mh01CP" ,radioList.get(3));
	           	map1.put("mh01DP" ,radioList.get(4));
	           	map1.put("mh01EP" ,radioList.get(5));
	          	map1.put("mh06P" ,radioList.get(6));
	          	map1.put("mh07P" ,radioList.get(7));
	          	map1.put("mh08P" ,radioList.get(8));
	          	map1.put("mh09P" ,radioList.get(9));
	          	map1.put("mh10P" ,radioList.get(10));
	          	map1.put("mh11P" ,radioList.get(11));
	          	map1.put("mh12P" ,radioList.get(12));
	          	map1.put("mh13P" ,radioList.get(13));
	          	map1.put("mh14P" ,radioList.get(14));
	          	map1.put("mh15P" ,radioList.get(15));
	          	map1.put("mh16P" ,radioList.get(16));
	          	map1.put("mh17P" ,radioList.get(17));
	          	map1.put("mh18P" ,radioList.get(18));
	          	map1.put("mh19P" ,radioList.get(19));
	          	map1.put("mh01N" ,radioList.get(20));
	           	map1.put("mh01AN" ,radioList.get(21));
	           	map1.put("mh01BN" ,radioList.get(22));
	           	map1.put("mh01CN" ,radioList.get(23));
	           	map1.put("mh01DN" ,radioList.get(24));
	           	map1.put("mh01EN" ,radioList.get(25));
	          	map1.put("mh02N" ,radioList.get(26));
	          	map1.put("mh03N" ,radioList.get(27));
	       		map1.put("mhNew04AN" ,radioList.get(28));
	       		map1.put("mhNew04BN" ,radioList.get(29));
	       		map1.put("mhNew04CN" ,radioList.get(30));       	
	          	map1.put("mh04N" ,radioList.get(31));
	          	map1.put("mh05N" ,radioList.get(32));
	          	map1.put("mh06N" ,radioList.get(33));
	          	map1.put("mh07N" ,radioList.get(34));
	          	map1.put("mh08N" ,radioList.get(35));
	          	map1.put("mh11N" ,radioList.get(36));
	          	map1.put("mh12N" ,radioList.get(37));
	          	map1.put("mh13N" ,radioList.get(38));
	          	map1.put("mh14N" ,radioList.get(39));
	          	map1.put("mh15N" ,radioList.get(40));
	          	map1.put("mh16N" ,radioList.get(41));
	          	map1.put("mh17N" ,radioList.get(42));
	          	map1.put("mh18N" ,radioList.get(43));
	          	map1.put("mh21N" ,radioList.get(44));                              
	            map1.put("mh20" , textList.get(0));
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_mh_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_medical");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
		mav.addObject("message", "신규에 등록에 성공 하였습니다.");
		mav.addObject("objIdx", idx);
		mav.addObject("exam_num", maxNum1);
		mav.addObject("result","new");
		}else if(whichChk.equals("edit")) {
		mav.addObject("result","edit");
		mav.addObject("message", "수정을 성공 하였습니다.");
		//mav.addObject("dataList", ja_data_list);
		}	
		
		
		
		
		return mav;
	}
	
	
	
	@RequestMapping("/view_drug.do")
	public ModelAndView view_drug(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbDgVO> dg_list = sheetService.select_mb_dg_list(map);
		//List<MbAhVO> ah_list = sheetService.select_mb_ah_list(map);
		//List<MbShVO> sh_list = sheetService.select_mb_sh_list(map);

			MbDgVO mbDgVO = new MbDgVO();
		for(MbDgVO mbDgVO1 : dg_list ) {
			
			System.out.println(mbDgVO1.getSelectionNum()+":::차수");
			mbDgVO.setTestDay(mbDgVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		
		String testDay = mbDgVO.getTestDay();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_drug");
		mav.addObject("success", true);
		mav.addObject("idx", object_idx);		
		mav.addObject("dg_list", dg_list);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		//mav.addObject("ah_list", ah_list);
		//mav.addObject("sh_list", sh_list);

		return mav;
	}
	
	
	
	//ajax호출 view_dg(drug)
	@RequestMapping(value="/view_savedrug.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savedrug(@RequestParam(value="colNmArr[]") List<String> colNmList,@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="textArr[]") List<String> textList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate,@RequestParam(value="cdbs")String cdbs
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,HttpServletRequest request,
		HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
		System.out.println(colNmList.get(3)+"::colNmList.get(0)");
			
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
			
				
				for(int i =0; i <radioList.size(); i++) {
					
					map.put(colNmList.get(i), radioList.get(i));
					
				}
				/*
				map.put("dg01" ,radioList.get(0)); 
				map.put("dg02" ,radioList.get(1)); 
				map.put("dg031" ,radioList.get(2)); 
				map.put("dg032" ,radioList.get(3)); 
				map.put("dg033" ,radioList.get(4)); 
				map.put("dg034" ,radioList.get(5)); 
				map.put("dg035" ,radioList.get(6)); 
				map.put("dg036" ,radioList.get(7)); 
				map.put("dg037" ,radioList.get(8)); 
				map.put("dg038" ,radioList.get(9)); 
				map.put("dg039" ,radioList.get(10));
				map.put("dg0310" ,radioList.get(11));
				map.put("dg0311" ,radioList.get(12));
				map.put("dg0312" ,radioList.get(13));
				map.put("dg0313" ,radioList.get(14));
				map.put("dg0314" ,radioList.get(15));
				map.put("dg0315" ,radioList.get(16));
				*/
				map.put("dg04" ,textList.get(0));                           

				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_dg_newlist(map);
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbDgVO> chkIclist =  sheetService.select_mb_dg_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
					for(int i =0; i <radioList.size(); i++) {
						
						map1.put(colNmList.get(i), radioList.get(i));
						
					}
					map1.put("dg04" ,textList.get(0));
					
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_dg_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
					for(int i =0; i <radioList.size(); i++) {
						
						map2.put(colNmList.get(i), radioList.get(i));
						
					}
					map2.put("dg04" ,textList.get(0));
					
		            map2.put("testDay",sdate);
			
				map2.put("insertUserid",subject_idx);
				map2.put("updateUserid",subject_idx);
				num = sheetService.update_mb_dg_newlist(map2);
			
					}
			  }

			   if(num> 0 ) {
			   Map<String, String> tmap	 = new HashMap<String, String>();
			   tmap.put("objectIdx", idx);
			   tmap.put("examId", exam_id);
			   tmap.put("cdbs", cdbs);
			  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
		
			  for(MbSJVO mbSJVO1 : maxNum){
				  	
				 mbSJVO.setExamNum(mbSJVO1.getExamNum());
				 maxNum1 = mbSJVO.getExamNum();
				 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
				 // System.out.println(maxNum1+"::maxnumselction값");
				 
			  }
			  
			  
		   }
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
				for(int i =0; i <radioList.size(); i++) {
					
					map.put(colNmList.get(i), radioList.get(i));
					
				}
				map.put("dg04" ,textList.get(0));
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_dg_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			
				for(int i =0; i <radioList.size(); i++) {
					
					map1.put(colNmList.get(i), radioList.get(i));
					
				}
				map1.put("dg04" ,textList.get(0));
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_dg_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_drug");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}		   
		
		
		
		return mav;
	}
	
	
	
	

	@RequestMapping("/view_kdsq.do")
	public ModelAndView view_kdsq(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");  
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);	
		
		List<MbKdsqVO> kdsq_list = sheetService.select_mb_kdsq_list(map);

		MbKdsqVO mbKdsqVO = new MbKdsqVO();	
		
		for(MbKdsqVO mbKdsqVO1 : kdsq_list ) {
			
			System.out.println(mbKdsqVO1.getSelectionNum()+":::차수");
			mbKdsqVO.setTestDay(mbKdsqVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbKdsqVO.getTestDay();

	
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_kdsq");
		mav.addObject("idx", object_idx);
		mav.addObject("exam_id", exam_id);  
		mav.addObject("action", action);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("kdsq_list", kdsq_list);


		return mav;
	}
	
	
	
	//ajax호출 view_kdsq(kdsq)
	@RequestMapping(value="/view_savekdsq.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_kdsq(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,
		@RequestParam(value="tscore")String tscore,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,
		HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(tscore+"::tscore값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
	

			

		MbSJVO mbSJVO = new MbSJVO();                                                                                                                              
		String maxNum1 = null;   
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
				
				map.put("kdsq01" ,radioList.get(0));
	            map.put("kdsq02" ,radioList.get(1));
	            map.put("kdsq03" ,radioList.get(2));
	            map.put("kdsq04" ,radioList.get(3));
	            map.put("kdsq05" ,radioList.get(4));
	            map.put("kdsq06" ,radioList.get(5));
	            map.put("kdsq07" ,radioList.get(6));
	            map.put("kdsq08" ,radioList.get(7));
	            map.put("kdsq09" ,radioList.get(8));
	            map.put("kdsq10" ,radioList.get(9));
	            map.put("kdsq11" ,radioList.get(10));
	            map.put("kdsq12" ,radioList.get(11));
	            map.put("kdsq13" ,radioList.get(12));
	            map.put("kdsq14" ,radioList.get(13));
	            map.put("kdsq15" ,radioList.get(14));
	            map.put("kdsqTotal" ,tscore); 	           


				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_kdsq_newlist(map);
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbKdsqVO> chkIclist =  sheetService.select_mb_kdsq_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
					map1.put("kdsq01" ,radioList.get(0));
					map1.put("kdsq02" ,radioList.get(1));
					map1.put("kdsq03" ,radioList.get(2));
					map1.put("kdsq04" ,radioList.get(3));
					map1.put("kdsq05" ,radioList.get(4));
					map1.put("kdsq06" ,radioList.get(5));
					map1.put("kdsq07" ,radioList.get(6));
					map1.put("kdsq08" ,radioList.get(7));
					map1.put("kdsq09" ,radioList.get(8));
					map1.put("kdsq10" ,radioList.get(9));
					map1.put("kdsq11" ,radioList.get(10));
					map1.put("kdsq12" ,radioList.get(11));
					map1.put("kdsq13" ,radioList.get(12));
					map1.put("kdsq14" ,radioList.get(13));
					map1.put("kdsq15" ,radioList.get(14));
					map1.put("kdsqTotal" ,tscore);
				
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_kdsq_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
					map2.put("kdsq01" ,radioList.get(0));
					map2.put("kdsq02" ,radioList.get(1));
					map2.put("kdsq03" ,radioList.get(2));
					map2.put("kdsq04" ,radioList.get(3));
					map2.put("kdsq05" ,radioList.get(4));
					map2.put("kdsq06" ,radioList.get(5));
					map2.put("kdsq07" ,radioList.get(6));
					map2.put("kdsq08" ,radioList.get(7));
					map2.put("kdsq09" ,radioList.get(8));
					map2.put("kdsq10" ,radioList.get(9));
					map2.put("kdsq11" ,radioList.get(10));
					map2.put("kdsq12" ,radioList.get(11));
					map2.put("kdsq13" ,radioList.get(12));
					map2.put("kdsq14" ,radioList.get(13));
					map2.put("kdsq15" ,radioList.get(14));
					map2.put("kdsqTotal" ,tscore); 
			
					map2.put("testDay",sdate);
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kdsq_newlist(map2);
			
					}
			  }
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
				
		
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
				map.put("kdsq01" ,radioList.get(0));
				map.put("kdsq02" ,radioList.get(1));
				map.put("kdsq03" ,radioList.get(2));
				map.put("kdsq04" ,radioList.get(3));
				map.put("kdsq05" ,radioList.get(4));
				map.put("kdsq06" ,radioList.get(5));
				map.put("kdsq07" ,radioList.get(6));
				map.put("kdsq08" ,radioList.get(7));
				map.put("kdsq09" ,radioList.get(8));
				map.put("kdsq10" ,radioList.get(9));
				map.put("kdsq11" ,radioList.get(10));
				map.put("kdsq12" ,radioList.get(11));
				map.put("kdsq13" ,radioList.get(12));
				map.put("kdsq14" ,radioList.get(13));
				map.put("kdsq15" ,radioList.get(14));
				map.put("kdsqTotal" ,tscore); 
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_kdsq_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			
				map1.put("kdsq01" ,radioList.get(0));
				map1.put("kdsq02" ,radioList.get(1));
				map1.put("kdsq03" ,radioList.get(2));
				map1.put("kdsq04" ,radioList.get(3));
				map1.put("kdsq05" ,radioList.get(4));
				map1.put("kdsq06" ,radioList.get(5));
				map1.put("kdsq07" ,radioList.get(6));
				map1.put("kdsq08" ,radioList.get(7));
				map1.put("kdsq09" ,radioList.get(8));
				map1.put("kdsq10" ,radioList.get(9));
				map1.put("kdsq11" ,radioList.get(10));
				map1.put("kdsq12" ,radioList.get(11));
				map1.put("kdsq13" ,radioList.get(12));
				map1.put("kdsq14" ,radioList.get(13));
				map1.put("kdsq15" ,radioList.get(14));
				map1.put("kdsqTotal" ,tscore); 
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_kdsq_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       mav.setViewName("/include/sheet/view_kdsq");
		//mav.addObject("retVal", retVal);
		if(whichChk.equals("new")) {                                                                                                                             
			 mav.addObject("code", "OK");
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");                                                                                              
			mav.addObject("objIdx", idx);                                                                                                                            
			mav.addObject("exam_num", maxNum1);                                                                                                                      
			mav.addObject("new","new");                                                                                                                              
			mav.addObject("result","new");                                                                                                                           
			}else if(whichChk.equals("edit")) {    
			 mav.addObject("code", "OK");
				mav.addObject("result","edit");                                                                                                                        
			mav.addObject("message", "수정을 성공 하였습니다.");                                                                                                     
			//mav.addObject("dataList", ja_data_list);                                                                                                               
			}   
	
		
		
		
		return mav;
	}	
	
	@RequestMapping("/view_kdsqv.do")
	public ModelAndView view_kdsqv(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");

		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);	
		
		List<MbKdsqvVO> kdsqv_list = sheetService.select_mb_kdsqv_list(map);

		MbKdsqvVO mbKdsqvVO = new MbKdsqvVO();	
		
		
		for(MbKdsqvVO mbKdsqvVO1 : kdsqv_list ) {
			
			System.out.println(mbKdsqvVO1.getSelectionNum()+":::차수");
			mbKdsqvVO.setTestDay(mbKdsqvVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbKdsqvVO.getTestDay();

	
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_kdsqv");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("kdsqv_list", kdsqv_list);


		return mav;
	}
	
	
	//ajax호출 view_kdsqv(kdsqv)
	@RequestMapping(value="/view_savekdsqv.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_kdsqv(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,
		@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
		,@RequestParam(value="cdbs")String cdbs,
		HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
	

		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;			

		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
				
				map.put("kdsqv01" ,radioList.get(0));
				map.put("kdsqv02" ,radioList.get(1));
				map.put("kdsqv03" ,radioList.get(2));
				map.put("kdsqv04" ,radioList.get(3));
				map.put("kdsqv05" ,radioList.get(4));
				

				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_kdsqv_newlist(map);
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbKdsqvVO> chkIclist =  sheetService.select_mb_kdsqv_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
					map1.put("kdsqv01" ,radioList.get(0));
					map1.put("kdsqv02" ,radioList.get(1));
					map1.put("kdsqv03" ,radioList.get(2));
					map1.put("kdsqv04" ,radioList.get(3));
					map1.put("kdsqv05" ,radioList.get(4));
					
				
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_kdsqv_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
					map2.put("kdsqv01" ,radioList.get(0));
					map2.put("kdsqv02" ,radioList.get(1));
					map2.put("kdsqv03" ,radioList.get(2));
					map2.put("kdsqv04" ,radioList.get(3));
					map2.put("kdsqv05" ,radioList.get(4));
					
			
					map2.put("testDay",sdate);
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_kdsqv_newlist(map2);
			
					}
			  }
		
			 if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
			
			
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
				map.put("kdsqv01" ,radioList.get(0));
				map.put("kdsqv02" ,radioList.get(1));
				map.put("kdsqv03" ,radioList.get(2));
				map.put("kdsqv04" ,radioList.get(3));
				map.put("kdsqv05" ,radioList.get(4));
				
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_kdsqv_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			
				map1.put("kdsqv01" ,radioList.get(0));
				map1.put("kdsqv02" ,radioList.get(1));
				map1.put("kdsqv03" ,radioList.get(2));
				map1.put("kdsqv04" ,radioList.get(3));
				map1.put("kdsqv05" ,radioList.get(4));
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_kdsqv_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_kdsqv");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}		   
		
		
		return mav;
	}		
	
	@RequestMapping("/view_smcq.do")
	public ModelAndView view_smcq(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
	
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
	
		List<MbSmcqVO> smcq_list = sheetService.select_mb_smcq_list(map);
		
		MbSmcqVO mbSmcqVO = new MbSmcqVO();
		
		for(MbSmcqVO mbSmcqVO1 : smcq_list ) {
			
			System.out.println(mbSmcqVO1.getSelectionNum()+":::차수");
			mbSmcqVO.setTestDay(mbSmcqVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbSmcqVO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_smcq");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("smcq_list", smcq_list);

		return mav;
	}

	
	
	//ajax호출 view_smcq(smcq)
		@RequestMapping(value="/view_savesmcq.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_smcq(@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,
			@RequestParam(value="cdbs")String cdbs,@RequestParam(value="tscore")String tscore,HttpServletRequest request,
			HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
		//	String exam_num = request.getParameter("exam_num");
		//	System.out.println(exam_num+"::exam_num");
		
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
		

			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;	

			
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					map.put("smcq01" ,radioList.get(0));
					map.put("smcq02" ,radioList.get(1));
					map.put("smcqN03" ,radioList.get(2));
					map.put("smcq03" ,radioList.get(3));
					map.put("smcq04" ,radioList.get(4));
					map.put("smcq05" ,radioList.get(5));
					map.put("smcq06" ,radioList.get(6));
					map.put("smcq07" ,radioList.get(7));
					map.put("smcq08" ,radioList.get(8));
					map.put("smcq09" ,radioList.get(9));
					map.put("smcq10" ,radioList.get(10));
					map.put("smcq11" ,radioList.get(11));
					map.put("smcq12" ,radioList.get(12));
					map.put("smcq13" ,radioList.get(13));
					map.put("smcq14" ,radioList.get(14));
		            map.put("smcqTotal" ,tscore); 	           


					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
					num = sheetService.update_mb_smcq_newlist(map);
				}else if(!(exam_num.equals(""))) {
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",exam_num);
					chkmap.put("examIdx",exam_idx);
					List<MbSmcqVO> chkIclist =  sheetService.select_mb_smcq_list(chkmap);
								
					if(chkIclist.size() ==0 ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);			
		

						map1.put("smcq01" ,radioList.get(0)); 
						map1.put("smcq02" ,radioList.get(1)); 
						map1.put("smcqN03" ,radioList.get(2));
						map1.put("smcq03" ,radioList.get(3)); 
						map1.put("smcq04" ,radioList.get(4)); 
						map1.put("smcq05" ,radioList.get(5)); 
						map1.put("smcq06" ,radioList.get(6)); 
						map1.put("smcq07" ,radioList.get(7)); 
						map1.put("smcq08" ,radioList.get(8)); 
						map1.put("smcq09" ,radioList.get(9)); 
						map1.put("smcq10" ,radioList.get(10));
						map1.put("smcq11" ,radioList.get(11));
						map1.put("smcq12" ,radioList.get(12));
						map1.put("smcq13" ,radioList.get(13));
						map1.put("smcq14" ,radioList.get(14));

						map1.put("smcqTotal" ,tscore); 
					
						map1.put("testDay",sdate);
					
						map1.put("insertUserid",subject_idx);
						map1.put("updateUserid",subject_idx);
					
						num = sheetService.update_mb_smcq_newlist(map1);
					}else {
						Map<String, String> map2	 = new HashMap<String, String>();
						int exam_num1 =  Integer.parseInt(exam_num);
						int exam_Addnum = exam_num1+1;
						String exam_Newnum = String.valueOf(exam_Addnum);
						map2.put("examNum",exam_Newnum);
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
					

						map2.put("objectIdx", idx);
						map2.put("subjectIdx",subject_idx);			
						map2.put("examIdx",exam_idx);
					
						map2.put("smcq01" ,radioList.get(0)); 
						map2.put("smcq02" ,radioList.get(1)); 
						map2.put("smcqN03" ,radioList.get(2));
						map2.put("smcq03" ,radioList.get(3)); 
						map2.put("smcq04" ,radioList.get(4)); 
						map2.put("smcq05" ,radioList.get(5)); 
						map2.put("smcq06" ,radioList.get(6)); 
						map2.put("smcq07" ,radioList.get(7)); 
						map2.put("smcq08" ,radioList.get(8)); 
						map2.put("smcq09" ,radioList.get(9)); 
						map2.put("smcq10" ,radioList.get(10));
						map2.put("smcq11" ,radioList.get(11));
						map2.put("smcq12" ,radioList.get(12));
						map2.put("smcq13" ,radioList.get(13));
						map2.put("smcq14" ,radioList.get(14));

						map2.put("smcqTotal" ,tscore); 	
				
						map2.put("testDay",sdate);
						map2.put("insertUserid",subject_idx);
						map2.put("updateUserid",subject_idx);
						num = sheetService.update_mb_smcq_newlist(map2);
				
						}
				  }
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					Map<String, String> map	 = new HashMap<String, String>();
					String examNum1 = "1";
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
					
					map.put("smcq01" ,radioList.get(0)); 
					map.put("smcq02" ,radioList.get(1)); 
					map.put("smcqN03" ,radioList.get(2));
					map.put("smcq03" ,radioList.get(3)); 
					map.put("smcq04" ,radioList.get(4)); 
					map.put("smcq05" ,radioList.get(5)); 
					map.put("smcq06" ,radioList.get(6)); 
					map.put("smcq07" ,radioList.get(7)); 
					map.put("smcq08" ,radioList.get(8)); 
					map.put("smcq09" ,radioList.get(9)); 
					map.put("smcq10" ,radioList.get(10));
					map.put("smcq11" ,radioList.get(11));
					map.put("smcq12" ,radioList.get(12));
					map.put("smcq13" ,radioList.get(13));
					map.put("smcq14" ,radioList.get(14));

		            map.put("smcqTotal" ,tscore); 	
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_smcq_editlist(map);
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",exam_idx);
					map1.put("examNum",exam_num);
				

					map1.put("smcq01" ,radioList.get(0)); 
					map1.put("smcq02" ,radioList.get(1)); 
					map1.put("smcqN03" ,radioList.get(2));
					map1.put("smcq03" ,radioList.get(3)); 
					map1.put("smcq04" ,radioList.get(4)); 
					map1.put("smcq05" ,radioList.get(5)); 
					map1.put("smcq06" ,radioList.get(6)); 
					map1.put("smcq07" ,radioList.get(7)); 
					map1.put("smcq08" ,radioList.get(8)); 
					map1.put("smcq09" ,radioList.get(9)); 
					map1.put("smcq10" ,radioList.get(10));
					map1.put("smcq11" ,radioList.get(11));
					map1.put("smcq12" ,radioList.get(12));
					map1.put("smcq13" ,radioList.get(13));
					map1.put("smcq14" ,radioList.get(14));

					map1.put("smcqTotal" ,tscore); 
				
					map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_smcq_editlist(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_smcq");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			return mav;
		}		
	
	
	
	@RequestMapping("/view_gdsk.do")
	public ModelAndView view_gdsk(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
	
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbGdsVO> gdsk_list = sheetService.select_mb_gdsk_list(map);

		MbGdsVO mbGdsVO = new MbGdsVO();
		
		for(MbGdsVO mbGdsVO1 : gdsk_list ) {
			
			System.out.println(mbGdsVO1.getSelectionNum()+":::차수");
			mbGdsVO.setTestDay(mbGdsVO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbGdsVO.getTestDay();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_gdsk");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);		
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("gdsk_list", gdsk_list);

		return mav;
	}
	
	//ajax호출 view_gdsk(gds-k)
			@RequestMapping(value="/view_savegdsk.do", method=RequestMethod.POST)
			@ResponseBody
			public ModelAndView view_savegdsk(@RequestParam(value="radioArr[]") List<String> radioList,
					@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
				,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,
				@RequestParam(value="tscore")String tscore,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,
				HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String subject_idx = sessionMember.getIdx(); // login user idx
				String userId = sessionMember.getId();
				String Name = sessionMember.getName();
			   //String selectionNum = request.getParameter("selection_num");
				System.out.println("selectionNum::"+exam_num);
				System.out.println("subject_idx::::"+subject_idx);
				
				String object_idx = request.getParameter("object_idx");
			//	String exam_num = request.getParameter("exam_num");
			//	System.out.println(exam_num+"::exam_num");
			
				System.out.println("잘넘어왔습니당");
				System.out.println(object_idx+":object_idx");
				System.out.println("examNum::"+exam_num);
				System.out.println("examIdx::"+exam_idx);
				System.out.println(idx+"::objectidx값 받아왔다 ");
				System.out.println("선택버튼:"+whichChk);
				System.out.println(sdate+"::sdate값 받아왔다");
				System.out.println(radioList.size()+"::radioList사이즈크기");
			

				MbSJVO mbSJVO = new MbSJVO();
				String maxNum1 = null;
				

				
				try {	
				int num = 0;
			
				if(whichChk.equals("new")) {
					if(exam_num.equals("")) {
						Map<String, String> map	 = new HashMap<String, String>();
						String examNum1 = "1";
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examNum",examNum1);
						
						map.put("gds01" ,radioList.get(0));
						map.put("gds02" ,radioList.get(1));
						map.put("gds03" ,radioList.get(2));
						map.put("gds04" ,radioList.get(3));
						map.put("gds05" ,radioList.get(4));
						map.put("gds06" ,radioList.get(5));
						map.put("gds07" ,radioList.get(6));
						map.put("gds08" ,radioList.get(7));
						map.put("gds09" ,radioList.get(8));
						map.put("gds10" ,radioList.get(9));
						map.put("gds11" ,radioList.get(10));
						map.put("gds12" ,radioList.get(11));
						map.put("gds13" ,radioList.get(12));
						map.put("gds14" ,radioList.get(13));
						map.put("gds15" ,radioList.get(14));
						map.put("gds16" ,radioList.get(15));
						map.put("gds17" ,radioList.get(16));
						map.put("gds18" ,radioList.get(17));
						map.put("gds19" ,radioList.get(18));
						map.put("gds20" ,radioList.get(19));
						map.put("gds21" ,radioList.get(20));
						map.put("gds22" ,radioList.get(21));
						map.put("gds23" ,radioList.get(22));
						map.put("gds24" ,radioList.get(23));
						map.put("gds25" ,radioList.get(24));
						map.put("gds26" ,radioList.get(25));
						map.put("gds27" ,radioList.get(26));
						map.put("gds28" ,radioList.get(27));
						map.put("gds29" ,radioList.get(28));
						map.put("gds30" ,radioList.get(29));
						map.put("gdsTotal" ,tscore); 


						map.put("testDay",sdate);
						map.put("insertUserid",subject_idx);
						map.put("updateUserid",subject_idx);
						num = sheetService.update_mb_gdsk_newlist(map); 
					}else if(!(exam_num.equals(""))) {
						Map<String, String> chkmap	 = new HashMap<String, String>();
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",exam_num);
						chkmap.put("examIdx",exam_idx);
						List<MbGdsVO> chkIclist =  sheetService.select_mb_gdsk_list(chkmap);
						
						
						if(chkIclist.size() ==0 ) {
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("subjectIdx",subject_idx);			
			

							map1.put("gds01" ,radioList.get(0));
							map1.put("gds02" ,radioList.get(1));
							map1.put("gds03" ,radioList.get(2));
							map1.put("gds04" ,radioList.get(3));
							map1.put("gds05" ,radioList.get(4));
							map1.put("gds06" ,radioList.get(5));
							map1.put("gds07" ,radioList.get(6));
							map1.put("gds08" ,radioList.get(7));
							map1.put("gds09" ,radioList.get(8));
							map1.put("gds10" ,radioList.get(9));
							map1.put("gds11" ,radioList.get(10));
							map1.put("gds12" ,radioList.get(11));
							map1.put("gds13" ,radioList.get(12));
							map1.put("gds14" ,radioList.get(13));
							map1.put("gds15" ,radioList.get(14));
							map1.put("gds16" ,radioList.get(15));
							map1.put("gds17" ,radioList.get(16));
							map1.put("gds18" ,radioList.get(17));
							map1.put("gds19" ,radioList.get(18));
							map1.put("gds20" ,radioList.get(19));
							map1.put("gds21" ,radioList.get(20));
							map1.put("gds22" ,radioList.get(21));
							map1.put("gds23" ,radioList.get(22));
							map1.put("gds24" ,radioList.get(23));
							map1.put("gds25" ,radioList.get(24));
							map1.put("gds26" ,radioList.get(25));
							map1.put("gds27" ,radioList.get(26));
							map1.put("gds28" ,radioList.get(27));
							map1.put("gds29" ,radioList.get(28));
							map1.put("gds30" ,radioList.get(29));
							map1.put("gdsTotal" ,tscore);
						
							map1.put("testDay",sdate);
						
							map1.put("insertUserid",subject_idx);
							map1.put("updateUserid",subject_idx);
						
							num = sheetService.update_mb_gdsk_newlist(map1);
						}else {
							Map<String, String> map2	 = new HashMap<String, String>();
							int exam_num1 =  Integer.parseInt(exam_num);
							int exam_Addnum = exam_num1+1;
							String exam_Newnum = String.valueOf(exam_Addnum);
							map2.put("examNum",exam_Newnum);
							System.out.println("examnNum(int)::"+exam_num1);
							System.out.println("exam_Addnum(int)::"+exam_Addnum);
							System.out.println("exam_Newnum(String)::"+exam_Newnum);
						

							map2.put("objectIdx", idx);
							map2.put("subjectIdx",subject_idx);			
							map2.put("examIdx",exam_idx);
						
							map2.put("gds01" ,radioList.get(0));
							map2.put("gds02" ,radioList.get(1));
							map2.put("gds03" ,radioList.get(2));
							map2.put("gds04" ,radioList.get(3));
							map2.put("gds05" ,radioList.get(4));
							map2.put("gds06" ,radioList.get(5));
							map2.put("gds07" ,radioList.get(6));
							map2.put("gds08" ,radioList.get(7));
							map2.put("gds09" ,radioList.get(8));
							map2.put("gds10" ,radioList.get(9));
							map2.put("gds11" ,radioList.get(10));
							map2.put("gds12" ,radioList.get(11));
							map2.put("gds13" ,radioList.get(12));
							map2.put("gds14" ,radioList.get(13));
							map2.put("gds15" ,radioList.get(14));
							map2.put("gds16" ,radioList.get(15));
							map2.put("gds17" ,radioList.get(16));
							map2.put("gds18" ,radioList.get(17));
							map2.put("gds19" ,radioList.get(18));
							map2.put("gds20" ,radioList.get(19));
							map2.put("gds21" ,radioList.get(20));
							map2.put("gds22" ,radioList.get(21));
							map2.put("gds23" ,radioList.get(22));
							map2.put("gds24" ,radioList.get(23));
							map2.put("gds25" ,radioList.get(24));
							map2.put("gds26" ,radioList.get(25));
							map2.put("gds27" ,radioList.get(26));
							map2.put("gds28" ,radioList.get(27));
							map2.put("gds29" ,radioList.get(28));
							map2.put("gds30" ,radioList.get(29));
							map2.put("gdsTotal" ,tscore);
					
							map2.put("testDay",sdate);
							map2.put("insertUserid",subject_idx);
							map2.put("updateUserid",subject_idx);
							num = sheetService.update_mb_gdsk_newlist(map2);
					
							}
					  }

					   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
				   
				}else if(whichChk.equals("edit")) {
					if(exam_num.equals("")) {
						Map<String, String> map	 = new HashMap<String, String>();
						String examNum1 = "1";
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examIdx",exam_idx);
						map.put("examNum",examNum1);
						
						map.put("gds01" ,radioList.get(0));
						map.put("gds02" ,radioList.get(1));
						map.put("gds03" ,radioList.get(2));
						map.put("gds04" ,radioList.get(3));
						map.put("gds05" ,radioList.get(4));
						map.put("gds06" ,radioList.get(5));
						map.put("gds07" ,radioList.get(6));
						map.put("gds08" ,radioList.get(7));
						map.put("gds09" ,radioList.get(8));
						map.put("gds10" ,radioList.get(9));
						map.put("gds11" ,radioList.get(10));
						map.put("gds12" ,radioList.get(11));
						map.put("gds13" ,radioList.get(12));
						map.put("gds14" ,radioList.get(13));
						map.put("gds15" ,radioList.get(14));
						map.put("gds16" ,radioList.get(15));
						map.put("gds17" ,radioList.get(16));
						map.put("gds18" ,radioList.get(17));
						map.put("gds19" ,radioList.get(18));
						map.put("gds20" ,radioList.get(19));
						map.put("gds21" ,radioList.get(20));
						map.put("gds22" ,radioList.get(21));
						map.put("gds23" ,radioList.get(22));
						map.put("gds24" ,radioList.get(23));
						map.put("gds25" ,radioList.get(24));
						map.put("gds26" ,radioList.get(25));
						map.put("gds27" ,radioList.get(26));
						map.put("gds28" ,radioList.get(27));
						map.put("gds29" ,radioList.get(28));
						map.put("gds30" ,radioList.get(29));
						map.put("gdsTotal" ,tscore);
						
						map.put("testDay",sdate);
					
						map.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_gdsk_editlist(map);
					}else if(!(exam_num.equals(""))) {	
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",exam_idx);
						map1.put("examNum",exam_num);
					

						map1.put("gds01" ,radioList.get(0));
						map1.put("gds02" ,radioList.get(1));
						map1.put("gds03" ,radioList.get(2));
						map1.put("gds04" ,radioList.get(3));
						map1.put("gds05" ,radioList.get(4));
						map1.put("gds06" ,radioList.get(5));
						map1.put("gds07" ,radioList.get(6));
						map1.put("gds08" ,radioList.get(7));
						map1.put("gds09" ,radioList.get(8));
						map1.put("gds10" ,radioList.get(9));
						map1.put("gds11" ,radioList.get(10));
						map1.put("gds12" ,radioList.get(11));
						map1.put("gds13" ,radioList.get(12));
						map1.put("gds14" ,radioList.get(13));
						map1.put("gds15" ,radioList.get(14));
						map1.put("gds16" ,radioList.get(15));
						map1.put("gds17" ,radioList.get(16));
						map1.put("gds18" ,radioList.get(17));
						map1.put("gds19" ,radioList.get(18));
						map1.put("gds20" ,radioList.get(19));
						map1.put("gds21" ,radioList.get(20));
						map1.put("gds22" ,radioList.get(21));
						map1.put("gds23" ,radioList.get(22));
						map1.put("gds24" ,radioList.get(23));
						map1.put("gds25" ,radioList.get(24));
						map1.put("gds26" ,radioList.get(25));
						map1.put("gds27" ,radioList.get(26));
						map1.put("gds28" ,radioList.get(27));
						map1.put("gds29" ,radioList.get(28));
						map1.put("gds30" ,radioList.get(29));
						map1.put("gdsTotal" ,tscore);
					
						map1.put("testDay",sdate);
					
						map1.put("updateUserid",subject_idx);
						num = sheetService.update_mb_gdsk_editlist(map1);
					}
					
					
					
				}

				
				System.out.println(num +":사이즈");
				
				}catch(NumberFormatException e) {
					exam_num ="1";
					System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
				
			    //리턴값
		        Map<String, Object> result = new HashMap<String, Object>();
				ModelAndView mav = new ModelAndView();
		        //성공했다고 처리
		       result.put("code", "OK");
		       result.put("message", "등록에 성공 하였습니다.");
		       mav.setViewName("/include/sheet/view_gdsk");
				//mav.addObject("retVal", retVal);
				mav.addObject("code", "OK");
				if(whichChk.equals("new")) {
					
					mav.addObject("message", "신규에 등록에 성공 하였습니다.");
					mav.addObject("objIdx", idx);
					mav.addObject("exam_num", maxNum1);
					mav.addObject("result","new");
					}else if(whichChk.equals("edit")) {
					mav.addObject("result","edit");
					mav.addObject("message", "수정을 성공 하였습니다.");
					//mav.addObject("dataList", ja_data_list);
					}		   
				
				return mav;
			}		
	

	@RequestMapping("/view_kiadl.do")
	public ModelAndView view_kiadl(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);                          
		String subject_idx = sessionMember.getIdx(); // login user idx                                              
		String userId = sessionMember.getId();                                                                      
		String Name = sessionMember.getName();                                                                      
		System.out.println(subject_idx+":loginuseridx");                                                            
		System.out.println(userId+":usrid");                                                                        
		System.out.println(Name+"::name");                     
		
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");                                              
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");                                             
		System.out.println(object_idx);                                                                             
		                                                                                                            
		Map<String, String> map = new HashMap<String, String>(2);                                                   
		map.put("objectIdx", object_idx);                                                                           
		map.put("examNum", exam_num);            
		
		List<MbKiadlVO> kiadl_list = sheetService.select_mb_kiadl_list(map);
		//List<String> remark_list = sheetService.select_remark(map);
		MbKiadlVO mbKiadlVO = new MbKiadlVO();
		for(MbKiadlVO mbKiadlVO1 : kiadl_list ) {
			
			System.out.println(mbKiadlVO1.getSelectionNum()+":::차수");
			mbKiadlVO.setTestDay(mbKiadlVO1.getTestDay());
			
		}
		String testDay = mbKiadlVO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_kiadl");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);
		mav.addObject("kiadl_list", kiadl_list);
	//	mav.addObject("remark_list", remark_list);

		return mav;
	}

	@RequestMapping("/view_memory.do")
	public ModelAndView view_memory(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		String exam_id = request.getParameter("exam_id");    
		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String action = request.getParameter("action"); 
	
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		System.out.println(exam_id+"이것은 내가만든 파라미터 시퀀스tabid값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbC1VO> c1_list = sheetService.select_mb_c1_list(map);

		MbC1VO mbC1VO = new MbC1VO();
		
		for(MbC1VO mbC1VO1 : c1_list ) {
			
			System.out.println(mbC1VO1.getSelectionNum()+":::차수");
			mbC1VO.setTestDay(mbC1VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbC1VO.getTestDay();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_memory");
		mav.addObject("idx", object_idx);
		mav.addObject("testDay",testDay);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("success", true);
		mav.addObject("c1_list", c1_list);

		return mav;
	}

	
	//ajax호출 view_memory(Memory)
	@RequestMapping(value="/view_savememory.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savememory(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx,
		@RequestParam(value="exam_id")String exam_id,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
	

  		MbSJVO mbSJVO = new MbSJVO();
  		String maxNum1 = null;

		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
				
				map.put("cM01",radioList.get(0)); 
				map.put("cM02",radioList.get(1)); 
				map.put("cM03",radioList.get(2)); 
				map.put("cM04",radioList.get(3)); 
				map.put("cM05",radioList.get(4)); 
				map.put("cM06",radioList.get(5)); 
				map.put("cM07",radioList.get(6)); 
				map.put("cM08",radioList.get(7)); 
				map.put("cM09",radioList.get(8)); 
				map.put("cM10",radioList.get(9)); 
				map.put("cM11",radioList.get(10)); 
				map.put("cM12",radioList.get(11)); 
				map.put("cM13",radioList.get(12)); 
				map.put("cM14",radioList.get(13)); 
				map.put("cM15",radioList.get(14)); 
				map.put("cM16",radioList.get(15)); 
				map.put("cM17",radioList.get(16)); 
				map.put("cM18",radioList.get(17)); 
				map.put("cM19",radioList.get(18)); 
				map.put("cM20",radioList.get(19)); 
				map.put("cM21",radioList.get(20)); 
				map.put("cM22",radioList.get(21)); 


				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_c1_newlist(map); 
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbC1VO> chkIclist =  sheetService.select_mb_c1_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
					map1.put("cM01",radioList.get(0)); 
					map1.put("cM02",radioList.get(1)); 
					map1.put("cM03",radioList.get(2)); 
					map1.put("cM04",radioList.get(3)); 
					map1.put("cM05",radioList.get(4)); 
					map1.put("cM06",radioList.get(5)); 
					map1.put("cM07",radioList.get(6)); 
					map1.put("cM08",radioList.get(7)); 
					map1.put("cM09",radioList.get(8)); 
					map1.put("cM10",radioList.get(9)); 
					map1.put("cM11",radioList.get(10)); 
					map1.put("cM12",radioList.get(11)); 
					map1.put("cM13",radioList.get(12)); 
					map1.put("cM14",radioList.get(13)); 
					map1.put("cM15",radioList.get(14)); 
					map1.put("cM16",radioList.get(15)); 
					map1.put("cM17",radioList.get(16)); 
					map1.put("cM18",radioList.get(17)); 
					map1.put("cM19",radioList.get(18)); 
					map1.put("cM20",radioList.get(19)); 
					map1.put("cM21",radioList.get(20)); 
					map1.put("cM22",radioList.get(21)); 
				
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_c1_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
					map2.put("cM01",radioList.get(0)); 
					map2.put("cM02",radioList.get(1)); 
					map2.put("cM03",radioList.get(2)); 
					map2.put("cM04",radioList.get(3)); 
					map2.put("cM05",radioList.get(4)); 
					map2.put("cM06",radioList.get(5)); 
					map2.put("cM07",radioList.get(6)); 
					map2.put("cM08",radioList.get(7)); 
					map2.put("cM09",radioList.get(8)); 
					map2.put("cM10",radioList.get(9)); 
					map2.put("cM11",radioList.get(10)); 
					map2.put("cM12",radioList.get(11)); 
					map2.put("cM13",radioList.get(12)); 
					map2.put("cM14",radioList.get(13)); 
					map2.put("cM15",radioList.get(14)); 
					map2.put("cM16",radioList.get(15)); 
					map2.put("cM17",radioList.get(16)); 
					map2.put("cM18",radioList.get(17)); 
					map2.put("cM19",radioList.get(18)); 
					map2.put("cM20",radioList.get(19)); 
					map2.put("cM21",radioList.get(20)); 
					map2.put("cM22",radioList.get(21));  
			
					map2.put("testDay",sdate);
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c1_newlist(map2);
			
					}
			  }
			
	   		   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
				map.put("cM01",radioList.get(0)); 
				map.put("cM02",radioList.get(1)); 
				map.put("cM03",radioList.get(2)); 
				map.put("cM04",radioList.get(3)); 
				map.put("cM05",radioList.get(4)); 
				map.put("cM06",radioList.get(5)); 
				map.put("cM07",radioList.get(6)); 
				map.put("cM08",radioList.get(7)); 
				map.put("cM09",radioList.get(8)); 
				map.put("cM10",radioList.get(9)); 
				map.put("cM11",radioList.get(10)); 
				map.put("cM12",radioList.get(11)); 
				map.put("cM13",radioList.get(12)); 
				map.put("cM14",radioList.get(13)); 
				map.put("cM15",radioList.get(14)); 
				map.put("cM16",radioList.get(15)); 
				map.put("cM17",radioList.get(16)); 
				map.put("cM18",radioList.get(17)); 
				map.put("cM19",radioList.get(18)); 
				map.put("cM20",radioList.get(19)); 
				map.put("cM21",radioList.get(20)); 
				map.put("cM22",radioList.get(21)); 
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_c1_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			

				map1.put("cM01",radioList.get(0)); 
				map1.put("cM02",radioList.get(1)); 
				map1.put("cM03",radioList.get(2)); 
				map1.put("cM04",radioList.get(3)); 
				map1.put("cM05",radioList.get(4)); 
				map1.put("cM06",radioList.get(5)); 
				map1.put("cM07",radioList.get(6)); 
				map1.put("cM08",radioList.get(7)); 
				map1.put("cM09",radioList.get(8)); 
				map1.put("cM10",radioList.get(9)); 
				map1.put("cM11",radioList.get(10)); 
				map1.put("cM12",radioList.get(11)); 
				map1.put("cM13",radioList.get(12)); 
				map1.put("cM14",radioList.get(13)); 
				map1.put("cM15",radioList.get(14)); 
				map1.put("cM16",radioList.get(15)); 
				map1.put("cM17",radioList.get(16)); 
				map1.put("cM18",radioList.get(17)); 
				map1.put("cM19",radioList.get(18)); 
				map1.put("cM20",radioList.get(19)); 
				map1.put("cM21",radioList.get(20)); 
				map1.put("cM22",radioList.get(21)); 
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_c1_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_memory");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}	
		
		
		
		
		return mav;
	}	
	
	//언어
	@RequestMapping("/view_cognition1.do")
	public ModelAndView view_cognition1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		
		List<MbC2VO> c2_list = sheetService.select_mb_c2_cogn1_list(map);

		MbC2VO mbC2VO = new MbC2VO();
		for(MbC2VO mbC2VO1 : c2_list ) {
			
			System.out.println(mbC2VO1.getSelectionNum()+":::차수");
			mbC2VO.setTestDay(mbC2VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbC2VO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cognition1");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		mav.addObject("c2_list", c2_list);

		return mav;
	}
	
	
	//ajax호출 view_congnition1(savecognition1 언어)
			@RequestMapping(value="/view_savecognition1.do", method=RequestMethod.POST)
			@ResponseBody
			public ModelAndView view_savecognition1(@RequestParam(value="radioArr[]") List<String> radioList,
					@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
				,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
				,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String subject_idx = sessionMember.getIdx(); // login user idx
				String userId = sessionMember.getId();
				String Name = sessionMember.getName();
			   //String selectionNum = request.getParameter("selection_num");
				System.out.println("selectionNum::"+exam_num);
				System.out.println("subject_idx::::"+subject_idx);
				
				String object_idx = request.getParameter("object_idx");
				String exam_numS = request.getParameter("exam_num");
				String exam_idxS = request.getParameter("exam_idx");
				System.out.println(exam_numS+"::exam_numS");
				System.out.println(exam_idxS+"::exam_idxS");
				System.out.println("잘넘어왔습니당");
				System.out.println(object_idx+":object_idx");
				System.out.println("examNum::"+exam_num);
				System.out.println("examIdx::"+exam_idx);
				System.out.println(idx+"::objectidx값 받아왔다 ");
				System.out.println("선택버튼:"+whichChk);
				System.out.println(sdate+"::sdate값 받아왔다");
				System.out.println(radioList.size()+"::radioList사이즈크기");
			

					

				MbSJVO mbSJVO = new MbSJVO();
				String maxNum1 = null;
				try {	
				int num = 0;
			
				if(whichChk.equals("new")) {
					if(exam_num.equals("")) {
					  Map<String, String> chkmap	 = new HashMap<String, String>();
						String examNum1 = "1";
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",examNum1);
						//chkmap.put("examIdx",exam_idx);
						
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
						
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						if(chkExamNumlist.size() == 0) {
							Map<String, String> map	 = new HashMap<String, String>();
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examNum",examNum1);
							
							map.put("cL01", radioList.get(0)); 
				       		map.put("cL02", radioList.get(1));   
					        
							map.put("testDay",sdate);
							map.put("insertUserid",subject_idx);
							map.put("updateUserid",subject_idx);
					
							num = sheetService.update_mb_c2_cogn1_newlist(map);
						}else if(chkExamNumlist.size() > 0) {
							Map<String, String> maps	 = new HashMap<String, String>();
							maps.put("objectIdx", idx);
							maps.put("subjectIdx",subject_idx);
							maps.put("examIdx",mbC2VO.getIdx());
							maps.put("examNum",mbC2VO.getSelectionNum());
							
							maps.put("cL01", radioList.get(0)); 
				       		maps.put("cL02", radioList.get(1));  	  
					        
					    	maps.put("testDay",sdate);
					    	maps.put("updateUserid",subject_idx);
					    	maps.put("insertUserid",subject_idx);

					    	num= sheetService.update_mb_c2_cogn1_list(maps);
						}
						
						
					
					}else if(!(exam_num.equals(""))) {
						String examNum1 = "1";
						Map<String, String> chkmap1	 = new HashMap<String, String>();
						chkmap1.put("objectIdx", idx);
						chkmap1.put("examNum",exam_num);
						chkmap1.put("examIdx",exam_idx);
						List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn1_list(chkmap1);
						
						List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
						MbC2VO mbC2VO1  = new MbC2VO();
						for(MbC2VO mbC2VO2 : chkExamNumlist1) {
							 mbC2VO1.setIdx(mbC2VO2.getIdx());
							 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
						}
						if(chkIclist1.size() ==0  ) {
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("subjectIdx",subject_idx);
							map1.put("examIdx",mbC2VO1.getIdx());
							map1.put("examNum",mbC2VO1.getSelectionNum());
							
							map1.put("cL01", radioList.get(0)); 
				       		map1.put("cL02", radioList.get(1));   
					        
					    	map1.put("testDay",sdate);
					    	map1.put("updateUserid",subject_idx);
					    	map1.put("insertUserid",subject_idx);

					    	num= sheetService.update_mb_c2_cogn1_list(map1);
						}else {
							
							
							int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
							int exam_Addnum = exam_num1+1;						
							String exam_Newnum = String.valueOf(exam_Addnum);
							System.out.println(exam_Newnum+"exam_Newnum"); 
							System.out.println("examnNum(int)::"+exam_num1);
							System.out.println("exam_Addnum(int)::"+exam_Addnum);
							System.out.println("exam_Newnum(String)::"+exam_Newnum);
							Map<String, String> chkmap2	 = new HashMap<String, String>();
							chkmap2.put("objectIdx", idx);
							chkmap2.put("examNum",exam_Newnum);
							chkmap2.put("examIdx",exam_idx);
							List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
							MbC2VO mbC2VO3  = new MbC2VO();
							for(MbC2VO mbC2VO2 : chkExamNumlist2) {
								 mbC2VO3.setIdx(mbC2VO2.getIdx());
								 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
							}
							if(chkExamNumlist2.size() > 0 ) {
								Map<String, String> map3	 = new HashMap<String, String>();
								map3.put("objectIdx", idx);
								map3.put("subjectIdx",subject_idx);
								map3.put("examIdx",mbC2VO3.getIdx());
								map3.put("examNum",mbC2VO3.getSelectionNum());
								
								map3.put("cL01", radioList.get(0)); 
					       		map3.put("cL02", radioList.get(1));  
						        
						    	map3.put("testDay",sdate);
						    	map3.put("updateUserid",subject_idx);
						    	map3.put("insertUserid",subject_idx);

						    	num = sheetService.update_mb_c2_cogn1_list(map3);
								
								
								
							}else if(chkExamNumlist2.size() == 0) {
							Map<String, String> map4	 = new HashMap<String, String>();
							
							map4.put("examNum",exam_Newnum);
							map4.put("objectIdx", idx);
							map4.put("subjectIdx",subject_idx);			
							map4.put("examIdx",exam_idx);
						
							map4.put("cL01", radioList.get(0)); 
				       		map4.put("cL02", radioList.get(1)); 	  
					
							map4.put("testDay",sdate);
							map4.put("insertUserid",subject_idx);
							map4.put("updateUserid",subject_idx);
							num = sheetService.update_mb_c2_cogn1_newlist(map4);
							}
					
						}
				
				}
					   if(num> 0 ) {
						   Map<String, String> tmap	 = new HashMap<String, String>();
						   tmap.put("objectIdx", idx);
						   tmap.put("examId", exam_id);
						   tmap.put("cdbs", cdbs);
						  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
					
						  for(MbSJVO mbSJVO1 : maxNum){
							  	
							 mbSJVO.setExamNum(mbSJVO1.getExamNum());
							 maxNum1 = mbSJVO.getExamNum();
							 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
							 // System.out.println(maxNum1+"::maxnumselction값");
							 
						  }
						  
						  
					   }	
				}else if(whichChk.equals("edit")) {
					if(exam_num.equals("")) {
						String examNum1 = "1";
						Map<String, String> chkmap	 = new HashMap<String, String>();
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",examNum1);
						chkmap.put("examIdx",exam_idx);
						List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn1_list(chkmap);
						
						
							Map<String, String> chkmaps	 = new HashMap<String, String>();
							chkmaps.put("objectIdx", idx);
							chkmaps.put("examNum",examNum1);
							chkmaps.put("examIdx",exam_idx);
							List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
							MbC2VO mbC2VO  = new MbC2VO();
							for(MbC2VO mbC2VO1 : chkExamNumlist) {
								 mbC2VO.setIdx(mbC2VO1.getIdx());
								 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
							}
							if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
					
								Map<String, String> map	 = new HashMap<String, String>();
						
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examIdx",exam_idx);
							map.put("examNum",examNum1);
							
							map.put("cL01", radioList.get(0)); 
				       		map.put("cL02", radioList.get(1)); 	 	  
					        
							map.put("testDay",sdate);
						
							map.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_c2_cogn1_editlist(map);
						}
						
						
					}else if(!(exam_num.equals(""))) {	
						Map<String, String> chkmaps	 = new HashMap<String, String>();	
						chkmaps.put("objectIdx", idx);
						chkmaps.put("examNum",exam_num);
						chkmaps.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",mbC2VO.getIdx());
						map1.put("examNum",mbC2VO.getSelectionNum());
					

						map1.put("cL01", radioList.get(0)); 
			       		map1.put("cL02", radioList.get(1));   
						
				        map1.put("testDay",sdate);
					
						map1.put("updateUserid",subject_idx);
						num = sheetService.update_mb_c2_cogn1_editlist(map1);
					}
					
					
					
				}

				
				System.out.println(num +":사이즈");
				
				
				}catch(NumberFormatException e) {
					exam_num ="1";
					System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
				
			    //리턴값
		        Map<String, Object> result = new HashMap<String, Object>();
				ModelAndView mav = new ModelAndView();
		        //성공했다고 처리
		       result.put("code", "OK");
		       mav.addObject("code", "OK");
		       mav.setViewName("/include/sheet/view_cognition1");
				if(whichChk.equals("new")) {	
					mav.addObject("message", "신규에 등록에 성공 하였습니다.");
					mav.addObject("objIdx", idx);
					mav.addObject("exam_num", maxNum1);
					mav.addObject("new","new");
					mav.addObject("result","new");
					}else if(whichChk.equals("edit")) {
						mav.addObject("result","edit");
					mav.addObject("message", "수정을 성공 하였습니다.");
					//mav.addObject("dataList", ja_data_list);
					}
				return mav;
			}	
	
				
	
	
	//인격 혹은 행동
	@RequestMapping("/view_cognition1_2.do")
	public ModelAndView view_cognition1_2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
	
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		List<MbC2VO> c2_list2 = sheetService.select_mb_c2_cogn1_list2(map);
		
		MbC2VO mbC2VO = new MbC2VO();
			for(MbC2VO mbC2VO1 : c2_list2 ) {
				
				System.out.println(mbC2VO1.getSelectionNum()+":::차수");
				mbC2VO.setTestDay(mbC2VO1.getTestDay());
				//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
			}
			String testDay = mbC2VO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cognition1_2");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		mav.addObject("c2_list2", c2_list2);

		return mav;
	}
	
	
	//ajax호출 view_congnition1_2(savecognition1_2인격과 행동)
		@RequestMapping(value="/view_savecognition1_2.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savecognition1_2(@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
			String exam_numS = request.getParameter("exam_num");
			String exam_idxS = request.getParameter("exam_idx");
			System.out.println(exam_numS+"::exam_numS");
			System.out.println(exam_idxS+"::exam_idxS");
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
		

			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;			

			
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
				  Map<String, String> chkmap	 = new HashMap<String, String>();
					String examNum1 = "1";
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					//chkmap.put("examIdx",exam_idx);
					
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
					
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					if(chkExamNumlist.size() == 0) {
						Map<String, String> map	 = new HashMap<String, String>();
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examNum",examNum1);
						
						map.put("cP01", radioList.get(0)); 
			       		map.put("cP02", radioList.get(1)); 
			       		map.put("cP03", radioList.get(2)); 
				        map.put("cP04", radioList.get(3));	 	  
				        map.put("cP05", radioList.get(4));	 	  
				        
						map.put("testDay",sdate);
						map.put("insertUserid",subject_idx);
						map.put("updateUserid",subject_idx);
				
						num = sheetService.update_mb_c2_cogn1_newlist2(map);
					}else if(chkExamNumlist.size() > 0) {
						Map<String, String> maps	 = new HashMap<String, String>();
						maps.put("objectIdx", idx);
						maps.put("subjectIdx",subject_idx);
						maps.put("examIdx",mbC2VO.getIdx());
						maps.put("examNum",mbC2VO.getSelectionNum());
						
						maps.put("cP01", radioList.get(0)); 
			       		maps.put("cP02", radioList.get(1)); 
			       		maps.put("cP03", radioList.get(2)); 
				        maps.put("cP04", radioList.get(3));	 	  
				        maps.put("cP05", radioList.get(4));	 	  
				        
				    	maps.put("testDay",sdate);
				    	maps.put("updateUserid",subject_idx);
				    	maps.put("insertUserid",subject_idx);

				    	num= sheetService.update_mb_c2_cogn1_list2(maps);
					}
					
					
				
				}else if(!(exam_num.equals(""))) {
					String examNum1 = "1";
					Map<String, String> chkmap1	 = new HashMap<String, String>();
					chkmap1.put("objectIdx", idx);
					chkmap1.put("examNum",exam_num);
					chkmap1.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn1_list2(chkmap1);
					
					List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
					MbC2VO mbC2VO1  = new MbC2VO();
					for(MbC2VO mbC2VO2 : chkExamNumlist1) {
						 mbC2VO1.setIdx(mbC2VO2.getIdx());
						 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
					}
					if(chkIclist1.size() ==0  ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",mbC2VO1.getIdx());
						map1.put("examNum",mbC2VO1.getSelectionNum());
						
						map1.put("cP01", radioList.get(0)); 
			       		map1.put("cP02", radioList.get(1)); 
			       		map1.put("cP03", radioList.get(2)); 
				        map1.put("cP04", radioList.get(3));	 	  
				        map1.put("cP05", radioList.get(4));	 	  
				        
				    	map1.put("testDay",sdate);
				    	map1.put("updateUserid",subject_idx);
				    	map1.put("insertUserid",subject_idx);

				    	num= sheetService.update_mb_c2_cogn1_list2(map1);
					}else {
						
						
						int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
						int exam_Addnum = exam_num1+1;						
						String exam_Newnum = String.valueOf(exam_Addnum);
						System.out.println(exam_Newnum+"exam_Newnum"); 
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
						Map<String, String> chkmap2	 = new HashMap<String, String>();
						chkmap2.put("objectIdx", idx);
						chkmap2.put("examNum",exam_Newnum);
						chkmap2.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
						MbC2VO mbC2VO3  = new MbC2VO();
						for(MbC2VO mbC2VO2 : chkExamNumlist2) {
							 mbC2VO3.setIdx(mbC2VO2.getIdx());
							 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
						}
						if(chkExamNumlist2.size() > 0 ) {
							Map<String, String> map3	 = new HashMap<String, String>();
							map3.put("objectIdx", idx);
							map3.put("subjectIdx",subject_idx);
							map3.put("examIdx",mbC2VO3.getIdx());
							map3.put("examNum",mbC2VO3.getSelectionNum());
							
							map3.put("cP01", radioList.get(0)); 
				       		map3.put("cP02", radioList.get(1)); 
				       		map3.put("cP03", radioList.get(2)); 
					        map3.put("cP04", radioList.get(3));	 	  
					        map3.put("cP05", radioList.get(4));	 	  
					        
					    	map3.put("testDay",sdate);
					    	map3.put("updateUserid",subject_idx);
					    	map3.put("insertUserid",subject_idx);

					    	num = sheetService.update_mb_c2_cogn1_list2(map3);
							
							
							
						}else if(chkExamNumlist2.size() == 0) {
						Map<String, String> map4	 = new HashMap<String, String>();
						
						map4.put("examNum",exam_Newnum);
						map4.put("objectIdx", idx);
						map4.put("subjectIdx",subject_idx);			
						map4.put("examIdx",exam_idx);
					
						map4.put("cP01", radioList.get(0)); 
			       		map4.put("cP02", radioList.get(1)); 
			       		map4.put("cP03", radioList.get(2)); 
				        map4.put("cP04", radioList.get(3));	 	  
				        map4.put("cP05", radioList.get(4));	 	  
				
						map4.put("testDay",sdate);
						map4.put("insertUserid",subject_idx);
						map4.put("updateUserid",subject_idx);
						num = sheetService.update_mb_c2_cogn1_newlist2(map4);
						}
				
					}
			
			}
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }	
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					String examNum1 = "1";
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					chkmap.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn1_list2(chkmap);
					
					
						Map<String, String> chkmaps	 = new HashMap<String, String>();
						chkmaps.put("objectIdx", idx);
						chkmaps.put("examNum",examNum1);
						chkmaps.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
				
							Map<String, String> map	 = new HashMap<String, String>();
					
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examIdx",exam_idx);
						map.put("examNum",examNum1);
						
						map.put("cP01", radioList.get(0)); 
			       		map.put("cP02", radioList.get(1)); 
			       		map.put("cP03", radioList.get(2)); 
				        map.put("cP04", radioList.get(3));	 	  
				        map.put("cP05", radioList.get(4));	 	  
				        
						map.put("testDay",sdate);
					
						map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_c2_cogn1_editlist2(map);
					}
					
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> chkmaps	 = new HashMap<String, String>();	
					chkmaps.put("objectIdx", idx);
					chkmaps.put("examNum",exam_num);
					chkmaps.put("examIdx",exam_idx);
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",mbC2VO.getIdx());
					map1.put("examNum",mbC2VO.getSelectionNum());
				

					map1.put("cP01", radioList.get(0)); 
		       		map1.put("cP02", radioList.get(1)); 
		       		map1.put("cP03", radioList.get(2)); 
			        map1.put("cP04", radioList.get(3));	 	  
			        map1.put("cP05", radioList.get(4));	 	  
					
			        map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c2_cogn1_editlist2(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	       result.put("code", "OK");
	       mav.addObject("code", "OK");
	       mav.setViewName("/include/sheet/view_cognition1_2");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			
			return mav;
		}	
			
	
	
	
	
	//시간이나 장소에 대한 지남력
	@RequestMapping("/view_cognition1_3.do")
	public ModelAndView view_cognition1_3(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		
		List<MbC2VO> c2_list3 = sheetService.select_mb_c2_cogn1_list3(map);

		 MbC2VO mbC2VO = new MbC2VO();
			for(MbC2VO mbC2VO1 : c2_list3 ) {
				
				System.out.println(mbC2VO1.getSelectionNum()+":::차수");
				mbC2VO.setTestDay(mbC2VO1.getTestDay());
				//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
			}
			String testDay = mbC2VO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cognition1_3");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		mav.addObject("c2_list3", c2_list3);

		return mav;
	}

	
	
	//ajax호출 view_congnition1_3(savecognition1_3일상생활)
	@RequestMapping(value="/view_savecognition1_3.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savecognition1_3(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
		,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
		String exam_numS = request.getParameter("exam_num");
		String exam_idxS = request.getParameter("exam_idx");
		System.out.println(exam_numS+"::exam_numS");
		System.out.println(exam_idxS+"::exam_idxS");
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
	

		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;		

		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
			  Map<String, String> chkmap	 = new HashMap<String, String>();
				String examNum1 = "1";
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",examNum1);
				//chkmap.put("examIdx",exam_idx);
				
				List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
				
				MbC2VO mbC2VO  = new MbC2VO();
				for(MbC2VO mbC2VO1 : chkExamNumlist) {
					 mbC2VO.setIdx(mbC2VO1.getIdx());
					 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
				}
				if(chkExamNumlist.size() == 0) {
					Map<String, String> map	 = new HashMap<String, String>();
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examNum",examNum1);
					
					map.put("cC01", radioList.get(0)); 
		       		map.put("cC02", radioList.get(1)); 
		       		map.put("cC03", radioList.get(2)); 
			        map.put("cC04", radioList.get(3));
			        
					map.put("testDay",sdate);
					map.put("insertUserid",subject_idx);
					map.put("updateUserid",subject_idx);
			
					num = sheetService.update_mb_c2_cogn1_newlist3(map);
				}else if(chkExamNumlist.size() > 0) {
					Map<String, String> maps	 = new HashMap<String, String>();
					maps.put("objectIdx", idx);
					maps.put("subjectIdx",subject_idx);
					maps.put("examIdx",mbC2VO.getIdx());
					maps.put("examNum",mbC2VO.getSelectionNum());
					
					maps.put("cC01", radioList.get(0)); 
		       		maps.put("cC02", radioList.get(1)); 
		       		maps.put("cC03", radioList.get(2)); 
			        maps.put("cC04", radioList.get(3));
			        
			    	maps.put("testDay",sdate);
			    	maps.put("updateUserid",subject_idx);
			    	maps.put("insertUserid",subject_idx);

			    	num= sheetService.update_mb_c2_cogn1_list3(maps);
				}
				
				
			
			}else if(!(exam_num.equals(""))) {
				String examNum1 = "1";
				Map<String, String> chkmap1	 = new HashMap<String, String>();
				chkmap1.put("objectIdx", idx);
				chkmap1.put("examNum",exam_num);
				chkmap1.put("examIdx",exam_idx);
				List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn1_list3(chkmap1);
				
				List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
				MbC2VO mbC2VO1  = new MbC2VO();
				for(MbC2VO mbC2VO2 : chkExamNumlist1) {
					 mbC2VO1.setIdx(mbC2VO2.getIdx());
					 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
				}
				if(chkIclist1.size() ==0  ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",mbC2VO1.getIdx());
					map1.put("examNum",mbC2VO1.getSelectionNum());
					
					map1.put("cC01", radioList.get(0)); 
		       		map1.put("cC02", radioList.get(1)); 
		       		map1.put("cC03", radioList.get(2)); 
			        map1.put("cC04", radioList.get(3));
			        
			    	map1.put("testDay",sdate);
			    	map1.put("updateUserid",subject_idx);
			    	map1.put("insertUserid",subject_idx);

			    	num= sheetService.update_mb_c2_cogn1_list3(map1);
				}else {
					
					
					int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
					int exam_Addnum = exam_num1+1;						
					String exam_Newnum = String.valueOf(exam_Addnum);
					System.out.println(exam_Newnum+"exam_Newnum"); 
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
					Map<String, String> chkmap2	 = new HashMap<String, String>();
					chkmap2.put("objectIdx", idx);
					chkmap2.put("examNum",exam_Newnum);
					chkmap2.put("examIdx",exam_idx);
					List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
					MbC2VO mbC2VO3  = new MbC2VO();
					for(MbC2VO mbC2VO2 : chkExamNumlist2) {
						 mbC2VO3.setIdx(mbC2VO2.getIdx());
						 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
					}
					if(chkExamNumlist2.size() > 0 ) {
						Map<String, String> map3	 = new HashMap<String, String>();
						map3.put("objectIdx", idx);
						map3.put("subjectIdx",subject_idx);
						map3.put("examIdx",mbC2VO3.getIdx());
						map3.put("examNum",mbC2VO3.getSelectionNum());
						
						map3.put("cC01", radioList.get(0)); 
			       		map3.put("cC02", radioList.get(1)); 
			       		map3.put("cC03", radioList.get(2)); 
				        map3.put("cC04", radioList.get(3));
				        
				    	map3.put("testDay",sdate);
				    	map3.put("updateUserid",subject_idx);
				    	map3.put("insertUserid",subject_idx);

				    	num = sheetService.update_mb_c2_cogn1_list3(map3);
						
						
						
					}else if(chkExamNumlist2.size() == 0) {
					Map<String, String> map4	 = new HashMap<String, String>();
					
					map4.put("examNum",exam_Newnum);
					map4.put("objectIdx", idx);
					map4.put("subjectIdx",subject_idx);			
					map4.put("examIdx",exam_idx);
				
					map4.put("cC01", radioList.get(0)); 
		       		map4.put("cC02", radioList.get(1)); 
		       		map4.put("cC03", radioList.get(2)); 
			        map4.put("cC04", radioList.get(3));
			
					map4.put("testDay",sdate);
					map4.put("insertUserid",subject_idx);
					map4.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c2_cogn1_newlist3(map4);
					}
			
				}
		
		}
			   if(num> 0 ) {
				   Map<String, String> tmap	 = new HashMap<String, String>();
				   tmap.put("objectIdx", idx);
				   tmap.put("examId", exam_id);
				   tmap.put("cdbs", cdbs);
				  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
			
				  for(MbSJVO mbSJVO1 : maxNum){
					  	
					 mbSJVO.setExamNum(mbSJVO1.getExamNum());
					 maxNum1 = mbSJVO.getExamNum();
					 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
					 // System.out.println(maxNum1+"::maxnumselction값");
					 
				  }
				  
				  
			   }
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				String examNum1 = "1";
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",examNum1);
				chkmap.put("examIdx",exam_idx);
				List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn1_list3(chkmap);
				
				
					Map<String, String> chkmaps	 = new HashMap<String, String>();
					chkmaps.put("objectIdx", idx);
					chkmaps.put("examNum",examNum1);
					chkmaps.put("examIdx",exam_idx);
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
			
						Map<String, String> map	 = new HashMap<String, String>();
				
					map.put("objectIdx", idx);
					map.put("subjectIdx",subject_idx);
					map.put("examIdx",exam_idx);
					map.put("examNum",examNum1);
					
					map.put("cC01", radioList.get(0)); 
		       		map.put("cC02", radioList.get(1)); 
		       		map.put("cC03", radioList.get(2)); 
			        map.put("cC04", radioList.get(3));
					
					map.put("testDay",sdate);
				
					map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_c2_cogn1_editlist3(map);
				}
				
				
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> chkmaps	 = new HashMap<String, String>();	
				chkmaps.put("objectIdx", idx);
				chkmaps.put("examNum",exam_num);
				chkmaps.put("examIdx",exam_idx);
				List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
				MbC2VO mbC2VO  = new MbC2VO();
				for(MbC2VO mbC2VO1 : chkExamNumlist) {
					 mbC2VO.setIdx(mbC2VO1.getIdx());
					 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
				}
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",mbC2VO.getIdx());
				map1.put("examNum",mbC2VO.getSelectionNum());
			

				map1.put("cC01", radioList.get(0)); 
	       		map1.put("cC02", radioList.get(1)); 
	       		map1.put("cC03", radioList.get(2)); 
		        map1.put("cC04", radioList.get(3));
				
		        map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_c2_cogn1_editlist3(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_cognition1_3");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
		mav.addObject("message", "신규에 등록에 성공 하였습니다.");
		mav.addObject("objIdx", idx);
		mav.addObject("exam_num", maxNum1);
		mav.addObject("result","new");
		}else if(whichChk.equals("edit")) {
		mav.addObject("result","edit");
		mav.addObject("message", "수정을 성공 하였습니다.");
		//mav.addObject("dataList", ja_data_list);
		}		   
		
		return mav;
	}	
	
	
	//ajax호출 view_congnition1_4(savecognition1_4일상생활)
			@RequestMapping(value="/view_savecognition1_4.do", method=RequestMethod.POST)
			@ResponseBody
			public ModelAndView view_savecognition1_4(@RequestParam(value="radioArr[]") List<String> radioList,
					@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
				,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
				,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String subject_idx = sessionMember.getIdx(); // login user idx
				String userId = sessionMember.getId();
				String Name = sessionMember.getName();
			   //String selectionNum = request.getParameter("selection_num");
				System.out.println("selectionNum::"+exam_num);
				System.out.println("subject_idx::::"+subject_idx);
				
				String object_idx = request.getParameter("object_idx");
				String exam_numS = request.getParameter("exam_num");
				String exam_idxS = request.getParameter("exam_idx");
				
				
				System.out.println(exam_numS+"::exam_numS");
				System.out.println(exam_idxS+"::exam_idxS");
				System.out.println("잘넘어왔습니당");
				System.out.println(object_idx+":object_idx");
				System.out.println("examNum::"+exam_num);
				System.out.println("examIdx::"+exam_idx);
				System.out.println(idx+"::objectidx값 받아왔다 ");
				System.out.println("선택버튼:"+whichChk);
				System.out.println(sdate+"::sdate값 받아왔다");
				System.out.println(radioList.size()+"::radioList사이즈크기");
			

					
				MbSJVO mbSJVO = new MbSJVO();
				String maxNum1 = null;
			
				
				try {	
				int num = 0;
			
				if(whichChk.equals("new")) {
					if(exam_num.equals("")) {
					  Map<String, String> chkmap	 = new HashMap<String, String>();
						String examNum1 = "1";
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",examNum1);
						//chkmap.put("examIdx",exam_idx);
						
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
						
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						if(chkExamNumlist.size() == 0) {
							Map<String, String> map	 = new HashMap<String, String>();
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examNum",examNum1);
							
							map.put("cAd01", radioList.get(0)); 
				       		map.put("cAd02", radioList.get(1)); 
				       		map.put("cAd03", radioList.get(2)); 
					        map.put("cAd04", radioList.get(3));
					        
							map.put("testDay",sdate);
							map.put("insertUserid",subject_idx);
							map.put("updateUserid",subject_idx);
					
							num = sheetService.update_mb_c2_cogn1_newlist4(map);
						}else if(chkExamNumlist.size() > 0) {
							Map<String, String> maps	 = new HashMap<String, String>();
							maps.put("objectIdx", idx);
							maps.put("subjectIdx",subject_idx);
							maps.put("examIdx",mbC2VO.getIdx());
							maps.put("examNum",mbC2VO.getSelectionNum());
							
							maps.put("cAd01", radioList.get(0)); 
				       		maps.put("cAd02", radioList.get(1)); 
				       		maps.put("cAd03", radioList.get(2)); 
					        maps.put("cAd04", radioList.get(3));
					        
					    	maps.put("testDay",sdate);
					    	maps.put("updateUserid",subject_idx);
					    	maps.put("insertUserid",subject_idx);

					    	num= sheetService.update_mb_c2_cogn1_list4(maps);
						}
						
						
					
					}else if(!(exam_num.equals(""))) {
						String examNum1 = "1";
						Map<String, String> chkmap1	 = new HashMap<String, String>();
						chkmap1.put("objectIdx", idx);
						chkmap1.put("examNum",exam_num);
						chkmap1.put("examIdx",exam_idx);
						List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn1_list4(chkmap1);
						
						List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
						MbC2VO mbC2VO1  = new MbC2VO();
						for(MbC2VO mbC2VO2 : chkExamNumlist1) {
							 mbC2VO1.setIdx(mbC2VO2.getIdx());
							 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
						}
						if(chkIclist1.size() ==0  ) {
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("subjectIdx",subject_idx);
							map1.put("examIdx",mbC2VO1.getIdx());
							map1.put("examNum",mbC2VO1.getSelectionNum());
							
							map1.put("cAd01", radioList.get(0)); 
				       		map1.put("cAd02", radioList.get(1)); 
				       		map1.put("cAd03", radioList.get(2)); 
					        map1.put("cAd04", radioList.get(3));
					        
					    	map1.put("testDay",sdate);
					    	map1.put("updateUserid",subject_idx);
					    	map1.put("insertUserid",subject_idx);

					    	num= sheetService.update_mb_c2_cogn1_list4(map1);
						}else {
							
							
							int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
							int exam_Addnum = exam_num1+1;						
							String exam_Newnum = String.valueOf(exam_Addnum);
							System.out.println(exam_Newnum+"exam_Newnum"); 
							System.out.println("examnNum(int)::"+exam_num1);
							System.out.println("exam_Addnum(int)::"+exam_Addnum);
							System.out.println("exam_Newnum(String)::"+exam_Newnum);
							Map<String, String> chkmap2	 = new HashMap<String, String>();
							chkmap2.put("objectIdx", idx);
							chkmap2.put("examNum",exam_Newnum);
							chkmap2.put("examIdx",exam_idx);
							List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
							MbC2VO mbC2VO3  = new MbC2VO();
							for(MbC2VO mbC2VO2 : chkExamNumlist2) {
								 mbC2VO3.setIdx(mbC2VO2.getIdx());
								 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
							}
							if(chkExamNumlist2.size() > 0 ) {
								Map<String, String> map3	 = new HashMap<String, String>();
								map3.put("objectIdx", idx);
								map3.put("subjectIdx",subject_idx);
								map3.put("examIdx",mbC2VO3.getIdx());
								map3.put("examNum",mbC2VO3.getSelectionNum());
								
								map3.put("cAd01", radioList.get(0)); 
					       		map3.put("cAd02", radioList.get(1)); 
					       		map3.put("cAd03", radioList.get(2)); 
						        map3.put("cAd04", radioList.get(3));
						        
						    	map3.put("testDay",sdate);
						    	map3.put("updateUserid",subject_idx);
						    	map3.put("insertUserid",subject_idx);

						    	num = sheetService.update_mb_c2_cogn1_list4(map3);
								
								
								
							}else if(chkExamNumlist2.size() == 0) {
							Map<String, String> map4	 = new HashMap<String, String>();
							
							map4.put("examNum",exam_Newnum);
							map4.put("objectIdx", idx);
							map4.put("subjectIdx",subject_idx);			
							map4.put("examIdx",exam_idx);
						
							map4.put("cAd01", radioList.get(0)); 
				       		map4.put("cAd02", radioList.get(1)); 
				       		map4.put("cAd03", radioList.get(2)); 
					        map4.put("cAd04", radioList.get(3));
					
							map4.put("testDay",sdate);
							map4.put("insertUserid",subject_idx);
							map4.put("updateUserid",subject_idx);
							num = sheetService.update_mb_c2_cogn1_newlist4(map4);
							}
					
						}
				
				}
					   if(num> 0 ) {
						   Map<String, String> tmap	 = new HashMap<String, String>();
						   tmap.put("objectIdx", idx);
						   tmap.put("examId", exam_id);
						   tmap.put("cdbs", cdbs);
						  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
					
						  for(MbSJVO mbSJVO1 : maxNum){
							  	
							 mbSJVO.setExamNum(mbSJVO1.getExamNum());
							 maxNum1 = mbSJVO.getExamNum();
							 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
							 // System.out.println(maxNum1+"::maxnumselction값");
							 
						  }
						  
						  
					   }
				}else if(whichChk.equals("edit")) {
					if(exam_num.equals("")) {
						String examNum1 = "1";
						Map<String, String> chkmap	 = new HashMap<String, String>();
						chkmap.put("objectIdx", idx);
						chkmap.put("examNum",examNum1);
						chkmap.put("examIdx",exam_idx);
						List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn1_list4(chkmap);
						
						
							Map<String, String> chkmaps	 = new HashMap<String, String>();
							chkmaps.put("objectIdx", idx);
							chkmaps.put("examNum",examNum1);
							chkmaps.put("examIdx",exam_idx);
							List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
							MbC2VO mbC2VO  = new MbC2VO();
							for(MbC2VO mbC2VO1 : chkExamNumlist) {
								 mbC2VO.setIdx(mbC2VO1.getIdx());
								 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
							}
							if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
					
								Map<String, String> map	 = new HashMap<String, String>();
						
							map.put("objectIdx", idx);
							map.put("subjectIdx",subject_idx);
							map.put("examIdx",exam_idx);
							map.put("examNum",examNum1);
							
							map.put("cAd01", radioList.get(0)); 
				       		map.put("cAd02", radioList.get(1)); 
				       		map.put("cAd03", radioList.get(2)); 
					        map.put("cAd04", radioList.get(3));
							
							map.put("testDay",sdate);
						
							map.put("updateUserid",subject_idx);
						
						num = sheetService.update_mb_c2_cogn1_editlist4(map);
						}
						
						
					}else if(!(exam_num.equals(""))) {	
						Map<String, String> chkmaps	 = new HashMap<String, String>();	
						chkmaps.put("objectIdx", idx);
						chkmaps.put("examNum",exam_num);
						chkmaps.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",mbC2VO.getIdx());
						map1.put("examNum",mbC2VO.getSelectionNum());
					

						map1.put("cAd01", radioList.get(0)); 
			       		map1.put("cAd02", radioList.get(1)); 
			       		map1.put("cAd03", radioList.get(2)); 
				        map1.put("cAd04", radioList.get(3));
						
				        map1.put("testDay",sdate);
					
						map1.put("updateUserid",subject_idx);
						num = sheetService.update_mb_c2_cogn1_editlist4(map1);
					}
					
					
					
				}

				
				System.out.println(num +":사이즈");
				
				
				}catch(NumberFormatException e) {
					exam_num ="1";
					System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
				
			    //리턴값
		        Map<String, Object> result = new HashMap<String, Object>();
				ModelAndView mav = new ModelAndView();
		        //성공했다고 처리     
				result.put("message", "등록에 성공 하였습니다.");
		       mav.setViewName("/include/sheet/view_cognition1_4");
				//mav.addObject("retVal", retVal);
				mav.addObject("code", "OK");
				if(whichChk.equals("new")) {
					
					mav.addObject("message", "신규에 등록에 성공 하였습니다.");
					mav.addObject("objIdx", idx);
					mav.addObject("exam_num", maxNum1);
					mav.addObject("result","new");
					}else if(whichChk.equals("edit")) {
					mav.addObject("result","edit");
					mav.addObject("message", "수정을 성공 하였습니다.");
					//mav.addObject("dataList", ja_data_list);
					}		   
				
				
				
				
				return mav;
			}	
			
	
	//일상 생활동작 AD
	@RequestMapping("/view_cognition1_4.do")
	public ModelAndView view_cognition1_4(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		
		
		List<MbC2VO> c2_list4 = sheetService.select_mb_c2_cogn1_list4(map);
		ModelAndView mav = new ModelAndView();
		 MbC2VO mbC2VO = new MbC2VO();
		for(MbC2VO mbC2VO1 : c2_list4 ) {
			
			System.out.println(mbC2VO1.getSelectionNum()+":::차수");
			mbC2VO.setTestDay(mbC2VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		
		String testDay = mbC2VO.getTestDay();
		mav.setViewName("/include/sheet/view_cognition1_4");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		mav.addObject("c2_list4", c2_list4);

		return mav;
	}
	
	//직업활동 
	@RequestMapping("/view_cognition2_1.do")
	public ModelAndView view_cognition1_5(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		System.out.println(exam_id+"이것은 내가만든 파라미터 eaxamid값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		List<MbC2VO> c2_list1 = sheetService.select_mb_c2_cogn2_list1(map);

		
		ModelAndView mav = new ModelAndView();
		 MbC2VO mbC2VO = new MbC2VO();
		for(MbC2VO mbC2VO1 : c2_list1 ) {
			
			System.out.println(mbC2VO1.getSelectionNum()+":::차수");
			mbC2VO.setTestDay(mbC2VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		
		String testDay = mbC2VO.getTestDay();
	
		mav.setViewName("/include/sheet/view_cognition2_1");
		mav.addObject("idx", object_idx);
		mav.addObject("testDay",testDay);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("success", true);	
		mav.addObject("c2_list1", c2_list1);

		return mav;
	}
	
	//ajax호출 view_congnition2_1(savecognition2_1직업활동)
		@RequestMapping(value="/view_savecognition2_1.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savecognition2_1(@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
			String exam_numS = request.getParameter("exam_num");
			String exam_idxS = request.getParameter("exam_idx");
			System.out.println(exam_numS+"::exam_numS");
			System.out.println(exam_idxS+"::exam_idxS");
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
		

				
			
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
			
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
				  Map<String, String> chkmap	 = new HashMap<String, String>();
					String examNum1 = "1";
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					//chkmap.put("examIdx",exam_idx);
					
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
					
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					if(chkExamNumlist.size() == 0) {
						Map<String, String> map	 = new HashMap<String, String>();
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examNum",examNum1);
						
						map.put("cS01", radioList.get(0)); 
			       		map.put("cS02", radioList.get(1)); 
			       		map.put("cS03", radioList.get(2)); 
				        map.put("cS04", radioList.get(3));
				        
						map.put("testDay",sdate);
						map.put("insertUserid",subject_idx);
						map.put("updateUserid",subject_idx);
				
						num = sheetService.update_mb_c2_cogn2_newlist1(map);
					}else if(chkExamNumlist.size() > 0) {
						Map<String, String> maps	 = new HashMap<String, String>();
						maps.put("objectIdx", idx);
						maps.put("subjectIdx",subject_idx);
						maps.put("examIdx",mbC2VO.getIdx());
						maps.put("examNum",mbC2VO.getSelectionNum());
						
						maps.put("cS01", radioList.get(0)); 
			       		maps.put("cS02", radioList.get(1)); 
			       		maps.put("cS03", radioList.get(2)); 
				        maps.put("cS04", radioList.get(3));
				        
				    	maps.put("testDay",sdate);
				    	maps.put("updateUserid",subject_idx);
				    	maps.put("insertUserid",subject_idx);

				    	num= sheetService.update_mb_c2_cogn2_list1(maps);
					}
					
					
				
				}else if(!(exam_num.equals(""))) {
					String examNum1 = "1";
					Map<String, String> chkmap1	 = new HashMap<String, String>();
					chkmap1.put("objectIdx", idx);
					chkmap1.put("examNum",exam_num);
					chkmap1.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn2_list1(chkmap1);
					
					List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
					MbC2VO mbC2VO1  = new MbC2VO();
					for(MbC2VO mbC2VO2 : chkExamNumlist1) {
						 mbC2VO1.setIdx(mbC2VO2.getIdx());
						 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
					}
					if(chkIclist1.size() ==0  ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",mbC2VO1.getIdx());
						map1.put("examNum",mbC2VO1.getSelectionNum());
						
						map1.put("cS01", radioList.get(0)); 
			       		map1.put("cS02", radioList.get(1)); 
			       		map1.put("cS03", radioList.get(2)); 
				        map1.put("cS04", radioList.get(3));
				        
				    	map1.put("testDay",sdate);
				    	map1.put("updateUserid",subject_idx);
				    	map1.put("insertUserid",subject_idx);

				    	num= sheetService.update_mb_c2_cogn2_list1(map1);
					}else {
						
						
						int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
						int exam_Addnum = exam_num1+1;						
						String exam_Newnum = String.valueOf(exam_Addnum);
						System.out.println(exam_Newnum+"exam_Newnum"); 
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
						Map<String, String> chkmap2	 = new HashMap<String, String>();
						chkmap2.put("objectIdx", idx);
						chkmap2.put("examNum",exam_Newnum);
						chkmap2.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
						MbC2VO mbC2VO3  = new MbC2VO();
						for(MbC2VO mbC2VO2 : chkExamNumlist2) {
							 mbC2VO3.setIdx(mbC2VO2.getIdx());
							 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
						}
						if(chkExamNumlist2.size() > 0 ) {
							Map<String, String> map3	 = new HashMap<String, String>();
							map3.put("objectIdx", idx);
							map3.put("subjectIdx",subject_idx);
							map3.put("examIdx",mbC2VO3.getIdx());
							map3.put("examNum",mbC2VO3.getSelectionNum());
							
							map3.put("cS01", radioList.get(0)); 
				       		map3.put("cS02", radioList.get(1)); 
				       		map3.put("cS03", radioList.get(2)); 
					        map3.put("cS04", radioList.get(3));
					        
					    	map3.put("testDay",sdate);
					    	map3.put("updateUserid",subject_idx);
					    	map3.put("insertUserid",subject_idx);

					    	num = sheetService.update_mb_c2_cogn2_list1(map3);
							
							
							
						}else if(chkExamNumlist2.size() == 0) {
						Map<String, String> map4	 = new HashMap<String, String>();
						
						map4.put("examNum",exam_Newnum);
						map4.put("objectIdx", idx);
						map4.put("subjectIdx",subject_idx);			
						map4.put("examIdx",exam_idx);
					
						map4.put("cS01", radioList.get(0)); 
			       		map4.put("cS02", radioList.get(1)); 
			       		map4.put("cS03", radioList.get(2)); 
				        map4.put("cS04", radioList.get(3));
				
						map4.put("testDay",sdate);
						map4.put("insertUserid",subject_idx);
						map4.put("updateUserid",subject_idx);
						num = sheetService.update_mb_c2_cogn2_newlist1(map4);
						}
				
					}
			
			}
				   if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					String examNum1 = "1";
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					chkmap.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn2_list1(chkmap);
					
					
						Map<String, String> chkmaps	 = new HashMap<String, String>();
						chkmaps.put("objectIdx", idx);
						chkmaps.put("examNum",examNum1);
						chkmaps.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
				
							Map<String, String> map	 = new HashMap<String, String>();
					
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examIdx",exam_idx);
						map.put("examNum",examNum1);
						
						map.put("cS01", radioList.get(0)); 
			       		map.put("cS02", radioList.get(1)); 
			       		map.put("cS03", radioList.get(2)); 
				        map.put("cS04", radioList.get(3));
						
						map.put("testDay",sdate);
					
						map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_c2_cogn2_editlist1(map);
					}
					
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> chkmaps	 = new HashMap<String, String>();	
					chkmaps.put("objectIdx", idx);
					chkmaps.put("examNum",exam_num);
					chkmaps.put("examIdx",exam_idx);
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",mbC2VO.getIdx());
					map1.put("examNum",mbC2VO.getSelectionNum());
				

					map1.put("cS01", radioList.get(0)); 
		       		map1.put("cS02", radioList.get(1)); 
		       		map1.put("cS03", radioList.get(2)); 
			        map1.put("cS04", radioList.get(3));
					
			        map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c2_cogn2_editlist1(map1);
				}
				
				
				
			}

			
			System.out.println(num +":사이즈");
			
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_cognition2_1");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
				mav.addObject("message", "신규에 등록에 성공 하였습니다.");
				mav.addObject("objIdx", idx);
				mav.addObject("exam_num", maxNum1);
				mav.addObject("result","new");
				}else if(whichChk.equals("edit")) {
				mav.addObject("result","edit");
				mav.addObject("message", "수정을 성공 하였습니다.");
				//mav.addObject("dataList", ja_data_list);
				}		   
			
			
			return mav;
		}	
		
	
	//문제해결
	@RequestMapping("/view_cognition2_2.do")
	public ModelAndView view_cognition2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
		List<MbC2VO> c2_list2 = sheetService.select_mb_c2_cogn2_list2(map);
	//	List<MbC3VO> c3_list = sheetService.select_mb_c3_list(map);
		 MbC2VO mbC2VO = new MbC2VO();
		for(MbC2VO mbC2VO1 : c2_list2 ) {
			
			System.out.println(mbC2VO1.getSelectionNum()+":::차수");
			mbC2VO.setTestDay(mbC2VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbC2VO.getTestDay();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cognition2_2");
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		mav.addObject("c2_list2", c2_list2);
	//	mav.addObject("c3_list", c3_list);

		return mav;
	}
	
	
	
	
	//ajax호출 view_congnition2(savecognition2문제해결)
		@RequestMapping(value="/view_savecognition2_2.do", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView view_savecognition2(@RequestParam(value="textArr[]") List<String> textList,@RequestParam(value="radioArr[]") List<String> radioList,
				@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
			,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
			,@RequestParam(value="cdbs")String cdbs,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
			MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
			String subject_idx = sessionMember.getIdx(); // login user idx
			String userId = sessionMember.getId();
			String Name = sessionMember.getName();
		   //String selectionNum = request.getParameter("selection_num");
			System.out.println("selectionNum::"+exam_num);
			System.out.println("subject_idx::::"+subject_idx);
			
			String object_idx = request.getParameter("object_idx");
		//	String exam_num = request.getParameter("exam_num");
		//	System.out.println(exam_num+"::exam_num");
		
			System.out.println("잘넘어왔습니당");
			System.out.println(object_idx+":object_idx");
			System.out.println("examNum::"+exam_num);
			System.out.println("examIdx::"+exam_idx);
			System.out.println(idx+"::objectidx값 받아왔다 ");
			System.out.println("선택버튼:"+whichChk);
			System.out.println(sdate+"::sdate값 받아왔다");
			System.out.println(radioList.size()+"::radioList사이즈크기");
		
			MbSJVO mbSJVO = new MbSJVO();
			String maxNum1 = null;
				
			try {	
			int num = 0;
		
			if(whichChk.equals("new")) {
				if(exam_num.equals("")) {
				  Map<String, String> chkmap	 = new HashMap<String, String>();
					String examNum1 = "1";
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					//chkmap.put("examIdx",exam_idx);
					
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmap);
					
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					if(chkExamNumlist.size() == 0) {
						Map<String, String> map	 = new HashMap<String, String>();
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examNum",examNum1);
						
						map.put("cJ01", radioList.get(0)); 
			       		map.put("cJ02", radioList.get(1)); 
			       		map.put("cJ03", radioList.get(2)); 
				        map.put("cJ04", radioList.get(3));
				        map.put("cJ05", radioList.get(4));
				        map.put("cJ06", radioList.get(5));
				        map.put("cJ07", radioList.get(6));
				        map.put("cJ08", radioList.get(7));
				        map.put("cJ09", radioList.get(8));
				        map.put("cJ10", radioList.get(9));
				        map.put("cZ01", radioList.get(10));

			       		map.put("cZ02", textList.get(0));
				        
						map.put("testDay",sdate);
						map.put("insertUserid",subject_idx);
						map.put("updateUserid",subject_idx);
				
						num = sheetService.update_mb_c2_cogn2_newlist2(map);
					}else if(chkExamNumlist.size() > 0) {
						Map<String, String> maps	 = new HashMap<String, String>();
						maps.put("objectIdx", idx);
						maps.put("subjectIdx",subject_idx);
						maps.put("examIdx",mbC2VO.getIdx());
						maps.put("examNum",mbC2VO.getSelectionNum());
						
						maps.put("cJ01", radioList.get(0)); 
			       		maps.put("cJ02", radioList.get(1)); 
			       		maps.put("cJ03", radioList.get(2)); 
				        maps.put("cJ04", radioList.get(3));
				        maps.put("cJ05", radioList.get(4));
				        maps.put("cJ06", radioList.get(5));
				        maps.put("cJ07", radioList.get(6));
				        maps.put("cJ08", radioList.get(7));
				        maps.put("cJ09", radioList.get(8));
				        maps.put("cJ10", radioList.get(9));
				        maps.put("cZ01", radioList.get(10));

			       		maps.put("cZ02", textList.get(0));
				        
				    	maps.put("testDay",sdate);
				    	maps.put("insertUserid",subject_idx);
				    	maps.put("updateUserid",subject_idx);
				    	
				    	num=	 sheetService.update_mb_c2_cogn2_list2(maps);
					}
					
					
				
				}else if(!(exam_num.equals(""))) {
					String examNum1 = "1";
					Map<String, String> chkmap1	 = new HashMap<String, String>();
					chkmap1.put("objectIdx", idx);
					chkmap1.put("examNum",exam_num);
					chkmap1.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist1 =  sheetService.select_mb_c2_cogn2_list2(chkmap1);
					
					List<MbC2VO> chkExamNumlist1 = sheetService.select_mb_c2_chkExamNumlist(chkmap1);
					MbC2VO mbC2VO1  = new MbC2VO();
					for(MbC2VO mbC2VO2 : chkExamNumlist1) {
						 mbC2VO1.setIdx(mbC2VO2.getIdx());
						 mbC2VO1.setSelectionNum(mbC2VO2.getSelectionNum());
					}
					if(chkIclist1.size() ==0  ) {
						Map<String, String> map1	 = new HashMap<String, String>();
						map1.put("objectIdx", idx);
						map1.put("subjectIdx",subject_idx);
						map1.put("examIdx",mbC2VO1.getIdx());
						map1.put("examNum",mbC2VO1.getSelectionNum());
						
						map1.put("cJ01", radioList.get(0)); 
			       		map1.put("cJ02", radioList.get(1)); 
			       		map1.put("cJ03", radioList.get(2)); 
				        map1.put("cJ04", radioList.get(3));
				        map1.put("cJ05", radioList.get(4));
				        map1.put("cJ06", radioList.get(5));
				        map1.put("cJ07", radioList.get(6));
				        map1.put("cJ08", radioList.get(7));
				        map1.put("cJ09", radioList.get(8));
				        map1.put("cJ10", radioList.get(9));
				        map1.put("cZ01", radioList.get(10));

			       		map1.put("cZ02", textList.get(0));
				        
				    	map1.put("testDay",sdate);
				    	map1.put("updateUserid",subject_idx);
				    	map1.put("insertUserid",subject_idx);

				    	num=	 sheetService.update_mb_c2_cogn2_list2(map1);
					}else {
						
						
						int exam_num1 =  Integer.parseInt(mbC2VO1.getSelectionNum());
						int exam_Addnum = exam_num1+1;						
						String exam_Newnum = String.valueOf(exam_Addnum);
						System.out.println(exam_Newnum+"exam_Newnum"); 
						System.out.println("examnNum(int)::"+exam_num1);
						System.out.println("exam_Addnum(int)::"+exam_Addnum);
						System.out.println("exam_Newnum(String)::"+exam_Newnum);
						Map<String, String> chkmap2	 = new HashMap<String, String>();
						chkmap2.put("objectIdx", idx);
						chkmap2.put("examNum",exam_Newnum);
						chkmap2.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist2 = sheetService.select_mb_c2_chkExamNumlist(chkmap2);
						MbC2VO mbC2VO3  = new MbC2VO();
						for(MbC2VO mbC2VO2 : chkExamNumlist2) {
							 mbC2VO3.setIdx(mbC2VO2.getIdx());
							 mbC2VO3.setSelectionNum(mbC2VO2.getSelectionNum());
						}
						if(chkExamNumlist2.size() > 0 ) {
							Map<String, String> map3	 = new HashMap<String, String>();
							map3.put("objectIdx", idx);
							map3.put("subjectIdx",subject_idx);
							map3.put("examIdx",mbC2VO3.getIdx());
							map3.put("examNum",mbC2VO3.getSelectionNum());
							
							map3.put("cJ01", radioList.get(0)); 
				       		map3.put("cJ02", radioList.get(1)); 
				       		map3.put("cJ03", radioList.get(2)); 
					        map3.put("cJ04", radioList.get(3));
					        map3.put("cJ05", radioList.get(4));
					        map3.put("cJ06", radioList.get(5));
					        map3.put("cJ07", radioList.get(6));
					        map3.put("cJ08", radioList.get(7));
					        map3.put("cJ09", radioList.get(8));
					        map3.put("cJ10", radioList.get(9));
					        map3.put("cZ01", radioList.get(10));

				       		map3.put("cZ02", textList.get(0));
					        
					    	map3.put("testDay",sdate);
					    	map3.put("updateUserid",subject_idx);
					    	map3.put("insertUserid",subject_idx);
					    	num = sheetService.update_mb_c2_cogn2_list2(map3);
							
							
							
						}else if(chkExamNumlist2.size() == 0) {
						Map<String, String> map4	 = new HashMap<String, String>();
						
						map4.put("examNum",exam_Newnum);
						map4.put("objectIdx", idx);
						map4.put("subjectIdx",subject_idx);			
						map4.put("examIdx",exam_idx);
					
						map4.put("cJ01", radioList.get(0)); 
			       		map4.put("cJ02", radioList.get(1)); 
			       		map4.put("cJ03", radioList.get(2)); 
				        map4.put("cJ04", radioList.get(3));
				        map4.put("cJ05", radioList.get(4));
				        map4.put("cJ06", radioList.get(5));
				        map4.put("cJ07", radioList.get(6));
				        map4.put("cJ08", radioList.get(7));
				        map4.put("cJ09", radioList.get(8));
				        map4.put("cJ10", radioList.get(9));
				        map4.put("cZ01", radioList.get(10));

			       		map4.put("cZ02", textList.get(0));
				
						map4.put("testDay",sdate);
						map4.put("insertUserid",subject_idx);
						map4.put("updateUserid",subject_idx);
						num = sheetService.update_mb_c2_cogn2_newlist2(map4);
						}
				
					}
			
			}
				  if(num> 0 ) {
					   Map<String, String> tmap	 = new HashMap<String, String>();
					   tmap.put("objectIdx", idx);
					   tmap.put("examId", exam_id);
					   tmap.put("cdbs", cdbs);
					  List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);
				
					  for(MbSJVO mbSJVO1 : maxNum){
						  	
						 mbSJVO.setExamNum(mbSJVO1.getExamNum());
						 maxNum1 = mbSJVO.getExamNum();
						 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
						 // System.out.println(maxNum1+"::maxnumselction값");
						 
					  }
					  
					  
				   }
				   
			}else if(whichChk.equals("edit")) {
				if(exam_num.equals("")) {
					String examNum1 = "1";
					Map<String, String> chkmap	 = new HashMap<String, String>();
					chkmap.put("objectIdx", idx);
					chkmap.put("examNum",examNum1);
					chkmap.put("examIdx",exam_idx);
					List<MbC2VO> chkIclist =  sheetService.select_mb_c2_cogn2_list2(chkmap);
					
					
						Map<String, String> chkmaps	 = new HashMap<String, String>();
						chkmaps.put("objectIdx", idx);
						chkmaps.put("examNum",examNum1);
						chkmaps.put("examIdx",exam_idx);
						List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
						MbC2VO mbC2VO  = new MbC2VO();
						for(MbC2VO mbC2VO1 : chkExamNumlist) {
							 mbC2VO.setIdx(mbC2VO1.getIdx());
							 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
						}
						if(chkIclist.size() == 0 && chkExamNumlist.size() ==0) {
				
							Map<String, String> map	 = new HashMap<String, String>();
					
						map.put("objectIdx", idx);
						map.put("subjectIdx",subject_idx);
						map.put("examIdx",exam_idx);
						map.put("examNum",examNum1);
						
						map.put("cJ01", radioList.get(0)); 
			       		map.put("cJ02", radioList.get(1)); 
			       		map.put("cJ03", radioList.get(2)); 
				        map.put("cJ04", radioList.get(3));
				        map.put("cJ05", radioList.get(4));
				        map.put("cJ06", radioList.get(5));
				        map.put("cJ07", radioList.get(6));
				        map.put("cJ08", radioList.get(7));
				        map.put("cJ09", radioList.get(8));
				        map.put("cJ10", radioList.get(9));
				        map.put("cZ01", radioList.get(10));

			       		map.put("cZ02", textList.get(0));
						
						map.put("testDay",sdate);
					
						map.put("updateUserid",subject_idx);
					
					num = sheetService.update_mb_c2_cogn2_editlist2(map);
					}
					
					
				}else if(!(exam_num.equals(""))) {	
					Map<String, String> chkmaps	 = new HashMap<String, String>();	
					chkmaps.put("objectIdx", idx);
					chkmaps.put("examNum",exam_num);
					chkmaps.put("examIdx",exam_idx);
					List<MbC2VO> chkExamNumlist = sheetService.select_mb_c2_chkExamNumlist(chkmaps);
					MbC2VO mbC2VO  = new MbC2VO();
					for(MbC2VO mbC2VO1 : chkExamNumlist) {
						 mbC2VO.setIdx(mbC2VO1.getIdx());
						 mbC2VO.setSelectionNum(mbC2VO1.getSelectionNum());
					}
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);
					map1.put("examIdx",mbC2VO.getIdx());
					map1.put("examNum",mbC2VO.getSelectionNum());
				

					map1.put("cJ01", radioList.get(0)); 
		       		map1.put("cJ02", radioList.get(1)); 
		       		map1.put("cJ03", radioList.get(2)); 
			        map1.put("cJ04", radioList.get(3));
			        map1.put("cJ05", radioList.get(4));
			        map1.put("cJ06", radioList.get(5));
			        map1.put("cJ07", radioList.get(6));
			        map1.put("cJ08", radioList.get(7));
			        map1.put("cJ09", radioList.get(8));
			        map1.put("cJ10", radioList.get(9));
			        map1.put("cZ01", radioList.get(10));

		       		map1.put("cZ02", textList.get(0));
					
			        map1.put("testDay",sdate);
				
					map1.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c2_cogn2_editlist2(map1);
				}
				
				
				
			}

			
			

			
			System.out.println(num +":사이즈");
			
			}catch(NumberFormatException e) {
				exam_num ="1";
				System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
		    //리턴값
	        Map<String, Object> result = new HashMap<String, Object>();
			ModelAndView mav = new ModelAndView();
	        //성공했다고 처리
	       result.put("code", "OK");
	       result.put("message", "등록에 성공 하였습니다.");
	       mav.setViewName("/include/sheet/view_cognition2_2");
			//mav.addObject("retVal", retVal);
			mav.addObject("code", "OK");
			if(whichChk.equals("new")) {
				
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}		 
			
			return mav;
		}	
	
	
	//판단력과 문제해결 mb_c3
	@RequestMapping("/view_progression.do")
	public ModelAndView view_progression(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");
		String exam_idx = request.getParameter("exam_idx");
		String exam_id = request.getParameter("exam_id");
		String action = request.getParameter("action");
		System.out.println(object_idx+"이것은 내가만든 파라미터 objectidx값");
		System.out.println(exam_num+"이것은 내가만든 파라미터 차수넘버값");
		System.out.println(exam_idx+"이것은 내가만든 파라미터 시퀀스idx값");
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		map.put("examIdx", exam_idx);
	//	List<MbC2VO> c2_list = sheetService.select_mb_c2_cogn2_list(map);
		List<MbC3VO> c3_list = sheetService.select_mb_c3_list(map);

		MbC3VO mbC3VO = new MbC3VO();
		
		for(MbC3VO mbC3VO1 : c3_list ) {
			
			System.out.println(mbC3VO1.getSelectionNum()+":::차수");
			mbC3VO.setTestDay(mbC3VO1.getTestDay());
			//mbSmVO.setIcFdropDate(mbSmVO1.getIcFdropDate());
		}
		String testDay = mbC3VO.getTestDay();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_progression");
		mav.addObject("success", true);
		mav.addObject("idx", object_idx);
		mav.addObject("action", action);
		mav.addObject("exam_id", exam_id);
		mav.addObject("testDay",testDay);
		mav.addObject("success", true);	
		//	mav.addObject("c2_list", c2_list);
		mav.addObject("c3_list", c3_list);

		return mav;
	}
	
	
	//ajax호출 view_progression(progression)
	@RequestMapping(value="/view_saveprogression.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView view_savepreogression(@RequestParam(value="radioArr[]") List<String> radioList,
			@RequestParam(value="textArr[]") List<String> textList,@RequestParam(value="idx") String idx,@RequestParam(value="sdate")String sdate
		,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="cdbs")String cdbs,@RequestParam(value="exam_id")String exam_id,@RequestParam(value="exam_num")String exam_num, @RequestParam(value="exam_idx")String exam_idx
		,HttpServletRequest request,HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
	   //String selectionNum = request.getParameter("selection_num");
		System.out.println("selectionNum::"+exam_num);
		System.out.println("subject_idx::::"+subject_idx);
		
		String object_idx = request.getParameter("object_idx");
	//	String exam_num = request.getParameter("exam_num");
	//	System.out.println(exam_num+"::exam_num");
	
		System.out.println("잘넘어왔습니당");
		System.out.println(object_idx+":object_idx");
		System.out.println("examNum::"+exam_num);
		System.out.println("examIdx::"+exam_idx);
		System.out.println(idx+"::objectidx값 받아왔다 ");
		System.out.println("선택버튼:"+whichChk);
		System.out.println(sdate+"::sdate값 받아왔다");
		System.out.println(radioList.size()+"::radioList사이즈크기");
	

			
		MbSJVO mbSJVO = new MbSJVO();
		String maxNum1 = null;
		
		try {	
		int num = 0;
	
		if(whichChk.equals("new")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examNum",examNum1);
				
	       		map.put("cO02", radioList.get(0)); 
	       		map.put("cAc01",radioList.get(1)); 
	       		map.put("cH01", radioList.get(2)); 
	       		
	       		
	       		map.put("cO01", textList.get(0));
	       		map.put("cAc02", textList.get(1));
	       		map.put("cH02", textList.get(2));	

				map.put("testDay",sdate);
				map.put("insertUserid",subject_idx);
				map.put("updateUserid",subject_idx);
				num = sheetService.update_mb_c3_newlist(map); 
			}else if(!(exam_num.equals(""))) {
				Map<String, String> chkmap	 = new HashMap<String, String>();
				chkmap.put("objectIdx", idx);
				chkmap.put("examNum",exam_num);
				chkmap.put("examIdx",exam_idx);
				List<MbC3VO> chkIclist =  sheetService.select_mb_c3_list(chkmap);
				
				
				if(chkIclist.size() ==0 ) {
					Map<String, String> map1	 = new HashMap<String, String>();
					map1.put("objectIdx", idx);
					map1.put("subjectIdx",subject_idx);			
	
		       		map1.put("cO02", radioList.get(0)); 
		       		map1.put("cAc01",radioList.get(1)); 
		       		map1.put("cH01", radioList.get(2)); 
		       		
		       		
		       		map1.put("cO01", textList.get(0));
		       		map1.put("cAc02", textList.get(1));
		       		map1.put("cH02", textList.get(2));
				
					map1.put("testDay",sdate);
				
					map1.put("insertUserid",subject_idx);
					map1.put("updateUserid",subject_idx);
				
					num = sheetService.update_mb_c3_newlist(map1);
				}else {
					Map<String, String> map2	 = new HashMap<String, String>();
					int exam_num1 =  Integer.parseInt(exam_num);
					int exam_Addnum = exam_num1+1;
					String exam_Newnum = String.valueOf(exam_Addnum);
					map2.put("examNum",exam_Newnum);
					System.out.println("examnNum(int)::"+exam_num1);
					System.out.println("exam_Addnum(int)::"+exam_Addnum);
					System.out.println("exam_Newnum(String)::"+exam_Newnum);
				

					map2.put("objectIdx", idx);
					map2.put("subjectIdx",subject_idx);			
					map2.put("examIdx",exam_idx);
				
		       		map2.put("cO02", radioList.get(0)); 
		       		map2.put("cAc01",radioList.get(1)); 
		       		map2.put("cH01", radioList.get(2)); 
		       		
		       		
		       		map2.put("cO01", textList.get(0));
		       		map2.put("cAc02", textList.get(1));
		       		map2.put("cH02", textList.get(2));
			
					map2.put("testDay",sdate);
					map2.put("insertUserid",subject_idx);
					map2.put("updateUserid",subject_idx);
					num = sheetService.update_mb_c3_newlist(map2);
			
					}
			  }
			 if(num> 0 ) {
				  Map<String, String> tmap	 = new HashMap<String, String>();
				  tmap.put("objectIdx", idx);
				  tmap.put("examId", exam_id);
				  tmap.put("cdbs", cdbs);
				 List<MbSJVO> maxNum =  commonService.select_load_selTablist(tmap);

				 for(MbSJVO mbSJVO1 : maxNum){
				  	
				 mbSJVO.setExamNum(mbSJVO1.getExamNum());
				 maxNum1 = mbSJVO.getExamNum();
				 System.out.println(mbSJVO1.getExamNum()+"::maxnumselction값");
				 // System.out.println(maxNum1+"::maxnumselction값");
				 
				 }
				 
				 
				}
							   		
		}else if(whichChk.equals("edit")) {
			if(exam_num.equals("")) {
				Map<String, String> map	 = new HashMap<String, String>();
				String examNum1 = "1";
				map.put("objectIdx", idx);
				map.put("subjectIdx",subject_idx);
				map.put("examIdx",exam_idx);
				map.put("examNum",examNum1);
				
	       		map.put("cO02", radioList.get(0)); 
	       		map.put("cAc01",radioList.get(1)); 
	       		map.put("cH01", radioList.get(2)); 
	       		
	       		
	       		map.put("cO01", textList.get(0));
	       		map.put("cAc02", textList.get(1));
	       		map.put("cH02", textList.get(2));
				
				map.put("testDay",sdate);
			
				map.put("updateUserid",subject_idx);
				
				num = sheetService.update_mb_c3_editlist(map);
			}else if(!(exam_num.equals(""))) {	
				Map<String, String> map1	 = new HashMap<String, String>();
				map1.put("objectIdx", idx);
				map1.put("subjectIdx",subject_idx);
				map1.put("examIdx",exam_idx);
				map1.put("examNum",exam_num);
			

	       		map1.put("cO02", radioList.get(0)); 
	       		map1.put("cAc01",radioList.get(1)); 
	       		map1.put("cH01", radioList.get(2)); 
	       		
	       		
	       		map1.put("cO01", textList.get(0));
	       		map1.put("cAc02", textList.get(1));
	       		map1.put("cH02", textList.get(2));
			
				map1.put("testDay",sdate);
			
				map1.put("updateUserid",subject_idx);
				num = sheetService.update_mb_c3_editlist(map1);
			}
			
			
			
		}

		
		System.out.println(num +":사이즈");
		
		}catch(NumberFormatException e) {
			exam_num ="1";
			System.out.println("NumberFormatException디폴트값설정(selecttionNum)::"+exam_num);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	
		
	    //리턴값
        Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
        //성공했다고 처리
       result.put("code", "OK");
       result.put("message", "등록에 성공 하였습니다.");
       mav.setViewName("/include/sheet/view_progression");
		//mav.addObject("retVal", retVal);
		mav.addObject("code", "OK");
		if(whichChk.equals("new")) {
			
			mav.addObject("message", "신규에 등록에 성공 하였습니다.");
			mav.addObject("objIdx", idx);
			mav.addObject("exam_num", maxNum1);
			mav.addObject("result","new");
			}else if(whichChk.equals("edit")) {
			mav.addObject("result","edit");
			mav.addObject("message", "수정을 성공 하였습니다.");
			//mav.addObject("dataList", ja_data_list);
			}		   
		
		
		
		return mav;
	}	
	
	@RequestMapping("/view_hrsd1.do")
	public ModelAndView view_hrsd1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbHrsdVO> hrsd_list = sheetService.select_mb_hrsd_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_hrsd1");
		mav.addObject("success", true);
		mav.addObject("hrsd_list", hrsd_list);

		return mav;
	}

	@RequestMapping("/view_hrsd2.do")
	public ModelAndView view_hrsd2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbHrsdVO> hrsd_list = sheetService.select_mb_hrsd_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_hrsd2");
		mav.addObject("success", true);
		mav.addObject("hrsd_list", hrsd_list);

		return mav;
	}

	@RequestMapping("/view_hrsd3.do")
	public ModelAndView view_hrsd3(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbHrsdVO> hrsd_list = sheetService.select_mb_hrsd_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_hrsd3");
		mav.addObject("success", true);
		mav.addObject("hrsd_list", hrsd_list);

		return mav;
	}

	@RequestMapping("/view_cdr1.do")
	public ModelAndView view_cdr1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbCdr1VO> cdr1_list = sheetService.select_mb_cdr1_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cdr1");
		mav.addObject("success", true);
		mav.addObject("cdr1_list", cdr1_list);

		return mav;
	}

	@RequestMapping("/view_cdr2.do")
	public ModelAndView view_cdr2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbCdr2VO> cdr2_list = sheetService.select_mb_cdr2_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_cdr2");
		mav.addObject("success", true);
		mav.addObject("cdr2_list", cdr2_list);

		return mav;
	}

	@RequestMapping("/view_lab1.do")
	public ModelAndView view_lab1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbLabVO> lab_list = sheetService.select_mb_lab1_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_lab1");
		mav.addObject("success", true);
		mav.addObject("lab_list", lab_list);

		return mav;
	}

	@RequestMapping("/view_lab2.do")
	public ModelAndView view_lab2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbLabVO> lab_list = sheetService.select_mb_lab2_list(map);
		List<MbDiaVO> dia_list = sheetService.select_mb_dia_list(map);
		List<String> memo_list = sheetService.select_memo(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_lab2");
		mav.addObject("success", true);
		mav.addObject("lab_list", lab_list);
		mav.addObject("dia_list", dia_list);
		mav.addObject("memo_list", memo_list);

		return mav;
	}

	@RequestMapping("/view_blood.do")
	public ModelAndView view_blood(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String exam_num = request.getParameter("exam_num");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("objectIdx", object_idx);
		map.put("examNum", exam_num);
		List<MbTestblodVO> testblod_list = sheetService.select_mb_testblod_list(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/sheet/view_blood");
		mav.addObject("success", true);
		mav.addObject("testblod_list", testblod_list);

		return mav;
	}
}