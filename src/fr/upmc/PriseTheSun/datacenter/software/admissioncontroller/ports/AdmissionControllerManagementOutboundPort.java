package fr.upmc.PriseTheSun.datacenter.software.admissioncontroller.ports;

import fr.upmc.PriseTheSun.datacenter.software.admissioncontroller.interfaces.AdmissionControllerManagementI;
import fr.upmc.PriseTheSun.datacenter.software.requestdispatcher.interfaces.RequestDispatcherManagementI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class AdmissionControllerManagementOutboundPort extends		AbstractOutboundPort
implements	fr.upmc.PriseTheSun.datacenter.software.admissioncontroller.interfaces.AdmissionControllerManagementI{

	public	AdmissionControllerManagementOutboundPort(String uri, ComponentI owner) throws Exception
	{
		super(uri, AdmissionControllerManagementI.class, owner);
	}

	public AdmissionControllerManagementOutboundPort(String uri, Class<?> implementedInterface, ComponentI owner)
			throws Exception {
		super(uri, AdmissionControllerManagementI.class, owner);
		assert	owner != null && owner instanceof AdmissionControllerManagementI;
	}

	@Override
	public int addCores(String controllerURI, String vmUri, int nbCores) throws Exception {
		return ( ( AdmissionControllerManagementI ) this.connector ).addCores(controllerURI, null, nbCores);
	}

	@Override
	public void linkComputer(String computerURI, String ComputerServicesInboundPortURI,
			String ComputerStaticStateDataInboundPortURI, String ComputerDynamicStateDataInboundPortURI)
			throws Exception {
		((AdmissionControllerManagementI)this.connector).linkComputer(computerURI, ComputerServicesInboundPortURI, ComputerStaticStateDataInboundPortURI, ComputerDynamicStateDataInboundPortURI);	
	}

	@Override
	public boolean supCores(int nbCores, String vmUri) throws Exception {
		return ( ( AdmissionControllerManagementI ) this.connector ).supCores(nbCores, vmUri);
	}

	@Override
	public void releaseCore(String controllerURI, String VMURI) throws Exception {
		( ( AdmissionControllerManagementI ) this.connector ).releaseCore(controllerURI, VMURI);
	}
}
