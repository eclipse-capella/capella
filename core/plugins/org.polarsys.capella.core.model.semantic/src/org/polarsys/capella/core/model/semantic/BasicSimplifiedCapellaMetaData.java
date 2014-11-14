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
package org.polarsys.capella.core.model.semantic;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Default implementation for SimplifiedCapellaMetaData.
 * 
 * Two operation modes are provided, see constructor documentation for details.
 *
 * 
 */
public class BasicSimplifiedCapellaMetaData extends AbstractMetaData implements SimplifiedCapellaMetadata {
  
	/**
	 * Reads/Writes metadata directly from Ecore model elements. As such,
	 * this should not be used if you intend to use the setter methods
	 * to modify metadata on generated packages, because those packages
	 * are frozen and write operations will fail.
	 */
	public BasicSimplifiedCapellaMetaData(){
	  this(null);
	}
	
	/**
	 * This allows to re-define/override metadata defined on model elements 
	 * by using an external mapping. Read and write operations will 
	 * completely bypasses metadata defined in the ecore model itself
	 * and use the provided map to read and store annotations.
	 * 
	 * @param annotationMap
	 */
	public BasicSimplifiedCapellaMetaData(Map<EModelElement, EAnnotation> map_p){
	  super(SOURCE_SEMANTIC, map_p);
	}
 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNavigable(EStructuralFeature feature_p) {
	  return !isProcessAnnotations(feature_p.getEContainingClass().getEPackage()) || getAnnotation(feature_p, false) != null;
	}

	/**
   * @param ePackage_p
   * @return
   */
	@Override
  public boolean isProcessAnnotations(EPackage ePackage_p) {
    return getAnnotation(ePackage_p, false) != null;
  }

	@Override
	public void setProcessAnnotations(EPackage ePackage, boolean process){
	  if (process){
      getAnnotation(ePackage, true); // simply demand creating the annotation is sufficient
    } else {
      deleteAnnotation(ePackage);
    }
	}
	
  @Override
  public boolean isContainment(EReference reference_p){
	  return getContainment(reference_p) != null;
	}
	
	@Override
	public EReference getContainment(EReference reference_p) {
	  if (reference_p.isContainment()){
	    return reference_p;
	  }
	  EAnnotation ann = getAnnotation(reference_p, false);
	  if (ann != null){
	    String feature = ann.getDetails().get(KEY_DETAILS_FEATURE);
	    if (feature != null){
	      EStructuralFeature source = reference_p.getEContainingClass().getEStructuralFeature(feature);
	      if (source instanceof EReference){
	        if (((EReference) source).isContainment()){
	          return (EReference) source;
	        }
	      }
	    }
	  }
	  return null;
	}
  
	@Override
	public void setContainment(EReference reference_p, EReference containment_p){
	  if (!reference_p.isDerived()){
	    throw new IllegalArgumentException("Containment semantics can only be set on a derived reference"); //$NON-NLS-1$
	  }
	  if (containment_p == null){
	    EAnnotation ann = getAnnotation(reference_p, false);
	    if (ann != null){
	      ann.getDetails().remove(KEY_DETAILS_FEATURE);
	    }
	  } else {
	    EAnnotation ann = getAnnotation(reference_p, true);
	    ann.getDetails().put(KEY_DETAILS_FEATURE, containment_p.getName());
	  }
	}

	@Override
	public void setNavigable(EStructuralFeature feature_p, boolean navigable_p) {
	  if (navigable_p){
	    getAnnotation(feature_p, true);
	  } else {
	    deleteAnnotation(feature_p);
	  }
	}

  @Override
  public void setSemantic(EClassifier classifier_p, boolean semantic_p) {
    if (semantic_p){
      getAnnotation(classifier_p, true); // simply demand creating the annotation is sufficient
    } else {
      deleteAnnotation(classifier_p);
    }
  }

  @Override
  public boolean isSemantic(EClassifier classifier_p) {
    return !isProcessAnnotations(classifier_p.getEPackage()) || getAnnotation(classifier_p, false) != null;
  }
 
  @Override
  public String getSimplifiedNsPrefix(EPackage pack_p){
    if (pack_p == EcorePackage.eINSTANCE){
      return pack_p.getNsPrefix();
    }
      
    EAnnotation annotation = getAnnotation(pack_p, false);
    if (annotation != null){
      String annotatedSimplifiedNsPrefix = annotation.getDetails().get(KEY_DETAILS_NS_PREFIX);
      if (annotatedSimplifiedNsPrefix != null){
        return annotatedSimplifiedNsPrefix;
      }
    }
    return pack_p.getNsPrefix();
  }
    
  @Override
  public String getSimplifiedNsURI(EPackage pack_p){
      
    if (pack_p == EcorePackage.eINSTANCE){
      return pack_p.getNsURI();
    }
      
    EAnnotation annotation = getAnnotation(pack_p, false);
    if (annotation != null){
      String annotatedSimplifiedNsURI = annotation.getDetails().get(KEY_DETAILS_NS_URI);
      if (annotatedSimplifiedNsURI != null){
        return annotatedSimplifiedNsURI;
      }
    }
    return URI.createURI(pack_p.getNsURI()).appendSegment(DEFAULT_SEMANTIC_NS_URI_SUFFIX).toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSimplifiedName(ENamedElement technical_p) {
    // if desired, we could add an annotation key 'name' to change the name simplified eclass to something
    // different and make use of that key here. for now the name is just the technical name.
    return technical_p.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExcludeFrom(EStructuralFeature feature_p, String processor_p) {
    boolean result = false;
    EAnnotation annotation = getAnnotation(feature_p, false);
    if (annotation != null){
      String value = annotation.getDetails().get(KEY_DETAILS_EXCLUDEFROM);
      result = value != null && value.equals(processor_p);
    }
    return result;
  }

}
