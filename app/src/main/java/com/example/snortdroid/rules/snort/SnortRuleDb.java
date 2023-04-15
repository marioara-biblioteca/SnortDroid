package com.example.snortdroid.rules.snort;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {SnortRule.class},version = 1)
public abstract class SnortRuleDb extends RoomDatabase {
    public  abstract SnortRuleDAO snortRuleDAO();
}
