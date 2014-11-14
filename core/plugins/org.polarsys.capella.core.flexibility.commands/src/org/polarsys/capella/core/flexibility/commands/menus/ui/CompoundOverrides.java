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
package org.polarsys.capella.core.flexibility.commands.menus.ui;

import java.util.ArrayList;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;

/**
 *
 */
public class CompoundOverrides implements IContributionManagerOverrides {
  ArrayList<IContributionManagerOverrides> overrides;

  public CompoundOverrides() {
    overrides = new ArrayList<IContributionManagerOverrides>();
  }

  public void add(IContributionManagerOverrides override_p) {
    overrides.add(override_p);
  }

  public void remove(IContributionManagerOverrides override_p) {
    overrides.remove(override_p);
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getEnabled(IContributionItem item_p) {
    Boolean result = null;
    for (IContributionManagerOverrides override : overrides) {
      Boolean childResult = override.getEnabled(item_p);
      if (childResult != null) {
        if (childResult == Boolean.FALSE) {
          return Boolean.FALSE;
        }
        result = childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Integer getAccelerator(IContributionItem item_p) {
    Integer result = null;
    for (IContributionManagerOverrides override : overrides) {
      Integer childResult = override.getAccelerator(item_p);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String getAcceleratorText(IContributionItem item_p) {
    String result = null;
    for (IContributionManagerOverrides override : overrides) {
      String childResult = override.getAcceleratorText(item_p);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String getText(IContributionItem item_p) {
    String result = null;
    for (IContributionManagerOverrides override : overrides) {
      String childResult = override.getText(item_p);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getVisible(IContributionItem item_p) {
    Boolean result = null;
    for (IContributionManagerOverrides override : overrides) {
      Boolean childResult = override.getVisible(item_p);
      if (childResult != null) {
        if (childResult == Boolean.FALSE) {
          return Boolean.FALSE;
        }
        result = childResult;
      }
    }
    return result;
  }

}
