/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Migration contribution that migrates Capella legacy resources references. The legacy resources are converted to
 * modern resources (renamed) beforehand (see MigrationHelpers.migrateCandidatesExtensions(Collection<IResource>)). The
 * renaming transforms all the legacy resource references to proxies, that we convert here.
 *
 */
public class LegacyResourceMigrationContribution extends AbstractMigrationContribution {

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {

    if (peekObject instanceof DAnalysis && ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES.equals(feature)
        && value instanceof String) {
      String semanticResourceString = (String) value;
      IPath semanticResourcePath = Path.fromPortableString(semanticResourceString);

      if (CapellaResourceHelper.isLegacyCapellaResourcePath(semanticResourcePath)) {
        IPath mondernPath = CapellaResourceHelper.convertLegacyResourcePathToModern(semanticResourcePath);

        return mondernPath.toPortableString();
      }
    }
    return super.getValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context) {

    if (result instanceof InternalEObject && result.eIsProxy()) {
      InternalEObject internalObject = (InternalEObject) result;
      URI internalObjectURI = internalObject.eProxyURI();

      if (internalObjectURI.isPlatformResource()) {
        String platformString = internalObjectURI.toPlatformString(true);
        IPath platformPath = Path.fromPortableString(platformString);

        if (CapellaResourceHelper.isLegacyCapellaResourcePath(platformPath)) {
          IPath mondernPath = CapellaResourceHelper.convertLegacyResourcePathToModern(platformPath);
          String modernPathString = mondernPath.toPortableString();

          String fragment = internalObjectURI.fragment();
          URI modernURI = URI.createPlatformResourceURI(modernPathString, true).appendFragment(fragment);

          internalObject.eSetProxyURI(modernURI);
        }
      }
    } else {
      super.updateElement(peekObject, typeName, result, feature, resource, context);
    }
  }

  @Override
  public String getHandleProxy(InternalEObject proxy, String uriLiteral, Resource resource, XMLHelper helper,
      MigrationContext context) {

    if (isLegacyURILiteral(uriLiteral)) {
      return convertLegacyURILiteralToModern(uriLiteral);
    }

    return super.getHandleProxy(proxy, uriLiteral, resource, helper, context);
  }

  private boolean isLegacyURILiteral(String uriLiteral) {
    Set<String> legacyExtensions = CapellaResourceHelper.LEGACY_TO_MODERN_FILE_EXTENSIONS.keySet();

    for (String legacyExtension : legacyExtensions) {
      if (uriLiteral.contains(legacyExtension)) {
        return true;
      }
    }
    return false;
  }

  private String convertLegacyURILiteralToModern(String uriLiteral) {
    String modernLiteral = uriLiteral;

    for (Map.Entry<String, String> entry : CapellaResourceHelper.LEGACY_TO_MODERN_FILE_EXTENSIONS.entrySet()) {
      String legacyExtension = entry.getKey();
      String modernExtension = entry.getValue();

      modernLiteral = modernLiteral.replace(legacyExtension, modernExtension);
    }

    return modernLiteral;
  }

}
