package itss.ecobike.models;

public interface Electric {
    int getBatteryPercentage();
    void setBatteryPercentage(int batteryPercentage);

    int getDuration();
}
