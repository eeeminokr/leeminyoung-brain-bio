package csbrain.data.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import csbrain.common.service.CommonService;
import csbrain.common.util.Constant;
import csbrain.common.util.DateUtil;
import csbrain.data.service.DataService;
import csbrain.main.service.MemberService;

@Controller
public class DataNoIncController {

	/** Sevice Class */
	@Resource(name = "MemberService")
	private MemberService memberService;

	@Resource(name = "DataService")
	private DataService dataService;

	@Resource(name = "CommonService")
	private CommonService commonService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/csbr3010_upload_action.do")
	public ModelAndView csbr3010_upload_action(@RequestParam("upfile") MultipartFile upfile, MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/csbr3010_upload_action");

		//저장파일이름
		String save_filename = "upload/" + "xls_upload_" + DateUtil.getSimpleDate("yyyyMMddHHmmss") + request.getRemoteAddr().replaceAll(".", "");

		boolean tf = false;
		String htmlTag = "";

		try {
			if(upfile == null) {
				mav.addObject("msg", "파일이 없습니다.");
				return mav;
			}else if (upfile.getSize() > 0) {
				String org_filename = "";
				String org_extension = "";
				String filename = "";
				try {
						org_filename = upfile.getOriginalFilename();
						org_extension = FilenameUtils.getExtension(org_filename).toLowerCase();
						if(!"xls".equals(org_extension)){
							mav.addObject("msg", "엑셀파일이 아닙니다.");
							return mav;
						}

						File file = new File(save_filename);

						// directory 없을경우
						if (!file.exists()) {
							file.mkdirs();
						}
						upfile.transferTo(file);
						if (!file.exists()) {
							mav.addObject("msg", "파일 저장에 실패했습니다. 개발자에게 문의바랍니다.");
							return mav;
						}

						//엑셀파일 읽기
						try {
							FileInputStream fis = new FileInputStream(save_filename);
							HSSFWorkbook workbook = new HSSFWorkbook(fis);

							//첫번째 시트
							HSSFSheet worksheet = workbook.getSheet("1_Excel1_snsb2data");
							if ( worksheet == null ) {
								mav.addObject("msg", "1_Excel1_snsb2data 시트가 없는 잘못된 파일입니다.");
								return mav;
							}

							//제목줄 체크 ( 처음(A1)=고유번호 마지막(IT1)=RCFT_recognition_score_s
							CellReference cr = new CellReference("A1");
							String t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"고유번호".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}
							cr = new CellReference("IT1");
							t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"RCFT_recognition_score_s".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}

							//데이터 추출
							int irow = 2; //두번재 줄부터
							int success_cnt = 0;
							int fail_cnt = 0;
							String d = "";

							do {
								cr = new CellReference("A" + irow);
								d = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								String d_query = "";
								for(int col=1;col<=254;col++){
									//String col_str = CellReference.convertNumToColString(col-1); //열번호로 알파벳 추출
									if ( !"".equals(d_query) ) d_query += ", ";
									d_query += "'" + worksheet.getRow(cr.getRow()).getCell(col-1).getStringCellValue() + "'";
								}

								String unum = d;
								//기존것 지우기
								String delResult = dataService.delete_mb_snsb2data_excel1(unum);
								String[] arrDelResult = delResult.split("/");
								String query = arrDelResult[0];
								int rtnCnt = Integer.parseInt(arrDelResult[1]);
								htmlTag += query + "<br>";

								//입력
								Map<String, String> map = new HashMap<String, String>(2);
								map.put("userId", Constant.USER.get("ID"));
								
								System.out.println("-----------------------------------------------------------------");
								System.out.println( Constant.USER.get("ID")+"EXEL 1 ID 값인데 무슨값이냐 너는?????????????????????????");
								System.out.println("-----------------------------------------------------------------");
								
								
								map.put("dQuery", d_query);

								String insResult = dataService.insert_mb_snsb2data_excel1(map);
								String[] arrInsResult = insResult.split("/");
								query = arrInsResult[0];
								rtnCnt = Integer.parseInt(arrInsResult[1]);
								htmlTag += query + "<br>";

								if(rtnCnt > 0) success_cnt++;
								else fail_cnt++;

								irow++;
							} while (!"".equals(d)); //고유번호가 있는곳 까지만

							String r = "[SNSB2DATA_EXCEL1]\\n성공 : " + success_cnt + "건\\n실패 : " + fail_cnt + "건\\n";

							//두번째 시트
							worksheet = workbook.getSheet("2_Excel2_snsb2data");
							if ( worksheet == null ) {
								mav.addObject("msg", "2_Excel2_snsb2data 시트가 없는 잘못된 파일입니다.");
								return mav;
							}

							//제목줄 체크 ( 처음(A1)=고유번호 마지막(DH1)=31Other
							cr = new CellReference("A1");
							t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"고유번호".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}
							cr = new CellReference("DH1");
							t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"31Other".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}

							//데이터 추출
							irow = 2; //두번재 줄부터
							success_cnt = 0;
							fail_cnt = 0;
							d = "";

							do {
								cr = new CellReference("A" + irow);
								d = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								String d_query = "";
								for(int col=1;col<=112;col++){
									//String col_str = CellReference.convertNumToColString(col-1); //열번호로 알파벳 추출
									if ( !"".equals(d_query) ) d_query += ", ";
									d_query += "'" + worksheet.getRow(cr.getRow()).getCell(col-1).getStringCellValue() + "'";
								}

								String unum = d;
								//기존것 지우기
								String delResult = dataService.delete_mb_snsb2data_excel2(unum);
								String[] arrDelResult = delResult.split("/");
								String query = arrDelResult[0];
								int rtnCnt = Integer.parseInt(arrDelResult[1]);
								htmlTag += query + "<br>";

								//입력
								Map<String, String> map = new HashMap<String, String>(2);
								map.put("userId", Constant.USER.get("ID"));
								
								System.out.println("-----------------------------------------------------------------");
								System.out.println( Constant.USER.get("ID")+"EXEL 2 ID 값인데 무슨값이냐 너는?????????????????????????");
								System.out.println("-----------------------------------------------------------------");
								map.put("dQuery", d_query);

								String insResult = dataService.insert_mb_snsb2data_excel2(map);
								String[] arrInsResult = insResult.split("/");
								query = arrInsResult[0];
								rtnCnt = Integer.parseInt(arrInsResult[1]);
								htmlTag += query + "<br>";

								if(rtnCnt > 0) success_cnt++;
								else fail_cnt++;

								irow++;
							} while (!"".equals(d)); //고유번호가 있는곳 까지만

							r += "[SNSB2DATA_EXCEL2]\\n성공 : " + success_cnt + "건\\n실패 : " + fail_cnt + "건\\n";
							mav.addObject("msg", r);
							return mav;

						} catch (IOException e) {
							e.printStackTrace();
							mav.addObject("msg", e.getMessage());
							return mav;
						}

				}catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(tf)mav.addObject("success", true);
			else mav.addObject("success", false);
		}catch(Exception e) {
			mav.addObject("success", false);
			mav.addObject("msg", e.getMessage());
			e.printStackTrace();
		}finally {

		}

		return mav;
	}
	
	
	
	
	//CSBR3020 PET EXCEL업로드 CONTROLLER 부분
	
	
	@RequestMapping("/csbr3020_upload_action.do")
	public ModelAndView csbr3020_upload_action(@RequestParam("upfile") MultipartFile upfile, MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/csbr3020_upload_action");

		//저장파일이름
		String save_filename = "upload/" + "xls_upload_" + DateUtil.getSimpleDate("yyyyMMddHHmmss") + request.getRemoteAddr().replaceAll(".", "");

		boolean tf = false;
		String htmlTag = "";

		try {
			if(upfile == null) {
				mav.addObject("msg", "파일이 없습니다.");
				return mav;
			}else if (upfile.getSize() > 0) {
				String org_filename = "";
				String org_extension = "";
				String filename = "";
				try {
						org_filename = upfile.getOriginalFilename();
						org_extension = FilenameUtils.getExtension(org_filename).toLowerCase();
						if(!"xls".equals(org_extension)){
							mav.addObject("msg", "엑셀파일이 아닙니다.");
							return mav;
						}

						File file = new File(save_filename);

						// directory 없을경우
						if (!file.exists()) {
							file.mkdirs();
						}
						upfile.transferTo(file);
						if (!file.exists()) {
							mav.addObject("msg", "파일 저장에 실패했습니다. 개발자에게 문의바랍니다.");
							return mav;
						}

						//엑셀파일 읽기
						try {
							FileInputStream fis = new FileInputStream(save_filename);
							HSSFWorkbook workbook = new HSSFWorkbook(fis);

							//첫번째 시트
							HSSFSheet worksheet = workbook.getSheet("Excel_pet_data");
							if ( worksheet == null ) {
								mav.addObject("msg", "Excel_pet_data 시트가 없는 잘못된 파일입니다.");
								return mav;
							}

							//제목줄 체크 ( 처음(A1)=고유번호 마지막(IT1)=RCFT_recognition_score_s
							CellReference cr = new CellReference("A1");
							String t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"고유번호".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}
							cr = new CellReference("IT1");
							t = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
							if(!"RCFT_recognition_score_s".equals(t)) {
								mav.addObject("msg", "잘못된 양식입니다.");
								return mav;
							}

							//데이터 추출
							int irow = 2; //두번재 줄부터
							int success_cnt = 0;
							int fail_cnt = 0;
							String d = "";

							do {
								cr = new CellReference("A" + irow);
								d = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								String d_query = "";
								for(int col=1;col<=254;col++){
									//String col_str = CellReference.convertNumToColString(col-1); //열번호로 알파벳 추출
									if ( !"".equals(d_query) ) d_query += ", ";
									d_query += "'" + worksheet.getRow(cr.getRow()).getCell(col-1).getStringCellValue() + "'";
								}

								String unum = d;
								//기존것 지우기
								String delResult = dataService.delete_mb_rctu_object_respet_suvr(unum);
								String[] arrDelResult = delResult.split("/");
								String query = arrDelResult[0];
								int rtnCnt = Integer.parseInt(arrDelResult[1]);
								htmlTag += query + "<br>";

								//입력
								Map<String, String> map = new HashMap<String, String>(2);
								map.put("userId", Constant.USER.get("ID"));
								
								System.out.println(Constant.USER.get("ID"));
								
								
								map.put("dQuery", d_query);

								String insResult = dataService.insert_mb_rctu_object_respet_suvr(map);
								String[] arrInsResult = insResult.split("/");
								query = arrInsResult[0];
								rtnCnt = Integer.parseInt(arrInsResult[1]);
								htmlTag += query + "<br>";

								if(rtnCnt > 0) success_cnt++;
								else fail_cnt++;

								irow++;
							} while (!"".equals(d)); //고유번호가 있는곳 까지만

							String r = "[PETDATA]\\n성공 : " + success_cnt + "건\\n실패 : " + fail_cnt + "건\\n";


							//데이터 추출
							irow = 2; //두번재 줄부터
							success_cnt = 0;
							fail_cnt = 0;
							d = "";

							do {
								cr = new CellReference("A" + irow);
								d = worksheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								String d_query = "";
								for(int col=1;col<=112;col++){
									//String col_str = CellReference.convertNumToColString(col-1); //열번호로 알파벳 추출
									if ( !"".equals(d_query) ) d_query += ", ";
									d_query += "'" + worksheet.getRow(cr.getRow()).getCell(col-1).getStringCellValue() + "'";
								}

								String petid = d;
								//기존것 지우기
								String delResult = dataService.delete_mb_rctu_object_respet_suvr(petid);
								String[] arrDelResult = delResult.split("/");
								String query = arrDelResult[0];
								int rtnCnt = Integer.parseInt(arrDelResult[1]);
								htmlTag += query + "<br>";

								//입력
								Map<String, String> map = new HashMap<String, String>(2);
								map.put("userId", Constant.USER.get("ID"));
								map.put("dQuery", d_query);

								String insResult = dataService.insert_mb_rctu_object_respet_suvr(map);
								String[] arrInsResult = insResult.split("/");
								query = arrInsResult[0];
								rtnCnt = Integer.parseInt(arrInsResult[1]);
								htmlTag += query + "<br>";

								if(rtnCnt > 0) success_cnt++;
								else fail_cnt++;

								irow++;
							} while (!"".equals(d)); //고유번호가 있는곳 까지만

							r += "[EXCEL_pet_data]\\n성공 : " + success_cnt + "건\\n실패 : " + fail_cnt + "건\\n";
							mav.addObject("msg", r);
							return mav;

						} catch (IOException e) {
							e.printStackTrace();
							mav.addObject("msg", e.getMessage());
							return mav;
						}

				}catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(tf)mav.addObject("success", true);
			else mav.addObject("success", false);
		}catch(Exception e) {
			mav.addObject("success", false);
			mav.addObject("msg", e.getMessage());
			e.printStackTrace();
		}finally {

		}

		return mav;
	}
}