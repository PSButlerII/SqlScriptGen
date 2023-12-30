package com.recondev.service;

import com.recondev.interfaces.DatabaseScriptGenerator;

public class ScriptGeneratorFactory {
    public static DatabaseScriptGenerator getScriptGenerator(int dbType) {
        switch (dbType) {
            case 1:
                return new PostgreSQLScriptGenerator();
            case 2:
                return new MySQLScriptGenerator();
            // other cases will go here if i ever get that far
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
    }
}
