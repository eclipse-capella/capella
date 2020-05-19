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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.TitleBlockPreferencesInitializer;

/*
 * This class exposes the Title Block Services.
 * We can have two types of title Blocks:
 * - Diagram Title Blocks
 * - Element Title Blocks
 */
public class TitleBlockServices {
  private static TitleBlockServices service = null;

  static Map<String, String> propertiesName = new HashMap<String, String>();
  static Map<String, String> propertiesContent = new HashMap<String, String>();
  private static final String SEPARATOR = "SEPARATOR";
  private static final String DEFAULT_CELL_NAME = "Name";
  private static final String DEFAULT_CELL_CONTENT = "feature:name";
  private static final String FILTERS_TITLE_BLOCKS_MESSAGE = "{0}s are hidden by filters. Do you want to deactivate the filters for {1}s?";
  private static final String FILTERS_DIALOG_TITLE = "Filters deactivation";
  private static final String FILTER_DIAGRAM_TITLE_BLOCK = "hide.diagram.title.blocks.filter";
  private static final String FILTER_ELEMENT_TITLE_BLOCK = "hide.element.title.blocks.filter";

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
   * @param elementView
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
    return isContainerDiagram(containerView)
        && (!hasADiagramTitleBlock((DDiagram) containerView) || getVisibleDiagramTitleBlocks(containerView).isEmpty()
            || isFilterDiagramTitleBlocksEnabled((DDiagram) containerView));
  }

  /**
   * @param containerView:
   *          the element in diagram on which the annotation is attached
   * @return true if is valid to create an Element Title Block is valid if the containerView is a diagram element, is
   *         not annotation and there is not another annotation associated with elementView
   */
  public boolean isValidCreateElementTitleBlock(EObject containerView) {
    return (containerView instanceof DDiagramElement) && (!hasAElementTitleBlock((DDiagramElement) containerView)
        || getVisibleElementTitleBlocks(containerView).isEmpty()
        || isFilterElementTitleBlocksEnabled((DDiagramElement) containerView));
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
   * creates a Diagram Title Block
   * 
   * @param diagram
   * @return
   */
  public void createDiagramTitleBlock(DDiagram diagram) {
    createDiagramTitleBlock(diagram, true);
  }

  /**
   * creates a Diagram Title Block and if the filters are activated for the created element, the user will see a dialog
   * to choose if he wants to disable the filters.
   * 
   * @param diagram
   * @param checkFilters
   * @return
   */
  public void createDiagramTitleBlock(DDiagram diagram, boolean checkFilters) {
    DAnnotation titleBlock = TitleBlockHelper.addDiagramTitleBlock(diagram);
    addDiagramTitleBlockContent(diagram, titleBlock);
    createTitleBlockView(titleBlock, diagram, diagram);
    if (checkFilters) {
      checkTitleBlocksFilters(diagram, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
    }
  }

  /**
   * allows to deactivate filters when create/show-hide Diagram Title Block tools are used
   * 
   * @param diagram
   * @return
   */
  public boolean deactivateDiagramTitleBlockFilter(DDiagram diagram) {
    if (hasADiagramTitleBlock(diagram) && !getVisibleDiagramTitleBlocks(diagram).isEmpty()
        && isFilterDiagramTitleBlocksEnabled(diagram)) {
      return checkTitleBlocksFilters(diagram, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
    }
    return false;
  }

  /**
   * add the content (lines, columns, content) to a Diagram Title Block
   * 
   * @param diagram
   * @param titleBlock
   * @return
   */
  protected void addDiagramTitleBlockContent(DDiagram diagram, DAnnotation titleBlock) {
    int numLines = TitleBlockPreferencesInitializer.getLinesNumber();
    int numCols = TitleBlockPreferencesInitializer.getColumnsNumber();
    String[] titleBlockContent = TitleBlockPreferencesInitializer.getContentAsArray();
    // use this index to go trough the content stored in preferences for the content of Diagram TB
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
    createTitleBlockView(titleBlock, diagram, elementView);
    checkTitleBlocksFilters(diagram, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
  }

  /**
   * allows to deactivate filters when create/show-hide Diagram Title Block tools are used
   * 
   * @param diagram
   * @return
   */
  public boolean deactivateElementTitleBlockFilter(DDiagramElement elementView) {
    if (hasAElementTitleBlock(elementView) && !getVisibleElementTitleBlocks(elementView).isEmpty()
        && isFilterElementTitleBlocksEnabled(elementView)) {
      return checkTitleBlocksFilters((DDiagram) elementView.eContainer(), TitleBlockHelper.ELEMENT_TITLE_BLOCK);
    }
    return false;
  }

  /**
   * insert a new line to Element Title Block (after the current selected cell)
   * 
   * @param cell:
   *          the selected cell on which we click
   * @param diagram
   * @return
   */
  public void insertTitleBlockLine(DAnnotation cell, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell, diagram);
    int numCols = TitleBlockHelper.getNumOfColumns(titleBlock);
    if (numCols > 0) {
      // insert the new line under the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlock);
      DAnnotation annotationLine = TitleBlockHelper.addTitleBlockLine(diagram, titleBlock, indexLine + 1, numCols);
      createTitleBlockLineView(DiagramServices.getDiagramServices().getDiagramElement(diagram, titleBlock),
          annotationLine, diagram, diagram);
    }
  }

  /**
   * insert a new column to Element Title Block (after the current selected cell)
   * 
   * @param cell:
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
      DAnnotation annotationColumn = TitleBlockHelper.addTitleBlockCell(diagram, line, "", "", indexCol + 1);
      createTitleBlockColumnView(DiagramServices.getDiagramServices().getDiagramElement(diagram, line),
          annotationColumn, diagram, diagram);
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
   * 
   * get the list of Title Blocks related to this diagram
   * 
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB)
   */
  public List<DAnnotation> getTitleBlocksInDiagram(DDiagram diagram) {
    List<DAnnotation> allList = new ArrayList<DAnnotation>();
    allList.addAll(getElementTitleBlocks(diagram));
    allList.addAll(getDiagramTitleBlocks(diagram));
    return allList;
  }

  /**
   * performs a refresh operation so that the dangling TB are removed or updated according to preferences
   * 
   * @param diagram
   * @return list of Title Blocks (both Diagram or Element TB) that will be displayed in diagram
   */
  public void refreshTitleBlocksInDiagram(DDiagram diagram) {
    if (TitleBlockHelper.isInitializedDiagramTitleBlock(diagram) == false) {
      titleBlockFirstInitialize(diagram);
      if (TitleBlockPreferencesInitializer.isCreateDiagramTitleBlockByDefault()) {
        createDiagramTitleBlock(diagram, false);
      }
    }
    // delete the dangling element title blocks
    List<DAnnotation> listElementTitleBlocks = getElementTitleBlocks(diagram);
    handleDanglingElementTitleBlocks(listElementTitleBlocks, diagram);

    // refresh the diagram title block, so that if preferences have changed, TB is updated
    DAnnotation diagramTitleBlock = TitleBlockHelper.getDiagramTitleBlock(diagram);
    if (diagramTitleBlock != null) {
      updateDiagramTitleBlock(diagramTitleBlock, (EObject) diagram);
    }
  }

  /**
   * @param containerView
   * @return list of visible Diagram Title Blocks in diagram
   */
  public List<DAnnotation> getVisibleDiagramTitleBlocks(Object containerView) {
    return getVisibleTitleBlocks(containerView, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
  }

  /**
   * @param containerView
   * @return list of visible Element Title Blocks in diagram
   */
  public List<DAnnotation> getVisibleElementTitleBlocks(Object containerView) {
    return getVisibleTitleBlocks(containerView, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
  }

  /**
   * @param diagram
   * @return check if Diagram Title Block filter is enabled
   */
  public boolean isFilterDiagramTitleBlocksEnabled(DDiagram diagram) {
    return !getActivatedFilters(diagram, FILTER_DIAGRAM_TITLE_BLOCK).isEmpty();
  }

  /**
   * @param diagram
   * @return check if Diagram Title Block filter is enabled
   */
  public boolean isFilterElementTitleBlocksEnabled(DDiagramElement element) {
    if (element.eContainer() instanceof DDiagram) {
      DDiagram diagram = (DDiagram) element.eContainer();
      return !getActivatedFilters(diagram, FILTER_ELEMENT_TITLE_BLOCK).isEmpty();
    }
    return false;
  }

  public List<FilterDescription> getActivatedFilters(DDiagram diagram, String filerType) {
    return diagram.getActivatedFilters().stream().filter(x -> x.getName().equals(filerType))
        .collect(Collectors.toList());
  }

  /**
   * get the list of Title Blocks visible in diagram
   *
   * @param containerView
   * @param type:
   *          can be DIAGRAM_TITLE_BLOCK or ELEMENT_TITLE_BLOCK
   * @return list of Title Blocks (both Diagram or Element TB) that are visible in diagram
   */
  private List<DAnnotation> getVisibleTitleBlocks(Object containerView, String type) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    if (containerView instanceof DDiagramElementContainer) {
      for (DEdge edge : ((DDiagramElementContainer) containerView).getOutgoingEdges()) {
        if (edge.getTargetNode() instanceof DNodeContainer
            && ((DNodeContainer) edge.getTargetNode()).getTarget() instanceof DAnnotation) {
          list.add((DAnnotation) ((DNodeContainer) edge.getTargetNode()).getTarget());
        }
      }
    } else if (containerView instanceof DDiagram) {
      EList<DDiagramElement> elements = ((DDiagram) containerView).getOwnedDiagramElements();
      for (DDiagramElement element : elements) {
        if (element.getTarget() instanceof DAnnotation) {
          DAnnotation annotation = (DAnnotation) element.getTarget();
          if (annotation.getSource() != null && annotation.getSource().equals(type)) {
            if (type.equals(TitleBlockHelper.ELEMENT_TITLE_BLOCK)) {
              DNodeContainer node = (DNodeContainer) element;

              // do not return the Title Blocks that are associated to a hidden element
              if (!node.getIncomingEdges().isEmpty()) {
                DEdge edge = node.getIncomingEdges().get(0);
                if (edge.getSourceNode() != null)
                  list.add(annotation);
              }
            } else {
              list.add(annotation);
            }
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
   * get the list of Element Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks (Element TB) that will be displayed in diagram
   */
  public List<DAnnotation> getAvailableToInsertElementTitleBlocks(DDiagram diagram) {
    List<DAnnotation> allElementTitleBlocks = getElementTitleBlocks(diagram);
    List<DAnnotation> toInsertlementTitleBlocks = new ArrayList<>();

    // remove from list the TB that belong to a hidden element
    for (DAnnotation titleBlock : allElementTitleBlocks) {
      if (!titleBlock.getReferences().isEmpty()) {
        // Element Title Block is available to be inserted only if its associated element is visible
        if (DiagramServices.getDiagramServices().getDiagramElement(diagram,
            titleBlock.getReferences().get(0)) != null) {
          toInsertlementTitleBlocks.add(titleBlock);
        }
      }
    }
    return toInsertlementTitleBlocks;
  }

  /**
   * get the list of Element Title Blocks
   * 
   * @param elementView
   * @param diagram
   * @return list of Title Blocks (Element TB) for elementView
   */
  public List<DAnnotation> getAvailableToInsertElementTitleBlocks(DDiagramElement elementView, DDiagram diagram) {
    return getAvailableToInsertElementTitleBlocks(diagram).stream()
        .filter(x -> x.getReferences().contains(elementView.getTarget())).collect(Collectors.toList());
  }

  /**
   * get the Diagram Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks (Diagram) that will be displayed in diagram
   */
  public List<DAnnotation> getDiagramTitleBlocks(DDiagram diagram) {
    List<DAnnotation> list = new ArrayList<>();
    DAnnotation titleBlock = TitleBlockHelper.getDiagramTitleBlock(diagram);
    if (titleBlock != null) {
      list.add(titleBlock);
    }
    return list;
  }

  /**
   * update the Diagram Title Block if the preferences settings were updated
   * 
   * @param diagramTitleBlock
   * @param elementView
   * @return
   */
  private void updateDiagramTitleBlock(DAnnotation diagramTitleBlock, EObject elementView) {
    /*
     * create a string similar to the one stored in preferences of the Diagram Title Block content use this string to
     * compare with the one in preferences, if they are different, update the Title Block
     */
    String currentTB = "";

    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(diagramTitleBlock)) {
      for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
        currentTB += cell.getDetails().get(TitleBlockHelper.NAME) + SEPARATOR
            + cell.getDetails().get(TitleBlockHelper.CONTENT) + SEPARATOR;
      }
    }
    if (currentTB.length() > 0) {
      currentTB = currentTB.substring(0, currentTB.length() - SEPARATOR.length());
    }

    /*
     * if the current Title Block from diagram needs to be updated with the new preferences stored Title Block remove
     * the current Title Block and re-create it again
     */
    if (!currentTB.equals(TitleBlockPreferencesInitializer.getContent())) {
      removeTitleBlockContent((DDiagram) elementView, diagramTitleBlock);
      addDiagramTitleBlockContent((DDiagram) elementView, diagramTitleBlock);

      DDiagramElement nodeTitleBlock = DiagramServices.getDiagramServices().getDiagramElement(((DDiagram) elementView),
          diagramTitleBlock);
      for (EObject objLine : diagramTitleBlock.getReferences()) {
        if (objLine instanceof DAnnotation) {
          createTitleBlockLineView(nodeTitleBlock, (DAnnotation) objLine, ((DDiagram) elementView),
              ((DDiagram) elementView));
        }
      }
      DialectManager.INSTANCE.refresh((DDiagram) elementView, new NullProgressMonitor());
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
    List<EObject> hideList = new ArrayList<>();
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
            hideList.add(annotation);
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
      DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
    }

    if (!hideList.isEmpty()) {
      List<DAnnotation> elementsTB = getVisibleElementTitleBlocks(diagram);
      List<EObject> listToShow = new ArrayList<EObject>();
      listToShow.addAll(elementsTB);
      listToShow.removeAll(hideList);
      showHideTitleBlocks(diagram, listToShow, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
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
    removeTitleBlockContent(diagram, titleBlock);
    diagram.getEAnnotations().remove(titleBlock);
  }

  /**
   * this deletes Title Blocks lines, columns, content DAnnotations
   * 
   * @param diagram
   * @param titleBlock
   * @return
   */
  protected void removeTitleBlockContent(DDiagram diagram, DAnnotation titleBlock) {
    List<DAnnotation> annotationsList = new ArrayList<>();
    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
      annotationsList.add(line);
      for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
        annotationsList.add(cell);
        for (EObject reference : cell.getReferences()) {
          if (reference instanceof DAnnotation) {
            annotationsList.add((DAnnotation) reference);
          }
        }
        cell.getReferences().clear();
      }
      line.getReferences().clear();
    }
    titleBlock.getReferences().clear();
    diagram.getEAnnotations().removeAll(annotationsList);
  }

  /**
   * this function removes a line of the Title Block
   * 
   * @param cell
   * @param diagram
   * @return
   */
  public void removeLineOfTitleBlock(DAnnotation cell, DDiagram diagram) {
    if (TitleBlockHelper.isTitleBlockCell(cell)) {
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell, diagram);

      // remove the line of the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlockContainer);
      DAnnotation titleBlockLine = TitleBlockHelper.getTitleBlockLines(titleBlockContainer).get(indexLine);

      List<DAnnotation> toRemove = new ArrayList<>();
      toRemove.add(titleBlockLine);
      toRemove.addAll(TitleBlockHelper.getTitleBlockCells(titleBlockLine));
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
   * @param titleBlockContainer
   * @return the content of the cell, the returned object can be a EObject, Collection<?EObjects>; we wrap a primitive
   *         type in a DAnnotation object
   */
  public Object getCellContent(EObject diagram, EObject cell, EObject containerView, DAnnotation titleBlockContainer) {
    if ((diagram instanceof DDiagram)) {
      if (cell instanceof DAnnotation && TitleBlockHelper.isTitleBlockCell((DAnnotation) cell)) {
        String feature = ((DAnnotation) cell).getDetails().get(TitleBlockHelper.CONTENT);
        if (feature != null) {
          Object obj = TitleBlockHelper.getResultOfExpression(RepresentationHelper.getRepresentationDescriptor((DDiagram) diagram),
              feature, titleBlockContainer);
          if (obj != null && !(obj instanceof EvaluationException)) {
            if (obj instanceof Collection) {
              return ((Collection) obj).stream()
                  .map(object -> getWrappedObject(object, (DAnnotation) cell, (DDiagram) diagram))
                  .collect(Collectors.toList());
            }
            return getWrappedObject(obj, (DAnnotation) cell, (DDiagram) diagram);
          }
        }
      }
    }
    return null;
  }

  /**
   * 
   * @param diagram
   * @param cell
   * @param containerView
   * @return the content of the cell that will be displayed in diagram
   */
  public Object getTitleBlockCellContent(EObject diagram, EObject cell, EObject containerView) {
    if ((diagram instanceof DDiagram)) {
      if (cell instanceof DAnnotation && TitleBlockHelper.isTitleBlockCell((DAnnotation) cell)) {
        DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock((DAnnotation) cell, (DDiagram) diagram);
        if (titleBlockContainer != null) {
          return getCellContent(diagram, cell, containerView, titleBlockContainer);
        }
      }
    }
    return null;
  }

  /**
   * get an EObject; wrap the object in DAnnotation if is not already an EObject; if the content of the cell is already
   * stored in a DAnnotation, just update the content if the cell content changed
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
    if (cell.getReferences().isEmpty()) {
      wrapperAnnotation = DescriptionFactory.eINSTANCE.createDAnnotation();
      wrapperAnnotation.setSource(TitleBlockHelper.TITLE_BLOCK_CONTENT);
      wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, object.toString());
      cell.getReferences().add(wrapperAnnotation);
      diagram.getEAnnotations().add(wrapperAnnotation);
    } else {
      wrapperAnnotation = (DAnnotation) cell.getReferences().get(0);
      if (!object.toString().equals(wrapperAnnotation.getDetails().get(TitleBlockHelper.CONTENT))) {
        wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, object.toString());
      }
    }
    return wrapperAnnotation;
  }

  /**
   * create a DAnnotation when diagram is first created
   * 
   * @param diagram
   */
  private void titleBlockFirstInitialize(DDiagram diagram) {
    DAnnotation titleBlockInitialized = DescriptionFactory.eINSTANCE.createDAnnotation();
    titleBlockInitialized.setSource(TitleBlockHelper.TITLE_BLOCK_INITIALIZED);
    diagram.getEAnnotations().add(titleBlockInitialized);
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

  public EObject showHideDiagramTitleBlocks(EObject context, List<EObject> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, TitleBlockHelper.DIAGRAM_TITLE_BLOCK);
  }

  public EObject showHideElementTitleBlocks(EObject context, List<EObject> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, TitleBlockHelper.ELEMENT_TITLE_BLOCK);
  }

  /**
   * show or hide a Title Block depending on given type (DIAGRAM_TITLE_BLOCK or ELEMENT_TITLE_BLOCK). selectedTypes are
   * the elements that shall be displayed
   * 
   * @param elementView
   * @param selectedTypes
   * @param type
   * @return String the label of the content cell
   */
  public EObject showHideTitleBlocks(final EObject elementView, List<EObject> selectedTypes, String type) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(elementView);

    Map<EObject, AbstractDNode> existingTypes = new HashMap<>();
    for (EObject currentContainer : DiagramServices.getDiagramServices().getAllContainersAndNodeLists(elementView)) {
      AbstractDNode aContainer = (AbstractDNode) currentContainer;
      if ((aContainer.getTarget() instanceof DAnnotation)) {
        DAnnotation annotation = (DAnnotation) aContainer.getTarget();
        if (annotation.getSource() != null && annotation.getSource().equals(type))
          existingTypes.put(aContainer.getTarget(), aContainer);
      }
    }

    for (Entry<EObject, AbstractDNode> me : existingTypes.entrySet()) {
      if (!selectedTypes.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeContainerView(me.getValue());
      }
    }
    for (EObject aType : selectedTypes) {
      if (!existingTypes.containsKey(aType)) {
        createTitleBlockView((DAnnotation) aType, diagram, elementView);
        checkTitleBlocksFilters(diagram, type);
      }
    }
    return elementView;
  }

  /**
   * creates a Container Node for the given mapping name
   * 
   * @param titleBlock
   * @param diagram
   * @param context
   * @param mappingName
   * @return DNodeContainer the created graphical node for the given mapping
   */
  private DNodeContainer createTitleBlockContainerNode(DAnnotation annotation, DDiagram diagram, EObject context,
      String mappingName) {
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
    return DiagramServices.getDiagramServices().createContainer(mapping, annotation, (DragAndDropTarget) context,
        diagram);
  }

  /**
   * creates the graphical view of the Title Block (including lines and columns)
   * 
   * @param titleBlock
   * @param diagram
   * @param context
   * @return
   */
  protected void createTitleBlockView(DAnnotation titleBlock, DDiagram diagram, EObject context) {
    try {
      // create the DT_TITLE_BLOCK_CONTAINER
      DNodeContainer nodeTitleBlock = createTitleBlockContainerNode(titleBlock, diagram, diagram,
          IMappingNameConstants.DT_TITLE_BLOCK_CONTAINER);

      for (EObject objLine : titleBlock.getReferences()) {
        if (objLine instanceof DAnnotation) {
          createTitleBlockLineView(nodeTitleBlock, (DAnnotation) objLine, diagram, context);
        }
      }
    } catch (RuntimeException e) {
    }
  }

  /**
   * creates the graphical view of a line for a Title Block (creates also the views for each column)
   * 
   * @param nodeTitleBlock
   * @param annotationLine
   * @param diagram
   * @param context
   * @return
   */
  protected void createTitleBlockLineView(DDiagramElement nodeTitleBlock, DAnnotation annotationLine, DDiagram diagram,
      EObject context) {
    try {
      // create the DT_TITLE_BLOCK_LINE_CONTAINER
      DNodeContainer nodeLine = createTitleBlockContainerNode(annotationLine, diagram, nodeTitleBlock,
          IMappingNameConstants.DT_TITLE_BLOCK_LINE_CONTAINER);

      for (EObject annotationCol : annotationLine.getReferences()) {
        createTitleBlockColumnView(nodeLine, (DAnnotation) annotationCol, diagram, context);
      }
    } catch (RuntimeException e) {
    }
  }

  /**
   * creates the graphical view of a column for a Title Block
   * 
   * @param nodeLine
   * @param annotationCol
   * @param diagram
   * @param context
   * @return
   */
  protected void createTitleBlockColumnView(DDiagramElement nodeLine, DAnnotation annotationCol, DDiagram diagram,
      EObject context) {
    try {
      ContainerMapping mappingCol = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.DT_TITLE_BLOCK_COLUMN_CONTAINER);
      DiagramServices.getDiagramServices().createDNodeListElement(mappingCol, annotationCol,
          (DragAndDropTarget) nodeLine, diagram);
    } catch (RuntimeException e) {
    }
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

  /**
   * 
   * @param element
   * @return true if element is a Diagram Title Block
   */
  public boolean isDiagramTitleBlock(EObject element) {
    return (element instanceof DAnnotation) && TitleBlockHelper.isDiagramTitleBlock((DAnnotation) element);
  }

  /**
   * check if the filters are activated for Element Title Block or Diagram Title Block
   * 
   * @param diagram
   * @param type
   *          (Diagram or Element TB)
   * @return boolean
   */
  protected boolean checkTitleBlocksFilters(DDiagram diagram, String type) {
    List<FilterDescription> activatedFiltersOnNode = getActivatedFilters(diagram, getFilterLable(type));
    if (!activatedFiltersOnNode.isEmpty()) {
      String typeLabel = TitleBlockHelper.getTitleBlockName(type);
      boolean confirmation = MessageDialog.openQuestion(Display.getDefault().getActiveShell(), FILTERS_DIALOG_TITLE,
          NLS.bind(FILTERS_TITLE_BLOCKS_MESSAGE, typeLabel, typeLabel));
      if (confirmation) {
        // remove the filter
        AbstractCommand cmd = new AbstractReadWriteCommand() {
          @Override
          public void run() {
            EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
            activatedFilters.removeAll(activatedFiltersOnNode);
            CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
            builder.computeCompositeFilterApplications();
          }
        };
        TransactionHelper.getExecutionManager(diagram).execute(cmd);
        return true;
      }
    }
    return false;
  }

  private String getFilterLable(String type) {
    if (type.equals(TitleBlockHelper.DIAGRAM_TITLE_BLOCK)) {
      return FILTER_DIAGRAM_TITLE_BLOCK;
    }
    if (type.equals(TitleBlockHelper.ELEMENT_TITLE_BLOCK)) {
      return FILTER_ELEMENT_TITLE_BLOCK;
    }
    return "";
  }
}
