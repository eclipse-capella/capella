/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.ui.business.api.session.analysis.SmartDialogAnalysisSelector;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

/**
 * Capella specific analysis selector.
 */
public class CapellaAnalysisSelector extends SmartDialogAnalysisSelector {

    public static final String DIALOG_SETTINGS = "org.polarsys.capella.core.sirius.ui.AnalysisSelector"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation_p, Collection<DAnalysis> allAnalysis_p) {
        // Precondition.
        if (!(representation_p instanceof DSemanticDecorator)) {
            return super.selectSmartlyAnalysisForAddedRepresentation(representation_p, allAnalysis_p);
        }

        EObject targetElement = ((DSemanticDecorator) representation_p).getTarget();
        Resource targetResource = targetElement.eResource();

        DAnalysis bestCandidate = null;
        List<DAnalysis> bestCandidates = new ArrayList<DAnalysis>();
        // If different analysis candidates are available, sort them smartly to ease the end-user selection.
        if (allAnalysis_p.size() > 1) {

            // Loop over analysis candidates.
            for (DAnalysis candidateAnalysis : allAnalysis_p) {
                for (EObject semanticElement : candidateAnalysis.getModels()) {
                    // Is semantic element contains in the same resource ?
                    if (targetResource.equals(semanticElement.eResource())) {
                        // Yes it is :)
                        bestCandidates.add(candidateAnalysis);
                        break;
                    }
                }
            }

            // Loop over analysis candidates to find the best candidate from best candidates
            for (DAnalysis candidateAnalysis : bestCandidates) {
                if (candidateAnalysis.getModels().size() > 0) {
                    EObject semanticElement = candidateAnalysis.getModels().get(0);
                    if (targetResource.equals(semanticElement.eResource())) {
                        // Yes it is :)
                        bestCandidate = candidateAnalysis;
                        break;
                    }
                }
            }

            return doSelectSmartlyAnalysisForAddedRepresentation(representation_p, allAnalysis_p, bestCandidates, bestCandidate);
        }
        return allAnalysis_p.iterator().next();
    }

    /**
     * Do select smartly analysis for added representation with an initial selection based on best candidate.
     * 
     * @param representation_p
     * @param allAnalysis_p
     * @param bestCandidate_p
     * @return
     */
    protected DAnalysis doSelectSmartlyAnalysisForAddedRepresentation(DRepresentation representation_p, final Collection<DAnalysis> allAnalysis_p, final List<DAnalysis> bestCandidates_p,
            final DAnalysis bestCandidate_p) {

        final ILabelProvider labelProvider = new MDEAdapterFactoryLabelProvider() {
            @Override
            public String getText(final Object object_p) {
                if (object_p instanceof DAnalysis) {
                    return ((DAnalysis) object_p).eResource().getURI().toString();
                }
                return super.getText(object_p);
            }

        };

        RunnableWithResult<Object> runnable = new RunnableWithResult.Impl<Object>() {

            @Override
            public void run() {
                FilteredItemsSelectionDialog dialog = new FilteredItemsSelectionDialog(Display.getDefault().getActiveShell()) {

                    @Override
                    protected Control createExtendedContentArea(Composite parent_p) {
                        return null;
                    }

                    @Override
                    protected void createButtonsForButtonBar(Composite parent) {
                        // create OK and Cancel button
                        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
                        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
                    }

                    @Override
                    protected IDialogSettings getDialogSettings() {
                        IDialogSettings settings = SiriusUIPlugin.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS);

                        if (settings == null) {
                            settings = SiriusUIPlugin.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
                        }

                        return settings;
                    }

                    @Override
                    protected IStatus validateItem(Object item_p) {
                        return Status.OK_STATUS;
                    }

                    @Override
                    protected ItemsFilter createFilter() {
                        return new ItemsFilter() {

                            @Override
                            public String getPattern() {
                                String pattern = super.getPattern();
                                // Allows empty pattern fills view
                                if ((pattern == null) || (pattern.length() == 0)) {
                                    return "*"; //$NON-NLS-1$
                                }
                                return pattern;
                            }

                            @Override
                            public boolean matchItem(Object item_p) {
                                // perform a match with displayed text
                                if (patternMatcher.matches(labelProvider.getText(item_p))) {
                                    return true;
                                }
                                // If doesn't match, perform a match with fileName
                                if (item_p instanceof DAnalysis) {
                                    DAnalysis analysis = (DAnalysis) item_p;
                                    Resource resource = analysis.eResource();
                                    if ((resource != null) && (resource.getURI() != null)) {
                                        URI fileUri = resource.getURI();
                                        if (fileUri.lastSegment() != null) {
                                            if (patternMatcher.matches(fileUri.lastSegment())) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                                return false;
                            }

                            @Override
                            public boolean isConsistentItem(Object item_p) {
                                return true;
                            }
                        };
                    }

                    @Override
                    protected Comparator<Object> getItemsComparator() {
                        return new Comparator<Object>() {

                            @Override
                            public int compare(Object o1_p, Object o2_p) {
                                if (o1_p.equals(bestCandidate_p)) {
                                    return -2;
                                }
                                if (o2_p.equals(bestCandidate_p)) {
                                    return +2;
                                }
                                return labelProvider.getText(o1_p).compareTo(labelProvider.getText(o2_p));
                            }

                        };
                    }

                    @Override
                    protected void fillContentProvider(AbstractContentProvider contentProvider_p, ItemsFilter itemsFilter_p, IProgressMonitor progressMonitor_p) throws CoreException {
                        for (Object element : allAnalysis_p) {
                            contentProvider_p.add(element, itemsFilter_p);
                        }
                    }

                    @Override
                    protected void storeDialog(IDialogSettings settings) {
                        super.storeDialog(settings);
                    }

                    @Override
                    protected void restoreDialog(IDialogSettings settings) {
                        setSelectionHistory(new SelectionHistory() {

                            @Override
                            protected Object restoreItemFromMemento(IMemento memento_p) {
                                return null;
                            }

                            @Override
                            protected void storeItemToMemento(Object item_p, IMemento memento_p) {
                                // we don't save any history here
                            }
                        });

                        super.restoreDialog(settings);

                        // but we load best candidates
                        for (Object object : bestCandidates_p) {
                            accessedHistoryItem(object);
                        }
                    }

                    @Override
                    public String getElementName(Object item_p) {
                        return labelProvider.getText(item_p);
                    }

                    @Override
                    protected void setShellStyle(int newShellStyle_p) {
                        super.setShellStyle(SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
                    }

                };

                dialog.setHelpAvailable(false);
                dialog.setDetailsLabelProvider(labelProvider);
                dialog.setListLabelProvider(labelProvider);
                dialog.setSeparatorLabel(Messages.CapellaAnalysisSelector_SelectionDialog_OthersFragments);
                dialog.setTitle(Messages.CapellaAnalysisSelector_SelectionDialog_Title);
                dialog.setMessage(Messages.CapellaAnalysisSelector_SelectionDialog_Message);

                if (bestCandidate_p != null) {
                    dialog.setInitialElementSelections(Collections.singletonList(bestCandidate_p));
                }

                if (dialog.open() == Window.OK) {
                    if (dialog.getFirstResult() != null) {
                        setResult(dialog.getFirstResult());
                    }
                } else {
                    // Box closed by cancel, ESC key ...
                    setResult(null);
                }
            }
        };
        /* synch execution as the user need to choose before we can get further */
        PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
        if (runnable.getResult() instanceof DAnalysis) {
            return (DAnalysis) runnable.getResult();
        } else {
            throw new OperationCanceledException(Messages.CapellaAnalysisSelector_Cancel);
        }
    }
}
