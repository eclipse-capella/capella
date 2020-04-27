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
package org.polarsys.capella.test.fragmentation.ju.messages;

public class FragmentationMessages {
  public static String abstractFragmentationTest_EObjectNotSetted = "The target EObject has not been set (yet?)";
  public static String abstractFragmentationTest_UnsupportedKindOfdRepresentation = "The DRepresentation ''{0}'' is not a DSemanticDecorator one. Please modify the test.";
  public static String abstractFragmentationTest_wrongAirdresourceAfterOps = "The DRepresentation ''{0}'' has not been moved at all or to the good AIRD resource.";
  public static String abstractFragmentTest_isAlreadyFragmented = "The element ''{0}'' is already fragmented.";
  public static String abstractFragmentTest_hasNotItsOwnResource = "The element ''{0}'' has just been fragmented but it has not its own eResource attribute set.";
  public static String abstractFragmentTest_hasNotSameNumbersOfchildrenAfterFragmentation = "The fragmentation of the element ''{0}'' change the number of its proper children";
  public static String abstractFragmentationTest_dRepresentationToMoveDoesNotMatch = "The DRepresentation ''{0}'' is not linked (directly or indirectly) to the object to fragment.  ";
  public static String abstractUnfragmentTest_isNotFragmented = "The element ''{0}'' is not fragmented.";
  public static String abstractunfragmentTest_hasItsOwnResource = " The element ''{0}'' has just been unfragmented but it has its own eResource attribute set.";
  public static String fragmentUtils_resourceDoesNotMatch = "The Object ''{0}'' is contained into the resource ''{1}'' instead of ''{2}''.";
  public static String fragmentUtils_numberOfRefDoesNotMatch = "The number of references to ''{0}'' into the resourceSet does not match.";
  public static String nullSession = "The tested session is null";
  public static String duplicatedModelElements = "There are duplicated elements in the model: {0}";
  public static String wrongFragmentsNumber = "{0} fragments number is {1} whereas {2} are expected.";
  public static String wrongTablesDiagramsNumber = "{0} fragment has {1} tables and diagrams whereas {2} are expected";
  public static String errorInGetFile = "cannot get the file at the following path: {0}";
  public static String wrongModelElementsNumber = "{0} fragment has {1} model elements in semantic model elements whereas {2} are expected";
  public static String resourceStatusNotSync = "The resource {0} is in {1}. That makes the session in dirty.";
  public static String wrongNumberOfDiagramElements = "{0} diagram has {1} elements whereas {2} are expected";
  public static String wrongNumberOfColumns = "{0} table has {1} columns whereas {2} are expected";
  public static String wrongNumberOfLines = "{0} table has {1} lines whereas {2} are expected";
  public static String airdName = "sysmodel.aird";
  public static String m2Name = "sysmodel.melodymodeller";
  public static String RSFairdName = "SA-System Functions-RSF.airdfragment";
  public static String RSFm2Name = "SA-System Functions-RSF.melodyfragment";
  public static String SF1airdName = "SA-System Functions-RSF-SF1.airdfragment";
  public static String SF1m2Name = "SA-System Functions-RSF-SF1.melodyfragment";
  public static String LAairdfragment = "LA.airdfragment";
  public static String LAmelodyfragment = "LA.melodyfragment";
  public static String RLF_OA2airdfragment = "LA-Logical Functions-RLF-OA2.airdfragment";
  public static String RLF_OA2melodyfragment = "LA-Logical Functions-RLF-OA2.melodyfragment";
  public static String RLF_OA2_SysOA2_1airdfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1.airdfragment";
  public static String RLF_OA2_SysOA2_1melodyfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1.melodyfragment";
  public static String RLF_OA2_SysOA2_1_LogicalFunctionPkg1airdfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1-LogicalFunctionPkg 1.airdfragment";
  public static String RLF_OA2_SysOA2_1_LogicalFunctionPkg1melodyfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1-LogicalFunctionPkg 1.melodyfragment";
  
  public static String LC2airdfragment = "LA-Logical System-LC2.airdfragment";
  public static String LC2melodyfragment = "LA-Logical System-LC2.melodyfragment";
  
