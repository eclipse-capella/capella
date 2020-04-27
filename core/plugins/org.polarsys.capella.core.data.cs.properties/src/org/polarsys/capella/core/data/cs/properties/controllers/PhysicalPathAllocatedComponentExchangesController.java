/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.properties.controllers.AllocatedComponentExchangesController;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 */
public class PhysicalPathAllocatedComponentExchangesController extends AllocatedComponentExchangesController {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    super.doAddOperationInWriteOpenValues(semanticElement, semanticFeature, object);

    if (CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfPhysicalPortToComponentPortOnPhysicalPathAllowed()
        && (semanticElement instanceof PhysicalPath) && (object instanceof ComponentExchange)) {
      PhysicalPathExt.synchronizeAllocations((PhysicalPath) semanticElement, (ComponentExchange) object);
    }
  }

  /**
   * {@inheritDoc}
   * The synchronization of the delegations/allocations is now managed by {@link DeleteHelper} class
   */
  @Override
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    super.doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, object);
  }
}
