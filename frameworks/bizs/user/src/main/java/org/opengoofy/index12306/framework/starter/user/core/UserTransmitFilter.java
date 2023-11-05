package org.opengoofy.index12306.framework.starter.user.core;

import com.opengoofy.index12306.framework.starter.base.constant.UserConstant;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static com.opengoofy.index12306.framework.starter.base.constant.UserConstant.*;

/**
 * @Auther: Mr.wu
 * @Date: 2023/11/5 17:01
 * @Description:
 */
public class UserTransmitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userId = httpServletRequest.getHeader(USER_ID_KEY);
        if (StringUtils.hasText(userId)){
            String userName = httpServletRequest.getHeader(USER_NAME_KEY);
            String realName = httpServletRequest.getHeader(REAL_NAME_KEY);
            if (StringUtils.hasText(userName)){
                userName = URLDecoder.decode(userName, StandardCharsets.UTF_8);
            }
            if (StringUtils.hasText(realName)){
                realName = URLDecoder.decode(realName,StandardCharsets.UTF_8);
            }
            UserInfo userInfo = UserInfo.builder()
                    .userId(userId)
                    .userName(userName)
                    .realName(realName)
                    .build();
            UserContext.setUserInfo(userInfo);
            try {
                chain.doFilter(request,response);
            } finally {
                UserContext.removeUser();
            }
        }

    }
}
