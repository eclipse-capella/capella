/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides differences located outside the Target Architecture
 */
public class OutsideArchitectureCategoryFilter extends CategoryFilter {

  public OutsideArchitectureCategoryFilter(IContext context) {
    super(context, Messages.OutsideArchitectureCategoryFilter, Messages.OutsideArchitectureCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(false);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      EObject reference = diff.getElementMatch().get(Role.REFERENCE);
      EObject blockArch = getTargetArchitecture(reference, context);
      EObject arch = BlockArchitectureExt.getRootBlockArchitecture(reference);

      if (reference != null) {

        // We don't hide differences from outside architectures (ownedExtensions for instance)
        if (arch == null) {
          return false;
        }
        if (!blockArch.eClass().isInstance(arch)) {
          // In transformed scope, we don't transform all elements from source architecture to source architecture,
          // so in the target scope, the source architecture can have some (many?) additional elements
          return true;
        }
      }

    }

    return false;
  }

  protected BlockArchitecture getTargetArchitecture(EObject source, IContext context) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject selected = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(selected);
    }

    EClass clazz = TransformationHandlerHelper.getInstance(context).getTargetType(architecture, context);
    SystemEngineering engeneering = getSystemEngineering(source);
    if (engeneering != null) {
      for (ModellingArchitecture archi : engeneering.getOwnedArchitectures()) {
        if ((clazz != null) && clazz.isInstance(archi)) {
          return (BlockArchitecture) archi;
        }
      }
    }
    return architecture;
  }

  /**
   * [TRANSITION] Transition shall manage Metamodel extension / eMDE
   * 
   * @param currentElement
   * @return
   */
  public static SystemEngineering getSystemEngineering(EObject currentElement) {
    EObject element = currentElement;
    while ((element != null) && !(element instanceof SystemEngineering)) {
      element = element.eContainer();
    }
    if (element == null) {
      return null;
    }
    return (SystemEngineering) element;
  }

}
