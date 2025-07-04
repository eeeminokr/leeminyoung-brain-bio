package csbrain.data.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import csbrain.common.service.CommonService;
import csbrain.common.service.CommonVO;
import csbrain.common.service.MbEtcCdVO;
import csbrain.common.service.MbIcVO;
import csbrain.common.service.MbMmseVO;
import csbrain.common.service.MbSJVO;
import csbrain.common.util.Constant;
import csbrain.common.util.DateUtil;
import csbrain.common.util.StringUtil;
import csbrain.data.service.Csbr1020ListDataVO;
import csbrain.data.service.Csbr1031SearchVO;
import csbrain.data.service.Csbr1050InfoVO;
import csbrain.data.service.Csbr1050SearchVO;
import csbrain.data.service.DataService;
import csbrain.data.service.MbCsfhdVO;
import csbrain.data.service.MbMriTestblodVO;
import csbrain.data.service.MbNrcdVO;
import csbrain.data.service.MbObjectVO;
import csbrain.data.service.MbOpinionVO;
import csbrain.data.service.MbResebsVO;
import csbrain.data.service.MbResecsfVO;
import csbrain.data.service.MbResecstVO;
import csbrain.data.service.MbReseibVO;
import csbrain.data.service.MbResemriVO;
import csbrain.data.service.MbResepetVO;
import csbrain.data.service.MbReseselVO;
import csbrain.data.service.MbResesnsbVO;
import csbrain.data.service.MbResebwVO;
import csbrain.data.service.MbReseckVO;
import csbrain.data.service.MbSpecimdtMbCsfdtVO;
import csbrain.data.service.MbSpecimhdVO;
import csbrain.data.service.mb_snsb;
import csbrain.main.service.MemberService;
import csbrain.main.service.MemberVO;
import csbrain.target.service.TargetService;
import csbrain.target.service.TargetVO;
import csbrain.common.util.FileUtils;
import csbrain.common.util.ResourceBundleUtil;

@Controller
@RequestMapping("/include")
public class DataController {

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

