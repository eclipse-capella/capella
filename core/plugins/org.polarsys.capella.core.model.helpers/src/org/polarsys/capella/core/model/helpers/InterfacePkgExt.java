/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 * InterfacePkg helpers
 */
public class InterfacePkgExt {

  /**
   * Gets the root component of the current interface package
   * @param interfacePkg the interface package
   * @return the component
   */
  static public Component getRootComponent(InterfacePkg interfacePkg) {
    Component comp = null;
    if (null != interfacePkg) {
      Object container = interfacePkg.eContainer();

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
   * @param interfacePkg the InterfacePkg
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(InterfacePkg interfacePkg) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != interfacePkg) {
      BlockArchitecture compArch = getRootBlockArchitecture(interfacePkg);
      if (null != compArch) {
        list.addAll(getAllInterfaces(compArch.getOwnedInterfacePkg()));
        list.addAll(getOwnedInterfacesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(interfacePkg);
      if (null != parentComp) {
        list.addAll(getAllInterfaces(parentComp.getOwnedInterfacePkg()));
        list.addAll(getOwnedInterfacesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces from the parent component and its hierarchy
   * @param component the Parent Component
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromComponentParent(Component component) {
    return getOwnedInterfacesFromComponentParent(component, false);
  }

  static public List<Interface> getOwnedInterfacesFromComponentParent(Component component, boolean isLayerVisibilityRequired) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = component.eContainer();
    if (isLayerVisibilityRequired) {
      if (container instanceof SystemComponent && !((SystemComponent) container).isActor()) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      list.addAll(getAllInterfaces(((Component) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired));
    } else if (container instanceof BlockArchitecture) {
      list.addAll(getAllInterfaces(((BlockArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromBlockArchitectureParent((BlockArchitecture) container, isLayerVisibilityRequired));
    }
    return list;
  }

  /**
   * Gets all the interfaces from the parent component architecture and its hierarchy
   * @param compArch the Parent ComponentArchitecture
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromComponentArchitectureParent(ComponentArchitecture compArch) {
    return getOwnedInterfacesFromComponentArchitectureParent(compArch, false);
  }

  /**
   * Gets all the interfaces from the parent block architecture and its hierarchy
   * @param compArch the Parent BlockArchitecture
   * @return list of interfaces
   */
  static public List<Interface> getOwnedInterfacesFromBlockArchitectureParent(BlockArchitecture compArch) {
    return getOwnedInterfacesFromBlockArchitectureParent(compArch, false);
  }

  static public List<Interface> getOwnedInterfacesFromComponentArchitectureParent(ComponentArchitecture compArch, boolean isLayerVisibilityRequired) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = compArch.eContainer();
    if (isLayerVisibilityRequired) {
      if (container instanceof SystemComponent && !((SystemComponent) container).isActor()) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        list.addAll(getAllInterfaces(((LogicalComponent) container).getOwnedInterfacePkg()));
      }
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired));
    } else if (container instanceof ComponentArchitecture) {
      list.addAll(getAllInterfaces(((ComponentArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentArchitectureParent((ComponentArchitecture) container, isLayerVisibilityRequired));
    }
    return list;
  }

  static public List<Interface> getOwnedInterfacesFromBlockArchitectureParent(BlockArchitecture compArch, boolean isLayerVisibilityRequired) {
    List<Interface> list = new ArrayList<Interface>(1);
    Object container = compArch.eContainer();
    if (isLayerVisibilityRequired) {
      if (container instanceof SystemComponent && !((SystemComponent) container).isActor()) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
    }
    if (container instanceof Component) {
      list.addAll(getAllInterfaces(((Component) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromComponentParent((Component) container, isLayerVisibilityRequired));
    } else if (container instanceof BlockArchitecture) {
      list.addAll(getAllInterfaces(((BlockArchitecture) container).getOwnedInterfacePkg()));
      list.addAll(getOwnedInterfacesFromBlockArchitectureParent((BlockArchitecture) container, isLayerVisibilityRequired));
    }
    return list;
  }

  /**
   * Gets the root component architecture of the current interface package
   * @param interfacePkg the interface package
   * @return the component architecture
   */
  static public ComponentArchitecture getRootComponentArchitecture(InterfacePkg interfacePkg) {
    ComponentArchitecture compArchitecture = null;
    if (null != interfacePkg) {
      Object container = interfacePkg.eContainer();

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
   * @param modelElement : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
    BlockArchitecture arch = null;
    if (modelElement != null) {
      EObject container = modelElement.eContainer();
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
   * @param interfacePkg the interface package
   * @return the interface package
   */
  static public InterfacePkg getRootInterfacePkg(InterfacePkg interfacePkg) {
    if (interfacePkg.eContainer() instanceof InterfacePkg) {
      return getRootInterfacePkg((InterfacePkg) interfacePkg.eContainer());
    }
    return interfacePkg;
  }

  /**
   * Gets recursively all the interfaces in the interface package
   * @param interfacePkg the interface package
   * @return list of all the interfaces
   */
  static public List<Interface> getAllInterfaces(InterfacePkg interfacePkg) {
    List<Interface> interfaceList = new ArrayList<Interface>();

    if (interfacePkg != null) {
      interfaceList.addAll(interfacePkg.getOwnedInterfaces());
      for (InterfacePkg subInterfacePkg : interfacePkg.getOwnedInterfacePkgs()) {
        interfaceList.addAll(getAllInterfaces(subInterfacePkg));
      }
    }

    return interfaceList;
  }

  /**
   * Gets all the interfaces in <code>interfacePkg</code> and all its sub packages. Checks whether <code>component</code> already uses the interface, if
   * used flag is true, or checks if <code>component</code> already implements this interface.
   * @param interfacePkg the Interface Package
   * @param component the component to be checked for use or implementation
   * @param usedFlag flag for "used" or "implemented"
   * @return list of interfaces
   */
  static public List<Interface> getAllInterfacesFiltered(InterfacePkg interfacePkg, Component component, boolean usedFlag) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    if (interfacePkg != null) {
      for (Interface inter : interfacePkg.getOwnedInterfaces()) {
        if (null != component) {
          if (usedFlag) {
            if (ComponentExt.isUsingInterface(component, inter)) {
              continue;
            }
          } else {
            if (ComponentExt.isImplementingInterface(component, inter)) {
              continue;
            }
          }
        }
        interfaceList.add(inter);
      }
      for (InterfacePkg subInterfacePkg : interfacePkg.getOwnedInterfacePkgs()) {
        interfaceList.addAll(getAllInterfacesFiltered(subInterfacePkg, component, usedFlag));
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
   * @param interfacePkg current Interface Package
   * @return recursively all sub Interface Packages of interfacePkg
   */
  public static List<InterfacePkg> getRecursiveSubInterfacePkgs(InterfacePkg interfacePkg) {
    List<InterfacePkg> returnedList = new ArrayList<InterfacePkg>();
    returnedList.addAll(interfacePkg.getOwnedInterfacePkgs());
    for (InterfacePkg aSubPkg : interfacePkg.getOwnedInterfacePkgs()) {
      returnedList.addAll(getRecursiveSubInterfacePkgs(aSubPkg));
    }
    return returnedList;
  }

  /**
   * @see #getInterfacePkgDependenciesHierarchy(InterfacePkg, int)
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getInterfacePkgDependenciesHierarchy2(InterfacePkg interfacePkg) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result =
        new HashMap<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>>();

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> tmp = null;

    for (Interface anInterface : interfacePkg.getOwnedInterfaces()) {

      tmp = InterfaceExt.getInterfaceDependencies2(anInterface);

      for (Map.Entry<AbstractDependenciesPkg,Collection<Couple<EObject,Collection<EObject>>>> entry : tmp.entrySet()) {
        if (!result.containsKey(entry.getKey())) {
          Collection<Couple<EObject, Collection<EObject>>> col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result.put(entry.getKey(), col);
        }
        result.get(entry.getKey()).addAll(entry.getValue());
      }
    }

    // ExchangeItem
    for (AbstractExchangeItem anExchangeItem : interfacePkg.getOwnedExchangeItems()) {
      addToResultMap(anExchangeItem, ExchangeItemExt.getExchangeItemDependencies2(anExchangeItem), result);
    }

    // sub dependencies
    for (InterfacePkg aSubPkg : interfacePkg.getOwnedInterfacePkgs()) {
      tmp = getInterfacePkgDependenciesHierarchy2(aSubPkg);
      for (Map.Entry<AbstractDependenciesPkg,Collection<Couple<EObject,Collection<EObject>>>> entry : tmp.entrySet()) {
        if (result.containsKey(entry.getKey())) {
          result.get(entry.getKey()).addAll(entry.getValue());
        } else {
          result.put(entry.getKey(), entry.getValue());
        }
      }
    }

    return result;
  }

  /** for internal use */
  private static void addToResultMap(EObject tgt, Map<AbstractDependenciesPkg, Collection<EObject>> map,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result) {

    Couple<EObject, Collection<EObject>> couple = null;
    Set<Couple<EObject, Collection<EObject>>> col = null;

    if (null != map) {
      for (Map.Entry<AbstractDependenciesPkg,Collection<EObject>> entry : map.entrySet()) {

        if (!result.containsKey(entry.getKey())) {
          col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result.put(entry.getKey(), col);
        }

        couple = new Couple<EObject, Collection<EObject>>(tgt, entry.getValue());
        result.get(entry.getKey()).add(couple);
      }
    }
  }

  private static Collection<AbstractDependenciesPkg> getInterfacePkgDependenciesHierarchy(InterfacePkg interfacePkg, int hierarchy) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>(); // dependencies
    Collection<AbstractDependenciesPkg> dependenciesHierarchy = new HashSet<AbstractDependenciesPkg>(); // dependencies of interfacePkg ancestors

    for (Interface anInterface : interfacePkg.getOwnedInterfaces()) {
      dependencies.addAll(InterfaceExt.getInterfaceDependencies(anInterface));
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : interfacePkg.getOwnedExchangeItems()) {
      dependencies.addAll(ExchangeItemExt.getExchangeItemDependencies(anExchangeItem));
    }
    // Retrieving the dependencies for the ancestors.
    for (AbstractDependenciesPkg aPackage : dependencies) {
      int i = hierarchy;
      AbstractDependenciesPkg dependentPackage = aPackage;
      AbstractDependenciesPkg currentPackage = interfacePkg;
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
    for (InterfacePkg aSubPkg : interfacePkg.getOwnedInterfacePkgs()) {
      dependenciesHierarchy.addAll(getInterfacePkgDependenciesHierarchy(aSubPkg, hierarchy + 1));
    }
    return dependenciesHierarchy;
  }

  /**
   * @param interfacePkg
   * @return all the packages that depends on interfacePkg
   */
  public static Collection<AbstractDependenciesPkg> getInterfacePkgDependencies(InterfacePkg interfacePkg) {
    Collection<AbstractDependenciesPkg> returnedDependencies = new HashSet<AbstractDependenciesPkg>();
    if (interfacePkg.eContainer() instanceof InterfacePkg) {
      return getInterfacePkgDependenciesHierarchy(interfacePkg, 0);
    }
    for (AbstractDependenciesPkg aDependentPackage : getInterfacePkgDependenciesHierarchy(interfacePkg, 0)) {
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
   * @param sourceElement
   * @return
   */
  public static boolean isInInterfacePkg(AbstractExchangeItem sourceElement) {
    EObject container = sourceElement.eContainer();
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
	 * @param src
	 * @param tar
	 * @return
	 */
	public static boolean isPrimitiveDependency(InterfacePkg src,
			AbstractDependenciesPkg tar) {
		for (Interface anInterface : src.getOwnedInterfaces()) {
			if (InterfaceExt.getInterfaceDependencies(anInterface).contains(
					tar))
				return true;
		}
		// ExchangeItem
		for (ExchangeItem anExchangeItem : src.getOwnedExchangeItems()) {
			if (ExchangeItemExt.getExchangeItemDependencies(anExchangeItem)
					.contains(tar))
				return true;
		}

		return false;
	}

	/**
	 * Get all the dependencies of a package, including ancestor's dependencies
	 * @param interfacePkg
	 * @return
	 */
	public static Collection<AbstractDependenciesPkg> getInterfacePkgDependenciesHierarchy(
			InterfacePkg interfacePkg) {
		Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>(); // dependencies

		for (Interface anInterface : interfacePkg.getOwnedInterfaces()) {
			dependencies.addAll(InterfaceExt
					.getInterfaceDependencies(anInterface));
		}
		// ExchangeItem
		for (ExchangeItem anExchangeItem : interfacePkg
				.getOwnedExchangeItems()) {
			dependencies.addAll(ExchangeItemExt
					.getExchangeItemDependencies(anExchangeItem));
		}
		// Retrieving the dependencies for the ancestors.
		for (AbstractDependenciesPkg aPackage : dependencies) {
			AbstractDependenciesPkg dependentPackage = aPackage;
			AbstractDependenciesPkg currentPackage = interfacePkg;
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
		for (InterfacePkg aSubPkg : interfacePkg.getOwnedInterfacePkgs()) {
			dependencies.addAll(getInterfacePkgDependenciesHierarchy(aSubPkg));
		}
		return dependencies;
	}
	
	 /**
   * @param context
   * @return all InterfacePkgs which are in current block architecture
   */
  public static Collection<InterfacePkg> getAllInterfacePkgsInCurrentBlockArchitectures(EObject context) {
    Set<InterfacePkg> result = new HashSet<>();
    BlockArchitecture aArchitecture = BlockArchitectureExt.getRootBlockArchitecture(context);
    for(EObject intefacePkg :  EObjectExt.getAll(aArchitecture, CsPackage.Literals.INTERFACE_PKG)){
      result.add((InterfacePkg)intefacePkg);
    }
    return result;
  }
}
