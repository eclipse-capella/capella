/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.launcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.handlers.log.ILogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.system.handlers.log.CapellaLogHandler;

public class UpdateReplicaLauncher extends org.polarsys.capella.common.re.launcher.UpdateReplicaLauncher {

  @Override
  protected void initializeLogHandler() {
    ILogHandler handler = new CapellaLogHandler(getReportComponent());
    handler.init(TransitionContext.EMPTY_CONTEXT);
    LogHelper.setInstance(handler);
  }

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.core.re.updateReplica";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.core.re.updateReplica";
  }

  @Override
  protected void initOverrides() {
    addOverrides(org.polarsys.capella.common.re.rpl2re.activities.InitializeTransitionActivity.ID,
        org.polarsys.capella.core.re.rpl2re.activities.InitializeTransitionActivity.ID);

    addOverrides(org.polarsys.capella.common.re.activities.InitializeReMgtActivity.ID,
        org.polarsys.capella.core.re.activities.InitializeReMgtActivity.ID);

    addOverrides(org.polarsys.capella.common.re.re2rpl.activities.InitializeTransitionActivity.ID,
        org.polarsys.capella.core.re.re2rpl.activities.InitializeTransitionActivity.ID);

    addOverrides(org.polarsys.capella.common.re.activities.DifferencesComputingActivity.ID,
        org.polarsys.capella.core.re.activities.DifferencesComputingActivity.ID);

    addOverrides(org.polarsys.capella.common.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity.ID,
        org.polarsys.capella.core.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity.ID);

    addOverrides(org.polarsys.capella.common.re.rpl2re.activities.InitializeDiffMergeUpdateReActivity.ID,
        org.polarsys.capella.core.re.rpl2re.activities.InitializeDiffMergeUpdateReActivity.ID);

  }

  @Override
  public void run(Collection<?> selection, boolean save, IProgressMonitor monitor) {
    Collection<EObject> semanticElements = CapellaAdapterHelper.resolveSemanticObjects(selection);
    
    HashSet<CatalogElement> catalogElements = new HashSet<>();
    for (EObject selected : semanticElements) {
      if (selected instanceof CatalogElement) {
        catalogElements.add((CatalogElement) selected);
      } else {
        catalogElements.addAll(ReplicableElementExt.getReferencingReplicableElements((EObject) selected));
      }
    }
    
    for (CatalogElement selected : catalogElements) {
      ArrayList<Object> item = new ArrayList<>();
      item.add(selected);
      launch(item, getPurpose(), getMapping(), monitor);
    }
  }
}
