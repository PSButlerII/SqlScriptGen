package com.recondev.interfaces;

import java.util.List;
import java.util.Map;

public interface DatabaseScriptGenerator {
    String generateCreateTableScript(String table, Map<String, String> columns, List<String> constraints);

    String generateCreateDatabaseScript(String name);
}