	@RequestMapping("/data/csbr2010_left_data.do")
	public ModelAndView csbr2010_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resesel");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2010_right_data.do")
	public ModelAndView csbr2010_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2010_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbReseselVO> list = dataService.select_csbr2010_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/csbr2010_action.do")
	public ModelAndView csbr2010_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");

		String k, rtime, rname, telno, birth, rem, tyn, rowdel;
		String[] sp;
		String ortime, orname;

		String htmlTag = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			rtime = request.getParameter("resetime" + i).replace(":", "");
			rname = request.getParameter("resename" + i);
			telno = request.getParameter("tel_no" + i);
			birth = request.getParameter("birthday" + i).replace("-", "");
			rem = request.getParameter("remark" + i);
			tyn = request.getParameter("testyn" + i);
			rowdel = request.getParameter("del" + i);

			if(tyn == null || tyn == "") {
				tyn = "0";
			}
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}

			MbReseselVO mrvo = new MbReseselVO();
			mrvo.setTelNo(telno);
			mrvo.setBirthday(birth);
			mrvo.setRemark(rem);
			mrvo.setTestyn(tyn);
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(k) ) {
				sp = k.split("\\|");
				ortime = sp[0];
				orname = sp[1];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(3);
					map.put("savedate", savedate);
					map.put("ortime", ortime);
					map.put("orname", orname);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(rtime) && !StringUtil.isEmptyString(rname) ) {
						mrvo.setResetime(ortime);
						mrvo.setResename(orname);
						rtnCnt = dataService.update_csbr2010_mb_resesel(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2010_action : " + rtnCnt);
						}
					}
				}
			} else {
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(rtime) && !StringUtil.isEmptyString(rname) ) {
					mrvo.setResetime(rtime);
					mrvo.setResename(rname);
					String sqlCntResult = dataService.insert_csbr2010_mb_resesel(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2010_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + k + rtime + rname + telno + birth + rem + tyn + "<br>";
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2010_print.do")
	public ModelAndView csbr2010_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbReseselVO> list = dataService.select_csbr2010_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2010_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2020_left_data.do")
	public ModelAndView csbr2020_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resesnsb");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2020_right_data.do")
	public ModelAndView csbr2020_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2020_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResesnsbVO> list = dataService.select_csbr2020_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	// SNSB예약관리 신규 저장
	@RequestMapping("/csbr2020_action.do")
	public ModelAndView csbr2020_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		
		if(StringUtil.isEmptyString(savedate)){
			System.out.println("savedate is null ");
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");
		
		System.out.println("savedate2 -> "+savedate);
		System.out.println("moddate -> "+moddate);

		String k, rtime, oidx, rem, tyn, rowdel;
		String[] sp;
		String ortime, o;

		String htmlTag = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) { // 30
			k = request.getParameter("key" + i);
			rtime = request.getParameter("resetime" + i).replace(":", "");
			oidx = request.getParameter("object_idx" + i);
			rem = request.getParameter("remark" + i);
			tyn = request.getParameter("testyn" + i);
			rowdel = request.getParameter("del" + i);
			
			if(tyn == null || tyn == "") {
				tyn = "0";
			}
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}

			MbResesnsbVO mrvo = new MbResesnsbVO();
			mrvo.setRemark(rem);
			mrvo.setTestyn(tyn);
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(k) ) {
				
				sp = k.split("\\|");
				ortime = sp[0];
				o = sp[1];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(3);
					map.put("savedate", savedate);
					map.put("ortime", ortime);
					map.put("oidx", o);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(rtime) && !StringUtil.isEmptyString(oidx) ) {
						mrvo.setResetime(ortime);
						mrvo.setObjectIdx(o);
						rtnCnt = dataService.update_csbr2020_mb_resesnsb(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2020_action : " + rtnCnt);
						}
					}
				}
			} else {
				
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(rtime) && !StringUtil.isEmptyString(oidx) ) {
					
					mrvo.setResetime(rtime);
					mrvo.setObjectIdx(oidx);
					String sqlCntResult = dataService.insert_csbr2020_mb_resesnsb(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2020_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + k + rtime + oidx + rem + tyn + "<br>";
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2020_print.do")
	public ModelAndView csbr2020_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2020");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResesnsbVO> list = dataService.select_csbr2020_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2020_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/search_name.do")
	public ModelAndView search_name(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {


		String st = (String) request.getParameter("st");

		List<MbObjectVO> list = dataService.select_mb_object(st);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/search_name");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2040_left_data.do")
	public ModelAndView csbr2040_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resemri");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2040_right_data.do")
	public ModelAndView csbr2040_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2040_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResemriVO> list = dataService.select_csbr2040_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/csbr2040_action.do")
	public ModelAndView csbr2040_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_integration_num;
		String rowdel;

		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResemriVO mrvo = new MbResemriVO();
			i_k = request.getParameter("key" + i);
			mrvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			mrvo.setObjectIdx(request.getParameter("object_idx" + i));
			mrvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			mrvo.setMriid(request.getParameter("mriid" + i));
			mrvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			mrvo.setTestyn(i_testyn);
			mrvo.setMriyn(request.getParameter("mriyn" + i));
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			mrvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			mrvo.setSelectionNum(i_selection_num);
			i_integration_num = request.getParameter("integration_num" + i);
			if(i_integration_num == null || i_integration_num == "") {
				i_integration_num = "0";
			}
			mrvo.setIntegrationNum(i_integration_num);
			mrvo.setAgree(request.getParameter("agree" + i));
			mrvo.setPhoto(request.getParameter("photo" + i));
			mrvo.setBlood(request.getParameter("blood" + i));
			mrvo.setDamdang1(request.getParameter("damdang1" + i));
			mrvo.setCdrom(request.getParameter("cdrom" + i));
			mrvo.setReading(request.getParameter("reading" + i));
			mrvo.setConsult(request.getParameter("consult" + i));
			mrvo.setDamdang2(request.getParameter("damdang2" + i));
			mrvo.setDiagnose(request.getParameter("diagnose" + i));
			mrvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			mrvo.setFollowup("");
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) {
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
						mrvo.setResetime(org_resetime);
						mrvo.setObjectIdx(org_object_idx);
						mrvo.setResersno(org_resersno);
						rtnCnt = dataService.update_csbr2040_mb_resemri(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2040_action : " + rtnCnt);
						}
					}
				}
			} else {
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_resemri");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					
					org_resersno = String.format("%s", org_resersno);
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					
					mrvo.setResersno(org_resersno);
					mrvo.setMriid(savedate.substring(2) + org_resersno); //처음 추가시 mriid 만들기

					String sqlCntResult = dataService.insert_csbr2040_mb_resemri(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2040_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + mrvo.getResetime() + mrvo.getObjectIdx() + mrvo.getTelNo() + mrvo.getBirth() + mrvo.getRemark() + mrvo.getTestyn() + "<br>";
			i++;
		}

		//mriid 만들기
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("tableName", "mb_resemri");
		map.put("savedate", savedate);
		//dataService.update_csbr_mriid(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2040_print.do")
	public ModelAndView csbr2040_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2040");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResemriVO> list = dataService.select_csbr2040_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2040_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2050_left_data.do")
	public ModelAndView csbr2050_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resecst");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2050_right_data.do")
	public ModelAndView csbr2050_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2050_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResecstVO> list = dataService.select_csbr2050_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/csbr2050_action.do")
	public ModelAndView csbr2050_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String rowdel;

		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResecstVO mrvo = new MbResecstVO();
			i_k = request.getParameter("key" + i);
			mrvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			mrvo.setObjectIdx(request.getParameter("object_idx" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			mrvo.setTestyn(i_testyn);
			mrvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) {
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(3);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					dataService.delete_csbr2050_mb_resecst(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
						mrvo.setResetime(org_resetime);
						mrvo.setObjectIdx(org_object_idx);
						rtnCnt= dataService.update_csbr2050_mb_resecst(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2050_action : " + rtnCnt);
						}
					}
				}
			} else {
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
					String sqlCntResult = dataService.insert_csbr2050_mb_resecst(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2050_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + mrvo.getResetime() + mrvo.getResename() + mrvo.getObjectIdx() + mrvo.getTelNo() + mrvo.getBirth() + mrvo.getRemark() + mrvo.getTestyn() + "<br>";
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2050_print.do")
	public ModelAndView csbr2050_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2050");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResecstVO> list = dataService.select_csbr2050_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2050_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2060_left_data.do")
	public ModelAndView csbr2060_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resepet");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2060_right_data.do")
	public ModelAndView csbr2060_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2060_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResepetVO> list = dataService.select_csbr2060_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/csbr2060_action.do")
	public ModelAndView csbr2060_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_selection_num;
		String i_petid;
		String rowdel;

		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResepetVO mrvo = new MbResepetVO();
			i_k = request.getParameter("key" + i);
			mrvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			mrvo.setObjectIdx(request.getParameter("object_idx" + i));
			mrvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			mrvo.setPetid(request.getParameter("petid" + i));
			mrvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			mrvo.setTestyn(i_testyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			mrvo.setSelectionNum(i_selection_num);
			mrvo.setAgree(request.getParameter("agree" + i));
			mrvo.setPhoto(request.getParameter("photo" + i));
			mrvo.setBlood(request.getParameter("blood" + i));
			mrvo.setDamdang1(request.getParameter("damdang1" + i));
			mrvo.setCdrom(request.getParameter("cdrom" + i));
			mrvo.setReading(request.getParameter("reading" + i));
			mrvo.setConsult(request.getParameter("consult" + i));
			mrvo.setDamdang2(request.getParameter("damdang2" + i));
			mrvo.setDiagnose(request.getParameter("diagnose" + i));
			mrvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) {
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2060_mb_resepet(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
						mrvo.setResetime(org_resetime);
						mrvo.setObjectIdx(org_object_idx);
						mrvo.setResersno(org_resersno);
						rtnCnt = dataService.update_csbr2060_mb_resepet(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2060_action : " + rtnCnt);
						}
					}
				}
			} else {
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_resepet");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					org_resersno = String.format("%s", org_resersno);
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					i_petid = "P" + savedate.substring(2) + org_resersno;

					mrvo.setResersno(org_resersno);
					mrvo.setPetid(i_petid);

					String sqlCntResult = dataService.insert_csbr2060_mb_resepet(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2060_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + mrvo.getResetime() + mrvo.getResename() + mrvo.getObjectIdx() + mrvo.getTelNo() + mrvo.getBirth() + mrvo.getRemark() + mrvo.getTestyn() + "<br>";
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2060_print.do")
	public ModelAndView csbr2060_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2060");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResepetVO> list = dataService.select_csbr2060_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2060_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2070_left_data.do")
	public ModelAndView csbr2070_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resecsf");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr2070_right_data.do")
	public ModelAndView csbr2070_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2070_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResecsfVO> list = dataService.select_csbr2070_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/csbr2070_action.do")
	public ModelAndView csbr2070_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_csfid;
		String rowdel;

		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResecsfVO mrvo = new MbResecsfVO();
			i_k = request.getParameter("key" + i);
			mrvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			mrvo.setObjectIdx(request.getParameter("object_idx" + i));
			mrvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			mrvo.setCsfid(request.getParameter("csfid" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			mrvo.setTestyn(i_testyn);
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			mrvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			mrvo.setSelectionNum(i_selection_num);
			mrvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			mrvo.setModifyusid(Constant.USER.get("ID"));
			mrvo.setResedate(savedate);
			mrvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) {
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2070_mb_resecsf(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
						mrvo.setResetime(org_resetime);
						mrvo.setObjectIdx(org_object_idx);
						mrvo.setResersno(org_resersno);
						rtnCnt = dataService.update_csbr2070_mb_resecsf(mrvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2070_action : " + rtnCnt);
						}
					}
				}
			} else {
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(mrvo.getResetime()) && !StringUtil.isEmptyString(mrvo.getObjectIdx()) ) {
					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_resecsf");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					org_resersno = String.format("%s", org_resersno);
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					i_csfid = "C" + savedate.substring(2) + org_resersno;

					mrvo.setResersno(org_resersno);
					mrvo.setCsfid(i_csfid);

					String sqlCntResult = dataService.insert_csbr2070_mb_resecsf(mrvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2070_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + mrvo.getResetime() + mrvo.getResename() + mrvo.getObjectIdx() + mrvo.getTelNo() + mrvo.getBirth() + mrvo.getRemark() + mrvo.getTestyn() + "<br>";
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr2070_print.do")
	public ModelAndView csbr2070_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2070");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResecsfVO> list = dataService.select_csbr2070_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2070_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}


	//csbr10=================================================================

	@RequestMapping("/csbr1010_print.do")
	public ModelAndView csbr1010_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR1010");
			commonService.insert_mb_usedlog(map);
		}

		String st_idx = request.getParameter("st_idx");
		String st_name = request.getParameter("st_name");
		String st_jumin = request.getParameter("st_jumin");
		String st_telno = request.getParameter("st_telno");

		String idx_query = "";
		if ( !StringUtil.isEmptyString(st_idx) ) idx_query = " AND O.IDX = #{stIdx} ";

		Map<String, String> map = new HashMap<String, String>(5);
		map.put("stIdx", st_idx);
		map.put("stName", st_name);
		map.put("stJumin", st_jumin);
		map.put("stTelno", st_telno);
		map.put("idxQuery", idx_query);
		List<MbObjectVO> list = dataService.select_csbr1010_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1010_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr1010_search.do")
	public ModelAndView csbr1010_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String st_name = request.getParameter("st_name");
		String st_idx = request.getParameter("st_idx");
		String st_jumin = request.getParameter("st_jumin");
		String st_telno = request.getParameter("st_telno");
		//String st_consult = request.getParameter("st_consult");

		String idx_query = "";
		if ( !StringUtil.isEmptyString(st_idx) ) idx_query = " AND O.IDX = #{stIdx} ";

		Map<String, String> map = new HashMap<String, String>(5);
		map.put("stIdx", st_idx);
		map.put("stName", st_name);
		map.put("stJumin", st_jumin);
		map.put("stTelno", st_telno);
		map.put("idxQuery", idx_query);
		List<MbObjectVO> list = dataService.select_csbr1010_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1010_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr1010_info.do")
	public ModelAndView csbr1010_info(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");

		//띠 정보 추출
		Map<String, String> getDDI = new HashMap<String, String>();
		getDDI.put("KDCD","A007");
		List<MbEtcCdVO> ddiInfoList = commonService.select_mb_etccd_list(getDDI);
		Map<String, String> DDI = new HashMap<String, String>();

		for(MbEtcCdVO vo : ddiInfoList) {
			String cd = vo.getCDBS();
			if(cd == null || cd == "") {
				cd = "0";
			}
			String cdnm = vo.getCDNM();
			
			DDI.put(cd, cdnm);
		}

		//학습 정보 추출
		Map<String, String> getEDU = new HashMap<String, String>();
		getEDU.put("KDCD","A008");
		List<MbEtcCdVO> learningInfoList = commonService.select_mb_etccd_list(getEDU);
		Map<String, String> EDU = new HashMap<String, String>();

		for(MbEtcCdVO vo : learningInfoList) {
			String cd = vo.getCDBS();
			if(cd == null || cd == "") {
				cd = "0";
			}
			String cdnm = vo.getCDNM();
			EDU.put(cd, cdnm);
		}

		MbObjectVO movo = memberService.selectMemberObject(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1010_info");
		mav.addObject("success", true);
		mav.addObject("DDI", DDI);
		mav.addObject("EDU", EDU);
		mav.addObject("movo", movo);

		return mav;
	}
	
	@RequestMapping(value ="/target/csbr1010_Insert_ObjIdx.do",method= {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView csbr1010_Insert_ObjIdx(@ModelAttribute("targetVO")TargetVO targetVO, HttpServletRequest  request ,HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		System.out.println("form action 넘어들어왔다-----------------------");

	
		
	
		System.out.println("voaddress1::::"+targetVO.getAddress1());
		System.out.println("votel1::::"+targetVO.getTel1());
		
		System.out.println("Vname::::"+targetVO.getName());
		System.out.println("Vjumin1::::"+targetVO.getJumin1());
		System.out.println("Vjumin2::::"+targetVO.getJumin2());
	
	
		

	//	targetService.select_csbr1010_Chk_objIdx(map); 

		
				
		String name = request.getParameter("name"); //이름
		String jumin1 = request.getParameter("jumin1");
		String jumin2 = request.getParameter("jumin2");

		System.out.println("Rname::"+name);
		System.out.println("Rjumin1::"+jumin1);
		System.out.println("Rjumin2::"+jumin2);
		
		String tel1 = request.getParameter("tel1"); //집전화1
		String tel2 = request.getParameter("tel2"); //집전화2
		String tel3 = request.getParameter("tel3"); //집전화3
		
		//주소컬럼
		String sample4_sido = request.getParameter("sample4_sido");
		String sample4_sigungu = request.getParameter("sample4_sigungu");
		String sample4_roadAddress = request.getParameter("sample4_roadAddress");	 //주 ADDRESS 컬럼 저장 부분 //도로주소
		String sample4_jibunAddress = request.getParameter("sample4_jibunAddress");  //주 ADDRESS 컬럼 저장 부분  //지번주소 
		String sample4_detailAddress = request.getParameter("sample4_detailAddress");
		

		
		try {
	    String result = null;
		String address3 = null;
		if(!(sample4_roadAddress == null)) {
			String [] addressR3 = sample4_roadAddress.split(" ",3);
			System.out.println(addressR3+":addressR3");
		
		 String str =  Arrays.toString(addressR3);
		 	System.out.println(str+":::str");
		    
		   int beginIndex = str.lastIndexOf(", ");
		   int endIndex = str.length();
		   
		   result = str.substring(beginIndex, endIndex-1);
		   
		   System.out.println(result+":result값");
		   
		   
		   String [] addressR_3 = sample4_roadAddress.split(" ");
			for(int i =0; i<addressR_3.length; i++ ) {
				
				  System.out.println("adrdress3_R["+i+"] : "+addressR_3[i]);		    
				  	
				  address3 = addressR_3[2];
		
				  
			}

			
		}
		
		/*
		else{		
			String addressJ3[] = sample4_jibunAddress.split(" ");
			
			for(int i = 1; i<addressJ3.length; i++ ) {
				
				  System.out.println("addrressJ["+i+"] : "+addressJ3[i]);
				  //	targetVO.setAddress4(addressJ3[3]+", "+sample4_detailAddress);
				  address3 = addressJ3[3];
				  str1 = addressJ3[3];
				
				    if(addressJ3.length > 3) {
					    //	StringBuilder sb = new StringBuilder();
					    	//sb.append(addressR3[2+i]);
					    	str1 += " ";
					    	str1 += addressJ3[3+i];
					    }
				    
				    System.out.println("strJ1값::"+str1);
				    
					}							
		
		}
		*/
		targetVO.setTel1(tel1);
		targetVO.setTel2(tel2);
		targetVO.setTel3(tel3);
		targetVO.setAddress1(sample4_sido);
		targetVO.setAddress2(sample4_sigungu);
		targetVO.setAddress3(address3);
		targetVO.setAddress4(result+" "+sample4_detailAddress);
		
		
		
		}catch(IndexOutOfBoundsException e) {
			
			
			System.out.println(e);		
			
		}
	
		
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("name",name);
		map.put("jumin1",jumin1);
		map.put("jumin2",jumin2);
		
		List<TargetVO> list =  targetService.select_csbr1010_Chk_objIdx(map); 

		
		ModelAndView mav  = new ModelAndView(); 
		String answer = null;
		if(list.size() == 0 || list.isEmpty()) {
			targetService.insert_csbr1010_objIdx(targetVO);
			answer = "true";
		
		}else {
		
			answer = "false";	
		}
		mav.setViewName("/include/target/csbr1010_insertIdx_OpenPopUp");
		mav.addObject("pass",answer);
		
		return mav ;	
	}
	
	
	
	
	//ajax호출 view_savedemography(savedemography)
		@RequestMapping(value ="/data/editcsbr1010_info.do",method= RequestMethod.POST)
				@ResponseBody
				public ModelAndView editcsbr1010_info(@RequestParam(value="tcolNmArr[]") List<String> tcolNmList,@RequestParam(value="textArr[]") List<String> textList,
						@RequestParam(value="idx") String idx,@RequestParam(value="whichChk")String whichChk,@RequestParam(value="gender")String gender,
						@RequestParam(value="year_ani")String year_ani,@RequestParam(value="edu")String edu,HttpServletRequest request,
					HttpServletResponse response, HttpSession session,ModelMap model) throws  Exception {
					MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
					String subject_idx = sessionMember.getIdx(); // login user idx
					String userId = sessionMember.getId();
					String Name = sessionMember.getName();
				   //String selectionNum = request.getParameter("selection_num");
					System.out.println("subject_idx::::"+subject_idx);
					
					String object_idx = request.getParameter("object_idx");
				//	String exam_num = request.getParameter("exam_num");
				//	System.out.println(exam_num+"::exam_num");
				
					System.out.println("잘넘어왔습니당");
					System.out.println(object_idx+":object_idx");
					System.out.println(idx+"::objectidx값 받아왔다 ");
					System.out.println("선택버튼:"+whichChk);
					System.out.println(tcolNmList.get(3)+"::colNmList.get(0)");
					System.out.println(gender+"::gender");
					System.out.println(year_ani+"::띠");
					System.out.println(edu+"::교육기간");
					try {	
					int num = 0;
				
					 if(whichChk.equals("edit")) {
							Map<String, String> map1	 = new HashMap<String, String>();
							map1.put("objectIdx", idx);
							map1.put("gender",gender);
							map1.put("year_ani",year_ani);
							map1.put("edu", edu);
				
							
							
							for(int i =0; i <textList.size(); i++) {
								
							map1.put(tcolNmList.get(i), textList.get(i));
								
							}
							map1.put("updateUserid",subject_idx);
							num = targetService.update_mb_object_editlist(map1);
						
						
						
						
					}

					
					System.out.println(num +":사이즈");
					
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
			       mav.setViewName("/include/data/csbr1010_info");
					//mav.addObject("retVal", retVal);
					mav.addObject("code", "OK");
					if(whichChk.equals("new")) {
					mav.addObject("message", "신규에 등록에 성공 하였습니다.");
					}else if(whichChk.equals("edit"))
					mav.addObject("message", "수정을 성공 하였습니다.");
					//mav.addObject("dataList", ja_data_list);
					
					
					
					
					return mav;
				}
		
	@RequestMapping("/data/csbr1010_right.do")
	public ModelAndView csbr1010_right(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		MbObjectVO mobjvo = memberService.selectMemberObject(object_idx);
		MbOpinionVO mopnvo = memberService.selectMemberOpinion(object_idx);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1010_right");
		mav.addObject("success", true);
		mav.addObject("mobjvo", mobjvo);
		mav.addObject("mopnvo", mopnvo);

		return mav;
	}

	// 대상자관리 - 진료소견
	@RequestMapping("/csbr1010_opinion_save.do")
	public ModelAndView csbr1010_opinion_save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO)session.getAttribute(Constant.SESSION_MEMBER); // 20220822 th.kim
		String object_idx = request.getParameter("object_idx");
		String subject_idx = sessionMember.getIdx(); // 20220822 th.kim
		String opinion = request.getParameter("opinion");

		if(!StringUtil.isEmptyString(object_idx)) {
			MbOpinionVO ovo = memberService.selectMemberOpinion(object_idx);
			Map<String, String> map = new HashMap<String, String>();
			map.put("objectIdx", object_idx);
			map.put("subject_idx", subject_idx); // 20220822 th.kim
			map.put("opindesc", opinion);
			map.put("modifyusid", Constant.USER.get("ID"));
			int rtnCnt = -1;
			if(ovo != null && object_idx.equals(ovo.getObjectIdx())) { //있으면 update
				rtnCnt = memberService.updateMemberOpinion(map);
			}else { //없으면 insert
				rtnCnt = memberService.insertMemberOpinion(map);
			}
			if(rtnCnt > 0) {
				logger.debug("csbr1010_opinion_save : " + rtnCnt);
			}
		}else {
			return null;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1010_opinion_save");
		mav.addObject("success", true);
		return mav;
	}
	
	// 대상자관리 - 비고
	@RequestMapping("/csbr1010_opinion_remark_save.do")
	public ModelAndView csbr1010_opinion_remark_save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		MemberVO sessionMember = (MemberVO)session.getAttribute(Constant.SESSION_MEMBER); // 20220822 th.kim
		String object_idx = request.getParameter("object_idx");
		String subject_idx = sessionMember.getIdx(); // 20220822 th.kim
		String remark = request.getParameter("remark");

		if(!StringUtil.isEmptyString(object_idx)) {
			MbOpinionVO ovo = memberService.selectMemberOpinionRemark(object_idx);
			Map<String, String> map = new HashMap<String, String>();
			map.put("objectIdx", object_idx);
			map.put("subject_idx", subject_idx); // 20220822 th.kim
			map.put("remark", remark);
			map.put("modifyusid", Constant.USER.get("ID"));
			int rtnCnt = -1;
			if(ovo != null && object_idx.equals(ovo.getObjectIdx())) { //있으면 update
				rtnCnt = memberService.updateMemberOpinionRemark(map);
			}else { //없으면 insert
				rtnCnt = memberService.insertMemberOpinionRemark(map);
			}
			if(rtnCnt > 0) {
				logger.debug("csbr1010_opinion_remark_save : " + rtnCnt);
			}
		}else {
			return null;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1010_opinion_save");
		mav.addObject("success", true);
		return mav;
	}

	@RequestMapping("/data/mmse_list.do")
	public ModelAndView mmse_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<MbMmseVO> mmse_list = commonService.select_mb_mmse_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/mmse_list");
		mav.addObject("success", true);
		mav.addObject("mmse_list", mmse_list);

		return mav;
	}

	@RequestMapping("/data/sunbyul_list.do")
	public ModelAndView sunbyul_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		 String object_idx = request.getParameter("object_idx");
		 	System.out.println("object_idx"+object_idx);
		

		List<MbSJVO> sunbyul_list = commonService.select_sunbyul_list(object_idx);
		
		for(MbSJVO Sjlist : sunbyul_list ) {
			
			System.out.println("ExamNm: "+Sjlist.getExamNm());
			System.out.println("SelectionNum: "+Sjlist.getExamNum());
			System.out.println("DESC2(테이블명): "+Sjlist.getDesc2());
	
	
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/sunbyul_list");
		mav.addObject("success", true);
		mav.addObject("sunbyul_list", sunbyul_list);	
	
		return mav;
	}
	
	@RequestMapping(value="/data/load_selTablist.do", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> load_selTablist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("MbIcVO") MbIcVO mbIcvo,ModelMap model) throws Exception {
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		String userId = sessionMember.getId();
		String Name = sessionMember.getName();
		System.out.println(subject_idx+":loginuseridx");
		System.out.println(userId+":usrid");
		System.out.println(Name+"::name");
		 String object_idx = request.getParameter("object_idx");
		 String examId = request.getParameter("examId");	
		 System.out.println("object_idx:"+object_idx);
		 System.out.println("examId:"+examId);

		 Map<String, String> map = new HashMap<>();
		 map.put("objectIdx",object_idx);
		 map.put("examId", examId);
		List<MbSJVO> selTablist = commonService.select_load_selTablist(map) ;
		

		
		String exids = null;
		String exidxs = null;
		String subidx = null;
		String examnum = null;
		String examday = null;
 		mbIcvo =	new MbIcVO();
		HashMap <String, String> maps = new HashMap<String, String>();
		for(MbSJVO Sjlist : selTablist ) {
			

			
			System.out.println("getExamId: "+Sjlist.getExamId());
			System.out.println("ExamIdx(시퀀스idx): "+Sjlist.getExamIdx());
			System.out.println("getSubjectIdx: "+Sjlist.getSubjectIdx());
			System.out.println("SelectionNum<selnumEXAM_NUM>: "+Sjlist.getExamNum());
			System.out.println(Sjlist.getTestDay());
		  
			
			
			 exids = Sjlist.getExamId();
		    exidxs = Sjlist.getExamIdx();
			subidx = Sjlist.getSubjectIdx();
			examnum = Sjlist.getExamNum();
			examday = Sjlist.getTestday();
		maps.put("examId", exids);
		maps.put("examIdx",exidxs);
		maps.put("examNum",examnum);
		maps.put("subjectIdx",subidx);
		maps.put("examDay",examday);
		}
		System.out.println("exids::::"+exids);


     /*
		mav.addObject("examIds",exids);
     	mav.addObject("examIdx",exidxs);
     	mav.addObject("examNum",examnum);
     	mav.addObject("subjectIdx",subidx);
     */	
     //	mav.addObject("selTablist", selTablist);
		return  maps;
	}
	
/*
	@RequestMapping("/data/jungmil_list.do")
	public ModelAndView jungmil_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<MbSJVO> jungmil_list = commonService.select_jungmil_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/jungmil_list");
		mav.addObject("success", true);
		mav.addObject("jungmil_list", jungmil_list);

		return mav;
	}
*/

	@RequestMapping("/data/etc_list.do")
	public ModelAndView etc_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<MbSJVO> etc_list = commonService.select_etc_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/etc_list");
		mav.addObject("success", true);
		mav.addObject("etc_list", etc_list);

		return mav;
	}

	@RequestMapping("/csbr1020_print.do")
	public ModelAndView csbr1020_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR1020");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<Csbr1020ListDataVO> list = dataService.select_csbr1020_list_data(d);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1020_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("d", d);

		return mav;
	}

	@RequestMapping("/data/csbr1020_left_data.do")
	public ModelAndView csbr1020_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr1020_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1020_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr1020_list_data.do")
	public ModelAndView csbr1020_list_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		//String edate = request.getParameter("edate");

		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyyMMdd", d);

		List<Csbr1020ListDataVO> list = dataService.select_csbr1020_list_data(d);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1020_list_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
		
	@RequestMapping("/csbr1050_print.do")
	public ModelAndView csbr1050_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String st_name = request.getParameter("st_name");
		
		System.out.println(st_name+"csbr1050_print.do////st_name 넌 무슨 이름이야????????????????");
		
		
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR1050");
			commonService.insert_mb_usedlog(map);
		}

		String st_idx = request.getParameter("st_idx");
		String st_num = request.getParameter("st_num");

		String idx_query = "";
		String num_query = "";
		if ( !StringUtil.isEmptyString(st_idx) ) idx_query = " AND O.IDX = #{stIdx} ";
		if ( !StringUtil.isEmptyString(st_num) ) num_query = " AND A.SELECTION_NUM = #{stNum} ";

		Map<String, String> map = new HashMap<String, String>(4);
		map.put("sd", sd);
		map.put("ed", ed);
		map.put("stName", st_name);
		map.put("idxQuery", idx_query);
		map.put("numQuery", num_query);

		List<Csbr1050SearchVO> list = dataService.select_csbr1050_search(map);

		for(int i = 0; i < list.size(); i++) {
			// 진단명
			if(list.get(i).getNormal().equals("Y")) {
				list.get(i).setDiagnosis("NORMAL(정상)");
			}
			else if(list.get(i).getSmi().equals("Y")) {
				list.get(i).setDiagnosis("SMI(주관적인지장애)");
			}
			else if(list.get(i).getMci().equals("Y")) {
				list.get(i).setDiagnosis("MCI");
			}
			else if(list.get(i).getDementia().equals("Y")) {
				list.get(i).setDiagnosis("DEMENTIA");
			}else {
				list.get(i).setDiagnosis("");
			}
			
			// 진단일 (진단명이 없으면 공백)
			if(list.get(i).getDiagnosis().equals("") || list.get(i).getDiagnosis() == null || list.get(i).getDiagnosis() == ""){
				list.get(i).setDiagnosisDate("");
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1050_print");
		mav.addObject("success", true);
		mav.addObject("sdate", sdate);
		mav.addObject("edate", edate);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr1050_search.do")
	public ModelAndView csbr1050_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String st_name = request.getParameter("st_name");
		String st_idx = request.getParameter("st_idx");
		String st_num = request.getParameter("st_num");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String idx_query = "";
		String num_query = "";
		if ( !StringUtil.isEmptyString(st_idx) ) idx_query = " AND O.IDX = #{stIdx} ";
		if ( !StringUtil.isEmptyString(st_num) ) num_query = " AND A.SELECTION_NUM = #{stNum} ";

		Map<String, String> map = new HashMap<String, String>(4);
		map.put("sd", sd);
		map.put("ed", ed);
		map.put("stName", st_name);
		map.put("stIdx", st_idx);
		map.put("stNum", st_num);
		map.put("idxQuery", idx_query);
		map.put("numQuery", num_query);
		List<Csbr1050SearchVO> list = dataService.select_csbr1050_search(map);

		for(int i = 0; i < list.size(); i++) {
			// 진단명
			if(list.get(i).getNormal().equals("Y")) {
				list.get(i).setDiagnosis("NORMAL(정상)");
			}
			else if(list.get(i).getSmi().equals("Y")) {
				list.get(i).setDiagnosis("SMI(주관적인지장애)");
			}
			else if(list.get(i).getMci().equals("Y")) {
				list.get(i).setDiagnosis("MCI");
			}
			else if(list.get(i).getDementia().equals("Y")) {
				list.get(i).setDiagnosis("DEMENTIA");
			}else {
				list.get(i).setDiagnosis("");
			}
			
			// 진단일 (진단명이 없으면 공백)
			if(list.get(i).getDiagnosis().equals("") || list.get(i).getDiagnosis() == null || list.get(i).getDiagnosis() == ""){
				list.get(i).setDiagnosisDate("");
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1050_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	// 진단관리등록
	@RequestMapping("/csbr1050_info.do")
	public ModelAndView csbr1050_info(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String selection_num = request.getParameter("selection_num");
		String object_idx = request.getParameter("object_idx");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("selectionNum", selection_num);
		map.put("objectIdx", object_idx);

		List<Csbr1050InfoVO> list = dataService.select_csbr1050_info(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1050_info");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr1050_info_action.do")
	public ModelAndView csbr1050_info_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("mbNrcdVO") MbNrcdVO vo, ModelMap model) throws Exception {

		String htmlTag = "";

		if(!StringUtil.isEmptyString(vo.getObjectIdx()) && !StringUtil.isEmptyString(vo.getSelectionNum())){

			Map<String, String> map = new HashMap<String, String>(2);
			map.put("objectIdx", vo.getObjectIdx());
			map.put("selectionNum", vo.getSelectionNum());

			dataService.delete_csbr1050_mb_nrcd(map);

			if(StringUtil.isEmptyString(vo.getSubjectId())) {
				vo.setSubjectId(Constant.USER.get("ID"));
			}

			String sqlCntResult = dataService.insert_csbr1050_mb_nrcd(vo);
			String[] arrSqlCntResult = sqlCntResult.split("/");
			String query = arrSqlCntResult[0];
			int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
			if(rtnCnt > 0) {
				logger.debug("csbr1050_info_action : " + rtnCnt);
			}
			htmlTag += query + "<br>";
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1050_info_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}
	
	@RequestMapping("/csbr1050_cdr.do")
	public ModelAndView csbr1050_cdr(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String object_idx = request.getParameter("object_idx");
		String selection_num = request.getParameter("selection_num");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1050_cdr");
		
		Map<String, String> map = new HashMap<String, String>(3); 
		map.put("object_idx", object_idx);
		map.put("selection_num", selection_num);
		
		Map<String,Object> cdrMap = this.dataService.select_csbr1050_cdr(map);
		Map<String,Object> objectMap = this.dataService.select_csbr1050_object(map);
		String action = null;
		if(cdrMap==null || !cdrMap.containsKey("test_day")) {
			action = "new";
			cdrMap = new HashMap<String,Object>();
		} else {
			action = "update";
		}
		mav.addObject("objectMap", objectMap);
		mav.addObject("cdrMap", cdrMap);
		mav.addObject("object_idx", object_idx);
		mav.addObject("selection_num", selection_num);
		mav.addObject("action", action);
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr1050_cdr_save.do")
	public ModelAndView csbr1050_cdr_save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String action = request.getParameter("action");
		if(action==null || action.equals("")) throw new Exception("The action is required.");
		
		MemberVO sessionMember = (MemberVO)session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		
		/********************************************************************
		 * subject_idx,object_idx,
		 * selection_num,cdr_m,cdr_o,cdr_j,cdr_s,cdr_h,cdr_p,cdr_step,test_day		 * 
		 * cdr_m_01,cdr_m_02,cdr_j_01,cdr_j_02,cdr_j_03,cdr_j_04,cdr_j_05,cdr_j_06,cdr_j_07,cdr_j_08,cdr_j_09,cdr_s_01,cdr_s_02,cdr_s_03,cdr_h_01,cdr_h_02,test_day
		 ********************************************************************/
		Map<String,String> map = new HashMap<String,String>();
		set(request, map, "object_idx", true);
		set(request, map, "selection_num", true);
		
		set(request, map, "cdr_m", false);
		set(request, map, "cdr_o", false);
		set(request, map, "cdr_j", false);
		set(request, map, "cdr_s", false);
		set(request, map, "cdr_h", false);
		set(request, map, "cdr_p", false);
		set(request, map, "cdr_step", false);
		
		set(request, map, "cdr_m_01", false);
		set(request, map, "cdr_m_02", false);
		set(request, map, "cdr_j_01", false);
		set(request, map, "cdr_j_02", false);
		set(request, map, "cdr_j_03", false);
		set(request, map, "cdr_j_04", false);
		set(request, map, "cdr_j_05", false);
		set(request, map, "cdr_j_06", false);
		set(request, map, "cdr_j_07", false);
		set(request, map, "cdr_j_08", false);
		set(request, map, "cdr_j_09", false);
		set(request, map, "cdr_s_01", false);
		set(request, map, "cdr_s_02", false);
		set(request, map, "cdr_s_03", false);
		set(request, map, "cdr_h_01", false);
		set(request, map, "cdr_h_02", false);
		set(request, map, "test_day", false);
		
		map.put("subject_idx", subject_idx);
		
		int result=0;
		if(action.equals("update")) {
			result = this.dataService.update_csbr1050_cdr(map);
			this.dataService.update_csbr1050_cdr_detail(map);
		} else if(action.equals("new")) {
			result = this.dataService.insert_csbr1050_cdr(map);
			this.dataService.insert_csbr1050_cdr_detail(map);
		} else {
			throw new Exception("Invalid action. - "+action);
		}
		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/include/csbr1050_cdr_save_result");
		model.addAttribute("result", result);
		model.addAttribute("success", true);

		return new ModelAndView("jsonView", model);
	}
	
	private void set(HttpServletRequest request, Map<String,String> map, String name, boolean required) throws Exception {
		String value = request.getParameter(name);
		if(required && (value==null||value.equals(""))) {
			throw new Exception("The "+name+" is required.");
		}
		
		map.put(name, value);
	}

	@RequestMapping("/data/snsb_list.do")
	public ModelAndView snsb_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> snsb_list = commonService.select_snsb_list(object_idx);
//		String list [];
//		String pathName;
//		for(int i = 0; i < snsb_list.size(); i++) {
//			if(snsb_list.get(i).getPdfPath() != null || snsb_list.get(i).getPdfPath() != "" || snsb_list.get(i).getPdfPath() != " ") {
//				list = snsb_list.get(i).getPdfPath().split("/");
//				pathName = list[list.length - 1];
//				
//				snsb_list.get(i).setPdfPath(pathName.replace(".pdf", ""));
//			}else {
//				snsb_list.get(i).setPdfPath("N");
//			}
//		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/snsb_list");
		mav.addObject("success", true);
		mav.addObject("snsb_list", snsb_list);

		return mav;
	}

	// NRCD 진단창 MRI
	@RequestMapping("/data/mri_list.do")
	public ModelAndView mri_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> mri_list = commonService.select_mri_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/mri_list");
		mav.addObject("success", true);
		mav.addObject("mri_list", mri_list);

		return mav;
	}

	@RequestMapping("/data/pet_list.do")
	public ModelAndView pet_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> pet_list = commonService.select_pet_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/pet_list");
		mav.addObject("success", true);
		mav.addObject("pet_list", pet_list);

		return mav;
	}

	@RequestMapping("/data/csf_list.do")
	public ModelAndView csf_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> csf_list = commonService.select_csf_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csf_list");
		mav.addObject("success", true);
		mav.addObject("csf_list", csf_list);

		return mav;
	}
	
	@RequestMapping("/data/blood_list.do")
	public ModelAndView blood_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> blood_list = commonService.select_blood_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/blood_list");
		mav.addObject("success", true);
		mav.addObject("blood_list", blood_list);

		return mav;
	}
	
	@RequestMapping("/data/apoe_list.do")
	public ModelAndView apoe_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		List<CommonVO> apoe_list = commonService.select_apoe_list(object_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/apoe_list");
		mav.addObject("success", true);
		mav.addObject("apoe_list", apoe_list);

		return mav;
	}

	@RequestMapping("/data/csbr3010_search_1.do")
	public ModelAndView csbr3010_search_1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3); 
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_mb_snsb2data_excel1(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_1(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3010_search_1");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/data/csbr3010_search_2.do")
	public ModelAndView csbr3010_search_2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_mb_snsb2data_excel1(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_2(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3010_search_2");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}
	
	@RequestMapping("/data/csbr3010_search_3.do")
	public ModelAndView csbr3010_search_3(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3); 
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_csbr3010_search_3(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_3(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3010_search_3");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

   //엑셀 다운받을때 
	@RequestMapping("/csbr3010_1_print.do")
	public ModelAndView csbr3010_1_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_1(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_1_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr3010_2_print.do")
	public ModelAndView csbr3010_2_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_2(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_2_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	@RequestMapping("/csbr3010_3_print.do")
	public ModelAndView csbr3010_3_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_3(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_3_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr3010_upload.do")
	public ModelAndView csbr3010_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_upload");
		mav.addObject("success", true);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3010_upload_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3010_upload_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);

				String userId = sessionMember.getId();
				String Name = sessionMember.getName();
				

				
				
				// 업로드 진행
				dataService.excelUpload(temp,0,"mb_snsb2data_excel1",userId);
				dataService.excelUpload(temp,1,"mb_snsb2data_excel2",userId);
				
				
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }

				}

			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
			
		}
	
		mav.setViewName("/include/csbr3010_upload");
		return mav;
	}

	@RequestMapping("/csbr3010_target.do")
	public ModelAndView csbr3010_target(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String name = request.getParameter("name");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_target");
		if(name==null || name.equals("")) {
			mav.addObject("list", new ArrayList<LinkedHashMap<String, Object>>());
			mav.addObject("success", true);
			return mav;
		}
		
		Map<String, String> map = new HashMap<String, String>(3); 
		map.put("name", name);
		List<LinkedHashMap<String, Object>> result = dataService.select_csbr3010_target_search(map);
		mav.addObject("list", result);
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3010_snsb.do")
	public ModelAndView csbr3010_snsb(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String action = request.getParameter("action");// create or update
		String resedate = request.getParameter("resedate");// for creating
		String object_idx = request.getParameter("id");
		String selection_num = request.getParameter("selection_num");
		
		if(action==null || action.equals("")) throw new Exception("The action parameter is required.");
		
		Map<String, String> map = new HashMap<String, String>(); 
		Map<String,Object> resultMap = null;
		map.put("object_idx", object_idx);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3010_snsb");
		if(action.equals("create")) {	// new
			mav.addObject("action","create");
			mav.addObject("resedate",resedate);
		
			resultMap = dataService.select_csbr3010_next_selection_num(map);
			if(!resultMap.containsKey("next_num")) throw new Exception("대상자 정보를 찾을수 없습니다. - object_idx="+object_idx);
			selection_num = resultMap.get("next_num").toString();
		} else if(action.equals("update")) { // update
			mav.addObject("action","update");
			
			map.put("selection_num", selection_num);
			resultMap = dataService.select_csbr3010_snsb(map);
		} else {
			throw new Exception("Invalid action. - "+action);
		}
		
		mav.addObject("object_idx", object_idx);
		mav.addObject("selection_num", selection_num);
		mav.addObject("resultMap", resultMap);
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3010_snsb_save.do")
	public ModelAndView csbr3010_snsb_save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String action = request.getParameter("action");
		if(action==null || action.equals("")) throw new Exception("The action parameter is required.");
		Map<String,String> map = new HashMap<String,String>();
		for(mb_snsb item : mb_snsb.values()) {
			String name = item.name();
			String value = request.getParameter(name);
			map.put(name, value);
		}
		
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		map.put("subject_idx", subject_idx);
		
		int result = 0;
		if(action.equals("create")) {
			String resedate = request.getParameter("resedate");
			result = this.dataService.insert_csbr3010_snsb(map);
			map = new HashMap<String,String>();
			map.put("object_idx", request.getParameter(mb_snsb.object_idx.name()));
			map.put("resedate", resedate);
			this.dataService.update_csbr3010_resesnsb(map);
		} else if(action.equals("update")) {
			result = this.dataService.update_csbr3010_snsb(map);
		} else {
			throw new Exception("Invalid action. - "+action);
		}
		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/include/csbr3010_snsb_result");
		model.addAttribute("result", result);
		model.addAttribute("success", true);

		return new ModelAndView("jsonView", model);
	}
	
	//csbr3020 pet검사관리
	@RequestMapping("/data/csbr3020_search.do")
	public ModelAndView csbr3020_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);
		System.out.println(sd+"시작페이지"+ed+"마지막페이지");
		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3); //capacity map 용량
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_mb_rctu_object_respet_suvr(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3020_search(map);
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3020_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);
		mav.addObject("test","testversion");
		return mav;
			
	}	
	
	@RequestMapping("/csbr3020_print.do")
	public ModelAndView csbr3020_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3020");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
	//	List<LinkedHashMap<String, Object>> list = dataService.select_csbr3010_search_1(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3020_print");
		mav.addObject("success", true);
	//	mav.addObject("list", list);

		return mav;
	}
	
	@RequestMapping("/csbr3020_upload.do")
	public ModelAndView csbr3020_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3020_upload");
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3020_upload_suvr.do")
	public ModelAndView csbr3020_upload_suvr(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3020_upload_suvr");
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3020_target.do")
	public ModelAndView csbr3020_target(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String name = request.getParameter("name");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3020_target");
		if(name==null || name.equals("")) {
			mav.addObject("list", new ArrayList<LinkedHashMap<String, Object>>());
			mav.addObject("success", true);
			return mav;
		}
		
		Map<String, String> map = new HashMap<String, String>(3); 
		map.put("name", name);
		List<LinkedHashMap<String, Object>> result = dataService.select_csbr3020_target_search(map);
		mav.addObject("list", result);
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3020_pet.do")
	public ModelAndView csbr3020_pet(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String action = request.getParameter("action");
		if(action==null || action.equals("")) throw new Exception("The action parameter is required.");
		
		String object_idx = request.getParameter("id");
		String selection_num = request.getParameter("selection_num");
		
		Map<String, String> map = new HashMap<String, String>(); 
		Map<String,Object> resultMap = null;
		map.put("object_idx", object_idx);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3020_pet");
		if(action.equals("create")) {	// new
			mav.addObject("action","create");
			mav.addObject("reservno",selection_num);
		
			resultMap = dataService.select_csbr3020_next_selection_num(map);
			if(!resultMap.containsKey("next_num")) throw new Exception("대상자 정보를 찾을수 없습니다. - object_idx="+object_idx);
			selection_num = resultMap.get("next_num").toString();
		} else if(action.equals("update")){ // update
			mav.addObject("action","update");
			
			map.put("selection_num", selection_num);
			resultMap = dataService.select_csbr3020_pet(map);
		} else {
			throw new Exception("Invalid action. - "+action);
		}
		
		mav.addObject("object_idx", object_idx);
		mav.addObject("selection_num", selection_num);
		mav.addObject("resultMap", resultMap);
		mav.addObject("success", true);

		return mav;
	}
	
	@RequestMapping("/csbr3020_pet_save.do")
	public ModelAndView csbr3020_pet_save(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		String action = request.getParameter("action");
		if(action==null || action.equals("")) throw new Exception("The action parameter is required.");
		String idx = request.getParameter("idx");
		String object_idx = request.getParameter("object_idx");
		String selection_num = request.getParameter("selection_num");
		String test_day = request.getParameter("test_day");
		String rctu_01 = request.getParameter("rctu_01");
		String rctu_remark = request.getParameter("rctu_remark");
		String rctu_03l = request.getParameter("rctu_03l");
		String rctu_03r = request.getParameter("rctu_03r");
		String rctu_04l = request.getParameter("rctu_04l");
		String rctu_04r = request.getParameter("rctu_04r");
		String rctu_05l = request.getParameter("rctu_05l");
		String rctu_05r = request.getParameter("rctu_05r");
		String rctu_06l = request.getParameter("rctu_06l");
		String rctu_06r = request.getParameter("rctu_06r");
		String rctu_bapl = request.getParameter("rctu_bapl");
		String reservno = request.getParameter("reservno");
		
		MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
		String subject_idx = sessionMember.getIdx(); // login user idx
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("idx", idx);
		map.put("subject_idx", subject_idx);
		map.put("object_idx", object_idx);
		map.put("selection_num", selection_num);
		map.put("rctu_01", rctu_01);
		map.put("rctu_remark", rctu_remark);
		map.put("rctu_03l", rctu_03l);
		map.put("rctu_03r", rctu_03r);
		map.put("rctu_04l", rctu_04l);
		map.put("rctu_04r", rctu_04r);
		map.put("rctu_05l", rctu_05l);
		map.put("rctu_05r", rctu_05r);
		map.put("rctu_06l",  rctu_06l);
		map.put("rctu_06r",  rctu_06r);
		map.put("rctu_bapl",rctu_bapl);
		map.put("test_day", test_day);
		
		int result = 0;
		if(action.equals("create")) {
			result = this.dataService.insert_csbr3020_pet(map);
			map = new HashMap<String,String>();
			map.put("object_idx", object_idx);
			map.put("selection_num", selection_num);
			map.put("reservno", reservno);
			this.dataService.update_csbr3020_resepet(map);
		} else if(action.equals("update")) {
			result = this.dataService.update_csbr3020_pet(map);
		} else {
			throw new Exception("Invalid action. - "+action);
		}
		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/include/csbr3020_pet_result");
		model.addAttribute("result", result);
		model.addAttribute("success", true);

		return new ModelAndView("jsonView", model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3020_upload_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3020_upload_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				// mb_object idx == object_idx 값 같음
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String userId = sessionMember.getId();
				// 업로드 진행
				dataService.excelUpload(temp,0,"mb_snsb2data_excel1",userId);
				dataService.excelUpload(temp,1,"mb_snsb2data_excel2",userId);
				
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }

				}
			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
		}
	
		mav.setViewName("/include/csbr3020_upload");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3020_upload_suvr_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3020_upload_suvr_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
	
		try {
			
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				// mb_object idx == object_idx 값 같음
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String userId = sessionMember.getId();
				// 업로드 진행
				dataService.excelUploadSuvrScore(temp,userId);
				
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }

				}
			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
		}
	
		mav.setViewName("/include/csbr3020_upload");
		return mav;
	}
	
	@RequestMapping("/csbr3030_upload.do")
	public ModelAndView csbr3030_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		System.out.println("csbr3030_upload.do 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3030_upload");
		mav.addObject("success", true);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3030_upload_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3030_upload_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("CSBR3030 CSF 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
	
		try {
			
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				// mb_object idx == object_idx 값 같음
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String userId = sessionMember.getId();
				// 업로드 진행
				this.dataService.excelUploadCSF(temp, userId);
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }
				}
			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
		}
	
		mav.setViewName("/include/csbr3030_upload");
		return mav;
	}
	
	@RequestMapping("/data/csbr3030_search.do")
	public ModelAndView csbr3030_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);
		System.out.println(sd+"시작페이지"+ed+"마지막페이지");
		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3); //capacity map 용량
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_csbr3030_csf(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3030_search(map);
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3030_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);
		mav.addObject("test","testversion");
		return mav;
	}
	
	@RequestMapping("/csbr3030_print.do")
	public ModelAndView csbr3030_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3030");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3030_search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3030_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	@RequestMapping("/csbr3060_upload.do")
	public ModelAndView csbr3060_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		System.out.println("csbr3060_upload.do 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3060_upload");
		mav.addObject("success", true);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3060_upload_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3060_upload_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("CSBR3060 InBody 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
		
		try {
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				// mb_object idx == object_idx 값 같음
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String userId = sessionMember.getId();
				// 업로드 진행
				this.dataService.excelUploadInBody(temp, userId);
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }
				}
			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
		}
	
		mav.setViewName("/include/csbr3060_upload");
		return mav;
	}
	
	@RequestMapping("/data/csbr3060_search.do")
	public ModelAndView csbr3060_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String type = request.getParameter("type");
		if(type==null) throw new RuntimeException("The type parameter is required.");
		System.out.println("csbr3060_search : "+type);
		
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");
		
		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);
		System.out.println(sd+"시작페이지"+ed+"마지막페이지");
		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}
		
		Map<String, String> map = new HashMap<String, String>(3); //capacity map 용량
		map.put("sd", sd);
		map.put("ed", ed);
		int count = 0; 
		if("inbody1".equals(type)) {
			count = dataService.select_count_csbr3060_inbody1(map);
		} else if("inbody2".equals(type)) {
			count = dataService.select_count_csbr3060_inbody2(map);
		} else {
			throw new RuntimeException("Invalid type - type="+type);
		}
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = null;
		if("inbody1".equals(type)) {
			list = dataService.select_csbr3060_search1(map);
		} else if("inbody2".equals(type)) {
			list = dataService.select_csbr3060_search2(map);
		} else {
			throw new RuntimeException("Invalid type - type="+type);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3060_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);
		mav.addObject("test","testversion");
		return mav;
	}
	
	@RequestMapping("/csbr3060_print.do")
	public ModelAndView csbr3060_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		String type  = request.getParameter("type");
		if(type==null) throw new RuntimeException("The type parameter is required.");
		System.out.println("csbr3060_print : "+type);
		
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3060");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = null;
		if("inbody1".equals(type)) {
			list = dataService.select_csbr3060_search1(map);
		} else if("inbody2".equals(type)) {
			list = dataService.select_csbr3060_search2(map);
		} else {
			throw new RuntimeException("Invalid type - type="+type);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3060_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("type", type);

		return mav;
	}
	
	@RequestMapping("/csbr3080_upload.do")
	public ModelAndView csbr3080_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		System.out.println("csbr3080_upload.do 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3080_upload");
		mav.addObject("success", true);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr3080_upload_excel.do", method = RequestMethod.POST)
	public ModelAndView csbr3080_upload_excel(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("CSBR3080 APOE 값들어왔다-----------------------------------------------");
		ModelAndView mav = new ModelAndView();
	
		try {
			
			if (testFile != null) {
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("excelDir");
				
				if (!FileUtils.makeDir(docDir)) {
					throw new Exception(docDir + " 폴더 생성이 실패하였습니다. 관리자에게 문의하세요.");
				}

				File temp = new File(ResourceBundleUtil.getResourceBundle("excelDir") + fileNameWOExtenstion + "__"
						+ DateUtil.getShortDateTimeStampString() + fileExtension);

				testFile.transferTo(temp);
				
				// mb_object idx == object_idx 값 같음
				MemberVO sessionMember = (MemberVO) session.getAttribute(Constant.SESSION_MEMBER);
				String userId = sessionMember.getId();
				// 업로드 진행
				this.dataService.excelUploadAPOE(temp, userId);
				//파일삭제
				if( temp.exists() ){
					if(temp.delete()){ System.out.println("파일삭제 성공"); }else{ System.out.println("파일삭제 실패"); }
				}
			}else {
				throw new Exception("비어있는 엑셀파일입니다. 데이터를 입력 후 업로드 해주세요.");
			}
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
		}
	
		mav.setViewName("/include/csbr3080_upload");
		return mav;
	}
	
	//csbr3080 pet검사관리
	@RequestMapping("/data/csbr3080_search.do")
	public ModelAndView csbr3080_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String page = request.getParameter("page");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);
		System.out.println(sd+"시작페이지"+ed+"마지막페이지");
		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		if ( StringUtil.isEmptyString(page) || Integer.parseInt(page) < 1 ) {
			page = "1";
		}

		Map<String, String> map = new HashMap<String, String>(3); //capacity map 용량
		map.put("sd", sd);
		map.put("ed", ed);
		int count = dataService.select_count_csbr3080_apoe(map);
		int total_num = count;
		int per_page = 50;
		int total_page =  (int) (Math.floor((total_num-1)/per_page) + 1);
		if(Integer.parseInt(page) > total_page) {
			page = total_page + "";
		}
		String limit_query = "limit " + ((Integer.parseInt(page)-1) * per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3080_search(map);
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr3080_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);
		mav.addObject("test","testversion");
		return mav;
	}	
	
	@RequestMapping("/csbr3080_print.do")
	public ModelAndView csbr3080_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR3080");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr3080_search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr3080_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	@RequestMapping("/data/csbr4010_left_data.do")
	public ModelAndView csbr4010_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resemri");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr4010_action.do")
	public ModelAndView csbr4010_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String k, rowdel, testdate, object_idx;
		String[] sp;

		String htmlTag = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			rowdel = request.getParameter("del" + i);

			if(rowdel == null || rowdel =="") {
				rowdel = "0";
			}

			if ( !StringUtil.isEmptyString(k) && "1".equals(rowdel) ) {
				sp = k.split("\\|");
				testdate = sp[0];
				object_idx = sp[1];

				Map<String, String> map = new HashMap<String, String>(2);
				map.put("testdate", testdate);
				map.put("objectIdx", object_idx);
				String sqlCntResult = dataService.delete_csbr4010_mb_testblod(map);

				String[] arrDelResult = sqlCntResult.split("/");
				String query = arrDelResult[0];
				int rtnCnt = Integer.parseInt(arrDelResult[1]);
				if(rtnCnt > 0) {
					logger.debug("csbr4010_action : " + rtnCnt);
				}
				htmlTag += query + "<br>";

			}
			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr4010_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/data/csbr4010_content.do")
	public ModelAndView csbr4010_content(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String exam_day = request.getParameter("exam_day").replaceAll("-", "");
		String object_idx = request.getParameter("object_idx");

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("resedate", exam_day);
		map.put("objectIdx", object_idx);
		List<MbMriTestblodVO> list = dataService.select_csbr4010_content(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr4010_content");
		mav.addObject("success", true);
		mav.addObject("exam_day", exam_day);
		mav.addObject("list", list);

		return mav;
	}

	// 수정
	@RequestMapping("/csbr4010_content_action.do")
	public ModelAndView csbr4010_content_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("MbMriTestblodVO")MbMriTestblodVO mtvo, ModelMap model) throws Exception {

		String object_idx = request.getParameter("object_idx");
		String testdate = request.getParameter("testdate");
		
		if ( StringUtil.isEmptyString(object_idx) || StringUtil.isEmptyString(testdate) ) return null;

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("resedate", testdate);
		map.put("objectIdx", object_idx);
		List<MbMriTestblodVO> list = dataService.select_csbr4010_content(map);
		MbMriTestblodVO vo = list.get(0); //조회

		map.put("testdate", testdate);
		int count = dataService.select_count_mb_testblod(map);

		Method[] voMethods = vo.getClass().getMethods();
		Method[] mtvoMethods = mtvo.getClass().getMethods();

		String setName = "";
		String value = "";
		for(Method mtvoMethod : mtvoMethods){
			if(mtvoMethod.getName().startsWith("get")){
				try{
					value = (String) mtvoMethod.invoke(mtvo);
				}catch(Exception e){
					System.out.println(mtvoMethod.getName() + ":" + e.getStackTrace());
				}
				if(value != null){
					setName = mtvoMethod.getName().replace("get", "set");
					for(Method voMethod : voMethods){
						if(setName.equals(voMethod.getName())) {
							try{
								voMethod.invoke(vo, new Object[] { value } ); //조회한 값에 수정 또는 등록할 데이터 추가 저장 (setter 실행)
							}catch(Exception e){
								System.out.println(voMethod.getName() + ":" + e.getStackTrace());
							}
						}
					}
				}
			}
		}
		
		mtvo.setTestdate(testdate);
		mtvo.setUserId(Constant.USER.get("ID"));
		mtvo.setResedate(vo.getResedate());
		mtvo.setResetime(vo.getResetime());
		mtvo.setRemark(vo.getRemark());
		mtvo.setTestyn(vo.getTestyn());
		mtvo.setModifyusid(vo.getModifyusid());
		mtvo.setModifydate(vo.getModifydate());
		mtvo.setMriid(vo.getMriid());
		
		if(StringUtil.isEmptyString((String) mtvo.getRemark())){ //null 인 경우 "" - not null 때문
			mtvo.setRemark(" ");
		}
		if(StringUtil.isEmptyString((String) mtvo.getAbo())){ //null 인 경우 "" - not null 때문
			mtvo.setAbo(" ");
		}
		if(StringUtil.isEmptyString((String) mtvo.getMridiag())){ //null 인 경우 "" - not null 때문
			mtvo.setMridiag(" ");
		}
		if(StringUtil.isEmptyString((String) mtvo.getPetdiag())){ //null 인 경우 "" - not null 때문
			mtvo.setPetdiag(" ");
		}
		if(StringUtil.isEmptyString((String) mtvo.getMriid())){ //null 인 경우 "" - not null 때문
			mtvo.setMriid(" ");
		}
		
		String sqlCntResult = "";

		System.out.println(mtvo);
		
		
		if ( count > 0  ) { //기존에 있으면 update
			sqlCntResult = dataService.update_csbr4010_mb_testblod(mtvo);
		}else {
			sqlCntResult = dataService.insert_csbr4010_mb_testblod(mtvo);
		}

		String[] arrDelResult = sqlCntResult.split("/");
		String query = arrDelResult[0];
		int rtnCnt = Integer.parseInt(arrDelResult[1]);
		if(rtnCnt > 0) {
			logger.debug("csbr4010_content_action : " + rtnCnt);
		}
		String htmlTag = query;

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr4010_content_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr4010_print.do")
	public ModelAndView csbr4010_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			//엑셀 출력 기록 저장
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR4010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("resedate", d);
		List<MbMriTestblodVO> list = dataService.select_csbr4010_content(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr4010_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("d", d);
		mav.addObject("dtext", dtext);

		return mav;
	}

	@RequestMapping("/data/csbr4010_list_data.do")
	public ModelAndView csbr4010_list_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");
		//String edit = request.getParameter("edit");

		ModelAndView mav = new ModelAndView();

		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyyMMdd", d);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("resedate", d);
			map.put("orderBy", "ORDER BY RESETIME, MRIID");
			List<MbMriTestblodVO> list = dataService.select_csbr4010_content(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}


		mav.setViewName("/include/data/csbr4010_list_data");
		mav.addObject("success", true);

		return mav;
	}

	@RequestMapping("/csbr5020_info_action.do")
	public ModelAndView csbr5020_info_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String savedate = request.getParameter("savedate");
		String saveid = request.getParameter("saveid");

		if ( StringUtil.isEmptyString(savedate) || StringUtil.isEmptyString(saveid) ) return null;

		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);

		String htmlTag = savedate + "-" + saveid + "<br>";

		String k, bcd, scd, loc, bc, tran, rowdel;
		double sunit, squnt, sqtot;
		String key_date, key_object_idx, key_no;
		String[] sp;
		String sqlCntResult = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			bcd = request.getParameter("bclasscd" + i);
			scd = request.getParameter("storagecd" + i);
			loc = request.getParameter("location" + i);
			try {
				sunit = Double.parseDouble((String) request.getParameter("specunit" + i));
			}catch (NumberFormatException e) {
				sunit = 0;
			}
			try {
				squnt = Double.parseDouble((String) request.getParameter("specqunt" + i));
			}catch (NumberFormatException e) {
				squnt = 0;
			}
			try {
				sqtot = Double.parseDouble((String) request.getParameter("specqtot" + i));
			}catch (NumberFormatException e) {
				sqtot = 0;
			}
			bc = request.getParameter("bcolor" + i);
			tran = request.getParameter("transparen" + i);
			rowdel = request.getParameter("del" + i);

			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}

			MbSpecimdtMbCsfdtVO msvo = new MbSpecimdtMbCsfdtVO();
			msvo.setBclasscd(bcd);
			msvo.setStoragecd(scd);
			msvo.setLocation(loc);
			msvo.setSpecunit(sunit);
			msvo.setSpecqunt(squnt);
			msvo.setSpecqtot(sqtot);
			msvo.setBcolor(bc);
			msvo.setTransparen(tran);
			msvo.setModifyusid(Constant.USER.get("ID"));

			if ( !StringUtil.isEmptyString(k) ) {

				sp = k.split("\\|");
				key_date = sp[0];
				key_object_idx = sp[1];
				key_no = sp[2];

				msvo.setSpecdate(key_date);
				msvo.setObjectIdx(key_object_idx);
				msvo.setSpdtsqno(key_no);

				if ( "1".equals(rowdel) ) { //삭제면
					sqlCntResult = dataService.delete_csbr5020_mb_specimdt(msvo);
				} else { //삭제가 아니면 수정
					sqlCntResult = dataService.update_csbr5020_mb_specimdt(msvo);
				}
			} else { //키가 없으면 삽입
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(saveid)
						&& (!StringUtil.isEmptyString(bcd) || !StringUtil.isEmptyString(scd) || !ObjectUtils.isEmpty(sunit) || !ObjectUtils.isEmpty(squnt) || !ObjectUtils.isEmpty(sqtot) || !StringUtil.isEmptyString(bc))) {
					msvo.setSpecdate(savedate);
					msvo.setObjectIdx(saveid);

					htmlTag += k + "|" + bcd + "|" + scd + "|" + loc + "|" + sunit + "|" + squnt + "|" + sqtot + "|" + bc + "|" + tran + "|" + d + "<br>";
					sqlCntResult = dataService.insert_csbr5020_mb_specimdt(msvo);
				}
			}

			if(!StringUtil.isEmptyString(sqlCntResult)) {
				String[] arrSqlCntResult = sqlCntResult.split("/");
				String query = arrSqlCntResult[0];
				int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
				if(rtnCnt > 0) {
					logger.debug("csbr5020_info_action : " + rtnCnt);
				}
				htmlTag += query + "<br>";
			}

			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5020_info_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/data/csbr5020_info_data.do")
	public ModelAndView csbr5020_info_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String id = request.getParameter("id");

		if ( StringUtil.isEmptyString(d) || StringUtil.isEmptyString(id) ) return null;

		if( !StringUtil.isEmptyString(d) ) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		//bclass 항목 추출
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("kdcd", "A001");
		map.put("unuseyn", "N");
		map.put("orderby", "ORDER BY SORTNO, CDBS");
		List<MbEtcCdVO> bclassList = commonService.select_mb_etccd_list(map);

		Map<String, String> BCLASSCD = new HashMap<String, String>();
		Map<String, String> STORAGECD = new HashMap<String, String>();

		for(MbEtcCdVO vo : bclassList) {
			String code = vo.getCDBS();
			String name = vo.getCDNM();
			BCLASSCD.put(code, name);
		}

		//storage 항목 추출
		map.put("kdcd", "A002");
		//위에서 이미 넣음
		//map.put("unuseyn", "N");
		//map.put("orderby", "ORDER BY SORTNO, CDBS");
		List<MbEtcCdVO> storageList = commonService.select_mb_etccd_list(map);

		for(MbEtcCdVO vo : storageList) {
			String code = vo.getCDBS();
			String name = vo.getCDNM();
			STORAGECD.put(code, name);
		}

		map = new HashMap<String, String>(2);
		map.put("specdate", d);
		map.put("objectIdx", id);
		List<MbSpecimdtMbCsfdtVO> list = dataService.select_csbr5020_mb_specimdt(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr5020_info_data");
		mav.addObject("success", true);
		mav.addObject("d", d);
		mav.addObject("BCLASSCD", BCLASSCD);
		mav.addObject("STORAGECD", STORAGECD);
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/data/csbr5020_left_data.do")
	public ModelAndView csbr5020_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr5020_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr5020_right_data.do")
	public ModelAndView csbr5020_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");

		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyyMMdd", d);

		ModelAndView mav = new ModelAndView();

		List<Map<String, String>> list = dataService.select_csbr5020_right_data(d);
		mav.addObject("list", list);
		mav.addObject("d", d);

		mav.setViewName("/include/data/csbr5020_right_data");
		mav.addObject("success", true);

		return mav;
	}

	@RequestMapping("/csbr5020_right_action.do")
	public ModelAndView csbr5020_right_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");

		if ( StringUtil.isEmptyString(savedate) ) return null;

		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);

		String htmlTag = savedate + "<br>";

		String k, oidx, bid, stv, dd, fol, obid, cta, rem;
		String sqlCntResult = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			oidx = request.getParameter("object_idx" + i);
			bid = request.getParameter("bloodid" + i).replaceAll("-", "");
			stv = request.getParameter("starve" + i);
			dd = request.getParameter("damdang" + i);
			fol = request.getParameter("followup" + i);
			obid = request.getParameter("ownbloodid" + i);
			cta = request.getParameter("collectagency" + i);
			rem = request.getParameter("remark" + i);

			MbSpecimhdVO msvo = new MbSpecimhdVO();
			msvo.setObjectIdx(oidx);
			msvo.setBloodid(bid);
			msvo.setStarve(stv);
			msvo.setDamdang(dd);
			msvo.setFollowup(fol);
			msvo.setOwnbloodid(obid);
			msvo.setCollectagency(cta);
			msvo.setRemark(rem);
			msvo.setModifyusid(Constant.USER.get("ID"));

			if ( !StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(oidx) ) {
				//기존에 있는지 확인
				Map<String, String> map = new HashMap<String, String>(2);
				map.put("specdate", savedate);
				map.put("objectIdx", oidx);
				sqlCntResult = dataService.select_count_csbr5020_mb_specimhd(map);
				msvo.setSpecdate(savedate);

				if(!StringUtil.isEmptyString(sqlCntResult)) {
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) { //기존에 있으면 수정
						logger.debug("csbr5020_right_action : " + rtnCnt);
						sqlCntResult = dataService.update_csbr5020_mb_specimhd(msvo);
					}else { //없으면 삽입
						sqlCntResult = dataService.insert_csbr5020_mb_specimhd(msvo);
					}
					htmlTag += k + "|" + oidx + "|" + bid + "|" + stv + "|" + dd + "|" + fol + "|" + obid + "|" + cta + "|" + rem + "<br>";
					htmlTag += query + "<br>";
				}
			}

			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5020_right_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr5020_print.do")
	public ModelAndView csbr5020_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			//엑셀 출력 기록 저장
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR5020"); //CSBR2010
			commonService.insert_mb_usedlog(map);
		}

		List<Map<String, String>> list = dataService.select_csbr5020_right_data(d);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5020_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("d", d);
		mav.addObject("dtext", dtext);

		return mav;
	}

	@RequestMapping("/csbr5040_info_action.do")
	public ModelAndView csbr5040_info_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		String saveid = request.getParameter("saveid");

		if ( StringUtil.isEmptyString(savedate) || StringUtil.isEmptyString(saveid) ) return null;

		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);

		String htmlTag = savedate + "-" + saveid + "<br>";

		String k, bcd, scd, loc, bc, tran, rowdel;
		double sunit, squnt, sqtot;
		String key_date, key_object_idx, key_no;
		String[] sp;
		String sqlCntResult = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			bcd = request.getParameter("bclasscd" + i);
			scd = request.getParameter("storagecd" + i);
			loc = request.getParameter("location" + i);
			try {
				sunit = Double.parseDouble((String) request.getParameter("specunit" + i));
			}catch (NumberFormatException e) {
				sunit = 0;
			}
			try {
				squnt = Double.parseDouble((String) request.getParameter("specqunt" + i));
			}catch (NumberFormatException e) {
				squnt = 0;
			}
			try {
				sqtot = Double.parseDouble((String) request.getParameter("specqtot" + i));
			}catch (NumberFormatException e) {
				sqtot = 0;
			}
			bc = request.getParameter("bcolor" + i);
			tran = request.getParameter("transparen" + i);
			rowdel = request.getParameter("del" + i);

			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}

			MbSpecimdtMbCsfdtVO msvo = new MbSpecimdtMbCsfdtVO();
			msvo.setBclasscd(bcd);
			msvo.setStoragecd(scd);
			msvo.setLocation(loc);
			msvo.setSpecunit(sunit);
			msvo.setSpecqunt(squnt);
			msvo.setSpecqtot(sqtot);
			msvo.setBcolor(bc);
			msvo.setTransparen(tran);
			msvo.setModifyusid(Constant.USER.get("ID"));

			if ( !StringUtil.isEmptyString(k) ) {

				sp = k.split("\\|");
				key_date = sp[0];
				key_object_idx = sp[1];
				key_no = sp[2];

				msvo.setSpecdate(key_date);
				msvo.setObjectIdx(key_object_idx);
				msvo.setSpdtsqno(key_no);

				if ( "1".equals(rowdel) ) { //삭제면
					sqlCntResult = dataService.delete_csbr5040_mb_csfdt(msvo);
				} else { //삭제가 아니면 수정
					sqlCntResult = dataService.update_csbr5040_mb_csfdt(msvo);
				}
			} else { //키가 없으면 삽입
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(saveid)
						&& (!StringUtil.isEmptyString(bcd) || !StringUtil.isEmptyString(scd) || !ObjectUtils.isEmpty(sunit) || !ObjectUtils.isEmpty(squnt) || !ObjectUtils.isEmpty(sqtot) || !StringUtil.isEmptyString(bc))) {
					msvo.setSpecdate(savedate);
					msvo.setObjectIdx(saveid);

					htmlTag += k + "|" + bcd + "|" + scd + "|" + loc + "|" + sunit + "|" + squnt + "|" + sqtot + "|" + bc + "|" + tran + "<br>";
					sqlCntResult = dataService.insert_csbr5040_mb_csfdt(msvo);
				}
			}

			if(!StringUtil.isEmptyString(sqlCntResult)) {
				String[] arrSqlCntResult = sqlCntResult.split("/");
				String query = arrSqlCntResult[0];
				int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
				if(rtnCnt > 0) {
					logger.debug("csbr5040_info_action : " + rtnCnt);
				}
				htmlTag += query + "<br>";
			}

			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5040_info_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/data/csbr5040_info_data.do")
	public ModelAndView csbr5040_info_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String id = request.getParameter("id");
		String add = request.getParameter("add");

		if ( StringUtil.isEmptyString(d) || StringUtil.isEmptyString(id) ) return null;

		if( !StringUtil.isEmptyString(d) ) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		//bclass 항목 추출
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("kdcd", "A019");
		map.put("unuseyn", "N");
		map.put("orderby", "ORDER BY SORTNO, CDBS");
		List<MbEtcCdVO> bclassList = commonService.select_mb_etccd_list(map);

		Map<String, String> BCLASSCD = new HashMap<String, String>();
		Map<String, String> STORAGECD = new HashMap<String, String>();

		for(MbEtcCdVO vo : bclassList) {
			String code = vo.getCDBS();
			String name = vo.getCDNM();
			BCLASSCD.put(code, name);
		}

		//storage 항목 추출
		map.put("kdcd", "A002");
		//위에서 이미 넣음
		//map.put("unuseyn", "N");
		//map.put("orderby", "ORDER BY SORTNO, CDBS");
		List<MbEtcCdVO> storageList = commonService.select_mb_etccd_list(map);

		for(MbEtcCdVO vo : storageList) {
			String code = vo.getCDBS();
			String name = vo.getCDNM();
			STORAGECD.put(code, name);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr5040_info_data");
		mav.addObject("success", true);
		mav.addObject("d", d);
		mav.addObject("BCLASSCD", BCLASSCD);
		mav.addObject("STORAGECD", STORAGECD);

		if(!"true".equals(add)) {
			map = new HashMap<String, String>(2);
			map.put("specdate", d);
			map.put("objectIdx", id);
			List<MbSpecimdtMbCsfdtVO> list = dataService.select_csbr5040_mb_csfdt(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}

	@RequestMapping("/data/csbr5040_left_data.do")
	public ModelAndView csbr5040_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr5040_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr5040_right_data.do")
	public ModelAndView csbr5040_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");

		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyyMMdd", d);

		ModelAndView mav = new ModelAndView();

		List<Map<String, String>> list = dataService.select_csbr5040_right_data(d);
		mav.addObject("list", list);
		mav.addObject("d", d);

		mav.setViewName("/include/data/csbr5040_right_data");
		mav.addObject("success", true);

		return mav;
	}

	@RequestMapping("/csbr5040_right_action.do")
	public ModelAndView csbr5040_right_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");

		if ( StringUtil.isEmptyString(savedate) ) return null;

		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);

		String htmlTag = savedate + "<br>";

		String k, oidx, cid, dd, rem;
		String sqlCntResult = "";

		int i = 1;
		while(request.getParameter("key" + i) != null) {
			k = request.getParameter("key" + i);
			oidx = request.getParameter("object_idx" + i);
			cid = request.getParameter("csfid" + i).replaceAll("-", "");
			dd = request.getParameter("damdang" + i);
			rem = request.getParameter("remark" + i);

			MbCsfhdVO mcvo = new MbCsfhdVO();
			mcvo.setObjectIdx(oidx);
			mcvo.setCsfid(cid);
			mcvo.setDamdang(dd);
			mcvo.setRemark(rem);
			mcvo.setModifyusid(Constant.USER.get("ID"));

			if ( !StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(oidx) ) {
				//기존에 있는지 확인
				Map<String, String> map = new HashMap<String, String>(2);
				map.put("specdate", savedate);
				map.put("objectIdx", oidx);
				sqlCntResult = dataService.select_count_csbr5040_mb_csfhd(map);
				mcvo.setSpecdate(savedate);

				if(!StringUtil.isEmptyString(sqlCntResult)) {
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) { //기존에 있으면 수정
						logger.debug("csbr5040_right_action : " + rtnCnt);
						sqlCntResult = dataService.update_csbr5040_mb_csfhd(mcvo);
					}else { //없으면 삽입
						sqlCntResult = dataService.insert_csbr5040_mb_csfhd(mcvo);
					}
					htmlTag += k + "|" + oidx + "|" + cid + "|" + dd + "|" + rem + "<br>";
					htmlTag += query + "<br>";
				}
			}

			i++;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5040_right_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr5040_print.do")
	public ModelAndView csbr5040_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			//엑셀 출력 기록 저장
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR5040"); //CSBR2010
			commonService.insert_mb_usedlog(map);
		}

		List<Map<String, String>> list = dataService.select_csbr5040_right_data(d);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr5040_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("d", d);
		mav.addObject("dtext", dtext);

		return mav;
	}


	@RequestMapping("/data/csbr7001_search.do")
	public ModelAndView csbr7001_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7001_search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr7001_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr7001_print.do")
	public ModelAndView csbr7001_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7001");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
