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

import org.eclipse.emf.common.notify.Notification;


/**
 */
public enum NotificationEnum {
  
  SET(Notification.SET,"SET"), //$NON-NLS-1$
  UNSET(Notification.UNSET,"UNSET"), //$NON-NLS-1$
  ADD(Notification.ADD,"ADD"), //$NON-NLS-1$
  REMOVE(Notification.REMOVE,"REMOVE"), //$NON-NLS-1$
  ADD_MANY(Notification.ADD_MANY,"ADD_MANY"), //$NON-NLS-1$
  REMOVE_MANY(Notification.REMOVE_MANY,"REMOVE_MANY"), //$NON-NLS-1$
  MOVE(Notification.MOVE,"MOVE"), //$NON-NLS-1$
  REMOVING_ADAPTER(Notification.REMOVING_ADAPTER,"REMOVING_ADAPTER"), //$NON-NLS-1$
  RESOLVE(Notification.RESOLVE,"RESOLVE"), //$NON-NLS-1$
  UNDEFINED(IRecorderManagerConstants.NONE,"UNDEFINED") //$NON-NLS-1$
  ;

  /** Accessor on closing event */
  public int getEvent() {
    return _eventType;
  }
  
  /** Accessor on the literal */
  public String getLiteral() {
    return _literal;
  }
  
  /** the closing event type for this operation */
  final private int _eventType;
  
  /** the literal value */
  final private String _literal;
  
  /** Constructor */
  NotificationEnum(int eventType, String literal) {
    _eventType = eventType;
    _literal = literal;
  }
  
  static public NotificationEnum getOperationEnum(int closingEventType) {
    
    NotificationEnum result = UNDEFINED;
    
    for (int i = 0; i < values().length; i++) {
      if ( closingEventType == values()[i].getEvent() ) {
        result = values()[i];
        break;
      }
    }
    
    return result;
  }
  
  static public NotificationEnum getOperationEnum(String literal) {
    
    NotificationEnum result = UNDEFINED;
    
    for (int i = 0; i < values().length; i++) {
      if ( literal.equals(values()[i].getLiteral()) ) {
        result = values()[i];
        break;
      }
    }
    
    return result;
  }
  
}
