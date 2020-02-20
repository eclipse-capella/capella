/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * change from singleton to static method
 */
public class ModelCreationHelper {

  protected static Scenario createScenario(AbstractCapability abstractCapability, ScenarioKind scenarioKind) {
    if (abstractCapability != null) {
      Scenario sc = InteractionFactory.eINSTANCE.createScenario(); //$NON-NLS-1$
      sc.setKind(scenarioKind);
      abstractCapability.getOwnedScenarios().add(sc);
      CapellaElementExt.creationService(sc);
      return sc;
    }
    return null;
  }

  protected static void createStateMachineRegion(Block block) {
    StateMachine defaultSM = null;
    for (StateMachine sm : block.getOwnedStateMachines()) {
      defaultSM = sm;
      break;
    }
    if (defaultSM == null) {
      defaultSM = CapellacommonFactory.eINSTANCE.createStateMachine();
      block.getOwnedStateMachines().add(defaultSM);
      CapellaElementExt.creationService(defaultSM);
    }
    Region defaultRegion = null;
    for (Region region : defaultSM.getOwnedRegions()) {
      defaultRegion = region;
    }
    if (defaultRegion == null) {
      defaultRegion = CapellacommonFactory.eINSTANCE.createRegion(); //$NON-NLS-1$
      defaultSM.getOwnedRegions().add(defaultRegion);
      CapellaElementExt.creationService(defaultRegion);
    }
  }

  public static AbstractCapability selectCapability(final Project project, BlockArchitecture architecture) {
    AbstractCapability[] result = new AbstractCapability[1];
    AbstractCapabilityPkg capabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
    if (capabilityPkg != null) {
      EClass clazz = CtxPackage.Literals.CAPABILITY;
      if (capabilityPkg instanceof CapabilityRealizationPkg) {
        clazz = LaPackage.Literals.CAPABILITY_REALIZATION;
      } else if (capabilityPkg instanceof OperationalCapabilityPkg) {
        clazz = OaPackage.Literals.OPERATIONAL_CAPABILITY;
      }
      Set<EObject> all = EObjectExt.getAll(capabilityPkg, clazz);
      if (!all.isEmpty()) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        result[0] = (AbstractCapability) SelectionDialogHelper
            .simplePropertySelectionDialogWizard(new ArrayList<EObject>(all), shell);
        return result[0];
      }
      // No capability found, let's create a new one.
      AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
        public void run() {
          result[0] = createAbstractCapability(capabilityPkg);
        }
      };
      TransactionHelper.getExecutionManager(project).execute(cmd);

    }
    return result[0];
  }

  public static AbstractCapability createAbstractCapability(AbstractCapabilityPkg capabilityPkg) {
    AbstractCapability result = null;
    if (capabilityPkg instanceof CapabilityPkg) {
      result = CtxFactory.eINSTANCE.createCapability(); //$NON-NLS-1$
      ((CapabilityPkg) capabilityPkg).getOwnedCapabilities().add((Capability) result);
    } else if (capabilityPkg instanceof CapabilityRealizationPkg) {
      result = LaFactory.eINSTANCE.createCapabilityRealization(); //$NON-NLS-1$
      ((CapabilityRealizationPkg) capabilityPkg).getOwnedCapabilityRealizations().add((CapabilityRealization) result);
    } else if (capabilityPkg instanceof OperationalCapabilityPkg) {
      result = OaFactory.eINSTANCE.createOperationalCapability(); //$NON-NLS-1$
      ((OperationalCapabilityPkg) capabilityPkg).getOwnedOperationalCapabilities().add((OperationalCapability) result);
    }
    CapellaElementExt.creationService(result);
    return result;
  }

  public static Scenario selectCapabilityAndCreateScenario(final Project project,
      final BlockArchitecture architecture, final ScenarioKind scenarioKind) {
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

  @Deprecated
  public static Scenario selectLACapabilityRealizationAndCreateDataFlowScenario(final Project project) {
    return selectCapabilityAndCreateScenario(project, ModelQueryHelper.getLogicalArchitecture(project), ScenarioKind.DATA_FLOW);
  }

  @Deprecated
  public static Scenario selectOperationalCapabilityAndCreateInteractionScenario(final Project project) {
    return selectCapabilityAndCreateScenario(project, ModelQueryHelper.getOperationalAnalysis(project), ScenarioKind.INTERACTION);
  }

  @Deprecated
  public static Scenario selectPACapabilityRealizationAndCreateDataFlowScenario(final Project project) {
    return selectCapabilityAndCreateScenario(project, ModelQueryHelper.getPhysicalArchitecture(project), ScenarioKind.DATA_FLOW);
  }

  /**
   * Select (or create) an operational entity and create a state machine region for it.
   * 
   * @return <code>null</code> if no Operational Entity is selected.
   */
  @Deprecated
  public static Region selectOperationalEntityAndCreateStateMachineRegion(final Project project) {
    return selectComponentCreateStateMachineRegion(project, ModelQueryHelper.getOperationalAnalysis(project));
  }

  /**
   * Select (or create) an operational entity and create a state machine region for it.
   * 
   * @return <code>null</code> if no Operational Entity is selected.
   */
  @Deprecated
  public static Region selectComponentAtSALevelAndCreateStateMachineRegion(final Project project) {
    return selectComponentCreateStateMachineRegion(project, ModelQueryHelper.getSystemAnalysis(project));
  }

  /**
   * Select (or create) an operational entity and create a state machine region for it.
   * 
   * @return <code>null</code> if no Operational Entity is selected.
   */
  @Deprecated
  public static Region selectComponentAtLALevelAndCreateStateMachineRegion(final Project project) {
    return selectComponentCreateStateMachineRegion(project, ModelQueryHelper.getLogicalArchitecture(project));
  }

  /**
   * Select (or create) an operational entity and create a state machine region for it.
   * 
   * @return <code>null</code> if no Operational Entity is selected.
   */
  @Deprecated
  public static Region selectComponentAtPALevelAndCreateStateMachineRegion(final Project project) {
    return selectComponentCreateStateMachineRegion(project, ModelQueryHelper.getPhysicalArchitecture(project));
  }

  public static Region selectComponentCreateStateMachineRegion(Project project, BlockArchitecture architecture) {
    final Component[] components = { null };
    AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        components[0] = selectComponent(architecture);
        if (null != components[0]) {
          createStateMachineRegion(components[0]); //$NON-NLS-1$
        }
      }
    };
    TransactionHelper.getExecutionManager(project).execute(cmd);
    return (null != components[0]) ? ModelQueryHelper.getStateMachineRegion(components[0]) : null;
  }

  /**
   * Select (or create) an operational entity and create a state machine region for it.
   * 
   * @return <code>null</code> if no Operational Entity is selected.
   */
  @Deprecated
  public static Region selectComponentAtAPBSLevelAndCreateStateMachineRegion(final Project project) {
    return selectComponentCreateStateMachineRegion(project, ModelQueryHelper.getEPBSArchitecture(project));
  }

  protected static Component selectComponent(BlockArchitecture architecture) {
    List<Component> components = new ArrayList<>();
    components.addAll(BlockArchitectureExt.getAllComponents(architecture));
    if (!components.isEmpty()) {
      return (Component) SelectionDialogHelper.simplePropertySelectionDialogWizard(new ArrayList<EObject>(components),
          PlatformUI.getWorkbench().getDisplay().getActiveShell());
    }
    return BlockArchitectureExt.getFirstComponent(architecture, true);
  }

}
