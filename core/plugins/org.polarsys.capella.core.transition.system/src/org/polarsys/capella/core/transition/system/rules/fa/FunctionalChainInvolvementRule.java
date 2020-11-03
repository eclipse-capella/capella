/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class FunctionalChainInvolvementRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    FunctionalChainInvolvement inv = (FunctionalChainInvolvement) source;
    // Add dependent elements to the scope
    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      result.add(inv.getInvolved());
      handler.add(ITransitionConstants.SOURCE_SCOPE, inv.getInvolved(), context);
    }
  }
  
  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    // Specify which elements must be transformed first
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED));
    if (element instanceof FunctionalChainReference) {
      needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN));
    }
  }
  
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    // Attach necessary references
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, context);
    if (element instanceof FunctionalChainReference) {
      AttachmentHelper.getInstance(context).attachTracedElements(element, result,
          FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN, context);
    }
  }
  
  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);
    if (result.isOK()) {
      FunctionalChainInvolvement fci = (FunctionalChainInvolvement) source;
      EObject involvedElement = fci.getInvolved();
      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(fci, context, involvedElement);
    }
    return result;
  }
}
