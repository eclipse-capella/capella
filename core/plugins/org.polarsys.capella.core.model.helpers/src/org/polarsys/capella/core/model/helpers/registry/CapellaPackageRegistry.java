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

package org.polarsys.capella.core.model.helpers.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Static helper methods to query the Global EMF Package Registry 
 * for EPackages defined by the Capella Metamodel
 */
public final class CapellaPackageRegistry {

  private CapellaPackageRegistry(){}

  private static final URI CAPELLA_GENMODEL_URI = URI
      .createPlatformResourceURI("org.polarsys.capella.core.data.gen/model/CapellaModeller.genmodel", true); //$NON-NLS-1$
  private static Collection<EPackage> allCapellaPackages = null;

  /**
   * Returns a collection that includes all capella EPackages from the global EMF Package Registry
   * This collection also includes nested EPackages, e.g. information.datatype.
   * Viewpoints and emde extensions are not returned in this collection
   */
  public static synchronized Collection<EPackage> getAllCapellaPackages() {
    if (allCapellaPackages == null) {
      List<EPackage> l = new ArrayList<EPackage>();
      for (GenPackage p : getCapellaGenModel().getAllGenAndUsedGenPackagesWithClassifiers()) {
        EPackage registered = EPackage.Registry.INSTANCE.getEPackage(p.getNSURI());
        if (registered != null) {
          l.add(registered);
        } else {
          l.add(p.getEcorePackage());
        }
      }
      allCapellaPackages = Collections.unmodifiableList(l);
    }
    return allCapellaPackages;
  }


  /**
   * Returns a (delegating) package registry whose directly registered packages are all packages from the capella
   * genmodel. Packages will be registered even if they are already registered in the delegate registry.
   * 
   * @param delegate
   *          a delegate registry. not null.
   * @return
   */
  public static EPackage.Registry create(EPackage.Registry delegate) {
    EPackage.Registry registry = new EPackageRegistryImpl(delegate);
    for (EPackage p : getAllCapellaPackages()) {
      registry.put(p.getNsURI(), p);
    }
    return registry;
  }


  private static GenModel getCapellaGenModel() {
    ResourceSet rs = new ResourceSetImpl();
    rs.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
    return (GenModel) rs.getResource(CAPELLA_GENMODEL_URI, true).getContents().get(0);
  }
}