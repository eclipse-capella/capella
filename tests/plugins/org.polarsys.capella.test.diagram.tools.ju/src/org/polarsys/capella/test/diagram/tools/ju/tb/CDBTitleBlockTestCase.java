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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.preferences.TitleBlockPreferencePage;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CDBTitleBlockTestCase extends AbstractDiagramTestCase {
  private ScopedPreferenceStore preferenceStore;
  protected Session session;
  protected SessionContext context;
  protected CommonDiagram diagram;
  protected CommonDiagram newDiagram;
  protected ArrayList<String> elementsId = new ArrayList<String>();

  protected String diagramName;
  protected String dataPKG;

  private static final String CLASS_ID = "330d0716-9466-4a2c-8a06-9b95b1d2d1ef";
  private static final String DATA_ID = "28654216-6820-4e87-8d31-d146b194ba19";
  private static final String BOOLEAN_ID = "06c37c44-d801-4ff0-8f0f-2708df03d97b";
  private static final String DIAGRAM_NAME = "[CDB] Data Logical";
  private static final String DATA_PKG = "402bc79a-3d0f-4361-8deb-b0e2237bfed5";

  public CDBTitleBlockTestCase() {
    elementsId.add(CLASS_ID);
    elementsId.add(DATA_ID);
    elementsId.add(BOOLEAN_ID);
    diagramName = DIAGRAM_NAME;
    dataPKG = DATA_PKG;
  }

  @Override
  protected String getRequiredTestModel() {
    return "TitleBlocksModel";
  }

  @Override
  public void test() throws Exception {
    initTest();
    titleBlocks();
    // unsynchronized
    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    titleBlocks();
    deleteAll();
  }

  public void titleBlocks() {
    diagramTitleBlock();
    for (String elementId : elementsId) {
      elementTitleBlock(elementId);
    }
  }

  public void diagramTitleBlock() {

    // Check if diagram was created by default
    // TODO Reactivate or remove the test when the preference option is decided
    // newDiagram.checkAutocreateDiagramTitleBlock();

    // create TB tools
    DDiagramElement viewTB = diagram.createDiagramTitleBlock();
    DAnnotation diagramTB = (DAnnotation) viewTB.getTarget();

    // show/hide tool + create TB after hide it
    diagram.removeDiagramTitleBlock(diagramTB.getUid());
    diagram.checkCreateDiagramTitleBlock();
    diagram.insertDiagramTitleBlock(diagramTB.getUid());

    viewTB = DiagramServices.getDiagramServices().getDiagramElement(diagram.getDiagram(), diagramTB);
    diagram.deleteSemantic(viewTB);
    assertTrue(TitleBlockHelper.getAllAnnotationsForTitleBlock(diagramTB).isEmpty());
  }

  public void elementTitleBlock(String elementId) {
    // create TB tools
    DDiagramElement viewTB = diagram.createDiagramTitleBlock();
    DAnnotation elementTB = (DAnnotation) viewTB.getTarget();

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

    viewTB = DiagramServices.getDiagramServices().getDiagramElement(diagram.getDiagram(), elementTB);
    diagram.deleteSemantic(viewTB);
    // no inner annotations
    assertTrue(TitleBlockHelper.getAllAnnotationsForTitleBlock(elementTB).isEmpty());
    // ensure element is not deleted
    assertTrue(diagram.getSessionContext().getSemanticElement(elementId).eContainer() != null);
  }

  public void deleteAll() {
    newDiagram.createDiagramTitleBlock();
    newDiagram.createConstraint(newDiagram.getDiagramId());
    newDiagram.deleteAllSemantic();
    assertTrue(newDiagram.getDiagram().getOwnedDiagramElements().size() == 0);
  }

  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
    diagram = CDBDiagram.openDiagram(context, diagramName);
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
    preferenceStore.setValue(TitleBlockPreferencePage.DEFAULT_TITLEBLOCK_PREFERENCE_STORE, true);
    newDiagram = CDBDiagram.createDiagram(context, dataPKG);
    session.save(new NullProgressMonitor());
  }

}
