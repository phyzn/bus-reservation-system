package com.reservationapp.service;


import com.reservationapp.entity.SubRoute;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRouteService {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    public SubRoute createSubRoute(long busId,SubRoute subRoute){
        busRepository.findById(busId).orElseThrow(()->new ResourceNotFound("Bus not added first add Bus"));

        SubRoute r = subRouteRepository.findByBusId(subRoute.getBusId());

        if(r!=null){
            throw new ResourceNotFound("SubRoute was already added");
        }
        if(r==null){
            // Set the busId from the path variable
            subRoute.setBusId(busId);
            subRouteRepository.save(subRoute);
            return subRoute;
        }
        return null;
    }
}
