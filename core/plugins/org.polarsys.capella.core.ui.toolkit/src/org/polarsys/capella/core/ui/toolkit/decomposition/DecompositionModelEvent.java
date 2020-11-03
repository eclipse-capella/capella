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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.EventObject;

/**
 * Class <code>DecompositionModelEvent</code> occurs whenever something happens in <code>DecompositionModel</code>
 */
public class DecompositionModelEvent extends EventObject {

  private static final long serialVersionUID = 4184740568857749228L;

  /**
   * A constant for Decomposition Added Event
   */
  public static final int DECOMPOSITION_ADDED = 1;
  /**
   * A constant for Decomposition Removed Event
   */
  public static final int DECOMPOSITION_REMOVED = 2;
  /**
   * A constant for All Decompositions Removed Event
   */
  public static final int DECOMPOSITION_ALL_REMOVED = 3;
  /**
   * A constant for Decomposition Renamed Event
   */
  public static final int DECOMPOSITION_RENAMED = 4;
  /**
   * A constant for Target Component Added Event
   */
  public static final int TARGET_COMPONENT_ADDED = 5;
  /**
   * A constant for Target Component Removed Event
   */
  public static final int TARGET_COMPONENT_REMOVED = 6;
  /**
   * A constant for All Target Components Removed Event
   */
  public static final int TARGET_COMPONENT_ALL_REMOVED = 7;
  /**
   * A constant for Target Component Renamed Event
   */
  public static final int TARGET_COMPONENT_RENAMED = 8;
  /**
   * A constant for Interface Attached Event
   */
  public static final int TARGET_COMPONENT_INTERFACE_ATTACHED = 9;
  /**
   * A constant for Interface Detached Event
   */
  public static final int TARGET_COMPONENT_INTERFACE_DETACHED = 10;
  /** 
   * A constant for Component Reused Event
   */
  public static final int TARGET_COMPONENT_REUSED = 11;
  /**
   * A constant for Decomposition Finished Event
   */
  public static final int DECOMPOSITION_FINISHED = 12;

  protected int _eventType;
  protected Decomposition _decomposition;
  protected DecompositionComponent _sourceComponent;
  protected DecompositionComponent _targetComponent;
  protected Object _currentData;
  protected boolean _operationSuccess;
  protected DecompositionComponent _reusedComponent;

  /**
   * Constructor
   * @param source_p
   *          the source
   * @param target_p
   *          the target component
   * @param decomposition_p
   *          the Decomposition
   * @param currentData_p
   *          the current data
   * @param eventType_p
   *          the event type
   */
  public DecompositionModelEvent(DecompositionComponent source_p, DecompositionComponent target_p, Decomposition decomposition_p, Object currentData_p,
      int eventType_p) {
    super(source_p);
    this._sourceComponent = source_p;
    this._targetComponent = target_p;
    this._decomposition = decomposition_p;
    this._currentData = currentData_p;
    this._eventType = eventType_p;
    setOperationSuccess(false);
  }
  
  /**
   * Constructor
   * @param source_p
   *          the source
   * @param target_p
   *          the target component
   * @param decomposition_p
   *          the Decomposition
   * @param currentData_p
   *          the current data
   * @param eventType_p
   *          the event type
   */
  public DecompositionModelEvent(DecompositionModel model_p, DecompositionComponent source_p, DecompositionComponent target_p, Decomposition decomposition_p, Object currentData_p,
      int eventType_p) {
    super(model_p);
    this._sourceComponent = source_p;
    this._targetComponent = target_p;
    this._decomposition = decomposition_p;
    this._currentData = currentData_p;
    this._eventType = eventType_p;
    setOperationSuccess(false);
  }

  /**
   * @return the eventType
   */
  public int getEventType() {
    return _eventType;
  }

  /**
   * @return the currentData
   */
  public Object getCurrentData() {
    return _currentData;
  }

  /**
   * @return the decomposition
   */
  public Decomposition getDecomposition() {
    return _decomposition;
  }

  /**
   * @return the sourceComponent
   */
  public DecompositionComponent getSourceComponent() {
    return _sourceComponent;
  }

  /**
   * @return the targetComponent
   */
  public DecompositionComponent getTargetComponent() {
    return _targetComponent;
  }

  /**
   * @return the operationSuccess
   */
  public boolean isOperationSuccess() {
    return _operationSuccess;
  }

  /**
   * @param operationSuccess_p
   *          the operationSuccess to set
   */
  public void setOperationSuccess(boolean operationSuccess_p) {
    _operationSuccess = operationSuccess_p;
  }

  /**
   * @return the reusedComponent
   */
  public DecompositionComponent getReusedComponent() {
    return _reusedComponent;
  }

  /**
   * @param reusedComponent_p the reusedComponent to set
   */
  public void setReusedComponent(DecompositionComponent reusedComponent_p) {
    _reusedComponent = reusedComponent_p;
  }
}
