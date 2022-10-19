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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
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
    return !TitleBlockHelper.getDiagramTitleBlocks(diagram).isEmpty();
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
    return isContainerDiagram(containerView);
  }

  /**
   * @param containerView:
   *          the element in diagram on which the annotation is attached
   * @return true if is valid to create an Element Title Block is valid if the containerView is a diagram element, is
   *         not title block
   */
  public boolean isValidCreateElementTitleBlock(EObject containerView) {
    return (containerView instanceof DDiagramElement
        && !TitleBlockHelper.isTitleBlockAnnotation(((DDiagramElement) containerView).getTarget()));
  }

  public boolean isValidCreateTitleBlock(EObject containerView) {
    return isValidCreateDiagramTitleBlock(containerView) || isValidCreateElementTitleBlock(containerView);
  }

  /**
   * check if is valid to insert a new line or a new column.
   * 
   * @param containerView
   * @return
   */
  public boolean isValidInsertLineOrColumn(EObject containerView) {
    if (containerView instanceof DDiagramElement) {
      DDiagramElement diagramElement = (DDiagramElement) containerView;
      return TitleBlockHelper.isTitleBlockCell(diagramElement);
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
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(annotation);
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
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(annotation);
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
   * insert a new line to Title Block (after the current selected cell)
   * 
   * @param cell:
   *          the selected cell on which we click
   * @param diagram
   * @return
   */
  public void insertTitleBlockLine(DAnnotation cell, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell);
    int numCols = TitleBlockHelper.getNumOfColumns(titleBlock);
    if (numCols > 0) {
      // insert the new line under the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlock);
      DAnnotation annotationLine = TitleBlockHelper.addTitleBlockLine(diagram, titleBlock, indexLine + 1, numCols);
      createTitleBlockLineView(DiagramServices.getDiagramServices().getDiagramElement(diagram, titleBlock),
          annotationLine, diagram, diagram, indexLine + 1);
    }
  }

  /**
   * insert a new column to Title Block (after the current selected cell)
   * 
   * @param cell:
   *          the selected cell
   * @param diagram
   * @return
   */
  public void insertTitleBlockColumn(DAnnotation cell, DDiagram diagram) {
    DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(cell);
    // insert the new column at the right of the cell we clicked on
    int indexCol = TitleBlockHelper.getColumnIndexOfCell(cell, titleBlock);
    for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
      // Add new empty cell
      DAnnotation annotationColumn = TitleBlockHelper.addTitleBlockCell(diagram, line, "", "", indexCol + 1);
      createTitleBlockColumnView(DiagramServices.getDiagramServices().getDiagramElement(diagram, line),
          annotationColumn, diagram, diagram, indexCol + 1);
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
   * @return list of Elements (will be one Element) that are associated to a given Title Block as parameter
   */
  public List<EObject> targetFinderExpressionForTitleBlocks(EObject element) {
    List<EObject> list = new ArrayList<EObject>();
    if (element instanceof DAnnotation) {
      list.add(TitleBlockHelper.getReferencedElement(element));
    }
    return list;
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
    if (diagram.getOwnedDiagramElements().isEmpty()
        && TitleBlockPreferencesInitializer.isCreateDiagramTitleBlockByDefault()) {
      createDiagramTitleBlock(diagram, false);
    }

    handleDanglingElementTitleBlocks(diagram);
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

  public Set<DAnnotation> getVisibleTitleBlocks(Object containerView) {
    return Stream.concat(getVisibleDiagramTitleBlocks(containerView).stream(),
        getVisibleElementTitleBlocks(containerView).stream()).collect(Collectors.toSet());
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
    if (containerView instanceof EdgeTarget) {
      return getAssociatedElementTitleBlocks((EdgeTarget) containerView);
    } else if (containerView instanceof DDiagram) {
      return getAllAssociatedTitleBlocks((DDiagram) containerView, type);
    }
    return new ArrayList<DAnnotation>();
  }

  /**
   * get the Element Title Block associated to an element in diagram
   *
   * @param edgeTarget
   * @return a Element Title Block or null
   */
  private List<DAnnotation> getAssociatedElementTitleBlocks(EdgeTarget edgeTarget) {
    List<DAnnotation> elementTBs = new ArrayList<>();
    for (DEdge edge : edgeTarget.getIncomingEdges()) {
      if (edge.getSourceNode() instanceof DNodeContainer
          && ((DNodeContainer) edge.getSourceNode()).getTarget() instanceof DAnnotation) {
        elementTBs.add((DAnnotation) ((DNodeContainer) edge.getSourceNode()).getTarget());
      }
    }
    return elementTBs;
  }

  /**
   * get the list of associated title blocks by type (type: can be all the Element Title Blocks in diagram or the
   * Diagram Title Block)
   * 
   * @param diagram
   * @param type
   * @return list of Title Blocks (Element TB/Diagram TB) that are displayed in the diagram
   */
  private List<DAnnotation> getAllAssociatedTitleBlocks(DDiagram diagram, String type) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    list = diagram.getDiagramElements().stream()
        .filter(x -> x instanceof DNodeContainer && x.getTarget() instanceof DAnnotation && type != null
            && type.equals(((DAnnotation) x.getTarget()).getSource()))
        .map(x -> (DAnnotation) x.getTarget()).collect(Collectors.toList());
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
   * get the list of Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks that will be displayed in diagram
   */
  public List<DAnnotation> getAvailableToInsertTitleBlocks(DDiagram diagram) {
    List<DAnnotation> allTitleBlocks = getTitleBlocksInDiagram(diagram);

    // remove from list the TB that belong to a hidden element
    List<DAnnotation> ignoredTBs = allTitleBlocks.stream().filter(tb -> !tb.getReferences().isEmpty())
        .filter(TitleBlockHelper::isElementTitleBlock)
        .filter(
            tb -> DiagramServices.getDiagramServices().getDiagramElement(diagram, tb.getReferences().get(0)) == null)
        .collect(Collectors.toList());

    allTitleBlocks.removeAll(ignoredTBs);
    return allTitleBlocks;
  }

  /**
   * get the list of Element Title Blocks
   * 
   * @param elementView
   * @param diagram
   * @return list of Title Blocks (Element TB) for elementView
   */
  public List<DAnnotation> getAvailableToInsertTitleBlocks(EObject elementView, DDiagram diagram) {
    if (elementView instanceof EdgeTarget)
      return getAvailableToInsertTitleBlocks(diagram).stream()
          .filter(x -> x.getReferences().contains(((DDiagramElement) elementView).getTarget()))
          .collect(Collectors.toList());
    return getAvailableToInsertTitleBlocks(diagram);
  }

  /**
   * get the Diagram Title Blocks
   * 
   * @param diagram
   * @return list of Title Blocks (Diagram) that will be displayed in diagram
   */
  public List<DAnnotation> getDiagramTitleBlocks(DDiagram diagram) {
    return TitleBlockHelper.getDiagramTitleBlocks(diagram);
  }

  /**
   * Handles the dangling Element Title Blocks in two situations: if the element associated to an element TB was deleted
   * from model -> delete the TB; if the element associate to an element TB was removed from diagram -> hide the TB
   * 
   * @param diagram
   *          the diagram.
   */
  private void handleDanglingElementTitleBlocks(DDiagram diagram) {
    Set<DAnnotation> titleBlocks = getVisibleTitleBlocks(diagram);

    List<DAnnotation> titleBlocksToDelete = new ArrayList<>();
    List<DAnnotation> titleBlocksToHide = new ArrayList<>();

    for (DAnnotation titleBlock : titleBlocks) {
      if (TitleBlockHelper.isElementTitleBlock(titleBlock)) {
        EObject semanticReference = TitleBlockHelper.getSemanticElementReference(titleBlock);

        if (semanticReference != null) {
          DDiagramElement semanticElementView = DiagramServices.getDiagramServices().getDiagramElement(diagram,
              semanticReference);
          if (semanticElementView == null) {
            titleBlocksToHide.add(titleBlock);
          }

        } else {
          titleBlocksToDelete.add(titleBlock);
        }
      }
    }

    if (!titleBlocksToDelete.isEmpty()) {
      for (DAnnotation titleBlock : titleBlocksToDelete) {
        DDiagramElement titleBlockView = DiagramServices.getDiagramServices().getDiagramElement(diagram, titleBlock);

        if (titleBlockView != null) {
          DiagramServices.getDiagramServices().removeContainerView(titleBlockView);
        }

        deleteTitleBlock(diagram, titleBlock);
      }

      titleBlocks.removeAll(titleBlocksToDelete);
    }

    if (!titleBlocksToHide.isEmpty()) {
      titleBlocks.removeAll(titleBlocksToHide);

      showHideTitleBlocks(diagram, titleBlocks);
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
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    descriptor.getEAnnotations().remove(titleBlock);
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
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    descriptor.getEAnnotations().removeAll(annotationsList);
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
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell);

      // remove the line of the cell we clicked on
      int indexLine = TitleBlockHelper.getLineIndexOfCell(cell, titleBlockContainer);
      DAnnotation titleBlockLine = TitleBlockHelper.getTitleBlockLines(titleBlockContainer).get(indexLine);

      List<DAnnotation> toRemove = new ArrayList<>();
      toRemove.add(titleBlockLine);
      toRemove.addAll(TitleBlockHelper.getTitleBlockCells(titleBlockLine));
      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
      descriptor.getEAnnotations().removeAll(toRemove);
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
      DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(cell);

      // remove the column of the cell we clicked on
      int indexCol = TitleBlockHelper.getColumnIndexOfCell(cell, titleBlockContainer);

      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
      // go in each line and remove a cell on column of the clicked cell
      for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlockContainer)) {
        EObject removedCell = line.getReferences().remove(indexCol);
        descriptor.getEAnnotations().remove(removedCell);
      }
    }
  }

  /**
   * Interprets the evaluation expression of the cell and returns the result. EObjects are returned as they are, but
   * primitive values are wrapped in a DAnnotations (since Sirius needs an EObject target).
   * 
   * @param diagram
   * @param cell
   * @param titleBlockContainer
   * @return the cell content to display.
   */
  public Object getCellContent(DDiagram diagram, DAnnotation cell, DAnnotation titleBlockContainer) {
    if (TitleBlockHelper.isTitleBlockCell(cell)) {
      String evaluationExpression = cell.getDetails().get(TitleBlockHelper.CONTENT);

      if (evaluationExpression != null) {
        Object evaluationResult = TitleBlockHelper.getResultOfExpression(
            RepresentationHelper.getRepresentationDescriptor(diagram), evaluationExpression, titleBlockContainer);

        if (evaluationResult != null && !(evaluationResult instanceof EvaluationException)) {
          if (evaluationResult instanceof Collection) {
            Collection<?> evaluationResultCollection = (Collection<?>) evaluationResult;
            return getCellContentFromCollection(diagram, cell, evaluationResultCollection);
          }

          return getCellContentFromObject(diagram, cell, evaluationResult);
        }
      }
    }

    return null;
  }

  /**
   * Extracts an EObject from the evaluation result of a Title Block content. Since a cell can only contain EObjects,
   * primitive values are wrapped in a DAnnotation.
   * 
   * @param diagram
   * @param cell
   * @param evaluationResult
   * @return the extracted EObject from the evaluation result of a Title Block content.
   */
  private EObject getCellContentFromObject(DDiagram diagram, DAnnotation cell, Object evaluationResult) {
    return evaluationResult instanceof EObject ? (EObject) evaluationResult
        : createOrUpdatePrimitiveAnnotation(Arrays.asList(evaluationResult), cell, diagram);
  }

  /**
   * Extracts a collection of EObjects from the evaluation result of a Title Block content. Since a cell can only
   * contain EObjects, all the primitive objects are combined into a DAnnotation.
   * 
   * @param diagram
   * @param cell
   * @param evaluationResult
   * @return the extracted collection of EObjects from the evaluation result of a Title Block content.
   */
  private List<EObject> getCellContentFromCollection(DDiagram diagram, DAnnotation cell,
      Collection<?> evaluationResult) {
    List<EObject> wrappedEvaluationResult = new ArrayList<>();
    List<Object> primitiveEvaluationResult = new ArrayList<>();

    for (Object object : evaluationResult) {
      if (object instanceof EObject) {
        wrappedEvaluationResult.add((EObject) object);
      } else {
        primitiveEvaluationResult.add(object);
      }
    }

    EObject primitiveResultAnnotation = createOrUpdatePrimitiveAnnotation(primitiveEvaluationResult, cell, diagram);
    wrappedEvaluationResult.add(primitiveResultAnnotation);

    return wrappedEvaluationResult;
  }

  /**
   * 
   * @param diagram
   * @param cell
   * @param containerView
   * @return the content of the cell that will be displayed in diagram
   */
  public Object getTitleBlockCellContent(EObject diagram, EObject cell) {
    if (diagram instanceof DDiagram && cell instanceof DAnnotation) {
      DAnnotation titleBlockCell = (DAnnotation) cell;
      if (TitleBlockHelper.isTitleBlockCell(titleBlockCell)) {
        DAnnotation titleBlockContainer = TitleBlockHelper.getParentTitleBlock(titleBlockCell);
        if (titleBlockContainer != null) {
          return getCellContent((DDiagram) diagram, titleBlockCell, titleBlockContainer);
        }
      }
    }
    return null;
  }

  /**
   * Primitive values must be wrapped in a DAnnotations (since Sirius needs an EObject target). In order to avoid an
   * unneeded dirty session, a new primitive DAnnotation is created only when the new primitive result is different than
   * the existing one.
   * 
   * @param primitiveEvaluationResult
   * @param cell
   * @param diagram
   * @return a DAnnotation wrapping a primitive result
   */
  private DAnnotation createOrUpdatePrimitiveAnnotation(List<Object> primitiveEvaluationResult, DAnnotation cell,
      DDiagram diagram) {

    DAnnotation wrapperAnnotation = null;
    String serializedResult = primitiveEvaluationResult.stream().map(o -> htmlToPlainText(o.toString()))
        .collect(Collectors.joining("\n"));

    if (cell.getReferences().isEmpty()) {
      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
      wrapperAnnotation = DAnnotationHelper.createAnnotation(TitleBlockHelper.TITLE_BLOCK_CONTENT, descriptor);
      wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, serializedResult);
      cell.getReferences().add(wrapperAnnotation);
    } else {
      wrapperAnnotation = (DAnnotation) cell.getReferences().get(0);
      String existingResult = wrapperAnnotation.getDetails().get(TitleBlockHelper.CONTENT);
      if (!serializedResult.equals(existingResult)) {
        wrapperAnnotation.getDetails().put(TitleBlockHelper.CONTENT, serializedResult);
      }
    }
    return wrapperAnnotation;
  }

  private String htmlToPlainText(String content) {
    return StringEscapeUtils.unescapeHtml(content).replaceAll("\\<[^>]*>", "").trim();
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

  public EObject showHideTitleBlocks(EObject targetView, Collection<DAnnotation> titleBlockUserSelection) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(targetView);
    Map<DAnnotation, Collection<DSemanticDecorator>> existingTitleBlocksToViews = Collections.emptyMap();

    if (targetView instanceof EdgeTarget) {
      existingTitleBlocksToViews = getTitleBLocksToViewsMap((EdgeTarget) targetView, diagram);
    } else {
      existingTitleBlocksToViews = getTitleBLocksToViewsMap(diagram);
    }

    hideTitleBlocks(titleBlockUserSelection, existingTitleBlocksToViews);

    showTitleBlocks(targetView, diagram, titleBlockUserSelection, existingTitleBlocksToViews);

    return targetView;
  }

  private Map<DAnnotation, Collection<DSemanticDecorator>> getTitleBLocksToViewsMap(EdgeTarget targetView,
      DDiagram diagram) {
    DiagramServices diagramServices = DiagramServices.getDiagramServices();
    Map<DAnnotation, Collection<DSemanticDecorator>> existingTitleBlocksToViews = new HashMap<>();

    List<DAnnotation> associatedTitleBlocks = getAssociatedElementTitleBlocks(targetView);
    for (DAnnotation titleBLock : associatedTitleBlocks) {
      Collection<DSemanticDecorator> titleBlockViews = diagramServices.getDiagramElements(diagram, titleBLock);
      existingTitleBlocksToViews.put(titleBLock, titleBlockViews);
    }

    return existingTitleBlocksToViews;
  }

  private Map<DAnnotation, Collection<DSemanticDecorator>> getTitleBLocksToViewsMap(DDiagram diagram) {
    Map<DAnnotation, Collection<DSemanticDecorator>> existingTitleBlocksToViews = new HashMap<>();

    for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
      EObject target = diagramElement.getTarget();
      if (target instanceof DAnnotation) {
        DAnnotation annotation = (DAnnotation) target;
        if (TitleBlockHelper.isTitleBlock(annotation) && annotation.getSource() != null) {
          existingTitleBlocksToViews.computeIfAbsent(annotation, key -> new HashSet<>()).add(diagramElement);
        }
      }
    }

    return existingTitleBlocksToViews;
  }

  private void hideTitleBlocks(Collection<DAnnotation> titleBlockUserSelection,
      Map<DAnnotation, Collection<DSemanticDecorator>> existingTitleBlocksToViews) {

    DiagramServices diagramServices = DiagramServices.getDiagramServices();

    for (Entry<DAnnotation, Collection<DSemanticDecorator>> existingEntry : existingTitleBlocksToViews.entrySet()) {
      DAnnotation existingTitleBlock = existingEntry.getKey();

      if (!titleBlockUserSelection.contains(existingTitleBlock)) {
        Collection<DSemanticDecorator> titleBlockViews = existingEntry.getValue();

        for (DSemanticDecorator view : titleBlockViews) {
          if (view instanceof DEdge) {
            diagramServices.removeEdgeView((DEdge) view);
          } else {
            diagramServices.removeContainerView(view);
          }
        }
      }
    }
  }

  private void showTitleBlocks(EObject targetView, DDiagram diagram, Collection<DAnnotation> titleBlockUserSelection,
      Map<DAnnotation, Collection<DSemanticDecorator>> existingTitleBlocksToViews) {

    for (DAnnotation selectedTitleBLock : titleBlockUserSelection) {
      if (!existingTitleBlocksToViews.containsKey(selectedTitleBLock)) {
        String titleBlockType = TitleBlockHelper.isDiagramTitleBlock(selectedTitleBLock)
            ? TitleBlockHelper.DIAGRAM_TITLE_BLOCK
            : TitleBlockHelper.ELEMENT_TITLE_BLOCK;

        createTitleBlockView(selectedTitleBLock, diagram, targetView);
        checkTitleBlocksFilters(diagram, titleBlockType);
      }
    }
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
      String mappingName, int index) {
    ContainerMapping mapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
    return DiagramServices.getDiagramServices().createContainer(mapping, annotation, (DragAndDropTarget) context,
        diagram, index);
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
          IMappingNameConstants.DT_TITLE_BLOCK_CONTAINER, -1);

      for (EObject objLine : titleBlock.getReferences()) {
        int i = 0;
        if (objLine instanceof DAnnotation) {
          createTitleBlockLineView(nodeTitleBlock, (DAnnotation) objLine, diagram, context, i);
          i++;
        }
      }

      if (TitleBlockHelper.isElementTitleBlock(titleBlock) && context instanceof EdgeTarget) {
        EdgeMapping edgeMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.DT_TITLE_BLOCK_EDGE);
        DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) nodeTitleBlock, (EdgeTarget) context,
            titleBlock);
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
      EObject context, int index) {
    try {
      // create the DT_TITLE_BLOCK_LINE_CONTAINER
      DNodeContainer nodeLine = createTitleBlockContainerNode(annotationLine, diagram, nodeTitleBlock,
          IMappingNameConstants.DT_TITLE_BLOCK_LINE_CONTAINER, index);
      int i = 0;
      for (EObject annotationCol : annotationLine.getReferences()) {
        createTitleBlockColumnView(nodeLine, (DAnnotation) annotationCol, diagram, context, i);
        i++;
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
      EObject context, int index) {
    try {
      ContainerMapping mappingCol = DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.DT_TITLE_BLOCK_COLUMN_CONTAINER);
      DiagramServices.getDiagramServices().createDNodeListElement(mappingCol, annotationCol,
          (DragAndDropTarget) nodeLine, diagram, index);
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
      return TitleBlockHelper.isDiagramTitleBlock(annotation) || TitleBlockHelper.isElementTitleBlock(annotation);
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
