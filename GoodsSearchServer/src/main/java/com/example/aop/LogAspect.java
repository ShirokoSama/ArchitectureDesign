package com.example.aop;

import org.apache.commons.logging.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by echo on 17/4/10.
 */
@Component
@Aspect
public class LogAspect {

    private static final Log LOG = LogFactory.getLog(LogAspect.class);

    FileWriter fw;

    @Pointcut("execution(* com.example..*.*(..))")
    public void logPointcut(){}

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{

        if(fw == null){
            fw = new FileWriter("log.txt");
        }

        StringBuilder sb = new StringBuilder();

        long start = System.currentTimeMillis();
        sb.append("start at "+new Date(start)+" "+joinPoint);
        sb.append("\r\n");
        sb.append("args are "+convertObjArr2Str(joinPoint.getArgs())+" "+joinPoint);
        sb.append("\r\n");
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            sb.append("end at "+new Date(end)+" "+joinPoint);
            sb.append("\r\n");
            sb.append("results are "+result+ " "+joinPoint);
            sb.append("\r\n");

            fw.write(sb.toString());
            fw.flush();

            return result;

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            sb.append("end at "+new Date(end)+" "+joinPoint);
            sb.append("\r\n");

            fw.write(sb.toString());
            fw.flush();
            throw e;
        }

    }


    private String convertObjArr2Str(Object[] args){
        String result = "";
        for(int i=0; i<args.length; i++){
            result+=args[i].toString();
            result+=" ";
        }

        return result;
    }
}
