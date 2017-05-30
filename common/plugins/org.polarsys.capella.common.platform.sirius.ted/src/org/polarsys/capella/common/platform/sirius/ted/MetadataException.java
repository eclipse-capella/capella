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
		this(childMessages(status), status);
	}

	public MetadataException(String message, IStatus status) {
		super(message);
		this.status = status;
	}

	protected static String childMessages(IStatus status) {
		String result = "";
		int i = 0;
		for (IStatus child : status.getChildren()) {
			result += child.getMessage();
			if (i < status.getChildren().length - 1) {
				result += "\n";
			}
			if (i < status.getChildren().length) {
				result += "\n";
			}
			i++;
		}
		return result;
	}

}