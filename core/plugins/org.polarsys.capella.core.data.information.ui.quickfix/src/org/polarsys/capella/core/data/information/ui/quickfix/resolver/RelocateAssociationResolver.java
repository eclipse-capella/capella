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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class RelocateAssociationResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker_p) {
    List<EObject> modelElements = getModelElements(marker_p);
    if (modelElements.isEmpty()) {
      return;
    }
    final EObject eObject = modelElements.get(0);
    if (eObject instanceof Association) {
      TransactionHelper.getExecutionManager(eObject).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          AssociationExt.moveToCorrectContainer((Association) eObject);
        }
      });
      try {
        marker_p.delete();
      } catch (CoreException exception_p) {
        // d nothing
      }
    }
  }
}
