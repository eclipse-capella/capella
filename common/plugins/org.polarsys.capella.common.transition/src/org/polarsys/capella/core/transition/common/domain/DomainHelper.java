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

package org.polarsys.capella.core.transition.common.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.transition.common.Activator;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.kitalpha.transposer.transformation.emf.TransposerEMFPlugin;
import org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper;

/**
 * 
 */
public class DomainHelper extends EmfDomainHelper {

  private HashMap<String, EPackage> packages;

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getAnalysisSources(java.util.Collection)
   */
  @Override
  public Collection<Object> getAnalysisSources(Collection<?> selection) {
    Collection<Object> sources = new ArrayList<Object>(selection);
    return sources;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getDomainMetaclass(java.lang.String)
   */
  @Override
  public Class<?> getDomainMetaclass(String name) {

    try {
      return Class.forName(name);

    } catch (ClassNotFoundException e) {
      if (name != null) {
        try {
          int index = name.lastIndexOf(".");
          String pkgName = name.substring(0, index);
          String className = name.substring(index + 1);

          for (EPackage pkg : getPackages(pkgName)) {
            EClassifier clazz = pkg.getEClassifier(className);
            if ((clazz != null) && (clazz.getInstanceClass() != null)) {
              return clazz.getInstanceClass();
            }
          }

        } catch (Exception e3) {
          // Nothing more
        }
      }
      Activator.getDefault().getLog()
          .log(new Status(IStatus.ERROR, TransposerEMFPlugin.PLUGIN_ID, "No Domain Class called : " + name, e)); //$NON-NLS-1$
    }

    return null;
  }

  /**
   * Return an optimized list of EPackage to check
   * 
   * @param pkgName
   * @return
   */
  protected Collection<EPackage> getPackages(String pkgName) {

    if (packages == null) {
      packages = new HashMap<String, EPackage>();
      for (EPackage pkg : getEPackages()) {
        for (EClassifier clazz : pkg.getEClassifiers()) {
          Class<?> a = clazz.getInstanceClass();
          if (a != null) {
            String name = a.getPackage().getName();
            packages.put(name, pkg);
            break;
          }
        }
      }
    }

    EPackage pkg = packages.get(pkgName);
    if (pkg != null) {
      return Collections.singletonList(pkg);

    }

    Collection<EPackage> pkgs = new ArrayList<EPackage>();
    for (EPackage pkgItem : getEPackages()) {
      if (pkgName.contains(pkgItem.getName())) {
        pkgs.add(pkgItem);
      }
    }

    return pkgs;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getEPackages()
   */
  @Override
  protected Set<EPackage> getEPackages() {
    Set<EPackage> ePackages = new LinkedHashSet<EPackage>();

    // We may restrict Domain on purpose and mapping but it's not yet possible (there is no context in
    // this class instantiation) and not critical
    for (String handler : ExtensionHelper.collectDomainFromExtensions(TransitionContext.EMPTY_CONTEXT,
        ISchemaConstants.HANDLERS__PURPOSE__ALL_PURPOSES, ISchemaConstants.HANDLERS__MAPPING__ALL_MAPPINGS)) {
      try {
        EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(handler);
        if (pkg != null) {
          ePackages.add(pkg);
        }
      } catch (Exception e) {
        // Nothing here
      }
    }

    return ePackages;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper#isDomainFor(java.lang.Object)
   */
  @Override
  public boolean isDomainFor(Object object) {
    return true;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#isHotSpot(java.lang.Object)
   */
  @Override
  public boolean isHotSpot(Object object) {
    return true;
  }
}
