/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.typereferencename;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckReferenceNameTest extends BasicTestCase {

  protected String referenceName;

  public CheckReferenceNameTest(String areferenceName) {
    referenceName = areferenceName;
  }

  @Override
  public void test() throws Exception {
    // check the reference name is not null
    assertNotNull("reference name is null", referenceName);

    // boolean value which stores if the reference name is found in the structural features list
    // default value false
    boolean eIsContainment = false;

    // get all ecore packages
    List<EPackage> allPkg = new ArrayList<EPackage>();
    Set<String> keySet = EPackage.Registry.INSTANCE.keySet();
    for (String pname : keySet) {
      if (pname.contains("www.polarsys.org")) { //$NON-NLS-1$
        EPackage p = EPackage.Registry.INSTANCE.getEPackage(pname);
        allPkg.add(p);
      }

    }

    // for all ecore packages, check if the actual reference name refer to a Structural Feature contained in a ecore
    // package
    for (EPackage pack : allPkg) {
      Iterator<?> it = EcoreUtil.getAllContents(pack.eContents());

      while (it.hasNext()) {

        Object o = it.next();

        if (o instanceof EStructuralFeature) {
          EStructuralFeature feature = (EStructuralFeature) o;
          if (feature.getName().equals(referenceName)) {
            eIsContainment = true;

          }
        }
      }
    }
    assertTrue("The reference name is not a structural feature", eIsContainment); //$NON-NLS-1$

  }

}
