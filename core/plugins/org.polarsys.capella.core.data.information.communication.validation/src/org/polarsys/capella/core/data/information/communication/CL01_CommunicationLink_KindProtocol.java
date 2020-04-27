/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.communication;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 *
 */
public class CL01_CommunicationLink_KindProtocol extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof CommunicationLink) {
      ArrayList<Object> listExclude = new ArrayList<Object>();
      listExclude.add(CommunicationLinkKind.UNSET);
      boolean hasSameKind = !RefinementLinkExt.hasMissingValuesFromRefined(eObj, CommunicationPackage.Literals.COMMUNICATION_LINK__KIND, listExclude);
      
      listExclude = new ArrayList<Object>();
      listExclude.add(CommunicationLinkProtocol.UNSET);
      boolean hasSameProtocol = !RefinementLinkExt.hasMissingValuesFromRefined(eObj, CommunicationPackage.Literals.COMMUNICATION_LINK__PROTOCOL, listExclude);
      
      if (!hasSameKind || !hasSameProtocol) {
        return ctx.createFailureStatus(new Object[] { ((AbstractNamedElement)eObj.eContainer()).getName(), CapellaElementExt.getName(eObj)});
      }
    }
    return ctx.createSuccessStatus();
  }

}
