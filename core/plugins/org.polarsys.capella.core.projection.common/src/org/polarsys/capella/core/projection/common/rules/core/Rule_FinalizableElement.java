/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public class Rule_FinalizableElement extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_FinalizableElement(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param _eSpecificLinkKind_p
   */
  public Rule_FinalizableElement(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    super(sourceType_p, targetType_p, _eSpecificLinkKind_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {

    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      FinalizableElement src = (FinalizableElement) element_p;
      if (src.isFinal()) {
        result =
            new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.IsFinal,
                EObjectLabelProviderHelper.getText(element_p)));
      }
    }
    return result;

  }

}
