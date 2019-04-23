package com.example.yyw.config;

import com.example.yyw.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 全局异常处理切面 Description: 利用 @ControllerAdvice + @ExceptionHandler
 * 组合处理Controller层RuntimeException异常
 * 
 * @author rico
 * @created 2017年7月4日 下午4:29:07
 */
@ControllerAdvice   // 控制器增强
@ResponseBody
@Slf4j
public class ExceptionAspect {

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseData handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e, HttpServletRequest request) {
		log.error("could_not_read_json...", e);
		ResponseData info = ResponseData.failure("could_not_read_json");
        return info;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseData handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
		log.error("parameter_validation_exception...", e);
		ResponseData info = ResponseData.failure("parameter_validation_exception");
        return info;
	}

	/**
	 * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseData handleHttpRequestMethodNotSupportedException(
	        HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		log.error("request_method_not_supported...", e);
		ResponseData info = ResponseData.failure("request_method_not_supported");
        return info;
	}

	/**
	 * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseData handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
		log.error("content_type_not_supported...", e);
		ResponseData info = ResponseData.failure("content_type_not_supported");
        return info;
	}

	/**
	 * 500 - Internal Server Error
	 */
	/*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TokenException.class)
	public ResponseData handleTokenException(Exception e, HttpServletRequest request) {
		log.error("Token is invaild...", e);
		ResponseData info = ResponseData.failure("Token is invaild");
		return info;
	}*/

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseData handleException(Exception e,HttpServletRequest request) {
		log.error("Internal Server Error...", e);
		ResponseData info = ResponseData.failure("Internal Server Error");
        return info;
	}

	/**
	 * @Validated 参数校验未通过
	 * */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BindException.class)
	public ResponseData handleBindException(BindException e,HttpServletRequest request){
		BindingResult bindingResult = e.getBindingResult();
		List<String> msg = new ArrayList<>();
		if(bindingResult.hasErrors()){
			bindingResult.getFieldErrors().forEach(fieldError -> msg.add(fieldError.getDefaultMessage()));
		}
		log.error("paremeter validation failure... : {}", msg);
		ResponseData info = ResponseData.failure(Arrays.toString(msg.toArray()));
		return info;
	}
}
