package com.example.startdemo.common.constant.enums;

/**
 * @author zhangboqing
 * @date 2019/4/14
 */
public enum RedisKeyEnum {

    ACCESS_LIMIT_KEY("ACCESS_LIMIT_KEY","访问限制") {
        /**
         * @param addedValue 秒值，1，2，...
         * @return
         */
        @Override
        public String addToKey(Object addedValue) {
            return ACCESS_LIMIT_KEY.key + ":"+addedValue;
        }
    };

    private String key;
    private String desc;

    RedisKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    abstract public String addToKey(Object addedValue);
}
