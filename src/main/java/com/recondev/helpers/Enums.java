package com.recondev.helpers;

import com.recondev.interfaces.DatabaseConstraintType;

public class Enums {

    public enum DatabaseType {
        POSTGRESQL("PostgreSQL"),
        MYSQL("MySQL"),
        MONGODB("MongoDB");

        private final String type;

        DatabaseType(String type) {
            this.type = type;
        }

        public static DatabaseType fromInt(int index) {
            return DatabaseType.values()[index];
        }

        public String getType() {
            return type;
        }

        public int getValue() {
            return this.ordinal() + 1;
        }
    }

    public enum PostgreSQLDataType {
        SMALLINT("smallint"),
        INTEGER("integer"),
        BIGINT("bigint"),
        DECIMAL("decimal"),
        NUMERIC("numeric"),
        REAL("real"),
        DOUBLE_PRECISION("double precision"),
        SERIAL("serial"),
        BIGSERIAL("bigserial"),
        MONEY("money"),
        VARCHAR("varchar"),
        CHAR("char"),
        TEXT("text"),
        BYTEA("bytea"),
        TIMESTAMP("timestamp"),
        DATE("date"),
        TIME("time"),
        INTERVAL("interval"),
        BOOLEAN("boolean"),
        BIT("bit"),
        VARBIT("varbit"),
        UUID("uuid"),
        XML("xml"),
        JSON("json"),
        JSONB("jsonb");

        private final String type;

        PostgreSQLDataType(String type) {
            this.type = type;
        }

        public static PostgreSQLDataType fromInt(int index) {
            return PostgreSQLDataType.values()[index];
        }

        public String getType() {
            return type;
        }
    }

    public enum PostgreSQLComplexDataType {
        POINT("point"),
        LINE("line"),
        LSEG("lseg"),
        BOX("box"),
        PATH("path"),
        POLYGON("polygon"),
        CIRCLE("circle"),
        CIDR("cidr"),
        INET("inet"),
        MACADDR("macaddr"),
        ARRAY("array"); // Note: Array is a more complex type

        private final String complexType;

        PostgreSQLComplexDataType(String complexType) {
            this.complexType = complexType;
        }

        public static PostgreSQLComplexDataType fromInt(int index) {
            return PostgreSQLComplexDataType.values()[index];
        }

        public String getComplexType() {
            return complexType;
        }
    }

    public enum MySQLDataType {
        TINYINT("TINYINT"),
        SMALLINT("SMALLINT"),
        MEDIUMINT("MEDIUMINT"),
        INT("INT"),
        BIGINT("BIGINT"),
        DECIMAL("DECIMAL"),
        FLOAT("FLOAT"),
        DOUBLE("DOUBLE"),
        BIT("BIT"),
        CHAR("CHAR"),
        VARCHAR("VARCHAR"),
        TINYTEXT("TINYTEXT"),
        TEXT("TEXT"),
        MEDIUMTEXT("MEDIUMTEXT"),
        LONGTEXT("LONGTEXT"),
        DATE("DATE"),
        DATETIME("DATETIME"),
        TIMESTAMP("TIMESTAMP"),
        TIME("TIME"),
        YEAR("YEAR"),
        BINARY("BINARY"),
        VARBINARY("VARBINARY"),
        TINYBLOB("TINYBLOB"),
        BLOB("BLOB"),
        MEDIUMBLOB("MEDIUMBLOB"),
        LONGBLOB("LONGBLOB"),
        ENUM("ENUM"),
        SET("SET");

        private final String type;

        MySQLDataType(String type) {
            this.type = type;
        }

        public static MySQLDataType fromInt(int index) {
            return MySQLDataType.values()[index];
        }

        public String getType() {
            return type;
        }
    }

    public enum MongoDBDataType {
        DOUBLE("Double"),
        STRING("String"),
        BINARY_DATA("Binary data"),
        OBJECT_ID("ObjectId"),
        BOOLEAN("Boolean"),
        DATE("Date"),
        NULL("Null"),
        JAVASCRIPT("JavaScript"),
        INT("32-bit integer"),
        TIMESTAMP("Timestamp"),
        LONG("64-bit integer"),
        DECIMAL128("Decimal128");

