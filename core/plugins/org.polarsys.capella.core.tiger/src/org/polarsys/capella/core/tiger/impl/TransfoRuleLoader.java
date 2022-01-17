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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.tiger.IResolver;

/**
 * Loads rules defined from extension points Add the possibility to define sharedContexts which load rules/sharedRules defined in linked contexts of the
 * specified context. (for instance, for the context lc2pc, load also the data context for part cardinalities)
 */
public class TransfoRuleLoader {

  /**
   * Describes a context
   */
  protected class IContext {
    List<IConfigurationElement> elements;
    String id;

    boolean isInitialized;
    List<IRuleSet> ruleSets;

    IContext(String id) {
      this.id = id;
      isInitialized = false;
      ruleSets = new ArrayList<IRuleSet>();
      elements = new ArrayList<IConfigurationElement>();
    }
  }

  /**
   * Describes a finalizer
   */
  private class IFinalizer extends IRule {
    IFinalizer(IConfigurationElement name) {
      super(name);
      this.name = name.getAttribute(FINALIZER_CLASS_ID);
    }
  }

  /**
   * Describes a rule
   */
  private class IRule {
    IConfigurationElement element;
    String name;

    IRule(IConfigurationElement element) {
      this.element = element;
      this.name = element.getAttribute(RULE_NAME_ID);
    }
  }

  /**
   * Describes a ruleset
   */
  private class IRuleSet {
    Bundle bundle;
    HashMap<String, IFinalizer> finalizers = new HashMap<String, IFinalizer>();
    String packageName;
    HashMap<String, IRule> rules = new HashMap<String, IRule>();

    IRuleSet(Bundle bundle, String packageName) {
      rules = new HashMap<String, IRule>();
      this.bundle = bundle;
      this.packageName = packageName;
    }

  }

  protected static final String BUNDLE_ID = "bundle"; //$NON-NLS-1$
  protected static final String CONTEXT_NAME_ID = "context"; //$NON-NLS-1$
  protected static final String FINALIZER_CLASS_ID = "class"; //$NON-NLS-1$
  protected static final String FINALIZER_ID = "finalizer"; //$NON-NLS-1$
  protected static final String PACKAGE_NAME_ID = "rulepackage"; //$NON-NLS-1$
  protected static final String PLUGIN_ID = "org.polarsys.capella.core.tiger"; //$NON-NLS-1$
  protected static final String PRIORITY_HIGH = "high"; //$NON-NLS-1$
  protected static final String PRIORITY_ID = "priority"; //$NON-NLS-1$

  protected static final String PRIORITY_LOW = "low"; //$NON-NLS-1$
  protected static final String PRIORITY_NORMAL = "normal"; //$NON-NLS-1$
  protected static final String RESOLVER_ID = "resolver"; //$NON-NLS-1$
  protected static final String RESOLVER_ID_ID = "id"; //$NON-NLS-1$
  protected static final String RULE_ID = "rule"; //$NON-NLS-1$
  protected static final String RULE_NAME_ID = "rulename"; //$NON-NLS-1$

  protected static final String RULESET_ID = "ruleset"; //$NON-NLS-1$

  protected static final String SHARED_CONTEXT_ID = "sharedContext"; //$NON-NLS-1$

  protected static final String SHARED_RULES_ID = "sharedRuleset"; //$NON-NLS-1$
  protected static final String TRANSFO_LINK_ID = "transfolink"; //$NON-NLS-1$

  // All ruleset needs to be loaded
  protected HashMap<String, IRuleSet> allRuleSets = new HashMap<String, IRuleSet>();

  // All defined contexts
  protected HashMap<String, IContext> contexts = new HashMap<String, IContext>();

  EClass defaultTrace;

  Transfo transfo;

  TransfoRuleLoader(Transfo transfo) {
    this.transfo = transfo;
    initContextsFromRegistry();
    loadResolvers();
  }

  /**
   * @return the default trace which should be used in Transfo
   */
  public EClass getDefaultTrace() {
    return defaultTrace;
  }

  /**
   * Initialize a context by retrieving all ruleSets required
   */
  protected void initContext(String contextName) {

    if (contextName != null) {
      // Load specified context
      if (!contexts.containsKey(contextName)) {
        return;
      }
      IContext context = contexts.get(contextName);
      if (context.isInitialized) {
        return;
      }
      context.isInitialized = true;
      initElements(context.elements);

    } else {
      // Load all contexts if no context defined
      for (IContext context : contexts.values()) {
        if (!context.isInitialized) {
          context.isInitialized = true;
          initElements(context.elements);
        }
      }
    }
  }

  /**
   * Initialize contexts from plugin registry
   */
  protected void initContextsFromRegistry() {
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    IConfigurationElement[] elementsForPlugin = extensionRegistry.getConfigurationElementsFor(PLUGIN_ID);

    for (IConfigurationElement configurationElement : elementsForPlugin) {
      String contextName = configurationElement.getAttribute(CONTEXT_NAME_ID);
      if (contexts.get(contextName) == null) {
        contexts.put(contextName, new IContext(contextName));
      }
      contexts.get(contextName).elements.add(configurationElement);
    }
  }

