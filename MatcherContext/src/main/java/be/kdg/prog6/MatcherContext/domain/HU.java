package be.kdg.prog6.MatcherContext.domain;

import be.kdg.prog6.MatcherContext.adapters.out.ProductJPAEntity;
import jakarta.persistence.*;


public class HU {

    private String huNumber;


    public HU() {
    }

    public void initialiseHU(String huNumber) {
        this.huNumber = huNumber;
    }

    public String getHuNumber() {
        return huNumber;
    }



}
