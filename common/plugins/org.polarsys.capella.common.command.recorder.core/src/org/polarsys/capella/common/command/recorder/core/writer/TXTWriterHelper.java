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
package org.polarsys.capella.common.command.recorder.core.writer;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Utility methods for  Writers.
 *
 */
public class TXTWriterHelper {

  static {
    String s = System.getProperty("line.separator"); //$NON-NLS-1$
    LINE_SEPARATOR = s == null ? "\n" : s; //$NON-NLS-1$
  }
  
  public final static String LINE_SEPARATOR;
  
  public static String ENTRY_TAG="!ENTRY"; //$NON-NLS-1$
  
  public static String SUBENTRY_TAG="!SUBENTRY"; //$NON-NLS-1$
  
  public static String EXTRADATA_TAG="!DATA"; //$NON-NLS-1$
  
  public static void writeln(Writer writer_p, String line_p) throws IOException {
    
    if ( null!= writer_p ) {
      
      writer_p.write(line_p);
      writeln(writer_p);
    }
    
    return;
  }
  
  public static void writeln(Writer writer_p) throws IOException {
    
    if (null != writer_p) {
      writer_p.write(LINE_SEPARATOR);
    }
    
    return;
  }
  
  public static void writeEntry(Writer writer_p, String line_p) throws IOException {
    
    if ( null != writer_p ) {
      writeln(writer_p, ENTRY_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + line_p);
    }
    
    return;
  }
  
  public static void writeSubEntry(Writer writer_p, int depth, String line_p) throws IOException {
    
    if ( null != writer_p ) {
      writeln(writer_p, 
          SUBENTRY_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + 
          depth +  ICommonConstants.WHITE_SPACE_CHARACTER +
          line_p
      );
    }
    
    return;
  }  
  
  public static void writeExtraDataLine(Writer writer_p, String line_p) throws IOException {
    
    if ( null != writer_p ) {
      writeln(
          writer_p, 
          EXTRADATA_TAG + ICommonConstants.WHITE_SPACE_CHARACTER + line_p
      );
    }
    
    return;
  } 
    
  public static void writeSubEntryForEObject(Writer writer_p, EObject eobject_p, INameResolver nameResolver_p, int depth_p, boolean withContents_p) throws IOException {
    
    INameResolver nameResolver = 
      null == nameResolver_p ? new DefaultNameResolver() : nameResolver_p
    ; 
    
    // The eobject itself
      
    String id = nameResolver.getID(eobject_p);
    
    String str =
      nameResolver.getReadableName(eobject_p)
    ;
    
    if (null != id && !ICommonConstants.EMPTY_STRING.equals(id)) {
      str+=
        ICommonConstants.WHITE_SPACE_CHARACTER +
        ITXTConstants.ID_PRE + id
     ;
    }
    
    writeSubEntry(
        writer_p,
        depth_p,
        str
    );
    
    // Extra data about this object
    if ( true == RecorderCorePreferenceServices.isExtraDataShouldBeSerialized() ) {
      EClass eClass = eobject_p.eClass();
      String uri = eClass.getEPackage().getNsURI();
      writeExtraDataLine(
          writer_p,
          eClass.getName() + ICommonConstants.WHITE_SPACE_CHARACTER + uri
      );      
    }
    
    // Recurse on all EObject contents 
    if ( true == withContents_p ) {
      for (EObject current: eobject_p.eContents()) {
        writeSubEntryForEObject(writer_p, current, nameResolver, depth_p + 1 , true);
      }
    }
    
    
    return;
  }
  
}
