package cn.com.codingce.common.exception;

import cn.com.codingce.common.lang.ResultCodeEnum;
import cn.com.codingce.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ma
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 实体校验异常捕获
     *
     * @param e 异常
     * @return Message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------{}", e.getMessage(), e);
        R result = new R();

        result.put("mcode", ResultCodeEnum.CALL_FAILURE.Fmt());
        result.put("msg", "实体校验异常");
        result.put("code", cn.hutool.http.HttpStatus.HTTP_INTERNAL_ERROR);
        result.put("data", e.getMessage());

        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public R handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage(), e);
        R result = new R();

        result.put("mcode", ResultCodeEnum.CALL_FAILURE.Fmt());
        result.put("msg", "Assert异常");
        result.put("code", cn.hutool.http.HttpStatus.HTTP_INTERNAL_ERROR);
        result.put("data", e.getMessage());

        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e.getMessage(), e);
        R result = new R();

        result.put("mcode", ResultCodeEnum.CALL_FAILURE.Fmt());
        result.put("msg", "运行时异常");
        result.put("code", cn.hutool.http.HttpStatus.HTTP_INTERNAL_ERROR);
        result.put("data", e.getMessage());

        return result;
    }

}