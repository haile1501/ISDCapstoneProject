package itss.ecobike.models;

public class Bike {
    private String barcode;
    private int battery_percentage;
    private int dock_id;
    private String license_plate;
    private int bike_type_id;

    public Bike(String barcode, int battery_percentage, int dock_id, String license_plate, int bike_type_id) {
        this.barcode = barcode;
        this.battery_percentage = battery_percentage;
        this.dock_id = dock_id;
        this.license_plate = license_plate;
        this.bike_type_id = bike_type_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getBattery_percentage() {
        return battery_percentage;
    }

    public void setBattery_percentage(int battery_percentage) {
        this.battery_percentage = battery_percentage;
    }

    public int getDock_id() {
        return dock_id;
    }

    public void setDock_id(int dock_id) {
        this.dock_id = dock_id;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getBike_type_id() {
        return bike_type_id;
    }

    public void setBike_type_id(int bike_type_id) {
        this.bike_type_id = bike_type_id;
    }
}
