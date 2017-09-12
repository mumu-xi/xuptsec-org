package org.xuptsec.recruit.service;

import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultStudentList;

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


}
