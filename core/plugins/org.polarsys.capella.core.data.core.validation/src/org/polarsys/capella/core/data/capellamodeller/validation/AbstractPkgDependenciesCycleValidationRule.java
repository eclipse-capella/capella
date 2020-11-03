/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.TarjanAlgorithm;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public abstract class AbstractPkgDependenciesCycleValidationRule extends AbstractValidationRule {

  private int identifierCounter = 0;
  private Map<Integer, AbstractDependenciesPkg> ident2Object = new HashMap<>();
  private Map<AbstractDependenciesPkg, Integer> object2Ident = new HashMap<>();

  protected abstract boolean shouldValidate(IValidationContext ctx);

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (shouldValidate(ctx)) {
      IStatus status = doValidate(ctx);
      clearMaps();
      return status;
    }
    return ctx.createSuccessStatus();
  }

  protected EObject getTarget(IValidationContext ctx) {
    return ctx.getTarget();
  }

  protected IStatus doValidate(IValidationContext ctx) {
    Collection<AbstractDependenciesPkg> allPackages = getAllPackages(getTarget(ctx));
    skipCurrentConstraintFor(ctx, allPackages);
    List<List<AbstractDependenciesPkg>> cycles = getInterPackageCycles(allPackages);
    if (!cycles.isEmpty()) {
      // There are conflicts
      return createCycleFailureStatus(ctx, cycles);
    }
    // No conflict found
    return ctx.createSuccessStatus();
  }

  /**
   * As this rule is enabled for SystemEngineering, BlockArchitecture and AbstractDependenciesPkg, we skip it for all
   * relevant children under the first context target which is the selected by the user.
   * 
   * @param ctx
   * @param allPackages
   */
  protected void skipCurrentConstraintFor(IValidationContext ctx, Collection<AbstractDependenciesPkg> allPackages) {
    EObject target = getTarget(ctx);
    if (target instanceof SystemEngineering) {
      ctx.skipCurrentConstraintForAll(((SystemEngineering) target).getOwnedArchitectures());
    }
    ctx.skipCurrentConstraintForAll(allPackages);
  }

  protected IStatus createCycleFailureStatus(IValidationContext ctx, List<List<AbstractDependenciesPkg>> cycles) {
    Collection<IStatus> statuses = new ArrayList<>();
    for (List<AbstractDependenciesPkg> cycle : cycles) {
      // Create a status to get the message from it
      IStatus status = ctx.createFailureStatus(toString(cycle));
      // Create a status with the cycle to use it in the resolver
      statuses.add(ConstraintStatus.createStatus(ctx, cycle, status.getMessage(), ""));
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }

  private void clearMaps() {
    ident2Object.clear();
    object2Ident.clear();
  }

  public List<List<AbstractDependenciesPkg>> getInterPackageCycles(EObject context) {
    return getInterPackageCycles(getAllPackages(context));
  }

  protected List<List<AbstractDependenciesPkg>> getInterPackageCycles(
      final Collection<AbstractDependenciesPkg> allPkgs) {

    // Packages to check
    Set<AbstractDependenciesPkg> pkgsToCheck = new HashSet<>(allPkgs);

    // Create the graph
    Map<Integer, Set<Integer>> graphMap = new HashMap<>();

    while (!(pkgsToCheck = buildPkgGraph(pkgsToCheck, allPkgs, graphMap)).isEmpty()) {
      allPkgs.addAll(pkgsToCheck);
    }

    @SuppressWarnings("unchecked")
    Set<Integer>[] graph = new HashSet[identifierCounter];
    for (int i = 0; i < identifierCounter; i++) {
      Set<Integer> x = graphMap.get(i);
      if (x == null) {
        x = new HashSet<>();
      }
      graph[i] = x;
    }

    // Compute using TarjanAlgorithm
    TarjanAlgorithm t = new TarjanAlgorithm();
    List<List<Integer>> scComponents = t.getSCComponents(graph);
    List<List<AbstractDependenciesPkg>> cfcs = new ArrayList<>();
    for (List<Integer> list : scComponents) {
      if (list.size() > 1) {
        List<AbstractDependenciesPkg> cfc = new ArrayList<>();
        for (Integer integer : list) {
          cfc.add(ident2Object.get(integer));
        }
        cfcs.add(cfc);
      }
    }

    return cfcs;
  }

  protected Collection<AbstractDependenciesPkg> getAllPackages(EObject context) {
    if (context instanceof SystemEngineering) {
      return AbstractDependenciesPkgExt.getAllPackages((SystemEngineering) context);
    } else if (context instanceof AbstractDependenciesPkg || context instanceof BlockArchitecture) {
      Collection<AbstractDependenciesPkg> returnedList = new ArrayList<>();
      returnedList.addAll(DataPkgExt.getAllDataPkgsInCurrentBlockArchitectures(context));
      returnedList.addAll(InterfacePkgExt.getAllInterfacePkgsInCurrentBlockArchitectures(context));
      return returnedList;
    }
    return Collections.emptyList();
  }

  /**
   * Build the graph [Pkg(id of pkg), Set of dependent pkgs(ids)] from input packages.
   * 
   * @param pkgsToCheck
   *          the package to check
   * @param allPkgs
   *          all the packages
   * @param graph
   *          the graph to build
   * @return
   */
  protected Set<AbstractDependenciesPkg> buildPkgGraph(Collection<AbstractDependenciesPkg> pkgsToCheck,
      Collection<AbstractDependenciesPkg> allPkgs, Map<Integer, Set<Integer>> graph) {
    Set<AbstractDependenciesPkg> newObjects = new HashSet<>();
    for (AbstractDependenciesPkg pkgToCheck : pkgsToCheck) {
      Set<Integer> dependentPkgIdentifiers = new HashSet<>();
      graph.put(getObjectIdentifier(pkgToCheck), dependentPkgIdentifiers);
      for (AbstractDependenciesPkg dependentPkg : getPkgDependencies(pkgToCheck)) {
        if (!allPkgs.contains(dependentPkg)) {
          newObjects.add(dependentPkg);
        }
        dependentPkgIdentifiers.add(getObjectIdentifier(dependentPkg));
      }
    }
    return newObjects;
  }

  protected Collection<AbstractDependenciesPkg> getPkgDependencies(AbstractDependenciesPkg pkgToCheck) {
    return AbstractDependenciesPkgExt.getDependencies(pkgToCheck);
  }

  private Integer getObjectIdentifier(AbstractDependenciesPkg object) {
    return object2Ident.computeIfAbsent(object, f -> {
      Integer res = identifierCounter;
      identifierCounter++;
      ident2Object.put(res, object);
      return res;
    });
  }

  /**
   * It takes into account only the first 3 packages in order not to have a long marker message. The user can use tha
   * available resolver to check the details of the cycle.
   * 
   * @param cycle
   * @return the string representing the packages in the given cycle.
   */
  protected String toString(List<AbstractDependenciesPkg> cycle) {
    StringBuilder builder = new StringBuilder();
    int count = 0;
    for (Iterator<AbstractDependenciesPkg> iterator = cycle.iterator(); iterator.hasNext();) {
      count++;
      builder.append(iterator.next().getName());
      if (iterator.hasNext() && count < 3) {
        builder.append(", ");
      } else {
        if (iterator.hasNext()) {
          builder.append(" ...");
        }
        break;
      }
    }
    return builder.toString();
  }
}
