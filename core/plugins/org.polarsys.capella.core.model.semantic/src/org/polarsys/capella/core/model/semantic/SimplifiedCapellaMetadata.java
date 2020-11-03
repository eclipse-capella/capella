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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * High level abstraction to read and write capella semantic metadata.
 * 
 */
public interface SimplifiedCapellaMetadata {

	SimplifiedCapellaMetadata INSTANCE = new FilteredSimplifiedCapellaMetaData(new BasicSimplifiedCapellaMetaData());
	
	/**
	 * The annotation details key used to indicate containment semantics.
	 * Presence of the key alone is not sufficient, see {@code VALUE_DETAILS_CONTAINMENT}.
	 */
	String KEY_DETAILS_FEATURE = "feature"; //$NON-NLS-1$
	
	/**
	 * The annotation source for 'semantic' elements.
	 */
	String SOURCE_SEMANTIC = "http://www.polarsys.org/capella/semantic"; //$NON-NLS-1$
	
	/**
	 * The annotation details key that maps to the 'simplified' namespace uri of an EPackage
	 * If no such details key exists, the simplified namespace uri is simply the
	 * namespace uri of the package with {@code DEFAULT_SEMANTIC_NS_URI} appended
	 * as an additional segment.
	 */
	String KEY_DETAILS_NS_URI = "nsURI"; //$NON-NLS-1$
	
	/**
	 * The annotation details key that indicates the 'simplified' namespace prefix of an EPackage
	 */
	String KEY_DETAILS_NS_PREFIX = "nsPrefix"; //$NON-NLS-1$
	
	/**
	 * The annotation details key for the exclude from semantics.
	 */
	String KEY_DETAILS_EXCLUDEFROM = "excludefrom"; //$NON-NLS-1$
	
	/**
	 * The string that's appended as an additional segment to an EPackages namespace uri.
	 */
	String DEFAULT_SEMANTIC_NS_URI_SUFFIX = "semantic"; //$NON-NLS-1$
	
	
	/**
	 * Is the given reference navigable?
	 * @param reference
	 * @return
	 */
	boolean isNavigable(EStructuralFeature feature);
	
	/**
	 * Is the given reference a containment reference? 
	 * 
	 * Returns true if one of the following is true:
	 * <ul>
	 *  <li> Its containment flag is set to true
	 *  <li> It is an annotated reference which has a details entry <code>KEY_DETAILS_FEATURE</code> set to the name of a containment feature
	 *       defined in the arguments eClass or any of its superclasses.
	 * </ul>
	 */
	boolean isContainment(EReference eReference);
	
	/**
	 * Returns the "true" containment reference for the given reference. This is either the reference itself if it is
	 * a containment reference, or the reference denoted by the semantic containment annotation @see isContainment, or null
	 * if the argument is neither a true nor a semantic containment reference.
	 * @param reference_p
	 */
	EReference getContainment(EReference eReference);

	/**
	 * Set/clear containment semantics on the given reference. To clear the containment
	 * semantics, pass null as the second argument. This will
	 * not clear 'navigable' semantics. To do that use setNavigable(false), which will
	 * also clear containment information.
	 * 
	 * @param reference
	 * @param feature the name of the original containment feature from which 'reference' is derived
	 * @throws IllegalArgumentException if the reference is not derived
	 */
	void setContainment(EReference eReference, EReference eContainmentReference);
	
	/**
	 * Is the given classifier a 'semantic' classifier?
	 */
	boolean isSemantic(EClassifier eClassifier);

	/**
   * Is the given structural feature a 'semantic' structural feature?
   */
	boolean isSemantic(EStructuralFeature eReference);
	/**
	 * Set/clear the 'semantic' state for the given classifier.
	 */
	void setSemantic(EClassifier eClassifier, boolean semantic);

	/**
	 * Set/clear the 'navigable' state for the given reference.
	 */
	void setNavigable(EStructuralFeature feature, boolean navigable);
	
  /**
   * Get the simplified namespace prefix for the given EPackage.
   */
  String getSimplifiedNsPrefix(EPackage ePackage);

  /**
   * Get the simplified namespace URI for the given EPackage.
   */
  String getSimplifiedNsURI(EPackage ePackage);

  /**
   * Get the simplified name fore a named technical model element.
   * @param technical_p
   * @return
   */
  String getSimplifiedName(ENamedElement eNamedElement);

  /**
   * Should semantic annotations be processed on the given package. 
   * 
   * If false, then the packages EClassifiers are by definition
   * semantic and the packages EStructuralFeatures are by definition
   * navigable. 
   */
  boolean isProcessAnnotations(EPackage ePackage);

  /**
   * Set/clear the semantic annotation processor flag on the given package. 
   */
  void setProcessAnnotations(EPackage ePackage, boolean semantic);

  /**
   * Should the given processor exclude the given feature? Attention: Always returns false for non-navigable features.
   *
   * @param feature the feature to query
   * @param processor a processor id
   */
  boolean isExcludeFrom(EStructuralFeature feature, String processor);



}
