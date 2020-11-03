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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class EmptyProject extends AbstractDiagramTestCase {
  public static final String PROJECT_EMPTYPROJECT = "3f8ad859-410c-45e2-88af-9a49368644f9";
  public static final String PROJECT_EMPTYPROJECT__LIBRARY_DEPENDENCIES = "755718f0-24e1-4028-92bf-78b62bea42d7";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS = "c4fbaefa-0086-403c-822d-065c51a3b559";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__DRAFT = "40160e86-ee93-4ea3-bb47-f5a5dc3c9665";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__TO_BE_REVIEWED = "ab58c534-cf52-453c-8418-dd2a23723b2e";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__TO_BE_DISCUSSED = "de41020d-cde2-463e-8087-cecc1a48ca66";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__REWORK_NECESSARY = "29e189ab-887d-4a40-8788-0264eef91317";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__UNDER_REWORK = "8ac16912-cae1-4456-aa59-04f074df0af4";
  public static final String PROJECT_EMPTYPROJECT__PROGRESSSTATUS__REVIEWED_OK = "f73f56d9-692d-4779-8f8a-610977c6ddac";
  public static final String PROJECT_EMPTYPROJECT__PROJECTAPPROACH = "0017e26d-9c6d-46bc-9827-a8012e586c67";
  public static final String EMPTYPROJECT = "8aa48fbd-b540-4fcc-8bb0-f52bbdd570b8";
  public static final String OA = "869266ff-6ec4-4024-94c4-3145f58ddde5";
  public static final String OA__OPERATIONAL_ACTIVITIES = "18c5c92d-abe3-46e7-b53a-d2c9ab4b015c";
  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA = "6575a08a-df1a-4f94-9ef1-81029c29c5da";
  public static final String OA__OPERATIONAL_CAPABILITIES = "17272056-0c09-4f18-879c-3abd881be0d7";
  public static final String OA__INTERFACES = "ff8580b4-8866-4660-b09c-2fb74f4e972b";
  public static final String OA__DATA = "fb3e1e54-1c28-4e30-b7a3-bf18e7be1c62";
  public static final String OA__OPERATIONAL_CONTEXT = "bba24bed-20cd-4d6e-b981-12de37a4926d";
  public static final String OA__ROLES = "1e252d42-3f9b-4dd9-853d-d3a4d509a2f9";
  public static final String OA__OPERATIONAL_ENTITIES = "bba24bed-20cd-4d6e-b981-12de37a4926d";
  public static final String SA = "ecc705c5-b32e-4907-b7ed-4e4b43437584";
  public static final String SA__SYSTEM_FUNCTIONS = "33f73053-04e6-4880-863b-e63a691e80f2";
  public static final String SA__ROOT_SF = "f875adac-b475-4f56-8ef5-0f7d3cc43347";
  public static final String SA__ROOT_SF__FUNCTION_REALIZATION_TO_ROOT_OPERATIONAL_ACTIVITY = "da627241-56fa-4843-8d5f-51cad3d311bd";
  public static final String SA__CAPABILITIES = "2e8f8d52-dc83-45a2-b64c-31e676486b4c";
  public static final String SA__INTERFACES = "9520f0ac-db11-4d5c-b464-b679b9d61a7b";
  public static final String SA__DATA = "6b3bec36-904e-4223-98be-41884a26772a";
  public static final String SA__DATA__PREDEFINED_TYPES = "0a7d1139-abbc-41a3-866c-7574c14b7882";
  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN = "5e472859-dc5a-42d1-9f1e-75ef7f16d153";
  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_TRUE = "613816e8-9e6b-4638-b96f-5500c842fd15";
  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_FALSE = "7593fdb1-6c6d-43d8-8eb8-5438fbaa7e07";
  public static final String SA__DATA__PREDEFINED_TYPES__BYTE = "6b7dc7e5-01e6-4408-a73a-1f829c420d4f";
  public static final String SA__DATA__PREDEFINED_TYPES__BYTE__MIN = "1fad7c15-85f0-480f-b32f-51f62a91a733";
  public static final String SA__DATA__PREDEFINED_TYPES__BYTE__MAX = "00e50e12-b2af-4742-93e9-4bf1e0efbb17";
  public static final String SA__DATA__PREDEFINED_TYPES__CHAR = "27c85e58-7db8-406b-b07b-8a0fb837e73d";
  public static final String SA__DATA__PREDEFINED_TYPES__CHAR__MINLENGTH = "85e71bb4-2c5c-49c7-b82a-4778acccffad";
  public static final String SA__DATA__PREDEFINED_TYPES__CHAR__MAXLENGTH = "13a8af28-974b-4a2d-af31-78971377b3ff";
  public static final String SA__DATA__PREDEFINED_TYPES__DOUBLE = "245b3890-5164-4654-b01c-9faf91eac17b";
  public static final String SA__DATA__PREDEFINED_TYPES__FLOAT = "50ad3d00-75bf-45b3-8cc5-69e91d3fabd7";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL = "5126b8ff-d8eb-47b6-9f24-b69789af748f";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MIN = "89794ed9-314a-4522-81cb-936c23190398";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX = "2ab0910b-eb16-4841-8812-d66af28c309a";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND = "58783184-9be0-40ff-bb05-01f5adb2d9ca";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__LEFTOPERAND = "fdde9035-e997-46c7-b55f-1c194114f588";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__RIGHTOPERAND = "c6cfaca0-8c5b-4ca0-9dba-163ec2f73f68";
  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__RIGHTOPERAND = "858192dd-726d-408a-b17e-1af744b28ad2";
  public static final String SA__DATA__PREDEFINED_TYPES__INTEGER = "1fd1f696-b10c-4f80-8c9f-3c58f8083ad7";
  public static final String SA__DATA__PREDEFINED_TYPES__LONG = "2ffdde50-04f6-44de-99c9-3bd89cf43370";
  public static final String SA__DATA__PREDEFINED_TYPES__LONGLONG = "3f36a090-f456-4dfd-b285-7901ecff7937";
  public static final String SA__DATA__PREDEFINED_TYPES__SHORT = "4071a045-61d8-4290-8c3d-0356f1e9d8d1";
  public static final String SA__DATA__PREDEFINED_TYPES__STRING = "812ec374-b591-4a69-a913-ab0e5c099e4e";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER = "cff9b172-e716-43d9-91b8-9c4c1f28a783";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER__MIN = "bfa56d6f-9477-4c43-bc67-ecd7f2c2f166";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT = "2c59dbbb-ad09-4f06-970e-ad46ecfeef97";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT__MIN = "af87ba94-f207-4bff-99f3-613757167d38";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG = "752a464f-9e5e-4319-ae2b-68466bec40ce";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG__MIN = "cf675d5a-aeac-4e84-b863-8728f14516d3";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG = "3223faa0-cefd-42da-9269-1543373fc754";
  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG__MIN = "9675e3ee-1f66-48f8-8d78-1c110f673c63";
  public static final String SA__SYSTEM_CONTEXT = "826533be-e00c-468d-a47e-ba3ee51b4d35";
  public static final String SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM = "148f62ef-77db-48f3-becd-eb9de464a9d3";
  public static final String SA__SYSTEM = "b121dd59-9d3f-4c21-94ae-87e957aaa2a9";
  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE = "b3c846c5-2cc3-4cd6-8c21-aedb71cbb771";
  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION = "555fc1d5-1910-4d77-8158-9302240fe0ca";
  public static final String SA__ACTORS = "56a0fbd3-4a69-4754-b140-0128ae9fef9a";
  public static final String SA__MISSIONS = "080eb8d2-1970-42dd-9540-cacaa1018452";
  public static final String SA__OPERATIONAL_ANALYSIS_REALIZATION_TO_OPERATIONAL_ANALYSIS = "12315a4a-8532-4f54-9167-afee84201526";
  public static final String LA = "fc17c973-223a-4be0-a4a2-c306fce3a2ff";
  public static final String LA__LOGICAL_FUNCTIONS = "5b750a09-4dc7-4675-bf54-039ef8e33f41";
  public static final String LA__ROOT_LF = "c88d639e-8bc5-4bc4-bcbd-46ce26d7a64d";
  public static final String LA__ROOT_LF__FUNCTION_REALIZATION_TO_ROOT_SYSTEM_FUNCTION = "2279a88a-ff80-4a3e-9243-a921b7dc5c36";
  public static final String LA__CAPABILITIES = "d89e7f00-8f98-4a05-a9ee-930307efee73";
  public static final String LA__INTERFACES = "6785bc26-f5a5-49b5-910e-ec0d58b754a2";
  public static final String LA__DATA = "7474ef84-8450-4e03-9ba6-0674ce0b0dbd";
  public static final String LA__LOGICAL_CONTEXT = "a59425f8-6827-48eb-ab41-cbf7e4d7674b";
  public static final String LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM = "ca2e0d95-cba7-4606-8734-00c23f117ce8";
  public static final String LA__LOGICAL_SYSTEM = "32f06455-48c7-4d68-aea5-cf397ab4aff2";
  public static final String LA__SYSTEM_REALIZATION_TO_SYSTEM = "3a899202-b245-46f6-b038-539ee8fcafe3";
  public static final String LA__LOGICAL_ACTORS = "fc4df3b0-14d0-4ca3-8b46-edf92cb43c99";
  public static final String LA__SYSTEM_ANALYSIS_REALIZATION_TO_SYSTEM_ANALYSIS = "07e1899d-02ba-4635-b279-6310870f7406";
  public static final String PA = "17383f8c-fd8a-4d57-8d96-2bf7a7f8e5dd";
  public static final String PA__PHYSICAL_FUNCTIONS = "3586e584-f10e-4d4b-a4ee-a30817d33ee7";
  public static final String PA__ROOT_PF = "dc79c9ef-cb81-4b2d-b9dc-0e1603cbfaba";
  public static final String PA__ROOT_PF__FUNCTION_REALIZATION_TO_ROOT_LOGICAL_FUNCTION = "82e62d74-7e24-41f9-b409-3638e1d7c2a0";
  public static final String PA__CAPABILITIES = "b02dd33a-ffa1-47fa-96dc-fa0863aab9ee";
  public static final String PA__INTERFACES = "fe0c0b8b-fb65-49c5-a104-ceb45bc9d285";
  public static final String PA__DATA = "dbaec066-1f9c-43e3-9ce1-b295c1e7fc8c";
  public static final String PA__PHYSICAL_CONTEXT = "9a842ee5-662a-4534-a70a-d9241779d0ba";
  public static final String PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM = "3815ea53-abad-4700-a5e3-ae72585f31d8";
  public static final String PA__PHYSICAL_SYSTEM = "16953dc7-be25-45ef-9da9-32869d0cb53f";
  public static final String PA__LOGICAL_COMPONENT_REALIZATION_TO_LOGICAL_SYSTEM = "74ab6b1c-938f-426b-bf1c-e96800818ef9";
  public static final String PA__PHYSICAL_ACTORS = "54d762b5-2914-4cec-a8a5-02702956a051";
  public static final String PA__LOGICAL_ARCHITECTURE_REALIZATION_TO_LOGICAL_ARCHITECTURE = "a99c8315-3679-445a-9820-c0d23d2850a5";

  public static final String EPBS = "38f9a505-2d82-4963-8b93-2f5226a4f057";
  public static final String EPBS__CAPABILITIES = "eff0c767-1e47-441f-ab17-2515e90346db";
  public static final String EPBS__EPBS_CONTEXT = "21136eb8-5933-4b30-ac29-ca20f0ef4ba8";
  public static final String EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM = "df9e2025-ed4c-4387-a520-325d2124e1c7";
  public static final String EPBS__SYSTEMCI_SYSTEM = "00ed7f24-a97b-45e5-922e-27d8aa3d8e12";
  public static final String EPBS__SYSTEMCI_SYSTEM__PHYSICAL_ARTIFACT_REALIZATION_TO_PHYSICAL_SYSTEM = "92a030d2-168c-429a-bbe0-675417994c8a";
  public static final String EPBS__PHYSICAL_ARCHITECTURE_REALIZATION_TO_PHYSICAL_ARCHITECTURE = "801cb740-ffa1-4085-931e-aeb8b7f09be3";

  @Override
  public String getRequiredTestModel() {
    return EmptyProject.class.getSimpleName();
  }

  public String getRootFunctionId(BlockArchitectureExt.Type type) {
    String rootFunction = "";
    switch (type) {
    case OA:
      rootFunction = OA__OPERATIONAL_ACTIVITIES__ROOT_OA;
      break;
    case SA:
      rootFunction = SA__ROOT_SF;
      break;
    case LA:
      rootFunction = LA__ROOT_LF;
      break;
    case PA:
      rootFunction = PA__ROOT_PF;
      break;
    default:
      break;
    }

    return rootFunction;
  }

  public String getContextId(BlockArchitectureExt.Type type) {
    String rootFunction = "";
    switch (type) {
    case OA:
      rootFunction = OA__OPERATIONAL_CONTEXT;
      break;
    case SA:
      rootFunction = SA__SYSTEM_CONTEXT;
      break;
    case LA:
      rootFunction = LA__LOGICAL_CONTEXT;
      break;
    case PA:
      rootFunction = PA__PHYSICAL_CONTEXT;
      break;
    default:
      break;
    }

    return rootFunction;
  }

  public String getCapabilitiesId(BlockArchitectureExt.Type type) {
    String capabilitiesId = "";
    switch (type) {
    case OA:
      capabilitiesId = EmptyProject.OA__OPERATIONAL_CAPABILITIES;
      break;
    case SA:
      capabilitiesId = EmptyProject.SA__CAPABILITIES;
      break;
    case LA:
      capabilitiesId = EmptyProject.LA__CAPABILITIES;
      break;
    case PA:
      capabilitiesId = EmptyProject.PA__CAPABILITIES;
      break;
    default:
      break;
    }
    return capabilitiesId;
  }

  public String getInterfacesId(BlockArchitectureExt.Type type) {
    String interfacesId = "";
    switch (type) {
    case SA:
      interfacesId = EmptyProject.SA__INTERFACES;
      break;
    case LA:
      interfacesId = EmptyProject.LA__INTERFACES;
      break;
    case PA:
      interfacesId = EmptyProject.PA__INTERFACES;
      break;
    default:
      break;
    }
    return interfacesId;
  }
}
