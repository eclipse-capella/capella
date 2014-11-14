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

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Delete functional chain involvement
 */
public class I_13_Resolver extends AbstractCapellaMarkerResolution {

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
              EObject owner = obj.eContainer();
              if (owner instanceof InvolverElement) {
                ((FunctionalChainInvolvement) obj).setInvolver((InvolverElement) owner);
              }
            }
          }
          flag[0] = true;
        }
      };
      TransactionHelper.getExecutionManager(modelElements).execute(cmd);

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
