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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 */
public class AbstractDependenciesPkgExt {

  /**
   * @param pkg_p
   * @return all packages that pkg_p directly depends on
   * @deprecated please use getDependencies() instead
   */
  @Deprecated
  public static Collection<AbstractDependenciesPkg> getDependentPackages(AbstractDependenciesPkg pkg_p) {
    return getDependencies(pkg_p);
  }

  /**
   * Search direct dependencies of a package.
   * @param pkg_p an AbstractDependenciesPkg
   * @return The dependencies of pkg_p, i.e. all packages that pkg_p directly depends on
   */
  public static Collection<AbstractDependenciesPkg> getDependencies(AbstractDependenciesPkg pkg_p) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>();
    if (pkg_p instanceof DataPkg) {
      dependencies.addAll(DataPkgExt.getDataPkgDependencies((DataPkg) pkg_p));
    }
    if (pkg_p instanceof InterfacePkg) {
      dependencies.addAll(InterfacePkgExt.getInterfacePkgDependencies((InterfacePkg) pkg_p));
    }
    return dependencies;
  }

  /**
   * Search for packages that directly depend on the parameter package, i.e the parameter's inverse dependencies. The scope of the search is the parameter's
   * entire ResourceSet.
   * @param pkg_p an AbstractDependenciesPkg
   * @return The inverse dependencies of pkg_p, i.e. all packages that depend directly on pkg_p
   */
  public static Collection<AbstractDependenciesPkg> getInverseDependencies(AbstractDependenciesPkg pkg_p) {

    List<AbstractDependenciesPkg> result = new ArrayList<AbstractDependenciesPkg>();
    List<AbstractDependenciesPkg> all = new ArrayList<AbstractDependenciesPkg>();

    // find all AbstractDependenciesPkg in the resource set.
    Resource res = pkg_p.eResource();
    if (res != null) {
      ResourceSet rs = res.getResourceSet();
      for (Iterator<Notifier> it = rs.getAllContents(); it.hasNext();) {
        Notifier next = it.next();
        if (next instanceof AbstractDependenciesPkg) {
          all.add(((AbstractDependenciesPkg) next));
        }
      }

      for (AbstractDependenciesPkg current : all) {
        for (AbstractDependenciesPkg dependency : AbstractDependenciesPkgExt.getDependencies(current)) {
          if (dependency == pkg_p) {
            result.add(current);
          }
        }
      }
    }
    return result;
  }

  private static void buildGraphOfDependencies(AbstractDependenciesPkg sourcePackage,
      Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> graphOfDependencies) {
    Collection<AbstractDependenciesPkg> sourcePackageDependencies = getDependencies(sourcePackage); // dependencies of the current package and all and the dependencies of its ancestors
    EObject itsContainer = sourcePackage.eContainer();
    // add the dependencies of the ancestors
    while (itsContainer instanceof AbstractDependenciesPkg) {
      sourcePackageDependencies.addAll(getDependencies((AbstractDependenciesPkg) itsContainer));
      itsContainer = itsContainer.eContainer();
    }
    graphOfDependencies.put(sourcePackage, sourcePackageDependencies);
    // parse dependent packages
    for (AbstractDependenciesPkg aDependentPkg : sourcePackageDependencies) {
      if (!graphOfDependencies.containsKey(aDependentPkg)) {
        buildGraphOfDependencies(aDependentPkg, graphOfDependencies);
      }
    }
  }

  /**
   * @param sourcePackage
   * @param targetPackage
   * @return test if we add a dependency between sourcePackage and targetPackage it does not create any cycle
   */
  public static boolean isADependencyAvailable(AbstractDependenciesPkg sourcePackage, AbstractDependenciesPkg targetPackage) {
    if (EcoreUtil.equals(sourcePackage, targetPackage) || EcoreUtil.isAncestor(sourcePackage, targetPackage)) {
      return true;
    }
    if (EcoreUtil.isAncestor(targetPackage, sourcePackage)) {
      return false;
    }
    Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> graphOfDependencies =
        new HashMap<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>>();
    // construction of the dependency graph from the linked element
    buildGraphOfDependencies(targetPackage, graphOfDependencies);
    // return false if the source element or its parents are in the dependency graph.
    for (AbstractDependenciesPkg aPackage : graphOfDependencies.keySet()) {
      if (EcoreUtil.isAncestor(aPackage, sourcePackage) || aPackage.equals(sourcePackage)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param root_p a systemEngineering
   * @return all InterfacePkgs and DataPkgs in the systemEngineering
   */
  public static Collection<AbstractDependenciesPkg> getAllPackages(SystemEngineering root_p) {
    Collection<AbstractDependenciesPkg> returnedList = new ArrayList<AbstractDependenciesPkg>();
    PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(root_p);
    returnedList.addAll(DataPkgExt.getAllDataPkgs(pa));
    returnedList.addAll(InterfacePkgExt.getAllInterfacePkgs(pa));
    return returnedList;
  }

  /**
   * Find 'eobject_p's first AbstractDependenciesPkg container. If this container is not null, adds the eobject_p to the collection stored as the value for the
   * container. Lazily installs a new collection if none exists for the container.
   * @param result_p
   * @param eobject_p
   */
  static void checkDependenciesAndAddToResult(Map<AbstractDependenciesPkg, Collection<EObject>> result_p, EObject eobject_p) {
    if (null != eobject_p) {
      AbstractDependenciesPkg adp = (AbstractDependenciesPkg) EcoreUtil2.getFirstContainer(eobject_p, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      if (adp != null) {
        if (!result_p.containsKey(adp)) {
          Set<EObject> set = new HashSet<EObject>();
          result_p.put(adp, set);
        }
        result_p.get(adp).add(eobject_p);
      }
    }
  }

}
