package org.delivery.api.exceptionhandler;
// 예상하지 못한 모든 예외를 처리한다.

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.criteria.CriteriaBuilder;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) // 가장 마지막에 실행 적용(값이 높을수록 마지막에 실행된다)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
            log.error("", exception);

            return ResponseEntity
                    .status(500)
                    .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)
                    );
    }
}
