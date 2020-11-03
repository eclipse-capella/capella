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
package org.polarsys.capella.core.data.migration.aird;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.business.internal.resource.AirDResourceFactory;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.eclipse.sirius.business.internal.resource.parser.RepresentationsFileXMIHelper;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;

/**
 * 
 */
@SuppressWarnings("restriction")
public class AirdMigrationRunnable extends ModelMigrationRunnable {

  /**
   * @param file
   */
  public AirdMigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  protected boolean shallBeLoaded(URI uri) {
    // We want to load all kind of resources while this migration
    return true;
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_DiagramMigration;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable#doCreateResource(org.eclipse.emf.common
   * .util.URI)
   */
  @Override
  public XMLResource doCreateResource(URI uri, final MigrationContext context) {
    if (CapellaResourceHelper.isAirdResource(uri)) {

      return (XMIResource) new AirDResourceFactory() {

        @Override
        protected XMIResource doCreateAirdResourceImpl(URI uri) {
          return new AirDResourceImpl(uri) {

            @Override
            protected XMIExtensionHelperImpl createXMLHelper() {
              return createCapellaXMLHelper(this);
            }

            @Override
            protected XMLLoad createXMLLoad() {
              return createCustomizedHandler(createXMLHelper(), context);
            }

            @Override
            protected XMLLoad createXMLLoad(Map<?, ?> options) {
              return createXMLLoad();
            }

            @Override
            protected void init() {
              super.init();

              // We want the loaded version information, even if there is no Sirius migration needed. maybe we have some
              // too
              RepresentationsFileVersionSAXParser parser = new RepresentationsFileVersionSAXParser(getURI());
              String loadedVersion = parser.getVersion(new NullProgressMonitor());
              getDefaultLoadOptions().put(SiriusMigrationContribution.SIRIUS_VERSION, loadedVersion);

            }
          };
        }

      }.createResource(uri);

    } else if (CapellaResourceHelper.AFM_FILE_EXTENSION.equals(uri.fileExtension())) {
      // we want to use the default resource for this one.
      return null;
    }

    return super.doCreateResource(uri, context);
  }

  @Override
  public XMIExtensionHelperImpl createCapellaXMLHelper(XMLResource resource) {
    // Ideally, the XMLHelper used should be inherited from RepresentationsFileXMIHelper. But, as
    // XMIExtensionHelperImpl and RepresentationsFileXMIHelper are not in the same inheritance branch we do
    // inheritance by composition instantiating RepresentationsFileXMIHelper as delegate.
    final RepresentationsFileXMIHelper delegateXMLHelper = new RepresentationsFileXMIHelper(resource);
    XMIExtensionHelperImpl result = new XMIExtensionHelperImpl(resource) {

      @Override
      public String getID(EObject obj) {
        // [525261] Add technical id on Sirius meta-model
        // getId on RepresentationsFileXMIHelper has been added to avoid save of xmi:id when an uid exist.
        return delegateXMLHelper.getID(obj);
      }

      @Override
      public EClassifier getType(EFactory eFactory, String typeName) {
        EClassifier type = null;
        if (eFactory != null) {
          EPackage ePackage = eFactory.getEPackage();
          if (extendedMetaData != null) {
            type = extendedMetaData.getType(ePackage, typeName);
          }
          if (type == null) {
            EClass eClass = (EClass) ePackage.getEClassifier(typeName);
            if ((eClass == null) && (xmlMap != null)) {
              return xmlMap.getClassifier(ePackage.getNsURI(), typeName);
            }
            return eClass;
          }
        }
        return type;
      }

          @Override
          public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
              delegateXMLHelper.setValue(object, feature, value, position);
          }

      @Override
      public EObject createObject(EFactory eFactory, EClassifier type) {
        return delegateXMLHelper.createObject(eFactory, type);
      }

      @Override
      public URI deresolve(URI uri) {
        return delegateXMLHelper.deresolve(uri);
      }

      @Override
      public String convertToString(EFactory factory, EDataType dataType, Object value) {
        return delegateXMLHelper.convertToString(factory, dataType, value);
      }

      /**
       * Copied from
       * org.eclipse.sirius.business.internal.resource.parser.RepresentationsFileXMIHelper.createFromString(EFactory,
       * EDataType, String).
       * 
       * @see org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl#createFromString(org.eclipse.emf.ecore.EFactory,
       *      org.eclipse.emf.ecore.EDataType, java.lang.String)
       */
      @Override
      protected Object createFromString(EFactory eFactory, EDataType eDataType, String value) {
        if (value != null && eDataType.equals(ViewpointPackage.eINSTANCE.getResourceDescriptor())) {
          // ResourceDescriptor(String) constructor converts string into URI
          // That URI is used to get a relative URI
          URI resolvedURI = new ResourceDescriptor(value).getResourceURI().resolve(resourceURI);
          return new ResourceDescriptor(resolvedURI);
        }
        return super.createFromString(eFactory, eDataType, value);
      }

    };

    return result;
  }

}
