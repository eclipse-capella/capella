/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.projection.scenario.fc2fs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.refmap.Pair;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

public class FC2FSExt {

  private FC2FSExt() {
    // To hide the implicit public one
  }

  public static boolean isLogEnabled() {
    PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    return preferenceHelper.isFC2FSLogEnabled();
  }

  public static boolean isCreateMsgWithReply(FunctionalChain fc) {
    PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    if (fc instanceof OperationalProcess) {
      return preferenceHelper.isOP2OASCreateMsgWithReply();
    }
    return preferenceHelper.isFC2FSCreateMsgWithReply();
  }

  public static void addToModel(Collection<Pair<FunctionalChain, Scenario>> fc2ScenarioPairs) {
    // We assume that all objects are in the same session
    Session session = SessionManager.INSTANCE.getSession(fc2ScenarioPairs.iterator().next().getFirstValue());

    ExecutionManager manager = TransactionHelper.getExecutionManager(session);
    manager.execute(new AbstractReadWriteCommand() {

      Collection<Scenario> addedScenarions = new ArrayList<>();

      @Override
      public void run() {
        Iterator<Pair<FunctionalChain, Scenario>> iterator = fc2ScenarioPairs.iterator();
        while (iterator.hasNext()) {
          Pair<FunctionalChain, Scenario> next = iterator.next();
          FunctionalChain fc = next.getFirstValue();
          Scenario scenario = next.getSecondValue();
          AbstractCapability capability = FC2FSExt.getCapability(fc);
          if (capability != null) {
            // Get a unique name once we know the container
            String uniqueName = EcoreUtil2.getUniqueName(scenario, capability,
                InteractionPackage.eINSTANCE.getAbstractCapability_OwnedScenarios(),
                ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), scenario.getName());
            scenario.setName(uniqueName);
            capability.getOwnedScenarios().add(scenario);
          }
          addedScenarions.add(scenario);
        }
      }

      @Override
      public Collection<?> getAffectedObjects() {
        return addedScenarions;
      }
    });
  }

  public static AbstractCapability getCapability(FunctionalChain functionalChain) {
    // If FunctionalChain is contained in a Capability return it.
    EObject eContainer = functionalChain.eContainer();
    if (eContainer instanceof AbstractCapability) {
      return (AbstractCapability) eContainer;
    }
    // If not look in the block architecture
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(functionalChain);
    AbstractCapabilityPkg capabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
    if (capabilityPkg != null) {
      List<AbstractCapability> allCapabilities = AbstractCapabilityPkgExt.getAllAbstractCapabilities(capabilityPkg);
      if (allCapabilities.size() == 1) {
        return allCapabilities.iterator().next();
      }
      if (!allCapabilities.isEmpty()) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        return (AbstractCapability) SelectionDialogHelper
            .simplePropertySelectionDialogWizard(new ArrayList<EObject>(allCapabilities), shell);
      }
      // No capability found, let's create a new one.
      return AbstractCapabilityPkgExt.createAbstractCapability(capabilityPkg);
    }
    return null;
  }

  /**
   * 
   * @param fc
   *          The FunctionalChain
   * @return the available, already initialized, Scenarios in the same block architecture of the given functional chain.
   *
   */
  public static Collection<Scenario> getAvailableInitializedScenarios(FunctionalChain fc) {
    Set<Scenario> result = new HashSet<>();
    List<EObject> referencers = EObjectExt.getReferencers(fc, CapellacommonPackage.Literals.TRANSFO_LINK,
        ModellingcorePackage.eINSTANCE.getAbstractTrace_TargetElement());
    BlockArchitecture fcBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(fc);
    for (EObject referencer : referencers) {
      if (referencer instanceof TransfoLink && referencer.eContainer() instanceof Scenario
          && TransitionHelper.getService().isFunctionalScenario((Scenario) referencer.eContainer())) {
        Scenario scenario = (Scenario) referencer.eContainer();
        BlockArchitecture scenarioBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario);
        if (EcoreUtil.equals(fcBlockArchitecture, scenarioBlockArchitecture)) {
          result.add(scenario);
        }
      }
    }
    return result;
  }

  public static Shell getActiveShell() {
    return Display.getDefault().getActiveShell();
  }
}
