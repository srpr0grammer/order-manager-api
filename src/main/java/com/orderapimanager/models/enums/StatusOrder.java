package com.orderapimanager.models.enums;

import com.orderapimanager.service.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum StatusOrder {

    PENDING(1, "PENDING"),
    COMPLETED(2, "COMPLETED"),
    CANCELLED(3, "CANCELLED");

    private int cod;
    private String descricao;

    private StatusOrder(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }


    public int getCod() {
        return cod;
    }

    private String getDescricao() {
        return descricao;
    }

    public static StatusOrder toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (StatusOrder x : StatusOrder.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new BusinessException("Id Invalid.", HttpStatus.NOT_FOUND.value());
    }
}