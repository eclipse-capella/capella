/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.capella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
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
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ModelElementExt;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;

/**
 * This class takes care of the migration of the Actor refactoring work. Here are some basic migration steps:
 * 
 * 1) Unknowned features ({@link ActorRefactoringMigrationContribution#UNKNOWNED_FEATURES}) are temporarily stored via
 * the ownedMigratedElements feature.
 * 
 * 2) Renamed features are processed via ({@link ActorRefactoringMigrationContribution#OLD_FEATURE_2_NEW_FEATURE}).
 * 
 * 3) New types are processed via ({@link ActorRefactoringMigrationContribution#OLD_TYPE_2_NEW_TYPE}).
 * 
 * 4) After all elements are migrated, they are reorganized via
 * ({@link ActorRefactoringMigrationContribution#reorganizeMigratedElements}).
 * 
 */
public class ActorRefactoringMigrationContribution extends AbstractMigrationContribution {
  private static final List<UnknownEStructuralFeature> UNKNOWNED_FEATURES = new ArrayList<>();
  static {
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedSystem"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalComponent"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalComponent"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(EpbsPackage.Literals.EPBS_ARCHITECTURE, "ownedConfigurationItem"));

    // System context
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(OaPackage.Literals.OPERATIONAL_ANALYSIS, "ownedOperationalContext"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedSystemContext"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalContext"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalContext"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(EpbsPackage.Literals.EPBS_ARCHITECTURE, "ownedEPBSContext"));

    // Actor pkg
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_ANALYSIS, "ownedActorPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_ARCHITECTURE, "ownedLogicalActorPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_ARCHITECTURE, "ownedPhysicalActorPkg"));

