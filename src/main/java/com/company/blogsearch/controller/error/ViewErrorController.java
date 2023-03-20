package com.company.blogsearch.controller.error;

import com.company.blogsearch.constant.ErrorCode;
import com.company.blogsearch.dto.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> errorHandler(HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.BAD_REQUEST), ErrorCode.BAD_REQUEST.getStatus());
    }

}
