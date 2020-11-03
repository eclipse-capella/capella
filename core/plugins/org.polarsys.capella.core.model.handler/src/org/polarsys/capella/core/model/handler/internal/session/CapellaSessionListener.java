/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.internal.session;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;

/**
 * Used to install a saving policy.
 */
public class CapellaSessionListener extends SessionManagerListener.Stub {
    /**
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session,
     *      int)
     */
    @Override
    public void notify(Session updatedSession_p, int notification_p) {
        switch (notification_p) {
        case SessionListener.OPENING:

            if (Boolean.valueOf(System.getProperty("org.polarsys.capella.core.session.saving.policy.old", "true"))) { // $NON-NLS-1$ //$NON-NLS-2$
                // Set a custom implementation of the saving policy to avoid temporary file creation to detect real
                // changes.
                updatedSession_p.setSavingPolicy(new CapellaSavingPolicy(updatedSession_p.getTransactionalEditingDomain()));

                if (updatedSession_p instanceof DAnalysisSessionImpl) {
                    // we don't want to defer save of the session
                    ((DAnalysisSessionImpl) updatedSession_p).setDeferSaveToPostCommit(false);
                    ((DAnalysisSessionImpl) updatedSession_p).setSaveInExclusiveTransaction(false);
                }
            } else {
                // Set a custom implementation of the saving policy to avoid temporary file creation to detect real
                // changes.
                updatedSession_p.setSavingPolicy(new CapellaIsModifiedSavingPolicy(updatedSession_p.getTransactionalEditingDomain()));

                if (updatedSession_p instanceof DAnalysisSessionImpl) {
                    // we to defer save of the session as done in Sirius with the isModifiedSavingPolicy
                    ((DAnalysisSessionImpl) updatedSession_p).setDeferSaveToPostCommit(true);
                    ((DAnalysisSessionImpl) updatedSession_p).setSaveInExclusiveTransaction(true);
                }
            }
            break;
        }
    }
}
