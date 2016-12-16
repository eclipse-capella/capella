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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 * InterfacePkg helpers
 */
public class InterfacePkgExt {

  /**
   * Gets the root component of the current interface package
   * @param interfacePkg_p the interface package
   * @return the component
   */
  static public Component getRootComponent(InterfacePkg interfacePkg_p) {
    Component comp = null;
    if (null != interfacePkg_p) {
      Object container = interfacePkg_p.eContainer();

      if (container instanceof InterfacePkg) {
        comp = getRootComponent((InterfacePkg) container);
      } else if (container instanceof Structure) {
        comp = StructureExt.getRootComponent((Structure) container);
      } else if (container instanceof Component) {
        comp = (Component) container;
      }
    }
    return comp;
  }

  /**
   * Gets all the interfaces from the Parent Hierarchy of the interface package
   * @param interfacePkg_p the InterfacePkg
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(InterfacePkg interfacePkg_p) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != interfacePkg_p) {
      BlockArchitecture compArch = getRootBlockArchitecture(interfacePkg_p);
      if (null != compArch) {
        list.addAll(getAllInterfaces(compArch.getOwnedInterfacePkg()));
        list.addAll(getOwnedInterfacesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(interfacePkg_p);
      if (null != parentComp) {
        list.addAll(getAllInterfaces(parentComp.getOwnedInterfacePkg()));
        list.addAll(getOwnedInterfacesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces from the parent component and its hierarchy
   * @param component_p the Parent Component
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromComponentParent(Component component_p) {
    return getOwnedInterfacesFromComponentParent(component_p, false);
  }

  static public List<Interface> getOwnedInterfacesFromComponentParent(Component component_p, boolean isLayerVisibilityRequired_p) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = component_p.eContainer();
    if (isLayerVisibilityRequired_p) {
      if (container instanceof System) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      list.addAll(getAllInterfaces(((Component) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired_p));
    } else if (container instanceof BlockArchitecture) {
      list.addAll(getAllInterfaces(((BlockArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromBlockArchitectureParent((BlockArchitecture) container, isLayerVisibilityRequired_p));
    }
    return list;
  }

  /**
   * Gets all the interfaces from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromComponentArchitectureParent(ComponentArchitecture compArch_p) {
    return getOwnedInterfacesFromComponentArchitectureParent(compArch_p, false);
  }

  /**
   * Gets all the interfaces from the parent block architecture and its hierarchy
   * @param compArch_p the Parent BlockArchitecture
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromBlockArchitectureParent(BlockArchitecture compArch_p) {
    return getOwnedInterfacesFromBlockArchitectureParent(compArch_p, false);
  }

  static public List<Interface> getOwnedInterfacesFromComponentArchitectureParent(ComponentArchitecture compArch_p, boolean isLayerVisibilityRequired_p) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = compArch_p.eContainer();
    if (isLayerVisibilityRequired_p) {
      if (container instanceof System) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        list.addAll(getAllInterfaces(((LogicalComponent) container).getOwnedInterfacePkg()));
      }
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired_p));
    } else if (container instanceof ComponentArchitecture) {
      list.addAll(getAllInterfaces(((ComponentArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentArchitectureParent((ComponentArchitecture) container, isLayerVisibilityRequired_p));
    }
    return list;
  }

  static public List<Interface> getOwnedInterfacesFromBlockArchitectureParent(BlockArchitecture compArch_p, boolean isLayerVisibilityRequired_p) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = compArch_p.eContainer();
    if (isLayerVisibilityRequired_p) {
      if (container instanceof System) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      list.addAll(getAllInterfaces(((Component) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired_p));
    } else if (container instanceof BlockArchitecture) {
      list.addAll(getAllInterfaces(((BlockArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromBlockArchitectureParent((BlockArchitecture) container, isLayerVisibilityRequired_p));
    }
    return list;
  }

  /**
   * Gets the root component architecture of the current interface package
   * @param interfacePkg_p the interface package
   * @return the component architecture
   */
  static public ComponentArchitecture getRootComponentArchitecture(InterfacePkg interfacePkg_p) {
    ComponentArchitecture compArchitecture = null;
    if (null != interfacePkg_p) {
      Object container = interfacePkg_p.eContainer();

      if (container instanceof ComponentArchitecture) {
        compArchitecture = (ComponentArchitecture) container;
      } else if (container instanceof InterfacePkg) {
        compArchitecture = InterfacePkgExt.getRootComponentArchitecture((InterfacePkg) container);
      } else if (container instanceof Structure) {
        compArchitecture = StructureExt.getRootComponentArchitecture((Structure) container);
      } else if (container instanceof Component) {
        compArchitecture = ComponentExt.getRootComponentArchitecture((Component) container);
      }
    }
    return compArchitecture;
  }

