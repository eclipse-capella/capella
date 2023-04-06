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
package org.polarsys.capella.core.transition.diagram.commands;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewRefactorHelper;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.type.DiagramNotationType;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Ratio;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationTask;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.internal.refresh.SynchronizeGMFModelCommand;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectArrangeOperation;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension.FormatDataManagerRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.ShapeUtil;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.diagram.Activator;
import org.polarsys.capella.core.transition.diagram.commands.DiagramTransitionRunnable.ExtendedViewRefactorHelper;
import org.polarsys.capella.core.transition.diagram.handlers.DiagramDescriptionHelper;
import org.polarsys.capella.core.transition.diagram.helpers.TraceabilityHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A transition of diagram cannot be made into only one transactional command. In fact, GMF is registered to be
 * processing visibility update after each transaction.
 */
public class DiagramTransitionRunnable extends AbstractProcessingCommands<DDiagram> {

  public static final String TRANSITION_TRACEABILITY = "org.polarsys.capella.core.transition.diagram";

  public static final String SOURCE_DIAGRAM = "SOURCE_DIAGRAM";
  public static final String TARGET_DIAGRAM = "TARGET_DIAGRAM";
  public static final String SOURCE_DESCRIPTION = "SOURCE_DESCRIPTION";
  public static final String TARGET_DESCRIPTION = "TARGET_DESCRIPTION";

  public static final String TARGET_VIEWS = "TARGET__VIEWS";
  public static final String SOURCE_HIDDEN_VIEWS = "SOURCE__HIDDEN_VIEWS";
  public static final String SOURCE_HIDDEN_LABEL_VIEWS = "SOURCE__HIDDEN_LABEL_VIEWS";

  public static final String TARGET_DOUBLE_VIEWS = "SOURCE__HIDDEN_LABEL_VIEWS";

  public static final String DIAGRAM_CREATION = "DIAGRAM_CREATION";

  /**
   * @param diagrams
   */
  public DiagramTransitionRunnable(Collection<DDiagram> elements) {
    super(elements);
  }

  public String getName() {
    return "Diagram Initialization";
  }

  @Override
  protected void init(IProgressMonitor monitor) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void dispose(IProgressMonitor monitor) {
    super.dispose(monitor);

    IContext context = getContext();
    DiagramDescriptionHelper.getService(context).dispose(context);
    TraceabilityHelper.getService(context).dispose(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performCommand(Collection<DDiagram> elements, IProgressMonitor monitor) {
    IStatus status = Status.OK_STATUS;

    if ((elements != null) && (!elements.isEmpty())) {
      monitor.beginTask(NLS.bind(Messages.DiagramTransitionCommand_DescriptionCount, elements.size()), elements.size());
      for (DDiagram object : elements) {
        try {
          status = proceedDiagram(object, new SubProgressMonitor(monitor, 1));

          DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);
          checkStatus(object, targetDiagram, status);

        } catch (Exception e) {
          DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);
          checkStatus(object, targetDiagram, status);

        }
        monitor.worked(1);
      }
      monitor.done();
    }
  }

  /**
   * @param status
   */
  protected void checkStatus(DRepresentation source, DRepresentation target, IStatus status) {
    Logger logger = Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM);
    Collection list = new ArrayList<Object>();
    if (source != null) {
      list.add(source);
    }
    if (target != null) {
      list.add(target);
    }

    EmbeddedMessage message = new EmbeddedMessage(status.getMessage(), IReportManagerDefaultComponents.DIAGRAM, list);

