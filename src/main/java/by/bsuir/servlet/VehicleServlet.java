package by.bsuir.servlet;

import by.bsuir.autobase.dao.DaoFactory;
import by.bsuir.autobase.entity.Vehicle;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class VehicleServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(String.valueOf(VehicleServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Vehicle> vehicles = null;
        vehicles = DaoFactory.getVehicleDAO().getVehicles();

        req.setAttribute("vehicleList", vehicles);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/vehicle.jsp");
        requestDispatcher.forward(req, resp);
    }
}
