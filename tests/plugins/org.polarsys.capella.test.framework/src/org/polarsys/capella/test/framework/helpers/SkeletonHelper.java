/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SkeletonHelper {

  public static void createFunctionPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      createObject(elementId, containerId,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG,
          NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name, context);

    } else if (container instanceof SystemAnalysis) {
      createObject(elementId, containerId,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          CtxPackage.Literals.SYSTEM_FUNCTION_PKG, NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name,
          context);
      
    } else if (container instanceof LogicalArchitecture) {
      createObject(elementId, containerId,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          LaPackage.Literals.LOGICAL_FUNCTION_PKG, NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name,
          context);
      
    } else if (container instanceof PhysicalArchitecture) {
      createObject(elementId, containerId,
          FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          PaPackage.Literals.PHYSICAL_FUNCTION_PKG, NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name,
          context);
    }

  }

  public static void createRootFunction(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalActivityPkg) {
      createObject(elementId, containerId,
          OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES,
          OaPackage.Literals.OPERATIONAL_ACTIVITY, NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name,
          context);

    } else if (container instanceof SystemFunctionPkg) {
      createObject(elementId, containerId,
          CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS, CtxPackage.Literals.SYSTEM_FUNCTION,
          NamingConstants.CreateSysAnalysisCmd_system_function_root_name, context);
      
    } else if (container instanceof LogicalFunctionPkg) {
      createObject(elementId, containerId,
          LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS, LaPackage.Literals.LOGICAL_FUNCTION,
          NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name, context);
      
    } else if (container instanceof PhysicalFunctionPkg) {
      createObject(elementId, containerId,
          PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS, PaPackage.Literals.PHYSICAL_FUNCTION,
          NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name, context);
    }
    
  }

  public static void createCapabilityPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG,
          OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG, NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name,
          context);

    } else if (container instanceof SystemAnalysis) {
      createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, CtxPackage.Literals.CAPABILITY_PKG,
          NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name, context);
      
    } else if (container instanceof LogicalArchitecture) {
      createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, LaPackage.Literals.CAPABILITY_REALIZATION_PKG,
          NamingConstants.CreateCommonCmd_capability_realisation_pkg_name, context);
      
    } else if (container instanceof PhysicalArchitecture) {
      createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, LaPackage.Literals.CAPABILITY_REALIZATION_PKG,
          NamingConstants.CreateCommonCmd_capability_realisation_pkg_name, context);
    }
  }
  
  public static void createActorPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      createObject(elementId, containerId,
          OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, OaPackage.Literals.ENTITY_PKG,
          NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name, context);
      
    } else  if (container instanceof SystemAnalysis) {
      createObject(elementId, containerId,
          CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, CtxPackage.Literals.ACTOR_PKG,
          NamingConstants.CreateSysAnalysisCmd_actors_pkg_name, context);
      
    } else if (container instanceof LogicalArchitecture) {
      createObject(elementId, containerId,
          LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, LaPackage.Literals.LOGICAL_ACTOR_PKG,
          NamingConstants.CreateLogicalArchCmd_actors_pkg_name, context);
      
    } else if (container instanceof PhysicalArchitecture) {
      createObject(elementId, containerId,
          PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, PaPackage.Literals.PHYSICAL_ACTOR_PKG,
          NamingConstants.CreatePhysicalArchCmd_actors_pkg_name, context);
    }
  }
  
  public static void createDataPkg(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, InformationPackage.Literals.DATA_PKG,
          NamingConstants.CreateCommonCmd_data_pkg_name, context);
  }

  public static void createInterfacePkg(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId,
          CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, CsPackage.Literals.INTERFACE_PKG,
          NamingConstants.CreateCommonCmd_interfaces_pkg_name, context);
  }
  

  public static EObject createObject(final String id, final String containerId, final EStructuralFeature feature,
      final EClass clazz, final String name, final SessionContext context) {

    context.getExecutionManager().execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        EObject container = context.getSemanticElement(containerId);
        EObject result = ((EPackage) clazz.eContainer()).getEFactoryInstance().create(clazz);
        if (feature.isMany()) {
          ((EList) container.eGet(feature)).add(result);
        } else {
          container.eSet(feature, result);
        }

        if (result instanceof AbstractNamedElement) {
          ((AbstractNamedElement) result).setName(name);
        }
        context.putSemanticElement(id, result);
      }
    });

    return context.getSemanticElement(id);
  }


}
