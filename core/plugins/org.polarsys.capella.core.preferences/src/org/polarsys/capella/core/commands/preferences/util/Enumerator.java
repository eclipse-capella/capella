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
package org.polarsys.capella.core.commands.preferences.util;


/**
 * An interface implemented by the enumerators of a type-safe enum.
 */
public interface Enumerator
{
  /**
   * Returns the name of the enumerator.
   * @return the name.
   */
  String getName();

  /**
   * Returns the <code>int</code>value of the enumerator.
   * @return the value.
   */
  int getValue();
  
  /**
   * Returns the literal value of the enumerator.
   * @return the literal.
   */
  String getLiteral();
}
