package org.opengoofy.index12306.framework.starter.user.toolkit;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.index12306.framework.starter.user.core.UserInfo;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.opengoofy.index12306.framework.starter.base.constant.UserConstant.*;

/**
 * @Auther: Mr.wu
 * @Date: 2023/11/5 15:04
 * @Description:
 */
@Slf4j
public class JWTUtil {

    private static final long EXPIRATION = 86400L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ISS = "index12306";
    public static final String SECRET = "SecretKey039245678901232039487623456783092349288901402967890140939827";

    /**
     * 生成token
     * @param userInfo
     * @return
     */
    public static String generateAccessToken(UserInfo userInfo) {
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(USER_ID_KEY, userInfo.getUserId());
        customerUserMap.put(USER_NAME_KEY, userInfo.getUserName());
        customerUserMap.put(REAL_NAME_KEY, userInfo.getRealName());
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(customerUserMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }

    public static UserInfo parseJwtToken(String jwtToken) {
       if(StringUtils.hasText(jwtToken)){
            String actualJwtToken = jwtToken.replace(TOKEN_PREFIX, "");
           try {
               Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(actualJwtToken).getBody();
               Date expiration = claims.getExpiration();
               if(expiration.after(new Date())){
                   String subject = claims.getSubject();
                   return JSON.parseObject(subject,UserInfo.class);
               }
           } catch (ExpiredJwtException e) {
           }catch (Exception e){
               log.error("解析jwtToken失败",e);
           }
       }
       return null;
    }


}
