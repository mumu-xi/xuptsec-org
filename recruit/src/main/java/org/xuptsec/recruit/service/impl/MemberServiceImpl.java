package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.MemberMapper;
import org.xuptsec.recruit.poji.Member;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.service.MemberService;

import java.util.List;

/**
 * Created by mu on 2017/9/16.
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    /**
     * 分页查询实验室内部成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ResultList findLabMemberByPage(int pageNum, int pageSize) {
        ResultList result = new ResultList();
        try {
            List<Member> list = memberMapper.findLabMemberByPage((pageNum - 1) * pageSize, pageSize);
            for (Member member:
                 list) {
                String[] split = member.getStr().split("，");
                member.setStr("");
                member.setPeopleIntro(split);
            }
            result.setData(list);
            result.setTotal(memberMapper.findTotalMember());
            result.setState("true");
            result.setMessage("请求成功");
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("请求失败");
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
