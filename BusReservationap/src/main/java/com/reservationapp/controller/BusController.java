package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SearchListOfBusesDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    @Autowired
    private BusService busService;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @PostMapping("/saveBus")
    public ResponseEntity<String> saveBus(
            @RequestBody BusDto busDto
            ) throws ParseException {

        busService.addBus(busDto);
        return new ResponseEntity<>("Bus Detail Added", HttpStatus.CREATED);
    }

    //api/buses?fromLocation=&toLocation=&fromDate
    @GetMapping
    public List<SearchListOfBusesDto> getAllBuses(@RequestParam String fromLocation,
                                 @RequestParam String toLocation,
                                 @RequestParam String fromDate) {
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,
                toLocation, fromDate);
        List<SubRoute> subroutes = subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,
                toLocation, fromDate);
        List<SearchListOfBusesDto> buses = new ArrayList<>();
        if(routes!=null){
            for(Route route:routes){
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);
            }
            return buses;
        }
        if(subroutes!=null){
            for(SubRoute route:subroutes){
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);
            }
            return buses;
        }
        return null;
      }
      SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, Route route){
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setTotalSeat(bus.getTotalSeat());
        searchListOfBusesDto.setAvailableSeat(bus.getAvailableSeat());

        searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        return searchListOfBusesDto;
      }
    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, SubRoute route){
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setTotalSeat(bus.getTotalSeat());
        searchListOfBusesDto.setAvailableSeat(bus.getAvailableSeat());

        //searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        return searchListOfBusesDto;
    }
    }



