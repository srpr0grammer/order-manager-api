package com.orderapimanager.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "This field cannot be null.")
    private Long itemId;

    @NotNull(message = "This field cannot be null.")
    private Long userId;

    @NotNull(message = "This field cannot be null.")
    private Integer quantidade;
}
