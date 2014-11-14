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
package org.polarsys.capella.core.command.recorder.internal.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import org.polarsys.capella.common.command.recorder.core.writer.ITXTConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * Utility class for Capella {@link IProject} and others...
 */
public class CapellaProjectUtils {
  
  public static String DEFAULT_VALUE = ITXTConstants.NOT_FOUND_STRING;
  
  /** Returns the nature Id for Capella Project */
  public static String getCapellaProjectNatureId() {
    //FIXME Nature of Capella project is defined inside an ui plugin. As This plug-ins is not ui...
    return "org.polarsys.capella.project.nature"; //$NON-NLS-1$
  }
 
  /** return id of object */
  public static String getID(EObject eobject_p) {
    String result = ICommonConstants.EMPTY_STRING;
    
    if (null != eobject_p ) {
      if ( ModellingcorePackage.Literals.MODEL_ELEMENT.isSuperTypeOf(eobject_p.eClass()) ) {
        ModelElement me = (ModelElement) eobject_p;
        result = me.getId();
      }
    }
    
    return result;
  }
  
  
  /**
   * Check whether a given {@link EObject} is a Capella element.
   * @param eobject_p
   * @return
   */
  public static boolean isCapellaElement(EObject eobject_p) {
    
    boolean result = false;
    
    if ( null != eobject_p) {
      EClass eclass = eobject_p.eClass();
      result = CapellacorePackage.Literals.CAPELLA_ELEMENT.isSuperTypeOf(eclass);
    }
    
    return result;
  }
  
  /**
   * Check whether a given {@link EObject} is a {@link NamedElement}.
   * @param eobject_p the {@link EObject} to check.
   * @return an <code>null</code> object whether condition is not filled.
   */
  public static AbstractNamedElement isNamedElementElement(EObject eobject_p) {
    
    AbstractNamedElement result = null;
    
    if (
        isOfType(eobject_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT, false)
    ) {
      result = (AbstractNamedElement) eobject_p;
    }

    return result;
  }
  
  /**
   * Check whether a given {@link EObject} is a {@link DSemanticDecorator}.
   * @param eobject_p the {@link EObject} to check.
   * @return an <code>null</code> object whether condition is not filled.
   */
  public static DSemanticDecorator isDSemanticDecorator(EObject eobject_p) {
    
    DSemanticDecorator result = null;
    
    if (
        isOfType(eobject_p, ViewpointPackage.Literals.DSEMANTIC_DECORATOR, false)
    ) {
      result = (DSemanticDecorator) eobject_p;
    }

    return result;
  }
  
  /**
   * For internal use
   */
  private static boolean isOfType(EObject eobject_p, EClass eclass, boolean strictMode) {
    
    boolean result = false;
    
    if (null != eobject_p && null != eclass) {
      result = eclass.isSuperTypeOf(eobject_p.eClass());
      if (result && strictMode && eclass != eobject_p.eClass() ) {
        result = false;
      }
    }
    
    return result;
  }
  
  /**
   * Return the readable name of an {@link EObject} e.g. it's name whether the {@link EObject}
   * is a "named" Capella element; whether it's a DDiagramElement linked to a Capella semantic element,
   * it's eClass "name" otherwise
   *  
   * @param eobject_p
   * @return an empty String whether the target object is null.
   */
  public static String getReadableName(EObject eobject_p) {
    String result = ICommonConstants.EMPTY_STRING;
    
    DSemanticDecorator dsd = isDSemanticDecorator(eobject_p);
    
    EObject tgt = 
      null != dsd ?
      dsd.getTarget():
      eobject_p
    ;
    
    /** diagram and table case specific case */
    if ( null != dsd) {
      EClass tgtEclass = dsd.eClass();
      if ( 
        ViewpointPackage.Literals.DREPRESENTATION.isSuperTypeOf(tgtEclass)
      ) {
        result = ((DRepresentation) dsd).getName();
      }

      return result;
    }
      
    AbstractNamedElement tgtSemantic = isNamedElementElement(tgt);

    if ( null != tgtSemantic ) {
      result = tgtSemantic.getName();
      if (null == result || result.equals(ICommonConstants.EMPTY_STRING)) {
        result = eobject_p.eClass().getName();
      }
    } else if ( null != eobject_p ){
      result = eobject_p.eClass().getName();
    }
    
    
    // last security
    if ( null == result || 0 == result.length() ) {
      result = DEFAULT_VALUE;
    }
    
    return result;
  }
  
}
