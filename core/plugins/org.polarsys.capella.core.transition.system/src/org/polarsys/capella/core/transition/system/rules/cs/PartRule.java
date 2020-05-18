/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class PartRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PART;
  }

  @Override
  public IStatus transformRequired(EObject eObject1, IContext iContext1) {
    IStatus result = super.transformRequired(eObject1, iContext1);

    if (result.isOK()) {
      AbstractTypedElement element = (AbstractTypedElement) eObject1;
      AbstractType inSrc = element.getAbstractType();

      result = TransformationHandlerHelper.getInstance(iContext1).checkTransformRequired(element, iContext1, inSrc);
    }
    return result;
  }

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
    result.add(((Part) element).getAbstractType());
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getComponentPkg(target);
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    if (container instanceof Component) {
      return CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
    }
    if (container instanceof ComponentPkg) {
      return CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
    }
    return element.eContainingFeature();
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Part element = (Part) source;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getAbstractType(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getDeploymentLinks(), context);
      result.addAll(element.getDeploymentLinks());
    }

    // Add all involving involvements
    handler.addAll(ITransitionConstants.SOURCE_SCOPE, ((InvolvedElement) element).getInvolvingInvolvements(), context);

    for (Involvement involvement : ((InvolvedElement) element).getInvolvingInvolvements()) {
      if (involvement instanceof PhysicalPathInvolvement) {
        result.add(involvement);
        InvolverElement involver = involvement.getInvolver();
        if ((involver != null) && (involver instanceof PhysicalPath)) {
          result.add(involver);
          handler.addAll(ITransitionConstants.SOURCE_SCOPE, Arrays.asList(involver), context);
        }
      }
    }

    result.add(element.getAbstractType());
    result.add(element.getOwnedMinCard());
    result.add(element.getOwnedMinLength());
    result.add(element.getOwnedMinValue());
    result.add(element.getOwnedMaxCard());
    result.add(element.getOwnedMaxLength());
    result.add(element.getOwnedMaxValue());
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    // Nothing here
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
