package daoGetSilver;

import java.math.BigDecimal;
import java.sql.*;

public class Sql {

    public static String [][] DB_CONFIG = {
        {"172.16.50.5", "dbv9", "BEE", "BEE"},
        {"localhost", "orcl", "BEE", "BEE"}
    };

    public static int ACTUAL_CONFIG = 0;

    public static String ADR_IP = DB_CONFIG[ACTUAL_CONFIG][0];
    public static String NOM_BASE = DB_CONFIG[ACTUAL_CONFIG][1];
    public static String LOGIN = DB_CONFIG[ACTUAL_CONFIG][2];
    public static String PWD = DB_CONFIG[ACTUAL_CONFIG][3] ;


  private Connection cnn = null;
  private String Adresse_IP;
  private String Nom_Base;
  private String Login;
  private String Psw;

  public Sql()
  {
    Adresse_IP = "172.16.50.5";
    Nom_Base = "dbv9";
    Login = "BEE";
    Psw = "BEE";
  }

  public Sql(String adresse_ip, String nom_base, String login, String psw)
  {
    Adresse_IP = adresse_ip;
    Nom_Base = nom_base;
    Login = login;
    Psw = psw;
  }
  
  public void Open_Connexion() {
    try {
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      String url = "jdbc:oracle:thin:@" + Adresse_IP + ":1521:" + Nom_Base + "";
      cnn = DriverManager.getConnection(url, Login, Psw);
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public void Update(String query) {
    System.out.println(query);
    try {
      Statement st = cnn.createStatement();
      st.executeUpdate(query);
      st.close();
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public int Get_Int_For_This_Query(String query) {
    try {
      Statement st = cnn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        return rs.getInt(1);
      }
      else {
        return -1;
      }
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return -1;
    }
  }

  public BigDecimal Get_BigDecimal_For_This_Query(String query) {
    try {
      Statement st = cnn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        return rs.getBigDecimal(1);
      }
      else {
        return new BigDecimal(-1);
      }
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return new BigDecimal(-1);
    }
  }




  public String Get_String_For_This_Query(String query) {
    try {
      Statement st = cnn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        return rs.getString(1);
      }
      else {
        return "";
      }
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return "";
    }
  }


  public void Commit() {
    Update("commit");
  }

  public Connection Get_Connection() {
    return cnn;
  }

  public void Fermer_Cnn() {
    try {
      cnn.close();
    }
    catch (Exception e) {

    }
  }

  public static void main(String[] args) {
      Sql sql = new Sql ("172.16.50.5","dbv9","getsilver","getsilver");
      sql.Open_Connexion();
      sql.Fermer_Cnn();


  }

}
