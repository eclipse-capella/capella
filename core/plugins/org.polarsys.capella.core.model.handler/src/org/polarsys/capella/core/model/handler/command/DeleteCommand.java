/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;

/**
 * A model command that deletes given elements, along with their references.<br>
 * This code is taken from {@link org.eclipse.emf.edit.command.DeleteCommand}, but differs at cross referencing time, and allow for sub-classing deletion
 * behavior.
 */
public class DeleteCommand extends CompoundCommand {

	/**
	 * Editing domain holding semantic elements, in which the command operates.
	 */
	private EditingDomain editingDomain;
	/**
	 * Collection of elements to be deleted.
	 */
	private Collection<?> elementsToDelete;

	/**
	 * @param editingDomain must be not null
	 * @param elementToDelete must be not null
	 */
	public DeleteCommand(EditingDomain editingDomain, Collection<?> elementToDelete){
	  Assert.isTrue(editingDomain instanceof TransactionalEditingDomain, "The Editing Domain must be not null and must be a Transactional one");
		Assert.isNotNull(elementToDelete, "The Element set to delete must be not null"); //$NON-NLS-1$
		this.editingDomain = editingDomain;
		this.elementsToDelete = elementToDelete;
	}

	/**
	 * Delete specified pointing reference.
	 * @param referencingEObject The object referencing the deleted element.
	 * @param feature The feature hosting the reference.
	 * @param referenceToDelete The deleted element to remove.
	 */
	protected void deletePointingReference(EObject referencingEObject, EStructuralFeature feature, EObject referenceToDelete) {
		if (feature.isMany()) {
			appendAndExecute(RemoveCommand.create(getEditingDomain(), referencingEObject, feature, referenceToDelete));
		} else {
			appendAndExecute(SetCommand.create(getEditingDomain(), referencingEObject, feature, null));
		}
	}

	@Override
	public void execute() {
		// Unwrap elements to be deleted.
		Collection<EObject> eObjects = new LinkedHashSet<EObject>();
		for (Object wrappedObject : getElementsToDelete()) {
			Object object = AdapterFactoryEditingDomain.unwrap(wrappedObject);
			if (object instanceof EObject) {
				eObjects.add((EObject) object);
				for (Iterator<EObject> j = ((EObject) object).eAllContents(); j.hasNext();) {
					eObjects.add(j.next());
				}
			} else if (object instanceof Resource) {
				for (Iterator<EObject> j = ((Resource) object).getAllContents(); j.hasNext();) {
					eObjects.add(j.next());
				}
			}
		}

		// Use permanent cross referencer for better performances.
		// Note that this one does only take into account semantic resources.
		ECrossReferenceAdapter crossReferencer = getECrossReferencerAdapter();
		Map<EObject, Collection<EStructuralFeature.Setting>> usages = new HashMap<EObject, Collection<Setting>>(0);
		for (EObject eObject : eObjects) {
			Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObject, true);
			// Remove containment references out of inverse references, there are handled natively by the command.
			for (Iterator<Setting> settings = inverseReferences.iterator(); settings.hasNext();) {
				Setting setting = settings.next();
				try {
					// Test feature against reference, and containment.
					if (EReference.class.cast(setting.getEStructuralFeature()).isContainment()) {
						// Remove this feature from inverse references.
						settings.remove();
					}
				} catch (ClassCastException cce) {
					// Not a reference, skip it.
				}
			}
			usages.put(eObject, inverseReferences);
		}

		// Execute composing commands.
		super.execute();

		// Delete references.
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet()) {
			EObject eObject = entry.getKey();
			Collection<EStructuralFeature.Setting> settings = entry.getValue();
			for (EStructuralFeature.Setting setting : settings) {
				EObject referencingEObject = setting.getEObject();
				if (!eObjects.contains(referencingEObject)) {
					EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
					if (eStructuralFeature.isChangeable()) {
						deletePointingReference(referencingEObject, eStructuralFeature, eObject);
					}
				}
			}
		}
	}

	/**
	 * Do add an initial command to this compound one.<br>
	 * Default implementation adds an EMF {@link RemoveCommand}.
	 */
	protected void doPrepare() {
		append(RemoveCommand.create(getEditingDomain(), getElementsToDelete()));
	}

	/**
	 * @see org.eclipse.emf.common.command.CompoundCommand#prepare()
	 */
	@Override
	protected boolean prepare() {
		doPrepare();
		return super.prepare();
	}

	protected ECrossReferenceAdapter getECrossReferencerAdapter() {
    for (Adapter adapter : editingDomain.getResourceSet().eAdapters()) {
            if (adapter instanceof SiriusCrossReferenceAdapter) {
        return (ECrossReferenceAdapter) adapter;
      }
    }
    return null;
	}

	protected EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	protected Collection<?> getElementsToDelete() {
		return elementsToDelete;
	}
}