    // Context's DataPkg
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedDataPkg"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedDataPkg"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedDataPkg"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedDataPkg"));
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedDataPkg"));

    // Context's InterfacePkg
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedInterfacePkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedInterfacePkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedInterfacePkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedInterfacePkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedInterfacePkg"));

    // Context's CapabilityPkg
    UNKNOWNED_FEATURES.add(new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedAbstractCapabilityPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedAbstractCapabilityPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedAbstractCapabilityPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedAbstractCapabilityPkg"));
    UNKNOWNED_FEATURES
        .add(new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedAbstractCapabilityPkg"));
  }

  private static final Map<UnknownEStructuralFeature, EStructuralFeature> OLD_FEATURE_2_NEW_FEATURE = new HashMap<>();
  static {
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT, "ownedOperationalActorRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT, "ownedOperationalEntityRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT, "ownedSystemActorRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT, "ownedSystemRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT, "ownedLogicalActorRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT, "ownedLogicalComponentRealizations"),
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);

    // Part
    OLD_FEATURE_2_NEW_FEATURE.put(new UnknownEStructuralFeature(OaPackage.Literals.ENTITY_PKG, "ownedFeatures"),
        CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedFeatures"),
        CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedFeatures"),
        CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedFeatures"),
        CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(EpbsPackage.Literals.CONFIGURATION_ITEM_PKG, "ownedFeatures"),
        CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);

    // Actor
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedActors"),
        CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedLogicalActors"),
        LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedPhysicalActors"),
        PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);
    // Physical Components
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedComponents"),
        PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);
    // Capability/Mission Involvements
    OLD_FEATURE_2_NEW_FEATURE.put(new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY_INVOLVEMENT, "actor"),
        CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY, "ownedActorCapabilityInvolvements"),
        CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.CAPABILITY, "ownedSystemCapabilityInvolvement"),
        CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(new UnknownEStructuralFeature(CtxPackage.Literals.MISSION_INVOLVEMENT, "actor"),
        CtxPackage.Literals.MISSION_INVOLVEMENT__SYSTEM_COMPONENT);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.MISSION, "ownedActorMissionInvolvements"),
        CtxPackage.Literals.MISSION__OWNED_MISSION_INVOLVEMENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.MISSION, "ownedSystemMissionInvolvement"),
        CtxPackage.Literals.MISSION__OWNED_MISSION_INVOLVEMENTS);

    // Capability Realization Involvements
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.CAPABILITY_REALIZATION, "ownedActorCapabilityRealizations"),
        LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.CAPABILITY_REALIZATION,
            "ownedSystemComponentCapabilityRealizations"),
        LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);

    // Packages
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, "ownedActorPkgs"),
        CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(LaPackage.Literals.LOGICAL_COMPONENT_PKG, "ownedLogicalActorPkgs"),
        LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS);
    OLD_FEATURE_2_NEW_FEATURE.put(
        new UnknownEStructuralFeature(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, "ownedPhysicalActorPkgs"),
        PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS);
  }

  private static final Map<String, String> OLD_TYPE_2_NEW_TYPE = new HashMap<>();
  static {
    // Context to Package
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.oa:OperationalContext",
        "org.polarsys.capella.core.data.oa:EntityPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:SystemContext",
        "org.polarsys.capella.core.data.ctx:SystemComponentPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.la:LogicalContext",
        "org.polarsys.capella.core.data.la:LogicalComponentPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.pa:PhysicalContext",
        "org.polarsys.capella.core.data.pa:PhysicalComponentPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.epbs:EPBSContext",
        "org.polarsys.capella.core.data.epbs:ConfigurationItemPkg");

    // Actor package
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:ActorPkg",
        "org.polarsys.capella.core.data.ctx:SystemComponentPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.la:LogicalActorPkg",
        "org.polarsys.capella.core.data.la:LogicalComponentPkg");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.pa:PhysicalActorPkg",
        "org.polarsys.capella.core.data.pa:PhysicalComponentPkg");

    // Realization links
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:OperationalActorRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:OperationalEntityRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.la:SystemActorRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.la:SystemRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.pa:LogicalActorRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.pa:LogicalComponentRealization",
        "org.polarsys.capella.core.data.cs:ComponentRealization");

    // System
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:System",
        "org.polarsys.capella.core.data.ctx:SystemComponent");

    // Actors
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.oa:OperationalActor",
        "org.polarsys.capella.core.data.oa:Entity");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:Actor",
        "org.polarsys.capella.core.data.ctx:SystemComponent");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.la:LogicalActor",
        "org.polarsys.capella.core.data.la:LogicalComponent");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.pa:PhysicalActor",
        "org.polarsys.capella.core.data.pa:PhysicalComponent");

    // Capability/Mission Involvements
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:ActorCapabilityInvolvement",
        "org.polarsys.capella.core.data.ctx:CapabilityInvolvement");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:SystemCapabilityInvolvement",
        "org.polarsys.capella.core.data.ctx:CapabilityInvolvement");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:ActorMissionInvolvement",
        "org.polarsys.capella.core.data.ctx:MissionInvolvement");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.ctx:SystemMissionInvolvement",
        "org.polarsys.capella.core.data.ctx:MissionInvolvement");

    // Capability Realization Involvements
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.cs:ActorCapabilityRealizationInvolvement",
        "org.polarsys.capella.core.data.capellacommon:CapabilityRealizationInvolvement");
    OLD_TYPE_2_NEW_TYPE.put("org.polarsys.capella.core.data.cs:SystemComponentCapabilityRealizationInvolvement",
        "org.polarsys.capella.core.data.capellacommon:CapabilityRealizationInvolvement");
  }

  public static final Map<String, String> MAPPINGS = new HashMap<>();
  static {
    MAPPINGS.put("CCRI%20SystemComponentCapabilityRealizationInvolvement", "CCRI%20involvement");
    MAPPINGS.put("CCRI%20ActorCapabilityRealizationInvolvement", "CCRI%20involvement");
    MAPPINGS.put("CCRI%20Actor", "CCRI%20Component");
    MAPPINGS.put("SC_Actor", "SC_System");
    MAPPINGS.put("Logical%20Actors", "LAB%20Logical%20Component");
    MAPPINGS.put("PAB_Actor", "PAB_PC");
  }

  private static final Map<String, String> STYLES = new HashMap<>();
  static {
    STYLES.put("CCRI%20SystemComponentCapabilityRealizationInvolvement", "CCRI%20involvement");
    STYLES.put("CCRI%20ActorCapabilityRealizationInvolvement", "CCRI%20involvement");

    STYLES.put("[name='CCRI%20Actor']/@style", "[name='CCRI%20Component']/@conditionnalStyles.0/@style");
    STYLES.put("[name='SC_Actor']/@style", "[name='SC_System']/@conditionnalStyles.0/@style");

    STYLES.put("[name='Logical%20Actors']/@style", "[name='LAB%20Logical%20Component']/@conditionnalStyles.0/@style");
    STYLES.put("[name='Logical%20Actors']/@conditionnalStyles.0/@style",
        "[name='LAB%20Logical%20Component']/@conditionnalStyles.0/@style");
    STYLES.put("Logical%20Actors", "LAB%20Logical%20Component");

    STYLES.put("[name='PAB_Actor']/@style", "[name='PAB_PC']/@conditionnalStyles.0/@style");
    STYLES.put("[name='PAB_Actor']/@conditionnalStyles.0/@style", "[name='PAB_PC']/@conditionnalStyles.0/@style");
    STYLES.put("[name='PAB_Actor']/@conditionnalStyles.1/@style", "[name='PAB_PC']/@conditionnalStyles.0/@style");
    STYLES.put("PAB_Actor", "PAB_PC");

  }
  private static final List<String> OLD_ACTOR_TYPES = Arrays.asList( //
      "org.polarsys.capella.core.data.oa:OperationalActor", //
      "org.polarsys.capella.core.data.ctx:Actor", //
      "org.polarsys.capella.core.data.la:LogicalActor", //
      "org.polarsys.capella.core.data.pa:PhysicalActor" //
  );

  /**
   * This map is used to lock the Block Architecture Id to its former Context Id.
   * These Ids will be used for the new ComponentPkg elements.
   */
  private Map<String, String> archToOldContextIdsMap = new HashMap<>();

  @Override
  public EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
    UnknownEStructuralFeature featureToTest = new UnknownEStructuralFeature(object.eClass(), name);
    if (UNKNOWNED_FEATURES.contains(featureToTest)) {
      return ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;
    } else if (OLD_FEATURE_2_NEW_FEATURE.containsKey(featureToTest)) {
      return OLD_FEATURE_2_NEW_FEATURE.get(featureToTest);
    }
    return super.getFeature(object, prefix, name, isElement);
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    if (OLD_TYPE_2_NEW_TYPE.containsKey(typeQName)) {
      return OLD_TYPE_2_NEW_TYPE.get(typeQName);
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (currentElement instanceof DRepresentationElement) {
      EStructuralFeature feature = currentElement.eClass().getEStructuralFeature("actualMapping");
      if (feature != null) {
        Object reference = ((EObject) currentElement).eGet(feature);
        if (reference != null && reference instanceof EObject && ((EObject) reference).eIsProxy()) {
          URI uri = migrateMappings(((InternalEObject) reference).eProxyURI());
          if (uri != null) {
            ((InternalEObject) reference).eSetProxyURI(uri);
          }
        }
      }
    } else if (currentElement instanceof Style) {
      StyleDescription reference = ((Style) currentElement).getDescription();
      if (reference != null && reference instanceof EObject && ((EObject) reference).eIsProxy()) {
        URI uri = ((InternalEObject) reference).eProxyURI();
        uri = migrateStyles(uri);
        if (uri != null) {
          ((InternalEObject) reference).eSetProxyURI(uri);
        }
      }
    }
  }

  private URI migrateMappings(URI uri) {
    boolean change = false;
    String fragment = uri.fragment();
    for (String mapping : MAPPINGS.keySet()) {
      if (fragment.contains(mapping)) {
        fragment = fragment.replace(mapping, MAPPINGS.get(mapping));
        change = true;
      }
    }
    if (change) {
      String uriValue = uri.toPlatformString(true);
      return URI.createPlatformPluginURI(uriValue, true).appendFragment(fragment);
    }
    return null;
  }

  private URI migrateStyles(URI uri) {
    boolean change = false;
    String fragment = uri.fragment();
    for (String mapping : STYLES.keySet()) {
      if (fragment.contains(mapping)) {
        fragment = fragment.replace(mapping, STYLES.get(mapping));
        change = true;
      }
    }
    if (change) {
      String uriValue = uri.toPlatformString(true);
      return URI.createPlatformPluginURI(uriValue, true).appendFragment(fragment);
    }
    return null;
  }

  @Override
  public void updateCreatedObject(EObject peekObject, EObject eObject, String typeQName, EStructuralFeature feature,
      XMLResource resource, XMLHelper helper, MigrationContext context) {
    // Actor become Component of Actor type
    if (OLD_ACTOR_TYPES.contains(typeQName) && eObject instanceof Component) {
      ((Component) eObject).setActor(true);
      ((Component) eObject).setHuman(true);
      if (typeQName.equals("org.polarsys.capella.core.data.pa:PhysicalActor") && eObject instanceof PhysicalComponent) {
        ((PhysicalComponent) eObject).setNature(PhysicalComponentNature.NODE);
      }
    }

    else if (eObject instanceof ComponentPkg) {
      ComponentPkg componentPkg = (ComponentPkg) eObject;
      String componentPkgId = null;

      // Store old contexts' ids
      if ("org.polarsys.capella.core.data.oa:OperationalContext".equals(typeQName)
          && componentPkg instanceof EntityPkg) {
        componentPkgId = ((EntityPkg) componentPkg).getId();

      } else if ("org.polarsys.capella.core.data.ctx:SystemContext".equals(typeQName)
          && componentPkg instanceof SystemComponentPkg) {
        componentPkgId = ((SystemComponentPkg) componentPkg).getId();

      } else if ("org.polarsys.capella.core.data.la:LogicalContext".equals(typeQName)
          && componentPkg instanceof LogicalComponentPkg) {
        componentPkgId = ((LogicalComponentPkg) componentPkg).getId();

      } else if ("org.polarsys.capella.core.data.pa:PhysicalContext".equals(typeQName)
          && componentPkg instanceof PhysicalComponentPkg) {
        componentPkgId = ((PhysicalComponentPkg) componentPkg).getId();

      } else if ("org.polarsys.capella.core.data.epbs:EPBSContext".equals(typeQName)
          && componentPkg instanceof ConfigurationItemPkg) {
        componentPkgId = ((ConfigurationItemPkg) componentPkg).getId();
      }

      if (!StringUtil.isNullOrEmpty(componentPkgId)) {
        BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(componentPkg);
        
        if (architecture == null) {
          System.err.println("Null Block Architecture for " + componentPkgId);
          
        } else {
          String architectureId = architecture.getId();
          archToOldContextIdsMap.put(architectureId, componentPkgId);
        }

      }

    }

    super.updateCreatedObject(peekObject, eObject, typeQName, feature, resource, helper, context);
  }

  protected void fusionContainmentReferences(EObject source, EObject target,
      List<EStructuralFeature> excludedFeatures) {
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

    EcoreUtil2.replaceReferencingFeatures(source, target, false, false, excludedFeatures);
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
      reorganizeEPBSArchitecture(epbs);
    }
  }

  protected <T> List<T> filter(List<?> objects, Class<?> clazz) {
    return (List) objects.stream().filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toList());
  }

  /**
   * 1) Move the migrated EPBS System to the Configuration Item Pkg.
   * 
   * 2) Move the migrated EPBS Context to Configuration Item Pkg.
   * 
   * 3) Move Physical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to corresponding packages. If
   * the packages do not exist, create them.
   * 
   * @param epbs
   */
  protected void reorganizeEPBSArchitecture(EPBSArchitecture epbs) {
    if (!epbs.getOwnedMigratedElements().isEmpty()) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg(epbs, true);
      String ePBSContextId = archToOldContextIdsMap.get(epbs.getId());

      if (ePBSContextId != null) {
        ModelElementExt.setObjectId(componentPkg, ePBSContextId);
      }

      List<ModelElement> migratedComponentPkgs = epbs.getOwnedMigratedElements().stream()
          .filter(ConfigurationItemPkg.class::isInstance).collect(Collectors.toList());
      if (migratedComponentPkgs.size() == 1) {
        // We do not want CatalogElementLink's reference to old EPBS Context
        fusionContainmentReferences(migratedComponentPkgs.get(0),
            (ConfigurationItemPkg) BlockArchitectureExt.getComponentPkg(epbs, true),
            Arrays.asList(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET));
      }

      List<ConfigurationItem> items = filter(epbs.getOwnedMigratedElements(), ConfigurationItem.class);
      if (!items.isEmpty()) {
        epbs.getOwnedConfigurationItemPkg().getOwnedConfigurationItems().addAll(0, items);
      }

      List<DataPkg> dataPkgs = filter(epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements(), DataPkg.class);
      if (!dataPkgs.isEmpty()) {
        BlockArchitectureExt.getDataPkg(epbs, true).getOwnedDataPkgs().addAll(dataPkgs);
      }

      List<InterfacePkg> interfacePkgs = filter(epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements(),
          InterfacePkg.class);
      if (!interfacePkgs.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(epbs, true).getOwnedInterfacePkgs().addAll(interfacePkgs);
      }

      List<CapabilityRealizationPkg> capabilityPkgs = filter(
          epbs.getOwnedConfigurationItemPkg().getOwnedMigratedElements(), CapabilityRealizationPkg.class);
      if (!capabilityPkgs.isEmpty()
          && BlockArchitectureExt.getAbstractCapabilityPkg(epbs) instanceof CapabilityRealizationPkg) {
        ((CapabilityRealizationPkg) BlockArchitectureExt.getAbstractCapabilityPkg(epbs))
            .getOwnedCapabilityRealizationPkgs().addAll(capabilityPkgs);
      }
    }
  }

  /**
   * 1) Move the migrated Physical System to the Physical Component Pkg.
   * 
   * 2) Move the migrated Physical Context to Physical Component Pkg.
   * 
   * 3) Move Physical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to corresponding packages.
   * 
   * @param la
   */
  protected void reorganizePhysicalArchitecture(PhysicalArchitecture pa) {
    if (!pa.getOwnedMigratedElements().isEmpty()) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg(pa, true);
      String physicalContextId = archToOldContextIdsMap.get(pa.getId());

      if (physicalContextId != null) {
        ModelElementExt.setObjectId(componentPkg, physicalContextId);
      }

      List<ModelElement> migratedComponentPkgs = pa.getOwnedMigratedElements().stream()
          .filter(PhysicalComponentPkg.class::isInstance).collect(Collectors.toList());
      if (migratedComponentPkgs.size() == 2) {
        // We do not want CatalogElementLink's reference to old Context
        fusionContainmentReferences(migratedComponentPkgs.get(0), pa.getOwnedPhysicalComponentPkg(),
            Arrays.asList(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET));
        // We want to conserve all references to old Physical Actors Pkg
        fusionContainmentReferences(migratedComponentPkgs.get(1), pa.getOwnedPhysicalComponentPkg(),
            Collections.emptyList());
      }

      List<PhysicalComponent> items = filter(pa.getOwnedMigratedElements(), PhysicalComponent.class);
      if (!items.isEmpty()) {
        pa.getOwnedPhysicalComponentPkg().getOwnedPhysicalComponents().addAll(0, items);
      }

      List<DataPkg> dataPkgs = filter(pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements(), DataPkg.class);
      if (!dataPkgs.isEmpty()) {
        BlockArchitectureExt.getDataPkg(pa).getOwnedDataPkgs().addAll(dataPkgs);
      }

      List<InterfacePkg> interfacePkgs = filter(pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements(),
          InterfacePkg.class);
      if (!interfacePkgs.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(pa).getOwnedInterfacePkgs().addAll(interfacePkgs);
      }

      List<CapabilityRealizationPkg> capabilityPkgs = filter(
          pa.getOwnedPhysicalComponentPkg().getOwnedMigratedElements(), CapabilityRealizationPkg.class);
      if (!capabilityPkgs.isEmpty()
          && BlockArchitectureExt.getAbstractCapabilityPkg(pa) instanceof CapabilityRealizationPkg) {
        ((CapabilityRealizationPkg) BlockArchitectureExt.getAbstractCapabilityPkg(pa))
            .getOwnedCapabilityRealizationPkgs().addAll(capabilityPkgs);
      }
    }
  }

  /**
   * 1) Move the migrated Logical System to the Logical Component Pkg.
   * 
   * 2) Move the migrated Logical Context to Logical Component Pkg.
   * 
   * 3) Move Logical Context's DataPkg, InterfacePkg, CapabilityRealizationPkg (if exist) to corresponding packages.
   * 
   * @param la
   */
  protected void reorganizeLogicalArchitecture(LogicalArchitecture la) {
    if (!la.getOwnedMigratedElements().isEmpty()) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg(la, true);
      String logicalContextId = archToOldContextIdsMap.get(la.getId());

      if (logicalContextId != null) {
        ModelElementExt.setObjectId(componentPkg, logicalContextId);
      }

      List<ModelElement> migratedComponentPkgs = la.getOwnedMigratedElements().stream()
          .filter(LogicalComponentPkg.class::isInstance).collect(Collectors.toList());
      if (migratedComponentPkgs.size() == 2) {
        // We do not want CatalogElementLink's reference to old Context
        fusionContainmentReferences(migratedComponentPkgs.get(0), la.getOwnedLogicalComponentPkg(),
            Arrays.asList(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET));
        // We want to conserve all references to old Logical Actors Pkg
        fusionContainmentReferences(migratedComponentPkgs.get(1), la.getOwnedLogicalComponentPkg(),
            Collections.emptyList());
      }

      List<LogicalComponent> items = filter(la.getOwnedMigratedElements(), LogicalComponent.class);
      if (!items.isEmpty()) {
        la.getOwnedLogicalComponentPkg().getOwnedLogicalComponents().addAll(0, items);
      }

      List<DataPkg> dataPkgs = filter(la.getOwnedLogicalComponentPkg().getOwnedMigratedElements(), DataPkg.class);
      if (!dataPkgs.isEmpty()) {
        BlockArchitectureExt.getDataPkg(la).getOwnedDataPkgs().addAll(dataPkgs);
      }

      List<InterfacePkg> interfacePkgs = filter(la.getOwnedLogicalComponentPkg().getOwnedMigratedElements(),
          InterfacePkg.class);
      if (!interfacePkgs.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(la).getOwnedInterfacePkgs().addAll(interfacePkgs);
      }

      List<CapabilityRealizationPkg> capabilityPkgs = filter(
          la.getOwnedLogicalComponentPkg().getOwnedMigratedElements(), CapabilityRealizationPkg.class);
      if (!capabilityPkgs.isEmpty()
          && BlockArchitectureExt.getAbstractCapabilityPkg(la) instanceof CapabilityRealizationPkg) {
        ((CapabilityRealizationPkg) BlockArchitectureExt.getAbstractCapabilityPkg(la))
            .getOwnedCapabilityRealizationPkgs().addAll(capabilityPkgs);
      }
    }
  }

  /**
   * 1) Move the migrated System to the System Component Pkg.
   * 
   * 2) Move the migrated System Context to System Component Pkg.
   * 
   * 3) Move System Context's DataPkg, InterfacePkg, CapabilityPkg (if exist) to corresponding packages.
   * 
   * @param sa
   */
  protected void reorganizeSystemAnalysis(SystemAnalysis sa) {
    if (!sa.getOwnedMigratedElements().isEmpty()) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg(sa, true);
      String systemContextId = archToOldContextIdsMap.get(sa.getId());

      if (systemContextId != null) {
        ModelElementExt.setObjectId(componentPkg, systemContextId);
      }

      List<ModelElement> migratedComponentPkgs = sa.getOwnedMigratedElements().stream()
          .filter(SystemComponentPkg.class::isInstance).collect(Collectors.toList());
      if (migratedComponentPkgs.size() == 2) {
        // We do not want CatalogElementLink's reference to old Context
        fusionContainmentReferences(migratedComponentPkgs.get(0), sa.getOwnedSystemComponentPkg(),
            Arrays.asList(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET));
        // We want to conserve all references to old Actors Pkg
        fusionContainmentReferences(migratedComponentPkgs.get(1), sa.getOwnedSystemComponentPkg(),
            Collections.emptyList());
      }

      List<SystemComponent> items = filter(sa.getOwnedMigratedElements(), SystemComponent.class);
      if (!items.isEmpty()) {
        sa.getOwnedSystemComponentPkg().getOwnedSystemComponents().addAll(0, items);
      }
      List<DataPkg> dataPkgs = filter(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements(), DataPkg.class);
      if (!dataPkgs.isEmpty()) {
        BlockArchitectureExt.getDataPkg(sa).getOwnedDataPkgs().addAll(dataPkgs);
      }

      List<InterfacePkg> interfacePkgs = filter(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements(),
          InterfacePkg.class);
      if (!interfacePkgs.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(sa).getOwnedInterfacePkgs().addAll(interfacePkgs);
      }

      List<CapabilityPkg> capabilityPkgs = filter(sa.getOwnedSystemComponentPkg().getOwnedMigratedElements(),
          CapabilityPkg.class);
      if (!capabilityPkgs.isEmpty() && BlockArchitectureExt.getAbstractCapabilityPkg(sa) instanceof CapabilityPkg) {
        ((CapabilityPkg) BlockArchitectureExt.getAbstractCapabilityPkg(sa)).getOwnedCapabilityPkgs()
            .addAll(capabilityPkgs);
      }
    }
  }

  /**
   * 1) Move migrated Operational Context to Entity Pkg.
   * 
   * 2) Move OperationalContext's DataPkg, InterfacePkg, OperationalCapabilityPkg (if exist) to corresponding packages.
   * 
   * @param oa
   */
  protected void reorganizeOperationalAnalysis(OperationalAnalysis oa) {
    if (!oa.getOwnedMigratedElements().isEmpty()) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg(oa, true);
      String operationalContextId = archToOldContextIdsMap.get(oa.getId());

      if (operationalContextId != null) {
        ModelElementExt.setObjectId(componentPkg, operationalContextId);
      }

      List<ModelElement> migratedComponentPkgs = oa.getOwnedMigratedElements().stream()
          .filter(EntityPkg.class::isInstance).collect(Collectors.toList());
      if (migratedComponentPkgs.size() == 1) {
        // We do not want CatalogElementLink's reference to old Context
        fusionContainmentReferences(migratedComponentPkgs.get(0), oa.getOwnedEntityPkg(),
            Arrays.asList(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET));
      }

      List<DataPkg> dataPkgs = filter(oa.getOwnedEntityPkg().getOwnedMigratedElements(), DataPkg.class);
      if (!dataPkgs.isEmpty()) {
        BlockArchitectureExt.getDataPkg(oa).getOwnedDataPkgs().addAll(dataPkgs);
      }

      List<InterfacePkg> interfacePkgs = filter(oa.getOwnedEntityPkg().getOwnedMigratedElements(), InterfacePkg.class);
      if (!interfacePkgs.isEmpty()) {
        BlockArchitectureExt.getInterfacePkg(oa).getOwnedInterfacePkgs().addAll(interfacePkgs);
      }

      List<OperationalCapabilityPkg> capabilityPkgs = filter(oa.getOwnedEntityPkg().getOwnedMigratedElements(),
          OperationalCapabilityPkg.class);
      if (!capabilityPkgs.isEmpty()) {
        ((OperationalCapabilityPkg) BlockArchitectureExt.getAbstractCapabilityPkg(oa))
            .getOwnedOperationalCapabilityPkgs().addAll(capabilityPkgs);
      }
    }
  }

  /**
   * 1) Move migrated elements into the right container (e.g. System to Component Package).
   * 
   * 2) Clear temporarily OwnedMigratedElements feature.
   * 
   * 3) Delete dangling TransfoLinks (e.g. Transfo Link to old Actors).
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
    if (feature == ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE
        && (value instanceof ComponentPkg)) {
      return true;
    }
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public void dispose(MigrationContext context) {
    archToOldContextIdsMap.clear();
    super.dispose(context);
  }
}
