package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Passenger;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;

import com.reservationapp.payload.ReservationDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.PassengerRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.util.EmailService;
import com.reservationapp.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;
    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;
    @Autowired
    private EmailService emailService;
    @PostMapping
    public ResponseEntity<String> bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestBody Passenger passenger
    ){
        boolean busIsPresent=false;
        boolean routeIsPresent=false;
        boolean subRouteIsPresent=false;

        Optional<Bus> byId = busRepository.findById(busId);
          if(byId.isPresent()){
              busIsPresent=true;
              Bus bus = byId.get();
          }
        Optional<Route> byRouteId = routeRepository.findById(routeId);
          if(byRouteId.isPresent()){
              routeIsPresent=true;
              Bus bus = byId.get();
          }
        Optional<SubRoute> subRoute = subRouteRepository.findById(routeId);
          if(byRouteId.isPresent()){
              subRouteIsPresent=true;
              Bus bus = byId.get();
          }

          if(busIsPresent&&routeIsPresent || busIsPresent&&subRouteIsPresent){
              //add passenger detail
              Passenger p = new Passenger();
              p.setFirstName(passenger.getFirstName());
              p.setLastName(passenger.getLastName());
              p.setEmail(passenger.getEmail());
              p.setMobile(passenger.getMobile());
              p.setRouteId(routeId);
              p.setBusId(busId);

              Passenger savedPassenger = passengerRepository.save(p);
              byte[] pdfBytes = pdfTicketGeneratorService.generateTicket(savedPassenger, byRouteId.get().getFromLocation(),
                      byRouteId.get().getToLocation(), byRouteId.get().getFromDate());
              emailService.sendEmailWithAttachment(passenger.getEmail(),
                      "Booking Confirmed...",
                      "Your Reservation details id"+savedPassenger.getId(),pdfBytes,
                      "Tickets");

          }
        return new ResponseEntity<>("done", HttpStatus.CREATED);
    }
}
