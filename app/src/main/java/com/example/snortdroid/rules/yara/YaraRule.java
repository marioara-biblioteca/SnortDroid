package com.example.snortdroid.rules.yara;

import com.example.snortdroid.rules.Rule;

import java.util.*;

public class YaraRule extends Rule {
    private String description,author,reference,hash;
    int score;
    private String date;

    private List<YaraStrings> strings=new ArrayList<>();
    private List<String> condition=new ArrayList<>();
    public YaraRule() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<YaraStrings> getStrings() {
        return strings;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void addString(YaraStrings  str){this.strings.add(str);}
    public void addCondition(String cond){this.condition.add(cond);}

    @Override
    public String toString() {
        return "YaraRule{" +
                "description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", reference='" + reference + '\'' +
                ", hash='" + hash + '\'' +
                ", score=" + score +
                ", date='" + date + '\'' +
                ", strings=" + strings +
                ", condition=" + condition +
                '}';
    }
}
