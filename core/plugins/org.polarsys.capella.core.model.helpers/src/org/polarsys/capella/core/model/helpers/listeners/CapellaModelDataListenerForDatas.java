/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 */
public class CapellaModelDataListenerForDatas extends CapellaModelDataListener {

  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  private static String DELETION_MSG = "Setting \"{0}\" class to primitive implies the deletion of element: \"{1}\" ({2})"; //$NON-NLS-1$

  /**
   * This listener will:
   * <li> remove the associations (and the pending properties) referencing a class when it becomes 'primitive'
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
    if (notification.getEventType() != Notification.SET)
      return;

    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (feature != null) {
      if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
        final Object notifier = notification.getNotifier();
        if (notifier instanceof Property) {
          EObject value = (EObject) notification.getNewValue();
          if (value instanceof DataType)
          {
            if (!((Property) notifier).getAggregationKind().equals(AggregationKind.COMPOSITION)) {
              executeCommand((Property) notifier, new AbstractReadWriteCommand() {
                public void run() {
                  ((Property) notifier).setAggregationKind(AggregationKind.COMPOSITION);
                }
              });
            }
          }
        }
      } else if (feature.equals(InformationPackage.Literals.CLASS__IS_PRIMITIVE)) {
        final Object notifier = notification.getNotifier();
        if (notifier instanceof Class) {
          Object value = notification.getNewValue();
          if ((value instanceof Boolean) && Boolean.TRUE.equals(value)
           && CapellaModelPreferencesPlugin.getDefault().isPrimitiveSynchroAllowed())
          {
            final List<Association> assocToBeRemoved = new ArrayList<Association>();
            for (EObject ref : EObjectExt.getReferencers((Class) notifier, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
              if (ref instanceof Property) {
                for (EObject assoc : EObjectExt.getReferencers(ref, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS)) {
                  if (assoc instanceof Association) {
                    if (!assocToBeRemoved.contains(assoc)) {
                      assocToBeRemoved.add((Association) assoc);
                    }
                  }
                }
              }
            }
            executeCommand((Class) notifier, new AbstractReadWriteCommand() {
              public void run() {
                removeAssociations((Class) notifier, assocToBeRemoved);
              }
            });
          }
        }
      }
    }
  }

  /**
   * When the class 'cls' becomes 'primitive' the associations referencing it shall be removed
   *
   * @param cls
   * @param associations
   */
  protected void removeAssociations(Class cls, List<Association> associations) {
    Iterator<Association> associationIt = associations.iterator();
    while (associationIt.hasNext()) {
      Association association = associationIt.next();
      for (Property property : association.getNavigableMembers()) {
        property.setAggregationKind(AggregationKind.COMPOSITION);
      }
      removeProperties(cls, new ArrayList<Property>(association.getOwnedMembers()));
      traceDeletion(cls, association);
      association.destroy();
    }
  }

  /**
   * @param cls
   * @param properties
   */
  protected void removeProperties(Class cls, List<Property> properties) {
    Iterator<Property> propertyIt = properties.iterator();
    while (propertyIt.hasNext()) {
      Property property = propertyIt.next();
      traceDeletion(cls, property);
      property.destroy();
    }
  }

  /**
   * @param element1
   * @param element2
   */
  private void traceDeletion(NamedElement element1, NamedElement element2) {
    String loggedMsg = MessageFormat.format(DELETION_MSG, element1.getName(), element2.getName(), element2.eClass().getName());
    _logger.info(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.MODEL, element1));
  }
}
