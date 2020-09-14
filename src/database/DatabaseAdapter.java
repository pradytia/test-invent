package database;

import model.mdlListParam;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.List;


public class DatabaseAdapter {

    public static Connection getConnectionMysql(){

        //Creating the connection
        String url = "jdbc:mysql://localhost:3306/db_invent_test?autoReconnect=true&useSSL=false";
        String user = "root";
        String pass = "abc123";
        Connection con = null;

        try {

            // disetting dalam pom xml
            Class.forName("com.mysql.jdbc.Driver");

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(url, user, pass);

            return  con;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static CachedRowSet getExecute(String sql) throws SQLException {

        RowSetFactory rowSetFactory = null;
        CachedRowSet crs = null;

        Connection con = null;

        try {

            con  = DatabaseAdapter.getConnectionMysql();

            if(con != null){

                System.out.println("Connected to the database successs");
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(sql);

                rowSetFactory = RowSetProvider.newFactory();
                crs = rowSetFactory.createCachedRowSet();
                crs.populate(rs);

            }else {
                System.out.println("Connected to Database Failed");
            }


        }catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }finally {
            con.close();
        }
        return crs;
    }

    public static boolean uploadExecute(String sql, List<mdlListParam> listParams){
        Connection connection = null;
        PreparedStatement pstm =  null;

        boolean result = false;

        try {
            connection = DatabaseAdapter.getConnectionMysql();

            if(connection != null){
                System.out.println("Connect to database success");
                pstm = connection.prepareStatement(sql);

//                for()
//                pstm.setString(1, name);
                result = pstm.execute();
            }else {
                result = false;
            }

        }catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean uploadExecute2(String sql){
        Connection connection = null;
        PreparedStatement pstm =  null;

        boolean result = false;

        try {
            connection = DatabaseAdapter.getConnectionMysql();

            if(connection != null){
                System.out.println("Connect to database success");
                pstm = connection.prepareStatement(sql);

                result = pstm.execute();
            }else {
                result = false;
            }

        }catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
        }
        return result;
    }

    public static mdlListParam getParamSql(String type, Object value){
        mdlListParam param = new mdlListParam();
        param.paramType = type;
        param.paramValue = value;

        return param;
    }
}
