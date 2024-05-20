package com.tracking.management.system.trackingmicroservice.usercase;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.entities.StatusHistory;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class OrderStatusUserCase {

//    @Autowired
//    private Clock clock;
//
//    public boolean hasStock(List<StatusHistory> statusHistory) {
//        return statusHistory != null && statusHistory.stream()
//                .anyMatch(status -> TrackingStatus.STOCK_SEPARATION.compareTo(status.getStatus()) == 0);
//    }
//
//    public boolean hasTransportantionHistory(List<StatusHistory> statusHistory) {
//        return statusHistory != null && statusHistory.stream()
//                .anyMatch(status -> TrackingStatus.SHIPPED.equals(status.getStatus()) || OrderStatus.ON_CARRIAGE.equals(status.getStatus()));
//    }
//
//    public boolean hasTransportantionHistory(TrackingStatus status) {
//        return List.of(OrderStatus.SHIPPED, TrackingStatus.ON_CARRIAGE).contains(status);
//    }
//
//    public void updateStatusCanceled(Order order, OrderCancellationType cancellationType, LocalDateTime processAt) throws BusinessException {
//        if (OrderStatus.DELIVERED.equals(order.getStatus())) {
//            throw new BusinessException(MessageUtil.getMessage("MESSAGE_STATUS_DELIVERED_TO_CANCEL"));
//        }
//
//        order.setStatus(OrderStatus.CANCELED);
//        order.setMotive(cancellationType);
//
//        StatusHistory statusHistory = new StatusHistory(OrderStatus.CANCELED, processAt == null ? LocalDateTime.now(clock) : processAt);
//        order.getStatusHistory().add(statusHistory);
//    }
//
//    public void updateToOnCarrier(Order order, String trackingNumber, String urlTracking) throws BusinessException {
//        if (trackingNumber == null || trackingNumber.trim().isEmpty()) {
//            throw new IllegalArgumentException(MessageUtil.getMessage("MESSAGE_STATUS_UPDATE_TRACKING_REQUIRED"));
//        }
//
//        if (!OrderStatus.SHIPPED.equals(order.getStatus())) {
//            throw new BusinessException("MESSAGE_STATUS_UPDATE_INVALID", order.getStatus().toString(), OrderStatus.ON_CARRIAGE.toString());
//        }
//
//        order.setStatus(TrackingStatus.ON_CARRIAGE);
//        order.getShipment().setTracking(trackingNumber);
//        order.getShipment().setUrlTracking(urlTracking);
//
//        StatusHistory statusHistory = new StatusHistory(OrderStatus.ON_CARRIAGE, LocalDateTime.now(clock));
//        order.getStatusHistory().add(statusHistory);
//    }
//
//    public boolean validateStatusMap(Delivery delivery, TrackingStatus newStatus) {
//        TrackingStatus currentStatus = delivery.getStatus();
//
//        Map<TrackingStatus, Set<TrackingStatus>> validTransitions = new HashMap<>();
//        validTransitions.put(TrackingStatus.STOCK_SEPARATION, new HashSet<>(Arrays.asList(TrackingStatus.PROCESSING, TrackingStatus.WAITING_PAYMENT)));
//        validTransitions.put(TrackingStatus.SHIPPING_READY, new HashSet<>(Collections.singletonList(TrackingStatus.SHIPPED)));
//        validTransitions.put(TrackingStatus.PENDING, new HashSet<>(Arrays.asList(TrackingStatus.PROCESSING, TrackingStatus.STOCK_SEPARATION)));
//        validTransitions.put(TrackingStatus.PROCESSING, new HashSet<>(Arrays.asList(TrackingStatus.STOCK_SEPARATION, TrackingStatus.WAITING_PAYMENT)));
//        validTransitions.put(TrackingStatus.WAITING_PAYMENT, new HashSet<>(Collections.singletonList(TrackingStatus.PAYMENT_ACCEPT)));
//        validTransitions.put(TrackingStatus.PAYMENT_ACCEPT, new HashSet<>(Collections.singletonList(TrackingStatus.SHIPPING_READY)));
//
//        if (validTransitions.containsKey(currentStatus) && validTransitions.get(currentStatus).contains(newStatus)) {
//            delivery.setStatus(newStatus);
//
//            StatusHistory statusHistory = new StatusHistory(newStatus, LocalDateTime.now(clock));
//            delivery.getStatusHistory().add(statusHistory);
//
//            if (OrderStatus.PAYMENT_ACCEPT.equals(newStatus)) {
//                delivery.getPayment().setStatus(PaymentStatus.AUTHORIZED);
//            }
//
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean hasValidCustomer(List<StatusHistory> statusHistory) {
//        return statusHistory != null && statusHistory.stream()
//                .anyMatch(status -> OrderStatus.PROCESSING.compareTo(status.getStatus()) == 0);
//    }
//
//    public boolean moveToWaitingPayment(List<StatusHistory> statusHistory, OrderStatus currentStatus) {
//        return ((currentStatus == OrderStatus.PROCESSING && hasStock(statusHistory)) ||
//                (currentStatus == OrderStatus.STOCK_SEPARATION && hasValidCustomer(statusHistory)));
//    }
//
//    public void updateToDelivered(Order order) throws BusinessException {
//        if (!OrderStatus.ON_CARRIAGE.equals(order.getStatus())) {
//            throw new BusinessException("MESSAGE_STATUS_UPDATE_INVALID", order.getStatus().toString(), OrderStatus.ON_CARRIAGE.toString());
//        }
//
//        order.setStatus(OrderStatus.DELIVERED);
//
//        order.getStatusHistory().add(new StatusHistory(OrderStatus.DELIVERED, LocalDateTime.now(clock)));
//    }
}