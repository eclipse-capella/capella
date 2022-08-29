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
package org.polarsys.capella.test.semantic.ui.ju.testcases;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polarsys.capella.test.semantic.ui.ju.AbstractSemanticBrowserUITest;

public class SBNavigationMenuInteractionUseTest extends AbstractSemanticBrowserUITest {
    
    public static final String OAS_IU_INPUT = "580916dc-d7ac-46cb-bce8-c99d7694bbb5"; //$NON-NLS-1$
    
    public static final String OAS_IU_REF_NAVIGATED_DIAGRAM_1 = "[OAS] OperationalProcess 2 2"; //$NON-NLS-1$
    
    public static final String OAS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_kHHusCIqEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String OAS_SCENARIO_INPUT = "996aea39-2e38-40a8-8e2c-0b123e264d78"; //$NON-NLS-1$
    
    public static final String FS_IU_INPUT = "af8a84dc-6417-4b78-9928-53beca53713f"; //$NON-NLS-1$
    
    public static final String FS_IU_REF_NAVIGATED_DIAGRAM_1 = "[FS] [OAS] OperationalProcess 2 2"; //$NON-NLS-1$
    
    public static final String FS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_X7DlACIrEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String FS_SCENARIO_INPUT = "767afa48-728e-4035-bdd7-4afb2d2e1afe"; //$NON-NLS-1$

    public static final String IS_IU_INPUT = "68e5ba27-4f44-4153-9b30-7f3ba3cce254"; //$NON-NLS-1$
    
    public static final String IS_IU_REF_NAVIGATED_DIAGRAM_1 = "[IS] OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String IS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_JJYrASIsEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String IS_SCENARIO_INPUT = "e92420b3-1436-467a-b608-8546a5b2a8e2"; //$NON-NLS-1$

    public static final String ES_IU_INPUT = "d88c3128-f1e2-4a81-bf6f-8e94b02aefc1"; //$NON-NLS-1$
    
    public static final String ES_IU_REF_NAVIGATED_DIAGRAM_1 = "[ES] OperationalCapability 2"; //$NON-NLS-1$
    
    public static final String ES_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_EoKGECItEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String ES_SCENARIO_INPUT = "5952115a-214c-4f86-bafe-685089bc68b3"; //$NON-NLS-1$

    @Override
    public List<String> getRequiredTestModels() {
        return Collections.singletonList("DiagramNavigationModel");
    }

    @Override
    public void test() throws Exception {
        init();

        testOASReferencing();
        testOASCurrent();
        testOASReferenced();

        testFSReferenced();
        testFSCurrent();
        testFSReferencing();
        
        testISReferenced();
        testISCurrent();
        testISReferencing();
    }
    
    public void testOASReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OAS_IU_REF_NAVIGATED_DIAGRAM_1, OAS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(OAS_SCENARIO_INPUT, OAS_IU_INPUT, SB_REFERENCING_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testOASCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OAS_IU_REF_NAVIGATED_DIAGRAM_1, OAS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(OAS_IU_INPUT, OAS_IU_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testOASReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OAS_IU_REF_NAVIGATED_DIAGRAM_1, OAS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(OAS_SCENARIO_INPUT, OAS_IU_INPUT, SB_REFERENCING_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testFSReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_IU_REF_NAVIGATED_DIAGRAM_1, FS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(FS_SCENARIO_INPUT, FS_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testFSCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_IU_REF_NAVIGATED_DIAGRAM_1, FS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(FS_IU_INPUT, FS_IU_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testFSReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(FS_IU_REF_NAVIGATED_DIAGRAM_1, FS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(FS_SCENARIO_INPUT, FS_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testISReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_IU_REF_NAVIGATED_DIAGRAM_1, IS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(IS_SCENARIO_INPUT, IS_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testISCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_IU_REF_NAVIGATED_DIAGRAM_1, IS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(IS_IU_INPUT, IS_IU_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testISReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(IS_IU_REF_NAVIGATED_DIAGRAM_1, IS_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(IS_SCENARIO_INPUT, IS_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testESReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_IU_REF_NAVIGATED_DIAGRAM_1, ES_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(ES_SCENARIO_INPUT, ES_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testESCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_IU_REF_NAVIGATED_DIAGRAM_1, ES_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(ES_IU_INPUT, ES_IU_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testESReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(ES_IU_REF_NAVIGATED_DIAGRAM_1, ES_IU_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(ES_SCENARIO_INPUT, ES_IU_INPUT, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
}
