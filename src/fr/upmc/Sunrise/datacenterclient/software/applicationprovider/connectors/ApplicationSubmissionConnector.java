package fr.upmc.Sunrise.datacenterclient.software.applicationprovider.connectors;

import fr.upmc.Sunrise.datacenterclient.software.applicationprovider.interfaces.ApplicationSubmissionI;
import fr.upmc.components.connectors.AbstractConnector;
/**
 *   * The class <code>ApplicationSubmissionConnector</code> implements the
 * connector between outbound and inboud ports implementing the interface
 * <code>ApplicationSubmissionI</code>.
 * @author Maxime Lavaste
 *
 */
public class ApplicationSubmissionConnector extends	AbstractConnector
implements	ApplicationSubmissionI {

	@Override
	public String[] submitApplication(String appURI) throws Exception {
		
		return ( ( ApplicationSubmissionI ) this.offering ).submitApplication(appURI);

	}

	@Override
	public void submitGenerator(String requestNotificationInboundPort, String appURI, String rgURI) throws Exception {
		( ( ApplicationSubmissionI ) this.offering ).submitGenerator(requestNotificationInboundPort, appURI,  rgURI);	
	}

	/*@Override
	public String[] submitApplication(String appURI, int nbVM, Class submissionInterface) throws Exception {
		return ( ( ApplicationSubmissionI ) this.offering ).submitApplication(appURI,nbVM,submissionInterface);
	}*/

	@Override
	public void stopApplication(String rdURI) throws Exception {
		( ( ApplicationSubmissionI ) this.offering ).stopApplication(rdURI);
	}
}
