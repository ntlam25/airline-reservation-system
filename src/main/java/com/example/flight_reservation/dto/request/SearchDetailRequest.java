package com.example.flight_reservation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDetailRequest {
    String column;
    String value;
    Operation operation;
    String joinTable;

    public enum Operation{
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN,COUNT
    }
}
