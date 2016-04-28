/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;

public class ProjectExt {

	/** @return the project of the model containing the given object. */
	public static Project getProject(EObject object) {
		while (object != null) {
			if (object instanceof Project)
				return (Project) object;
			else
				object = object.eContainer();
		}
		return null;
	}
}
