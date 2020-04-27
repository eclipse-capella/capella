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

package org.polarsys.capella.patterns.migration;

import static org.polarsys.capella.patterns.migration.PatternsMigrationConstants.DEFAULT_PATTERN_SUPPORT_GEN_PREFIX;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonpatternsupportFactory;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonpatternsupportPackage;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.data.migration.handlers.AbstractMigrationHandler;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdepatternsupportFactory;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdepatternsupportPackage;

/**
 * A class that maps new classes to old classes of elements to migrate.
 */
public class PatternsCustomizeHandler extends AbstractMigrationHandler {

  /**
   * Return the qualified XMI type name for the given class name and the given prefix
   * 
   * @param prefix
   *          a non-null string
   * @param className_p
   *          a non-null string
   * @return a non-null string
   */
  protected String getQualifiedClassName(String prefix, String className_p) {
    return prefix + ':' + className_p;
  }

  /**
   * @see com.thalesgroup.mde.melody.data.migration.DefaultCustomHandler#getFactoryForPrefix(java.lang.String)
   */
  public EFactory getFactoryForPrefix(String prefix) {
    if (PatternsMigrationConstants.KITALPHA_PATTERN_SUPPORT_GEN_PREFIX.equals(prefix)) {
      EFactory f = EmdepatternsupportFactory.eINSTANCE;
      f.setEPackage(EmdepatternsupportPackage.eINSTANCE);
      return f;
    }
    if (DEFAULT_PATTERN_SUPPORT_GEN_PREFIX.equals(prefix)) {
      EFactory f = CommonpatternsupportFactory.eINSTANCE;
      f.setEPackage(CommonpatternsupportPackage.eINSTANCE);
      return f;
    }
    return null;
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
    for (Object selected : selection.toList()) {
      if (selected instanceof IResource) {
        MigrationHelpers.getInstance().trigger((IResource) selected, HandlerUtil.getActiveShell(event), false, true,
            new String[] { MigrationConstants.MIGRATION_KIND__PATTERN });
      }
    }
    return event;
  }

  @Override
  protected boolean isValidSelection(List<Object> selection) {
    for (Object select : selection) {
      if (!((select instanceof IFile) && PatternCatalogResourceHelper.isPatternCatalogResource((IFile) select))) {
        return false;
      }
    }
    return true;
  }
}
