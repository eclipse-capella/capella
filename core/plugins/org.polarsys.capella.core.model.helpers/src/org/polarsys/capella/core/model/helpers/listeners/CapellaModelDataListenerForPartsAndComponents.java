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
package org.polarsys.capella.core.model.helpers.listeners;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 */
public class CapellaModelDataListenerForPartsAndComponents extends CapellaModelDataListener {
  /**
   * This listener will rename:
   * <li>all the Parts typed by a PartitionableElement, according to the new PartitionableElement's name
   * <li>the name of the part's Type, according its new name
   * <li>the name of the part, according its new Type
   * <li>all the InstanceRoles typed by a Part, according to the new Part's name
   * 
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
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
    if (feature != null) {
      if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME)) {
        final String value = notification.getNewStringValue();
        Object notifier = notification.getNotifier();

        // In Reusable Components mode, we disable name synchronization between type and parts
        if (!TriStateBoolean.True
            .equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) notifier))) {
 
          if (notifier instanceof Component) {
            for (final Part part : ((Component) notifier).getRepresentingParts()) {
              synchronizeName(part, value);
            }

          } else if (notifier instanceof Part) {
            final Type type = ((Part) notifier).getType();
            synchronizeName(type, value);
          }
        }

        if (notifier instanceof Part) {
          final Part partition = ((Part) notifier);

          // only sync unique per-scenario instanceroles
          Collection<EObject> representingInstanceRoles = EObjectExt.getReferencers(partition, InteractionPackage.Literals.INSTANCE_ROLE,
              InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE);

          if (representingInstanceRoles.size() > 0) {
            Multimap<EObject, InstanceRole> mm = ArrayListMultimap.create();
            for (EObject instanceRole :  representingInstanceRoles) {
              mm.put(instanceRole.eContainer(), (InstanceRole) instanceRole);
            }
            for (EObject scenario : mm.keySet()) {
              Collection<InstanceRole> ir = mm.get(scenario);
              if (scenario == null || ir.size() == 1) {
                synchronizeName(ir.iterator().next(), value);
              }
            }
          }

        } else if (notifier instanceof InstanceRole) {
          // don't sync if there's another instancerole representing the same instance in the scenario
          InstanceRole ir = (InstanceRole) notifier;
          if (ir.eContainer() instanceof Scenario) {
            Scenario sc = (Scenario) ir.eContainer();
            for (InstanceRole i : sc.getOwnedInstanceRoles()) {
              if (i != ir && i.getRepresentedInstance() == ir.getRepresentedInstance()) {
                return;
              }
            }
          }
          synchronizeName(ir.getRepresentedInstance(), value);
        }

      } else if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
        Object value = notification.getNewValue();
        Object notifier = notification.getNotifier();
        // Check the project that contains given notifier is not in Reusable Components mode.
        if (!TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) notifier))) {
          return;
        }
        if ((notifier instanceof Part) && (value instanceof AbstractType)) {
          final Part part = (Part) notifier;
          final AbstractType type = (AbstractType) value;
          synchronizeName(part, type.getName());
        }
      }
    }
  }

  /**
   * Change the name of the given element to the given value if necessary
   */
  private void synchronizeName(final AbstractNamedElement element, final String value) {
    if ((element != null) && !StringUtils.equals(element.getName(), value)) {
      executeCommand(element, new AbstractReadWriteCommand() {
        public void run() {
          element.setName(value);
        }
      });
    }
  }

  /**
   * @param part
   * @param name
   */
  @Deprecated
  protected void renameInstanceRole(Part part, String name) {
    for (EObject role : EObjectExt.getReferencers(part, InteractionPackage.Literals.INSTANCE_ROLE,
        InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE)) {
      synchronizeName((InstanceRole)role, name);
    }
  }
}
