/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.exchanges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * This implementation of <code>IExchangesCreator</code> is used for Operational Entities
 */
public class EntityExchangesCreator extends DefaultExchangesCreator {

  /**
   * Constructor
   * 
   * @param component_p
   */
  public EntityExchangesCreator(Component component_p) {
    super(component_p);
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    boolean comMeanCreated = false;
    List<AbstractFunction> lf = new ArrayList<>();
    if (_component instanceof Entity) {
      Entity entity = (Entity) _component;
      // Gets the list of the sub components
      Collection<Component> subComponents = ComponentExt.getAllSubUsedComponents(entity);
      subComponents.add(entity);
      for (Component component : subComponents) {
        for (AbstractFunction af : component.getAllocatedFunctions()) {
          lf.add(af);
        }
      }
      for (Component component : subComponents) {
        EList<AbstractFunction> laf = component.getAllocatedFunctions();
        for (AbstractFunction abstractFunction : laf) {
          comMeanCreated |= handleFunction(abstractFunction, entity);
        }
      }
      // Now test whether some Roles are allocated to the entity on which the action has been launched or not
      comMeanCreated |= computeRoles(entity);
    }
    if (!comMeanCreated) {
      String message = "No Communication mean has been created.";
      EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName());
      logger.info(eMessage);
    }
  }

  /**
   * Handles the exchanges creations which are found through roles allocated to the given entity.
   * 
   * @param entity_p
   *          the entity
   */
  protected boolean computeRoles(Entity entity_p) {
    boolean comMeanCreated = false;
    EList<RoleAllocation> roleAllocations = EntitiesExchangesHelper.getRoleAllocations(entity_p);

    for (RoleAllocation roleAllocation : roleAllocations) {
      EList<ActivityAllocation> activityAllocations = EntitiesExchangesHelper
          .getActivityAllocations(roleAllocation.getRole());
      for (ActivityAllocation activityAllocation : activityAllocations) {
        OperationalActivity activity = activityAllocation.getActivity();
        comMeanCreated |= handleFunction(activity, entity_p);
      }
    }
    return comMeanCreated;
  }

  /**
   * Handles the given function exchange creation for the given entity
   * 
   * @param function_p
   *          the function
   * @param entity_p
   *          the entity
   */
  protected boolean handleFunction(AbstractFunction function_p, Entity entity_p) {
    boolean comMeanCreated = false;
    for (ActivityEdge output : function_p.getOutgoing()) {
      if (output instanceof FunctionalExchange) {
        FunctionalExchange fe = (FunctionalExchange) output;
        if (fe.getIncomingComponentExchangeFunctionalExchangeRealizations().isEmpty()) {
          AbstractFunction targetF = (AbstractFunction) fe.getTarget();
          // role
          EList<AbstractFunctionalBlock> allocationBlocks = targetF.getAllocationBlocks();
          if (allocationBlocks.size() == 0) {
            // Tries to check whether some roles are implementing the function or not
            allocationBlocks = new BasicEList<>();
            Role implementingRole = EntitiesExchangesHelper.getImplementingRole(targetF);
            EList<Entity> implementingEntities = EntitiesExchangesHelper.getImplementingEntities(implementingRole);
            for (Entity entity : implementingEntities) {
              allocationBlocks.add(entity);
            }
          }
          for (AbstractFunctionalBlock allocationBlock : allocationBlocks) {
            try {
              Entity allocationBlockAsEntity = (Entity) allocationBlock;
              if (!doesFunctionalExchangeAlreadyHaveACommunicationMean(fe, entity_p, allocationBlockAsEntity)) {
                createCommunicationMean(fe, entity_p, allocationBlockAsEntity);
                comMeanCreated = true;
              }
            } catch (ClassCastException exception_p) {
              // FIXME maybe something should be logged here...
            }
          }
        }
      }
    }
    //
    // Handles function inputs:
    //
    for (ActivityEdge input : function_p.getIncoming()) {
      if (input instanceof FunctionalExchange) {
        FunctionalExchange fe = (FunctionalExchange) input;
        if (fe.getIncomingComponentExchangeFunctionalExchangeRealizations().isEmpty()) {
          AbstractFunction sourceF = (AbstractFunction) fe.getSource();
          EList<AbstractFunctionalBlock> allocationBlocks = sourceF.getAllocationBlocks();

          if (allocationBlocks.size() == 0) {
            // Tries to check whether some roles are implementing the function or not
            allocationBlocks = new BasicEList<>();
            Role implementingRole = EntitiesExchangesHelper.getImplementingRole(sourceF);
            EList<Entity> implementingEntities = EntitiesExchangesHelper.getImplementingEntities(implementingRole);
            for (Entity entity : implementingEntities) {
              allocationBlocks.add(entity);
            }
          }
          for (AbstractFunctionalBlock allocationBlock : allocationBlocks) {
            try {
              Entity allocationBlockAsEntity = (Entity) allocationBlock;
              if (!doesFunctionalExchangeAlreadyHaveACommunicationMean(fe, allocationBlockAsEntity, entity_p)) {
                createCommunicationMean(fe, allocationBlockAsEntity, entity_p);
                comMeanCreated = true;
              }
            } catch (ClassCastException exception_p) {
              // FIXME maybe something should be logged here...
            }
          }
        }

      }
    }
    return comMeanCreated;
  }

  /**
   * Test whether the given functional exchange has already been allocated to a communication mean between the given
   * entities.
   * 
   * @param functionalExchange_p
   *          the functional exchange
   * @param sourceEntity_p
   *          the first entity
   * @param targetEntity_p
   *          the second entity
   * @return true if the functional exchange has already been allocated to a communication mean, false otherwise
   */
  protected boolean doesFunctionalExchangeAlreadyHaveACommunicationMean(FunctionalExchange functionalExchange_p,
      Entity sourceEntity_p, Entity targetEntity_p) {
    if (sourceEntity_p != null) {
      for (AbstractInformationFlow flow : sourceEntity_p.getInformationFlows()) {
        if (flow instanceof CommunicationMean) {
          CommunicationMean communicationMean = (CommunicationMean) flow;
          if (communicationMean.getAllocatedFunctionalExchanges().contains(functionalExchange_p)
              && (communicationMean.getSource() == sourceEntity_p)
              && (communicationMean.getTarget() == targetEntity_p)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Creates a communication mean for the given functional exchange between the given entities
   * 
   * @param functionalExchange_p
   *          the functional exchange
   * @param source_p
   *          the source entity
   * @param target_p
   *          the target entity
   */
  protected void createCommunicationMean(FunctionalExchange functionalExchange_p, Entity source_p, Entity target_p) {

    CommunicationMean ce = OaFactory.eINSTANCE.createCommunicationMean(functionalExchange_p.getName());
    ce.setSource(source_p);
    ce.setTarget(target_p);
    ComponentExchangeExt.attachToDefaultContainer(ce);
    CapellaElementExt.creationService(ce);

    ComponentExchangeFunctionalExchangeAllocation allocation = FaFactory.eINSTANCE
        .createComponentExchangeFunctionalExchangeAllocation();
    ce.getOwnedComponentExchangeFunctionalExchangeAllocations().add(allocation);
    allocation.setTargetElement(functionalExchange_p);
    allocation.setSourceElement(ce);
    CapellaElementExt.creationService(allocation);
    String message = "the Communication mean " + ce.getName()
        + " has been succefully created between the entity source " + source_p.getLabel() + " and the entity target "
        + target_p.getLabel();
    EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(), Arrays.asList(ce, source_p, target_p));
    logger.info(eMessage);
  }
}
