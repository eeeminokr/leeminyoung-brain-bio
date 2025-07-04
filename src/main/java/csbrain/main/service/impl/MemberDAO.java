package csbrain.main.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import csbrain.data.service.MbObjectVO;
import csbrain.data.service.MbOpinionVO;
import csbrain.main.service.MemberProgramListVO;
import csbrain.main.service.MemberUseProgramVO;
import csbrain.main.service.MemberVO;


@Repository("MemberDAO")
public class MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<MemberVO> selectMember(MemberVO memberVO){
		return sqlSession.selectList("Member.selectMember", memberVO);
	}

	public int updateMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return sqlSession.update("Member.updateMemberUseProgram", memberUseProgramVO);
	}

	public int selectCountMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return sqlSession.selectOne("Member.selectCountMemberUseProgram", memberUseProgramVO);
	}

	public int insertMemberUseProgram(MemberUseProgramVO memberUseProgramVO) {
		return sqlSession.insert("Member.insertMemberUseProgram", memberUseProgramVO);
	}

	public int selectCountMemberProgramList(String progid) {
		return sqlSession.selectOne("Member.selectCountMemberProgramList", progid);
	}

	public int selectCountMemberPermissionPower(Map<String, String> map) {
		return sqlSession.selectOne("Member.selectCountMemberPermissionPower", map);
	}

	public List<MemberProgramListVO> selectMemberProgramList(String progid) {
		return sqlSession.selectList("Member.selectMemberProgramList", progid);
	}

	public List<Map<String, String>> selectMemberPermissionPowerList(String userId) {
		return sqlSession.selectList("Member.selectMemberPermissionPowerList", userId);
	}

	public List<MemberProgramListVO> selectMemberProgramListForWeb() {
		return sqlSession.selectList("Member.selectMemberProgramListForWeb");
	}

	public MbObjectVO selectMemberObject(String objectIdx) {
		return sqlSession.selectOne("Member.selectMemberObject", objectIdx);
	}
	public List<MbObjectVO> select_from_object_to_dm(Map<String, String> map) {
		return sqlSession.selectList("Member.select_from_object_to_dm", map);
	}	

	
	public MbOpinionVO selectMemberOpinion(String objectIdx) {
		return sqlSession.selectOne("Member.selectMemberOpinion", objectIdx);
	}

	public int updateMemberOpinion(Map<String, String> map) {
		return sqlSession.update("Member.updateMemberOpinion", map);
	}

	public int insertMemberOpinion(Map<String, String> map) {
		return sqlSession.insert("Member.insertMemberOpinion", map);
	}
	
	public MbOpinionVO selectMemberOpinionRemark(String objectIdx) {
		return sqlSession.selectOne("Member.selectMemberOpinionRemark", objectIdx);
	}

	public int updateMemberOpinionRemark(Map<String, String> map) {
		return sqlSession.update("Member.updateMemberOpinionRemark", map);
	}

	public int insertMemberOpinionRemark(Map<String, String> map) {
		return sqlSession.insert("Member.insertMemberOpinionRemark", map);
	}
}
