package fr.upmc.datacenter.software.controller;

import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentShutdownException;
import fr.upmc.components.exceptions.ComponentStartException;
import fr.upmc.components.ports.PortI;
import fr.upmc.datacenter.hardware.computers.ports.ComputerServicesOutboundPort;
import fr.upmc.datacenter.software.applicationvm.ports.ApplicationVMManagementOutboundPort;
import fr.upmc.datacenter.software.ports.ApplicationNotificationInboundPort;
import fr.upmc.datacenterclient.applicationprovider.interfaces.ApplicationNotificationI;
import fr.upmc.datacenterclient.applicationprovider.interfaces.ApplicationSubmissionI;

public class AdmissionController extends AbstractComponent {
	
	 /** the URI of the component. */
    protected String apURI;

    protected ApplicationSubmissionInboundPort asip;

    protected ApplicationNotificationInboundPort anip;

    protected ApplicationVMManagementOutboundPort avmop;

    protected ComputerServicesOutboundPort csop;

    private int cpt = 0;


    public AdmissionController( String apURI , String applicationSubmissionInboundPortURI ,
            String applicationNotificationInboundPortURI , String computerServiceOutboundPortURI ) throws Exception {
        super( false , true );
        this.apURI = apURI;
        this.addRequiredInterface( ApplicationSubmissionI.class );
        this.asip = new ApplicationSubmissionInboundPort( applicationSubmissionInboundPortURI , this );
        this.addPort( asip );
        this.asip.localPublishPort();

        this.addRequiredInterface( ApplicationNotificationI.class );
        this.anip = new ApplicationNotificationInboundPort( applicationNotificationInboundPortURI , this );
        this.addPort( anip );
        this.anip.localPublishPort();

        this.csop = new ComputerServicesOutboundPort( computerServiceOutboundPortURI , this );
        this.addPort( csop );
        this.csop.localPublishPort();
}
	public AdmissionController(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		// TODO Auto-generated constructor stub
	}

	public AdmissionController(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);
		// TODO Auto-generated constructor stub
	}
}