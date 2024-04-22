package com.att.tdp.bisbis10.model.requests;

import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.model.DTO.OrderItemDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class AddOrderRequest {
    private Integer restaurantId;
    private List<OrderItemDTO> orderItems;

}
