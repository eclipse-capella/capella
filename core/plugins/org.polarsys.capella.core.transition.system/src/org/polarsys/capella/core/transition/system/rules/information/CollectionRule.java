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
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getDataPkg(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Collection element = (Collection) source_p;
    result_p.addAll(element.getIndex());
    result_p.addAll(element.getContainedOperations());
    result_p.add(element.getType());
    result_p.addAll(element.getOwnedFeatures());
    result_p.addAll(element.getOwnedDataValues());
    result_p.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getIndex(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getContainedOperations(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getType(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFeatures(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context_p);

    }

  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.COLLECTION__INDEX, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.COLLECTION__TYPE, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InformationPackage.Literals.COLLECTION__INDEX));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InformationPackage.Literals.COLLECTION__TYPE));
  }

}
