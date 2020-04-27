/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.helpers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 */
public class LockHelper {

  private static LockHelper _instance;
  private Map<Control, Boolean> _controlStatus;
  private Map<Control, ControlDecoration> _decorationRegistry;

  private LockHelper() {
    // do nothing
  }

  public static LockHelper getInstance() {
    if (null == _instance) {
      _instance = new LockHelper();
    }
    return _instance;
  }
  
  private Map<Control, Boolean> getControlStatus() {
    if (null == _controlStatus) {
      _controlStatus = new HashMap<Control, Boolean>();
    }
    return _controlStatus;
  }

  private Map<Control, ControlDecoration> getDecorationRegistry() {
    if (null == _decorationRegistry) {
      _decorationRegistry = new HashMap<Control, ControlDecoration>();
    }
    return _decorationRegistry;
  }

  /**
   * @param control
   */
  public void decorate(Control control) {
    ControlDecoration decoration = new ControlDecoration(control, SWT.TOP | SWT.LEFT);
    decoration.setImage(CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_LOCK));
    decoration.setDescriptionText("This field is locked"); //$NON-NLS-1$
    decoration.hide();
    getDecorationRegistry().put(control, decoration);
  }

  /**
   * @param control
   * @param enabled
   */
  public void enable(Control control, boolean enabled) {
    if (null != control && !control.isDisposed()) {
      if (enabled) {
        Boolean previousState = getControlStatus().remove(control);
        if (null != previousState) {
          control.setEnabled(previousState.booleanValue());
        } else {
          control.setEnabled(enabled);
        }
      } else {
        getControlStatus().put(control, Boolean.valueOf(control.getEnabled()));
        control.setEnabled(enabled);
      }
      update(control, enabled);
    }
  }

  /**
   * @param control
   * @param enabled
   */
  public void update(Control control, boolean enabled) {
  }
}
