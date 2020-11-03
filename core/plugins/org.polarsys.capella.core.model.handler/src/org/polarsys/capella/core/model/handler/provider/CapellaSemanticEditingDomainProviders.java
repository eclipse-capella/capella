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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IAdapterFactoryProvider;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IReadOnlyDelegationHandler;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ITransactionChangeRecorderProvider;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.model.handler.crossreferencer.CapellaCrossReferencerProvider;

/**
 * Customize the {@link SemanticEditingDomain} for Capella needs.
 */
public class CapellaSemanticEditingDomainProviders implements ISemanticEditingDomainProviders {
  /**
   * Provides the cross referencer used in Capella.
   */
  private ICrossReferencerProvider _capellaCrossReferencerProvider;
  /**
   * Provides the file modification pre-commit listener used in Capella.
   */
//  private FileModificationPreCommitListenerProvider _fileModificationPreCommitListenerProvider;
  /**
   * Read only delegation handler.
   */
  private IReadOnlyDelegationHandler _readOnlyDelegationHandler;
  /**
   * Delegated semantic editing domain providers.
   */
  private ISemanticEditingDomainProviders _delegatedSemanticEditingDomainProviders;
  /**
   * Delegated adapter factory provider.
   */
  private IAdapterFactoryProvider _capellaAdapterFactoryProvider;

  /**
   * Constructor.
   */
  public CapellaSemanticEditingDomainProviders() {
    loadSemanticEditingDomainProviders();
  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders#getCrossReferencerProvider()
   */
  public ICrossReferencerProvider getCrossReferencerProvider() {
    if (null == _capellaCrossReferencerProvider) {
      if (null != _delegatedSemanticEditingDomainProviders) {
        _capellaCrossReferencerProvider = _delegatedSemanticEditingDomainProviders.getCrossReferencerProvider();
      }
      if (null == _capellaCrossReferencerProvider) {
        // Default implementation.
        _capellaCrossReferencerProvider = new CapellaCrossReferencerProvider();
      }
    }
    return _capellaCrossReferencerProvider;
  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders#getAdapterFactoryProvider()
   */
  public IAdapterFactoryProvider getAdapterFactoryProvider() {
    if (null == _capellaAdapterFactoryProvider) {
      if (null != _delegatedSemanticEditingDomainProviders) {
        _capellaAdapterFactoryProvider = _delegatedSemanticEditingDomainProviders.getAdapterFactoryProvider();
      }
      if (null == _capellaAdapterFactoryProvider) {
        // Default implementation.
        _capellaAdapterFactoryProvider = CapellaAdapterFactoryProvider.getInstance();
      }
    }
    return _capellaAdapterFactoryProvider;
  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders#getPreCommitListenerProvider()
   */
//  public IPreCommitListenerProvider getPreCommitListenerProvider() {
//    if (null == _fileModificationPreCommitListenerProvider) {
//      _fileModificationPreCommitListenerProvider = new FileModificationPreCommitListenerProvider();
//    }
//    return _fileModificationPreCommitListenerProvider;
//  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders#getReadOnlyDelegationHandler()
   */
  public IReadOnlyDelegationHandler getReadOnlyDelegationHandler() {
    if (null == _readOnlyDelegationHandler) {
      if (null != _delegatedSemanticEditingDomainProviders) {
        _readOnlyDelegationHandler = _delegatedSemanticEditingDomainProviders.getReadOnlyDelegationHandler();
      }
      if (null == _readOnlyDelegationHandler) {
        // Default implementation.
        _readOnlyDelegationHandler = new CapellaReadOnlyDelegationHandler();
      }
    }
    return _readOnlyDelegationHandler;
  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ISemanticEditingDomainProviders#getTransactionChangeRecorderProvider()
   */
  public ITransactionChangeRecorderProvider getTransactionChangeRecorderProvider() {
    ITransactionChangeRecorderProvider result = null;
    if (null != _delegatedSemanticEditingDomainProviders) {
      result = _delegatedSemanticEditingDomainProviders.getTransactionChangeRecorderProvider();
    }
    return result;
  }

  /**
   * Load the unique semantic editing domain providers.
   */
  private void loadSemanticEditingDomainProviders() {
    // Load SemanticEditingDomain providers if any.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.model.handler", "delegatedSemanticEditingDomainProviders"); //$NON-NLS-1$ //$NON-NLS-2$
    // Loop over contributed SemanticEditingDomain providers, must be only one.
    if (configurationElements.length > 0) {
      _delegatedSemanticEditingDomainProviders =
          (ISemanticEditingDomainProviders) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Registry getResourceFactoryRegistry(Registry resourceFactoryRegistry_p) {
    if (null != _delegatedSemanticEditingDomainProviders) {
      return _delegatedSemanticEditingDomainProviders.getResourceFactoryRegistry(resourceFactoryRegistry_p);
    }
    return resourceFactoryRegistry_p;
  }
}
