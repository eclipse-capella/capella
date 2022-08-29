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

public class PPDiagramNavigationTest extends AbstractDiagramNavigationTest {
    
    public static final String SAB_DIAGRAM = "[SAB] System"; //$NON-NLS-1$
    
    public static final String SAB_REF = "919f43b3-19cc-4f38-a0e2-0e962ca3d897"; //$NON-NLS-1$
    
    public static final String SAB_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Sys PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String SAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_SesxYCIoEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_SYS_DIAGRAM = "[PPD] Sys PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF = "0f3411b5-b63e-46eb-95ec-216dd9df7ab8"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Sys PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_xzdfkCOuEe27Q_6bH3dUDg"; //$NON-NLS-1$
    
    public static final String LAB_DIAGRAM = "[LAB] Logical System"; //$NON-NLS-1$
    
    public static final String LAB_REF = "59d21390-5263-44ea-abe9-d21494b442a4"; //$NON-NLS-1$
    
    public static final String LAB_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Logical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String LAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_cVhskCIoEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_DIAGRAM = "[PPD] Logical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF = "7a246568-537d-4c97-b404-a1be14e37cfa"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Logical PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_ElZ7QChHEe2f_LZ3tbUQuw"; //$NON-NLS-1$

    public static final String PAB_DIAGRAM = "[PAB] Physical System"; //$NON-NLS-1$
    
    public static final String PAB_REF = "f3e7feae-d856-4a8d-a615-8279dd032a44"; //$NON-NLS-1$
    
    public static final String PAB_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Physical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_bzCc0CIjEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_DIAGRAM = "[PPD] Physical PhysicalPath 1"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF = "a5dce1e5-3291-411c-8052-265fe08ada10"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_1 = "[PPD] Physical PhysicalPath 2"; //$NON-NLS-1$
    
    public static final String PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_uQ77gCIjEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    @Override
    public void test() throws Exception {
        testSABPP();
        testLABPP();
        testPABPP();
        testPPDSys();
        testPPDLogical();
        testPPDPhysical();
    }
    
    public void testSABPP() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(SAB_REF_NAVIGATED_DIAGRAM_1, SAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(SAB_DIAGRAM, SAB_REF, mapExpectedNavigationActions);
    }
    
    public void testPPDSys() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_SYS_REF_NAVIGATED_DIAGRAM_1, PPD_SYS_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PPD_SYS_DIAGRAM, PPD_SYS_REF, mapExpectedNavigationActions);
    }
    
    public void testLABPP() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(LAB_REF_NAVIGATED_DIAGRAM_1, LAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(LAB_DIAGRAM, LAB_REF, mapExpectedNavigationActions);
    }
    
    public void testPPDLogical() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_LOGICAL_REF_NAVIGATED_DIAGRAM_1, PPD_LOGICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PPD_LOGICAL_DIAGRAM, PPD_LOGICAL_REF, mapExpectedNavigationActions);
    }
    
    public void testPABPP() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PAB_REF_NAVIGATED_DIAGRAM_1, PAB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PAB_DIAGRAM, PAB_REF, mapExpectedNavigationActions);
    }
    
    public void testPPDPhysical() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PPD_PHYSICAL_REF_NAVIGATED_DIAGRAM_1, PPD_PHYSICAL_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PPD_PHYSICAL_DIAGRAM, PPD_PHYSICAL_REF, mapExpectedNavigationActions);
    }

    @Override
    protected void undoAllChanges() {
        // TODO Undo all changes does not work with this test
    }
}
