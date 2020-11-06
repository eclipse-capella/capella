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
package org.polarsys.capella.core.model.helpers.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.utils.NamingHelper;

/**
 * 
 * This listener will synchronize the name of InstanceRole and its represented element
 * 
 */
public class CapellaModelDataListenerForInstanceRole extends CapellaModelDataListener {
  @Override
  public void notifyChanged(Notification notification) {
    // pre-condition: call contributed filters
    if (filterNotification(notification)) {
      return;
    }

    // pre-condition: only SET notifications are wanted
    if (notification.getEventType() != Notification.SET) {
      return;
    }

    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature)) {
      String value = notification.getNewStringValue();
      Object notifier = notification.getNotifier();

      if (notifier instanceof AbstractInstance) {
        AbstractInstance abstractInstance = ((AbstractInstance) notifier);

        // only sync unique per-scenario instance roles
        Collection<EObject> representingInstanceRoles = EObjectExt.getReferencers(abstractInstance,
            InteractionPackage.Literals.INSTANCE_ROLE, InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE);

        if (!representingInstanceRoles.isEmpty()) {
          Map<Scenario, List<InstanceRole>> scenario2IRs = new HashMap<>();
          for (EObject instanceRole : representingInstanceRoles) {
            if (instanceRole instanceof InstanceRole && instanceRole.eContainer() instanceof Scenario) {
              Scenario scenario = (Scenario) instanceRole.eContainer();
              List<InstanceRole> instanceRoles = scenario2IRs.computeIfAbsent(scenario, k -> new ArrayList<>());
              instanceRoles.add((InstanceRole) instanceRole);
            }
          }

          for (Entry<Scenario, List<InstanceRole>> entry : scenario2IRs.entrySet()) {
            Collection<InstanceRole> iRs = entry.getValue();
            if (iRs.size() == 1) {
              NamingHelper.synchronizeName(iRs.iterator().next(), value);
            }
          }
        }

      } else if (notifier instanceof InstanceRole) {
        // don't sync if there's another instance role representing the same instance in the scenario
        InstanceRole ir = (InstanceRole) notifier;
        if (ir.eContainer() instanceof Scenario) {
          Scenario sc = (Scenario) ir.eContainer();
          for (InstanceRole i : sc.getOwnedInstanceRoles()) {
            if (i != ir && i.getRepresentedInstance() == ir.getRepresentedInstance()) {
              return;
            }
          }
        }
        NamingHelper.synchronizeName(ir.getRepresentedInstance(), value);
      }

    }
  }
}
