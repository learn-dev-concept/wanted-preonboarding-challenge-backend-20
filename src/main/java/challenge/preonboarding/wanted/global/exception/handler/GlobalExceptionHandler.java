package challenge.preonboarding.wanted.global.exception.handler;

import challenge.preonboarding.wanted.global.dto.response.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static challenge.preonboarding.wanted.global.constant.StatusCode.*;

@Slf4j
@Order(value = 2)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Servlet Error
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseDto<String>> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(NOT_SUPPORTED_MEDIA_TYPE.getCode()
                , NOT_SUPPORTED_MEDIA_TYPE.getMsg(), null), NOT_SUPPORTED_MEDIA_TYPE.getStatus());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ResponseDto<String>> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) throws JsonProcessingException {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(NOT_ACCEPTABLE_TYPE.getCode()
                , NOT_ACCEPTABLE_TYPE.getMsg(), null), NOT_ACCEPTABLE_TYPE.getStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto<String>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(NOT_SUPPORTED_METHOD.getCode()
                , NOT_SUPPORTED_METHOD.getMsg(), null), NOT_SUPPORTED_METHOD.getStatus());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDto<String>> noResourceFoundException(NoResourceFoundException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(NOT_FOUND_RESOURCE.getCode()
                , NOT_FOUND_RESOURCE.getMsg(), null), NOT_FOUND_RESOURCE.getStatus());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseDto<String>> missingRequestHeaderException(MissingRequestHeaderException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(REQ_HEADER.getCode()
                , ex.getHeaderName() + REQ_HEADER.getMsg(),null), REQ_HEADER.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseDto<String>> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(REQ_PARAM.getCode()
                , ex.getParameterName() + REQ_PARAM.getMsg(), null), REQ_PARAM.getStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ResponseDto<String>> missingPathVariableException(MissingPathVariableException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(REQ_PARAM.getCode()
                , ex.getParameter().getParameterName() + REQ_PARAM.getMsg(), null), REQ_PARAM.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<String>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(BAD_PARAM.getCode()
                , BAD_PARAM.getMsg(), null), BAD_PARAM.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(BAD_PARAM.getCode()
                , BAD_PARAM.getMsg(), null), BAD_PARAM.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<String>> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<>(BAD_PARAM.getCode()
                , BAD_PARAM.getMsg(), null), BAD_PARAM.getStatus());
    }

    // Database Error


    // Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> exception(Exception ex) {
        log.error("예기치 못한 서버 오류가 발생하였습니다", ex);

        return new ResponseEntity<ResponseDto<String>>(
                new ResponseDto<>(SERVER_ERROR.getCode(), SERVER_ERROR.getMsg(), null), SERVER_ERROR.getStatus());
    }
}
