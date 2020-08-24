package businessGetSilver;

import daoGetSilver.DaoGsmListe;
import daoGetSilver.DaoListe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import daoGetSilver.Sql;
import jxl.CellType;
import org.apache.poi.hssf.record.formula.functions.Column;

public class Fichier_Excel {

    public String MSISDN;

    public Fichier_Excel() {
    }

    public Fichier_Excel(String MSISDN) {
        this.MSISDN = MSISDN;

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

   public void Extract_DATA(String path_fichier, String nomListe, String utilisateur) throws FileNotFoundException, IOException, InvalidFormatException {


        File fichier = new File(path_fichier);
        if (fichier.exists()) {


            String msisdn;
            File file = null;
            FileInputStream fis = null;
            Workbook workbook = null;
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            int numSheets = 0;
            Iterator<Row> rowIter = null;
            Iterator<Cell> cellIter = null;
            CellReference cellRef = null;

            Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
            sql.Open_Connexion();

            Liste liste = new Liste(nomListe, utilisateur);
            DaoListe daoListe = new DaoListe();
            daoListe.addListe(sql, liste);
            liste = daoListe.getLastListe(sql, utilisateur);
            DaoGsmListe daoGsmListe = new DaoGsmListe();
            sql.Fermer_Cnn();
            try {
// Open the workbook.
                file = new File(path_fichier);
                fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);

// Get the number of sheets in the workbook and enter a forloop
// to iterate through them one at a time.
                numSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numSheets; i++) {

// Get the sheet and recover from that an iterator that
// allows us to step through all of the rows the sheetcontains.
                    sheet = workbook.getSheetAt(i);
                    rowIter = sheet.rowIterator();

                    while (rowIter.hasNext()) {

// Get a row and recover from it an Iterator thatallows
// us to access each of the cells on the row.
                        row = rowIter.next();
                        cellIter = row.cellIterator();
                        while (cellIter.hasNext()) {

// Get a cell
                            cell = cellIter.next();

// The CellReference object must be created for eachcell and used
// to convert POI's indices into Excel's co-ordinatesystem.
                            cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                            //System.out.print("Cell " + cellRef.formatAsString() + " ");

                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_BLANK:
                                    //System.out.println("is blank.");
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    //System.out.println("contains a booleanvalueof: " + cell.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    //System.out.println("contains an errorcodeof: " + cell.getErrorCellValue());
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    //System.out.println("contains a formula: " + cell.getCellFormula());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    try {
                                        if (DateUtil.isCellDateFormatted(cell)) {
                                            //System.out.println("contains adate:" + cell.getDateCellValue());
                                        } else {

                                            msisdn = String.valueOf(cell.getNumericCellValue());
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
                                                    GsmListe gsm = new GsmListe(msisdn, liste, "", "");
                                                    sql.Open_Connexion();
                                                    daoGsmListe.addGsm(sql, gsm);
                                                    sql.Fermer_Cnn();
                                                }
                                            }
                                        }
                                    } catch (IndexOutOfBoundsException iobEx) {
                                        //System.out.println("an exception wasthrown " + "checking the dateformatting. " + "The cell contains thefollowing" + "numeric value: " + cell.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    try {

                                        msisdn = cell.getStringCellValue();
                                        msisdn = nettoyerGsm(msisdn);
                                        if (msisdn.startsWith("+216") && msisdn.length() == 12) {
                                            msisdn = msisdn.substring(4);
                                        }
                                        if (msisdn.startsWith("216") && msisdn.length() == 11) {
                                            msisdn = msisdn.substring(3);
                                        }
                                        if ((msisdn.startsWith("9") || msisdn.startsWith("5") || msisdn.startsWith("2") || msisdn.startsWith("79") || msisdn.startsWith("4")) && msisdn.length() == 8) {
                                            GsmListe gsm = new GsmListe(msisdn, liste, "", "");
                                            sql.Open_Connexion();
                                            daoGsmListe.addGsm(sql, gsm);
                                            sql.Fermer_Cnn();
                                        }
                                    } catch (IndexOutOfBoundsException iobEx) {
                                        //System.out.println("an exception wasthrown " + "checking the dateformatting. " + "The cell contains thefollowing" + "String value value: " + cell.getStringCellValue());
                                    }
                                    break;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException fnfEx) {
                //System.out.println("Caught: " + fnfEx.getClass().getName());
                //System.out.println("Message: " + fnfEx.getMessage());
                //System.out.println("Stacktrace follows..............");
               //fnfEx.printStackTrace(System.out);
            } catch (IOException ioEx) {
               /* System.out.println("Caught: " + ioEx.getClass().getName());
                System.out.println("Message: " + ioEx.getMessage());
                System.out.println("Stacktrace follows..............");
                ioEx.printStackTrace(System.out);*/
            } catch (InvalidFormatException invFEx) {
               /* System.out.println("Caught: " + invFEx.getClass().getName());
                System.out.println("Message: " + invFEx.getMessage());
                System.out.println("Stacktrace follows..............");
                invFEx.printStackTrace(System.out);*/
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                        fis = null;
                    } catch (IOException ioEx) {
                        // I G N O R E
                    }
                }
            }
            sql.Commit();
            sql.Fermer_Cnn();
        } else {
            //System.out.println("fichier inexistant");
        }
    }
