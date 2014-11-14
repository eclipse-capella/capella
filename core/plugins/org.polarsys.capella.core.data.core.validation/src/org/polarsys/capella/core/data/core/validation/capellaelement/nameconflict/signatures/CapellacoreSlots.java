/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public Object caseNamespace(Namespace object_p) {
      return CapellacorePackage.Literals.NAMESPACE;
    }  

}
