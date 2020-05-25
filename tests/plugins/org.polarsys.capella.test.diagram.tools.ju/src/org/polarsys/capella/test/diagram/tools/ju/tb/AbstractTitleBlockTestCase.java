/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.tb;

import java.util.ArrayList;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractTitleBlockTestCase extends AbstractDiagramTestCase {
  private ScopedPreferenceStore preferenceStore;
  protected Session session;
  protected SessionContext context;
  protected CommonDiagram diagram;
  protected CommonDiagram newDiagram;
  protected ArrayList<String> elementsId = new ArrayList<String>();

  @Override
  protected String getRequiredTestModel() {
    return "TitleBlocksModel";
  }

  @Override
  public void test() throws Exception {
    initTest();
    testTitleBlocks();
  }

  public void testTitleBlocks() {
    testDiagramTitleBlock();
    for (String elementId : elementsId) {
      testElementTitleBlock(elementId);
    }
  }

  public void testDiagramTitleBlock() {

    // check if diagram was created by default

    newDiagram.checkAutocreateDiagramTitleBlock();
    // create TB tools
    DAnnotation diagramTB = diagram.createDiagramTitleBlock();

    // show/hide tool + create TB after hide it
    diagram.removeDiagramTitleBlock(diagramTB.getUid());
    diagram.checkCreateDiagramTitleBlock();
    diagram.insertDiagramTitleBlock(diagramTB.getUid());
    
    diagram.deleteTitleBlock(diagramTB);
  }

  public void testElementTitleBlock(String elementId) {
    // create TB tools
    DAnnotation elementTB = diagram.createElementTitleBlock(elementId);

    // show/hide tool + create TB after hide it
    diagram.removeElementTitleBlock(elementTB.getUid());
    diagram.checkCreateElementTitleBlock(elementId);
    diagram.insertElementTitleBlock(elementTB.getUid());

    // insert line/column in TB element
    diagram.insertLineInTitleBlock(elementTB, 0);
    diagram.insertColumnInTitleBlock(elementTB, 0);

    // remove line/column from TB element
    diagram.removeLineFromTitleBlock(elementTB, 0);
    diagram.removeColumnFromTitleBlock(elementTB, 0);
    
    diagram.deleteTitleBlock(elementTB);
  }

  protected abstract CommonDiagram initDiagram();

  protected abstract CommonDiagram createDiagram();


  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
    diagram = initDiagram();
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
    preferenceStore.setValue("defaultTitleBlock", true);
    newDiagram = createDiagram();
    session.save(new NullProgressMonitor());
  }
}