  /**
   * Retrieve ruleSets from given IConfigurationElement
   */
  protected void initElements(Collection<IConfigurationElement> elements) {
    HashSet<String> contextToLoads = new HashSet<String>();

    for (IConfigurationElement element : elements) {
      Bundle bundle = Platform.getBundle(element.getNamespaceIdentifier());
      String packageName = element.getAttribute(PACKAGE_NAME_ID);

      if (defaultTrace == null) {
        String transfoLinkName = element.getAttribute(TRANSFO_LINK_ID);
        if ((transfoLinkName != null) && (transfoLinkName.length() > 0)) {
          defaultTrace = transfo.loadTrace(bundle, transfoLinkName);
        }
      }

      IRuleSet set = retrieveRuleSet(bundle, packageName);

      // retrieve missing rules defined for a context
      IConfigurationElement[] ruleList = element.getChildren(RULE_ID);
      initRulesForRuleSet(set, ruleList);

      // Retrieve shared defined rules
      IConfigurationElement[] sharedRuleList = element.getChildren(SHARED_RULES_ID);
      for (IConfigurationElement ruleElement : sharedRuleList) {
        String sharedPackageName = ruleElement.getAttribute(PACKAGE_NAME_ID);
        String sharedBundleId = ruleElement.getAttribute(BUNDLE_ID);

        IRuleSet sharedSet = retrieveRuleSet(Platform.getBundle(sharedBundleId), sharedPackageName);
        IConfigurationElement[] subRuleList = ruleElement.getChildren(RULE_ID);
        initRulesForRuleSet(sharedSet, subRuleList);
      }

      // Retrieve shared contexts
      IConfigurationElement[] sharedContextList = element.getChildren(SHARED_CONTEXT_ID);
      for (IConfigurationElement contextElement : sharedContextList) {
        String sharedContext = contextElement.getAttribute(CONTEXT_NAME_ID);
        if (sharedContext != null) {
          contextToLoads.add(sharedContext);
        }
      }

      // Retrieve finalizers
      IConfigurationElement[] finalizers = element.getChildren(FINALIZER_ID);
      initFinalizersForRuleSet(set, finalizers);
    }

    // Load others contexts
    for (String contextName : contextToLoads) {
      initContext(contextName);
    }
  }

  /**
   * Fill a rule set with finalizers from IConfigurationElements
   */
  protected void initFinalizersForRuleSet(IRuleSet set, IConfigurationElement[] rules) {
    for (IConfigurationElement ruleElement : rules) {
      String ruleName = ruleElement.getAttribute(FINALIZER_CLASS_ID);
      if ((ruleName != null) && (ruleName.length() > 0) && !set.finalizers.containsKey(ruleName)) {
        IFinalizer rule = new IFinalizer(ruleElement);
        set.finalizers.put(rule.name, rule);
      }
    }
  }

  /**
   * Fill a rule set with rules from IConfigurationElements
   */
  protected void initRulesForRuleSet(IRuleSet set, IConfigurationElement[] rules) {
    for (IConfigurationElement ruleElement : rules) {
      String ruleName = ruleElement.getAttribute(RULE_NAME_ID);
      if ((ruleName != null) && (ruleName.length() > 0) && !set.rules.containsKey(ruleName)) {
        IRule rule = new IRule(ruleElement);
        set.rules.put(rule.name, rule);
      }
    }
  }

  /**
   * Allows to load rule classes of the given context name
   * @param contextName
   */
  public void loadContext(String contextName) {
    initContext(contextName);
    loadRuleSets();
  }

  /**
   * Load defined resolvers by priority order
   */
  private void loadResolvers() {
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    IConfigurationElement[] elementsForPlugin = extensionRegistry.getConfigurationElementsFor(FrameworkUtil.getBundle(this.getClass()).getSymbolicName());
    TreeSet<IConfigurationElement> elements = new TreeSet<IConfigurationElement>(new Comparator<IConfigurationElement>() {

      public int compare(IConfigurationElement arg0, IConfigurationElement arg1) {
        int priority1 = getPriority(arg0);
        int priority2 = getPriority(arg1);

        if (priority1 == priority2) {
          return arg0.getAttribute(RESOLVER_ID_ID).compareTo(arg1.getAttribute(RESOLVER_ID_ID));
        }
        return priority2 - priority1;
      }

      private int getPriority(IConfigurationElement configurationElement) {
        String value = configurationElement.getAttribute(PRIORITY_ID);
        if (value == null) {
          return 0;
        } else if (PRIORITY_LOW.equals(value)) {
          return -1;
        } else if (PRIORITY_NORMAL.equals(value)) {
          return 0;
        } else if (PRIORITY_HIGH.equals(value)) {
          return 1;
        }
        return 0;
      }
    });

    for (IConfigurationElement configurationElement : elementsForPlugin) {
      if (RESOLVER_ID.equals(configurationElement.getName())) {
        elements.add(configurationElement);
      }
    }

    for (IConfigurationElement configurationElement : elements) {
      IResolver resolver = (IResolver) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (resolver != null) {
        transfo.addResolver(resolver);
      }
    }
  }

  /**
   * Load rules class from defined ruleSets
   */
  protected void loadRuleSets() {
    for (IRuleSet ruleSet : allRuleSets.values()) {
      for (IRule rule : ruleSet.rules.values()) {
        transfo.loadRule(ruleSet.bundle, ruleSet.packageName, rule.element);
      }
      for (IFinalizer finalizer : ruleSet.finalizers.values()) {
        transfo.loadFinalizer(ruleSet.bundle, finalizer.name);
      }
    }
  }

  /**
   * Retrieve ruleset
   */
  protected IRuleSet retrieveRuleSet(Bundle bundle, String packageName) {
    if (!allRuleSets.containsKey(packageName)) {
      IRuleSet set = new IRuleSet(bundle, packageName);
      allRuleSets.put(packageName, set);
    }
    return allRuleSets.get(packageName);
  }
}
