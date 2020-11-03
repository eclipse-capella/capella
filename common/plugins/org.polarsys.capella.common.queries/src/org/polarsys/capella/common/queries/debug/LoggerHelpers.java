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
package org.polarsys.capella.common.queries.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 */
public class LoggerHelpers {

  public static DefaultPrinter<? extends Object> defaultPrinter = new DefaultPrinter<Object>();

  public static <X> String printCollection(Collection<X> collection, ObjectPrinter<X> printer) {
    StringBuffer buffer = new StringBuffer();
    Iterator<X> it = collection.iterator();
    while (it.hasNext()) {
      buffer.append(printer.print(it.next()));
      if (it.hasNext()) {
        buffer.append('\n');
      }
    }
    return buffer.toString();
  }

  public static <X, Y> String printHashTable(Hashtable<X, Y> table, ObjectPrinter<X> keyPrinter, ObjectPrinter<Y> valuePrinter) {
    int maxKeyStringLentgh = 0;
    List<String> keysStrings = new ArrayList<String>();
    List<String> valueStrings = new ArrayList<String>();
    for (X key : table.keySet()) {
      String keyString = null;
      if (keyPrinter != null) {
        keyString = keyPrinter.print(key);
      } else {
        keyString = key.toString();
      }
      keysStrings.add(keyString);
      int keyStringLength = keyString.length();
      if (maxKeyStringLentgh < keyStringLength) {
        maxKeyStringLentgh = keyStringLength;
      }
      Y value = table.get(key);
      String valueString = null;
      if (valuePrinter != null) {
        valueString = valuePrinter.print(value);
      } else {
        valueString = value.toString();
      }
      valueStrings.add(valueString);
    }
    StringBuffer buffer = new StringBuffer();
    int keysStringsSize = keysStrings.size();
    for (int i = 0; i < keysStringsSize; i++) {
      String keyString = keysStrings.get(i);
      buffer.append(normalizeStringLength(keyString, maxKeyStringLentgh));
      buffer.append(" : ");
      buffer.append(valueStrings.get(i));
      if (i < (keysStringsSize - 1)) {
        buffer.append('\n');
      }
    }
    return buffer.toString();
  }

  private static String normalizeStringLength(String value, int length) {
    StringBuffer b = new StringBuffer();
    b.append(value);
    for (int i = b.length(); i < length; i++) {
      b.append(' ');
    }
    return b.toString();
  }

}