//		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7001_search(map);
		//List<LinkedHashMap<String, Object>> list = dataService.select_csbr7001_search2(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7001_print");
		mav.addObject("success", true);
//		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		
		return mav;
	}

	@RequestMapping("/data/csbr7003_search.do")
	public ModelAndView csbr7003_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7003_search();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr7003_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr7003_print.do")
	public ModelAndView csbr7003_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7003");
			commonService.insert_mb_usedlog(map);
		}

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7003_search();

		System.out.println("list -> "+list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7003_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr7003_chart.do")
	public ModelAndView csbr7003_chart(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7003_search();

		String category_data = "";
		String full_data = "";
		String male_data = "";
		String female_data = "";

		int age = 0;
		double male = 0.0;
		double female = 0.0;

		for(int i=0; i<list.size(); i++){
			age = (list.get(i).get("AGE") == null ? 0 : Integer.parseInt(list.get(i).get("AGE").toString()));
			male = (list.get(i).get("M") == null ? 0.0 : Double.parseDouble(list.get(i).get("M").toString()));
			female = (list.get(i).get("F") == null ? 0.0 : Double.parseDouble(list.get(i).get("F").toString()));

			if ( category_data != "" ) category_data += ",";
			category_data += age;

			if ( full_data != "" ) full_data += ",";
			full_data += "[" + age + "," + 30 + "]";

			if ( male_data != "" ) male_data += ",";
			male_data += "[" + age + "," + male + "]";

			if ( female_data != "" ) female_data += ",";
			female_data += "[" + age + "," + female + "]";
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7003_chart");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("full_data", full_data);
		mav.addObject("male_data", male_data);
		mav.addObject("female_data", female_data);

		return mav;
	}

	@RequestMapping("/data/csbr7004_search.do")
	public ModelAndView csbr7004_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		int total_num = dataService.select_count_csbr7004_mb_specimhd();
		int per_page = 100; //페이지당 100건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;

		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7004_search(limit_query);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7004_print.do")
	public ModelAndView csbr7004_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7004");
			commonService.insert_mb_usedlog(map);
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		int total_num = dataService.select_count_csbr7004_mb_specimhd();
		int per_page = 100; //페이지당 100건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		
		System.out.println(total_page);
		
		if ( page > total_page ) page = total_page;

		//String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		
		//System.out.println(limit_query);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7004_search(null);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7004_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr7005_search.do")
	public ModelAndView csbr7005_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7005_mb_object(map);
		int per_page = 100; //페이지당 100건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;

		map.put("limitQuery", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7005_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7005_print.do")
	public ModelAndView csbr7005_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7005");
			commonService.insert_mb_usedlog(map);
		}

		
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		//map.put("limitQuery", "");
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7005_search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7005_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}

	@RequestMapping("/data/csbr7006_search.do")
	public ModelAndView csbr7006_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		int total_num = dataService.select_count_csbr7006_mb_object();
		int per_page = 100; //페이지당 100건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7006_search(limit_query);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr7006_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7006_print.do")
	public ModelAndView csbr7006_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7006");
			commonService.insert_mb_usedlog(map);
		}

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7006_search(null);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7006_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/data/csbr7007_search.do")
	public ModelAndView csbr7007_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);

		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7007_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr7007_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr7007_print.do")
	public ModelAndView csbr7007_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7006");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7007_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7007_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}

	@RequestMapping("/data/csbr7008_search.do")
	public ModelAndView csbr7008_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7008_mb_snsb(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7008_search(map);		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7008_print.do")
	public ModelAndView csbr7008_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7008");
			commonService.insert_mb_usedlog(map);
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7008_mb_snsb(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		//String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		//map.put("limit_query", limit_query);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7008_search(map);	

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7008_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}

	@RequestMapping("/data/csbr7009_search.do")
	public ModelAndView csbr7009_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7009_mb_dm(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7009_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7009_print.do")
	public ModelAndView csbr7009_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7009");
			commonService.insert_mb_usedlog(map);
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7009_mb_dm(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		//String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		//map.put("limit_query", limit_query);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7009_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7009_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}

	@RequestMapping("/data/csbr7010_search.do")
	public ModelAndView csbr7010_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7010_mb_object(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7010_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7010_print.do")
	public ModelAndView csbr7010_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7010");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		map.put("flag", xls!=null && xls.equals("1") ? "xls" : "data");
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7010_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7010_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}

	@RequestMapping("/data/csbr7011_search.do")
	public ModelAndView csbr7011_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		int page = 0;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception ep) {
			page = 1;
		}

		if ( page < 1 ) page = 1;

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		int total_num = dataService.select_count_csbr7011_mb_object(map);
		int per_page = 50; //페이지당 50건
		int total_page = (int) (Math.floor((total_num-1)/per_page)+1);
		if ( page > total_page ) page = total_page;
		String limit_query = "limit " + ((page-1)*per_page) + "," + per_page;
		map.put("limit_query", limit_query);
		
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7011_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr70_common_search");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("page", page);
		mav.addObject("total_page", total_page);

		return mav;
	}

	@RequestMapping("/csbr7011_print.do")
	public ModelAndView csbr7011_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR7011");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("ed", ed);
		List<LinkedHashMap<String, Object>> list = dataService.select_csbr7011_search(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr7011_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("sd", sd);
		mav.addObject("ed", ed);
		return mav;
	}


	@RequestMapping("/data/csbr9020_left_data.do")
	public ModelAndView csbr9020_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String searchid = request.getParameter("searchid");
		List<Map<String, String>> list = dataService.select_csbr9020_left_data(searchid);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9020_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr9020_right_data.do")
	public ModelAndView csbr9020_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9020_right_data");
		mav.addObject("success", true);

		String id = request.getParameter("id");
		if ( !StringUtil.isEmptyString(id)) {
			List<Map<String, String>> list = dataService.select_csbr9020_right_data(id);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		List<Map<String, String>> list2 = dataService.select_csbr9020_right_data2(id);
		mav.addObject("list2", list2);

		return mav;
	}

	@RequestMapping("/data/csbr9020_nrcd_data.do")
	public ModelAndView csbr9020_nrcd_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9020_nrcd_data");
		mav.addObject("success", true);

		String id = request.getParameter("id");

		if ( StringUtil.isEmptyString(id)) return null;

		List<Map<String, String>> list = dataService.select_csbr9020_nrcd_data(id);
		List<Map<String, String>> list2 = dataService.select_csbr9020_nrcd_data2(id);
		mav.addObject("list", list);
		mav.addObject("list2", list2);

		return mav;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/csbr9020_action.do")
	public ModelAndView csbr9020_action(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param, HttpSession session,
			ModelMap model) throws Exception {

		String htmlTag = "";

		String add = request.getParameter("add");
		String userid = request.getParameter("userid");

		String username = param.get("username");
		String userauth = param.get("userauth");
		String userpw = param.get("userpw");
		String usertelno = param.get("usertelno");
		String useridx = param.get("useridx");
		String userremark = param.get("userremark");
		String useruseyn = param.get("useruseyn");

		htmlTag += add + userid + username + userauth + userpw + usertelno + useridx + userremark + useruseyn + "<br>";

		if ( "1".equals(add) ) { //신규이면
			int resultCnt = dataService.select_count_mb_subject(userid);

			if(resultCnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 존재하는 아이디입니다.');</script>");
				out.flush();
			}
		}

		//정보추출
		Map<String, String> row = dataService.select_mb_subject(userid);

		String sqlCntResult = "";
		if(StringUtil.isEmptyString(useruseyn)) {
			param.put("useruseyn", "N");
		}

		if(row != null && userid.equals(row.get("id"))) { //수정
			sqlCntResult = dataService.update_mb_subject(param);
		}else { //삽입
			sqlCntResult = dataService.insert_mb_subject(param);
		}

		String[] arrSqlCntResult = sqlCntResult.split("/");
		String query = arrSqlCntResult[0];
		int rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
		htmlTag += query + "<br>";

		if(rtnCnt <= 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('사용자 등록/수정중 오류발생! 개발자에게 문의바랍니다.');</script>");
			out.flush();
		}else {
			logger.debug("csbr9020_action : " + rtnCnt);
		}

		boolean ox = false;
		Enumeration names = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>(3);

		while(names.hasMoreElements()) {
			String key = (String) names.nextElement();
			if(key.indexOf("progid") > -1) {
				String k = key.replace("progid", "");
				String pid = request.getParameter(key).toUpperCase(); //대문자로
				String chk = request.getParameter("useyn" + k);
				map.put("puserid", userid);
				map.put("progid", pid);
				int resultCnt = dataService.select_count_mb_pempower(map);
				if(resultCnt > 0) ox = true;
				else ox = false;

				map.put("modifyusid", Constant.USER.get("ID"));

				if(!"Y".equals(chk)) {
					if(ox == true) //기존에 있으면 삭제
						sqlCntResult = dataService.delete_mb_pempower(map);
				}else{
					if(ox == true) { //기존에 있으면 update
						sqlCntResult = dataService.update_mb_pempower(map);
					}else { //기존에 없으면 insert
						sqlCntResult = dataService.insert_mb_pempower(map);
					}
				}

				arrSqlCntResult = sqlCntResult.split("/");
				query = arrSqlCntResult[0];

				htmlTag += k + pid + chk + "<br>";
				htmlTag += query + "<br>";
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9020_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr9020_nrcd_action.do")
	public ModelAndView csbr9020_nrcd_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String sel_id = request.getParameter("sel_id");
		if(StringUtil.isEmptyString(action) || StringUtil.isEmptyString(id) || StringUtil.isEmptyString(sel_id)) return null;

		Map<String, String> map = new HashMap<String, String>(4);
		map.put("userid", id);
		map.put("progid", "CSBR1050");
		map.put("useridFr", sel_id);
		map.put("modifyusid", Constant.USER.get("ID"));

		if("add".equals(action)) {
			dataService.insert_mb_nrcdpower(map);
		}

		if("del".equals(action)) {
			dataService.delete_mb_nrcdpower(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9020_nrcd_action");
		mav.addObject("success", true);

		return mav;
	}

	@RequestMapping("/data/csbr9030_left_data.do")
	public ModelAndView csbr9030_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		sd = DateUtil.getSimpleDate("yyyy-MM-dd", sd);
		ed = DateUtil.getSimpleDate("yyyy-MM-dd", ed);

		String sname = request.getParameter("sname");
		String sname_query = "";
		String spname = request.getParameter("spname");
		String spname_query = "";

		if ( !StringUtil.isEmptyString(sname) ) sname_query = " and NAME like CONCAT('%', '" + sname + "' ,'%')";
		else sname_query = "";

		if ( !StringUtil.isEmptyString(spname) ) spname_query = " and PROGNAME CONCAT('%', '" + spname + "' ,'%')";
		else spname_query = "";

		Map<String, String> map = new HashMap<String, String>(4);
		map.put("sd", sd);
		map.put("ed", ed);
		map.put("snameQuery", sname_query);
		map.put("spnameQuery", spname_query);

		List<Map<String, String>> list = dataService.select_csbr9030_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9030_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr9030_right_data.do")
	public ModelAndView csbr9030_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		//String edit = request.getParameter("edit");

		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyy-MM-dd", d);

		String sname = request.getParameter("sname");
		String sname_query = "";
		String spname = request.getParameter("spname");
		String spname_query = "";

		if ( !StringUtil.isEmptyString(sname) ) sname_query = " and NAME like CONCAT('%', '" + sname + "' ,'%')";
		else sname_query = "";

		if ( !StringUtil.isEmptyString(spname) ) spname_query = " and PROGNAME CONCAT('%', '" + spname + "' ,'%')";
		else spname_query = "";

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("d", d);
		map.put("snameQuery", sname_query);
		map.put("spnameQuery", spname_query);

		List<Map<String, String>> list = dataService.select_csbr9030_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9030_right_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/csbr9030_print.do")
	public ModelAndView csbr9030_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if ( !StringUtil.isEmptyString(d) ) d = DateUtil.getSimpleDate("yyyy-MM-dd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR9030");
			commonService.insert_mb_usedlog(map);
		}

		String sname = request.getParameter("sname");
		String sname_query = "";
		String spname = request.getParameter("spname");
		String spname_query = "";

		if ( !StringUtil.isEmptyString(sname) ) sname_query = " and NAME like CONCAT('%', '" + sname + "' ,'%')";
		else sname_query = "";

		if ( !StringUtil.isEmptyString(spname) ) spname_query = " and PROGNAME CONCAT('%', '" + spname + "' ,'%')";
		else spname_query = "";

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("d", d);
		map.put("snameQuery", sname_query);
		map.put("spnameQuery", spname_query);

		List<Map<String, String>> list = dataService.select_csbr9030_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9030_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/data/csbr9140_left_data.do")
	public ModelAndView csbr9140_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("kdcd", "0000");
		List<Map<String, String>> list = dataService.select_csbr9140_left_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9140_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr9140_right_data.do")
	public ModelAndView csbr9140_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		ModelAndView mav = new ModelAndView();

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(3);
			map.put("kdcd", d);
			map.put("orderby", "ORDER BY SORTNO, CDBS");

			List<Map<String, String>> list = dataService.select_csbr9140_left_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		mav.setViewName("/include/data/csbr9140_right_data");
		mav.addObject("success", true);

		return mav;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/csbr9140_left_action.do")
	public ModelAndView csbr9140_left_action(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param, HttpSession session,
			ModelMap model) throws Exception {

		String htmlTag = "";

		Enumeration names = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>(3);

		String sqlCntResult = "";

		while(names.hasMoreElements()) {
			String key = (String) names.nextElement();
			if(key.startsWith("key")) {
				String idx = key.replace("key", "");
				String k = request.getParameter("key" + idx);
				String bs = request.getParameter("cdbs" + idx);
				String nm = request.getParameter("cdnm" + idx);
				map.put("bs", bs);
				map.put("nm", nm);

				if(!StringUtil.isEmptyString(bs) && !StringUtil.isEmptyString(nm)) {
					if(!StringUtil.isEmptyString(k)) {
						sqlCntResult = dataService.update_csbr9140_left_mb_etccd(map);
					}else {
						map.put("modifyusid", Constant.USER.get("ID"));
						sqlCntResult = dataService.insert_csbr9140_left_mb_etccd(map);
					}
				}

				String[] arrSqlCntResult = sqlCntResult.split("/");
				String query = arrSqlCntResult[0];

				htmlTag += idx + " | " + k + " | " + bs + " | " + nm + "<br>";
				htmlTag += query + "<br>";
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9140_left_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/csbr9140_right_action.do")
	public ModelAndView csbr9140_right_action(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param, HttpSession session,
			ModelMap model) throws Exception {

		String selcode = request.getParameter("selcode");
		if ( StringUtil.isEmptyString(selcode) ) return null;

		String htmlTag = "";

		Enumeration names = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();

		String sqlCntResult = "";

		while(names.hasMoreElements()) {
			String key = (String) names.nextElement();
			
			
			
			if(key.indexOf("key") > -1) {
				
				String idx = key.replace("key", "");
				String k = request.getParameter("key" + idx);
				String bs = request.getParameter("cdbs" + idx);
				String nm = request.getParameter("cdnm" + idx);
				String sno = request.getParameter("sortno" + idx);
				String uyn = request.getParameter("unuseyn" + idx);
				if("".equals(uyn)) uyn = "Y";

				String d1 = request.getParameter("desc1" + idx);
				String d2 = request.getParameter("desc2" + idx);
				String d3 = request.getParameter("desc3" + idx);
				
				map.put("bs", bs); //코드
				map.put("nm", nm); //명칭
				map.put("sno", sno);
				map.put("uyn", uyn);
				map.put("d1", d1);
				map.put("d2", d2);
				map.put("d3", d3);
				map.put("selcode", selcode);
				map.put("modifyusid", Constant.USER.get("ID"));

				if(!StringUtil.isEmptyString(bs) && !StringUtil.isEmptyString(nm)) {
					if(!StringUtil.isEmptyString(k)) {
						sqlCntResult = dataService.update_csbr9140_right_mb_etccd(map);
					}else {
						System.out.println("새로운 코드 삽입");
						sqlCntResult = dataService.insert_csbr9140_right_mb_etccd(map);
					}
				}

				String[] arrSqlCntResult = sqlCntResult.split("/");
				String query = arrSqlCntResult[0];

				htmlTag += idx + " " + k + " " + bs + " " + nm + " " + sno + " " + uyn + " " + d1 + " " + d2 + " " + d3 + "<br>";
				htmlTag += query + "<br>";
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9140_right_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/data/csbr9201_left_data.do")
	public ModelAndView csbr9201_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		if ( StringUtil.isEmptyString(sdate) ) return null;
		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);

		String stype = request.getParameter("stype");
		String stype_query = "";

		if("0".equals(stype)) stype_query = "";
		if("1".equals(stype)) stype_query = " AND a.CSDTE IS NULL"; //접속
		if("2".equals(stype)) stype_query = " AND a.CSDTE IS NOT NULL"; //종료

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("sd", sd);
		map.put("stypeQuery", stype_query);
		List<Map<String, String>> list = dataService.select_csbr9201_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9201_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr9201_right_data.do")
	public ModelAndView csbr9201_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String id = request.getParameter("id");
		String sdate = request.getParameter("sdate");
		if ( StringUtil.isEmptyString(id) || StringUtil.isEmptyString(sdate) ) return null;

		sdate = DateUtil.getSimpleDate("yyyyMMdd", sdate);

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("id", id);
		map.put("sdate", sdate);

		ModelAndView mav = new ModelAndView();
		List<Map<String, String>> list = dataService.select_csbr9201_right_data(map);
		mav.addObject("list", list);

		mav.setViewName("/include/data/csbr9201_right_data");
		mav.addObject("success", true);

		return mav;
	}

	@RequestMapping("/csbr9201_print.do")
	public ModelAndView csbr9201_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String id = request.getParameter("id");
		String sdate = request.getParameter("sdate");

		if ( StringUtil.isEmptyString(id) || StringUtil.isEmptyString(sdate) ) return null;

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", sdate);

		dtext = dtext + "(" + DateUtil.getDayOfWeek(sdate) + ")";

		String d =  DateUtil.getSimpleDate("yyyy-MM-dd", sdate);
		sdate =  DateUtil.getSimpleDate("yyyyMMdd", sdate);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR9201");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("id", id);
		map.put("sdate", sdate);

		List<Map<String, String>> list = dataService.select_csbr9201_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9201_print");
		mav.addObject("success", true);
		mav.addObject("list", list);
		mav.addObject("d", d);
		mav.addObject("dtext", dtext);
		return mav;
	}

	@RequestMapping("/data/csbr9920_left_data.do")
	public ModelAndView csbr9920_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		List<Map<String, String>> list = dataService.select_csbr9920_left_data();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr9920_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}

	@RequestMapping("/data/csbr9920_right_data.do")
	public ModelAndView csbr9920_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String progid = request.getParameter("progid");

		ModelAndView mav = new ModelAndView();
		List<Map<String, String>> list = dataService.select_csbr9920_right_data(progid);
		List<Map<String, String>> list2 = dataService.select_csbr9920_right_data2(progid);
		mav.addObject("list", list);
		mav.addObject("list2", list2);

		mav.setViewName("/include/data/csbr9920_right_data");
		mav.addObject("success", true);

		return mav;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/csbr9920_left_action.do")
	public ModelAndView csbr9920_left_action(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param, HttpSession session,
			ModelMap model) throws Exception {

		String htmlTag = "";

		Enumeration names = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();

		while(names.hasMoreElements()) {
			String key = (String) names.nextElement();
			if(key.indexOf("progcd01") > -1) {
				String idx = key.replace("progcd01", "");
				String k = request.getParameter("key" + idx);
				String cd1 = request.getParameter("progcd01" + idx);
				String cd2 = request.getParameter("progcd02" + idx);
				String cd3 = request.getParameter("progcd03" + idx);
				String pid = request.getParameter("progid" + idx);
				String pname = request.getParameter("progname" + idx);
				String puseyn = request.getParameter("useyn" + idx);
				if("".equals(puseyn)) puseyn = "N";

				htmlTag += k + " | " + cd1 + " | " + cd2 + " | " + cd3 + " | " + pid + " | " + pname + " | " + puseyn + "\\n";

				map.put("k", k);
				map.put("cd1", cd1);
				map.put("cd2", cd2);
				map.put("cd3", cd3);
				map.put("pid", pid);
				map.put("pname", pname);
				map.put("puseyn", puseyn);

				if(!StringUtil.isEmptyString(k)) {
					dataService.update_csbr9920_left_mb_proglist(map);
				}else {
					map.put("modifyusid", Constant.USER.get("ID"));
					if((!StringUtil.isEmptyString(cd1) || !StringUtil.isEmptyString(cd2) || !StringUtil.isEmptyString(cd3)) && !StringUtil.isEmptyString(pname)) {
						dataService.insert_csbr9920_left_mb_proglist(map);
					}
				}
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9920_left_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}

	@RequestMapping("/csbr9920_right_action.do")
	public ModelAndView csbr9920_right_action(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> param, HttpSession session,
			ModelMap model) throws Exception {

		String action = request.getParameter("action");
		String progid = request.getParameter("progid");
		String sel_id = request.getParameter("sel_id");
		if ( StringUtil.isEmptyString(action) || StringUtil.isEmptyString(progid) || StringUtil.isEmptyString(sel_id) ) return null;

		Map<String, String> map = new HashMap<String, String>();

		map.put("puserid", sel_id);
		map.put("progid", progid);
		map.put("modifyusid", Constant.USER.get("ID"));

		if("add".equals(action)){
			dataService.insert_mb_pempower(map);
		}

		if("del".equals(action)){
			dataService.delete_mb_pempower(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr9920_right_action");
		mav.addObject("success", true);

		return mav;
	}
	
	// snsb pdf 업로드 창
	@RequestMapping("/csbr1050_upload.do")
	public ModelAndView csbr1050_upload(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr1050_pdf_upload");
		mav.addObject("success", true);
		mav.addObject("objectIdx", request.getParameter("objectIdx"));
		mav.addObject("selectionNum", request.getParameter("selectionNum"));

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csbr1050_pdf_upload.do", method = RequestMethod.POST)
	public ModelAndView csbr1050_pdf_upload(@RequestParam("upfile") MultipartFile testFile, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session
			) throws Exception {

		ModelAndView mav = new ModelAndView();
	
		try {
			
				String fileName = testFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				String fileNameWOExtenstion = fileName.substring(0, fileName.lastIndexOf("."));
				String docDir = ResourceBundleUtil.getResourceBundle("pdfDir");
				String objectIdx = request.getParameter("objectIdx");
				String selectionNum = request.getParameter("selectionNum");
				String fileYn = request.getParameter("fileYn");
				
				fileNameWOExtenstion = fileNameWOExtenstion + "_" + objectIdx + "_" + selectionNum;
				
				File temp = new File(ResourceBundleUtil.getResourceBundle("pdfDir") + fileNameWOExtenstion + fileExtension);
				File mkdir = new File(ResourceBundleUtil.getResourceBundle("pdfDir"));

				Map<String, String> map = new HashMap<String, String>();
				map.put("objectIdx", objectIdx);
				map.put("selectionNum", selectionNum);
				map.put("pdfPath", docDir + fileNameWOExtenstion + fileExtension);
				
				if(fileYn == "Y") { //업로드된 파일 존재
					// 1) 기존파일삭제
					if( temp.exists() ){
						temp.delete();
					}
				}
					
				// 3) 새로운 업로드 시작
				if (!mkdir.exists()) { // 폴더 생성
					mkdir.mkdir();
				}
				testFile.transferTo(temp); // 파일 저장
				
				dataService.update_csbr1050_pdfPath(map); // 파일 패스 업데이트
					
			mav.addObject("success", true);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.addObject("success", false);
			
		}
	
		mav.setViewName("/include/csbr1050_pdf_upload");
		return mav;
	}
	
	 @RequestMapping(value="/csbr1050_pdf_download.do")
	 public void csbr1050_pdf_download(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 FileInputStream fis = null;
		 BufferedOutputStream bos = null;

		 try {

		  String pdfFileName = request.getParameter("realPath");
		  File pdfFile = new File(pdfFileName);

		  
		  String header = request.getHeader( "User-Agent" );
		  String encodedFilename = "";
		  
			if ( header.indexOf( "MSIE" ) > -1 ) {
				encodedFilename = URLEncoder.encode( pdfFile.getName(), "UTF-8" ).replaceAll( "\\+", "%20" );
			}else if ( header.indexOf( "Trident" ) > -1 ) { 
				encodedFilename = URLEncoder.encode( pdfFile.getName(), "UTF-8" ).replaceAll( "\\+", "%20" );
			}else if ( header.indexOf( "Chrome" ) > -1 ) {
				StringBuffer sb = new StringBuffer();
				for ( int i = 0; i < pdfFile.getName().length(); i++ ) {
					char c = pdfFile.getName().charAt( i );
					if ( c > '~' ) {
						sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
					}
					else {
						sb.append( c );
					}
				}
				encodedFilename = sb.toString();
			}else if ( header.indexOf( "Opera" ) > -1 ) {
				encodedFilename = "\"" + new String( pdfFile.getName().getBytes( "UTF-8" ), "8859_1" ) + "\"";
			}
			response.setContentType("application/pdf; UTF-8");
//			response.setHeader( "Content-Disposition", "attachment; filename=\"" + encodedFilename + "\";" );
			response.setHeader( "Content-Disposition", "inline; filename=\"" + encodedFilename + "\";" );
			response.setHeader( "Content-Transfer-Encoding", "binary" );

		  //파일 읽고 쓰는 건 일반적인 Write방식이랑 동일. 다만 reponse 출력 스트림 객체에 write.
		  fis = new FileInputStream(pdfFile);
		  int size = fis.available(); //지정 파일에서 읽을 수 있는 바이트 수를 반환
		  byte[] buf = new byte[size]; //버퍼설정
		  int readCount = fis.read(buf);

		  response.flushBuffer();
		  bos = new BufferedOutputStream(response.getOutputStream());
		  bos.write(buf, 0, readCount);
		  bos.flush();

		 } catch(Exception e) {
		  e.printStackTrace();
		 } finally {
		  try {
		   if (fis != null) fis.close(); //close는 꼭! 반드시!
		   if (bos != null) bos.close();
		  } catch (IOException e) {
		   e.printStackTrace();

		  }

		 }
		
	 }
	
	// 2080 뇌파
	@RequestMapping("/data/csbr2080_left_data.do")
	public ModelAndView csbr2080_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resebw");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}	 
	
	@RequestMapping("/data/csbr2080_right_data.do")
	public ModelAndView csbr2080_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2080_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResebwVO> list = dataService.select_csbr2080_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}
	
	@RequestMapping("/csbr2080_action.do")
	public ModelAndView csbr2080_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_integration_num;
		String rowdel;
		
		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResebwVO bwvo = new MbResebwVO();
			i_k = request.getParameter("key" + i);
			bwvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			bwvo.setObjectIdx(request.getParameter("object_idx" + i));
			bwvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			bwvo.setBwid(request.getParameter("bwid" + i));
			bwvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			bwvo.setTestyn(i_testyn);
			bwvo.setBwyn(request.getParameter("bwyn" + i));
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			bwvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			bwvo.setSelectionNum(i_selection_num);
			i_integration_num = request.getParameter("integration_num" + i);
			if(i_integration_num == null || i_integration_num == "") {
				i_integration_num = "0";
			}
			bwvo.setIntegrationNum(i_integration_num);
			bwvo.setAgree(request.getParameter("agree" + i));
			bwvo.setPhoto(request.getParameter("photo" + i));
			bwvo.setBlood(request.getParameter("blood" + i));
			bwvo.setDamdang1(request.getParameter("damdang1" + i));
			bwvo.setCdrom(request.getParameter("cdrom" + i));
			bwvo.setReading(request.getParameter("reading" + i));
			bwvo.setConsult(request.getParameter("consult" + i));
			bwvo.setDamdang2(request.getParameter("damdang2" + i));
			bwvo.setDiagnose(request.getParameter("diagnose" + i));
			bwvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			bwvo.setFollowup("");
			bwvo.setModifyusid(Constant.USER.get("ID"));
			bwvo.setResedate(savedate);
			bwvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) { // update
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {
						bwvo.setResetime(org_resetime);
						bwvo.setObjectIdx(org_object_idx);
						bwvo.setResersno(org_resersno);
						
						// bwid 만들기 : F(성별)50(출생년도)BW(뇌파)210310(예약년월일)01(순번)01(차수)
//						if(bwvo.getResersno().length() < 2) {
//							reNo = "0"+bwvo.getResersno();
//						}else {
//							reNo = bwvo.getResersno();
//						}
//						if(bwvo.getSelectionNum().length() < 2) {
//							selNo = "0"+bwvo.getSelectionNum();
//						}else {
//							selNo = bwvo.getSelectionNum();
//						}
//						String mkBwid = request.getParameter("gender"+i) + (request.getParameter("jumin"+i)).substring(0, 2)
//								+ "BW" + (bwvo.getResedate()).substring(2) + reNo + selNo;
						String mkBwid = Id.BW.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno(), bwvo.getSelectionNum());
						bwvo.setBwid(mkBwid); 
						
						rtnCnt = dataService.update_csbr2080_mb_resebw(bwvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2080_action : " + rtnCnt);
						}
					}
				}
			} else { //insert
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_resebw");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					
					if(org_resersno.equals("0") || org_resersno == "0") {
						//System.out.println("0일때 더하기 .. 순번 1로 시작하기위함");
						org_resersno = Integer.toString(Integer.parseInt(org_resersno) + 1);
					}
					
					org_resersno = String.format("%s", org_resersno);
					
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					
					bwvo.setResersno(org_resersno);
					
					// bwid 만들기 : F(성별)50(출생년도)BW(뇌파)210310(예약년월일)01(순번)01(차수)
//					if(bwvo.getResersno().length() < 2) {
//						reNo = "0"+bwvo.getResersno();
//					}else {
//						reNo = bwvo.getResersno();
//					}
//					if(bwvo.getSelectionNum().length() < 2) {
//						selNo = "0"+bwvo.getSelectionNum();
//					}else {
//						selNo = bwvo.getSelectionNum();
//					}
//					String mkBwid = request.getParameter("gender"+i) + (request.getParameter("jumin"+i)).substring(0, 2)
//							+ "BW" + (bwvo.getResedate()).substring(2) + reNo + selNo;
					String mkBwid = Id.BW.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno(), bwvo.getSelectionNum());
					bwvo.setBwid(mkBwid); 

					String sqlCntResult = dataService.insert_csbr2080_mb_resebw(bwvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2080_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + bwvo.getResetime() + bwvo.getObjectIdx() + bwvo.getTelNo() + bwvo.getBirth() + bwvo.getRemark() + bwvo.getTestyn() + "<br>";
			i++;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("tableName", "mb_resebw");
		map.put("savedate", savedate);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}
	
	@RequestMapping("/csbr2080_print.do")
	public ModelAndView csbr2080_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2080");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResebwVO> list = dataService.select_csbr2080_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2080_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	// 버칼
	@RequestMapping("/data/csbr2090_left_data.do")
	public ModelAndView csbr2090_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_resebs");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}	 
	
	@RequestMapping("/data/csbr2090_right_data.do")
	public ModelAndView csbr2090_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2090_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbResebsVO> list = dataService.select_csbr2090_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}
	
	@RequestMapping("/csbr2090_action.do")
	public ModelAndView csbr2090_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_integration_num;
		String rowdel;
		
		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbResebsVO bwvo = new MbResebsVO();
			i_k = request.getParameter("key" + i);
			bwvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			bwvo.setObjectIdx(request.getParameter("object_idx" + i));
			bwvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			bwvo.setBsid(request.getParameter("bsid" + i));
			bwvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			bwvo.setTestyn(i_testyn);
			bwvo.setBsyn(request.getParameter("bsyn" + i));
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			bwvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			bwvo.setSelectionNum(i_selection_num);
			i_integration_num = request.getParameter("integration_num" + i);
			if(i_integration_num == null || i_integration_num == "") {
				i_integration_num = "0";
			}
			bwvo.setIntegrationNum(i_integration_num);
			bwvo.setAgree(request.getParameter("agree" + i));
			bwvo.setPhoto(request.getParameter("photo" + i));
			bwvo.setBlood(request.getParameter("blood" + i));
			bwvo.setDamdang1(request.getParameter("damdang1" + i));
			bwvo.setCdrom(request.getParameter("cdrom" + i));
			bwvo.setReading(request.getParameter("reading" + i));
			bwvo.setConsult(request.getParameter("consult" + i));
			bwvo.setDamdang2(request.getParameter("damdang2" + i));
			bwvo.setDiagnose(request.getParameter("diagnose" + i));
			bwvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			bwvo.setFollowup("");
			bwvo.setModifyusid(Constant.USER.get("ID"));
			bwvo.setResedate(savedate);
			bwvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) { // update
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {
						bwvo.setResetime(org_resetime);
						bwvo.setObjectIdx(org_object_idx);
						bwvo.setResersno(org_resersno);
						
						// bsid 만들기 : F(성별)50(출생년도)BS(버칼)210310(예약년월일)01(순번)
//						if(bwvo.getResersno().length() < 2) {
//							reNo = "0"+bwvo.getResersno();
//						}else {
//							reNo = bwvo.getResersno();
//						}
//						if(bwvo.getSelectionNum().length() < 2) {
//							selNo = "0"+bwvo.getSelectionNum();
//						}else {
//							selNo = bwvo.getSelectionNum();
//						}
//						String mkBsid = request.getParameter("gender"+i) + (request.getParameter("jumin"+i)).substring(0, 2)
//								+ "BS" + (bwvo.getResedate()).substring(2) + reNo; // + selNo;
						String mkBsid = Id.BS.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno());
						bwvo.setBsid(mkBsid); 
						
						rtnCnt = dataService.update_csbr2090_mb_resebs(bwvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2090_action : " + rtnCnt);
						}
					}
				}
			} else { //insert
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_resebs");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					
					if(org_resersno.equals("0") || org_resersno == "0") {
						//System.out.println("0일때 더하기 .. 순번 1로 시작하기위함");
						org_resersno = Integer.toString(Integer.parseInt(org_resersno) + 1);
					}
					
					org_resersno = String.format("%s", org_resersno);
					
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					
					bwvo.setResersno(org_resersno);
					
					// bsid 만들기 : F(성별)50(출생년도)BS(버칼)210310(예약년월일)01(순번)
//					if(bwvo.getResersno().length() < 2) {
//						reNo = "0"+bwvo.getResersno();
//					}else {
//						reNo = bwvo.getResersno();
//					}
//					if(bwvo.getSelectionNum().length() < 2) {
//						selNo = "0"+bwvo.getSelectionNum();
//					}else {
//						selNo = bwvo.getSelectionNum();
//					}
//					String mkBsid = request.getParameter("gender"+i) + (request.getParameter("jumin"+i)).substring(0, 2)
//							+ "BS" + (bwvo.getResedate()).substring(2) + reNo; // + selNo;
					String mkBsid = Id.BS.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno());
					bwvo.setBsid(mkBsid); 

					String sqlCntResult = dataService.insert_csbr2090_mb_resebs(bwvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2090_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + bwvo.getResetime() + bwvo.getObjectIdx() + bwvo.getTelNo() + bwvo.getBirth() + bwvo.getRemark() + bwvo.getTestyn() + "<br>";
			i++;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("tableName", "mb_resebs");
		map.put("savedate", savedate);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}
	
	@RequestMapping("/csbr2090_print.do")
	public ModelAndView csbr2090_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2090");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbResebsVO> list = dataService.select_csbr2090_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2090_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	// 인바디
	@RequestMapping("/data/csbr2100_left_data.do")
	public ModelAndView csbr2100_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_reseib");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}	 
	
	@RequestMapping("/data/csbr2100_right_data.do")
	public ModelAndView csbr2100_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2100_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbReseibVO> list = dataService.select_csbr2100_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}
	
	@RequestMapping("/csbr2100_action.do")
	public ModelAndView csbr2100_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_integration_num;
		String rowdel;
		
		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbReseibVO bwvo = new MbReseibVO();
			i_k = request.getParameter("key" + i);
			bwvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			bwvo.setObjectIdx(request.getParameter("object_idx" + i));
			bwvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			bwvo.setIbid(request.getParameter("ibid" + i));
			bwvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			bwvo.setTestyn(i_testyn);
			bwvo.setIbyn(request.getParameter("ibyn" + i));
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			bwvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			bwvo.setSelectionNum(i_selection_num);
			i_integration_num = request.getParameter("integration_num" + i);
			if(i_integration_num == null || i_integration_num == "") {
				i_integration_num = "0";
			}
			bwvo.setIntegrationNum(i_integration_num);
			bwvo.setAgree(request.getParameter("agree" + i));
			bwvo.setPhoto(request.getParameter("photo" + i));
			bwvo.setBlood(request.getParameter("blood" + i));
			bwvo.setDamdang1(request.getParameter("damdang1" + i));
			bwvo.setCdrom(request.getParameter("cdrom" + i));
			bwvo.setReading(request.getParameter("reading" + i));
			bwvo.setConsult(request.getParameter("consult" + i));
			bwvo.setDamdang2(request.getParameter("damdang2" + i));
			bwvo.setDiagnose(request.getParameter("diagnose" + i));
			bwvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			bwvo.setFollowup("");
			bwvo.setModifyusid(Constant.USER.get("ID"));
			bwvo.setResedate(savedate);
			bwvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) { // update
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {
						bwvo.setResetime(org_resetime);
						bwvo.setObjectIdx(org_object_idx);
						bwvo.setResersno(org_resersno);
						
						// ibid 만들기 : F(성별)50(출생년도)IB(인바디)210310(예약년월일)01(순번)01(차수)
						String mkIbid = Id.IB.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno(), bwvo.getSelectionNum());
						bwvo.setIbid(mkIbid); 
						
						rtnCnt = dataService.update_csbr2100_mb_reseib(bwvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2100_action : " + rtnCnt);
						}
					}
				}
			} else { //insert
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_reseib");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					
					if(org_resersno.equals("0") || org_resersno == "0") {
						//System.out.println("0일때 더하기 .. 순번 1로 시작하기위함");
						org_resersno = Integer.toString(Integer.parseInt(org_resersno) + 1);
					}
					
					org_resersno = String.format("%s", org_resersno);
					
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					
					bwvo.setResersno(org_resersno);
					
					// ibid 만들기 : F(성별)50(출생년도)IB(인바디)210310(예약년월일)01(순번)01(차수)
					String mkIbid = Id.IB.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno(), bwvo.getSelectionNum());
					bwvo.setIbid(mkIbid); 

					String sqlCntResult = dataService.insert_csbr2100_mb_reseib(bwvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2100_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + bwvo.getResetime() + bwvo.getObjectIdx() + bwvo.getTelNo() + bwvo.getBirth() + bwvo.getRemark() + bwvo.getTestyn() + "<br>";
			i++;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("tableName", "mb_reseib");
		map.put("savedate", savedate);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}
	
	@RequestMapping("/csbr2100_print.do")
	public ModelAndView csbr2100_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2100");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbReseibVO> list = dataService.select_csbr2100_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2100_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	// cread-k
	@RequestMapping("/data/csbr2110_left_data.do")
	public ModelAndView csbr2110_left_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) throws Exception {

		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");

		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("tableName", "mb_reseck");
		map.put("sd", sd);
		map.put("ed", ed);
		List<Map<String, String>> list = dataService.select_csbr_left_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr_left_data");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}	 
	
	@RequestMapping("/data/csbr2110_right_data.do")
	public ModelAndView csbr2110_right_data(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		String add = request.getParameter("add");

		if ( !StringUtil.isEmptyString(d)) {
			d = DateUtil.getSimpleDate("yyyyMMdd", d);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr2110_right_data");
		mav.addObject("success", true);

		if(!"true".equals(add)) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put("d", d);
			List<MbReseckVO> list = dataService.select_csbr2110_right_data(map);
			mav.addObject("list", list);
		}else {
			mav.addObject("list", null);
		}

		return mav;
	}
	
	@RequestMapping("/csbr2110_action.do")
	public ModelAndView csbr2110_action(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String savedate = request.getParameter("savedate");
		if(StringUtil.isEmptyString(savedate)){
			return null;
		}
		savedate = DateUtil.getSimpleDate("yyyyMMdd", savedate);
		String moddate = DateUtil.getSimpleDate("yyyyMMddHHmmss");


		String[] sp;
		String org_resetime, org_object_idx, org_resersno;

		String htmlTag = "";

		String i_k;
		String i_testyn;
		String i_bloodyn;
		String i_selection_num;
		String i_integration_num;
		String rowdel;
		
		int i = 1;
		while(request.getParameter("key" + i) != null) {

			MbReseckVO bwvo = new MbReseckVO();
			i_k = request.getParameter("key" + i);
			bwvo.setResetime(request.getParameter("resetime" + i).replace(":", ""));
			bwvo.setObjectIdx(request.getParameter("object_idx" + i));
			bwvo.setSnsbdate(request.getParameter("snsbdate" + i).replace("-", ""));
			bwvo.setCkid(request.getParameter("ckid" + i));
			bwvo.setCudc(request.getParameter("cudc" + i));
			i_testyn = request.getParameter("testyn" + i);
			if(i_testyn == null || i_testyn == "") {
				i_testyn = "0";
			}
			bwvo.setTestyn(i_testyn);
			bwvo.setCkyn(request.getParameter("ckyn" + i));
			i_bloodyn = request.getParameter("bloodyn" + i);
			if(i_bloodyn == null || i_bloodyn == "") {
				i_bloodyn = "0";
			}
			bwvo.setBloodyn(i_bloodyn);
			i_selection_num = request.getParameter("selection_num" + i);
			if(i_selection_num == null || i_selection_num == "") {
				i_selection_num = "0";
			}
			bwvo.setSelectionNum(i_selection_num);
			i_integration_num = request.getParameter("integration_num" + i);
			if(i_integration_num == null || i_integration_num == "") {
				i_integration_num = "0";
			}
			bwvo.setIntegrationNum(i_integration_num);
			bwvo.setAgree(request.getParameter("agree" + i));
			bwvo.setPhoto(request.getParameter("photo" + i));
			bwvo.setBlood(request.getParameter("blood" + i));
			bwvo.setDamdang1(request.getParameter("damdang1" + i));
			bwvo.setCdrom(request.getParameter("cdrom" + i));
			bwvo.setReading(request.getParameter("reading" + i));
			bwvo.setConsult(request.getParameter("consult" + i));
			bwvo.setDamdang2(request.getParameter("damdang2" + i));
			bwvo.setDiagnose(request.getParameter("diagnose" + i));
			bwvo.setRemark(request.getParameter("remark" + i));
			rowdel = request.getParameter("del" + i);
			if(rowdel == null || rowdel == "") {
				rowdel = "0";
			}
			bwvo.setFollowup("");
			bwvo.setModifyusid(Constant.USER.get("ID"));
			bwvo.setResedate(savedate);
			bwvo.setModifydate(moddate);

			int rtnCnt = -1;

			if ( !StringUtil.isEmptyString(i_k) ) { // update
				sp = i_k.split("\\|");
				org_resetime = sp[0];
				org_object_idx = sp[1];
				org_resersno = sp[2];

				if ( "1".equals(rowdel) ) {
					Map<String, String> map = new HashMap<String, String>(4);
					map.put("savedate", savedate);
					map.put("org_resetime", org_resetime);
					map.put("org_object_idx", org_object_idx);
					map.put("org_resersno", org_resersno);
					dataService.delete_csbr2010_mb_resesel(map);
				} else {
					if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {
						bwvo.setResetime(org_resetime);
						bwvo.setObjectIdx(org_object_idx);
						bwvo.setResersno(org_resersno);
						
						// ckid 만들기 : F(성별)50(출생년도)CK(CERAD-K)210310(예약년월일)01(순번)
						String mkCkid = Id.CK.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno());
						bwvo.setCkid(mkCkid); 
						
						rtnCnt = dataService.update_csbr2110_mb_reseck(bwvo);
						if(rtnCnt > 0) {
							logger.debug("csbr2110_action : " + rtnCnt);
						}
					}
				}
			} else { //insert
				if(!StringUtil.isEmptyString(savedate) && !StringUtil.isEmptyString(bwvo.getResetime()) && !StringUtil.isEmptyString(bwvo.getObjectIdx()) ) {

					Map<String, String> map = new HashMap<String, String>(2);
					map.put("tableName", "mb_reseck");
					map.put("savedate", savedate);
					org_resersno = dataService.selectMaxResersnoSeq(map);
					
					if(org_resersno.equals("0") || org_resersno == "0") {
						//System.out.println("0일때 더하기 .. 순번 1로 시작하기위함");
						org_resersno = Integer.toString(Integer.parseInt(org_resersno) + 1);
					}
					
					org_resersno = String.format("%s", org_resersno);
					
					if(org_resersno.length() < 2) {
						org_resersno = "0"+org_resersno;
					}
					
					bwvo.setResersno(org_resersno);
					
					// ckid 만들기 : F(성별)50(출생년도)CK(CERAD-K)210310(예약년월일)01(순번)
					String mkCkid = Id.CK.get(request.getParameter("gender"+i), request.getParameter("jumin"+i), bwvo.getResedate(), bwvo.getResersno());
					bwvo.setCkid(mkCkid); 

					String sqlCntResult = dataService.insert_csbr2110_mb_reseck(bwvo);
					String[] arrSqlCntResult = sqlCntResult.split("/");
					String query = arrSqlCntResult[0];
					rtnCnt = Integer.parseInt(arrSqlCntResult[1]);
					if(rtnCnt > 0) {
						logger.debug("csbr2110_action : " + rtnCnt);
					}
					htmlTag += query + "<br>";
				}
			}

			htmlTag += i + i_k + bwvo.getResetime() + bwvo.getObjectIdx() + bwvo.getTelNo() + bwvo.getBirth() + bwvo.getRemark() + bwvo.getTestyn() + "<br>";
			i++;
		}

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("tableName", "mb_reseck");
		map.put("savedate", savedate);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr_action");
		mav.addObject("success", true);
		mav.addObject("htmlTag", htmlTag);

		return mav;
	}
	
	@RequestMapping("/csbr2110_print.do")
	public ModelAndView csbr2110_print(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String d = request.getParameter("d");
		if(StringUtil.isEmptyString(d)){
			return null;
		}

		String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
		dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

		d = DateUtil.getSimpleDate("yyyyMMdd", d);

		String xls = request.getParameter("xls");
		if(!StringUtil.isEmptyString(xls)){
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("userId", Constant.USER.get("ID"));
			map.put("menuId", "CSBR2110");
			commonService.insert_mb_usedlog(map);
		}

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("d", d);
		List<MbReseckVO> list = dataService.select_csbr2110_right_data(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/csbr2110_print");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
	
	// mri preview
	@RequestMapping("/csbr1050_mri_preview.do")
	public ModelAndView csbr1050_mri_QcView(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		ModelAndView mav = new ModelAndView();

		String testDay = request.getParameter("testDay");
		String objectIdx = request.getParameter("objectIdx");
		String selectionNum = request.getParameter("selectionNum");
		
		if((testDay!=null && testDay!="") && (objectIdx!=null && objectIdx!="") && (selectionNum!=null && selectionNum!="")) {
			Map<String, String> map = new HashMap<String, String>(3);
			
			map.put("testDay", testDay);
			map.put("objectIdx", objectIdx);
			map.put("selectionNum", selectionNum);
			
			List<CommonVO> nii_list = commonService.select_nii_list(map);
			
			mav.addObject("medicId",nii_list.get(0).getMedicId());
			
			for(int i =0; i< nii_list.size(); i++) {
				//System.out.println(" nii -> "+nii_list.get(i).getNiiPath());
				if(nii_list.get(i).getSeriesId().equals("Flair")) {
					mav.addObject("flair", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("fMRI")) {
					mav.addObject("fMRI", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("Local")) {
					mav.addObject("local", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("T1sag")) {
					mav.addObject("t1sag", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("T2sag")) {
					mav.addObject("t2sag", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("T1_MPR")) {
					mav.addObject("t1mpr", nii_list.get(i).getNiiPath());
				}else if(nii_list.get(i).getSeriesId().equals("T2_MPR")) {
					mav.addObject("t2mpr", nii_list.get(i).getNiiPath());
				}
			}
			
		}else {
				mav.addObject("medicId","");
			
				mav.addObject("flair","");
				mav.addObject("fMRI","");
				mav.addObject("local","");
				mav.addObject("t1sag","");
				mav.addObject("t2sag","");
				mav.addObject("t1mpr","");
				mav.addObject("t2mpr","");
		}
			
		mav.setViewName("/include/csbr1050_mri_preview");
		mav.addObject("success", true);
		mav.addObject("objectIdx", objectIdx);
		mav.addObject("selectionNum", selectionNum);
		mav.addObject("testDay", testDay);
		mav.addObject("name", request.getParameter("name"));
		mav.addObject("subjectNm", request.getParameter("subjectNm"));
		mav.addObject("gender", request.getParameter("gender"));
		mav.addObject("jumin", request.getParameter("jumin"));
		//System.out.println("mav   ->  "+mav.toString());
		return mav;
	}
	
	// mri QcView
	@RequestMapping("/csbr1050_mri_QcView.do")
	public ModelAndView ImagingQcView(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String, Object>(1);
//		String niiPath = request.getParameter("niiPath").replace("/home/medic", "H:/");	// for windows
		String niiPath = request.getParameter("niiPath");
		
//		System.out.println("niiPath->  "+niiPath);
		
		File oFile = new File(niiPath);
		//String classifiDm = ResourceBundleUtil.getResourceBundle("ImagingClassifiDir");
		//classifiDm = classifiDm.substring(0,classifiDm.lastIndexOf("/"));
		
//		if(!oFile.exists()) { // for testing in my local 
		if(oFile.exists()) {	
			//File[] fileList = oFile.listFiles();
			//if(fileList != null) {
//					String niiFilePath = new String();
//					for(File fFile : fileList) {
//						if("DTI".equals(oImageInfoVO.getClassCdDtlNm())) {
//							if("gz".equals(FilenameUtils.getExtension(fFile.getName()))
//									&& isThisNii(FilenameUtils.getBaseName(fFile.getName()),prohibitArr)){
//								niiFilePath = fFile.getAbsolutePath().replaceAll(classifiDm, "/nii");
//								break;
//							}
//						}else {
//							if("gz".equals(fFile.getName().substring(fFile.getName().lastIndexOf(".")+1))){
//								niiFilePath = fFile.getAbsolutePath().replaceAll(classifiDm, "/nii");
//								break;
//							}
//						}
//					}
			
//				niiPath = niiPath.replace("H:/", "/nii"); for windows
				niiPath = niiPath.replace("/home/medic", request.getContextPath()+"/nii");
				
				request.setAttribute("niiFilePath", niiPath);
				request.setAttribute("niiPath", niiPath);
				request.setAttribute("objectIdx", request.getParameter("objectIdx"));
				request.setAttribute("medicId", request.getParameter("medicId"));
				request.setAttribute("selectionNum", request.getParameter("selectionNum"));
				request.setAttribute("seriesId", request.getParameter("seriesId"));
			
				modelMap.put("niiPath", niiPath);
				modelMap.put("niiFilePath", niiPath);
				modelMap.put("objectIdx", request.getParameter("objectIdx"));
				modelMap.put("medicId", request.getParameter("medicId"));
				modelMap.put("selectionNum", request.getParameter("selectionNum"));
				modelMap.put("seriesId", request.getParameter("seriesId"));
			//}
			return new ModelAndView("/include/csbr1050_mri_qcView",modelMap);
		} else {
			throw new Exception("Cannot find the NII file. - "+oFile.getAbsolutePath());
		}
	}
	
	@RequestMapping("/data/csbr5010_data.do")
	public ModelAndView selectBarcodeSettings(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("kdcd", "S001");
		map.put("orderby", "ORDER BY SORTNO, CDBS");

		List<Map<String, String>> list = dataService.select_csbr9140_left_right_data(map);
		model.put("success", true);
		model.put("list", list);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping("/data/csbr5010_action.do")
	public ModelAndView saveBarcodeSettings(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("kdcd", "S001");
		map.put("orderby", "ORDER BY SORTNO, CDBS");
		List<Map<String, String>> list = dataService.select_csbr9140_left_right_data(map);
		
		map.clear();
		map.put("port_name", 	request.getParameter("port_name"));
		map.put("baudrate", 	request.getParameter("baudrate"));
		map.put("data_bits", 	request.getParameter("data_bits"));
		map.put("parity", 		request.getParameter("parity"));
		map.put("stop_bits", 	request.getParameter("stop_bits"));
		map.put("flow_control", request.getParameter("flow_control"));
		
		for(Map<String,String> item : list) {
			String cdnm = item.get("cdnm");
			if(map.containsKey(cdnm)) {
				item.put("desc2", map.get(cdnm));
				
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("selcode", "S001");
				dataMap.put("bs",  item.get("cdbs"));
				dataMap.put("nm",  item.get("cdnm"));
				Object sno = item.get("sortno");
				dataMap.put("sno", sno==null ? "0" : sno.toString());
				dataMap.put("uyn", item.get("unuseyn"));
				dataMap.put("d1",  item.get("desc1"));
				dataMap.put("d2",  map.get(cdnm));
				dataMap.put("d3",  item.get("desc3"));
				dataService.update_csbr9140_right_mb_etccd(dataMap);
			} else {
				logger.warn("Cannot find the cdnm. - cdnm="+cdnm+", request data="+map);
			}
		}
		
		model.put("success", true);
		model.put("list", list);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping("/data/csbr1031_search.do")
	public ModelAndView csbr1031_search(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		String st_name = request.getParameter("st_name");
		String st_idx = request.getParameter("st_idx");
		String st_num = request.getParameter("st_num");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		
		if ( StringUtil.isEmptyString(sdate) || StringUtil.isEmptyString(edate) ) return null;

		String sd = DateUtil.getSimpleDate("yyyyMMdd", sdate);
		String ed = DateUtil.getSimpleDate("yyyyMMdd", edate);

		String td = "";

		if ( DateUtil.getDate(sd).compareTo(DateUtil.getDate(ed)) > 0 ) {
			td = sdate; sdate = edate; edate = td;
			td = sd; sd = ed; ed = td;
		}

		String idx_query = "";
		String num_query = "";
		if ( !StringUtil.isEmptyString(st_idx) ) idx_query = " AND O.IDX = #{stIdx} ";
		if ( !StringUtil.isEmptyString(st_num) ) num_query = " AND A.SELECTION_NUM = #{stNum} ";

		Map<String, String> map = new HashMap<String, String>(4);
		map.put("sd", sd);
		map.put("ed", ed);
		map.put("stName", st_name);
		map.put("stIdx", st_idx);
		map.put("stNum", st_num);
		map.put("idxQuery", idx_query);
		map.put("numQuery", num_query);
		List<Csbr1031SearchVO> list = dataService.select_csbr1031_search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/include/data/csbr1031_search");
		mav.addObject("success", true);
		mav.addObject("list", list);

		return mav;
	}
}
