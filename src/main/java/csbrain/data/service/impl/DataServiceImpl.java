package csbrain.data.service.impl;

import csbrain.common.util.ExcelRead;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import csbrain.common.service.AbstractService;
import csbrain.common.util.ExcelReadOption;
import csbrain.data.controller.InBody1;
import csbrain.data.controller.InBody2;
import csbrain.data.service.Csbr1020ListDataVO;
import csbrain.data.service.Csbr1031SearchVO;
import csbrain.data.service.Csbr1050InfoVO;
import csbrain.data.service.Csbr1050SearchVO;
import csbrain.data.service.Csbr7001SearchVO;
import csbrain.data.service.DataService;
import csbrain.data.service.MbCsfhdVO;
import csbrain.data.service.MbMriTestblodVO;
import csbrain.data.service.MbNrcdVO;
import csbrain.data.service.MbObjectVO;
import csbrain.data.service.MbResebsVO;
import csbrain.data.service.MbResebwVO;
import csbrain.data.service.MbReseckVO;
import csbrain.data.service.MbResecsfVO;
import csbrain.data.service.MbResecstVO;
import csbrain.data.service.MbReseibVO;
import csbrain.data.service.MbResemriVO;
import csbrain.data.service.MbResepetVO;
import csbrain.data.service.MbReseselVO;
import csbrain.data.service.MbResesnsbVO;
import csbrain.data.service.MbSpecimdtMbCsfdtVO;
import csbrain.data.service.MbSpecimhdVO;

@Service("DataService")
public class DataServiceImpl extends AbstractService implements DataService{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** DAO Class */
	@Resource(name = "DataDAO")
	private DataDAO dataDAO;

	@Override
	public List<Map<String, String>> select_csbr_left_data(Map<String, String> map) {
		return dataDAO.select_csbr_left_data(map);
	}

