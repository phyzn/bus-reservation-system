package com.reservationapp.service;

import com.reservationapp.entity.Route;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusService busService;

    //check if there is bus then add route
    public Route createRoute(long busId,Route route){
        busRepository.findById(busId).orElseThrow(()->new ResourceNotFound("Bus not added first add Bus"));

        Route r = routeRepository.findByBusId(route.getBusId());

        if(r!=null){
            throw new ResourceNotFound("Route was already added");
        }

        if(r==null){
            // Set the busId from the path variable
            route.setBusId(busId);
            routeRepository.save(route);
            return route;
        }
        return null;
    }
}
