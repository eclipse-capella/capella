/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;

/**
 * Dynamic contribution item for {@link IAction}.
 */
public class DynamicActionContributionItem extends ActionContributionItem {
  /**
   * Constructor.
   * @param action
   */
  public DynamicActionContributionItem(IAction action) {
    super(action);
  }

  /**
   * @see org.eclipse.jface.action.ContributionItem#isDirty()
   */
  @Override
  public boolean isDirty() {
    return true;
  }

  /**
   * @see org.eclipse.jface.action.ActionContributionItem#isDynamic()
   */
  @Override
  public boolean isDynamic() {
    return true;
  }

  /**
   * Return the text label of the underlying action.
   * @return
   */
  public String getText() {
    return getAction().getText();
  }
}
