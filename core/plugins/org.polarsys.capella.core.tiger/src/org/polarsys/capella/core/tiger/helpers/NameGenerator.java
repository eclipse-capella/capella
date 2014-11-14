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
   * @param messageEnd_p The message end.
   */
  @SuppressWarnings("nls")
  public static void computeMessageEndName(MessageEnd messageEnd_p) {
    
    InstanceRole instanceRole = messageEnd_p.getCovered();
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
    
    SequenceMessage message = messageEnd_p.getMessage();
    String messageName = message.getName();
    
    boolean isSending = message.getSendingEnd() == messageEnd_p;
    messageEnd_p.setName("[[ " + (isSending?"S":"R") 
                         + " [" + representedInstanceName + "] " 
                         + messageName + "() " 
                         + "(Ref=" + __idAbstractEnd + ")" 
                         + " ]] ");
    __idAbstractEnd++;
  }  
  
  /**
   * Computes the name of an execution.
   * @param executionEnd_p The execution to be named.
   */
  @SuppressWarnings("nls")
  public static void computeExecutionEndName(ExecutionEnd executionEnd_p) {
    InstanceRole instanceRole = executionEnd_p.getCovered();
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    NamedElement component = null;
    if (instance instanceof Part) {
      component = (NamedElement) instance.eContainer();
    }
    else if (instance instanceof SignalInstance) {
      component =((SignalInstance) instance).getType();
    }
    String representedInstanceName = component.getName();
    
    executionEnd_p.setName("[[ " 
                           + "[" + representedInstanceName + "] " 
                           + "(Ref=" + __idAbstractEnd + ")" 
                           + " ]] ");
    
    __idAbstractEnd++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idInstanceRole = 0;
  
  /**
   * Computes the name of an execution.
   * @param executionEnd_p The execution to be named.
   */
  @SuppressWarnings("nls")
  public static void computeInstanceRoleName(InstanceRole instanceRole_p) {
    AbstractInstance instance = instanceRole_p.getRepresentedInstance();
    NamedElement component = null;
    if (instance instanceof Part) {
      component = (NamedElement) instance.eContainer();
    }
    else if (instance instanceof SignalInstance) {
      component = ((SignalInstance) instance).getType();
    }
    String componentName = component.getName();
    String representedInstanceName = component.getName();  
    instanceRole_p.setName("[[ " 
                           + "[" + representedInstanceName + " : "+ componentName + "] " 
                           + "(Ref=" + __idInstanceRole + ")" 
                           + " ]] ");
    
    __idInstanceRole++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idExecution = 0;
  
  /**
   * Computes the name of an execution.
   * @param executionEnd_p The execution to be named.
   */
  @SuppressWarnings("nls")
  public static void computeExecutionName(Execution execution_p) {
    String roleName = ((AbstractEnd) execution_p.getStart()).getCovered().getName();
 
    execution_p.setName("Execution on " + roleName 
                           + "(Ref=" + __idExecution + ")");
    
    __idExecution++;    
  }
  
  /**
   * The unique id for new names
   */
  public static int __idEvent = 0;
  
  @SuppressWarnings("nls")
  public static void computeEventName(Event event_p) {
    boolean receive = event_p instanceof EventReceiptOperation;
    boolean send = event_p instanceof EventSentOperation;
    boolean execution = event_p instanceof ExecutionEvent;
    
    event_p.setName("Event " 
                    + (send?"S":"")
                    + (receive?"R":"")
                    + (execution?"E":"")
                    + " (Ref=" + __idEvent + ")");
    
    __idEvent++;
  }
  
}
