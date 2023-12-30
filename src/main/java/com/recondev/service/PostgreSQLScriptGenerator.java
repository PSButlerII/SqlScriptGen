package com.recondev.service;

import java.util.List;
import java.util.Map;

public class PostgreSQLScriptGenerator implements DatabaseScriptGenerator {
    @Override
    public String generateCreateTableScript(String tableName, Map<String, String> columns, List<String> constraints) {
        String script = "CREATE TABLE " + tableName + " (";

        // Adding columns to the script
        for (Map.Entry<String, String> column : columns.entrySet()) {
            script += column.getKey() + " " + column.getValue() + ", ";
        }

        // Adding constraints
        if (constraints != null) {
            for (String constraint : constraints) {
                script += constraint + ", ";
            }
        }

        // Remove the last comma and space
        if (script.endsWith(", ")) {
            script = script.substring(0, script.length() - 2);
        }
        script += ");";

        return script;
    }

    @Override
    public String generateCreateDatabaseScript(String databaseName) {
        return "CREATE DATABASE " + databaseName + ";";
    }

    // TODO: Add generateCreateSchemaScript
    // TODO: Add generateCreateViewScript
    // TODO: Add generateCreateFunctionScript
    // TODO: Add generateCreateTriggerScript
    // TODO: Add generateCreateIndexScript
    // TODO: Add generateCreateSequenceScript
    // TODO: Add generateCreateUserScript
    // TODO: Add generateCreateRoleScript


}
