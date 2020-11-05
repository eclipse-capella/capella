/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class InstanceRoleHasDifferentNameThanRepresentedInstance extends AbstractValidationRule {
  /**
   * A container class that encloses all the elements required to efficiently perform the quick fix for the rename.</br>
   * This container will be added to the validation context and used in the quick fix. </br>
   * We are required to inherit from EObject since the validation context can contain only EObjects.
   */
  public static final class InstanceRoleRenamingData extends BasicEObjectImpl {
    public final String newName;
    public final InstanceRole instanceRole;

    public InstanceRoleRenamingData(InstanceRole instanceRole, String newName) {
      this.instanceRole = instanceRole;
      this.newName = newName;
    }
  }

  public static final String ID = "org.polarsys.capella.core.data.interaction.validation.DWF_DS_24"; // $NON-NLS-N$

  @Override
  public IStatus validate(IValidationContext validationContext) {
    EObject target = validationContext.getTarget();
    EMFEventType eventType = validationContext.getEventType();
    List<IStatus> failureStatuses = new ArrayList<>();

    if (eventType == EMFEventType.NULL && target instanceof Scenario) {
      Scenario scenario = (Scenario) target;
      Map<AbstractInstance, Collection<InstanceRole>> representedInstanceToInstanceRoleMap = //
          extractRepresentedInstanceToInstanceRoleMap(scenario);

      for (Map.Entry<AbstractInstance, Collection<InstanceRole>> entry : representedInstanceToInstanceRoleMap
          .entrySet()) {
        AbstractInstance representedInstance = entry.getKey();

        Collection<InstanceRole> instanceRoles = entry.getValue();

        if (haveSingleLifeline(instanceRoles)) {
          String representedInstanceName = representedInstance.getName();
          InstanceRole instanceRole = instanceRoles.iterator().next();
          String instanceRoleName = instanceRole.getName();

          if (!representedInstanceName.equals(instanceRoleName)) {
            IStatus failureStatus = createFailureStatus(validationContext, instanceRole, representedInstance);
            failureStatuses.add(failureStatus);
          }
        }
      }

    }

    return failureStatuses.isEmpty() ? //
        validationContext.createSuccessStatus() : //
        ConstraintStatus.createMultiStatus(validationContext, failureStatuses);
  }

  private Map<AbstractInstance, Collection<InstanceRole>> extractRepresentedInstanceToInstanceRoleMap(
      Scenario scenario) {
    EList<InstanceRole> instanceRoles = scenario.getOwnedInstanceRoles();
    Map<AbstractInstance, Collection<InstanceRole>> representedInstanceToInstanceRoleMap = new HashMap<>();

    for (InstanceRole instanceRole : instanceRoles) {
      AbstractInstance representedInstance = instanceRole.getRepresentedInstance();
      representedInstanceToInstanceRoleMap.computeIfAbsent(representedInstance, key -> new ArrayList<>())
          .add(instanceRole);
    }
    return representedInstanceToInstanceRoleMap;
  }

  private boolean haveSingleLifeline(Collection<InstanceRole> instanceRoles) {
    return instanceRoles.size() == 1;
  }

  private IStatus createFailureStatus(IValidationContext validationContext, InstanceRole instanceRole,
      AbstractInstance representedInstance) {
    String constraintId = validationContext.getCurrentConstraintId();
    IConstraintDescriptor constraintDescriptor = ConstraintRegistry.getInstance().getDescriptor(constraintId);

    InstanceRoleRenamingData renamingData = new InstanceRoleRenamingData(instanceRole, representedInstance.getName());

    Collection<EObject> localResultLocus = new ArrayList<>();
    localResultLocus.add(instanceRole);
    localResultLocus.add(renamingData);

    String messagePattern = constraintDescriptor.getMessagePattern();
    Object[] messageArguments = { instanceRole.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(instanceRole, false), representedInstance.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(representedInstance, false) };

    return ConstraintStatus.createStatus(validationContext, instanceRole, localResultLocus, messagePattern,
        messageArguments);
  }

}
