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
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;

/**
 * 
 */
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

}
