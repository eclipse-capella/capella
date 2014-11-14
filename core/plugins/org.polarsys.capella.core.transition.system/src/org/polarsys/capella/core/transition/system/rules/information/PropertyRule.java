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
package org.polarsys.capella.core.transition.system.rules.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class PropertyRule extends MultiplicityElementRule {

  public PropertyRule() {
    super();
    registerUpdatedAttribute(InformationPackage.Literals.PROPERTY__AGGREGATION_KIND);
    registerUpdatedAttribute(InformationPackage.Literals.PROPERTY__IS_DERIVED);
    registerUpdatedAttribute(InformationPackage.Literals.PROPERTY__IS_PART_OF_KEY);
    registerUpdatedAttribute(InformationPackage.Literals.PROPERTY__IS_READ_ONLY);
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__IS_STATIC);
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__IS_ABSTRACT);
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__VISIBILITY);
    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);
  }

  @Override
  protected boolean isOrderedContainment(EObject element_p) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.PROPERTY;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    Property element = (Property) source_p;
    result_p.add(element.getAssociation());
    result_p.add(element.getType());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getAssociation(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getType(), context_p);

    }

  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
        context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE));

  }
}
