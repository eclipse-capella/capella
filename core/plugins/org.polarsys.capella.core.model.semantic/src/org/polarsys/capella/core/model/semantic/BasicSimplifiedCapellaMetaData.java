/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
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
	public BasicSimplifiedCapellaMetaData(Map<EModelElement, EAnnotation> map){
	  super(SOURCE_SEMANTIC, map);
	}
 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNavigable(EStructuralFeature feature) {
	  return !isProcessAnnotations(feature.getEContainingClass().getEPackage()) || getAnnotation(feature, false) != null;
	}

	/**
   * @param ePackage
   * @return
   */
	@Override
  public boolean isProcessAnnotations(EPackage ePackage) {
    return getAnnotation(ePackage, false) != null;
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
  public boolean isContainment(EReference reference){
	  return getContainment(reference) != null;
	}
	
	@Override
	public EReference getContainment(EReference reference) {
	  if (reference.isContainment()){
	    return reference;
	  }
	  EAnnotation ann = getAnnotation(reference, false);
	  if (ann != null){
	    String feature = ann.getDetails().get(KEY_DETAILS_FEATURE);
	    if (feature != null){
	      EStructuralFeature source = reference.getEContainingClass().getEStructuralFeature(feature);
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
	public void setContainment(EReference reference, EReference containment){
	  if (!reference.isDerived()){
	    throw new IllegalArgumentException("Containment semantics can only be set on a derived reference"); //$NON-NLS-1$
	  }
	  if (containment == null){
	    EAnnotation ann = getAnnotation(reference, false);
	    if (ann != null){
	      ann.getDetails().remove(KEY_DETAILS_FEATURE);
	    }
	  } else {
	    EAnnotation ann = getAnnotation(reference, true);
	    ann.getDetails().put(KEY_DETAILS_FEATURE, containment.getName());
	  }
	}

	@Override
	public void setNavigable(EStructuralFeature feature, boolean navigable) {
	  if (navigable){
	    getAnnotation(feature, true);
	  } else {
	    deleteAnnotation(feature);
	  }
	}

  @Override
  public void setSemantic(EClassifier classifier, boolean semantic) {
    if (semantic){
      getAnnotation(classifier, true); // simply demand creating the annotation is sufficient
    } else {
      deleteAnnotation(classifier);
    }
  }

  @Override
  public boolean isSemantic(EClassifier classifier) {
    return !isProcessAnnotations(classifier.getEPackage()) || getAnnotation(classifier, false) != null;
  }
 
  @Override
  public boolean isSemantic(EStructuralFeature eStructuralFeature) {
    return !isProcessAnnotations(((EClass)eStructuralFeature.eContainer()).getEPackage()) || getAnnotation(eStructuralFeature, false) != null;
  }
  
  @Override
  public String getSimplifiedNsPrefix(EPackage pack){
    if (pack == EcorePackage.eINSTANCE){
      return pack.getNsPrefix();
    }
      
    EAnnotation annotation = getAnnotation(pack, false);
    if (annotation != null){
      String annotatedSimplifiedNsPrefix = annotation.getDetails().get(KEY_DETAILS_NS_PREFIX);
      if (annotatedSimplifiedNsPrefix != null){
        return annotatedSimplifiedNsPrefix;
      }
    }
    return pack.getNsPrefix();
  }
    
  @Override
  public String getSimplifiedNsURI(EPackage pack){
      
    if (pack == EcorePackage.eINSTANCE){
      return pack.getNsURI();
    }
      
    EAnnotation annotation = getAnnotation(pack, false);
    if (annotation != null){
      String annotatedSimplifiedNsURI = annotation.getDetails().get(KEY_DETAILS_NS_URI);
      if (annotatedSimplifiedNsURI != null){
        return annotatedSimplifiedNsURI;
      }
    }
    return URI.createURI(pack.getNsURI()).appendSegment(DEFAULT_SEMANTIC_NS_URI_SUFFIX).toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSimplifiedName(ENamedElement technical) {
    // if desired, we could add an annotation key 'name' to change the name simplified eclass to something
    // different and make use of that key here. for now the name is just the technical name.
    return technical.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExcludeFrom(EStructuralFeature feature, String processor) {
    boolean result = false;
    EAnnotation annotation = getAnnotation(feature, false);
    if (annotation != null){
      String value = annotation.getDetails().get(KEY_DETAILS_EXCLUDEFROM);
      result = value != null && value.equals(processor);
    }
    return result;
  }

}
