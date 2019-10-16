package org.polarsys.capella.test.diagram.tools.ju.delete;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DeleteTest extends DeleteModelTest {

  protected static final List<String> LAB_DELETE_ACTIVATED_IDS = Arrays.asList(LAB_B1, LAB_C);
  protected static final List<String> LAB_DELETE_DEACTIVATED_IDS = Arrays.asList(LAB_A, LAB_B);

  protected static final List<String> CDB_DELETE_ACTIVATED_IDS = Arrays.asList(CDB_B1, CDB_C);
  protected static final List<String> CDB_DELETE_DEACTIVATED_IDS = Arrays.asList(CDB_A, CDB_B);

  protected static final String DELETE_BTN_STATUS_ERROR_MSG_TEMPLATE = "Diagram {0} Element {1} has delete button enablement at {2} but should be {3}";
  protected static final String DELETE_TOOL_PRECONDITION_ERROR_MSG_TEMPLATE = "Diagram {0} Tool {1} has precondition {2} but should be {3}";
  protected static final String DELETE_TOOL_INVALID_NUMBER_ERROR_MSG_TEMPLATE = "Expected {0} tools but instead {1} are present";

  private static final String INPUT_FILE_NAME = "src/org/polarsys/capella/test/diagram/tools/ju/delete/deleteDiagramAndToolNames.csv";

  private static final String VALID_PRECONDITION = "aql:element.shouldDisplayDeleteButton(containerView)";

  protected Collection<String> errorMessages;
  protected Map<String, String> diagramAndDeleteToolNames;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.session = getSession(PROJECT_NAME);
    this.context = new SessionContext(session);
    this.errorMessages = new ArrayList<>();

    diagramAndDeleteToolNames = loadDeleteDescriptionAndToolsMap();
  }

  @Override
  public void test() throws Exception {
    testPreconditionService(LAB, LAB_DELETE_ACTIVATED_IDS, LAB_DELETE_DEACTIVATED_IDS);
    testPreconditionService(CDB, CDB_DELETE_ACTIVATED_IDS, CDB_DELETE_DEACTIVATED_IDS);

    testDiagramDescriptionPreconditionPresence();

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  public void testPreconditionService(String diagramName, List<String> deleteActivatedIds,
      List<String> deleteDeactivatedIds) {
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);

    deleteActivatedIds.stream().forEach(id -> evaluateDeletePrecondition(diagram, id, true));
    deleteDeactivatedIds.stream().forEach(id -> evaluateDeletePrecondition(diagram, id, false));
  }

  public void evaluateDeletePrecondition(DDiagram diagram, String deleteElementId, boolean shouldDeleteBeEnabled) {

    ModelElement deleteElement = context.getSemanticElement(deleteElementId);
    assertNotEquals(null, deleteElement);

    DDiagramElement deleteElementView = DiagramServices.getDiagramServices().getDiagramElement(diagram, deleteElement);
    assertNotEquals(null, deleteElementView);

    boolean isDeleteEnabled = DiagramServices.getDiagramServices().shouldDisplayDeleteButton(deleteElement,
        deleteElementView);

    if (shouldDeleteBeEnabled != isDeleteEnabled) {
      String msg = NLS.bind(DELETE_BTN_STATUS_ERROR_MSG_TEMPLATE,
          new Object[] { diagram.getName(), deleteElementView.getName(), isDeleteEnabled, shouldDeleteBeEnabled });

      errorMessages.add(msg);
    }
  }

  public void testDiagramDescriptionPreconditionPresence() {

    int analyzedTools = 0;

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      EList<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();
      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;

          String diagramDescriptionName = diagramDescription.getName();
          String deleteToolName = diagramAndDeleteToolNames.get(diagramDescriptionName);

          if (deleteToolName != null) {
            Optional<DeleteElementDescription> deleteTool = diagramDescription.getAllTools().stream()
                .filter(DeleteElementDescription.class::isInstance).map(DeleteElementDescription.class::cast)
                .filter(tool -> deleteToolName.equals(tool.getName())).findFirst();

            if (deleteTool.isPresent()) {
              String deleteToolPrecondition = deleteTool.get().getPrecondition();

              if (deleteToolPrecondition == null || !deleteToolPrecondition.equals(VALID_PRECONDITION)) {
                String msg = NLS.bind(DELETE_TOOL_PRECONDITION_ERROR_MSG_TEMPLATE, new Object[] {
                    diagramDescription.getName(), deleteToolName, deleteToolPrecondition, VALID_PRECONDITION });

                errorMessages.add(msg);

              } else {
                analyzedTools++;
              }
            }
          }
        }
      }
    }

    if (analyzedTools != diagramAndDeleteToolNames.size()) {
      String msg = NLS.bind(DELETE_TOOL_INVALID_NUMBER_ERROR_MSG_TEMPLATE, diagramAndDeleteToolNames.size(),
          analyzedTools);
      errorMessages.add(msg);
    }

  }

  protected Map<String, String> loadDeleteDescriptionAndToolsMap() {
    Map<String, String> result = new HashMap<>();

    try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_NAME))) {

      lines.forEach(line -> {
        String[] tokens = line.split(",");
        assertEquals(2, tokens.length);
        result.put(tokens[0].trim(), tokens[1].trim());
      });

    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}
