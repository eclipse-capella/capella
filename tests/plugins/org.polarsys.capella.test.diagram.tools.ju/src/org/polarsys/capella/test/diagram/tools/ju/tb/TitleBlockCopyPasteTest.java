package org.polarsys.capella.test.diagram.tools.ju.tb;

import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.platform.sirius.clipboard.actions.CapellaDiagramCopyAction;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;

public class TitleBlockCopyPasteTest extends AbstractTitleBlockTestCase {
  protected String diagramName;
  protected String diagramId;

  private static final String ELEMENT_TITLE_BLOCK_ID = "_fkTCAKu_Eeq5dLOJgOme6A";
  private static final String DIAGRAM_TITLE_BLOCK_ID = "_h154QKu_Eeq5dLOJgOme6A";
  private static final String CLASS_ID = "6da79542-9d7b-497e-9505-44fd5a7fc05a";
  private static final String DIAGRAM_NAME = "[CDB] Data Title Blocks CopyPaste Test";
  private static final String DIAGRAM_ID = "7da31f5f-5ce1-454e-916a-e8593af47236";

  public TitleBlockCopyPasteTest() {
    elementsId.add(ELEMENT_TITLE_BLOCK_ID);
    elementsId.add(DIAGRAM_TITLE_BLOCK_ID);
    elementsId.add(CLASS_ID);
    diagramName = DIAGRAM_NAME;
    diagramId = DIAGRAM_ID;
  }

  @Override
  protected CommonDiagram initDiagram() {
    return CDBDiagram.openDiagram(context, diagramName);
  }

  @Override
  protected CommonDiagram createDiagram() {
    return diagram;
  }
  
  @Override
  public void test() throws Exception {
    
    initTest();
    
    DAnnotation elementTB = TitleBlockHelper.getElementTitleBlocks(diagram.getDiagram()).get(0);
    Object selectedElementTB = LayerUtil.getGraphicalPart(
        DiagramServices.getDiagramServices().getDiagramElement(diagram.getDiagram(), (EObject) elementTB));
    
    DAnnotation diagramTB = TitleBlockHelper.getDiagramTitleBlocks(diagram.getDiagram()).get(0);
    Object selectedDiagramTB = LayerUtil.getGraphicalPart(
        DiagramServices.getDiagramServices().getDiagramElement(diagram.getDiagram(), (EObject) diagramTB));
    
    //select only one Element Title Block
    Object[] selectedElements = new Object[] {selectedElementTB};
    copyPasteTest(selectedElements);
    
    //select one Element Title Block and one Diagram Title Block
    selectedElements = new Object[] {selectedElementTB, selectedDiagramTB};
    copyPasteTest(selectedElements);
  }
  
  private void copyPasteTest(Object[] selectedElements) {
    CapellaDiagramCopyAction copy = new CapellaDiagramCopyAction();
    EList<DDiagramElement> elementsBefore = new BasicEList<DDiagramElement>(diagram.getDiagram().getOwnedDiagramElements());
    
    ISelection selectionTB = new StructuredSelection(selectedElements);
    copy.selectionChanged(null, selectionTB);
    copy.run(null);
    
    EList<DDiagramElement> elementsAfter = new BasicEList<DDiagramElement>(diagram.getDiagram().getOwnedDiagramElements());
    elementsAfter.removeAll(elementsBefore);
    int diff = elementsAfter.stream().filter(element -> TitleBlockHelper.isTitleBlockAnnotation(element)).collect(Collectors.toList()).size();
    assertTrue("Failure: At least one Title Block was copied!", diff == 0);
  }  
}