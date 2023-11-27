package sh.hellospringboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* sh.hellospringboot..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: "+joinPoint.toString());

        try{
            Object result = joinPoint.proceed(); // 다음 메소드로 진행 ?
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;

            System.out.println("END: "+joinPoint.toString()+" --- "+timeMs+"ms");
        }
    }
}
