/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;

public class RefreshAllDiagramsTestCase extends AbstractBenchmarkTestCase {

  @Override
  public void test() {
    long usedMemoryBefore = Memory.getUsedMemory();

    Session session = getSession(getRequiredTestModels().get(0));

    Collection<DRepresentation> representationsToRefresh = DialectManager.INSTANCE.getAllRepresentations(session);

    for (DRepresentation rep : representationsToRefresh) {
      IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rep, new NullProgressMonitor());
      assertNotNull("Unable to open editor " + rep.getName(), editor);
      closeEditor(editor);
    }

    long usedMemoryAfter = Memory.getUsedMemory();
    System.out.println(
        "Used memory: " + usedMemoryBefore / (1024 * 1024) + "MB / " + usedMemoryAfter / (1024 * 1024) + "MB.");
  }

  public void closeEditor(IEditorPart editor) {
    IEditorSite editorSite = editor.getEditorSite();
    assertNotNull("EditorSite is null", editorSite);
    IWorkbenchPage page = editorSite.getPage();
    assertNotNull("WorkbenchPage is null", page);
    page.closeEditor(editor, false);
  }
}
