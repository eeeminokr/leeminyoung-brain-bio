package csbrain.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import csbrain.common.service.CommonVO;
import csbrain.common.service.MbEtcCdVO;
import csbrain.common.service.MbMmseVO;
import csbrain.common.service.MbSJVO;


@Repository("CommonDAO")

public class CommonDAO {
	@Autowired
	private SqlSession sqlSession;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public int insert_mb_usedlog(Map<String, String> map) {
		return sqlSession.insert("Common.insert_mb_usedlog", map);
	}

	public List<MbEtcCdVO> select_mb_etccd_list(Map<String, String> map) {
		return sqlSession.selectList("Common.select_mb_etccd_list", map);
	}

	public List<MbMmseVO> select_mb_mmse_list(String objectIdx) {
		return sqlSession.selectList("Common.select_mb_mmse_list", objectIdx);
	}

	public List<MbSJVO> select_sunbyul_list(String objectIdx) {
		return sqlSession.selectList("Common.select_sunbyul_list", objectIdx);
	}
	public List<MbSJVO> select_load_selTablist(Map<String, String> map) {
		return sqlSession.selectList("Common.select_load_selTablist", map);
	}
	
	public List<MbSJVO> select_AllSjselection_num() {
		return sqlSession.selectList("Common.select_AllSjselection_num");
	}
	public int select_TotalAllSjselection_num() {
		return sqlSession.selectOne("Common.select_TotalAllSjselection_num");
	}
	public int insert_AllSjselection_num(MbSJVO mbSJVO) {
		return sqlSession.insert("Common.insert_AllSjselection_num",mbSJVO);
	}
	
	public int insert_mb_c2_SelctionOneNumFlagD_list(String objectIdx) {
		return sqlSession.insert("Common.insert_mb_c2_SelctionOneNumFlagD_list");
	}
	public int insert_mb_c3_SelctionOneNumFlagD_list(String objectIdx) {
		return sqlSession.insert("Common.insert_mb_c3_SelctionOneNumFlagD_list");
	}
	
	
	public List<MbSJVO> select_sjselctionOneNumFlagD_list(String objectIdx) {
		return sqlSession.selectList("Common.select_sjselctionOneNumFlagD_list", objectIdx);
	}
	public int select_sjselctionOneNumFlagD_totalList(String objectIdx) {
		return sqlSession.selectOne("Common.select_sjselctionOneNumFlagD_totalList",objectIdx);
	}
	
	public List<MbSJVO> select_c2_chkIdxlist(String objectIdx) {
		return sqlSession.selectList("Common.select_c2_chkIdxlist", objectIdx);
	}
	public List<MbSJVO> select_c3_chkIdxlist(String objectIdx) {
		return sqlSession.selectList("Common.select_c3_chkIdxlist", objectIdx);
	}
	public List<MbSJVO> select_sjChk_NullList(String objectIdx) {
		return sqlSession.selectList("Common.select_sjChk_NullList", objectIdx);
	}

	public List<MbSJVO> select_sj_Nulllist(String objectIdx) {
		return sqlSession.selectList("Common.select_sj_Nulllist", objectIdx);
	}
	public List<MbSJVO> select_sjFlafDF_list(String objectIdx) {
		return sqlSession.selectList("Common.select_sjFlafDF_list", objectIdx);
	}
	public List<MbSJVO> select_jungmil_list(String objectIdx) {
		return sqlSession.selectList("Common.select_jungmil_list", objectIdx);
	}

	public List<MbSJVO> select_etc_list(String objectIdx) {
		return sqlSession.selectList("Common.select_etc_list", objectIdx);
	}

	public List<CommonVO> select_snsb_list(String objectIdx) {
		return sqlSession.selectList("Common.select_snsb_list", objectIdx);
	}

	public List<CommonVO> select_mri_list(String objectIdx) {
		return sqlSession.selectList("Common.select_mri_list", objectIdx);
	}

	public List<CommonVO> select_pet_list(String objectIdx) {
		return sqlSession.selectList("Common.select_pet_list", objectIdx);
	}

	public List<CommonVO> select_csf_list(String objectIdx) {
		return sqlSession.selectList("Common.select_csf_list", objectIdx);
	}

	public int truncateTable(String tableName) {
		return sqlSession.delete("Common.truncateTable", tableName);
	}

	public List<Map<String, String>> select_mb_c1_list(Map<String, String> map) {
		return sqlSession.selectList("Common.select_mb_c1_list", map);
	}

	public List<Map<String, String>> select_idx_list(Map<String, String> map) {
		return sqlSession.selectList("Common.select_idx_list", map);
	}

	public String insert_mb_c2_song(Map<String, String> map) {
		String sql = sqlSession.getConfiguration().getMappedStatement("Common.insert_mb_c2_song").getBoundSql(map).getSql();
		if (logger.isDebugEnabled()) {
			logger.info("insert_mb_c2_song sql :" + sql );
		}
		int rtnCnt = sqlSession.insert("Common.insert_mb_c2_song", map);
		return sql + "/" + rtnCnt;
	}

	public int select_max_idx(String tablename) {
		return sqlSession.selectOne("Common.select_max_idx", tablename);
	}

	public List<Map<String, String>> select_mb_c2_list() {
		return sqlSession.selectList("Common.select_mb_c2_list");
	}

	public int select_count(Map<String, String> map) {
		return sqlSession.selectOne("Common.select_count", map);
	}

	public int delete_table(Map<String, String> map) {
		return sqlSession.delete("Common.delete_table", map);
	}

	public int update_mb_c2_temp2(int max_idx) {
		return sqlSession.update("Common.update_mb_c2_temp2", max_idx);
	}

	public int insert_mb_c2_song_select_mb_c2_temp2() {
		return sqlSession.insert("Common.insert_mb_c2_song_select_mb_c2_temp2");
	}
	
	public List<CommonVO> select_blood_list(String objectIdx) {
		return sqlSession.selectList("Common.select_blood_list", objectIdx);
	}
	public List<CommonVO> select_apoe_list(String objectIdx) {
		return sqlSession.selectList("Common.select_apoe_list", objectIdx);
	}
	
	public List<CommonVO> select_nii_list(Map<String, String> map) {
		return sqlSession.selectList("Common.select_nii_list", map);
	}
}
