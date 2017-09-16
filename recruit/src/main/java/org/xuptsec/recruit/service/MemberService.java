package org.xuptsec.recruit.service;

import org.apache.ibatis.annotations.Param;
import org.xuptsec.recruit.poji.ResultList;


/**
 * Created by mu on 2017/9/16.
 */
public interface MemberService {
    /**
     * 分页查询实验室内部成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultList findLabMemberByPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
