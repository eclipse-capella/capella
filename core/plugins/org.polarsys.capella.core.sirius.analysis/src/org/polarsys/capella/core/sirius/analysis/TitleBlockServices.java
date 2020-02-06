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
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.polarsys.capella.core.data.information.impl.ClassImpl;

public class TitleBlockServices {
  private static TitleBlockServices service = null;

  static Map<String, String> propertiesName = new HashMap<String, String>();
  static Map<String, String> propertiesContent = new HashMap<String, String>();

  public static TitleBlockServices getService() {
    if (service == null) {
      init();
      service = new TitleBlockServices();
    }
    return service;
  }

  public static void init() {
    propertiesName.put("TB_0_0", "Name");
    propertiesName.put("TB_0_1", "Documentation");
    propertiesName.put("TB_1_0", "Contextual elements");
    propertiesContent.put("TB_0_0", "feature:name");
    propertiesContent.put("TB_0_1", "");// "feature:documentation");
    propertiesContent.put("TB_1_0", "");
  }

  public void createTitleBlock(EObject elementView, EObject diagram) {
    DRepresentation representation = null;
    if ((diagram instanceof DRepresentation)) {
      representation = (DRepresentation) diagram;
    }

    if (representation != null) {
      // todo - read from properties
      int numLines = 2;
      int numCols = 2;

      DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
      annotation.setSource("TitleBlock");

      List<DAnnotation> annotationLines = new ArrayList<DAnnotation>();
      for (int i = 0; i < numLines; i++) {
        DAnnotation annotationLine = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotationLine.setSource("TitleBlockLine");

        // addColumnsToLine(annotationLine, representation, numCols); start
        List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
        for (int j = 0; j < numCols; j++) {
          DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
          annotationCol.setSource("TB_" + i + "_" + j);
          annotationCol.getDetails().put("Name:", "Name");
          annotationCol.getDetails().put("Content:", "feature:name");
          if (!elementView.equals(diagram)) {
            annotationCol.getReferences().add(((DDiagramElement) elementView).getTarget());
          }
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
      }
      annotation.getReferences().addAll(annotationLines);
      representation.getEAnnotations().add(annotation);
    }
  }

  public void reconnectTitleBlockEdgeTarget(EObject elementView, DAnnotation source, DAnnotation target) {
    source.getReferences().remove(elementView);
    target.getReferences().add(elementView);
  }

  public void reconnectTitleBlockEdgeSource(EObject elementView, EObject source, EObject target, DEdge edgeView) {
    DNodeContainer targetNode = (DNodeContainer) edgeView.getTargetNode();
    DAnnotation annotation = (DAnnotation) targetNode.getTarget();
    annotation.getReferences().remove(source);
    annotation.getReferences().add(target);
  }

  public void createTitleBlockLine(EObject titleBlock, EObject diagram) {
    if (!isDiagramLevel(titleBlock)) {
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
            addColumnsToLine(annotationLine, representation, numCols, getNumLines((DAnnotation) titleBlock));
          }
        }
      }
    }
  }

  public void createTitleBlockColumn(EObject titleBlock, EObject diagram) {
    if (!isDiagramLevel(titleBlock)) {
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
            annotationCol.setSource("TitleBlockLineCol");
            if (lines.get(i) instanceof DAnnotation) {
              ((DAnnotation) (lines.get(i))).getReferences().add(annotationCol);
              representation.getEAnnotations().add(annotationCol);
            }
          }
        }
      }
    }
  }

  private boolean isDiagramLevel(EObject titleBlock) {
    for (EObject reference : ((DAnnotation) titleBlock).getReferences()) {
      if (!(reference instanceof DAnnotation)) {
        return false;
      }
    }
    return true;
  }

  private void addColumnsToLine(DAnnotation annotationLine, DRepresentation representation, int numCols,
      int lineNumber) {
    List<DAnnotation> annotationCols = new ArrayList<DAnnotation>();
    for (int j = 0; j < numCols; j++) {
      DAnnotation annotationCol = DescriptionFactory.eINSTANCE.createDAnnotation();
      annotationCol.setSource("TB_" + lineNumber + "_" + j);
      annotationCols.add(annotationCol);
      representation.getEAnnotations().add(annotationCol);
    }
    annotationLine.getReferences().addAll(annotationCols);
    representation.getEAnnotations().add(annotationLine);
  }

  public int getNumLines(DAnnotation titleBlock) {
    return titleBlock.getReferences().size();
  }

  public int getNumColumns(DAnnotation titleBlock) {
    EObject obj = titleBlock.getReferences().get(1);
    if (obj instanceof DAnnotation)
      return ((DAnnotation) obj).getReferences().size();
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
      list = representation.getEAnnotations().stream().filter(x -> x.getSource().equals("TitleBlock"))
          .collect(Collectors.toList());
    }
    return list;
  }

  public List<Object> getTitleBlockCellContent(EObject diagram, EObject cell) {
    init();
    List<Object> list = new ArrayList<Object>();
    if ((diagram instanceof DRepresentation)) {
      if (cell instanceof DAnnotation) {
        DAnnotation annotation = (DAnnotation) cell;
        String idCell = annotation.getSource();
        FeatureInterpreter interpreter = new FeatureInterpreter();
        try {
          // String feature = propertiesContent.get(idCell);
          String feature = annotation.getDetails().get("Content:");
          if (feature != null) {
            EObject objToEvaluate = diagram;
            if (!annotation.getReferences().isEmpty()) {
              objToEvaluate = annotation.getReferences().get(0);
            }
            Object obj = interpreter.evaluate(objToEvaluate, feature);
            if (obj != null) {
              if (obj instanceof EObject) {
                list.add(obj);
              } else if (obj instanceof String) {
                DAnnotation annotationContent = DescriptionFactory.eINSTANCE.createDAnnotation();
                annotationContent.setSource("abc");
                annotationContent.getDetails().put("Content:", (String) obj);
                DRepresentation representation = (DRepresentation) diagram;
                representation.getEAnnotations().add(annotationContent);
                list.add(annotationContent);
              }
            } else {
              DRepresentation representation = (DRepresentation) diagram;
              list = representation.getOwnedRepresentationElements().stream()
                  .filter(x -> (x.getTarget() instanceof ClassImpl)).collect(Collectors.toList());
            }
          }
        } catch (EvaluationException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return list;
  }

  public String getTitleBlockCellLabel(EObject diagram, EObject cell) {
    init();
    if (cell instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) cell;
      String idCell = annotation.getSource();
      // String name = propertiesName.get(idCell);
      String name = ((DAnnotation) cell).getDetails().get("Name:");
      if (name != null)
        return name;
    }
    return "";
  }

  public String getCellLabel(EObject obj) {
    // feature:name
    if (obj instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) obj;
      return annotation.getDetails().get("Content:");
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
}
