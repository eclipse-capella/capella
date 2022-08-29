/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.navigation;

import java.util.HashMap;
import java.util.Map;

public class SequenceDiagramNavigationTest extends AbstractDiagramNavigationTest {

    public static final String OAS_DIAGRAM = "[OAS] OperationalProcess 1 1"; //$NON-NLS-1$
    public static final String OAS_INTERACTION_USE = "580916dc-d7ac-46cb-bce8-c99d7694bbb5"; //$NON-NLS-1$
    
    public static final String OAS_INTERACTION_USE_NAVIGATED_DIAGRAM = "[OAS] OperationalProcess 2 2"; //$NON-NLS-1$
    
    public static final String OAS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_kHHusCIqEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String OES_DIAGRAM = "[OES] OperationalCapability"; //$NON-NLS-1$
    
    public static final String OES_INTERACTION_USE = "ccd3c605-1f48-4929-8328-f60e65ec0576"; //$NON-NLS-1$
    
    public static final String OES_INTERACTION_USE_NAVIGATED_DIAGRAM_1 = "[OES] OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String OES_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_bl0mkCIrEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String OES_INTERACTION_USE_NAVIGATED_DIAGRAM_2 = "[OES] OperationalCapability 2.2"; //$NON-NLS-1$
    
    public static final String OES_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR_2 = "_FLLEYCePEe22W48GObwpnA"; //$NON-NLS-1$

    public static final String FS_SYS_DIAGRAM = "[FS] [OAS] OperationalProcess 1 1"; //$NON-NLS-1$
    
    public static final String FS_SYS_INTERACTION_USE = "af8a84dc-6417-4b78-9928-53beca53713f"; //$NON-NLS-1$
    
    public static final String FS_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM = "[FS] [OAS] OperationalProcess 2 2"; //$NON-NLS-1$
    
    public static final String FS_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_X7DlACIrEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String IS_SYS_DIAGRAM = "[IS] OperationalCapability"; //$NON-NLS-1$
    
    public static final String IS_SYS_INTERACTION_USE = "0d43d642-4f2b-4602-bd69-11b679b5b3b8"; //$NON-NLS-1$
    
    public static final String IS_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM = "[IS] OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String IS_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_JJYrASIsEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String ES_SYS_DIAGRAM = "[ES] OperationalCapability"; //$NON-NLS-1$
    
    public static final String ES_SYS_INTERACTION_USE = "d88c3128-f1e2-4a81-bf6f-8e94b02aefc1"; //$NON-NLS-1$
    
    public static final String ES_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM = "[ES] OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String ES_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_EoKGECItEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String FS_LOGICAL_DIAGRAM = "[FS] Logical FunctionalChain 1 1"; //$NON-NLS-1$
    
    public static final String FS_LOGICAL_INTERACTION_USE = "4228f90d-3945-4c27-ae10-871f491ba72b"; //$NON-NLS-1$
    
    public static final String FS_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[FS] Logical FunctionalChain 2 1"; //$NON-NLS-1$
    
    public static final String FS_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_7b_kMCH6Ee2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String IS_LOGICAL_DIAGRAM = "[IS] Logical OperationalCapability"; //$NON-NLS-1$
    
    public static final String IS_LOGICAL_INTERACTION_USE = "a23b3a2b-84e2-45e2-a3c7-21fe90f142d8"; //$NON-NLS-1$
    
    public static final String IS_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[IS] Logical OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String IS_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_hDawUSIuEe2ws6wdu8Wbow"; //$NON-NLS-1$

    public static final String ES_LOGICAL_DIAGRAM = "[ES] Logical OperationalCapability"; //$NON-NLS-1$
    
    public static final String ES_LOGICAL_INTERACTION_USE = "19e917b3-84cb-4a12-95ff-625215507fcf"; //$NON-NLS-1$
    
    public static final String ES_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[ES] Logical OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String ES_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_TwhwECOxEe2IDMSN_zhq_g"; //$NON-NLS-1$public static final String FS_LOGICAL_DIAGRAM = "[FS] Logical FunctionalChain 1 1"; //$NON-NLS-1$
    
    public static final String FS_PHYSICAL_DIAGRAM = "[FS] Physical FunctionalChain 1 1"; //$NON-NLS-1$
    
    public static final String FS_PHYSICAL_INTERACTION_USE = "1434ff40-9787-43b9-a2de-e62778a0880c"; //$NON-NLS-1$
    
    public static final String FS_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[FS] Physical FunctionalChain 2 1"; //$NON-NLS-1$
    
    public static final String FS_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_QnOqsSIuEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String IS_PHYSICAL_DIAGRAM = "[IS] Physical CapabilityRealization 2"; //$NON-NLS-1$
    
    public static final String IS_PHYSICAL_INTERACTION_USE = "89f68253-4eba-41c0-9778-cb659e2ed70f"; //$NON-NLS-1$
    
    public static final String IS_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[IS] Physical CapabilityRealization 1"; //$NON-NLS-1$
    
    public static final String IS_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_Nt5GECIuEe2ws6wdu8Wbow"; //$NON-NLS-1$

    public static final String ES_PHYSICAL_DIAGRAM = "[ES] Physical CapabilityRealization 2"; //$NON-NLS-1$
    
    public static final String ES_PHYSICAL_INTERACTION_USE = "c36a7f05-af02-476e-bd76-ccff7c37fd2c"; //$NON-NLS-1$
    
    public static final String ES_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM = "[ES] Physical OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String ES_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR = "_RfqHgCIuEe2ws6wdu8Wbow"; //$NON-NLS-1$

    @Override
    public void test() throws Exception {
        testOAS();
        testOES();
        testFSSys();
        testISSys();
        testESSys();
        testFSLog();
        testISLog();
        testESLog();
        testFSPhy();
        testISPhy();
        testESPhy();
    }
    
    public void testOAS() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OAS_INTERACTION_USE_NAVIGATED_DIAGRAM, OAS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(OAS_DIAGRAM, OAS_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testOES() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OES_INTERACTION_USE_NAVIGATED_DIAGRAM_1, OES_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        mapExpectedNavigationActions.put(OES_INTERACTION_USE_NAVIGATED_DIAGRAM_2, OES_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestForDiagramNavigation(OES_DIAGRAM, OES_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testFSSys() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM, FS_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(FS_SYS_DIAGRAM, FS_SYS_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testISSys() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM, IS_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(IS_SYS_DIAGRAM, IS_SYS_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testESSys() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_SYS_INTERACTION_USE_NAVIGATED_DIAGRAM, ES_SYS_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(ES_SYS_DIAGRAM, ES_SYS_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testFSLog() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, FS_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(FS_LOGICAL_DIAGRAM, FS_LOGICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testISLog() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, IS_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(IS_LOGICAL_DIAGRAM, IS_LOGICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testESLog() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_LOGICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, ES_LOGICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(ES_LOGICAL_DIAGRAM, ES_LOGICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testFSPhy() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, FS_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(FS_PHYSICAL_DIAGRAM, FS_PHYSICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testISPhy() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, IS_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(IS_PHYSICAL_DIAGRAM, IS_PHYSICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    public void testESPhy() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_PHYSICAL_INTERACTION_USE_NAVIGATED_DIAGRAM, ES_PHYSICAL_INTERACTION_USE_NAVIGATED_REPRESENTATIONDESCRIPTOR);
        runTestForDiagramNavigation(ES_PHYSICAL_DIAGRAM, ES_PHYSICAL_INTERACTION_USE, mapExpectedNavigationActions);
    }

    @Override
    protected void undoAllChanges() {
        // TODO Undo all changes does not work with this test
    }
}
