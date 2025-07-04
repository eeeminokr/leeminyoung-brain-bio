package csbrain.main.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import csbrain.common.util.Constant;
import csbrain.common.util.DateUtil;
import csbrain.common.util.Md5Util;
import csbrain.main.service.MainService;
import csbrain.main.service.MemberService;
import csbrain.main.service.MemberVO;

@Controller
public class MainController {

	/** Sevice Class */
	@Resource(name = "MainService")
	private MainService mainService;

	@Resource(name = "MemberService")
	private MemberService memberService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/login_action.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @ModelAttribute("memberVO") MemberVO memberVO,	ModelMap model) throws Exception {

		String id = request.getParameter("uid");
		String pw = request.getParameter("upass");
		int saveid = Integer.parseInt(request.getParameter("saveid"));

		if(memberVO == null || memberVO.getId() == null) {
			memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setPw(pw);
		}

		List<MemberVO> result = memberService.selectMember(memberVO);
		MemberVO obj;

		if(result.size()>0) {
			// 사용자의 권한과 이름을 Session에 저장
			obj = (MemberVO) result.get(0);
			System.out.println("object");
			
			if(memberVO.getPw()==null || !memberVO.getPw().equals(obj.getPw())) {
				throw new LoginFailureException("비밀번호가 일치하지 않습니다.");
			}
			
			Cookie cookie;

			//아이디 저장 체크했으면
			if ( saveid > 0 ) {
				cookie = new Cookie("CSBRAIN_SAVE_ID", id);
			} else {
				cookie = new Cookie("CSBRAIN_SAVE_ID", "");
			}

			cookie.setMaxAge((int) (new Date().getTime()/1000 + 30*60*60*24));
			cookie.setPath("/");

			//쿠키에 로그인 정보 저장. 오랜시간 켜져있으면 세션만으로는 지속하기 힘듬.
			response.setContentType("text/plain;charset=UTF-8");
			response.addCookie(cookie);

			// session 생성 및 사용자 권한 확인
			for(MemberVO vo : result) {
				String id_enc = Md5Util.encrypt_md5(vo.getId(), Constant.SUPER_ENC_PASS_STRING);
				String pass_enc = Md5Util.encrypt_md5(vo.getPw(), Constant.SUPER_ENC_PASS_STRING);
				String name_enc = Md5Util.encrypt_md5(vo.getName(), Constant.SUPER_ENC_PASS_STRING);
				String auth_enc = Md5Util.encrypt_md5(vo.getAuth(), Constant.SUPER_ENC_PASS_STRING);

				String csbrain_cookie = id_enc + "|&|" + pass_enc + "|&|" + name_enc + "|&|" + auth_enc;
				cookie = new Cookie("CSBRAIN_COOKIE", csbrain_cookie);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);

				session.setAttribute("USERIDCOOKIE", id_enc);
				session.setAttribute("USERPASSCOOKIE", pass_enc);
				session.setAttribute("USERNAMECOOKIE", name_enc);
				session.setAttribute("USERAUTHCOOKIE", auth_enc);

				session.setAttribute(Constant.SESSION_MEMBER, vo);

				Constant.USER.put("ID", id);

				//접속기록
				Constant.save_start_log(request, response, session, memberService, "00000000");

				model.put("success", true);

				//로그인 성공
				if (logger.isDebugEnabled()) {
					logger.info("USER:[" + vo.getId()+ "]");
				}
			}

			//session.setMaxInactiveInterval(60 * 60);

		}else {
			throw new LoginFailureException("로그인 정보가 다릅니다.");
		}


		return new ModelAndView("jsonView", model);
	}

	@RequestMapping({"/main.see", "/subpage.see"})
	public ModelAndView seeJsp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();

		String uri = request.getRequestURI().replaceFirst(request.getContextPath(), "").replace(".see", "");
		mav.setViewName(uri);
		if("/subpage".equals(uri)) {
			mav.addObject("menu_id", request.getParameter("menu_id"));
		}

		return mav;
	}

	@RequestMapping("/include/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @ModelAttribute("memberVO") MemberVO memberVO,	ModelMap model) throws Exception {

		if (session != null) {
			session.removeAttribute("USERIDCOOKIE");
			session.removeAttribute("USERPASSCOOKIE");
			session.removeAttribute("USERNAMECOOKIE");
			session.removeAttribute("USERAUTHCOOKIE");
			session.removeAttribute(Constant.SESSION_MEMBER);
			session.invalidate();
		}

		Cookie cookie = new Cookie(Constant.CSBRAIN_COOKIE, "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		//종료 로그
		Constant.save_end_log(request, response, session, memberService);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>try{ window.external.application_exit(); } catch(e) {}</script>");

		return new ModelAndView("redirect:/index.jsp");
	}
}