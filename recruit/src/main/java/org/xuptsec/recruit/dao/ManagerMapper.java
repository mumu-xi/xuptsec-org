package org.xuptsec.recruit.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.xuptsec.recruit.poji.Manager;

/**
 * Created by mu on 2017/9/10.
 */
@Repository
public interface ManagerMapper {
    Manager managerLogin(@Param("username") String username, @Param("password") String password);
}
