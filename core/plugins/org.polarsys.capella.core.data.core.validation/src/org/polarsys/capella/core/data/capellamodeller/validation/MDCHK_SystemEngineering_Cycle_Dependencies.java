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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class MDCHK_SystemEngineering_Cycle_Dependencies extends AbstractValidationRule {

  static public Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> getCycleOfDependencies(final SystemEngineering context) {
    Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> graphOfDependencies = new HashMap<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>>(); // graph of dependencies
    Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> reversedGraphOfDependencies = new HashMap<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>>(); // graph of dependencies with inverse edges
    Collection<AbstractDependenciesPkg> allPackages = AbstractDependenciesPkgExt.getAllPackages(context);
    // init graphs of dependencies
    for (AbstractDependenciesPkg aPackage : allPackages) {
      reversedGraphOfDependencies.put(aPackage, new HashSet<AbstractDependenciesPkg>());
    }
    for (AbstractDependenciesPkg aPackage : allPackages) {
      Collection<AbstractDependenciesPkg> dependentPackages = AbstractDependenciesPkgExt.getDependentPackages(aPackage);
      graphOfDependencies.put(aPackage, dependentPackages);
      for (AbstractDependenciesPkg aDependentPackage : dependentPackages) {
        reversedGraphOfDependencies.get(aDependentPackage).add(aPackage);
      }
    }
    // algorithm
    Iterator<Entry<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>>> it = null;
    while (!graphOfDependencies.isEmpty()) {
      it = graphOfDependencies.entrySet().iterator();
      Collection<AbstractDependenciesPkg> toBeRemoved = new ArrayList<AbstractDependenciesPkg>();
      Entry<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> dependency = null;
      // iteration to find node with no outgoing edges
      while (it.hasNext()) {
        dependency = it.next();
        // if there is no outgoing edge
        if (dependency.getValue().isEmpty()) {
          AbstractDependenciesPkg selectedPackage = dependency.getKey();
          for (AbstractDependenciesPkg aPackage : reversedGraphOfDependencies.get(selectedPackage)) {
            // deletion of incoming edge
            graphOfDependencies.get(aPackage).remove(selectedPackage);
          }
          // deletion of the node after iteration
          toBeRemoved.add(selectedPackage);
        }
      }
      it = null;
      // if there is no node with no outgoing edge
      if (toBeRemoved.isEmpty()) {
        // so there is a cycle
        // return packages of the cycle
        return graphOfDependencies;
      }
      // deletion of nodes
      for (AbstractDependenciesPkg aPackageToDelete : toBeRemoved) {
        graphOfDependencies.remove(aPackageToDelete);
      }
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SystemEngineering) {
        Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> dependencies = getCycleOfDependencies((SystemEngineering) eObj);
        if (null != dependencies && !dependencies.isEmpty()) {
          // There are conflicts
          IStatus status = createFailureStatus(ctx_p, new Object[] { Integer.valueOf(dependencies.size()) });
          statuses.add(status);
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }
      }
    }
    // No conflict found
    return ctx_p.createSuccessStatus();
  }
}
