package com.xh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbUserAdvice;

public interface KbUserAdviceMapper {
    KbUserAdvice selectByPrimaryKey(Integer id);
    
    
    /**
     * 
     * @Title: parentUserCodeByCode  
     * @Description: 查询项目父类下所有的相关人员的userCode 
     * @author 陈专懂 
     * @return List<String> 
     * @date 2018年7月9日  
     * @version 1.0
     */
    public List<String> parentUserCodeByCode(@Param("parentProjectCode")String parentProjectCode);
    /**
     * 
     * @Title: addUserAdvice  
     * @Description: 新增通知的方法 
     * @author 陈专懂 
     * @return int 
     * @date 2018年7月9日  
     * @version 1.0
     */
    public int addUserAdvice(@Param("kbUserAdvice")KbUserAdvice kbUserAdvice);
    
    /**
     * 
     * @Title: getAdviceMsgByUser  
     * @Description: 获取该用户下所有的未读通知（显示在前端界面）
     * @author 陈专懂 
     * @return KbUserAdvice 
     * @date 2018年7月9日  
     * @version 1.0
     */
    public List<KbUserAdvice> getAdviceMsgByUser(@Param("userCode")String userCode);
    
    /**
     * 
     * @Title: updateAdviceStatusByAdviceCode  
     * @Description:已读（修改通知的状态改成Y） 
     * @author 陈专懂 
     * @return int 
     * @date 2018年7月9日  
     * @version 1.0
     */
    public int updateAdviceStatusByAdviceCode(@Param("adviceCode")List<String> adviceCode);
    
    
}