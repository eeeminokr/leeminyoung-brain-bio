package csbrain.target.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import csbrain.target.service.TargetVO;

@Repository("TargetDAO")
public class TargetDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public int insert_csbr1010_objIdx(TargetVO targetVO) {
		
		return sqlSession.insert("CSBR10.insert_csbr1010_objIdx", targetVO);
	}
	
	public int update_mb_object_editlist(Map<String, String> map) {
		return sqlSession.update("CSBR10.update_mb_object_editlist",map);
	}	
	
	public List<TargetVO> select_csbr1010_Chk_objIdx(Map<String, String> map){
	 
		return	sqlSession.selectList("CSBR10.select_csbr1010_Chk_objIdx", map);
		
	}
	
	
}
