/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.core.commands.preferences.initializers.CapellaDiagramPreferencesInitializer;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
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

    checkTitleBlockUpdate();

    titleBlocks();
    // unsynchronized
    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    titleBlocks();
    deleteAll();
  }

  /**
   * Check the content of the formatted last representation change date and that is it updated when a change on the
   * representation is done.
   */
  private void checkTitleBlockUpdate() {
    DDiagramElement viewTB = diagram.createDiagramTitleBlock();
    DAnnotation diagramTB = (DAnnotation) viewTB.getTarget();
    session.getSiriusPreferences().setAutoRefresh(false);

    DAnnotation[] titleBlockLine = { null };
    session.getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

          @Override
          protected void doExecute() {
            titleBlockLine[0] = TitleBlockHelper.addTitleBlockLine(diagram.getDiagram(), diagramTB);
            TitleBlockHelper.addTitleBlockCell(diagram.getDiagram(), titleBlockLine[0], "Formatted Date",
                "aql:self.getLastModificationDate()");
          }
        });

    String initialTitleBlockContent = getTitleBlockContent(titleBlockLine);
    session.getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

          @Override
          protected void doExecute() {
            // any change is needed to update DRepresentation.changeId
            try {
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            diagram.getDiagram().setSynchronized(false);
            refreshRepresentation();
          }
        });
    session.save(new NullProgressMonitor());

    String newTitleBlockContent = getTitleBlockContent(titleBlockLine);

    // check that the last representation change date is updated
    assertNotEquals(initialTitleBlockContent, newTitleBlockContent);

    // check that the content of the cell is a formatted date
    String errorMsg = "The diagram last change date is not correctly formatted";
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = sdf.parse(newTitleBlockContent);
      assertEquals(errorMsg, newTitleBlockContent, sdf.format(date));
    } catch (ParseException ex) {
      fail(errorMsg);
    }

    // check that refreshing the diagram does not set it dirty and that the date did not change
    String titleBlockContentAfterSimpleRefresh = getTitleBlockContent(titleBlockLine);
    assertEquals(titleBlockContentAfterSimpleRefresh, newTitleBlockContent);
    assertEquals(SessionStatus.SYNC, session.getStatus());

    // check that modifying the preference will update the representation after a refresh
    CapellaDiagramPreferencesInitializer.preferencesManager.setValue(CapellaDiagramPreferences.PREF_DATE_FORMAT,
        "yy-MM-dd HH:mm:ss");
    refreshRepresentation();
    String titleBlockContentAfterPrefChange = getTitleBlockContent(titleBlockLine);
    assertNotEquals(titleBlockContentAfterPrefChange, newTitleBlockContent);
    assertEquals(SessionStatus.DIRTY, session.getStatus());
  }

  private void refreshRepresentation() {
    session.getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

          @Override
          protected void doExecute() {
            DialectManager.INSTANCE.refresh(diagram.getDiagram(), new NullProgressMonitor());
          }
        });
  }

  private String getTitleBlockContent(DAnnotation[] titleBlockLine) {
    return ((DAnnotation) TitleBlockHelper.getTitleBlockCells(titleBlockLine[0]).get(0)
        .getReferences().get(0)).getDetails().get(TitleBlockHelper.CONTENT);
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
