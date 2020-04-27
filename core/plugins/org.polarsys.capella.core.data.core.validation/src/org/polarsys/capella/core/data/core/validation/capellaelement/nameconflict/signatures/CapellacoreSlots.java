/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.util.CapellacoreSwitch;

/**
 * Computes slots for Capellacore model objects. 
 * 
 */
public class CapellacoreSlots extends CapellacoreSwitch<Object> {

    @Override
    /**
     * Everything that's a "Namespace" goes into the same slot.
     * {@inheritDoc}
     */
    public Object caseNamespace(Namespace object) {
      return CapellacorePackage.Literals.NAMESPACE;
    }  

}
