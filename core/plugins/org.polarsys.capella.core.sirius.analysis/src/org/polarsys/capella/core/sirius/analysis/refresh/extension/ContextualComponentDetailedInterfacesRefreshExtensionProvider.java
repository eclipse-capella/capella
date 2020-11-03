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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * FIXME COMMENT
 * 
 */
public class ContextualComponentDetailedInterfacesRefreshExtensionProvider implements IRefreshExtensionProvider {

    private static final ContextualComponentDetailedInterfacesRefreshExtension REFRESH_EXTENSION = new ContextualComponentDetailedInterfacesRefreshExtension();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#getRefreshExtension(org.eclipse.sirius.DDiagram)
     */
    public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
        return REFRESH_EXTENSION;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#provides(org.eclipse.sirius.DDiagram)
     */
    public boolean provides(DDiagram viewPoint) {
        return IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(viewPoint.getDescription().getName());
    }

}
