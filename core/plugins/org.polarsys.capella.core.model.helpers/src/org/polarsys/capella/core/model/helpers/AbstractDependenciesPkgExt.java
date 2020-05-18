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
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;

/**
 */
public class AbstractDependenciesPkgExt {

  private AbstractDependenciesPkgExt() {
    //  To hide the implicit public one.
  }
  
  /**
   * @param pkg
   * @return all packages that pkg directly depends on
   * @deprecated please use getDependencies() instead
   */
  @Deprecated
  public static Collection<AbstractDependenciesPkg> getDependentPackages(AbstractDependenciesPkg pkg) {
    return getDependencies(pkg);
  }

	/**
	* Get all the dependencies of a package, including ancestor's dependencies
	* @param pkg
	* @return
	*/
	public static Collection<AbstractDependenciesPkg> getDependencies2(
			AbstractDependenciesPkg pkg) {
		Collection<AbstractDependenciesPkg> dependencies = new HashSet<>();
		if (pkg instanceof DataPkg) {
			dependencies.addAll(DataPkgExt
				.getDataPkgDependenciesHierarchy((DataPkg) pkg));
		}
		if (pkg instanceof InterfacePkg) {
			dependencies.addAll(InterfacePkgExt
				.getInterfacePkgDependenciesHierarchy((InterfacePkg) pkg));
		}
		return dependencies;
	}
	

  /**
   * Search direct dependencies of a package.
   * @param pkg an AbstractDependenciesPkg
   * @return The dependencies of pkg, i.e. all packages that pkg directly depends on
   */
  public static Collection<AbstractDependenciesPkg> getDependencies(AbstractDependenciesPkg pkg) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<>();
    if (pkg instanceof DataPkg) {
      dependencies.addAll(DataPkgExt.getDataPkgDependencies((DataPkg) pkg));
    }
    if (pkg instanceof InterfacePkg) {
      dependencies.addAll(InterfacePkgExt.getInterfacePkgDependencies((InterfacePkg) pkg));
    }
    return dependencies;
  }

  /**
   * Search for packages that directly depend on the parameter package, i.e the parameter's inverse dependencies. The scope of the search is the parameter's
   * entire ResourceSet.
   * @param pkg an AbstractDependenciesPkg
   * @return The inverse dependencies of pkg, i.e. all packages that depend directly on pkg
   */
  public static Collection<AbstractDependenciesPkg> getInverseDependencies(AbstractDependenciesPkg pkg) {

    List<AbstractDependenciesPkg> result = new ArrayList<>();
    List<AbstractDependenciesPkg> all = new ArrayList<>();

    // find all AbstractDependenciesPkg in the resource set.
    Resource res = pkg.eResource();
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
          if (dependency == pkg) {
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
  public static boolean isADependencyAvailable(AbstractDependenciesPkg sourcePackage,
      AbstractDependenciesPkg targetPackage) {
    if (EcoreUtil.equals(sourcePackage, targetPackage) || EcoreUtil.isAncestor(sourcePackage, targetPackage)) {
      return true;
    }
    if (EcoreUtil.isAncestor(targetPackage, sourcePackage)) {
      return false;
    }
    Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> graphOfDependencies = new HashMap<>();
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
   * @param root a systemEngineering
   * @return all InterfacePkgs and DataPkgs in the systemEngineering
   */
  public static Collection<AbstractDependenciesPkg> getAllPackages(SystemEngineering root) {
    Set<AbstractDependenciesPkg> returnedList = new HashSet<>();
    for(ModellingArchitecture architecture : root.getOwnedArchitectures()) {
      returnedList.addAll(DataPkgExt.getAllDataPkgsInCurrentBlockArchitectures(architecture));
      returnedList.addAll(InterfacePkgExt.getAllInterfacePkgsInCurrentBlockArchitectures(architecture));      
    }
    return returnedList;
  }

  /**
   * Find 'eobject's first AbstractDependenciesPkg container. If this container is not null, adds the eobject to the collection stored as the value for the
   * container. Lazily installs a new collection if none exists for the container.
   * @param result
   * @param eobject
   */
  static void checkDependenciesAndAddToResult(Map<AbstractDependenciesPkg, Collection<EObject>> result,
      EObject eobject) {
    if (null != eobject) {
      AbstractDependenciesPkg adp = (AbstractDependenciesPkg) EcoreUtil2.getFirstContainer(eobject,
          CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      if (adp != null) {
        if (!result.containsKey(adp)) {
          Set<EObject> set = new HashSet<>();
          result.put(adp, set);
        }
        result.get(adp).add(eobject);
      }
    }
  }
}
