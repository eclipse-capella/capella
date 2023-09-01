/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.transition.ju.model.EmptySkeletonProject;

/**
 * This test ensure that default elements (skeleton) are correctly propagated and named if they were deleted in the target architecture
 */
public class SkeletonElementsNames extends EmptySkeletonProject {

  private static String CUSTOM_NAME = "CUSTOM";

  @Override
  public void performTest() throws Exception {
    checkRootFunctionPkg(OA);
    checkRootFunction(OA);
    checkRootFunctionPkg(SA);
    checkRootFunction(SA);
    checkRootFunctionPkg(LA);
    checkRootFunction(LA);

    checkActorPkg(SA);
    checkActorPkg(LA);
    
    checkDataPkg(SA);
    checkDataPkg(LA);
    
    checkInterfacePkg(SA);
    checkInterfacePkg(LA);

    checkCapabilityPkg(OA);
    checkCapabilityPkg(SA);
    checkCapabilityPkg(LA);
  }

  protected void setName(String elementId, String name) {
    ((AbstractNamedElement)getObject(elementId)).setName(name);
  }
  
  protected void checkRootFunctionPkg(String containerId) {

    SkeletonHelper.createFunctionPkg(containerId, GenericModel.FUNCTION_PKG, context);

    //Check default name
    EObject result = null;
    performFunctionalTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.FUNCTION_PKG);
    checkDefaultName(result);
    EObjectHelper.removeElement(result);

    //Check custom name
    setName(GenericModel.FUNCTION_PKG, CUSTOM_NAME);
    performFunctionalTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.FUNCTION_PKG);
    mustBeNamed(result, CUSTOM_NAME);
    EObjectHelper.removeElement(result);

    EObjectHelper.removeElement(GenericModel.FUNCTION_PKG, context);
  }

  protected void checkRootFunction(String containerId) {

    SkeletonHelper.createFunctionPkg(containerId, GenericModel.FUNCTION_PKG, context);
    SkeletonHelper.createRootFunction(GenericModel.FUNCTION_PKG, GenericModel.FUNCTION_1, context);

    //Check default name
    EObject result = null;
    performFunctionalTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.FUNCTION_1);
    checkDefaultName(result);
    EObjectHelper.removeElement(result);

    //Check custom name
    setName(GenericModel.FUNCTION_1, CUSTOM_NAME);
    performFunctionalTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.FUNCTION_1);
    mustBeNamed(result, CUSTOM_NAME);
    EObjectHelper.removeElement(result);

    EObjectHelper.removeElement(GenericModel.FUNCTION_PKG, context);
  }

  protected void checkActorPkg(String containerId) {
    boolean isTransitionable = TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(containerId));
    assertFalse("Can not perform actor transition on Architecture without any actor under it", isTransitionable);
  }
  
  protected void checkDataPkg(String containerId) {

    SkeletonHelper.createDataPkg(containerId, GenericModel.DATA_PKG, context);

    //Check default name
    EObject result = null;
    performDataTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.DATA_PKG);
    checkDefaultName(result);
    EObjectHelper.removeElement(result);

    //Check custom name
    setName(GenericModel.DATA_PKG, CUSTOM_NAME);
    performDataTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.DATA_PKG);
    mustBeNamed(result, CUSTOM_NAME);
    EObjectHelper.removeElement(result);

    EObjectHelper.removeElement(GenericModel.DATA_PKG, context);
  }

  protected void checkInterfacePkg(String containerId) {

    SkeletonHelper.createInterfacePkg(containerId, GenericModel.INTERFACE_PKG, context);

    //Check default name
    EObject result = null;
    performInterfaceTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.INTERFACE_PKG);
    //On Logical Architecture / Physical Architecture, the InterfacePkg is located inside 
    //the root package and have same name with a suffix
    startWithName(result, NamingConstants.CreateCommonCmd_interfaces_pkg_name); 
    EObjectHelper.removeElement(result);

    //Check custom name
    setName(GenericModel.INTERFACE_PKG, CUSTOM_NAME);
    performInterfaceTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.INTERFACE_PKG);
    startWithName(result, CUSTOM_NAME);
    EObjectHelper.removeElement(result);

    EObjectHelper.removeElement(GenericModel.INTERFACE_PKG, context);
  }
  
  protected void checkCapabilityPkg(String containerId) {

    SkeletonHelper.createCapabilityPkg(containerId, GenericModel.CAPABILITY_PKG, context);

    //Check default name
    EObject result = null;
    performCapabilityTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.CAPABILITY_PKG);
    checkDefaultName(result);
    EObjectHelper.removeElement(result);

    //Check custom name
    setName(GenericModel.CAPABILITY_PKG, CUSTOM_NAME);
    performCapabilityTransition(getObjects(containerId));
    result = mustBeTransitioned(GenericModel.CAPABILITY_PKG);
    mustBeNamed(result, CUSTOM_NAME);
    EObjectHelper.removeElement(result);

    EObjectHelper.removeElement(GenericModel.CAPABILITY_PKG, context);
  }
  
  protected void startWithName(EObject result, String name) {
    assertTrue(((AbstractNamedElement)result).getName().startsWith(name));
  }
  
  /**
   * Check if the given skeleton element has the correct default name
   */
  protected void checkDefaultName(EObject result) {

    if (result instanceof OperationalActivityPkg) {
      mustBeNamed(result, NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name);

    } else if (result instanceof SystemFunctionPkg) {
      mustBeNamed(result, NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name);

    } else if (result instanceof LogicalFunctionPkg) {
      mustBeNamed(result, NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name);

    } else if (result instanceof PhysicalFunctionPkg) {
      mustBeNamed(result, NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);

    } else if (result instanceof OperationalActivity) {
      mustBeNamed(result, NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name);

    } else if (result instanceof SystemFunction) {
      mustBeNamed(result, NamingConstants.CreateSysAnalysisCmd_system_function_root_name);

    } else if (result instanceof LogicalFunction) {
      mustBeNamed(result, NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name);

    } else if (result instanceof PhysicalFunction) {
      mustBeNamed(result, NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);

    } else if (result instanceof EntityPkg) {
      mustBeNamed(result, NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name);

    } else if (result instanceof SystemComponentPkg) {
      mustBeNamed(result, NamingConstants.CreateSysAnalysisCmd_actors_pkg_name);

    } else if (result instanceof LogicalComponentPkg) {
      mustBeNamed(result, NamingConstants.CreateLogicalArchCmd_actors_pkg_name);

    } else if (result instanceof PhysicalComponentPkg) {
      mustBeNamed(result, NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);

    } else if (result instanceof OperationalCapabilityPkg) {
      mustBeNamed(result, NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name);

    } else if (result instanceof CapabilityPkg) {
      mustBeNamed(result, NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name);

    } else if (result instanceof CapabilityRealizationPkg) {
      mustBeNamed(result, NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);

    } else if (result instanceof MissionPkg) {
      mustBeNamed(result, NamingConstants.CreateSysAnalysisCmd_missions_pkg_name);

    } else if (result instanceof DataPkg) {
      mustBeNamed(result, NamingConstants.CreateCommonCmd_data_pkg_name);

    } else if (result instanceof InterfacePkg) {
      mustBeNamed(result, NamingConstants.CreateCommonCmd_interfaces_pkg_name);

    }
  }
}
