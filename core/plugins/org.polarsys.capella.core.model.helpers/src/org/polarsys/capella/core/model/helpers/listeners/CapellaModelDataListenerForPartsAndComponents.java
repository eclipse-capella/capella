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

import java.util.Arrays;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

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
        if (!TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) notifier))) {

          if (notifier instanceof Component) {
            renameElements(((Component) notifier).getRepresentingParts(), value);

          } else if (notifier instanceof Part) {
            final Type type = ((Part) notifier).getType();
            renameElements(Arrays.asList(type), value);
          }
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
          renameElements(Arrays.asList(part), type.getName());
        }
      }
    }
  }
}
