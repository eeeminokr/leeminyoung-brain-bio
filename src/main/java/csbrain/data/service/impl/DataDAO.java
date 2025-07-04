package csbrain.data.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import csbrain.data.service.Csbr1020ListDataVO;
import csbrain.data.service.Csbr1031SearchVO;
import csbrain.data.service.Csbr1050InfoVO;
import csbrain.data.service.Csbr1050SearchVO;
import csbrain.data.service.Csbr7001SearchVO;
import csbrain.data.service.MbCsfhdVO;
import csbrain.data.service.MbMriTestblodVO;
import csbrain.data.service.MbNrcdVO;
import csbrain.data.service.MbObjectVO;
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

@Repository("DataDAO")
public class DataDAO {

	@Autowired
	private SqlSession sqlSession;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<Map<String, String>> select_csbr_left_data(Map<String, String> map){
		return sqlSession.selectList("CSBR20.select_csbr_left_data", map);
	}

	public List<MbReseselVO> select_csbr2010_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2010_right_data", map);
	}

	public int delete_csbr2010_mb_resesel(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2010_mb_resesel", map);
	}

	public int update_csbr2010_mb_resesel(MbReseselVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2010_mb_resesel", mrvo);
	}

	public String insert_csbr2010_mb_resesel(MbReseselVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2010_mb_resesel").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2010_mb_resesel sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2010_mb_resesel", mrvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbResesnsbVO> select_csbr2020_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2020_right_data", map);
	}

	public int delete_csbr2020_mb_resesnsb(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2020_mb_resesnsb", map);
	}

	public int update_csbr2020_mb_resesnsb(MbResesnsbVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2020_mb_resesnsb", mrvo);
	}

	public String insert_csbr2020_mb_resesnsb(MbResesnsbVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2020_mb_resesnsb").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2020_mb_resesnsb sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2020_mb_resesnsb", mrvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbObjectVO> select_mb_object(String st) {
		return sqlSession.selectList("CSBR20.select_mb_object", st);
	}

	public List<MbResemriVO> select_csbr2040_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2040_right_data", map);
	}

	public int delete_csbr2040_mb_resemri(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2040_mb_resemri", map);
	}

	public int update_csbr2040_mb_resemri(MbResemriVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2040_mb_resemri", mrvo);
	}

	public String selectMaxResersnoSeq(Map<String, String> map) {
		return sqlSession.selectOne("CSBR20.selectMaxResersnoSeq", map);
	}

	public String insert_csbr2040_mb_resemri(MbResemriVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2040_mb_resemri").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2040_mb_resemri sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2040_mb_resemri", mrvo);
		return sql + "/" + rtnCnt;
	}

	public int update_csbr_mriid(Map<String, String> map) {
		return sqlSession.update("CSBR20.update_csbr_mriid", map);
	}

	public List<MbResecstVO> select_csbr2050_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2050_right_data", map);
	}

	public int delete_csbr2050_mb_resecst(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2050_mb_resecst", map);
	}

	public int update_csbr2050_mb_resecst(MbResecstVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2050_mb_resecst", mrvo);
	}

	public String insert_csbr2050_mb_resecst(MbResecstVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2050_mb_resecst").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2050_mb_resecst sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2050_mb_resecst", mrvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbResepetVO> select_csbr2060_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2060_right_data", map);
	}

	public int delete_csbr2060_mb_resepet(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2060_mb_resepet", map);
	}

	public int update_csbr2060_mb_resepet(MbResepetVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2060_mb_resepet", mrvo);
	}

	public String insert_csbr2060_mb_resepet(MbResepetVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2060_mb_resepet").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2060_mb_resepet sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2060_mb_resepet", mrvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbResecsfVO> select_csbr2070_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2070_right_data", map);
	}

	public int delete_csbr2070_mb_resecsf(Map<String, String> map) {
		return sqlSession.delete("CSBR20.delete_csbr2070_mb_resecsf", map);
	}

	public int update_csbr2070_mb_resecsf(MbResecsfVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2070_mb_resecsf", mrvo);
	}

	public String insert_csbr2070_mb_resecsf(MbResecsfVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2070_mb_resecsf").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2070_mb_resecsf sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2070_mb_resecsf", mrvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbObjectVO> select_csbr1010_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR10.select_csbr1010_search", map);
	}
	


	public List<Map<String, String>> select_csbr1020_left_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR10.select_csbr1020_left_data", map);
	}

	public List<Csbr1020ListDataVO> select_csbr1020_list_data(String d) {
		return sqlSession.selectList("CSBR10.select_csbr1020_list_data", d);
	}

	public List<Csbr1031SearchVO> select_csbr1031_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR10.select_csbr1031_search", map);
	}
	
	public List<Csbr1050SearchVO> select_csbr1050_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR10.select_csbr1050_search", map);
	}

	public List<Csbr1050InfoVO> select_csbr1050_info(Map<String, String> map) {
		return sqlSession.selectList("CSBR10.select_csbr1050_info", map);
	}

	public int delete_csbr1050_mb_nrcd(Map<String, String> map) {
		return sqlSession.delete("CSBR10.delete_csbr1050_mb_nrcd", map);
	}

	public String insert_csbr1050_mb_nrcd(MbNrcdVO mbNrcdVO) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR10.insert_csbr1050_mb_nrcd").getBoundSql(mbNrcdVO).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr1050_mb_nrcd sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR10.insert_csbr1050_mb_nrcd", mbNrcdVO);
		return sql + "/" + rtnCnt;
	}

	public int select_count_mb_snsb2data_excel1(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_count_mb_snsb2data_excel1", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr3010_search_1(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3010_search_1", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr3010_search_2(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3010_search_2", map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3010_target_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3010_target_search", map);
	}
	
	public Map<String,Object> select_csbr3010_next_selection_num(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_csbr3010_next_selection_num", map);
	}
	
	public Map<String, Object> select_csbr3010_snsb(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_csbr3010_snsb", map);
	}
	
	public int select_count_csbr3010_search_3(Map<String,String> map) {
		return sqlSession.selectOne("CSBR30.select_count_csbr3010_search_3", map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3010_search_3(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3010_search_3", map);
	}

	public int insert_csbr3010_snsb(Map<String, String> map) {
		return sqlSession.insert("CSBR30.insert_csbr3010_snsb", map);
	}
	
	public int update_csbr3010_snsb(Map<String, String> map) {
		return sqlSession.update("CSBR30.update_csbr3010_snsb", map);
	}
	
	public int update_csbr3010_resesnsb(Map<String,String> map) {
		return sqlSession.update("CSBR30.update_csbr3010_resesnsb", map);
	}
	
	public String delete_mb_snsb2data_excel1(String unum) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.delete_mb_snsb2data_excel1").getBoundSql(unum).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_mb_snsb2data_excel1 sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.delete_mb_snsb2data_excel1", unum);
		return sql + "/" + rtnCnt;
	}

	public String insert_mb_snsb2data_excel1(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.insert_mb_snsb2data_excel1").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_snsb2data_excel1 sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.insert_mb_snsb2data_excel1", map);
		return sql + "/" + rtnCnt;
	}

	public String delete_mb_snsb2data_excel2(String unum) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.delete_mb_snsb2data_excel2").getBoundSql(unum).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_mb_snsb2data_excel2 sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.delete_mb_snsb2data_excel2", unum);
		return sql + "/" + rtnCnt;
	}

	public String insert_mb_snsb2data_excel2(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.insert_mb_snsb2data_excel2").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_snsb2data_excel2 sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.insert_mb_snsb2data_excel2", map);
		return sql + "/" + rtnCnt;
	}

	public String delete_csbr4010_mb_testblod(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR40.delete_csbr4010_mb_testblod").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_csbr4010_mb_testblod sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR40.delete_csbr4010_mb_testblod", map);
		return sql + "/" + rtnCnt;
	}

	
	//csbr3020 pet검사결과
	
	public int select_count_mb_rctu_object_respet_suvr(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_count_mb_rctu_object_respet_suvr", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr3020_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3020_search", map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3020_target_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3020_target_search", map);
	}
	
	public String delete_mb_rctu_object_respet_suvr(String petid) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.delete_mb_rctu_object_respet_suvr").getBoundSql(petid).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_mb_snsb2data_excel1 sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.delete_mb_snsb2data_excel1", petid);
		return sql + "/" + rtnCnt;
	}

	public String insert_mb_rctu_object_respet_suvr(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR30.insert_mb_rctu_object_respet_suvr").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_rctu_object_respet_suvr sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR30.mb_rctu_object_respet_suvr", map);
		return sql + "/" + rtnCnt;
	}
	
	public Map<String,Object> select_csbr3020_next_selection_num(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_csbr3020_next_selection_num", map);
	}
	
	public Map<String, Object> select_csbr3020_pet(Map<String, String> map) {
		return sqlSession.selectOne("CSBR30.select_csbr3020_pet", map);
	}
	
	public int insert_csbr3020_pet(Map<String, String> map) {
		return sqlSession.insert("CSBR30.insert_csbr3020_pet", map);
	}
	
	public int update_csbr3020_pet(Map<String, String> map) {
		return sqlSession.update("CSBR30.update_csbr3020_pet", map);
	}
	
	public int update_csbr3020_resepet(Map<String,String> map) {
		return sqlSession.update("CSBR30.update_csbr3020_resepet", map);
	}
	
	public int update_csbr3020_suvr(List<Map<String,String>> map) {
		return sqlSession.update("CSBR30.update_csbr3020_suvr", map);
	}
	
	public int select_count_csbr3030_csf(Map<String, String> map) {
		 return sqlSession.selectOne("CSBR30.select_count_csbr3030_csf",map);
	}
	
	public int update_csbr3030_csf(List<Map<String,String>> map) {
		return sqlSession.update("CSBR30.update_csbr3030_csf", map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3030_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3030_search", map);
	}
	
	public int update_csbr3060_inbody1(List<Map<String,String>> map) {
		return sqlSession.update("CSBR30.update_csbr3060_inbody1", map);
	}
	
	public int update_csbr3060_inbody2(List<Map<String,String>> map) {
		return sqlSession.update("CSBR30.update_csbr3060_inbody2", map);
	}
	
	public int select_count_csbr3060_inbody1(Map<String, String> map) {
		 return sqlSession.selectOne("CSBR30.select_count_csbr3060_inbody1",map);
	}
	public int select_count_csbr3060_inbody2(Map<String, String> map) {
		 return sqlSession.selectOne("CSBR30.select_count_csbr3060_inbody2",map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3060_search1(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3060_search1", map);
	}
	public List<LinkedHashMap<String, Object>> select_csbr3060_search2(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3060_search2", map);
	}
	
	//csbr3080 apoe
	
	public int select_count_csbr3080_apoe(Map<String, String> map) {
		 return sqlSession.selectOne("CSBR30.select_count_csbr3080_apoe",map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr3080_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR30.select_csbr3080_search", map);
	}
	
	public int update_csbr3080_apoe(List<Map<String,String>> map) {
		return sqlSession.update("CSBR30.update_csbr3080_apoe", map);
	}
	
	public List<MbMriTestblodVO> select_csbr4010_content(Map<String, String> map) {
		return sqlSession.selectList("CSBR40.select_csbr4010_content", map);
	}

	public int select_count_mb_testblod(Map<String, String> map) {
		return sqlSession.selectOne("CSBR40.select_count_mb_testblod", map);
	}

	public String update_csbr4010_mb_testblod(MbMriTestblodVO mtvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR40.update_csbr4010_mb_testblod").getBoundSql(mtvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr4010_mb_testblod sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR40.update_csbr4010_mb_testblod", mtvo);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr4010_mb_testblod(MbMriTestblodVO mtvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR40.insert_csbr4010_mb_testblod").getBoundSql(mtvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr4010_mb_testblod sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR40.insert_csbr4010_mb_testblod", mtvo);
		return sql + "/" + rtnCnt;
	}

	public String delete_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.delete_csbr5020_mb_specimdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_csbr5020_mb_specimdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.delete_csbr5020_mb_specimdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public String update_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.update_csbr5020_mb_specimdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr5020_mb_specimdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.update_csbr5020_mb_specimdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr5020_mb_specimdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.insert_csbr5020_mb_specimdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr5020_mb_specimdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.insert_csbr5020_mb_specimdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbSpecimdtMbCsfdtVO> select_csbr5020_mb_specimdt(Map<String, String> map) {
		return sqlSession.selectList("CSBR50.select_csbr5020_mb_specimdt", map);
	}

	public List<Map<String, String>> select_csbr5020_left_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR50.select_csbr5020_left_data", map);
	}

	public List<Map<String, String>> select_csbr5020_right_data(String resedate) {
		return sqlSession.selectList("CSBR50.select_csbr5020_right_data", resedate);
	}

	public String update_csbr5020_mb_specimhd(MbSpecimhdVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.update_csbr5020_mb_specimhd").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr5020_mb_specimhd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.update_csbr5020_mb_specimhd", msvo);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr5020_mb_specimhd(MbSpecimhdVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.insert_csbr5020_mb_specimhd").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr5020_mb_specimhd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.insert_csbr5020_mb_specimhd", msvo);
		return sql + "/" + rtnCnt;
	}

	public String select_count_csbr5020_mb_specimhd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.select_count_csbr5020_mb_specimhd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("select_count_csbr5020_mb_specimhd sql :" + sql );
		}
		int rtnCnt = sqlSession.selectOne("CSBR50.select_count_csbr5020_mb_specimhd", map);
		return sql + "/" + rtnCnt;
	}

	public String delete_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.delete_csbr5040_mb_csfdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_csbr5040_mb_csfdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.delete_csbr5040_mb_csfdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public String update_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.update_csbr5040_mb_csfdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr5040_csfdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.update_csbr5040_mb_csfdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr5040_mb_csfdt(MbSpecimdtMbCsfdtVO msvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.insert_csbr5040_mb_csfdt").getBoundSql(msvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr5040_mb_csfdt sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.insert_csbr5040_mb_csfdt", msvo);
		return sql + "/" + rtnCnt;
	}

	public List<MbSpecimdtMbCsfdtVO> select_csbr5040_mb_csfdt(Map<String, String> map) {
		return sqlSession.selectList("CSBR50.select_csbr5040_mb_csfdt", map);
	}

	public List<Map<String, String>> select_csbr5040_left_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR50.select_csbr5040_left_data", map);
	}

	public List<Map<String, String>> select_csbr5040_right_data(String d) {
		return sqlSession.selectList("CSBR50.select_csbr5040_right_data", d);
	}

	public String select_count_csbr5040_mb_csfhd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.select_count_csbr5040_mb_csfhd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("select_count_csbr5040_mb_csfhd sql :" + sql );
		}
		int rtnCnt = sqlSession.selectOne("CSBR50.select_count_csbr5040_mb_csfhd", map);
		return sql + "/" + rtnCnt;
	}

	public String update_csbr5040_mb_csfhd(MbCsfhdVO mcvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.update_csbr5040_mb_csfhd").getBoundSql(mcvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr5040_mb_csfhd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.update_csbr5040_mb_csfhd", mcvo);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr5040_mb_csfhd(MbCsfhdVO mcvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR50.insert_csbr5040_mb_csfhd").getBoundSql(mcvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr5040_mb_csfhd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR50.insert_csbr5040_mb_csfhd", mcvo);
		return sql + "/" + rtnCnt;
	}

	public List<LinkedHashMap<String, Object>> select_csbr7001_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7001_search", map);
	}
	
	public List<LinkedHashMap<String, Object>> select_csbr7001_search2(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7001_search2", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7003_search() {
		return sqlSession.selectList("CSBR70.select_csbr7003_search");
	}

	public int select_count_csbr7004_mb_specimhd() {
		return sqlSession.selectOne("CSBR70.select_count_csbr7004_mb_specimhd");
	}

	public List<LinkedHashMap<String, Object>> select_csbr7004_search(String limitQuery) {
		return sqlSession.selectList("CSBR70.select_csbr7004_search", limitQuery);
	}

	public int select_count_csbr7005_mb_object(Map<String, String> map) {
		return sqlSession.selectOne("CSBR70.select_count_csbr7005_mb_object", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7005_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7005_search", map);
	}

	public int select_count_csbr7006_mb_object() {
		return sqlSession.selectOne("CSBR70.select_count_csbr7006_mb_object");
	}

	public List<LinkedHashMap<String, Object>> select_csbr7006_search(String limitQuery) {
		return sqlSession.selectList("CSBR70.select_csbr7006_search", limitQuery);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7007_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7007_search", map);
	}

	public int select_count_csbr7008_mb_snsb(Map<String, String> map) {
		return sqlSession.selectOne("CSBR70.select_count_csbr7008_mb_snsb", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7008_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7008_search", map);
	}

	public int select_count_csbr7009_mb_dm(Map<String, String> map) {
		return sqlSession.selectOne("CSBR70.select_count_csbr7009_mb_dm", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7009_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7009_search", map);
	}

	public int select_count_csbr7010_mb_object(Map<String, String> map) {
		return sqlSession.selectOne("CSBR70.select_count_csbr7010_mb_object", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7010_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7010_search", map);
	}

	public int select_count_csbr7011_mb_object(Map<String, String> map) {
		return sqlSession.selectOne("CSBR70.select_count_csbr7011_mb_object", map);
	}

	public List<LinkedHashMap<String, Object>> select_csbr7011_search(Map<String, String> map) {
		return sqlSession.selectList("CSBR70.select_csbr7011_search", map);
	}

	public List<Map<String, String>> select_csbr9020_left_data(String searchid) {
		return sqlSession.selectList("CSBR90.select_csbr9020_left_data", searchid);
	}

	public List<Map<String, String>> select_csbr9020_right_data(String id) {
		return sqlSession.selectList("CSBR90.select_csbr9020_right_data", id);
	}

	public List<Map<String, String>> select_csbr9020_right_data2(String id) {
		return sqlSession.selectList("CSBR90.select_csbr9020_right_data2", id);
	}

	public List<Map<String, String>> select_csbr9020_nrcd_data(String id) {
		return sqlSession.selectList("CSBR90.select_csbr9020_nrcd_data", id);
	}

	public List<Map<String, String>> select_csbr9020_nrcd_data2(String id) {
		return sqlSession.selectList("CSBR90.select_csbr9020_nrcd_data2", id);
	}

	public int select_count_mb_subject(String userid) {
		return sqlSession.selectOne("CSBR90.select_count_mb_subject", userid);
	}

	public Map<String, String> select_mb_subject(String userid) {
		return sqlSession.selectOne("CSBR90.select_mb_subject", userid);
	}

	public String update_mb_subject(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.update_mb_subject").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_mb_subject sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR90.update_mb_subject", map);
		return sql + "/" + rtnCnt;
	}

	public String insert_mb_subject(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.insert_mb_subject").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_subject sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR90.insert_mb_subject", map);
		return sql + "/" + rtnCnt;
	}

	public int select_count_mb_pempower(Map<String, String> map) {
		return sqlSession.selectOne("CSBR90.select_count_mb_pempower", map);
	}

	public String delete_mb_pempower(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.delete_mb_pempower").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("delete_mb_pempower sql :" + sql );
		}
		int rtnCnt = sqlSession.delete("CSBR90.delete_mb_pempower", map);
		return sql + "/" + rtnCnt;
	}

	public String update_mb_pempower(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.update_mb_pempower").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_mb_pempower sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR90.update_mb_pempower", map);
		return sql + "/" + rtnCnt;
	}

	public String insert_mb_pempower(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.insert_mb_pempower").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_pempower sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR90.insert_mb_pempower", map);
		return sql + "/" + rtnCnt;
	}

	public int insert_mb_nrcdpower(Map<String, String> map) {
		return sqlSession.insert("CSBR90.insert_mb_nrcdpower", map);
	}

	public int delete_mb_nrcdpower(Map<String, String> map) {
		return sqlSession.delete("CSBR90.delete_mb_nrcdpower", map);
	}

	public List<Map<String, String>> select_csbr9030_left_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR90.select_csbr9030_left_data", map);
	}

	public List<Map<String, String>> select_csbr9030_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR90.select_csbr9030_right_data", map);
	}

	public List<Map<String, String>> select_csbr9140_left_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR90.select_csbr9140_left_right_data", map);
	}

	public String update_csbr9140_left_mb_etccd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.update_csbr9140_left_mb_etccd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr9140_left_mb_etccd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR90.update_csbr9140_left_mb_etccd", map);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr9140_left_mb_etccd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.insert_csbr9140_left_mb_etccd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr9140_left_mb_etccd sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR90.insert_csbr9140_left_mb_etccd", map);
		return sql + "/" + rtnCnt;
	}

	public String update_csbr9140_right_mb_etccd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.update_csbr9140_right_mb_etccd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("update_csbr9140_right_mb_etccd sql :" + sql );
		}
		int rtnCnt = sqlSession.update("CSBR90.update_csbr9140_right_mb_etccd", map);
		return sql + "/" + rtnCnt;
	}

	public String insert_csbr9140_right_mb_etccd(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR90.insert_csbr9140_right_mb_etccd").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr9140_right_mb_etccd sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR90.insert_csbr9140_right_mb_etccd", map);
		return sql + "/" + rtnCnt;
	}

	public List<Map<String, String>> select_csbr9201_left_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR90.select_csbr9201_left_data", map);
	}

	public List<Map<String, String>> select_csbr9201_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR90.select_csbr9201_right_data", map);
	}

	public List<Map<String, String>> select_csbr9920_left_data() {
		return sqlSession.selectList("CSBR90.select_csbr9920_left_data");
	}

	public List<Map<String, String>> select_csbr9920_right_data(String progid) {
		return sqlSession.selectList("CSBR90.select_csbr9920_right_data", progid);
	}

	public List<Map<String, String>> select_csbr9920_right_data2(String progid) {
		return sqlSession.selectList("CSBR90.select_csbr9920_right_data2", progid);
	}

	public int update_csbr9920_left_mb_proglist(Map<String, String> map) {
		return sqlSession.update("CSBR90.update_csbr9920_left_mb_proglist", map);
	}

	public int insert_csbr9920_left_mb_proglist(Map<String, String> map) {
		return sqlSession.insert("CSBR90.insert_csbr9920_left_mb_proglist", map);
	}
	public int excelUpload(List<Map<String, String>> excelContent) {
		return sqlSession.insert("CSBR90.excelUpload", excelContent);
	}
	public int excelUpload2(List<Map<String, String>> excelContent) {
		return sqlSession.insert("CSBR90.excelUpload2", excelContent);
	}
	public int update_csbr1050_pdfPath(Map<String, String> map) {
		return sqlSession.update("CSBR10.update_csbr1050_pdfPath", map);
	}
	
	public Map<String,Object> select_csbr1050_object(Map<String, String> map) {
		return sqlSession.selectOne("CSBR10.select_csbr1050_object", map);
	}
	
	public Map<String,Object> select_csbr1050_cdr(Map<String, String> map) {
		return sqlSession.selectOne("CSBR10.select_csbr1050_cdr", map);
	}
	
	public int insert_csbr1050_cdr(Map<String, String> map) {
		return sqlSession.insert("CSBR10.insert_csbr1050_cdr", map);
	}
	
	public int insert_csbr1050_cdr_detail(Map<String, String> map) {
		return sqlSession.insert("CSBR10.insert_csbr1050_cdr_detail", map);
	}
	
	public int update_csbr1050_cdr(Map<String, String> map) {
		return sqlSession.update("CSBR10.update_csbr1050_cdr", map);
	}
	
	public int update_csbr1050_cdr_detail(Map<String, String> map) {
		return sqlSession.update("CSBR10.update_csbr1050_cdr_detail", map);
	}
	
	public List<MbResebwVO> select_csbr2080_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2080_right_data", map);
	}
	public int update_csbr2080_mb_resebw(MbResebwVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2080_mb_resebw", mrvo);
	}
	public String insert_csbr2080_mb_resebw(MbResebwVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2080_mb_resebw").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2080_mb_resebw sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2080_mb_resebw", mrvo);
		return sql + "/" + rtnCnt;
	}
	
	public List<MbResebsVO> select_csbr2090_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2090_right_data", map);
	}
	public int update_csbr2090_mb_resebs(MbResebsVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2090_mb_resebs", mrvo);
	}
	public String insert_csbr2090_mb_resebs(MbResebsVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2090_mb_resebs").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2090_mb_resebs sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2090_mb_resebs", mrvo);
		return sql + "/" + rtnCnt;
	}
	
	public List<MbReseibVO> select_csbr2100_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2100_right_data", map);
	}
	public int update_csbr2100_mb_reseib(MbReseibVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2100_mb_reseib", mrvo);
	}
	public String insert_csbr2100_mb_reseib(MbReseibVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2100_mb_reseib").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2100_mb_reseib sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2100_mb_reseib", mrvo);
		return sql + "/" + rtnCnt;
	}
	
	public List<MbReseckVO> select_csbr2110_right_data(Map<String, String> map) {
		return sqlSession.selectList("CSBR20.select_csbr2110_right_data", map);
	}
	public int update_csbr2110_mb_reseck(MbReseckVO mrvo) {
		return sqlSession.update("CSBR20.update_csbr2110_mb_reseck", mrvo);
	}
	public String insert_csbr2110_mb_reseck(MbReseckVO mrvo) {
		String sql = sqlSession.getConfiguration().getMappedStatement("CSBR20.insert_csbr2110_mb_reseck").getBoundSql(mrvo).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_csbr2110_mb_reseck sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("CSBR20.insert_csbr2110_mb_reseck", mrvo);
		return sql + "/" + rtnCnt;
	}
}
