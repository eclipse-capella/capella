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

package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * 
 */
public abstract class BusPatternHelper extends PatternHelper {

	public Map<EClass, EndDescription> getEndsMap() {
		if (getPattern() instanceof BusPattern) {
			return ((BusPattern) getPattern()).getEndsMap();
		}
		return null;
	}

	public EClass getBusEClass() {
		if (getPattern() instanceof BusPattern) {
			return ((BusPattern) getPattern()).getBusEClass();
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public Set<EObject> getAvailable(EObject from) {
		if (from == null)
			return null;
		Map<EClass, EndDescription> endsMap = getEndsMap();
		EClass busEClass = getBusEClass();
		Set<EClass> endsEClass = endsMap.keySet();
		if (endsMap == null || busEClass == null)
			return null;
		Set<EObject> available = new HashSet<EObject>();
		if (busEClass.isSuperTypeOf(from.eClass())) {
			return available;
		}
		for (EClass endEClass : endsEClass) {
			if (busEClass.isSuperTypeOf(from.eClass())) {
				return available;
			}
		}
		// found nothing...
		return available;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<EObject> getCurrent(EObject from) {
		if (from == null)
			return null;
		Set<EObject> current = new HashSet<EObject>();
		Map<EClass, EndDescription> endsMap = getEndsMap();
		EClass busEClass = getBusEClass();
		Set<EClass> endsEClass = endsMap.keySet();
		if (endsMap == null || busEClass == null)
			return null;
		if (busEClass.isSuperTypeOf(from.eClass())) {
			for (EClass endEClass : endsEClass) {
				EndDescription desc = endsMap.get(endEClass);
				String endName = desc.getEndName();
				if (endName != "" && !busEClass.getEAllReferences().isEmpty()) { //$NON-NLS-1$
					List<EReference> references = busEClass.getEAllReferences();
					for (EReference ref : references) {
						if (ref.getName().compareTo(endName) == 0) {
							if (ref.isMany()) {
								EList<EObject> many = (EList<EObject>) from.eGet(ref, true /* resolve */);
								if (many != null && many.isEmpty())
									current.addAll(many);
								return current;
							}
							EObject single = (EObject) from.eGet(ref, true /* resolve */);
							if (single != null)
								current.add(single);
							return current;
						}
					}
				}
			}
			return current;
		}
		for (EClass endEClass : endsEClass) {
			EndDescription desc = endsMap.get(endEClass);
			String busName = desc.getBusName();
			if (busName != "" && !endEClass.getEAllReferences().isEmpty()) { //$NON-NLS-1$
				List<EReference> references = busEClass.getEAllReferences();
				for (EReference ref : references) {
					if (ref.getName().compareTo(busName) == 0) {
						if (ref.isMany()) {
							EList<EObject> many = (EList<EObject>) from.eGet(ref, true /* resolve */);
							if (many != null && many.isEmpty())
								current.addAll(many);
							return current;
						}
						EObject single = (EObject) from.eGet(ref, true /* resolve */);
						if (single != null)
							current.add(single);
						return current;
					}
				}
			}
		}
		// found nothing...
		return current;
	}

	/**
	 * Extracts first list object and checks if it can be a Bus. Handles job to
	 * {@link #validateBusPattern(EObject, Set)}.
	 * 
	 * @return false if first list object can not be viewed as bus or
	 *         {@link #validateBusPattern(EObject, Set)} returns false
	 */
	@Override
	public boolean validatePattern(Set<EObject> objects) {
		if (objects == null || objects.isEmpty())
			return false;

		return false;
	}

	/**
	 * Checks that objects are currently connected by our Pattern. IE
	 * {@link #validatePattern(Set)} returns true.
	 */
	@Override
	public boolean validateDisconnection(Set<EObject> list) {
		return false;
	}

	/**
	 * Checks that objects are currently not connected as defined by our
	 * Pattern. IE {@link #validatePattern(Set)} returns false.
	 */
	@Override
	public boolean validateConnection(Set<EObject> list) {
		return false;
	}
}
