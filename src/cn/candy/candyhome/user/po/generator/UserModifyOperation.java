package cn.candy.candyhome.user.po.generator;

import java.util.Date;

public class UserModifyOperation {
    private String uid;

    private Date modifyTime;

    private String modifyBefore;

    private String modifyAfter;

    private String modifyType;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBefore() {
        return modifyBefore;
    }

    public void setModifyBefore(String modifyBefore) {
        this.modifyBefore = modifyBefore == null ? null : modifyBefore.trim();
    }

    public String getModifyAfter() {
        return modifyAfter;
    }

    public void setModifyAfter(String modifyAfter) {
        this.modifyAfter = modifyAfter == null ? null : modifyAfter.trim();
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType == null ? null : modifyType.trim();
    }
}