package fr.upmc.Sunrise.datacenter.software.controller.ports;

import fr.upmc.Sunrise.datacenter.software.controller.interfaces.VMDisconnectionNotificationHandlerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class VMDisconnectionNotificationHandlerOutboundPort 
extends AbstractOutboundPort
implements	VMDisconnectionNotificationHandlerI{

	public	VMDisconnectionNotificationHandlerOutboundPort(ComponentI owner) throws Exception
	{
		super(VMDisconnectionNotificationHandlerI.class, owner) ;
		assert	owner != null ;
	}
	
	public	VMDisconnectionNotificationHandlerOutboundPort(String uri, ComponentI owner) throws Exception
	{
		super(uri,VMDisconnectionNotificationHandlerI.class, owner) ;
		assert	owner != null ;
	}
	
	@Override
	public void receiveVMDisconnectionNotification(String vmURI) throws Exception {
		((VMDisconnectionNotificationHandlerI)this.connector).receiveVMDisconnectionNotification(vmURI);
	}

	@Override
	public void disconnectController() throws Exception {
		((VMDisconnectionNotificationHandlerI)this.connector).disconnectController();
	}

}
