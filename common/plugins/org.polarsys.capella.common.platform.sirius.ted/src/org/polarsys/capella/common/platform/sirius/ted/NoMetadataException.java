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

@Deprecated
/**
 * Use a MetadataException instead
 */
public class NoMetadataException extends RuntimeException {

	public NoMetadataException(String message) {
		super(message);
	}

	public NoMetadataException() {
		super();
	}

}