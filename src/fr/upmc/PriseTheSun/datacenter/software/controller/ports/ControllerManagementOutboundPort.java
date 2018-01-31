package fr.upmc.PriseTheSun.datacenter.software.controller.ports;

import fr.upmc.PriseTheSun.datacenter.software.controller.interfaces.ControllerRingManagementI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
/**
* The class <code>ControllerManagementOutboundPort</code> implements the
 * inbound port through which the component management methods are called.
* 
* 
* @author	Maxime LAVASTE Lo�c Lafontaine
*/
public class ControllerManagementOutboundPort extends AbstractOutboundPort
implements	ControllerRingManagementI{

    /***
     * 
     * @param owner       owner component
     * @throws Exception e
     */
	public	ControllerManagementOutboundPort(ComponentI owner) throws Exception
	{
		super(ControllerRingManagementI.class, owner) ;
		assert	owner != null ;
	}

	/***
	 *  
	 * @param uri             uri of the component
	 * @param owner           owner component
	 * @throws Exception e
	 */
	public	ControllerManagementOutboundPort(String uri, ComponentI owner) throws Exception
	{
		super(uri, ControllerRingManagementI.class, owner);
		assert	owner != null;
	}

	@Override
	public void bindSendingDataUri(String DataInboundPortUri) throws Exception {
		((ControllerRingManagementI)this.connector).bindSendingDataUri(DataInboundPortUri);
	}

	@Override
	public void setNextManagementInboundPort(String managementInboundPort) throws Exception {
		((ControllerRingManagementI)this.connector).setNextManagementInboundPort(managementInboundPort);		
	}

	@Override
	public void setPreviousManagementInboundPort(String managementInboundPort) throws Exception {
		((ControllerRingManagementI)this.connector).setPreviousManagementInboundPort(managementInboundPort);
	}

	@Override
	public void stopPushing() throws Exception {
		((ControllerRingManagementI)this.connector).stopPushing();
	}

	@Override
	public void startPushing() throws Exception {
		((ControllerRingManagementI)this.connector).startPushing();
	}

}
