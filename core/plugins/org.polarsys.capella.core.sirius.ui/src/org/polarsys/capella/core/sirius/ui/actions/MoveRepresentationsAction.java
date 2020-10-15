/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES. All rights reserved. 
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
package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * The action allowing to move representations to a different {@link DAnalysis}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class MoveRepresentationsAction extends BaseSelectionListenerAction {

    /**
     * Constructs the action allowing to open representations.
     */
    public MoveRepresentationsAction() {
        super("Move"); //$NON-NLS-1$
    }

    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
        List<DRepresentationDescriptor> validRepresentationDescriptors = getValidRepresentationDescriptors(selection);
        if (!validRepresentationDescriptors.isEmpty()) {
            Session session = SessionManager.INSTANCE.getSession(validRepresentationDescriptors.get(0));
            // The move action should be available only if there is more than one Session resource (aird).
            return session.getAllSessionResources().size() > 1;
        }
        return false;
    }

    /**
     * The action is enabled if at least one valid representation
     */
    private List<DRepresentationDescriptor> getValidRepresentationDescriptors(IStructuredSelection selection) {
        return RepresentationHelper.getSelectedDescriptors(selection.toList()).stream().filter(RepresentationHelper::isValid).collect(Collectors.toList());
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Collection<DRepresentationDescriptor> repDescriptors = getValidRepresentationDescriptors(getStructuredSelection());

        // Precondition
        if (repDescriptors.isEmpty()) {
            return;
        }
        DRepresentationDescriptor next = repDescriptors.iterator().next();
        Session session = SessionManager.INSTANCE.getSession(next.getTarget());
        DAnalysisSelector dAnalysisSelector = DAnalysisSelectorService.getSelector((DAnalysisSession) session);
        for (DRepresentationDescriptor dRepresentationDescriptor : repDescriptors) {
            Option<EObject> dAnalysisOption = new EObjectQuery(dRepresentationDescriptor).getFirstAncestorOfType(ViewpointPackage.eINSTANCE.getDAnalysis());
            Collection<DAnalysis> analysesCandidates = ((DAnalysisSession) session).allAnalyses();
            analysesCandidates.remove(dAnalysisOption.get());
            DAnalysis selectedDAnalysis = dAnalysisSelector.selectSmartlyAnalysisForAddedRepresentation(dRepresentationDescriptor.getRepresentation(), analysesCandidates);
            if (selectedDAnalysis != dAnalysisOption.get()) {
                session.getTransactionalEditingDomain().getCommandStack()
                        .execute(new MoveRepresentationCommand(session, selectedDAnalysis, Collections.<DRepresentationDescriptor> singleton(dRepresentationDescriptor)));
            }
        }
    }
}
