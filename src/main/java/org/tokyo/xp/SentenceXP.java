/*package org.tokyo.xp;

import org.tokyo.db.DatabaseAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SentenceXP {

    private static Connection database = DatabaseAccess.getConnect();
    private static Statement statement = null;

    public static void addXP() {

    }

    private static boolean checkNewLevel(int obtainedXP, String userID) {
        try {
            statement = database.createStatement();
            String currentXP = statement.executeQuery("SELECT CURRENT_XP FROM XP WHERE USER_ID = " +
                    userID).getString("USER_XP");
            String requiredXP = 

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
*/