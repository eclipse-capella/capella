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
package org.polarsys.capella.core.commands.preferences;

/**
 */
public class CapellaPreferenceNode {

  /*
	 * 
	 */
  private String key;

  /*
	 * 
	 */
  private Boolean value;

  /**
	 * 
	 */
  public CapellaPreferenceNode(String _key, Boolean _value) {
    this.key = _key;
    this.value = _value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key_p) {
    key = key_p;
  }

  public Boolean getValue() {
    return value;
  }

  public void setValue(Boolean value_p) {
    value = value_p;
  }

}
