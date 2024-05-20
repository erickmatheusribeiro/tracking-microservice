package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.frameworks.db.DeliveryRepository;
import com.tracking.management.system.trackingmicroservice.util.MessageUtil;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdeningWeb {
    @Autowired
    private OrdeningWebInterface ordeningWebInterface;

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void updateStatusOrder(Integer deliveryId) throws ExternalInterfaceException {
        Delivery delivery = deliveryRepository.findById(deliveryId).get();

        try{
            if(delivery.getStatus().equals(TrackingStatus.DELIVERED)){
                ordeningWebInterface.updateStatusOrderDelivered(Integer.valueOf(delivery.getOrderId()));
            } else if (delivery.getStatus().equals(TrackingStatus.ON_CARRIAGE)) {
                ordeningWebInterface.updateStatusOrderOnCarrier(Integer.valueOf(delivery.getOrderId()),
                        delivery.getTrakingCode(),
                        "http://www.melhorrastreio.com.br/" + delivery.getTrakingCode());
            } else if (delivery.getStatus().equals(TrackingStatus.CANCELED)) {
                ordeningWebInterface.updateStatusOrderCanceled(Integer.valueOf(delivery.getOrderId()));
            }
        }catch (FeignException exception) {
            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_EXTERNAL_SERVICE_ORDER", delivery.getId().toString()), exception);
        } catch (Exception exception) {
            throw new ExternalInterfaceException(MessageUtil.getMessage("LOG_GENERAL_EXCEPTION", "Alterar o status do pedido: " + delivery.getId().toString()), exception);
        }
    }
}
