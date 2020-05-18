/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.platform.sirius.customisation.uicallback;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A new callBack to do the same thing that the original callBack except for the
 * notification of the migration (see {@link #notifyUserForMigration()}.
 */
public class SiriusUiCallBack implements UICallBack {

    UICallBack defaultCallBack;

    /**
     * Default constructor.
     * 
     * @param defaultCallBack
     *            The callBack with the default behavior.
     */
    public SiriusUiCallBack(UICallBack defaultCallBack) {
        this.defaultCallBack = defaultCallBack;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForDetailName(java.lang.String)
     */
    public String askForDetailName(String defaultName) throws InterruptedException {
        return defaultCallBack.askForDetailName(defaultName);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForDetailName(java.lang.String,
     *      java.lang.String)
     * @deprecated
     */
    @Deprecated
    public String askForDetailName(String defaultName, String representationDescription) throws InterruptedException {
        return defaultCallBack.askForDetailName(defaultName, representationDescription);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForDetailName(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public String askForDetailName(String defaultName, String representationDescriptionName, String representationDescriptionDoc) throws InterruptedException {
        return defaultCallBack.askForDetailName(defaultName, representationDescriptionName, representationDescriptionDoc);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForEObject(java.lang.String,
     *      org.eclipse.sirius.common.tools.api.util.TreeItemWrapper,
     *      org.eclipse.emf.common.notify.AdapterFactory)
     */
    public EObject askForEObject(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        return defaultCallBack.askForEObject(message, input, factory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForEObjects(java.lang.String,
     *      org.eclipse.sirius.common.tools.api.util.TreeItemWrapper,
     *      org.eclipse.emf.common.notify.AdapterFactory)
     */
    public Collection<EObject> askForEObjects(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        return defaultCallBack.askForEObjects(message, input, factory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForVariableValues(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable)
     */
    public Collection<EObject> askForVariableValues(EObject model, SelectModelElementVariable variable) throws InterruptedException {
        return defaultCallBack.askForVariableValues(model, variable);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForTypedVariable(java.util.List, java.util.List)
     */
    public List<String> askForTypedVariable(List<TypedVariable> typedVariableList, List<String> defaultValues) throws InterruptedException {
        return defaultCallBack.askForTypedVariable(typedVariableList, defaultValues);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askSessionReopeningWithResourceVersionMismatch(org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException)
     */
    public boolean askSessionReopeningWithResourceVersionMismatch(AirdResourceVersionMismatchException exception) {
      return defaultCallBack.askSessionReopeningWithResourceVersionMismatch(exception);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#loadResource(org.eclipse.emf.edit.domain.EditingDomain,
     *      org.eclipse.core.resources.IFile)
     */
    public Resource loadResource(EditingDomain domain, IFile file) {
        return defaultCallBack.loadResource(domain, file);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#openEObjectsDialogMessage(java.util.Collection,
     *      java.lang.String, java.lang.String)
     */
    public boolean openEObjectsDialogMessage(Collection<EObject> eObjects, String title, String message) {
        return defaultCallBack.openEObjectsDialogMessage(eObjects, title, message);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#openRepresentation(org.eclipse.sirius.business.api.session.Session,
     *      org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public void openRepresentation(Session openedSession, DRepresentation representation) {
        defaultCallBack.openRepresentation(openedSession, representation);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldClose(Session,
     *      Resource)
     */
    public boolean shouldClose(Session session, Resource resource) {
        return defaultCallBack.shouldClose(session, resource);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldReload(Resource)
     */
    public boolean shouldReload(Resource resource) {
        return defaultCallBack.shouldReload(resource);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldRemove(Resource)
     */
    public boolean shouldRemove(Resource resource) {
        return defaultCallBack.shouldRemove(resource);
    }

    /**
     * Return a specific name for Sirius (use the previous name (before
     * Sirius 6.1).
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#getSessionDisplayed(org.eclipse.sirius.business.api.session.Session)
     */
    public String getSessionNameToDisplayWhileSaving(Session session) {
        String name = "Session";
        if (session != null) {
            if (session instanceof DAnalysisSessionEObject) {
                name = computeRootDAnalysisName((DAnalysisSessionEObject) session);
            } else {
                name = session.toString();
            }
        }
        return name;
    }

    /**
     * Computes a name out of the given session's roots (using the
     * {@link DAnalysisSessionEObject#getAnalyses()} method).
     * 
     * @param session
     *            the session from which compute a name
     * @return the given session's name
     */
    // SEE VP-1729 : Close diagram and 'Save Resource' dialog window
    private String computeRootDAnalysisName(DAnalysisSessionEObject sessionObj) {
        final StringBuilder builder = new StringBuilder("Local Session: ");
        // We only consider the root DAnalysises of the given session
        for (final DAnalysis analysis : sessionObj.getAnalyses()) {
            final Resource resource = analysis.eResource();
            if (resource != null && resource.getURI() != null) {
                final URI uri = resource.getURI();
                if (uri.segments().length > 0) {
                    builder.append(URI.decode(uri.lastSegment())).append("  ");
                } else {
                    builder.append(uri.opaquePart()).append(" ");
                }
            }
        }
        // Remove the last two spaces if needed
        if ("  ".equals(builder.substring(builder.length() - 2, builder.length()))) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }

    private boolean inUIThread() {
        return Display.getCurrent() != null;
    }

    private Shell getActiveShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#openError(java.lang.String,
     *      java.lang.String)
     */
    public void openError(String title, String message) {
        if (inUIThread()) {
            MessageDialog.openError(getActiveShell(), title, message);
        }
    }
    
    @Override
    public void askUserAndSaveMigratedSession(Session session) {
        defaultCallBack.askUserAndSaveMigratedSession(session);
    }

}
