/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * change from singleton to static method
 */
public class ModelCreationHelper {

	protected static Scenario createScenario(AbstractCapability abstractCapability, ScenarioKind scenarioKind) {
		if (abstractCapability != null) {
			Scenario sc = InteractionFactory.eINSTANCE.createScenario("Scenario"); //$NON-NLS-1$
			sc.setKind(scenarioKind);
			abstractCapability.getOwnedScenarios().add(sc);
			return sc;
		}
		return null;
	}

	protected static void createStateMachineRegion(Block block, String stateMachineName) {
		StateMachine defaultSM = null;
		for (StateMachine sm : block.getOwnedStateMachines()) {
			defaultSM = sm;
			break;
		}
		if (defaultSM == null) {
			defaultSM = CapellacommonFactory.eINSTANCE.createStateMachine(stateMachineName);
			block.getOwnedStateMachines().add(defaultSM);
		}

		Region defaultRegion = null;
		for (Region region : defaultSM.getOwnedRegions()) {
			defaultRegion = region;
		}
		if (defaultRegion == null) {
			defaultRegion = CapellacommonFactory.eINSTANCE.createRegion("Default Region"); //$NON-NLS-1$
			defaultSM.getOwnedRegions().add(defaultRegion);
		}
	}

	protected static AbstractCapability selectCapability(final Project project, ComponentArchitecture architecture) {
		AbstractCapability result = null;
		AbstractCapabilityPkg capabilityPkg = ModelQueryHelper.getCapabilityPkgFrom(project, architecture);
		if (capabilityPkg != null) {
			EClass clazz = CtxPackage.Literals.CAPABILITY;
			if (capabilityPkg instanceof CapabilityRealizationPkg) {
				clazz = LaPackage.Literals.CAPABILITY_REALIZATION;
			}
			Set<EObject> all = EObjectExt.getAll(capabilityPkg, clazz);
			if (!all.isEmpty()) {
				Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
				result = (AbstractCapability) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(all), shell);
				return result;
			}
			// No capability found, let's create a new one.
			result = createAbstractCapability(capabilityPkg);
		}
		return result;
	}

	public static AbstractCapability createAbstractCapability(AbstractCapabilityPkg capabilityPkg) {
		AbstractCapability result = null;
		if (capabilityPkg instanceof CapabilityPkg) {
			result = CtxFactory.eINSTANCE.createCapability("Capability"); //$NON-NLS-1$
			((CapabilityPkg) capabilityPkg).getOwnedCapabilities().add((Capability) result);
		} else if (capabilityPkg instanceof CapabilityRealizationPkg) {
			result = LaFactory.eINSTANCE.createCapabilityRealization("Capability"); //$NON-NLS-1$
			((CapabilityRealizationPkg) capabilityPkg).getOwnedCapabilityRealizations().add((CapabilityRealization) result);
		}
		return result;
	}

	public static Scenario selectCapabilityAndCreateScenario(final Project project, final ComponentArchitecture architecture,
			final ScenarioKind scenarioKind) {
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			private Scenario scenario;

			/**
			 * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
			 */
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singletonList(scenario);
			}

			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				AbstractCapability capability = selectCapability(project, architecture);
				if (capability != null) {
					scenario = createScenario(capability, scenarioKind);
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);

		return (Scenario) cmd.getAffectedObjects().iterator().next();
	}

	protected static CapabilityRealization selectCapabilityRealization(CapabilityRealizationPkg capabilityRealizationPkg) {
		if (capabilityRealizationPkg != null) {
			Set<EObject> all = EObjectExt.getAll(capabilityRealizationPkg, LaPackage.Literals.CAPABILITY_REALIZATION);
			if (!all.isEmpty()) {
				Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
				return (CapabilityRealization) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(all), shell);
			}
			CapabilityRealization capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization("Capability Realization"); //$NON-NLS-1$
			capabilityRealizationPkg.getOwnedCapabilityRealizations().add(capabilityRealization);
			return capabilityRealization;
		}
		return null;
	}

	protected static CapabilityRealization selectLACapabilityRealization(Project project) {
		CapabilityRealizationPkg capabilityRealizationPkg = ModelQueryHelper.getLACapabilityRealizationPkg(project);
		if (capabilityRealizationPkg != null) {
			return selectCapabilityRealization(capabilityRealizationPkg);
		}
		return null;
	}

	public static Scenario selectLACapabilityRealizationAndCreateDataFlowScenario(final Project project) {
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			private Scenario scenario;

			/**
			 * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
			 */
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singletonList(scenario);
			}

			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				CapabilityRealization capabilityRealization = selectLACapabilityRealization(project);
				if (capabilityRealization != null) {
					scenario = createScenario(capabilityRealization, ScenarioKind.DATA_FLOW);
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);

		return (Scenario) cmd.getAffectedObjects().iterator().next();
	}

	protected static OperationalCapability selectOperationalCapability(Project project) {
		OperationalCapabilityPkg operationalCapabilityPkg = ModelQueryHelper.getOperationalCapabilityPkg(project);
		if (operationalCapabilityPkg != null) {
			Set<EObject> all = EObjectExt.getAll(operationalCapabilityPkg, OaPackage.Literals.OPERATIONAL_CAPABILITY);
			if (!all.isEmpty()) {
				Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
				return (OperationalCapability) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(all), shell);
			}
			OperationalCapability operationalCapability = OaFactory.eINSTANCE.createOperationalCapability("Operational Capability"); //$NON-NLS-1$
			operationalCapabilityPkg.getOwnedOperationalCapabilities().add(operationalCapability);
			return operationalCapability;
		}
		return null;
	}

	public static Scenario selectOperationalCapabilityAndCreateInteractionScenario(final Project project) {
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			private Scenario scenario;

			/**
			 * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
			 */
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singletonList(scenario);
			}

			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				OperationalCapability operationalCapability = selectOperationalCapability(project);
				if (operationalCapability != null) {
					scenario = createScenario(operationalCapability, ScenarioKind.INTERACTION);
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);

		return (Scenario) cmd.getAffectedObjects().iterator().next();
	}

	/**
	 * Select an Operation Entity for current project.
	 * @return <code>null</code> if no Operational Entity Package found.
	 */
	protected static Entity selectOperationalEntity(Project project) {
		EntityPkg operationalEntityPkg = ModelQueryHelper.getOperationalEntityPkg(project);
		// Precondition.
		if (null == operationalEntityPkg) {
			return null;
		}
		// Get all existing operational entities.
		Set<EObject> existingOperationalEntities = EObjectExt.getAll(operationalEntityPkg, OaPackage.Literals.ENTITY);
		if (!existingOperationalEntities.isEmpty()) {
			return (Entity) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(existingOperationalEntities), PlatformUI.getWorkbench()
					.getDisplay().getActiveShell());
		}
		// Nothing to select, let's create a default Operational Entity.
		Entity operationalEntity = OaFactory.eINSTANCE.createEntity("Operational Entity"); //$NON-NLS-1$
		operationalEntityPkg.getOwnedEntities().add(operationalEntity);
		return operationalEntity;
	}

	/**
	 * Select (or create) an operational entity and create a state machine region for it.
	 * @return <code>null</code> if no Operational Entity is selected.
	 */
	public static Region selectOperationalEntityAndCreateStateMachineRegion(final Project project) {
		final Entity[] operationalEntity = { null };
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				operationalEntity[0] = selectOperationalEntity(project);
				if (null != operationalEntity[0]) {
					createStateMachineRegion(operationalEntity[0], "Operational Entity State Machine"); //$NON-NLS-1$
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);
		return (null != operationalEntity[0]) ? ModelQueryHelper.getStateMachineRegion(operationalEntity[0]) : null;
	}

	protected static CapabilityRealization selectPACapabilityRealization(Project project) {
		CapabilityRealizationPkg capabilityRealizationPkg = ModelQueryHelper.getPACapabilityRealizationPkg(project);
		if (capabilityRealizationPkg != null) {
			return selectCapabilityRealization(capabilityRealizationPkg);
		}
		return null;
	}

	public static Scenario selectPACapabilityRealizationAndCreateDataFlowScenario(final Project project) {
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			private Scenario scenario;

			/**
			 * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
			 */
			@Override
			public Collection<?> getAffectedObjects() {
				return Collections.singletonList(scenario);
			}

			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				CapabilityRealization capabilityRealization = selectPACapabilityRealization(project);
				if (capabilityRealization != null) {
					scenario = createScenario(capabilityRealization, ScenarioKind.DATA_FLOW);
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);

		return (Scenario) cmd.getAffectedObjects().iterator().next();
	}

	/**
	 * Select (or create) an operational entity and create a state machine region for it.
	 * @return <code>null</code> if no Operational Entity is selected.
	 */
	public static Region selectComponentAtSALevelAndCreateStateMachineRegion(final Project project) {
		final Component[] components = { null };
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				components[0] = selectComponentAtSaLevel(project);
				if (null != components[0]) {
					createStateMachineRegion(components[0], "System State Machine"); //$NON-NLS-1$
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);
		return (null != components[0]) ? ModelQueryHelper.getStateMachineRegion(components[0]) : null;
	}

	/**
	 * Select (or create) an operational entity and create a state machine region for it.
	 * @return <code>null</code> if no Operational Entity is selected.
	 */
	public static Region selectComponentAtLALevelAndCreateStateMachineRegion(final Project project) {
		final Component[] components = { null };
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				components[0] = selectComponentAtLaLevel(project);
				if (null != components[0]) {
					createStateMachineRegion(components[0], "Logical Component State Machine"); //$NON-NLS-1$
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);
		return (null != components[0]) ? ModelQueryHelper.getStateMachineRegion(components[0]) : null;
	}

	/**
	 * Select (or create) an operational entity and create a state machine region for it.
	 * @return <code>null</code> if no Operational Entity is selected.
	 */
	public static Region selectComponentAtPALevelAndCreateStateMachineRegion(final Project project) {
		final Component[] components = { null };
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				components[0] = selectComponentAtPaLevel(project);
				if (null != components[0]) {
					createStateMachineRegion(components[0], "Physical Component State Machine"); //$NON-NLS-1$
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);
		return (null != components[0]) ? ModelQueryHelper.getStateMachineRegion(components[0]) : null;
	}

	/**
	 * Select (or create) an operational entity and create a state machine region for it.
	 * @return <code>null</code> if no Operational Entity is selected.
	 */
	public static Region selectComponentAtAPBSLevelAndCreateStateMachineRegion(final Project project) {
		final Component[] components = { null };
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				components[0] = selectComponentAtEPBSLevel(project);
				if (null != components[0]) {
					createStateMachineRegion(components[0], "Configuration Item State Machine"); //$NON-NLS-1$
				}
			}
		};
		TransactionHelper.getExecutionManager(project).execute(cmd);
		return (null != components[0]) ? ModelQueryHelper.getStateMachineRegion(components[0]) : null;
	}

	/**
	 * Select a System or any Actor for current project.
	 * @return <code>null</code> if no SystemAnlysis Package found.
	 */
	protected static Component selectComponentAtSaLevel(Project project) {
		List<Component> components = new ArrayList<>(0);
		// add System
		System system = ModelQueryHelper.getSystem(project);
		if (null != system) {
			components.add(system);
		}
		// add All Actors
		SystemEngineering systemEngineering = ModelQueryHelper.getSystemEngineering(project);
		if (null != systemEngineering) {
			List<Actor> allActors = SystemEngineeringExt.getAllActors(systemEngineering);
			if ((null != allActors) && !allActors.isEmpty()) {
				components.addAll(allActors);
			}
		}
		// open Selection wizard, if component list not empty
		if (!components.isEmpty()) {
			return (Component) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(components), PlatformUI.getWorkbench().getDisplay()
					.getActiveShell());
		}
		// Nothing to select, let's create a default System.
		System newSystem = CtxFactory.eINSTANCE.createSystem("System"); //$NON-NLS-1$
		SystemAnalysis systemAnalysis = ModelQueryHelper.getSystemAnalysis(project);
		if (null != systemAnalysis) {
			systemAnalysis.setOwnedSystem(newSystem);
			return newSystem;
		}
		return null;
	}

	/**
	 * Select a Logical component or Logical Actor for current project.
	 * @return <code>null</code> if no LogicalArchitecture Package found.
	 */
	protected static Component selectComponentAtLaLevel(Project project) {
		List<Component> components = new ArrayList<>(0);
		// add All Logical Components
		SystemEngineering systemEngineering = ModelQueryHelper.getSystemEngineering(project);
		if (null != systemEngineering) {
			List<LogicalComponent> allLogicalComponents = SystemEngineeringExt.getAllLogicalComponents(systemEngineering);
			if ((null != allLogicalComponents) && !allLogicalComponents.isEmpty()) {
				components.addAll(allLogicalComponents);
			}
			// add all Logical Actors
			LogicalActorPkg logicalActorPkg = ModelQueryHelper.getLogicalActorPkg(project);
			if (null != logicalActorPkg) {
				List<LogicalActor> allLAsFromLAPkg = ActorPkgExt.getAllActors(logicalActorPkg);
				if ((null != allLAsFromLAPkg) && !allLAsFromLAPkg.isEmpty()) {
					components.addAll(allLAsFromLAPkg);
				}
			}
		}
		// open Selection wizard, if component list not empty
		if (!components.isEmpty()) {
			return (Component) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(components), PlatformUI.getWorkbench().getDisplay()
					.getActiveShell());
		}
		// Nothing to select, let's create a default Logical System.
		LogicalComponent newLC = LaFactory.eINSTANCE.createLogicalComponent("Logical System"); //$NON-NLS-1$
		LogicalArchitecture logicalArchitecute = ModelQueryHelper.getLogicalArchitecture(project);
		if (null != logicalArchitecute) {
			logicalArchitecute.setOwnedLogicalComponent(newLC);
			return newLC;
		}
		return null;
	}

	/**
	 * Select a Physical component or Physical Actor for current project.
	 * @return <code>null</code> if no PhysicalArchitecture Package found.
	 */
	protected static Component selectComponentAtPaLevel(Project project) {
		List<Component> components = new ArrayList<>(0);
		// add All Physical Components
		SystemEngineering systemEngineering = ModelQueryHelper.getSystemEngineering(project);
		if (null != systemEngineering) {
			List<PhysicalComponent> allPhysicalComponents = SystemEngineeringExt.getAllPhysicalComponents(systemEngineering);
			if ((null != allPhysicalComponents) && !allPhysicalComponents.isEmpty()) {
				components.addAll(allPhysicalComponents);
			}
			// add all Physical Actors
			List<PhysicalActor> allPhysicalActors = SystemEngineeringExt.getAllPhysicalActors(systemEngineering);
			if ((null != allPhysicalActors) && !allPhysicalActors.isEmpty()) {
				components.addAll(allPhysicalActors);
			}
		}
		// open Selection wizard, if component list not empty
		if (!components.isEmpty()) {
			return (Component) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(components), PlatformUI.getWorkbench().getDisplay()
					.getActiveShell());
		}
		// Nothing to select, let's create a default Logical System.
		PhysicalComponent newPC = PaFactory.eINSTANCE.createPhysicalComponent("Physical System"); //$NON-NLS-1$
		PhysicalArchitecture physicalArchitecute = ModelQueryHelper.getPhysicalArchitecture(project);
		if (null != physicalArchitecute) {
			physicalArchitecute.setOwnedPhysicalComponent(newPC);
			return newPC;
		}
		return null;
	}

	/**
	 * Select a ConfiguraitonItem for current project.
	 * @return <code>null</code> if no EPBSArchitecture Package found.
	 */
	protected static Component selectComponentAtEPBSLevel(Project project) {
		List<Component> components = new ArrayList<>(0);
		// add All Configuration Items
		SystemEngineering systemEngineering = ModelQueryHelper.getSystemEngineering(project);
		if (null != systemEngineering) {
			List<ConfigurationItem> allConfigurationItems = SystemEngineeringExt.getAllConfigurationItems(systemEngineering);
			if ((null != allConfigurationItems) && !allConfigurationItems.isEmpty()) {
				components.addAll(allConfigurationItems);
			}
		}
		// open Selection wizard, if component list not empty
		if (!components.isEmpty()) {
			return (Component) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(components), PlatformUI.getWorkbench().getDisplay()
					.getActiveShell());
		}
		// Nothing to select, let's create a default SystemCI.
		ConfigurationItem newCI = EpbsFactory.eINSTANCE.createConfigurationItem("SystemCI"); //$NON-NLS-1$
		newCI.setKind(ConfigurationItemKind.SYSTEM_CI);
		EPBSArchitecture epbsArchitecute = ModelQueryHelper.getEPBSArchitecture(project);
		if (null != epbsArchitecute) {
			epbsArchitecute.setOwnedConfigurationItem(newCI);
			return newCI;
		}
		return null;
	}
}
