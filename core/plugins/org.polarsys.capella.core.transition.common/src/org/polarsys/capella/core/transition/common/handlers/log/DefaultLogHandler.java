/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.handlers.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
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
    switch (status.getSeverity()) {
      case IStatus.OK:
        return ReportManagerConstants.LOG_LEVEL_INFO;
      case IStatus.CANCEL:
        return ReportManagerConstants.LOG_LEVEL_WARN;
      case IStatus.WARNING:
        return ReportManagerConstants.LOG_LEVEL_WARN;
      case IStatus.ERROR:
        return ReportManagerConstants.LOG_LEVEL_ERROR;
      case IStatus.INFO:
        return ReportManagerConstants.LOG_LEVEL_INFO;
      default:
        return ReportManagerConstants.LOG_LEVEL_INFO;
    }
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

      } else if (object instanceof DiffModelViewer) {
        return getViewerText((DiffModelViewer) object);

      }

      return object.toString();
    }
    return "null";
  }

  /**
   * @param view
   * @return
   */
  protected String getViewerText(DiffModelViewer view) {
    IDifference diff = view.getRelatedDiff();
    DiffScope diffScope = view.getScopeDiff();
    String _textDiff = ""; //$NON-NLS-1$
    EObject me = null;
    EObject diffelt = null;

    // Difference on Reference of element
    if (diff instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) diff;

      if (diffScope == DiffScope.Source) {
        diffelt = rvp.getValue().get(Role.REFERENCE);

        String ordering = rvp.isOrder() ? "of order " : ""; //$NON-NLS-1$ //$NON-NLS-2$
        if (diffelt != null) {
          _textDiff = "Modification " + ordering + "on reference of " + getReadableText(diffelt); //$NON-NLS-1$ //$NON-NLS-2$

          me = diffelt;

          EObject meMatch = rvp.getElementMatch().get(Role.REFERENCE);
          if (meMatch != null) {
            _textDiff = _textDiff + " -> From " + getReadableText(meMatch); //$NON-NLS-1$
          }

          Collection<IMergeableDifference> listMergeDiff = new ArrayList<IMergeableDifference>();
          listMergeDiff.addAll(rvp.getDirectRequiresDependencies(Role.TARGET));
          listMergeDiff.addAll(rvp.getDirectImpliesDependencies(Role.TARGET));
          for (IMergeableDifference mergeDiff : listMergeDiff) {
            if (mergeDiff instanceof IReferenceValuePresence) {
              EObject meMergeDiff = ((IReferenceValuePresence) mergeDiff).getElementMatch().get(Role.TARGET);
              if (meMergeDiff != null) {
                _textDiff = _textDiff + " To " + getReadableText(meMergeDiff); //$NON-NLS-1$
              }
            }
          }

          if (rvp.getFeature() != null) {
            String featureName = rvp.getFeature().getName();
            _textDiff = _textDiff + " [" + featureName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
      } else {
        diffelt = rvp.getValue().get(Role.TARGET);

        String ordering = rvp.isOrder() ? "of order " : ""; //$NON-NLS-1$ //$NON-NLS-2$
        if (diffelt != null) {

          _textDiff = "Modification " + ordering + "on reference of " + getReadableText(diffelt); //$NON-NLS-1$ //$NON-NLS-2$

          me = diffelt;

          EObject meMatch = rvp.getElementMatch().get(Role.TARGET);
          if (meMatch != null) {
            _textDiff = _textDiff + " -> From " + getReadableText(meMatch); //$NON-NLS-1$
          }

          Collection<IMergeableDifference> listMergeDiff = new ArrayList<IMergeableDifference>();
          listMergeDiff.addAll(rvp.getDirectRequiresDependencies(Role.REFERENCE));
          listMergeDiff.addAll(rvp.getDirectImpliesDependencies(Role.REFERENCE));
          for (IMergeableDifference mergeDiff : listMergeDiff) {
            if (mergeDiff instanceof IReferenceValuePresence) {
              EObject meMergeDiff = ((IReferenceValuePresence) mergeDiff).getElementMatch().get(Role.REFERENCE);
              if (meMergeDiff != null) {
                _textDiff = _textDiff + " To " + getReadableText(meMergeDiff); //$NON-NLS-1$
              }
            }
          }
        }

        if (rvp.getFeature() != null) {
          String featureName = rvp.getFeature().getName();
          _textDiff = _textDiff + " [" + featureName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }

      }
    }

    // Difference on Presence of new element
    if (diff instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) diff;

      diffelt = ep.getElement();

      if (diffelt != null) {
        _textDiff = "Presence of " + getReadableText(diffelt); //$NON-NLS-1$
        me = diffelt;
      }
    }

    // Difference on Attribute of an element
    if (diff instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) diff;

      diffelt = avp.getElementMatch().get(Role.REFERENCE);
      String ordering = avp.isOrder() ? "of order " : ""; //$NON-NLS-1$ //$NON-NLS-2$

      if (diffelt != null) {
        _textDiff = "Modification " + ordering + "on attribute of " + getReadableText(diffelt); //$NON-NLS-1$ //$NON-NLS-2$
        me = diffelt;

        String featureName = avp.getFeature().getName();
        _textDiff = _textDiff + " -> " + featureName; //$NON-NLS-1$

        String value = avp.getValue().toString();
        _textDiff = _textDiff + " = " + value; //$NON-NLS-1$

        for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
          if (mergeDiff instanceof IAttributeValuePresence) {
            String featureMergeDiffName = ((IAttributeValuePresence) mergeDiff).getValue().toString();
            _textDiff = _textDiff + " (was '" + featureMergeDiffName + "')"; //$NON-NLS-1$ //$NON-NLS-2$
          }
        }

        EObject meMatch = avp.getElementMatch().get(Role.TARGET);
        if (meMatch != null) {
          _textDiff = _textDiff + " (Target : " + getReadableText(meMatch) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
    }
    return _textDiff;

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
