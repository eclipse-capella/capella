/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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