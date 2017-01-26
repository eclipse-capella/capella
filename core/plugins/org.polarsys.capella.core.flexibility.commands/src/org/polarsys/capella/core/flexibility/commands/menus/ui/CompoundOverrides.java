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

  public void add(IContributionManagerOverrides override) {
    overrides.add(override);
  }

  public void remove(IContributionManagerOverrides override) {
    overrides.remove(override);
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getEnabled(IContributionItem item) {
    Boolean result = null;
    for (IContributionManagerOverrides override : overrides) {
      Boolean childResult = override.getEnabled(item);
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
  public Integer getAccelerator(IContributionItem item) {
    Integer result = null;
    for (IContributionManagerOverrides override : overrides) {
      Integer childResult = override.getAccelerator(item);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String getAcceleratorText(IContributionItem item) {
    String result = null;
    for (IContributionManagerOverrides override : overrides) {
      String childResult = override.getAcceleratorText(item);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String getText(IContributionItem item) {
    String result = null;
    for (IContributionManagerOverrides override : overrides) {
      String childResult = override.getText(item);
      if (childResult != null) {
        return childResult;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getVisible(IContributionItem item) {
    Boolean result = null;
    for (IContributionManagerOverrides override : overrides) {
      Boolean childResult = override.getVisible(item);
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
