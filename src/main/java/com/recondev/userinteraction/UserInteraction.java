package com.recondev.userinteraction;

import com.recondev.helpers.Enums;
import com.recondev.interfaces.DatabaseConstraintType;

import java.util.*;

public class UserInteraction {
    //    private String getConstraintString(int constraintCount) {
//
//        //TODO: Make this method more generic so it can be used for other databases
//        String constraintSQL = null;
//        for (int i = 0; i < constraintCount; i++) {
//            System.out.println("Choose constraint type:");
//
//            // print out the constraint types
//            for (int j = 0; j < Enums.PostgreSQLConstraintType.values().length; j++) {
//                System.out.println(j + ": " + Enums.PostgreSQLConstraintType.values()[j].getConstraintType());
//            }
//
//            int constraintTypeIndex = Integer.parseInt(scanner.nextLine());
//            Enums.PostgreSQLConstraintType constraintType = Enums.PostgreSQLConstraintType.fromInt(constraintTypeIndex);
//
//            System.out.println("Enter column name for constraint:");
//            String columnName = scanner.nextLine();
//
//            String additionalInput = "";
//            if (constraintType.requiresAdditionalInput()) {
//                if (constraintType == Enums.PostgreSQLConstraintType.FOREIGN_KEY) {
//                    System.out.println("Enter table name for the constraint:");
//                    String tableName = scanner.nextLine();
//
//                    System.out.println("Enter column name for the constraint:");
//                    String foreignKeyColumnName = scanner.nextLine();
//
//                    additionalInput = tableName + "(" + foreignKeyColumnName + ")";
//                } else if (constraintType == Enums.PostgreSQLConstraintType.CHECK) {
//                    System.out.println("Enter check condition (e.g., column_name > 5):");
//                    additionalInput = scanner.nextLine();
//                }
//
//                // Assuming other constraint types might need a generic input
//                else if (constraintType == Enums.PostgreSQLConstraintType.UNIQUE ||
//                        constraintType == Enums.PostgreSQLConstraintType.PRIMARY_KEY /*||
//                                constraintType == Enums.PostgreSQLConstraintType.EXCLUSION*/) {
//                    System.out.println("Enter additional input for the constraint:");
//                    additionalInput = scanner.nextLine();
//                }
//                // Handle any other constraints that might need additional input
//                else {
//                    System.out.println("Enter additional input for the constraint:");
//                    additionalInput = scanner.nextLine();
//                }
//            }
//            constraintSQL = constraintType.getConstraintSQL(columnName, additionalInput);
//        }
//        return constraintSQL;
//    }
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

    public List<String> addSQLConstraints(Enums.DatabaseType dbType) {
        List<String> constraints = new ArrayList<>();
        System.out.println("Do you want to add constraints? (yes/no)");
        String addConstraints;
        String constraintSQL;
        try {

            try {
                addConstraints = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please enter yes or no");
                return addSQLConstraints(dbType);
            }

            if (addConstraints.equalsIgnoreCase("yes") || addConstraints.equalsIgnoreCase("y")) {
                System.out.println("Enter number of constraints:");
                int constraintCount = Integer.parseInt(scanner.nextLine());

                //TODO: Make this method more generic so it can be used for other databases.  Maybe pass in the database type?
//                constraintSQL = getConstraint(constraintCount,dbType);
//                constraints.add(constraintSQL);

                for (int i = 0; i < constraintCount; i++) {
                    String constraint = getConstraint(dbType);
                    constraints.add(constraint);
                }
            } else if (addConstraints.equalsIgnoreCase("no") || addConstraints.equalsIgnoreCase("n")) {
                System.out.println("No constraints added");
            } else {
                System.out.println("Please enter yes or no");
                return addSQLConstraints(dbType);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return addSQLConstraints(dbType);
        } catch (IllegalArgumentException e) {
            System.out.println("Input  " + e.getMessage() + " is not valid" + "\nPlease enter a valid input");
            return addSQLConstraints(dbType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return addSQLConstraints(dbType);
        }
        return constraints;
    }

    private String getConstraint(Enums.DatabaseType dbType) {

        //TODO: Make this method more generic so it can be used for other databases
        String constraintSQL = null;
        int constraintTypeIndex;
        String columnName;
        System.out.println("Choose constraint type:");

        constraintTypeIndex = getConstraintValues(dbType);

        //Should this be a method that takes in the database type and returns the appropriate constraint types?
        DatabaseConstraintType constraintType = getSelectedConstraintType(dbType, constraintTypeIndex);

        System.out.println("Enter column name for constraint:");
        columnName = scanner.nextLine();

        String additionalInput = "";
        if (constraintType.requiresAdditionalInput()) {
            if (constraintType == Enums.PostgreSQLConstraintType.FOREIGN_KEY) {
                System.out.println("Enter table name for the constraint:");
                String tableName = scanner.nextLine();

                    System.out.println("Enter column name for the constraint:");
                    String foreignKeyColumnName = scanner.nextLine();

                    additionalInput = tableName + "(" + foreignKeyColumnName + ")";
                } else if (constraintType == Enums.PostgreSQLConstraintType.CHECK) {
                    System.out.println("Enter check condition (e.g., column_name > 5):");
                    additionalInput = scanner.nextLine();
                }

                // Assuming other constraint types might need a generic input
                else if (constraintType == Enums.PostgreSQLConstraintType.UNIQUE ||
                        constraintType == Enums.PostgreSQLConstraintType.PRIMARY_KEY /*||
                                constraintType == Enums.PostgreSQLConstraintType.EXCLUSION*/) {
                    System.out.println("Enter additional input for the constraint:");
                    additionalInput = scanner.nextLine();
                }
                // Handle any other constraints that might need additional input
            else {
                System.out.println("Enter additional input for the constraint:");
                additionalInput = scanner.nextLine();
            }
        }
        constraintSQL = constraintType.getConstraintSQL(columnName, additionalInput);


        return constraintSQL;
    }

    public int getConstraintValues(Enums.DatabaseType dbType) {
        // Use the dbtype to print out the appropriate constraint types
        int constraintTypeIndex = 0;
        switch (dbType) {
            case POSTGRESQL:
                for (int j = 0; j < Enums.PostgreSQLConstraintType.values().length; j++) {
                    System.out.println(j + ": " + Enums.PostgreSQLConstraintType.values()[j].getConstraintType());
                }
                constraintTypeIndex = Integer.parseInt(scanner.nextLine());
                return constraintTypeIndex;
            case MYSQL:
                for (int j = 0; j < Enums.MySQLConstraintType.values().length; j++) {
                    System.out.println(j + ": " + Enums.MySQLConstraintType.values()[j].getConstraintType());
                }
                constraintTypeIndex = Integer.parseInt(scanner.nextLine());
                return constraintTypeIndex;
//                case MONGODB:
//                    for (int j = 0; j < Enums.MongoDBConstraintType.values().length; j++) {
//                        System.out.println(j + ": " + Enums.MongoDBConstraintType.values()[j].getConstraintType());
//                    }
//                    break;
        }
        return constraintTypeIndex;
    }

    private DatabaseConstraintType getSelectedConstraintType(Enums.DatabaseType dbType, int constraintTypeIndex) {
        switch (dbType) {
            case POSTGRESQL:
                return Enums.PostgreSQLConstraintType.fromInt(constraintTypeIndex);
            case MYSQL:
                return Enums.MySQLConstraintType.fromInt(constraintTypeIndex);
            // Add cases for other databases
            default:
                throw new IllegalArgumentException("Unsupported database type");
        }
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
