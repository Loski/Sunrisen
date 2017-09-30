package fr.upmc.components.plugins.dconnection.example;

//Copyright Jacques Malenfant, Univ. Pierre et Marie Curie.
//
//Jacques.Malenfant@lip6.fr
//
//This software is a computer program whose purpose is to provide a
//basic component programming model to program with components
//distributed applications in the Java programming language.
//
//This software is governed by the CeCILL-C license under French law and
//abiding by the rules of distribution of free software.  You can use,
//modify and/ or redistribute the software under the terms of the
//CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
//URL "http://www.cecill.info".
//
//As a counterpart to the access to the source code and  rights to copy,
//modify and redistribute granted by the license, users are provided only
//with a limited warranty  and the software's author,  the holder of the
//economic rights,  and the successive licensors  have only  limited
//liability. 
//
//In this respect, the user's attention is drawn to the risks associated
//with loading,  using,  modifying and/or developing or reproducing the
//software by the user in light of its specific status of free software,
//that may mean  that it is complicated to manipulate,  and  that  also
//therefore means  that it is reserved for developers  and  experienced
//professionals having in-depth computer knowledge. Users are therefore
//encouraged to load and test the software's suitability as regards their
//requirements in conditions enabling the security of their systems and/or 
//data to be ensured and,  more generally, to use and operate it in the 
//same conditions as regards security. 
//
//The fact that you are presently reading this means that you have had
//knowledge of the CeCILL-C license and that you accept its terms.

import fr.upmc.components.AbstractComponent;
import fr.upmc.components.ComponentI;
import fr.upmc.components.plugins.dconnection.DynamicConnectionClientSidePlugin;
import fr.upmc.components.plugins.dconnection.interfaces.DynamicConnectionDescriptorI;
import fr.upmc.components.ports.PortI;
import fr.upmc.components.pre.reflection.connectors.ReflectionConnector;
import fr.upmc.components.pre.reflection.ports.ReflectionOutboundPort;

/**
 * The class <code>ClientSideExample</code> shows how a client component can
 * use the dynamic connection plug-in to connect itself with a server
 * component and call its services.
 *
 * <p><strong>Description</strong></p>
 * 
 * To benefit from the example, carefully read the code and adapt it to your
 * needs in your own code.
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : 17 févr. 2017</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class				ClientSideExample
extends		AbstractComponent
{
	// ------------------------------------------------------------------------
	// Constants and variables
	// ------------------------------------------------------------------------

	/** 	URI of the reflection inbound port of the server component.		*/
	protected String					serverSideReflectionInboundPortURI ;
	/** 	reflection outbound port of this component.						*/
	protected ReflectionOutboundPort	rop ;

	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * create the client side component with required interface
	 * <code>ExampleI</code> and with the plug-in for dynamic connection
	 * between components installed.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	serverSideReflectionInboundPortURI != null
	 * post	isInstalled(DynamicConnectionClientSidePlugin.PLUGIN_URI)
	 * </pre>
	 *
	 * @param serverSideReflectionInboundPortURI	URI of the reflection inbound port of the server component
	 * @throws Exception
	 */
	public				ClientSideExample(
		String serverSideReflectionInboundPortURI
		) throws Exception
	{
		super(1, 0) ;

		assert	serverSideReflectionInboundPortURI != null ;

		this.addRequiredInterface(ExampleI.class) ;
		this.serverSideReflectionInboundPortURI =
									serverSideReflectionInboundPortURI ;

		this.installPlugin(new DynamicConnectionClientSidePlugin()) ;
	}

	// ------------------------------------------------------------------------
	// Methods
	// ------------------------------------------------------------------------

	/**
	 * example scenario showing how tho use the dynamic connection facilities
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @throws Exception
	 */
	public void			exampleScenario() throws Exception
	{
		// Connecting the reflection ports
		this.rop = new ReflectionOutboundPort(this) ;
		this.addPort(this.rop) ;
		this.rop.publishPort() ;
		this.rop.doConnection(
					this.serverSideReflectionInboundPortURI,
					ReflectionConnector.class.getCanonicalName()) ;

		// Connecting the dynamic connection plug-ins
		DynamicConnectionClientSidePlugin dconnectionPlugIn =
				(DynamicConnectionClientSidePlugin)
						this.getPlugin(
								DynamicConnectionClientSidePlugin.PLUGIN_URI) ;
		dconnectionPlugIn.connectWithServerSide(this.rop) ;

		// Disconnect the reflection ports
		this.rop.doDisconnection() ;

		// Use the dynamic connection facilities to connect the example
		// ports.
		final ComponentI c = this ;
		String testOutboundPortURI =
			dconnectionPlugIn.doDynamicConnection(
				ExampleI.class,
				new DynamicConnectionDescriptorI() {
						@Override
						public PortI createClientSideDynamicPort() {
							PortI p = null ;
							try {
								p = new ExampleOutboundPort(c) ;
							} catch (Exception e) {
								throw new RuntimeException(e) ;
							}
							return p ;
						}

						@Override
						public String dynamicConnectorClassName() {
							return ExampleConnector.class.getCanonicalName() ;
						}
					}) ;

		// Use the created outbound port to call the server component
		ExampleOutboundPort top =
			(ExampleOutboundPort) this.findPortFromURI(testOutboundPortURI) ;
		System.out.println("result = " + top.exampleCall(10)) ;
		dconnectionPlugIn.disconnectFromServerSide() ;

	}
}
