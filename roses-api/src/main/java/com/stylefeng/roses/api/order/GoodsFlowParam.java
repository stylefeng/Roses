package com.stylefeng.roses.api.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单流水记录的参数(本类同 GoodsOrder)
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
public class GoodsFlowParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 总金额
     */
    private BigDecimal sum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 下单人id
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GoodsFlowParam{" +
                "id=" + id +
                ", goodsName=" + goodsName +
                ", count=" + count +
                ", sum=" + sum +
                ", createTime=" + createTime +
                ", userId=" + userId +
                "}";
    }
}
