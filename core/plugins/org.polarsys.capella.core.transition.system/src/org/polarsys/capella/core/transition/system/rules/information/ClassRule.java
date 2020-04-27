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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class ClassRule extends AbstractCapellaElementRule {

  public ClassRule() {
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT);
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERAL_CLASS__VISIBILITY);
    registerUpdatedAttribute(InformationPackage.Literals.CLASS__IS_PRIMITIVE);
    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.CLASS;
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

    org.polarsys.capella.core.data.information.Class element = (org.polarsys.capella.core.data.information.Class) source;

    result.addAll(element.getOwnedFeatures());
    result.addAll(element.getOwnedDataValues());
    result.addAll(element.getNestedGeneralClasses());
    result.addAll(element.getSuperGeneralizations());
    result.addAll(element.getOwnedStateMachines());

    for (TypedElement typedElt : element.getTypedElements()) {
      if ((typedElt instanceof Property) && (typedElt.eContainer() instanceof Association)) {
        result.add(typedElt);
      }
    }

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {

      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFeatures(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getNestedGeneralClasses(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedStateMachines(), context);

      for (TypedElement typedElt : element.getTypedElements()) {
        if ((typedElt instanceof Property) && (typedElt.eContainer() instanceof Association)) {
          handler.add(ITransitionConstants.SOURCE_SCOPE, typedElt, context);
        }
      }
    }

  }

  @Override
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    super.retrieveContainer(element, result, context);
  }

}
