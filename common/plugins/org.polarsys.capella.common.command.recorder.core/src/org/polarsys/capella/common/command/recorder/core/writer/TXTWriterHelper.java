/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.command.recorder.core.writer;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Utility methods for Writers.
 * 
 */
public class TXTWriterHelper {

  static {
    String s = System.getProperty("line.separator"); //$NON-NLS-1$
    LINE_SEPARATOR = s == null ? "\n" : s; //$NON-NLS-1$
  }

  public final static String LINE_SEPARATOR;

  public static String ENTRY_TAG = "!ENTRY"; //$NON-NLS-1$

  public static String SUBENTRY_TAG = "!SUBENTRY"; //$NON-NLS-1$

  public static String EXTRADATA_TAG = "!DATA"; //$NON-NLS-1$

  public static void writeln(Writer writer, String line) throws IOException {
    if (null != writer) {
      writer.write(line);
      writeln(writer);
    }
  }

  public static void writeln(Writer writer) throws IOException {
    if (null != writer) {
      writer.write(LINE_SEPARATOR);
    }
  }

  public static void writeEntry(Writer writer, String line) throws IOException {
    if (null != writer) {
      writeln(writer, ENTRY_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + line);
    }
  }

  public static void writeSubEntry(Writer writer, int depth, String line) throws IOException {
    if (null != writer) {
      writeln(writer, SUBENTRY_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + depth
          + ICommonConstants.WHITE_SPACE_CHARACTER + line);
    }
  }

  public static void writeExtraDataLine(Writer writer, String line) throws IOException {
    if (null != writer) {
      writeln(writer, EXTRADATA_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + line);
    }
  }

  public static void writeSubEntryForEObject(Writer writer, EObject eobject, INameResolver nameResolver, int depth,
      boolean withContents) throws IOException {

    INameResolver resolver = null == nameResolver ? new DefaultNameResolver() : nameResolver;

    // The eobject itself
    String id = resolver.getID(eobject);
    String str = resolver.getReadableName(eobject);

    if (null != id && !ICommonConstants.EMPTY_STRING.equals(id)) {
      str += ICommonConstants.WHITE_SPACE_CHARACTER + ITXTConstants.ID_PRE + id;
    }

    writeSubEntry(writer, depth, str);

    // Extra data about this object
    if (true == RecorderCorePreferenceServices.isExtraDataShouldBeSerialized()) {
      EClass eClass = eobject.eClass();
      String uri = eClass.getEPackage().getNsURI();
      writeExtraDataLine(writer, eClass.getName() + ICommonConstants.WHITE_SPACE_CHARACTER + uri);
    }

    // Recurse on all EObject contents
    if (true == withContents) {
      for (EObject current : eobject.eContents()) {
        writeSubEntryForEObject(writer, current, resolver, depth + 1, true);
      }
    }
  }
}
