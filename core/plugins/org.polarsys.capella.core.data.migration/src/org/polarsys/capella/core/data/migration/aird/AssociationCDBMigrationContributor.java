/*******************************************************************************
 * Copyright (c) 2025 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.aird;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.osgi.framework.Version;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.InformationServices;

/**
 * 
 * This class migrates the association of the CDB diagram to always have the same source and target.
 * 
 * @author Séraphin Costa
 * 
 */
public class AssociationCDBMigrationContributor extends AirdMigrationContributor {
  public static final Version MIGRATION_MAX_VERSION_EXPECTED = new Version(7, 0, 0);

  private boolean isClassDiagramBlank(DDiagram diagram) {
    String diagramName = DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME;
    return diagram.getDescription().getName().equalsIgnoreCase(diagramName);
  }

  private boolean isAssociation(DDiagram diagram, DEdge edge) {
    String mappingName = IMappingNameConstants.CDB_ASSOCIATION_MAPPING_NAME;
    EdgeMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
    if (mapping == null) {
      return false;
    } else {
      return DiagramServices.getDiagramServices().isMapping(edge, mapping);
    }
  }

  private void migrateDEdgeStyle(DEdge dEdge) {
    Session.of(dEdge).ifPresent(session -> {
      EObject containerVariable;
      if (dEdge.eContainer() instanceof DSemanticDecorator dEdgeContainer) {
        containerVariable = dEdgeContainer.getTarget();
      } else {
        containerVariable = null;
      }
      IInterpreter interpreter = session.getInterpreter();
      interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, dEdge.getParentDiagram());
      interpreter.setVariable(IInterpreterSiriusVariables.VIEW, dEdge);
      interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, dEdge.getSourceNode());
      interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, dEdge.getTargetNode());

      StyleDescription styleDescription = new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(
          dEdge.getDiagramElementMapping(), dEdge.getTarget(), dEdge, containerVariable, dEdge.getParentDiagram());

      interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
      interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
      interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
      interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);

      dEdge.getOwnedStyle().setDescription(styleDescription);
    });
  }

  private void migrateDEdge(DEdge dEdge) {
    EdgeTarget sourceNode = dEdge.getSourceNode();
    EdgeTarget targetNode = dEdge.getTargetNode();
    String beginLabel = dEdge.getBeginLabel();
    String endLabel = dEdge.getEndLabel();
    EdgeArrows sourceArrow = dEdge.getOwnedStyle().getSourceArrow();
    EdgeArrows targetArrow = dEdge.getOwnedStyle().getTargetArrow();
    List<String> customFeatures = dEdge.getOwnedStyle().getCustomFeatures();

    List<DEdge> sourceNodeIncommingEdges = sourceNode.getIncomingEdges();
    List<DEdge> sourceNodeOutgoingEdges = sourceNode.getOutgoingEdges();
    List<DEdge> targetNodeIncommingEdges = targetNode.getIncomingEdges();
    List<DEdge> targetNodeOutgoingEdges = targetNode.getOutgoingEdges();

    dEdge.setSourceNode(targetNode);
    dEdge.setTargetNode(sourceNode);
    dEdge.setBeginLabel(endLabel);
    dEdge.setEndLabel(beginLabel);
    sourceNodeOutgoingEdges.remove(dEdge);
    targetNodeIncommingEdges.remove(dEdge);
    sourceNodeIncommingEdges.add(dEdge);
    targetNodeOutgoingEdges.add(dEdge);

    dEdge.getOwnedStyle().setSourceArrow(targetArrow);
    dEdge.getOwnedStyle().setTargetArrow(sourceArrow);

    // inverse the custom features sourceArrow and targetArrow
    String sourceArrowFeatureName = DiagramPackage.eINSTANCE.getEdgeStyle_SourceArrow().getName();
    String targetArrowFeatureName = DiagramPackage.eINSTANCE.getEdgeStyle_TargetArrow().getName();
    boolean containsSourceArrowFeatureName = customFeatures.contains(sourceArrowFeatureName);
    boolean containsTargetArrowFeatureName = customFeatures.contains(targetArrowFeatureName);
    if (containsSourceArrowFeatureName && !containsTargetArrowFeatureName) {
      customFeatures.add(targetArrowFeatureName);
      customFeatures.remove(sourceArrowFeatureName);
    } else if (!containsSourceArrowFeatureName && containsTargetArrowFeatureName) {
      customFeatures.add(sourceArrowFeatureName);
      customFeatures.remove(targetArrowFeatureName);
    }

    migrateDEdgeStyle(dEdge);
  }

  private boolean isBeginLabel(Object view) {
    return view instanceof Node node
        && SiriusVisualIDRegistry.getVisualID(node.getType()) == DEdgeBeginNameEditPart.VISUAL_ID;
  }

  private boolean isMiddleLabel(Object view) {
    return view instanceof Node node
        && SiriusVisualIDRegistry.getVisualID(node.getType()) == DEdgeNameEditPart.VISUAL_ID;
  }

  private boolean isEndLabel(Object view) {
    return view instanceof Node node
        && SiriusVisualIDRegistry.getVisualID(node.getType()) == DEdgeEndNameEditPart.VISUAL_ID;
  }

  private Object migrateBendpoint(Object rawBendpoint) {
    if (rawBendpoint instanceof RelativeBendpoint bp) {
      return new RelativeBendpoint(bp.getTargetX(), bp.getTargetY(), bp.getSourceX(), bp.getSourceY());
    } else {
      return rawBendpoint;
    }
  }

  private List<?> getReversedBendpoints(List<?> points) {
    List<?> newPoints = Arrays.asList(points.stream().map(this::migrateBendpoint).toArray());
    Collections.reverse(newPoints);
    return newPoints;
  }

  private Point getBendpointSourcePosition(Object rawBendpoint) {
    if (rawBendpoint instanceof RelativeBendpoint bp) {
      return new Point(bp.getSourceX(), bp.getSourceY());
    } else {
      return null;
    }
  }

  private Point getBendpointTargetPosition(Object rawBendpoint) {
    if (rawBendpoint instanceof RelativeBendpoint bp) {
      return new Point(bp.getTargetX(), bp.getTargetY());
    } else {
      return null;
    }
  }

  private Collector<Point, PointList, PointList> pointListCollector() {
    return Collector.of(PointList::new, PointList::addPoint, (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    });
  }

  private int getLabelLocation(Node label) {
    switch (SiriusVisualIDRegistry.getVisualID(label)) {
    case DEdgeBeginNameEditPart.VISUAL_ID:
      return LabelViewConstants.SOURCE_LOCATION;
    case DEdgeEndNameEditPart.VISUAL_ID:
      return LabelViewConstants.TARGET_LOCATION;
    case DEdgeNameEditPart.VISUAL_ID:
    default:
      return LabelViewConstants.MIDDLE_LOCATION;
    }
  }

  private int getLabelReverseLocation(Node label) {
    switch (SiriusVisualIDRegistry.getVisualID(label)) {
    case DEdgeBeginNameEditPart.VISUAL_ID:
      return LabelViewConstants.TARGET_LOCATION;
    case DEdgeEndNameEditPart.VISUAL_ID:
      return LabelViewConstants.SOURCE_LOCATION;
    case DEdgeNameEditPart.VISUAL_ID:
    default:
      return LabelViewConstants.MIDDLE_LOCATION;
    }
  }

  private Point getNewLabelPosition(Node label, Bounds bounds, List<?> bendPoints, List<?> revBendPoints) {
    Point labelOffset = new Point(bounds.getX(), bounds.getY());

    PointList bendPointList = bendPoints.stream().map(this::getBendpointTargetPosition).collect(pointListCollector());
    PointList newBendPointList = revBendPoints.stream().map(this::getBendpointSourcePosition)
        .collect(pointListCollector());

    int referenceLocation = getLabelLocation(label);
    int newReferenceLocation = getLabelReverseLocation(label);

    Point referencePoint = PointListUtilities.calculatePointRelativeToLine(bendPointList, 0, referenceLocation, true);
    Point newReferencePoint = PointListUtilities.calculatePointRelativeToLine(newBendPointList, 0, newReferenceLocation,
        true);

    Point labelCenter = EdgeLabelQuery.relativeCenterCoordinateFromOffset(bendPointList, referencePoint, labelOffset);
    return EdgeLabelQuery.offsetFromRelativeCoordinate(labelCenter, newBendPointList, newReferencePoint);
  }

  private void migrateLabelPosition(Node label, List<?> bendPoints, List<?> revBendPoints) {
    Bounds bounds = (Bounds) label.getLayoutConstraint();
    Point newPosition = getNewLabelPosition(label, bounds, bendPoints, revBendPoints);
    bounds.setX(newPosition.x());
    bounds.setY(newPosition.y());
  }

  @SuppressWarnings("unchecked")
  private void migrateGMFEdge(Edge gmfEdge) {
    // source/target, anchors, bendpoints
    View sourceView = gmfEdge.getSource();
    View targetView = gmfEdge.getTarget();
    List<Edge> sourceViewSourceEdges = sourceView.getSourceEdges();
    List<Edge> sourceViewTargetEdges = sourceView.getTargetEdges();
    List<Edge> targetViewSourceEdges = targetView.getSourceEdges();
    List<Edge> targetViewTargetEdges = targetView.getTargetEdges();
    Anchor sourceAnchor = gmfEdge.getSourceAnchor();
    Anchor targetAnchor = gmfEdge.getTargetAnchor();
    RelativeBendpoints bendpoints = (RelativeBendpoints) gmfEdge.getBendpoints();
    List<?> bendpointsList = bendpoints.getPoints();
    List<?> revBendpointsList = getReversedBendpoints(bendpointsList);

    // labels
    List<?> edgeChildren = gmfEdge.getChildren();
    Optional<Node> beginLabel = edgeChildren.stream().filter(this::isBeginLabel).map(Node.class::cast).findAny();
    Optional<Node> middleLabel = edgeChildren.stream().filter(this::isMiddleLabel).map(Node.class::cast).findAny();
    Optional<Node> endLabel = edgeChildren.stream().filter(this::isEndLabel).map(Node.class::cast).findAny();

    // changes
    if (!bendpointsList.isEmpty()) {
        beginLabel.ifPresent(label -> migrateLabelPosition(label, bendpointsList, revBendpointsList));
        middleLabel.ifPresent(label -> migrateLabelPosition(label, bendpointsList, revBendpointsList));
        endLabel.ifPresent(label -> migrateLabelPosition(label, bendpointsList, revBendpointsList));
    }

    beginLabel.ifPresent(label -> label.setType(SiriusVisualIDRegistry.getType(DEdgeEndNameEditPart.VISUAL_ID)));
    endLabel.ifPresent(label -> label.setType(SiriusVisualIDRegistry.getType(DEdgeBeginNameEditPart.VISUAL_ID)));

    gmfEdge.setSource(targetView);
    gmfEdge.setTarget(sourceView);
    sourceViewTargetEdges.add(gmfEdge);
    targetViewSourceEdges.add(gmfEdge);
    sourceViewSourceEdges.remove(gmfEdge);
    targetViewTargetEdges.remove(gmfEdge);

    gmfEdge.setSourceAnchor(targetAnchor);
    gmfEdge.setTargetAnchor(sourceAnchor);
    bendpoints.setPoints(revBendpointsList);
  }

  private void migrateAssociation(DEdge dEdge) {
    migrateDEdge(dEdge);
    migrateGMFEdge(SiriusGMFHelper.getGmfEdge(dEdge));
  }

  private boolean needMigration(DEdge dEdge) {
    if (dEdge.getTarget() instanceof Association association) {
      Property sourceProp = InformationServices.getService().getAssociationSource(association);
      Property targetProp = InformationServices.getService().getAssociationTarget(association);
      AbstractType expectedSource = sourceProp.getAbstractType();
      AbstractType expectedTarget = targetProp.getAbstractType();
      if (dEdge.getSourceNode() instanceof DSemanticDecorator sourceNode
          && dEdge.getTargetNode() instanceof DSemanticDecorator targetNode) {
        EObject sourceObject = sourceNode.getTarget();
        EObject targetObject = targetNode.getTarget();
        return sourceObject == expectedTarget && targetObject == expectedSource;
      } else {
        // error
        String errorMsg = MessageFormat.format(Messages.MigrationAction_InvalidEdgeExtremityInvalidType,
            dEdge.getName(), dEdge.getParentDiagram().getName());
        Activator.getDefault().getLog().error(errorMsg, new ClassCastException(errorMsg));
        return false;
      }
    } else {
      // not association
      return false;
    }
  }

  private void migrateClassDiagramBlank(DDiagram diagram) {
    diagram.getEdges().stream().filter(edge -> isAssociation(diagram, edge)).filter(this::needMigration)
        .forEach(this::migrateAssociation);
  }

  private void migrate(Session session) {
    session.getOwnedViews().stream().flatMap(dView -> dView.getOwnedRepresentationDescriptors().stream())
        .map(dRepDesc -> dRepDesc.getRepresentation()).filter(DDiagram.class::isInstance).map(DDiagram.class::cast)
        .filter(AssociationCDBMigrationContributor.this::isClassDiagramBlank)
        .forEach(AssociationCDBMigrationContributor.this::migrateClassDiagramBlank);
  }

  @Override
  public MigrationRunnable getRunnable(IFile file) {
    return new AirdMigrationRunnable(file) {
      @Override
      public String getName() {
        return Messages.MigrationAction_AssociationCDBMigration;
      }

      @Override
      public IStatus run(MigrationContext context, boolean checkVersion) {
        IFile airdFile = getFile();

        // fileVersion > MIGRATION_MAX_VERSION_EXPECTED
        if (context.getFileVersion(airdFile).compareTo(MIGRATION_MAX_VERSION_EXPECTED) > 0) {
          // If the version of the file is newer than the fix of the association CDB bug fix,
          // so, we don't need to migrate and we can ignore the migration.
          return Status.OK_STATUS;
        } else {
          URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
          Session session = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
          if (session != null) {
            session.open(new NullProgressMonitor());

            TransactionalEditingDomain editingDomain = session.getTransactionalEditingDomain();
            editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
              @Override
              protected void doExecute() {
                migrate(session);
              }
            });

            session.save(new NullProgressMonitor());
            session.close(new NullProgressMonitor());
          }
          return Status.OK_STATUS;
        }
      }
    };
  }

  @Override
  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__ASSOCIATION_CDB;
  }
}
