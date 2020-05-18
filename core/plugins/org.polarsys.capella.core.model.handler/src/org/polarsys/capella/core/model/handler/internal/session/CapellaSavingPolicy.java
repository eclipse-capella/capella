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
package org.polarsys.capella.core.model.handler.internal.session;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SavingPolicyImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

/**
 * Custom saving policy base on {@link ResourceSetSync} status.
 */
public class CapellaSavingPolicy extends SavingPolicyImpl {

    CapellaSavingPolicy(TransactionalEditingDomain domain_p) {
        super(domain_p);
    }

    @Override
    public Collection<Resource> save(final Iterable<Resource> allResources, final Map<?, ?> options, IProgressMonitor monitor) {

        CapellaSavingPolicyHelper.checkUnsaveableFiles(allResources);

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

}
