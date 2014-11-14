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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InterfaceRule extends AbstractCapellaElementRule {

  public InterfaceRule() {
    super();
    registerUpdatedAttribute(CsPackage.Literals.INTERFACE__MECHANISM);
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERAL_CLASS__VISIBILITY);
    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.INTERFACE;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof Component)) {
      if (!(element_p.eContainer() instanceof BlockArchitecture)) {
        super.retrieveContainer(element_p, result_p, context_p);
      }
    }
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getInterfacePkg(target);
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);
    return result;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    Interface element = (Interface) source_p;
    result_p.addAll(element.getOwnedExchangeItemAllocations());
    result_p.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedExchangeItemAllocations(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context_p);
    }

  }

}
