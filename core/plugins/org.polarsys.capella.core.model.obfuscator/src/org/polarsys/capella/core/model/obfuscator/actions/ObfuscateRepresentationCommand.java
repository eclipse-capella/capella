/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.obfuscator.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.model.obfuscator.ObfuscatorHelper;

/**
 * A command to obfuscate manually a diagram
 */
public class ObfuscateRepresentationCommand extends AbstractReadWriteCommand {

  private DRepresentationDescriptor descriptor;

  public ObfuscateRepresentationCommand(DRepresentationDescriptor descriptor) {
    this.descriptor = descriptor;
  }

  public void run() {
    if (descriptor != null) {
      obfuscateDRepresentation(descriptor);
      descriptor = null;
    }
  }

  protected void obfuscateDRepresentation(DRepresentationDescriptor descriptor) {
    descriptor.setName(ObfuscatorHelper.generateUnreadableString(descriptor.getName()));
    descriptor.setDocumentation(ObfuscatorHelper.generateUnreadableString(descriptor.getDocumentation()));
    DRepresentation representation = descriptor.getRepresentation();
    if (representation != null) {
      obfuscateGMFDiagram(representation);
      obfuscateRepresentationElements(representation);
    }
  }

  /**
   * Obfuscate the GMF diagram of the current representation
   * @param representation
   */
  protected void obfuscateGMFDiagram(DRepresentation representation) {
    for (EObject diag : getOpposites(representation, NotationPackage.eINSTANCE.getView_Element())) {
      if ((diag != null) && (diag instanceof Diagram)) {
        Diagram diagram = (Diagram) diag;
        for (Object child : diagram.getPersistedChildren()) {
          if (child != null) {
            if (child instanceof Shape) {
              obfuscateShape((Shape) child);
            } else if (child instanceof Node) {
              obfuscateNode((Node) child);
            }
          }
        }
      }
    }
  }

  /**
   * Obfuscate a node and its children
   */
  protected void obfuscateNode(Node parent) {
    if (parent == null) {
      return;
    }
    for (Object child : parent.getPersistedChildren()) {
      if (child != null) {
        if (child instanceof Shape) {
          obfuscateShape((Shape) child);
        } else if (child instanceof Node) {
          obfuscateNode((Node) child);
        }
      }
    }

    if (parent.getElement() == null) {
      // for capella 1.X notes
      Style style = parent.getStyle(NotationPackage.Literals.SHAPE_STYLE);
      if (style instanceof ShapeStyle) {
        ShapeStyle ss = (ShapeStyle) style;
        ss.setDescription(ObfuscatorHelper.generateUnreadableString(ss.getDescription()));
      }
    }
  }

  /**
   * Obfuscate a shape and its children
   */
  protected void obfuscateShape(Shape shape) {
    if (shape == null) {
      return;
    }
    shape.setDescription(ObfuscatorHelper.generateUnreadableString(shape.getDescription()));
  }

  /**
   * Obfuscate all representation elements from a given representation_p
   */
  protected void obfuscateRepresentationElements(DRepresentation representation) {
    TreeIterator<EObject> content = representation.eAllContents();
    while (content.hasNext()) {
      EObject object = content.next();
      if ((object == null) || object.eIsProxy()) {
        continue;
      }

      if (object instanceof DRepresentationElement) {
        obfuscateDRepresentationElement((DRepresentationElement) object);
      }
    }
  }

  /**
   * Obfuscate a DRepresentationElement
   */
  protected void obfuscateDRepresentationElement(DRepresentationElement dElement) {
    EObject target = dElement.getTarget();

    // Update name if any
    String value = dElement.getName();
    if ((value != null) && (value.length() > 0)) {
      if ((target != null) && !(target.eIsProxy())) {
        if (target instanceof AbstractNamedElement) {
          String semanticName = (((AbstractNamedElement) target).getName());
          if ((semanticName != null) && (semanticName.length() > 0)) {
            dElement.setName(semanticName);
          } else {
            dElement.setName(ObfuscatorHelper.generateUnreadableString(value));
          }
        } else {
          dElement.setName(ObfuscatorHelper.generateUnreadableString(value));
        }
      } else {
        dElement.setName(ObfuscatorHelper.generateUnreadableString(value));
      }
    }

    if (dElement instanceof DDiagramElement) {
      obfuscateDDiagramElement((DDiagramElement) dElement);
    }

    if (dElement instanceof DLine) {
      obfuscateDLine((DLine) dElement);
    }

    if (dElement instanceof DColumn) {
      obfuscateDColumn((DColumn) dElement);
    }

    if (dElement instanceof DCell) {
      obfuscateDCell((DCell) dElement);
    }

  }

