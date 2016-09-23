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

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class StructureSynchronizationProcessor implements IProcessor {

	private static final String START_PAT = " [From '"; //$NON-NLS-1$
	private static final String END_PAT = "']"; //$NON-NLS-1$

	private ModelElement _context = null;

	/**
	 * Refinement target (can be either a 'ComponentArchitecture', or a 'LogicalComponent')
	 */
	private NamedElement _target = null;
	private NamedElement _synchronizedElement = null;
	private boolean _forceScenarioCreation = false;

	public StructureSynchronizationProcessor() {
		/** no op */
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the element on which the processing will applied
	 * @param target
	 *            the target of the processing
	 */
	public StructureSynchronizationProcessor(NamedElement context, NamedElement target) {
		this(context, target, false);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the element on which the processing will applied
	 * @param target
	 *            the target of the processing
	 * @param forceScenarioCreation
	 */
	public StructureSynchronizationProcessor(NamedElement context, NamedElement target, boolean forceScenarioCreation) {
		setContext(context);
		setTarget(target);

		_forceScenarioCreation = forceScenarioCreation;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
	 */
	public Object getName() {
		return "structure synchronization"; //$NON-NLS-1$
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
	 */
	public Object getResult() {
		return _synchronizedElement;
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
	 *            the element on which the processing will applied
	 */
	public void setContext(ModelElement context) {
		_context = context;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.CapellaElement)
	 * 
	 * @param target
	 *            the target of the processing
	 */
	public void setTarget(NamedElement target) {
		if ((target instanceof ComponentArchitecture) || (target instanceof LogicalComponent) || (target instanceof PhysicalComponent)) {
			_target = target;
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute()
	 * 
	 * @throws ProcessorException
	 */
	public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
		if (_context instanceof CapabilityPkg) {
			CapabilityRealizationPkg pkg = createCapabilityRealizationPkg((CapabilityPkg) _context, _target);
			synchronizeName((CapabilityPkg) _context, pkg);
			_synchronizedElement = pkg;
		} else if (_context instanceof Capability) {
			CapabilityRealization cap = createCapabilityRealization((Capability) _context, _target);
			synchronizeName((Capability) _context, cap);
			_synchronizedElement = cap;
		} else if (_context instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg pkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) _context, _target);
			synchronizeName((CapabilityRealizationPkg) _context, pkg);
			_synchronizedElement = pkg;
		} else if (_context instanceof CapabilityRealization) {
			CapabilityRealization cap = createCapabilityRealization((CapabilityRealization) _context, _target);
			synchronizeName((CapabilityRealization) _context, cap);
			_synchronizedElement = cap;
		} else if (_context instanceof Scenario) {
			Scenario sc = createScenario((Scenario) _context, _target);
			synchronizeName((Scenario) _context, sc);
			_synchronizedElement = sc;
		} else {
			throw new ProcessorException("Non supported object type.", this); //$NON-NLS-1$
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void updateName(NamedElement referenceElt, NamedElement updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			if (updatedElt.getName() != referenceElt.getName()) {
				updatedElt.setName(referenceElt.getName());
			}
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(Capability referenceElt, CapabilityRealization updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			updateName(referenceElt, updatedElt);

			/** calling parent container */
			if (referenceElt.eContainer() instanceof CapabilityPkg) {
				synchronizeName((CapabilityPkg) referenceElt.eContainer(), (CapabilityRealizationPkg) updatedElt.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityRealization referenceElt, CapabilityRealization updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			updatedElt.setName(computeCapabilityRealizationName(referenceElt, updatedElt));

			/** calling parent container */
			if (referenceElt.eContainer() instanceof CapabilityRealizationPkg) {
				synchronizeName((CapabilityRealizationPkg) referenceElt.eContainer(), (CapabilityRealizationPkg) updatedElt.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityPkg referenceElt, CapabilityRealizationPkg updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			updateName(referenceElt, updatedElt);

			/** calling parent container */
			if (referenceElt.eContainer() instanceof CapabilityPkg) {
				synchronizeName((CapabilityPkg) referenceElt.eContainer(), (CapabilityRealizationPkg) updatedElt.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityRealizationPkg referenceElt, CapabilityRealizationPkg updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			updateName(referenceElt, updatedElt);

			/** calling parent container */
			if (referenceElt.eContainer() instanceof CapabilityRealizationPkg) {
				synchronizeName((CapabilityRealizationPkg) referenceElt.eContainer(), (CapabilityRealizationPkg) updatedElt.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt
	 *            reference element
	 * @param updatedElt
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(Scenario referenceElt, Scenario updatedElt) {
		if ((referenceElt != null) && (updatedElt != null)) {
			updatedElt.setName(computeScenarioName(referenceElt, updatedElt));

			/** calling parent container */
			if (_context.eContainer() instanceof Capability) {
				synchronizeName((Capability) _context.eContainer(), (CapabilityRealization) updatedElt.eContainer());
			} else if (_context.eContainer() instanceof CapabilityRealization) {
				synchronizeName((CapabilityRealization) _context.eContainer(), (CapabilityRealization) updatedElt.eContainer());
			}
		}
	}

	/**
	 * @param capPkg
	 * @param target
	 */
	private CapabilityRealizationPkg createCapabilityRealizationPkg(CapabilityPkg capPkg, NamedElement target) {
		boolean recentCreation = false;
		CapabilityRealizationPkg capabilityRealizationPkg = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capPkg, LaPackage.Literals.CAPABILITY_REALIZATION_PKG);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target)) {
				capabilityRealizationPkg = (CapabilityRealizationPkg) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealizationPkg == null) {
			if (!(capPkg.eContainer() instanceof CapabilityPkg)) {
				AbstractCapabilityPkg abstractCapaPkg = null;
				// Try to find the Root CapabilityPkg existing but not linked
				if (target instanceof BlockArchitecture) {
					abstractCapaPkg = ((BlockArchitecture) target).getOwnedAbstractCapabilityPkg();
				} else if (target instanceof Block) {
					abstractCapaPkg = ((Block) target).getOwnedAbstractCapabilityPkg();
				}
				if (abstractCapaPkg != null)
					capabilityRealizationPkg = (CapabilityRealizationPkg) abstractCapaPkg;
				else {
					capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg.getName());
				}
			} else {
				capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg.getName());
			}
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capPkg.eContainer() instanceof CapabilityPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityPkg) capPkg.eContainer(), target);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizationPkgs().add(capabilityRealizationPkg);
		} else if (target instanceof BlockArchitecture) {
			((BlockArchitecture) target).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		} else if (target instanceof Block) {
			((Block) target).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		}

		if ((capabilityRealizationPkg != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealizationPkg, capPkg);
		}

		return capabilityRealizationPkg;
	}

	/**
	 * @param capPkg
	 * @param target
	 */
	private CapabilityRealizationPkg createCapabilityRealizationPkg(CapabilityRealizationPkg capPkg, NamedElement target) {
		boolean recentCreation = false;
		CapabilityRealizationPkg capabilityRealizationPkg = null;

		if ((capPkg.eContainer() instanceof Block || capPkg.eContainer() instanceof BlockArchitecture) && target instanceof Block) {
			capabilityRealizationPkg = (CapabilityRealizationPkg) ((Block) target).getOwnedAbstractCapabilityPkg();
		}

		if (capabilityRealizationPkg == null) {
			/** search for a linked element */
			List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capPkg, LaPackage.Literals.CAPABILITY_REALIZATION_PKG);
			for (CapellaElement relatedElement : relatedElements) {
				if (EcoreUtil2.isContainedBy(relatedElement, target)) {
					capabilityRealizationPkg = (CapabilityRealizationPkg) relatedElement;
				}
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealizationPkg == null) {
			if (!(capPkg.eContainer() instanceof CapabilityPkg)) {
				AbstractCapabilityPkg abstractCapaPkg = null;
				// Try to find the Root CapabilityPkg existing but not linked
				if (target instanceof BlockArchitecture) {
					abstractCapaPkg = ((BlockArchitecture) target).getOwnedAbstractCapabilityPkg();
				} else if (target instanceof Block) {
					abstractCapaPkg = ((Block) target).getOwnedAbstractCapabilityPkg();
				}
				if (abstractCapaPkg != null)
					capabilityRealizationPkg = (CapabilityRealizationPkg) abstractCapaPkg;
				else {
					capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg.getName());
				}
			} else {
				capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg.getName());
			}
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capPkg.eContainer() instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) capPkg.eContainer(), target);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizationPkgs().add(capabilityRealizationPkg);
		} else if (target instanceof BlockArchitecture) {
			((BlockArchitecture) target).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		} else if (target instanceof Block) {
			((Block) target).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		}

		if ((capabilityRealizationPkg != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealizationPkg, capPkg);
		}

		return capabilityRealizationPkg;
	}

	/**
	 * @param capability
	 * @param target
	 * @param callOwnedUseCase
	 */
	private CapabilityRealization createCapabilityRealization(Capability capability, NamedElement target) {
		boolean recentCreation = false;
		CapabilityRealization capabilityRealization = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capability, LaPackage.Literals.CAPABILITY_REALIZATION);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target)) {
				capabilityRealization = (CapabilityRealization) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealization == null) {
			capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization(capability.getName());
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capability.eContainer() instanceof CapabilityPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityPkg) capability.eContainer(), target);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizations().add(capabilityRealization);
		}

		if ((capabilityRealization != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealization, capability);
		}

		return capabilityRealization;
	}

	/**
	 * @param capabilityReal
	 * @param target
	 * @param callOwnedUseCase
	 */
	private CapabilityRealization createCapabilityRealization(CapabilityRealization capabilityReal, NamedElement target) {
		boolean recentCreation = false;
		CapabilityRealization capabilityRealization = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capabilityReal, LaPackage.Literals.CAPABILITY_REALIZATION);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target)) {
				capabilityRealization = (CapabilityRealization) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealization == null) {
			capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization(computeCapabilityRealizationName(capabilityReal, target));
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capabilityReal.eContainer() instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) capabilityReal.eContainer(), target);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizations().add(capabilityRealization);
		}

		if ((capabilityRealization != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealization, capabilityReal);
		}

		return capabilityRealization;
	}

	/**
	 * Return a Name from the CapabilityRealization given in Parameter
	 * 
	 * @param capabilityReal
	 * @param target
	 */
	private String computeCapabilityRealizationName(CapabilityRealization capabilityReal, NamedElement target) {
		StringBuffer newName = new StringBuffer();
		LogicalComponent containerLc = null;

		if (CapellaLayerCheckingExt.isInPhysicalLayer(target) || CapellaLayerCheckingExt.isInEPBSLayer(target) || target instanceof EPBSArchitecture) {
			// Not apply the naming rule in PhysicalLayer refinement case
			return capabilityReal.getName();
		}

		containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(capabilityReal, LaPackage.Literals.LOGICAL_COMPONENT);
		if (containerLc == null) {
			// Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
			// Retrieve the Root Logical Component (from System)
			LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(capabilityReal, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			containerLc = SystemEngineeringExt.getRootLogicalComponent(la);
		}
		String srcCapName = capabilityReal.getName();
		int startIndex = srcCapName.indexOf(START_PAT);
		if (startIndex != -1) {
			newName.append(srcCapName.substring(0, startIndex));
		} else
			newName.append(srcCapName);
		newName.append(START_PAT).append(containerLc.getName()).append(END_PAT);

		return newName.toString();
	}

	/**
	 * Return a Name from the Scenario given in Parameter
	 * 
	 * @param scenario
	 * @param target
	 */
	private String computeScenarioName(Scenario scenario, NamedElement target) {
		StringBuffer newName = new StringBuffer();
		LogicalComponent containerLc = null;

		// Not apply the naming rule in ContextLayer refinement case
		if (CapellaLayerCheckingExt.isInContextLayer(scenario)) {
			return scenario.getName();
		}

		// Not apply the naming rule in PhysicalLayer refinement case
		if (CapellaLayerCheckingExt.isInPhysicalLayer(target) ||
			CapellaLayerCheckingExt.isInEPBSLayer(target) || (target instanceof EPBSArchitecture))
		{
			return scenario.getName();
		}

		containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(scenario, LaPackage.Literals.LOGICAL_COMPONENT);
		if (containerLc == null) {
			// Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
			// Retrieve the Root Logical Component (from System)
			LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(scenario, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			containerLc = SystemEngineeringExt.getRootLogicalComponent(la);
		}
		String srcCapName = scenario.getName();
		int startIndex = srcCapName.indexOf(START_PAT);
		if (startIndex != -1) {
			newName.append(srcCapName.substring(0, startIndex));
		} else
			newName.append(srcCapName);
		newName.append(START_PAT).append(containerLc.getName()).append(END_PAT);

		return newName.toString();
	}

	/**
	 * @param scenario
	 * @param target
	 */
	private Scenario createScenario(Scenario scenario, NamedElement target) {
		boolean recentCreation = false;
		Scenario sc = null;

		/** search for a linked element */
		if (!_forceScenarioCreation) {
			List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(scenario, InteractionPackage.Literals.SCENARIO);
			for (CapellaElement relatedElement : relatedElements) {
				if (EcoreUtil2.isContainedBy(relatedElement, target)) {
					sc = (Scenario) relatedElement;
				}
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (sc == null) {
			sc = InteractionFactory.eINSTANCE.createScenario(scenario.getName());
			sc.setKind(scenario.getKind());
			recentCreation = true;
		}

		/**
		 * attach the retrieved element to its retrieved container
		 * TODO (refinement) if the scenario is moved, its related events must also be moved
		 */
		if (scenario.eContainer() instanceof Capability) {
			CapabilityRealization abstractCapabilityContext = createCapabilityRealization((Capability) scenario.eContainer(), target);
			abstractCapabilityContext.getOwnedScenarios().add(sc);
		} else if (scenario.eContainer() instanceof CapabilityRealization) {
			CapabilityRealization abstractCapabilityContext = createCapabilityRealization((CapabilityRealization) scenario.eContainer(), target);
			abstractCapabilityContext.getOwnedScenarios().add(sc);
		}

		if ((sc != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(sc, scenario);
		}

		return sc;
	}
}
