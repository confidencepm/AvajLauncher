package com.avajLauncher.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.avajLauncher.simulator.vehicles.AircraftFactory;
import com.avajLauncher.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {
	// write your code here

        //System.out.println("Hello Avaj-Launcher!!!");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
//                System.out.println(simulations);
                if (simulations <= 0) {
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    System.out.println(line);
                    flyables.add(flyable);
                }

                for (Flyable flyable: flyables) {
                    flyable.registerTower(weatherTower);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Path to input file not found!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error while reading file!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("The value provided is not an integer!");
            System.exit(1);
        }
    }
}
