/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.projection.commands.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.refmap.Pair;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

public class FC2FSHelper {

  private FC2FSHelper() {
    // To hide the implicit public one
  }

  public static boolean isCreateMsgWithReply(FunctionalChain fc) {
    PreferenceHelper preferenceHelper = new PreferenceHelper();
    if (fc instanceof OperationalProcess) {
      return preferenceHelper.isOP2OASCreateMsgWithReply();
    }
    return preferenceHelper.isFC2FSCreateMsgWithReply();
  }

  public static void addToModel(Collection<Pair<FunctionalChain, Scenario>> fc2ScenarioPairs) {
    // We assume that of objects are in the same session
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
          BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(fc);
          AbstractCapability capability = FC2FSHelper.getCapability(rootBlockArchitecture);
          if (capability != null) {
            capability.getOwnedScenarios().add(scenario);
          }
          // Add a trace from the scenario to the functional chain
          TransfoLink trace = CapellacommonFactory.eINSTANCE.createTransfoLink();
          trace.setTargetElement(fc);
          scenario.getOwnedTraces().add(trace);
          addedScenarions.add(scenario);
        }
      }

      @Override
      public Collection<?> getAffectedObjects() {
        return addedScenarions;
      }
    });
  }

  public static AbstractCapability getCapability(BlockArchitecture architecture) {
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
}
