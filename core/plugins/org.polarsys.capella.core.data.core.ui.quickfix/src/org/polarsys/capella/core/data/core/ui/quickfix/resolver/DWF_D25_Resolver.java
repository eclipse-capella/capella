/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.utils.graph.BasicDirectedGraph;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.common.utils.graph.SCCSearch;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.validation.MDCHK_SystemEngineering_Cycle_Dependencies;
import org.polarsys.capella.core.data.core.ui.quickfix.messages.CoreQuickFixMessages;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.Messages;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.EObjectNavigatorDialog;

/**
 * Show details for package dependency cycles.
 */
public class DWF_D25_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {

    List<EObject> tgts = getModelElements(marker); // The root system Engineering as first element

    SystemEngineering se = (SystemEngineering) tgts.get(0);
    Couple<IDirectedGraph<EObject>, Collection<List<EObject>>> result = computeCycles(se);

    if (result == null) {
      return;
    }

    final IDirectedGraph<EObject> graph = result.getKey();
    final Collection<List<EObject>> cycles = result.getValue();

    // show the dialog
    if (cycles != null && cycles.iterator().hasNext()) {
      EObjectNavigatorDialog dialog = new EObjectNavigatorDialog(cycles.iterator().next(),
          CoreQuickFixMessages.cycle_details_dialog_title, CoreQuickFixMessages.cycle_details_dialog_message,
          CoreQuickFixMessages.cycle_details_dialog_combo_lbl,
          CoreQuickFixMessages.cycle_details_dialog_combo_cycle_prefix);

      dialog.setCycles(cycles);
      dialog.setContextMenuManagerFiller(new AbstractContextMenuFiller() {

        @Override
        public void fillMenuManager(IMenuManager contextMenuManager, final ISelection selection) {
          final EObject selectedEObject = (EObject) ((TreeSelection) selection).iterator().next();

          //
          // Show elements referenced by selection...
          //
          if (!(selectedEObject instanceof AbstractDependenciesPkg)) {
            Collection<EObject> successors = new ArrayList<EObject>();
            for (Iterator<EObject> it = graph.getSucessors(selectedEObject); it.hasNext();) {
              EObject referenced = it.next();
              if (referenced instanceof AbstractDependenciesPkg) {
                continue; // this may happen because of the way we built up the dependency graph
              }
              successors.add(referenced);
            }

            for (EObject referenced : successors) {
              IAction action = LocateInCapellaExplorerAction.createLocateTowards(referenced, Messages.LocateInCapellaExplorerAction_GoToReferencedElement, false);
              if (action.isEnabled()) {
                contextMenuManager.add(action);
              }
            }
          }
        }
      });

      dialog.open();
    }
    return;
  }

  @Override
  protected String[] getResolvableRuleIds() {
    return noRuleIds;
  }

  /**
   * Helper to support the notion that each package depends on all its subpackages.
   * 
   * @param current
   */
  private void addSubpackageDependencies(BasicDirectedGraph<? super EObject> graph, AbstractDependenciesPkg current) {
    List<? extends AbstractDependenciesPkg> subpackages = null;
    if (current instanceof DataPkg) {
      subpackages = ((DataPkg) current).getOwnedDataPkgs();
    } else if (current instanceof InterfacePkg) {
      subpackages = ((InterfacePkg) current).getOwnedInterfacePkgs();
    }
    if (subpackages != null) {
      for (AbstractDependenciesPkg sub : subpackages) {
        graph.addEdge(current, sub);
        addSubpackageDependencies(graph, sub);
      }
    }
  }

  /** for internal use */
  private AbstractDependenciesPkg getPkg(EObject eobject) {

    if (eobject instanceof AbstractDependenciesPkg) {
      return (AbstractDependenciesPkg) eobject;
    }

    AbstractDependenciesPkg adp = null;

    if (null != eobject) {
      adp = (AbstractDependenciesPkg) EcoreUtil2.getFirstContainer(eobject,
          CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
    }

    return adp;
  }

  /**
   * @param cycle
   */
  @SuppressWarnings("unused")
  private void debugPrintCycle(List<EObject> cycle) {
    StringBuilder builder = new StringBuilder();
    for (EObject e : cycle) {
      builder.append(((NamedElement) e).getName());
      builder.append(":" + getPkg(e).getName() + ", "); //$NON-NLS-1$ //$NON-NLS-2$
    }
    System.out.println(builder);
  }

  public Couple<IDirectedGraph<EObject>, Collection<List<EObject>>> computeCycles(SystemEngineering se) {
    Map<AbstractDependenciesPkg, Collection<AbstractDependenciesPkg>> pkgDeps = MDCHK_SystemEngineering_Cycle_Dependencies
        .getCycleOfDependencies(se);
    if (pkgDeps == null) {
      return null;
    }

    // { package P => dependencyInductions }
    // dependencyInduction: someEObject A => list of dependent objects inside P
    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> dependencyDescriptors = null;

    // create an extended dependency graph to compute cycles
    final BasicDirectedGraph<EObject> graph = new BasicDirectedGraph<EObject>();

    EClass eclass = null;
    for (AbstractDependenciesPkg current : pkgDeps.keySet()) {

      // each package implicitly depends per definition on all its subpackages
      addSubpackageDependencies(graph, current);

      eclass = current.eClass();
      if (InformationPackage.Literals.DATA_PKG.isSuperTypeOf(eclass)) {
        dependencyDescriptors = DataPkgExt.getDataPkgDependenciesHierarchy2((DataPkg) current);
      } else if (CsPackage.Literals.INTERFACE_PKG.isSuperTypeOf(eclass)) {
        dependencyDescriptors = InterfacePkgExt.getInterfacePkgDependenciesHierarchy2((InterfacePkg) current);
      }
      if (dependencyDescriptors != null) {
        for (AbstractDependenciesPkg pkgDependency : dependencyDescriptors.keySet()) {
          Collection<Couple<EObject, Collection<EObject>>> objectDependencies = dependencyDescriptors
              .get(pkgDependency);
          // for every dependency A->B add the following edges to the graph:
          // - A->B itself
          // - x->A for _each_ AbstractDependencyPkg x in the containment hierarchy of A
          // to express an implicit dependency from A's package to A
          // - B->y for _each_ AbstractDependencyPkg in B's containment hierarchy,
          // to express an implicit dependency from B to its package hierarchy
          for (Couple<EObject, Collection<EObject>> objectDependency : objectDependencies) {
            EObject dependent = objectDependency.getKey();
            EObject container = dependent.eContainer();
            while (container != null) {
              if (container instanceof AbstractDependenciesPkg) {
                graph.addEdge(container, dependent); // x->A
                container = null;
              } else {
                container = container.eContainer();
              }
            }
            for (EObject dependee : objectDependency.getValue()) {
              AbstractDependenciesPkg pkg = pkgDependency;
              while (pkg != null) {
                graph.addEdge(dependee, pkg); // B->y
                if (pkg.eContainer() instanceof AbstractDependenciesPkg) {
                  pkg = (AbstractDependenciesPkg) pkg.eContainer();
                } else {
                  pkg = null;
                }
              }
              graph.addEdge(dependent, dependee); // A->B
            }
          }
        }
      }
    }

    Collection<List<EObject>> scc = new SCCSearch<EObject>().findSCC(graph, false);

    // Not done yet: Because of the artificial graph expansion above (adding package dependencies),
    // the result may contain cycles in which we're not really interested:

    // 1) Remove cycles that cover only a single package.
    for (Iterator<List<EObject>> l = scc.iterator(); l.hasNext();) {
      List<EObject> cycle = l.next();
      // debugPrintCycle(cycle);
      AbstractDependenciesPkg ap = null;
      boolean exclude = true;
      for (EObject o : cycle) {
        AbstractDependenciesPkg oap = getPkg(o);
        if (ap == null) {
          ap = oap;
        } else if (ap != oap) {
          exclude = false;
          break;
        }
      }
      if (exclude) {
        l.remove();
      }
    }

    // 2) Remove cycles whose affected packages form a linear hierarchy
    // ,e.g. a cycle between a package a subpackage
    for (Iterator<List<EObject>> l = scc.iterator(); l.hasNext();) {
      List<EObject> cycle = l.next();
      AbstractDependenciesPkg mostSpecificPkg = null;
      boolean remove = true;
      for (EObject elem : cycle) {
        AbstractDependenciesPkg p = getPkg(elem);
        if (mostSpecificPkg != null) {
          if (mostSpecificPkg != p) {
            if (EcoreUtil.isAncestor(mostSpecificPkg, p)) {
              mostSpecificPkg = p;
            } else if (!EcoreUtil.isAncestor(p, mostSpecificPkg)) {
              remove = false;
              break;
            }
          }
        } else {
          mostSpecificPkg = p;
        }
      }
      if (remove) {
        l.remove();
      }
    }

    // 3) Remove AbstractDependenciesPkg elements from all cycles,
    // we're only interested in the elements that _cause_ the cycles
    for (List<EObject> cycle : scc) {
      for (Iterator<EObject> elems = cycle.iterator(); elems.hasNext();) {
        EObject elem = elems.next();
        if (elem instanceof AbstractDependenciesPkg) {
          elems.remove();
        }
      }
    }

    return new Couple<IDirectedGraph<EObject>, Collection<List<EObject>>>(graph, scc);
  }

}
