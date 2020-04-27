/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class UnionRule extends ClassRule {

  public UnionRule() {
    super();
    registerUpdatedAttribute(InformationPackage.Literals.UNION__KIND);
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.UNION;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Union element = (Union) source;
    result.add(element.getDefaultProperty());
    result.add(element.getDiscriminant());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getDiscriminant(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getDefaultProperty(), context);
    }

  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.UNION__DEFAULT_PROPERTY, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.UNION__DISCRIMINANT, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.UNION__DEFAULT_PROPERTY));
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.UNION__DISCRIMINANT));
  }

}
