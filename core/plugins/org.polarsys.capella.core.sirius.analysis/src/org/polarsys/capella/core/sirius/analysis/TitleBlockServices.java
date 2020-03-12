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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.information.impl.DataPkgImpl;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

public class TitleBlockServices {
  private static TitleBlockServices service = null;

  static Map<String, String> propertiesName = new HashMap<String, String>();
  static Map<String, String> propertiesContent = new HashMap<String, String>();

  private static final String DIAGRAM_TITLE_BLOCK = "DiagramTitleBlock";
  private static final String ELEMENT_TITLE_BLOCK = "ElementTitleBlock";
  private static final String NAME = "Name:";
  private static final String CONTENT = "Content:";
  private static final String VISIBILITY = "Visibility";
  private static final String IS_ELEMENT_TITLE_BLOCK = "Is Element Title Block";
  private static final String TRUE = "True";
  private static final String FALSE = "False";
  private static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock";
  private static final String LINES_NUMBER_PREFERENCE_STORE = "linesNumberTitleBlock";
  private static final String TABEL_CONTENT_PREFERENCE_STORE = "tableTitleBlock";
  private static final String ESCAPED_SEPARATOR = "\\+";

  public static TitleBlockServices getService() {
    if (service == null) {
      service = new TitleBlockServices();
    }
    return service;
  }

  public boolean isDiagram(EObject container) {
    return (container instanceof DataPkgImpl);
  }

  public boolean isDiagramTitleBlock(DAnnotation titleBlock) {
    List<EObject> references = titleBlock.getReferences().stream().filter(x -> !(x instanceof DAnnotation))
        .collect(Collectors.toList());
    if (references.isEmpty()) {
      return true;
    }
    return false;
  }

  public void createElementTitleBlock(EObject elementView) {
    EObject diagram = elementView.eContainer();
    if (!(elementView instanceof DSemanticDiagram) && hasElementTitleBlock(elementView)) {
      if (elementView instanceof DNodeList) {
        if (!(((DNodeList) elementView).getTarget() instanceof DAnnotation)) {

          DRepresentation representation = null;
          if ((diagram instanceof DRepresentation)) {
            representation = (DRepresentation) diagram;
          }

          if (representation != null) {
            DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotation.setSource(ELEMENT_TITLE_BLOCK);
            annotation.getDetails().put(VISIBILITY, TRUE);

            DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationLine.setSource("TitleBlockLine");
            DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationCol.setSource("TB_0_0");
            annotationCol.getDetails().put(NAME, "Name");
            annotationCol.getDetails().put(CONTENT, "feature:name");
            annotationCol.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
            representation.getEAnnotations().add(annotationCol);
            annotationLine.getReferences().add(annotationCol);
            representation.getEAnnotations().add(annotationLine);

            if (!elementView.equals(diagram)) {
              annotation.getReferences().add(((DDiagramElement) elementView).getTarget());
              annotation.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
            }
            annotation.getReferences().add(annotationLine);
            representation.getEAnnotations().add(annotation);
          }
        }
      }
    }
  }

