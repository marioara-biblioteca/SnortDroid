package com.example.snortdroid.rules.yara;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.snortdroid.rules.snort.SnortRule;

import java.util.List;

@Dao
public interface YaraRuleDAO {
    @Insert
    public void insertYaraRule(YaraRule rule);
    @Query("SELECT * FROM YaraRule")
    List<YaraRule> getAllYaraRules();
    @Delete
    void deleteYaraRule(YaraRule yaraRule);
}
