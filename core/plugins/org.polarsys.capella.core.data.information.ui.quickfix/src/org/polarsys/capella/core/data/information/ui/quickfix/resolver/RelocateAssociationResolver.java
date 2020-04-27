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
  public void run(IMarker marker) {
    List<EObject> modelElements = getModelElements(marker);
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
        marker.delete();
      } catch (CoreException exception) {
        // do nothing
      }
    }
  }
}
