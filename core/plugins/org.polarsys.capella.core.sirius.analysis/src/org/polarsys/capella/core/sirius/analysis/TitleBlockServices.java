/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.core.sirius.analysis.preferences.TitleBlockPreferencesInitializer;

public class TitleBlockServices {
  private static TitleBlockServices service = null;

  static Map<String, String> propertiesName = new HashMap<String, String>();
  static Map<String, String> propertiesContent = new HashMap<String, String>();

  private static final String DIAGRAM_TITLE_BLOCK = "DiagramTitleBlock";
  private static final String ELEMENT_TITLE_BLOCK = "ElementTitleBlock";
  private static final String NAME = "Name:";
  private static final String CONTENT = "Content:";
  private static final String VISIBILITY = "Visibility";
  private static final String TRUE = "True";
  private static final String FALSE = "False";
  private static final String DEFAULT_CELL_NAME = "Name";
  private static final String DEFAULT_CELL_CONTENT = "feature:name";
  private static final String TB_CELL = "TB_CELL";

  public static TitleBlockServices getService() {
    if (service == null) {
      service = new TitleBlockServices();
    }
    return service;
  }

  /**
   * @param containerView
   * @return true if the container is a diagram
   */
  public boolean isContainerDiagram(EObject containerView) {
    return (containerView instanceof DDiagram);
  }

  /**
   * @param diagram
   * @return true if the diagram already contains a Diagram Title Block
   */
  public boolean hasADiagramTitleBlock(DDiagram diagram) {
    return !getDiagramTitleBlocks(diagram).isEmpty();
  }

  /**
   * @param diagram
   * @return true if the element is already associated with an Element Title Block, if we find annotations in this
   *         diagram that already have reference to the elementView
   */
  public boolean hasAElementTitleBlock(DDiagramElement elementView) {
    List<DAnnotation> list = getElementTitleBlocks(elementView.getParentDiagram());
    for(DAnnotation annotation : list) {
      if(annotation.getReferences().contains(elementView.getTarget()))
        return true;
    }
    return false;
  }

  /**
   * @param containerView
   * @return true if the container is valid to create a Diagram Title Block
   */
  public boolean isValidCreateDiagramTitleBlock(EObject containerView) {
    return isContainerDiagram(containerView) && !hasADiagramTitleBlock((DDiagram) containerView);
  }

  /**
   * @param containerView:
   *          the element in diagram on which the annotation is attached
   * @return true if is valid to create an Element Title Block is valid if the containerView is a diagram element, is
   *         not annotation and there is not another annotation associated with elementView
   */
  public boolean isValidCreateElementTitleBlock(EObject containerView) {
    return (containerView instanceof DDiagramElement) && !hasAElementTitleBlock((DDiagramElement) containerView)
        && !(((DDiagramElement) containerView).getTarget() instanceof DAnnotation);
  }

  /**
   * check if is valid to insert a new line or a new column we can insert by clicking on a cell of an Element Title
   * Block
   * 
   * @param containerView
   * @return
   */
  public boolean isValidInsertLineOrColumn(EObject containerView) {
    if ((containerView instanceof DDiagramElement)
        && ((DDiagramElement) containerView).getTarget() instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) ((DDiagramElement) containerView).getTarget();
      return isElementTitleBlock(TitleBlockHelper.getParentTitleBlock(annotation,
          ((DDiagramElement) containerView).getParentDiagram()));
    }

