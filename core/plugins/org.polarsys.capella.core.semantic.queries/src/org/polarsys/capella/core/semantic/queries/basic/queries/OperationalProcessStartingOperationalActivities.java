/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.oa.OperationalProcess;

public class OperationalProcessStartingOperationalActivities extends FunctionalChainStartingFunctions {

    /**
     * 
     */
    public OperationalProcessStartingOperationalActivities() {
      // do nothing
    }
    
    @Override
    protected boolean isValidInstanceOf(Object object) {
        return object instanceof OperationalProcess;
    }

}
