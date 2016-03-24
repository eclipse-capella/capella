/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger.helpers;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.communication.SignalInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEvent;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * This class is a class utility to generate comprehensive names for model elements like:
 * <ul>
 * <li> {@link MessageEnd} </li>
 * <li> {@link ExecutionEnd} </li>
 * <li> {@link InstanceRole} </li>
 * <li> {@link Execution} </li>
 * </ul>
 */
public class NameGenerator {
  
  /**
   * Default constructor
   */
  private NameGenerator() {
    // Nothing to be done
  }  
  
  /**
   * The unique id for new names
   */
  public static int __idAbstractEnd = 0;
  
  /**
   * Computes the name of the message end depending on some info contained into it.
   * @param messageEnd The message end.
   */
  public static void computeMessageEndName(MessageEnd messageEnd) {
    
    InstanceRole instanceRole = messageEnd.getCovered();
    assert instanceRole!=null;
    
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    assert instance!=null;
    
    NamedElement component = null;
    if (instance instanceof Part) {
      component = (NamedElement) instance.eContainer();
    }
    else if (instance instanceof SignalInstance) {
      component = ((SignalInstance) instance).getType();
    }
    assert component!=null;
    
    String representedInstanceName = component.getName();
    
    SequenceMessage message = messageEnd.getMessage();
    String messageName = message.getName();
    
    boolean isSending = message.getSendingEnd() == messageEnd;
    messageEnd.setName("[[ " + (isSending?"S":"R") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                         + " [" + representedInstanceName + "] " //$NON-NLS-1$ //$NON-NLS-2$
                         + messageName + "() " //$NON-NLS-1$
                         + "(Ref=" + __idAbstractEnd + ")" //$NON-NLS-1$ //$NON-NLS-2$
                         + " ]] "); //$NON-NLS-1$
    __idAbstractEnd++;
  }  
  
  /**
   * Computes the name of an execution.
   * @param executionEnd The execution to be named.
   */
  public static void computeExecutionEndName(ExecutionEnd executionEnd) {
    InstanceRole instanceRole = executionEnd.getCovered();
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    NamedElement component = null;
    if (instance instanceof Part) {
      component = (NamedElement) instance.eContainer();
    }
    else if (instance instanceof SignalInstance) {
      component =((SignalInstance) instance).getType();
    }
    assert component!=null;
    
    String representedInstanceName = component.getName();
    
    executionEnd.setName("[[ " //$NON-NLS-1$
                           + "[" + representedInstanceName + "] " //$NON-NLS-1$ //$NON-NLS-2$
                           + "(Ref=" + __idAbstractEnd + ")" //$NON-NLS-1$ //$NON-NLS-2$
                           + " ]] "); //$NON-NLS-1$
    
    __idAbstractEnd++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idInstanceRole = 0;
  
  /**
   * Computes the name of an execution.
   * @param executionEnd The execution to be named.
   */
  public static void computeInstanceRoleName(InstanceRole instanceRole) {
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    NamedElement component = null;
    if (instance instanceof Part) {
      component = (NamedElement) instance.eContainer();
    }
    else if (instance instanceof SignalInstance) {
      component = ((SignalInstance) instance).getType();
    }
    assert component!=null;
    
    String componentName = component.getName();
    String representedInstanceName = component.getName();  
    instanceRole.setName("[[ " //$NON-NLS-1$
                           + "[" + representedInstanceName + " : "+ componentName + "] " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                           + "(Ref=" + __idInstanceRole + ")" //$NON-NLS-1$ //$NON-NLS-2$
                           + " ]] "); //$NON-NLS-1$
    
    __idInstanceRole++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idExecution = 0;
  
  /**
   * Computes the name of an execution.
   * @param executionEnd The execution to be named.
   */
  public static void computeExecutionName(Execution execution) {
    String roleName = ((AbstractEnd) execution.getStart()).getCovered().getName();
 
    execution.setName("Execution on " + roleName //$NON-NLS-1$
                           + "(Ref=" + __idExecution + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    
    __idExecution++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idEvent = 0;
  
  public static void computeEventName(Event event) {
    boolean receive = event instanceof EventReceiptOperation;
    boolean send = event instanceof EventSentOperation;
    boolean execution = event instanceof ExecutionEvent;
    
    event.setName("Event " //$NON-NLS-1$
                    + (send?"S":"") //$NON-NLS-1$ //$NON-NLS-2$
                    + (receive?"R":"") //$NON-NLS-1$ //$NON-NLS-2$
                    + (execution?"E":"") //$NON-NLS-1$ //$NON-NLS-2$
                    + " (Ref=" + __idEvent + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    
    __idEvent++;
  }
}
