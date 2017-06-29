package com.song.web.controllerAdvice;

import com.song.web.controller.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Song on 2017/6/28.
 */
@ControllerAdvice
public class GlobalExceptionController extends BaseController {

    @ExceptionHandler(Exception.class)
    private String defaultExceptionHandle(HttpServletRequest request, Exception e) {
        log.debug(request.getRequestURI() + ":" + e.getMessage());
        return "unauthorized";
    }
}
