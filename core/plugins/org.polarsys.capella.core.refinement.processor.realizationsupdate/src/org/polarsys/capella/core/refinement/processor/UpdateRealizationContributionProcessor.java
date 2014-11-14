/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

	protected Kind _kind = Kind.UNDEFINED;
	protected NamedElement _context = null;
	protected NamedElement _target = null;

	protected enum Kind {
		UNDEFINED, STATIC, DYNAMIC
	}

	public UpdateRealizationContributionProcessor(Kind kind_p) {
		_kind = kind_p;
	}

	/**
	 * Constructor
	 * 
	 * @param kind_p
	 * @param context_p
	 *            the {@link NamedElement} on which the processing will be
	 *            applied
	 */
	public UpdateRealizationContributionProcessor(Kind kind_p, NamedElement context_p) {
		_kind = kind_p;
		_context = context_p;
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
	public void setContext(List<ModelElement> context_p) {
		if ((context_p != null) && (context_p.size() > 0)) {
			setContext(context_p.get(0));
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
	 * 
	 * @param context_p
	 *            the {@link NamedElement} on which the processing will be
	 *            applied
	 */
	public void setContext(ModelElement context_p) {
		if (context_p instanceof NamedElement) {
			_context = (NamedElement) context_p;
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
	 */
	public void setTarget(NamedElement target_p) {
		_target = target_p;
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
	public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
		if (_context instanceof LogicalArchitecture) {
			update((LogicalArchitecture) _context);
		} else if (_context instanceof LogicalComponent) {
			update((LogicalComponent) _context);
		} else if (_context instanceof PhysicalArchitecture) {
			update((PhysicalArchitecture) _context);
    } else if (_context instanceof PhysicalComponent) {
      update((PhysicalComponent) _context);
		} else if (_context instanceof Capability) {
			update((Capability) _context);
		} else if (_context instanceof CapabilityPkg) {
			update((CapabilityPkg) _context);
		} else if (_context instanceof CapabilityRealization) {
			update((CapabilityRealization) _context);
		} else if (_context instanceof CapabilityRealizationPkg) {
			update((CapabilityRealizationPkg) _context);
		} else if (_context instanceof Scenario) {
			update((Scenario) _context);
		} else {
			throw new ProcessorException("Invalid context type.", this); //$NON-NLS-1$
		}
	}

	/**
	 * When launched from a {@link BlockArchitecture}, the update is applied on
	 * all its contained {@link AspectPkg}.
	 * 
	 * @param currentElt_p
	 */
	private void update(BlockArchitecture currentElt_p) {
		if (currentElt_p != null) {
			update(currentElt_p.getOwnedAbstractCapabilityPkg());
		}
	}

	/**
	 * When launched from a {@link LogicalComponent}, the update is applied on
	 * all its contained {@link AspectPkg}.
	 * 
	 * @param currentElt_p
	 */
	private void update(LogicalComponent currentElt_p) {
		if (currentElt_p != null) {
			update(currentElt_p.getOwnedAbstractCapabilityPkg());
		}
	}

  /**
   * When launched from a {@link PhysicalComponent}, the update is applied on
   * all its contained {@link AspectPkg}.
   * 
   * @param currentElt_p
   */
  private void update(PhysicalComponent currentElt_p) {
    if (currentElt_p != null) {
      update(currentElt_p.getOwnedAbstractCapabilityPkg());
    }
  }

	/**
	 * When launched from a {@link AbstractCapabilityPkg}, the update is applied on
	 * corresponding {@link CapabilityPkg}/{@link CapabilityRealizationPkg}.
	 * 
	 * @param currentElt_p
	 */
	private void update(AbstractCapabilityPkg currentElt_p) {
		if (currentElt_p != null) {
			if (currentElt_p instanceof CapabilityPkg) {
				update((CapabilityPkg) currentElt_p);
			}
			else if (currentElt_p instanceof CapabilityRealizationPkg) {
				update((CapabilityRealizationPkg) currentElt_p);
			}
		}
	}

	/**
	 * When launched from a {@link Capability}, the update is applied on its
	 * contained {@link CapabilityUseCase}.
	 * 
	 * @param currentElt_p
	 */
	private void update(Capability currentElt_p) {
		if (currentElt_p != null) {
			updateInvolvements(currentElt_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
		}
	}

	/**
	 * When launched from a {@link CapabilityPkg}, the update is applied on its
	 * contained {@link CapabilityPkg} and {@link Capability}.
	 * 
	 * @param currentElt_p
	 */
	private void update(CapabilityPkg currentElt_p) {
		if (currentElt_p != null) {
			for (Capability capability : currentElt_p.getOwnedCapabilities()) {
				update(capability);
			}
			for (CapabilityPkg capabilityPkg : currentElt_p.getOwnedCapabilityPkgs()) {
				update(capabilityPkg);
			}
		}
	}

	/**
	 * When launched from a {@link CapabilityRealization}, the update is
	 * applied on its contained {@link CapabilityRealizationUseCase}.
	 * 
	 * @param currentElt_p
	 */
	private void update(CapabilityRealization currentElt_p) {
		if (currentElt_p != null) {
			if (_kind == Kind.STATIC) {
				if (EcoreUtil2.isContainedBy(currentElt_p, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
					updateInvolvements(currentElt_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
				} else if (EcoreUtil2.isContainedBy(currentElt_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
					updateInvolvements(currentElt_p, EpbsPackage.Literals.EPBS_ARCHITECTURE);
				}
			} else if (_kind == Kind.DYNAMIC) {
			  if (_target instanceof LogicalArchitecture || EcoreUtil2.isContainedBy(_target, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
			    if (EcoreUtil2.isContainedBy(currentElt_p, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
			      updateInvolvements(currentElt_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			    }
			  } else if (_target instanceof PhysicalArchitecture || EcoreUtil2.isContainedBy(_target, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
			    if (EcoreUtil2.isContainedBy(currentElt_p, LaPackage.Literals.LOGICAL_ARCHITECTURE) ||
			        EcoreUtil2.isContainedBy(currentElt_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE))
			    {
            updateInvolvements(currentElt_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
          }
			  } else if (_target instanceof EPBSArchitecture || EcoreUtil2.isContainedBy(_target, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
          if (EcoreUtil2.isContainedBy(currentElt_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
            updateInvolvements(currentElt_p, EpbsPackage.Literals.EPBS_ARCHITECTURE);
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
	 * @param currentElt_p
	 */
	private void update(CapabilityRealizationPkg currentElt_p) {
		if (currentElt_p != null) {
			for (CapabilityRealization capability : currentElt_p.getOwnedCapabilityRealizations()) {
				update(capability);
			}
			for (CapabilityRealizationPkg capabilityPkg : currentElt_p.getOwnedCapabilityRealizationPkgs()) {
				update(capabilityPkg);
			}
		}
	}

	/**
	 * When launched from a {@link Scenario}, the update is applied on its
	 * related {@link CapabilityUseCase}/{@link CapabilityRealizationUseCase}.
	 * 
	 * @param currentElt_p
	 */
	private void update(Scenario currentElt_p) {
		if (currentElt_p != null) {
			AbstractCapability useCase = ScenarioExt.getRelatedCapability(currentElt_p);
			if (useCase instanceof Capability) {
				update((Capability) useCase);
			} else if (useCase instanceof CapabilityRealization) {
				update((CapabilityRealization) useCase);
			}
		}
	}

	/**
	 * @param currentCapabilityUseCase_p
	 * @param target_p
	 */
	protected abstract void updateInvolvements(AbstractCapability currentCapability_p, EClass target_p);
}