        private final String type;

        MongoDBDataType(String type) {
            this.type = type;
        }

        public static MongoDBDataType fromInt(int index) {
            return MongoDBDataType.values()[index];
        }

        public String getType() {
            return type;
        }
    }

    public enum MongoDBComplexDataType {
        OBJECT("Object"), // Object or embedded document
        ARRAY("Array"),   // Array type
        JAVASCRIPT_WITH_SCOPE("JavaScript (with scope)"), // JavaScript code with scope
        DB_POINTER("DBPointer (Deprecated)"), // Reference to another document
        REGEX("Regular Expression"); // Regular expression type

        private final String complexType;

        MongoDBComplexDataType(String complexType) {
            this.complexType = complexType;
        }

        public static MongoDBComplexDataType fromInt(int index) {
            return MongoDBComplexDataType.values()[index];
        }

        public String getComplexType() {
            return complexType;
        }
    }

    // TODO: Add PostgreSQLConstraintType
    public enum PostgreSQLConstraintType implements DatabaseConstraintType {
        PRIMARY_KEY("PRIMARY KEY"),
        UNIQUE("UNIQUE"),
        CHECK("CHECK"),
        FOREIGN_KEY("FOREIGN KEY"),
        EXCLUSION("EXCLUSION");

        private final String constraintType;
        private boolean requiresAdditionalInput;

        PostgreSQLConstraintType(String constraintType) {
            this.constraintType = constraintType;
        }

        public static PostgreSQLConstraintType fromInt(int index) {
            return PostgreSQLConstraintType.values()[index];
        }

        public static PostgreSQLConstraintType fromString(String constraintType) {
            for (PostgreSQLConstraintType type : PostgreSQLConstraintType.values()) {
                if (type.getConstraintType().equals(constraintType)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid constraint type");
        }

        public String getConstraintType() {
            return constraintType;
        }

        public boolean requiresAdditionalInput() {
            if (this == CHECK || this == FOREIGN_KEY || this == fromString("CHECK") || this == fromString("FOREIGN KEY")) {
                return this.requiresAdditionalInput = true;
            }
            return this.requiresAdditionalInput;
        }

        public String getConstraintSQL(String columnName, String additionalInput) {
            switch (this) {
                case PRIMARY_KEY:
                    return "CONSTRAINT " + columnName + "_pk " + this.getConstraintType() + " (" + columnName + ")";
                case UNIQUE:
                    return "CONSTRAINT " + columnName + "_unique " + this.getConstraintType() + " (" + columnName + ")";
                case CHECK:
                    return "CONSTRAINT " + columnName + "_check " + this.getConstraintType() + " (" + columnName + " " + additionalInput + ")";
                case FOREIGN_KEY:
                    return "CONSTRAINT " + columnName + "_fk " + this.getConstraintType() + " (" + columnName + ") REFERENCES " + "(" + additionalInput + ")";
//                case EXCLUSION:
//                    return "CONSTRAINT " + columnName + "_exclusion " + this.getConstraintType() + " USING gist (" + columnName + " WITH =)";
                default:
                    throw new IllegalArgumentException("Invalid constraint type");
            }
        }
    }

    // TODO: Add MySQLConstraintType
    public enum MySQLConstraintType implements DatabaseConstraintType {
        PRIMARY_KEY("PRIMARY KEY"),
        UNIQUE("UNIQUE"),
        FOREIGN_KEY("FOREIGN KEY"),
        CHECK("CHECK");

        private final String constraintType;

        MySQLConstraintType(String constraintType) {
            this.constraintType = constraintType;
        }

        public static MySQLConstraintType fromInt(int index) {
            return MySQLConstraintType.values()[index];
        }

        public String getConstraintType() {
            return constraintType;
        }

        /**
         * @return
         */
        @Override
        public boolean requiresAdditionalInput() {
            return false;
        }

        /**
         * @param columnName
         * @param additionalInput
         * @return
         */
        @Override
        public String getConstraintSQL(String columnName, String additionalInput) {
            return null;
        }
    }

    // TODO: Add MongoDBConstraintType
}
