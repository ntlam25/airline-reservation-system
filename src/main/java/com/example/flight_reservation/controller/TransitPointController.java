package com.example.flight_reservation.controller;
import com.example.flight_reservation.dto.request.TransitPointRequest;
import com.example.flight_reservation.dto.response.TransitPointResponse;
import com.example.flight_reservation.service.TransitPoint.ITransitPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transitpoints")
public class TransitPointController {
    private final ITransitPointService service;

    public TransitPointController(ITransitPointService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<TransitPointResponse> create(@RequestBody TransitPointRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public TransitPointResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @GetMapping("/all")
    public List<TransitPointResponse> getTransit(){
        return service.findAll();
    }
    @PutMapping("/{id}")
    public TransitPointResponse update(@PathVariable Long id, @RequestBody TransitPointRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

}
