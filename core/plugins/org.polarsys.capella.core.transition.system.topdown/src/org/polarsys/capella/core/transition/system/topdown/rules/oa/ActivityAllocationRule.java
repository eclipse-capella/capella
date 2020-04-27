/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.oa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.core.AllocationRule;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.attachment.ActivityAllocationAttachmentHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class ActivityAllocationRule extends AllocationRule {

  @Override
  protected EClass getSourceType() {
    return OaPackage.Literals.ACTIVITY_ALLOCATION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
  }

  @Override
  protected EObject getTarget(EObject source_p, IContext context_p) {
    EObject target = super.getTarget(source_p, context_p);

    String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
    if (ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR.equals(transitionKind) || ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(transitionKind)) {
      if (!(OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
          ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL_DEFAULT))) {
        if (!TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(target, context_p)) {
          return null;
        }
      }
    }
    return target;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    Collection<EObject> objects = new ArrayList<EObject>();

    ActivityAllocation element = (ActivityAllocation) element_p;
    for (Entity entity : element.getRole().getAllocatingEntities()) {
      //If entity is in scope, we create an allocation
      if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, entity, context_p)) {
        EObject target = super.transformDirectElement(element_p, context_p);
        objects.add(target);
        ActivityAllocationAttachmentHelper.getInstance(context_p).addEntity(context_p, (ComponentFunctionalAllocation) target, entity);
      }

    }
    return objects;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {

    for (Entity entity : ActivityAllocationAttachmentHelper.getInstance(context_p).getEntities(context_p, (ComponentFunctionalAllocation) result_p)) {
      return TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(entity, context_p,
          SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));
    }

    return super.getBestContainer(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  @Override
  protected void premicesAllocationRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    IContext context = getCurrentContext();
    ActivityAllocation element = (ActivityAllocation) element_p;

    needed_p.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT));

    for (RoleAllocation roleAllocation : element.getRole().getRoleAllocations()) {
      Entity entity = roleAllocation.getEntity();

      //If entity is in scope, we create an allocation
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, entity, context)) {
        needed_p.addAll(createDefaultPrecedencePremices(Collections.singleton((EObject) entity),
            ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT.getName()));
      }
    }

  }

  @Override
  protected void attachAllocationRelated(EObject element_p, EObject result_p, IContext context_p) {
    Collection<Entity> entities = ActivityAllocationAttachmentHelper.getInstance(context_p).getEntities(context_p, (ComponentFunctionalAllocation) result_p);
    if (!entities.isEmpty()) {
      Entity entity = entities.iterator().next();
      EObject target =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(entity, context_p,
              SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));
      AttachmentHelper.getInstance(context_p).attachElementByReference(result_p, target, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT);
      ActivityAllocationAttachmentHelper.getInstance(context_p).removeEntity(context_p, (ComponentFunctionalAllocation) result_p, entity);

    }

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, context_p);
  }

}
