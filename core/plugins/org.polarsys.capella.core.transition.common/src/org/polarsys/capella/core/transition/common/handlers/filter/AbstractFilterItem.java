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
package org.polarsys.capella.core.transition.common.handlers.filter;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractFilterItem implements IFilterItem {

  public enum FilterAction {
    TARGET, REFERENCE, NO_ACTION
    //A NoAction have more priority than others
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isDisplayable(IDifference difference_p, Role role_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public String getIdentifier() {
    return getClass().getSimpleName();
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription(IDifference difference_p) {
    return getClass().getSimpleName();
  }

  /**
   * {@inheritDoc}
   */
  public boolean isApplicable(EClass differenceClass_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMergeable(EStructuralFeature feature_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isReadOnly(IDifference diff_p, Role role_p, IContext context_p) {
    return false;
  }

}
