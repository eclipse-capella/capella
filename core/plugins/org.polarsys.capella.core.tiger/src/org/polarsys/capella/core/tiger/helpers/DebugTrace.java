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
package org.polarsys.capella.core.tiger.helpers;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Trace buffer for debug
 * @deprecated
 */
 @Deprecated
 class DebugTrace {

  static class Message extends EmbeddedMessage {

    String priority;
    String info;

    public Message(String message_p, String info_p, String priority_p) {
      super(message_p, info_p);
      priority = priority_p;
    }

    public Message(String message_p, String priority_p, Object affectedObjects_p, String info_p) {
      super(message_p, info_p, affectedObjects_p);
      priority = priority_p;
      info = info_p;
    }

    @Override
    public void adapt(Object target) {
      super.adapt(target);
      if (target instanceof IMarker) {
        IMarker marker = (IMarker) target;
        try {
          marker.setAttribute(IValidationConstants.TAG_RULE_ID, info);
        } catch (CoreException exception_p) {
          //Catch exceptions silently
        }
      }
    }
  }

  private static LinkedList<Message> __messages = new LinkedList<Message>();

  /**
   * Appends a message to the content of the buffer
   * @param message_p The message to be appended
   */
   private static void append(String message_p) {
    //append(message_p, ReportManagerConstants.LOG_LEVEL_DEBUG);
  }

  private static void append(String message_p, String priority_p) {
    __messages.add(new Message(message_p, ICommonConstants.EMPTY_STRING, priority_p));
  }

  /**
   * @param string_p
   * @param priority, should be a ReportManagerConstants.LOG_LEVEL* constants
   * @param affectedObject_p
   */
  private static void append(String message, String priority, Object affectedObject_p, String source) {
    StringBuilder value = new StringBuilder();
    value.append(message);
    value.append(ICommonConstants.WHITE_SPACE_CHARACTER);
    __messages.add(new Message(value.toString(), priority, affectedObject_p, source));
  }

  /**
   * Gets the content and flushes the buffer
   * @return The content
   */
  private static String getContent() {
    StringBuilder __buffer = new StringBuilder();
    for (Message mes : __messages) {
      __buffer.append(mes.getLabel());
      __buffer.append(ICommonConstants.EOL_CHARACTER);
    }
    return __buffer.toString();
  }

  private static void flush() {
    __messages.clear();
  }

  private static void flush(Logger logger) {
    if (logger != null) {
      for (Message mes : __messages) {
        logger.log(Level.toLevel(mes.priority), mes);
      }
    }
    flush();
  }

  /**
   * Retrieve a readable text of the element
   */
  protected static void getText(Object affectedObject, StringBuilder value) {
    if (affectedObject != null) {
      if (affectedObject instanceof EObject) {
        value.append(EObjectLabelProviderHelper.getText((EObject) affectedObject));
      } else if (affectedObject instanceof List<?>) {
        List<?> list = (List<?>) affectedObject;
        value.append("{"); //$NON-NLS-1$
        for (Object o : list) {
          getText(o, value);
          value.append(ICommonConstants.SEMICOLON_CHARACTER);
        }
        if (list.size() > 0) {
          value.deleteCharAt(value.length() - 1);
        }
        value.append("}"); //$NON-NLS-1$
      } else {
        value.append(affectedObject.toString());
      }
    }
  }

  /**
   * Retrieve a readable text of the element
   */
  private static String getText(Object affectedObject) {
    StringBuilder __buffer = new StringBuilder();
    getText(affectedObject, __buffer);
    return __buffer.toString();
  }
}
