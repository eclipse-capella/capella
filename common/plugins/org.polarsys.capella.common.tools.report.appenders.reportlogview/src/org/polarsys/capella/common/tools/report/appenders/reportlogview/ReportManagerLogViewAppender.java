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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry.IMarkerModification;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * A Log4J Appender that creates Eclipse IMarkers.
 */
public final class ReportManagerLogViewAppender extends WriterAppender {

  private static final String UNKNOWN = "Unknown"; //$NON-NLS-1$

  private final IResource workspaceRoot;

  /**
   * Constructor.
   */
  public ReportManagerLogViewAppender() {
    this.setName(ReportManagerConstants.LOG_OUTPUT_PROBLEMS_VIEW);
    workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
  }

  /**
   * @see org.apache.log4j.WriterAppender#append(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  public void append(LoggingEvent event) {
    Level level = event.getLevel();

    Object message = event.getMessage();
    if (message instanceof EmbeddedMessage) {
      report((EmbeddedMessage) message, level);
    } else if (message instanceof Diagnostic) {
      report((Diagnostic) message);
    } else if (message != null) {
      report(event.getRenderedMessage(), level);
      
    } else {
      ThrowableInformation information = event.getThrowableInformation();
      if (information != null) {
        Throwable thr = information.getThrowable();
        String throwableMessage = thr.getMessage();
        if (throwableMessage != null) {
          report(throwableMessage, level);
        } else {
          // we don't want a stack trace in the information view, but we don't want
          // to lose the exception either. a separate appender should log such messages
          // to the error log with full stack trace information.
          report(thr.getClass().getName(), level);
        }
      }
    }
  }

  // Resolve marker's IResource (.aird file) and fall back to
  // the workspace root if unable
  private IResource getMarkerResource(List<?> objects) {
    if (objects != null && !objects.isEmpty()) {
      return getSessionResource(objects.get(0));
    }
    return workspaceRoot;
  }

  private void report(EmbeddedMessage message, Level level) {
    int severity = log4jToDiagnostics(level);
    IResource markerResource = getMarkerResource(message.getCapellaElements());
    LightMarkerRegistry.getInstance().createMarker(markerResource, 
        new BasicDiagnostic(severity, message.getSource(), 0, message.getLabel(), message.getCapellaElements().toArray()));
  }

  private void report(Diagnostic message) {
    IResource markerResource = getMarkerResource(message.getData());
    LightMarkerRegistry.getInstance().createMarker(markerResource, message);
  }

  /**
   * Get Sirius Session .aird file from a given EObject/Resource.
   * @param obj
   * @return
   */
  public static IFile getSessionResource(Object obj) {
    // Find Session
    Session session = null;
    if (obj instanceof EObject) {
      session = SessionManager.INSTANCE.getSession((EObject) obj);
    } else if (obj instanceof Resource) {
      session = SessionManager.INSTANCE.getSession((Resource) obj);
    }
    // Get .aird file
    if (session != null) {
      return WorkspaceSynchronizer.getFile(session.getSessionResource());
    }
    return null;
  }

  /**
   * @param event
   */
  protected void report(final LoggingEvent event) {
    final EmbeddedMessage em = (EmbeddedMessage) event.getMessage();
    int severity = log4jToDiagnostics(event.getLevel());

		IResource resource = workspaceRoot;
		if ((em.getCapellaElements() != null) && (em.getCapellaElements().size() > 0)) {
			Session session = SessionManager.INSTANCE.getSession((EObject) em.getCapellaElements().iterator().next());
			if (session != null) {
				resource = EcoreUtil2.getFile(session.getSessionResource());
			}
		}

		final Diagnostic diag = new BasicDiagnostic(severity, em.getComponentName(), 0, em.getLabel(), em.getCapellaElements().toArray());
		LightMarkerRegistry.getInstance().createMarker(resource, diag, MarkerView.MARKER_ID, new IMarkerModification() {
			@Override
		  public void modify(IMarker marker) {
				try {
					marker.setAttribute(IMarker.MESSAGE, em.getLabel());
					marker.setAttribute(IMarker.SEVERITY, event.getLevel()); // violates IMarker API
					marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
					marker.setAttribute(IMarker.LOCATION, em.getComponentName());
					marker.setAttribute(MarkerViewUtil.NAME_ATTRIBUTE, event.getLoggerName());
					if ((em.getCapellaElements() != null) && (em.getCapellaElements().size() > 0)) {
						Object element_o = em.getCapellaElements().get(0);
						if (element_o instanceof ModelElement) {
							ModelElement element = (ModelElement) element_o;
							marker.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, element.getFullLabel());
						} else {
							marker.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, UNKNOWN);
						}
					}
				} catch (CoreException e) {
					MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			}
		});
	}

  public void report(final String message, final Level level, final IMarkerModification additions) {
    int severity = log4jToDiagnostics(level);

    LightMarkerRegistry.getInstance().createMarker(workspaceRoot, new BasicDiagnostic(severity, "", 0, message, Collections.emptyList().toArray()), MarkerView.MARKER_ID, new IMarkerModification() {
      @Override
      public void modify(IMarker marker) {
        try {
          marker.setAttribute(IMarker.MESSAGE, message);
          marker.setAttribute(IMarker.SEVERITY, level); // violates IMarker API
          marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
        } catch (CoreException e) {
          MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
        }
        if (additions != null) {
          additions.modify(marker);
        }
      }
    });
  }

  /**
   * @param msg
   * @param level
   */
  public void report(final String message, final Level level) {
    report(message, level, null);
  }


  private int log4jToDiagnostics(Level level){
    int severity = Diagnostic.INFO;
    if (level == Level.WARN){
      severity = Diagnostic.WARNING;
    } else if (level == Level.ERROR || level == Level.FATAL){
      severity = Diagnostic.ERROR;
    }
    return severity;
  }

}
