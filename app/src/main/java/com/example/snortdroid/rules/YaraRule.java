package com.example.snortdroid.rules;

import java.util.*;

public class YaraRule extends Rule{
    private String name;
    private String description;
    private String reference;

    private List<String> strings=new ArrayList<>();
    private List<String> condition=new ArrayList<>();
    public YaraRule(String protocol, String message) {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStrings() {
        return strings;
    }

    public List<String> getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public String getReference() {
        return reference;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void addString(String str){this.strings.add(str);}
    public void addCondition(String cond){this.condition.add(cond);}
}
