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
package org.polarsys.capella.core.tiger.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.Bundle;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITracelinkProvider;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class Transfo extends HashMap<String, Object> implements ITransfo {

  /**
   * Transfo tag to indicate whether the transfo modifies the model {@link Boolean#FALSE} or not {@link Boolean#FALSE}
   */
  public static final String DRY_RUN = "isDryRun"; //$NON-NLS-1$
  
  private static String __cr = System.getProperty("line.separator"); //$NON-NLS-1$

  private static final String RULE_NAME_ID = "rulename"; //$NON-NLS-1$
  /**
   * Serial version UID for this new HashMap
   */
  private static final long serialVersionUID = -4143258412471058338L;

  private static final String TRANSFO_LINK_ID = "transfolink"; //$NON-NLS-1$
  private EClass _eDefaultTrace;
  private HashMap<String, EClass> _eSpecificLinkKindMap;
  /**
   * finalizer defined by the user of the engine
   */
  private List<IFinalizer> _finalizers = new ArrayList<IFinalizer>(1);
  private List<IResolver> _resolvers = new ArrayList<IResolver>(1);

  private ITransfoRuleBase _ruleBase;

  private Map<EObject, ITransfoRule> _ruleBaseCache;

  private String _uid = "noUID"; //$NON-NLS-1$

  public Transfo() {
    _ruleBase = new TransfoRuleBase();
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
    loadRulesFromExtensionPoint(null);

  }

  public Transfo(EClass eGenericTrace) {
    this();
    _eDefaultTrace = eGenericTrace;
  }

  public Transfo(EClass eGenericTrace, String context) {
    _eDefaultTrace = eGenericTrace;
    _ruleBase = new TransfoRuleBase();
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
    loadRulesFromExtensionPoint(context);
  }

  public Transfo(ITransfoRuleBase ruleBase) {
    _ruleBase = ruleBase;
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
  }

  public Transfo(ITransfoRuleBase ruleBase, EClass eGenericTrace) {
    this(ruleBase);
    _eDefaultTrace = eGenericTrace;
  }

  public Transfo(ITransfoRuleBase ruleBase, EClass eGenericTrace, String context) {
    this(ruleBase, eGenericTrace);
    loadRulesFromExtensionPoint(context);
  }

  public Transfo(String context) {
    _ruleBase = new TransfoRuleBase();
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
    loadRulesFromExtensionPoint(context);
  }

  /**
   * @param resolver
   */
  public void addResolver(IResolver resolver) {
    _resolvers.add(resolver);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#addRule(org.polarsys.capella.core.bridges.transfo.impl.TransfoRule)
   */
  public void addRule(TransfoRule rule) {
    _ruleBase.addRule(rule);
  }

  /**
   * @throws TransfoException
   * @see org.polarsys.capella.common.tiger.ITransfo#findMatchingRule(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public ITransfoRule findCachedMatchingRule(EObject element) throws TransfoException {
    ITransfoRule rule = _ruleBaseCache.get(element);
    if (null == rule) {
      rule = _ruleBase.findMatchingRule(element, this);
      _ruleBaseCache.put(element, rule);
    }

    if (rule == null) {
      throw new TransfoException("rule not found", element); //$NON-NLS-1$
    }

    // Store the kind of TransfoLink corresponding to the Source and Target
    // Element for the current rule
    setSpecificLinkKindFromMap(rule.getSourceType(), rule.getTargetType(), rule.getSpecificLinkKind());

    return rule;
  }

  /**
   * @throws TransfoException
   * @see org.polarsys.capella.common.tiger.ITransfo#findMatchingRule(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public ITransfoRule findMatchingRule(EObject element) throws TransfoException {
    return _ruleBase.findMatchingRule(element, this);
  }

  public EClass get_eDefaultTrace() {
    return _eDefaultTrace;
  }

  /**
   * @see org.polarsys.capella.core.tiger.ITransfo#getFinalizers()
   */
  public List<IFinalizer> getFinalizers() {
    return _finalizers;
  }

  // Return a key for access to the specific kind link map in according to the
  // Source and Target type Element
  private String getKey(EClass sourceType, EClass targetType) {
    return sourceType.getName() + "_" + targetType.getName(); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.ITransfo#getResolvers()
   */
  public List<IResolver> getResolvers() {
    return _resolvers;
  }

  /**
   * Return a specific kind of TransfoLink in according to the Source and Target type Element
   */
  public EClass getSpecificLinkKindFromMap(EObject srcEltType, EObject tgtEltType) {
    String sourceKey = getKey(srcEltType.eClass(), tgtEltType.eClass());

    EClass specific = _eSpecificLinkKindMap.get(sourceKey);

    // Allow sub-typing of semantic source and target
    if (specific == null) {
      ArrayList<EClass> superSources = new ArrayList<EClass>(srcEltType.eClass().getEAllSuperTypes());
      superSources.add(srcEltType.eClass());
      ArrayList<EClass> superTargets = new ArrayList<EClass>(tgtEltType.eClass().getEAllSuperTypes());
      superTargets.add(tgtEltType.eClass());

      // using descendingIterator() on a linkedList in java 1.6 should be more efficient
      for (EClass eclassSource : superSources) {
        for (EClass eclassTarget : superTargets) {
          specific = _eSpecificLinkKindMap.get(getKey(eclassSource, eclassTarget));
          if (specific != null) {
            _eSpecificLinkKindMap.put(sourceKey, specific);
            return specific;
          }
        }
      }
    }
    return specific;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#getUid()
   */
  public String getUid() {
    return _uid;
  }

  /**
   * Check the kind of link given in parameter
   * @param link
   * @return
   */
  public boolean isValidLinkKind(AbstractTrace link) {
    EClass eGenericTraceFound = null;
    TraceableElement sourceType = link.getTargetElement();
    TraceableElement targetType = link.getSourceElement();

    if ((sourceType != null) && (targetType != null)) {
      eGenericTraceFound = this.getSpecificLinkKindFromMap(sourceType, targetType);

      if ((eGenericTraceFound == null) && (this.get_eDefaultTrace() != null)) {
        eGenericTraceFound = this.get_eDefaultTrace();
      }
    }
    // Allow sub-eclass of the generic trace eclass
    return ((eGenericTraceFound != null) && EcoreUtil2.isEqualOrSuperClass(eGenericTraceFound, link.eClass()));
  }

  @SuppressWarnings("rawtypes")
  void loadFinalizer(Bundle bundle, String finalizer) {
    try {
      Class clazz = bundle.loadClass(finalizer);
      IFinalizer finalizerInstance = (IFinalizer) clazz.newInstance();
      _finalizers.add(finalizerInstance);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("rawtypes")
  void loadRule(Bundle bundle, String packageName, IConfigurationElement ruleElement) {
    String ruleName = ruleElement.getAttribute(RULE_NAME_ID);
    String linkName = ruleElement.getAttribute(TRANSFO_LINK_ID);
    String completeName = packageName + "." + ruleName; //$NON-NLS-1$
    try {
      Class clazz = bundle.loadClass(completeName);
      TransfoRule ruleInstance = (TransfoRule) clazz.newInstance();
      addRule(ruleInstance);
      if ((linkName != null) && !linkName.equals("")) { //$NON-NLS-1$
        setSpecificLinkKindFromMap(ruleInstance.getSourceType(), ruleInstance.getTargetType(), loadTrace(bundle, linkName));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#loadRule(java.lang.Class)
   */
  public void loadRule(Class<?> ruleClass) {
    _ruleBase.loadRule(ruleClass);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#loadRules(java.lang.String)
   */
  public void loadRules(String rulePkgName) throws ClassNotFoundException {
    _ruleBase.loadRules(rulePkgName);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#loadRules(java.lang.String, java.lang.String[])
   */
  public void loadRules(String rulePkgName, String[] classNames) throws ClassNotFoundException {
    _ruleBase.loadRules(rulePkgName, classNames);
  }

  /**
   * load rules from extension registry
   */
  void loadRulesFromExtensionPoint(String contextName) {
    TransfoRuleLoader loader = new TransfoRuleLoader(this);
    loader.loadContext(contextName);
    EClass defaultTrace = loader.getDefaultTrace();
    if ((defaultTrace != null) && (_eDefaultTrace != defaultTrace)) {
      _eDefaultTrace = defaultTrace;
    }
  }

  @SuppressWarnings("rawtypes")
  EClass loadTrace(Bundle bundle, String transfoLinkName) {
    EClass result = _eDefaultTrace;
    try {
      Class clazz = bundle.loadClass(transfoLinkName);
      ITracelinkProvider ruleInstance = (ITracelinkProvider) clazz.newInstance();
      result = ruleInstance.getTraceLinkType();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return result;
  }

  // Store the specific kind of Link corresponding to the Source and Target
  // Element
  public void setSpecificLinkKindFromMap(EClass sourceType, EClass targetType, EClass traceType) {
    if ((sourceType != null) && (targetType != null) && (traceType != null)) {
      String key = getKey(sourceType, targetType);
      _eSpecificLinkKindMap.put(key, traceType);
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#setUid(java.lang.String)
   */
  public void setUid(String uid) {
    _uid = uid;
  }

  public String toHtml() {
    StringBuilder htmlDoc = new StringBuilder();
    htmlDoc.append("<html>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<head>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<title> Rule Base Documentation </title>" + __cr); //$NON-NLS-1$
    htmlDoc.append("</head>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<body>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(_ruleBase.toHtml(false));
    htmlDoc.append("</body>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("</html>").append(__cr); //$NON-NLS-1$
    return htmlDoc.toString();
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#toString()
   */
  @Override
  public String toString() {
    String newLine = System.getProperty("line.separator"); //$NON-NLS-1$
    StringBuilder builder = new StringBuilder();

    builder.append("Transformation <"); //$NON-NLS-1$
    builder.append(_uid);
    builder.append(">"); //$NON-NLS-1$
    builder.append(newLine);

    builder.append(_ruleBase);

    builder.append(" + Properties"); //$NON-NLS-1$
    builder.append(newLine);
    for (String key : keySet()) {
      builder.append("   - " + key + "=" + get(key)); //$NON-NLS-1$ //$NON-NLS-2$
      builder.append(newLine);
    }

    return builder.toString();
  }

  @Override
  public boolean isDryRun() {
    return containsKey(DRY_RUN) && get(DRY_RUN) == Boolean.TRUE;
  }

  @Override
  public void setDryRun(boolean dryRun) {
    put(DRY_RUN, dryRun);
  }
}
