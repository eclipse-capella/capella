/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.ui.menu.dynamic.DynamicCreateChildAction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.menu.dynamic.DynamicActionProvider;
import org.polarsys.capella.core.menu.dynamic.DynamicCreationAction;

/**
 * A wrapper to the ContributionItem used to create elements on Capella
 * Specific case: we use the treeViewer to retrieve selection
 */
public class DynamicCreateContributionItem extends CompoundContributionItem {

  private TreeViewer treeViewer;
  
  DynamicProvider provider;
  
  public DynamicCreateContributionItem(TreeViewer t) {
    super();
    this.treeViewer = t;
    provider = new DynamicProvider();
  }

  class DynamicProvider extends DynamicActionProvider {

    protected void initActions(Shell shell, IWorkbenchPage page, ISelectionProvider selectionProvider) {
      dynamicAction = new DynamicCreationAction(shell, selectionProvider) {
        
        @Override
        protected Collection<CommandParameter> getFilteredNewChildDescriptors(EditingDomain editingDomain,
            EObject modelElement) {
          return super.getFilteredNewChildDescriptors(editingDomain, modelElement).stream().filter(c -> isValid(c.getEReference()) ).collect(Collectors.toList());
        }
        
        protected boolean isValid(EReference ref) {
          return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS.equals(ref)
              || ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS.equals(ref)
              || ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS.equals(ref);
        }
        
        @Override
        protected DynamicCreateChildAction createChildAction(EditingDomain editingDomain, ISelection selection,
            CommandParameter descriptor) {
          
          return new CapellaCreateChildAction(editingDomain, selection, descriptor) {
            @Override
            public void run() {
              super.run();
              treeViewer.refresh(true);
            }
          };
        }
      };
    }
    
    IContributionItem getItem() {
      initActions(null, null, treeViewer);
      IContributionItem item = createContributionItem();
      return item;
    }
  }

  @Override
  protected IContributionItem[] getContributionItems() {
    IContributionItem item = provider.getItem();
    return new IContributionItem[] { item };
  }

}
