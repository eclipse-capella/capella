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
package org.polarsys.capella.core.data.cs.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    super.doAddOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, object_p);

    if (CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfPhysicalPortToComponentPortOnPhysicalPathAllowed()
        && (semanticElement_p instanceof PhysicalPath) && (object_p instanceof ComponentExchange)) {
      PhysicalPathExt.synchronizeAllocations((PhysicalPath) semanticElement_p, (ComponentExchange) object_p);
    }
  }

  /**
   * {@inheritDoc}
   * The synchronization of the delegations/allocations is now managed by {@link DeleteHelper} class
   */
  @Override
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    super.doRemoveOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, object_p);
  }
}
