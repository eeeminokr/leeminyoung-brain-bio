package csbrain.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import csbrain.common.service.AbstractService;
import csbrain.data.service.MbObjectVO;
import csbrain.data.service.MbOpinionVO;
import csbrain.main.service.MemberProgramListVO;
import csbrain.main.service.MemberService;
import csbrain.main.service.MemberUseProgramVO;
import csbrain.main.service.MemberVO;

@Service("MemberService")
public class MemberServiceImpl extends AbstractService implements MemberService{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** DAO Class */
	@Resource(name = "MainDAO")
	private MainDAO mainDAO;

	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;

	@Override
	public List<MemberVO> selectMember(MemberVO memberVO) {
		return memberDAO.selectMember(memberVO);
	}

	@Override
	public int updateMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return memberDAO.updateMemberUseProgram(memberUseProgramVO);
	}

	@Override
	public int selectCountMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return memberDAO.selectCountMemberUseProgram(memberUseProgramVO);
	}

	@Override
	public int insertMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return memberDAO.insertMemberUseProgram(memberUseProgramVO);
	}

	@Override
	public int selectCountMemberProgramList(String progid) {
		return memberDAO.selectCountMemberProgramList(progid);
	}

	@Override
	public int selectCountMemberPermissionPower(Map<String, String> map) {
		return memberDAO.selectCountMemberPermissionPower(map);
	}

	@Override
	public List<MemberProgramListVO> selectMemberProgramList(String progid) {
		return memberDAO.selectMemberProgramList(progid);
	}

	@Override
	public List<Map<String, String>> selectMemberPermissionPowerList(String userId) {
		return memberDAO.selectMemberPermissionPowerList(userId);
	}

	@Override
	public List<MemberProgramListVO> selectMemberProgramListForWeb() {
		return memberDAO.selectMemberProgramListForWeb();
	}

	@Override
	public MbObjectVO selectMemberObject(String objectIdx) {
		return memberDAO.selectMemberObject(objectIdx);
	}

	@Override
	public List<MbObjectVO> select_from_object_to_dm(Map<String, String> map) {
	return memberDAO.select_from_object_to_dm(map);
	}
	
	@Override
	public MbOpinionVO selectMemberOpinion(String objectIdx) {
		return memberDAO.selectMemberOpinion(objectIdx);
	}

	@Override
	public int updateMemberOpinion(Map<String, String> map) {
		return memberDAO.updateMemberOpinion(map);
	}

	@Override
	public int insertMemberOpinion(Map<String, String> map) {
		return memberDAO.insertMemberOpinion(map);
	}

	@Override
	public int updateMemberOpinionRemark(Map<String, String> map) {
		return memberDAO.updateMemberOpinionRemark(map);
	}

	@Override
	public int insertMemberOpinionRemark(Map<String, String> map) {
		return memberDAO.insertMemberOpinionRemark(map);
	}

	@Override
	public MbOpinionVO selectMemberOpinionRemark(String objectIdx) {
		return memberDAO.selectMemberOpinionRemark(objectIdx);
	}

}
