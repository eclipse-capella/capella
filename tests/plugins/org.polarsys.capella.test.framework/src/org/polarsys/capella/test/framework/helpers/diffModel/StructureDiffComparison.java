/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.diffModel;

import java.util.HashMap;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;

/**
 * Generic implementation of DefaultMatchPolicy used to compare Capella models. The comparison is not based on IDs but only on the model structure.<br>
 * Useful to check if a model generated during a test equals to the expected model, especially when the test means creating new elements in the model 
 * (because element IDs are randomly generated). 
 * 
 * @author Erwan Brottier
 */
public class StructureDiffComparison extends DefaultMatchPolicy {

	private HashMap<IModelScope, HashMap<EObject, String>> scope2IdSpace = new HashMap<IModelScope, HashMap<EObject, String>>();
	private HashMap<IModelScope, HashMap<String, Integer>> scope2EquivalentIdTable = new HashMap<IModelScope, HashMap<String, Integer>>();
	
	private HashMap<EObject, String> getIdSpace(IModelScope scope_p) {
		HashMap<EObject, String> res = scope2IdSpace.get(scope_p);
		if (res == null) {
			res = new HashMap<EObject, String>();
			scope2IdSpace.put(scope_p, res);
		}
		return res;
	}
	
	private HashMap<String, Integer> getEquivalentIdTable(IModelScope scope_p) {
		HashMap<String, Integer> equivalentIdTable = scope2EquivalentIdTable.get(scope_p);
		if (equivalentIdTable == null) {
			equivalentIdTable = new HashMap<String, Integer>();
			scope2EquivalentIdTable.put(scope_p, equivalentIdTable);
		}
		return equivalentIdTable;
	}
	
	private String getIdentifier(EObject element_p, IModelScope scope_p) {
		HashMap<EObject, String> idSpace = getIdSpace(scope_p);
		return idSpace.get(element_p);
	}
	
	private String saveIdentifier(EObject element_p, IModelScope scope_p, String identifier) {
		HashMap<EObject, String> idSpace = getIdSpace(scope_p);
		if (idSpace.values().contains(identifier)) {
			HashMap<String, Integer> table = getEquivalentIdTable(scope_p);
			Integer suffix = table.get(identifier);
			if (suffix == null)
				suffix = 0;
			suffix++;
			table.put(identifier, suffix);
			identifier = identifier+"$$"+suffix; //$NON-NLS-1$
		}
		idSpace.put(element_p, identifier);
		return identifier;
	}
	
	public String getIdentifier(EObject object) {
		for (IModelScope key : scope2IdSpace.keySet()) {
			String identifier = scope2IdSpace.get(key).get(object);
			if (identifier != null)
				return identifier;
		}
		return null;
	}
	
	@Override
	public Object getMatchID(EObject element_p, IModelScope scope_p) {		
		String identifier = getIdentifier(element_p, scope_p);
		if (identifier == null) {
			identifier = getSpecificIdentifier(element_p, scope_p);						
			if (identifier == null) {
					identifier = ""; //$NON-NLS-1$
					EObject container = element_p.eContainer();
					if (container != null) {
						identifier = (String) getMatchID(container, scope_p) + '/';
					}
					identifier += getLocalIdentifier(element_p);
			}
			// special case when local identifier is blank (typically if the object or one of its container is not contained by the project (DanglingHREFException is raised in that case if the model is saved) 
			if (identifier.equals("/")) {//$NON-NLS-1$
				identifier = NO_NAME;
				if (element_p instanceof NamedElement)
					identifier += ((NamedElement) element_p).getName();				
			}
			// dismiss identical id for one scope
			identifier = saveIdentifier(element_p, scope_p, identifier);
		}
		return identifier; 
	} 
	
	private static final String NULL_VALUE = "[null]"; //$NON-NLS-1$
	private static final String NO_NAME = "$$[NO_LOCAL_NAME]"; //$NON-NLS-1$
	
	private String getSpecificIdentifier(Object element, IModelScope scope_p) {
		if (element instanceof RequirementsTrace) {
			RequirementsTrace trace = (RequirementsTrace) element;
			TraceableElement source = trace.getSourceElement();
			TraceableElement target = trace.getTargetElement();
			return "RequirementsTrace["+(source != null ? getMatchID(source, scope_p).toString() : NULL_VALUE) + " => " + (target != null ? getMatchID(target, scope_p).toString() : NULL_VALUE)+"]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return null;
	}
	
	private String getLocalIdentifier(EObject element) {
		return EObjectLabelProviderHelper.getText(element);
	}

}