    if (status.getSeverity() == IStatus.ERROR) {
      logger.error(message);
    } else if (status.getSeverity() == IStatus.WARNING) {
      logger.warn(message);
    } else {
      logger.info(message);
    }
  }

  protected HashMapSet<DSemanticDecorator, DSemanticDecorator> getTargetViews(IContext context) {
    if (!context.exists(TARGET_VIEWS)) {
      HashMapSet<DSemanticDecorator, DSemanticDecorator> map = new HashMapSet<DSemanticDecorator, DSemanticDecorator>();
      context.put(TARGET_VIEWS, map);
    }
    return (HashMapSet<DSemanticDecorator, DSemanticDecorator>) context.get(TARGET_VIEWS);
  }

  protected HashMapSet<DSemanticDecorator, DSemanticDecorator> getHiddenSourceViews(IContext context) {
    if (!context.exists(SOURCE_HIDDEN_VIEWS)) {
      HashMapSet<DSemanticDecorator, DSemanticDecorator> map = new HashMapSet<DSemanticDecorator, DSemanticDecorator>();
      context.put(SOURCE_HIDDEN_VIEWS, map);
    }
    return (HashMapSet<DSemanticDecorator, DSemanticDecorator>) context.get(SOURCE_HIDDEN_VIEWS);
  }

  /**
   * @param diagram
   */
  protected IStatus proceedDiagram(final DRepresentation diagram, final IProgressMonitor monitor) {
    String diagramName = EObjectExt.getText(diagram);

    monitor.beginTask(NLS.bind("Diagram Initialization - {0}", diagramName), 3);
    monitor.setTaskName(NLS.bind("Diagram Initialization - {0}", diagramName));

    final IContext context = getContext();

    if (!(diagram instanceof DSemanticDecorator)) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Invalid source diagram");
    }
    context.put(SOURCE_DIAGRAM, diagram);

    // Retrieve semantic target of diagram
    final EObject sourceSemantic = ((DSemanticDecorator) diagram).getTarget();
    if ((sourceSemantic == null) || sourceSemantic.eIsProxy()) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Diagram with invalid semantic target");
    }

    // Retrieve the current session
    final Session session = DiagramHelper.getService().getSession(diagram);
    if (session == null) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Cannot retrieve session from the given diagram");
    }

    context.put(ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN, session.getTransactionalEditingDomain());
    context.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN, session.getTransactionalEditingDomain());

    // Retrieve the current description
    final RepresentationDescription description = DiagramHelper.getService().getDescription(diagram);
    if ((description == null) || description.eIsProxy()) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Invalid source diagram (maybe a missing viewpoint)");
    }
    context.put(SOURCE_DESCRIPTION, description);

    monitor.worked(1);

    IStatus status = Status.OK_STATUS;

    // Copy layout cannot copy layout of hidden elements, we need to run two commands !
    status = runCommand(session, NLS.bind("{0} - {1} (1/3)", getName(), diagramName), new RunnableWithBooleanResult() {
      @Override
      public void run() {

        IStatus result = Status.OK_STATUS;

        // Retrieve or initialize target diagram
        result = retrieveTargetDiagram(diagram, session, sourceSemantic, description, monitor);
        if (!result.isOK()) {
          setStatus(result);
          return;
        }

        DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);

        // Initialize diagram (filters and others shared elements)
        result = initializeDiagrams(diagram, targetDiagram);
        if (!result.isOK()) {
          setStatus(result);
          return;
        }

        // Synchronize elements in diagram
        result = synchronizeDiagrams(diagram, targetDiagram);
        if (!result.isOK()) {
          setStatus(result);
          return;
        }

        // Reveal hidden elements
        revealHiddenElements((DSemanticDiagram) diagram, (DSemanticDiagram) targetDiagram);

        setStatus(result);
      }
    });

    monitor.worked(1);

    if (!status.isOK()) {
      rollbackDiagram(diagram);
      return status;
    }

    status = runCommand(session, NLS.bind("{0} - {1} (2/3)", getName(), diagramName), new RunnableWithBooleanResult() {
      @Override
      public void run() {
        DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);

        // Copy Layout of elements and GMF contents
        copyDDiagramLayout((DSemanticDiagram) diagram, (DSemanticDiagram) targetDiagram, session, getShell());

        setStatus(Status.OK_STATUS);
      }
    });

    status = runCommand(session, NLS.bind("{0} - {1} (3/3)", getName(), diagramName), new RunnableWithBooleanResult() {
      @Override
      public void run() {
        DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);

        // Copy Layout of elements and GMF contents
        rearrangeDDiagramLayout((DSemanticDiagram) diagram, (DSemanticDiagram) targetDiagram, session, getShell());

        // Restore hidden elements
        restoreHiddenElements((DSemanticDiagram) diagram);

        // Perform a refresh on target diagram
        CapellaServices.getService().forceRefresh((DDiagram) targetDiagram);

        // Finalize diagrams
        finalizeDiagram(diagram, targetDiagram);

        setStatus(Status.OK_STATUS);
      }
    });

    if (!status.isOK()) {
      rollbackDiagram(diagram);
      return status;
    }

    monitor.worked(1);
    monitor.done();

    DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);
    if (targetDiagram == null) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Invalid target diagram");
    }
    return new Status(IStatus.OK, Activator.PLUGIN_ID, NLS.bind("Diagram initialized - {0}", diagramName));
  }

  /**
   * @param diagram
   * @param description
   * @param sourceSemantic
   * @param session
   */
  protected IStatus retrieveTargetDiagram(DRepresentation diagram, Session session, EObject sourceSemantic,
      RepresentationDescription description, IProgressMonitor monitor) {

    IContext context = getContext();
    EObject targetSemantic = null;
    DRepresentation targetDiagram = null;
    RepresentationDescription allocatingDescription = null;

    if (context.exists(TARGET_DIAGRAM)) {
      targetDiagram = (DRepresentation) context.get(TARGET_DIAGRAM);
      context.put(DIAGRAM_CREATION, Boolean.FALSE);

      if (targetDiagram != null) {
        allocatingDescription = DiagramHelper.getService().getDescription(targetDiagram);

        if ((targetDiagram instanceof DSemanticDecorator)) {
          targetSemantic = ((DSemanticDecorator) targetDiagram).getTarget();
        }
      }
    }

    // Retrieve a valid target and the transformed description
    if (allocatingDescription == null) {
      allocatingDescription = DiagramDescriptionHelper.getService(getContext()).getTargetDescription(context, session,
          description);
    }
    if ((allocatingDescription == null) || allocatingDescription.eIsProxy()) {
      return (new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          NLS.bind("Diagram ''{0}'' is not yet supported by diagram initialization", EObjectExt.getText(diagram))));
    }
    context.put(TARGET_DESCRIPTION, allocatingDescription);

    // Retrieve target semantic of diagram
    if (targetSemantic == null) {
      targetSemantic = getTargetSemantic(sourceSemantic, description, allocatingDescription);
    }
    if (targetSemantic == null) {
      return (new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          "No semantic element has been found to put the created diagram"));
    }

    // Create a target diagram
    if (targetDiagram == null) {
      String name = DiagramDescriptionHelper.getService(context).getTargetName(context, diagram, allocatingDescription);
      targetDiagram = DiagramHelper.getService().createDRepresentation(name, targetSemantic, allocatingDescription,
          session, monitor);
      getContext().put(TARGET_DIAGRAM, targetDiagram);
    }

    if (targetDiagram == null) {
      return (new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Cannot create the target diagram"));
    }
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected Shell getShell() {
    return Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
  }

  /**
   * @param diagram
   * @param targetDiagram
   */
  protected void finalizeDiagram(DRepresentation diagram, DRepresentation targetDiagram) {

    HashMapSet<DSemanticDecorator, DSemanticDecorator> views = getTargetViews(getContext());

    HashMapSet<DSemanticDecorator, DSemanticDecorator> hiddens = getHiddenSourceViews(getContext());

    if (targetDiagram != null) {

      if (targetDiagram instanceof DDiagram) {
        if (diagram instanceof DDiagram) {
          ((DDiagram) targetDiagram).setSynchronized(((DDiagram) diagram).isSynchronized());
        }
      }
    }

    views.clear();

    hiddens.clear();
  }

  /**
   * @param diagram
   */
  private IStatus rollbackDiagram(final DRepresentation diagram) {
    // Retrieve the current session
    final Session session = DiagramHelper.getService().getSession(diagram);
    if (session == null) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Cannot retrieve session from the given diagram");
    }

    IStatus status = runCommand(session, NLS.bind("{0} - {1} (2/2)", getName(), EObjectExt.getText(diagram)),
        new RunnableWithBooleanResult() {
          @Override
          public void run() {
            DRepresentation targetDiagram = (DRepresentation) getContext().get(TARGET_DIAGRAM);

            try {
              // Restore hidden elements
              restoreHiddenElements((DSemanticDiagram) diagram);
            } finally {

              try {
                if (!(getContext().exists(DIAGRAM_CREATION)
                    && Boolean.FALSE.equals(getContext().get(DIAGRAM_CREATION)))) {
                  // Delete diagram
                  deleteDiagram(targetDiagram);
                }
              } finally {

                try {
                  // Clean diagram elements
                  finalizeDiagram(diagram, targetDiagram);

                } finally {
                  setStatus(Status.OK_STATUS);
                }
              }
            }

          }
        });

    return status;
  }

  /**
   * @param targetDiagram
   */
  protected void deleteDiagram(DRepresentation targetDiagram) {
    DeleteDRepresentationTask task = new DeleteDRepresentationTask(
        RepresentationHelper.getRepresentationDescriptor(targetDiagram));
    task.execute();
  }

  /**
   * @param diagram
   * @param targetDiagram
   */
  protected IStatus initializeDiagrams(DRepresentation sourceDiagram, DRepresentation targetDiagram) {
    RepresentationDescription sourceDescription = DiagramHelper.getService().getDescription(sourceDiagram);
    RepresentationDescription targetDescription = DiagramHelper.getService().getDescription(targetDiagram);

    if ((sourceDiagram instanceof DDiagram) && (targetDiagram instanceof DDiagram)) {
      DDiagram srcDiagram = (DDiagram) sourceDiagram;
      DDiagram tgtDiagram = (DDiagram) targetDiagram;

      tgtDiagram.setSynchronized(false);

      // Activate all compatible filters between both diagrams
      for (FilterDescription description : srcDiagram.getActivatedFilters()) {
        FilterDescription filterDescription = DiagramDescriptionHelper.getService(getContext())
            .getTargetFilterDescription(getContext(), (DiagramDescription) sourceDescription,
                (DiagramDescription) targetDescription, description);
        if (filterDescription != null) {
          tgtDiagram.getActivatedFilters().add(filterDescription);
        }
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param diagram
   * @param targetDiagram
   */
  protected IStatus synchronizeDiagrams(DRepresentation sourceDiagram, DRepresentation targetDiagram) {

    if ((sourceDiagram instanceof DDiagram) && (targetDiagram instanceof DDiagram)) {
      DDiagramContents sourceContents = new DDiagramContents((DDiagram) sourceDiagram);
      DDiagramContents targetContents = new DDiagramContents((DDiagram) targetDiagram);

      HashMapSet<DSemanticDecorator, DSemanticDecorator> map = getTargetViews(getContext());
      map.put((DSemanticDecorator) sourceContents.getDDiagram(), (DSemanticDecorator) targetContents.getDDiagram());

      DiagramDescription sourceDescription = (DiagramDescription) DiagramHelper.getService()
          .getDescription(sourceDiagram);
      DiagramDescription targetDescription = (DiagramDescription) DiagramHelper.getService()
          .getDescription(targetDiagram);

      // For all nodes
      for (DDiagramElement element : sourceContents.getDiagramElements()) {
        if (element instanceof AbstractDNode) {
          EObject sourceSemantic = element.getTarget();
          EObject semanticSource = sourceSemantic;

          for (EObject semanticTarget : getTargetSemantics(semanticSource, sourceDescription, targetDescription)) {
            EObject targetSemantic = semanticTarget;
            DiagramElementMapping mapping = DiagramDescriptionHelper.getService(getContext()).getTargetMapping(
                getContext(), sourceDescription, targetDescription, element.getDiagramElementMapping(), semanticSource,
                semanticTarget);

            if ((mapping != null) && (mapping instanceof AbstractNodeMapping)) {
              for (DDiagramElement target : getNodes(sourceDescription, mapping, targetContents, targetSemantic,
                  element)) {
                map.put(element, target);
                copyElementView(element, target);
              }
            }
          }

        }
      }

      // For all edges
      for (DDiagramElement element : sourceContents.getDiagramElements()) {
        if (element instanceof DEdge) {
          EObject sourceSemantic = element.getTarget();
          for (EObject targetSemantic : getTargetSemantics(sourceSemantic, sourceDescription, targetDescription)) {
            DiagramElementMapping mapping = DiagramDescriptionHelper.getService(getContext()).getTargetMapping(
                getContext(), sourceDescription, targetDescription, element.getDiagramElementMapping(), sourceSemantic,
                targetSemantic);

            if ((mapping != null) && (mapping instanceof EdgeMapping)) {

              for (DDiagramElement target : getEdges(sourceDescription, mapping, targetContents, targetSemantic,
                  element)) {
                map.put(element, target);
                copyElementView(element, target);
              }
            }
          }
        }
      }

    }

    return Status.OK_STATUS;
  }

  /**
   * @param sourceDescription
   * @param mapping
   * @param targetContents
   * @param targetSemantic
   * @param element
   * @return
   */
  protected Collection<DDiagramElement> getEdges(DiagramDescription sourceDescription, DiagramElementMapping mapping,
      DDiagramContents targetContents, EObject targetSemantic, DDiagramElement element) {
    DEdge edge = (DEdge) element;
    Collection<DDiagramElement> targetViews = new ArrayList<DDiagramElement>();

    HashMapSet<DSemanticDecorator, DSemanticDecorator> map = getTargetViews(getContext());
    Collection<DSemanticDecorator> sourceNodes = map.get(edge.getSourceNode());
    Collection<DSemanticDecorator> targetNodes = map.get(edge.getTargetNode());

    Collection<DDiagramElement> elements = targetContents.getDiagramElements(targetSemantic, mapping);
    for (DDiagramElement target : elements) {
      map.put(element, target);
    }

    for (DSemanticDecorator sourceNode : sourceNodes) {
      if ((sourceNode == null) || !(sourceNode instanceof EdgeTarget)) {
        continue;
      }

      for (DSemanticDecorator targetNode : targetNodes) {
        if ((targetNode == null) || !(targetNode instanceof EdgeTarget)) {
          continue;
        }

        DDiagramElement targetView = DiagramDescriptionHelper.getService(getContext()).showEdge(getContext(),
            sourceDescription, targetContents, (EdgeMapping) mapping, sourceNode, targetNode, targetSemantic);

        if (targetView != null) {
          map.put(element, targetView);
          targetContents.addView(targetView);
          copyElementView(element, targetView);
        }

      }
    }
    return targetViews;
  }

  /**
   * @param mapping
   * @param targetContents
   * @param targetSemantic
   * @return
   */
  protected Collection<DDiagramElement> getNodes(DiagramDescription sourceDescription, DiagramElementMapping mapping,
      DDiagramContents targetContents, EObject targetSemantic, DDiagramElement sourceView) {

    Collection<DDiagramElement> targetViews = new ArrayList<DDiagramElement>();

    HashMapSet<DSemanticDecorator, DSemanticDecorator> map = getTargetViews(getContext());
    Collection<DDiagramElement> elements = targetContents.getDiagramElements(targetSemantic, mapping);
    for (DDiagramElement target : elements) {
      map.put(sourceView, target);
      targetViews.add(target);
    }

    Collection<DSemanticDecorator> containerNodes = map.get(sourceView.eContainer());
    for (DSemanticDecorator containerNode : containerNodes) {
      if (containerNode == null) {
        continue;
      }

      DSemanticDecorator targetView = DiagramDescriptionHelper.getService(getContext()).showNode(getContext(),
          sourceDescription, targetContents, (AbstractNodeMapping) mapping, containerNode, targetSemantic);

      if (targetView == null) {
        if (targetSemantic instanceof Part) {
          AbstractType targetSemanticComponent = ((Part) targetSemantic).getAbstractType();
          if (targetSemanticComponent instanceof Component) {
            BlockArchitecture blockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(targetSemantic);
            Component systemComponent = blockArchitecture.getSystem();

            if (targetSemanticComponent.equals(systemComponent)) {
              targetView = ((DSemanticDecorator) targetContents.getDDiagram());
            }
          }
        }
      }
      if (targetView != null) {
        map.put(sourceView, targetView);
        if (targetView instanceof DDiagramElement) {
          targetContents.addView((DDiagramElement) targetView);
          copyElementView(sourceView, (DDiagramElement) targetView);
        }
      }

    }
    return targetViews;
  }

  /**
   * @param element
   * @param targetView
   */
  private void copyElementView(DDiagramElement element, DDiagramElement targetView) {
    DiagramElementMapping mapping = targetView.getDiagramElementMapping();

    ShapeUtil.copyCustomStyle(element, targetView);

    if (targetView instanceof AbstractDNode) {
      AbstractDNode targetNode = (AbstractDNode) targetView;
      if (mapping instanceof ContainerMapping) {
        targetNode.getStyle().setDescription(((ContainerMapping) mapping).getStyle());
      }
      if (mapping instanceof NodeMapping) {
        targetNode.getStyle().setDescription(((NodeMapping) mapping).getStyle());
      }

      for (ArrangeConstraint constraint : ((AbstractDNode) element).getArrangeConstraints()) {
        targetNode.getArrangeConstraints().add(constraint);
      }

    } else if (targetView instanceof DEdge) {
      DEdge targetEdge = (DEdge) targetView;
      if (mapping instanceof EdgeMapping) {
        targetEdge.getStyle().setDescription(((EdgeMapping) mapping).getStyle());
      }

      for (ArrangeConstraint constraint : ((DEdge) element).getArrangeConstraints()) {
        targetEdge.getArrangeConstraints().add(constraint);
      }
    }

    if (element.getTooltipText() != null) {
      targetView.setTooltipText(element.getTooltipText());
    }
  }

  HashMap<Diagram, DiagramEditPart> diagramsGMF = new HashMap<Diagram, DiagramEditPart>();

  /**
   * Create edit parts for the given GMF diagram
   * 
   * @param diagram
   * @param shell
   * @return
   */
  DiagramEditPart loadEditParts(Diagram diagram, Shell shell) {
    DiagramEditPart part = diagramsGMF.get(diagram);
    if (part == null) {
      part = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
      diagramsGMF.put(diagram, part);
    }
    return diagramsGMF.get(diagram);
  }

  /** GMF Helper to make life easier. */
  ExtendedViewRefactorHelper helperViewer = new ExtendedViewRefactorHelper();

  class ExtendedViewRefactorHelper extends ViewRefactorHelper {

    @Override
    public Diagram createDiagram(Diagram oldDiagram, EObject newElement) {
      return super.createDiagram(oldDiagram, newElement);
    }

    /*
     * This method is overridden to use Sirius's EObjectQuery.getInverseReferences instead of EMFCoreUtil.getReferencers
     * since the latter initializes GMF's CrossReferenceAdapter which creates bugs when the session is closed (see Bug
     * 1754)
     */
    @Override
    public Collection getReferencingViews(EObject element) {
      Collection<EObject> views = new EObjectQuery(element)
          .getInverseReferences(NotationPackage.eINSTANCE.getView_Element());

      // remove subviews since they will be refactored with their parent
      for (Iterator i = views.iterator(); i.hasNext();) {
        View view = (View) i.next();

        EObject parent = null;
        while ((parent = view.eContainer()) instanceof View) {
          if (views.contains(parent)) {
            i.remove();
            break;
          }
          view = (View) parent;
        }
      }
      return views;
    }

    @Override
    public void copyViewChildren(View oldView, View newView) {
      super.copyViewChildren(oldView, newView);
    }

    @Override
    public void copyViewStyle(View oldView, View newView, Style oldStyle, List excludeStyles) {
      super.copyViewStyle(oldView, newView, oldStyle, excludeStyles);
    }

  }

  /**
   * Load GMF diagram and perform a sync if necessary
   * 
   * @param diagram
   * @param session
   * @param sync
   * @return
   */
  Diagram loadGMF(DSemanticDiagram diagram, Session session, boolean sync) {
    Collection<?> sourceDiagrams = helperViewer.getReferencingViews(diagram);
    if (!sourceDiagrams.isEmpty()) {
      // Initialize GMF diagram
      Diagram diagramGMF = (Diagram) sourceDiagrams.iterator().next();
      if (sync) {
        CanonicalSynchronizer sourceSynchronizer = CanonicalSynchronizerFactory.INSTANCE
            .createCanonicalSynchronizer(diagramGMF);
        Command sourceSynchronizerCommand = new SynchronizeGMFModelCommand(session.getTransactionalEditingDomain(),
            sourceSynchronizer);
        sourceSynchronizerCommand.execute();
      }

      return diagramGMF;
    }
    return null;
  }

  protected void revealHiddenElements(DSemanticDiagram sourceDiagram, DSemanticDiagram targetDiagram) {
    HashMapSet<DSemanticDecorator, DSemanticDecorator> views = getTargetViews(getContext());

    // Reveal all hidden elements
    HashMapSet<DSemanticDecorator, DSemanticDecorator> hiddens = getHiddenSourceViews(getContext());
    for (DSemanticDecorator sourceView : views.keySet()) {
      if (sourceView instanceof DDiagramElement) {
        if (new DDiagramElementQuery((DDiagramElement) sourceView).isHidden()) {
          hiddens.put(sourceView, sourceView);
          CapellaServices.getService().show((DDiagramElement) sourceView);
        }
      }
    }
  }

  protected void restoreHiddenElements(DSemanticDiagram sourceDiagram) {
    HashMapSet<DSemanticDecorator, DSemanticDecorator> views = getTargetViews(getContext());

    // Hide all hidden elements
    HashMapSet<DSemanticDecorator, DSemanticDecorator> hiddens = getHiddenSourceViews(getContext());
    for (DSemanticDecorator sourceView : hiddens.keySet()) {
      if (sourceView instanceof DDiagramElement) {
        CapellaServices.getService().hide((DDiagramElement) sourceView);
      }
    }

    for (DSemanticDecorator sourceView : views.keySet()) {
      if (sourceView instanceof DDiagramElement) {
        if (DiagramServices.getDiagramServices().isHidden((DDiagramElement) sourceView)) {
          for (DSemanticDecorator targetView : views.get(sourceView)) {
            if (targetView instanceof DDiagramElement) {
              DiagramServices.getDiagramServices().hide((DDiagramElement) targetView);
            }
          }
        }
        if (DiagramServices.getDiagramServices().isHiddenLabel((DDiagramElement) sourceView)) {
          for (DSemanticDecorator targetView : views.get(sourceView)) {
            if (targetView instanceof DDiagramElement) {
              DiagramServices.getDiagramServices().hideLabel((DDiagramElement) targetView);
            }
          }
        }
      }
    }

  }

  protected void rearrangeDDiagramLayout(DSemanticDiagram sourceDiagram, DSemanticDiagram targetDiagram,
      Session session, Shell shell) {

    HashMapSet<DSemanticDecorator, DSemanticDecorator> views = getTargetViews(getContext());

    // For each view, if multiple view have been created, we move them with PADDING to avoid multiple views at the same
    // location
    for (DSemanticDecorator sourceView : views.keySet()) {
      Collection<DSemanticDecorator> targetViews = views.get(sourceView);
      if (targetViews.size() > 1) {

        int padding = 0;
        Iterator<DSemanticDecorator> targetViewsIterator = targetViews.iterator();
        while (targetViewsIterator.hasNext()) {
          DSemanticDecorator targetView = targetViewsIterator.next();

          Collection<?> sourceDiagrams = helperViewer.getReferencingViews(targetView);
          if (!sourceDiagrams.isEmpty()) {
            for (Object view : sourceDiagrams) {

              // Add padding on Nodes
              if (view instanceof Node) {
                Node sview = (Node) view;
                LayoutConstraint targetConstraint = (sview.getLayoutConstraint());
                if (targetConstraint != null) {
                  if (targetConstraint instanceof Location) {
                    Location targetLocation = (Location) targetConstraint;
                    targetLocation.setX(targetLocation.getX() + padding);
                    targetLocation.setY(targetLocation.getY() + padding);
                  }
                }
              }

              // Add padding on Edges bendpoints
              if (view instanceof Edge) {
                Edge sview = (Edge) view;
                Bendpoints bendpoints = sview.getBendpoints();
                if ((bendpoints != null) && (bendpoints instanceof RelativeBendpoints)) {
                  RelativeBendpoints bps = (RelativeBendpoints) bendpoints;

                  List<RelativeBendpoint> cl = new ArrayList<RelativeBendpoint>();
                  for (Object point : bps.getPoints()) {
                    if (point instanceof RelativeBendpoint) {
                      RelativeBendpoint bp = (RelativeBendpoint) point;
                      RelativeBendpoint bpE = new RelativeBendpoint(bp.getSourceX() + padding,
                          bp.getSourceY() + padding, bp.getTargetX() + padding, bp.getTargetY() + padding);
                      cl.add(bpE);
                    }
                  }

                  bps.setPoints(cl);
                }
              }
            }
          }
          padding += SiriusLayoutDataManager.PADDING;
        }
      }
    }

  }

  protected void copyDDiagramLayout(DSemanticDiagram sourceDiagram, DSemanticDiagram targetDiagram, Session session,
      Shell shell) {

    Diagram srcDiagram = loadGMF(sourceDiagram, session, false);
    Diagram tgtDiagram = loadGMF(targetDiagram, session, true);

    final DiagramEditPart sourceEditPart = loadEditParts(srcDiagram, shell);
    final DiagramEditPart targetEditPart = loadEditParts(tgtDiagram, shell);

    // Store layout of source diagram
    List<SiriusFormatDataManager> mgrs = FormatDataManagerRegistry.getSiriusFormatDataManagers(sourceDiagram);
    if (!mgrs.isEmpty()) {
      try {
        mgrs.iterator().next().storeFormatData(sourceEditPart);
      } catch (Exception e) {
        // Nothing. if we are not able to store a layout, it's ok.
      }
    }

    // Restore layout into target diagram
    mgrs = FormatDataManagerRegistry.getSiriusFormatDataManagers(targetDiagram);
    if (!mgrs.isEmpty()) {
      try {
        mgrs.iterator().next().applyLayout(targetEditPart);
      } catch (Exception e) {
        // Nothing. if we are not able to apply a layout, it's ok.
      }
    }

    // Clear layout of source diagram
    mgrs = FormatDataManagerRegistry.getSiriusFormatDataManagers(sourceDiagram);
    if (!mgrs.isEmpty()) {
      try {
        mgrs.iterator().next().clearFormatData();
      } catch (Exception e) {
        // Nothing. if we are not able to store a layout, it's ok.
      }
    }

    // --- Sirius SHARED DATA : Clean to avoid ArrangeLayout on diagram opening ---//
    cleanArrangeLayout(targetDiagram);

    copyNotes(srcDiagram, tgtDiagram, sourceEditPart, targetEditPart);
  }

  /**
   * This method should not exists. Prevent an ArrangeLayout on diagram opening by removing some shared informations
   * stored
   */
  protected void cleanArrangeLayout(DSemanticDiagram targetDiagram) {

    // Perform an empty arrange layout on target diagram.
    new DiagramDialectArrangeOperation().arrange(null, targetDiagram);

    // Clean ArrangeLayout shared map to avoid ArrageLayout on opening
    SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout().clear();
  }

  /**
   * Copy GMF notes from source diagram to target diagram
   * 
   * @param sourceDiagram
   * @param targetDiagram
   */
  void copyNotes(Diagram sourceDiagram, Diagram targetDiagram, DiagramEditPart sourceEditPart,
      DiagramEditPart targetEditPart) {
    LinkedList<EObject> childs = new LinkedList<EObject>();
    if (sourceDiagram.getChildren().size() > 0) {
      childs.addAll(sourceDiagram.getChildren());
    }

    while (childs.size() > 0) {
      Object child = childs.removeFirst();
      if (child instanceof View) {
        View viewChild = (View) child;
        if (DiagramNotationType.NOTE.getSemanticHint().equals(viewChild.getType())) {
          CreateViewRequest request = CreateViewRequestFactory.getCreateShapeRequest(DiagramNotationType.NOTE,
              DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT);
          if ((request != null) && request.getViewDescriptors().iterator().hasNext()) {
            targetEditPart.getCommand(request).execute();
            ViewDescriptor descriptor = request.getViewDescriptors().iterator().next();
            View view = (View) descriptor.getAdapter(View.class);
            helperViewer.copyViewChildren(viewChild, view);
            helperViewer.copyViewStyle(viewChild, view, view.getStyle(NotationPackage.Literals.SHAPE_STYLE),
                Collections.emptyList());
            helperViewer.copyViewAppearance(viewChild, view, Collections.emptyList());

            if ((viewChild instanceof Node) && (view instanceof Node)) {
              Node sourceNode = (Node) viewChild;
              Node targetNode = (Node) view;
              Style style = sourceNode.getStyle(NotationPackage.Literals.SHAPE_STYLE);

              if ((style != null) && (style instanceof ShapeStyle)) {
                ShapeStyle shapeStyle = (ShapeStyle) style;
                // Copy old gmf note styles
                if (view instanceof Shape) {
                  Shape targetShape = (Shape) view;
                  targetShape.setDescription(shapeStyle.getDescription());
                  targetShape.setFillColor(shapeStyle.getFillColor());
                  targetShape.setFontColor(shapeStyle.getFontColor());
                  targetShape.setFontHeight(shapeStyle.getFontHeight());
                  targetShape.setLineColor(shapeStyle.getLineColor());
                  targetShape.setLineWidth(shapeStyle.getLineWidth());
                  targetShape.setRoundedBendpointsRadius(shapeStyle.getRoundedBendpointsRadius());
                  targetShape.setTransparency(shapeStyle.getTransparency());
                }
              }

              if ((sourceNode.getLayoutConstraint() != null) && (targetNode.getLayoutConstraint() != null)) {
                LayoutConstraint sourceConstraint = (sourceNode.getLayoutConstraint());
                LayoutConstraint targetConstraint = (targetNode.getLayoutConstraint());

                if ((sourceConstraint instanceof Location) && (targetConstraint instanceof Location)) {
                  Location sourceLocation = (Location) sourceConstraint;
                  Location targetLocation = (Location) targetConstraint;
                  targetLocation.setX(sourceLocation.getX());
                  targetLocation.setY(sourceLocation.getY());
                }
                if ((sourceConstraint instanceof Size) && (targetConstraint instanceof Size)) {
                  Size sourceSize = (Size) sourceConstraint;
                  Size targetSize = (Size) targetConstraint;
                  targetSize.setHeight(sourceSize.getHeight());
                  targetSize.setWidth(sourceSize.getWidth());
                }
                if ((sourceConstraint instanceof Ratio) && (targetConstraint instanceof Ratio)) {
                  Ratio sourceRatio = (Ratio) sourceConstraint;
                  Ratio targetRatio = (Ratio) targetConstraint;
                  targetRatio.setValue(sourceRatio.getValue());
                }
              }
            }
          }

        }
      }
    }
  }

  /**
   * @param sourceSemantic
   * @param allocatingDescription
   * @return
   */
  protected EObject getTargetSemantic(EObject sourceSemantic, RepresentationDescription sourceDescription,
      RepresentationDescription allocatingDescription) {
    if (sourceSemantic instanceof TraceableElement) {
      EObject target = DiagramDescriptionHelper.getService(getContext()).getTargetSemantic(getContext(), sourceSemantic,
          sourceDescription, allocatingDescription);
      if (target != null) {
        return target;
      }
    }
    return null;
  }

  /**
   * @param sourceSemantic
   * @param allocatingDescription
   * @return
   */
  protected Collection<EObject> getTargetSemantics(EObject sourceSemantic, RepresentationDescription sourceDescription,
      RepresentationDescription targetDescription) {
    if (sourceSemantic instanceof TraceableElement) {
      Collection<EObject> target = DiagramDescriptionHelper.getService(getContext()).getTargetSemantics(getContext(),
          sourceSemantic, sourceDescription, targetDescription);
      if (!target.isEmpty()) {
        return target;
      }
    }
    return Collections.emptyList();
  }
}
