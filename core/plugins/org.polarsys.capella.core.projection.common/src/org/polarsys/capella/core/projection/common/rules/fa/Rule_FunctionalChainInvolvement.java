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
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 * This rule is the rule that transforms the functional chains
 */
public class Rule_FunctionalChainInvolvement extends Rule_CapellaElement {

  public Rule_FunctionalChainInvolvement(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  public Rule_FunctionalChainInvolvement() {
    super(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (!Query.isElementTransformed(source_p, _transfo)) {
      FunctionalChainInvolvement sourceElement = (FunctionalChainInvolvement) source_p;
      result_p.add(sourceElement.getInvolved());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    FunctionalChainInvolvement transfoSource = (FunctionalChainInvolvement) element_p;
    if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(transfoSource.getInvolved(), context_p).isOK()) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.InvolvedElementNotTransitioned,
          EObjectLabelProviderHelper.getText(transfoSource.getInvolved())));
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    // Creates the references towards the transformed elements of the referenced referenced by the transformation source element
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS, context_p);
  }

}
