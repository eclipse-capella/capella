/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.test.framework.model.IdentifiableModelProject;
import org.polarsys.capella.test.framework.model.Identifier;

public abstract class NavigatorEmptyProject extends IdentifiableModelProject {
  
  public @Identifier(id="1cf74c51-808b-45de-abcf-0162dd84a239") Project NAVIGATOREMPTYPROJECT;
  public @Identifier(id="22a15265-0e59-4fe2-9398-a3fe1ac41a3e") SystemEngineering EMPTYPROJECT;
  public @Identifier(id="3d35bf9f-4e1e-47d5-a9bf-f6d3f89da372") OperationalAnalysis OPERATIONAL_ANALYSIS;
  public @Identifier(id="02616508-9083-49f2-94dd-adb957784642") OperationalActivityPkg OA_OPERATIONAL_ACTIVITIES;
  public @Identifier(id="9c65267d-b80d-4be7-997a-18e064cee8bd") OperationalActivity ROOT_OPERATIONAL_ACTIVITY;
  public @Identifier(id="731fa50f-434d-4af9-a7f0-f9e64df50bba") OperationalCapabilityPkg OA_OPERATIONAL_CAPABILITIES;
  public @Identifier(id="a4a5c153-d1fa-4647-bd54-eb64435f5e69") InterfacePkg OA_INTERFACES;
  public @Identifier(id="d5480652-075a-4178-9993-7af5f84eb075") DataPkg OA_DATA;
  public @Identifier(id="bbe761e2-e1fc-4186-a479-d2e645abf8a0") RolePkg OA_ROLES;
  public @Identifier(id="95f5ba8b-5570-4c16-9071-31b482d75b44") EntityPkg OA_OPERATIONAL_ENTITIES;
  public @Identifier(id="a0c778a5-5614-48b2-b44e-3ddf10a2f2db") SystemAnalysis SYSTEM_ANALYSIS;
  public @Identifier(id="526f5a5b-aba8-4137-aa8c-86392c6c6006") SystemFunctionPkg SA_SYSTEM_FUNCTIONS;
  public @Identifier(id="614ce7db-028f-4162-ad8c-b6b39ab70ee9") SystemFunction ROOT_SYSTEM_FUNCTION;
  public @Identifier(id="cbcd5a12-e090-4022-87ae-f9c486fc669a") CapabilityPkg SA_CAPABILITIES;
  public @Identifier(id="fc88ac27-df8c-46ac-9a45-7fa2ae1b1781") InterfacePkg SA_INTERFACES;
  public @Identifier(id="8c3b544f-5934-431c-a425-b875be175d04") DataPkg SA_DATA;
  public @Identifier(id="231d5107-d9ed-4eff-acbd-49c6d9e30f71") SystemComponentPkg SA_STRUCTURE;
  public @Identifier(id="07f440fd-2202-439c-a10a-52cf32bb671f") SystemComponent SYSTEM;
  public @Identifier(id="8145bb87-a943-4951-a7be-a51c5518fc55") MissionPkg SA_MISSIONS;
  public @Identifier(id="1114a7bc-b859-48df-82eb-564c6c8df624") LogicalArchitecture LOGICAL_ARCHITECTURE;
  public @Identifier(id="2fdadd61-e48c-436b-98a5-71497bfebd24") LogicalFunctionPkg LA_LOGICAL_FUNCTIONS;
  public @Identifier(id="74f6ad70-0302-4ef9-aa94-13ca7f3ba809") LogicalFunction ROOT_LOGICAL_FUNCTION;
  public @Identifier(id="14f2f349-8bf9-45b8-9416-a73a22322ce1") CapabilityRealizationPkg LA_CAPABILITIES;
  public @Identifier(id="5fa52b43-6f33-4eb6-ad33-6e53f4a635d5") InterfacePkg LA_INTERFACES;
  public @Identifier(id="88adec8d-848c-4002-887b-21e5495ab294") DataPkg LA_DATA;
  public @Identifier(id="711b6fd9-25a4-4f75-a276-e2ad768b475a") LogicalComponentPkg LA_STRUCTURE;
  public @Identifier(id="a3bc8c18-37f1-4b61-9e7c-759cab3e845f") LogicalComponent LOGICAL_SYSTEM;
  public @Identifier(id="cfc940a9-48a5-46a6-ab37-fca484d1df87") ComponentRealization COMPONENT_REALIZATION_TO_SYSTEM;
  public @Identifier(id="a434c206-3822-4a94-a241-4d9fc2d36c2d") PhysicalArchitecture PHYSICAL_ARCHITECTURE;
  public @Identifier(id="ac2ff900-7e6f-41a2-ae34-7af6e47beaed") PhysicalFunctionPkg PA_PHYSICAL_FUNCTIONS;
  public @Identifier(id="7214c258-b6ee-4636-a8ad-e82b02dfb971") PhysicalFunction ROOT_PHYSICAL_FUNCTION;
  public @Identifier(id="3a4d0e6d-11da-447c-9635-48a411b9e0d0") CapabilityRealizationPkg PA_CAPABILITIES;
  public @Identifier(id="02986fe7-6a6f-4ca3-80aa-70a74d168c78") InterfacePkg PA_INTERFACES;
  public @Identifier(id="b02a9544-339e-4f89-99fe-dd2b6f85d202") DataPkg PA_DATA;
  public @Identifier(id="0633f5d2-8cab-4361-9697-cd3640aff36f") PhysicalComponentPkg PA_STRUCTURE;
  public @Identifier(id="971368d2-cea3-4c53-b16f-b250a0b800aa") PhysicalComponent PHYSICAL_SYSTEM;
  public @Identifier(id="cef65f7a-b239-49e7-bf4b-b3f9c03ec310") EPBSArchitecture EPBS_ARCHITECTURE;
  public @Identifier(id="23150452-5e78-471c-94b5-abc34de9adc8") CapabilityRealizationPkg EPBS_CAPABILITIES;
  public @Identifier(id="cd952456-562b-4664-9726-80c9e5573aea") ConfigurationItemPkg EPBS_STRUCTURE;
  public @Identifier(id="8399b78d-e668-4934-970d-8516b02d5db0") Part SYSTEM__SYSTEM;
  public @Identifier(id="d1cb578f-2c6a-42fc-baa4-99be1529970f") ConfigurationItem SYSTEMCI_SYSTEM;
  
  
  
  
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(NavigatorEmptyProject.class.getSimpleName());
  }
  
  @Override
  protected Class<?> getModelClass() {
    return NavigatorEmptyProject.class;
  }
  
}