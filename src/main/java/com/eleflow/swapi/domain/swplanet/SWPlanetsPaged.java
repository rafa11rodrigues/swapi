package com.eleflow.swapi.domain.swplanet;

import java.util.List;

public class SWPlanetsPaged {

    private Integer count;
    private String next;
    private String previous;
    private List<SWPlanet> results;


    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<SWPlanet> getResults() {
        return results;
    }


    @Override
    public String toString() {
        return "SWPlanetsPaged{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
