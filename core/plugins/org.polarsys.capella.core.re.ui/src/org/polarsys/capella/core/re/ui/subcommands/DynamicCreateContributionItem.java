/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.subcommands;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import org.polarsys.capella.core.menu.dynamic.DynamicActionProvider;

/**
 * A wrapper to the ContributionItem used to create elements on Capella
 * Specific case: we use the serviceLocator to retrieve selection made on the TreeViewer instead of global selection
 */
public class DynamicCreateContributionItem extends CompoundContributionItem implements IWorkbenchContribution {

  class DynamicProvider extends DynamicActionProvider {

    IContributionItem getItem() {
      initActions(null, null, new ISelectionProvider() {

        @Override
        public void setSelection(ISelection selection_p) {
          //Nothing here
        }

        @Override
        public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
          //Nothing here
        }

        @Override
        public ISelection getSelection() {
          ISelectionService selectionService = (ISelectionService) _locator.getService(ISelectionService.class);
          return selectionService.getSelection();
        }

        @Override
        public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
          //Nothing here
        }
      });

      IContributionItem item = createContributionItem();
      return item;
    }
  }

  IServiceLocator _locator = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(IServiceLocator serviceLocator_p) {
    _locator = serviceLocator_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    IContributionItem item = new DynamicProvider().getItem();
    return new IContributionItem[] { item };
  }

}
