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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreSwitch;

/**
 * Tools for the Semantic (a.k.a. Simplified) Capella meta model.
 * 
 * The shared instance does not emit any log messages. If you want, you can create your own instance by using one of the static factory methods
 * <code>newXYZInstance(...)</code>, passing a Logger. The tool methods will then log to the given logger, for example, getEAllReferences will log a
 * message for each reference that is invisible in the semantic meta model. To avoid spamming the log, an internal set (per instance) is 
 * maintained to avoid logging a message for a given meta model element more than once.
 */
public class SemanticModelUtil {

  /**
   * Annotation source used to indicate semantic epackages, metaclasses and features.
   * @Deprecated use SemanticCapellaMetadata.SOURCE_SEMANTIC
   */
  @Deprecated
  public static final String SEMANTIC = SimplifiedCapellaMetadata.SOURCE_SEMANTIC;

  /**
   * Annotation key used to indicate containment semantics.
   * @Deprecated use SemanticCapellaMetadata.KEY_FEATURE
   */
  @Deprecated
  public static final String KEY_CONTAINMENT = SimplifiedCapellaMetadata.KEY_DETAILS_FEATURE;

  /*
   * Our logger
   */
  protected Logger _logger;

  /*
   * The shared instance.
   */
  private static final SemanticModelUtil __semanticInstance = new SemanticModelUtil(SimplifiedCapellaMetadata.INSTANCE, null);

  /*
   * Decides if an EModelElement is visible or not
   */
  private final EcoreSwitch<Boolean> _ecoreSwitch;

  /*
   * Remembers for which element we already sent a debug message to avoid spamming the log.
   */
  private final Set<EModelElement> _messageSent;
  
  /*
   * The metadata that backs this instance
   */
  protected final SimplifiedCapellaMetadata _metadata;
  
  /**
   * @deprecated use create() instead.
   * @param logger
   * @return
   */
  @Deprecated
  public static SemanticModelUtil newSemanticInstance(Logger logger){
	  return create(SimplifiedCapellaMetadata.INSTANCE, logger);
  }
  
  /**
   * Returns the shared non-logging instance.
   */
  public static SemanticModelUtil getInstance() {
    return __semanticInstance;
  }
  
  /**
   * Returns the semantic instance, which always behaves <i>semantically</i>, independent of the value of
   * <code>ISemanticCommonPreferences.isSemanticMode()</code>. if the preference is <code>false</code>
   * @Deprecated use getInstance()
   */
  @Deprecated
  public static SemanticModelUtil getSemantic() {
    return __semanticInstance;
  }
  
  /**
   * Creates a new instance using supplied metadata and logger.
   * @param metadata_p Never null.
   * @param logger_p May be null.
   */
  public static SemanticModelUtil create(SimplifiedCapellaMetadata metadata_p, Logger logger_p){
	  return new SemanticModelUtil(metadata_p, logger_p);
  }

  SemanticModelUtil(SimplifiedCapellaMetadata metadata_p, Logger logger_p) {
    _messageSent = new HashSet<EModelElement>();
    _ecoreSwitch = new EcoreSwitch<Boolean>() {
      @SuppressWarnings("boxing")
      @Override
      public Boolean caseEStructuralFeature(EStructuralFeature feature_p) {
    	  return _metadata.isNavigable(feature_p);
      }

      @SuppressWarnings("boxing")
      @Override
      public Boolean caseEClassifier(EClassifier clazz_p) {
        return _metadata.isSemantic(clazz_p);
      }

      @Override
      public Boolean defaultCase(EObject e_p) {
        return Boolean.TRUE;
      }

    };
    _logger = logger_p;
    _metadata = metadata_p;
  }

  /**  
   * Semantic containments are EReferences that are navigable 
   * references that either have the containment annotation key set, or
   * are "normal" containment references.
   **/
  public final boolean isSemanticContainment(EReference reference_p) {
	  return _metadata.isContainment(reference_p);
  }

  /**
   * Returns the feature that semantically contains the given element. This may look weird, since in semantic mode elements are split into several
   * 'virtual' containment features in order to obtain strongly typed content subsets. Most importantly in semantic mode this may return an EReference that has
   * 'containment' set to false!
   * @see EObject.eContainingFeature()
   * @param element_p
   * @throws NoContainmentException if the element has a non-null container and no navigable containment feature can be found
   */
  public EStructuralFeature eContainingFeature(EObject element_p) throws NoContainmentException {

    // FIXME this will break probably for feature maps
    EStructuralFeature containingFeature = element_p.eContainingFeature();
    
    if (containingFeature == null){
    	return null;
    }
    
    if (_metadata.isNavigable(containingFeature)){
    	return containingFeature;
    }
    
    // the element's containing feature is not navigable so we must now search
    // all "navigable containment" features of the container and its supertypes
    // the element. This will feel a bit weird now..
    containingFeature = null;
    EObject container = element_p.eContainer();
    if (container != null) {
    	List<EReference> allContainments = container.eClass().getEAllReferences();
    	for (EReference candidate : allContainments) {

    	  if (!container.eIsSet(candidate)){
    	    continue;
    	  }
    	
    	  // no need to check other containment references
          if (candidate.isContainment()) {
            continue;
          }

          if (!(_metadata.isNavigable(candidate) && _metadata.isContainment(candidate))){
        	  continue;
          }
          
          // we don't have to search references whose declared type
          // incompatible with this element's type
          if (!candidate.getEReferenceType().isSuperTypeOf(element_p.eClass())) {
            continue;
          }

          // now search
          if (candidate.isMany()) {
            if (((List<?>) container.eGet(candidate)).contains(element_p)) {
              containingFeature = candidate;
              break;
            }
          } else if (container.eGet(candidate) == element_p) {
              containingFeature = candidate;
              break;
          }
        }

    	if (containingFeature == null) {
          // None of the container's "annotated containment" features
          // contains this element.
          throw new NoContainmentException();
        }
      }
    return containingFeature;
  }

