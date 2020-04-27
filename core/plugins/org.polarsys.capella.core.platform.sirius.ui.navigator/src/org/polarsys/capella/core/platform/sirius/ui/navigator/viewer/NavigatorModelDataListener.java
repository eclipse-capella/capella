/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.capella.core.model.helpers.listeners.CapellaModelDataListener;

/**
 * A ModelDataListener used to monitor changes on name or others features which requires a refresh on the viewer.
 * On such event, it redirects it to the registered callback
 */
public class NavigatorModelDataListener extends CapellaModelDataListener {

  /**
   * Monitored references for additional notifications.
   */
  private static List<EStructuralFeature> __monitoredReferencesForAdditionalNotifications;

  private INotifyChangedListener _callback;

  public NavigatorModelDataListener(INotifyChangedListener callback) {

    _callback = callback;

    // Initialize monitored references for additional notifications.
    initializeMonitoredReferences();

  }

  /**
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
  @SuppressWarnings("synthetic-access")
  @Override
  public void notifyChanged(Notification notification) {
    // Forward only notification related to following features:
    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (__monitoredReferencesForAdditionalNotifications.contains(feature)) {
      _callback.notifyChanged(notification);
    }
  }

  /**
   * Initialize monitored references for additional notifications.
   */
  private void initializeMonitoredReferences() {
    // Initialize the map at first call only.
    if (null == __monitoredReferencesForAdditionalNotifications) {
      __monitoredReferencesForAdditionalNotifications = new ArrayList<EStructuralFeature>(11);
      __monitoredReferencesForAdditionalNotifications.add(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME);
      __monitoredReferencesForAdditionalNotifications.add(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
      // Numeric Reference.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.NUMERIC_REFERENCE__REFERENCED_VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.NUMERIC_REFERENCE__REFERENCED_PROPERTY);
      // Boolean Reference.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_PROPERTY);
      // String Reference.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_PROPERTY);
      // Enumeration Reference.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_PROPERTY);
      // Value Part.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.VALUE_PART__REFERENCED_PROPERTY);
      // Literals Value.
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE__VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE);
      __monitoredReferencesForAdditionalNotifications.add(DatavaluePackage.Literals.LITERAL_STRING_VALUE__VALUE);

      __monitoredReferencesForAdditionalNotifications.add(ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET);
    }
  }

  // Register types we want to get additional notifications, only registered features in the map will send additional notifications.
  protected void registerToDataNotifier(SemanticEditingDomain editingDomain) {
    if (null != editingDomain) {
      DataNotifier dataNotifier = editingDomain.getDataNotifier();
      if (null != dataNotifier) {
        dataNotifier.addAdapter(AbstractNamedElement.class, this);
        dataNotifier.addAdapter(AbstractTypedElement.class, this);
        dataNotifier.addAdapter(NumericReference.class, this);
        dataNotifier.addAdapter(BooleanReference.class, this);
        dataNotifier.addAdapter(StringReference.class, this);
        dataNotifier.addAdapter(EnumerationReference.class, this);
        dataNotifier.addAdapter(ValuePart.class, this);
        dataNotifier.addAdapter(LiteralBooleanValue.class, this);
        dataNotifier.addAdapter(LiteralNumericValue.class, this);
        dataNotifier.addAdapter(LiteralStringValue.class, this);
        dataNotifier.addAdapter(DRepresentationDescriptor.class, this);
      }
    }
  }

  // Remove the registered listener from the data notifier.
  protected void unregisterFromDataNotifier(SemanticEditingDomain editingDomain) {
    if (null != editingDomain) {
      DataNotifier dataNotifier = editingDomain.getDataNotifier();
      if (null != dataNotifier) {
        dataNotifier.remove(this);
      }
    }
  }
}
