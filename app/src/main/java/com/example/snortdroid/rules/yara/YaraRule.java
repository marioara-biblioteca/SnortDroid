package com.example.snortdroid.rules.yara;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.snortdroid.rules.Rule;

import java.util.*;

@Entity
public class YaraRule extends Rule {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description,author,reference,hash;
    int score;
    private String date;
    @TypeConverters(YaraStringsConverter.class)
    @ColumnInfo(name="strings")
    private  List<YaraStrings> strings=new ArrayList<>();



    // private List<String> condition=new ArrayList<>();
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

    public void setStrings(List<YaraStrings> strings) {
        this.strings = strings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                //", condition=" + condition +
                '}';
    }
    public void addString(YaraStrings yaraString){
        if(!strings.contains(yaraString))
            strings.add(yaraString);
    }
}
