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
import org.eclipse.ui.views.markers.MarkerViewUtil;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry.IMarkerModification;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * A Log4J Appender that creates Eclipse IMarkers.
 */
public class ReportManagerLogViewAppender extends WriterAppender {

  private int numberMarker;

  private static final String UNKNOWN = "Unknown"; //$NON-NLS-1$
  private IResource workspaceRoot;

  /**
   * Constructor.
   */
  public ReportManagerLogViewAppender() {
    this.setName(ReportManagerConstants.LOG_OUTPUT_PROBLEMS_VIEW);
    workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(ResourcesPlugin.getWorkspace().getRoot().getFullPath().toString()));
    numberMarker = 0;
  }

  /**
   * @see org.apache.log4j.WriterAppender#append(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  public void append(LoggingEvent event_p) {
    Level level = event_p.getLevel();
    Object message = event_p.getMessage();
    if (message instanceof EmbeddedMessage) {
      report(event_p);
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

  /**
   * @param message_p
   * @return The model element
   */
  protected ModelElement getImpactedElement(EmbeddedMessage message_p) {
    if (message_p != null) {
      for (Object iterable_element : message_p.getCapellaElements()) {
        if (iterable_element instanceof ModelElement) {
          return (ModelElement) iterable_element;
        }
      }
    }
    return null;
  }

  /**
   * @param event_p
   */
  protected void report(final LoggingEvent event_p) {
    final EmbeddedMessage em = (EmbeddedMessage) event_p.getMessage();
    report(em.getLabel(), event_p.getLevel(), new IMarkerModification() {
      public void modify(IMarker marker_p) {
        em.adapt(marker_p);
        try {
          marker_p.setAttribute(IMarker.LOCATION, em.getComponentName());
          marker_p.setAttribute(MarkerViewUtil.NAME_ATTRIBUTE, event_p.getLoggerName());
          Object element_o = null;
          if ((em.getCapellaElements() != null) && (em.getCapellaElements().size() > 0)) {
            element_o = em.getCapellaElements().get(0);
            if (element_o instanceof ModelElement) {
              ModelElement element = (ModelElement) element_o;
              marker_p.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, element.getFullLabel());
            } else {
              marker_p.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, UNKNOWN);
            }
          }
        } catch (CoreException e) {
          MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
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

  public void report(final String message, final Level level_p, final IMarkerModification additions) {
    final int number = numberMarker++;

    // workspaceRoot.createMarker(MarkerView.MARKER_ID); // FIXME get back there
    LightMarkerRegistry.getInstance().createMarker(workspaceRoot, MarkerView.MARKER_ID, new IMarkerModification() {
      public void modify(IMarker marker_p) {
        try {
          marker_p.setAttribute(MarkerView.MARKER_NUMBER, number);
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

}
