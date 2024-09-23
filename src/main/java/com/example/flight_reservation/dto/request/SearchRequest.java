package com.example.flight_reservation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequest {
    private List<SearchDetailRequest> searchDetailRequest;

    private GlobalOperator globalOperator;

    public enum GlobalOperator{
        AND, OR
    }
}
