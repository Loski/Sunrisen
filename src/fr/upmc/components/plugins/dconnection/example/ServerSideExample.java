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
import fr.upmc.components.plugins.dconnection.DynamicConnectionServerSidePlugin;
import fr.upmc.components.ports.PortI;
import fr.upmc.components.pre.plugins.PluginI;

/**
 * The class <code>ServerSideExample</code> show how a server component
 * uses the dynamic connection plug-in to be connected with a client
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
public class			ServerSideExample
extends		AbstractComponent
{
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------

	/**
	 * create the server component xith the given reflection inbound port URI
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	reflectionInboundPortURI != null
	 * post	isInstalled(DynamicConnectionServerSidePlugin.PLUGIN_URI)
	 * </pre>
	 *
	 * @param reflectionInboundPortURI
	 * @throws Exception 
	 */
	public				ServerSideExample(
		String reflectionInboundPortURI
		) throws Exception
	{
		super(reflectionInboundPortURI, 1, 0) ;

		assert	reflectionInboundPortURI != null ;

		this.addOfferedInterface(ExampleI.class) ;

		final ComponentI fOwner = this ;
		PluginI p =
			new DynamicConnectionServerSidePlugin() {
				private static final long serialVersionUID = 1L;

				@Override
				protected PortI createServerSideDynamicPort(
					Class<?> offeredInterface
					) throws Exception
				{
					return new ExampleInboundPort(fOwner) ;
				}
			} ;
		this.installPlugin(p) ;
	}

	// ------------------------------------------------------------------------
	// Component services
	// ------------------------------------------------------------------------

	/**
	 * simple service in this example: add 1 to the parameter and
	 * return this result.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param i	integer that must be incremented by 1.
	 * @return	the parameter incremented by 1.
	 */
	public int			add1(int i)
	{
		return i + 1 ;
	}
}
