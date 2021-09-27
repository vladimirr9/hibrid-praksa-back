package com.hybrid.internship.dto;

import javax.validation.constraints.NotNull;

public class RentRequestDTO {
    @NotNull
    private Long userId;

    public RentRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }
}
