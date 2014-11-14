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
package org.polarsys.capella.common.linkedtext.ui;

import org.eclipse.jface.text.Position;

public class LinkedTextHyperlink extends Position {

  private final Object _target;

  private LinkedTextHyperlink(int offset_p, int length_p, Object target_p){
    super(offset_p, length_p);
    _target = target_p;
  }

  public static LinkedTextHyperlink create(int offset_p, int length_p, Object target_p){
    return new LinkedTextHyperlink(offset_p, length_p, target_p);
  }

  public Object getTarget(){
    return _target;
  }

}