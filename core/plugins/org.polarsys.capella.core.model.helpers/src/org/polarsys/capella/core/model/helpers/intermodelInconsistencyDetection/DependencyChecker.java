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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * This class contains methods used to check if a link conforms to the library dependency graph.
 * <br/>Warning : this class maintains some cache for the sake of performance (one on links and one on library dependencies). 
 * You should use a new instance each time the model is modified.
 * 
 * @author Erwan Brottier
 */
public class DependencyChecker {

	protected SemanticEditingDomain domain;
	protected Map<EObject, Set<EObject>> correctLinks = new HashMap<>();
	protected Map<IModelIdentifier, Set<IModelIdentifier>> modelIndentifier2AllReferencedModelIdentifiers = new HashMap<>();
	protected Set<DependencyViolation> dependencyViolations = new HashSet<>();
	
	public DependencyChecker(SemanticEditingDomain domain) {
		this.domain = domain;
	}
	
	public Set<DependencyViolation> getDependencyViolations() {
		return dependencyViolations;
	}
	
	public Set<IModelIdentifier> getAllReferencedLibraryIdentifiers(IModel model) {
		Set<IModelIdentifier> res = modelIndentifier2AllReferencedModelIdentifiers.get(model.getIdentifier());
		if (res == null) {
			res = new HashSet<>();
			for (IModel referencedModel : LibraryManagerExt.getAllReferences(model))
				res.add(referencedModel.getIdentifier());
			modelIndentifier2AllReferencedModelIdentifiers.put(model.getIdentifier(), res);
		}
		return res;
	}

	/**
	 * @param source the source node of the link
	 * @param target the target node of the link
	 * @param reference the property that implements the link
	 * @return true if the link between source and target (and implemented by the reference) conforms to the library dependency graph.
	 *  If false, create and store an instance of {@link DependencyViolation} that describes the error.
	 *  Reference is optional (can be null) and is only used to describe the error (user feedback).  */
	public boolean checkLink(EObject source, EObject target, EReference reference) {
		if (source.eResource() != target.eResource()) {
			if (doesLinkHasBeenDeclaredAsCorrect(source, target)) {
				return true;
			}
			IModel sourceModel = ILibraryManager.INSTANCE.getModel(source);
			IModel targetModel = ILibraryManager.INSTANCE.getModel(target);
			if (sourceModel != null && targetModel != null && !sourceModel.equals(targetModel)) {
				boolean res = getAllReferencedLibraryIdentifiers(sourceModel).contains(targetModel.getIdentifier());				
				if (res)
					declareLinkAsCorrect(source, target);
				else
					dependencyViolations.add(new DependencyViolation(source, target, reference));
				return res;
			}
		}
		return true;
	}
	
	public boolean checkAllLinks(EObject object) {
		for (EReference ref : object.eClass().getEAllReferences()) {
			if (!ref.isDerived()) {
				Object o = object.eGet(ref);
				if (o != null) {
					Set<EObject> targetedObjects = new HashSet<>();
					if (o instanceof List<?>) {
						for (Object item : (List<?>) o)
							if (item instanceof EObject)
								targetedObjects.add((EObject) item);
					} else if (o instanceof EObject) {
					  targetedObjects.add((EObject) o);					  
					}
					for (EObject target : targetedObjects) {
						if (!checkLink(object, target, ref))
							return false;
						if (ref.isContainment() && !checkAllLinks(target))
							return false;
					}
				}
			}
		}
		for (EObject source : EObjectExt.getReferencers(object, null, domain, true)) {
			if (!checkLink(source, object, null))
				return false;			
		}
		return true;
	}
	
	protected void declareLinkAsCorrect(EObject source, EObject target) {
    Set<EObject> list = correctLinks.computeIfAbsent(source, f -> new HashSet<>());
		list.add(target);		
	}
	
	protected boolean doesLinkHasBeenDeclaredAsCorrect(EObject source, EObject target) {
		Set<EObject> list = correctLinks.get(source);
		return list != null && list.contains(target);
	}
}
