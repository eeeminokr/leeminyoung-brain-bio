package csbrain.common.service;

import java.util.List;
import java.util.Map;

import csbrain.data.service.MbObjectVO;
import csbrain.target.service.TargetVO;

public interface SheetService {
	
	List<MbKmmse2VO> select_mb_kmmse2_list(Map<String, String> map);
	int update_mb_kmmse2_newlist(Map<String, String> map);
	int update_mb_kmmse2_editlist(Map<String, String> map);

	List<MbEqVO> select_mb_eq_list(Map<String, String> map);
	int update_mb_eq_newlist(Map<String, String> map);
	int update_mb_eq_editlist(Map<String, String> map);		

	List<MbPeVO> select_mb_pe_list(Map<String, String> map);
	int update_mb_pe_newlist(Map<String, String> map);
	int update_mb_pe_editlist(Map<String, String> map);	
	List<MbIcVO> select_mb_ic_list(Map<String, String> map);
	int insert_mb_ic_list(MbIcVO mbIcVO);
	int update_mb_ic_newlist(Map<String, String> map);
	int update_mb_ic_editlist(Map<String, String> map);
	List<MbDmVO> select_mb_dm_list(Map<String, String> map);
	List<MbSmVO> select_mb_sm_list(Map<String, String> map);
	
	int update_mb_dm_newlist(Map<String, String> map);
	int update_mb_dm_editlist(Map<String, String> map);
	int update_mb_sm_newlist(Map<String, String> map);
	int update_mb_sm_editlist(Map<String, String> map);
	int update_mb_dr_newlist(Map<String, String> map);
	int update_mb_dr_editlist(Map<String, String> map);
	List<MbDrVO> select_mb_dr_list(Map<String, String> map);
	int update_mb_pha_newlist(Map<String, String> map);
	int update_mb_pha_editlist(Map<String, String> map);
	List<MbPhaVO> select_mb_pha_list(Map<String, String> map);
	int update_mb_sp_newlist(Map<String, String> map);
	int update_mb_sp_editlist(Map<String, String> map);
	int update_mb_mh_newlist(Map<String, String> map);
	int update_mb_mh_editlist(Map<String, String> map);
	int update_mb_dg_newlist(Map<String, String> map);
	int update_mb_dg_editlist(Map<String, String> map);
	int update_mb_kdsq_newlist(Map<String, String> map);
	int update_mb_kdsq_editlist(Map<String, String> map);
	int update_mb_kdsqv_newlist(Map<String, String> map);
	int update_mb_kdsqv_editlist(Map<String, String> map);
	int update_mb_smcq_newlist(Map<String, String> map);
	int update_mb_smcq_editlist(Map<String, String> map);
	int update_mb_gdsk_newlist(Map<String, String> map);
	int update_mb_gdsk_editlist(Map<String, String> map);
	int update_mb_c1_newlist(Map<String, String> map);
	int update_mb_c1_editlist(Map<String, String> map);
	int update_mb_c3_newlist(Map<String, String> map);
	int update_mb_c3_editlist(Map<String, String> map);
	int update_mb_c2_cogn2_newlist2(Map<String, String> map);
	int update_mb_c2_cogn2_editlist2(Map<String, String> map);
	int update_mb_c2_cogn2_list2(Map<String, String> map);
	int update_mb_c2_cogn2_newlist1(Map<String, String> map);
	int update_mb_c2_cogn2_editlist1(Map<String, String> map);
	int update_mb_c2_cogn2_list1(Map<String, String> map);
	int update_mb_c2_cogn1_newlist4(Map<String, String> map);
	int update_mb_c2_cogn1_editlist4(Map<String, String> map);
	int update_mb_c2_cogn1_list4(Map<String, String> map);	
	int update_mb_c2_cogn1_newlist3(Map<String, String> map);
	int update_mb_c2_cogn1_editlist3(Map<String, String> map);
	int update_mb_c2_cogn1_list3(Map<String, String> map);	
	int update_mb_c2_cogn1_newlist2(Map<String, String> map);
	int update_mb_c2_cogn1_editlist2(Map<String, String> map);
	int update_mb_c2_cogn1_list2(Map<String, String> map);		
	int update_mb_c2_cogn1_newlist(Map<String, String> map);
	int update_mb_c2_cogn1_editlist(Map<String, String> map);
	int update_mb_c2_cogn1_list(Map<String, String> map);	
	
	List<MbMhVO> select_mb_mh_list(Map<String, String> map);
	List<MbSpVO> select_mb_sp_list(Map<String, String> map);
	List<MbMbVO> select_mb_mb_list(Map<String, String> map);
	List<MbFhVO> select_mb_fh_list(Map<String, String> map);
	int update_mb_fmh_newlist(Map<String, String> map);
	int update_mb_fmh_editlist(Map<String, String> map);
	List<MbFmhVO> select_mb_fmh_list(Map<String, String> map);
	List<MbMmseVO> select_mb_kmmse_list(Map<String, String> map);
	List<MbMmseVO> select_mb_mmseds_list(Map<String, String> map);
	List<MbMmseVO> select_mb_mmsekc_list(Map<String, String> map);
	List<MbDgVO> select_mb_dg_list(Map<String, String> map);
	List<MbAhVO> select_mb_ah_list(Map<String, String> map);
	List<MbShVO> select_mb_sh_list(Map<String, String> map);
	List<MbKdsqVO> select_mb_kdsq_list(Map<String, String> map);
	List<MbKdsqvVO> select_mb_kdsqv_list(Map<String, String> map);
	List<MbSmcqVO> select_mb_smcq_list(Map<String, String> map);
	List<MbGdsVO> select_mb_gdsk_list(Map<String, String> map);
	int update_mb_kiadl_newlist(Map<String, String> map);
	int update_mb_kiadl_editlist(Map<String, String> map);
	List<MbKiadlVO> select_mb_kiadl_list(Map<String, String> map);
	List<String> select_remark(Map<String, String> map);
	List<MbC1VO> select_mb_c1_list(Map<String, String> map);
	List<MbC2VO> select_mb_c2_chkExamNumlist(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn1_list(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn1_list2(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn1_list3(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn1_list4(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn2_list1(Map<String, String> map);
	List<MbC2VO> select_mb_c2_cogn2_list2(Map<String, String> map);
	List<MbC3VO> select_mb_c3_list(Map<String, String> map);
	List<MbHrsdVO> select_mb_hrsd_list(Map<String, String> map);
	List<MbCdr1VO> select_mb_cdr1_list(Map<String, String> map);
	List<MbCdr2VO> select_mb_cdr2_list(Map<String, String> map);
	List<MbLabVO> select_mb_lab1_list(Map<String, String> map);
	List<MbLabVO> select_mb_lab2_list(Map<String, String> map);
	List<MbDiaVO> select_mb_dia_list(Map<String, String> map);
	List<String> select_memo(Map<String, String> map);
	List<MbTestblodVO> select_mb_testblod_list(Map<String, String> map);

}
