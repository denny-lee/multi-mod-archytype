#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.enums;

/**
 * Description:
 * On 2017/9/4 15:15 created by LW
 */
public enum EnumRespCode {

    SUCCESS("0", "操作成功"),
    FAIL("1", "操作失败"),
    USERID_NULL("1", "商家ID不能为空"),
    PARAMS_ERROR("1", "参数错误"),
    REPREAT_REQUEST("1", "请勿重复提交"),
    UNSUPORTTED("-1", "此功能暂不开放");

    private String value;
    private String desc;

    private EnumRespCode(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
