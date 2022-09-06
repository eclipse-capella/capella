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
package org.polarsys.capella.core.diagram.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.tools.internal.interpreter.DefaultInterpreterProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.viewpoint.ViewpointHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * Various helpers for {@link DAnnotation} annotations on {@link Title Blocks} elements.
 */
public class TitleBlockHelper {
  public static final String TITLE_BLOCK_LINE = "TitleBlockLine";

  public static final String TITLE_BLOCK_CELL = "TitleBlockCell";

  public static final String ELEMENT_TITLE_BLOCK = "ElementTitleBlock";

  public static final String TITLE_BLOCK_CONTENT = "TitleBlockContent";

  public static final String TRUE = "True";

  public static final String NAME = "Name:";

  public static final String CONTENT = "Content:";

  public static final String DIAGRAM_TITLE_BLOCK = "DiagramTitleBlock";

  public static final String CAPELLA_PREFIX = "capella:";

  public static final String AQL_PREFIX = "aql:";

  public static final String FEATURE_PREFIX = "feature:";

  public static final String TITLE_BLOCK_MAPPING_PREFIX = "DT_TitleBlock";

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
   * @param titleBlock
   * @return true if the annotation is a Diagram Title Block type annotation
   */
  public static boolean isContentTitleBlock(DAnnotation titleBlock) {
    return titleBlock.getSource() != null && titleBlock.getSource().equals(TITLE_BLOCK_CONTENT);
  }

  /**
   * @param diagramElement
   * @return true if the diagramElement is a Title Block Cell
   */
  public static boolean isTitleBlockCell(DDiagramElement diagramElement) {
    return diagramElement.getTarget() instanceof DAnnotation
        && isTitleBlockCell((DAnnotation) diagramElement.getTarget());
  }

  public static boolean isTitleBlockMapping(RepresentationElementMapping mapping) {
    return mapping != null && isTitleBlockMapping(mapping.getName());
  }

  public static boolean isTitleBlockMapping(String mappingName) {
    return mappingName != null && mappingName.contains(TITLE_BLOCK_MAPPING_PREFIX);
  }

