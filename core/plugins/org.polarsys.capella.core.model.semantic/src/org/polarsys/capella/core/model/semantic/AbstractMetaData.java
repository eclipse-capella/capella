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
package org.polarsys.capella.core.model.semantic;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 *
 */
public class AbstractMetaData {

  private final String _source;
  private final Map<EModelElement, EAnnotation> _mapping;
  
  public AbstractMetaData(String source_p, Map<EModelElement, EAnnotation> mapping_p){
    _source = source_p;
    _mapping = mapping_p;
  }
  
  public EAnnotation getAnnotation(EModelElement element_p, boolean create){
    EAnnotation result = null;
    if (_mapping != null){
      result = _mapping.get(element_p);
    } else {
      result = element_p.getEAnnotation(_source);
    }
    if (result == null && create){
      result = EcoreFactory.eINSTANCE.createEAnnotation();
      result.setSource(_source);
      if (_mapping != null){
        _mapping.put(element_p, result);
      } else {
        element_p.getEAnnotations().add(result);
      }
    }
    return result;
  }
  
  protected void deleteAnnotation(EModelElement element_p){
    if (_mapping != null){
      _mapping.remove(element_p);
    } else {
      EAnnotation annotation = element_p.getEAnnotation(_source);
      if (annotation != null){
        EcoreUtil.delete(annotation);
      }
    }
    
  }
  
}
