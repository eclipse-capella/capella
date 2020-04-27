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
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticResourceSet;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test ensure that SemanticResourceSet.registerAdapters is registering DataNotifier always before the TransactionChangeRecorder
 */
public class DataNotifierBeforeTransactionRecorder extends BasicTestCase {

  @Override
  public void test() throws Exception {
    SemanticResourceSet set;
    TransactionChangeRecorder recorder;

    // Add a data notifier then change recorder, data notifier shall be before the recorder
    set = createResourceSet();
    recorder = getChangeRecorder(set);
    set.registerAdapters();
    set.eAdapters().add(recorder);

    int dataNotifier = set.eAdapters().indexOf(((SemanticEditingDomain) set.getEditingDomain()).getDataNotifier());
    int changeRecorder = set.eAdapters().indexOf(recorder);
    assertTrue("DataNotifier shall be registered before the Recorder", dataNotifier < changeRecorder);

    // Add a change recorder then data notifier, data notifier shall be before the recorder
    set = createResourceSet();
    recorder = getChangeRecorder(set);
    set.eAdapters().add(recorder);
    set.registerAdapters();

    dataNotifier = set.eAdapters().indexOf(((SemanticEditingDomain) set.getEditingDomain()).getDataNotifier());
    changeRecorder = set.eAdapters().indexOf(recorder);
    assertTrue("DataNotifier shall be registered before the Recorder", dataNotifier < changeRecorder);
  }

  private TransactionChangeRecorder getChangeRecorder(SemanticResourceSet set) {
    return ((TransactionalEditingDomainImpl) set.getEditingDomain()).getChangeRecorder();
  }

  /**
   * Create a specific SemanticResourceSet associated with a SemanticEditingDomain containing a
   * TransactionalChangeRecorder but SemanticResourceSet.eAdapters() has been cleared
   */
  private SemanticResourceSet createResourceSet() {
    SemanticEditingDomainFactory factory = new SemanticEditingDomainFactory();
    SemanticResourceSet set = factory.new SemanticResourceSet();

    factory.new SemanticEditingDomain(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(),
        new TransactionalCommandStackImpl(), set) {
      @Override
      protected TransactionChangeRecorder createChangeRecorder(ResourceSet rset) {
        return new TransactionChangeRecorder((InternalTransactionalEditingDomain) set.getEditingDomain(), set);
      }
    };
    set.eAdapters().clear();
    return set;
  }

}
