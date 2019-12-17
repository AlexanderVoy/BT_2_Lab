package by.bsuir.parsers;

import by.bsuir.autobase.entity.FuelType;
import by.bsuir.autobase.entity.Vehicle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.logging.Logger;

public class SAXParser extends DefaultHandler {
    private Vehicle vehicle;
    private String thisElement = "";
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private static Logger log = Logger.getLogger(String.valueOf(SAXParser.class));

    public ArrayList<Vehicle> getResult(){  return vehicles;  }

    @Override
    public void startDocument() throws SAXException {
        log.info("SAX: Parsing started");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;

        if (qName.equals("vehicle")){
            vehicle = new Vehicle();
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
        if (qName.equals("vehicle")){
            vehicles.add(vehicle);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("make")) {
            vehicle.setMake(new String(ch, start, length));
        }
        if (thisElement.equals("model")) {
            vehicle.setModel(new String(ch, start, length));
        }
        if (thisElement.equals("price")) {
            vehicle.setPrice(Double.parseDouble(new String(ch, start, length)));
        }
        if (thisElement.equals("fuelConsumption")) {
            vehicle.setFuelConsumption(Double.parseDouble(new String(ch, start, length)));
        }
        if (thisElement.equals("power")) {
            vehicle.setPower(Integer.parseInt(new String(ch, start, length)));
        }
        if (thisElement.equals("year")) {
            vehicle.setYear(Integer.parseInt(new String(ch, start, length)));
        }
        if (thisElement.equals("fuelType")) {
            vehicle.setFuelType(FuelType.valueOf(new String(ch, start, length)));
        }
    }

    @Override
    public void endDocument() {
        log.info("SAX: Parsing completed");
    }
}
