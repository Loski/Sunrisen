package fr.upmc.Sunrise.datacenter.software.admissioncontroller.ports;

import fr.upmc.Sunrise.datacenter.software.admissioncontroller.interfaces.AdmissionControllerManagementI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;


public class AdmissionControllerManagementInboundPort extends AbstractInboundPort implements fr.upmc.Sunrise.datacenter.software.admissioncontroller.interfaces.AdmissionControllerManagementI{

	public AdmissionControllerManagementInboundPort(Class<?> implementedInterface, ComponentI owner) throws Exception {
		super(AdmissionControllerManagementI.class, owner);
		assert	owner != null && owner instanceof AdmissionControllerManagementI ;
	}

	
	public AdmissionControllerManagementInboundPort(String uri, Class<?> implementedInterface, ComponentI owner)
			throws Exception {
		super(uri, AdmissionControllerManagementI.class, owner);
		assert	owner != null && owner instanceof AdmissionControllerManagementI ;
	}



	/**
	 * @see fr.upmc.Sunrise.datacenter.software.admissioncontroller.interfaces.AdmissionControllerManagementI#linkComputer(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void linkComputer(final String computerURI, final String ComputerServicesInboundPortURI,
			final String ComputerStaticStateDataInboundPortURI, final String ComputerDynamicStateDataInboundPortURI)
			throws Exception {
		final AdmissionControllerManagementI acm = ( AdmissionControllerManagementI ) this.owner;

		 this.owner.handleRequestSync(
				new ComponentI.ComponentService<Void>() {
					@Override
					public Void call() throws Exception {
						acm.linkComputer(computerURI, ComputerServicesInboundPortURI, ComputerStaticStateDataInboundPortURI, ComputerDynamicStateDataInboundPortURI);
						return null;
					}
				}
		);
	}


	
	
}
