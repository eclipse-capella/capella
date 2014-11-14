/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * @param control_p
   */
  public void decorate(Control control_p) {
    ControlDecoration decoration = new ControlDecoration(control_p, SWT.TOP | SWT.LEFT);
    decoration.setImage(CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_LOCK));
    decoration.setDescriptionText("This field is locked"); //$NON-NLS-1$
    decoration.hide();
    getDecorationRegistry().put(control_p, decoration);
  }

  /**
   * @param control_p
   * @param enabled_p
   */
  public void enable(Control control_p, boolean enabled_p) {
    if (null != control_p && !control_p.isDisposed()) {
      if (enabled_p) {
        Boolean previousState = getControlStatus().remove(control_p);
        if (null != previousState) {
          control_p.setEnabled(previousState.booleanValue());
        } else {
          control_p.setEnabled(enabled_p);
        }
      } else {
        getControlStatus().put(control_p, Boolean.valueOf(control_p.getEnabled()));
        control_p.setEnabled(enabled_p);
      }
      update(control_p, enabled_p);
    }
  }

  /**
   * @param control_p
   * @param enabled_p
   */
  public void update(Control control_p, boolean enabled_p) {
  }
}
