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

public class FCDiagramNavigationTest extends AbstractDiagramNavigationTest {
    
    public static final String OPD_DIAGRAM = "[OPD] OperationalProcess 1"; //$NON-NLS-1$
    
    public static final String OPD_REF = "021fb5b4-3796-4d75-b4f5-d32c74abb428"; //$NON-NLS-1$
    
    public static final String OPD_REF_NAVIGATED_DIAGRAM_1 = "[OPD] OperationalProcess 2"; //$NON-NLS-1$
    
    public static final String OPD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_h-OlACIpEe2ws6wdu8Wbow"; //$NON-NLS-1$

    public static final String OAIB_DIAGRAM = "[OAIB] Root Operational Activity"; //$NON-NLS-1$
    
    public static final String OAIB_REF = "f1d8cc48-7b7e-4a25-bc7d-25206e642098"; //$NON-NLS-1$
    
    public static final String OAIB_REF_NAVIGATED_DIAGRAM_1 = "[OPD] OperationalProcess 1"; //$NON-NLS-1$
    
    public static final String OAIB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_qFBicCIoEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String SFCD_DIAGRAM = "[SFCD] FunctionalChain 1"; //$NON-NLS-1$
    
    public static final String SFCD_REF = "55266fbf-7bb5-4bfd-af92-9f2d58c8f60e"; //$NON-NLS-1$
    
    public static final String SFCD_REF_NAVIGATED_DIAGRAM_1 = "[SFCD] FunctionalChain 2"; //$NON-NLS-1$
    
    public static final String SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_Y18cwCInEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String SFCD_REF_NAVIGATED_DIAGRAM_2 = "[SFCD] FunctionalChain 2 V2"; //$NON-NLS-1$
    
    public static final String SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2 = "_6RRNgCUbEe26AuRB3Cwpfg"; //$NON-NLS-1$
    
    public static final String SDFB_DIAGRAM = "[SDFB] Root System Function"; //$NON-NLS-1$
    
    public static final String LFCD_DIAGRAM = "[LFCD] FunctionalChain 1"; //$NON-NLS-1$
    
    public static final String LFCD_REF = "2bd84e85-9dcd-41cf-a24b-532c0664fcd7"; //$NON-NLS-1$
    
    public static final String LFCD_REF_NAVIGATED_DIAGRAM_1 = "[LFCD] FunctionalChain 2"; //$NON-NLS-1$
    
    public static final String LFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_mYE68CH5Ee2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String LDFB_DIAGRAM = "[LDFB] Root Logical Function"; //$NON-NLS-1$
    
    public static final String PFCD_DIAGRAM = "[PFCD] FunctionalChain 1"; //$NON-NLS-1$
    
    public static final String PFCD_REF = "49b14553-8695-407d-be00-0e224cf34c05"; //$NON-NLS-1$
    
    public static final String PFCD_REF_NAVIGATED_DIAGRAM_1 = "[PFCD] FunctionalChain 2"; //$NON-NLS-1$
    
    public static final String PFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1 = "_gDXpkCIjEe2ws6wdu8Wbow"; //$NON-NLS-1$
    
    public static final String PDFB_DIAGRAM = "[PDFB] Root Physical Function"; //$NON-NLS-1$
    
    @Override
    public void test() throws Exception {
        testOPD();
        testOAIB();
        testSFCD();
        testSDFB();
        testLFCD();
        testLDFB();
        testPFCD();
        testPDFB();
    }
    
    public void testOPD() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OPD_REF_NAVIGATED_DIAGRAM_1, OPD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(OPD_DIAGRAM, OPD_REF, mapExpectedNavigationActions);
    }
    
    public void testOAIB() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(OAIB_REF_NAVIGATED_DIAGRAM_1, OAIB_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(OAIB_DIAGRAM, OAIB_REF, mapExpectedNavigationActions);
    }

    public void testSFCD() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(SFCD_REF_NAVIGATED_DIAGRAM_1, SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        mapExpectedNavigationActions.put(SFCD_REF_NAVIGATED_DIAGRAM_2, SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestForDiagramNavigation(SFCD_DIAGRAM, SFCD_REF, mapExpectedNavigationActions);
    }

    // Same references and navigations in SDFB_DIAGRAM and SFCD_DIAGRAM for SFCD_REF
    public void testSDFB() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(SFCD_REF_NAVIGATED_DIAGRAM_1, SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        mapExpectedNavigationActions.put(SFCD_REF_NAVIGATED_DIAGRAM_2, SFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_2);
        runTestForDiagramNavigation(SDFB_DIAGRAM, SFCD_REF, mapExpectedNavigationActions);
    }

    public void testLFCD() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(LFCD_REF_NAVIGATED_DIAGRAM_1, LFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(LFCD_DIAGRAM, LFCD_REF, mapExpectedNavigationActions);
    }

    // Same references and navigations in LDFB_DIAGRAM and LFCD_DIAGRAM for LFCD_REF
    public void testLDFB() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(LFCD_REF_NAVIGATED_DIAGRAM_1, LFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(LDFB_DIAGRAM, LFCD_REF, mapExpectedNavigationActions);
    }

    public void testPFCD() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PFCD_REF_NAVIGATED_DIAGRAM_1, PFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PFCD_DIAGRAM, PFCD_REF, mapExpectedNavigationActions);
    }

    // Same references and navigations in PDFB_DIAGRAM and PFCD_DIAGRAM for PFCD_REF
    public void testPDFB() throws Exception {
        Map<String, String> mapExpectedNavigationActions = new HashMap<>();
        mapExpectedNavigationActions.put(PFCD_REF_NAVIGATED_DIAGRAM_1, PFCD_REF_NAVIGATED_REPRESENTATIONDESCRIPTOR_1);
        runTestForDiagramNavigation(PDFB_DIAGRAM, PFCD_REF, mapExpectedNavigationActions);
    }

    @Override
    protected void undoAllChanges() {
        // TODO Undo all changes does not work with this test
    }
}
