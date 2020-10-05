package com.company;

import com.google.gson.Gson;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;
/*
Наступила осень и в нашем городе похолодало.
Вы находитесь в аэропорту Иркутска и решаете, куда можно полететь.
Используйте сервис OpenWeatherMap, чтобы выбрать город с наиболее тёплой погодой.
Вам необходимо поиском по OpenWeatherMap собрать ID как минимум 10 городов.
Например, для Шелехова это 2016764.
Ваша программа должна вывести города и температуру в них в порядке убывания температуры.
 список городов считать из файла или запросить у пользователя
В качестве ответа приложите исходный год класса(-ов) и текстовый файл с примером работы программы.
 */

public class Main {
    public static Info getTempByCity(String name) {
        String my_ID = "595e89aedc071ff307b3f89a66d40c78";
        //System.out.println(0x0bp3);
        Gson gson = new Gson();
        String API_URL = "https://api.openweathermap.org/data/2.5/weather?q="+name+"&appid="+my_ID;

        Info weather_city = new Info();

        try {
            URL url = new URL(API_URL);
            InputStream stream = (InputStream) url.getContent();
            // передаём сетевой поток специальному считывателю
            InputStreamReader reader = new InputStreamReader(stream);
            weather_city = gson.fromJson(reader, Info.class); // создать нужные классы
            //System.out.println(weather_city.id);

        } catch (IOException e) {
            System.out.println(e.getMessage()); // в случае исключения выдаём ошибку на экран
        }
        return weather_city; // вернуть объект weather с id, названием города и инф-ей о погоде
    }

    public static ArrayList<String> getCities(String filename) {
        //считать города из файла
        ArrayList<String> cities = new ArrayList<String>();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cities.add(line);
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cities;
    }

    public static ArrayList<Info> getTempByCitys(String filename){
        //считываем города из файла, создаем массив из объектов Info(id города, название города, ин-я о погоде)
        ArrayList<String> cities = getCities(filename);
        ArrayList <Info> temps = new ArrayList<>();

        for (String city : cities) {
            Info getObjectInfo = getTempByCity(city);
            temps.add(getObjectInfo);
        }

        Collections.sort(temps);

        return temps;
    }

    public static void main(String[] args) {
        //в аргументе передаем наш файл
        ArrayList<Info> temps = getTempByCitys("cities.txt");

    }
}