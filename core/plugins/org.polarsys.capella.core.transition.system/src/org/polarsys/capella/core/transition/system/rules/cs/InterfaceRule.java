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
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (!(element.eContainer() instanceof Component)) {
      if (!(element.eContainer() instanceof BlockArchitecture)) {
        super.retrieveContainer(element, result, context);
      }
    }
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getInterfacePkg(target);
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);
    return result;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Interface element = (Interface) source;
    result.addAll(element.getOwnedExchangeItemAllocations());
    result.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedExchangeItemAllocations(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context);
    }

  }

}
