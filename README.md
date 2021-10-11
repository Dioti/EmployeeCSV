# EmployeeCSV

A simple program that reads a csv file containing employees, stores each employee using Java collections and subsequently writes valid entries to a database.

![image](https://user-images.githubusercontent.com/11021728/136734178-92e3546b-5635-446b-890a-7392b66a48f7.png)

---

### Features

The program performs the following operations:
- Opens csv file containing a list of employees stored in a specific format
- Reads and parses employee data
- Stores an array list of valid and invalid employees and records duplicate entries
- Prints out invalid entries
- Connects to a database using the db.properties file
- Creates an Employees table within the database specified in the properties file
- Writes the valid employees to the table using multithreading and batch commits
- Records and prints out the time taken for various program operations

---

### Setting up a database

This project requires a MySQL server. You can find downloads and installation instructions on [the MySQL website](https://dev.mysql.com/downloads/mysql/).

In order to allow the program to connect to the database, you will need to configure the `db.properties` file, which can be located in `src/main/resources`. Below is an explanation of the properties:

```
jdbc.driver - the database driver. leave this alone, unless you're using a database that's not mysql
jdbc.url - the url of the database. if hosting the server locally, you only need to change {project} to the name of your database
jdbc.username - the username you want to connect to the database with. change {user} to your database username
jdbc.password - the password you want to connect to the database with. change {password} to your database username
```

---

### Performance

The program utilises threading to improve performance. Below shows a comparison of the time taken for the program to carry out various operations, using input data of various sizes.

##### Using EmployeeRecords.csv (791 KB)
```
Total entries: 10000
Number of duplicates: 57
Valid entries: 9942
Invalid entries: 58
Time taken to populate Java collections: 642 ms (~1 seconds)
Time taken to populate database (single-threaded): 20244 ms (~20 seconds)
Time taken to populate database (multi-threaded): 1349 ms (~1 seconds)
```

##### Using EmployeeRecordsLarge.csv (5.13 MB)
```
Total entries: 65499
Number of duplicates: 0
Valid entries: 65498
Invalid entries: 1
Time taken to populate Java collections: 971 ms (~1 seconds)
Time taken to populate database (single-threaded): 130011 ms (~130 seconds)
Time taken to populate database (multi-threaded): 11988 ms (~12 seconds)
```
The number of threads has a significant impact on performance, though the perfomance gains drop off at a certain point. Below shows the time taken to populate the database using `EmployeeRecords.csv` and a varying number of threads. The optimal number of threads seems to be between 50 and 100.
```
Threads:1   | 20478 ms (~20 seconds)
Threads:5   | 7640 ms (~8 seconds)
Threads:20  | 3098 ms (~3 seconds)
Threads:50  | 1545 ms (~2 seconds)
Threads:100 | 1422 ms (~1 seconds)
Threads:200 | 1541 ms (~2 seconds)
```

---

### To-Do List

- [x] Implement multi-threading to improve performance
- [x] Implement functional programming using streams and lambda expressions
- [ ] Add more operations to employee dao (update, remove, etc.)
- [ ] Allow user input through the command line
