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
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.information.impl.DataPkgImpl;
import org.polarsys.capella.core.preferences.Activator;
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
  private static final String DEFAULT_TITLEBLOCK_PREFERENCE_STORE = "defaultTitleBlock";
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

  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  public boolean isDiagramTitleBlock(DAnnotation titleBlock) {
    List<EObject> references = titleBlock.getReferences().stream().filter(x -> !(x instanceof DAnnotation))
        .collect(Collectors.toList());
    if (references.isEmpty()) {
      return true;
    }
    return false;
  }

  public void createElementTitleBlock(EObject elementView, EObject diagram) {
    if (!(elementView instanceof DSemanticDiagram) && isUniqueElementTitleBlock(elementView, diagram)) {
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
    if (elementView instanceof DSemanticDiagram && isUniqueDiagramTitleBlock(diagram)) {
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }

      if (representation != null) {
        int numLines = doGetPreferenceStore().getInt(COLUMNS_NUMBER_PREFERENCE_STORE);
        int numCols = doGetPreferenceStore().getDefaultInt(LINES_NUMBER_PREFERENCE_STORE);
        String[] titleBlockContent = doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE)
            .split(ESCAPED_SEPARATOR);

        DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotation.setSource(DIAGRAM_TITLE_BLOCK);
        annotation.getDetails().put(VISIBILITY, TRUE);

        List<DAnnotation> annotationLines = new ArrayList<DAnnotation>();
        int currIndexTB = 0;
        for (int i = 0; i < numLines; i++) {
          DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
          annotationLine.setSource("TitleBlockLine");
          // start
          List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
          for (int j = 0; j < numCols; j++) {
            DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationCol.setSource("TB_" + i + "_" + j);
            annotationCol.getDetails().put(NAME, titleBlockContent[currIndexTB]);
            annotationCol.getDetails().put(CONTENT, titleBlockContent[currIndexTB + 1]);
            currIndexTB += 2;
            annotationCols.add(annotationCol);
            representation.getEAnnotations().add(annotationCol);
          }
          annotationLine.getReferences().addAll(annotationCols);
          representation.getEAnnotations().add(annotationLine);
          // stop

          annotationLines.add(annotationLine);
        }

        if (!elementView.equals(diagram)) {
          annotation.getReferences().add(((DDiagramElement) elementView).getTarget());
          annotation.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
        }
        annotation.getReferences().addAll(annotationLines);
        representation.getEAnnotations().add(annotation);
      }
    }
  }

  public void createTitleBlockLine(EObject titleBlock, EObject diagram) {
    if (!isDiagramTitleBlock((DAnnotation) titleBlock)
        || "True".equals(((DAnnotation) titleBlock).getDetails().get(IS_ELEMENT_TITLE_BLOCK))) {
      titleBlock = getParentTitleBlock((DAnnotation) titleBlock, diagram);
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }
      if (representation != null) {
        DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotationLine.setSource("TitleBlockLine");
        if (titleBlock instanceof DAnnotation) {
          int numCols = getNumColumns((DAnnotation) titleBlock);
          if (numCols > 0) {
            ((DAnnotation) titleBlock).getReferences().add(annotationLine);
            addColumnsToLine(annotationLine, representation, numCols, getNumLines((DAnnotation) titleBlock) - 1);
          }
        }
      }
    }
  }

  private DAnnotation getParentTitleBlock(DAnnotation titleBlock, EObject diagram) {
    DAnnotation parentTitleBlock = DescriptionFactory.eINSTANCE.createDAnnotation();
    if (titleBlock.getSource().startsWith("TB")) {
      List<DDiagramElement> diagramElements = ((DDiagram) diagram).getOwnedDiagramElements();
      for (DDiagramElement diagramElem : diagramElements) {
        if (diagramElem.getTarget() instanceof DAnnotation) {
          List<EObject> references = ((DAnnotation) diagramElem.getTarget()).getReferences();
          for (EObject reference : references) {
            if (reference instanceof DAnnotation) {
              List<EObject> refs = ((DAnnotation) reference).getReferences();
              if (refs.contains(titleBlock)) {
                parentTitleBlock = (DAnnotation) diagramElem.getTarget();
              }
            }
          }
        }
      }
      return parentTitleBlock;
    }
    return titleBlock;
  }

  public void createTitleBlockColumn(EObject titleBlock, EObject diagram) {
    if (!isDiagramTitleBlock((DAnnotation) titleBlock)
        || "True".equals(((DAnnotation) titleBlock).getDetails().get(IS_ELEMENT_TITLE_BLOCK))) {
      titleBlock = getParentTitleBlock((DAnnotation) titleBlock, diagram);
      DRepresentation representation = null;
      if ((diagram instanceof DRepresentation)) {
        representation = (DRepresentation) diagram;
      }
      if (representation != null) {
        if (titleBlock instanceof DAnnotation) {
          int numLines = ((DAnnotation) titleBlock).getReferences().size();
          int numColumns = getNumColumns((DAnnotation) titleBlock);
          List<EObject> lines = ((DAnnotation) titleBlock).getReferences();
          for (int i = 0; i < numLines; i++) {
            DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
            annotationCol.setSource("TB_" + String.valueOf(i - 1) + "_" + String.valueOf(numColumns));
            annotationCol.getDetails().put(NAME, "Name");
            annotationCol.getDetails().put(CONTENT, "feature:name");
            annotationCol.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
            if (lines.get(i) instanceof DAnnotation) {
              ((DAnnotation) (lines.get(i))).getReferences().add(annotationCol);
              representation.getEAnnotations().add(annotationCol);
            }
          }
        }
      }
    }
  }

  private void addColumnsToLine(DAnnotation annotationLine, DRepresentation representation, int numCols,
      int lineNumber) {
    List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
    for (int j = 0; j < numCols; j++) {
      DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
      annotationCol.setSource("TB_" + lineNumber + "_" + j);
      annotationCol.getDetails().put(NAME, "Name");
      annotationCol.getDetails().put(CONTENT, "feature:name");
      annotationCol.getDetails().put(IS_ELEMENT_TITLE_BLOCK, TRUE);
      annotationCols.add(annotationCol);
      representation.getEAnnotations().add(annotationCol);
    }
    annotationLine.getReferences().addAll(annotationCols);
    representation.getEAnnotations().add(annotationLine);
  }

  public int getNumLines(DAnnotation titleBlock) {
    if (isDiagramTitleBlock(titleBlock)) {
      return titleBlock.getReferences().size();
    }
    return titleBlock.getReferences().size() - 1;
  }

  public int getNumColumns(DAnnotation titleBlock) {
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

  public List<DAnnotation> checkIsTitleBlockContainer(Object elementView) {
    List<DAnnotation> list = new ArrayList<DAnnotation>();
    if ((elementView instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) elementView;
      list = representation.getEAnnotations().stream()
          .filter(x -> (x.getSource().equals(DIAGRAM_TITLE_BLOCK) || x.getSource().equals(ELEMENT_TITLE_BLOCK)))
          .collect(Collectors.toList());
      deleteDanglingTitleBlock(list, elementView);
      list = list.stream().filter(x -> Objects.nonNull(x.getDetails().get(VISIBILITY)))
          .filter(x -> x.getDetails().get(VISIBILITY).equals(TRUE)).collect(Collectors.toList());
      List<DAnnotation> diagramTitleBlockList = list.stream().filter(x -> x.getSource().equals(DIAGRAM_TITLE_BLOCK))
          .collect(Collectors.toList());
      if (!diagramTitleBlockList.isEmpty()) {
        DAnnotation diagramTitleBlock = diagramTitleBlockList.get(0);
        updateDiagramTitleBlock(diagramTitleBlock, (EObject) elementView);
      } else {
        if (doGetPreferenceStore().getBoolean(DEFAULT_TITLEBLOCK_PREFERENCE_STORE)) {
          createDiagramTitleBlock((EObject) elementView, (EObject) elementView);
        }
      }
    }
    return list;
  }

  private void updateDiagramTitleBlock(DAnnotation diagramTitleBlock, EObject elementView) {
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
    if (!currentTB.equals(doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE))) {
      clearEAnnotations(elementView, diagramTitleBlock);
      createDiagramTitleBlock(elementView, elementView);
    }

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
    annotationsList.add(element);
    ((DSemanticDiagram) elementView).getEAnnotations().removeAll(annotationsList);
  }

  public void clearLineEAnnotation(DAnnotation titleBlock, DDiagram diagram) {
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

  public void clearColumnEAnnotation(DAnnotation titleBlock, DDiagram diagram) {
    int last = titleBlock.getSource().lastIndexOf("_");
    int length = titleBlock.getSource().length();
    String columnNumber = titleBlock.getSource().substring(last + 1, length);
    List<DAnnotation> eAnnotationsList = diagram.getEAnnotations();
    List<DAnnotation> annotationsListToBeRemoved = new ArrayList<>();
    DAnnotation parentTitleBlock = getParentTitleBlock(titleBlock, diagram);
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
        String feature = ((DAnnotation) cell).getDetails().get(CONTENT);
        if (feature != null) {
          EObject objToEvaluate = diagram;
          List<EObject> modelElements = annotation.getReferences().parallelStream()
              .filter(x -> !(x instanceof DAnnotation)).collect(Collectors.toList());
          if (!modelElements.isEmpty()) {
            objToEvaluate = modelElements.get(0);
          }
          Object obj = Interpreter.evaluate(objToEvaluate, feature);
          if (obj != null) {
            if (obj instanceof String) {
              boolean EANNOTATION_PRESENT = false;
              List<DAnnotation> eAnnotations = ((DRepresentation) diagram).getEAnnotations();
              DAnnotation selectedEAnnotation = null;
              for (DAnnotation eAnnotation : eAnnotations) {
                if (eAnnotation.getSource().equals(((DAnnotation) cell).getSource() + "_CONTENT")) {
                  EANNOTATION_PRESENT = true;
                  selectedEAnnotation = eAnnotation;
                  selectedEAnnotation.getDetails().put(CONTENT, (String) obj);
                  list.add(selectedEAnnotation);
                  break;
                }
              }
              if (!EANNOTATION_PRESENT) {
                DAnnotation annotationContent = DescriptionFactory.eINSTANCE.createDAnnotation();
                annotationContent.setSource(((DAnnotation) cell).getSource() + "_CONTENT");
                annotationContent.getDetails().put(CONTENT, (String) obj);
                ((DRepresentation) diagram).getEAnnotations().add(annotationContent);
                list.add(annotationContent);
              }
            }
          }
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

  private List<DAnnotation> getAvailableTitleBlocksToInsert(final EObject elementView, String type) {
    List<DAnnotation> result = new ArrayList<>();
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

  public boolean isUniqueDiagramTitleBlock(EObject diagram) {
    Collection<DAnnotation> result = new ArrayList<>();
    if ((diagram instanceof DRepresentation)) {
      DRepresentation representation = (DRepresentation) diagram;
      result = representation.getEAnnotations().stream().filter(x -> x.getSource().equals(DIAGRAM_TITLE_BLOCK))
          .filter(x -> x.getReferences().size() == 2).collect(Collectors.toList());
    }
    return (result.size() == 0);
  }

  public boolean isUniqueElementTitleBlock(Object elementView, EObject diagram) {
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

  private static class Interpreter {
    private static Object evaluate(EObject target, String expression) {
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

}
