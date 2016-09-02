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
package org.polarsys.capella.core.af.integration.listener;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class WrongCapellaVersionException extends MetadataException {

	public WrongCapellaVersionException(IStatus status) {
		super(NLS.bind(Messages.WrongCapellaVersionException_Message, status.getMessage()), status);
	}

	public WrongCapellaVersionException() {
		super();
	}

}