package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect     // AOP 클래스 선언 : 부가 기능을 주입하는 클래스
@Component  // IoC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {
    // 대상 메서드 선택 : CommentService#create()      [스프링 부트 입문 30]
    // 파라메터가 뭐든지 상관없으니까 .. 찍음, * 은 public 이나 return 타입 적는 곳
//    @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))")
//    private void cut() {
//    }

    // 대상 메서드 선택 : api 패키지의 모든 메서드 [스프링 부트 입문 31]
   @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() {
    }
    // 실행 시점 설정 : cut()의 대상이 수행되기 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) {      // cut()의 대상 메서드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메서드명
        String methodName = joinPoint.getSignature().getName();

        // 입력값 로깅하기

        // CommentService#create()의 입력값 => 5
        // CommentService#create()의 입력값 => CommentDto(id=null, ...)
        for (Object obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }
    @AfterReturning(value = "cut()", returning = "returnObj")       // returning 값에 return 값을 입력해줘야한다.
    public void loggingReturnValue(JoinPoint joinPoint,     // cut()의 대상 메서드
                                   Object returnObj) {      // return 값

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메서드명
        String methodName = joinPoint.getSignature().getName();

        // 반환값 로깅
        // CommentService#create()의 반환값 => CommentDto(id=10, ....)
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
