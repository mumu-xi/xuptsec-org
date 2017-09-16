package org.xuptsec.recruit.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.xuptsec.recruit.poji.Member;

import java.util.List;

/**
 * Created by mu on 2017/9/16.
 */
@Repository
public interface MemberMapper {
    /**
     * 分页查询实验室内部成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Member> findLabMemberByPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 内部成员总数
     * @return
     */
    int findTotalMember();
}
