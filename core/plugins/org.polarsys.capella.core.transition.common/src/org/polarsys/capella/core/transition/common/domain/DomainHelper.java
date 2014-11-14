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

import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.kitalpha.transposer.transformation.emf.TransposerEMFPlugin;
import org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper;

/**
 * 
 */
public class DomainHelper extends EmfDomainHelper {

  private HashMap<String, EPackage> _packages;

  /** 
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getAnalysisSources(java.util.Collection)
   */
  @Override
  public Collection<Object> getAnalysisSources(Collection<?> selection_p) {
    Collection<Object> sources = new ArrayList<Object>(selection_p);
    return sources;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#getDomainMetaclass(java.lang.String)
   */
  @Override
  public Class<?> getDomainMetaclass(String name_p) {

    try {
      return Class.forName(name_p);

    } catch (ClassNotFoundException e) {
      if (name_p != null) {
        try {
          int index = name_p.lastIndexOf(".");
          String pkgName = name_p.substring(0, index);
          String className = name_p.substring(index + 1);

          for (EPackage pkg : getPackages(pkgName)) {
            EClassifier clazz = pkg.getEClassifier(className);
            if ((clazz != null) && (clazz.getInstanceClass() != null)) {
              return clazz.getInstanceClass();
            }
          }

        } catch (Exception e3) {
          //Nothing more
        }
      }
      TransposerEMFPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, TransposerEMFPlugin.PLUGIN_ID, "No Domain Class called : " + name_p, e)); //$NON-NLS-1$
    }

    return null;
  }

  /**
   * Return an optimized list of EPackage to check
   * @param pkgName_p
   * @return
   */
  protected Collection<EPackage> getPackages(String pkgName_p) {

    if (_packages == null) {
      _packages = new HashMap<String, EPackage>();
      for (EPackage pkg : getEPackages()) {
        for (EClassifier clazz : pkg.getEClassifiers()) {
          Class<?> a = clazz.getInstanceClass();
          if (a != null) {
            String name = a.getPackage().getName();
            _packages.put(name, pkg);
            break;
          }
        }
      }
    }

    EPackage pkg = _packages.get(pkgName_p);
    if (pkg != null) {
      return Collections.singletonList(pkg);

    }

    Collection<EPackage> pkgs = new ArrayList<EPackage>();
    for (EPackage pkgItem : getEPackages()) {
      if (pkgName_p.contains(pkgItem.getName())) {
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

    for (String handler : ExtensionHelper.collectStringFromExtensions(null, ISchemaConstants.EXTENSION_ID, ISchemaConstants.DOMAIN)) {
      try {
        EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(handler);
        if (pkg != null) {
          ePackages.add(pkg);
        }
      } catch (Exception e) {
        //Nothing here
      }
    }

    return ePackages;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper#isDomainFor(java.lang.Object)
   */
  @Override
  public boolean isDomainFor(Object object_p) {
    return true;
  }

  /**
   * @see org.polarsys.kitalpha.transposer.transformation.emf.util.EmfDomainHelper#isHotSpot(java.lang.Object)
   */
  @Override
  public boolean isHotSpot(Object object_p) {
    return true;
  }
}
