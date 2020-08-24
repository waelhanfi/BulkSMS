/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import daoGetSilver.Sql;
/**
 *
 * @author hakim
 */
public class Fichier_ExcelBlacklist {


     public String MSISDN;

    public Fichier_ExcelBlacklist() {
    }

    public Fichier_ExcelBlacklist(String MSISDN) {
        this.MSISDN = MSISDN;

    }

    public String Toiletage(String num_gsm) {
        return num_gsm.replaceAll(" ", "").trim();
    }

   public void Extract_DATA(String path_fichier, String nomListe,String utilisateur) throws FileNotFoundException, IOException, InvalidFormatException {


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
                                /*case Cell.CELL_TYPE_BLANK:
                                System.out.println("is blank.");
                                break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                System.out.println("contains a booleanvalueof: " + cell.getBooleanCellValue());
                                break;
                                case Cell.CELL_TYPE_ERROR:
                                System.out.println("contains an errorcodeof: " + cell.getErrorCellValue());
                                break;
                                case Cell.CELL_TYPE_FORMULA:
                                System.out.println("contains a formula: " + cell.getCellFormula());
                                break;*/
                                case Cell.CELL_TYPE_NUMERIC:
                                    try {
                                        if (DateUtil.isCellDateFormatted(cell)) {
                                            System.out.println("contains adate:" + cell.getDateCellValue());
                                        } else {
                                            msisdn = String.valueOf(cell.getNumericCellValue()).replace(".", "").replaceAll("E7", "").replaceAll("E10", "");
                                            if (msisdn.startsWith("+216")&&msisdn.length()==12) {
                                                msisdn = msisdn.substring(4);
                                            }
                                            if (msisdn.startsWith("216") && msisdn.length()==11) {
                                                msisdn = msisdn.substring(3);
                                            }

                                            if (msisdn.length()==7) {
                                                msisdn = msisdn+"0";
                                            }

                                            if ((msisdn.startsWith("9")||msisdn.startsWith("5")||msisdn.startsWith("2")||msisdn.startsWith("79"))&&msisdn.length()==8)
                                            {
                                                    Utile util = new Utile();
                                                    util.addBlacklist(sql,msisdn,utilisateur);
                                            }
                                        }
                                    } catch (IndexOutOfBoundsException iobEx) {
                                        System.out.println("an exception wasthrown " + "checking the dateformatting. " + "The cell contains thefollowing" + "numeric value: " + cell.getNumericCellValue());
                                    }
                                    break;/*
                               case Cell.CELL_TYPE_STRING:
                                System.out.println("contains a String: " +cell.getStringCellValue());
                                break;*/
                            }
                        }
                    }
                }
            } catch (FileNotFoundException fnfEx) {
                System.out.println("Caught: " + fnfEx.getClass().getName());
                System.out.println("Message: " + fnfEx.getMessage());
                System.out.println("Stacktrace follows..............");
                fnfEx.printStackTrace(System.out);
            } catch (IOException ioEx) {
                System.out.println("Caught: " + ioEx.getClass().getName());
                System.out.println("Message: " + ioEx.getMessage());
                System.out.println("Stacktrace follows..............");
                ioEx.printStackTrace(System.out);
            } catch (InvalidFormatException invFEx) {
                System.out.println("Caught: " + invFEx.getClass().getName());
                System.out.println("Message: " + invFEx.getMessage());
                System.out.println("Stacktrace follows..............");
                invFEx.printStackTrace(System.out);
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
            System.out.println("fichier inexistant");
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {

        Fichier_Excel fichier = new Fichier_Excel();
       
    }









}
