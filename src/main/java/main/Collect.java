package main;

public class Collect {
    double temperature;
    float vibration;
    int humidity;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public float getVibration() {
        return vibration;
    }

    public void setVibration(float vibration) {
        this.vibration = vibration;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void getCollec(double temperature, float vibration, int humidity) {
        this.temperature = getTemperature();
        this.vibration = getVibration();
        this.humidity = getHumidity();
    }



}




