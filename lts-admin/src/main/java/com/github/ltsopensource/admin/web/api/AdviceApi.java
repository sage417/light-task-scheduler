package com.github.ltsopensource.admin.web.api;

import com.github.ltsopensource.admin.web.support.Builder;
import com.github.ltsopensource.admin.web.vo.RestfulResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Package: com.github.ltsopensource.admin.web.api
 * Author: mac
 * Date: 2016/11/28
 */
@ControllerAdvice(value = {"com.github.ltsopensource.admin.web.api"})
public class AdviceApi {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public RestfulResponse processRuntimeException(NativeWebRequest nativeWebRequest, RuntimeException re) {
        return Builder.build(false, re.getMessage());
    }
}
