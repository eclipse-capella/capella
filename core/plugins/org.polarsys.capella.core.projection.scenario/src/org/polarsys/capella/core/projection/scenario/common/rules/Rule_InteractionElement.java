/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.common.rules;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.projection.common.CommonRule;

public abstract class Rule_InteractionElement extends CommonRule {

  public Rule_InteractionElement(EClass sourceType, EClass targetType, EClass _eSpecificLinkKind) {
    super(sourceType, targetType, _eSpecificLinkKind);
  }

  public Rule_InteractionElement(EClass sourceType, EClass targetType) {
    super(sourceType, targetType);
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    super.doGoDeep(element, result);
    result.addAll(((CapellaElement) element).getOwnedConstraints());
  }
}
