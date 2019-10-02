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
package org.polarsys.capella.test.framework.odesign.error.detection;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SiriusRuntimeErrorDetection extends BasicTestCase {

  protected static final String PROJECT_NAME = "sysmodel";
  protected static final String LOG_FILE_PREFIX = "log/sirius-runtime";

  protected Session session;
  protected SessionContext context;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.session = getSession(PROJECT_NAME);
    this.context = new SessionContext(session);

  }

  @Override
  public void test() throws Exception {

    List<DDiagram> diagrams = DialectManager.INSTANCE.getAllRepresentations(session).stream()
        .filter(DDiagram.class::isInstance).map(DDiagram.class::cast).collect(Collectors.toList());

    try {

      SiriusRuntimeLogger.activate();

      for (int i = 0; i < diagrams.size(); i++) {
        DDiagram diagram = diagrams.get(i);

        System.out.println("[" + (i + 1) + "/" + diagrams.size() + "]" + diagram.getName());

        SiriusRuntimeLogger.setAnalyzedDiagram(diagram);

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        assertNotNull("Unable to open editor " + RepresentationHelper.getRepresentationDescriptor(diagram), editor);

        IEditorSite editorSite = editor.getEditorSite();
        assertNotNull("EditorSite is null", editorSite);
        IWorkbenchPage page = editorSite.getPage();
        assertNotNull("WorkbenchPage is null", page);
        page.closeEditor(editor, false);

      }
    } finally {
      SiriusRuntimeLogger.deactivate();

      File outputLogFile = getFile(getLogFileName());
      System.out.println("Writing to " + outputLogFile.getAbsolutePath());
      SiriusRuntimeLogger.persistLogs(outputLogFile);
    }

  }

  protected static File getFile(String fileNamePath) {
    File file = new File(fileNamePath);

    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return file;
  }

  protected static String getLogFileName() {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    String time = formatter.format(date);

    return LOG_FILE_PREFIX + "_" + time + ".log";
  }

}
