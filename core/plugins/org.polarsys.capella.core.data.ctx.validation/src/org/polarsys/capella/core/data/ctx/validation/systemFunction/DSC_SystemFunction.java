/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SA.
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx.validation.systemFunction;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.validation.rule.AbstractDescriptionCheckingRule;
import org.polarsys.capella.core.validation.rule.AbstractValidationRuleOnSystemAnalysis;

/**
 * Functions should have a description
 */
public class DSC_SystemFunction extends AbstractDescriptionCheckingRule {

  @Override
  protected boolean isApplicable(final EObject eObj) {
    return AbstractValidationRuleOnSystemAnalysis.belongsToSA(eObj);
  }

}
