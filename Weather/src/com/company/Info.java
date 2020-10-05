package com.company;

class Info implements Comparable<Info>{

    private Weather main;
    private String name;
    private int id;

    public Info(){
        main = this.main;
        name = this.name;
        id = this.id;
    }

    public Weather getWeather() {return this.main;}
    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public double getTemp() {return this.main.getTemp();}

    @Override
    public int compareTo(Info i) {
        if (this.getTemp() > i.getTemp())
            return 1;
        else if (this.getTemp() < i.getTemp())
            return -1;
        else return 0;
    }
}

class Weather {
    private double temp, feels_like, temp_min, temp_max, pressure, humidity;

    Weather() {
        temp = this.temp;
        feels_like = this.feels_like;
        temp_min = this.temp_min;
        temp_max = this.temp_max;
        pressure = this.pressure;
        humidity = this.humidity;
    }

    public double getTemp() { return temp;}
}
