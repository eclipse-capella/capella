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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Arrays;

public final class SiriusViewConstants {

    /** Mappings of LogicalFunction. */
    public static final String[] lOGICAL_FUNCTION_DF_MAPPING_NAMES = { "LDF_Function", "LDF_WhiteBoxFunc", "LDFB_Function" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    /** Mappings of Transitions. */
    public static final String[] TRANSITIONS_DF_MAPPING_NAMES = { "LDF_Flow_NodeToFunc", "LDF_Flow_NodeToNode", "LDF_Flow_FuncToFunc", "LDF_Flow_FuncToNode", "LDFB_Flow_FuncToFunc" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    
    static {
        Arrays.sort(lOGICAL_FUNCTION_DF_MAPPING_NAMES);
        Arrays.sort(TRANSITIONS_DF_MAPPING_NAMES);
    }

    /**
     * Avoid instantiation.
     */
    private SiriusViewConstants() {
        // empty.
    }

}
