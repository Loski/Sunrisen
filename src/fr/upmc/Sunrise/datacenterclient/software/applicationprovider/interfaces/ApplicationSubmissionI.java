package fr.upmc.Sunrise.datacenterclient.software.applicationprovider.interfaces;

import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;
/**
 * This interface define the interaction to give an application to the administrationController.
 * @author Maxime Lavaste
 *
 */
public interface ApplicationSubmissionI extends	OfferedI, RequiredI {
	
	/**
	 * Envoie une application au controller
	 * Le controller vérifie s'il possède les ressources pour accepter l'application
	 * @param appURI Uri of the Application
	 * @return The requestDispatcherUri if success with his inboundPort.
	 * @throws Exception
	 */
	public String[] submitApplication(String appURI) throws Exception;
	
	//public String[] submitApplication(String appURI, int nbVM, Class submissionInterface) throws Exception;
	
	/**
	 * Submit a generator to the administration controller.
	 * submitApplication need to be called before. 
	 * @param requestNotificationInboundPort uri of the requestNotificationInboundPort of the requestGenerator.
	 * @param appURI Uri of the application.
	 * @param rgURI	URI of the requestGenerator.
	 * @throws Exception
	 */
	public void submitGenerator(String requestNotificationInboundPort, String appURI, String rgURI) throws Exception;
	
	
	//TODO Passez en port de notification ?
	/**
	 * Signale l'arrêt d'une application à l'admission controller.
	 * @param appURI
	 * @throws Exception
	 */
	
	public void stopApplication(String appURI) throws Exception;
}
