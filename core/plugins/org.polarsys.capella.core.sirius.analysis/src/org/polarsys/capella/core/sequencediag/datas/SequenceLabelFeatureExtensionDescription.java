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
package org.polarsys.capella.core.sequencediag.datas;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;

public class SequenceLabelFeatureExtensionDescription implements
		FeatureExtensionDescription {

	public EClass eClass() {
		return null;
	}

	public Resource eResource() {
		return null;
	}

	public EObject eContainer() {
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		return null;
	}

	public EReference eContainmentFeature() {
		return null;
	}

	public EList<EObject> eContents() {
		return null;
	}

	public TreeIterator<EObject> eAllContents() {
		return null;
	}

	public boolean eIsProxy() {
		return false;
	}

	public EList<EObject> eCrossReferences() {
		return null;
	}

	public Object eGet(EStructuralFeature feature) {
		return null;
	}

	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return null;
	}

	public void eSet(EStructuralFeature feature, Object newValue) {

	}

	public boolean eIsSet(EStructuralFeature feature) {
		return false;
	}

	public void eUnset(EStructuralFeature feature) {

	}

	public Object eInvoke(EOperation operation, EList<?> arguments)
			throws InvocationTargetException {
		return null;
	}

	public EList<Adapter> eAdapters() {
		return null;
	}

	public boolean eDeliver() {
		return false;
	}

	public void eSetDeliver(boolean deliver) {

	}

	public void eNotify(Notification notification) {

	}
}
