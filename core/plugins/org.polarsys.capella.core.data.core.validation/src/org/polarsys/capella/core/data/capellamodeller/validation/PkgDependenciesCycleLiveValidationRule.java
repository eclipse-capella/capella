/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;

/**
 */
public class PkgDependenciesCycleLiveValidationRule extends AbstractPkgDependenciesCycleValidationRule {

  private Map<DependencyDescriptor, Collection<EObject>> dependencyCache = new HashMap<>();

  @Override
  protected boolean shouldValidate(IValidationContext ctx) {
    EMFEventType eType = ctx.getEventType();
    return eType == EMFEventType.ADD && getTarget(ctx) instanceof AbstractDependenciesPkg;
  }

  @Override
  protected IStatus doValidate(IValidationContext ctx) {
    IStatus status = ctx.createSuccessStatus();
    Object newValue = ctx.getFeatureNewValue();
    if (newValue instanceof Association) {
      status = doValidateNewAssociation((Association) newValue, ctx);
    }
    dependencyCache.clear();
    return status;
  }

  private IStatus doValidateNewAssociation(Association association, IValidationContext ctx) {
    Class sourceClass = AssociationExt.getSourceClass(association);
    Set<Classifier> navigableMembersClassifiers = AssociationExt.getNavigableMembersClassifiers(association);
    if (sourceClass != null && !navigableMembersClassifiers.isEmpty()) {
      List<AbstractDependenciesPkg> allPkgs = new ArrayList<>();
      AbstractDependenciesPkg srcPkg = (AbstractDependenciesPkg) getAncestor(sourceClass,
          CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      allPkgs.add(srcPkg);
      AbstractDependenciesPkg trgtPkg = (AbstractDependenciesPkg) getAncestor(
          navigableMembersClassifiers.iterator().next(), CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      allPkgs.add(trgtPkg);
      if (EcoreUtil.equals(srcPkg, trgtPkg) || EcoreUtil.isAncestor(srcPkg, trgtPkg)) {
        return ctx.createSuccessStatus();
      }
      if (EcoreUtil.isAncestor(trgtPkg, srcPkg)) {
        return ctx.createFailureStatus(toString(allPkgs));
      }
      List<List<AbstractDependenciesPkg>> detectedCycles = getInterPackageCycles(allPkgs);
      List<List<AbstractDependenciesPkg>> relevantCycles = detectedCycles.isEmpty() ? Collections.emptyList()
          : getRelevantCycles(sourceClass, detectedCycles);
      if (!relevantCycles.isEmpty()) {
        return createCycleFailureStatus(ctx, relevantCycles);
      }
    }
    return ctx.createSuccessStatus();
  }

  // Filter cycles which are not related to this new Association
  private List<List<AbstractDependenciesPkg>> getRelevantCycles(Class sourceClass,
      List<List<AbstractDependenciesPkg>> detectedCycles) {
    List<List<AbstractDependenciesPkg>> relevantCycles = new ArrayList<>();
    for (List<AbstractDependenciesPkg> cycle : detectedCycles) {
      for (Entry<DependencyDescriptor, Collection<EObject>> entry : dependencyCache.entrySet()) {
        if (entry.getKey().isRelatedTo(cycle) && entry.getValue().contains(sourceClass)) {
          relevantCycles.add(cycle);
        }
      }
    }
    return relevantCycles;
  }

  @Override
  protected Collection<AbstractDependenciesPkg> getPkgDependencies(AbstractDependenciesPkg pkgToCheck) {
    if (pkgToCheck instanceof DataPkg) {
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> dependencies = DataPkgExt
          .getDataPkgDependenciesHierarchy2((DataPkg) pkgToCheck);
      addDescriptors(pkgToCheck, dependencies);
      return dependencies.keySet();

    } else if (pkgToCheck instanceof InterfacePkg) {
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> dependencies = InterfacePkgExt
          .getInterfacePkgDependenciesHierarchy2((InterfacePkg) pkgToCheck);
      addDescriptors(pkgToCheck, dependencies);
      return dependencies.keySet();
    }
    return Collections.emptyList();
  }

  private void addDescriptors(AbstractDependenciesPkg pkgToCheck,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> dependencies) {
    for (AbstractDependenciesPkg dependentPkg : dependencies.keySet()) {
      DependencyDescriptor descriptor = new DependencyDescriptor(pkgToCheck, dependentPkg);
      Collection<EObject> cachedCollection = dependencyCache.get(descriptor);
      if (cachedCollection == null) {
        cachedCollection = new HashSet<>();
        addDependencyOrigin(dependencies, dependentPkg, cachedCollection);
        dependencyCache.put(descriptor, cachedCollection);
      } else {
        addDependencyOrigin(dependencies, dependentPkg, cachedCollection);
      }
    }
  }

  private void addDependencyOrigin(
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> dependencies,
      AbstractDependenciesPkg pkg, Collection<EObject> collection) {
    Collection<Couple<EObject, Collection<EObject>>> origin = dependencies.get(pkg);
    for (Couple<EObject, Collection<EObject>> couple : origin) {
      collection.add(couple.getKey());
    }
  }

  private EObject getAncestor(EObject context, EClass eclass) {
    return EcoreUtil2.getFirstContainer(context, eclass);
  }

  class DependencyDescriptor {

    private AbstractDependenciesPkg src;
    private AbstractDependenciesPkg trgt;

    public DependencyDescriptor(AbstractDependenciesPkg src, AbstractDependenciesPkg trgt) {
      this.src = src;
      this.trgt = trgt;
    }

    public boolean isRelatedTo(Collection<AbstractDependenciesPkg> cycle) {
      return cycle.contains(src) && cycle.contains(trgt);
    }

    private PkgDependenciesCycleLiveValidationRule getOuterType() {
      return PkgDependenciesCycleLiveValidationRule.this;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DependencyDescriptor other = (DependencyDescriptor) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (src == null) {
        if (other.src != null)
          return false;
      } else if (!src.equals(other.src)) {
        return false;
      }
      if (trgt == null) {
        if (other.trgt != null)
          return false;
      } else if (!trgt.equals(other.trgt)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((src == null) ? 0 : src.hashCode());
      result = prime * result + ((trgt == null) ? 0 : trgt.hashCode());
      return result;
    }
  }
}
