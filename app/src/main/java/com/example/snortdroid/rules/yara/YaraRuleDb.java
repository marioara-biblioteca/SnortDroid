package com.example.snortdroid.rules.yara;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {YaraRule.class},version = 1)
public abstract class YaraRuleDb extends RoomDatabase {
    public abstract YaraRuleDAO yaraRuleDAO();
}
