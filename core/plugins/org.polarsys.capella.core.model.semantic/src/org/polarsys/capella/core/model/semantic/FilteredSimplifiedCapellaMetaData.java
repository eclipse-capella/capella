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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.model.semantic.internal.SemanticModelActivator;

/**
 * A SimplifiedCapellaMetadata implementation that uses an extension point mechanism that works
 * like a package registry. For a registered package, all its EClasses are considered non-semantic
 * and all its ERefererences are considered non-navigable. This offers a way to override package 
 * annotations/absence of annotations to completely ignore elements from a given package in
 * the simplified capella metamodel.
 */
public class FilteredSimplifiedCapellaMetaData implements SimplifiedCapellaMetadata {

  public static final String NAME_PACKAGE_FILTER_ELEMENT = "packageFilter"; //$NON-NLS-1$
  public static final String ATTRIBUTE_NS_URI = "nsURI"; //$NON-NLS-1$
  public static final String EXTENSION_SEMANTIC_METADATA = "semanticMetadata"; //$NON-NLS-1$
  
  private Collection<String> packageFilters;
  private Set<EPackage> unfilteredEPackages;
  private final SimplifiedCapellaMetadata delegate;

  public FilteredSimplifiedCapellaMetaData(SimplifiedCapellaMetadata delegate_p){
    delegate = delegate_p;
    reloadFilteredPackagesFromRegistry();
  }
  
  /**
   * @param feature_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isNavigable(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean isNavigable(EStructuralFeature feature_p) {
    return isFiltered(feature_p.getEContainingClass().getEPackage()) ? false : delegate.isNavigable(feature_p);
  }

  /**
   * @param eReference_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isContainment(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public boolean isContainment(EReference eReference_p) {
    return delegate.isContainment(eReference_p);
  }

  /**
   * @param eReference_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getContainment(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public EReference getContainment(EReference eReference_p) {
    return delegate.getContainment(eReference_p);
  }

  /**
   * @param eReference_p
   * @param eContainmentReference_p
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setContainment(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EReference)
   */
  @Override
  public void setContainment(EReference eReference_p, EReference eContainmentReference_p) {
    delegate.setContainment(eReference_p, eContainmentReference_p);
  }

  /**
   * @param eClassifier_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isSemantic(org.eclipse.emf.ecore.EClassifier)
   */
  @Override
  public boolean isSemantic(EClassifier eClassifier_p) {
    return isFiltered(eClassifier_p.getEPackage()) ? false : delegate.isSemantic(eClassifier_p);
  }

  /**
   * @param eClassifier_p
   * @param semantic_p
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setSemantic(org.eclipse.emf.ecore.EClassifier, boolean)
   */
  @Override
  public void setSemantic(EClassifier eClassifier_p, boolean semantic_p) {
    delegate.setSemantic(eClassifier_p, semantic_p);
  }

  /**
   * @param feature_p
   * @param navigable_p
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setNavigable(org.eclipse.emf.ecore.EStructuralFeature, boolean)
   */
  @Override
  public void setNavigable(EStructuralFeature feature_p, boolean navigable_p) {
    delegate.setNavigable(feature_p, navigable_p);
  }

  /**
   * @param ePackage_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedNsPrefix(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public String getSimplifiedNsPrefix(EPackage ePackage_p) {
    return delegate.getSimplifiedNsPrefix(ePackage_p);
  }

  /**
   * @param ePackage_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedNsURI(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public String getSimplifiedNsURI(EPackage ePackage_p) {
    return delegate.getSimplifiedNsURI(ePackage_p);
  }

  /**
   * @param eNamedElement_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedName(org.eclipse.emf.ecore.ENamedElement)
   */
  @Override
  public String getSimplifiedName(ENamedElement eNamedElement_p) {
    return delegate.getSimplifiedName(eNamedElement_p);
  }

  /**
   * @param ePackage_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isProcessAnnotations(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public boolean isProcessAnnotations(EPackage ePackage_p) {
    return delegate.isProcessAnnotations(ePackage_p);
  }

  /**
   * @param ePackage_p
   * @param semantic_p
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setProcessAnnotations(org.eclipse.emf.ecore.EPackage, boolean)
   */
  @Override
  public void setProcessAnnotations(EPackage ePackage_p, boolean semantic_p) {
    delegate.setProcessAnnotations(ePackage_p, semantic_p);
  }

 
  /**
   * @param feature_p
   * @param processor_p
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isExcludeFrom(org.eclipse.emf.ecore.EStructuralFeature, java.lang.String)
   */
  @Override
  public boolean isExcludeFrom(EStructuralFeature feature_p, String processor_p) {
    return delegate.isExcludeFrom(feature_p, processor_p);
  }
  
  
  /**
   * @param ePackage_p
   * @return
   */
  private boolean isFiltered(EPackage ePackage_p) {
    if (unfilteredEPackages.contains(ePackage_p)){
      return false;
    }
    for (String packageFilter : packageFilters){
      if (ePackage_p.getNsURI().equals(packageFilter)){
        return true;
      }
    }
    unfilteredEPackages.add(ePackage_p);
    return false;
  }
 
  private void reloadFilteredPackagesFromRegistry(){
    packageFilters = new ArrayList<String>();
    unfilteredEPackages = Collections.synchronizedSet(new HashSet<EPackage>());
    for (IConfigurationElement e : Platform.getExtensionRegistry().getConfigurationElementsFor(SemanticModelActivator.PLUGIN_ID, EXTENSION_SEMANTIC_METADATA)){
      if (NAME_PACKAGE_FILTER_ELEMENT.equals(e.getName())){
        String nsURI = e.getAttribute(ATTRIBUTE_NS_URI);
        if (nsURI != null){
          packageFilters.add(nsURI);
        }
      }
    }
  }
  
}
