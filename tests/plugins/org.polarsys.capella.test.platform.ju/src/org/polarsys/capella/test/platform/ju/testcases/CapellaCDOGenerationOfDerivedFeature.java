/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.model.helpers.IModelConstants;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import com.google.common.collect.Sets;

/**
 * This class test the generating model EPackage for all Capella package, if catch exception
 * UnsupportedOperationException Assertion failed error is thrown
 */
public class CapellaCDOGenerationOfDerivedFeature extends BasicTestCase {

  @Override
  public void test() throws Exception {

    Registry registry = EPackage.Registry.INSTANCE;
    Set<String> uris = Sets.newHashSet(registry.keySet());
    for (String uri : uris) {
      EPackage ePackage = registry.getEPackage(uri);
      if (uri.contains("capella")) {
        for (EClassifier c : ePackage.getEClassifiers()) {
          if (c instanceof EClass) {
            EClass eclass = (EClass) c;
            if (!eclass.isAbstract()) {
              EObject object = eclass.getEPackage().getEFactoryInstance().create(eclass);
              ExecutionManager executionManager = ExecutionManagerRegistry.getInstance().addNewManager();
              TransactionalEditingDomain domainTransaction = executionManager.getEditingDomain();
              domainTransaction.getCommandStack().execute(new RecordingCommand(domainTransaction) {

                @Override
                protected void doExecute() {
                  HoldingResourceHelper.getHoldingResource(domainTransaction).getContents().add(object);
                  for (EReference reference : eclass.getEAllReferences()) {
                    if (reference.isDerived()
                        && reference.getEAnnotation(IModelConstants.HELPER_ANNOTATION_SOURCE) != null) {
                      try {
                        object.eGet(reference);
                      } catch (UnsupportedOperationException e) {
                        assertTrue(e.getMessage(), false);
                      }
                    }
                  }
                }
              });
            }
          }
        }
      }
    }
  }
}
