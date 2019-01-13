/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DWFD57Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final EObject eObj = getModelElements(marker).get(0);
    if (eObj instanceof EnumerationLiteral && eObj.eContainer() instanceof Enumeration) {
      AbstractReadWriteCommand moveCommand = new AbstractReadWriteCommand() {

        @Override
        public void run() {
          Enumeration enumeration = (Enumeration) eObj.eContainer();
          enumeration.getOwnedDataValues().remove(eObj);
          enumeration.getOwnedLiterals().add((EnumerationLiteral) eObj);
        }
      };

      // Execute the command
      TransactionHelper.getExecutionManager(eObj).execute(moveCommand);

      try {
        marker.delete();
      } catch (CoreException exception) {
        // Do nothing
      }
    }
  }
}
