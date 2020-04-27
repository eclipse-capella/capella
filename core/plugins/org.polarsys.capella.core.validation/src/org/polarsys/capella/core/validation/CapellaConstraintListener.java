/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.emf.validation.service.ConstraintChangeEvent;
import org.eclipse.emf.validation.service.ConstraintChangeEventType;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintListener;

/**
 */
public class CapellaConstraintListener implements IConstraintListener {

  /**
   * Singleton pattern - unique instance
   */
  private static CapellaConstraintListener _instance;

  private List<IConstraintDescriptor> _registeredConstraints;
  private List<IConstraintDescriptor> _modifiedConstraints;

  /**
   * Singleton pattern - cannot be instantiated by users
   */
  private CapellaConstraintListener() {
    _registeredConstraints = new ArrayList<IConstraintDescriptor>(0);
    _modifiedConstraints = new ArrayList<IConstraintDescriptor>(0);
  }

  /**
   * Singleton pattern - unique instance accessor
   */
  public static CapellaConstraintListener getInstance() {
    if (null == _instance) {
      _instance = new CapellaConstraintListener();
    }
    return _instance;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void constraintChanged(ConstraintChangeEvent event_p) {
    IConstraintDescriptor constraint = event_p.getConstraint();

    if (ConstraintChangeEventType.REGISTERED.equals(event_p.getEventType())) {
      _registeredConstraints.add(constraint);
    } else if (ConstraintChangeEventType.UNREGISTERED.equals(event_p.getEventType())) {
      _registeredConstraints.remove(constraint);
    }
  }

  public List<IConstraintDescriptor> getEnabledByDefaultRegisteredConstraints() {
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();
    for (IConstraintDescriptor desc : _registeredConstraints) {
      if (!EMFModelValidationPreferences.isConstraintDisabledByDefault(desc.getId())) {
        result.add(desc);
      }
    }
    return result;
  }

  public void forceDefaultStateOnRegisteredConstraints() {
    for (IConstraintDescriptor desc : _registeredConstraints) {
      String id = desc.getId();
      boolean defaultState = EMFModelValidationPreferences.isConstraintDisabledByDefault(id);
      boolean currentState = EMFModelValidationPreferences.isConstraintDisabled(id);
      if (currentState != defaultState) {
        EMFModelValidationPreferences.setConstraintDisabled(id, defaultState);
        _modifiedConstraints.add(desc);
      }
    }
  }

  public void restoreStateOnRegisteredConstraints() {
    for (IConstraintDescriptor desc : _modifiedConstraints) {
      String id = desc.getId();
      boolean currentState = EMFModelValidationPreferences.isConstraintDisabled(id);
      EMFModelValidationPreferences.setConstraintDisabled(id, !currentState);
    }
    _modifiedConstraints.clear();
  }
}