	@Override
	public List<MbReseselVO> select_csbr2010_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2010_right_data(map);
	}

	@Override
	public int delete_csbr2010_mb_resesel(Map<String, String> map) {
		return dataDAO.delete_csbr2010_mb_resesel(map);
	}

	@Override
	public int update_csbr2010_mb_resesel(MbReseselVO mrvo) {
		return dataDAO.update_csbr2010_mb_resesel(mrvo);
	}

	@Override
	public String insert_csbr2010_mb_resesel(MbReseselVO mrvo) {
		return dataDAO.insert_csbr2010_mb_resesel(mrvo);
	}

	@Override
	public List<MbResesnsbVO> select_csbr2020_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2020_right_data(map);
	}

	@Override
	public int delete_csbr2020_mb_resesnsb(Map<String, String> map) {
		return dataDAO.delete_csbr2020_mb_resesnsb(map);
	}

	@Override
	public int update_csbr2020_mb_resesnsb(MbResesnsbVO mrvo) {
		return dataDAO.update_csbr2020_mb_resesnsb(mrvo);
	}

	@Override
	public String insert_csbr2020_mb_resesnsb(MbResesnsbVO mrvo) {
		return dataDAO.insert_csbr2020_mb_resesnsb(mrvo);
	}

	@Override
	public List<MbObjectVO> select_mb_object(String st) {
		return dataDAO.select_mb_object(st);
	}

	@Override
	public List<MbResemriVO> select_csbr2040_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2040_right_data(map);
	}

	@Override
	public int delete_csbr2040_mb_resemri(Map<String, String> map) {
		return dataDAO.delete_csbr2040_mb_resemri(map);
	}

	@Override
	public int update_csbr2040_mb_resemri(MbResemriVO mrvo) {
		return dataDAO.update_csbr2040_mb_resemri(mrvo);
	}

	@Override
	public String selectMaxResersnoSeq(Map<String, String> map) {
		return dataDAO.selectMaxResersnoSeq(map);
	}

	@Override
	public String insert_csbr2040_mb_resemri(MbResemriVO mrvo) {
		return dataDAO.insert_csbr2040_mb_resemri(mrvo);
	}

	@Override
	public int update_csbr_mriid(Map<String, String> map) {
		return dataDAO.update_csbr_mriid(map);
	}

	@Override
	public List<MbResecstVO> select_csbr2050_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2050_right_data(map);
	}

	@Override
	public int delete_csbr2050_mb_resecst(Map<String, String> map) {
		return dataDAO.delete_csbr2050_mb_resecst(map);
	}

	@Override
	public int update_csbr2050_mb_resecst(MbResecstVO mrvo) {
		return dataDAO.update_csbr2050_mb_resecst(mrvo);
	}

	@Override
	public String insert_csbr2050_mb_resecst(MbResecstVO mrvo) {
		return dataDAO.insert_csbr2050_mb_resecst(mrvo);
	}

	@Override
	public List<MbResepetVO> select_csbr2060_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2060_right_data(map);
	}

	@Override
	public int delete_csbr2060_mb_resepet(Map<String, String> map) {
		return dataDAO.delete_csbr2060_mb_resepet(map);
	}

	@Override
	public int update_csbr2060_mb_resepet(MbResepetVO mrvo) {
		return dataDAO.update_csbr2060_mb_resepet(mrvo);
	}

	@Override
	public String insert_csbr2060_mb_resepet(MbResepetVO mrvo) {
		return dataDAO.insert_csbr2060_mb_resepet(mrvo);
	}

	@Override
	public List<MbResecsfVO> select_csbr2070_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2070_right_data(map);
	}

	@Override
	public int delete_csbr2070_mb_resecsf(Map<String, String> map) {
		return dataDAO.delete_csbr2070_mb_resecsf(map);
	}

	@Override
	public int update_csbr2070_mb_resecsf(MbResecsfVO mrvo) {
		return dataDAO.update_csbr2070_mb_resecsf(mrvo);
	}

	@Override
	public String insert_csbr2070_mb_resecsf(MbResecsfVO mrvo) {
		return dataDAO.insert_csbr2070_mb_resecsf(mrvo);
	}

	@Override
	public List<MbObjectVO> select_csbr1010_search(Map<String, String> map) {
		return dataDAO.select_csbr1010_search(map);
	}

	@Override
	public List<Map<String, String>> select_csbr1020_left_data(Map<String, String> map) {
		return dataDAO.select_csbr1020_left_data(map);
	}

	@Override
	public List<Csbr1020ListDataVO> select_csbr1020_list_data(String d) {
		return dataDAO.select_csbr1020_list_data(d);
	}

	@Override
	public List<Csbr1031SearchVO> select_csbr1031_search(Map<String, String> map) {
		return dataDAO.select_csbr1031_search(map);
	}
	
	@Override
	public List<Csbr1050SearchVO> select_csbr1050_search(Map<String, String> map) {
		return dataDAO.select_csbr1050_search(map);
	}

	@Override
	public List<Csbr1050InfoVO> select_csbr1050_info(Map<String, String> map) {
		return dataDAO.select_csbr1050_info(map);
	}

	@Override
	public int delete_csbr1050_mb_nrcd(Map<String, String> map) {
		return dataDAO.delete_csbr1050_mb_nrcd(map);
	}

	@Override
	public String insert_csbr1050_mb_nrcd(MbNrcdVO mbNrcdVO) {
		return dataDAO.insert_csbr1050_mb_nrcd(mbNrcdVO);
	}

	@Override
	public int select_count_mb_snsb2data_excel1(Map<String, String> map) {
		return dataDAO.select_count_mb_snsb2data_excel1(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3010_search_1(Map<String, String> map) {
		return dataDAO.select_csbr3010_search_1(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3010_search_2(Map<String, String> map) {
		return dataDAO.select_csbr3010_search_2(map);
	}
	
	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3010_target_search(Map<String, String> map) {
		return dataDAO.select_csbr3010_target_search(map);
	}
	
	@Override
	public Map<String,Object> select_csbr3010_next_selection_num(Map<String, String> map) {
		return dataDAO.select_csbr3010_next_selection_num(map);
	}
	
	@Override
	public Map<String,Object> select_csbr3010_snsb(Map<String, String> map) {
		return dataDAO.select_csbr3010_snsb(map);
	}
	
	@Override
	public int insert_csbr3010_snsb(Map<String,String> map) {
		return dataDAO.insert_csbr3010_snsb(map);
	}
	@Override
	public int update_csbr3010_snsb(Map<String,String> map) {
		return dataDAO.update_csbr3010_snsb(map);
	}
	
	@Override
	public int update_csbr3010_resesnsb(Map<String,String> map) {
		return dataDAO.update_csbr3010_resesnsb(map);
	}
	@Override
	public int select_count_csbr3010_search_3(Map<String,String> map) {
		return dataDAO.select_count_csbr3010_search_3(map);
	}
	
	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3010_search_3(Map<String, String> map) {
		return dataDAO.select_csbr3010_search_3(map);
	}
	
	@Override
	public String delete_mb_snsb2data_excel1(String unum) {
		return dataDAO.delete_mb_snsb2data_excel1(unum);
	}

	@Override
	public String insert_mb_snsb2data_excel1(Map<String, String> map) {
		return dataDAO.insert_mb_snsb2data_excel1(map);
	}

	@Override
	public String delete_mb_snsb2data_excel2(String unum) {
		return dataDAO.delete_mb_snsb2data_excel2(unum);
	}

	@Override
	public String insert_mb_snsb2data_excel2(Map<String, String> map) {
		return dataDAO.insert_mb_snsb2data_excel2(map);
	}
	
	//csbr3020 pet검사결과
	@Override
	public int select_count_mb_rctu_object_respet_suvr(Map<String, String> map) {
		return dataDAO.select_count_mb_rctu_object_respet_suvr(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3020_search(Map<String, String> map) {
		return dataDAO.select_csbr3020_search(map);
	}
	
	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3020_target_search(Map<String, String> map) {
		return dataDAO.select_csbr3020_target_search(map);
	}
	
	@Override
	public Map<String,Object> select_csbr3020_next_selection_num(Map<String, String> map) {
		return dataDAO.select_csbr3020_next_selection_num(map);
	}
	
	@Override
	public Map<String,Object> select_csbr3020_pet(Map<String, String> map) {
		return dataDAO.select_csbr3020_pet(map);
	}
	
	@Override
	public int insert_csbr3020_pet(Map<String,String> map) {
		return dataDAO.insert_csbr3020_pet(map);
	}
	@Override
	public int update_csbr3020_pet(Map<String,String> map) {
		return dataDAO.update_csbr3020_pet(map);
	}
	@Override
	public int update_csbr3020_resepet(Map<String,String> map) {
		return dataDAO.update_csbr3020_resepet(map);
	}
	@Override
	public String delete_mb_rctu_object_respet_suvr(String unum) {
		return dataDAO.delete_mb_rctu_object_respet_suvr(unum);
	}

	@Override
	public String insert_mb_rctu_object_respet_suvr(Map<String, String> map) {
		return dataDAO.insert_mb_rctu_object_respet_suvr(map);
	}
	
	@Override
	public int select_count_csbr3030_csf(Map<String, String> map) {
		return dataDAO.select_count_csbr3030_csf(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3030_search(Map<String, String> map) {
		return dataDAO.select_csbr3030_search(map);
	}
	
	@Override
	public int select_count_csbr3060_inbody1(Map<String, String> map) {
		return dataDAO.select_count_csbr3060_inbody1(map);
	}

	@Override
	public int select_count_csbr3060_inbody2(Map<String, String> map) {
		return dataDAO.select_count_csbr3060_inbody2(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3060_search1(Map<String, String> map) {
		return dataDAO.select_csbr3060_search1(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3060_search2(Map<String, String> map) {
		return dataDAO.select_csbr3060_search2(map);
	}
	
	//csbr3080 apoe
	@Override
	public int select_count_csbr3080_apoe(Map<String, String> map) {
		return dataDAO.select_count_csbr3080_apoe(map);
	}
	
	@Override
	public List<LinkedHashMap<String, Object>> select_csbr3080_search(Map<String, String> map) {
		return dataDAO.select_csbr3080_search(map);
	}
	
	@Override
	public String delete_csbr4010_mb_testblod(Map<String, String> map) {
		return dataDAO.delete_csbr4010_mb_testblod(map);
	}

	@Override
	public List<MbMriTestblodVO> select_csbr4010_content(Map<String, String> map) {
		return dataDAO.select_csbr4010_content(map);
	}

	@Override
	public int select_count_mb_testblod(Map<String, String> map) {
		return dataDAO.select_count_mb_testblod(map);
	}

	@Override
	public String update_csbr4010_mb_testblod(MbMriTestblodVO mtvo) {
		return dataDAO.update_csbr4010_mb_testblod(mtvo);
	}

	@Override
	public String insert_csbr4010_mb_testblod(MbMriTestblodVO mtvo) {
		return dataDAO.insert_csbr4010_mb_testblod(mtvo);
	}

	@Override
	public String delete_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.delete_csbr5020_mb_specimdt(msvo);
	}

	@Override
	public String update_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.update_csbr5020_mb_specimdt(msvo);
	}

	@Override
	public String insert_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.insert_csbr5020_mb_specimdt(msvo);
	}

	@Override
	public List<MbSpecimdtMbCsfdtVO> select_csbr5020_mb_specimdt(Map<String, String> map) {
		return dataDAO.select_csbr5020_mb_specimdt(map);
	}

	@Override
	public List<Map<String, String>> select_csbr5020_left_data(Map<String, String> map) {
		return dataDAO.select_csbr5020_left_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr5020_right_data(String resedate) {
		return dataDAO.select_csbr5020_right_data(resedate);
	}


	@Override
	public String update_csbr5020_mb_specimhd(MbSpecimhdVO msvo) {
		return dataDAO.update_csbr5020_mb_specimhd(msvo);
	}

	@Override
	public String insert_csbr5020_mb_specimhd(MbSpecimhdVO msvo) {
		return dataDAO.insert_csbr5020_mb_specimhd(msvo);
	}

	@Override
	public String select_count_csbr5020_mb_specimhd(Map<String, String> map) {
		return dataDAO.select_count_csbr5020_mb_specimhd(map);
	}

	@Override
	public String delete_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.delete_csbr5040_mb_csfdt(msvo);
	}

	@Override
	public String update_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.update_csbr5040_mb_csfdt(msvo);
	}

	@Override
	public String insert_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		return dataDAO.insert_csbr5040_mb_csfdt(msvo);
	}

	@Override
	public List<MbSpecimdtMbCsfdtVO> select_csbr5040_mb_csfdt(Map<String, String> map) {
		return dataDAO.select_csbr5040_mb_csfdt(map);
	}

	@Override
	public List<Map<String, String>> select_csbr5040_left_data(Map<String, String> map) {
		return dataDAO.select_csbr5040_left_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr5040_right_data(String d) {
		return dataDAO.select_csbr5040_right_data(d);
	}

	@Override
	public String select_count_csbr5040_mb_csfhd(Map<String, String> map) {
		return dataDAO.select_count_csbr5040_mb_csfhd(map);
	}

	@Override
	public String update_csbr5040_mb_csfhd(MbCsfhdVO mcvo) {
		return dataDAO.update_csbr5040_mb_csfhd(mcvo);
	}

	@Override
	public String insert_csbr5040_mb_csfhd(MbCsfhdVO mcvo) {
		return dataDAO.insert_csbr5040_mb_csfhd(mcvo);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7001_search(Map<String, String> map) {
		return dataDAO.select_csbr7001_search(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7003_search() {
		return dataDAO.select_csbr7003_search();
	}

	@Override
	public int select_count_csbr7004_mb_specimhd() {
		return dataDAO.select_count_csbr7004_mb_specimhd();
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7004_search(String limitQuery) {
		return dataDAO.select_csbr7004_search(limitQuery);
	}

	@Override
	public int select_count_csbr7005_mb_object(Map<String, String> map) {
		return dataDAO.select_count_csbr7005_mb_object(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7005_search(Map<String, String> map) {
		return dataDAO.select_csbr7005_search(map);
	}

	@Override
	public int select_count_csbr7006_mb_object() {
		return dataDAO.select_count_csbr7006_mb_object();
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7006_search(String limitQuery) {
		return dataDAO.select_csbr7006_search(limitQuery);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7007_search(Map<String, String> map) {
		return dataDAO.select_csbr7007_search(map);
	}

	@Override
	public int select_count_csbr7008_mb_snsb(Map<String, String> map) {
		return dataDAO.select_count_csbr7008_mb_snsb(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7008_search(Map<String, String> map) {
		return dataDAO.select_csbr7008_search(map);
	}

	@Override
	public int select_count_csbr7009_mb_dm(Map<String, String> map) {
		return dataDAO.select_count_csbr7009_mb_dm(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7009_search(Map<String, String> map) {
		return dataDAO.select_csbr7009_search(map);
	}

	@Override
	public int select_count_csbr7010_mb_object(Map<String, String> map) {
		return dataDAO.select_count_csbr7010_mb_object(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7010_search(Map<String, String> map) {
		return dataDAO.select_csbr7010_search(map);
	}

	@Override
	public int select_count_csbr7011_mb_object(Map<String, String> map) {
		return dataDAO.select_count_csbr7011_mb_object(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7011_search(Map<String, String> map) {
		return dataDAO.select_csbr7011_search(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9020_left_data(String searchid) {
		return dataDAO.select_csbr9020_left_data(searchid);
	}

	@Override
	public List<Map<String, String>> select_csbr9020_right_data(String id) {
		return dataDAO.select_csbr9020_right_data(id);
	}

	@Override
	public List<Map<String, String>> select_csbr9020_right_data2(String id) {
		return dataDAO.select_csbr9020_right_data2(id);
	}

	@Override
	public List<Map<String, String>> select_csbr9020_nrcd_data(String id) {
		return dataDAO.select_csbr9020_nrcd_data(id);
	}

	@Override
	public List<Map<String, String>> select_csbr9020_nrcd_data2(String id) {
		return dataDAO.select_csbr9020_nrcd_data2(id);
	}

	@Override
	public int select_count_mb_subject(String userid) {
		return dataDAO.select_count_mb_subject(userid);
	}

	@Override
	public Map<String, String> select_mb_subject(String userid) {
		return dataDAO.select_mb_subject(userid);
	}

	@Override
	public String update_mb_subject(Map<String, String> map) {
		return dataDAO.update_mb_subject(map);
	}

	@Override
	public String insert_mb_subject(Map<String, String> map) {
		return dataDAO.insert_mb_subject(map);
	}

	@Override
	public int select_count_mb_pempower(Map<String, String> map) {
		return dataDAO.select_count_mb_pempower(map);
	}

	@Override
	public String delete_mb_pempower(Map<String, String> map) {
		return dataDAO.delete_mb_pempower(map);
	}

	@Override
	public String update_mb_pempower(Map<String, String> map) {
		return dataDAO.update_mb_pempower(map);
	}

	@Override
	public String insert_mb_pempower(Map<String, String> map) {
		return dataDAO.insert_mb_pempower(map);
	}

	@Override
	public int insert_mb_nrcdpower(Map<String, String> map) {
		return dataDAO.insert_mb_nrcdpower(map);
	}

	@Override
	public int delete_mb_nrcdpower(Map<String, String> map) {
		return dataDAO.delete_mb_nrcdpower(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9030_left_data(Map<String, String> map) {
		return dataDAO.select_csbr9030_left_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9030_right_data(Map<String, String> map) {
		return dataDAO.select_csbr9030_right_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9140_left_right_data(Map<String, String> map) {
		return dataDAO.select_csbr9140_left_right_data(map);
	}

	@Override
	public String update_csbr9140_left_mb_etccd(Map<String, String> map) {
		return dataDAO.update_csbr9140_left_mb_etccd(map);
	}

	@Override
	public String insert_csbr9140_left_mb_etccd(Map<String, String> map) {
		return dataDAO.insert_csbr9140_left_mb_etccd(map);
	}

	@Override
	public String update_csbr9140_right_mb_etccd(Map<String, String> map) {
		return dataDAO.update_csbr9140_right_mb_etccd(map);
	}

	@Override
	public String insert_csbr9140_right_mb_etccd(Map<String, String> map) {
		return dataDAO.insert_csbr9140_right_mb_etccd(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9201_left_data(Map<String, String> map) {
		return dataDAO.select_csbr9201_left_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9201_right_data(Map<String, String> map) {
		return dataDAO.select_csbr9201_right_data(map);
	}

	@Override
	public List<Map<String, String>> select_csbr9920_left_data() {
		return dataDAO.select_csbr9920_left_data();
	}

	@Override
	public List<Map<String, String>> select_csbr9920_right_data(String progid) {
		return dataDAO.select_csbr9920_right_data(progid);
	}

	@Override
	public List<Map<String, String>> select_csbr9920_right_data2(String progid) {
		return dataDAO.select_csbr9920_right_data2(progid);
	}

	@Override
	public int update_csbr9920_left_mb_proglist(Map<String, String> map) {
		return dataDAO.update_csbr9920_left_mb_proglist(map);
	}

	@Override
	public int insert_csbr9920_left_mb_proglist(Map<String, String> map) {
		return dataDAO.insert_csbr9920_left_mb_proglist(map);
	}

	@Override
	public List<LinkedHashMap<String, Object>> select_csbr7001_search2(Map<String, String> map) {
		return dataDAO.select_csbr7001_search2(map);
	}

	@Override
	public void excelUpload(File destFile, int SheetNo, String tableName, String userId) throws IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		
		// 파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		
		// 추출할 컬럼명 추가
		//excelReadOption.setOutputColumns("a","b");
		
		// 시작행
		excelReadOption.setStartRow(1);
		
		List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption, SheetNo);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("excelContent", excelContent);
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		for(int i = 0; i<excelContent.size(); i++) {
			
			String unum = excelContent.get(i).get("Unum");
			String testD = "Y";
			if(tableName == "mb_snsb2data_excel1") {
				if(excelContent.get(i).get("Test_Day") == null || excelContent.get(i).get("Test_Day") == "") {
					testD = "N";
				}
			}
			
			if( unum == null || unum == "" || unum == " " || testD == "N") {
				//excelContent.remove(i);
			}else { 
				
				excelContent.get(i).put("Insert_UserID", userId);
				list.add(excelContent.get(i));
				
			}
			
		}
		
		try {
			// upsert시작
			if(excelContent.size() > 0) {
				if(tableName == "mb_snsb2data_excel1") {
					dataDAO.excelUpload(list);
				}else if(tableName == "mb_snsb2data_excel2"){
					dataDAO.excelUpload2(list);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int update_csbr1050_pdfPath(Map<String, String> map) {
		return dataDAO.update_csbr1050_pdfPath(map);
	}
	
	@Override
	public Map<String,Object> select_csbr1050_object(Map<String, String> map) {
		return dataDAO.select_csbr1050_object(map);
	}
	
	@Override
	public Map<String,Object> select_csbr1050_cdr(Map<String, String> map) {
		return dataDAO.select_csbr1050_cdr(map);
	}
	
	@Override
	public int insert_csbr1050_cdr(Map<String,String> map) {
		return dataDAO.insert_csbr1050_cdr(map);
	}
	@Override
	public int insert_csbr1050_cdr_detail(Map<String,String> map) {
		return dataDAO.insert_csbr1050_cdr_detail(map);
	}
	@Override
	public int update_csbr1050_cdr(Map<String,String> map) {
		return dataDAO.update_csbr1050_cdr(map);
	}
	@Override
	public int update_csbr1050_cdr_detail(Map<String,String> map) {
		return dataDAO.update_csbr1050_cdr_detail(map);
	}

	@Override
	public List<MbResebwVO> select_csbr2080_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2080_right_data(map);
	}

	@Override
	public int update_csbr2080_mb_resebw(MbResebwVO mrvo) {
		return dataDAO.update_csbr2080_mb_resebw(mrvo);
	}

	@Override
	public String insert_csbr2080_mb_resebw(MbResebwVO mrvo) {
		return dataDAO.insert_csbr2080_mb_resebw(mrvo);
	}

	@Override
	public List<MbResebsVO> select_csbr2090_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2090_right_data(map);
	}

	@Override
	public int update_csbr2090_mb_resebs(MbResebsVO mrvo) {
		return dataDAO.update_csbr2090_mb_resebs(mrvo);
	}

	@Override
	public String insert_csbr2090_mb_resebs(MbResebsVO mrvo) {
		return dataDAO.insert_csbr2090_mb_resebs(mrvo);
	}

	@Override
	public List<MbReseibVO> select_csbr2100_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2100_right_data(map);
	}

	@Override
	public int update_csbr2100_mb_reseib(MbReseibVO mrvo) {
		return dataDAO.update_csbr2100_mb_reseib(mrvo);
	}

	@Override
	public String insert_csbr2100_mb_reseib(MbReseibVO mrvo) {
		return dataDAO.insert_csbr2100_mb_reseib(mrvo);
	}

	@Override
	public List<MbReseckVO> select_csbr2110_right_data(Map<String, String> map) {
		return dataDAO.select_csbr2110_right_data(map);
	}

	@Override
	public int update_csbr2110_mb_reseck(MbReseckVO mrvo) {
		return dataDAO.update_csbr2110_mb_reseck(mrvo);
	}

	@Override
	public String insert_csbr2110_mb_reseck(MbReseckVO mrvo) {
		return dataDAO.insert_csbr2110_mb_reseck(mrvo);
	}
	
	@Override
	public void excelUploadSuvrScore(File destFile, String userId) throws IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		
		// 파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		
		// 추출할 컬럼명 추가
		//excelReadOption.setOutputColumns("a","b");
		
		// 시작행
		excelReadOption.setStartRow(1);
		
		List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption, 0);	
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		int total = 0, success=0;
		for(int i = 0; i<excelContent.size(); i++) {
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> tmp = excelContent.get(i);
			String key=null, val=null;
			for(Map.Entry<String,String> entry : tmp.entrySet()) {
				key = entry.getKey();
				val = entry.getValue();
				map.put(key.toUpperCase(), val);
			}
			if(val==null || val.equals("")) continue;
			
			if(map.containsKey("ID") && map.containsKey("SUVR_SCORE")) {
				list.add(map);
				total += 1;
			} else {
				throw new IOException("Invalid column headers - "+map.keySet());
			}
		}
		try {
			// update시작
			if(excelContent.size() > 0) {
				int result = dataDAO.update_csbr3020_suvr(list);
				success += result;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("SUVR Score upload result : total="+total+", success="+success);
	}

	@Override
	public void excelUploadAPOE(File destFile, String userId) throws IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		
		// 파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		// 시작행
		excelReadOption.setStartRow(1);
		List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption, 0);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		int total = 0, success=0;
		for(int i = 0; i<excelContent.size(); i++) {
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> tmp = excelContent.get(i);
			for(Map.Entry<String,String> entry : tmp.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				if(key.equalsIgnoreCase("APOE")) {
					map.put("APOE_CD", val);
				} else if(key.equalsIgnoreCase("APOE+")) {
					map.put("APOE_PL", val);
				} else if(key.equals("검사일")) {
					map.put("DIAG_DATETM", val);
				} else {
					map.put(key.toUpperCase(), val);
				}
			}
			String id = map.get("ID");
			if(id==null || "".equals(id)) continue;
			
			map.put("USER_ID", userId);
			if(map.containsKey("ID") && map.containsKey("APOE_CD") && map.containsKey("APOE_PL")) {
				list.add(map);
				total += 1;
			} else {
				throw new IOException("Invalid column headers - "+map.keySet());
			}
		}	
		try {
			// update시작
			if(excelContent.size() > 0) {
				int result = dataDAO.update_csbr3080_apoe(list);
				success += result;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("APOE upload result : total="+total+", success="+success+", actual="+list.size());
	}

	@Override
	public void excelUploadInBody(File destFile, String userId) throws IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		
		// 파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		// 시작행
		excelReadOption.setStartRow(1);
		List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption, 0);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		int total = 0, success=0;
		boolean isInBody1 = false;
		for(int i = 0; i<excelContent.size(); i++) {
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> tmp = excelContent.get(i);
			int columnLength = tmp.keySet().size();
			if(columnLength==InBody1.values().length) {
				isInBody1 = true;
				if(!tmp.containsKey(InBody1.ID.getHeader())) {
					throw new RuntimeException("Not Found primary key column. - "+InBody1.ID.getHeader());
				}
				for(InBody1 item : InBody1.values()) {
					String key = item.name();
					String val = tmp.get(item.getHeader());
					map.put(key, val);
				}
			} else if(columnLength==InBody2.values().length) {
				isInBody1 = false;
				if(!tmp.containsKey(InBody2.ID.getHeader())) {
					throw new RuntimeException("Not Found primary key column. - "+InBody2.ID.getHeader());
				}
				for(InBody2 item : InBody2.values()) {
					String key = item.name();
					String val = tmp.get(item.getHeader());
					map.put(key, val);
				}
			} else {
				throw new RuntimeException("Invalid upload file format(InBody1:"+InBody1.values().length+" , InBody2:"+InBody2.values().length+"). - uploaded file : column length="+columnLength);
			}
			String id = map.get("ID");
			if(id==null || "".equals(id)) continue;
			
			map.put("USER_ID", userId);
			if(map.containsKey("ID")) {
				list.add(map);
				total += 1;
			} else {
				throw new IOException("Invalid column headers - "+map.keySet());
			}
		}
		try {
			// update시작
			if(excelContent.size() > 0) {
				int result = 0;
				if(isInBody1) {
					result = dataDAO.update_csbr3060_inbody1(list);
				} else {
					result = dataDAO.update_csbr3060_inbody2(list);
				}
				success += result;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("InBody upload result : total="+total+", success="+success+", actual="+list.size());
	}

	@Override
	public void excelUploadCSF(File destFile, String userId) throws IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		
		// 파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		// 시작행
		excelReadOption.setStartRow(2);
		List<Map<String,String>> excelContent = ExcelRead.readCSF(excelReadOption, 0);
		int total = 0, success=0;
		try {
			// update시작
			if(excelContent.size() > 0) {
				int result = dataDAO.update_csbr3030_csf(excelContent);
				success += result;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("CSF upload result : total="+total+", success="+success+", actual="+excelContent.size());
	}
}
