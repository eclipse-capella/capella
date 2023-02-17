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
package org.polarsys.capella.core.diagram.helpers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension.FormatDataManagerRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.naming.DAnnotationSourceConstants;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

public class DiagramHelper {

  private static DiagramHelper instance;

  protected DiagramHelper() {
  }

  public static DiagramHelper getService() {
    if (instance == null) {
      instance = new DiagramHelper();
    }
    return instance;
  }

  public RepresentationDescription getDescription(DRepresentation representation) {
    return DialectManager.INSTANCE.getDescription(representation);
  }

  /**
   * Returns the description of an element whether it's a model or diagram
   * 
   * @param object
   */
  public static String getElementDescription(EObject object) {
    if (object instanceof CapellaElement) {
      return ((CapellaElement) object).getDescription();
    }

    if (object instanceof DRepresentationDescriptor) {
      return ((DRepresentationDescriptor) object).getDocumentation();
    }

    return null;
  }

  /**
   * Update the description of an element whether it's a model or diagram
   * 
   * @param object
   * @param description
   */
  public static void setElementDescription(EObject object, String description) {
    if (object instanceof CapellaElement) {
      ((CapellaElement) object).setDescription(description);
    }

    if (object instanceof DRepresentationDescriptor) {
      ((DRepresentationDescriptor) object).setDocumentation(description);
    }
  }

  /**
   * Returns whether the given diagram use the given description
   * 
   * @param diagram
   *          current diagram
   * @param diagramDescriptionId
   *          a DiagramDescriptionConstants
   */
  public boolean isA(DRepresentation diagram, String descriptionId) {
    if (diagram != null) {
      RepresentationDescription description = getDescription(diagram);
      return isA(description, descriptionId);
    }
    return false;
  }

  /**
   * Returns whether the given diagram description is the given description
   * 
   * @param diagram
   *          current diagram
   * @param diagramDescriptionId
   *          a DiagramDescriptionConstants
   */
  public boolean isA(RepresentationDescription description, String diagramDescriptionId) {
    if (description != null) {
      if (diagramDescriptionId == null) {
        return true;
      }
      return diagramDescriptionId.equals(description.getName());
    }
    return false;
  }

  public boolean isArchitectureBlank(DRepresentation diagram) {
    return hasKind(diagram, DiagramDescriptionConstants.ARCHITECTURE_BLANK_DIAGRAM_NAME)
        || hasKind(diagram, DiagramDescriptionConstants.ENTITY_BLANK_DIAGRAM_NAME);
  }

  public boolean isBreakdown(DRepresentation diagram) {
    return hasKind(diagram, DiagramDescriptionConstants.BREAKDOWN_DIAGRAM_NAME);
  }

  public boolean hasKind(DRepresentation diagram, String diagramDescriptionId) {
    if (diagram != null) {
      RepresentationDescription description = getDescription(diagram);
      return hasKind(description, diagramDescriptionId);
    }
    return false;
  }

  public boolean hasKind(RepresentationDescription description, String diagramDescriptionId) {
    if (description != null) {
      if (diagramDescriptionId == null) {
        return true;
      }
      return description.getName().contains(diagramDescriptionId);
    }
    return false;
  }

  /**
   * Returns the DDiagram owning the given element
   * 
   * @param current
   * @return
   */
  public DDiagram getDiagramContainer(EObject current) {
    if (current instanceof DDiagram) {
      return ((DDiagram) current);
    }
    return (DDiagram) EcoreUtil2.getFirstContainer(current, DiagramPackage.Literals.DDIAGRAM);
  }

  public RepresentationDescription getDescription(Session session, String descriptionId) {
    Collection<Viewpoint> viewpoints = session.getSelectedViewpoints(true);
    for (Viewpoint viewpoint : viewpoints) {
      for (RepresentationDescription description : viewpoint.getOwnedRepresentations()) {
        if (descriptionId.equals(description.getName())) {
          return description;
        }
      }
    }
    return null;
  }

