/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.libraries.extendedqueries.GenericGetForLibWithSystemEngineering;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class SemanticCandidateServices {
  private static SemanticCandidateServices service = null;

  public static SemanticCandidateServices getService() {
    if (service == null) {
      service = new SemanticCandidateServices();
    }
    return service;
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in all the root SystemEngineering and in the libraries with the type in the
   *         parameters
   */
  private Collection<EObject> getReferencedCommonElementsFromProject(EClass elementEClass, DSemanticDecorator diagram) {
    Collection<EObject> allReferencedElements = new ArrayList<>(
        getCache(this::getElementsFromProject, elementEClass, diagram));
    allReferencedElements.addAll(getCache(this::getElementsFromLibraries, elementEClass, diagram));
    return allReferencedElements;
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in all the root SystemEngineering and in the libraries with the type in the
   *         parameters
   */
  private Collection<EObject> getReferencedCommonElementsFromSystemEngineering(EClass elementEClass,
      DSemanticDecorator diagram) {
    Collection<EObject> allReferencedElements = new ArrayList<>(
        getCache(this::getElementsFromRootSystemEngineering, elementEClass, diagram));
    allReferencedElements.addAll(getCache(this::getElementsFromLibraries, elementEClass, diagram));
    return allReferencedElements;
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in the root System Engineering with the type in the parameters
   */
  private Collection<EObject> getElementsFromRootSystemEngineering(EClass elementEClass, DSemanticDecorator diagram) {
    EObject rootSystemEngineering = SystemEngineeringExt.getSystemEngineering((CapellaElement) diagram.getTarget());
    if (rootSystemEngineering != null) {
      return getCache(EcoreUtil2::getAllContentsFromEObject, rootSystemEngineering, elementEClass);
    }

    return new ArrayList<>();
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in the project with the type in the parameters, this function is specially
   *         for Property Value and Property Value Group types
   */
  private Collection<EObject> getElementsFromProject(EClass elementEClass, DSemanticDecorator diagram) {
    EObject project = ProjectExt.getProject(diagram.getTarget());
    if (project != null) {
      return getCache(EcoreUtil2::getAllContentsFromEObject, project, elementEClass);
    }
    return new ArrayList<>();
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in the libraries with the type in the parameters
   */
  private Collection<EObject> getElementsFromLibraries(EClass elementEClass, DSemanticDecorator diagram) {
    List<Object> libraries = new GenericGetForLibWithSystemEngineering().execute(diagram, new QueryContext());
    List<EObject> librariesElements = new ArrayList<>();

    for (Object library : libraries) {
      if (library instanceof Library) {
        librariesElements.addAll(getCache(EcoreUtil2::getAllContentsFromEObject, (EObject) library, elementEClass));
      }
    }
    return librariesElements;
  }

  /**
   * 
   * @param elementEClass
   *          the EClass of the target Objects
   * @param diagram
   *          the current diagram of type DSemanticDecorator
   * @return the list of elements contained in the parent BlockArchitecture with type in the parameters
   */

  private Collection<EObject> getElementsFromParentBlockArchitecture(EClass elementEClass, DSemanticDecorator diagram) {
    EObject parentBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(diagram.getTarget());
    if (parentBlockArchitecture != null) {
      return getCache(EcoreUtil2::getAllContentsFromEObject, parentBlockArchitecture, elementEClass);
    }
    return Collections.emptyList();
  }

  public Collection<EObject> getPhysicalFunctionSemanticCandidates(DSemanticDecorator diagram) {
    EClass physicalFunctionEClass = PaPackage.Literals.PHYSICAL_FUNCTION;
    return getCache(this::getElementsFromParentBlockArchitecture, physicalFunctionEClass, diagram);
  }

  public Collection<EObject> getPhysicalPathSemanticCandidates(DSemanticDecorator diagram) {
    EClass physicalPathEClass = CsPackage.Literals.PHYSICAL_PATH;
    return getCache(this::getElementsFromParentBlockArchitecture, physicalPathEClass, diagram);
  }

  public Collection<EObject> getFunctionalChainSemanticCandidates(DSemanticDecorator diagram) {
    EClass functionalChainEClass = FaPackage.Literals.FUNCTIONAL_CHAIN;
    return getCache(this::getElementsFromParentBlockArchitecture, functionalChainEClass, diagram);
  }

  public Collection<EObject> getExchangeCategorySemanticCandidates(DSemanticDecorator diagram) {
    EClass exchangeCategoryEClass = FaPackage.Literals.EXCHANGE_CATEGORY;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, exchangeCategoryEClass, diagram);
  }

  public Collection<EObject> getComponentExchangeCategorySemanticCandidates(DSemanticDecorator diagram) {
    EClass componentExchangeCategoryEClass = FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY;
    return getCache(this::getElementsFromParentBlockArchitecture, componentExchangeCategoryEClass, diagram);
  }

  public Collection<EObject> getPhysicalLinkCategorySemanticCandidates(DSemanticDecorator diagram) {
    EClass physicalLinkCategoryEClass = CsPackage.Literals.PHYSICAL_LINK_CATEGORY;
    return getCache(this::getElementsFromParentBlockArchitecture, physicalLinkCategoryEClass, diagram);
  }

  public Collection<EObject> getPartSemanticCandidates(DSemanticDecorator diagram) {
    EClass partEClass = CsPackage.Literals.PART;
    return getCache(this::getElementsFromParentBlockArchitecture, partEClass, diagram);
  }

  public Collection<EObject> getPortAllocationSemanticCandidates(DSemanticDecorator diagram) {
    EClass portAllocationEClass = InformationPackage.Literals.PORT_ALLOCATION;
    return getCache(this::getElementsFromParentBlockArchitecture, portAllocationEClass, diagram);
  }

  public Collection<EObject> getCommonPortAllocationSemanticCandidates(DSemanticDecorator diagram) {
    EClass portAllocationEClass = InformationPackage.Literals.PORT_ALLOCATION;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, portAllocationEClass, diagram);
  }

  public Collection<EObject> getConstraintSemanticCandidates(DSemanticDecorator diagram) {
    EClass constraintEClass = CapellacorePackage.Literals.CONSTRAINT;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, constraintEClass, diagram);
  }

  public Collection<EObject> getDataValueSemanticCandidates(DSemanticDecorator diagram) {
    EClass dataValueEClass = DatavaluePackage.Literals.DATA_VALUE;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, dataValueEClass, diagram);
  }

  public Collection<EObject> getUnitSemanticCandidates(DSemanticDecorator diagram) {
    EClass unitEClass = InformationPackage.Literals.UNIT;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, unitEClass, diagram);
  }

  public Collection<EObject> getAbstractPropertyValueSemanticCandidates(DSemanticDecorator diagram) {
    EClass abstractPropertyValueEClass = CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE;
    return getCache(this::getReferencedCommonElementsFromProject, abstractPropertyValueEClass, diagram);
  }

  public Collection<EObject> getClassSemanticCandidates(DSemanticDecorator diagram) {
    EClass classEClass = InformationPackage.Literals.CLASS;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, classEClass, diagram);
  }

  public Collection<EObject> getEnumerationSemanticCandidates(DSemanticDecorator diagram) {
    EClass enumerationEClass = DatatypePackage.Literals.ENUMERATION;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, enumerationEClass, diagram);
  }

  public Collection<EObject> getCollectionSemanticCandidates(DSemanticDecorator diagram) {
    EClass collectionEClass = InformationPackage.Literals.COLLECTION;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, collectionEClass, diagram);
  }

  public Collection<EObject> getBooleanTypeSemanticCandidates(DSemanticDecorator diagram) {
    EClass booleanTypeEClass = DatatypePackage.Literals.BOOLEAN_TYPE;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, booleanTypeEClass, diagram);
  }

  public Collection<EObject> getDataTypeSemanticCandidates(DSemanticDecorator diagram) {
    EClass dataTypeEClass = DatatypePackage.Literals.DATA_TYPE;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, dataTypeEClass, diagram);
  }

  public Collection<EObject> getPropertyValueGroupSemanticCandidates(DSemanticDecorator diagram) {
    EClass propertyValueGroupEClass = CapellacorePackage.Literals.PROPERTY_VALUE_GROUP;
    return getCache(this::getReferencedCommonElementsFromProject, propertyValueGroupEClass, diagram);
  }

  public Collection<EObject> getDataPkgSemanticCandidates(DSemanticDecorator diagram) {
    EClass dataPkgEClass = InformationPackage.Literals.DATA_PKG;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, dataPkgEClass, diagram);
  }

  public Collection<EObject> getExchangeItemSemanticCandidates(DSemanticDecorator diagram) {
    EClass exchangeItemEClass = InformationPackage.Literals.EXCHANGE_ITEM;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, exchangeItemEClass, diagram);
  }

  public Collection<EObject> getInterfacePkgSemanticCandidates(DSemanticDecorator diagram) {
    EClass interfacePkgEClass = CsPackage.Literals.INTERFACE_PKG;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, interfacePkgEClass, diagram);
  }

  public Collection<EObject> getInterfaceSemanticCandidates(DSemanticDecorator diagram) {
    EClass interfaceEClass = CsPackage.Literals.INTERFACE;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, interfaceEClass, diagram);
  }

  public Collection<EObject> getGeneralizationSemanticCandidates(DSemanticDecorator diagram) {
    EClass generalizationEClass = CapellacorePackage.Literals.GENERALIZATION;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, generalizationEClass, diagram);
  }

  public Collection<EObject> getComponentSemanticCandidates(DSemanticDecorator diagram) {
    EClass componentEClass = CsPackage.Literals.COMPONENT;
    return getReferencedCommonElementsFromSystemEngineering(componentEClass, diagram);
  }

  public Collection<EObject> getCapabilityRealizationSemanticCandidates(DSemanticDecorator diagram) {
    EClass capabilityRealizationEClass = LaPackage.Literals.CAPABILITY_REALIZATION;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, capabilityRealizationEClass, diagram);
  }

  public Collection<EObject> getAbstractActorSemanticCandidates(DSemanticDecorator diagram) {
    EClass abstractActorEClass = CsPackage.Literals.ABSTRACT_ACTOR;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, abstractActorEClass, diagram);
  }

  public Collection<EObject> getSystemComponentSemanticCandidates(DSemanticDecorator diagram) {
    EClass systemComponentEClass = CsPackage.Literals.SYSTEM_COMPONENT;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, systemComponentEClass, diagram);
  }

  public Collection<EObject> getCapabilityRealizationInvolvementSemanticCandidates(DSemanticDecorator diagram) {
    EClass capabilityRealizationInvolvementEClass = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT;
    return getCache(this::getReferencedCommonElementsFromSystemEngineering, capabilityRealizationInvolvementEClass,
        diagram);
  }

  public Collection<EObject> getMissionSemanticCandidates(DSemanticDecorator diagram) {
    EClass missionEClass = CtxPackage.Literals.MISSION;
    return getCache(this::getElementsFromParentBlockArchitecture, missionEClass, diagram);
  }

  public Collection<EObject> getActorSemanticCandidates(DSemanticDecorator diagram) {
    EClass actorEClass = CtxPackage.Literals.ACTOR;
    return getCache(this::getElementsFromParentBlockArchitecture, actorEClass, diagram);
  }

  public Collection<EObject> getCapabilitySemanticCandidates(DSemanticDecorator diagram) {
    EClass capabilityEClass = CtxPackage.Literals.CAPABILITY;
    return getCache(this::getElementsFromParentBlockArchitecture, capabilityEClass, diagram);
  }

  public Collection<EObject> getRequirementSemanticCandidates(DSemanticDecorator diagram) {
    EClass requirementEClass = RequirementPackage.Literals.REQUIREMENT;
    return getCache(this::getElementsFromParentBlockArchitecture, requirementEClass, diagram);
  }

  public Collection<EObject> getAbstractFunctionSemanticCandidates(DSemanticDecorator diagram) {
    EClass abstractFunctionEClass = FaPackage.Literals.ABSTRACT_FUNCTION;
    return getCache(this::getElementsFromParentBlockArchitecture, abstractFunctionEClass, diagram);
  }

  public Collection<EObject> getCapellaElementSemanticCandidates(DSemanticDecorator diagram) {
    EClass capellaElementEClass = CapellacorePackage.Literals.CAPELLA_ELEMENT;
    return getCache(this::getElementsFromParentBlockArchitecture, capellaElementEClass, diagram);
  }

  public Collection<EObject> getSystemFunctionSemanticCandidates(DSemanticDecorator diagram) {
    EClass systemFunctionEClass = CtxPackage.Literals.SYSTEM_FUNCTION;
    return getCache(this::getElementsFromParentBlockArchitecture, systemFunctionEClass, diagram);
  }

  public Collection<EObject> getOperationalProcessSemanticCandidates(DSemanticDecorator diagram) {
    EClass operationalProcessEClass = OaPackage.Literals.OPERATIONAL_PROCESS;
    return getCache(this::getElementsFromParentBlockArchitecture, operationalProcessEClass, diagram);
  }

  public Collection<EObject> getEntitySemanticCandidates(DSemanticDecorator diagram) {
    EClass entityEClass = OaPackage.Literals.ENTITY;
    return getCache(this::getElementsFromParentBlockArchitecture, entityEClass, diagram);
  }

  public Collection<EObject> getOperationalActivitySemanticCandidates(DSemanticDecorator diagram) {
    EClass operationalActivityEClass = OaPackage.Literals.OPERATIONAL_ACTIVITY;
    return getCache(this::getElementsFromParentBlockArchitecture, operationalActivityEClass, diagram);
  }

  public Collection<EObject> getRoleSemanticCandidates(DSemanticDecorator diagram) {
    EClass roleEClass = OaPackage.Literals.ROLE;
    return getCache(this::getElementsFromParentBlockArchitecture, roleEClass, diagram);
  }

  public Collection<EObject> getOperationalCapabilitySemanticCandidates(DSemanticDecorator diagram) {
    EClass operationalCapabilityEClass = OaPackage.Literals.OPERATIONAL_CAPABILITY;
    return getCache(this::getElementsFromParentBlockArchitecture, operationalCapabilityEClass, diagram);
  }

  public Collection<EObject> getLogicalFunctionSemanticCandidates(DSemanticDecorator diagram) {
    EClass logicalFunctionEClass = LaPackage.Literals.LOGICAL_FUNCTION;
    return getCache(this::getElementsFromParentBlockArchitecture, logicalFunctionEClass, diagram);

  }

}
