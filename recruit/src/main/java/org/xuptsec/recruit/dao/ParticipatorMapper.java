package org.xuptsec.recruit.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.xuptsec.recruit.poji.Participator;

import java.util.List;

/**
 * Created by mu on 2017/9/9.
 */
@Repository
public interface ParticipatorMapper {
    /**
     * 插入学生报名信息
     *
     * @param participator
     * @return
     */
    void insertParticipator(Participator participator);

    /**
     * 查找所有学生报名信息
     * @return
     */
    List<Participator> findParticipatorByPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    int findTotalParticipator();
}
