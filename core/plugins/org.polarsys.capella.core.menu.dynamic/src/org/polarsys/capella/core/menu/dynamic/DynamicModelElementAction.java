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
package org.polarsys.capella.core.menu.dynamic;

import java.util.Collection;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.capella.common.menu.dynamic.AbstractModelElementAction;

/**
 * Base class to implement dynamic action.
 */
public abstract class DynamicModelElementAction extends AbstractModelElementAction {
  /**
   * Constructor.
   * @param shell_p
   * @param selectionProvider_p
   */
  public DynamicModelElementAction(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super(shell_p, selectionProvider_p);
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractNavigatorAction#getModelElementClass()
   */
  @Override
  protected Class<?> getModelElementClass() {
    return Element.class;
  }

  /**
   * Get structural dynamic actions.
   * @return a not <code>null</code> collection.
   */
  public abstract Collection<IContributionItem> getStructuralDynamicActions();

  /**
   * Get property value actions.
   * @return
   */
  public abstract Collection<IContributionItem> getPropertyValueDynamicActions();

  /**
   * Get extension actions.
   * @return
   */
  public abstract Collection<IContributionItem> getExtensionDynamicActions();

  /**
   * Get non structural dynamic actions.
   * @return
   */
  public abstract Collection<IContributionItem> getNonStructuralDynamicActions();
}
