package com.map.valeponto.controllers.error;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}


