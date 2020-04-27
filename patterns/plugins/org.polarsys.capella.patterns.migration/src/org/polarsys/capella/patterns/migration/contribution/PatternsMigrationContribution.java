/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.patterns.migration.contribution;

import java.util.HashMap;

import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.CorepatternsPackage;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.predefined.PredefinedPackage;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonpatternsupportPackage;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.TemplatepatternsPackage;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.patterns.migration.PatternsMigrationConstants;
import org.polarsys.capella.patterns.migration.PatternsMigrationHelper;
import org.polarsys.kitalpha.emde.model.EmdePackage;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdepatternsupportPackage;

/**
 * 
 */
public class PatternsMigrationContribution extends AbstractMigrationContribution {
  public static HashMap<String, EPackage> prefixes = new HashMap<String, EPackage>();

  static {

    prefixes.put(XMLResource.XML_NS + ":" + CorepatternsPackage.eINSTANCE.getNsPrefix(), CorepatternsPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + PredefinedPackage.eINSTANCE.getNsPrefix(), PredefinedPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + CommonpatternsupportPackage.eINSTANCE.getNsPrefix(), CommonpatternsupportPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + TemplatepatternsPackage.eINSTANCE.getNsPrefix(), TemplatepatternsPackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + EmdePackage.eINSTANCE.getNsPrefix(), EmdePackage.eINSTANCE);
    prefixes.put(XMLResource.XML_NS + ":" + EmdepatternsupportPackage.eINSTANCE.getNsPrefix(), EmdepatternsupportPackage.eINSTANCE);
  }

  public static HashMap<String, EPackage> pkgs = new HashMap<String, EPackage>();

  static {
    pkgs.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_CORE_NSURI, CorepatternsPackage.eINSTANCE);
    pkgs.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_CORE_PREDEFINED_NSURI, PredefinedPackage.eINSTANCE);
    pkgs.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_SUPPORT_EMDE_NSURI, CommonpatternsupportPackage.eINSTANCE);
    pkgs.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_TEMPLATES_NSURI, TemplatepatternsPackage.eINSTANCE);
    pkgs.put(PatternsMigrationConstants.OLD_FULL_EMDE_NSURI, EmdePackage.eINSTANCE);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unaryMigrationExecute(EObject object, MigrationContext context) {
    String typeName = object.eClass().getName();
    if (CorepatternsPackage.eINSTANCE.getPatternRepository().getName().equals(typeName)) {
      // Pattern repository
      PatternsMigrationHelper.getInstance().migrateCatalog(object);
    }
  }

  @Override
  public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {

    for (String oldNSURI : pkgs.keySet()) {
      packageRegistry.put(oldNSURI, pkgs.get(oldNSURI));
    }

  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource, XMLHelper helper, MigrationContext context) {
    if ((typeQName != null) && typeQName.endsWith(EmdepatternsupportPackage.Literals.EMDE_PATTERN_INSTANCE_SET.getName())) {
      return EmdepatternsupportPackage.eINSTANCE.getNsPrefix() + ":" + EmdepatternsupportPackage.Literals.EMDE_PATTERN_INSTANCE_SET.getName();
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }

  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {

    if (prefixes.containsKey(prefix)) {
      return XMLResource.XML_NS + ":" + prefixes.get(prefix).getNsPrefix();
    }

    return null;
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    if (prefix.equals(EmdepatternsupportPackage.eINSTANCE.getNsPrefix())) {
      return EmdepatternsupportPackage.eINSTANCE.getEFactoryInstance();
    }
    return super.getEFactory(prefix, resource, context);
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {
    if (pkgs.containsKey(nsUri)) {
      return pkgs.get(nsUri).getNsURI();
    }
    return null;
  }

}
