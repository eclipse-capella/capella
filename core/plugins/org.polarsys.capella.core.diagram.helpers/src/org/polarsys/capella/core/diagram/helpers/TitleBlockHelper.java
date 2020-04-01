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
package org.polarsys.capella.core.diagram.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Various helpers for {@link DAnnotation} annotations on {@link Title Blocks} elements.
 */
public class TitleBlockHelper {
  public static final String TITLE_BLOCK_LINE = "TitleBlockLine";
  public static final String TITLE_BLOCK_CELL = "TitleBlockCell";
  public static final String ELEMENT_TITLE_BLOCK = "ElementTitleBlock";
  public static final String VISIBILITY = "Visibility";
  public static final String TRUE = "True";
  public static final String NAME = "Name:";
  public static final String CONTENT = "Content:";
  public static final String DIAGRAM_TITLE_BLOCK = "DiagramTitleBlock";
  
  /**
   * @param titleBlock
   * @return true if the annotation is a Diagram Title Block type annotation
   */
  public static boolean isDiagramTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getSource() != null && titleBlock.getSource().equals(DIAGRAM_TITLE_BLOCK);
  }
  /**
   * @param titleBlock
   * @return true if the annotation is a Element Title Block type annotation
   */
  public static boolean isElementTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getSource() != null && titleBlock.getSource().equals(ELEMENT_TITLE_BLOCK);
  }
  
  /**
   * @param annotation
   * @return true if the annotation is a Title Block type annotation
   */
  public static boolean isTitleBlock(DAnnotation annotation) {
    return isDiagramTitleBlock(annotation) || isElementTitleBlock(annotation);
  }
  
  /**
   * @param annotation
   * @return true if the annotation is a Title Block Line type annotation
   */
  public static boolean isTitleBlockLine(DAnnotation annotation) {
    return annotation.getSource() != null && annotation.getSource().equals(TITLE_BLOCK_LINE);
  }

  /**
   * @param annotation
   * @return true if the annotation is a Title Block Cell type annotation
   */
  public static boolean isTitleBlockCell(DAnnotation annotation) {
    return annotation.getSource() != null && annotation.getSource().equals(TITLE_BLOCK_CELL);
  }

  /**
   * @param diagramElement
   * @return true if the diagramElement is a Title Block Cell
   */
  public static boolean isTitleBlockCell(DDiagramElement diagramElement) {
    return diagramElement.getTarget() instanceof DAnnotation
        && isTitleBlockCell((DAnnotation) diagramElement.getTarget());
  }

  public static List<DAnnotation> getElementTitleBlocks(DDiagram diagram) {
    return diagram.getEAnnotations().stream().filter(a -> a.getSource().equals(ELEMENT_TITLE_BLOCK))
        .collect(Collectors.toList());
  }

  public static DAnnotation getDiagramTitleBlock(DDiagram diagram) {
    Optional<DAnnotation> blockOpt = diagram.getEAnnotations().stream().filter(a -> a.getSource().equals(DIAGRAM_TITLE_BLOCK)).findFirst();
    if (blockOpt.isPresent()) {
      return blockOpt.get();
    }
    return null;
  }
  
  public static List<DAnnotation> getElementTitleBlocks(DDiagram diagram, EObject element) {
    return getElementTitleBlocks(diagram).stream().filter(block -> block.getReferences().contains(element))
        .collect(Collectors.toList());
  }

  public static List<DAnnotation> getTitleBlockLines(DAnnotation titleBlock) {
    List<DAnnotation> lines = new ArrayList<>();
    if (isTitleBlock(titleBlock)) {
      for (EObject element : titleBlock.getReferences()) {
        if (element instanceof DAnnotation && isTitleBlockLine((DAnnotation) element)) {
          lines.add((DAnnotation) element);
        }
      }
    }
    return lines;
  }

  public static List<DAnnotation> getTitleBlockCells(DAnnotation titleBlockLine) {
    List<DAnnotation> cells = new ArrayList<>();
    if (isTitleBlockLine(titleBlockLine)) {
      for (EObject element : titleBlockLine.getReferences()) {
        if (element instanceof DAnnotation && isTitleBlockCell((DAnnotation) element)) {
          cells.add((DAnnotation) element);
        }
      }
    }
    return cells;
  }
  
  /**
   * 
   * @param container
   * @param cell
   * @return whether the container contain the cell
   */
  public static boolean containsCell(DAnnotation container, DAnnotation cell) {
    if (isTitleBlock(container) && isTitleBlockCell(cell)) {
      return getTitleBlockLines(container).stream().flatMap(line -> getTitleBlockCells(line).stream())
          .anyMatch(c -> c.equals(cell));
    } else if (isTitleBlockLine(container) && isTitleBlockCell(cell)) {
      return getTitleBlockCells(container).stream().anyMatch(c -> c.equals(cell));
    }
    return false;
  }

  /**
   * function that return the container Title Block of a selected cell; the container is the one storing all together
   * the lines and columns
   * 
   * @param titleBlockCell:
   *          the selected cell
   * @param diagram:
   * @return the parent title block
   */
  public static DAnnotation getParentTitleBlock(DAnnotation titleBlockCell, DDiagram diagram) {
    if (isTitleBlockCell(titleBlockCell)) {
      for (DDiagramElement diagramElem : diagram.getOwnedDiagramElements()) {
        if (diagramElem.getTarget() instanceof DAnnotation && isTitleBlock((DAnnotation) diagramElem.getTarget())) {
          DAnnotation titleBlock = (DAnnotation) diagramElem.getTarget();
          if (containsCell(titleBlock, titleBlockCell)) {
            return titleBlock;
          }
        }
      }
    }
    return null;
  }
  
  /**
   * 
   * @param titleBlock
   * @return then semantic element attached to a Title Block
   */
  public static EObject getSemanticElementReference(DAnnotation titleBlock) {
    List<EObject> modelElements = titleBlock.getReferences().stream()
        .filter(x -> !(x instanceof DAnnotation)).collect(Collectors.toList());
    if(!modelElements.isEmpty())
      return modelElements.iterator().next();
    return null;
  }
  
  /**
   * function that return the index of the column of a selected cell in a Title Block
   * 
   * @param titleBlock:
   *          the selected cell
   * @param titleBlockContainer:
   *          the container Title Block (which stores all the lines + cols)
   * @return int
   */
  public static int getColumnIndexOfCell(DAnnotation titleBlockCell, DAnnotation titleBlock) {
    for (DAnnotation line : getTitleBlockLines(titleBlock)) {
      List<DAnnotation> cells = getTitleBlockCells(line);
      for (int index = 0; index < cells.size(); index++) {
        if (cells.get(index).equals(titleBlockCell)) {
          return index;
        }
      }
    }
    return -1;
  }
  
  /**
   * function that return the index of the line of a selected cell in a Title Block
   * 
   * @param cell:
   *          the selected cell
   * @param titleBlock:
   *          the container Title Block (which stores all  the lines + cols)
   * @return
   */
  public static int getLineIndexOfCell(DAnnotation cell, DAnnotation titleBlock) {
    List<DAnnotation> lines = getTitleBlockLines(titleBlock);
    for (int index = 0; index < lines.size(); index++) {
      if (containsCell(lines.get(index), cell)) {
        return index;
      }
    }
    return -1;
  }
  

  /**
   * Create a cell where with the settings from parameters
   * 
   * @return DAnnotation
   */
  public static DAnnotation createTitleBlockCell(String name, String content) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(TITLE_BLOCK_CELL);
    annotation.getDetails().put(NAME, name);
    annotation.getDetails().put(CONTENT, content);
    return annotation;
  }

  /**
   * Add new Title Block to diagram
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addDiagramTitleBlock(DDiagram diagram) {
    DAnnotation titleBlock = DescriptionFactory.eINSTANCE.createDAnnotation();
    titleBlock.setSource(DIAGRAM_TITLE_BLOCK);
    titleBlock.getDetails().put(VISIBILITY, TRUE);
    diagram.getEAnnotations().add(titleBlock);
    return titleBlock;
  }

  /**
   * Add new Title Block to diagram element
   * 
   * @param diagram element
   * @return the added Title Block
   */
  public static DAnnotation addElementTitleBlock(DDiagram diagram, DDiagramElement diagramElement) {
    DAnnotation titleBLock = DescriptionFactory.eINSTANCE.createDAnnotation();
    titleBLock.setSource(ELEMENT_TITLE_BLOCK);
    titleBLock.getDetails().put(VISIBILITY, TRUE);
    titleBLock.getReferences().add(diagramElement.getTarget());
    diagram.getEAnnotations().add(titleBLock);
    return titleBLock;
  }
  
  /**
   * Add new Title Block to diagram
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock) {
    DAnnotation line = DescriptionFactory.eINSTANCE.createDAnnotation();
    line.setSource(TITLE_BLOCK_LINE);
    diagram.getEAnnotations().add(line);
    titleBlock.getReferences().add(line);
    return line;
  }
  
  /**
   * Add new Title Block to diagram to the given position
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock, int position) {
    DAnnotation line = DescriptionFactory.eINSTANCE.createDAnnotation();
    line.setSource(TITLE_BLOCK_LINE);
    diagram.getEAnnotations().add(line);
    // position + 1 because on the first position in references we have the semantic element
    titleBlock.getReferences().add(position + 1, line);
    return line;
  }
  
  /**
   * Add new Title Block to diagram to the given position
   * and create the columns in the line also
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock, int position, int numCols) {
    DAnnotation line = addTitleBlockLine(diagram, titleBlock, position);
    for(int i = 0; i < numCols; i++) {
    	addTitleBlockCell(diagram, line, "", "");
    }
    return line;
  }

  /**
   * Add new Title Block to diagram
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockCell(DDiagram diagram, DAnnotation line, String name, String content) {
    DAnnotation cell = DescriptionFactory.eINSTANCE.createDAnnotation();
    cell.setSource(TITLE_BLOCK_CELL);
    cell.getDetails().put(NAME, name);
    cell.getDetails().put(CONTENT, content);
    diagram.getEAnnotations().add(cell);
    line.getReferences().add(cell);
    return cell;
  }

  /**
   * Add new Title Block to diagram at the given position
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockCell(DDiagram diagram, DAnnotation line, String name, String content, int position) {
    DAnnotation cell = DescriptionFactory.eINSTANCE.createDAnnotation();
    cell.setSource(TITLE_BLOCK_CELL);
    cell.getDetails().put(NAME, name);
    cell.getDetails().put(CONTENT, content);
    diagram.getEAnnotations().add(cell);
    line.getReferences().add(position, cell);
    return cell;
  }
  
  public static int getNumOfColumns(DAnnotation titleBlock) {
    DAnnotation firstLine = getTitleBlockLines(titleBlock).get(0);
    return getTitleBlockCells(firstLine).size();
  }
  
  public static void setTitleBlockCellContent(DAnnotation line, String name, String content) {
    line.getDetails().put(NAME, name);
    line.getDetails().put(CONTENT, content);
  }
}
