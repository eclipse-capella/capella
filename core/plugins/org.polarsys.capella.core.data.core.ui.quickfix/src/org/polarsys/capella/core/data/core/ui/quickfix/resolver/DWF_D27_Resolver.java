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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.core.ui.quickfix.messages.CoreQuickFixMessages;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistency;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistencyDetector;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.Messages;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.InterModelErrorNavigatorDialog;

/**
 * Show details for package dependency cycles.
 */
public class DWF_D27_Resolver extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker_p) {
		List<EObject> tgts = getModelElements(marker_p);
		SystemEngineering se = (SystemEngineering) tgts.get(0);
		List<InterModelInconsistency> inconsistencies = new InterModelInconsistencyDetector().getInterModelInconsistencies(se);

		// show the dialog
		if (inconsistencies.size() > 0) {
			InterModelErrorNavigatorDialog dialog = new InterModelErrorNavigatorDialog(inconsistencies.get(0).getInvolvedObjects(), "Inter-model inconsistency details", "Capella Element(s) involved in detected cycle(s)Capella Element(s) involved in detected inconsistency(ies)", "Select inconsistency:"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			dialog.setCycles(inconsistencies);
			dialog.setContextMenuManagerFiller(new AbstractContextMenuFiller() {
				/**
				 * {@inheritDoc}
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void fillMenuManager(IMenuManager contextMenuManager_p, final ISelection selection_p) {

					final EObject selectedEObject = (EObject) ((TreeSelection) selection_p).iterator().next();

					final LocateInCapellaExplorerAction selectInExplorerDelegate = new LocateInCapellaExplorerAction() {
						@Override
						protected ISelection getSelection() {
							return selection_p;
						}
					};
					// Ignore workbench part site, since in a dialog, site has no meaning.
					selectInExplorerDelegate.shouldIgnoreWorkbenchPartSite(true);

					IAction selectInExplorerAction = new Action() {
						@Override
						public void run() {
							selectInExplorerDelegate.run(this);
						}
					};
					selectInExplorerAction.setText(Messages.ImpactAnalysisAction_ShowInCapellaExplorer_Title);
					selectInExplorerAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));

					selectInExplorerDelegate.selectionChanged(selectInExplorerAction, selection_p);
					if (selectInExplorerAction.isEnabled()) {
						contextMenuManager_p.add(selectInExplorerAction);
					}

					final LocateInCapellaExplorerAction selectInSemanticBrowserDelegate = new LocateInCapellaExplorerAction() {
						@Override
						protected ISelection getSelection() {
							return selection_p;
						}
					};
					// Ignore workbench part site, since in a dialog, site has no meaning.
					selectInSemanticBrowserDelegate.shouldIgnoreWorkbenchPartSite(true);

					// open semantic browser and then select in explorer
					IAction selectInSemanticBrowserAction = new Action() {
						@Override
						public void run() {
							try {
								activateSemanticBrowser();
							} catch (CoreException e) {
								// Do nothing
							}
							selectInSemanticBrowserDelegate.run(this);
						}

						private void activateSemanticBrowser() throws CoreException {
							IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							SemanticBrowserView smView = (SemanticBrowserView) activePage.findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
							if (null == smView) {
								// Show it if not found.
								smView = (SemanticBrowserView) activePage.showView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
							}
							activePage.activate(smView);
							smView.setInput(selectedEObject);
						}
					};

					selectInSemanticBrowserAction.setText(CoreQuickFixMessages.selectInSemanticBrowser);
					selectInSemanticBrowserAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
					selectInSemanticBrowserDelegate.selectionChanged(selectInSemanticBrowserAction, selection_p);
					if (selectInSemanticBrowserAction.isEnabled()) {
						contextMenuManager_p.add(selectInSemanticBrowserAction);
					}
				}
			});

			dialog.open();
		}
		return;
	}

	@Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}

}
