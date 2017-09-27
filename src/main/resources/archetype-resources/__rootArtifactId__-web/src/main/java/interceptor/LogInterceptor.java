#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.interceptor;

import ${package}.entity.AccessLog;
import ${package}.entity.LogProcess;
import ${package}.entity.LogStatus;
import ${package}.service.AccessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Description:
 * On 2017/9/11 14:46 created by LW
 */
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    private static final String USER_ID = "userId";
    private static final String SYSTEM_KEY = "systemKey";
    private Long logId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        AccessLogService logService = getLogService(request);
        if (null != logService) {
            Map<String, String[]> param = request.getParameterMap();
            String userId = null == param.get(USER_ID) ? null : param.get(USER_ID)[0];
            String systemKey = null == param.get(SYSTEM_KEY) ? null : param.get(SYSTEM_KEY)[0];
            logger.info("userId: {}, systemKey: {}", userId, systemKey);
            String url = request.getRequestURI();
            String fromIp = request.getRemoteAddr(); // 有Apache等反向代理时，IP不是客户端IP
            AccessLog log = new AccessLog();
            log.setProcess(LogProcess.BEFORE.name());
            log.setIp(fromIp);
            log.setSystemKey(systemKey);
            log.setStatus(LogStatus.NORMAL.name());
            log.setTargetUrl(url);
            log.setUserId(userId);
            try {
                logId = logService.save(log);
            } catch (Exception e) {
                logger.error("AccessLog save error.", e);
            }
            logger.info("logId is: " + logId);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        /*AccessLogService logService = getLogService(request);
        if (null != logService) {

        }*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        AccessLogService logService = getLogService(request);
        if (null != e) {
            logger.error("request : " + request.getRequestURI() + " ,error:", e);
            if (null != logService && null != logId) {
                AccessLog log = logService.get(logId);
                if (null == log) {
                    logger.warn("cannot find logid: {}, missed log exception to db!", logId);
                    return;
                }
                log.setStatus(LogStatus.EXCEPTION.name());
                log.setProcess(LogProcess.AFTER.name());
                try {
                    logService.save(log);
                } catch (Exception e1) {
                    logger.error("AccessLog save error.", e);
                }
            } else {
                logger.warn("afterCompletion update AccessLog, log id is null!");
            }
        }
        logger.info("afterCompletion done log");
    }

    private AccessLogService getLogService(HttpServletRequest httpServletRequest) {
        Object logService = getBean("accessLogServiceImpl", httpServletRequest);
        return null == logService ? null : (AccessLogService) logService;
    }

    private Object getBean(String beanName, HttpServletRequest httpServletRequest) {
        if (null == httpServletRequest) {
            return null;
        }
        ServletContext sc = httpServletRequest.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (null != wac) {
            return wac.getBean(beanName);
        }
        return null;
    }
}
