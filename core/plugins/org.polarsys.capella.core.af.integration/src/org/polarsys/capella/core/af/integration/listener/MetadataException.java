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
package org.polarsys.capella.core.af.integration.listener;

import org.eclipse.core.runtime.IStatus;

public class MetadataException extends RuntimeException {

	private IStatus status;

	public MetadataException() {
		super();
	}

	public IStatus getStatus() {
		return status;
	}

	public MetadataException(IStatus status) {
		this("Migration needed or missing Viewpoint(s), see documentation:\n" + childMessages(status), status);
	}

	public MetadataException(String message, IStatus status) {
		super(message);
		this.status = status;
	}

	protected static String childMessages(IStatus status) {
		StringBuilder result = new StringBuilder();
		for (IStatus child : status.getChildren()) {
			result.append("- " + child.getMessage() + "\n");
		}
		return result.toString();
	}

}