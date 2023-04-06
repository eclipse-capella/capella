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
package org.polarsys.capella.core.transition.system.topdown.rules.la.lc2pc;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PartRule extends org.polarsys.capella.core.transition.system.rules.cs.PartRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PART;
  }

  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    Part partSrc = (Part) element;
    boolean result =
        CsPackage.Literals.COMPONENT.isSuperTypeOf(TransformationHandlerHelper.getInstance(context).getTargetType(partSrc.getAbstractType(), context));
    if (!result) {
      return new Status(IStatus.WARNING, Messages.Activity_Transition, "Type transitioned to ComponentPackage");
    }
    return super.transformRequired(element, context);
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);

    if (!(ComponentPkgExt.isRootComponentPkg(element.eContainer()))) {
      return BlockArchitectureExt.getOrCreateSystem(target);
    }
    return BlockArchitectureExt.getComponentPkg(target);
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    Part part = (Part) element;
    EClass targetType = TransformationHandlerHelper.getInstance(context).getTargetType(part.getAbstractType(), context);

    boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(part));

    //Specific case for the part of the root component. Retrieve the existing part
    if (part.getAbstractType() instanceof Component) {
      Component type = (Component) part.getAbstractType();
      if ((type != null) && (type.getRepresentingParts().size() == 1)) {

        EObject targetObject =
            TransformationHandlerHelper.getInstance(context).getBestTracedElement(
                type,
                context,
                new EClassSelectionContext(SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context,
                    ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION), CsPackage.Literals.COMPONENT));

        if (targetObject instanceof Component) {
          Component targetCps = (Component) targetObject;
          if ((targetCps.getRepresentingParts().size() == 1) && (targetCps.getRepresentingParts().get(0) instanceof Part)) {
            if ((part.getAbstractType().eContainer() instanceof BlockArchitecture) || !allowMultiplePart) {
              return targetCps.getRepresentingParts().get(0);
            }
          }
        }
      }
    }

    if (CtxPackage.Literals.SYSTEM_COMPONENT.isSuperTypeOf(targetType) || (part.getAbstractType().eContainer() instanceof BlockArchitecture)) {
      // Retrieve the existing architecture if any
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);

      BlockArchitecture target =
          (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
      Component cps = BlockArchitectureExt.getOrCreateSystem(target);
      Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, cps);
      if (!parts.isEmpty()) {
        return parts.iterator().next();
      }
    }

    return super.transformDirectElement(element, context);
  }
}
