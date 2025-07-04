package csbrain.main.service;

import java.util.List;
import java.util.Map;

import csbrain.data.service.MbObjectVO;
import csbrain.data.service.MbOpinionVO;

public interface MemberService {
	List<MemberVO> selectMember(MemberVO memberVO);

	int updateMemberUseProgram(MemberUseProgramVO memberUseProgramVO);

	int selectCountMemberUseProgram(MemberUseProgramVO memberUseProgramVO);

	int insertMemberUseProgram(MemberUseProgramVO memberUseProgramVO);

	int selectCountMemberProgramList(String progid);

	int selectCountMemberPermissionPower(Map<String, String> map);

	List<MemberProgramListVO> selectMemberProgramList(String progid);

	List<Map<String, String>> selectMemberPermissionPowerList(String userId);

	List<MemberProgramListVO> selectMemberProgramListForWeb();

	MbObjectVO selectMemberObject(String objectIdx);

	List<MbObjectVO> select_from_object_to_dm(Map<String, String> map);
	MbOpinionVO selectMemberOpinion(String objectIdx);

	int updateMemberOpinion(Map<String, String> map);

	int insertMemberOpinion(Map<String, String> map);
	
	MbOpinionVO selectMemberOpinionRemark(String objectIdx);

	int updateMemberOpinionRemark(Map<String, String> map);

	int insertMemberOpinionRemark(Map<String, String> map);
}
