package com.appsdeveloperblog.estore.ProductService.command.rest;
import com.appsdeveloperblog.estore.ProductService.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")//http://localhost:8080/products
public class ProductsCommandController {

    private Environment env;
    private final CommandGateway commandGateway;
    @Autowired
    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        this.env = env;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel){

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        String returnValue;

        returnValue = commandGateway.sendAndWait(createProductCommand);
//        try {
//            returnValue = commandGateway.sendAndWait(createProductCommand);
//        }catch (Exception ex){
//            returnValue = ex.getLocalizedMessage();
//        }
        return returnValue;
    }
}