  /**
   * Only considers semantic contents of the argument.
   * 
   * @see SimplifiedCapellaMetadata.isContainment
   * @see SimplifiedCapellaMetadata.isNavigable
   * @param element_p
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<EObject> eContents(EObject element_p) {

    // a set to detect and warn if we find the same element twice (which
    // indicates a bug in the metamodel definition/implementation)
    Set<EObject> result = new LinkedHashSet<EObject>();
    for (EReference ref : element_p.eClass().getEAllReferences()) {
      if (_metadata.isNavigable(ref) && _metadata.isContainment(ref)) {
        Object children = element_p.eGet(ref);
        if (children != null) {
          if (ref.isMany()) {
            for (EObject e : ((List<EObject>) children)) {
            	if (_metadata.isSemantic(e.eClass())){
            		if (!result.add(e)) {
            			logDuplicateContainment(element_p, e, ref);
            		}
            	} else {
            		logNonSemanticClass(e.eClass());
            	}
            }
          } else if (_metadata.isSemantic(((EObject) children).eClass())){
        	  if (!result.add((EObject) children)) {
        		  logDuplicateContainment(element_p, (EObject) children, ref);
        	  }
          } else {
        	  logNonSemanticClass(((EObject) children).eClass());
          }
        }
      } else if (ref.isContainment() && ((_logger != null) && _logger.isDebugEnabled() && !_messageSent.contains(ref))) {
        _messageSent.add(ref);
        _logger.debug("[Semantic M2] Filtering non-navigable children: " + LogHelper.makeFeatureDescription(ref)); //$NON-NLS-1$						
      }
    }
    return new ArrayList<EObject>(result);
  }

  /**
 * @param eClass_p
 */
private void logNonSemanticClass(EClass eClass_p) {
	if (_logger != null && _logger.isDebugEnabled() && !_messageSent.contains(eClass_p)){
  	  _logger.debug("[Semantic M2] Filtering instances of non-semantic class: " + eClass_p.getName()); //$NON-NLS-1$
	}
}

/**
   * In semantic mode, non-navigable references are not considered.
   * @see EObject.getEAllReferences()
   */
  public List<EReference> getEAllReferences(EClass clazz_p) {
    List<EReference> result = new ArrayList<EReference>();
    for (EReference ref : clazz_p.getEAllReferences()) {
      if (_metadata.isNavigable(ref)) {
        result.add(ref);
      } else if ((_logger != null) && _logger.isDebugEnabled() && !_messageSent.contains(ref)) {
        _messageSent.add(ref);
        _logger.debug("[Semantic M2] Filtering non-navigable reference: " + LogHelper.makeFeatureDescription(ref)); //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * In semantic mode, non-navigable attributes are not considered.
   * @see EObject.getEAllAttributes()
   */
  public List<EAttribute> getEAllAttributes(EClass clazz_p) {
    List<EAttribute> result = new ArrayList<EAttribute>();
    for (EAttribute attrib : clazz_p.getEAllAttributes()) {
      if (_metadata.isNavigable(attrib)) {
        result.add(attrib);
      } else if ((_logger != null) && _logger.isDebugEnabled() && !_messageSent.contains(attrib)) {
        _messageSent.add(attrib);
        _logger.debug("[Semantic M2] Filtering non-navigable attribute: " + LogHelper.makeFeatureDescription(attrib)); //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * In semantic mode, non navigable features are not considered.
   * @see EObject.getEStructuralFeatures()
   * @param clazz_p
   * @return
   */
  public List<EStructuralFeature> getEStructuralFeatures(EClass clazz_p) {
    List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
    for (EStructuralFeature feature : clazz_p.getEStructuralFeatures()) {
      if (_metadata.isNavigable(feature)) {
        result.add(feature);
      }
    }
    return result;
  }

  /**
   * If semantic mode is disabled, returns true in all cases. If semantic mode is enabled, different rules apply for different types of elements: - An
   * EClassifier is visible if it is tagged with the 'SEMANTIC' annotation. - An EStructuralFeature is visible if it is tagged with the 'NAVIGABLE' annotation.
   * - All other types of elements are visible. FIXME spec?
   * @param element_p the element to check
   * @return whether the element is visible or not.
   */
  @SuppressWarnings("boxing")
  public boolean isVisible(EModelElement element_p) {
    return _ecoreSwitch.doSwitch(element_p);
  }

  public static class NoContainmentException extends Exception {
    private static final long serialVersionUID = 1L;
  }

  private void logDuplicateContainment(EObject parent_p, EObject child, EReference one_p) {
    if (_logger != null) {
      _logger.error("[Semantic M2] Containment error: " + child); //$NON-NLS-1$
      _logger.error("[Semantic M2] Containment error: " + child.eContainingFeature()); //$NON-NLS-1$
      _logger.error("[Semantic M2] Containment error: " + one_p); //$NON-NLS-1$
    }
  }

}