  public static String LC2_Capabilitiesairdfragment = "LA-Structure-Logical System-LC2-Capabilities.airdfragment";
  public static String LC2_Capabilitiesmelodyfragment = "LA-Structure-Logical System-LC2-Capabilities.melodyfragment";
  public static String LC3airdfragment = "LA-Structure-Logical System-LC3.airdfragment";
  public static String LC3melodyfragment = "LA-Structure-Logical System-LC3.melodyfragment";
  public static String LC3_3airdfragment = "LA-Structure-Logical System-LC3-LC3_3.airdfragment";
  public static String LC3_3melodyfragment = "LA-Structure-Logical System-LC3-LC3_3.melodyfragment";
  public static String LC3_LC3_1airdfragment = "LA-Structure-Logical System-LC3-LC3_1.airdfragment";
  public static String LC3_LC3_1melodyfragment = "LA-Structure-Logical System-LC3-LC3_1.melodyfragment";
  public static String LA_LogicalSystemairdfragment = "LA-Structure-Logical System.airdfragment";
  public static String LA_LogicalSystemmelodyfragment = "LA-Structure-Logical System.melodyfragment";
  public static String UC1FromLogicalSystemairdfragment = "LA-Structure-Logical System-LC2-Capabilities-UC1 From Logical System.airdfragment";
  public static String UC1FromLogicalSystemmelodyfragment = "LA-Structure-Logical System-LC2-Capabilities-UC1 From Logical System.melodyfragment";
  
  public static String LF2_3airdfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1-LogicalFunctionPkg 1-LF2_3.airdfragment";
  public static String LF2_3melodyfragment = "LA-Logical Functions-RLF-OA2-SysOA2_1-LogicalFunctionPkg 1-LF2_3.melodyfragment";
  public static String PAairdfragment = "PA.airdfragment";
  public static String PAmelodyfragment = "PA.melodyfragment";
  public static String PhysicalSystemairdfragment = "PA-Physical System.airdfragment";
  public static String PhysicalSystemmelodyfragment = "PA-Physical System.melodyfragment";
  public static String OC1airdfragment = "SA-Capabilities-OC1.airdfragment";
  public static String OC1melodyfragment = "SA-Capabilities-OC1.melodyfragment";
  public static String Dataairdfragment = "SA-Data.airdfragment";
  public static String Datamelodyfragment = "SA-Data.melodyfragment";
  public static String PA_PhysicalFunctionsRPFairdfragment = "PA-Physical Functions-RPF.airdfragment";
  public static String PA_PhysicalFunctionsRPFmelodyfragment = "PA-Physical Functions-RPF.melodyfragment";
  public static String PA_PhysicalSystemairdfragment = "PA-Physical System.airdfragment";
  public static String PA_PhysicalSystemmelodyfragment = "PA-Physical System.melodyfragment";
  public static String fragmentsFolder = "fragments";
  public static String projectNameTC4 = "TC4prod00086565";
  public static String prod00086565Aird = "prod00086565.aird";
  public static String prod00086565Modeller = "prod00086565.melodymodeller";
  public static String EPBSAFragment = "EPBSA.airdfragment";
  public static String OAFragment = "OA.airdfragment";
  public static String SAFragment = "SA.airdfragment";
  public static String OA_1342a = "1342a - OA";
  public static String OA_1342a_ID = "2a9ced3c-438e-4305-a9e9-f3886a1ece9b";
  public static String SA_31673 = "31673 - SA";
  public static String SA_31673_ID = "c56c1489-4600-4e90-99f7-5053c272d4ba";
  public static String LA_b1f4f = "b1f4f - LA";
  public static String LA_b1f4f_ID = "09f3015c-5d07-40a6-8539-731f7f66db39";
  public static String PA_47af3 = "47af3 - PA";
  public static String PA_47af3_ID = "2528a220-37cb-4a9c-a71a-3c6091da2408";
  public static String EPBS_dc124 = "dc124 - EPBS";
  public static String EPBS_dc124_ID = "d7bac6ec-ab2f-46a7-926e-9dc97b9d5cc7";
  public static String Data_Pkg_ff1eb = "ff1eb - Data Pkg";
  public static String Data_Pkg_ff1eb_ID = "e76e606f-8d44-4018-848d-1555deb87e8d";
  public static String PF_9df8b = "9df8b - Physical Function";
  public static String PF_9df8b_ID = "65acd2cb-3deb-47da-bf27-f923d83cc256";
  public static String SM_5aa5b = "5aa5b - State Machine";
  public static String SM_5aa5b_ID = "989a6d8f-eda4-4bb3-9d9e-7ca0832a7d82";
  public static String Fragment_ff1eb = "47af3-ff1eb";
  public static String Fragment_9df8b = "47af3-a425e-b7a5f-98533-49897-9df8b";
  public static String Fragment_5aa5b = "47af3-ad244-5aa5b";
}
