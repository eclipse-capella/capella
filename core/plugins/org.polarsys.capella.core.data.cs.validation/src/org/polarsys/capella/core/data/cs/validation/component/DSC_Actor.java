/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.validation.rule.AbstractDescriptionCheckingRule;
import org.polarsys.capella.core.validation.rule.AbstractValidationRuleOnSystemAnalysis;

/**
 * Actors should have a description
 */
public class DSC_Actor extends AbstractDescriptionCheckingRule {

  @Override
  protected boolean isApplicable(final EObject eObj) {
    if (eObj instanceof Component) {
      final Component comp = (Component) eObj;

      if (comp.isActor())
        return AbstractValidationRuleOnSystemAnalysis.belongsToSA(eObj);
      return false;
    }
    return false;
  }

}
