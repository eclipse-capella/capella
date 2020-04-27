/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/** 
 * @author Erwan Brottier
 */
public interface InterModelInconsistency {
	
	public List<EObject> getInvolvedObjects();
	public String getTypeName();
}
