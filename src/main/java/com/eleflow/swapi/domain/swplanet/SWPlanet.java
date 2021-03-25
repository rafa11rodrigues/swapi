package com.eleflow.swapi.domain.swplanet;

import java.util.List;

public class SWPlanet {

    private String name;
    private String diameter;
    private String rotation_period;
    private String orbital_period;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String surface_water;
    private List<String> residents;
    private List<String> films;
    private String url;
    private String created;
    private String edited;


    public String getName() {
        return name;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public String getGravity() {
        return gravity;
    }

    public String getPopulation() {
        return population;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public List<String> getResidents() {
        return residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }


    @Override
    public String toString() {
        return "SWPlanet{" +
                "name='" + name + '\'' +
                ", diameter='" + diameter + '\'' +
                ", rotation_period='" + rotation_period + '\'' +
                ", orbital_period='" + orbital_period + '\'' +
                ", gravity='" + gravity + '\'' +
                ", population='" + population + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", surface_water='" + surface_water + '\'' +
                ", residents=" + residents +
                ", films=" + films +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                '}';
    }
}
