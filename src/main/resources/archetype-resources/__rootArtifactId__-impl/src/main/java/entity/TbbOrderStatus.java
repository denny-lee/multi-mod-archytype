#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * On 2017/9/15 9:55 created by LW
 */
@Entity
@Table(name = "tbb_launcher_order_status")
public class TbbOrderStatus {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private String userId;
    private String orderNo;
    private String status;
    private Long timestamp; // 状态改变的时间
    private String riderId;
    private String riderName;
    private String riderPhone;
    private Boolean pushed;
    private Integer retryTimes; // 重试次数
    @Column(name="create_gmt", nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createGmt;
    @Column(name="modify_gmt", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modifyGmt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getPushed() {
        return pushed;
    }

    public void setPushed(Boolean pushed) {
        this.pushed = pushed;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Date getCreateGmt() {
        return createGmt;
    }

    public void setCreateGmt(Date createGmt) {
        this.createGmt = createGmt;
    }

    public Date getModifyGmt() {
        return modifyGmt;
    }

    public void setModifyGmt(Date modifyGmt) {
        this.modifyGmt = modifyGmt;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }
}
