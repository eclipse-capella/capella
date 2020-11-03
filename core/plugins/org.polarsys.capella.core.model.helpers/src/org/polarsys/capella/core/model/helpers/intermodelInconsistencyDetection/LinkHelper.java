/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/** 
 * @author Erwan Brottier
 */
public class LinkHelper {

	public static HashSet<EObject> getTargetedObjects(EObject object) {
		HashSet<EObject> targetedObjects = new HashSet<EObject>();
		for (EReference ref : object.eClass().getEAllReferences()) {
			if (!ref.isDerived()) {
				Object o = object.eGet(ref);
				if (o != null) {
					if (o instanceof List<?>) {
						for (Object item : (List<?>) o)
							if (item instanceof EObject)
								targetedObjects.add((EObject) item);
					} else if (o instanceof EObject)
						targetedObjects.add((EObject) o);
				}
			}
		}
		return targetedObjects;
	}
	
	public static List<EReference> getReferencesForLink(EObject source, EObject target) {
		List<EReference> refs = new ArrayList<EReference>();
		for (EReference ref : source.eClass().getEAllReferences()) {
			if (!ref.isDerived()) {
				Object o = source.eGet(ref);
				if (o != null) {
					if (o instanceof List<?> && ((List<?>) o).contains(target)) {
						refs.add(ref);
					} else if (o instanceof EObject && o == target)
						refs.add(ref);
				}
			}
		}
		return refs;		
	}

}