public void Extract_DATA_Param(String path_fichier, String nomListe, String utilisateur) throws FileNotFoundException, IOException, InvalidFormatException {


        File fichier = new File(path_fichier);
        if (fichier.exists()) {


            String msisdn;
            File file = null;
            FileInputStream fis = null;
            Workbook workbook = null;
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            
            int numSheets = 0;
            Iterator<Row> rowIter = null;           
            Iterator<Cell> cellIter = null;
            CellReference cellRef = null;

            Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
            sql.Open_Connexion();

            Liste liste = new Liste(nomListe+"$pm", utilisateur);
            DaoListe daoListe = new DaoListe();
            daoListe.addListe(sql, liste);
            liste = daoListe.getLastListe(sql, utilisateur);
            DaoGsmListe daoGsmListe = new DaoGsmListe();
            sql.Fermer_Cnn();
            try {
// Open the workbook.
// Open the workbook.
                file = new File(path_fichier);
                fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);

// Get the number of sheets in the workbook and enter a forloop
// to iterate through them one at a time.
                numSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numSheets; i++) {

// Get the sheet and recover from that an iterator that
// allows us to step through all of the rows the sheetcontains.
                    sheet = workbook.getSheetAt(i);
                    rowIter = sheet.rowIterator();
                    int nbrow=sheet.getLastRowNum();
                   while (rowIter.hasNext() && nbrow!=9997) {
                       
// Get a row and recover from it an Iterator thatallows
// us to access each of the cells on the row.
                        row = rowIter.next();
                        String[] par={"vide","vide","vide","vide","vide","vide","vide"};
                      
                        
                      //  cellIter = row.cellIterator();
                        for (int colomn=0;colomn<7;colomn++) {

// Get a cell
                        
                            cell = row.getCell(colomn);
                            String g=""+cell;

// The CellReference object must be created for eachcell and used
// to convert POI's indices into Excel's co-ordinatesystem.
                           // colomn=cell.getColumnIndex();
                           
                        //    cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                            //System.out.print("Cell " + cellRef.formatAsString() + " ");
 //System.out.print("Cell "+colomn+" "+sheet.getCellComment(1, c).getString());
                         //   if(!sheet.getCellComment(1, i).getString())
       if(!g.contains("null"))
        {
     // System.out.print("Cell1 "+cell);
          
          
          
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_BLANK:
                             //     System.out.println("contains a blankof: " );
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                              //      System.out.println("contains a booleanvalueof: " + cell.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                              //      System.out.println("contains an errorcodeof: " + cell.getErrorCellValue());
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                               //     System.out.println("contains a formula: " + cell.getCellFormula());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    try {
                                       
                                        if (DateUtil.isCellDateFormatted(cell)) {
                                            //System.out.println("contains adate:" + cell.getDateCellValue());
                                        } else if(cell.getColumnIndex()==0) {

                                            msisdn = String.valueOf(cell.getNumericCellValue());
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
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    try {
                             
                          
                            msisdn = cell.getStringCellValue();
                            if(cell.getColumnIndex()==0){
                                        
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
                                    } catch (IndexOutOfBoundsException iobEx) {
                                        //System.out.println("an exception wasthrown " + "checking the dateformatting. " + "The cell contains thefollowing" + "String value value: " + cell.getStringCellValue());
                                    }
                                    break;
                            }
        }
                       
       else{
        //   System.out.print("Cell2 "+cell);
       par[colomn]="vide";
       }
            }//boucle for 5 colomn 
                        // ici l'enregistrement de la ligne ds la BD
                    
                      GsmParam gsm = new GsmParam(par[0], liste, par[1], par[2],par[3],par[4],par[5],par[6]);
                                                    sql.Open_Connexion();
                                                    daoGsmListe.addGsmparam(sql, gsm);
                                                    sql.Fermer_Cnn();
                    }//boucle row/ligne
                }
            } catch (FileNotFoundException fnfEx) {
              
                //System.out.println("Caught: " + fnfEx.getClass().getName());
                //System.out.println("Message: " + fnfEx.getMessage());
                //System.out.println("Stacktrace follows..............");
               //fnfEx.printStackTrace(System.out);
            } catch (IOException ioEx) {
              
               /* System.out.println("Caught: " + ioEx.getClass().getName());
                System.out.println("Message: " + ioEx.getMessage());
                System.out.println("Stacktrace follows..............");
                ioEx.printStackTrace(System.out);*/
            } catch (InvalidFormatException invFEx) {
               
               /* System.out.println("Caught: " + invFEx.getClass().getName());
                System.out.println("Message: " + invFEx.getMessage());
                System.out.println("Stacktrace follows..............");
                invFEx.printStackTrace(System.out);*/
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                        fis = null;
                    } catch (IOException ioEx) {
                      
                        // I G N O R E
                    }
                }
            }
            sql.Commit();
            sql.Fermer_Cnn();
        } else {
            //System.out.println("fichier inexistant");
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {

        Fichier_Excel fichier = new Fichier_Excel();
        String msisdn = "20.24.54!98*";
        //System.out.println(fichier.nettoyerGsm(msisdn));
    }
}
