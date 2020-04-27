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
package org.polarsys.capella.core.data.migration.capella;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * This class is used to remove potential references to holdingResource which can lead to invalid proxies.
 */
public class HoldingResourceRemovalContribution extends AbstractMigrationContribution {

  Collection<EObject> proxies = new ArrayList<EObject>();

  private final String schemeResource = "capella";
  private final String holdingResource = "holdingResource." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;

  @Override
  public void unaryStartMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    proxies.clear();
  }

  protected boolean isHoldingResourceUri(URI uri) {
    if (uri != null) {
      String scheme = uri.scheme();
      if (schemeResource.equals(scheme)) {
        String host = uri.host();
        String file = uri.path();
        if (holdingResource.equals(host) || holdingResource.equals(file)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    if (currentElement.eIsProxy()) {
      if (isHoldingResourceUri(((InternalEObject) currentElement).eProxyURI())) {
        proxies.add(currentElement);
      }
    }
    super.unaryMigrationExecute(currentElement, context);
  }

  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    super.unaryEndMigrationExecute(executionManager, resource, context);

    if (!proxies.isEmpty()) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(executionManager, proxies, false, false, true);
      if (command.canExecute()) {
        command.execute();
      }
    }
  }

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position, XMLResource resource,
      MigrationContext context) {
    // We remove the holdingResource from the <semanticResources>
    if (ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES.equals(feature)) {
      if (value instanceof String) {
        if (((String) value).contains(holdingResource)) {
          return true;
        }
      }
    }

    // We remove the holdingResource from the <models> (since sirius also modify <models> to <semanticResources> unaryPostMigrationExecute can't work here)
    if (ViewpointPackage.Literals.DANALYSIS__MODELS.equals(feature)) {
      if (value instanceof EObject) {
        if (isHoldingResourceUri(((InternalEObject) value).eProxyURI())) {
          return true;
        }
      }
    }

    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

}
