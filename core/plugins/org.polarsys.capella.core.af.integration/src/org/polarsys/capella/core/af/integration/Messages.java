/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.af.integration;

import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Guiu
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.polarsys.capella.core.af.integration.messages"; //$NON-NLS-1$
	public static String MetadataUpdateContributor_Name;
	public static String MetadataCreationContributor_Name;
	public static String ViewpointMigrationContributor_Name;

    static {
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

	private Messages() {
	}
}
