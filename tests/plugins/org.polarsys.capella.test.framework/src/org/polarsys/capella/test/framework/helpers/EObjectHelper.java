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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Useful services on EObject
 */
public class EObjectHelper {

	public static Map<String, EObject> getMatchingEObjects(final EObject root, final EClass elementType,
			final EStructuralFeature featureType, String... pValues) {
		Assert.isNotNull(root);

		Map<String, EObject> results = new HashMap<String, EObject>();

		List<String> values = new ArrayList<String>();
		values.addAll(Arrays.asList(pValues));

		TreeIterator<EObject> allContents = root.eAllContents();
		while (allContents.hasNext()) {
			EObject current = allContents.next();
			EClass currentEClass = current.eClass();
			if (elementType.isSuperTypeOf(currentEClass)
					&& current.eClass().getEAllStructuralFeatures().contains(featureType)) {
				String key = (String) current.eGet(featureType);
				if (values.contains(key)) {
					results.put(key, current);
					values.remove(key);
				}
			}
		}
		return results;
	}

	public static EObject getMatchingEObject(final EObject root, final EClass elementType,
			final EStructuralFeature featureType, final String value) {
		Assert.isNotNull(root);
		EObject result = null;

		TreeIterator<EObject> allContents = root.eAllContents();

		while (allContents.hasNext()) {
			EObject current = allContents.next();

			EClass currentEClass = current.eClass();

			if (elementType.isSuperTypeOf(currentEClass)
					/* current.eClass() == elementType */ && current.eClass().getEAllStructuralFeatures()
							.contains(featureType)) {
				String key = (String) current.eGet(featureType);
				if (value.equals(key)) {
					result = current;
				}
			}
		}

		return result;
	}

	public static Map<String, EObject> getMatchingEObject(final EObject root, final EClass elementType,
			final EStructuralFeature featureType, final Collection<String> pValues) {
		Assert.isNotNull(root);

		Map<String, EObject> results = new HashMap<String, EObject>();

		List<String> values = new ArrayList<String>();
		values.addAll(pValues);

		TreeIterator<EObject> allContents = root.eAllContents();

		while (allContents.hasNext()) {
			EObject current = allContents.next();

			EClass currentEClass = current.eClass();

			if (elementType.isSuperTypeOf(currentEClass)
					/* current.eClass() == elementType */ && current.eClass().getEAllStructuralFeatures()
							.contains(featureType)) {
				String key = (String) current.eGet(featureType);
				if (pValues.contains(key)) {
					results.put(key, current);
					pValues.remove(key);
				}
			}
		}

		return results;
	}

	/**
	 * Return all children object of a given meta class.
	 * 
	 * @param root
	 *            the root {@link EObject} to check
	 * @param eClass
	 *            the type of object to find
	 * @param strictMode
	 *            must be of type/ or specialization of
	 * @return a {@link List} of found objects, otherwise an empty one.
	 */
	public static List<EObject> getAllEObjectOfType(EObject root, EClass eClass, boolean strictMode) {
		List<EObject> result = new ArrayList<EObject>();
		TreeIterator<EObject> allContents = root.eAllContents();
		EObject current = null;
		boolean isMatching = false;
		while (allContents.hasNext()) {
			current = allContents.next();
			isMatching = (true == strictMode) ? current.eClass().equals(eClass)
					: eClass.isSuperTypeOf(current.eClass());
			if (true == isMatching) {
				result.add(current);
			}
		}
		return result;
	}

	/**
	 * Fetch an EObject from its Capella identifier
	 * 
	 * @param model
	 *            the Capella model
	 * @param id
	 *            the object identifier
	 * @return the associated EObject
	 */
	public static EObject getObject(IModel model, String id) {
		IScope scope = new ScopeModelWrapper(model);
		EObject object = IdManager.getInstance().getEObject(id, scope);
		return object;
	}

	/**
	 * Fetch a list of EObject from a collection of Capella identifiers
	 * 
	 * @param model
	 *            the Capella model
	 * @param ids
	 *            the list of identifiers
	 * @return the list of associated EObject
	 */
	public static Collection<EObject> getObjects(IModel model, Collection<String> ids) {
		IScope scope = new ScopeModelWrapper(model);
		Collection<EObject> result = new ArrayList<EObject>();
		for (String id : ids) {
			EObject object = IdManager.getInstance().getEObject(id, scope);
			result.add(object);
		}
		return result;
	}
	
  public static void removeElement(EObject element) {
    if (element != null) {
      List<EObject> list = new ArrayList<EObject>();
      list.add(element);
      removeElements(list);
    }
  }
  
  public static void removeElement(final String id, final SessionContext context) {
    EObject object = context.getSemanticElement(id);
    EObjectHelper.removeElement(object);
    context.removeSemanticElement(id);
  }

  public static void removeElements(Collection<? extends EObject> elements) {
    if ((elements != null) && (elements.size() > 0)) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elements),
          elements, false, false, true);
      if (command.canExecute()) {
        command.execute();
      }
    }
  }

}
