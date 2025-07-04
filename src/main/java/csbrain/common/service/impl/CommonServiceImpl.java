package csbrain.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import csbrain.common.service.AbstractService;
import csbrain.common.service.CommonService;
import csbrain.common.service.CommonVO;
import csbrain.common.service.MbEtcCdVO;
import csbrain.common.service.MbMmseVO;
import csbrain.common.service.MbSJVO;
import csbrain.target.service.TargetVO;

@Service("CommonService")
public class CommonServiceImpl extends AbstractService implements CommonService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/** DAO Class */
	@Resource(name = "CommonDAO")
	private CommonDAO commonDAO;

	@Override
	public int insert_mb_usedlog(Map<String, String> map) {
		return commonDAO.insert_mb_usedlog(map);
	}

	@Override
	public List<MbEtcCdVO> select_mb_etccd_list(String kdcd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(kdcd, kdcd);
		return commonDAO.select_mb_etccd_list(map);
	}

	@Override
	public List<MbEtcCdVO> select_mb_etccd_list(Map<String, String> map) {
		return commonDAO.select_mb_etccd_list(map);
	}

	@Override
	public List<MbMmseVO> select_mb_mmse_list(String objectIdx) {
		return commonDAO.select_mb_mmse_list(objectIdx);
	}

	@Override
	public List<MbSJVO> select_sunbyul_list(String objectIdx) {
		return commonDAO.select_sunbyul_list(objectIdx);
	}
	@Override
	public List<MbSJVO> select_load_selTablist(Map<String, String> map) {
		return commonDAO.select_load_selTablist(map);
	}

	@Override
	public List<MbSJVO> select_AllSjselection_num() {
		return commonDAO.select_AllSjselection_num();
	}
	@Override
	public int select_TotalAllSjselection_num() {
		return commonDAO.select_TotalAllSjselection_num();
	}
	@Override
	public int insert_AllSjselection_num(MbSJVO mbSJVO) {
		
		return commonDAO.insert_AllSjselection_num(mbSJVO);
 	}
	
	@Override
	public int insert_mb_c2_SelctionOneNumFlagD_list(String objectIdx) {
		
		return commonDAO.insert_mb_c2_SelctionOneNumFlagD_list(objectIdx);
 	}
	@Override
	public int insert_mb_c3_SelctionOneNumFlagD_list(String objectIdx) {
		
		return commonDAO.insert_mb_c3_SelctionOneNumFlagD_list(objectIdx);
 	}
	
	
	@Override
	public List<MbSJVO> select_sjselctionOneNumFlagD_list(String objectIdx) {
		return commonDAO.select_sjselctionOneNumFlagD_list(objectIdx);
	}
	@Override
	public int select_sjselctionOneNumFlagD_totalList(String objectIdx) {
		return commonDAO.select_sjselctionOneNumFlagD_totalList(objectIdx);
	}
	@Override
	public List<MbSJVO> select_c2_chkIdxlist(String objectIdx) {
		return commonDAO.select_c2_chkIdxlist(objectIdx);
	}
	@Override
	public List<MbSJVO> select_c3_chkIdxlist(String objectIdx) {
		return commonDAO.select_c3_chkIdxlist(objectIdx);
	}
	@Override
	public List<MbSJVO> select_sjChk_NullList(String objectIdx) {
		return commonDAO.select_sjChk_NullList(objectIdx);
	}
	@Override
	public List<MbSJVO> select_sj_Nulllist(String objectIdx) {
		return commonDAO.select_sj_Nulllist(objectIdx);
	}
	@Override
	public List<MbSJVO>select_sjFlafDF_list(String objectIdx) {
		return commonDAO.select_sjFlafDF_list(objectIdx);
	}
	@Override
	public List<MbSJVO> select_jungmil_list(String objectIdx) {
		return commonDAO.select_jungmil_list(objectIdx);
	}

	@Override
	public List<MbSJVO> select_etc_list(String objectIdx) {
		return commonDAO.select_etc_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_snsb_list(String objectIdx) {
		return commonDAO.select_snsb_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_mri_list(String objectIdx) {
		return commonDAO.select_mri_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_pet_list(String objectIdx) {
		return commonDAO.select_pet_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_csf_list(String objectIdx) {
		return commonDAO.select_csf_list(objectIdx);
	}

	@Override
	public int truncateTable(String tableName) {
		return commonDAO.truncateTable(tableName);
	}

	@Override
	public List<Map<String, String>> select_mb_c1_list(Map<String, String> map) {
		return commonDAO.select_mb_c1_list(map);
	}

	@Override
	public List<Map<String, String>> select_idx_list(Map<String, String> map) {
		return commonDAO.select_idx_list(map);
	}

	@Override
	public String insert_mb_c2_song(Map<String, String> map) {
		return commonDAO.insert_mb_c2_song(map);
	}

	@Override
	public int select_max_idx(String tablename) {
		return commonDAO.select_max_idx(tablename);
	}

	@Override
	public List<Map<String, String>> select_mb_c2_list() {
		return commonDAO.select_mb_c2_list();
	}

	@Override
	public int select_count(Map<String, String> map) {
		return commonDAO.select_count(map);
	}

	@Override
	public int delete_table(Map<String, String> map) {
		return commonDAO.delete_table(map);
	}

	@Override
	public int update_mb_c2_temp2(int max_idx) {
		return commonDAO.update_mb_c2_temp2(max_idx);
	}

	@Override
	public int insert_mb_c2_song_select_mb_c2_temp2() {
		return commonDAO.insert_mb_c2_song_select_mb_c2_temp2();
	}
	
	@Override
	public List<CommonVO> select_blood_list(String objectIdx) {
		return commonDAO.select_blood_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_apoe_list(String objectIdx) {
		return commonDAO.select_apoe_list(objectIdx);
	}

	@Override
	public List<CommonVO> select_nii_list(Map<String, String> map) {
		return commonDAO.select_nii_list(map);
	}

	
}
