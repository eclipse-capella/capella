/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.internal.session.SaveSessionRunnable;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=521989
 */
public class CapellaSaveSessionRunnable extends SaveSessionRunnable {

    public CapellaSaveSessionRunnable(Session session) {
        super(session);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
        // Save the Session when there is no Sirius DialectEditor opened on the
        // Session, or when the active editor is a Sirius DialectEditor.
        super.run(monitor);

        
        // We also need to save the Session when the active editor is not a
        // DialectEditor, such as the Project Explorer or the Activity
        // Explorer Editor.
        if (super.session != null) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(super.session);
            Collection<DialectEditor> editors = uiSession.getEditors();
            if (!editors.isEmpty()) {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IEditorPart activeEditor = activeWorkbenchWindow.getActivePage().getActiveEditor();
                    if (!editors.contains(activeEditor)) {
                        // Save the Session in any case.
                        super.session.save(monitor);
                    }
                }
            }
        }
        
        return Status.OK_STATUS;
    }
}
