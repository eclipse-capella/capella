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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Algorithm based on Tarjan algorithm that detect:
 * <ul>
 * <li>inter-model dependency violations</li>
 * <li>inter-model cycles</li>
 * </ul>
 * 
 * @author Erwan Brottier
 */
public class InterModelInconsistencyDetector {

  protected Map<Integer, EObject> ident2Object = new HashMap<>();
  protected Map<EObject, Integer> object2Ident = new HashMap<>();
  protected List<InterModelInconsistency> errors = new ArrayList<>();
  protected int identifierCounter = 0;
  protected DependencyChecker linkChecker;

  @SuppressWarnings("unchecked")
  public List<InterModelInconsistency> getInterModelInconsistencies(SystemEngineering systemEngineering) {
    linkChecker = createDependencyChecker(systemEngineering);

    // Get all objects under the given model element
    Set<EObject> allObjects = getAllContents(systemEngineering);

    // Create graph representation
    Map<Integer, Set<Integer>> g = new HashMap<>();
    Set<EObject> objectsToTreat = allObjects;
    while (!(objectsToTreat = treatObjects(objectsToTreat, allObjects, g)).isEmpty())
      allObjects.addAll(objectsToTreat);

    Set<Integer>[] graph = new HashSet[identifierCounter];
    for (int i = 0; i < identifierCounter; i++) {
      Set<Integer> x = g.get(i);
      if (x == null) {
        x = new HashSet<>();
      }
      graph[i] = x;
    }

    // compute CFC
    TarjanAlgorithm t = new TarjanAlgorithm();
    List<List<Integer>> scComponents = t.getSCComponents(graph);
    List<List<EObject>> cfcs = new ArrayList<>();
    for (List<Integer> list : scComponents) {
      if (list.size() > 1) {
        List<EObject> cfc = new ArrayList<>();
        for (Integer integer : list) {
          cfc.add(ident2Object.get(integer));
        }
        cfcs.add(cfc);
      }
    }
    // Analyse CFC
    for (List<EObject> cfc : cfcs) {
      Set<Resource> res = new HashSet<>();
      for (EObject object : cfc) {
        Resource objRes = CapellaResourceHelper.getMainModelResource(object);
        if (objRes != null) {
          res.add(objRes);
        }
      }
      if (res.size() > 1)
        errors.add(new InterModelCycle(cfc));
    }
    errors.addAll(linkChecker.getDependencyViolations());
    return errors;
  }

  private Set<EObject> getAllContents(SystemEngineering systemEngineering) {
    TreeIterator<EObject> treeIterator = systemEngineering.eAllContents();
    Set<EObject> allObjects = new HashSet<>();
    while (treeIterator.hasNext()) {
      allObjects.add(treeIterator.next());      
    }
    return allObjects;
  }

  private DependencyChecker createDependencyChecker(SystemEngineering systemEngineering) {
    return new DependencyChecker((SemanticEditingDomain) TransactionHelper.getEditingDomain(systemEngineering));
  }

  protected Integer getObjectIdentifier(EObject object) {
    return object2Ident.computeIfAbsent(object, f -> {
      Integer res = identifierCounter;
      identifierCounter++;
      ident2Object.put(res, object);
      return res;
    });
  }

  protected Set<EObject> treatObjects(Set<EObject> objects, Set<EObject> allObjects, Map<Integer, Set<Integer>> g) {
    Set<EObject> newObjects = new HashSet<>();
    for (EObject currentNode : objects) {
      Set<Integer> targetedNodeIdent = new HashSet<>();
      g.put(getObjectIdentifier(currentNode), targetedNodeIdent);
      HashSet<EObject> targetedObjects = LinkHelper.getTargetedObjects(currentNode);
      for (EObject targetedObject : targetedObjects) {
        if (!allObjects.contains(targetedObject)) {
          newObjects.add((EObject) targetedObject);          
        }
        targetedNodeIdent.add(getObjectIdentifier((EObject) targetedObject));
        linkChecker.checkLink(currentNode, targetedObject, null);
      }
    }
    return newObjects;
  }
}
