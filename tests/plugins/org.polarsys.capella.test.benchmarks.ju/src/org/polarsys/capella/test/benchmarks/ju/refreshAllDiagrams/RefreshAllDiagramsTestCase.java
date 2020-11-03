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
package org.polarsys.capella.test.benchmarks.ju.refreshAllDiagrams;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.polarsys.capella.common.lib.Memory;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.benchmarks.ju.utils.MemoryLogger;

public class RefreshAllDiagramsTestCase extends AbstractBenchmarkTestCase {

  @Override
  public void test() {
    long usedMemoryBefore = Memory.getUsedMemory() / (1024 * 1024);

    Session session = getSession(getRequiredTestModels().get(0));

    Collection<DRepresentation> representationsToRefresh = DialectManager.INSTANCE.getAllRepresentations(session);

    for (DRepresentation rep : representationsToRefresh) {
      IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rep, new NullProgressMonitor());
      assertNotNull("Unable to open editor " + RepresentationHelper.getRepresentationDescriptor(rep), editor);
      closeEditor(editor);
    }

    long usedMemoryAfter = Memory.getUsedMemory() / (1024 * 1024);

    System.out.println("Used memory: " + usedMemoryBefore + "MB / " + usedMemoryAfter + "MB.");

    MemoryLogger.getInstance().log(this.getClass().getCanonicalName(), usedMemoryAfter - usedMemoryBefore);
  }

  public void closeEditor(IEditorPart editor) {
    IEditorSite editorSite = editor.getEditorSite();
    assertNotNull("EditorSite is null", editorSite);
    IWorkbenchPage page = editorSite.getPage();
    assertNotNull("WorkbenchPage is null", page);
    page.closeEditor(editor, false);
  }
}
