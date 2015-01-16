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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/** 
 * @author Erwan Brottier
 */
public class InterModelInconsistencyDetector {

	protected HashMap<Integer, EObject> ident2Object = new HashMap<Integer, EObject>();
	protected HashMap<EObject, Integer> object2Ident = new HashMap<EObject, Integer>();
	protected List<InterModelInconsistency> errors = new ArrayList<InterModelInconsistency>();
	protected int identifierCounter = 0;
	protected DependencyChecker linkChecker;

	@SuppressWarnings("unchecked")
	public List<InterModelInconsistency> getInterModelInconsistencies(SystemEngineering systemEngineering) {
		long startTime = System.currentTimeMillis();
		linkChecker = new DependencyChecker((SemanticEditingDomain) TransactionHelper.getEditingDomain(systemEngineering));
		
		/** get all objects in the graph **/
		TreeIterator<EObject> treeIterator = systemEngineering.eAllContents();
		HashSet<EObject> allObjects = new HashSet<EObject>();
		while (treeIterator.hasNext())
			allObjects.add(treeIterator.next());
		
		/** create graph representation **/
		HashMap<Integer, HashSet<Integer>> g = new HashMap<Integer, HashSet<Integer>>();		
		HashSet<EObject> objectsToTreat = allObjects;
		while ((objectsToTreat = treatObjects(objectsToTreat, allObjects, g)).size() > 0)
			allObjects.addAll(objectsToTreat);

		HashSet<Integer>[] graph = new HashSet[identifierCounter];		
		for (int i = 0; i < identifierCounter; i++) {
//			System.out.println("put "+i+" "+g.get(i)); //$NON-NLS-1$ //$NON-NLS-2$
			HashSet<Integer> x = g.get(i);
			if (x == null) {
				x = new HashSet<Integer>();
			}
			graph[i] = x;			
		}		
		
		/** compute cfc **/
		TarjanAlgorithm t = new TarjanAlgorithm();
		List<List<Integer>> scComponents = t.getSCComponents(graph);
		List<List<EObject>> cfcs = new ArrayList<List<EObject>>();
		for (List<Integer> list : scComponents) {
			if (list.size() > 1) {
				List<EObject> cfc = new ArrayList<EObject>();
				for (Integer integer : list) {
					cfc.add(ident2Object.get(integer));
				}
				cfcs.add(cfc);
			}
		}
		
		/** analyse cfc **/ 
		for (List<EObject> cfc : cfcs) {
			HashSet<Resource> res = new HashSet<Resource>();
			for (EObject object : cfc)
				res.add(object.eResource());
			if (res.size() > 1)
				errors.add(new InterModelCycle(cfc));
		}
		errors.addAll(linkChecker.getDependencyViolations());

		long endTime = System.currentTimeMillis();
		long millis = (endTime - startTime);  //milliseconds.
		String duration = String.format("%02d:%02d:%02d",  //$NON-NLS-1$
				TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));   		
		System.out.println("nb element "+identifierCounter); //$NON-NLS-1$
		System.out.println(">> VALIDATION DURATION ("+millis+") : "+duration); //$NON-NLS-1$ //$NON-NLS-2$
		return errors;
	}
	
	protected Integer getObjectIdentifier(EObject object) {
		Integer res = object2Ident.get(object);
		if (res == null) {
			res = identifierCounter;
			identifierCounter++;
			object2Ident.put(object, res);
			ident2Object.put(res, object);
		}
		return res;
	}
		
//	protected void addDependencyViolation(EObject source, EObject target) {
//		System.out.println("INCONSISTENT LINK DETECTED :"); //$NON-NLS-1$
//		System.out.println("- "+source); //$NON-NLS-1$
//		System.out.println("- "+target); //$NON-NLS-1$
//		List<EObject> involvedElements = new ArrayList<EObject>();
//		involvedElements.add(source);
//		involvedElements.add(target);
//		errors.add(new InterModelInconsistency(involvedElements, InterModelInconsistency.DEPENDENCY_VIOLATION));
//	}
	
	protected HashSet<EObject> treatObjects(HashSet<EObject> objects, HashSet<EObject> allObjects, HashMap<Integer, HashSet<Integer>> g) {
		HashSet<EObject> newObjects = new HashSet<EObject>();
		for (EObject currentNode : objects) {
			HashSet<Integer> targetedNodeIdent = new HashSet<Integer>();
			g.put(getObjectIdentifier(currentNode), targetedNodeIdent);			
			HashSet<EObject> targetedObjects = LinkHelper.getTargetedObjects(currentNode);
			for (EObject targetedObject : targetedObjects) {
				if (!allObjects.contains(targetedObject))
					newObjects.add((EObject) targetedObject);							
				targetedNodeIdent.add(getObjectIdentifier((EObject) targetedObject));
				linkChecker.checkLink(currentNode, targetedObject, null);
			}
		}
		return newObjects;
	}
	
}
