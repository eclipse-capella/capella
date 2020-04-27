/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
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