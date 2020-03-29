package com.bridgelabz.adapter;

import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.dto.USCensusCSV;
import com.bridgelabz.exception.MyCensusException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyCensusException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}
