#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity;

import ${package}.api.enums.EnumRespCode;

/**
 * Description:
 * On 2017/9/12 16:31 created by LW
 */
public class RespWrapper<T> {
    private String resultCode;
    private String resultDesc;
    private T resultData;

    public RespWrapper(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.resultData = null;
    }

    public RespWrapper(T resultData) {
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
