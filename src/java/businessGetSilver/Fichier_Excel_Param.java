package businessGetSilver;

import daoGetSilver.DaoGsmListe;
import daoGetSilver.DaoListe;
import jxl.*;
import jxl.biff.Type;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import daoGetSilver.Sql;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.record.formula.functions.Column;

public class Fichier_Excel_Param {

    public String MSISDN;

    public Fichier_Excel_Param() {
    }

    public Fichier_Excel_Param(String MSISDN) {
        this.MSISDN = MSISDN;

    }
public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    // only got here if we didn't return false
    return true;
}
    public String Toiletage(String num_gsm) {
        return num_gsm.replaceAll(" ", "").trim();
    }

    public String nettoyerGsm(String msisdn) {
        msisdn = msisdn.replaceAll("\\.", "");
        msisdn = msisdn.replaceAll("/", "");
        msisdn = msisdn.replaceAll("\\*", "");
        msisdn = msisdn.replaceAll("-", "");
        msisdn = msisdn.replaceAll("\\+", "");
        msisdn = msisdn.replaceAll("&", "");
        msisdn = msisdn.replaceAll(",", "");
        msisdn = msisdn.replaceAll("\\?", "");
        msisdn = msisdn.replaceAll(";", "");
        msisdn = msisdn.replaceAll(":", "");
        msisdn = msisdn.replaceAll("!", "");
        msisdn = msisdn.replaceAll("=", "");
        msisdn = msisdn.replaceAll("\"", "");
        msisdn = msisdn.replaceAll("'", "");
        msisdn = msisdn.replaceAll("\\(", "");
        msisdn = msisdn.replaceAll("_", "");
        msisdn = msisdn.replaceAll("\\)", "");
        msisdn = msisdn.replaceAll("~", "");
        msisdn = msisdn.replaceAll("#", "");
        msisdn = msisdn.replaceAll("\\{", "");
        msisdn = msisdn.replaceAll("\\[", "");
        msisdn = msisdn.replaceAll("|", "");
        msisdn = msisdn.replaceAll("`", "");
        msisdn = msisdn.replaceAll("\\\\", "");
        msisdn = msisdn.replaceAll("^", "");
        msisdn = msisdn.replaceAll("@", "");
        msisdn = msisdn.replaceAll("\\]", "");
        msisdn = msisdn.replaceAll("\\}", "");
        msisdn = msisdn.replaceAll("%", "");
        msisdn = msisdn.replaceAll("$", "");
        msisdn = msisdn.replaceAll("£", "");
        msisdn = msisdn.replaceAll("¤", "");
        msisdn = msisdn.replaceAll("§", "");
        msisdn = msisdn.replaceAll("<", "");
        msisdn = msisdn.replaceAll(">", "");
        msisdn = msisdn.replaceAll("²", "");
        msisdn = msisdn.replaceAll("°", "");
        for (int i = 0; i < msisdn.length(); i++) {
            if (msisdn.charAt(i) != '0' && msisdn.charAt(i) != '1'
                    && msisdn.charAt(i) != '2' && msisdn.charAt(i) != '3'
                    && msisdn.charAt(i) != '4' && msisdn.charAt(i) != '5'
                    && msisdn.charAt(i) != '6' && msisdn.charAt(i) != '7'
                    && msisdn.charAt(i) != '8' && msisdn.charAt(i) != '9') {
                msisdn.toCharArray()[i] = 'a';
            }

        }
        msisdn = msisdn.replaceAll("a", "");
        return msisdn;
    }

  
