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

    org.polarsys.capella.core.data.information.Class element = (org.polarsys.capella.core.data.information.Class) source_p;

    result_p.addAll(element.getOwnedFeatures());
    result_p.addAll(element.getOwnedDataValues());
    result_p.addAll(element.getNestedGeneralClasses());
    result_p.addAll(element.getSuperGeneralizations());
    result_p.addAll(element.getOwnedStateMachines());

    for (TypedElement typedElt : element.getTypedElements()) {
      if ((typedElt instanceof Property) && (typedElt.eContainer() instanceof Association)) {
        result_p.add(typedElt);
      }
    }

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {

      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFeatures(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getNestedGeneralClasses(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedStateMachines(), context_p);

      for (TypedElement typedElt : element.getTypedElements()) {
        if ((typedElt instanceof Property) && (typedElt.eContainer() instanceof Association)) {
          handler.add(ITransitionConstants.SOURCE_SCOPE, typedElt, context_p);
        }
      }
    }

  }

  @Override
  protected boolean isOrderedContainment(EObject element_p) {
    return true;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

}
