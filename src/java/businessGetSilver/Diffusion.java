package businessGetSilver;

import static businessGetSilver.UnicodeFormatter.HexToByte;
import daoGetSilver.DaoHistoriqueEnvoi;
import java.util.StringTokenizer;
import daoGetSilver.Sql;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class Diffusion {

    //utilise stringtokenizer avec le ;
    public String[] getMsisdnListRapide(String list) {
        String[] tab = new String[50];
        list = list.replaceAll(" ", "");
        StringTokenizer tokenizer = new StringTokenizer(list, ";");
        int i = 0;

        while (tokenizer.hasMoreTokens()) {
            tab[i] = tokenizer.nextToken();
            //System.out.println(tab[i]);
            i++;
        }
        return tab;
    }
 public static String printBytes(byte[] array) {
        String result = "";
        for (int k = 0; k < array.length; k++) {
            result = result + "%" +
                 UnicodeFormatter.byteToHex(array[k]);
        }
        return result;
    }
 
  public Vector  data (Sql sql, String date_min, String date_max,String utilisateur)
    {
        Vector history = new Vector();
        try {
            Sql sq=new Sql("172.16.50.5","dbv9","send_sms","send_sms");
            sq.Open_Connexion();
      Statement st = sq.Get_Connection().createStatement();
          ResultSet rs = st.executeQuery("select num_gsm,msg,date_envoie,heure_envoi,stat from OCT where to_char(DATE_ENVOIE,'yyyy-mm-dd') between '" +
                                     date_min + "' and '" + date_max + "'");
      while (rs.next()) {

         HistoriqueEnvoi histo =new HistoriqueEnvoi();
         histo.setNumber(rs.getString(1));
         histo.setMessage(rs.getString(2));
         histo.setDateEnvoi(rs.getString(3));
         histo.setHeure(rs.getString(4));
         histo.setStatus(rs.getString(5));
         history.addElement( histo );
      }
          rs.close();
          st.close();
          sq.Fermer_Cnn();
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
    return history;
    }
   public int getTotalSMSByStatus(String status,String date_min, String date_max){
     int total = 0;
     Sql sq= new Sql("172.16.50.5","dbv9","send_sms","send_sms");
            sq.Open_Connexion();
      Statement st;
        try {
            st = sq.Get_Connection().createStatement();
             ResultSet rs = st.executeQuery("select count(*) from BEE where upper(stat)='"+status.toUpperCase()+"' AND to_char(DATE_ENVOIE,'yyyy-mm-dd') between '" +
                                     date_min + "' and '" + date_max + "'");
             if(rs.next()){
                 total = rs.getInt(1);
             }
        } catch (Exception ex) {
            
        }
         
     return total;
 }
 public static String Bytes(String s) throws UnsupportedEncodingException {
      
      String b = s.replaceAll("%", "");
        byte[] c = HexToByte(b);
         String result = new String(c,"windows-1256");
        
        
        return result;
    }
    public int getListLengthRapide(String list) {
        String[] tab = new String[50];
        list = list.replaceAll(" ", "");
        StringTokenizer tokenizer = new StringTokenizer(list, ";");
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            tab[i] = tokenizer.nextToken();
            //System.out.println(tab[i]);
            i++;
        }
        return i;
    }
public static String convert(String str) {
        StringBuffer ostr = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if ((ch >= 0x0020) && (ch <= 0x007e)) // Does the char need to be converted to unicode?
            {
                ostr.append(ch); // No.
            } else // Yes.
            {
                //ostr.append("\\u"); // standard unicode format.
                String hex = Integer.toHexString(str.charAt(i) & 0xFFFF); // Get hex value of the char.

                for (int j = 0; j < 4 - hex.length(); j++) // Prepend zeros because unicode requires 4 digits
                {
                    ostr.append("0");
                }
                ostr.append(hex.toLowerCase()); // standard unicode format.
            //   ostr.append(hex.toLowerCase(Locale.ENGLISH));
            }
        }

        return (new String(ostr)); //Return the stringbuffer cast as a string.

    }
public void DiffuserListeRapide(String list, String message,String utilisateur) {
        Sql sql = new Sql();
        sql.Open_Connexion();
        String[] msisdnList = getMsisdnListRapide(list);
        for (int i = 0; i < getListLengthRapide(list); i++) {
            SendSMS(sql, msisdnList[i], message, utilisateur.toUpperCase());
        }      

     
        Utile utile = new Utile();
		int total = getListLengthRapide(list);        
        if(message.length() > 160){ total = total * 2;}
        String nombre=String.valueOf(total);
        
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(message, nombre,utilisateur);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi = new DaoHistoriqueEnvoi();
        utile.debitAccount(sql, nombre,utilisateur);
       // System.out.println("ok");
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
        sql.Commit();
        sql.Fermer_Cnn();
        
    }


