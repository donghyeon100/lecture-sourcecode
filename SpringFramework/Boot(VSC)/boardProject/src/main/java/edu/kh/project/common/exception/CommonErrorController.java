package edu.kh.project.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CommonErrorController implements ErrorController{

  @RequestMapping("/error")
  public String error(Model model, HttpServletRequest request) {

    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    int statusCode = Integer.parseInt(status.toString());
    
    Object msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    String errorMessage = (msg != null) ? msg.toString() : "알 수 없는 오류가 발생했습니다.";

    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("statusCode", statusCode);

    return "error/common-error";
  }


}
