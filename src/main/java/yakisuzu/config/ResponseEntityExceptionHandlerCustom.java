package yakisuzu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ResponseEntityExceptionHandlerCustom extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandlerCustom.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // bodyがnullでくるので、共通フォーマットに変更
        return super.handleExceptionInternal(ex, toBody(ex, status), headers, status, request);
    }

    ErrorResponseDto toBody(Exception ex, HttpStatus status) {
        String errorName = ex.getClass().getSimpleName();
        logger.debug("{}: status={}", errorName, status.value());

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (HttpRequestMethodNotSupportedException) ex);
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (HttpMediaTypeNotSupportedException) ex);
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (HttpMediaTypeNotAcceptableException) ex);
        } else if (ex instanceof MissingPathVariableException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (MissingPathVariableException) ex);
        } else if (ex instanceof MissingServletRequestParameterException) {
            logger.debug("{}: パラメータ名の指定間違い/指定無し", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (MissingServletRequestParameterException) ex);
        } else if (ex instanceof ServletRequestBindingException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (ServletRequestBindingException) ex);
        } else if (ex instanceof ConversionNotSupportedException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (ConversionNotSupportedException) ex);
        } else if (ex instanceof TypeMismatchException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (TypeMismatchException) ex);
        } else if (ex instanceof HttpMessageNotReadableException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (HttpMessageNotReadableException) ex);
        } else if (ex instanceof HttpMessageNotWritableException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (HttpMessageNotWritableException) ex);
        } else if (ex instanceof MethodArgumentNotValidException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (MethodArgumentNotValidException) ex);
        } else if (ex instanceof MissingServletRequestPartException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (MissingServletRequestPartException) ex);
        } else if (ex instanceof BindException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (BindException) ex);
        } else if (ex instanceof NoHandlerFoundException) {
            // application.ymlで/errorへのmappingを無効済
            logger.error("{}: NotFound", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (NoHandlerFoundException) ex);
        } else if (ex instanceof AsyncRequestTimeoutException) {
            logger.error("{}: TODO", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, (AsyncRequestTimeoutException) ex);
        } else if (ex instanceof ConstraintViolationException) {
            // 追加
            logger.debug("{}: パラメータ名が正しいが、型がおかしい", errorName);
            return ErrorResponseDtoFactory.of(errorName, (ConstraintViolationException) ex);
        } else {
            logger.error("{}: ハンドルしていない", errorName);
            return ErrorResponseDtoFactory.ofDefault(errorName, ex);
        }
    }
}
