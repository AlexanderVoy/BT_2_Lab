package by.bsuir.autobase.entity;

import by.bsuir.autobase.controller.Controller;
import by.bsuir.autobase.dao.DaoFactory;

import java.util.ArrayList;

/**
 * The type Purchase.
 */
public class Purchase implements java.io.Serializable{

    private User customer;
    private Vehicle vehicle;
    private int raceDistance;

    /**
     * Instantiates a new Purchase.
     */
    public Purchase() {
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Get race distance int.
     *
     * @return the int
     */
    public int getRaceDistance(){return raceDistance;}

    /**
     * Set race distance.
     *
     * @param raceDistance the race distance
     */
    public void setRaceDistance(int raceDistance){ this.raceDistance = raceDistance;}

    /**
     * Gets vehicle.
     *
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets vehicle.
     *
     * @param vehicle the vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Add purchase.
     *
     * @param purchase the purchase
     */
    public static void addPurchase(Purchase purchase){
        DaoFactory.getPurchaseDAO().insert(purchase);
    }

    /**
     * Get purchases.
     */
    public static void getPurchases(){
        ArrayList<Object> purchases = new ArrayList<Object>();
        if (DaoFactory.getPurchaseDAO().getPurchases() != null)
            purchases.addAll(DaoFactory.getPurchaseDAO().getPurchases());

        Controller.printListRequest(purchases);
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Purchase that = (Purchase) o;

        if ( != that.deceased) {
            return false;
        }

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }*/
    @Override
    public String toString() {
        return  "\n Vehicle:" + vehicle.toString() +
                "\n Customer: " + customer.getName() +
                "\n Race distance: " + this.raceDistance;
    }

}
