/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.estore.OrdersService.command.rest;

import com.appsdeveloperblog.estore.OrdersService.core.model.OrderStatus;
import com.appsdeveloperblog.estore.OrdersService.command.commands.CreateOrderCommand;
import java.util.UUID;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private final Environment env;

    private final CommandGateway commandGateway;

    @Autowired
    public OrdersCommandController(CommandGateway commandGateway, Environment env) {
        this.commandGateway = commandGateway;
        this.env = env;

    }

    @PostMapping
    public String createOrder(@Valid @RequestBody OrderCreateRest order) {

        String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .addressId(order.getAddressId())
                .productId(order.getProductId())
                .userId(userId)
                .quantity(order.getQuantity())
                .orderId(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.CREATED)
                .build();

        return commandGateway.sendAndWait(createOrderCommand);

    }

}