public void DiffuserGsmListeParam(GsmParam[] listeGsm, String message, String service) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
       
       
        for (int i = 0; i < listeGsm.length; i++) {
          String message2=message;
            if(!listeGsm[i].getKW1().equals("vide")){
            message2=message2.replace("KW1",listeGsm[i].getKW1());
            message2=message2.replace("Kw1",listeGsm[i].getKW1());
            message2=message2.replace("kw1",listeGsm[i].getKW1());
            message2=message2.replace("kW1",listeGsm[i].getKW1());
            
            
                        }
          if(!listeGsm[i].getKW2().equals("vide")){
           message2=message2.replace("KW2",listeGsm[i].getKW2());
            message2=message2.replace("Kw2",listeGsm[i].getKW2());
            message2=message2.replace("kw2",listeGsm[i].getKW2());
            message2=message2.replace("kW2",listeGsm[i].getKW2());}
          
          if(!listeGsm[i].getKW3().equals("vide")){
              
            message2=message2.replace("KW3",listeGsm[i].getKW3());
            message2=message2.replace("Kw3",listeGsm[i].getKW3());
            message2=message2.replace("kw3",listeGsm[i].getKW3());
            message2=message2.replace("kW3",listeGsm[i].getKW3());}
          
          
             if(!listeGsm[i].getKW4().equals("vide")){
            message2=message2.replace("KW4",listeGsm[i].getKW4());
            message2=message2.replace("Kw4",listeGsm[i].getKW4());
            message2=message2.replace("kw4",listeGsm[i].getKW4());
            message2=message2.replace("kW4",listeGsm[i].getKW4());}
             
             
              if(!listeGsm[i].getKW5().equals("vide")){
            message2=message2.replace("KW5",listeGsm[i].getKW5());
            message2=message2.replace("Kw5",listeGsm[i].getKW5());
            message2=message2.replace("kw5",listeGsm[i].getKW5());
            message2=message2.replace("kW5",listeGsm[i].getKW5());}
              
               if(!listeGsm[i].getKW6().equals("vide")){
            message2=message2.replace("KW6",listeGsm[i].getKW6());
            message2=message2.replace("Kw6",listeGsm[i].getKW6());
            message2=message2.replace("kw6",listeGsm[i].getKW6());
            message2=message2.replace("kW6",listeGsm[i].getKW6());}
             
              
             message2=message2.replace("KW1"," ");
            message2=message2.replace("Kw1"," ");
            message2=message2.replace("kw1"," ");
            message2=message2.replace("kW1"," ");
             message2=message2.replace("KW2"," ");
            message2=message2.replace("Kw2"," ");
            message2=message2.replace("kw2"," ");
            message2=message2.replace("kW2"," ");
            message2=message2.replace("KW3"," ");
            message2=message2.replace("Kw3"," ");
            message2=message2.replace("kw3"," ");
            message2=message2.replace("kW3"," ");
            message2=message2.replace("KW4"," ");
            message2=message2.replace("Kw4"," ");
            message2=message2.replace("kw4"," ");
            message2=message2.replace("kW4"," ");
             message2=message2.replace("KW5"," ");
            message2=message2.replace("Kw5"," ");
            message2=message2.replace("kw5"," ");
            message2=message2.replace("kW5"," ");
            message2=message2.replace("KW6"," ");
            message2=message2.replace("Kw6"," ");
            message2=message2.replace("kw6"," ");
            message2=message2.replace("kW6"," ");
           
            SendSMS(sql, listeGsm[i].getNumGsm(), message2, service);
//System.out.println("Message" + message2 + i);
        }
        sql.Commit();
        sql.Fermer_Cnn();
    }
    public void DiffuserGsmListe(GsmListe[] listeGsm, String message, String service) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        //System.out.println(listeGsm.length);
        //System.out.println("Message" + message);
        for (int i = 0; i < listeGsm.length; i++) {
            SendSMS(sql, listeGsm[i].getNumGsm(), message, service);

        }
        sql.Commit();
        sql.Fermer_Cnn();
    }
    public void DiffuserGsmListe_enregistrement(GsmListe[] listeGsm, String message, String service,String dd) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
      
        for (int i = 0; i < listeGsm.length; i++) {
            SendSMS_enregistre(sql, listeGsm[i].getNumGsm(), message, service,dd);

        }
        sql.Commit();
        sql.Fermer_Cnn();
    }
 public void DiffuserGsmListe_enregistrementparam(GsmParam[] listeGsm, String message, String service,String dd) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
      
       
          
         for (int i = 0; i < listeGsm.length; i++) {
          String message2=message;
            if(!listeGsm[i].getKW1().equals("vide")){
            message2=message2.replace("KW1",listeGsm[i].getKW1());
            message2=message2.replace("Kw1",listeGsm[i].getKW1());
            message2=message2.replace("kw1",listeGsm[i].getKW1());
            message2=message2.replace("kW1",listeGsm[i].getKW1());
            
                        }
          if(!listeGsm[i].getKW2().equals("vide")){
           message2=message2.replace("KW2",listeGsm[i].getKW2());
            message2=message2.replace("Kw2",listeGsm[i].getKW2());
            message2=message2.replace("kw2",listeGsm[i].getKW2());
            message2=message2.replace("kW2",listeGsm[i].getKW2());}
          
          if(!listeGsm[i].getKW3().equals("vide")){
              
            message2=message2.replace("KW3",listeGsm[i].getKW3());
            message2=message2.replace("Kw3",listeGsm[i].getKW3());
            message2=message2.replace("kw3",listeGsm[i].getKW3());
            message2=message2.replace("kW3",listeGsm[i].getKW3());}
          
          
             if(!listeGsm[i].getKW4().equals("vide")){
            message2=message2.replace("KW4",listeGsm[i].getKW4());
            message2=message2.replace("Kw4",listeGsm[i].getKW4());
            message2=message2.replace("kw4",listeGsm[i].getKW4());
            message2=message2.replace("kW4",listeGsm[i].getKW4());}
             
             
              
             message2=message2.replace("KW1"," ");
            message2=message2.replace("Kw1"," ");
            message2=message2.replace("kw1"," ");
            message2=message2.replace("kW1"," ");
             message2=message2.replace("KW2"," ");
            message2=message2.replace("Kw2"," ");
            message2=message2.replace("kw2"," ");
            message2=message2.replace("kW2"," ");
            message2=message2.replace("KW3"," ");
            message2=message2.replace("Kw3"," ");
            message2=message2.replace("kw3"," ");
            message2=message2.replace("kW3"," ");
            message2=message2.replace("KW4"," ");
            message2=message2.replace("Kw4"," ");
            message2=message2.replace("kw4"," ");
            message2=message2.replace("kW4"," ");
             
            SendSMS_enregistre(sql, listeGsm[i].getNumGsm(), message2, service,dd);

        }
        sql.Commit();
        sql.Fermer_Cnn();
    }
    //envoi un seul SMS
    public void SendSMS(Sql sql, String MSISDN, String message, String service) {
        if (MSISDN.length() > 8) {
            sql.Update("insert into  send_sms.BEE values(send_sms.S_BEE.nextval,'+" + MSISDN
                    + "','" + message.replaceAll("'", "''") + "',to_char(sysdate,'dd-mm-yyyy'),to_char(sysdate,'hh24:mi')"
                    + ",0,'" + service + "','vide','vide')");
        } else {
            sql.Update("insert into  send_sms.BEE values(send_sms.S_BEE.nextval,'+216" + MSISDN
                    + "','" + message.replaceAll("'", "''") + "',to_char(sysdate,'dd-mm-yyyy'),to_char(sysdate,'hh24:mi')"
                    + ",0,'" + service + "','vide','vide')");
        }
    }
public void SendSMS_enregistre(Sql sql, String MSISDN, String message, String service,String d) {
    String dd="to_date('"+d+"','dd-mm-yyyy hh24:mi')";
    if (MSISDN.length() > 8) {
            sql.Update("insert into  send_sms.BEE values(send_sms.S_BEE.nextval,'+" + MSISDN
                    + "','" + message.replaceAll("'", "''") + "',to_char("+dd+",'dd-mm-yyyy'),to_char("+dd+",'hh24:mi')"
                    + ",0,'" + service + "','vide','vide')");
         //   System.out.println("houni");
            
        } else {
            sql.Update("insert into  send_sms.BEE values(send_sms.S_BEE.nextval,'+216" + MSISDN
                    + "','" + message.replaceAll("'", "''") + "',to_char("+dd+",'dd-mm-yyyy'),to_char("+dd+",'hh24:mi')"
                    + ",0,'" + service + "','vide','vide')");
           // System.out.println("houni2");
        }
    }
    public static void main(String[] args) {
        Diffusion diff = new Diffusion();
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
      // diff.DiffuserGsmListeParam("22970237", "test", "getsilver");
        //diff.SendSMS(sql, "22970237", "test", "getsilver");
        sql.Fermer_Cnn();

    }
}
