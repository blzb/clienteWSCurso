/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.servlet.calculadora.Calculator;
import test.servlet.calculadora.ICalculator;
import test.ws.client.Cita;
import test.ws.client.CitaFacade;
import test.ws.client.CitaFacadeService;

/**
 *
 * @author Angel
 */
@WebServlet(name = "testWS", urlPatterns = {"/testWS"})
public class testWS extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testWS</title>");            
            out.println("</head>");
            out.println("<body>");
            CitaFacadeService cfs = new CitaFacadeService();
            CitaFacade cita = cfs.getCitaFacadePort();
            List<Cita> citas = cita.listAll();
            for(Cita citaItem: citas){
                out.println("Usuario:"+citaItem.getUsuario().getNombre()+" Dentista:"+citaItem.getDentista().getEspecialidad()+"--"+citaItem.getFechaCita());
            }
            Calculator calc = new Calculator();
            ICalculator myCalculator = calc.getICalculator();
            out.println("http://soaptest.parasoft.com/calculator.wsdl");
            out.println("LA SUMA::"+myCalculator.add(10, 2)+"<br/>");
            out.println("LA REST::"+myCalculator.subtract(12, 1) +"<br/>");
            out.println("LA DIVISION::"+myCalculator.divide(100, 10) +"<br/>");
            out.println("LA MULTIPLICACION::"+myCalculator.multiply(1, 3) +"<br/>");
            

            out.println("<h1>Servlet testWS at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
