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
   * @param scenario_p The scenario
   * @param systemComponent_p The system
   * @param componentList_p The component list
   * @param filteredInstanceRoleList_p The list of instance role
   * @param filteredMessages_p The filtered messages
   * @param filteredAbstractEndList_p The filtered abstract end list
   * @param filteredExecutionList_p The filtered execution list
   * @param filteredEventList_p The filtered event list
   */
  public static void retrieveFilteredScenario(Scenario scenario_p, 
      Component systemComponent_p,
      List<Component> componentList_p,
      List<InstanceRole> filteredInstanceRoleList_p,
      List<SequenceMessage> filteredMessages_p,
      List<AbstractEnd> filteredAbstractEndList_p,
      List<TimeLapse> filteredExecutionList_p, 
      List<Event> filteredEventList_p) {

    // 0- Retrieves component instances of external components
    List<Part> externalComponentInstanceList 
      = ContextQuery.findComponentInstanceList(componentList_p);
    
    // 0- Retrieves component instances of external components    
    List<Part> systemComponentInstanceList
      = ContextQuery.findComponentInstanceList(systemComponent_p);
  
    List<InteractionFragment> abstractEnds = scenario_p.getOwnedInteractionFragments();
    List<TimeLapse> executions = scenario_p.getOwnedTimeLapses();
    
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
              filteredAbstractEndList_p.add(abstractEnd);
              filteredEventList_p.add(abstractEnd.getEvent());   
              
              // Add unique message
              if(!filteredMessages_p.contains(sequenceMessage)) {
                filteredMessages_p.add(sequenceMessage);
              }
              
              // Add unique instance role
              InstanceRole instanceRole = messageEnd.getCovered();
              if(!filteredInstanceRoleList_p.contains(instanceRole)) {
                filteredInstanceRoleList_p.add(instanceRole);
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
              filteredAbstractEndList_p.add(executionEnd);
              filteredEventList_p.add(executionEnd.getEvent());
              
              // Add unique instance role
              InstanceRole instanceRole = executionEnd.getCovered();
              if(!filteredInstanceRoleList_p.contains(instanceRole)) {
                filteredInstanceRoleList_p.add(instanceRole);
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
      if(filteredAbstractEndList_p.contains(startEnd)
          && filteredAbstractEndList_p.contains(finishEnd)) {
        if(!filteredExecutionList_p.contains(execution)) {
          filteredExecutionList_p.add(execution);
        }
      }
    }
  }
  
  
  /**
   * Specifies whether the sequence message is involved in the interaction
   * between the system and the external components
   * @param sequenceMessage_p The sequence message
   * @param systemComponentInstanceList_p The component instances of the system
   * @param externalComponentInstanceList_p The component instances of the external components
   * @return <code>true</code> if this is the case
   */
  private static boolean isSequenceMessageInvolvedInInteraction(SequenceMessage sequenceMessage_p,
      List<Part> systemComponentInstanceList_p,
      List<Part> externalComponentInstanceList_p) {
      AbstractInstance sendingComponentInstance 
      = sequenceMessage_p.getSendingEnd().getCovered().getRepresentedInstance();
      AbstractInstance receivingComponentInstance 
      = sequenceMessage_p.getReceivingEnd().getCovered().getRepresentedInstance();
    
    // Retain only elements which are linked to the new interaction
    return(externalComponentInstanceList_p.contains(sendingComponentInstance)
        && systemComponentInstanceList_p.contains(receivingComponentInstance))
        || (externalComponentInstanceList_p.contains(receivingComponentInstance)
        && systemComponentInstanceList_p.contains(sendingComponentInstance));
  }
  
  /**
   * Specifies whether the execution in involved in the interaction between the system
   * and the external components.
   * @param execution_p The execution
   * @param systemComponentInstanceList_p The component instances of the system
   * @param externalComponentInstanceList_p The component instances of the external components
   * @return <code>true</code> if this is the case
   */
  private static boolean isExecutionInvolvedInInteraction(Execution execution_p,
      List<Part> systemComponentInstanceList_p,
      List<Part> externalComponentInstanceList_p) {
    
    // No need to test execution ends
    boolean isInvolved = true;
    
    InteractionFragment startEnd = execution_p.getStart();
    InteractionFragment finishEnd = execution_p.getFinish();
    
    // Tests start end
    if (startEnd instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) startEnd;
      // Test whether the message is involved in the interaction
      SequenceMessage message = messageEnd.getMessage();
      if(!isSequenceMessageInvolvedInInteraction(message, 
                                                systemComponentInstanceList_p, 
                                                externalComponentInstanceList_p)) {
        isInvolved = false;
      }
    }
    
    // Tests finish end
    if (finishEnd instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) finishEnd;
      SequenceMessage message = messageEnd.getMessage();        
      // Test whether the message is involved in the interaction
      if(!isSequenceMessageInvolvedInInteraction(message, 
                                                 systemComponentInstanceList_p, 
                                                 externalComponentInstanceList_p)) {
         isInvolved = false;
       }
    }      
    
    return isInvolved;
  }
}
