#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.entity;

import java.io.Serializable;

/**
 * Description:
 * On 2017/9/4 15:13 created by LW
 */
public class ClientResp<T> implements Serializable {

    private String resultCode;
    private String resultDesc;
    private T resultData;

    public ClientResp() {

    }
    public ClientResp(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.resultData = null;
    }

    public ClientResp(T resultData) {
        this.resultCode = EnumRespCode.SUCCESS.getValue();
        this.resultDesc = EnumRespCode.SUCCESS.getDesc();
        this.resultData = resultData;
    }

    public String getResultCode() {
        return resultCode;
    }
    public String getResultDesc() {
        return resultDesc;
    }
    public T getResultData() {
        return resultData;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

}
