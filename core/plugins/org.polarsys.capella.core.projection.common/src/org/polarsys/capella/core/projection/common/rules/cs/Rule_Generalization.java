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
package org.polarsys.capella.core.projection.common.rules.cs;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class Rule_Generalization extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Generalization() {
    super(CapellacorePackage.Literals.GENERALIZATION, CapellacorePackage.Literals.GENERALIZATION);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    if (shouldUpdate()) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.GENERALIZATION__SUPER, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.GENERALIZATION__SUB, context_p);
    }
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Generalization sourceElement = (Generalization) source_p;
    if (sourceElement.getSub() != null) {
      result_p.add(sourceElement.getSub());
    }
    if (sourceElement.getSuper() != null) {
      result_p.add(sourceElement.getSuper());
    }
  }

  /**
   * @return
   */
  protected boolean shouldUpdate() {
    return true;
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      Generalization ce = (Generalization) element_p;

      EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
      if (element_p == transfoSource) {
        return result;
      }
      if (ce.getSuper() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.SourceNull);
      }
      if (ce.getSub() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context_p)
          .isOrWillBeTransformedTo(ce.getSuper(), context_p, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.SourceBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getSuper())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformedTo(ce.getSub(), context_p, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT)
          .isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TargetBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getSub())));
      }
    }
    return result;
  }
}
