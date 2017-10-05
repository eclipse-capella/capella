/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
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

  static final String FORMAT_UTF8 = "UTF-8"; //$NON-NLS-1$

  /**
   * @param file
   */
  public AirdMigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_DiagramMigration;
  }

  /**
   * Checks if is aird file.
   * 
   * @param uri
   *          the given URI
   * @return true, if is aird file
   */
  static boolean isAirdFile(URI uri) {
    boolean res = (uri != null)
        && (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(uri.fileExtension()) || CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION
            .equals(uri.fileExtension()));
    return res;
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
    if (isAirdFile(uri)) {

      XMLResource result = new AirDResourceImpl(uri) {
        
        @Override
        protected XMIExtensionHelperImpl createXMLHelper() {
          return createCapellaXMLHelper(this);
        }
        
        @Override
        protected XMLLoad createXMLLoad() {
          return createCustomizedHandler(createXMLHelper(), context);
        }

        @Override
        protected XMLLoad createXMLLoad(Map<?, ?> options)
        {
          return createXMLLoad();
        }
        
        // @Override
        // protected XMLSave createXMLSave() {
        // return createCustomizedSaveHandler(createXMLHelper(), context);
        // }

        @Override
        protected void init() {
          super.init();

          org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser parser = new RepresentationsFileVersionSAXParser(
              getURI());
          String loadedVersion = parser.getVersion(new NullProgressMonitor());

          getDefaultLoadOptions().put("VERSION", loadedVersion);

          // like CapellamodellerResource and AirDResourceFactory, add some
          // initial properties
          getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, FORMAT_UTF8);
          getDefaultLoadOptions().put(XMLResource.OPTION_ENCODING, FORMAT_UTF8);
          getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());

          getDefaultSaveOptions().put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
          getDefaultSaveOptions().put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));

          // Avoid any error with unknown features which can exist in aird
          // files. Migration of aird will be processed after
          getDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

          AirDResourceImpl.addMigrationOptions(loadedVersion, getDefaultLoadOptions(), getDefaultSaveOptions());
          
          if (!getEncoding().equals(FORMAT_UTF8)) {
            setEncoding(FORMAT_UTF8);
          }
        }

      };

      return result;
    }

    return super.doCreateResource(uri, context);
  }

  @Override
  @SuppressWarnings("restriction")
  public XMIExtensionHelperImpl createCapellaXMLHelper(XMLResource resource) {
      // Ideally, the XMLHelper used should be inherited from RepresentationsFileXMIHelper. But, as
      // XMIExtensionHelperImpl and RepresentationsFileXMIHelper are not in the same inheritance branch we do
      // inheritance by composition instantiating RepresentationsFileXMIHelper as delegate.
      final RepresentationsFileXMIHelper delegateXMLHelper = new RepresentationsFileXMIHelper(resource);
      XMIExtensionHelperImpl result = new XMIExtensionHelperImpl(resource) {
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
              super.setValue(object, feature, value, position);
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
