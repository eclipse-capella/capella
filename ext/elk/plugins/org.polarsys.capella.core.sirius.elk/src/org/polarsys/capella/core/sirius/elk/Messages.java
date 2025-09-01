/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.elk;

import org.eclipse.osgi.util.NLS;

/**
 * Internalization of displayed elements.
 * 
 * @author nperansin
 */
public final class Messages extends NLS {
    private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$

    public static String SECTION_COMMAND_NAME;

    public static String SECTION_GROUP_NAME;

    public static String SECTION_MODE_NAME;
    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
