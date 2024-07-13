package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constrants.commonconstrants;

public class myJDBC {

    //registering the user to the database
    public static boolean regester(String username, String password){
        //validating the user is already registerd or not
        //return false;
        try {
            //the logic is that we will only register if the user is not in database
            if (!checkUser(username)) {
                //jdbc server connect
                Connection connection = DriverManager.getConnection(commonconstrants.DB_URL,commonconstrants.DB_USERNAME, commonconstrants.DB_PASSWORD);

                //create insert query 
                PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT INTO " + commonconstrants.DB_USER_TABLE + "(username, password)" + "values (?,?)"
                );
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                //update user with new user
                insertUser.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //cheak if user already exist in database
    public static boolean checkUser(String userName){
        try {
            //jdbc server connect
            Connection connection = DriverManager.getConnection(commonconstrants.DB_URL,commonconstrants.DB_USERNAME, commonconstrants.DB_PASSWORD);

            PreparedStatement checkUserExist = connection.prepareStatement(
                "SELECT * FROM "+ commonconstrants.DB_USER_TABLE+ " where USERNAME = ?"
            );

            //we will replace the ? with values using setString()
            checkUserExist.setString(1, userName);

            //stong the result in resultset
            ResultSet resultSet = checkUserExist.executeQuery();

            //check to see if the result set is empty 
            //if its empty it means that their is no data that contain the username 
            //we use the isBeforeFirst() to point to the first row of data that is required to our result set
            if (!resultSet.isBeforeFirst()) {
                System.out.println("USER DOESN'T EXIST'S");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        //if the user does exist in the database
        System.out.println("USER ALREADY EXIST'S");
        return true;
    }

    //validate the user login
    public static boolean validateLogin(String username, String password){
        try {
            Connection connection = DriverManager.getConnection(commonconstrants.DB_URL,commonconstrants.DB_USERNAME, commonconstrants.DB_PASSWORD);

            //create select query
            PreparedStatement validateUser = connection.prepareStatement(
                "SELECT * FROM " + commonconstrants.DB_USER_TABLE + " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1,username);
            validateUser.setString(2,password);

            ResultSet resultSet = validateUser.executeQuery();

            //isbefore is used to see if the query returned any row that matched our query
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //if it is true it means that their was a username/password that match the user input
        return true;
    }
}