  public static List<DAnnotation> getElementTitleBlocks(DDiagram diagram) {
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    if (descriptor != null && descriptor.getEAnnotations() != null) {
      return descriptor.getEAnnotations().stream().filter(a -> a.getSource().equals(ELEMENT_TITLE_BLOCK))
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  public static List<DAnnotation> getDiagramTitleBlocks(DDiagram diagram) {
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    if (descriptor != null && descriptor.getEAnnotations() != null) {
      return descriptor.getEAnnotations().stream().filter(a -> a.getSource().equals(DIAGRAM_TITLE_BLOCK))
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
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
   * @return the parent title block
   */
  public static DAnnotation getParentTitleBlock(DAnnotation titleBlockCell) {
    if (isTitleBlockCell(titleBlockCell)) {
      Session session = SessionManager.INSTANCE.getSession(titleBlockCell);
      if (session != null) {
        Collection<Setting> referencer = session.getSemanticCrossReferencer().getInverseReferences(titleBlockCell,
            DescriptionPackage.Literals.DANNOTATION__REFERENCES, false);
        if (!referencer.isEmpty()) {
          DAnnotation line = (DAnnotation) referencer.iterator().next().getEObject();
          referencer = session.getSemanticCrossReferencer().getInverseReferences(line,
              DescriptionPackage.Literals.DANNOTATION__REFERENCES, false);
          if (!referencer.isEmpty()) {
            return (DAnnotation) referencer.iterator().next().getEObject();
          }
        }
      }
    }
    return null;
  }

  /**
   * function that return the line containing the given cell
   * 
   * @param titleBlockCell:
   *          the selected cell
   * @return the parent title block
   */
  public static DAnnotation getParentAnnotation(DAnnotation annotation) {
    Session session = SessionManager.INSTANCE.getSession(annotation);
    if (session != null) {
      Collection<Setting> referencer = session.getSemanticCrossReferencer().getInverseReferences(annotation,
          DescriptionPackage.Literals.DANNOTATION__REFERENCES, false);
      if (!referencer.isEmpty()) {
        return (DAnnotation) referencer.iterator().next().getEObject();
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
    if (titleBlock != null) {
      List<EObject> modelElements = titleBlock.getReferences().stream().filter(x -> !(x instanceof DAnnotation))
          .collect(Collectors.toList());
      if (!modelElements.isEmpty())
        return modelElements.get(0);
    }
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
   *          the container Title Block (which stores all the lines + cols)
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
   * Add new Title Block to diagram
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addDiagramTitleBlock(DDiagram diagram) {
    return DAnnotationHelper.createAnnotation(DIAGRAM_TITLE_BLOCK,
        RepresentationHelper.getRepresentationDescriptor(diagram));
  }

  /**
   * Add new Title Block to diagram element
   * 
   * @param diagram
   *          element
   * @return the added Title Block
   */
  public static DAnnotation addElementTitleBlock(DDiagram diagram, DDiagramElement diagramElement) {
    DAnnotation annotation = DAnnotationHelper.createAnnotation(ELEMENT_TITLE_BLOCK,
        RepresentationHelper.getRepresentationDescriptor(diagram));
    annotation.getReferences().add(diagramElement.getTarget());
    return annotation;
  }

  /**
   * Add new Title Block to diagram
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock) {
    DAnnotation line = DAnnotationHelper.createAnnotation(TITLE_BLOCK_LINE,
        RepresentationHelper.getRepresentationDescriptor(diagram));
    titleBlock.getReferences().add(line);
    return line;
  }

  /**
   * Add new line to an existing Title Block in diagram to the given position
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock, int position) {
    DAnnotation line = DAnnotationHelper.createAnnotation(TITLE_BLOCK_LINE,
        RepresentationHelper.getRepresentationDescriptor(diagram));
    if (isDiagramTitleBlock(titleBlock)) {
      titleBlock.getReferences().add(position, line);
    } else {
      // position + 1 because on the first position in references we have the semantic element
      titleBlock.getReferences().add(position + 1, line);
    }
    return line;
  }

  /**
   * Add new Title Block to diagram to the given position and create the columns in the line also
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockLine(DDiagram diagram, DAnnotation titleBlock, int position, int numCols) {
    DAnnotation line = addTitleBlockLine(diagram, titleBlock, position);
    for (int i = 0; i < numCols; i++) {
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
    DAnnotation cell = DAnnotationHelper.createAnnotation(TITLE_BLOCK_CELL,
        RepresentationHelper.getRepresentationDescriptor(diagram));
    cell.getDetails().put(NAME, name);
    cell.getDetails().put(CONTENT, content);
    line.getReferences().add(cell);
    return cell;
  }

  /**
   * Add new Title Block to diagram at the given position
   * 
   * @param diagram
   * @return the added Title Block
   */
  public static DAnnotation addTitleBlockCell(DDiagram diagram, DAnnotation line, String name, String content,
      int position) {
    DAnnotation cell = DAnnotationHelper.createAnnotation(TITLE_BLOCK_CELL,
        RepresentationHelper.getRepresentationDescriptor(diagram));
    cell.getDetails().put(NAME, name);
    cell.getDetails().put(CONTENT, content);
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

  public static void getServicesProposals(Text textField, EObject target) {
    KeyStroke keyStroke;
    EObject resolvedTarget = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(target);
    try {
      keyStroke = KeyStroke.getInstance("Ctrl+Space");
      IContentProposalProvider provider = new IContentProposalProvider() {

        @Override
        public IContentProposal[] getProposals(String contents, int position) {
          IInterpreter vpInterpreter = CompoundInterpreter.INSTANCE.getInterpreterForExpression(contents);

          List<IContentProposal> proposalsList = new ArrayList<IContentProposal>();
          ContentInstanceContext contentContext = new ContentInstanceContext(resolvedTarget, contents, position);
          if (contents.contains(CAPELLA_PREFIX)) {

            // get all the categories for target and match the command name from category with the command in TitleBlock
            Set<ICategory> categories = CategoryRegistry.getInstance().gatherCategories(resolvedTarget);

            for (ICategory category : categories) {
              String proposalContent = category.getSymbolicName();
              if (proposalContent.toLowerCase().startsWith(contents.replaceFirst(CAPELLA_PREFIX, "").toLowerCase())) {
                proposalsList
                    .add(new org.eclipse.jface.fieldassist.ContentProposal(proposalContent, proposalContent, null));
              }
            }
          } else {
            List<ContentProposal> computedProposals;

            if (contents == null || contents.length() == 0 || !contents.contains(":")) {
              computedProposals = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
              computedProposals.removeIf((ContentProposal p) -> !p.getProposal().equals(AQL_PREFIX)
                  && !p.getProposal().equals(FEATURE_PREFIX));
              computedProposals.add(new ContentProposal(CAPELLA_PREFIX, CAPELLA_PREFIX, null));
              if (contents != null && contents.length() > 0) {
                computedProposals
                    .removeIf((ContentProposal p) -> !p.getProposal().toLowerCase().startsWith(contents.toLowerCase()));
              }
            } else {
              if (vpInterpreter instanceof IProposalProvider) {
                computedProposals = ((IProposalProvider) vpInterpreter).getProposals(vpInterpreter, contentContext);
              } else {
                computedProposals = new ArrayList<>();
                final List<IProposalProvider> proposalProviders = ProposalProviderRegistry
                    .getProvidersFor(vpInterpreter);
                for (IProposalProvider provider : proposalProviders) {
                  computedProposals.addAll(provider.getProposals(vpInterpreter, contentContext));
                }
              }
            }
            for (ContentProposal proposals : computedProposals) {
              org.eclipse.jface.fieldassist.ContentProposal contentProposals = new org.eclipse.jface.fieldassist.ContentProposal(
                  proposals.getProposal(), proposals.getDisplay(), proposals.getInformation(),
                  proposals.getCursorPosition());
              proposalsList.add(contentProposals);
            }
          }

          IContentProposal[] proposals = new IContentProposal[proposalsList.size()];
          proposals = proposalsList.toArray(proposals);
          return proposals;
        }
      };
      ContentProposalAdapter adapter = new ContentProposalAdapter(textField, new TextContentAdapter(), provider,
          keyStroke, null);
      adapter.addContentProposalListener(new IContentProposalListener() {
        @Override
        public void proposalAccepted(IContentProposal proposal) {
          String proposalContent = proposal.getContent();
          String textBeforeProposal = textField.getText(0, textField.getText().length() - proposalContent.length() - 1);

          StringBuffer patternToMatch = new StringBuffer();
          int currentIndex = textBeforeProposal.length() - 1;
          String matchedString = "";

          while (currentIndex >= 0 && patternToMatch.length() <= textBeforeProposal.length()) {
            patternToMatch.reverse();
            patternToMatch.append(textBeforeProposal.charAt(currentIndex));
            patternToMatch.reverse();

            if (proposalContent.toLowerCase().startsWith(patternToMatch.toString().toLowerCase())) {
              matchedString = patternToMatch.toString();
            }
            currentIndex--;
          }

          textField.setText(
              textBeforeProposal.substring(0, textBeforeProposal.length() - matchedString.length()) + proposalContent);
          adapter.getControlContentAdapter().setCursorPosition(textField, textField.getText().length());
        }
      });

      adapter.getControlContentAdapter().setCursorPosition(textField, 5);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static List<DAnnotation> getAllAnnotationsForTitleBlock(DAnnotation titleBlock) {
    List<DAnnotation> pieces = new ArrayList<>();
    if (isTitleBlock(titleBlock)) {
      for (EObject element : titleBlock.getReferences()) {
        if (element instanceof DAnnotation && isTitleBlockLine((DAnnotation) element)) {
          pieces.add((DAnnotation) element);
          for (EObject elementCol : ((DAnnotation) element).getReferences()) {
            if (elementCol instanceof DAnnotation) {
              pieces.add((DAnnotation) elementCol);
              for (EObject elementCel : ((DAnnotation) elementCol).getReferences()) {
                if (elementCel instanceof DAnnotation) {
                  pieces.add((DAnnotation) elementCel);
                  for (EObject elementContent : ((DAnnotation) elementCel).getReferences()) {
                    if (elementContent instanceof DAnnotation) {
                      pieces.add((DAnnotation) elementContent);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return pieces;
  }

  public static List<Object> getAllContents(DAnnotation titleBlock) {
    List<Object> allContents = new ArrayList<>();

    if (isTitleBlock(titleBlock)) {
      for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
        allContents.add(line);
        for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
          allContents.add(cell);
          for (EObject cellReference : cell.getReferences()) {
            if (cellReference instanceof DAnnotation) {
              DAnnotation cellContent = (DAnnotation) cellReference;
              allContents.add(cellContent);
              allContents.addAll(cellContent.getDetails());
            }
          }
        }
      }
    }

    return allContents;
  }

  /**
   * @param titleBlock
   * @return true if the annotation is a Diagram Title Block type annotation
   */
  public static boolean isTitleBlockAnnotation(EObject object) {
    if (object instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) object;
      return isDiagramTitleBlock(annotation) || isElementTitleBlock(annotation) || isTitleBlockCell(annotation)
          || isContentTitleBlock(annotation) || isTitleBlockLine(annotation);
    }
    return false;
  }

  /**
   * @param titleBlock
   * @return true if the annotation is a Diagram Title Block type annotation
   */
  public static String getTitleBlockAnnotationLabel(EObject object) {
    if (object instanceof DAnnotation) {
      String label = ((DAnnotation) object).getSource();
      label = label.replaceAll("([^_])([A-Z])", "$1 $2");
      return label;
    }
    return "";
  }

  /**
   * @param object
   * @return the referenced object/diagram
   */
  public static EObject getReferencedElement(EObject object) {
    EObject target = null;
    if (object instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) object;
      if (isElementTitleBlock(annotation)) {
        target = annotation.getReferences().get(0);
      } else if (isDiagramTitleBlock(annotation)) {
        target = annotation.eContainer();
      } else if (isTitleBlockCell(annotation)) {
        target = getReferencedElement(getParentTitleBlock(annotation));
      }
      if (target instanceof DRepresentation) {
        target = RepresentationHelper.getRepresentationDescriptor((DRepresentation) target);
      }
    }
    return target;
  }

  /**
   * @param object
   * @return the referenced object/diagram label
   */
  public static String getReferencedElementLabel(EObject object) {
    EObject referencedElement = TitleBlockHelper.getReferencedElement(object);
    return EObjectLabelProviderHelper.getText(referencedElement);
  }

  public static String getServiceName(String service) {
    String text = service.replaceAll(TitleBlockHelper.CAPELLA_PREFIX, "").replaceAll("([^_])([A-Z])", "$1 $2");
    text = text.length() > 1 ? text.substring(0, 1).toUpperCase() + text.substring(1) : text;
    return text;
  }

  public static String getTitleBlockName(String type) {
    if (type.equals(TitleBlockHelper.DIAGRAM_TITLE_BLOCK)) {
      return "Diagram Title Block";
    }
    if (type.equals(TitleBlockHelper.ELEMENT_TITLE_BLOCK)) {
      return "Element Title Block";
    }
    return "";
  }

  /**
   * Returns the evaluation result of a Title Block expression (aql expression or capella semantic browser query).
   * 
   * @param descriptor
   * @param expression
   * @param titleBlock
   * @return the evaluation result of a Title Block expression.
   */
  public static Object getResultOfExpression(DRepresentationDescriptor descriptor, String expression,
      DAnnotation titleBlock) {
    EObject semanticElement = TitleBlockHelper.getSemanticElementReference(titleBlock);

    // if is a Diagram Title Block, the semantic element will be the descriptor
    if (semanticElement == null) {
      semanticElement = descriptor;
    }

    IInterpreter interpreter = getInterpreter(semanticElement, expression);
    if (interpreter == null) {
      return new EvaluationException();
    }

    Object result = null;
    try {
      result = interpreter.evaluate(semanticElement, expression);
    } catch (EvaluationException e) {
      return e;
    }

    if (result instanceof Collection) {
      Collection<?> originalResult = (Collection<?>) result;
      return sanitizeResultItems(originalResult);
    }

    return sanitizeResultItem(result);
  }

  private static IInterpreter getInterpreter(EObject eObject, String expression) {
    IInterpreter interpreter = Session.of(eObject).map(Session::getInterpreter).orElseGet(() -> {
      IInterpreterProvider provider = CompoundInterpreter.INSTANCE.getProviderForExpression(expression);
      if (!(provider instanceof DefaultInterpreterProvider)) {
        return provider.createInterpreter();
      }
      return null;
    });

    return interpreter;
  }

  private static boolean isValidResultItem(Object item) {
    if (item instanceof EObject) {
      EObject eObject = (EObject) item;

      return eObject instanceof Element || eObject instanceof DRepresentationDescriptor || isViewpointElement(eObject);
    }

    return true;
  }

  private static boolean isViewpointElement(EObject eObject) {
    EPackage ePackage = eObject.eClass().getEPackage();
    return ViewpointHelper.getViewpointPackages().contains(ePackage);
  }

  private static Object sanitizeResultItem(Object resultItem) {
    if (isValidResultItem(resultItem)) {
      return resultItem;
    }

    String sanitizedText = EObjectLabelProviderHelper.getText(resultItem);

    if (sanitizedText != null && !sanitizedText.isEmpty()) {
      return sanitizedText;
    }

    return resultItem != null ? resultItem.toString() : "";
  }

  private static Object sanitizeResultItems(Collection<?> originalResult) {
    return originalResult.stream().map(TitleBlockHelper::sanitizeResultItem).collect(Collectors.toList());
  }

}
