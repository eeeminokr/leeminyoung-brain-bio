package csbrain.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import csbrain.common.service.AbstractService;
import csbrain.common.service.MbAhVO;
import csbrain.common.service.MbC1VO;
import csbrain.common.service.MbC2VO;
import csbrain.common.service.MbC3VO;
import csbrain.common.service.MbCdr1VO;
import csbrain.common.service.MbCdr2VO;
import csbrain.common.service.MbDgVO;
import csbrain.common.service.MbDiaVO;
import csbrain.common.service.MbDmVO;
import csbrain.common.service.MbDrVO;
import csbrain.common.service.MbEqVO;
import csbrain.common.service.MbFhVO;
import csbrain.common.service.MbFmhVO;
import csbrain.common.service.MbGdsVO;
import csbrain.common.service.MbHrsdVO;
import csbrain.common.service.MbIcVO;
import csbrain.common.service.MbKdsqVO;
import csbrain.common.service.MbKdsqvVO;
import csbrain.common.service.MbKiadlVO;
import csbrain.common.service.MbKmmse2VO;
import csbrain.common.service.MbLabVO;
import csbrain.common.service.MbMbVO;
import csbrain.common.service.MbMhVO;
import csbrain.common.service.MbMmseVO;
import csbrain.common.service.MbPeVO;
import csbrain.common.service.MbPhaVO;
import csbrain.common.service.MbShVO;
import csbrain.common.service.MbSmVO;
import csbrain.common.service.MbSmcqVO;
import csbrain.common.service.MbSpVO;
import csbrain.common.service.MbTestblodVO;
import csbrain.common.service.SheetService;
import csbrain.data.service.MbObjectVO;
import csbrain.target.service.TargetVO;