  /**
   * Obfuscate a DDiagramElement
   */
  protected void obfuscateDDiagramElement(DDiagramElement object) {
    DDiagramElement dElement = object;
    EObject target = dElement.getTarget();

    // Update tooltip if any
    String name = dElement.getTooltipText();
    if ((name != null) && (name.length() > 0)) {
      if ((target != null) && !(target.eIsProxy())) {
        if (target instanceof AbstractNamedElement) {
          String semanticName = (((AbstractNamedElement) target).getName());
          if ((semanticName != null) && (semanticName.length() > 0)) {
            dElement.setTooltipText(semanticName);
          } else {
            dElement.setTooltipText(ObfuscatorHelper.generateUnreadableString(name));
          }
        } else {
          dElement.setTooltipText(ObfuscatorHelper.generateUnreadableString(name));
        }
      } else {
        dElement.setTooltipText(ObfuscatorHelper.generateUnreadableString(name));
      }
    }

    if (dElement instanceof DEdge) {
      obfuscateDEdge((DEdge) dElement);
    }
  }

  /**
   * Obfuscate a DEdge
   */
  protected void obfuscateDEdge(DEdge object) {
    // Set beginLabel and endLabel if any
    String value = object.getBeginLabel();
    if ((value != null) && (value.length() > 0)) {
      object.setBeginLabel(ObfuscatorHelper.generateUnreadableString(value));
    }

    value = object.getEndLabel();
    if ((value != null) && (value.length() > 0)) {
      object.setEndLabel(ObfuscatorHelper.generateUnreadableString(value));
    }
  }

  /**
   * Obfuscate a DLine
   */
  protected void obfuscateDLine(DLine dElement) {
    EObject target = dElement.getTarget();

    // Update name if any
    String value = dElement.getLabel();
    if ((value != null) && (value.length() > 0)) {
      if ((target != null) && !(target.eIsProxy())) {
        if (target instanceof AbstractNamedElement) {
          String semanticName = (((AbstractNamedElement) target).getName());
          if ((semanticName != null) && (semanticName.length() > 0)) {
            dElement.setLabel(semanticName);
          } else {
            dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
          }
        } else {
          dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
        }
      } else {
        dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
      }
    }
  }

  /**
   * Obfuscate a DColumn
   */
  protected void obfuscateDColumn(DColumn dElement) {
    EObject target = dElement.getTarget();

    // Update name if any
    String value = dElement.getLabel();
    if ((value != null) && (value.length() > 0)) {
      if ((target != null) && !(target.eIsProxy())) {
        if (target instanceof AbstractNamedElement) {
          String semanticName = (((AbstractNamedElement) target).getName());
          if ((semanticName != null) && (semanticName.length() > 0)) {
            dElement.setLabel(semanticName);
          } else {
            dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
          }
        } else {
          dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
        }
      } else {
        dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
      }
    }
  }

  /**
   * Obfuscate a DCell
   */
  protected void obfuscateDCell(DCell dElement) {
    EObject target = dElement.getTarget();

    // Update name if any
    String value = dElement.getLabel();
    if ((value != null) && (value.length() > 0) && !("X".equals(value))) {
      if ((target != null) && !(target.eIsProxy())) {
        if (target instanceof AbstractNamedElement) {
          String semanticName = (((AbstractNamedElement) target).getName());
          if ((semanticName != null) && (semanticName.length() > 0)) {
            dElement.setLabel(semanticName);
          } else {
            dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
          }
        } else {
          dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
        }
      } else {
        dElement.setLabel(ObfuscatorHelper.generateUnreadableString(value));
      }
    }
  }

  /**
   * Navigate the non-navigable opposite of a reference
   * @param element a potentially null element
   * @param ref a non-null reference
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  protected List<EObject> getOpposites(EObject element, EReference ref) {
    List<EObject> result = new UniqueEList<EObject>();
    ECrossReferenceAdapter referencer = getGlobalReferencer(element);
    if (referencer != null) {
      Collection<Setting> settings = referencer.getNonNavigableInverseReferences(element);
      for (Setting setting : settings) {
        if (ref.equals(setting.getEStructuralFeature())) {
          result.add(setting.getEObject());
        }
      }
    }
    return Collections.unmodifiableList(result);
  }

  protected ECrossReferenceAdapter getGlobalReferencer(EObject elementInSession) {
    ECrossReferenceAdapter result = null;
    EObject semanticElement = ((DSemanticDecorator) elementInSession).getTarget();
    if (semanticElement != null) {
      Session session = SessionManager.INSTANCE.getSession(semanticElement);
      if (session != null) {
        result = session.getSemanticCrossReferencer();
      }
    }
    return result;
  }

}
