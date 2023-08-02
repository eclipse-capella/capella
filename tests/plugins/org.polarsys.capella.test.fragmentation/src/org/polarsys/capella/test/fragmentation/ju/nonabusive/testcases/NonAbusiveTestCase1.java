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
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.fragmentation.ju.model.FragmentModelTestFramework;
import org.polarsys.capella.test.fragmentation.ju.utils.AbstractToolFragmentModifTest;

public class NonAbusiveTestCase1 extends FragmentModelTestFramework {
  IFile _SF1aird;
  IFile _SF1m2;
  IFile _SF2aird;
  IFile _SF2m2;

  String SF1airdName = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1.airdfragment";
  String SF1m2Name = "SA-System Functions-RSF-SystemFunctionPkg 1-SystemFunction 1.capellafragment";
  String sf1 = "5dec9cce-f8e4-41ea-847e-fa501df56dd2";
  String diag_SF1_SFBD = "[SFBD] SystemFunction 1";

  @Override
  public void test() throws Exception {
    init();

    fragmentWithRefChecks(context, sf1);

    DiagramContext diag = new OpenDiagramStep(context, diag_SF1_SFBD).run();

    new AbstractToolFragmentModifTest(context,
        new CreateAbstractDNodeTool<DDiagramElementContainer>(diag, IToolNameConstants.TOOL_SFBD_CREATE_SYSTEM_FUNCTION,
            diag.getDiagramId()) {
          @Override
          protected int expectedNewElements() {
            return 2;
          }
        }) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(_SF1aird);
        set.add(_SF1m2);
        return set;
      }

    }.run();

    unfragmentWithRefChecks(context, sf1);
  }
  
  @Override
  protected void init() {
    super.init();
    _airdFile = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + airdName);
    _m2File = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + m2Name);

    _SF1aird = FileHelper
        .getPlatformFile(
            getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER + fragmentsFolder
                + ICommonConstants.SLASH_CHARACTER + SF1airdName);

    _SF1m2 = FileHelper
        .getPlatformFile(
            getCurrentProjectName() + ICommonConstants.SLASH_CHARACTER + fragmentsFolder
                + ICommonConstants.SLASH_CHARACTER + SF1m2Name);
  }

}
