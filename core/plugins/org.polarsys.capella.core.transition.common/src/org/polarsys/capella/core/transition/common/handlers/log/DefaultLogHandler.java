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

  protected Logger _logger;

  protected List<TransitionMessage> logs;

  String reportComponent;

  public DefaultLogHandler(String reportComponent_p) {
    reportComponent = reportComponent_p;
  }

  protected class TransitionMessage extends EmbeddedMessage {

    String priority;

    public TransitionMessage(String message_p, String priority_p, String info_p) {
      super(message_p, info_p);
      priority = priority_p;
      setSource(info_p);
    }

    public TransitionMessage(String message_p, String priority_p, Object affectedObjects_p, String info_p) {
      super(message_p, info_p, affectedObjects_p);
      priority = priority_p;
      setSource(info_p);
    }

  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    _logger = ReportManagerRegistry.getInstance().subscribe(reportComponent);
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
  public IStatus dispose(IContext context_p) {
    flush();
    logs = null;
    return Status.OK_STATUS;
  }

  protected TransitionMessage createEmbeddedMessage(String message_p, String priority_p, Object relatedObjects_p, String source_p) {
    return new TransitionMessage(message_p, priority_p, relatedObjects_p, source_p);
  }

  protected TransitionMessage createEmbeddedMessage(String message_p, String priority_p, String source_p) {
    return new TransitionMessage(message_p, priority_p, source_p);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message_p, String priority_p, Object relatedObjects_p, String source_p) {
    TransitionMessage message = createEmbeddedMessage(message_p, priority_p, relatedObjects_p, source_p);
    logs.add(message);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message_p, String priority_p, String source_p) {
    TransitionMessage message = createEmbeddedMessage(message_p, priority_p, source_p);
    logs.add(message);
  }

  /**
   * {@inheritDoc}
   */
  public void log(String message_p, IStatus status_p, Object relatedObjects_p, String source_p) {
    String priority = toPriority(status_p);
    log(message_p, priority, relatedObjects_p, source_p);
  }

  public void log(String message_p, IStatus status_p, String source_p) {
    String priority = toPriority(status_p);
    log(message_p, priority, source_p);
  }

  /**
   * @param severity_p
   * @return
   */
  protected String toPriority(IStatus status_p) {
    switch (status_p.getSeverity()) {
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

  protected void log(TransitionMessage message_p) {
    _logger.log(Level.toLevel(message_p.priority), message_p);
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message_p, Object relatedObjects_p, String source_p) {
    if (hasDebug()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_DEBUG, relatedObjects_p, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void debug(String message_p, String source_p) {
    if (hasDebug()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_DEBUG, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message_p, Object relatedObjects_p, String source_p) {
    if (hasInfo()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_INFO, relatedObjects_p, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void info(String message_p, String source_p) {
    if (hasInfo()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_INFO, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message_p, Object relatedObjects_p, String source_p) {
    if (hasWarn()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_WARN, relatedObjects_p, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void warn(String message_p, String source_p) {
    if (hasWarn()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_WARN, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message_p, Object relatedObjects_p, String source_p) {
    if (hasError()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_ERROR, relatedObjects_p, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void error(String message_p, String source_p) {
    if (hasError()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_ERROR, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message_p, Object relatedObjects_p, String source_p) {
    if (hasFatal()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_FATAL, relatedObjects_p, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void fatal(String message_p, String source_p) {
    if (hasFatal()) {
      log(message_p, ReportManagerConstants.LOG_LEVEL_FATAL, source_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public String getText(Object object_p) {
    if (object_p != null) {

      if (object_p instanceof EClass) {
        return ((EClass) object_p).getName();

      } else if (object_p instanceof EObject) {
        return EObjectLabelProviderHelper.getText((EObject) object_p);

      } else if (object_p instanceof DiffModelViewer) {
        return getViewerText((DiffModelViewer) object_p);

      }

      return object_p.toString();
    }
    return "null";
  }

  /**
   * @param object_p
   * @return
   */
  protected String getViewerText(DiffModelViewer view) {
    IDifference diff_p = view.getRelatedDiff();
    DiffScope diffScope_p = view.getScopeDiff();
    String _textDiff = ""; //$NON-NLS-1$
    EObject me = null;
    EObject diffelt = null;

    // Difference on Reference of element
    if (diff_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) diff_p;

      if (diffScope_p == DiffScope.Source) {
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
    if (diff_p instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) diff_p;

      diffelt = ep.getElement();

      if (diffelt != null) {
        _textDiff = "Presence of " + getReadableText(diffelt); //$NON-NLS-1$
        me = diffelt;
      }
    }

    // Difference on Attribute of an element
    if (diff_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) diff_p;

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
   * @param meMatch_p
   * @return
   */
  protected String getReadableText(EObject object_p) {
    return getText(object_p);
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasDebug() {
    if (_logger == null) {
      return false;
    }
    Level level = _logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.DEBUG.isGreaterOrEqual(_logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasInfo() {
    if (_logger == null) {
      return false;
    }
    Level level = _logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.INFO.isGreaterOrEqual(_logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasWarn() {
    if (_logger == null) {
      return false;
    }
    Level level = _logger.getLevel();
    if (level == null) {
      return false;
    }
    return Level.WARN.isGreaterOrEqual(_logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasError() {
    if (_logger == null) {
      return false;
    }
    Level level = _logger.getLevel();
    if (level == null) {
      return true;
    }
    return Level.ERROR.isGreaterOrEqual(_logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasFatal() {
    if (_logger == null) {
      return false;
    }
    Level level = _logger.getLevel();
    if (level == null) {
      return true;
    }
    return Level.FATAL.isGreaterOrEqual(_logger.getLevel());
  }

  /**
   * {@inheritDoc}
   */
  public void setLevel(Level level_p) {
    if (_logger != null) {
      _logger.setLevel(level_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIdentifier(EObject me_p) {
    return me_p.eClass().getName();
  }

}
