/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.validation.design.consistency;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.re.CatalogElementLink;

public class DCON_07_RplLinkToRecElement extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    CatalogElementLink link = (CatalogElementLink) ctx.getTarget();
    if (link.getOrigin() != null) {
      if (link.getTarget() != null && link.getTarget().equals(link.getOrigin().getTarget())) {
        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(link.getSource()), EObjectLabelProviderHelper.getText(link.getTarget()) );
      }
    }
    return ctx.createSuccessStatus();
  }

}
