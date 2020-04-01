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
import java.util.Arrays;
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
  private static final String FALSE = "False";
  private static final String DEFAULT_CELL_NAME = "Name";
  private static final String DEFAULT_CELL_CONTENT = "feature:name";

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
    return TitleBlockHelper.getDiagramTitleBlock(diagram) != null;
  }

  /**
   * @param diagram
   * @return true if the element is already associated with an Element Title Block, if we find annotations in this
   *         diagram that already have reference to the elementView
   */
  public boolean hasAElementTitleBlock(DDiagramElement elementView) {
    List<DAnnotation> list = getElementTitleBlocks(elementView.getParentDiagram());
    for (DAnnotation annotation : list) {
      if (annotation.getReferences().contains(elementView.getTarget()))
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
   * check if is valid to insert a new line or a new column. We can insert by clicking on a cell of an Element Title
   * Block
   * 
   * @param containerView
   * @return
   */
  public boolean isValidInsertLineOrColumn(EObject containerView) {
    if (containerView instanceof DDiagramElement) {
      DDiagramElement diagramElement = (DDiagramElement) containerView;
      if (TitleBlockHelper.isTitleBlockCell(diagramElement)) {
        DAnnotation cell = (DAnnotation) diagramElement.getTarget();
        DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell, diagramElement.getParentDiagram());
        return TitleBlockHelper.isElementTitleBlock(titleBlock);
      }
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
      return TitleBlockHelper.getTitleBlockLines(titleBlockContainer).size() > 1;
    }
    return false;
  }

  /**
   * check if is valid to remove an existing column; we can remove by clicking on a cell of an Element Title Block
   * 
   * @param containerView
   * @return
   */
  public boolean isValidRemoveColumnOfElementTitleBlock(EObject containerView) {
    if (isValidInsertLineOrColumn(containerView)) {
      DAnnotation annotation = (DAnnotation) ((DDiagramElement) containerView).getTarget();
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(annotation,
          ((DDiagramElement) containerView).getParentDiagram());
      return TitleBlockHelper.getNumOfColumns(titleBlockContainer) > 1;
    }
    return false;
  }

  /**
   * check if is valid to clear the cell, shall be the cell of an Element TB
   * @param object
   * @param containerView
   * @return
   */
  public boolean isValidToClearCellContent(EObject object, DDiagramElement containerView) { 
    if(object instanceof DAnnotation){
      DAnnotation cell = (DAnnotation) object;
      if(TitleBlockHelper.isTitleBlockCell(cell)) {
        DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell, containerView.getParentDiagram());
        return TitleBlockHelper.isElementTitleBlock(titleBlockContainer);
      }
    }
    return false;
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
    DAnnotation titleBlock = TitleBlockHelper.addDiagramTitleBlock(diagram);
    // use this index to go trough the content stored in preferences for the content
    // of Diagram TB
    int currIndexTB = 0;
    for (int i = 0; i < numLines; i++) {
      DAnnotation line = TitleBlockHelper.addTitleBlockLine(diagram, titleBlock);
      for (int j = 0; j < numCols; j++) {
        TitleBlockHelper.addTitleBlockCell(diagram, line, titleBlockContent[currIndexTB],
            titleBlockContent[currIndexTB + 1]);
        currIndexTB += 2;
      }
    }
  }

  /**
   * creates an Element Title Block attached to an element in diagram
   * 
   * @param elementView
   * @param diagram
   * @return
   */
  public void createElementTitleBlock(DDiagramElement elementView, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.addElementTitleBlock(diagram, elementView);
    DAnnotation line = TitleBlockHelper.addTitleBlockLine(diagram, titleBlock);
    TitleBlockHelper.addTitleBlockCell(diagram, line, DEFAULT_CELL_NAME, DEFAULT_CELL_CONTENT);
  }

  /**
   * insert a new line to Element Title Block (after the current selected cell)
   * 
   * @param titleBlock:
   *          the selected cell
   * @param diagram
   * @return
   */
  public void insertTitleBlockLine(DAnnotation cell, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell, diagram);
    int numCols = TitleBlockHelper.getNumOfColumns(titleBlock);
    if (numCols > 0) {
      // insert the new line under the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlock);
      TitleBlockHelper.addTitleBlockLine(diagram, titleBlock, indexLine + 1, numCols);
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
  public void insertTitleBlockColumn(DAnnotation cell, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell, diagram);
    // insert the new column at the right of the cell we clicked on
    int indexCol = TitleBlockHelper.getColumnIndexOfCell(cell, titleBlock);
    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
      // Add new empty cell
      TitleBlockHelper.addTitleBlockCell(diagram, line, "", "", indexCol + 1);
    }
  }

  /**
   * it clears the content of a cell, by resetting the values stored; the cell will be empty, no name or content
   * 
   * @param object:
   *          the annotation cell
   * @return DAnnotation
   */
  public void clearCellContent(EObject object) {
    if (object instanceof DAnnotation) {
      DAnnotation cellAnnotation = (DAnnotation) object;
      if (TitleBlockHelper.isTitleBlockCell(cellAnnotation)) {
        TitleBlockHelper.setTitleBlockCellContent(cellAnnotation, "", "");
      }
    }
  }

  /**
   * 
   * @param element
   * @param diagram
   * @return list of Title Blocks (will be one Element TB) that are associated to element given as parameter
   */
  public List<DAnnotation> getTitleBlocksForElement(EObject element, DDiagram diagram) {
    return TitleBlockHelper.getElementTitleBlocks(diagram, element);
  }

  /**
   * TODO: this method is not supposed to do things like remove dangling elements or refresh elements!!!
   * 
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
    DAnnotation diagramTitleBlock = TitleBlockHelper.getDiagramTitleBlock(diagram);
    if (diagramTitleBlock != null) {
      updateDiagramTitleBlock(diagramTitleBlock, (EObject) diagram);
    } else {
      if (TitleBlockPreferencesInitializer.isCreateDiagramTitleBlockByDefault()) {
        createDiagramTitleBlock((DDiagram) diagram);
      }
    }

    List<DAnnotation> allList = new ArrayList<DAnnotation>();
    if (diagramTitleBlock != null) {
      allList.add(diagramTitleBlock);      
    }
    allList.addAll(listElementTitleBlocks);
    allList = allList.stream().filter(x -> Objects.nonNull(x.getDetails().get(TitleBlockHelper.VISIBILITY)))
        .filter(x -> x.getDetails().get(TitleBlockHelper.VISIBILITY).equals(TitleBlockHelper.TRUE))
        .collect(Collectors.toList());
    return allList;
  }

   public List<DAnnotation> getVisibleDiagramTitleBlocks(Object elementView) {
   return getVisibleTitleBlocks(elementView, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
   }

   public List<DAnnotation> getVisibleElementTitleBlocks(Object elementView) {
   return getVisibleTitleBlocks(elementView, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
   }

   /**
   * get the list of Title Blocks visible title block in diagram
   *
   * @param containerView
   * @param type:
   *          can be DIAGRAM_TITLE_BLOCK or ELEMENT_TITLE_BLOCK
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
    return TitleBlockHelper.getElementTitleBlocks(diagram);
  }

  /**
   * get the Diagram Title Block
   * 
   * @param diagram
   * @return list of Title Blocks (Diagram) that will be displayed in diagram
   */
  public List<DAnnotation> getDiagramTitleBlocks(DDiagram diagram) {
    return Arrays.asList(TitleBlockHelper.getDiagramTitleBlock(diagram));
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

    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(diagramTitleBlock)) {
      for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
        currentTB += cell.getDetails().get(TitleBlockHelper.NAME) + "+"
            + cell.getDetails().get(TitleBlockHelper.CONTENT) + "+";
      }
    }
    if (currentTB.length() > 0) {
      currentTB = currentTB.substring(0, currentTB.length() - 1);
    }

    // if the current Title Block from diagram needs to be updated with the new
    // preferences stored Title Block
    // remove the current Title Block and re-create it again
    if (!currentTB.equals(TitleBlockPreferencesInitializer.getContent())) {
      deleteTitleBlock((DDiagram) elementView, diagramTitleBlock);
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
    List<DAnnotation> deleteList = new ArrayList<>();
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
            // TODO Hide without using
             annotation.getDetails().put(TitleBlockHelper.VISIBILITY, FALSE);
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
        deleteTitleBlock(diagram, annotation);
      }
      list.removeAll(deleteList);
    }
  }

  /**
   * this deletes a Title block by clearing all the stored EAnnotations for a Title Block (clears each line, column and
   * the TB container annotation)
   * 
   * @param diagram
   * @param title
   *          block container
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  public void deleteTitleBlock(DDiagram diagram, DAnnotation titleBlock) {
    List<DAnnotation> annotationsList = new ArrayList<>();
    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
      annotationsList.add(line);
      for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
        annotationsList.add(cell);
        // delete also the annotations that wrapped objects
        for (EObject wrap : cell.getReferences()) {
          annotationsList.add((DAnnotation) wrap);
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
   * @param cell:
   *          removed the entire line that contains the annotation cell
   * @param diagram
   * @return
   */
  public void removeLineOfTitleBlock(DAnnotation cell, DDiagram diagram) {
    if (TitleBlockHelper.isTitleBlockCell(cell)) {
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell, diagram);

      // remove the line of the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlockContainer);
      DAnnotation titleBlockCell = TitleBlockHelper.getTitleBlockLines(titleBlockContainer).get(indexLine);

      List<DAnnotation> toRemove = new ArrayList<>();
      toRemove.add(titleBlockCell);
      toRemove.addAll(TitleBlockHelper.getTitleBlockCells(titleBlockCell));
      diagram.getEAnnotations().removeAll(toRemove);
    }
  }

  /**
   * this function removes the column of the Title Block cell
   * 
   * @param cell:
   *          the title block cell
   * @param diagram
   * @return
   */
  public void removeColumnOfTitleBlock(DAnnotation cell, DDiagram diagram) {
    if (TitleBlockHelper.isTitleBlockCell(cell)) {
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell, diagram);

      // remove the column of the cell we clicked on
      int indexCol = TitleBlockHelper.getColumnIndexOfCell(cell, titleBlockContainer);

      // go in each line and remove a cell on column of the clicked cell
      for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlockContainer)) {
        EObject removedCell = line.getReferences().remove(indexCol);
        diagram.getEAnnotations().remove(removedCell);
      }
    }
  }

  /**
   * 
   * @param diagram
   * @param cell
   * @param containerView
   * @return the content of the cell that will be displayed in diagram; the returned object can be a EObject,
   *         Collection<?EObjects>; we wrap a primitive type in a SimpleAnyType object
   */
  public Object getTitleBlockCellContent(EObject diagram, EObject cell, EObject containerView) {
    if ((diagram instanceof DDiagram)) {
      if (cell instanceof DAnnotation && TitleBlockHelper.isTitleBlockCell((DAnnotation)cell)) {
        DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock((DAnnotation)cell, (DDiagram)diagram);
        String feature = ((DAnnotation) cell).getDetails().get(TitleBlockHelper.CONTENT);
        if (feature != null) {
          
          EObject objToEvaluate = TitleBlockHelper.getSemanticElementReference(titleBlockContainer);
          // if is a Diagram Title Block, objToEvaluate will be the diagram
          if (objToEvaluate == null)
            objToEvaluate = diagram;
          
          Object obj = getResultOfExpression(objToEvaluate, feature);
          if (obj != null) {
            if (obj instanceof Collection) {
              return ((Collection) obj).stream().map(object -> getWrappedObject(object, 
                  (DAnnotation)cell, (DDiagram)diagram)).collect(Collectors.toList());
            }
            return getWrappedObject(obj, (DAnnotation)cell, (DDiagram)diagram);
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
  private EObject convertToEObjectUsingSimpleAnyTyoe(Object object) {
    if (object instanceof EObject)
      return (EObject) object;
    SimpleAnyType wrapper = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
    wrapper.setInstanceType(EcorePackage.eINSTANCE.getEString());
    wrapper.setValue(object.toString());

    return wrapper;
  }
  
  /**
   * get an EObject;
   * wrap the object in DAnnotation if is not already an EObject;
   * if the content of the cell is already stored in a DAnnotation, just update the content
   * if the cell content changed
   * 
   * @param object
   * @param cell
   * @param diagram
   * @return EObject
   */
  private EObject getWrappedObject(Object object, DAnnotation cell, DDiagram diagram) {
    if (object instanceof EObject)
      return (EObject) object;
    DAnnotation wrapperAnnotation = null;
    if(cell.getReferences().isEmpty()) {
      wrapperAnnotation = DescriptionFactory.eINSTANCE.createDAnnotation();
      wrapperAnnotation.setSource(TitleBlockHelper.CONTENT);
      wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, object.toString());
      cell.getReferences().add(wrapperAnnotation);
      diagram.getEAnnotations().add(wrapperAnnotation);
    }
    else {
      wrapperAnnotation = (DAnnotation) cell.getReferences().get(0);
      if(! object.toString().equals(wrapperAnnotation.getDetails().get(TitleBlockHelper.CONTENT))) {
        wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, object.toString());
      }
    }
    return (EObject)wrapperAnnotation;
  }

  /**
   * 
   * @param object
   * @return true if the object is wrapped in SympleAnyType
   */
  public boolean isWrapperOfPrimitiveType(EObject object) {
    return object instanceof SimpleAnyType;
  }
  
  /**
   * 
   * @param object
   * @return true if the object is DAnnotation
   */
  public boolean isAnnotation(EObject object) {
    return object instanceof DAnnotation;
  }

  
  /**
   * 
   * @param cell
   * @return String the label of the content cell
   */
  public String getTitleBockCellLabel(DAnnotation cell) {
    return cell.getDetails().get(TitleBlockHelper.CONTENT);
  }
  
  public EObject showHideDiagramTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
  }

  public EObject showHideElementTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
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
          if (TitleBlockHelper.DIAGRAM_TITLE_BLOCK.equals(type)) {
            if (TitleBlockHelper.isDiagramTitleBlock(annotation)) {
              annotation.getDetails().put(TitleBlockHelper.VISIBILITY, FALSE);
              DiagramServices.getDiagramServices().removeAbstractDNodeView((DNodeContainer) me.getValue());
            }
          } else {
            if (!TitleBlockHelper.isDiagramTitleBlock(annotation)) {
              annotation.getDetails().put(TitleBlockHelper.VISIBILITY, FALSE);
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
      if (null != titleBlock.getDetails().get(TitleBlockHelper.VISIBILITY)) {
        titleBlock.getDetails().put(TitleBlockHelper.VISIBILITY, TitleBlockHelper.TRUE);
      }
      return DiagramServices.getDiagramServices().createDNodeListElement(mapping, titleBlock,
          (DragAndDropTarget) context, diagram);
    }
    if (null != titleBlock.getDetails().get(TitleBlockHelper.VISIBILITY)) {
      titleBlock.getDetails().put(TitleBlockHelper.VISIBILITY, TitleBlockHelper.TRUE);
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
      name = ((DAnnotation) cell).getDetails().get(TitleBlockHelper.NAME);
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
      return TitleBlockHelper.isDiagramTitleBlock(annotation) | TitleBlockHelper.isElementTitleBlock(annotation);
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
