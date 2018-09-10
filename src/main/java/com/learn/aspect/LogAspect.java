package com.learn.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    /*
    定义切入点表达式  execution (* com.sample.service.impl..*.*(..))
    execution()是最常用的切点函数，其语法如下所示：

    整个表达式可以分为五个部分：
            1、execution(): 表达式主体。
            2、第一个*号：表示返回类型，*号表示所有的类型。
            3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
            4、第二个*号：表示类名，*号表示所有的类。
            5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
    */

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Before("execution(* com.learn.service.impl.UserServiceImpl.*(..))")
    public void loggingServiceAdvice() {
        System.out.println("Executing Service (Service前置)...");
    }

    @Before("execution(* com.learn.controller.ImgIconController.*(..))")
    public void loggingControllerAdvice() {
        System.out.println("Executing Controller(Controller前置)...");
    }

    @AfterThrowing(value="within(com.learn..*)", throwing = "ex")
    public void loggingExceptions(JoinPoint joinPoint, Exception ex) {
        System.err.println("(AfterThrowing处理异常抛出)Exception thrown in Method = "
                + joinPoint.toString() + " " + ex.getClass().getSimpleName() + " = " + ex.getMessage());
    }

    /*配置dao的访问记录，在每一个dao方法执行前后各创建一个切面，要用到@Around通知，
        1、首先来创建一个Pointcut
        2、Pointcut匹配了dao package及子package下的任意类的任意方法，之后就可以根据这个Pointcut来创建Around通知
    */
    @Pointcut("execution(* com.learn.dao..*.*(..))")
    public void daoPointCut() {
    }

    @Around("daoPointCut()")
    public Object loggingAround(ProceedingJoinPoint joinpoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("method starts...."
                + joinpoint.getSignature().getDeclaringTypeName() + "_"
                + joinpoint.getSignature().getName() + " with "
                + arrayToString(joinpoint.getArgs()));
        Object result = joinpoint.proceed();
        long diff = System.currentTimeMillis() - start;
        logger.info("method ends...."
                + joinpoint.getSignature().getDeclaringTypeName() + "_"
                + joinpoint.getSignature().getName() + " with " + diff + "ms");
        return result;
    }

    private String arrayToString(Object[] traces) {
        StringBuilder trace = new StringBuilder();
        for (Object s : traces) {
            trace.append(s == null ? "" : s.toString() + "\t");
        }
        if (trace.length() == 0) {
            trace.append("no parameter");
        }
        return trace.toString();
    }
}
