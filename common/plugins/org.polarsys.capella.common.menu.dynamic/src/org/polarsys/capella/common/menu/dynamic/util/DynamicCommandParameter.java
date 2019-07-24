/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.menu.dynamic.util;

import org.eclipse.emf.edit.command.CommandParameter;

/**
 * A extension of the CommandParameter class adding support for a custom command label.</br>
 * This class is also used a marker, meaning instances of this class will always lead to the creation of a dynamic
 * action in the <b>DynamicCreationAction</b> class.
 */
public class DynamicCommandParameter extends CommandParameter {

  private String menuLabel;

  public DynamicCommandParameter(Object owner, Object feature, Object value, String menuLabel) {
    super(owner, feature, value);
    this.menuLabel = menuLabel;
  }

  public String getLabel() {
    return menuLabel;
  }

}