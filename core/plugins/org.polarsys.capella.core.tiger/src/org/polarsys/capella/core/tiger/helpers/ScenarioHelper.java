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

import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;

/**
 * Scenario helper for filtering the scenarios implied in the bridge transformations.
 */
public class ScenarioHelper {

  /**
   * Retrieve the useful elements which could create a filtered scenario
   * with only the instance role list specified in parameter
   * @param scenario The scenario
   * @param systemComponent The system
   * @param componentList The component list
   * @param filteredInstanceRoleList The list of instance role
   * @param filteredMessages The filtered messages
   * @param filteredAbstractEndList The filtered abstract end list
   * @param filteredExecutionList The filtered execution list
   * @param filteredEventList The filtered event list
   */
  public static void retrieveFilteredScenario(Scenario scenario, 
      Component systemComponent,
      List<Component> componentList,
      List<InstanceRole> filteredInstanceRoleList,
      List<SequenceMessage> filteredMessages,
      List<AbstractEnd> filteredAbstractEndList,
      List<TimeLapse> filteredExecutionList, 
      List<Event> filteredEventList) {

    // 0- Retrieves component instances of external components
    List<Part> externalComponentInstanceList 
      = ContextQuery.findComponentInstanceList(componentList);
    
    // 0- Retrieves component instances of external components    
    List<Part> systemComponentInstanceList
      = ContextQuery.findComponentInstanceList(systemComponent);
  
    List<InteractionFragment> abstractEnds = scenario.getOwnedInteractionFragments();
    List<TimeLapse> executions = scenario.getOwnedTimeLapses();
    
    // 1- Filter ends, events and messages
    for (InteractionFragment interactionFragment : abstractEnds) {
      if (interactionFragment instanceof AbstractEnd) {
        AbstractEnd abstractEnd = (AbstractEnd) interactionFragment;

        AbstractInstance componentInstance  = abstractEnd.getCovered().getRepresentedInstance();
        
        // The instance role is involved in the interaction
        if(externalComponentInstanceList.contains(componentInstance)
            || systemComponentInstanceList.contains(componentInstance)) {
          
          // Parse the current message end
          if (abstractEnd instanceof MessageEnd) {
            MessageEnd messageEnd = (MessageEnd) abstractEnd;
            SequenceMessage sequenceMessage = messageEnd.getMessage();
          
            // The message must be involved in the possible interactions between the system
            // and the external components
            if(isSequenceMessageInvolvedInInteraction(sequenceMessage, 
                                                      systemComponentInstanceList, 
                                                      externalComponentInstanceList)) {
              filteredAbstractEndList.add(abstractEnd);
              filteredEventList.add(abstractEnd.getEvent());   
              
              // Add unique message
              if(!filteredMessages.contains(sequenceMessage)) {
                filteredMessages.add(sequenceMessage);
              }
              
              // Add unique instance role
              InstanceRole instanceRole = messageEnd.getCovered();
              if(!filteredInstanceRoleList.contains(instanceRole)) {
                filteredInstanceRoleList.add(instanceRole);
              }
            }
          }
          
          // Parse the current execution end
          if (abstractEnd instanceof ExecutionEnd) {
            ExecutionEnd executionEnd = (ExecutionEnd) abstractEnd;
            Execution execution = executionEnd.getExecution();
            
            // The execution must be involved in the interaction between the system and
            // the external components.
            if(isExecutionInvolvedInInteraction(execution, 
                                                systemComponentInstanceList, 
                                                externalComponentInstanceList))
            {
              filteredAbstractEndList.add(executionEnd);
              filteredEventList.add(executionEnd.getEvent());
              
              // Add unique instance role
              InstanceRole instanceRole = executionEnd.getCovered();
              if(!filteredInstanceRoleList.contains(instanceRole)) {
                filteredInstanceRoleList.add(instanceRole);
              }           
            }
          }
        }
      }
    }
    
    // 2- Filter executions
    for (TimeLapse execution : executions) {
      InteractionFragment startEnd = execution.getStart();
      InteractionFragment finishEnd = execution.getFinish();
      
      // Start and finish must be in the interaction
      if(filteredAbstractEndList.contains(startEnd)
          && filteredAbstractEndList.contains(finishEnd)) {
        if(!filteredExecutionList.contains(execution)) {
          filteredExecutionList.add(execution);
        }
      }
    }
  }
  
  
  /**
   * Specifies whether the sequence message is involved in the interaction
   * between the system and the external components
   * @param sequenceMessage The sequence message
   * @param systemComponentInstanceList The component instances of the system
   * @param externalComponentInstanceList The component instances of the external components
   * @return <code>true</code> if this is the case
   */
  private static boolean isSequenceMessageInvolvedInInteraction(SequenceMessage sequenceMessage,
      List<Part> systemComponentInstanceList,
      List<Part> externalComponentInstanceList) {
      AbstractInstance sendingComponentInstance 
      = sequenceMessage.getSendingEnd().getCovered().getRepresentedInstance();
      AbstractInstance receivingComponentInstance 
      = sequenceMessage.getReceivingEnd().getCovered().getRepresentedInstance();
    
    // Retain only elements which are linked to the new interaction
    return(externalComponentInstanceList.contains(sendingComponentInstance)
        && systemComponentInstanceList.contains(receivingComponentInstance))
        || (externalComponentInstanceList.contains(receivingComponentInstance)
        && systemComponentInstanceList.contains(sendingComponentInstance));
  }
  
  /**
   * Specifies whether the execution in involved in the interaction between the system
   * and the external components.
   * @param execution The execution
   * @param systemComponentInstanceList The component instances of the system
   * @param externalComponentInstanceList The component instances of the external components
   * @return <code>true</code> if this is the case
   */
  private static boolean isExecutionInvolvedInInteraction(Execution execution,
      List<Part> systemComponentInstanceList,
      List<Part> externalComponentInstanceList) {
    
    // No need to test execution ends
    boolean isInvolved = true;
    
    InteractionFragment startEnd = execution.getStart();
    InteractionFragment finishEnd = execution.getFinish();
    
    // Tests start end
    if (startEnd instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) startEnd;
      // Test whether the message is involved in the interaction
      SequenceMessage message = messageEnd.getMessage();
      if(!isSequenceMessageInvolvedInInteraction(message, 
                                                systemComponentInstanceList, 
                                                externalComponentInstanceList)) {
        isInvolved = false;
      }
    }
    
    // Tests finish end
    if (finishEnd instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) finishEnd;
      SequenceMessage message = messageEnd.getMessage();        
      // Test whether the message is involved in the interaction
      if(!isSequenceMessageInvolvedInInteraction(message, 
                                                 systemComponentInstanceList, 
                                                 externalComponentInstanceList)) {
         isInvolved = false;
       }
    }      
    
    return isInvolved;
  }
}
