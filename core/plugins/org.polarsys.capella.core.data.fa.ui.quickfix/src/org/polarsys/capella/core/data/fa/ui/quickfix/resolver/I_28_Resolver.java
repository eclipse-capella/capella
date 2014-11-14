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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Delete functional chain involvement
 */
public class I_28_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    final List<EObject> modelElements = getModelElements(marker_p);

    if (!modelElements.isEmpty()) {
      final boolean flag[] = { false };
      AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
        public void run() {
          for (EObject obj : modelElements) {
            if (obj instanceof FunctionalChainInvolvement) {
              EObject objOwner = obj.eContainer();
              for (FunctionalChainInvolvement inv : new ArrayList<FunctionalChainInvolvement>(((FunctionalChainInvolvement) obj).getPreviousFunctionalChainInvolvements())) {
                EObject invOwner = inv.eContainer();
                if (!objOwner.equals(invOwner)) {
                  ((FunctionalChainInvolvement) obj).getPreviousFunctionalChainInvolvements().remove(inv);
                }
              }
              for (FunctionalChainInvolvement inv : new ArrayList<FunctionalChainInvolvement>(((FunctionalChainInvolvement) obj).getNextFunctionalChainInvolvements())) {
                EObject invOwner = inv.eContainer();
                if (!objOwner.equals(invOwner)) {
                  ((FunctionalChainInvolvement) obj).getNextFunctionalChainInvolvements().remove(inv);
                }
              }
            }
          }
          flag[0] = true;
        }
      };
      MDEAdapterFactory.getExecutionManager().execute(cmd);

      // remove the marker if the element is deleted
      if (flag[0] == true) {
        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          // do nothing
        }
      }
    }
  }
}
