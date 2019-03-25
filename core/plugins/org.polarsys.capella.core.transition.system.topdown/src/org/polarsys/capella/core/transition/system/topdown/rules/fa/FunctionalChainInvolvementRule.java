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
package org.polarsys.capella.core.transition.system.topdown.rules.fa;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionalChainInvolvementRule
    extends org.polarsys.capella.core.transition.system.rules.fa.FunctionalChainInvolvementRule {
  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    FunctionalChainInvolvement transfoSource = (FunctionalChainInvolvement) element;
    if (!TransformationHandlerHelper.getInstance(context)
        .isOrWillBeTransformed(transfoSource.getInvolved(), context).isOK()) {
      return new Status(IStatus.WARNING, "Activity_Transformation", "InvolvedElementNotTransitioned");
    }
    return Status.OK_STATUS;
  }

}
