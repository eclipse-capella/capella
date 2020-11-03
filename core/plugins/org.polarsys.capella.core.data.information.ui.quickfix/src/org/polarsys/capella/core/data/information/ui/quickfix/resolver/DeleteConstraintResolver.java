/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class DeleteConstraintResolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {
    if (obj instanceof OpaqueExpression) {
      OpaqueExpression opExpression = (OpaqueExpression) obj;
      Object cstObj = opExpression.eContainer();
      if (cstObj instanceof Constraint) {
        return cstObj;
      }
    }
    return null;
  }

}
