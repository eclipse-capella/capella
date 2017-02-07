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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class Rule_Involvement extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Involvement(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  public Rule_Involvement() {
    super(CapellacorePackage.Literals.INVOLVEMENT, CapellacorePackage.Literals.INVOLVEMENT);
    setNeedsContext(true);
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param _eSpecificLinkKind_p
   */
  public Rule_Involvement(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    super(sourceType_p, targetType_p, _eSpecificLinkKind_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, context_p);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      Involvement ce = (Involvement) element_p;

      EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
      if (element_p == transfoSource) {
        return result;
      }
      if (ce.getInvolved() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.SourceNull);
      }
      if (ce.getInvolver() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getInvolved(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.SourceBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getInvolved())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getInvolver(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TargetBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getInvolver())));
      }
    }
    return result;
  }
}
