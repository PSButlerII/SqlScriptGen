# SqlScriptGen

SqlScriptGen is a Java-based application designed to simplify the creation of SQL scripts through user interaction. It
supports generating scripts for creating tables in SQL databases. This tool is particularly useful for developers and
database administrators who need to quickly generate SQL scripts without manually writing the SQL code.

## Features

- Interactive command-line interface.
- Support for creating `CREATE TABLE` scripts.
- Customizable columns and data types.
- Option to add table constraints.

## Requirements

- Java 11 or higher.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/SqlScriptGen.git
2. ```sh 
   $ java -jar SqlScriptGen.jar

## Example

```sh 
    Enter database type: PostgreSQL
 
   Enter table name: my_table

   Enter number of columns: 3

   Enter column name for column 1: id
   
   Enter data type for column id: int
   

   Enter column name for column 2: name

   Enter data type for column name: varchar(255)

   Enter column name for column 3: age

   Enter data type for column age: int

   Do you want to add constraints? (yes/no): no

   RETURNS::
   
       CREATE TABLE my_table (
            id int,
            name varchar(255),
            age int
       );

```

## Future Work

- Support for other SQL commands.
- Support for other database types.
- Support for other data types.
- Add options for constraints.
- Support for adding comments.
- Support for adding indexes.
- Support for adding triggers.
- Support for adding views.
- Support for adding stored procedures.
- Support for adding functions.
- Support for adding users.
- Support for adding roles.
- Support for adding permissions.
- Support for adding databases.
- Support for adding schemas.
- Add GUI or web interface.
