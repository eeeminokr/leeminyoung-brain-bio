package csbrain.target.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AbstractAspectJAdvisorFactory;
import org.springframework.stereotype.Service;



import csbrain.common.service.AbstractService;
import csbrain.main.service.impl.MemberDAO;
import csbrain.target.service.TargetService;
import csbrain.target.service.TargetVO;


@Service("TargetService")
public class TargetserviceImpl extends AbstractService implements TargetService {

	protected Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Resource(name = "TargetDAO")
	private TargetDAO targetDAO;
	
	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public int insert_csbr1010_objIdx(TargetVO targetVO) {
		
		return targetDAO.insert_csbr1010_objIdx(targetVO);
 	}
	@Override
	public int update_mb_object_editlist(Map<String, String> map) {	
		return targetDAO.update_mb_object_editlist(map);
 	}	
	@Override
	public List<TargetVO> select_csbr1010_Chk_objIdx(Map<String,String>map){
	
		return targetDAO.select_csbr1010_Chk_objIdx(map);
	}


	
}
