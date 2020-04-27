/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 * We need to store temporarily all logs messages. In fact, since transformed elements are not always attached to a resource at any moment of
 *         the transfo uri stored in messages can be linked to the holding resource. Navigation from the information view to the project explorer will not works
 *         properly until elements are attached. So messages are flush to Logger at the end to the transposition
 */
public class DebugTraceLogHandler implements ILogHandler {

  protected Logger _logger;

  String reportComponent;

  public DebugTraceLogHandler(String reportComponent_p) {
    reportComponent = reportComponent_p;
  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context_p) {
    _logger = ReportManagerRegistry.getInstance().subscribe(reportComponent);
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context_p) {
    /* do nothing */
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message_p, String priority_p, Object relatedObjects_p, String source_p) {
    if (_logger.isEnabledFor(Level.toLevel(priority_p))){
      EmbeddedMessage mess = new EmbeddedMessage(message_p, _logger.getName(), relatedObjects_p);
      mess.setSource(source_p);
      _logger.log(Level.toLevel(priority_p), mess);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message_p, String priority_p, String source_p) {
    log(message_p, priority_p, null, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message_p, Object relatedObjects_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_DEBUG, relatedObjects_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_DEBUG, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message_p, Object relatedObjects_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_INFO, relatedObjects_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_INFO, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message_p, Object relatedObjects_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_WARN, relatedObjects_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_WARN, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message_p, Object relatedObjects_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_ERROR, relatedObjects_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_ERROR, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message_p, Object relatedObjects_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_FATAL, relatedObjects_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message_p, String source_p) {
    log(message_p, ReportManagerConstants.LOG_LEVEL_FATAL, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public String getText(Object object_p) {
    if (object_p != null) {
      if (object_p instanceof EObject) {
        return EObjectLabelProviderHelper.getText((EObject) object_p);
      }
      return object_p.toString();
    }
    return ProjectionMessages.Null;
  }

}
