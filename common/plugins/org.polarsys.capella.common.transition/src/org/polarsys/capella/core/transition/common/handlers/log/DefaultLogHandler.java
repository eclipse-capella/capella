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
package org.polarsys.capella.core.transition.common.handlers.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * We need to store temporarily all logs messages. In fact, since transformed elements are not always attached to a resource at any moment of
 *         the transfo uri stored in messages can be linked to the holding resource. Navigation from the information view to the project explorer will not works
 *         properly until elements are attached. So messages are flush to Logger at the end to the transposition
 */
public class DefaultLogHandler implements ILogHandler {

  protected Logger logger;

  protected List<TransitionMessage> logs;

  String reportComponent;

  public DefaultLogHandler(String reportComponent) {
    this.reportComponent = reportComponent;
  }

  protected class TransitionMessage extends EmbeddedMessage {

    String priority;

    public TransitionMessage(String message, String priority, String info) {
      super(message, info);
      this.priority = priority;
      setSource(info);
    }

    public TransitionMessage(String message, String priority, Object affectedObjects, String info) {
      super(message, info, affectedObjects);
      this.priority = priority;
      setSource(info);
    }

  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    logger = ReportManagerRegistry.getInstance().subscribe(reportComponent);
    logs = new ArrayList<DefaultLogHandler.TransitionMessage>();
    return Status.OK_STATUS;
  }

  public void flush() {
    // Flush all messages
    for (TransitionMessage message : logs) {
      log(message);
    }
    logs.clear();
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    flush();
    logs = null;
    return Status.OK_STATUS;
  }

  protected TransitionMessage createEmbeddedMessage(String message, String priority, Object relatedObjects, String source) {
    return new TransitionMessage(message, priority, relatedObjects, source);
  }

  protected TransitionMessage createEmbeddedMessage(String message, String priority, String source) {
    return new TransitionMessage(message, priority, source);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message, String priority, Object relatedObjects, String source) {
    TransitionMessage transitionMessage = createEmbeddedMessage(message, priority, relatedObjects, source);
    logs.add(transitionMessage);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message, String priority, String source) {
    TransitionMessage transitionMessage = createEmbeddedMessage(message, priority, source);
    logs.add(transitionMessage);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message, IStatus status, Object relatedObjects, String source) {
    String priority = toPriority(status);
    log(message, priority, relatedObjects, source);
  }

  public void log(String message, IStatus status, String source) {
    String priority = toPriority(status);
    log(message, priority, source);
  }

  /**
   * @param status
   * @return
   */
  protected String toPriority(IStatus status) {
    return LogExt.toPriority(status);
  }

  protected void log(TransitionMessage message) {
    logger.log(Level.toLevel(message.priority), message);
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message, Object relatedObjects, String source) {
    if (hasDebug()) {
      log(message, ReportManagerConstants.LOG_LEVEL_DEBUG, relatedObjects, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message, String source) {
    if (hasDebug()) {
      log(message, ReportManagerConstants.LOG_LEVEL_DEBUG, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message, Object relatedObjects, String source) {
    if (hasInfo()) {
      log(message, ReportManagerConstants.LOG_LEVEL_INFO, relatedObjects, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message, String source) {
    if (hasInfo()) {
      log(message, ReportManagerConstants.LOG_LEVEL_INFO, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message, Object relatedObjects, String source) {
    if (hasWarn()) {
      log(message, ReportManagerConstants.LOG_LEVEL_WARN, relatedObjects, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message, String source) {
    if (hasWarn()) {
      log(message, ReportManagerConstants.LOG_LEVEL_WARN, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message, Object relatedObjects, String source) {
    if (hasError()) {
      log(message, ReportManagerConstants.LOG_LEVEL_ERROR, relatedObjects, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message, String source) {
    if (hasError()) {
      log(message, ReportManagerConstants.LOG_LEVEL_ERROR, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message, Object relatedObjects, String source) {
    if (hasFatal()) {
      log(message, ReportManagerConstants.LOG_LEVEL_FATAL, relatedObjects, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message, String source) {
    if (hasFatal()) {
      log(message, ReportManagerConstants.LOG_LEVEL_FATAL, source);
    }
  }

  /**
   * {@inheritDoc}
   */
  public String getText(Object object) {
    if (object != null) {

      if (object instanceof EClass) {
        return ((EClass) object).getName();

      } else if (object instanceof EObject) {
        return EObjectLabelProviderHelper.getText((EObject) object);

      }

      return object.toString();
    }
    return "null"; //$NON-NLS-1$
  }

  /**
   * @param object
   * @return
   */
  protected String getReadableText(EObject object) {
    return getText(object);
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasDebug() {
    if (logger == null) {
      return false;
    }
    Level level = logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.DEBUG.isGreaterOrEqual(logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasInfo() {
    if (logger == null) {
      return false;
    }
    Level level = logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.INFO.isGreaterOrEqual(logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasWarn() {
    if (logger == null) {
      return false;
    }
    Level level = logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.WARN.isGreaterOrEqual(logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasError() {
    if (logger == null) {
      return false;
    }
    Level level = logger.getLevel();
    if (level == null) {
      return true;
    }
    return Level.ERROR.isGreaterOrEqual(logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasFatal() {
    if (logger == null) {
      return false;
    }
    Level level = logger.getLevel();
    if (level == null) {
      return true;
    }
    return Level.FATAL.isGreaterOrEqual(logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public void setLevel(Level level) {
    if (logger != null) {
      logger.setLevel(level);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIdentifier(EObject me) {
    return me.eClass().getName();
  }

}
