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

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class CollectionRule extends MultiplicityElementRule {

  public CollectionRule() {
    super();
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT);
    registerUpdatedAttribute(InformationPackage.Literals.COLLECTION__AGGREGATION_KIND);
    registerUpdatedAttribute(InformationPackage.Literals.COLLECTION__KIND);
    registerUpdatedAttribute(InformationPackage.Literals.COLLECTION__VISIBILITY);
    registerUpdatedAttribute(InformationPackage.Literals.COLLECTION__IS_PRIMITIVE);
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.COLLECTION;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getDataPkg(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    Collection element = (Collection) source;
    result.addAll(element.getIndex());
    result.addAll(element.getContainedOperations());
    result.add(element.getType());
    result.addAll(element.getOwnedFeatures());
    result.addAll(element.getOwnedDataValues());
    result.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getIndex(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getContainedOperations(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getType(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFeatures(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context);

    }

  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    super.retrieveContainer(element, result, context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.COLLECTION__INDEX, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.COLLECTION__TYPE, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.COLLECTION__INDEX));
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS));
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.COLLECTION__TYPE));
  }

}