  /**
   * @param modelElement_p : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement_p) {
    BlockArchitecture arch = null;
    if (modelElement_p != null) {
      EObject container = modelElement_p.eContainer();
      if (container instanceof BlockArchitecture) {
        return (BlockArchitecture) container;
      } else if (container instanceof Component) {
        arch = ComponentExt.getRootBlockArchitecture((Component) container);
      } else if (container instanceof Structure) {
        arch = StructureExt.getRootBlockArchitecture((Structure) container);
      } else if (container instanceof Classifier) {
        arch = ClassifierExt.getRootBlockArchitecture((Classifier) container);
      } else {
        EObject container2 = container.eContainer();
        if (null != container2) {
          arch = getRootBlockArchitecture((ModelElement) container2);
        }
      }
    }
    return arch;
  }

  /**
   * Gets the Root Interface Package of the signal package
   * @param interfacePkg_p the interface package
   * @return the interface package
   */
  static public InterfacePkg getRootInterfacePkg(InterfacePkg interfacePkg_p) {
    if (interfacePkg_p.eContainer() instanceof InterfacePkg) {
      return getRootInterfacePkg((InterfacePkg) interfacePkg_p.eContainer());
    }
    return interfacePkg_p;
  }

  /**
   * Gets recursively all the interfaces in the interface package
   * @param interfacePkg_p the interface package
   * @return list of all the interfaces
   */
  static public List<Interface> getAllInterfaces(InterfacePkg interfacePkg_p) {
    List<Interface> interfaceList = new ArrayList<Interface>();

    if (interfacePkg_p != null) {
      interfaceList.addAll(interfacePkg_p.getOwnedInterfaces());
      for (InterfacePkg subInterfacePkg : interfacePkg_p.getOwnedInterfacePkgs()) {
        interfaceList.addAll(getAllInterfaces(subInterfacePkg));
      }
    }

    return interfaceList;
  }

