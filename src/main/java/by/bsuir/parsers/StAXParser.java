package by.bsuir.parsers;

import by.bsuir.autobase.entity.FuelType;
import by.bsuir.autobase.entity.Vehicle;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

public class StAXParser implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private static Logger log = Logger.getLogger(String.valueOf(StAXParser.class));
    private final XMLStreamReader reader;

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public StAXParser(XMLStreamReader reader) {
        this.reader = reader;
    }

    public ArrayList<Vehicle> getResult() throws XMLStreamException {

        boolean endOfVehicleElement;
        log.info("StAX: Parsing started");
        while (reader.hasNext()) {       // while not end of XML
            int event = reader.next();   // read next event
            if (event == XMLEvent.START_ELEMENT &&
                    "vehicle".equals(reader.getLocalName())) {
                Vehicle vehicle = new Vehicle();

                endOfVehicleElement = false;
                while (reader.hasNext())
                {
                    if (reader.next() == XMLEvent.START_ELEMENT)
                        switch (reader.getLocalName())
                        {
                            case "make":
                                vehicle.setMake(reader.getElementText());
                                break;
                            case "model":
                                vehicle.setModel(reader.getElementText());
                                break;
                            case "price":
                                vehicle.setPrice(Double.parseDouble(reader.getElementText()));
                                break;
                            case "fuelConsumption":
                                vehicle.setFuelConsumption(Double.parseDouble(reader.getElementText()));
                                break;
                            case "power":
                                vehicle.setPower(Integer.parseInt(reader.getElementText()));
                                break;
                            case "year":
                                vehicle.setYear(Integer.parseInt(reader.getElementText()));
                                break;
                            case "fuelType":
                                vehicle.setFuelType(FuelType.valueOf(reader.getElementText()));
                                endOfVehicleElement = true;
                                break;
                        }
                    if (endOfVehicleElement)
                        break;
                }

                vehicles.add(vehicle);
            }
        }
        log.info("StAX: Parsing completed");
        return vehicles;
    }

    public StAXParser(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }


    public XMLStreamReader getReader() {
        return reader;
    }


    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) { // empty
            }
        }
    }
}