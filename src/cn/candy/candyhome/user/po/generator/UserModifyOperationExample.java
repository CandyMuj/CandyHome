package cn.candy.candyhome.user.po.generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserModifyOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserModifyOperationExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeIsNull() {
            addCriterion("modify_before is null");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeIsNotNull() {
            addCriterion("modify_before is not null");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeEqualTo(String value) {
            addCriterion("modify_before =", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeNotEqualTo(String value) {
            addCriterion("modify_before <>", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeGreaterThan(String value) {
            addCriterion("modify_before >", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeGreaterThanOrEqualTo(String value) {
            addCriterion("modify_before >=", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeLessThan(String value) {
            addCriterion("modify_before <", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeLessThanOrEqualTo(String value) {
            addCriterion("modify_before <=", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeLike(String value) {
            addCriterion("modify_before like", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeNotLike(String value) {
            addCriterion("modify_before not like", value, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeIn(List<String> values) {
            addCriterion("modify_before in", values, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeNotIn(List<String> values) {
            addCriterion("modify_before not in", values, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeBetween(String value1, String value2) {
            addCriterion("modify_before between", value1, value2, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyBeforeNotBetween(String value1, String value2) {
            addCriterion("modify_before not between", value1, value2, "modifyBefore");
            return (Criteria) this;
        }

        public Criteria andModifyAfterIsNull() {
            addCriterion("modify_after is null");
            return (Criteria) this;
        }

        public Criteria andModifyAfterIsNotNull() {
            addCriterion("modify_after is not null");
            return (Criteria) this;
        }

        public Criteria andModifyAfterEqualTo(String value) {
            addCriterion("modify_after =", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterNotEqualTo(String value) {
            addCriterion("modify_after <>", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterGreaterThan(String value) {
            addCriterion("modify_after >", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterGreaterThanOrEqualTo(String value) {
            addCriterion("modify_after >=", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterLessThan(String value) {
            addCriterion("modify_after <", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterLessThanOrEqualTo(String value) {
            addCriterion("modify_after <=", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterLike(String value) {
            addCriterion("modify_after like", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterNotLike(String value) {
            addCriterion("modify_after not like", value, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterIn(List<String> values) {
            addCriterion("modify_after in", values, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterNotIn(List<String> values) {
            addCriterion("modify_after not in", values, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterBetween(String value1, String value2) {
            addCriterion("modify_after between", value1, value2, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyAfterNotBetween(String value1, String value2) {
            addCriterion("modify_after not between", value1, value2, "modifyAfter");
            return (Criteria) this;
        }

        public Criteria andModifyTypeIsNull() {
            addCriterion("modify_type is null");
            return (Criteria) this;
        }

        public Criteria andModifyTypeIsNotNull() {
            addCriterion("modify_type is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTypeEqualTo(String value) {
            addCriterion("modify_type =", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeNotEqualTo(String value) {
            addCriterion("modify_type <>", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeGreaterThan(String value) {
            addCriterion("modify_type >", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("modify_type >=", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeLessThan(String value) {
            addCriterion("modify_type <", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeLessThanOrEqualTo(String value) {
            addCriterion("modify_type <=", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeLike(String value) {
            addCriterion("modify_type like", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeNotLike(String value) {
            addCriterion("modify_type not like", value, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeIn(List<String> values) {
            addCriterion("modify_type in", values, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeNotIn(List<String> values) {
            addCriterion("modify_type not in", values, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeBetween(String value1, String value2) {
            addCriterion("modify_type between", value1, value2, "modifyType");
            return (Criteria) this;
        }

        public Criteria andModifyTypeNotBetween(String value1, String value2) {
            addCriterion("modify_type not between", value1, value2, "modifyType");
            return (Criteria) this;
        }
    }

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