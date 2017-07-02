package com.song.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Song on 2017/6/28.
 */
@ControllerAdvice
public class GlobalExceptionController extends BaseController {

    @ExceptionHandler(Exception.class)
    private ModelAndView defaultExceptionHandle(HttpServletRequest request, Exception e) {
        log.debug(request.getRequestURI() + ":", e);
        return new ModelAndView("unauthorized");
    }
}
