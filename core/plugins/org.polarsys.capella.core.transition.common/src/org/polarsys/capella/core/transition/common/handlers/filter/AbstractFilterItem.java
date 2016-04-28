/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMergeable(IDifference difference, Role role, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isDisplayable(IDifference difference, Role role, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {
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
  public String getDescription(IDifference difference) {
    return getClass().getSimpleName();
  }

  /**
   * {@inheritDoc}
   */
  public boolean isApplicable(EClass differenceClass) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMergeable(EStructuralFeature feature, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isReadOnly(IDifference diff, Role role, IContext context) {
    return false;
  }

}
