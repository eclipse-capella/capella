/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.diagram.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Various helpers for {@link DAnnotation} annotations on {@link Title Blocks} elements.
 */
public class TitleBlockHelper {

  /**
   * function that return the container Title Block of a selected cell;
   * the container is the one storing all together the lines and columns 
   * 
   * @param titleBlock:
   *          the selected cell
   * @param diagram:
   * @return int
   */
  public static DAnnotation getParentTitleBlock(DAnnotation titleBlock, EObject diagram) {
    DAnnotation parentTitleBlock = null;
    for (DDiagramElement diagramElem : ((DDiagram) diagram).getOwnedDiagramElements()) {
      if (diagramElem.getTarget() instanceof DAnnotation) {
        List<EObject> references = ((DAnnotation) diagramElem.getTarget()).getReferences();
        for (EObject reference : references) {
          if (reference instanceof DAnnotation) {
            List<EObject> refs = ((DAnnotation) reference).getReferences();
            if (refs.contains(titleBlock)) {
              return (DAnnotation) diagramElem.getTarget();
            }
          }
        }
      }
    }
    return parentTitleBlock;
  }
  
  /**
   * function that return the index of the column of a selected cell in a Title Block
   * 
   * @param titleBlock:
   *          the selected cell
   * @param titleBlockContainer:
   *          the container Title Block (which stores all  the lines + cols)
   * @return int
   */
  public static int getColumnIndexOfSelectedCell(DAnnotation titleBlock, DAnnotation titleBlockContainer) {
    for (Object element : titleBlockContainer.getReferences()) {
      if (element instanceof DAnnotation) {
        for (int index = 0; index < ((DAnnotation) element).getReferences().size(); index++) {
          if (((DAnnotation) element).getReferences().get(index).equals(titleBlock))
            return index;
        }
      }
    }

    return 0;
  }
  
  /**
   * function that return the index of the line of a selected cell in a Title Block
   * 
   * @param titleBlock:
   *          the selected cell
   * @param titleBlockContainer:
   *          the container Title Block (which stores all  the lines + cols)
   * @return
   */
  public static int getLineIndexOfSelectedCell(DAnnotation titleBlock, DAnnotation titleBlockContainer) {
    int linesNbr = titleBlockContainer.getReferences().size();
    for (int i = 0; i < linesNbr; i++) {
      EObject element = titleBlockContainer.getReferences().get(i);
      if (element instanceof DAnnotation) {
        for (EObject cell : ((DAnnotation) element).getReferences()) {
          if (cell.equals(titleBlock))
            return i;
        }
      }
    }

    return linesNbr - 1;
  }
  

  /**
   * get the number of lines of an Element Title Block
   * 
   * @param titleBlock
   * @return int
   */
  public static int getNumLinesOfElementTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getReferences().size() - 1;
  }

  /**
   * get the number of columns of an Element Title Block
   * 
   * @param titleBlock
   * @return int
   */
  public static int getNumColumnsOfElementTitleBlock(DAnnotation titleBlock) {
    return getFirstLineOfElementTitleBlock(titleBlock).getReferences().size();
  }

  /**
   * get the first line of a Title Block
   * 
   * @param titleBlock
   * @return DAnnotation
   */
  public static DAnnotation getFirstLineOfElementTitleBlock(DAnnotation titleBlock) {
    for (EObject element : titleBlock.getReferences()) {
      if (element instanceof DAnnotation)
        return (DAnnotation) element;
    }
    return null;
  }
  
  

  /**
   * it creates a cell where with the settings from parameters
   * @return DAnnotation
   */
  public static DAnnotation createTitleBlockCell(String source, String nameKey, String contentKey,
      String name, String content) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(source);
    annotation.getDetails().put(nameKey, name);
    annotation.getDetails().put(contentKey, content);
    return annotation;
  }
}
