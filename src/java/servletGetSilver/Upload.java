/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servletGetSilver;

import businessGetSilver.Fichier_Excel;
import businessGetSilver.Fichier_Excel_Param;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Nom_Liste = "";
        String Nom_file = "";
        
        int count = 0;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
        
           String user = (String) session.getAttribute("username");
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                   
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if (name.equals("nom_list")) {
                            Nom_Liste = value;
                            count = 1;
                        }
                        else
                        {
                        Nom_Liste = value;
                        count=2;
                        }
                       
                    } else {
                        try {
                           // Nom_file = item.getName();     //NOM du fichier example file.xls
                           
                            String adressedufichier = "";
                            String realPath = getServletContext().getRealPath("/");
                            String pathUnix = realPath + "upload/";
                            adressedufichier = pathUnix + "List.xlsx";
                            File savedFile = new File(adressedufichier);
                            item.write(savedFile);

                            if (count == 1) {                               
                                request.getRequestDispatcher("/menuAdmin.jsp").forward(request, response);
                                Fichier_Excel fichier = new Fichier_Excel();
                                fichier.Extract_DATA(adressedufichier, Nom_Liste,user);
                            

                            }
                            if (count == 2) {   
                            //  if(Nom_file.endsWith(".xlsx"))
                              {
                                request.getRequestDispatcher("/menuAdmin.jsp").forward(request, response);
                                Fichier_Excel fichierp = new Fichier_Excel();
                               // fichier.Extract_DATA(adressedufichier, Nom_Liste,user);
                                fichierp.Extract_DATA_Param(adressedufichier, Nom_Liste,user);
                          //  System.out.println("ici xlsx "+Nom_file);
                              }
                         /*     else
                              {
                                request.getRequestDispatcher("/menuAdmin.jsp").forward(request, response);
                                Fichier_Excel_Param fichierp = new Fichier_Excel_Param();
                               // fichier.Extract_DATA(adressedufichier, Nom_Liste,user);
                               fichierp.Extract_DATA(adressedufichier, Nom_Liste,user);
                             
                                   System.out.println("ici xls "+Nom_file);
                              }*/
                            }
                         
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

