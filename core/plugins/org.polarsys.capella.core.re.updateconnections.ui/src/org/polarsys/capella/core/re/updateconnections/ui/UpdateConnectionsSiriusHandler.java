/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

public final class UpdateConnectionsSiriusHandler extends UpdateConnectionsHandler {

  protected final List<?> adaptSelection(List<?> originalSelection) {
    List<EObject> result = new ArrayList<EObject>();
    for (Object o : originalSelection) {
      if (o instanceof IDiagramElementEditPart) {
        result.add(((IDiagramElementEditPart) o).resolveTargetSemanticElement());
      }
    }
    return result;
  }

}
