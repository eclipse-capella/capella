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

package org.polarsys.capella.common.command.recorder.core.manager.utils;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.command.recorder.core.exception.RecorderException;
import org.polarsys.capella.common.command.recorder.core.messages.RecorderMessages;

/**
 * Utility class for recorder manager
 */
public class RecorderManagerUtils {

  /**
   * Return the opening event type associate to a given closing event type  
   * @param closingEventType an (closing) event type
   * @return
   * @throws RecorderException whether the event Type does not fit with closing one's.
   * @see IRecorderManagerConstants
   */
  public static int getExpectedOpeningEvent(final int closingEventType) throws RecorderException {

    int result = IRecorderManagerConstants.NONE;
    final int data[] = IRecorderManagerConstants.DOUBLETS;
    
    for (int i = 1; i < data.length; i+=2) {
      if ( closingEventType == data[i] ) {
        result = data[--i];
        break;
      }
    }
    
    if ( IRecorderManagerConstants.NONE == result) {
      throw new RecorderException(
          NLS.bind(
              RecorderMessages.recorderManagerUtils_invalidHistoryClosingEventCode,
              String.valueOf(closingEventType)
          )
      );
    }
    
    return result;
  }

  /**
   * Return the closing event type associate to a given opening event type  
   * @param openingEventType an (opening) event type
   * @return
   * @throws RecorderException whether the event Type does not fit with openining one's.
   * @see IRecorderManagerConstants
   */

  public static int getExpectedClosingEvent(final int openingEventType) throws RecorderException {

    int result = IRecorderManagerConstants.NONE;
    final int data[] = IRecorderManagerConstants.DOUBLETS;
    
    for (int i = 0; i < data.length; i+=2) {
      if ( openingEventType == data[i] ) {
        result = data[++i];
        break;
      }
    }
    
    if ( IRecorderManagerConstants.NONE == result) {
      throw new RecorderException(
          NLS.bind(
              RecorderMessages.recorderManagerUtils_invalidHistoryOpeningEventCode,
              String.valueOf(openingEventType)
          )
      );
    }
    
    return result;
  }
  
}
