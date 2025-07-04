package csbrain.common.service;

import java.util.List;
import java.util.Map;

import csbrain.target.service.TargetVO;

public interface CommonService {
	int insert_mb_usedlog(Map<String, String> map);
	List<MbEtcCdVO> select_mb_etccd_list(String kdcd);
	List<MbEtcCdVO> select_mb_etccd_list(Map<String, String> map);
	List<MbMmseVO> select_mb_mmse_list(String objectIdx);
	List<MbSJVO> select_sunbyul_list(String objectIdx);
	List<MbSJVO> select_jungmil_list(String objectIdx);
	List<MbSJVO> select_load_selTablist(Map<String, String> map);
	List<MbSJVO> select_sj_Nulllist(String objectIdx);
	List<MbSJVO> select_sjFlafDF_list(String objectIdx);
	List<MbSJVO> select_AllSjselection_num();
	int select_TotalAllSjselection_num();
	int insert_AllSjselection_num(MbSJVO mbSJVO);
	int insert_mb_c2_SelctionOneNumFlagD_list(String objectIdx);
	int insert_mb_c3_SelctionOneNumFlagD_list(String objectIdx);
	List<MbSJVO> select_sjselctionOneNumFlagD_list(String objectIdx);
	int select_sjselctionOneNumFlagD_totalList(String objectIdx);
	List<MbSJVO> select_c2_chkIdxlist(String objectIdx);
	List<MbSJVO> select_c3_chkIdxlist(String objectIdx);
	List<MbSJVO> select_sjChk_NullList(String objectIdx);
	List<MbSJVO> select_etc_list(String objectIdx);
	List<CommonVO> select_snsb_list(String objectIdx);
	List<CommonVO> select_mri_list(String objectIdx);
	List<CommonVO> select_pet_list(String objectIdx);
	List<CommonVO> select_csf_list(String objectIdx);
	List<CommonVO> select_blood_list(String objectIdx);
	List<CommonVO> select_apoe_list(String objectIdx);
	int truncateTable(String tableName);
	List<Map<String, String>> select_mb_c1_list(Map<String, String> map);
	List<Map<String, String>> select_idx_list(Map<String, String> map);
	String insert_mb_c2_song(Map<String, String> map);
	int select_max_idx(String tablename);
	List<Map<String, String>> select_mb_c2_list();
	int select_count(Map<String, String> map);
	int delete_table(Map<String, String> map);
	int update_mb_c2_temp2(int max_idx);
	int insert_mb_c2_song_select_mb_c2_temp2();
	List<CommonVO> select_nii_list(Map<String, String> map);

}
