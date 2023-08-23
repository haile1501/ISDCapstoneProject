package itss.ecobike.interfaces;

public interface Electric {
    int getBatteryPercentage();
    void setBatteryPercentage(int batteryPercentage);

    int getDuration();
}
