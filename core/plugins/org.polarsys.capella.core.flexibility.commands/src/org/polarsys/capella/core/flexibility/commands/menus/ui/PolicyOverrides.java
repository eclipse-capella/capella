/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.menus.ui;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.MenuManager;

import org.polarsys.capella.core.flexibility.commands.menus.policy.IActionPolicy;
import org.polarsys.capella.core.flexibility.commands.menus.policy.PolicyProvider;

/**
 */
public class PolicyOverrides implements IContributionManagerOverrides {

  /**
   * {@inheritDoc}
   */
  public Boolean getVisible(IContributionItem item) {
    if (item.isEnabled()) {
      if (item instanceof MenuManager) {
        return null;
      }
      IActionPolicy policy = PolicyProvider.INSTANCE.getPolicy();
      return Boolean.valueOf(policy.cover(item.getId()));
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getEnabled(IContributionItem item) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Integer getAccelerator(IContributionItem item) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public String getAcceleratorText(IContributionItem item) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public String getText(IContributionItem item) {
    return null;
  }

}
