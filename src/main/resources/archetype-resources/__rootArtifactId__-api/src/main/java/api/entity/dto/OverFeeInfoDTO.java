#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

/**
 * Description: 溢价信息
 * On 2017/9/11 17:07 created by LW
 */
public class OverFeeInfoDTO {
    private Double peekOverFee;
    private Double totalOverFee;
    private Double weatherOverFee;

    public OverFeeInfoDTO() {
    }

    public Double getPeekOverFee() {
        return peekOverFee;
    }

    public void setPeekOverFee(Double peekOverFee) {
        this.peekOverFee = peekOverFee;
    }

    public Double getTotalOverFee() {
        return totalOverFee;
    }

    public void setTotalOverFee(Double totalOverFee) {
        this.totalOverFee = totalOverFee;
    }

    public Double getWeatherOverFee() {
        return weatherOverFee;
    }

    public void setWeatherOverFee(Double weatherOverFee) {
        this.weatherOverFee = weatherOverFee;
    }
}
