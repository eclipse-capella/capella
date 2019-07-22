/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.internal.session;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SavingPolicyImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Custom saving policy base on {@link ResourceSetSync} status.
 */
public class CapellaSavingPolicy extends SavingPolicyImpl {

    CapellaSavingPolicy(TransactionalEditingDomain domain_p) {
        super(domain_p);
    }

    @Override
    public Collection<Resource> save(final Iterable<Resource> allResources, final Map<?, ?> options, IProgressMonitor monitor) {

        new CapellaSavingPolicyHelper().checkUnsaveableFiles(allResources);

        return super.save(allResources, options, monitor);
    }

    /**
     * Overridden to avoid changes detection based on temporary file creation.<br>
     * {@inheritDoc}
     */
    @Override
    protected boolean hasDifferentSerialization(Resource resource, Map<?, ?> options) {
        boolean result = false;
        if (!ResourceSetSync.isReadOnly(resource)) {
            ResourceStatus resourceStatus = ResourceSetSync.getStatus(resource);
            // If resource status different from SYNC, there are some changes to save.
            if (ResourceStatus.SYNC != resourceStatus) {
                result = true;
            }
        }
        return result;
    }

    protected static class CapellaSavingPolicyHelper {

        /**
         * Check that the given collection of resources does not con
         *
         * @param allResources
         */
        public void checkUnsaveableFiles(final Iterable<Resource> allResources) {
            Collection<IFile> files = retrieveUnsaveableFiles(allResources);
            if (!files.isEmpty()) {
                String msg = Messages.CapellaSavingPolicy_unwriteableFiles;
                for (IFile file : files) {
                    msg += file.toString() + ICommonConstants.EOL_CHARACTER;
                }
                throw new RuntimeException(msg);
            }
        }

        /**
         * Utility method in order to perform a pre checking on a Session about the saveable state of its embedded
         * resource.
         *
         * @param session
         *            the target Session
         * @param unsaveableFiles
         *            return the list of unsaveable resources, otherwise an empty one.
         * @return <code>true</code> if session can be saved, <code>false</code> otherwise.
         */
        public Collection<IFile> retrieveUnsaveableFiles(Iterable<Resource> allResources) {
            Collection<IFile> files = new ArrayList<IFile>();

            // Let's check it
            for (Resource resource : allResources) {
                if (null != resource) {
                    // check whether we have the write permission to the folder containing the resource
                    IFile file = EcoreUtil2.getFile(resource);
                    if (null != file) {
                        IPath path = file.getLocation();
                        IPath parentFolderPath = path.removeLastSegments(1);
                        File dir = parentFolderPath.toFile();

                        // Read-only mode is already checked in
                        // org.polarsys.capella.core.model.handler.pre.commit.listener.FileModificationPreCommitListener
                        if ((dir.exists() && !dir.canWrite())) {
                            files.add(file);
                        }
                    }
                }
            }
            return files;
        }
    }
}
