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
package org.polarsys.capella.test.transition.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public abstract class EmptySkeletonProject extends TopDownTransitionTestCase {

  public static final String PROJECT_EMPTYSKELETONPROJECT = "6a38e008-9ce3-4c35-82bd-cbcd98e9d5d9";
  public static final String PROJECT_EMPTYSKELETONPROJECT__LIBRARY_DEPENDENCIES = "7770c9d3-4339-459a-b6b8-762385796f67";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS = "8fb26640-b3b2-48af-beba-86061ee08876";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__DRAFT = "c25c20af-e8f4-4439-8ac1-f41de5347567";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__TO_BE_REVIEWED = "fddffa42-40ef-4821-973a-98c91e5033ab";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__TO_BE_DISCUSSED = "dfbf5e4a-b7e1-48df-a770-6861856a65ad";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__REWORK_NECESSARY = "f472108b-ea93-4192-aa8f-98538cae0b7a";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__UNDER_REWORK = "3a9c029c-0a67-43bd-ab8c-25fa0e95db6c";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROGRESSSTATUS__REVIEWED_OK = "b353e44c-5d47-4957-95eb-c572a7621a15";
  public static final String PROJECT_EMPTYSKELETONPROJECT__PROJECTAPPROACH = "69f7664c-75d4-4c73-b6a8-1423f280392a";
  public static final String EMPTYSKELETONPROJECT = "be852797-3d9b-400d-b720-af79bba0a1fe";
  public static final String OA = "c2c5e86f-7100-45cd-af40-b89a2c78c212";
  public static final String OA__OPERATIONAL_CONTEXT = "fb2f7029-fdc9-400e-80fd-d3eedc6b30ba";
  public static final String SA = "70205a59-a948-4d13-90d7-f42bd45369e3";
  public static final String SA__SYSTEM_CONTEXT = "560fe3fc-bb2a-4e34-912c-dd5e7a802a12";
  public static final String SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM = "fb8e58a8-8f76-44c9-84fa-0bf97653cd9e";
  public static final String SA__SYSTEM = "8c824526-f0ad-4c88-a7d2-4fefeca44190";
  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE = "9bcd9a85-c896-47c2-ab1d-e69fd6b730d7";
  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION = "32057dc6-2618-423e-8db0-21238cf0e544";
  public static final String SA__OPERATIONAL_ANALYSIS_REALIZATION_TO_OPERATIONAL_ANALYSIS = "86813511-74a9-4d35-ac28-3d50d603dc77";
  public static final String LA = "9268f7fc-10e6-4f7c-bdec-cc843c177891";
  public static final String LA__LOGICAL_CONTEXT = "fd4d2302-1100-4569-b329-f842d1fff95d";
  public static final String LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM = "34c704bc-c8fb-4be4-b90d-7c0bd1f901cc";
  public static final String LA__LOGICAL_SYSTEM = "ec71f4ec-db1a-4094-8c96-d3b1f8df893d";
  public static final String LA__SYSTEM_REALIZATION_TO_SYSTEM = "105f41de-010c-4c93-b5b7-5fc31c1078f3";
  public static final String LA__SYSTEM_ANALYSIS_REALIZATION_TO_SYSTEM_ANALYSIS = "9e70b0d1-1827-46a1-8021-450afab1ad8c";
  public static final String PA = "2fe6705a-84fb-4369-892c-4144b97e220c";
  public static final String PA__PHYSICAL_CONTEXT = "973026ac-d734-4fea-8473-42d5fe9986ea";
  public static final String PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM = "5877e7bd-a42f-4fbc-9d41-e4ff959900af";
  public static final String PA__PHYSICAL_SYSTEM = "7c89ed72-00bc-4d10-aa23-e9459f7c0b29";
  public static final String PA__LOGICAL_COMPONENT_REALIZATION_TO_LOGICAL_SYSTEM = "4c2c41c6-6a56-4446-991c-258a52d4c73f";
  public static final String PA__LOGICAL_ARCHITECTURE_REALIZATION_TO_LOGICAL_ARCHITECTURE = "a95273b7-66a2-4d50-ac08-3f9122a57b57";
  public static final String EPBS = "1b893bc0-1d43-47e2-8871-6ac3006a2e8a";
  public static final String EPBS__CAPABILITIES = "44a6d8bd-febb-4323-913b-38cc76522721";
  public static final String EPBS__EPBS_CONTEXT = "6d0dde45-6ecf-4f75-812c-c3240e980343";
  public static final String EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM = "eb4895d7-f671-4651-9d5b-9fec571ebe29";
  public static final String EPBS__SYSTEMCI_SYSTEM = "c2bc36ee-bbcb-4b64-89f3-21ee028136cd";
  public static final String EPBS__SYSTEMCI_SYSTEM__PHYSICAL_ARTIFACT_REALIZATION_TO_PHYSICAL_SYSTEM = "8bf8ebb9-36ba-4a3c-a712-b5688f8fb3ad";
  public static final String EPBS__PHYSICAL_ARCHITECTURE_REALIZATION_TO_PHYSICAL_ARCHITECTURE = "873b6cfd-41c8-45f8-a6f3-413f20240a46";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(EmptySkeletonProject.class.getSimpleName());
  }

}
