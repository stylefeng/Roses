package com.stylefeng.roses.api.order.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
@TableName("goods_order")
public class GoodsOrder extends Model<GoodsOrder> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 商品名称
     */
    @TableField("goods_name")
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
    @TableField("create_time")
    private Date createTime;
    /**
     * 下单人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 订单状态：1.未完成   2.已完成
     */
    @TableField("status")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GoodsOrder{" +
                "id=" + id +
                ", goodsName=" + goodsName +
                ", count=" + count +
                ", sum=" + sum +
                ", createTime=" + createTime +
                ", userId=" + userId +
                "}";
    }
}
