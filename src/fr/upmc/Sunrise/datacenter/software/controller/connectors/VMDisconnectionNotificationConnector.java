package fr.upmc.Sunrise.datacenter.software.controller.connectors;

import fr.upmc.Sunrise.datacenter.software.controller.interfaces.VMDisconnectionNotificationHandlerI;
import fr.upmc.components.connectors.AbstractConnector;

public class VMDisconnectionNotificationConnector 
extends		AbstractConnector
implements	VMDisconnectionNotificationHandlerI {

	@Override
	public void receiveVMDisconnectionNotification(String vmURI) throws Exception {
		
		((VMDisconnectionNotificationHandlerI)this.offering).receiveVMDisconnectionNotification(vmURI) ;
	}

	@Override
	public void disconnectController() throws Exception {
		((VMDisconnectionNotificationHandlerI)this.offering).disconnectController();

	}

}