public void Extract_DATA(String path_fichier, String nomListe, String utilisateur) throws FileNotFoundException, IOException, InvalidFormatException, BiffException {

        String msisdn=" ";
        File fichier = new File(path_fichier);
        if (fichier.exists()) {


            Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
            sql.Open_Connexion();

            Liste liste = new Liste(nomListe+"$pm", utilisateur);
            DaoListe daoListe = new DaoListe();
            daoListe.addListe(sql, liste);
            liste = daoListe.getLastListe(sql, utilisateur);
            DaoGsmListe daoGsmListe = new DaoGsmListe();
            sql.Fermer_Cnn();
            
// Open the workbook.
                jxl.Workbook wb = jxl.Workbook.getWorkbook(new File(path_fichier));
             //   jxl.Sheet sheet = wb.getSheet(0);
                
// Get the number of sheets in the workbook and enter a forloop
// to iterate through them one at a time.
                int numSheets = wb.getNumberOfSheets();
                for (int ii = 0; ii < numSheets; ii++) {

// Get the sheet and recover from that an iterator that
// allows us to step through all of the rows the sheetcontains.
                  jxl.Sheet sheeti = wb.getSheet(ii);
                  int rt=sheeti.getRows();
                  System.out.println("number of row : "+rt);
                  //int i=1;
                   for (int i = 1; i < rt; i++)
                    {
//System.out.println("number of row : "+sheeti.getRows());
// Get a row and recover from it an Iterator thatallows
// us to access each of the cells on the row.
                       
                       
                      String[] par={" "," "," "," "," "};
                        
                      //  cellIter = row.cellIterator();
                        for (int colomn=0;colomn<5;colomn++) {

// Get a cell
                     //   System.out.println("number of colomn : "+colomn);    
                            try{
                                if(sheeti.getCell(colomn, i).getType()!=CellType.EMPTY)
                                {    msisdn = sheeti.getCell(colomn, i).getContents();}
                                else
                                {
                                 msisdn=" ";
                                }
                           
                            }
                            catch(Throwable any)
                            {
                             msisdn=" ";
                            }
                          
          
                           if(isInteger(msisdn))
                               {
                               
                                    try {
                                       
                                      if(colomn==0) {

                                           
                                            if (msisdn.length() >= 8 || msisdn.contains("E")) {
                                                msisdn = msisdn.replace(".", "");
                                                msisdn = msisdn.replaceAll("E7", "").replaceAll("E10", "");
                                                if (msisdn.startsWith("+216") && msisdn.length() == 12) {
                                                    msisdn = msisdn.substring(4);
                                                }
                                                if (msisdn.startsWith("216") && msisdn.length() == 11) {
                                                    msisdn = msisdn.substring(3);
                                                }

                                                if (msisdn.length() < 8) {
                                                    while ((msisdn.length() + 1) <= 8) {
                                                        msisdn = msisdn + "0";
                                                    }
                                                }


                                                if ((msisdn.startsWith("9") || msisdn.startsWith("5") || msisdn.startsWith("2") || msisdn.startsWith("79") || msisdn.startsWith("4")) && msisdn.length() == 8) {
                                                 /*   GsmListe gsm = new GsmListe(msisdn, liste, "", "col"+colomn);
                                                    sql.Open_Connexion();
                                                    daoGsmListe.addGsm(sql, gsm);
                                                    sql.Fermer_Cnn();*/
                                                    par[colomn]=""+msisdn;
                                                }
                                            }
                                        }
                                    } catch (IndexOutOfBoundsException iobEx) {
                                        //System.out.println("an exception wasthrown " + "checking the dateformatting. " + "The cell contains thefollowing" + "numeric value: " + cell.getNumericCellValue());
                                    }
                               }
                           else if(!msisdn.equals(null)){
                             
                          
                           
                            if(colomn==0){
                                        
                                        msisdn = nettoyerGsm(msisdn);
                                        if (msisdn.startsWith("+216") && msisdn.length() == 12) {
                                            msisdn = msisdn.substring(4);
                                        }
                                        if (msisdn.startsWith("216") && msisdn.length() == 11) {
                                            msisdn = msisdn.substring(3);
                                        }
                                        if ((msisdn.startsWith("9") || msisdn.startsWith("5") || msisdn.startsWith("2") || msisdn.startsWith("79") || msisdn.startsWith("4")) && msisdn.length() == 8) {
                                             par[colomn]=""+msisdn;
                                        }
                            }
                            else
                            {
                             par[colomn]=""+msisdn;
                            
                            }
                                  
                             }
                                   
                             System.out.println("number of msisdn : "+msisdn);
                        }//boucle For 5 colomn
                      
                        // ici l'enregistrement de la ligne ds la BD
                    
               /*      GsmParam gsm = new GsmParam(par[0], liste, par[1], par[2],par[3],par[4]);
                                                    sql.Open_Connexion();
                                                    daoGsmListe.addGsmparam(sql, gsm);
                                                    sql.Fermer_Cnn();*/
                    }//boucle for row
                }//boucle for sheet
           
            sql.Commit();
            sql.Fermer_Cnn();
        } 
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {

        Fichier_Excel_Param fichier = new Fichier_Excel_Param();
        String msisdn = "20.24.54!98*";
        //System.out.println(fichier.nettoyerGsm(msisdn));
    }
}
