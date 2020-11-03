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
package org.polarsys.capella.core.model.handler.crossreferencer;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider;

/**
 * A cross referencer provider specific to Capella M2.<br>
 * This result in an {@link ECrossReferenceAdapter} customized for Capella needs.
 */
public class CapellaCrossReferencerProvider implements ICrossReferencerProvider {

    /**
     * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider#getCrossReferencer()
     */
    @Override
    public SiriusCrossReferenceAdapter getCrossReferencer(EditingDomain editingDomain) {
        return new CapellaECrossReferenceAdapter(editingDomain);
    }

}
