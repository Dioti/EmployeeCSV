import com.sparta.employeecsv.controller.EmployeeParser;
import com.sparta.employeecsv.model.Employee;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class App_old {
    public static void main(String[] args) {
        ArrayList<Employee> validEmployees = new ArrayList<Employee>();
        ArrayList<Employee> invalidEmployees = new ArrayList<Employee>();
        HashSet<Integer> uniqueID = new HashSet<Integer>();
        HashSet<Integer> duplicateID = new HashSet<Integer>();
        String line = null;
        int currLine = 2;
        // try-with-resources
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/EmployeeRecords.csv"))) {
            in.readLine(); // pass over first line
            long start = System.currentTimeMillis();
            while ((line = in.readLine()) != null) {
                try {
                    Employee e = EmployeeParser.getAsEmployee(line);
                    int id = e.getId();
                    if(!uniqueID.add(id)) {
                        duplicateID.add(id);
                    } else {
                        uniqueID.add(id);
                        validEmployees.add(e);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to add employee on line " + currLine);
                    //e.printStackTrace();
                }
                currLine++;
            }
            long stop = System.currentTimeMillis();
            System.out.println("Total entries: " + (currLine - 2));
            System.out.println("Valid entries: " + validEmployees.size());
            System.out.println("Number of duplicates: " + duplicateID.size());
            System.out.println("Time taken to populate Java collections: " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Employee");
            statement.executeUpdate("CREATE TABLE Employee (" +
                    "id INTEGER NOT NULL PRIMARY KEY, " + // better to avoid declaring it as AUTOINCREMENT
                    "title VARCHAR(6), " +
                    "forename VARCHAR(50), " +
                    "middlename VARCHAR(50), " +
                    "surname VARCHAR(50), " +
                    "gender CHAR(5), " +
                    "email VARCHAR(50), " +
                    "birth_date DATE, " +
                    "join_date DATE, " +
                    "salary INTEGER)");

            connection.setAutoCommit(false);
            String sql = "INSERT INTO Employee (id, title, forename, middlename, surname, gender, email, birth_date, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            long start = System.currentTimeMillis();
            for(Employee e : validEmployees) {
                preparedStatement.setInt(1, e.getId());
                preparedStatement.setString(2, e.getTitle());
                preparedStatement.setString(3, e.getForename());
                preparedStatement.setString(4, e.getMiddlename());
                preparedStatement.setString(5, e.getSurname());
                preparedStatement.setString(6, String.valueOf(e.getGender()));
                preparedStatement.setString(7, e.getEmail());
                preparedStatement.setDate(8, e.getDob());
                preparedStatement.setDate(9, e.getJoinDate());
                preparedStatement.setInt(10, e.getSalary());
                preparedStatement.execute();
                //preparedStatement.addBatch();
            }
            long stop = System.currentTimeMillis();
            preparedStatement.close();
            connection.commit();

            //long start = System.currentTimeMillis();
            //int[] count = preparedStatement.executeBatch();
            //long stop = System.currentTimeMillis();
            //System.out.println(count.length + " row(s) affected:");
            System.out.println("Time taken to populate database: " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");

            /*ResultSet rs = statement.executeQuery("SELECT * FROM Employee");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for(int i = 1; i < columnsNumber + 1; i++)
                    System.out.print(rs.getString(i) + "\t");
                System.out.println();
            }*/
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
