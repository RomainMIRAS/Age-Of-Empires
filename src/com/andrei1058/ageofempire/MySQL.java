package com.andrei1058.ageofempire;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.andrei1058.ageofempire.Main.mysql;

public class MySQL {
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private Connection conn;

    private String getHost()
    {
        return this.host;
    }

    private int getPort()
    {
        return this.port;
    }

    private String getDataBase()
    {
        return this.database;
    }

    private String getUser()
    {
        return this.user;
    }

    private String getPassword()
    {
        return this.password;
    }

    private Connection getConnection()
    {
        return this.conn;
    }

    private void setHost(String host)
    {
        this.host = host;
    }

    private void setPort(int port)
    {
        this.port = port;
    }

    private void setDataBase(String datebase)
    {
        this.database = datebase;
    }

    private void setUser(String user)
    {
        this.user = user;
    }

    private void setPassword(String password)
    {
        this.password = password;
    }

    private void setConnection(Connection conn)
    {
        this.conn = conn;
    }

    public MySQL(String host, int port, String datebase, String user, String password)
    {
        setHost(host);
        setPort(port);
        setDataBase(datebase);
        setUser(user);
        setPassword(password);
    }

    public boolean createTable(String table, ArrayList<String> records)
    {
        openConnection();
        if (isConnected()) {
            try
            {
                boolean first = true;
                String records2 = "";
                for (String record : records) {
                    if (records.size() == 1)
                    {
                        records2 = records2 + record;
                    }
                    else if (first)
                    {
                        records2 = records2 + record;
                        first = false;
                    }
                    else
                    {
                        records2 = records2 + ", " + record;
                    }
                }
                getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + table + "` (" + records2 + ");");

                return true;
            }
            catch (SQLException e)
            {
                printError(e.getMessage());
            }
        }
        closeConnection();

