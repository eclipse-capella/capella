/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Quick Fix which sets the Abstract Type property of an Enumeration Literal element to the parent's type
 *
 */
public class EnumerationLiteralTypeResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {

    final EObject value = getModelElements(marker).get(0);
    if (value instanceof EnumerationLiteral) {

      EnumerationLiteral enumLiteral = (EnumerationLiteral) value;
      EObject container = enumLiteral.eContainer();
      if (container instanceof Enumeration) {

        Enumeration eNum = (Enumeration) container;
        AbstractReadWriteCommand comm = new AbstractReadWriteCommand() {

          @Override
          public void run() {

            // Set the Enumeration Literal element's type to match its parent's type
            if (null != eNum) {
              enumLiteral.setAbstractType(eNum);
            }
          }
        };

        TransactionHelper.getExecutionManager(value).execute(comm);

        boolean typeWasSet = enumLiteral.getAbstractType().equals(eNum);
        if (typeWasSet) {
          try {
            marker.delete();
          } catch (CoreException exception) {
            // no nothing
          }
        }
      }
    }
  }
}