  /**
   * Returns the current session for the given diagram
   * 
   * @param diagram
   * @return
   */
  public Session getSession(DRepresentation diagram) {
    if (diagram instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) diagram).getTarget();
      if (target == null) {
        return null;
      }
      return SessionManager.INSTANCE.getSession(target);
    }
    return null;
  }

  public DRepresentation createDRepresentation(String name, EObject semantic, RepresentationDescription description,
      Session session, IProgressMonitor monitor) {
    return DialectManager.INSTANCE.createRepresentation(name, semantic, description, session, monitor);
  }

  /**
   * @param decorator
   * @return
   */
  public DRepresentation getRepresentation(DSemanticDecorator decorator) {
    if (decorator instanceof DRepresentation) {
      return ((DRepresentation) decorator);
    }
    return (DRepresentation) EcoreUtil2.getFirstContainer(decorator, ViewpointPackage.Literals.DREPRESENTATION);
  }

  /**
   * Get the package name of a representation descriptor.
   * 
   * @param descriptor
   * @return the package name, or null if the descriptor has no package
   */
  public String getPackageName(DRepresentationDescriptor descriptor) {
    String name = null;
    DAnnotation annot = DAnnotationHelper.getAnnotation(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE, descriptor,
        false);
    if (annot != null) {
      name = annot.getDetails().get(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE_KEY);
    }
    return name;
  }

  /**
   * Creates a command to set or clear the package name for a representation descriptor. Before creating a change
   * command, inspect the current package name and if that is equal to the new name return null
   * 
   * @param descriptor
   * @param name
   *          the new package name, or null to clear the package
   */
  public Command createSetPackageNameCommand(TransactionalEditingDomain domain,
      final DRepresentationDescriptor descriptor, final String name) {

    //
    // If package is changed re-create the whole annotation to force a notification
    // from the descriptor which then causes a viewer refresh.
    //
    AbstractCommand command = null;
    final DAnnotation annot = DAnnotationHelper.getAnnotation(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE,
        descriptor, false);

    if (name != null) {

      String currentValue = null;

      if (annot != null) {
        currentValue = annot.getDetails().get(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE_KEY);
      }

      if (currentValue == null || !currentValue.equals(name)) {
        command = new RecordingCommand(domain) {
          @Override
          public void doExecute() {
            if (annot != null) {
              SiriusUtil.delete(annot);
            }
            DAnnotation annot = DAnnotationHelper.getAnnotation(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE,
                descriptor, true);
            annot.getDetails().put(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE_KEY, name);
          }
        };
        command.setLabel(Messages.DiagramHelper_0);
      }

    } else if (annot != null) {
      command = new RecordingCommand(domain) {

        @Override
        protected void doExecute() {
          SiriusUtil.delete(annot);
        }

      };
      command.setLabel(Messages.DiagramHelper_1);
    }

    return command;

  }

  // Note that this method does not synchronize AbsoluteBoundsFilter for DNodeContainer.
  public static void collapseContainer(TransactionalEditingDomain ted, DNodeContainer container) {
    if (container != null && ted != null) {
      ted.getCommandStack().execute(new RecordingCommand(ted) {

        @Override
        protected void doExecute() {
          Node gmfNode = SiriusGMFHelper.getGmfNode(container);

          for (Object subNode : gmfNode.getChildren()) {
            if (subNode instanceof Node) {
              for (Object style : ((Node) subNode).getStyles()) {
                if (style instanceof DrawerStyle) {
                  ((DrawerStyle) style).setCollapsed(true);
                }
              }
            }
          }
        }
      });
    }
  }

  // Note that this method does not synchronize AbsoluteBoundsFilter for DNodeContainer.
  public static void unCollapseContainer(TransactionalEditingDomain ted, DNodeContainer container) {
    if (container != null && ted != null) {
      ted.getCommandStack().execute(new RecordingCommand(ted) {

        @Override
        protected void doExecute() {
          Node gmfNode = SiriusGMFHelper.getGmfNode(container);

          for (Object subNode : gmfNode.getChildren()) {
            if (subNode instanceof Node) {
              for (Object style : ((Node) subNode).getStyles()) {
                if (style instanceof DrawerStyle) {
                  ((DrawerStyle) style).setCollapsed(false);
                }
              }
            }
          }
        }
      });
    }
  }

  /**
   * copy/paste layout from one diagram to another without creating an editor.
   * 
   * @param sourceDiagram
   * @param targetDiagram
   * @param shell
   */
  public void offScreenDiagramCopyPasteLayout(DSemanticDiagram sourceDiagram, DSemanticDiagram targetDiagram,
      Shell shell) {
    Collection<EObject> sourceViews = new EObjectQuery(sourceDiagram)
        .getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
    Collection<EObject> targetViews = new EObjectQuery(targetDiagram)
        .getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
    Optional<Diagram> sourceDiagramOptional = sourceViews.stream().filter(Diagram.class::isInstance)
        .map(Diagram.class::cast).findFirst();
    Optional<Diagram> targetDiagramOptional = targetViews.stream().filter(Diagram.class::isInstance)
        .map(Diagram.class::cast).findFirst();
    if (sourceDiagramOptional.isPresent() && targetDiagramOptional.isPresent()) {
      Diagram sourceGmfDiagram = sourceDiagramOptional.get();
      Diagram targetGmfDiagram = targetDiagramOptional.get();
      // Synchronize GMF model with target diagram
      CanonicalSynchronizer targetDiagramSynchronizer = CanonicalSynchronizerFactory.INSTANCE
          .createCanonicalSynchronizer(targetGmfDiagram);
      targetDiagramSynchronizer.synchronize();
      DiagramEditPart sourceEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(sourceGmfDiagram,
          shell);
      DiagramEditPart targetEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(targetGmfDiagram,
          shell);
      // Store layout of source diagram
      List<SiriusFormatDataManager> diagramFormatDataManagers = FormatDataManagerRegistry
          .getSiriusFormatDataManagers(sourceDiagram);
      diagramFormatDataManagers.stream()
          .forEach(formatDataManager -> formatDataManager.storeFormatData(sourceEditPart));
      // Copy layout to target diagram
      diagramFormatDataManagers.stream().forEach(formatDataManager -> formatDataManager.applyLayout(targetEditPart));
      // Clear layout of source diagram
      diagramFormatDataManagers.stream().forEach(SiriusFormatDataManager::clearFormatData);
    }
  }

  /**
   * copy layout from the diagram without creating an editor.
   * 
   * @param diagram
   * @param shell
   */
  public void offScreenDiagramCopyLayout(DSemanticDiagram diagram, Shell shell) {
    Collection<EObject> views = new EObjectQuery(diagram)
        .getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
    Optional<Diagram> diagramOptional = views.stream().filter(Diagram.class::isInstance).map(Diagram.class::cast)
        .findFirst();
    if (diagramOptional.isPresent()) {
      Diagram gmfDiagram = diagramOptional.get();
      DiagramEditPart sourceEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(gmfDiagram, shell);
      // Store layout of source diagram
      List<SiriusFormatDataManager> diagramFormatDataManagers = FormatDataManagerRegistry
          .getSiriusFormatDataManagers(diagram);
      diagramFormatDataManagers.stream()
          .forEach(formatDataManager -> formatDataManager.storeFormatData(sourceEditPart));
    }
  }

  /**
   * paste layout to the diagram without creating an editor.
   * 
   * @param diagram
   * @param shell
   */
  public void offScreenDiagramPasteLayout(DSemanticDiagram diagram, Shell shell) {
    Collection<EObject> views = new EObjectQuery(diagram)
        .getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
    Optional<Diagram> diagramOptional = views.stream().filter(Diagram.class::isInstance).map(Diagram.class::cast)
        .findFirst();
    if (diagramOptional.isPresent()) {
      Diagram gmfDiagram = diagramOptional.get();
      DiagramEditPart targetEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(gmfDiagram, shell);
      // Store layout of source diagram
      List<SiriusFormatDataManager> diagramFormatDataManagers = FormatDataManagerRegistry
          .getSiriusFormatDataManagers(diagram);
      // Copy layout to target diagram
      diagramFormatDataManagers.stream().forEach(formatDataManager -> formatDataManager.applyLayout(targetEditPart));
      // Clear layout of source diagram
      diagramFormatDataManagers.stream().forEach(SiriusFormatDataManager::clearFormatData);
    }
  }

  /**
   * Set the position of the given node at the given location
   * 
   * @param container
   * @param location
   */
  public static void setPosition(AbstractDNode node, Point location) {
    SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(node, location, null));
  }

  /**
   * Returns the position relatively to the given node (relative to the given container)
   * 
   * @param node
   * @param deltaX
   * @param deltaY
   * @return
   */
  public static Point getRelativePositionToNode(AbstractDNode node, EObject container, int deltaX, int deltaY) {
    // the new position is the top-left of diagram
    Point newLocation = new Point();
    Point nodeLocation = getAbsolutePositionOfNode(node);
    // get the width and height of the node
    int nodeWidth = 0;
    int nodeHeight = 0;
    ShapeEditPart nodeEditPart = getShapeEditPart(node);
    if (nodeEditPart != null) {
      nodeWidth = nodeEditPart.getSize().width;
      nodeHeight = nodeEditPart.getSize().height;
    }
    // translate to the top-left of the node
    newLocation.translate(nodeLocation);
    if (deltaX == 0) {
      // translate to the center of the node
      newLocation.translate(nodeWidth / 2, 0);
    } else if (deltaX > 0) {
      // translate to the right border of the node + given delta
      newLocation.translate(nodeWidth + deltaX, 0);
    } else if (deltaX < 0) {
      // translate to given delta
      newLocation.translate(deltaX, 0);
    }
    if (deltaY == 0) {
      // translate to the center of the node
      newLocation.translate(0, nodeHeight / 2);
    } else if (deltaY < 0) {
      // translate to given delta
      newLocation.translate(0, deltaY);
    } else if (deltaY > 0) {
      // translate to the bottom border of the node + given delta
      newLocation.translate(0, nodeHeight + deltaY);
    }
    // transform the absolute position to a relative position to its future container
    tranformAbsolutePositionInRelativePositionToTheContainer(newLocation, container);
    return newLocation;
  }

  /**
   * Returns the position of the middle of the given edge (relative to the given container)
   * 
   * @param edge
   * @return
   */
  public static Point getPositionAtMiddleOfEdge(DEdge edge, EObject container, int deltaX, int deltaY) {
    Point newLocation = new Point();
    EdgeTarget sourceEdge = edge.getSourceNode();
    EdgeTarget targetEdge = edge.getTargetNode();
    if (sourceEdge instanceof AbstractDNode && targetEdge instanceof AbstractDNode) {
      AbstractDNode source = (AbstractDNode) sourceEdge;
      AbstractDNode target = (AbstractDNode) targetEdge;
      Point sourceLocation = getAbsolutePositionOfNode(source);
      Point targetLocation = getAbsolutePositionOfNode(target);
      // translate to the top-left of the source
      newLocation.translate(sourceLocation);
      // set source and target position to their center
      ShapeEditPart sourceEditPart = getShapeEditPart(source);
      ShapeEditPart targetEditPart = getShapeEditPart(target);
      if (sourceEditPart != null && targetEditPart != null) {
        sourceLocation.translate(sourceEditPart.getSize().width / 2, sourceEditPart.getSize().height / 2);
        targetLocation.translate(targetEditPart.getSize().height / 2, targetEditPart.getSize().height / 2);
      }
      // translate to the middle of the edge (between centers of source and target)
      newLocation.translate((targetLocation.x - sourceLocation.x) / 2, (targetLocation.y - sourceLocation.y) / 2);
    }
    // translate to the given delta
    newLocation.translate(deltaX, deltaY);

    // transform the absolute position to a relative position to its future container
    tranformAbsolutePositionInRelativePositionToTheContainer(newLocation, container);
    return newLocation;
  }

  /**
   * Transform an absolute position in a relative position
   * 
   * @param position
   * @param container
   */
  public static void tranformAbsolutePositionInRelativePositionToTheContainer(Point position, EObject container) {
    // if the container is not the diagram, we have to substract its absolute position to be relative to it
    Point containerlocation = new Point();
    if (container instanceof AbstractDNode) {
      containerlocation = DiagramHelper.getAbsolutePositionOfNode((AbstractDNode) container);
    }
    position.translate(-containerlocation.x, -containerlocation.y);
  }

  /**
   * Returns the absolute position of the given node
   * 
   * @param node
   * @return
   */
  public static Point getAbsolutePositionOfNode(AbstractDNode node) {
    Point position = new Point();
    getAbsolutePositionOfNode(node, position);
    return position;
  }

  /**
   * Set the given point to the absolute position of the given node
   * 
   * @param node
   * @param position
   */
  private static void getAbsolutePositionOfNode(AbstractDNode node, Point position) {
    ShapeEditPart shapeEditPart = getShapeEditPart(node);
    if (shapeEditPart != null) {
      position.translate(shapeEditPart.getLocation());
      // position is relative to the container
      EObject container = node.eContainer();
      // so we have to take into account the position of the container, until the container is the diagram
      if (!(container instanceof DDiagram) && container instanceof AbstractDNode) {
        getAbsolutePositionOfNode((AbstractDNode) container, position);
      }
    }
  }

  /**
   * Returns the GraphicalEditPart of the given DDiagramElement
   * 
   * @param diagramElement
   * @return
   */
  public static ShapeEditPart getShapeEditPart(DDiagramElement diagramElement) {
    IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if (editor instanceof DiagramEditor) {
      Session session = new EObjectQuery(diagramElement).getSession();
      View gmfView = SiriusGMFHelper.getGmfView(diagramElement, session);

      if (gmfView != null && editor instanceof DiagramEditor) {
        final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
        final Object editPart = editPartRegistry.get(gmfView);
        if (editPart instanceof ShapeEditPart) {
          return (ShapeEditPart) editPart;
        }
      }
    }
    return null;
  }
}