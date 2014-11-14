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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

public class Part_Type implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    EObject container = element_p.eContainer();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
    Set<EObject> setOfAvailableElements = new HashSet<EObject>();  
    if (element_p instanceof Part) {
      if (container instanceof Entity){
        setOfAvailableElements = EObjectExt.getAll(arch, OaPackage.Literals.ENTITY);
        //remove the parent entity
        if (setOfAvailableElements.contains(container)) {
          setOfAvailableElements.remove(container);
        }
        //remove the root entity
        OperationalAnalysis archAnalysis = (OperationalAnalysis) arch;
        EntityPkg ownedEntityPkg = archAnalysis.getOwnedEntityPkg();
        if (null != ownedEntityPkg) {
          EList<Entity> ownedEntities = ownedEntityPkg.getOwnedEntities();
          if (ownedEntities != null && !ownedEntities.isEmpty()) {
            Entity rootEntity = ownedEntities.get(0);
            if (setOfAvailableElements.contains(rootEntity)) {
              setOfAvailableElements.remove(rootEntity);
            }
          }
        }
      } else
      if (container instanceof OperationalContext){
        setOfAvailableElements = EObjectExt.getAll(arch, OaPackage.Literals.ENTITY);
      }else
      if (container instanceof SystemContext){
        setOfAvailableElements = EObjectExt.getAll(arch, CtxPackage.Literals.ACTOR);
        setOfAvailableElements.add(((SystemAnalysis) arch).getOwnedSystem());
      }else
      if (container instanceof LogicalContext){
        setOfAvailableElements = EObjectExt.getAll(arch, LaPackage.Literals.LOGICAL_ACTOR);
        setOfAvailableElements.add(((LogicalArchitecture) arch).getOwnedLogicalComponent());
      }else 
      if (container instanceof PhysicalContext){
        setOfAvailableElements = EObjectExt.getAll(arch, PaPackage.Literals.PHYSICAL_ACTOR);
        setOfAvailableElements.add(((PhysicalArchitecture) arch).getOwnedPhysicalComponent());
      }else
      if (container instanceof EPBSContext){
        setOfAvailableElements.add(((EPBSArchitecture) arch).getOwnedConfigurationItem());
      }else
      if (container instanceof LogicalComponent){
        Collection<EObject> availableComponentsByNamespaceOfParts = ComponentExt.getAvailableComponentsByNamespaceOfParts((Part) element_p);
        for (EObject anObject : availableComponentsByNamespaceOfParts){
          if (! EcoreUtil.isAncestor(anObject, element_p) && anObject instanceof LogicalComponent){
            setOfAvailableElements.add(anObject);
          }
        }
      }else
      if (container instanceof PhysicalComponent){
        Collection<EObject> availableComponentsByNamespaceOfParts = ComponentExt.getAvailableComponentsByNamespaceOfParts((Part) element_p);
        for (EObject anObject : availableComponentsByNamespaceOfParts){
          if (! EcoreUtil.isAncestor(anObject, element_p) && anObject instanceof PhysicalComponent){
            setOfAvailableElements.add(anObject);
          }
        }
      }else
      if (container instanceof ConfigurationItem){
        Collection<EObject> availableComponentsByNamespaceOfParts = ComponentExt.getAvailableComponentsByNamespaceOfParts((Part) element_p);
        for (EObject anObject : availableComponentsByNamespaceOfParts){
          if (! EcoreUtil.isAncestor(anObject, element_p) && anObject instanceof ConfigurationItem){
            setOfAvailableElements.add(anObject);
          }
        }
      }
    }
    for (EObject anObject : setOfAvailableElements){
      if (anObject instanceof CapellaElement){
        availableElements.add((CapellaElement) anObject);
      }
    }
    return availableElements;

  }

  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof Part) {
      Part part = (Part) element_p;
      AbstractType type = part.getType();
      if (null != type) {
        currentElements.add((CapellaElement) type);
      }
    }
    return currentElements;
  }


  public EClass getEClass() {
    return CsPackage.Literals.PART;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_Part_Type__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_Part_Type", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}
  
}
