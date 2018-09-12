package com.xx.webframework.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPicSmallIsNull() {
            addCriterion("pic_small is null");
            return (Criteria) this;
        }

        public Criteria andPicSmallIsNotNull() {
            addCriterion("pic_small is not null");
            return (Criteria) this;
        }

        public Criteria andPicSmallEqualTo(String value) {
            addCriterion("pic_small =", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallNotEqualTo(String value) {
            addCriterion("pic_small <>", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallGreaterThan(String value) {
            addCriterion("pic_small >", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallGreaterThanOrEqualTo(String value) {
            addCriterion("pic_small >=", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallLessThan(String value) {
            addCriterion("pic_small <", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallLessThanOrEqualTo(String value) {
            addCriterion("pic_small <=", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallLike(String value) {
            addCriterion("pic_small like", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallNotLike(String value) {
            addCriterion("pic_small not like", value, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallIn(List<String> values) {
            addCriterion("pic_small in", values, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallNotIn(List<String> values) {
            addCriterion("pic_small not in", values, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallBetween(String value1, String value2) {
            addCriterion("pic_small between", value1, value2, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicSmallNotBetween(String value1, String value2) {
            addCriterion("pic_small not between", value1, value2, "picSmall");
            return (Criteria) this;
        }

        public Criteria andPicMiddleIsNull() {
            addCriterion("pic_middle is null");
            return (Criteria) this;
        }

        public Criteria andPicMiddleIsNotNull() {
            addCriterion("pic_middle is not null");
            return (Criteria) this;
        }

        public Criteria andPicMiddleEqualTo(String value) {
            addCriterion("pic_middle =", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleNotEqualTo(String value) {
            addCriterion("pic_middle <>", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleGreaterThan(String value) {
            addCriterion("pic_middle >", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("pic_middle >=", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleLessThan(String value) {
            addCriterion("pic_middle <", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleLessThanOrEqualTo(String value) {
            addCriterion("pic_middle <=", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleLike(String value) {
            addCriterion("pic_middle like", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleNotLike(String value) {
            addCriterion("pic_middle not like", value, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleIn(List<String> values) {
            addCriterion("pic_middle in", values, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleNotIn(List<String> values) {
            addCriterion("pic_middle not in", values, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleBetween(String value1, String value2) {
            addCriterion("pic_middle between", value1, value2, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicMiddleNotBetween(String value1, String value2) {
            addCriterion("pic_middle not between", value1, value2, "picMiddle");
            return (Criteria) this;
        }

        public Criteria andPicLargeIsNull() {
            addCriterion("pic_large is null");
            return (Criteria) this;
        }

        public Criteria andPicLargeIsNotNull() {
            addCriterion("pic_large is not null");
            return (Criteria) this;
        }

        public Criteria andPicLargeEqualTo(String value) {
            addCriterion("pic_large =", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeNotEqualTo(String value) {
            addCriterion("pic_large <>", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeGreaterThan(String value) {
            addCriterion("pic_large >", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeGreaterThanOrEqualTo(String value) {
            addCriterion("pic_large >=", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeLessThan(String value) {
            addCriterion("pic_large <", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeLessThanOrEqualTo(String value) {
            addCriterion("pic_large <=", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeLike(String value) {
            addCriterion("pic_large like", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeNotLike(String value) {
            addCriterion("pic_large not like", value, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeIn(List<String> values) {
            addCriterion("pic_large in", values, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeNotIn(List<String> values) {
            addCriterion("pic_large not in", values, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeBetween(String value1, String value2) {
            addCriterion("pic_large between", value1, value2, "picLarge");
            return (Criteria) this;
        }

        public Criteria andPicLargeNotBetween(String value1, String value2) {
            addCriterion("pic_large not between", value1, value2, "picLarge");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}