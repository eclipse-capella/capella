/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.migration.UnknownEStructuralFeature;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 * This class takes care of the migration of the Actor refactoring work. Here are some basic migration steps: 1)
 * Unknowned features ({@link ActorRefactoringMigrationContribution#unknownedFeatures}) are temporarily stored via the
 * ownedMigratedElements feature. 2) Renamed features are processed via
 * ({@link ActorRefactoringMigrationContribution#oldFeature2NewFeature}). 3) New types are processed via
 * ({@link ActorRefactoringMigrationContribution#oldType2NewType}). 4) After all elements are migrated, they are
 * reorganized via ({@link ActorRefactoringMigrationContribution#reorganizeMigratedElements}).
 * 
 */
public class ActorRefactoringMigrationContribution extends AbstractMigrationContribution {
  private List<UnknownEStructuralFeature> unknownedFeatures = Arrays.asList(
      // System
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedSystem"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalComponent"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalComponent"),
      new UnknownEStructuralFeature(EpbsPackage.Literals.EPBS_ARCHITECTURE, "ownedConfigurationItem"),

      // System context
      new UnknownEStructuralFeature(OaPackage.Literals.OPERATIONAL_ANALYSIS, "ownedOperationalContext"),
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedSystemContext"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalContext"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalContext"),
      new UnknownEStructuralFeature(EpbsPackage.Literals.EPBS_ARCHITECTURE, "ownedEPBSContext"),

      // Actor pkg
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedActorPkg"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalActorPkg"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalActorPkg"),

      // Context's DataPkg
      new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedDataPkg"),
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedDataPkg"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedDataPkg"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedDataPkg"),
      new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedDataPkg"),

      // Context's InterfacePkg
      new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedInterfacePkg"),
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedInterfacePkg"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedInterfacePkg"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedInterfacePkg"),
      new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedInterfacePkg"),

      // Context's CapabilityPkg
      new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedAbstractCapabilityPkg"),
      new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedAbstractCapabilityPkg"),
      new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedAbstractCapabilityPkg"),
      new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedAbstractCapabilityPkg"),
      new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedAbstractCapabilityPkg"));

  private Map<UnknownEStructuralFeature, EStructuralFeature> oldFeature2NewFeature = new HashMap<UnknownEStructuralFeature, EStructuralFeature>() {
    {
      // Realization links
      put(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT, "ownedOperationalActorRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT, "ownedOperationalEntityRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      put(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT, "ownedSystemActorRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      put(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT, "ownedSystemRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      put(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT, "ownedLogicalActorRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      put(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT, "ownedLogicalComponentRealizations"),
          CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);

      // Part
      put(new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedFeatures"),
          CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedFeatures"),
          CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
      put(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedFeatures"),
          CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
      put(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedFeatures"),
          CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
      put(new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedFeatures"),
          CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);

      // Actor
      put(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedActors"),
          CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS);
      put(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedLogicalActors"),
          LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS);
      put(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedPhysicalActors"),
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);

      // Capability/Mission Involvements
      put(new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY_INVOLVEMENT, "actor"),
          CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY, "ownedActorCapabilityInvolvements"),
          CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY, "ownedSystemCapabilityInvolvement"),
          CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.MISSION_INVOLVEMENT, "actor"),
          CtxPackage.Literals.MISSION_INVOLVEMENT__SYSTEM_COMPONENT);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.MISSION, "ownedActorMissionInvolvements"),
          CtxPackage.Literals.MISSION__OWNED_MISSION_INVOLVEMENTS);
      put(new UnknownEStructuralFeature(CtxPackage.Literals.MISSION, "ownedSystemMissionInvolvement"),
          CtxPackage.Literals.MISSION__OWNED_MISSION_INVOLVEMENTS);

      // Capability Realization Involvements
      put(new UnknownEStructuralFeature(LaPackage.Literals.CAPABILITY_REALIZATION, "ownedActorCapabilityRealizations"),
          LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);
      put(new UnknownEStructuralFeature(LaPackage.Literals.CAPABILITY_REALIZATION,
          "ownedSystemComponentCapabilityRealizations"),
          LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);
    }
  };

  private Map<String, String> oldType2NewType = new HashMap<String, String>() {
    {
      // Context to Package
      put("org.polarsys.capella.core.data.oa:OperationalContext", "org.polarsys.capella.core.data.oa:EntityPkg");
      put("org.polarsys.capella.core.data.ctx:SystemContext", "org.polarsys.capella.core.data.ctx:SystemComponentPkg");
      put("org.polarsys.capella.core.data.la:LogicalContext", "org.polarsys.capella.core.data.la:LogicalComponentPkg");
      put("org.polarsys.capella.core.data.pa:PhysicalContext",
          "org.polarsys.capella.core.data.pa:PhysicalComponentPkg");
      put("org.polarsys.capella.core.data.epbs:EPBSContext",
          "org.polarsys.capella.core.data.epbs:ConfigurationItemPkg");

      // Actor package
      put("org.polarsys.capella.core.data.ctx:ActorPkg", "org.polarsys.capella.core.data.ctx:SystemComponentPkg");
      put("org.polarsys.capella.core.data.la:LogicalActorPkg", "org.polarsys.capella.core.data.la:LogicalComponentPkg");
      put("org.polarsys.capella.core.data.pa:PhysicalActorPkg",
          "org.polarsys.capella.core.data.pa:PhysicalComponentPkg");

      // Realization links
      put("org.polarsys.capella.core.data.ctx:OperationalActorRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");
      put("org.polarsys.capella.core.data.ctx:OperationalEntityRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");
      put("org.polarsys.capella.core.data.la:SystemActorRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");
      put("org.polarsys.capella.core.data.la:SystemRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");
      put("org.polarsys.capella.core.data.pa:LogicalActorRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");
      put("org.polarsys.capella.core.data.pa:LogicalComponentRealization",
          "org.polarsys.capella.core.data.cs:ComponentRealization");

      // System
      put("org.polarsys.capella.core.data.ctx:System", "org.polarsys.capella.core.data.ctx:SystemComponent");

      // Actors
      put("org.polarsys.capella.core.data.oa:OperationalActor", "org.polarsys.capella.core.data.oa:Entity");
      put("org.polarsys.capella.core.data.ctx:Actor", "org.polarsys.capella.core.data.ctx:SystemComponent");
      put("org.polarsys.capella.core.data.la:LogicalActor", "org.polarsys.capella.core.data.la:LogicalComponent");
      put("org.polarsys.capella.core.data.pa:PhysicalActor", "org.polarsys.capella.core.data.pa:PhysicalComponent");

      // Capability/Mission Involvements
      put("org.polarsys.capella.core.data.ctx:ActorCapabilityInvolvement",
          "org.polarsys.capella.core.data.ctx:CapabilityInvolvement");
      put("org.polarsys.capella.core.data.ctx:SystemCapabilityInvolvement",
          "org.polarsys.capella.core.data.ctx:CapabilityInvolvement");
      put("org.polarsys.capella.core.data.ctx:ActorMissionInvolvement",
          "org.polarsys.capella.core.data.ctx:MissionInvolvement");
      put("org.polarsys.capella.core.data.ctx:SystemMissionInvolvement",
          "org.polarsys.capella.core.data.ctx:MissionInvolvement");

      // Capability Realization Involvements
      put("org.polarsys.capella.core.data.cs:ActorCapabilityRealizationInvolvement",
          "org.polarsys.capella.core.data.capellacommon:CapabilityRealizationInvolvement");
      put("org.polarsys.capella.core.data.cs:SystemComponentCapabilityRealizationInvolvement",
          "org.polarsys.capella.core.data.capellacommon:CapabilityRealizationInvolvement");
    }
  };

  private List<String> oldActorTypes = Arrays.asList("org.polarsys.capella.core.data.oa:OperationalActor",
      "org.polarsys.capella.core.data.ctx:Actor", "org.polarsys.capella.core.data.la:LogicalActor",
      "org.polarsys.capella.core.data.pa:PhysicalActor");

  @Override
  public EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
    UnknownEStructuralFeature featureToTest = new UnknownEStructuralFeature(object.eClass(), name);
    if (unknownedFeatures.contains(featureToTest)) {
      return ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;
    } else if (oldFeature2NewFeature.containsKey(featureToTest)) {
      return oldFeature2NewFeature.get(featureToTest);
    }
    return super.getFeature(object, prefix, name, isElement);
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    if (oldType2NewType.containsKey(typeQName)) {
      return oldType2NewType.get(typeQName);
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }

  @Override
  public void updateCreatedObject(EObject peekObject, EObject eObject, String typeQName, EStructuralFeature feature,
      XMLResource resource, XMLHelper helper, MigrationContext context) {
    // Actor become Component of Actor type
    if (oldActorTypes.contains(typeQName) && eObject instanceof Component) {
      ((Component) eObject).setActor(true);
    }
    super.updateCreatedObject(peekObject, eObject, typeQName, feature, resource, helper, context);
  }

  protected void fusionContainmentReferences(EObject source, EObject target) {
    if (source.eClass() != target.eClass()) {
      return;
    }
    for (EReference reference : source.eClass().getEAllReferences()) {
      if (reference.isContainment()) {
        Object refSourceObjs = source.eGet(reference);
        Object refTargetObjs = target.eGet(reference);
        if (refSourceObjs instanceof List && refTargetObjs instanceof List) {
          ((List) refTargetObjs).addAll((List) refSourceObjs);
        }
      }
    }
  }

  /**
   * Move migrated elements under an Architecture into the right container
   * 
   * @param architecture
   */
  protected void reorganizeMigratedElements(BlockArchitecture architecture) {
    if (architecture instanceof OperationalAnalysis) {
      OperationalAnalysis oa = (OperationalAnalysis) architecture;
      reorganizeOperationalAnalysis(oa);
    } else if (architecture instanceof SystemAnalysis) {
      SystemAnalysis sa = (SystemAnalysis) architecture;
      reorganizeSystemAnalysis(sa);
    } else if (architecture instanceof LogicalArchitecture) {
      LogicalArchitecture la = (LogicalArchitecture) architecture;
      reorganizeLogicalArchitecture(la);
    } else if (architecture instanceof PhysicalArchitecture) {
      PhysicalArchitecture pa = (PhysicalArchitecture) architecture;
      reorganizePhysicalArchitecture(pa);
    } else if (architecture instanceof EPBSArchitecture) {
      EPBSArchitecture epbs = (EPBSArchitecture) architecture;
      reorganizeEPBSArchitecture(architecture, epbs);
    }
  }

  /**
   * 1) Move the migrated EPBS System to the Configuration Item Pkg. 2) Move the migrated EPBS Context to Configuration
   * Item Pkg. 3) Move Physical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to corresponding
   * packages. If the packages do not exist, create them.
   * 
   * @param epbs
   */
  protected void reorganizeEPBSArchitecture(BlockArchitecture architecture, EPBSArchitecture epbs) {
    if (!epbs.getOwnedMigratedElements().isEmpty()) {
      BlockArchitectureExt.getComponentPkg(architecture, true);
      epbs.getOwnedConfigurationItemPkg().getOwnedConfigurationItems().addAll(epbs.getOwnedMigratedElements().stream()
          .filter(ConfigurationItem.class::isInstance).map(ConfigurationItem.class::cast).collect(Collectors.toList()));
      epbs.getOwnedMigratedElements().stream().filter(ConfigurationItemPkg.class::isInstance)
          .forEach(modelElement -> fusionContainmentReferences(modelElement, epbs.getOwnedConfigurationItemPkg()));

      List<DataPkg> migratedDataPkg = epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements().stream()
          .filter(DataPkg.class::isInstance).map(DataPkg.class::cast).collect(Collectors.toList());
      if (!migratedDataPkg.isEmpty()) {
        BlockArchitectureExt.getDataPkg(architecture, true);
        epbs.getOwnedDataPkg().getOwnedDataPkgs().addAll(migratedDataPkg);
      }

      List<InterfacePkg> migratedInterfacePkg = epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements().stream()
          .filter(InterfacePkg.class::isInstance).map(InterfacePkg.class::cast).collect(Collectors.toList());
      if (!migratedInterfacePkg.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(architecture, true);
        epbs.getOwnedInterfacePkg().getOwnedInterfacePkgs().addAll(migratedInterfacePkg);
      }

      ((CapabilityRealizationPkg) epbs.getOwnedAbstractCapabilityPkg()).getOwnedCapabilityRealizationPkgs()
          .addAll(epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements().stream()
              .filter(CapabilityRealizationPkg.class::isInstance).map(CapabilityRealizationPkg.class::cast)
              .collect(Collectors.toList()));
    }
  }

  /**
   * 1) Move the migrated Physical System to the Physical Component Pkg. 2) Move the migrated Physical Context to
   * Physical Component Pkg. 3) Move Physical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to
   * corresponding packages.
   * 
   * @param la
   */
  protected void reorganizePhysicalArchitecture(PhysicalArchitecture pa) {
    if (!pa.getOwnedMigratedElements().isEmpty()) {
      BlockArchitectureExt.getComponentPkg(pa, true);
      pa.getOwnedPhysicalComponentPkg().getOwnedPhysicalComponents().addAll(pa.getOwnedMigratedElements().stream()
          .filter(PhysicalComponent.class::isInstance).map(PhysicalComponent.class::cast).collect(Collectors.toList()));
      pa.getOwnedMigratedElements().stream().filter(PhysicalComponentPkg.class::isInstance)
          .forEach(modelElement -> fusionContainmentReferences(modelElement, pa.getOwnedPhysicalComponentPkg()));
      pa.getOwnedDataPkg().getOwnedDataPkgs().addAll(pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements()
          .stream().filter(DataPkg.class::isInstance).map(DataPkg.class::cast).collect(Collectors.toList()));
      pa.getOwnedInterfacePkg().getOwnedInterfacePkgs()
          .addAll(pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements().stream()
              .filter(InterfacePkg.class::isInstance).map(InterfacePkg.class::cast).collect(Collectors.toList()));
      ((CapabilityRealizationPkg) pa.getOwnedAbstractCapabilityPkg()).getOwnedCapabilityRealizationPkgs()
          .addAll(pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements().stream()
              .filter(CapabilityRealizationPkg.class::isInstance).map(CapabilityRealizationPkg.class::cast)
              .collect(Collectors.toList()));
    }
  }

  /**
   * 1) Move the migrated Logical System to the Logical Component Pkg. 2) Move the migrated Logical Context to Logical
   * Component Pkg. 3) Move Logical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to
   * corresponding packages.
   * 
   * @param la
   */
  protected void reorganizeLogicalArchitecture(LogicalArchitecture la) {
    if (!la.getOwnedMigratedElements().isEmpty()) {
      BlockArchitectureExt.getComponentPkg(la, true);
      la.getOwnedLogicalComponentPkg().getOwnedLogicalComponents().addAll(la.getOwnedMigratedElements().stream()
          .filter(LogicalComponent.class::isInstance).map(LogicalComponent.class::cast).collect(Collectors.toList()));
      la.getOwnedMigratedElements().stream().filter(LogicalComponentPkg.class::isInstance)
          .forEach(modelElement -> fusionContainmentReferences(modelElement, la.getOwnedLogicalComponentPkg()));
      la.getOwnedDataPkg().getOwnedDataPkgs().addAll(la.getOwnedLogicalComponentPkg().getOwnedMigratedElements()
          .stream().filter(DataPkg.class::isInstance).map(DataPkg.class::cast).collect(Collectors.toList()));
      la.getOwnedInterfacePkg().getOwnedInterfacePkgs()
          .addAll(la.getOwnedLogicalComponentPkg().getOwnedMigratedElements().stream()
              .filter(InterfacePkg.class::isInstance).map(InterfacePkg.class::cast).collect(Collectors.toList()));
      ((CapabilityRealizationPkg) la.getOwnedAbstractCapabilityPkg()).getOwnedCapabilityRealizationPkgs()
          .addAll(la.getOwnedLogicalComponentPkg().getOwnedMigratedElements().stream()
              .filter(CapabilityRealizationPkg.class::isInstance).map(CapabilityRealizationPkg.class::cast)
              .collect(Collectors.toList()));
    }
  }

  /**
   * 1) Move the migrated System to the System Component Pkg. 2) Move the migrated System Context to System Component
   * Pkg. 3) Move System Context's DataPkg, InterfacePkg, CapabilityPkg (if exist) to corresponding packages.
   * 
   * @param sa
   */
  protected void reorganizeSystemAnalysis(SystemAnalysis sa) {
    if (!sa.getOwnedMigratedElements().isEmpty()) {
      BlockArchitectureExt.getComponentPkg(sa, true);
      sa.getOwnedSystemComponentPkg().getOwnedSystemComponents().addAll(sa.getOwnedMigratedElements().stream()
          .filter(SystemComponent.class::isInstance).map(SystemComponent.class::cast).collect(Collectors.toList()));
      sa.getOwnedMigratedElements().stream().filter(SystemComponentPkg.class::isInstance)
          .forEach(modelElement -> fusionContainmentReferences(modelElement, sa.getOwnedSystemComponentPkg()));
      sa.getOwnedDataPkg().getOwnedDataPkgs().addAll(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements().stream()
          .filter(DataPkg.class::isInstance).map(DataPkg.class::cast).collect(Collectors.toList()));
      sa.getOwnedInterfacePkg().getOwnedInterfacePkgs()
          .addAll(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements().stream()
              .filter(InterfacePkg.class::isInstance).map(InterfacePkg.class::cast).collect(Collectors.toList()));
      ((CapabilityPkg) sa.getOwnedAbstractCapabilityPkg()).getOwnedCapabilityPkgs()
          .addAll(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements().stream()
              .filter(CapabilityPkg.class::isInstance).map(CapabilityPkg.class::cast).collect(Collectors.toList()));
    }
  }

  /**
   * 1) Move migrated Operational Context to Entity Pkg. 2) Move OperationalContext's DataPkg, InterfacePkg,
   * OperationalCapabilityPkg (if exist) to corresponding packages.
   * 
   * @param oa
   */
  protected void reorganizeOperationalAnalysis(OperationalAnalysis oa) {
    if (!oa.getOwnedMigratedElements().isEmpty()) {
      BlockArchitectureExt.getComponentPkg(oa, true);
      oa.getOwnedMigratedElements().stream().filter(EntityPkg.class::isInstance)
          .forEach(modelElement -> fusionContainmentReferences(modelElement, oa.getOwnedEntityPkg()));
      oa.getOwnedDataPkg().getOwnedDataPkgs().addAll(oa.getOwnedEntityPkg().getOwnedMigratedElements().stream()
          .filter(DataPkg.class::isInstance).map(DataPkg.class::cast).collect(Collectors.toList()));
      oa.getOwnedInterfacePkg().getOwnedInterfacePkgs().addAll(oa.getOwnedEntityPkg().getOwnedMigratedElements()
          .stream().filter(InterfacePkg.class::isInstance).map(InterfacePkg.class::cast).collect(Collectors.toList()));
      ((OperationalCapabilityPkg) oa.getOwnedAbstractCapabilityPkg()).getOwnedOperationalCapabilityPkgs()
          .addAll(oa.getOwnedEntityPkg().getOwnedMigratedElements().stream()
              .filter(OperationalCapabilityPkg.class::isInstance).map(OperationalCapabilityPkg.class::cast)
              .collect(Collectors.toList()));
    }
  }

  /**
   * 1) Move migrated elements into the right container (e.g. System to Component Package). 2) Clear temporarily
   * OwnedMigratedElements feature. 3) Delete dangling TransfoLinks (e.g. Transfo Link to old Actors).
   * 
   * @param resource
   */
  protected void cleanUpMigratedElements(Resource resource) {
    List<BlockArchitecture> blockArchitecturesToReorganize = new ArrayList<>();
    List<ModelElement> elementsToClean = new ArrayList<>();
    List<TransfoLink> transfoLinksToClean = new ArrayList<>();
    TreeIterator<EObject> iterator = resource.getAllContents();
    while (iterator.hasNext()) {
      EObject currentElement = iterator.next();
      if (currentElement instanceof BlockArchitecture) {
        blockArchitecturesToReorganize.add((BlockArchitecture) currentElement);
      }
      if (currentElement instanceof ModelElement
          && !((ModelElement) currentElement).getOwnedMigratedElements().isEmpty()) {
        elementsToClean.add((ModelElement) currentElement);
      }
      if (currentElement instanceof TransfoLink && ((TransfoLink) currentElement).getSource() == null
          && ((TransfoLink) currentElement).getTarget() == null) {
        transfoLinksToClean.add((TransfoLink) currentElement);
      }
    }
    blockArchitecturesToReorganize.stream().forEach(architecture -> reorganizeMigratedElements(architecture));
    elementsToClean.stream().forEach(element -> EcoreUtil.deleteAll(element.getOwnedMigratedElements(), true));
    EcoreUtil.deleteAll(transfoLinksToClean, false);
  }

  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    cleanUpMigratedElements(resource);
    super.unaryEndMigrationExecute(executionManager, resource, context);
  }

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {
    // Only migrate parts coming from the ownedFeatures feature of a Package
    if (feature == CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS && !(value instanceof Part)) {
      return true;
    }
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }
}
