/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that the interface has no several operations with same name and signature.
 */
public class MDCHK_Interface_OperationsSignatureUniqueness extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Interface) {
        Interface itf = (Interface) eObj;
        List<String> lSign = new ArrayList<String>();
        List<String> lSame = new ArrayList<String>();
        String r = ICommonConstants.EMPTY_STRING;

        for (ExchangeItem operation : ExchangeItemExt.getOperations(itf.getExchangeItems())) {
          String starString = "*"; //$NON-NLS-1$
          String sign = operation.getName() + starString;
          for (ExchangeItemElement parameter : operation.getOwnedElements()) {
            if (null != parameter.getType())
              sign = sign + parameter.getType().getName() + starString;
            else
              sign = sign + starString;
          }
          if (lSign.contains(sign))
            if (!lSame.contains(operation.getName()))
              lSame.add(operation.getName());
          lSign.add(sign);
        }
        for (String string : lSame) {
          if (r.length() > 0)
            r += ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER;
          r += string;
        }
        if (r.length() > 0)
          return createFailureStatus(ctx, new Object[] { itf.getName(), r });
      }
    }
    return ctx.createSuccessStatus();
  }
}
