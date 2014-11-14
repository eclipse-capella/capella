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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Remove invalid next/previous on physical path involvement
 */
public class I_30_Resolver extends AbstractCapellaMarkerResolution {

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
            if (obj instanceof PhysicalPathInvolvement) {
              EObject objOwner = obj.eContainer();
              for (PhysicalPathInvolvement inv : new ArrayList<PhysicalPathInvolvement>(((PhysicalPathInvolvement) obj).getPreviousInvolvements())) {
                EObject invOwner = inv.eContainer();
                if (!objOwner.equals(invOwner)) {
                  ((PhysicalPathInvolvement) obj).getPreviousInvolvements().remove(inv);
                }
              }
              for (PhysicalPathInvolvement inv : new ArrayList<PhysicalPathInvolvement>(((PhysicalPathInvolvement) obj).getNextInvolvements())) {
                EObject invOwner = inv.eContainer();
                if (!objOwner.equals(invOwner)) {
                  ((PhysicalPathInvolvement) obj).getNextInvolvements().remove(inv);
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