        return false;
    }

    public Object getScore(String table, String ceSelect, Player p)
    {
        if (!isConnected())
            connect();
        if (isConnected()) {
            if (p != null && p.getUniqueId() != null) {
                try {
                    ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + "UUID" + "`='" + p.getUniqueId().toString() + "';");
                    if (rs.next()) {
                        return rs.getObject(ceSelect);
                    } else {
                        try {
                            ResultSet rs2 = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + "Player" + "`='" + p.getName() + "';");
                            if (rs2.next()) {
                                return rs2.getObject(ceSelect);
                            } else {
                                return 0;
                            }
                        } catch (SQLException ex) {
                            return 0;
                        }
                    }
                } catch (SQLException e) {
                    try {
                        ResultSet rs2 = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + "Player" + "`='" + p.getName() + "';");
                        if (rs2.next()) {
                            return rs2.getObject(ceSelect);
                        } else {
                            return 0;
                        }
                    } catch (SQLException ex) {
                        return 0;
                    }
                }
            } else if (p != null && p.getName() != null){
                try {
                    ResultSet rs2 = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + "Player" + "`='" + p.getName() + "';");
                    if (rs2.next()) {
                        return rs2.getObject(ceSelect);
                    } else {
                        return 0;
                    }
                } catch (SQLException ex) {
                    return 0;
                }
            }
            return 0;
        }
        return 0;
    }

    public Object getScoreByUUID(String table, String ceSelect, String uuid)
    {
        if (!isConnected())
            connect();
        if (isConnected()) {
            try {
                ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + "UUID" + "`='" + uuid + "';");
                if (rs.next()) {
                    return rs.getObject(ceSelect);
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                return 0;
            }
        }
        return 0;
    }

    public String[] getTop(String table, String column, Integer limit){
        if (!isConnected())
            connect();
        if (isConnected()){
            String[] array = new String[limit];
            int i = 0;
            try {
                ResultSet rs = getConnection().createStatement().executeQuery("SELECT *  FROM `"+table+"` ORDER BY `"+column+"` DESC LIMIT " +limit);
                while (rs.next()) {
                    Bukkit.broadcastMessage("§c"+rs.getObject("Player")+" - "+rs.getObject(column));
                    array[i] = rs.getString("UUID") + "," + rs.getInt(column) + "," + rs.getString("Player");
                    i++;
                }
                close();
                return array;
            } catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    public boolean deleteData(String table, String wherecolumn, String whereoperator, String wherevalue)
    {
        openConnection();
        if (isConnected()) {
            try
            {
                getConnection().createStatement().executeUpdate("DELETE FROM `" + table + "` WHERE `" + wherecolumn + "`" + whereoperator + "'" + wherevalue + "';");

                return true;
            }
            catch (SQLException e)
            {
                printError(e.getMessage());
            }
        }
        closeConnection();

        return false;
    }

    public boolean isDataExists(String table, String recordname, String record)
    {
        openConnection();
        if (isConnected()) {
            try
            {
                ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM `" + table + "` WHERE `" + recordname + "`='" + record + "';");
                if (rs.next()) {
                    return true;
                }
            }
            catch (SQLException e)
            {
                printError(e.getMessage());
            }
        }
        closeConnection();

        return false;
    }

    public void connect(){
        openConnection();
    }
    public void close(){
        closeConnection();
    }

    public boolean setData(String table, ArrayList<String> recordsnames, ArrayList<String> records)
    {
        openConnection();
        if (isConnected()) {
            if (recordsnames.size() == records.size()) {
                try
                {
                    boolean first = true;
                    int index = 0;
                    String records2 = "";
                    for (String recordname : recordsnames)
                    {
                        if (first)
                        {
                            records2 = records2 + "`" + recordname + "`='" + (String)records.get(index) + "'";
                            first = false;
                        }
                        else
                        {
                            records2 = records2 + ", `" + recordname + "`='" + (String)records.get(index) + "'";
                        }
                        index++;
                    }
                    getConnection().createStatement().executeUpdate("UPDATE `" + table + "` SET " + records2 + ";");

                    return true;
                }
                catch (SQLException e)
                {
                    printError(e.getMessage());
                }
            }
        }
        closeConnection();

        return false;
    }


    public boolean createDate(String table, ArrayList<String> recordsnames, ArrayList<String> records)
    {
        openConnection();
        if (isConnected()) {
            try
            {
                boolean first = true;
                boolean first2 = true;
                String records2 = "";
                String recordsnames2 = "";
                for (String recordname : recordsnames) {
                    if (recordsnames.size() == 1)
                    {
                        recordsnames2 = recordsnames2 + "`" + recordname + "`";
                    }
                    else if (first)
                    {
                        recordsnames2 = recordsnames2 + "`" + recordname + "`";
                        first = false;
                    }
                    else
                    {
                        recordsnames2 = recordsnames2 + ", `" + recordname + "`";
                    }
                }
                for (String record : records) {
                    if (records.size() == 1)
                    {
                        records2 = records2 + "'" + record + "'";
                    }
                    else if (first2)
                    {
                        records2 = records2 + "'" + record + "'";
                        first2 = false;
                    }
                    else
                    {
                        records2 = records2 + ", '" + record + "'";
                    }
                }
                getConnection().createStatement().executeUpdate("INSERT INTO `" + table + "` (" + recordsnames2 + ") VALUES (" + records2 + ");");

                return true;
            }
            catch (SQLException e)
            {
                printError(e.getMessage());
            }
        }
        closeConnection();

        return false;
    }

    public boolean setData(String table, ArrayList<String> recordsnames, ArrayList<String> records, String wherecolumn, String whereoperator, String wherevalue)
    {
        openConnection();
        if (isConnected()) {
            if (recordsnames.size() == records.size()) {
                try
                {
                    boolean first = true;
                    int index = 0;
                    String records2 = "";
                    for (String recordname : recordsnames)
                    {
                        if (first)
                        {
                            records2 = records2 + "`" + recordname + "`='" + (String)records.get(index) + "'";
                            first = false;
                        }
                        else
                        {
                            records2 = records2 + ", `" + recordname + "`='" + (String)records.get(index) + "'";
                        }
                        index++;
                    }
                    getConnection().createStatement().executeUpdate("UPDATE `" + table + "` SET " + records2 + " WHERE `" + wherecolumn + "`" + whereoperator + "'" + wherevalue + "';");

                    return true;
                }
                catch (SQLException e)
                {
                    printError(e.getMessage());
                }
            }
        }
        closeConnection();

        return false;
    }

    public boolean execute(String inquiry)
    {
        openConnection();
        if (isConnected()) {
            try
            {
                getConnection().createStatement().executeUpdate(inquiry);

                return true;
            }
            catch (SQLException e)
            {
                printError(e.getMessage());
            }
        }
        closeConnection();

        return false;
    }

    private void printError(String error)
    {
        System.out.println(error);
    }

    private boolean openConnection()
    {
        if (!isConnected()) {
            try
            {
                setConnection(DriverManager.getConnection("jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDataBase() + "?user=" + getUser() + "&password=" + getPassword()));
            }
            catch (SQLException e)
            {
                printError(e.getMessage());

                return false;
            }
        }
        return true;
    }

    private boolean closeConnection()
    {
        if (isConnected()) {
            try
            {
                getConnection().close();
            }
            catch (SQLException e)
            {
                printError(e.getMessage());

                return false;
            }
        }
        return true;
    }

    public boolean isConnected()
    {
        try
        {
            if (getConnection() == null) {
                return false;
            }
            if (getConnection().isClosed()) {
                return false;
            }
        }
        catch (SQLException e)
        {
            printError(e.getMessage());
        }
        return true;
    }

    /*public static void saveStats(Player p) {
        if (mysql) {
                MySQL m = new MySQL(host, port, database, username, password);
                if (!(m.isDataExists(table, "UUID", p.getUniqueId().toString()))) {
                    ArrayList columns = new ArrayList();
                    columns.add("ID");
                    columns.add("Player");
                    columns.add("Kills");
                    columns.add("Deaths");
                    columns.add("BeastsSlain");
                    columns.add("GoldEarnt");
                    columns.add("GamesPlayed");
                    columns.add("Victories");
                    columns.add("Shutdowns");
                    columns.add("Rampages");
                    columns.add("UUID");
                    ArrayList insert = new ArrayList();
                    insert.add("0");
                    insert.add(p.getName().toString());
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add("0");
                    insert.add(p.getUniqueId().toString());
                    m.createDate(table, columns, insert);
                }
                m.close();
    }*/
}

