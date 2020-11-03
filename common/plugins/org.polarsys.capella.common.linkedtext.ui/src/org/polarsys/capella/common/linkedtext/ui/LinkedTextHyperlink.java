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

package org.polarsys.capella.common.linkedtext.ui;

import org.eclipse.jface.text.Position;

public class LinkedTextHyperlink extends Position {

  private final Object _target;

  private LinkedTextHyperlink(int offset, int length, Object target){
    super(offset, length);
    _target = target;
  }

  public static LinkedTextHyperlink create(int offset, int length, Object target){
    return new LinkedTextHyperlink(offset, length, target);
  }

  public Object getTarget(){
    return _target;
  }

}