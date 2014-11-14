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
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EmptyPackageFilterItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "Empty package is not propagated automatically";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {

    if (difference_p instanceof IElementPresence) {
      if (role_p == Role.REFERENCE) {
        IElementPresence presence = (IElementPresence) difference_p;
        EObject element = presence.getElement();

        if (isEmptyPackage(element, context_p)) {
          if (!isSourceTransition(element, context_p)) {
            return FilterAction.NO_ACTION;
          }
        }
      }
    }

    return super.getDestinationRole(difference_p, role_p, context_p);
  }

  /**
   * @param element_p
   * @return
   */
  private boolean isSourceTransition(EObject element_p, IContext context_p) {
    Collection<EObject> sources = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    Collection<EObject> elements = handler.retrieveSourceElements(element_p, context_p);
    elements.retainAll(sources);
    if (!elements.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * @param element_p
   * @return
   */
  private boolean isEmptyPackage(EObject element_p, IContext context_p) {
    if (element_p == null) {
      return false;
    }
    if (element_p instanceof InterfacePkg) {
      InterfacePkg pkg = (InterfacePkg) element_p;
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

    if (element_p instanceof DataPkg) {
      DataPkg pkg = (DataPkg) element_p;
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
