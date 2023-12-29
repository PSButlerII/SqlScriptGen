package com.recondev;

import com.recondev.helpers.Enums;
import com.recondev.service.DatabaseScriptGenerator;
import com.recondev.service.ScriptGeneratorFactory;
import com.recondev.userinteraction.UserInteraction;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserInteraction ui = new UserInteraction();

        Enums.DatabaseType dbType = ui.getDatabaseType();
        DatabaseScriptGenerator scriptGenerator = ScriptGeneratorFactory.getScriptGenerator(dbType.getValue());

        String tableName = ui.getTableName();
        Map<String, String> columns = ui.getColumns(dbType);
        List<String> constraints = ui.getConstraints();

        String createTableScript = scriptGenerator.generateCreateTableScript(tableName, columns, constraints);

        System.out.println("Create Table Script:");
        System.out.println(createTableScript);
    }
}
