package itss.ecobike.controllers;

import itss.ecobike.dao.RentalDAO;
import itss.ecobike.entities.dto.RentalInfo;

public class RentalController {
    public static RentalInfo getRentalInfo(String bikeCode) throws Exception{
        return RentalDAO.getRentalInfo(bikeCode);
    }
}
