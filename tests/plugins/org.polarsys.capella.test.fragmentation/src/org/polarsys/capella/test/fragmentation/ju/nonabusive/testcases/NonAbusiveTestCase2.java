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
package org.polarsys.capella.test.fragmentation.ju.nonabusive.testcases;

import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.SLASH_CHARACTER;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.fragmentation.ju.model.FragmentModelTestFramework;
import org.polarsys.capella.test.fragmentation.ju.utils.AbstractToolFragmentModifTest;
import org.polarsys.capella.test.fragmentation.ju.utils.NonAbusiveFragmentTest;

public class NonAbusiveTestCase2 extends FragmentModelTestFramework {
  IFile _SF11aird;
  IFile _SF11m2;
  IFile _SF12aird;
  IFile _SF12m2;

  String SF11airdName = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1-SystemFunction 11.airdfragment";
  String SF11m2Name = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1-SystemFunction 11.capellafragment";
  String SF12airdName = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1-SystemFunction 12.airdfragment";
  String SF12m2Name = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1-SystemFunction 12.airdfragment";
  String sf1 = "5dec9cce-f8e4-41ea-847e-fa501df56dd2";
  String sf11 = "20467916-5698-4389-b7d4-b5b88d4d11c8";
  String sf12 = "17b44430-41b5-4567-9a37-96000d866105";
  String diag_SF1_SFBD = "[SFBD] SystemFunction 1";
  String diag_SF121_SDFB = "[SDFB] SystemFunction 1.2.1";

  @Override
  public void test() throws Exception {
    init();

    fragmentWithRefChecks(context, sf11);
    fragmentWithRefChecks(context, sf12);

    DiagramContext diag = new OpenDiagramStep(context, diag_SF121_SDFB).run();

    new AbstractToolFragmentModifTest(context,
        new CreateContainerTool(diag, IToolNameConstants.TOOL_CREATE_FUNCTION, diag.getDiagramId())) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(_SF12aird);
        set.add(_SF12m2);
        return set;
      }

    }.run();

    new AbstractToolFragmentModifTest(context,
        new InsertRemoveTool(diag, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS)) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(_SF12aird);
        return set;
      }

      @Override
      protected void runTest() {
        ((InsertRemoveTool) _command).insert(sf11);
      }

    }.run();

    unfragmentWithRefChecks(context, sf12);
    unfragmentWithRefChecks(context, sf11);

    fragmentWithRefChecks(context, sf1);
    fragment(context, sf11);
    unfragment(context, sf11);
    unfragmentWithRefChecks(context, sf1);

    /*
     * aird ReadOnly. Test: fragment sf11
     */
    new AbstractToolFragmentModifTest(context, new NonAbusiveFragmentTest(context, getEObject(sf11))) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(_airdFile);
        return set;
      }

    }.run();

    unfragment(context, sf11);
  }

  @Override
  protected void init() {
    super.init();
    _airdFile = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + airdName);
    _m2File = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + m2Name);

    _SF11aird = FileHelper.getPlatformFile(getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER
        + fragmentsFolder + ICommonConstants.SLASH_CHARACTER + SF11airdName);

    _SF11m2 = FileHelper.getPlatformFile(getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER
        + fragmentsFolder + ICommonConstants.SLASH_CHARACTER + SF11m2Name);

    _SF12aird = FileHelper.getPlatformFile(getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER
        + fragmentsFolder + ICommonConstants.SLASH_CHARACTER + SF12airdName);

    _SF12m2 = FileHelper.getPlatformFile(getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER
        + fragmentsFolder + ICommonConstants.SLASH_CHARACTER + SF12m2Name);
  }

}
