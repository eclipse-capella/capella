/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import org.eclipse.core.runtime.IStatus;

/**
 * Use a MetadataException instead
 */
@Deprecated
public class WrongCapellaVersionException extends MetadataException {

	public WrongCapellaVersionException(IStatus status) {
		super(status);
	}

	public WrongCapellaVersionException() {
		super();
	}

}