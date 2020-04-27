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
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides differences of Packages without children
 */
public class EmptyPackageCategoryFilter extends CategoryFilter {

  public EmptyPackageCategoryFilter(IContext context) {
    super(context, Messages.EmptyPackageCategoryFilter, Messages.EmptyPackageCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementPresence) {
      IElementPresence presence = (IElementPresence) difference;
      EObject element = presence.getElement();

      EObject target = presence.getElementMatch().get(Role.REFERENCE);
      if (target != null) {
        if (isEmptyPackage(element, context)) {
          if (!isSourceTransition(element, context)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * @param element
   * @param context
   */
  private boolean isSourceTransition(EObject element, IContext context) {
    Collection<EObject> sources = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    ITraceabilityHandler handler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    Collection<EObject> elements = handler.retrieveSourceElements(element, context);
    elements.retainAll(sources);
    if (!elements.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * @param element
   * @param context
   */
  private boolean isEmptyPackage(EObject element, IContext context) {
    if (element == null) {
      return false;
    }
    if (element instanceof InterfacePkg) {
      InterfacePkg pkg = (InterfacePkg) element;
      if (!pkg.getOwnedConstraints().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedExchangeItems().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedInterfacePkgs().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedInterfaces().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValueGroups().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValues().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValuePkgs().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedEnumerationPropertyTypes().isEmpty()) {
        return false;
      }
      return true;
    }

    if (element instanceof DataPkg) {
      DataPkg pkg = (DataPkg) element;
      if (!pkg.getOwnedConstraints().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedExchangeItems().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedAssociations().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedClasses().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedCollections().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedDataPkgs().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedDataTypes().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedDataValues().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedExceptions().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedExchangeItems().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedKeyParts().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedMessageReferences().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedMessages().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValueGroups().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValuePkgs().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedPropertyValues().isEmpty()) {
        return false;
      }
      if (!pkg.getOwnedEnumerationPropertyTypes().isEmpty()) {
        return false;
      }
      return true;
    }

    return false;
  }
}
