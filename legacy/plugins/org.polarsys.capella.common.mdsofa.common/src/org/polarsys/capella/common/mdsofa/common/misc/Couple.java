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
package org.polarsys.capella.common.mdsofa.common.misc;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Key, value object.
 */
public class Couple<K, V> {
  // Key reference.
  private K _key;
  // Value reference.
  private V _value;

  /**
   * Constructor.
   * @param key_p
   * @param value_p
   */
  public Couple(K key_p, V value_p) {
    _key = key_p;
    _value = value_p;
  }
  
  public Couple(Couple<K, V> source) {
    this._key = source._key;
    this._value = source._value;
  }

  /**
   * Get key.
   * @return K
   */
  public K getKey() {
    return _key;
  }

  /**
   * Get value.
   * @return V
   */
  public V getValue() {
    return _value;
  }

  /**
   * Set key with given value.
   * @param key_p key to set. void
   */
  public void setKey(K key_p) {
    _key = key_p;
  }

  /**
   * Set value with given value.
   * @param value_p the value to set
   */
  public void setValue(V value_p) {
    _value = value_p;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String keyString = (null != _key) ? _key.toString() : ICommonConstants.EMPTY_STRING;
    String valueString = (null != _value) ? _value.toString() : ICommonConstants.EMPTY_STRING;
    return new StringBuffer(keyString).append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER).append(valueString).append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER).toString();
  }
}
