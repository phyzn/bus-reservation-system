package com.reservationapp.payload;

import com.reservationapp.entity.Driver;

import com.reservationapp.entity.SubRoute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Long busId;

    private String busNumber;
    private String busType;

    private double price;
    private int totalSeat;
    private int availableSeat;
    private RouteDto route;
    private List<SubRouteDto> subRoutes;



}
