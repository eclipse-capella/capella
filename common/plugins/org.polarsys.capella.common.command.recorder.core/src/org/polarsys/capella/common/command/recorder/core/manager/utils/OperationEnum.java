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

import org.eclipse.core.commands.operations.OperationHistoryEvent;


/**
 */
public enum OperationEnum {
  
  DONE(OperationHistoryEvent.DONE,"DONE" ), //$NON-NLS-1$
  UNDO(OperationHistoryEvent.UNDONE,"UNDONE"), //$NON-NLS-1$
  REDO(OperationHistoryEvent.REDONE,"REDONE"), //$NON-NLS-1$
  NOT_OK(OperationHistoryEvent.OPERATION_NOT_OK,"NOT_OK"), //$NON-NLS-1$
  UNDEFINED(IRecorderManagerConstants.NONE,"???") //$NON-NLS-1$
  ;

  /** Accessor on closing event */
  public int getClosingEvent() {
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
  OperationEnum(int eventType, String literal) {
    _eventType = eventType;
    _literal = literal;
  }
  
  static public OperationEnum getOperationEnum(int closingEventType) {
    
    OperationEnum result = UNDEFINED;
    
    for (int i = 0; i < values().length; i++) {
      if ( closingEventType == values()[i].getClosingEvent() ) {
        result = values()[i];
        break;
      }
    }
    
    return result;
  }
  
  static public OperationEnum getOperationEnum(String literal) {
    
    OperationEnum result = UNDEFINED;
    
    for (int i = 0; i < values().length; i++) {
      if ( literal.equals(values()[i].getLiteral()) ) {
        result = values()[i];
        break;
      }
    }
    
    return result;
  }
  
}
