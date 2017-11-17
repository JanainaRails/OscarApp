package Servlet;

import DAO.BuscaVotosDAO;
import Model.VotosUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@WebServlet(name = "BuscaVotosUsuario", urlPatterns = {"/BuscaVotosUsuario"})
public class BuscaVotosUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BuscaVotosDAO bvDAO = new BuscaVotosDAO();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        String usuario = request.getParameter("usuario");
        String msg;
        int usu = Integer.parseInt(usuario);
        VotosUsuario vu = new VotosUsuario();
        vu = bvDAO.buscaVotosUsuario(usu);
        if(vu != null){
            hm.put("diretor", vu.getDiretor());
            hm.put("filme", vu.getFilme());
        } else {
            hm.put("message", 0);
        }
        //Cada chave do HashMap vira uma chave do JSON
        JSONObject json = JSONObject.fromObject(hm);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
