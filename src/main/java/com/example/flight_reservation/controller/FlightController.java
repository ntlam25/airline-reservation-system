package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.FlightRequest;
import com.example.flight_reservation.dto.request.SearchRequest;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.entity.Flight;
import com.example.flight_reservation.repository.FlightRepository;
import com.example.flight_reservation.service.Filter.FilterService;
import com.example.flight_reservation.service.Flight.IFlightService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/flights")

public class FlightController {
    private final IFlightService service;
    private final FilterService<Flight> filterService;
    private final FlightRepository repository;

    public FlightController(IFlightService service, FilterService<Flight> filterService, FlightRepository repository) {
        this.service = service;
        this.filterService = filterService;
        this.repository = repository;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightResponse> create(@RequestBody FlightRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public FlightResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @GetMapping("/all")
    public List<FlightResponse> getFlight(){
        return service.findAll();
    }
    @PutMapping("/{id}")
    public FlightResponse updateById(@PathVariable Long id, @RequestBody FlightRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping("/filter")
    public List<Flight> findFlightBySpecification(@RequestBody SearchRequest request){
        Specification<Flight> responseSpecification = filterService
                .getSearchSpecification(request.getSearchDetailRequest(),request.getGlobalOperator());
        return repository.findAll(responseSpecification);
    }
    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(
            @RequestParam String depAirport,
            @RequestParam String arrAirport,
            @RequestParam String depDate) {

        List<FlightResponse> flights = service.searchFlights(depAirport, arrAirport, depDate);
        return ResponseEntity.ok(flights);
    }
}