    return false;
  }

  /**
   * check if is valid to remove an existing line; we can remove by clicking on a cell of an Element Title Block
   * 
   * @param containerView
   * @return
   */
  public boolean isValidRemoveLineOfElementTitleBlock(EObject containerView) {
    if (isValidInsertLineOrColumn(containerView)) {
      DAnnotation annotation = (DAnnotation) ((DDiagramElement) containerView).getTarget();
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(annotation,
          ((DDiagramElement) containerView).getParentDiagram());
      return TitleBlockHelper.getNumLinesOfElementTitleBlock(titleBlockContainer) > 1;
    }

    return false;
  }

  /**
   * check if is valid to remove an existing column;
   * we can remove by clicking on a cell of an Element Title Block
   * 
   * @param containerView
   * @return
   */
  public boolean isValidRemoveColumnOfElementTitleBlock(EObject containerView) {
    if (isValidInsertLineOrColumn(containerView)) {
      DAnnotation annotation = (DAnnotation) ((DDiagramElement) containerView).getTarget();
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(annotation,
          ((DDiagramElement) containerView).getParentDiagram());
      return TitleBlockHelper.getNumColumnsOfElementTitleBlock(titleBlockContainer) > 1;
    }

    return false;
  }

  /**
   * @param titleBlock
   * @return true if the annotation is a Diagram Title Block type annotation
   */
  public boolean isDiagramTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getSource() != null && titleBlock.getSource().equals(DIAGRAM_TITLE_BLOCK);
  }

  /**
   * @param titleBlock
   * @return true if the annotation is a Element Title Block type annotation
   */
  public boolean isElementTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getSource() != null && titleBlock.getSource().equals(ELEMENT_TITLE_BLOCK);
  }

  /**
   * creates a Diagram Title Block
   * 
   * @param diagram
   * @return
   */
  public void createDiagramTitleBlock(DDiagram diagram) {
    int numLines = TitleBlockPreferencesInitializer.getColumnsNumber();
    int numCols = TitleBlockPreferencesInitializer.getLinesNumber();
    String[] titleBlockContent = TitleBlockPreferencesInitializer.getContentAsArray();

    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(DIAGRAM_TITLE_BLOCK);
    annotation.getDetails().put(VISIBILITY, TRUE);

    // use this index to go trough the content stored in preferences for the content
    // of Diagram TB
    int currIndexTB = 0;

    // annotationLines - stores the list of lines of the Diagram TB
    List<DAnnotation> annotationLines = new ArrayList<DAnnotation>();

    for (int i = 0; i < numLines; i++) {
      // annotationCols - stores the list of columns (will be added to each line)
      List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();

      for (int j = 0; j < numCols; j++) {
        DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotationCol.getDetails().put(NAME, titleBlockContent[currIndexTB]);
        annotationCol.getDetails().put(CONTENT, titleBlockContent[currIndexTB + 1]);
        currIndexTB += 2;
        annotationCols.add(annotationCol);
        diagram.getEAnnotations().add(annotationCol);
      }

      // create a annotation line and add to it the contained columns
      DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
      annotationLine.getReferences().addAll(annotationCols);
      diagram.getEAnnotations().add(annotationLine);
      annotationLines.add(annotationLine);
    }

    annotation.getReferences().addAll(annotationLines);
    diagram.getEAnnotations().add(annotation);
  }

  /**
   * creates an Element Title Block attached to an element in diagram
   * @param elementView
   * @param diagram
   * @return
   */
  public void createElementTitleBlock(DDiagramElement elementView, DDiagram diagram) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(ELEMENT_TITLE_BLOCK);
    annotation.getDetails().put(VISIBILITY, TRUE);

    // add a single cell, with default content
    DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
    DAnnotation annotationCol = createDefaultCell();
    diagram.getEAnnotations().add(annotationCol);
    annotationLine.getReferences().add(annotationCol);
    diagram.getEAnnotations().add(annotationLine);

    annotation.getReferences().add(((DDiagramElement) elementView).getTarget());
    annotation.getReferences().add(annotationLine);
    diagram.getEAnnotations().add(annotation);
  }

  /**
   * insert a new line to Element Title Block (after the current selected cell)
   * 
   * @param titleBlock:
   *          the selected cell
   * @param diagram
   * @return
   */
  public void insertTitleBlockLine(DAnnotation titleBlock, DDiagram diagram) {
    DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(titleBlock, diagram);

    int numCols = TitleBlockHelper.getNumColumnsOfElementTitleBlock(titleBlockContainer);

    if (numCols > 0) {
      // create a new line (with numCols in it)
      DAnnotation annotationLine = createNewAnnotationLine(diagram, numCols);

      // insert the new line under the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfSelectedCell(titleBlock, titleBlockContainer);
      ((DAnnotation) titleBlockContainer).getReferences().add(indexLine + 1, annotationLine);
    }
  }

  /**
   * insert a new column to Element Title Block (after the current selected cell)
   * 
   * @param titleBlock:
   *          the selected cell
   * @param diagram
   * @return
   */
  public void insertTitleBlockColumn(DAnnotation titleBlock, DDiagram diagram) {
    DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(titleBlock, diagram);

    // insert the new column at the right of the cell we clicked on
    int indexCol = TitleBlockHelper.getColumnIndexOfSelectedCell(titleBlock, titleBlockContainer);

    // go in each line and insert a cell on the right of the clicked cell
    for (EObject elementLine : titleBlockContainer.getReferences()) {
      if (elementLine instanceof DAnnotation) {
        DAnnotation cell = createEmptyCell();
        diagram.getEAnnotations().add(cell);
        ((DAnnotation) elementLine).getReferences().add(indexCol + 1, cell);
      }
    }
  }

  /**
   * creates a new line belonging to a title  block;
   * it ads numColumns cells to the line
   * 
   * @param diagram
   * @param numCols
   * @return DAnnotation
   */
  private DAnnotation createNewAnnotationLine(DDiagram diagram, int numCols) {

    DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
    List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
    for (int j = 0; j < numCols; j++) {
      annotationCols.add(createEmptyCell());
    }
    annotationLine.getReferences().addAll(annotationCols);
    diagram.getEAnnotations().addAll(annotationCols);
    diagram.getEAnnotations().add(annotationLine);

    return annotationLine;
  }

  /**
   * it clears the content of a cell, by resetting the values stored;
   * the cell will be empty, no name or content
   * @param object: the annotation cell
   * @return DAnnotation
   */
  public void clearCellContent(EObject object) {
    if (object instanceof DAnnotation) {
      DAnnotation cellAnnotation = (DAnnotation) object;
      if (cellAnnotation.getSource() != null && cellAnnotation.getSource().equals(TB_CELL)) {
        cellAnnotation.getDetails().put(NAME, "");
        cellAnnotation.getDetails().put(CONTENT, "");
      }
    }
  }

  /**
   * it creates a cell where it puts empty values as name and content
   * @return DAnnotation
   */
  private DAnnotation createEmptyCell() {
    return TitleBlockHelper.createTitleBlockCell(TB_CELL, NAME, CONTENT, "", "");
  }

  /**
   * it creates a cell where it puts default values as name and content
   * @return DAnnotation
   */
  private DAnnotation createDefaultCell() {
    return TitleBlockHelper.createTitleBlockCell(TB_CELL, NAME, CONTENT, DEFAULT_CELL_NAME, DEFAULT_CELL_CONTENT);
  }

  /**
   * 
   * @param element
   * @param diagram
   * @return list of Title Blocks (will be one Element TB) that are associated to element given as parameter
   */
  public Collection<EObject> getTitleBlocksForElement(EObject element, DDiagram diagram) {
    return diagram.getEAnnotations().stream().filter(x -> x.getReferences().contains(element))
        .collect(Collectors.toList());
  }

  /**
   * get the list of Title Blocks related to this diagram also performs a refresh operation so that the dangling TB are
   * removed or updated according to preferences
   * 
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  public List<DAnnotation> getTitleBlocksInDiagram(DDiagram diagram) {

    // delete the dangling element title blocks
    List<DAnnotation> listElementTitleBlocks = getElementTitleBlocks(diagram);
    handleDanglingElementTitleBlocks(listElementTitleBlocks, diagram);

    // refresh the diagram title block, so that if preferences have changed, TB is
    // updated
    List<DAnnotation> listDiagramTitleBlocks = getDiagramTitleBlocks(diagram);
    if (!listDiagramTitleBlocks.isEmpty()) {
      DAnnotation diagramTitleBlock = listDiagramTitleBlocks.get(0);
      updateDiagramTitleBlock(diagramTitleBlock, (EObject) diagram);
    } else {
      if (TitleBlockPreferencesInitializer.isCreateDiagramTitleBlockByDefault()) {
        createDiagramTitleBlock((DDiagram) diagram);
      }
    }

    List<DAnnotation> allList = new ArrayList<DAnnotation>();
    allList.addAll(listDiagramTitleBlocks);
    allList.addAll(listElementTitleBlocks);
    allList = allList.stream().filter(x -> Objects.nonNull(x.getDetails().get(VISIBILITY)))
        .filter(x -> x.getDetails().get(VISIBILITY).equals(TRUE)).collect(Collectors.toList());
    return allList;
  }

  public List<DAnnotation> getVisibleDiagramTitleBlocks(Object elementView) {
    return getVisibleTitleBlocks(elementView, DIAGRAM_TITLE_BLOCK);
  }

  public List<DAnnotation> getVisibleElementTitleBlocks(Object elementView) {
    return getVisibleTitleBlocks(elementView, ELEMENT_TITLE_BLOCK);
  }

  /**
   * get the list of Title Blocks visible title block in diagram
   * 
   * @param containerView
   * @param type: can be DIAGRAM_TITLE_BLOCK or ELEMENT_TITLE_BLOCK
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  private List<DAnnotation> getVisibleTitleBlocks(Object containerView, String type) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    if (containerView instanceof DDiagram) {
      DDiagram diagram = (DDiagram) containerView;
      EList<DDiagramElement> elements = diagram.getOwnedDiagramElements();
      for (DDiagramElement element : elements) {
        if (element.getTarget() instanceof DAnnotation) {
          DAnnotation annotation = (DAnnotation) element.getTarget();
          if (annotation.getSource() != null && annotation.getSource().equals(type)) {
            list.add(annotation);
          }
        }
      }
    }
    return list;
  }

  /**
   * get the list of Element Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks (Element TB) that will be displayed in diagram
   */
  public List<DAnnotation> getElementTitleBlocks(DDiagram diagram) {
    return getTitleBlocks(diagram, ELEMENT_TITLE_BLOCK);
  }

  /**
   * get the list of Diagram Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks (Diagram) that will be displayed in diagram
   */
  public List<DAnnotation> getDiagramTitleBlocks(DDiagram diagram) {
    return getTitleBlocks(diagram, DIAGRAM_TITLE_BLOCK);
  }

  /**
   * get the list of Title Blocks by type (can be DIAGRAM_TITLE_BLOCK or ELEMENT_TITLE_BLOCK)
   * 
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  private List<DAnnotation> getTitleBlocks(DDiagram diagram, String titleBlocksType) {
    return diagram.getEAnnotations().stream()
        .filter(x -> (x.getSource() != null && x.getSource().equals(titleBlocksType))).collect(Collectors.toList());
  }

  /**
   * update the Diagram Title Block if the preferences settings were updated
   * 
   * @param diagramTitleBlock
   * @param elementView
   * @return
   */
  private void updateDiagramTitleBlock(DAnnotation diagramTitleBlock, EObject elementView) {

    // create a string similar to the one stored in preferences of the Diagram Title
    // Block content
    // use this string to compare with the one in preferences, if they are
    // different, update the Title Block
    String currentTB = "";
    for (EObject line : diagramTitleBlock.getReferences()) {
      if (line instanceof DAnnotation) {
        for (EObject column : ((DAnnotation) line).getReferences()) {
          if (column instanceof DAnnotation) {
            currentTB += ((DAnnotation) column).getDetails().get(NAME) + "+"
                + ((DAnnotation) column).getDetails().get(CONTENT) + "+";
          }
        }
      }
    }
    if (currentTB.length() > 0) {
      currentTB = currentTB.substring(0, currentTB.length() - 1);
    }

    // if the current Title Block from diagram needs to be updated with the new
    // preferences stored Title Block
    // remove the current Title Block and re-create it again
    if (!currentTB.equals(TitleBlockPreferencesInitializer.getContent())) {
      deleteTitleBlock(diagramTitleBlock, (DDiagram) elementView);
      createDiagramTitleBlock((DDiagram) elementView);
    }

  }

  /**
   * this function handles the dangling Element Title Blocks in two situations: if the element associated to an element
   * TB was deleted from model -> delete the TB; if the element associate to an element TB was removed from diagram ->
   * hide the TB
   * 
   * @param list:
   *          the list of element TB to check
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  private void handleDanglingElementTitleBlocks(List<DAnnotation> list, DDiagram diagram) {
    List<DAnnotation> deleteList = new ArrayList<DAnnotation>();
    for (DAnnotation annotation : list) {
      boolean hasExternalElementReference = false;
      for (EObject element : annotation.getReferences()) {
        if (!(element instanceof DAnnotation)) {
          hasExternalElementReference = true;
          boolean elementPresentInDiagram = false;
          List<DDiagramElement> diagramElementsList = diagram.getOwnedDiagramElements();
          for (DDiagramElement diagramElement : diagramElementsList) {
            if (!(diagramElement instanceof DEdge) && diagramElement.getTarget().equals(element)) {
              elementPresentInDiagram = true;
            }
          }
          // case to hide the TB
          if (!(elementPresentInDiagram)) {
             annotation.getDetails().put(VISIBILITY, FALSE);
          }
        }
      }

      // case to delete the TB because it has no reference to a semantic element
      if (!(hasExternalElementReference)) {
        deleteList.add(annotation);
      }
    }

    if (!deleteList.isEmpty()) {
      CapellaServices.getService().removeElements(deleteList);
      for (DAnnotation annotation : deleteList) {
        deleteTitleBlock(annotation, diagram);
      }
      list.removeAll(deleteList);
    }
  }

  /**
   * this deletes a Title block by clearing all the stored EAnnotations for a Title Block (clears each line, column and
   * the TB container annotation)
   * 
   * @param title
   *          block container
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  public void deleteTitleBlock(DAnnotation titleBlock, DDiagram diagram) {
    List<DAnnotation> annotationsList = new ArrayList<>();
    for (EObject titleBlockLine : titleBlock.getReferences()) {
      if (titleBlockLine instanceof DAnnotation) {
        annotationsList.add((DAnnotation) titleBlockLine); // title block lines
        for (EObject titleBlockCell : ((DAnnotation) titleBlockLine).getReferences()) {
          if (titleBlockCell instanceof DAnnotation) {
            annotationsList.add((DAnnotation) titleBlockCell);
          }
        }
      }
    }
    // remove also the container TB
    annotationsList.add(titleBlock);
    diagram.getEAnnotations().removeAll(annotationsList);
  }

  /**
   * this function removes a line of the Title Block
   * 
   * @param titleBlockLine:
   *          the annotation line of a title block
   * @param diagram
   * @return
   */
  public void removeLineOfTitleBlock(DAnnotation titleBlockLine, DDiagram diagram) {
    List<DAnnotation> eAnnotationsList = diagram.getEAnnotations();
    for (DAnnotation eAnnotation : eAnnotationsList) {
      if (eAnnotation.getReferences().contains(titleBlockLine)) {
        // remove the line and each column in it
        diagram.getEAnnotations().removeAll(eAnnotation.getReferences());
        diagram.getEAnnotations().remove(eAnnotation);
        eAnnotation.getReferences().clear();
        return;
      }
    }
  }

  /**
   * this function removes a line of the Title Block
   * 
   * @param titleBlockLine:
   *          the annotation line of a title block
   * @param diagram
   * @return
   */
  public void removeColumnOfTitleBlock(DAnnotation titleBlock, DDiagram diagram) {
    DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(titleBlock, diagram);

    // remove the column of the cell we clicked on
    int indexCol = TitleBlockHelper.getColumnIndexOfSelectedCell(titleBlock, titleBlockContainer);

    // go in each line and remove a cell on column of the clicked cell
    for (EObject elementLine : titleBlockContainer.getReferences()) {
      if (elementLine instanceof DAnnotation) {
        EObject removedCell = ((DAnnotation) elementLine).getReferences().remove(indexCol);
        diagram.getEAnnotations().remove(removedCell);
      }
    }
  }

  /**
   * 
   * @param diagram
   * @param cell
   * @param containerView
   * @return the content of the cell that will be displayed in diagram;
   * the returned object can be a EObject, Collection<?EObjects>;
   * we wrap a primitive type in a SimpleAnyType object
   */
  public Object getTitleBlockCellContent(EObject diagram, EObject cell, EObject containerView) {
    if ((diagram instanceof DRepresentation)) {
      if (cell instanceof DAnnotation) {
        DAnnotation annotation = (DAnnotation) ((DNodeContainer) (containerView.eContainer().eContainer())).getTarget();
        String feature = ((DAnnotation) cell).getDetails().get(CONTENT);
        if (feature != null) {
          EObject objToEvaluate = diagram;
          List<EObject> modelElements = annotation.getReferences().parallelStream()
              .filter(x -> !(x instanceof DAnnotation)).collect(Collectors.toList());
          if (!modelElements.isEmpty()) {
            objToEvaluate = modelElements.get(0);
          }
          Object obj = getResultOfExpression(objToEvaluate, feature);
          if (obj != null) {
            if (obj instanceof Collection) {
              return ((Collection) obj).stream().map(object -> convertToEObject(object)).collect(Collectors.toList());
            }
            return convertToEObject(obj);
          }
        }
      }
    }
    return null;
  }

  /**
   * convert a primitive object to an EObject using SimpleAnyType
   * 
   * @param object
   * @return EObject
   */
  private EObject convertToEObject(Object object) {
    if (object instanceof EObject)
      return (EObject) object;
    SimpleAnyType wrapper = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
    wrapper.setInstanceType(EcorePackage.eINSTANCE.getEString());
    wrapper.setValue(object.toString());

    return wrapper;
  }

  /**
   * 
   * @param object
   * @return true if the object is wrapped in SympleAnyType
   */
  public boolean isWrapperOfPrimitiveType(EObject object) {
    return object instanceof SimpleAnyType;
  }

  public EObject showHideDiagramTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, DIAGRAM_TITLE_BLOCK);
  }

  public EObject showHideElementTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, ELEMENT_TITLE_BLOCK);
  }

  private EObject showHideTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram,
      String type) {
    Map<DAnnotation, DDiagramElement> visibleElements = new HashMap<>();
    List<EObject> allNodes = new ArrayList<>();
    allNodes.addAll(((DSemanticDiagram) context).getOwnedDiagramElements());
    for (EObject aObject : allNodes) {
      if (aObject instanceof DNodeContainer) {
        if (((DNodeContainer) aObject).getTarget() instanceof DAnnotation) {
          if (((DAnnotation) ((DNodeContainer) aObject).getTarget()).getSource().equals(type)) {
            DDiagramElement aNode = ((DDiagramElement) aObject);
            EObject nodeTarget = aNode.getTarget();
            if (nodeTarget instanceof DAnnotation && aNode instanceof DDiagramElement) {
              visibleElements.put((DAnnotation) nodeTarget, aNode);
            }
          }
        }
      }
    }
    for (Entry<DAnnotation, DDiagramElement> me : visibleElements.entrySet()) {
      if (!selectedTitleBlocks.contains(me.getKey())) {
        EObject container = me.getValue().eContainer();
        if (container instanceof DSemanticDiagram) {
          DAnnotation annotation = ((DAnnotation) (me.getValue().getTarget()));
          if (DIAGRAM_TITLE_BLOCK.equals(type)) {
            if (isDiagramTitleBlock(annotation)) {
              annotation.getDetails().put(VISIBILITY, FALSE);
              DiagramServices.getDiagramServices().removeAbstractDNodeView((DNodeContainer) me.getValue());
            }
          } else {
            if (!isDiagramTitleBlock(annotation)) {
              annotation.getDetails().put(VISIBILITY, FALSE);
              DiagramServices.getDiagramServices().removeAbstractDNodeView((DNodeContainer) me.getValue());
            }
          }
        }
      }
    }
    for (DAnnotation aTitleBlock : selectedTitleBlocks) {
      if (!visibleElements.containsKey(aTitleBlock)) {
        createTitleBlockView(context, aTitleBlock, diagram);
      }
    }
    return context;
  }

  private AbstractDNode createTitleBlockView(EObject context, DAnnotation titleBlock, DDiagram diagram) {
    String mappingName = IMappingNameConstants.DT_TITLE_BLOCK;
    NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
    if (context instanceof DDiagramElement) {
      if (null != titleBlock.getDetails().get(VISIBILITY)) {
        titleBlock.getDetails().put(VISIBILITY, TRUE);
      }
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, titleBlock,
          (DragAndDropTarget) context, diagram);
    }
    if (null != titleBlock.getDetails().get(VISIBILITY)) {
      titleBlock.getDetails().put(VISIBILITY, TRUE);
    }
    return DiagramServices.getDiagramServices().createNode(mapping, titleBlock, (DragAndDropTarget) context, diagram);
  }

  /**
   * 
   * @param cell
   * @return the label that will be displayed in each cell (the value of the name of the cell)
   */
  public String getTitleBlockCellLabel(EObject cell) {
    String name = "";
    if (cell instanceof DAnnotation) {
      name = ((DAnnotation) cell).getDetails().get(NAME);
    }
    return name;
  }

  /**
   * 
   * @param element
   * @return true if element is a container Title Block (not a line, a column or a cell)
   */
  public boolean isTitleBlockContainer(EObject element) {
    if (element instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) element;
      return isDiagramTitleBlock(annotation) | isElementTitleBlock(annotation);
    }
    return false;
  }

  public static Image getImage(Object object) {
    DAnnotation annotation = (DAnnotation) object;
    String imagePath = "/icons/full/obj16/TitleBlock_16.gif";
    URL url = FileLocator.find(SiriusViewActivator.getInstance().getBundle(), new Path(imagePath), null);
    return ImageDescriptor.createFromURL(url).createImage();
  }

  /**
   * 
   * @param target
   * @param expression:
   *          the expression to be evaluate (ex feature: name, or capella: xyz)
   * @return result after the expression was evaluated
   */
  private Object getResultOfExpression(EObject target, String expression) {
    IInterpreterProvider provider = CompoundInterpreter.INSTANCE.getProviderForExpression(expression);
    IInterpreter interpreter = provider.createInterpreter();
    Object result = null;
    try {
      result = interpreter.evaluate(target, expression);
    } catch (EvaluationException e) {
      e.printStackTrace();
    }
    return result;
  }

}
