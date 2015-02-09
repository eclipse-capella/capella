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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.Collections;

import org.apache.log4j.Level;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry.IMarkerModification;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 * A Log4J Appender that creates Eclipse IMarkers.
 */
public final class ReportManagerLogViewAppender extends WriterAppender {

  private IResource workspaceRoot;

  /**
   * Constructor.
   */
  public ReportManagerLogViewAppender() {
    this.setName(ReportManagerConstants.LOG_OUTPUT_PROBLEMS_VIEW);
    workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(ResourcesPlugin.getWorkspace().getRoot().getFullPath().toString()));
  }

  /**
   * @see org.apache.log4j.WriterAppender#append(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  public void append(LoggingEvent event_p) {
    Level level = event_p.getLevel();

    Object message = event_p.getMessage();
    if (message instanceof EmbeddedMessage) {
      report((EmbeddedMessage) message, level);
    } else if (message != null) {
      report(message.toString(), level);
    } else {
      ThrowableInformation information = event_p.getThrowableInformation();
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

  private void report(EmbeddedMessage message_p, Level level_p) {
    int severity = log4jToDiagnostics(level_p);
    LightMarkerRegistry.getInstance().createMarker(workspaceRoot, 
        new BasicDiagnostic(severity, message_p.getSource(), 0, message_p.getLabel(), message_p.getCapellaElements().toArray()));
  }

  /**
   * @param event_p
   */
  protected void report(final LoggingEvent event_p) {
    final EmbeddedMessage em = (EmbeddedMessage) event_p.getMessage();
    int severity = log4jToDiagnostics(event_p.getLevel());

    IResource resource = workspaceRoot;
    if ((em.getCapellaElements() != null) && (em.getCapellaElements().size() > 0)) {
      Session session = SessionManager.INSTANCE.getSession((EObject) em.getCapellaElements().iterator().next());
      if (session != null) {
        resource = EcoreUtil2.getFile(session.getSessionResource());
      }
    }

    LightMarkerRegistry.getInstance().createMarker(resource,
        new BasicDiagnostic(severity, em.getComponentName(), 0, em.getLabel(), em.getCapellaElements().toArray()), MarkerView.MARKER_ID, new IMarkerModification() {
          public void modify(IMarker marker_p) {
            try {
              marker_p.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
              marker_p.setAttribute(IMarker.LOCATION, em.getComponentName());
              marker_p.setAttribute(MarkerViewUtil.NAME_ATTRIBUTE, event_p.getLoggerName());
              
            } catch (CoreException e) {
              MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
            }
          }
        });
  }

  public void report(final String message, final Level level_p, final IMarkerModification additions) {
    int severity = log4jToDiagnostics(level_p);

    LightMarkerRegistry.getInstance().createMarker(workspaceRoot, new BasicDiagnostic(severity, "", 0, message, Collections.emptyList().toArray()), MarkerView.MARKER_ID, new IMarkerModification() {
      public void modify(IMarker marker_p) {
        try {
          marker_p.setAttribute(IMarker.MESSAGE, message);
          marker_p.setAttribute(IMarker.SEVERITY, level_p); // violates IMarker API
          marker_p.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
        } catch (CoreException e) {
          MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
        }
        if (additions != null) {
          additions.modify(marker_p);
        }
      }
    });
  }

  /**
   * @param msg
   * @param level_p
   */
  public void report(final String message, final Level level_p) {
    report(message, level_p, null);
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
