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

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;

/**
 * This static class is a helper for handling components and component instances.<br>
 * It provides facilities for:
 * <ul>
 * <li> Creating a new component</li>
 * <li> Creating a new named instance of a component</li>
 * <li> Computing a instance name of a component</li>
 * <li> Retrieving whether a component is a container of another component</li>
 * </ul>
 */
public class ElementFactory {
   
  /**
   * Default constructor
   */
  private ElementFactory() {
    // Nothing to be done
  }
  
  /**
   * Creates a new logical component
   * @param componentNameString_p
   * @return The new logical component
   */
  public static LogicalComponent createComponent(String componentNameString_p) {
    LogicalComponent logicalComponent = LaFactory.eINSTANCE
      .createLogicalComponent(componentNameString_p);
    return logicalComponent;
  }
  
  /**
   * Creates a new instance of a component
   * @param component_p The component
   * @param instanceNameString_p The name of the new instance
   * @return The new instance
   */
  public static Part createInstance(LogicalComponent component_p, String instanceNameString_p) {
    Part logicalComponentInstance = CsFactory.eINSTANCE.createPart(instanceNameString_p);
    component_p.getOwnedLogicalComponents().add(component_p);

    return logicalComponentInstance;
  }
 
  
  public static SequenceMessage createMessage(Scenario scenario_p,
		  Part componentInstance1_p, Part componentInstance2_p,
       String messageNameString_p) {
     return createMessage(scenario_p, 
                          componentInstance1_p, 
                          componentInstance2_p, 
                          messageNameString_p,
                          new ArrayList<Event>());
   }
   /**
    * Creates a new message call (send and reply) from a component instance to another one.<br>
    * The message call is <b>synchronous</b>.
    * 
    * @param scenario_p The target scenario in which the message has to be added
    * @param componentInstance1_p The source component instance
    * @param componentInstance2_p The target component instance
    * @param messageNameString_p The message name
    * @return The new message
    */
   public static SequenceMessage createMessage(Scenario scenario_p,
		   Part componentInstance1_p, Part componentInstance2_p,
       String messageNameString_p,
       List<Event> listEvents_p) {

     SequenceMessage message = createMessageCall(scenario_p, componentInstance1_p,
                                                 componentInstance2_p, 
                                                 messageNameString_p,
                                                 listEvents_p);

     createReturnMessage(scenario_p, message, listEvents_p);

     return message;
   }
   
   /**
    * Creates a new message call (send only) from a component instance to another one.<br>
    * The message call is <b>synchronous</b>.
    * @param scenario_p The target scenario in which the message has to be added
    * @param componentInstance1_p The source component instance
    * @param componentInstance2_p The target component instance
    * @param messageNameString_p The message name
    * @return The new message
    */
   public static SequenceMessage createMessageCall(Scenario scenario_p,
		   Part componentInstance1_p, Part componentInstance2_p,
       String messageNameString_p,
       List<Event> listEvents_p) {
     return createMessageCall(scenario_p, 
                              componentInstance1_p, 
                              componentInstance2_p, 
                              messageNameString_p, 
                              MessageKind.SYNCHRONOUS_CALL,
                              listEvents_p);
   }
   
   /**
    * Creates a new message call (send only) from a component instance to another one.<br>
    * The message call is <b>asynchronous</b>.
    * @param scenario_p The target scenario in which the message has to be added
    * @param componentInstance1_p The source component instance
    * @param componentInstance2_p The target component instance
    * @param messageNameString_p The message name
    * @return The new message
    */
   public static SequenceMessage createAsynchronousMessageCall(Scenario scenario_p,
		   Part componentInstance1_p, 
		   Part componentInstance2_p,
       String messageNameString_p,
       List<Event> listEvents_p,
       List<AbstractEnd> listExecutionEnds_p) {

     // 1- Create the message
     SequenceMessage message = createMessageCall(scenario_p, 
                              componentInstance1_p, 
                              componentInstance2_p,
                              messageNameString_p, 
                              MessageKind.ASYNCHRONOUS_CALL,
                              listEvents_p);
     
     // 2- Create execution at the ending message end of the message call
     Execution execution = InteractionFactory.eINSTANCE.createExecution();
     execution.setStart(message.getReceivingEnd());
     
     ExecutionEnd executionEnd = InteractionFactory.eINSTANCE.createExecutionEnd();
     execution.setFinish(executionEnd);
     Event event = InteractionFactory.eINSTANCE.createExecutionEvent();
     executionEnd.setEvent(event);

     listEvents_p.add(event);

     executionEnd.getCoveredInstanceRoles().add(message.getReceivingEnd().getCovered());

     scenario_p.getOwnedTimeLapses().add(execution);

     listExecutionEnds_p.add(executionEnd);

     NameGenerator.computeExecutionEndName(executionEnd);
     
     return message;
   } 
   
