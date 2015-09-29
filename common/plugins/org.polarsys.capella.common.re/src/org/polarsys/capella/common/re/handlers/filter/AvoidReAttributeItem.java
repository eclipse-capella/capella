/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AvoidReAttributeItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference, Role role, IContext context) {
    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      if (diff instanceof IAttributeValuePresence) {
        if (RePackage.Literals.CATALOG_ELEMENT__KIND.equals(((IAttributeValuePresence) diff).getFeature())) {
          return false;
        }
        if (RePackage.Literals.RE_NAMED_ELEMENT__NAME.equals(((IAttributeValuePresence) diff).getFeature())) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {
    return super.getDestinationRole(difference, role, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisplayable(IDifference difference, Role role, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isApplicable(EClass differenceClass) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(EStructuralFeature feature, IContext context) {
    return true;
  }

}
