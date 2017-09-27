#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.service.MsgService;
import ${package}.service.TbbOrderStatusService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Description:
 * On 2017/9/11 11:16 created by LW
 */
@Service
public class MsgServiceImpl implements MsgService {

    private static final Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MsgServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public <T> boolean send(String destination, T t, String user) {
        logger.info("begin send. user:" + user);
        try {
            if (StringUtils.isBlank(user)) {
                messagingTemplate.convertAndSend(destination, t);
                return true;
            } else {
                messagingTemplate.convertAndSendToUser(user, destination, t);
                return true;
            }
        } catch (MessagingException e) {
            logger.error("send websocket msg error.", e);
        }
        return false;
    }
}