  public void createDiagramTitleBlock(EObject elementView, EObject diagram) {
    if (elementView instanceof DSemanticDiagram && hasDiagramTitleBlock(diagram)) {
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }

      if (representation != null) {
        // todo - read from properties
        int numLines = 2;
        int numCols = 2;

        DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotation.setSource(DIAGRAM_TITLE_BLOCK);
        annotation.getDetails().put(VISIBILITY, TRUE);

        List<DAnnotation> annotationLines = new ArrayList<DAnnotation>();
        for (int i = 0; i < numLines; i++) {
          DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
          annotationLine.setSource("TitleBlockLine");

          // addColumnsToLine(annotationLine, representation, numCols); start
          List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
          for (int j = 0; j < numCols; j++) {
            DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationCol.setSource("TB_" + i + "_" + j);
            annotationCol.getDetails().put(NAME, "Name");
            annotationCol.getDetails().put(CONTENT, "feature:name");
            annotationCols.add(annotationCol);
            representation.getEAnnotations().add(annotationCol);
          }
          annotationLine.getReferences().addAll(annotationCols);
          representation.getEAnnotations().add(annotationLine);
          // stop

          annotationLines.add(annotationLine);
        }

        annotation.getReferences().addAll(annotationLines);
        representation.getEAnnotations().add(annotation);
      }
    }
  }

  public void createTitleBlockLine(EObject titleBlock, EObject diagram) {
    if (!isDiagramTitleBlock((DAnnotation) titleBlock)
        || "True".equals(((DAnnotation) titleBlock).getDetails().get(IS_ELEMENT_TITLE_BLOCK))) {
      int lineNumber = getLineNumber((DAnnotation) titleBlock);
      titleBlock = DAnnotationHelper.getParentTitleBlock((DAnnotation) titleBlock, diagram);
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }
      if (representation != null) {
        DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotationLine.setSource("TitleBlockLine");
        if (titleBlock instanceof DAnnotation) {
          int numCols = getTitleBlockNumColumns((DAnnotation) titleBlock);
          if (numCols > 0) {
            ((DAnnotation) titleBlock).getReferences().add(lineNumber + 2, annotationLine);
            addColumnsToLine(annotationLine, representation, numCols, lineNumber + 1);
          }
        }
      }
      renumberCells((DAnnotation) titleBlock);
    }
  }

  private void addColumnsToLine(DAnnotation annotationLine, DRepresentation representation, int numCols,
      int lineNumber) {
    List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
    for (int j = 0; j < numCols; j++) {
      DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
      annotationCol.setSource("TB_" + lineNumber + "_" + j);
      annotationCol.getDetails().put(NAME, "");
      annotationCol.getDetails().put(CONTENT, "");
      annotationCol.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
      annotationCols.add(annotationCol);
      representation.getEAnnotations().add(annotationCol);
    }
    annotationLine.getReferences().addAll(annotationCols);
    representation.getEAnnotations().add(annotationLine);
  }

  public void createTitleBlockColumn(EObject titleBlock, EObject diagram) {
    if (!isDiagramTitleBlock((DAnnotation) titleBlock)
        || "True".equals(((DAnnotation) titleBlock).getDetails().get(IS_ELEMENT_TITLE_BLOCK))) {
      int columnNumber = getColumnNumber((DAnnotation) titleBlock);
      titleBlock = DAnnotationHelper.getParentTitleBlock((DAnnotation) titleBlock, diagram);
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }
      if (representation != null) {
        if (titleBlock instanceof DAnnotation) {
          int numLines = ((DAnnotation) titleBlock).getReferences().size();
          List<EObject> lines = ((DAnnotation) titleBlock).getReferences();
          for (int i = 0; i < numLines; i++) {
            DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationCol.setSource("TB_" + String.valueOf(i - 1) + "_" + String.valueOf(columnNumber));
            annotationCol.getDetails().put(NAME, "");
            annotationCol.getDetails().put(CONTENT, "");
            annotationCol.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
            if (lines.get(i) instanceof DAnnotation) {
              ((DAnnotation) (lines.get(i))).getReferences().add(columnNumber + 1, annotationCol);
              representation.getEAnnotations().add(annotationCol);
            }
          }
        }
      }
      renumberCells((DAnnotation) titleBlock);
    }
  }

  // after a line or a column is added, we need to renumber cells
  private void renumberCells(DAnnotation titleBlock) {
    int lineNumber = -1;
    for (EObject reference : titleBlock.getReferences()) {
      if (reference instanceof DAnnotation) {
        int columnNumber = 0;
        for (EObject ref : ((DAnnotation) reference).getReferences()) {
          String newSource = "TB_" + lineNumber + "_" + columnNumber;
          ((DAnnotation) ref).setSource(newSource);
          columnNumber++;
        }
      }
      lineNumber++;
    }
  }

  private int getLineNumber(DAnnotation titleBlock) {
    String source = titleBlock.getSource();
    int first = source.indexOf("_");
    int second = source.indexOf("_", first + 1);
    String lineNumber = source.substring(first + 1, second);
    return Integer.valueOf(lineNumber);
  }

  private int getColumnNumber(DAnnotation titleBlock) {
    String source = titleBlock.getSource();
    int last = source.lastIndexOf("_");
    int length = source.length();
    String columnNumber = source.substring(last + 1, length);
    return Integer.valueOf(columnNumber);
  }

  public int getTitleBlockNumLines(DAnnotation titleBlock) {
    if (isDiagramTitleBlock(titleBlock)) {
      return titleBlock.getReferences().size();
    }
    return titleBlock.getReferences().size() - 1;
  }

  public int getTitleBlockNumColumns(DAnnotation titleBlock) {
    if (titleBlock.getReferences().size() > 1) {
      EObject obj = titleBlock.getReferences().get(1);
      if (obj instanceof DAnnotation)
        return ((DAnnotation) obj).getReferences().size();
    }
    return 0;
  }

  public Collection<EObject> computeTitleBlockElements(EObject elem, EObject diagram) {
    Collection<EObject> result = new ArrayList<>();
    if ((diagram instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) diagram;
      result = representation.getEAnnotations().stream().filter(x -> x.getReferences().contains(elem))
          .collect(Collectors.toList());
    }
    return result;
  }

  public boolean isDiagramTitleBlockNotVisible(Object elementView) {
    return isTitleBlockNotVisible(elementView, DIAGRAM_TITLE_BLOCK);
  }

  public boolean isElementTitleBlockNotVisible(Object elementView) {
    return isTitleBlockNotVisible(elementView, ELEMENT_TITLE_BLOCK);
  }

  public boolean isTitleBlockNotVisible(Object elementView, String type) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    if (elementView instanceof DNodeList) {
      elementView = ((DNodeList) elementView).eContainer();
    }
    if ((elementView instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) elementView;
      list = representation.getEAnnotations().stream().filter(x -> (x.getSource().equals(type)))
          .collect(Collectors.toList());
      if (!list.isEmpty()) {
        list = list.stream().filter(x -> Objects.nonNull(x.getDetails().get(VISIBILITY)))
            .filter(x -> x.getDetails().get(VISIBILITY).equals(FALSE)).collect(Collectors.toList());
      } else {
        return false;
      }
    }
    return (!list.isEmpty());
  }

  public List<DAnnotation> checkIsTitleBlock(Object elementView) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    if ((elementView instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) elementView;
      list = representation.getEAnnotations().stream()
          .filter(x -> (x.getSource().equals(DIAGRAM_TITLE_BLOCK) || x.getSource().equals(ELEMENT_TITLE_BLOCK)))
          .collect(Collectors.toList());
      deleteDanglingTitleBlock(list, elementView);
      list = list.stream().filter(x -> Objects.nonNull(x.getDetails().get(VISIBILITY)))
          .filter(x -> x.getDetails().get(VISIBILITY).equals(TRUE)).collect(Collectors.toList());
    }
    return list;
  }

  public boolean isTitleBlockContainer(EObject element) {
    if (element instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) element;
      return annotation.getSource().equals(DIAGRAM_TITLE_BLOCK) || annotation.getSource().equals(ELEMENT_TITLE_BLOCK);
    }
    return false;
  }

  private void deleteDanglingTitleBlock(List<DAnnotation> list, Object elementView) {
    List<DAnnotation> deleteList = new ArrayList<DAnnotation>();
    for (DAnnotation annotation : list) {
      boolean hasExternalElementReference = false;
      for (EObject element : annotation.getReferences()) {
        if (!(element instanceof DAnnotation)) {
          hasExternalElementReference = true;
          boolean elementPresentInDiagram = false;
          List<DDiagramElement> diagramElementsList = ((DSemanticDiagram) elementView).getOwnedDiagramElements();
          for (DDiagramElement diagramElement : diagramElementsList) {
            if (!(diagramElement instanceof DEdge) && diagramElement.getTarget().equals(element)) {
              elementPresentInDiagram = true;
            }
          }
          if (!(elementPresentInDiagram)) {
            annotation.getDetails().put(VISIBILITY, FALSE);
          }
        }
      }
      if (!(hasExternalElementReference)) {
        deleteList.add(annotation);
      }
    }
    deleteList = deleteList.stream().filter(x -> Objects.nonNull(x.getDetails().get(IS_ELEMENT_TITLE_BLOCK)))
        .filter(x -> x.getDetails().get(IS_ELEMENT_TITLE_BLOCK).equals(TRUE)).collect(Collectors.toList());
    CapellaServices.getService().removeElements(deleteList);
    for (DAnnotation annotation : deleteList) {
      clearEAnnotations(elementView, annotation);
    }
  }

  public void clearEAnnotations(Object elementView, DAnnotation element) {
    List<DAnnotation> annotationsList = new ArrayList<>();
    for (EObject titleBlockLine : element.getReferences()) {
      if (titleBlockLine instanceof DAnnotation) {
        annotationsList.add((DAnnotation) titleBlockLine); // title block lines
        if (!((DAnnotation) titleBlockLine).getReferences().isEmpty()) {
          for (EObject titleBlockCell : ((DAnnotation) titleBlockLine).getReferences()) {
            if (titleBlockCell instanceof DAnnotation) {
              annotationsList.add((DAnnotation) titleBlockCell);
            }
          }
        }
      }
    }
    ((DSemanticDiagram) elementView).getEAnnotations().removeAll(annotationsList);
  }

  public void clearCell(Object element) {
    ((DAnnotation) element).getDetails().put(NAME, "");
    ((DAnnotation) element).getDetails().put(CONTENT, "");
  }

  public void removeLine(DAnnotation titleBlock, DDiagram diagram) {
    List<DAnnotation> eAnnotationsList = diagram.getEAnnotations();
    List<DAnnotation> annotationsListToBeRemoved = new ArrayList<>();
    for (DAnnotation eAnnotation : eAnnotationsList) {
      if (eAnnotation.getReferences().contains(titleBlock)) {
        annotationsListToBeRemoved.add(eAnnotation);
        for (EObject reference : eAnnotation.getReferences()) {
          if (reference instanceof DAnnotation) {
            annotationsListToBeRemoved.add((DAnnotation) reference);
          }
        }
      }
    }
    eAnnotationsList.removeAll(annotationsListToBeRemoved);
  }

  public void removeColumn(DAnnotation titleBlock, DDiagram diagram) {
    int last = titleBlock.getSource().lastIndexOf("_");
    int length = titleBlock.getSource().length();
    String columnNumber = titleBlock.getSource().substring(last + 1, length);
    List<DAnnotation> eAnnotationsList = diagram.getEAnnotations();
    List<DAnnotation> annotationsListToBeRemoved = new ArrayList<>();
    DAnnotation parentTitleBlock = DAnnotationHelper.getParentTitleBlock(titleBlock, diagram);
    for (EObject reference : parentTitleBlock.getReferences()) {
      if (reference instanceof DAnnotation) {
        List<EObject> lineReferences = ((DAnnotation) reference).getReferences();
        for (EObject lineRef : lineReferences) {
          if (lineRef instanceof DAnnotation) {
            last = titleBlock.getSource().lastIndexOf("_");
            length = titleBlock.getSource().length();
            if (((DAnnotation) lineRef).getSource().substring(last + 1, length).equals(columnNumber)) {
              annotationsListToBeRemoved.add((DAnnotation) lineRef);
            }
          }
        }
      }
    }
    eAnnotationsList.removeAll(annotationsListToBeRemoved);
  }

  public List<Object> getTitleBlockCellContent(EObject diagram, EObject cell, EObject containerView) {
    List<Object> list = new ArrayList<Object>();
    if ((diagram instanceof DRepresentation)) {
      if (cell instanceof DAnnotation) {
        DAnnotation annotation = (DAnnotation) ((DNodeContainer) (containerView.eContainer().eContainer())).getTarget();
        FeatureInterpreter interpreter = new FeatureInterpreter();
        try {
          String feature = ((DAnnotation) cell).getDetails().get(CONTENT);
          if (feature != null) {
            EObject objToEvaluate = diagram;
            List<EObject> modelElements = annotation.getReferences().parallelStream()
                .filter(x -> !(x instanceof DAnnotation)).collect(Collectors.toList());
            if (!modelElements.isEmpty()) {
              objToEvaluate = modelElements.get(0);
            }
            Object obj = interpreter.evaluate(objToEvaluate, feature);
            if (obj != null) {
              if (obj instanceof String) {
                boolean eAnnotationPresent = false;
                List<DAnnotation> eAnnotations = ((DRepresentation) diagram).getEAnnotations();
                DAnnotation selectedEAnnotation = null;
                for (DAnnotation eAnnotation : eAnnotations) {
                  if (eAnnotation.getSource().equals(((DAnnotation) cell).getSource() + "_CONTENT")) {
                    eAnnotationPresent = true;
                    selectedEAnnotation = eAnnotation;
                    selectedEAnnotation.getDetails().put(CONTENT, (String) obj);
                    list.add(selectedEAnnotation);
                    break;
                  }
                }
                if (!eAnnotationPresent) {
                  DAnnotation annotationContent = DescriptionFactory.eINSTANCE.createDAnnotation();
                  annotationContent.setSource(((DAnnotation) cell).getSource() + "_CONTENT");
                  annotationContent.getDetails().put(CONTENT, (String) obj);
                  ((DRepresentation) diagram).getEAnnotations().add(annotationContent);
                  list.add(annotationContent);
                } else {
                  if (obj instanceof Collection) {
                    list.addAll((Collection<? extends Object>) obj);
                  } else if (obj instanceof EObject)
                    list.add(obj);
                }
              }
            }
          }
        } catch (EvaluationException e) {
          e.printStackTrace();
        }
      }
    }
    return list;
  }

  public String getTitleBlockCellLabel(EObject diagram, EObject cell) {
    if (cell instanceof DAnnotation) {
      String name = ((DAnnotation) cell).getDetails().get(NAME);
      if (name != null)
        return name;
    }
    return "";
  }

  public String getCellLabel(EObject obj) {
    if (obj instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) obj;
      return annotation.getDetails().get(CONTENT);
    }
    return "";
  }

  public boolean isAnnotation(EObject obj) {
    return obj instanceof DAnnotation;
  }

  public boolean isTitleBlockAllowed(EObject element, EObject containerView) {
    if (containerView instanceof DRepresentation) {
      return true;
    }
    return false;
  }

  public List<DAnnotation> getAvailableDiagramTitleBlocksToInsert(final EObject elementView) {
    return getAvailableTitleBlocksToInsert(elementView, DIAGRAM_TITLE_BLOCK);
  }

  public List<DAnnotation> getAvailableElementTitleBlocksToInsert(final EObject elementView) {
    return getAvailableTitleBlocksToInsert(elementView, ELEMENT_TITLE_BLOCK);
  }

  private List<DAnnotation> getAvailableTitleBlocksToInsert(EObject elementView, String type) {
    List<DAnnotation> result = new ArrayList<>();
    if (elementView instanceof DNodeList) {
      elementView = ((DNodeList) elementView).eContainer();
    }
    EList<DAnnotation> eList = ((DDiagram) elementView).getEAnnotations();
    for (DAnnotation elem : eList) {
      if (elem.getSource().equals(type)) {
        result.add(elem);
      }
    }
    return result;
  }

  public List<DAnnotation> getInitialSelectionOfShowHideDiagramTitleBlocks(final EObject elementView) {
    return getInitialSelectionOfShowHideTitleBlocks(elementView, DIAGRAM_TITLE_BLOCK);
  }

  public List<DAnnotation> getInitialSelectionOfShowHideElementTitleBlocks(final EObject elementView) {
    return getInitialSelectionOfShowHideTitleBlocks(elementView, ELEMENT_TITLE_BLOCK);
  }

  private List<DAnnotation> getInitialSelectionOfShowHideTitleBlocks(final EObject elementView, String type) {
    List<DAnnotation> result = new ArrayList<>(1);
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(elementView);
    if (elementView.equals(diagram)) {
      EList<DAnnotation> diagramElements = diagram.getEAnnotations();
      for (DAnnotation dDiagramElement : diagramElements) {
        if (dDiagramElement.getSource().equals(type)) {
          if (null != dDiagramElement.getDetails().get(VISIBILITY)) {
            if (dDiagramElement.getDetails().get(VISIBILITY).equals(TRUE)) {
              result.add(dDiagramElement);
            }
          }
        }
      }
    }
    return result;
  }

  public EObject showHideDiagramTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, DIAGRAM_TITLE_BLOCK);
  }

  public EObject showHideElementTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram) {
    return showHideTitleBlocks(context, selectedTitleBlocks, diagram, ELEMENT_TITLE_BLOCK);
  }

  private EObject showHideTitleBlocks(EObject context, List<DAnnotation> selectedTitleBlocks, DDiagram diagram,
      String type) {
    if (context instanceof DNodeList) {
      context = ((DNodeList) context).eContainer();
    }
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
    if (context instanceof DNodeList) {
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

  public boolean hasDiagramTitleBlock(EObject diagram) {
    Collection<DAnnotation> result = new ArrayList<>();
    if ((diagram instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) diagram;
      result = representation.getEAnnotations().stream().filter(x -> x.getSource().equals(DIAGRAM_TITLE_BLOCK))
          .filter(x -> x.getReferences().size() == 2).collect(Collectors.toList());
    }
    return (result.size() == 0);
  }

  public boolean hasElementTitleBlock(EObject elementView) {
    EObject diagram = elementView.eContainer();
    Collection<DAnnotation> result = new ArrayList<>();
    if ((diagram instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) diagram;
      result = representation.getEAnnotations().stream().filter(x -> x.getSource().equals(ELEMENT_TITLE_BLOCK))
          .collect(Collectors.toList());
      for (DAnnotation annotation : result) {
        for (EObject reference : annotation.getReferences()) {
          if (reference == ((DNodeList) elementView).getTarget()) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static Image getImage(Object object) {
    DAnnotation annotation = (DAnnotation) object;
    String imagePath = "/icons/full/obj16/TitleBlock_16.gif";
    URL url = FileLocator.find(SiriusViewActivator.getInstance().getBundle(), new Path(imagePath), null);
    return ImageDescriptor.createFromURL(url).createImage();
  }

}
