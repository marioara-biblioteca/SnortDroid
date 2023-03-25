package com.example.snortdroid.rules.yara;

public class YaraStrings {
    private String paramName;
    private String paramValue;
    private String paramType;


    public YaraStrings(String paramName, String paramValue, String paramType) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "YaraStrings{" +
                "paramName='" + paramName + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", paramType='" + paramType + '\'' +
                '}';
    }
}
