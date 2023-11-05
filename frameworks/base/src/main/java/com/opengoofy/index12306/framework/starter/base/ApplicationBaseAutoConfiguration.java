package com.opengoofy.index12306.framework.starter.base;

import com.opengoofy.index12306.framework.starter.base.safa.FastJsonSafeMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 应用组件自动装配
 */

public class ApplicationBaseAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "framework.fastjson.safa-mode", havingValue = "true")
    public FastJsonSafeMode fastJsonSafeMode(){
        return new FastJsonSafeMode();
    }

}
