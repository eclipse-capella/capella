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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;

/**
 * 
 */
public class BusinessQueriesProvider {

  /**
	 * 
	 */
  private static BusinessQueriesProvider _instance = null;
  public static final String BUSINESS_QUERIES_EXTENSION_ID = "MDEBusinessQueries"; //$NON-NLS-1$

  /**
	 * 
	 */
  public static final String BUSINESS_QUERIES_PLUGIN_ID = "org.polarsys.capella.core.data.business.queries"; //$NON-NLS-1$
  private List<IBusinessQuery> _businessQueriesCache = null;

  /**
	 * 
	 */
  private BusinessQueriesProvider() {
    // do nothing
  }

  /**
	 * 
	 */
  public List<IBusinessQuery> getAllContributions() {
    if (null == _businessQueriesCache) {
      _businessQueriesCache = new ArrayList<IBusinessQuery>();
      List<IConfigurationElement> BQProvider =
          Arrays.asList(ExtensionPointHelper.getConfigurationElements(BUSINESS_QUERIES_PLUGIN_ID, BUSINESS_QUERIES_EXTENSION_ID));
      for (IConfigurationElement configurationElement : BQProvider) {
        IBusinessQuery contrib = (IBusinessQuery) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (contrib != null) {
          _businessQueriesCache.add(contrib);
        }
      }
    }
    return _businessQueriesCache;
  }

  /**
	 * 
	 */
  public IBusinessQuery getContribution(EClass cls_p, EStructuralFeature feature_p) {
    List<IBusinessQuery> lst = getAllContributions();
    for (IBusinessQuery contrib : lst) {
      if (contrib.getEStructuralFeatures() != null) {
        if (cls_p.equals(contrib.getEClass()) && contrib.getEStructuralFeatures().contains(feature_p)) {
          return contrib;
        }
      }
    }
    return null;
  }

  /**
	 * 
	 */
  public static BusinessQueriesProvider getInstance() {
    if (_instance == null) {
      _instance = new BusinessQueriesProvider();
    }
    return _instance;
  }
}
