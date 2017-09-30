package fr.upmc.components.examples.ddeployment_cs;

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

import fr.upmc.components.ComponentI;
import fr.upmc.components.cvm.AbstractCVM;
import fr.upmc.components.examples.ddeployment_cs.components.DynamicAssembler;

/**
 * The class <code>DynamicCVM</code> creates a component assembly for the
 * single-JVM execution of the dynamic connection example.
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : May 12, 2014</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class			CVM
extends		AbstractCVM
{
	public				CVM() throws Exception
	{
		super();
	}

	protected static String		ASSEMBLER_JVM_URI = "" ;
	protected static String		PROVIDER_JVM_URI = "" ;
	protected static String		CONSUMER_JVM_URI = "" ;
	protected static String		Consumer_OUTBOUND_PORT_URI = "oport" ;
	protected static String		Provider_INBOUND_PORT_URI = "iport" ;

	protected DynamicAssembler	da ;

	/**
	 * @see fr.upmc.components.cvm.AbstractCVM#deploy()
	 */
	@Override
	public void			deploy() throws Exception
	{
		this.da = new DynamicAssembler(this,
				   					   CONSUMER_JVM_URI,
				   					   PROVIDER_JVM_URI,
				   					   Consumer_OUTBOUND_PORT_URI,
				   					   Provider_INBOUND_PORT_URI) ;
		this.addDeployedComponent(this.da) ;

		// deployment done
		super.deploy() ;
	}

	/**
	 * @see fr.upmc.components.cvm.AbstractCVM#start()
	 */
	@Override
	public void			start() throws Exception
	{
		super.start() ;

		final DynamicAssembler fDa = this.da ;
		this.da.runTask(
			new ComponentI.ComponentTask() {
					@Override
					public void run() {
						try {
							fDa.dynamicDeploy() ;
							fDa.dynamicStart() ;
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				}) ;

	}

	public static void	main(String[] args)
	{
		System.out.println("Beginning") ;
		try {
			CVM c = new CVM() ;
			c.deploy() ;
			c.start() ;
			Thread.sleep(15000) ;
			c.shutdown() ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
		System.out.println("Main thread ending") ;
		System.exit(0);
	}
}
