#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Description:
 * On 2017/9/22 12:51 created by LW
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "common.properties")
public class Config {
    private int retryTimes;

    @DisconfFileItem(name = "ws.retryTimes", associateField = "retryTimes")
    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }
}