  /**
   * Gets all the interfaces in <code>interfacePkg_p</code> and all its sub packages. Checks whether <code>component_p</code> already uses the interface, if
   * used flag is true, or checks if <code>component_p</code> already implements this interface.
   * @param interfacePkg_p the Interface Package
   * @param component_p the component to be checked for use or implementation
   * @param usedFlag_p flag for "used" or "implemented"
   * @return list of interfaces
   */
  static public List<Interface> getAllInterfacesFiltered(InterfacePkg interfacePkg_p, Component component_p, boolean usedFlag_p) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    if (interfacePkg_p != null) {
      for (Interface inter : interfacePkg_p.getOwnedInterfaces()) {
        if (null != component_p) {
          if (usedFlag_p) {
            if (ComponentExt.isUsingInterface(component_p, inter)) {
              continue;
            }
          } else {
            if (ComponentExt.isImplementingInterface(component_p, inter)) {
              continue;
            }
          }
        }
        interfaceList.add(inter);
      }
      for (InterfacePkg subInterfacePkg : interfacePkg_p.getOwnedInterfacePkgs()) {
        interfaceList.addAll(getAllInterfacesFiltered(subInterfacePkg, component_p, usedFlag_p));
      }
    }
    return interfaceList;
  }

  /**
   * Gets all the interfaces in <code>interfacePkg_p</code> and all its sub packages. Checks whether <code>component_p</code> already uses the interface, if
   * used flag is true, or checks if <code>component_p</code> already implements this interface.
   * @param genericPkg_p the Interface Package
   * @param component_p the component to be checked for use or implementation
   * @param usedFlag_p flag for "used" or "implemented"
   * @return list of interfaces
   */
  static public List<Interface> getAllInterfacesFiltered(GenericPkg genericPkg_p, Component component_p, boolean usedFlag_p) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    if (genericPkg_p != null) {
      GenericPkgExt.getAllInterfaces(genericPkg_p);
      for (Interface inter : GenericPkgExt.getAllInterfaces(genericPkg_p)) {
        if (null != component_p) {
          if (usedFlag_p) {
            if (ComponentExt.isUsingInterface(component_p, inter)) {
              continue;
            }
          } else {
            if (ComponentExt.isImplementingInterface(component_p, inter)) {
              continue;
            }
          }
        }
        interfaceList.add(inter);
      }
    }
    return interfaceList;
  }

  /**
   * @param context
   * @return all InterfacePkgs which are in the same architecture as context or in previous architecture
   */
  public static List<InterfacePkg> getAllInterfacePkgs(EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACE_PCKS, context);
  }

  /**
   * @param interfacePkg_p current Interface Package
   * @return recursively all sub Interface Packages of interfacePkg_p
   */
  public static List<InterfacePkg> getRecursiveSubInterfacePkgs(InterfacePkg interfacePkg_p) {
    List<InterfacePkg> returnedList = new ArrayList<InterfacePkg>();
    returnedList.addAll(interfacePkg_p.getOwnedInterfacePkgs());
    for (InterfacePkg aSubPkg : interfacePkg_p.getOwnedInterfacePkgs()) {
      returnedList.addAll(getRecursiveSubInterfacePkgs(aSubPkg));
    }
    return returnedList;
  }

  /**
   * @see #getInterfacePkgDependenciesHierarchy(InterfacePkg, int)
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getInterfacePkgDependenciesHierarchy2(InterfacePkg interfacePkg_p) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result =
        new HashMap<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>>();

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> tmp = null;

    for (Interface anInterface : interfacePkg_p.getOwnedInterfaces()) {

      tmp = InterfaceExt.getInterfaceDependencies2(anInterface);

      for (AbstractDependenciesPkg pkg : tmp.keySet()) {
        if (!result.containsKey(pkg)) {
          Collection<Couple<EObject, Collection<EObject>>> col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result.put(pkg, col);
        }
        result.get(pkg).addAll(tmp.get(pkg));
      }
    }

    // ExchangeItem
    for (AbstractExchangeItem anExchangeItem : interfacePkg_p.getOwnedExchangeItems()) {
      addToResultMap(anExchangeItem, ExchangeItemExt.getExchangeItemDependencies2(anExchangeItem), result);
    }

    // sub dependencies
    for (InterfacePkg aSubPkg : interfacePkg_p.getOwnedInterfacePkgs()) {
      tmp = getInterfacePkgDependenciesHierarchy2(aSubPkg);
      for (AbstractDependenciesPkg pkg : tmp.keySet()) {
        if (result.containsKey(pkg)) {
          result.get(pkg).addAll(tmp.get(pkg));
        } else {
          result.put(pkg, tmp.get(pkg));
        }
      }
    }

    return result;
  }

  /** for internal use */
  private static void addToResultMap(EObject tgt_p, Map<AbstractDependenciesPkg, Collection<EObject>> map_p,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result_p) {

    Couple<EObject, Collection<EObject>> couple = null;

    Set<Couple<EObject, Collection<EObject>>> col = null;

    if (null != map_p) {
      for (AbstractDependenciesPkg pkg : map_p.keySet()) {

        if (!result_p.containsKey(pkg)) {
          col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result_p.put(pkg, col);
        }

        couple = new Couple<EObject, Collection<EObject>>(tgt_p, map_p.get(pkg));
        result_p.get(pkg).add(couple);
      }

    }

    return;
  }

  private static Collection<AbstractDependenciesPkg> getInterfacePkgDependenciesHierarchy(InterfacePkg interfacePkg_p, int hierarchy) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>(); // dependencies
    Collection<AbstractDependenciesPkg> dependenciesHierarchy = new HashSet<AbstractDependenciesPkg>(); // dependencies of interfacePkg_p ancestors

    for (Interface anInterface : interfacePkg_p.getOwnedInterfaces()) {
      dependencies.addAll(InterfaceExt.getInterfaceDependencies(anInterface));
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : interfacePkg_p.getOwnedExchangeItems()) {
      dependencies.addAll(ExchangeItemExt.getExchangeItemDependencies(anExchangeItem));
    }
    // Retrieving the dependencies for the ancestors.
    for (AbstractDependenciesPkg aPackage : dependencies) {
      int i = hierarchy;
      AbstractDependenciesPkg dependentPackage = aPackage;
      AbstractDependenciesPkg currentPackage = interfacePkg_p;
      while ((i > 0) && (dependentPackage.eContainer() instanceof InterfacePkg)
             && (!(EcoreUtil.isAncestor(dependentPackage.eContainer(), currentPackage) || EcoreUtil.isAncestor(currentPackage, dependentPackage.eContainer())))) {
        dependentPackage = (AbstractDependenciesPkg) dependentPackage.eContainer();
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      while (i > 0) {
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      if (!(EcoreUtil.isAncestor(currentPackage, dependentPackage) || EcoreUtil.isAncestor(dependentPackage, currentPackage) || currentPackage
          .equals(dependentPackage))) {
        dependenciesHierarchy.add(dependentPackage);
      }
    }
    // Retrieving the dependencies of sub-packages.
    for (InterfacePkg aSubPkg : interfacePkg_p.getOwnedInterfacePkgs()) {
      dependenciesHierarchy.addAll(getInterfacePkgDependenciesHierarchy(aSubPkg, hierarchy + 1));
    }
    return dependenciesHierarchy;
  }

  /**
   * @param interfacePkg_p
   * @return all the packages that depends on interfacePkg_p
   */
  public static Collection<AbstractDependenciesPkg> getInterfacePkgDependencies(InterfacePkg interfacePkg_p) {
    Collection<AbstractDependenciesPkg> returnedDependencies = new HashSet<AbstractDependenciesPkg>();
    if (interfacePkg_p.eContainer() instanceof InterfacePkg) {
      return getInterfacePkgDependenciesHierarchy(interfacePkg_p, 0);
    }
    for (AbstractDependenciesPkg aDependentPackage : getInterfacePkgDependenciesHierarchy(interfacePkg_p, 0)) {
      AbstractDependenciesPkg currentDependentPkg = aDependentPackage;
      returnedDependencies.add(currentDependentPkg);
      while (aDependentPackage.eContainer() instanceof InterfacePkg) {
        aDependentPackage = (AbstractDependenciesPkg) aDependentPackage.eContainer();
        returnedDependencies.add(aDependentPackage);
      }
    }
    return returnedDependencies;
  }

  /**
   * @param sourceElement_p
   * @return
   */
  public static boolean isInInterfacePkg(AbstractExchangeItem sourceElement_p) {
    EObject container = sourceElement_p.eContainer();
    while (container != null) {
      if (container instanceof InterfacePkg) {
        return true;
      }
      container = container.eContainer();
    }
    return false;
  }
  
  /**
	 * Check if the dependency between an interface package and a package is primitive
	 * @param src_p
	 * @param tar_p
	 * @return
	 */
	public static boolean isPrimitiveDependency(InterfacePkg src_p,
			AbstractDependenciesPkg tar_p) {
		for (Interface anInterface : src_p.getOwnedInterfaces()) {
			if (InterfaceExt.getInterfaceDependencies(anInterface).contains(
					tar_p))
				return true;
		}
		// ExchangeItem
		for (ExchangeItem anExchangeItem : src_p.getOwnedExchangeItems()) {
			if (ExchangeItemExt.getExchangeItemDependencies(anExchangeItem)
					.contains(tar_p))
				return true;
		}

		return false;
	}

	/**
	 * Get all the dependencies of a package, including ancestor's dependencies
	 * @param interfacePkg_p
	 * @return
	 */
	public static Collection<AbstractDependenciesPkg> getInterfacePkgDependenciesHierarchy(
			InterfacePkg interfacePkg_p) {
		Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>(); // dependencies

		for (Interface anInterface : interfacePkg_p.getOwnedInterfaces()) {
			dependencies.addAll(InterfaceExt
					.getInterfaceDependencies(anInterface));
		}
		// ExchangeItem
		for (ExchangeItem anExchangeItem : interfacePkg_p
				.getOwnedExchangeItems()) {
			dependencies.addAll(ExchangeItemExt
					.getExchangeItemDependencies(anExchangeItem));
		}
		// Retrieving the dependencies for the ancestors.
		for (AbstractDependenciesPkg aPackage : dependencies) {
			AbstractDependenciesPkg dependentPackage = aPackage;
			AbstractDependenciesPkg currentPackage = interfacePkg_p;
			while ((dependentPackage instanceof AbstractDependenciesPkg)
					&& (!(EcoreUtil
							.isAncestor(dependentPackage, currentPackage) || EcoreUtil
							.isAncestor(currentPackage, dependentPackage)))) {
				if (dependentPackage.eContainer() instanceof AbstractDependenciesPkg)
				{
					dependencies.add((AbstractDependenciesPkg) dependentPackage);
					dependentPackage = (AbstractDependenciesPkg) dependentPackage
							.eContainer();
				}
				else
					break;
			}
		}
		// Retrieving the dependencies of sub-packages.
		for (InterfacePkg aSubPkg : interfacePkg_p.getOwnedInterfacePkgs()) {
			dependencies.addAll(getInterfacePkgDependenciesHierarchy(aSubPkg));
		}
		return dependencies;
	}

}
