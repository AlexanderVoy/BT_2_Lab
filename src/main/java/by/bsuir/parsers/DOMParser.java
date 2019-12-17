package by.bsuir.parsers;

import by.bsuir.autobase.entity.FuelType;
import by.bsuir.autobase.entity.Vehicle;
import com.sun.java.browser.plugin2.DOM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DOMParser {
    public static final String VEHICLE = "vehicle";
    private List<Vehicle> vehicles;
    private static Logger log = Logger.getLogger(String.valueOf(DOM.class));

    public List<Vehicle> getResult(String XMLPath) throws ParserConfigurationException, IOException, SAXException {

        log.info("DOM: Parsing started");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XMLPath));

        NodeList vehicleElements = document.getDocumentElement().getElementsByTagName(VEHICLE);

        vehicles = new ArrayList<>();
        for (int i = 0; i < vehicleElements.getLength(); i++) {

            Node nNode = vehicleElements.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Vehicle vehicle = new Vehicle();

                vehicle.setMake(eElement.getElementsByTagName("make").item(0).getTextContent());
                vehicle.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
                vehicle.setPrice(Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent()));
                vehicle.setFuelConsumption(Double.parseDouble(eElement.getElementsByTagName("fuelConsumption").item(0).getTextContent()));
                vehicle.setPower(Integer.parseInt(eElement.getElementsByTagName("power").item(0).getTextContent()));
                vehicle.setYear(Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
                vehicle.setFuelType(FuelType.valueOf(eElement.getElementsByTagName("fuelType").item(0).getTextContent()));

                vehicles.add(vehicle);
            }
        }
        log.info("DOM: Parsing completed");
        return vehicles;
    }
}
