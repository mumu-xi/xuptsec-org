package org.xuptsec.recruit.service;

import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultLogin;

/**
 * Created by mu on 2017/9/10.
 */
public interface ManagerService {
    Manager managerLogin(String username, String password);
}
