package org.xuptsec.recruit.service;

import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultList;

import java.util.List;

/**
 * Created by mu on 2017/9/9.
 */
public interface ParticipatorService {
    /**
     * 插入学生报名信息
     *
     * @param participator
     * @return
     */
    ResultJoin insertParticipator(Participator participator);

    /**
     * 查找所有已报名学生的信息
     * @return
     */
    List<Participator> findParticipatorAll();

    /**
     * 通知报名者面试时间，查询部分信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultList findNoticeParticipator(int pageNum, int pageSize);

}
