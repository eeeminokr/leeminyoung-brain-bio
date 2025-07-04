package csbrain.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import csbrain.data.service.MbObjectVO;
import csbrain.target.service.TargetVO;

@Repository("SheetDAO")
public class SheetDAO {
	@Autowired
	private SqlSession sqlSession;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<MbKmmse2VO> select_mb_kmmse2_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_kmmse2_list", map);
	}
	public int update_mb_kmmse2_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_kmmse2_newlist",map);
	}
	public int update_mb_kmmse2_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_kmmse2_editlist",map);
	}

	
	public List<MbEqVO> select_mb_eq_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_eq_list", map);
	}
	public int update_mb_eq_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_eq_newlist",map);
	}
	public int update_mb_eq_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_eq_editlist",map);
	}		
	
	
	public List<MbPeVO> select_mb_pe_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_pe_list", map);
	}
	public int update_mb_pe_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_pe_newlist",map);
	}
	public int update_mb_pe_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_pe_editlist",map);
	}	
	
	public List<MbIcVO> select_mb_ic_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_ic_list", map);
	}
	
	public int insert_mb_ic_list(MbIcVO mbIcVO) {
		return sqlSession.insert("insert_mb_ic_list",mbIcVO);
	}
	public int update_mb_ic_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_ic_newlist",map);
	}
	public int update_mb_ic_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_ic_editlist",map);
	}
	
	

	public List<MbDmVO> select_mb_dm_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_dm_list", map);
	}

	

	public int update_mb_dm_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_dm_newlist",map);
	}	
	
	
	public int update_mb_dm_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_dm_editlist",map);
	}

	
	
	public int update_mb_sm_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_sm_newlist",map);
	}	
	
	
	public int update_mb_sm_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_sm_editlist",map);
	}

	public int update_mb_dr_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_dr_newlist",map);
	}	
	
	
	public int update_mb_dr_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_dr_editlist",map);
	}
	
	public int update_mb_pha_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_pha_newlist",map);
	}	
	
	
	public int update_mb_pha_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_pha_editlist",map);
	}
	public int update_mb_sp_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_sp_newlist",map);
	}	
	
	public int update_mb_sp_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_sp_editlist",map);
	}
	public int update_mb_mh_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_mh_newlist",map);
	}	
	
	public int update_mb_mh_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_mh_editlist",map);
	}
	
	public int update_mb_dg_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_dg_newlist",map);
	}	
	
	public int update_mb_dg_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_dg_editlist",map);
	}
	
	public int update_mb_kdsq_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_kdsq_newlist",map);
	}	
	
	public int update_mb_kdsq_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_kdsq_editlist",map);
	}
	
	public int update_mb_kdsqv_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_kdsqv_newlist",map);
	}	
	public int update_mb_kdsqv_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_kdsqv_editlist",map);
	}		
		
	public int update_mb_smcq_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_smcq_editlist",map);
	}	
	public int update_mb_smcq_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_smcq_newlist",map);
	}	
	public int update_mb_gdsk_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_gdsk_editlist",map);
	}	
	public int update_mb_gdsk_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_gdsk_newlist",map);
	}		
	public int update_mb_c1_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c1_editlist",map);
	}	
	public int update_mb_c1_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c1_newlist",map);
	}	
	public int update_mb_c3_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c3_editlist",map);
	}	
	public int update_mb_c3_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c3_newlist",map);
	}		
	public int update_mb_c2_cogn2_editlist2(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn2_editlist2",map);
	}	
	public int update_mb_c2_cogn2_newlist2(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn2_newlist2",map);
	}
	public int update_mb_c2_cogn2_list2(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn2_list2",map);
	}

	
	public int update_mb_c2_cogn2_editlist1(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn2_editlist1",map);
	}	
	public int update_mb_c2_cogn2_newlist1(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn2_newlist1",map);
	}	
	
	public int update_mb_c2_cogn2_list1(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn2_list1",map);
	}

	public int update_mb_c2_cogn1_editlist4(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_editlist4",map);
	}	
	public int update_mb_c2_cogn1_newlist4(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn1_newlist4",map);
	}	
	
	public int update_mb_c2_cogn1_list4(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_list4",map);
	}	
	public int update_mb_c2_cogn1_editlist3(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_editlist3",map);
	}	
	public int update_mb_c2_cogn1_newlist3(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn1_newlist3",map);
	}	
	
	public int update_mb_c2_cogn1_list3(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_list3",map);
	}	
	
	public int update_mb_c2_cogn1_editlist2(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_editlist2",map);
	}	
	public int update_mb_c2_cogn1_newlist2(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn1_newlist2",map);
	}	
	
	public int update_mb_c2_cogn1_list2(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_list2",map);
	}	
		
	public int update_mb_c2_cogn1_editlist(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_editlist",map);
	}	
	public int update_mb_c2_cogn1_newlist(Map<String, String> map) {
		return sqlSession.insert("Sheet.update_mb_c2_cogn1_newlist",map);
	}	
	
	public int update_mb_c2_cogn1_list(Map<String, String> map) {
		return sqlSession.update("Sheet.update_mb_c2_cogn1_list",map);
	}	
	
	
	public List<MbSmVO> select_mb_sm_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_sm_list", map);
	}

	public List<MbDrVO> select_mb_dr_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_dr_list", map);
	}
	public List<MbPhaVO> select_mb_pha_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_pha_list", map);
	}
	public List<MbSpVO> select_mb_sp_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_sp_list", map);
	}
	
	public List<MbMhVO> select_mb_mh_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_mh_list", map);
	}
	
	public List<MbDgVO> select_mb_dg_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_dg_list", map);
	}

	
	
	
	
	
	public List<MbMbVO> select_mb_mb_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_mb_list", map);
	}

	public List<MbFhVO> select_mb_fh_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_fh_list", map);
	}
	
	public int update_mb_fmh_newlist(Map<String, String> map) {
		
		return sqlSession.insert("Sheet.update_mb_fmh_newlist",map);
	}
	public int update_mb_fmh_editlist(Map<String, String> map) {
		
		return sqlSession.update("Sheet.update_mb_fmh_editlist",map);
	}
	
	public int insert_mb_fmh_list(Map<String, String> map) {
		
		return sqlSession.insert("Sheet.insert_mb_fmh_list",map);
	}
		
	public List<MbFmhVO> select_mb_fmh_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_fmh_list", map);
	}
	public List<MbMmseVO> select_mb_kmmse_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_kmmse_list", map);
	}




	public List<MbAhVO> select_mb_ah_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_ah_list", map);
	}

	public List<MbShVO> select_mb_sh_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_sh_list", map);
	}

	public List<MbKdsqVO> select_mb_kdsq_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_kdsq_list", map);
	}

	public List<MbKdsqvVO> select_mb_kdsqv_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_kdsqv_list", map);
	}

	public List<MbSmcqVO> select_mb_smcq_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_smcq_list", map);
	}

	public List<MbGdsVO> select_mb_gdsk_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_gdsk_list", map);
	}
	public int update_mb_kiadl_newlist(Map<String, String> map) {
		
		return sqlSession.insert("Sheet.update_mb_kiadl_newlist",map);
	}
	public int update_mb_kiadl_editlist(Map<String, String> map) {
		
		return sqlSession.update("Sheet.update_mb_kiadl_editlist",map);
	}
	public List<MbKiadlVO> select_mb_kiadl_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_kiadl_list", map);
	}

	public List<String> select_remark(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_remark", map);
	}

	public List<MbC1VO> select_mb_c1_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c1_list", map);
	}
	public List<MbC2VO> select_mb_c2_chkExamNumlist(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_chkExamNumlist", map);
	}
	public List<MbC2VO> select_mb_c2_cogn1_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn1_list", map);
	}

	public List<MbC2VO> select_mb_c2_cogn1_list2(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn1_list2", map);
	}
	public List<MbC2VO> select_mb_c2_cogn1_list3(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn1_list3", map);
	}
	public List<MbC2VO> select_mb_c2_cogn1_list4(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn1_list4", map);
	}
	public List<MbC2VO>select_mb_c2_cogn2_list1(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn2_list1", map);
	}
	
	public List<MbC2VO> select_mb_c2_cogn2_list2(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c2_cogn2_list2", map);
	}

	public List<MbC3VO> select_mb_c3_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_c3_list", map);
	}

	public List<MbHrsdVO> select_mb_hrsd_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_hrsd_list", map);
	}

	public List<MbCdr1VO> select_mb_cdr1_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_cdr1_list", map);
	}

	public List<MbCdr2VO> select_mb_cdr2_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_cdr2_list", map);
	}

	public List<MbLabVO> select_mb_lab1_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_lab1_list", map);
	}

	public List<MbLabVO> select_mb_lab2_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_lab2_list", map);
	}

	public List<MbDiaVO> select_mb_dia_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_dia_list", map);
	}

	public List<String> select_memo(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_memo", map);
	}

	public List<MbTestblodVO> select_mb_testblod_list(Map<String, String> map) {
		return sqlSession.selectList("Sheet.select_mb_testblod_list", map);
	}


}
