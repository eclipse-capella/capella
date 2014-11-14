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
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TargetDifferencesFilterItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReadOnly(IDifference diff_p, Role role_p, IContext context_p) {
    return role_p == Role.TARGET;
  }

  /**
   * @param source_p
   * @param context_p
   * @return
   */
  protected BlockArchitecture getTargetArchitecture(EObject source_p, IContext context_p) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }

    EClass clazz = TransformationHandlerHelper.getInstance(context_p).getTargetType(architecture, context_p);
    SystemEngineering engeneering = getSystemEngineering(source_p);
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
   * @param currentElement_p
   * @return
   */
  public static SystemEngineering getSystemEngineering(EObject currentElement_p) {
    EObject element = currentElement_p;
    while ((element != null) && !(element instanceof SystemEngineering)) {
      element = element.eContainer();
    }
    if (element == null) {
      return null;
    }
    return (SystemEngineering) element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {

    if (role_p == Role.TARGET) {
      if (difference_p instanceof IElementRelativeDifference) {
        IElementRelativeDifference diff = (IElementRelativeDifference) difference_p;
        EObject target = diff.getElementMatch().get(Role.TARGET);
        EObject blockArch = getTargetArchitecture(target, context_p);
        EObject arch = BlockArchitectureExt.getRootBlockArchitecture(target);

        if (!blockArch.eClass().isInstance(arch)) {
          //In transformed scope, we don't transform all elements from source architecture to source architecture,
          //so in the target scope, the source architecture can have some (many?) additional elements
          return false;
        }

      }

      if (difference_p instanceof IElementPresence) {
        return false;
      }
    }

    return true;
  }

}
