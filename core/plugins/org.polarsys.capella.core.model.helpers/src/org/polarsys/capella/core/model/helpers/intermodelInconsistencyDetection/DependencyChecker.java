/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * Contains method used to check if a link conforms to the library dependency graph.
 * Warning : this class maintains some cache for the sake of performance. 
 * You should use a new instance each time the model is modified.
 * 
 * @author Erwan Brottier
 */
public class DependencyChecker {

	protected SemanticEditingDomain domain;
	protected HashMap<EObject, HashSet<EObject>> correctLinks = new HashMap<EObject, HashSet<EObject>>();
	protected HashMap<IModelIdentifier, HashSet<IModelIdentifier>> modelIndentifier2AllReferencedModelIdentifiers = new HashMap<IModelIdentifier, HashSet<IModelIdentifier>>();
	protected HashSet<DependencyViolation> dependencyViolations = new HashSet<DependencyViolation>();
	
	public DependencyChecker(SemanticEditingDomain domain) {
		this.domain = domain;
	}
	
	public HashSet<DependencyViolation> getDependencyViolations() {
		return dependencyViolations;
	}
	
	public HashSet<IModelIdentifier> getAllReferencedLibraryIdentifiers(IModel model) {
		HashSet<IModelIdentifier> res = modelIndentifier2AllReferencedModelIdentifiers.get(model.getIdentifier());
		if (res == null) {
			res = new HashSet<IModelIdentifier>();
			for (IModel referencedModel : LibraryManagerExt.getAllReferences(model))
				res.add(referencedModel.getIdentifier());
			modelIndentifier2AllReferencedModelIdentifiers.put(model.getIdentifier(), res);
		}
		return res;
	}

	/** Return true if a link between @param source and @param target conforms to library dependency graph.
	 *  If false, create and store an instance of @see DependencyViolation that describe the error.
	 *  The @param reference is optional (can be null) and is only used to describe the error.  */
	public boolean checkLink(EObject source, EObject target, EReference reference) {
//		System.out.println("CHECK link {"); //$NON-NLS-1$
//		System.out.println("	source:"+source); //$NON-NLS-1$
//		System.out.println("	target:"+target); //$NON-NLS-1$
//		System.out.println("}"); //$NON-NLS-1$
		if (source.eResource() != target.eResource()) {
			if (doesLinkHasBeenDeclaredAsCorrect(source, target)) {
//				System.out.println("!! CACHED RESULT !!");
				return true;
			}
			IModel sourceModel = ILibraryManager.INSTANCE.getModel(source);
			IModel targetModel = ILibraryManager.INSTANCE.getModel(target);
			if (sourceModel != null && targetModel != null && !sourceModel.equals(targetModel)) {
//				Collection<IModelIdentifier> refs = sourceModel.getReferences();
//				IModelIdentifier identifier = targetModel.getIdentifier();
//				boolean res = refs.contains(identifier);
				boolean res = getAllReferencedLibraryIdentifiers(sourceModel).contains(targetModel.getIdentifier());				
				if (res)
					declareLinkAsCorrect(source, target);
				else
					dependencyViolations.add(new DependencyViolation(source, target, reference));
//				System.out.println("!! REAL TEST !!");
				return res;
			}
		}
//		else
//			System.out.println("!! SAME RESOURCE !!");
		return true;
	}
	
	public boolean checkAllLinks(EObject object) {
		for (EReference ref : object.eClass().getEAllReferences()) {
			if (!ref.isDerived()) {
				Object o = object.eGet(ref);
				if (o != null) {
					HashSet<EObject> targetedObjects = new HashSet<EObject>();
					if (o instanceof List<?>) {
						for (Object item : (List<?>) o)
							if (item instanceof EObject)
								targetedObjects.add((EObject) item);
					} else if (o instanceof EObject)
						targetedObjects.add((EObject) o);
					for (EObject target : targetedObjects) {
						if (!checkLink(object, target, ref))
							return false;
						if (ref.isContainment() && !checkAllLinks(target))
							return false;
					}
				}
			}
		}
		for (EObject source : EObjectExt.getReferencers(object, null, domain)) {
			if (!checkLink(source, object, null))
				return false;			
		}
		return true;
	}
	
	protected void declareLinkAsCorrect(EObject source, EObject target) {
		HashSet<EObject> list = correctLinks.get(source);
		if (list == null) {
			list = new HashSet<EObject>();
			correctLinks.put(source, list);
		}
		list.add(target);		
	}
	
	protected boolean doesLinkHasBeenDeclaredAsCorrect(EObject source, EObject target) {
		HashSet<EObject> list = correctLinks.get(source);
		return list != null && list.contains(target);
	}
}
