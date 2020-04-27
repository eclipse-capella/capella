/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.session;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.core.RepositoryProvider;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * Base class to implement SessionAdvison for SCM purpose e.g Clearcase, SVN (Capella strategy only)
 */
public abstract class AbstractScmSessionAdvisor implements ISessionAdvisor {
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Get checked out resources (recursively) for specified project.
   * @param resource_p
   * @return
   */
  protected List<IResource> getCheckedOutResources(IResource resource_p) {
    final List<IResource> checkedOutResources = new ArrayList<IResource>(0);
    try {
      resource_p.accept(new IResourceVisitor() {

        public boolean visit(IResource resource__p) throws CoreException {
          boolean cr = true;
          switch (resource__p.getType()) {
            case IResource.PROJECT:
              // If current resource is controlled by a SCM...
              cr = addToCheckedOutResources(checkedOutResources, resource__p);
            break;
            case IResource.FOLDER:
              cr = addToCheckedOutResources(checkedOutResources, resource__p);
            break;
            case IResource.FILE:
              // Don't visit members.
              addToCheckedOutResources(checkedOutResources, resource__p);
              cr = false;
            break;
          }
          return cr;
        }

        /**
         * Add to check out resources if conditions meet.
         * @param checkedOutResources_p
         * @param resource__p
         * @return <code>true</code> means added.
         */
        protected boolean addToCheckedOutResources(final List<IResource> checkedOutResources_p, IResource resource__p) {
          boolean cr = (null != RepositoryProvider.getProvider(resource__p.getProject()));
          if (cr && isCheckedOut(resource__p)) {
            checkedOutResources_p.add(resource__p);
          }
          return cr;
        }
      });
    } catch (CoreException exception_p) {
      if (__logger.isInfoEnabled()) {
        StringBuilder loggerMessage = new StringBuilder("AbstractScmSessionAdvisor.getCheckedOutResources(..) _ "); //$NON-NLS-1$
        __logger.info(loggerMessage.toString(), exception_p);
        __logger.info(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
      }
    }
    return checkedOutResources;
  }

  /**
   * Is given resource checked out (i.e in RW) ?
   * @param resource_p
   * @return
   */
  protected boolean isCheckedOut(IResource resource_p) {
    return !resource_p.getResourceAttributes().isReadOnly();
  }
}
