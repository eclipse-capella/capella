/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.processor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class UpdateRealizationContributionProcessor implements IProcessor {

	protected Kind kind = Kind.UNDEFINED;
	protected NamedElement context = null;
	protected NamedElement target = null;

	protected enum Kind {
		UNDEFINED, STATIC, DYNAMIC
	}

	public UpdateRealizationContributionProcessor(Kind kind) {
		this.kind = kind;
	}

	/**
	 * Constructor
	 * 
	 * @param kind
	 * @param context
	 *            the {@link NamedElement} on which the processing will be
	 *            applied
	 */
	public UpdateRealizationContributionProcessor(Kind kind, NamedElement context) {
		this.kind = kind;
		this.context = context;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
	 */
	public Object getResult() {
		return null;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
	 */
	public void setContext(List<ModelElement> context) {
		if ((context != null) && (context.size() > 0)) {
			setContext(context.get(0));
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
	 * 
	 * @param context
	 *            the {@link NamedElement} on which the processing will be
	 *            applied
	 */
	public void setContext(ModelElement context) {
		if (context instanceof NamedElement) {
			context = (NamedElement) context;
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
	 */
	public void setTarget(NamedElement target) {
		this.target = target;
	}

	/**
	 * The realization contribution update can be launched from:
	 * 
	 * <li> the {@link LogicalArchitecture},
	 * <li> a {@link LogicalComponent},
	 * <li> the {@link PhysicalArchitecture},
	 * <li> an {@link AspectPkg},
	 * <li> a {@link FunctionalAspect},
	 * <li> a {@link Capability},
	 * <li> a {@link CapabilityPkg},
	 * <li> a {@link CapabilityRealization},
	 * <li> a {@link CapabilityRealizationPkg},
	 * <li> a {@link CapabilityUseCase},
	 * <li> a {@link CapabilityRealizationUseCase},
	 * <li> a {@link Scenario}.
	 * 
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute()
	 * 
	 * @throws ProcessorException
	 */
	public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
		if (context instanceof LogicalArchitecture) {
			update((LogicalArchitecture) context);
		} else if (context instanceof LogicalComponent) {
			update((LogicalComponent) context);
		} else if (context instanceof PhysicalArchitecture) {
			update((PhysicalArchitecture) context);
    } else if (context instanceof PhysicalComponent) {
      update((PhysicalComponent) context);
		} else if (context instanceof Capability) {
			update((Capability) context);
		} else if (context instanceof CapabilityPkg) {
			update((CapabilityPkg) context);
		} else if (context instanceof CapabilityRealization) {
			update((CapabilityRealization) context);
		} else if (context instanceof CapabilityRealizationPkg) {
			update((CapabilityRealizationPkg) context);
		} else if (context instanceof Scenario) {
			update((Scenario) context);
		} else {
			throw new ProcessorException("Invalid context type.", this); //$NON-NLS-1$
		}
	}

	/**
	 * When launched from a {@link BlockArchitecture}, the update is applied on
	 * all its contained {@link AspectPkg}.
	 * 
	 * @param currentElt
	 */
	private void update(BlockArchitecture currentElt) {
		if (currentElt != null) {
			update(currentElt.getOwnedAbstractCapabilityPkg());
		}
	}

	/**
	 * When launched from a {@link LogicalComponent}, the update is applied on
	 * all its contained {@link AspectPkg}.
	 * 
	 * @param currentElt
	 */
	private void update(LogicalComponent currentElt) {
		if (currentElt != null) {
			update(currentElt.getOwnedAbstractCapabilityPkg());
		}
	}

  /**
   * When launched from a {@link PhysicalComponent}, the update is applied on
   * all its contained {@link AspectPkg}.
   * 
   * @param currentElt
   */
  private void update(PhysicalComponent currentElt) {
    if (currentElt != null) {
      update(currentElt.getOwnedAbstractCapabilityPkg());
    }
  }

	/**
	 * When launched from a {@link AbstractCapabilityPkg}, the update is applied on
	 * corresponding {@link CapabilityPkg}/{@link CapabilityRealizationPkg}.
	 * 
	 * @param currentElt
	 */
	private void update(AbstractCapabilityPkg currentElt) {
		if (currentElt != null) {
			if (currentElt instanceof CapabilityPkg) {
				update((CapabilityPkg) currentElt);
			}
			else if (currentElt instanceof CapabilityRealizationPkg) {
				update((CapabilityRealizationPkg) currentElt);
			}
		}
	}

	/**
	 * When launched from a {@link Capability}, the update is applied on its
	 * contained {@link CapabilityUseCase}.
	 * 
	 * @param currentElt
	 */
	private void update(Capability currentElt) {
		if (currentElt != null) {
			updateInvolvements(currentElt, LaPackage.Literals.LOGICAL_ARCHITECTURE);
		}
	}

	/**
	 * When launched from a {@link CapabilityPkg}, the update is applied on its
	 * contained {@link CapabilityPkg} and {@link Capability}.
	 * 
	 * @param currentElt
	 */
	private void update(CapabilityPkg currentElt) {
		if (currentElt != null) {
			for (Capability capability : currentElt.getOwnedCapabilities()) {
				update(capability);
			}
			for (CapabilityPkg capabilityPkg : currentElt.getOwnedCapabilityPkgs()) {
				update(capabilityPkg);
			}
		}
	}

	/**
	 * When launched from a {@link CapabilityRealization}, the update is
	 * applied on its contained {@link CapabilityRealizationUseCase}.
	 * 
	 * @param currentElt
	 */
	private void update(CapabilityRealization currentElt) {
		if (currentElt != null) {
			if (kind == Kind.STATIC) {
				if (EcoreUtil2.isContainedBy(currentElt, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
					updateInvolvements(currentElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
				} else if (EcoreUtil2.isContainedBy(currentElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
					updateInvolvements(currentElt, EpbsPackage.Literals.EPBS_ARCHITECTURE);
				}
			} else if (kind == Kind.DYNAMIC) {
			  if (target instanceof LogicalArchitecture || EcoreUtil2.isContainedBy(target, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
			    if (EcoreUtil2.isContainedBy(currentElt, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
			      updateInvolvements(currentElt, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			    }
			  } else if (target instanceof PhysicalArchitecture || EcoreUtil2.isContainedBy(target, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
			    if (EcoreUtil2.isContainedBy(currentElt, LaPackage.Literals.LOGICAL_ARCHITECTURE) ||
			        EcoreUtil2.isContainedBy(currentElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE))
			    {
            updateInvolvements(currentElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
          }
			  } else if (target instanceof EPBSArchitecture || EcoreUtil2.isContainedBy(target, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
          if (EcoreUtil2.isContainedBy(currentElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
            updateInvolvements(currentElt, EpbsPackage.Literals.EPBS_ARCHITECTURE);
          }
        }
				 
			}
		}
	}

	/**
	 * When launched from a {@link CapabilityRealizationPkg}, the update is
	 * applied on its contained {@link CapabilityRealizationPkg} and
	 * {@link CapabilityRealization}.
	 * 
	 * @param currentElt
	 */
	private void update(CapabilityRealizationPkg currentElt) {
		if (currentElt != null) {
			for (CapabilityRealization capability : currentElt.getOwnedCapabilityRealizations()) {
				update(capability);
			}
			for (CapabilityRealizationPkg capabilityPkg : currentElt.getOwnedCapabilityRealizationPkgs()) {
				update(capabilityPkg);
			}
		}
	}

	/**
	 * When launched from a {@link Scenario}, the update is applied on its
	 * related {@link CapabilityUseCase}/{@link CapabilityRealizationUseCase}.
	 * 
	 * @param currentElt
	 */
	private void update(Scenario currentElt) {
		if (currentElt != null) {
			AbstractCapability useCase = ScenarioExt.getRelatedCapability(currentElt);
			if (useCase instanceof Capability) {
				update((Capability) useCase);
			} else if (useCase instanceof CapabilityRealization) {
				update((CapabilityRealization) useCase);
			}
		}
	}

	/**
	 * @param currentCapabilityUseCase
	 * @param target
	 */
	protected abstract void updateInvolvements(AbstractCapability currentCapability, EClass target);
}
