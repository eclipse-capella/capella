/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.internal.session;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.internal.session.IsModifiedSavingPolicy;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;

/**
 * Custom saving policy base on {@link ResourceSetSync} status.
 */
public class CapellaIsModifiedSavingPolicy extends IsModifiedSavingPolicy {

    CapellaIsModifiedSavingPolicy(TransactionalEditingDomain domain) {
        super(domain);
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
        // IsModifiedSavingPolicy has already check that ResourceSetSync.isReadOnly returns false and that the resource
        // has been marked as modified.
        return true;
    }
}
