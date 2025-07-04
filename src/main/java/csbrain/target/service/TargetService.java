package csbrain.target.service;

import java.util.List;
import java.util.Map;


public interface TargetService {

	int insert_csbr1010_objIdx(TargetVO targetVO);
	int update_mb_object_editlist(Map<String, String> map);
	List<TargetVO> select_csbr1010_Chk_objIdx(Map<String, String> map);
	
}
