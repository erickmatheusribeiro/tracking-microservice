package com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening;

import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.message.StatusMessage;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@Component
public class OrderingWeb {

    @Autowired
    private OrdeningWebInterface ordeningWebInterface;

    // TODO SE FICAR LANÇANDO A MENSAGEM ELA VOLTA PARA FILA DEVIDO A CONFIG, OU SEJA, FICARÁ EM EXECUÇÃO PARA SEMPRE
    public void udpateToOnCarrier(StatusMessage statusMessage) throws ExternalInterfaceException {
        try {
            ordeningWebInterface.updateStatusOnCarrier(parseInt(statusMessage.getOrderId()), statusMessage.getTracking(), statusMessage.getUrl());
        } catch (FeignException feignException) {
            String message = feignException.getMessage();

            if (message == null) {
                throw new ExternalInterfaceException(feignException);
            }

            // TODO seria interessante validar que se o pedido já foi cancelado para você também cancelar a entrega
            if (message.contains("de CANCELADO para")) {
                System.out.println(message);
                /*Se cancelado deveria fazer operação para cancelar a entrega e descartar a mensagem*/
            }

            throw new ExternalInterfaceException(feignException);
        } catch (Exception exception) {
            throw new ExternalInterfaceException(exception.getMessage(), exception);
        }
    }

    public void udpateToDelivered(StatusMessage statusMessage) throws ExternalInterfaceException {
        try {
            ordeningWebInterface.updateStatusDelivered(parseInt(statusMessage.getOrderId()));
        } catch (FeignException feignException) {
            String message = feignException.getMessage();

            if (message == null) {
                throw new ExternalInterfaceException(feignException);
            }

            // TODO seria interessante validar que se o pedido já foi cancelado para você também cancelar a entrega
            if (message.contains("de CANCELADO para")) {
                System.out.println(message);
                /*Se cancelado deveria fazer operação para cancelar a entrega e descartar a mensagem*/
            }

            throw new ExternalInterfaceException(feignException);
        } catch (Exception exception) {
            throw new ExternalInterfaceException(exception.getMessage(), exception);
        }
    }

    public void updateToCacel(StatusMessage statusMessage) throws ExternalInterfaceException {
        try {
            ordeningWebInterface.updateToCancel(parseInt(statusMessage.getOrderId()));
        } catch (FeignException feignException) {
            System.out.println(feignException);
        } catch (Exception exception) {
            throw new ExternalInterfaceException(exception.getMessage(), exception);
        }
    }
}
