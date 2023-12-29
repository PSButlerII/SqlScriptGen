package com.recondev.service;

import java.util.List;
import java.util.Map;

public class MySQLScriptGenerator implements DatabaseScriptGenerator {
    /**
     * @return
     */
    @Override
    public String generateCreateTableScript(String tableName, Map<String, String> columns, List<String> constraints) {
        StringBuilder script = new StringBuilder("CREATE TABLE " + tableName + " (");

        // Adding columns to the script.  First time using string builder so I'm not sure if this is the best way to do it.
        StringBuilder finalScript = script;
        columns.forEach((columnName, columnType) -> {
            finalScript.append(columnName).append(" ").append(columnType).append(", ");
        });

        // Adding constraints. I'm not sure if this is the right way to do it.
        if (constraints != null) {
            for (String constraint : constraints) {
                script.append(constraint).append(", ");
            }
        }

        // Remove the last comma and space
        script = new StringBuilder(script.substring(0, script.length() - 2));
        script.append(");");

        return script.toString();
    }

    /**
     * @return
     */
    @Override
    public String generateCreateDatabaseScript(String databaseName) {
        return "CREATE DATABASE " + databaseName + ";";
    }
}
