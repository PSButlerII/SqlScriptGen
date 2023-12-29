package com.recondev.userinteraction;

import com.recondev.helpers.Enums;

import java.util.*;

public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    public Enums.DatabaseType getDatabaseType() {
        System.out.println("Enter database type\n (1)PostgreSQL\n (2)MySQL\n (3)MongoDB)[**Currently only PostgreSQL and MySQL are supported**]");
        int dbType;
        try {
            try {
                dbType = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
                return getDatabaseType();
            }
            //get a map of the database types and their corresponding numbers
            Map<Integer, String> dbTypes = new HashMap<>();
            int index = 1;
            for (Enums.DatabaseType type : Enums.DatabaseType.values()) {
                dbTypes.put(index, String.valueOf(type));
                index++;
            }
            //if the user entered a valid number, return the corresponding database type
            if (dbTypes.containsKey(dbType)) {
                return Enums.DatabaseType.valueOf(dbTypes.get(dbType));
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return getDatabaseType();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDatabaseType();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getDatabaseType();
        }
        return null;
    }

    public String getTableName() {
        System.out.println("Enter table name:");
        return scanner.nextLine();
    }

    public Map<String, String> getColumns(Enums.DatabaseType dbType) {
        System.out.println("Enter number of columns:");
        int columnCount;
        Map<String, String> columns;
        try {
            try {
                columnCount = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
                return getColumns(dbType);
            }
            columns = new HashMap<>();

            for (int i = 0; i < columnCount; i++) {
                System.out.println("Enter column name for column " + (i + 1) + ":");
                String columnName = scanner.nextLine();
                System.out.println("Enter data type for column " + columnName + ":");
                String dataType = promptForDataType(dbType);
                columns.put(columnName, dataType);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return getColumns(dbType);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getColumns(dbType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getColumns(dbType);
        }
        return columns;
    }

    public List<String> getConstraints() {
        List<String> constraints = new ArrayList<>();
        System.out.println("Do you want to add constraints? (yes/no)");
        String addConstraints;
        try {
            try {
                addConstraints = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please enter yes or no");
                return getConstraints();
            }

            if (addConstraints.equalsIgnoreCase("yes") || addConstraints.equalsIgnoreCase("y")) {
                System.out.println("Enter number of constraints:");
                int constraintCount = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < constraintCount; i++) {
                    System.out.println("Enter constraint " + (i + 1) + ":");
                    constraints.add(scanner.nextLine());
                }
            } else if (addConstraints.equalsIgnoreCase("no") || addConstraints.equalsIgnoreCase("n")) {
                System.out.println("No constraints added");
            } else {
                System.out.println("Please enter yes or no");
                return getConstraints();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return getConstraints();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getConstraints();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getConstraints();
        }
        return constraints;
    }

    private String promptForDataType(Enums.DatabaseType dbType) {
        List<String> dataTypes = getDataTypesForDatabase(dbType);
        for (int i = 0; i < dataTypes.size(); i++) {
            System.out.println(i + ": " + dataTypes.get(i));
        }
        System.out.println("Select the data type by number:");
        int dataTypeIndex = scanner.nextInt();
        scanner.nextLine();
        return dataTypes.get(dataTypeIndex);
    }

    private List<String> getDataTypesForDatabase(Enums.DatabaseType dbType) {
        List<String> dataTypes = new ArrayList<>();
        switch (dbType) {
            case POSTGRESQL:
                for (Enums.PostgreSQLDataType type : Enums.PostgreSQLDataType.values()) {
                    dataTypes.add(type.getType());
                }
                break;
            case MYSQL:
                for (Enums.MySQLDataType type : Enums.MySQLDataType.values()) {
                    dataTypes.add(type.getType());
                }
                break;
            case MONGODB:
                for (Enums.MongoDBDataType type : Enums.MongoDBDataType.values()) {
                    dataTypes.add(type.getType());
                }
                break;
        }
        return dataTypes;
    }
}
