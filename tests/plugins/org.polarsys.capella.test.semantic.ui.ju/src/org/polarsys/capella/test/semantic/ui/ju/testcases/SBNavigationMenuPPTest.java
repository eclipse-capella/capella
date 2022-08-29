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

public class SBNavigationMenuPPTest extends AbstractSemanticBrowserUITest {
    
    public static final String PPD_SYS_PPR_INPUT = "0f3411b5-b63e-46eb-95ec-216dd9df7ab8"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Sys PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_DIAGRAM_2 = "[PPD] Sys PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_SesxYCIoEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2 = "_xzdfkCOuEe27Q_6bH3dUDg"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REFING_SEM = "919f43b3-19cc-4f38-a0e2-0e962ca3d897"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REFED_SEM = "932f6a05-09e4-453d-a8c7-93e67422826e"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_PPR_INPUT = "7a246568-537d-4c97-b404-a1be14e37cfa"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Logical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_2 = "[PPD] Logical PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_cVhskCIoEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2 = "_ElZ7QChHEe2f_LZ3tbUQuw"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REFING_SEM = "59d21390-5263-44ea-abe9-d21494b442a4"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REFED_SEM = "3ba887f4-d8c6-449e-9bf8-72fbecc2e0c2"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_PPR_INPUT = "a5dce1e5-3291-411c-8052-265fe08ada10"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Physical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_2 = "[PPD] Physical PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_bzCc0CIjEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2 = "_uQ77gCIjEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REFING_SEM = "f3e7feae-d856-4a8d-a615-8279dd032a44"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REFED_SEM = "5617d352-e160-49bc-9415-f0b830677ac2"; //$NON-NLS-1$

    @Override
    public List<String> getRequiredTestModels() {
        return Collections.singletonList("DiagramNavigationModel");
    }
    
    @Override
    public void test() throws Exception {
        init();
        
        testPPDSysReferencing();
        testPPDSysCurrent();
        testPPDSysReferenced();
        
        testPPDLogicalReferencing();
        testPPDLogicalCurrent();
        testPPDLogicalReferenced();
        
        testPPDPhysicalReferencing();
        testPPDPhysicalCurrent();
        testPPDPhysicalReferenced();
    }
    
    public void testPPDSysReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_SYS_REF_NAVIGATED_DIAGRAM_2, PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_SYS_PPR_INPUT, PPD_SYS_REFED_SEM, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDSysCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_SYS_REF_NAVIGATED_DIAGRAM_2, PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_SYS_PPR_INPUT, PPD_SYS_PPR_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDSysReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_SYS_REF_NAVIGATED_DIAGRAM_1, PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(PPD_SYS_PPR_INPUT, PPD_SYS_REFING_SEM, SB_REFERENCING_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDLogicalReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_2, PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_LOGICAL_PPR_INPUT, PPD_LOGICAL_REFED_SEM, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDLogicalCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_2, PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_LOGICAL_PPR_INPUT, PPD_LOGICAL_PPR_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDLogicalReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_1, PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(PPD_LOGICAL_PPR_INPUT, PPD_LOGICAL_REFING_SEM, SB_REFERENCING_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDPhysicalReferenced() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_2, PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_PHYSICAL_PPR_INPUT, PPD_PHYSICAL_REFED_SEM, SB_REFERENCED_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDPhysicalCurrent() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_2, PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestSBViewerOpenMenu(PPD_PHYSICAL_PPR_INPUT, PPD_PHYSICAL_PPR_INPUT, SB_CURRENT_VIEWER_NAME, mapExpectedNavigationActions);
    }
    
    public void testPPDPhysicalReferencing() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_1, PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestSBViewerOpenMenu(PPD_PHYSICAL_PPR_INPUT, PPD_PHYSICAL_REFING_SEM, SB_REFERENCING_VIEWER_NAME, mapExpectedNavigationActions);
    }
}
