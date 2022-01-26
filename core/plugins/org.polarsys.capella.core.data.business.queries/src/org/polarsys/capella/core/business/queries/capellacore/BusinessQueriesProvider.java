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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.business.queries.BusinessQueriesPlugin;
import org.polarsys.capella.core.business.queries.IBusinessQuery;


public class BusinessQueriesProvider {

  private static BusinessQueriesProvider _instance = null;
  public static final String BUSINESS_QUERIES_EXTENSION_ID = "MDEBusinessQueries"; //$NON-NLS-1$

  private List<IBusinessQuery> _businessQueriesCache = null;
  private Map<SimpleEntry<EClass, EStructuralFeature>, IBusinessQuery> businessQueriesMap;


  private BusinessQueriesProvider() {
    // do nothing
  }


  public List<IBusinessQuery> getAllContributions() {
    if (null == _businessQueriesCache) {
      _businessQueriesCache = new ArrayList<IBusinessQuery>();
      List<IConfigurationElement> BQProvider =
          Arrays.asList(ExtensionPointHelper.getConfigurationElements(FrameworkUtil.getBundle(BusinessQueriesPlugin.class).getSymbolicName(), BUSINESS_QUERIES_EXTENSION_ID));
      for (IConfigurationElement configurationElement : BQProvider) {
        IBusinessQuery contrib = (IBusinessQuery) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (contrib != null) {
          _businessQueriesCache.add(contrib);
        }
      }
    }
    return _businessQueriesCache;
  }


  public IBusinessQuery getContribution(EClass cls, EStructuralFeature feature) {
    return getAllContributionsMap().get(new SimpleEntry<EClass, EStructuralFeature>(cls, feature));
  }

  /**
   * Returns an unmodifiable cached map view of all known business query contributions.
   */
  public Map<SimpleEntry<EClass, EStructuralFeature>, IBusinessQuery> getAllContributionsMap(){
    if (businessQueriesMap == null) {
      businessQueriesMap = new HashMap<SimpleEntry<EClass,EStructuralFeature>, IBusinessQuery>();
      for (IBusinessQuery query : getAllContributions()) {

        // there are deprecated queries around that return null, filter them here
        if (query.getEClass() != null && query.getEStructuralFeatures() != null) {

          for (EStructuralFeature f : query.getEStructuralFeatures()) {
            SimpleEntry<EClass, EStructuralFeature> key = new SimpleEntry<EClass, EStructuralFeature>(query.getEClass(), f);
            IBusinessQuery dup = businessQueriesMap.get(key);
            if (dup == null) {
              businessQueriesMap.put(key, query);
            } else {
              // keep the existing key and log error.
              ILog log = Platform.getLog(BusinessQueriesPlugin.class);
              log.log(Status.warning(
                  NLS.bind(Messages.BusinessQueriesProvider_duplicateQueryContributionKey,
                      new Object[] { key.getKey(), key.getValue(), query.getClass().getName(), dup.getClass().getName() })));
            }
          }
        }
      }
    }
    return Collections.unmodifiableMap(businessQueriesMap);
  }


  public static BusinessQueriesProvider getInstance() {
    if (_instance == null) {
      _instance = new BusinessQueriesProvider();
    }
    return _instance;
  }
}
