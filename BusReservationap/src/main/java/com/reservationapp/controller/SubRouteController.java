package com.reservationapp.controller;


import com.reservationapp.entity.SubRoute;
import com.reservationapp.service.SubRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/subroutes")
public class SubRouteController {

    @Autowired
    private SubRouteService subRouteService;

    @PostMapping("{busId}")
    public ResponseEntity<SubRoute> addSubRoute(@PathVariable long busId, @RequestBody SubRoute subRoute){
        SubRoute r = subRouteService.createSubRoute(busId, subRoute);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }
}
