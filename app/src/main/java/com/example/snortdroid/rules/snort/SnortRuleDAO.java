package com.example.snortdroid.rules.snort;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.example.snortdroid.rules.yara.YaraRule;

@Dao
public interface SnortRuleDAO {
    @Insert
    void insertSnortRule(SnortRule rule);
    @Delete
    void deleteSnortRule(SnortRule snortRule);
}