@Service("SheetService")
public class SheetServiceImpl extends AbstractService implements SheetService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/** DAO Class */
	@Resource(name = "SheetDAO")
	private SheetDAO sheetDAO;

	@Override
	public List<MbKmmse2VO> select_mb_kmmse2_list(Map<String, String> map) {
		return sheetDAO.select_mb_kmmse2_list(map);
	}
	@Override
	public int update_mb_kmmse2_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kmmse2_newlist(map);
 	}
	@Override
	public int update_mb_kmmse2_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kmmse2_editlist(map);
 	}
	@Override
	public List<MbEqVO> select_mb_eq_list(Map<String, String> map) {
		return sheetDAO.select_mb_eq_list(map);
	}
	@Override
	public int update_mb_eq_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_eq_newlist(map);
 	}
	@Override
	public int update_mb_eq_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_eq_editlist(map);
 	}	
	
	@Override
	public List<MbPeVO> select_mb_pe_list(Map<String, String> map) {
		return sheetDAO.select_mb_pe_list(map);
	}
	@Override
	public int update_mb_pe_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_pe_newlist(map);
 	}
	@Override
	public int update_mb_pe_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_pe_editlist(map);
 	}	

	
	@Override
	public List<MbIcVO> select_mb_ic_list(Map<String, String> map) {
		return sheetDAO.select_mb_ic_list(map);
	}
	@Override
	public int update_mb_ic_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_ic_newlist(map);
 	}
	@Override
	public int update_mb_ic_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_ic_editlist(map);
 	}
	@Override
	public int insert_mb_ic_list(MbIcVO mbIcVO) {
		
		return sheetDAO.insert_mb_ic_list(mbIcVO);
 	}
	
	@Override
	public List<MbDmVO> select_mb_dm_list(Map<String, String> map) {
		return sheetDAO.select_mb_dm_list(map);
	}
	@Override
	public List<MbSmVO> select_mb_sm_list(Map<String, String> map) {
		return sheetDAO.select_mb_sm_list(map);
	}
	@Override
	public int update_mb_dm_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dm_newlist(map);
 	}
	@Override
	public int update_mb_dm_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dm_editlist(map);
 	}
	@Override
	public int update_mb_sm_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_sm_newlist(map);
 	}
	@Override
	public int update_mb_sm_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_sm_editlist(map);
 	}
	@Override
	public List<MbDrVO> select_mb_dr_list(Map<String, String> map) {
		return sheetDAO.select_mb_dr_list(map);
	}
	@Override
	public int update_mb_dr_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dr_newlist(map);
 	}
	@Override
	public int update_mb_dr_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dr_editlist(map);
 	}
	@Override
	public List<MbPhaVO> select_mb_pha_list(Map<String, String> map) {
		return sheetDAO.select_mb_pha_list(map);
	}
	@Override
	public int update_mb_pha_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_pha_newlist(map);
 	}
	@Override
	public int update_mb_pha_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_pha_editlist(map);
 	}
	
	@Override
	public List<MbSpVO> select_mb_sp_list(Map<String, String> map) {
		return sheetDAO.select_mb_sp_list(map);
	}
	@Override
	public int update_mb_sp_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_sp_newlist(map);
 	}
	@Override
	public int update_mb_sp_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_sp_editlist(map);
 	}	
	@Override
	public List<MbMhVO> select_mb_mh_list(Map<String, String> map) {
		return sheetDAO.select_mb_mh_list(map);
	}
	@Override
	public int update_mb_mh_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_mh_newlist(map);
 	}
	@Override
	public int update_mb_mh_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_mh_editlist(map);
 	}
	@Override
	public int update_mb_dg_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dg_newlist(map);
 	}
	@Override
	public int update_mb_dg_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_dg_editlist(map);
 	}	 
	@Override
	public int update_mb_kdsq_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kdsq_newlist(map);
 	}
	@Override
	public int update_mb_kdsq_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kdsq_editlist(map);
 	}		
	@Override
	public int update_mb_kdsqv_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kdsqv_newlist(map);
 	}
	@Override
	public int update_mb_kdsqv_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_kdsqv_editlist(map);
 	}		
	
	@Override
	public int update_mb_smcq_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_smcq_newlist(map);
 	}
	@Override
	public int update_mb_smcq_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_smcq_editlist(map);
 	}		
	
	@Override
	public int update_mb_gdsk_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_gdsk_newlist(map);
 	}
	@Override
	public int update_mb_gdsk_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_gdsk_editlist(map);
 	}	
	@Override
	public int update_mb_c1_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c1_newlist(map);
 	}
	@Override
	public int update_mb_c1_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c1_editlist(map);
 	}
	@Override
	public int update_mb_c3_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c3_newlist(map);
 	}
	@Override
	public int update_mb_c3_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c3_editlist(map);
 	}	
	@Override
	public int update_mb_c2_cogn2_newlist2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_newlist2(map);
 	}
	@Override
	public int update_mb_c2_cogn2_editlist2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_editlist2(map);
 	}	
	@Override
	public int update_mb_c2_cogn2_list2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_list2(map);
 	}	
		
	@Override
	public int update_mb_c2_cogn2_newlist1(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_newlist1(map);
 	}
	@Override
	public int update_mb_c2_cogn2_editlist1(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_editlist1(map);
 	}	
	@Override
	public int update_mb_c2_cogn2_list1(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn2_list1(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_newlist4(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_newlist4(map);
 	}
	@Override
	public int update_mb_c2_cogn1_editlist4(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_editlist4(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_list4(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_list4(map);
 	}		
	
	@Override
	public int update_mb_c2_cogn1_newlist3(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_newlist3(map);
 	}
	@Override
	public int update_mb_c2_cogn1_editlist3(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_editlist3(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_list3(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_list3(map);
 	}		
	@Override
	public int update_mb_c2_cogn1_newlist2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_newlist2(map);
 	}
	@Override
	public int update_mb_c2_cogn1_editlist2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_editlist2(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_list2(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_list2(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_newlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_newlist(map);
 	}
	@Override
	public int update_mb_c2_cogn1_editlist(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_editlist(map);
 	}	
	@Override
	public int update_mb_c2_cogn1_list(Map<String, String> map) {	
		return sheetDAO.update_mb_c2_cogn1_list(map);
 	}
	
	@Override
	public List<MbMbVO> select_mb_mb_list(Map<String, String> map) {
		return sheetDAO.select_mb_mb_list(map);
	}
	@Override
	public List<MbFhVO> select_mb_fh_list(Map<String, String> map) {
		return sheetDAO.select_mb_fh_list(map);
	}
	
	@Override
	public int update_mb_fmh_newlist(Map<String, String> map) {
		
		return sheetDAO.update_mb_fmh_newlist(map);
 	}
	@Override
	public int update_mb_fmh_editlist(Map<String, String> map) {
		
		return sheetDAO.update_mb_fmh_editlist(map);
 	}
	
	
	@Override
	public List<MbFmhVO> select_mb_fmh_list(Map<String, String> map) {
		return sheetDAO.select_mb_fmh_list(map);
	}
	@Override
	public List<MbMmseVO> select_mb_kmmse_list(Map<String, String> map) {
		return sheetDAO.select_mb_kmmse_list(map);
	}
	@Override
	public List<MbMmseVO> select_mb_mmseds_list(Map<String, String> map) {
		return sheetDAO.select_mb_kmmse_list(map);
	}
	@Override
	public List<MbMmseVO> select_mb_mmsekc_list(Map<String, String> map) {
		return sheetDAO.select_mb_kmmse_list(map);
	}

	@Override
	public List<MbDgVO> select_mb_dg_list(Map<String, String> map) {
		return sheetDAO.select_mb_dg_list(map);
	}
	@Override
	public List<MbAhVO> select_mb_ah_list(Map<String, String> map) {
		return sheetDAO.select_mb_ah_list(map);
	}
	@Override
	public List<MbShVO> select_mb_sh_list(Map<String, String> map) {
		return sheetDAO.select_mb_sh_list(map);
	}
	@Override
	public List<MbKdsqVO> select_mb_kdsq_list(Map<String, String> map) {
		return sheetDAO.select_mb_kdsq_list(map);
	}
	@Override
	public List<MbKdsqvVO> select_mb_kdsqv_list(Map<String, String> map) {
		return sheetDAO.select_mb_kdsqv_list(map);
	}
	@Override
	public List<MbSmcqVO> select_mb_smcq_list(Map<String, String> map) {
		return sheetDAO.select_mb_smcq_list(map);
	}
	@Override
	public List<MbGdsVO> select_mb_gdsk_list(Map<String, String> map) {
		return sheetDAO.select_mb_gdsk_list(map);
	}
	
	@Override
	public int update_mb_kiadl_newlist(Map<String, String> map) {
		
		return sheetDAO.update_mb_kiadl_newlist(map);
 	}
	@Override
	public int update_mb_kiadl_editlist(Map<String, String> map) {
		
		return sheetDAO.update_mb_kiadl_editlist(map);
 	}	
	
	@Override
	public List<MbKiadlVO> select_mb_kiadl_list(Map<String, String> map) {
		return sheetDAO.select_mb_kiadl_list(map);
	}
	@Override
	public List<String> select_remark(Map<String, String> map) {
		return sheetDAO.select_remark(map);
	}
	@Override
	public List<MbC1VO> select_mb_c1_list(Map<String, String> map) {
		return sheetDAO.select_mb_c1_list(map);
	}
	
	@Override
	public List<MbC2VO> select_mb_c2_chkExamNumlist(Map<String, String> map) {
		return sheetDAO.select_mb_c2_chkExamNumlist(map);
	}	             
	@Override
	public List<MbC2VO> select_mb_c2_cogn1_list(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn1_list(map);
	}
	@Override
	public List<MbC2VO> select_mb_c2_cogn1_list2(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn1_list2(map);
	}
	@Override
	public List<MbC2VO> select_mb_c2_cogn1_list3(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn1_list3(map);
	}
	@Override
	public List<MbC2VO> select_mb_c2_cogn1_list4(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn1_list4(map);
	}
	@Override
	public List<MbC2VO> select_mb_c2_cogn2_list1(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn2_list1(map);
	}
	
	@Override
	public List<MbC2VO> select_mb_c2_cogn2_list2(Map<String, String> map) {
		return sheetDAO.select_mb_c2_cogn2_list2(map);
	}
	
	@Override
	public List<MbC3VO> select_mb_c3_list(Map<String, String> map) {
		return sheetDAO.select_mb_c3_list(map);
	}
	@Override
	public List<MbHrsdVO> select_mb_hrsd_list(Map<String, String> map) {
		return sheetDAO.select_mb_hrsd_list(map);
	}
	@Override
	public List<MbCdr1VO> select_mb_cdr1_list(Map<String, String> map) {
		return sheetDAO.select_mb_cdr1_list(map);
	}
	@Override
	public List<MbCdr2VO> select_mb_cdr2_list(Map<String, String> map) {
		return sheetDAO.select_mb_cdr2_list(map);
	}
	@Override
	public List<MbLabVO> select_mb_lab1_list(Map<String, String> map) {
		return sheetDAO.select_mb_lab1_list(map);
	}
	@Override
	public List<MbLabVO> select_mb_lab2_list(Map<String, String> map) {
		return sheetDAO.select_mb_lab2_list(map);
	}
	@Override
	public List<MbDiaVO> select_mb_dia_list(Map<String, String> map) {
		return sheetDAO.select_mb_dia_list(map);
	}
	@Override
	public List<String> select_memo(Map<String, String> map) {
		return sheetDAO.select_memo(map);
	}
	@Override
	public List<MbTestblodVO> select_mb_testblod_list(Map<String, String> map) {
		return sheetDAO.select_mb_testblod_list(map);
	}
}
