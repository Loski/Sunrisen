package fr.upmc.datacenter.software.applicationvm.ports;

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

import java.util.Map;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;
import fr.upmc.datacenter.software.applicationvm.ApplicationVM;
import fr.upmc.datacenter.software.applicationvm.ApplicationVM.ApplicationVMPortTypes;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMDynamicStateI;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMStaticStateI;

/**
 * The class <code>ApplicationVMIntrospectionInboundPort</code> implements
 * an inbound port for interface <code>ApplicationVMIntrospectionI</code>.
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : October 5, 2015</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class				ApplicationVMIntrospectionInboundPort
extends		AbstractInboundPort
implements	ApplicationVMIntrospectionI
{
	private static final long serialVersionUID = 1L;

	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------

	public				ApplicationVMIntrospectionInboundPort(
		ComponentI owner
		) throws Exception
	{
		super(ApplicationVMIntrospectionI.class, owner) ;

		assert	owner instanceof ApplicationVM ;
	}

	public				ApplicationVMIntrospectionInboundPort(
		String uri,
		ComponentI owner
		) throws Exception
	{
		super(uri, ApplicationVMIntrospectionI.class, owner);

		assert	owner instanceof ApplicationVM ;
	}

	// ------------------------------------------------------------------------
	// Methods
	// ------------------------------------------------------------------------

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getAVMPortsURI()
	 */
	@Override
	public Map<ApplicationVMPortTypes, String>	getAVMPortsURI()
	throws Exception
	{
		final ApplicationVM avm = (ApplicationVM) this.owner ;
		return this.owner.handleRequestSync(
							new ComponentI.ComponentService<
												Map<ApplicationVMPortTypes,
													String>>() {
								@Override
								public Map<ApplicationVMPortTypes, String>
																		call()
								throws Exception
								{
									return avm.getAVMPortsURI() ;
								}
							}) ;
	}

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getStaticState()
	 */
	@Override
	public ApplicationVMStaticStateI	getStaticState()
	throws Exception
	{
		final ApplicationVM avm = (ApplicationVM) this.owner ;
		return this.owner.handleRequestSync(
				new ComponentI.ComponentService<ApplicationVMStaticStateI>() {
						@Override
						public ApplicationVMStaticStateI call()
						throws Exception
						{
							return avm.getStaticState() ;
						}
					}) ;
	}

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getDynamicState()
	 */
	@Override
	public ApplicationVMDynamicStateI	getDynamicState() throws Exception
	{
		final ApplicationVM avm = (ApplicationVM) this.owner ;
		return this.owner.handleRequestSync(
				new ComponentI.ComponentService<ApplicationVMDynamicStateI>() {
						@Override
						public ApplicationVMDynamicStateI call()
						throws Exception
						{
							return avm.getDynamicState() ;
						}
					}) ;
	}

	@Override
	public int getNumberOfCores() throws Exception {
		final ApplicationVM avm = (ApplicationVM) this.owner ;
		return this.owner.handleRequestSync(
				new ComponentI.ComponentService<Integer>() {
						@Override
						public Integer call()
						throws Exception
						{
							return avm.getNumberOfCores() ;
						}
					}) ;
	}
}
