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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementEditResolver;

public class OpenPropertyResolver extends CapellaElementEditResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEditableInstance(EObject value) {
    if (value instanceof Property) {
      return true;
    }
    return false;
  }

}
