/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import org.eclipse.osgi.util.NLS;

public class NoMetadataException extends RuntimeException {

	public NoMetadataException(String message) {
		super(NLS.bind(Messages.NoMetadataException_Message, message));
	}

	public NoMetadataException() {
		super();
	}

}