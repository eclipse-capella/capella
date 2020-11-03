/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.cs;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.StructureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InterfaceRule extends org.polarsys.capella.core.transition.system.rules.cs.InterfaceRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public void apply(EObject element_p, IContext context_p) throws Exception {
    super.apply(element_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    Interface element = (Interface) source_p;
    result_p.addAll(element.getSuper());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuper(), context_p);
    }

  }

  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    return super.transformElement(element_p, context_p);
  }

  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);

    if (result_p instanceof Structure) {
      Structure named = (Structure) result_p;
      if (!TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(element_p.eContainer(), context_p).contains(result_p.eContainer())) {
        named.setName(StructureExt.getNewStructureName(named, element_p.eContainer()));
      }
    }
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject bestContainer = super.getBestContainer(element_p, result_p, context_p);

    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    if ((target instanceof PhysicalArchitecture) && (bestContainer instanceof BlockArchitecture)) {
      return null;
    }

    if ((bestContainer != null)) {
      EObject sourceContainer = element_p.eContainer();
      if ((sourceContainer instanceof Block)) {
        EObject targetObject =
            TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(
                sourceContainer,
                context_p,
                new EClassSelectionContext(SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p,
                    ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION), CsPackage.Literals.BLOCK));

        if ((targetObject != null) && (targetObject instanceof Component)) {
          InterfacePkg pkg = ((Component) targetObject).getOwnedInterfacePkg();

          //If the owned pkg is not the transitioned package, put it inside
          if ((pkg != null) && (pkg != result_p)) {
            return pkg;
          }
          return targetObject;
        }

        return null;
      }
    }

    return bestContainer;
  }
}