   /**
    * Creates a new message call 
    * @param scenario_p The target scenario in which the message has to be added
    * @param componentInstance1_p The source component instance
    * @param componentInstance2_p The target component instance
    * @param messageNameString_p The new message name
    * @param messageKind_p The message kind for the call
    * @return The new message
    */
   public static SequenceMessage createMessageCall(Scenario scenario_p,
       AbstractInstance componentInstance1_p, 
       AbstractInstance componentInstance2_p,
       String messageNameString_p,
       MessageKind messageKind_p,
       List<Event> listEvents_p) {
     
     // 1- Finds the 2 instance roles
       
     // 1.2- First instance role
     
     InstanceRole instanceRole1 = null;
     // 1.2.1- Checks whether the instance roles are not already in use in the scenario
     for (InstanceRole instanceRole : scenario_p.getOwnedInstanceRoles()) {
       if(instanceRole.getRepresentedInstance() == componentInstance1_p) {
         instanceRole1 = instanceRole;
       }
     }
     
     if(instanceRole1==null) {
       instanceRole1 = InteractionFactory.eINSTANCE.createInstanceRole(componentInstance1_p.getName());
       scenario_p.getOwnedInstanceRoles().add(instanceRole1);
       instanceRole1.setRepresentedInstance(componentInstance1_p);
       NameGenerator.computeInstanceRoleName(instanceRole1);       
     }   
     
     // 1.3- Second instance role
     
     InstanceRole instanceRole2 = null;
     // 1.3.1- Checks whether the instance roles are not already in use in the scenario    
     for (InstanceRole instanceRole : scenario_p.getOwnedInstanceRoles()) {
       if(instanceRole.getRepresentedInstance() == componentInstance2_p) {
         instanceRole2 = instanceRole;
       }
     }
     
     if(instanceRole2==null) {
       instanceRole2 = InteractionFactory.eINSTANCE.createInstanceRole(componentInstance2_p.getName());
       scenario_p.getOwnedInstanceRoles().add(instanceRole2);
       instanceRole2.setRepresentedInstance(componentInstance2_p);
       NameGenerator.computeInstanceRoleName(instanceRole2);           
     }
     
     // 2- Creates the new message ends
     MessageEnd messageEnd1 = InteractionFactory.eINSTANCE.createMessageEnd();
     MessageEnd messageEnd2 = InteractionFactory.eINSTANCE.createMessageEnd();
     messageEnd1.getCoveredInstanceRoles().add(instanceRole1);
     messageEnd2.getCoveredInstanceRoles().add(instanceRole2);

     // 3- Creates the new message
     SequenceMessage newMessage = InteractionFactory.eINSTANCE.createSequenceMessage(messageNameString_p);
     newMessage.setSendingEnd(messageEnd1);
     newMessage.setReceivingEnd(messageEnd2);
     newMessage.setKind(messageKind_p);

     // 5- Create events
     Event sentOperation = InteractionFactory.eINSTANCE.createEventSentOperation();
     messageEnd1.setEvent(sentOperation);

     Event receiveOperation = InteractionFactory.eINSTANCE.createEventReceiptOperation();
     messageEnd2.setEvent(receiveOperation);
     
     listEvents_p.add(sentOperation);
     listEvents_p.add(receiveOperation);
     
     // 6- Updates the scenario with the new message and message ends
     scenario_p.getOwnedMessages().add(newMessage);
     scenario_p.getOwnedInteractionFragments().add(messageEnd1);
     scenario_p.getOwnedInteractionFragments().add(messageEnd2);

     NameGenerator.computeMessageEndName(messageEnd1);
     NameGenerator.computeMessageEndName(messageEnd2);
     
     return newMessage;
   }

   public static SequenceMessage createReturnMessage(Scenario scenario_p,
       SequenceMessage message_p) {
     return createReturnMessage(scenario_p, message_p, new ArrayList<Event>());
   }
   
   /**
    * Creates a return message call of a message
    * @param scenario_p The target scenario in which the message has to be added
    * @param message_p The original message
    * @return The return message
    */
   public static SequenceMessage createReturnMessage(Scenario scenario_p,
       SequenceMessage message_p,
       List<Event> listEvents_p) {

     /*
      * 1- Create the return message by swaping the caller 
      * and the callee of the message in parameter
      */    
     SequenceMessage newMessage 
       = createMessageCall(scenario_p, 
                           // Swaped
                           message_p.getReceivingEnd().getCovered().getRepresentedInstance(), 
                           message_p.getSendingEnd().getCovered().getRepresentedInstance(),
                           message_p.getName(),
                           MessageKind.REPLY,
                           listEvents_p);
     /*
      * 2- Create the execution between the receiving message end of the message in parameter
      * and the new sending message end of the return message
      */
     Execution execution = InteractionFactory.eINSTANCE.createExecution();
     execution.setStart(message_p.getReceivingEnd());
     execution.setFinish(newMessage.getSendingEnd());

     /*
      * 3- Create the events
      */
     Event sentOperation = InteractionFactory.eINSTANCE.createEventSentOperation();
     newMessage.getSendingEnd().setEvent(sentOperation);

     Event receiveOperation = InteractionFactory.eINSTANCE.createEventReceiptOperation();
     newMessage.getReceivingEnd().setEvent(receiveOperation);
     
     listEvents_p.add(sentOperation);
     listEvents_p.add(receiveOperation);
     
     /*
      * 4- Add the new execution to the scenario
      */    
     scenario_p.getOwnedTimeLapses().add(execution);
     
     NameGenerator.computeExecutionName(execution);

     return newMessage;
   }
   
}
