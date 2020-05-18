/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.emf.ecore.EObject;

/**
 * A connection abstraction between a source and a target element.
 */
public class Connection {

  private final EObject connection;
  private final EObject source;
  private final EObject target;

  public Connection(EObject connection, EObject source, EObject target) {
    this.connection = connection;
    this.source = source;
    this.target = target;
  }

  public EObject getConnection() {
    return connection;
  }

  public EObject getSource() {
    return source;
  }

  public EObject getTarget() {
    return target;
  }

}