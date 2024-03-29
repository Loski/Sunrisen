package fr.upmc.Sunrise.datacenter.hardware.computer.ports;

import fr.upmc.Sunrise.datacenter.hardware.computer.interfaces.ComputerControllerManagementI;
import fr.upmc.Sunrise.datacenter.software.requestdispatcher.interfaces.RequestDispatcherManagementI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;
import fr.upmc.datacenter.hardware.computers.Computer.AllocatedCore;

public class ComputerControllerManagementInboundPort extends AbstractInboundPort implements ComputerControllerManagementI {

	public				ComputerControllerManagementInboundPort(
			ComponentI owner
			) throws Exception
		{
			super(ComputerControllerManagementI.class, owner) ;

			assert	owner instanceof ComputerControllerManagementI ;
		}

		public				ComputerControllerManagementInboundPort(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ComputerControllerManagementI.class, owner);

			assert	uri != null && owner instanceof ComputerControllerManagementI ;
		}

	@Override
	public int tryReserveCore(final String vmUri, final int nbToReserve, int numberAllocated) throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;

		 return this.owner.handleRequestSync(
				new ComponentI.ComponentService<Integer>() {
					@Override
					public Integer call() throws Exception {
						return ccm.tryReserveCore(vmUri, nbToReserve, numberAllocated);
					}
				}
		);
	}

	@Override
	public void releaseCore(final String vmUri) throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;
		 this.owner.handleRequestSync(
					new ComponentI.ComponentService<Void>() {
						@Override
						public Void call() throws Exception {
							ccm.releaseCore(vmUri);
							return null;
						}
					}
			);
	}

	@Override
	public AllocatedCore[] addCores(final String vmUri) throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;

		 return this.owner.handleRequestSync(
				new ComponentI.ComponentService<AllocatedCore[]>() {
					@Override
					public AllocatedCore[] call() throws Exception {
						return ccm.addCores(vmUri);
						
					}
				}
		);
	}


	@Override
	public AllocatedCore[] allocateCores(final int i) throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;

		 return this.owner.handleRequestSync(
				new ComponentI.ComponentService<AllocatedCore[]>() {
					@Override
					public AllocatedCore[] call() throws Exception {
						return ccm.allocateCores(i);
						
					}
				}
		);
	}

	@Override
	public int compteurVM() throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;
		 return this.owner.handleRequestSync(
				new ComponentI.ComponentService<Integer>() {
					@Override
					public Integer call() throws Exception {
						return ccm.compteurVM();
						
					}
				}
		);
	}

	@Override
	public int compteurCoreReserved(String Vmrui) throws Exception {
		final ComputerControllerManagementI ccm = ( ComputerControllerManagementI ) this.owner;
		 return this.owner.handleRequestSync(
				new ComponentI.ComponentService<Integer>() {
					@Override
					public Integer call() throws Exception {
						return ccm.compteurCoreReserved(Vmrui);
					}
				}
		);
	}
	


}
