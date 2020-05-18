/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;

/**
 * @author Erwan Brottier
 */
public class DependencyViolation implements InterModelInconsistency {

	protected EObject source;
	protected EObject target;
	protected EReference reference;
	protected List<EObject> involvedObjects;

	public DependencyViolation(EObject source, EObject target, EReference reference) {
		this.source = source;
		this.target = target;
		involvedObjects = new ArrayList<EObject>();
		involvedObjects.add(source);
		involvedObjects.add(target);
		this.reference = reference;
	}

	@Override
	public List<EObject> getInvolvedObjects() {
		return involvedObjects;
	}

	public EObject getSource() {
	  return source;
	}

	public EObject getTarget() {
	  return target;
	}

	public EReference getEReference() {
	  return reference;
	}

	@Override
	public String getTypeName() {
		return "dependency violation"; //$NON-NLS-1$
	}

	public String getViolatedDependencyDescription() {
		IModel sourceModel = ILibraryManager.INSTANCE.getModel(source);
		IModel targetModel = ILibraryManager.INSTANCE.getModel(target);
		return sourceModel.getIdentifier().getName()+" -> "+targetModel.getIdentifier().getName(); //$NON-NLS-1$
	}

	public String getDescription() {
		String sourceModelName = ILibraryManager.INSTANCE.getModel(source).getIdentifier().getName();
		String targetModelName = ILibraryManager.INSTANCE.getModel(target).getIdentifier().getName();
		String referenceName = reference != null ? reference.getName() : null;
		String description = "'" + EObjectLabelProviderHelper.getText(source) + "'["+source.eClass().getName()+"] (in model '"+sourceModelName+"') targets '" + EObjectLabelProviderHelper.getText(target) + "'["+target.eClass().getName()+"] (in model '"+targetModelName+"')"; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		if (referenceName != null)
			description += " via reference '"+referenceName+"'";  //$NON-NLS-1$//$NON-NLS-2$
		return description;
	}
}
