/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DWFD57Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final EObject eObj = getModelElements(marker).get(0);

    // Get the appropriate command
    ICommand moveCommand = null;
    if (eObj instanceof EnumerationLiteral && eObj.eContainer() instanceof Enumeration) {
      moveCommand = getMoveEnumerationLiteralCmd((EnumerationLiteral) eObj);
    } else if (eObj instanceof LiteralBooleanValue && eObj.eContainer() instanceof BooleanType) {
      moveCommand = getMoveLiteralBooleanValueCmd((LiteralBooleanValue) eObj);
    }

    if (moveCommand != null) {
      // Execute the command
      TransactionHelper.getExecutionManager(eObj).execute(moveCommand);

      try {
        marker.delete();
      } catch (CoreException exception) {
        // Do nothing
      }
    }
  }

  private ICommand getMoveEnumerationLiteralCmd(EnumerationLiteral enumerationLiteral) {
    return new AbstractReadWriteCommand() {

      @Override
      public void run() {
        Enumeration enumeration = (Enumeration) enumerationLiteral.eContainer();
        enumeration.getOwnedDataValues().remove(enumerationLiteral);
        enumeration.getOwnedLiterals().add(enumerationLiteral);
      }
    };
  }

  private ICommand getMoveLiteralBooleanValueCmd(LiteralBooleanValue literalBooleanValue) {
    return new AbstractReadWriteCommand() {

      @Override
      public void run() {
        BooleanType booleanType = (BooleanType) literalBooleanValue.eContainer();
        booleanType.getOwnedDataValues().remove(literalBooleanValue);
        booleanType.getOwnedLiterals().add(literalBooleanValue);
      }
    };
  }
}
