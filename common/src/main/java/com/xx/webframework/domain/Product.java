package com.xx.webframework.domain;

import java.io.Serializable;

/**
 * product
 * @author 
 */
public class Product implements Serializable {
    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片-小
     */
    private String picSmall;

    /**
     * 商品图片-中
     */
    private String picMiddle;

    /**
     * 商品图片-大
     */
    private String picLarge;

    /**
     * 商品描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicMiddle() {
        return picMiddle;
    }

    public void setPicMiddle(String picMiddle) {
        this.picMiddle = picMiddle;
    }

    public String getPicLarge() {
        return picLarge;
    }

    public void setPicLarge(String picLarge) {
        this.picLarge = picLarge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPicSmall() == null ? other.getPicSmall() == null : this.getPicSmall().equals(other.getPicSmall()))
            && (this.getPicMiddle() == null ? other.getPicMiddle() == null : this.getPicMiddle().equals(other.getPicMiddle()))
            && (this.getPicLarge() == null ? other.getPicLarge() == null : this.getPicLarge().equals(other.getPicLarge()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPicSmall() == null) ? 0 : getPicSmall().hashCode());
        result = prime * result + ((getPicMiddle() == null) ? 0 : getPicMiddle().hashCode());
        result = prime * result + ((getPicLarge() == null) ? 0 : getPicLarge().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", picSmall=").append(picSmall);
        sb.append(", picMiddle=").append(picMiddle);
        sb.append(", picLarge=").append(picLarge);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}