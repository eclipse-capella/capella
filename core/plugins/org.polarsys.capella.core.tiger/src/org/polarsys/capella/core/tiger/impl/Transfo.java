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

  public Transfo(EClass eGenericTrace_p) {
    this();
    _eDefaultTrace = eGenericTrace_p;
  }

  public Transfo(EClass eGenericTrace_p, String context_p) {
    _eDefaultTrace = eGenericTrace_p;
    _ruleBase = new TransfoRuleBase();
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
    loadRulesFromExtensionPoint(context_p);
  }

  public Transfo(ITransfoRuleBase ruleBase) {
    _ruleBase = ruleBase;
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
  }

  public Transfo(ITransfoRuleBase ruleBase_p, EClass eGenericTrace_p) {
    this(ruleBase_p);
    _eDefaultTrace = eGenericTrace_p;
  }

  public Transfo(ITransfoRuleBase ruleBase_p, EClass eGenericTrace_p, String context_p) {
    this(ruleBase_p, eGenericTrace_p);
    loadRulesFromExtensionPoint(context_p);
  }

  public Transfo(String context_p) {
    _ruleBase = new TransfoRuleBase();
    _ruleBaseCache = new HashMap<EObject, ITransfoRule>();
    _eSpecificLinkKindMap = new HashMap<String, EClass>();
    loadRulesFromExtensionPoint(context_p);
  }

  /**
   * @param resolver_p
   */
  public void addResolver(IResolver resolver_p) {
    _resolvers.add(resolver_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#addRule(org.polarsys.capella.core.bridges.transfo.impl.TransfoRule)
   */
  public void addRule(TransfoRule rule_p) {
    _ruleBase.addRule(rule_p);
  }

  /**
   * @throws TransfoException
   * @see org.polarsys.capella.common.tiger.ITransfo#findMatchingRule(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public ITransfoRule findCachedMatchingRule(EObject element_p) throws TransfoException {
    ITransfoRule rule = _ruleBaseCache.get(element_p);
    if (null == rule) {
      rule = _ruleBase.findMatchingRule(element_p, this);
      _ruleBaseCache.put(element_p, rule);
    }

    if (rule == null) {
      throw new TransfoException("rule not found", element_p); //$NON-NLS-1$
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
  public ITransfoRule findMatchingRule(EObject element_p) throws TransfoException {
    return _ruleBase.findMatchingRule(element_p, this);
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
  private String getKey(EClass sourceType_p, EClass targetType_p) {
    return sourceType_p.getName() + "_" + targetType_p.getName(); //$NON-NLS-1$
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
  public EClass getSpecificLinkKindFromMap(EObject srcEltType_p, EObject tgtEltType_p) {
    String sourceKey = getKey(srcEltType_p.eClass(), tgtEltType_p.eClass());

    EClass specific = _eSpecificLinkKindMap.get(sourceKey);

    // Allow sub-typing of semantic source and target
    if (specific == null) {
      ArrayList<EClass> superSources = new ArrayList<EClass>(srcEltType_p.eClass().getEAllSuperTypes());
      superSources.add(srcEltType_p.eClass());
      ArrayList<EClass> superTargets = new ArrayList<EClass>(tgtEltType_p.eClass().getEAllSuperTypes());
      superTargets.add(tgtEltType_p.eClass());

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
   * @param link_p
   * @return
   */
  public boolean isValidLinkKind(AbstractTrace link_p) {
    EClass eGenericTraceFound = null;
    TraceableElement sourceType_p = link_p.getTargetElement();
    TraceableElement targetType_p = link_p.getSourceElement();

    if ((sourceType_p != null) && (targetType_p != null)) {
      eGenericTraceFound = this.getSpecificLinkKindFromMap(sourceType_p, targetType_p);

      if ((eGenericTraceFound == null) && (this.get_eDefaultTrace() != null)) {
        eGenericTraceFound = this.get_eDefaultTrace();
      }
    }
    // Allow sub-eclass of the generic trace eclass
    return ((eGenericTraceFound != null) && EcoreUtil2.isEqualOrSuperClass(eGenericTraceFound, link_p.eClass()));
  }

  @SuppressWarnings("rawtypes")
  void loadFinalizer(Bundle bundle_p, String finalizer) {
    try {
      Class clazz = bundle_p.loadClass(finalizer);
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
  void loadRule(Bundle bundle_p, String packageName_p, IConfigurationElement ruleElement_p) {
    String ruleName = ruleElement_p.getAttribute(RULE_NAME_ID);
    String linkName = ruleElement_p.getAttribute(TRANSFO_LINK_ID);
    String completeName = packageName_p + "." + ruleName; //$NON-NLS-1$
    try {
      Class clazz = bundle_p.loadClass(completeName);
      TransfoRule ruleInstance = (TransfoRule) clazz.newInstance();
      addRule(ruleInstance);
      if ((linkName != null) && !linkName.equals("")) { //$NON-NLS-1$
        setSpecificLinkKindFromMap(ruleInstance.getSourceType(), ruleInstance.getTargetType(), loadTrace(bundle_p, linkName));
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
  public void loadRule(Class<?> ruleClass_p) {
    _ruleBase.loadRule(ruleClass_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#loadRules(java.lang.String)
   */
  public void loadRules(String rulePkgName_p) throws ClassNotFoundException {
    _ruleBase.loadRules(rulePkgName_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#loadRules(java.lang.String, java.lang.String[])
   */
  public void loadRules(String rulePkgName_p, String[] classNames_p) throws ClassNotFoundException {
    _ruleBase.loadRules(rulePkgName_p, classNames_p);
  }

  /**
   * load rules from extension registry
   */
  void loadRulesFromExtensionPoint(String contextName_p) {
    TransfoRuleLoader loader = new TransfoRuleLoader(this);
    loader.loadContext(contextName_p);
    EClass defaultTrace = loader.getDefaultTrace();
    if ((defaultTrace != null) && (_eDefaultTrace != defaultTrace)) {
      _eDefaultTrace = defaultTrace;
    }
  }

  @SuppressWarnings("rawtypes")
  EClass loadTrace(Bundle bundle_p, String transfoLinkName_p) {
    EClass result = _eDefaultTrace;
    try {
      Class clazz = bundle_p.loadClass(transfoLinkName_p);
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
  protected void setSpecificLinkKindFromMap(EClass sourceType, EClass targetType, EClass traceType) {
    if ((sourceType != null) && (targetType != null) && (traceType != null)) {
      String key = getKey(sourceType, targetType);
      _eSpecificLinkKindMap.put(key, traceType);
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#setUid(java.lang.String)
   */
  public void setUid(String uid_p) {
    _uid = uid_p;
  }

  @SuppressWarnings("nls")
  public String toHtml() {
    StringBuilder htmlDoc = new StringBuilder();
    htmlDoc.append("<html>" + __cr);
    htmlDoc.append("<head>" + __cr);
    htmlDoc.append("<title> Rule Base Documentation </title>" + __cr);
    htmlDoc.append("</head>" + __cr);
    htmlDoc.append("<body>" + __cr);
    htmlDoc.append(_ruleBase.toHtml(false));
    htmlDoc.append("</body>" + __cr);
    htmlDoc.append("</html>" + __cr);
    return htmlDoc.toString();
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfo#toString()
   */
  @Override
  @SuppressWarnings("nls")
  public String toString() {
    String newLine = System.getProperty("line.separator");
    StringBuilder builder = new StringBuilder();

    builder.append("Transformation <");
    builder.append(_uid);
    builder.append(">");
    builder.append(newLine);

    builder.append(_ruleBase);

    builder.append(" + Properties");
    builder.append(newLine);
    for (String key : keySet()) {
      builder.append("   - " + key + "=" + get(key));
      builder.append(newLine);
    }

    return builder.toString();
  }

}
