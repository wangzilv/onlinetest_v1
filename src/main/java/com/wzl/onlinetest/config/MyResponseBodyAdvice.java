package com.wzl.onlinetest.config;

import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

@ControllerAdvice
@Configuration
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        logger.debug("MyResponseBodyAdvice==>supports:" + converterType);
        logger.debug("MyResponseBodyAdvice==>supports:" + returnType.getClass());
        logger.debug("MyResponseBodyAdvice==>supports:"
                + MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType));
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return body;
        }
        if (body instanceof ActionResult || body instanceof String) {
            return body;
        } else if (body instanceof File) {
            return body;
        } else {
            logger.debug("MyResponseBodyAdvice==>beforeBodyWrite:" + returnType + "," + body);
            ActionResult result = new ActionResult("success");
            result.setCode(StaticDataConstants.resultMsg.SUCCESS);
            result.setData(body);
            result.setMessage("请求成功");
            body = (Object) result;
            // body.setB("我是后面设置的");
            return body;
        }
    }
}
