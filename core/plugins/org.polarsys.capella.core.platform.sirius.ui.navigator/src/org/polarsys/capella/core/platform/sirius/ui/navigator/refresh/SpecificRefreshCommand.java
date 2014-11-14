/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.business.api.view.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.view.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A command which refresh a selection of {@link DRepresentation}s of the given {@link Session}. For modified {@link DDiagram}, it will synchronize gmf models
 * and layout created views.
 */
public final class SpecificRefreshCommand extends RecordingCommand {

  protected final Session _session;

  private final Collection<DRepresentation> _representationsToRefresh;

  protected Collection<Object> impactedElements = new LinkedHashSet<Object>();

  private Collection<DRepresentation> modifiedRepresentations = new LinkedHashSet<DRepresentation>();

  private ModificationListener modificationListener;

  private IProgressMonitor _monitor;

  private Display _display;

  private void reInitImpactedElements() {
    impactedElements.clear();
    impactedElements = new LinkedHashSet<Object>();
  }

  /**
   * Constructor.
   * @param session_p the session which need the refresh.
   * @param representationsToRefresh_p the representations to refresh.
   * @param monitor_p
   * @param display_p
   */
  public SpecificRefreshCommand(Session session_p, Collection<DRepresentation> representationsToRefresh_p, IProgressMonitor monitor_p, Display display_p) {
    super(session_p.getTransactionalEditingDomain(), Messages.SpecificRefreshCommand_0);
    _session = session_p;
    _representationsToRefresh = representationsToRefresh_p;
    this.modificationListener = new ModificationListener();
    _monitor = monitor_p;
    _display = display_p;
  }

  /**
   * Create and execute a {@link SpecificRefreshCommand}. Refresh a selection of {@link DRepresentation}s of the given {@link Session}. Synchronize gmf models
   * for modified {@link DDiagram} and layout created views.
   * @param session_p
   * @param representationsToRefresh_p
   * @param monitor_p
   * @param _display
   * @return the modified representations.
   */
  public static Collection<DRepresentation> refreshRepresentations(final Session session_p, Collection<DRepresentation> representationsToRefresh_p,
      IProgressMonitor monitor_p, Display display_p) {
    final Collection<DRepresentation> modifiedRepresentations = new LinkedHashSet<DRepresentation>();
    final SpecificRefreshCommand refreshCommand = new SpecificRefreshCommand(session_p, representationsToRefresh_p, monitor_p, display_p);
    if (session_p != null) {
      session_p.getTransactionalEditingDomain().getCommandStack().execute(refreshCommand);
      modifiedRepresentations.addAll(refreshCommand.getModifiedRepresentations());
    }

    return modifiedRepresentations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doExecute() {
    for (DRepresentation representation : _representationsToRefresh) {
      if (DialectManager.INSTANCE.canRefresh(representation)) {
        modificationListener.activate(representation);
        DialectManager.INSTANCE.refresh(representation, _monitor);
        modificationListener.deactivate(representation);
      }
    }
    populateModifiedRepresentations();
    // mandatory threadUI
    _display.asyncExec(new Runnable() {
      @Override
      public void run() {
        _session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(_session.getTransactionalEditingDomain()) {
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
    Collection<Diagram> diagsToRefreshLayout = new LinkedHashSet<Diagram>();
    for (DRepresentation rep : modifiedRepresentations) {
      if (rep instanceof DDiagram) {
        DDiagram diag = (DDiagram) rep;
        Diagram gmfDiagram = SiriusGMFHelper.getGmfDiagram(diag, _session);
        CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(gmfDiagram);
        canonicalSynchronizer.synchronize();
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

    _display.asyncExec(new Runnable() {
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
