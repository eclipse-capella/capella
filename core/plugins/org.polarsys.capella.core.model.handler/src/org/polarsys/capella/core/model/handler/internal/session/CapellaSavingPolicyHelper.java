/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Utility class to provide services to saving policies.
 */
public class CapellaSavingPolicyHelper {

    /**
     * PRivate constructor to prevent instantiation.
     */
    private CapellaSavingPolicyHelper() {
    }

    /**
     * Check that the given collection of resources does not con
     *
     * @param allResources
     */
    public static void checkUnsaveableFiles(final Iterable<Resource> allResources) {
        Collection<IFile> files = retrieveUnsaveableFiles(allResources);
        if (!files.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            msg.append(Messages.CapellaSavingPolicy_unwriteableFiles);
            for (IFile file : files) {
                msg.append(file.toString() + ICommonConstants.EOL_CHARACTER);
            }
            throw new RuntimeException(msg.toString());
        }
    }

    /**
     * Utility method in order to perform a pre checking on a Session about the saveable state of its embedded resource.
     *
     * @param session
     *            the target Session
     * @param unsaveableFiles
     *            return the list of unsaveable resources, otherwise an empty one.
     * @return <code>true</code> if session can be saved, <code>false</code> otherwise.
     */
    public static Collection<IFile> retrieveUnsaveableFiles(Iterable<Resource> allResources) {
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
