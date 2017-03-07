package com.hci.api.util;

import cn.magicbeans.cache.util.CacheUtil;
import com.hci.api.bean.UserInfo;
import com.hci.api.constant.ApiCodeConstant;
import com.hci.business.exception.BusinessException;
import com.hci.business.util.ShareCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类 - 用户登录存取
 * Created by flyong86 on 2016/5/6.
 */
public class LoginHelper {

    /** 请求Header Token. */
    public final static String TOKEN = "token";
    private final static String LOGIN_USER = "_LOGIN_USER_";
    private final static int EXPIRE = 7 * 24 * 60 * 60;

    /**
     * 添加一个登录用户
     * @param user - 用户
     * @return token
     */
    public final static String addUser(UserInfo user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(LOGIN_USER, user);
        String token = ShareCodeUtil.serialCode(user.getId());
        //设置token（请求令牌）
        user.setToken(token);
        CacheUtil.put(LOGIN_USER + user.getId(), user, EXPIRE);

        return token;
    }

    /**
     * 获取当前登录用户
     * @return 登录用户信息
     */
    public final static UserInfo getCurrent() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object temp = request.getSession().getAttribute(LOGIN_USER);
        if (null == temp) {
            String token = request.getHeader(TOKEN);

            if (StringUtils.isNotEmpty(token)) {
                long userId = ShareCodeUtil.code2Long(token);
                UserInfo user = CacheUtil.get(LOGIN_USER + userId, UserInfo.class);
                request.getSession().setAttribute(LOGIN_USER, user);
                return user;
            }

            return null;
        }

        return (UserInfo) temp;
    }

    /**
     * 更新用户信息.
     * @return 登录用户信息
     */
    public final static void update(String token, UserInfo user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (user != null) {
            request.getSession().setAttribute(LOGIN_USER, user);

            if (StringUtils.isNotEmpty(token) ) {
                CacheUtil.put(LOGIN_USER + token, user);
            }
        }
    }



    /**
     * 设置用户信息失效.
     */
    public final static void invalidate() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().invalidate();
        String token = request.getHeader(TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            long userId = ShareCodeUtil.code2Long(token);
            CacheUtil.delKey(LOGIN_USER + userId);
        }
    }
}
