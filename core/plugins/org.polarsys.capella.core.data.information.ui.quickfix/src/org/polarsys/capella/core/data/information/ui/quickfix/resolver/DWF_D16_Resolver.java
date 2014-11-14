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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.ui.quickfix.InformationQuickFixActivator;
import org.polarsys.capella.core.data.information.ui.quickfix.messages.InformationQuickFixMessages;
import org.polarsys.capella.core.data.information.validation.class_.MDCHK_DWF_D16;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.Messages;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.EObjectNavigatorDialog;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class DWF_D16_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {

    DataPkg pkg = (DataPkg) getModelElements(marker_p).get(0);

    MDCHK_DWF_D16 validator = new MDCHK_DWF_D16();
    Couple<IDirectedGraph<EObject>, List<List<EObject>>> result = validator.findSCC(pkg);

    final IDirectedGraph<EObject> graph = result.getKey();
    List<List<EObject>> sccs = result.getValue();

    if ((sccs == null) || sccs.isEmpty()) {
      // user may have fixed the cycles in the meantime...
      return;
    }

    EObjectNavigatorDialog dialog =
        new EObjectNavigatorDialog(sccs.iterator().next(), InformationQuickFixMessages.cycle_details_dialog_title,
            InformationQuickFixMessages.cycle_details_dialog_message, InformationQuickFixMessages.cycle_details_dialog_combo_lbl,
            InformationQuickFixMessages.cycle_details_dialog_combo_cycle_prefix);

    dialog.setCycles(sccs);

    dialog.setContextMenuManagerFiller(new AbstractContextMenuFiller() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void fillMenuManager(IMenuManager contextMenuManager_p, final ISelection selection_p) {
        final LocateInCapellaExplorerAction selectInExplorerAction = new LocateInCapellaExplorerAction() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected ISelection getSelection() {
            return selection_p;
          }
        };

        IAction action = new Action() {
          /**
           * {@inheritDoc}
           */
          @Override
          public void run() {
            selectInExplorerAction.run(this);
          }

        };

        // Ignore workbench part site, since in a dialog, site has no meaning.
        selectInExplorerAction.shouldIgnoreWorkbenchPartSite(true);
        action.setText(Messages.ImpactAnalysisAction_ShowInCapellaExplorer_Title);
        action.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
        selectInExplorerAction.selectionChanged(action, selection_p);
        if (action.isEnabled()) {
          contextMenuManager_p.add(action);
        }

        final EObject eObject = (EObject) ((TreeSelection) selection_p).iterator().next();

        final LocateInCapellaExplorerAction selectInSemanticBrowserAction = new LocateInCapellaExplorerAction() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected ISelection getSelection() {
            return selection_p;
          }
        };

        IAction action3 = new Action() {
          /**
           * {@inheritDoc}
           */
          @Override
          public void run() {
            try {
              activateSemanticBrowser();
            } catch (CoreException e) {
              // Do nothing
            }
            selectInSemanticBrowserAction.run(this);
          }

          private void activateSemanticBrowser() throws CoreException {
            IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            SemanticBrowserView smView = (SemanticBrowserView) activePage.findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
            if (null == smView) {
              // Show it if not found.
              smView = (SemanticBrowserView) activePage.showView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
            }
            activePage.activate(smView);
            smView.setInput(eObject);
          }

        };

        // Ignore workbench part site, since in a dialog, site has no meaning.
        selectInSemanticBrowserAction.shouldIgnoreWorkbenchPartSite(true);
        action3.setText(InformationQuickFixMessages.selectInSemanticBrowser);
        action3.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
        selectInSemanticBrowserAction.selectionChanged(action3, selection_p);
        if (action3.isEnabled()) {
          contextMenuManager_p.add(action3);
        }

        //
        // dependencies (whether it exists)
        //

        if (InformationPackage.Literals.CLASS.isSuperTypeOf(eObject.eClass())) {

          Set<EObject> referencedSet = new HashSet<EObject>();
          for (Iterator<EObject> successors = graph.getSucessors(eObject); successors.hasNext();) {
            referencedSet.add(successors.next());
          }

          if (!referencedSet.isEmpty()) {
            for (final EObject referenced : referencedSet) {

              final LocateInCapellaExplorerAction goToAction = new LocateInCapellaExplorerAction() {
                /**
                 * {@inheritDoc}
                 */
                @Override
                protected ISelection getSelection() {
                  return selection_p;
                }

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run(IAction action_p) {
                  Object elementToSelectInCapellaExplorer = referenced;
                  // Keep the double check here, as getSemanticElement can return an element not from the model.
                  if ((elementToSelectInCapellaExplorer instanceof ModelElement)) {
                    selectElementInCapellaExplorer(new StructuredSelection(elementToSelectInCapellaExplorer));
                  }
                }
              };

              IAction action2 = new Action() {
                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                  goToAction.run(this);
                }

              };

              ItemProviderAdapter ipa = getItemProvider(referenced);

              // Ignore workbench part site, since in a dialog, site has no meaning.
              goToAction.shouldIgnoreWorkbenchPartSite(true);
              action2.setText(NLS.bind(InformationQuickFixMessages.goToReferencedElement, new Object[] { ipa.getText(referenced) }));
              action2.setImageDescriptor(InformationQuickFixActivator.getDefault().getImageDescriptor("goto_16.png") //$NON-NLS-1$
                  );
              selectInExplorerAction.selectionChanged(action2, selection_p);
              if (action2.isEnabled()) {
                contextMenuManager_p.add(action2);
              }
            }
          }
        } // END FILL MENU
      }

    });

    dialog.open();

    return;
  }
  
  @Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}

  /**
   * Get a generic item provider.
   * @return an {@link ItemProviderAdapter} if any.
   */
  private ItemProviderAdapter getItemProvider(EObject object_p) {
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) MDEAdapterFactory.getEditingDomain();
    IItemLabelProvider provider = (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(object_p, IItemLabelProvider.class);
    return (ItemProviderAdapter) provider;
  }

}
