/*******************************************************************************
 * Copyright (c) 2013, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.information.validation.exchangeItemElement;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.validation.rule.AbstractContainerNameConflict;

/**
 * CNC: ContainerNameConflict.
 */
public class CNC_ExchangeItemElement_TargetType extends AbstractContainerNameConflict {

  /**
   * @param obj
   *          the object to be tested for validity. @SuppressWarnings("unused") because param is here for the
   *          sub-classes to check
   */
  @Override
  protected boolean isApplicable(EObject obj) {
    return true;
  }

  @Override
  protected EObject conflictingElement(EObject item) {
    ExchangeItemElement elem = (ExchangeItemElement) item;
    return elem.getAbstractType();
  }

}
