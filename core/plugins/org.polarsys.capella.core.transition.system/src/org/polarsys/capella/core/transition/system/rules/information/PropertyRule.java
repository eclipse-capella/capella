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
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.PROPERTY;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Property element = (Property) source;
    result.add(element.getAssociation());
    result.add(element.getType());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getAssociation(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getType(), context);

    }

  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
        context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE));

  }
}
