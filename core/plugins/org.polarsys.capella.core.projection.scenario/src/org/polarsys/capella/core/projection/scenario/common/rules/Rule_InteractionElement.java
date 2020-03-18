/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
