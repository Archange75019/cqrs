package com.appsdevelopperblog.estore.core.events;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ProductReservedEvent {

    private final String productId;
    private final int quantity;
    private final String orderId;
    private final String userId;
}
