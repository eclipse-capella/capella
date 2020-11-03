/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.HashMap;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

/**
 * This contribution ensures migration of all prefixes, nsUri, in capella, aird and other resources
 */
public class CapellaMigrationContribution extends AbstractMigrationContribution {

  public static HashMap<String, EPackage> prefixes = new HashMap<String, EPackage>();

  static {

    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.COMPOSITE_STRUCTURE_PREFIX, CsPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.CONTEXT_ARCHITECTURE_PREFIX, CtxPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.EPBS_ARCHITECTURE_PREFIX, EpbsPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.FUNCTIONAL_ANALYSIS_PREFIX, FaPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.INFORMATION_PREFIX, InformationPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.INFORMATION_COMMUNICATION_PREFIX,
        CommunicationPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.INFORMATION_DATATYPE_PREFIX, DatatypePackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.INFORMATION_DATAVALUE_PREFIX,
        DatavaluePackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.INTERACTION_PREFIX, InteractionPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.LOGICAL_ARCHITECTURE_PREFIX, LaPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.CAPELLA_COMMON_PREFIX, CapellacommonPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.CAPELLA_CORE_PREFIX, CapellacorePackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.CAPELLA_MODELLER_PREFIX,
        CapellamodellerPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.OPERATIONAL_ANALYSIS_PREFIX, OaPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.PHYSICAL_ARCHITECTURE_PREFIX, PaPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.PHYSICAL_ARCHITECTURE_DEPLOYMENT_PREFIX,
        DeploymentPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.REQUIREMENT_PREFIX, RequirementPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.SHARED_MODEL_PREFIX, SharedmodelPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.ACTIVITY_PREFIX, ActivityPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.BEHAVIOR_PREFIX, BehaviorPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.MODELLING_CORE_PREFIX, ModellingcorePackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.LIBRARIES_PREFIX, LibrariesPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + MigrationConstants.RE_PREFIX, RePackage.eINSTANCE);
  }

  public static HashMap<String, EPackage> pkgs = new HashMap<String, EPackage>();

  static {
    pkgs.put(MigrationConstants.COMPOSITE_STRUCTURE_OLD_NSURI, CsPackage.eINSTANCE);
    pkgs.put(MigrationConstants.CONTEXT_ARCHITECTURE_OLD_NSURI, CtxPackage.eINSTANCE);
    pkgs.put(MigrationConstants.EPBS_ARCHITECTURE_OLD_NSURI, EpbsPackage.eINSTANCE);
    pkgs.put(MigrationConstants.FUNCTIONAL_ANALYSIS_OLD_NSURI, FaPackage.eINSTANCE);
    pkgs.put(MigrationConstants.INFORMATION_OLD_NSURI, InformationPackage.eINSTANCE);
    pkgs.put(MigrationConstants.INFORMATION_COMMUNICATION_OLD_NSURI, CommunicationPackage.eINSTANCE);
    pkgs.put(MigrationConstants.INFORMATION_DATATYPE_OLD_NSURI, DatatypePackage.eINSTANCE);
    pkgs.put(MigrationConstants.INFORMATION_DATAVALUE_OLD_NSURI, DatavaluePackage.eINSTANCE);
    pkgs.put(MigrationConstants.INTERACTION_OLD_NSURI, InteractionPackage.eINSTANCE);
    pkgs.put(MigrationConstants.LOGICAL_ARCHITECTURE_OLD_NSURI, LaPackage.eINSTANCE);
    pkgs.put(MigrationConstants.CAPELLA_COMMON_OLD_NSURI, CapellacommonPackage.eINSTANCE);
    pkgs.put(MigrationConstants.CAPELLA_CORE_OLD_NSURI, CapellacorePackage.eINSTANCE);
    pkgs.put(MigrationConstants.CAPELLA_MODELLER_OLD_NSURI, CapellamodellerPackage.eINSTANCE);
    pkgs.put(MigrationConstants.OPERATIONAL_ANALYSIS_OLD_NSURI, OaPackage.eINSTANCE);
    pkgs.put(MigrationConstants.PHYSICAL_ARCHITECTURE_OLD_NSURI, PaPackage.eINSTANCE);
    pkgs.put(MigrationConstants.PHYSICAL_ARCHITECTURE_DEPLOYMENT_OLD_NSURI, DeploymentPackage.eINSTANCE);
    pkgs.put(MigrationConstants.REQUIREMENT_OLD_NSURI, RequirementPackage.eINSTANCE);
    pkgs.put(MigrationConstants.SHARED_MODEL_OLD_NSURI, SharedmodelPackage.eINSTANCE);
    pkgs.put(MigrationConstants.ACTIVITY_OLD_NSURI, ActivityPackage.eINSTANCE);
    pkgs.put(MigrationConstants.BEHAVIOR_OLD_NSURI, BehaviorPackage.eINSTANCE);
    pkgs.put(MigrationConstants.MODELLING_CORE_OLD_NSURI, ModellingcorePackage.eINSTANCE);
    pkgs.put(MigrationConstants.LIBRARIES_OLD_NSURI, LibrariesPackage.eINSTANCE);
    pkgs.put(MigrationConstants.RE_OLD_NSURI, RePackage.eINSTANCE);

  }

  @Override
  public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {

    for (String oldNSURI : pkgs.keySet()) {
      packageRegistry.put(oldNSURI, pkgs.get(oldNSURI));
    }

  }

  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {

    if (prefixes.containsKey(prefix)) {
      return XMLResource.XML_NS + ":" + prefixes.get(prefix).getNsPrefix();
    }

    return null;
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {

    if (pkgs.containsKey(nsUri)) {
      return pkgs.get(nsUri).getNsURI();
    }

    return null;
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    if (prefixes.containsKey(XMLResource.XML_NS + ":" + prefix)) {
      return prefixes.get(XMLResource.XML_NS + ":" + prefix).getEFactoryInstance();
    }
    return super.getEFactory(prefix, resource, context);
  }

}
