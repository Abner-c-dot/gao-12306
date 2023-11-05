package org.opengoofy.index12306.framework.starter.user.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @Auther: Mr.wu
 * @Date: 2023/11/5 16:29
 * @Description:
 */
public class UserContext {

    private static final ThreadLocal<UserInfo> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 设置用户上下文
     * @param userInfo
     */
    public static void setUserInfo(UserInfo userInfo){
        USER_THREAD_LOCAL.set(userInfo);
    }

    /**
     * 获取用户ID
     * @return
     */
    public static String getUserID(){
        UserInfo userInfo = USER_THREAD_LOCAL.get();
        String userID = Optional.ofNullable(userInfo).map(UserInfo::getUserId).orElse(null);
        return userID;
    }

    /**
     * 获取上下文中的用户信息
     * @return
     */
    public static String getUserName(){
        UserInfo userInfo = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfo).map(UserInfo::getUserName).orElse(null);
    }

    /**
     * 获取上下文中的用户真实姓名
     * @return
     */
    public static String getRealName(){
        UserInfo userInfo = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfo).map(UserInfo::getRealName).orElse(null);
    }

    /**
     * 清理上下文
     */
    public static void removeUser(){
        USER_THREAD_LOCAL.remove();
    }





}
