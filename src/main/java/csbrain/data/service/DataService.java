package csbrain.data.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface DataService {
	List<Map<String, String>> select_csbr_left_data(Map<String, String> map);

	List<MbReseselVO> select_csbr2010_right_data(Map<String, String> map);

	int delete_csbr2010_mb_resesel(Map<String, String> map);

	int update_csbr2010_mb_resesel(MbReseselVO mrvo);

	String insert_csbr2010_mb_resesel(MbReseselVO mrvo);

	List<MbResesnsbVO> select_csbr2020_right_data(Map<String, String> map);

	int delete_csbr2020_mb_resesnsb(Map<String, String> map);

	int update_csbr2020_mb_resesnsb(MbResesnsbVO mrvo);

	String insert_csbr2020_mb_resesnsb(MbResesnsbVO mrvo);

	List<MbObjectVO> select_mb_object(String st);

	List<MbResemriVO> select_csbr2040_right_data(Map<String, String> map);

	int delete_csbr2040_mb_resemri(Map<String, String> map);

	int update_csbr2040_mb_resemri(MbResemriVO mrvo);

	String selectMaxResersnoSeq(Map<String, String> map);

	String insert_csbr2040_mb_resemri(MbResemriVO mrvo);

	int update_csbr_mriid(Map<String, String> map);

	List<MbResecstVO> select_csbr2050_right_data(Map<String, String> map);

	int delete_csbr2050_mb_resecst(Map<String, String> map);

	int update_csbr2050_mb_resecst(MbResecstVO mrvo);

	String insert_csbr2050_mb_resecst(MbResecstVO mrvo);

	List<MbResepetVO> select_csbr2060_right_data(Map<String, String> map);

	int delete_csbr2060_mb_resepet(Map<String, String> map);

	int update_csbr2060_mb_resepet(MbResepetVO mrvo);

	String insert_csbr2060_mb_resepet(MbResepetVO mrvo);

	List<MbResecsfVO> select_csbr2070_right_data(Map<String, String> map);

	int delete_csbr2070_mb_resecsf(Map<String, String> map);

	int update_csbr2070_mb_resecsf(MbResecsfVO mrvo);

	String insert_csbr2070_mb_resecsf(MbResecsfVO mrvo);

	List<MbObjectVO> select_csbr1010_search(Map<String, String> map);

	
	List<Map<String, String>> select_csbr1020_left_data(Map<String, String> map);

	List<Csbr1020ListDataVO> select_csbr1020_list_data(String d);

	List<Csbr1031SearchVO> select_csbr1031_search(Map<String, String> map);
	
	List<Csbr1050SearchVO> select_csbr1050_search(Map<String, String> map);

	List<Csbr1050InfoVO> select_csbr1050_info(Map<String, String> map);

	int delete_csbr1050_mb_nrcd(Map<String, String> map);

	String insert_csbr1050_mb_nrcd(MbNrcdVO mbNrcdVO);

	int select_count_mb_snsb2data_excel1(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr3010_search_1(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr3010_search_2(Map<String, String> map);
	List<LinkedHashMap<String, Object>> select_csbr3010_target_search(Map<String, String> map);
	public Map<String,Object>  select_csbr3010_next_selection_num(Map<String,String> map);
	public Map<String,Object> select_csbr3010_snsb(Map<String, String> map);
	public int insert_csbr3010_snsb(Map<String,String> map);
	public int update_csbr3010_snsb(Map<String,String> map);
	public int update_csbr3010_resesnsb(Map<String,String> map);
	public int select_count_csbr3010_search_3(Map<String,String> map);
	public List<LinkedHashMap<String, Object>> select_csbr3010_search_3(Map<String, String> map);

	String delete_mb_snsb2data_excel1(String unum);

	String insert_mb_snsb2data_excel1(Map<String, String> map);

	String delete_mb_snsb2data_excel2(String unum);

	String insert_mb_snsb2data_excel2(Map<String, String> map);

	//csbr3020 pet검사관리 
	int select_count_mb_rctu_object_respet_suvr(Map<String, String> map);
	
	List<LinkedHashMap<String, Object>> select_csbr3020_search(Map<String,String> map);
	List<LinkedHashMap<String, Object>> select_csbr3020_target_search(Map<String, String> map);
	public Map<String,Object>  select_csbr3020_next_selection_num(Map<String,String> map);
	public Map<String,Object> select_csbr3020_pet(Map<String, String> map);
	public int insert_csbr3020_pet(Map<String,String> map);
	public int update_csbr3020_pet(Map<String,String> map);
	public int update_csbr3020_resepet(Map<String,String> map);
	
	String delete_mb_rctu_object_respet_suvr(String unum);
	
	String insert_mb_rctu_object_respet_suvr(Map<String, String> map);
	
	int select_count_csbr3030_csf(Map<String, String> map);
	List<LinkedHashMap<String, Object>> select_csbr3030_search(Map<String,String> map);
	
	int select_count_csbr3060_inbody1(Map<String, String> map);
	int select_count_csbr3060_inbody2(Map<String, String> map);
	List<LinkedHashMap<String, Object>> select_csbr3060_search1(Map<String,String> map);
	List<LinkedHashMap<String, Object>> select_csbr3060_search2(Map<String,String> map);
	
	//csbr3080 apoe
	int select_count_csbr3080_apoe(Map<String, String> map);
	List<LinkedHashMap<String, Object>> select_csbr3080_search(Map<String,String> map);
	
	String delete_csbr4010_mb_testblod(Map<String, String> map);

	List<MbMriTestblodVO> select_csbr4010_content(Map<String, String> map);

	int select_count_mb_testblod(Map<String, String> map);

	String update_csbr4010_mb_testblod(MbMriTestblodVO mtvo);

	String insert_csbr4010_mb_testblod(MbMriTestblodVO mtvo);

	String delete_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo);

	String update_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo);

	String insert_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo);

	List<MbSpecimdtMbCsfdtVO> select_csbr5020_mb_specimdt(Map<String, String> map);

	List<Map<String, String>> select_csbr5020_left_data(Map<String, String> map);

	List<Map<String, String>> select_csbr5020_right_data(String resedate);

	String update_csbr5020_mb_specimhd(MbSpecimhdVO msvo);

	String insert_csbr5020_mb_specimhd(MbSpecimhdVO msvo);

	String select_count_csbr5020_mb_specimhd(Map<String, String> map);

	String delete_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo);

	String update_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo);

	String insert_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo);

	List<MbSpecimdtMbCsfdtVO> select_csbr5040_mb_csfdt(Map<String, String> map);

	List<Map<String, String>> select_csbr5040_left_data(Map<String, String> map);

	List<Map<String, String>> select_csbr5040_right_data(String d);

	String select_count_csbr5040_mb_csfhd(Map<String, String> map);

	String update_csbr5040_mb_csfhd(MbCsfhdVO mcvo);

	String insert_csbr5040_mb_csfhd(MbCsfhdVO mcvo);

	List<LinkedHashMap<String, Object>> select_csbr7001_search(Map<String, String> map);
	
	List<LinkedHashMap<String, Object>> select_csbr7001_search2(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7003_search();

	int select_count_csbr7004_mb_specimhd();

	List<LinkedHashMap<String, Object>> select_csbr7004_search(String limit_query);

	int select_count_csbr7005_mb_object(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7005_search(Map<String, String> map);

	int select_count_csbr7006_mb_object();

	List<LinkedHashMap<String, Object>> select_csbr7006_search(String limit_query);

	List<LinkedHashMap<String, Object>> select_csbr7007_search(Map<String, String> map);

	int select_count_csbr7008_mb_snsb(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7008_search(Map<String, String> map);

	int select_count_csbr7009_mb_dm(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7009_search(Map<String, String> map);

	int select_count_csbr7010_mb_object(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7010_search(Map<String, String> map);

	int select_count_csbr7011_mb_object(Map<String, String> map);

	List<LinkedHashMap<String, Object>> select_csbr7011_search(Map<String, String> map);

	List<Map<String, String>> select_csbr9020_left_data(String searchid);

	List<Map<String, String>> select_csbr9020_right_data(String id);

	List<Map<String, String>> select_csbr9020_right_data2(String id);

	List<Map<String, String>> select_csbr9020_nrcd_data(String id);

	List<Map<String, String>> select_csbr9020_nrcd_data2(String id);

	int select_count_mb_subject(String userid);

	Map<String, String> select_mb_subject(String userid);

	String update_mb_subject(Map<String, String> map);

	String insert_mb_subject(Map<String, String> map);

	int select_count_mb_pempower(Map<String, String> map);

	String delete_mb_pempower(Map<String, String> map);

	String update_mb_pempower(Map<String, String> map);

	String insert_mb_pempower(Map<String, String> map);

	int insert_mb_nrcdpower(Map<String, String> map);

	int delete_mb_nrcdpower(Map<String, String> map);

	List<Map<String, String>> select_csbr9030_left_data(Map<String, String> map);

	List<Map<String, String>> select_csbr9030_right_data(Map<String, String> map);

	List<Map<String, String>> select_csbr9140_left_right_data(Map<String, String> map);

	String update_csbr9140_left_mb_etccd(Map<String, String> map);

	String insert_csbr9140_left_mb_etccd(Map<String, String> map);

	String update_csbr9140_right_mb_etccd(Map<String, String> map);

	String insert_csbr9140_right_mb_etccd(Map<String, String> map);

	List<Map<String, String>> select_csbr9201_left_data(Map<String, String> map);

	List<Map<String, String>> select_csbr9201_right_data(Map<String, String> map);

	List<Map<String, String>> select_csbr9920_left_data();

	List<Map<String, String>> select_csbr9920_right_data(String progid);

	List<Map<String, String>> select_csbr9920_right_data2(String progid);

	int update_csbr9920_left_mb_proglist(Map<String, String> map);

	int insert_csbr9920_left_mb_proglist(Map<String, String> map);

	void excelUpload(File destFile, int sheetNo, String tableName, String userId) throws IOException;
	
	void excelUploadSuvrScore(File destFile, String userId) throws IOException;
	void excelUploadAPOE(File destFile, String userId) throws IOException;
	void excelUploadCSF(File destFile, String userId) throws IOException;
	void excelUploadInBody(File destFile, String userId) throws IOException;
	
	int update_csbr1050_pdfPath(Map<String, String> map);
	public Map<String,Object> select_csbr1050_cdr(Map<String, String> map);
	public Map<String,Object> select_csbr1050_object(Map<String, String> map);
	public int insert_csbr1050_cdr(Map<String,String> map);
	public int insert_csbr1050_cdr_detail(Map<String,String> map);
	public int update_csbr1050_cdr(Map<String,String> map);
	public int update_csbr1050_cdr_detail(Map<String,String> map);
	
	List<MbResebwVO> select_csbr2080_right_data(Map<String, String> map);
	int update_csbr2080_mb_resebw(MbResebwVO mrvo);
	String insert_csbr2080_mb_resebw(MbResebwVO mrvo);
	
	List<MbResebsVO> select_csbr2090_right_data(Map<String, String> map);
	int update_csbr2090_mb_resebs(MbResebsVO mrvo);
	String insert_csbr2090_mb_resebs(MbResebsVO mrvo);
	
	List<MbReseibVO> select_csbr2100_right_data(Map<String, String> map);
	int update_csbr2100_mb_reseib(MbReseibVO mrvo);
	String insert_csbr2100_mb_reseib(MbReseibVO mrvo);
	
	List<MbReseckVO> select_csbr2110_right_data(Map<String, String> map);
	int update_csbr2110_mb_reseck(MbReseckVO mrvo);
	String insert_csbr2110_mb_reseck(MbReseckVO mrvo);

}
