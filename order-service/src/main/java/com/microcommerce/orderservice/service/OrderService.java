package com.microcommerce.orderservice.service;

import com.microcommerce.common.data.ProductResponse;
import com.microcommerce.common.validation.*;
import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.orderservice.data.dto.EventOrderItemDto;
import com.microcommerce.orderservice.data.dto.OrderRequest;
import com.microcommerce.orderservice.data.dto.OrderResponse;
import com.microcommerce.orderservice.data.dto.UserDto;
import com.microcommerce.orderservice.data.entity.Order;
import com.microcommerce.orderservice.data.entity.OrderItem;
import com.microcommerce.orderservice.data.event.OrderCreatedEvent;
import com.microcommerce.orderservice.feign.ProductClient;
import com.microcommerce.orderservice.repository.OrderRepository;
import com.microcommerce.orderservice.util.ApiResponseFactory;
import com.microcommerce.orderservice.util.OrderUtils;
import com.microcommerce.orderservice.util.mapstrcut.OrderItemMapper;
import com.microcommerce.orderservice.util.validation.ProductsInStockValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    private final ApiResponseFactory<OrderResponse> responseFactory;
    private final SimpleValidator<OrderRequest> simpleValidator;

    private final ConstraintValidationRule<OrderRequest> orderRequestConstraintValidationRule;
    private final ProductsInStockValidationRule productsInStockValidationRule;

    private final KafkaTemplate<UUID, OrderCreatedEvent> kafkaTemplate;
    private final ProductClient productClient;

    @Value("${kafka.topics.notification-topic}")
    private String notificationTopic;

    @Transactional
    public ApiResponse<OrderResponse> createOrder(OrderRequest orderRequest) {
        var constraintValidationResult = simpleValidator.validate(orderRequest, orderRequestConstraintValidationRule);
        if (constraintValidationResult.isNotValid())
            return responseFactory.badRequest(constraintValidationResult.getMessages());

        // TODO: Error handling
        List<ProductResponse> enrichedProducts = productClient.getProducts(OrderUtils.extractSkuCodes(orderRequest));
        productsInStockValidationRule.setProducts(enrichedProducts);

        var productInStockValidationResult = simpleValidator.validate(orderRequest, productsInStockValidationRule);
        if (productInStockValidationResult.isNotValid())
            return responseFactory.badRequest(productInStockValidationResult.getMessages());

        List<EventOrderItemDto> orderItems = OrderUtils.getEventOrderItems(orderRequest, enrichedProducts);
        String orderNumber = OrderUtils.generateOrderNumber();

        Set<OrderItem> entityOrderItems = orderRequest.getOrderItems()
                .stream()
                .map(orderItemMapper::toEntity)
                .collect(Collectors.toSet());

        var orderEntity = Order.builder()
                .orderItems(entityOrderItems)
                .orderNumber(orderNumber)
                .totalAmount(orderItems.stream().map(EventOrderItemDto::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();

        orderRepository.save(orderEntity);
        produceOrderCreatedEvent(orderItems, orderNumber, orderRequest.getUser());

        return responseFactory.successfulOperation();
    }

    private void produceOrderCreatedEvent(List<EventOrderItemDto> orderItems, String orderNumber, UserDto user) {
        var orderCreatedEvent = OrderCreatedEvent.builder()
                .orderItems(orderItems)
                .orderNumber(orderNumber)
                .user(user)
                .build();

        var completableFuture = kafkaTemplate.send(notificationTopic, UUID.randomUUID(), orderCreatedEvent);

        completableFuture.whenComplete((result, exception) -> {
            if (exception != null) {
                completableFuture.completeExceptionally(exception);
            } else {
                completableFuture.complete(result);
            }
        });
    }

    public ApiResponse<OrderResponse> updateOrder(OrderRequest orderRequest) {
        throw new NotImplementedException();
    }

    public ApiResponse<Void> deleteOrder(String orderNumber) {
        throw new NotImplementedException();
    }

    public ApiResponse<List<OrderResponse>> getAllOrders() {
        throw new NotImplementedException();
    }

    public ApiResponse<List<OrderResponse>> getOrderByOrderNumber(String orderNumber) {
        throw new NotImplementedException();
    }

    public ApiResponse<List<OrderResponse>> getOrderByUserId(String userId) {
        throw new NotImplementedException();
    }
}
