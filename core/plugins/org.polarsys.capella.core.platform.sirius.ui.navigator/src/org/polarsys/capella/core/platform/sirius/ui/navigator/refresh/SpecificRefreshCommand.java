/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.refresh;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.business.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A command which refresh a selection of {@link DRepresentation}s of the given {@link Session}. For modified {@link DDiagram}, it will synchronize gmf models
 * and layout created views.
 */
public final class SpecificRefreshCommand extends RecordingCommand {

  protected final Session session;

  private final Collection<DRepresentation> representationsToRefresh;

  protected Collection<Object> impactedElements = new LinkedHashSet<>();

  private Collection<DRepresentation> modifiedRepresentations = new LinkedHashSet<>();

  private ModificationListener modificationListener;

  private IProgressMonitor monitor;

  private Display display;

  /**
   * Constructor.
   * @param session the session which need the refresh.
   * @param representationsToRefresh the representations to refresh.
   * @param monitor
   * @param display
   */
  public SpecificRefreshCommand(Session session, Collection<DRepresentation> representationsToRefresh, IProgressMonitor monitor, Display display) {
    super(session.getTransactionalEditingDomain(), Messages.SpecificRefreshCommand_0);
    this.session = session;
    this.representationsToRefresh = representationsToRefresh;
    this.modificationListener = new ModificationListener();
    this.monitor = monitor;
    this.display = display;
  }

  private void reInitImpactedElements() {
    impactedElements.clear();
    impactedElements = new LinkedHashSet<>();
  }
  
  /**
   * Create and execute a {@link SpecificRefreshCommand}. Refresh a selection of {@link DRepresentation}s of the given {@link Session}. Synchronize gmf models
   * for modified {@link DDiagram} and layout created views.
   * @param session
   * @param representationsToRefresh
   * @param monitor
   * @param display
   * @return the modified representations.
   */
  public static Collection<DRepresentation> refreshRepresentations(final Session session, Collection<DRepresentation> representationsToRefresh,
      IProgressMonitor monitor, Display display) {
    final Collection<DRepresentation> modifiedRepresentations = new LinkedHashSet<>();
    final SpecificRefreshCommand refreshCommand = new SpecificRefreshCommand(session, representationsToRefresh, monitor, display);
    if (session != null) {
      session.getTransactionalEditingDomain().getCommandStack().execute(refreshCommand);
      modifiedRepresentations.addAll(refreshCommand.getModifiedRepresentations());
    }

    return modifiedRepresentations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doExecute() {
    for (DRepresentation representation : representationsToRefresh) {
      if (DialectManager.INSTANCE.canRefresh(representation)) {
        modificationListener.activate(representation);
        // Initialize the synchronizer of GMF model
        SiriusDiagramSessionEventBroker.getInstance(session);
        DialectManager.INSTANCE.refresh(representation, monitor);
        modificationListener.deactivate(representation);
      }
    }
    populateModifiedRepresentations();
    // mandatory threadUI
    display.asyncExec(new Runnable() {
      @Override
      public void run() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
          @SuppressWarnings("synthetic-access")
          @Override
          protected void doExecute() {
            synchronizeAndLayoutGMFModels();
          }
        });
      }
    });
  }

  /**
   * Returns modified {@link DRepresentation}
   */
  @Override
  public Collection<?> getAffectedObjects() {
    return getModifiedRepresentations();
  }

  /**
   * Returns modified {@link DRepresentation}
   */
  public Collection<DRepresentation> getModifiedRepresentations() {
    return Collections.unmodifiableCollection(modifiedRepresentations);
  }

  /**
   * Returns modified {@link DRepresentation}
   */
  @Override
  public Collection<?> getResult() {
    return getModifiedRepresentations();
  }

  private void populateModifiedRepresentations() {
    for (Object obj : impactedElements) {
      if (obj instanceof EObject) {
        EObject eobject = (EObject) obj;
        DRepresentation rep = getModifiedRepresentation(eobject);
        if (rep != null) {
          modifiedRepresentations.add(rep);
        }
      }
    }
    reInitImpactedElements();
  }

  private DRepresentation getModifiedRepresentation(EObject eobject) {
    DRepresentation rep = null;
    if (eobject instanceof DRepresentation) {
      rep = (DRepresentation) eobject;
    } else {
      EObjectQuery query = new EObjectQuery(eobject);
      Option<EObject> firstAncestorOfType = query.getFirstAncestorOfType(ViewpointPackage.eINSTANCE.getDRepresentation());
      if (firstAncestorOfType.some() && (firstAncestorOfType.get() instanceof DRepresentation)) {
        rep = (DRepresentation) firstAncestorOfType.get();

      }
    }
    return rep;
  }

  private void synchronizeAndLayoutGMFModels() {
    Collection<Diagram> diagsToRefreshLayout = new LinkedHashSet<>();
    for (DRepresentation rep : modifiedRepresentations) {
      if (rep instanceof DDiagram) {
        DDiagram diag = (DDiagram) rep;
        Diagram gmfDiagram = SiriusGMFHelper.getGmfDiagram(diag, session);
        diagsToRefreshLayout.add(gmfDiagram);
      }
    }

    layoutModifiedGMFDiagrams(diagsToRefreshLayout);
  }

  private void layoutModifiedGMFDiagrams(Collection<Diagram> diagsToRefreshLayout) {
    final Shell shell = new Shell();
    DiagramEditPartService service = new DiagramEditPartService();
    for (Diagram gmfDiagram : diagsToRefreshLayout) {

      final DiagramEditPart diagramEditPart = service.createDiagramEditPart(gmfDiagram, shell, PreferencesHint.USE_DEFAULTS);
      if (diagramEditPart != null) {
        service.arrangeCreatedViews(diagramEditPart);

        // dispose ui resources.
        diagramEditPart.deactivate();
      }
    }

    display.asyncExec(new Runnable() {
      @Override
      public void run() {
        shell.dispose();
      }
    });
  }

  /**
   * Adapter to detected modified {@link DRepresentation}.
   */
  private final class ModificationListener extends EContentAdapter {

    private boolean activated;

    /**
     * Constructor.
     */
    public ModificationListener() {
      super();
    }

    /**
     * Activate this listener.
     */
    public void activate(DRepresentation representation) {
      if (representation != null) {
        representation.eAdapters().add(this);
        activated = true;
      }
    }

    /**
     * Deactivate this listener.
     */
    public void deactivate(DRepresentation representation) {
      activated = false;
      if (representation != null) {
        representation.eAdapters().remove(this);
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(final Notification notification) {
      if (activated && !notification.isTouch()) {
        switch (notification.getEventType()) {
          case Notification.SET:
          case Notification.ADD:
          case Notification.ADD_MANY:
            impactedElements.add(notification.getNotifier());
          break;
          case Notification.MOVE:
          case Notification.UNSET:
          case Notification.REMOVE:
          case Notification.REMOVE_MANY:
            impactedElements.add(notification.getNotifier());
          break;
          default:
          break;
        }
      }
      super.notifyChanged(notification);
    }
  }
}
