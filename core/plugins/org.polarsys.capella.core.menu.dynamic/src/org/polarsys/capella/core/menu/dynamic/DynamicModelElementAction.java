/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.menu.dynamic;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.menu.dynamic.AbstractModelElementAction;

/**
 * Base class to implement dynamic action.
 */
public abstract class DynamicModelElementAction extends AbstractModelElementAction {
  /**
   * Constructor.
   * @param shell
   * @param selectionProvider
   */
  public DynamicModelElementAction(Shell shell, ISelectionProvider selectionProvider) {
    super(shell, selectionProvider);
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractNavigatorAction#getModelElementClass()
   */
  @Override
  protected Class<?> getModelElementClass() {
    return EObject.class;
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
