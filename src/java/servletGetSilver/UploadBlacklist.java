/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servletGetSilver;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */import businessGetSilver.Fichier_ExcelBlacklist;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UploadBlacklist extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Upload at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

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
        processRequest(request, response);
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
        int count = 0;
        HttpSession session = request.getSession(false);
        String user = "";

        if (session != null && session.getAttribute("username") != null) {


            user = (String) session.getAttribute("username");

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

                    } else {
                        try {
                            String adressedufichier = "";
                            String realPath = getServletContext().getRealPath("/");
                            String pathUnix = realPath + "upload/";
                            adressedufichier = pathUnix + "Blacklist.xlsx";
                            File savedFile = new File(adressedufichier);
                            item.write(savedFile);
                            if (count == 1) {
                                Fichier_ExcelBlacklist fichier = new Fichier_ExcelBlacklist();
                                fichier.Extract_DATA(adressedufichier, Nom_Liste, user);
                            }
                            request.getRequestDispatcher("/BlackListes.jsp").forward(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

