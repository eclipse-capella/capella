/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration.listener;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.core.af.integration.CapellaContribution;
import org.polarsys.kitalpha.ad.metadata.helpers.Contribution;

/**
 * This class update the AFM file according to the usage of the Capella Viewpoint
 * It is restricted to the CapellaContribution to avoid unwanted behavior while this update
 * 
 * This will may be promoted to org.polarsys.kitalpha.ad.metadata to update all viewpoint contributions after further analysis
 */
public class MetadataViewpointUpdater extends ResourceSetListenerImpl implements IEditingDomainListener {

  public MetadataViewpointUpdater() {
    super(NotificationFilter.createFeatureFilter(ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES));
  }

  @Override
  public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
   return new RecordingCommand(event.getEditingDomain()) {

     @Override
      public void doExecute() {
        for (Contribution contrib : Contribution.getContributions()) {
          //When a capella resource is added on the semantic resources, we enable/remove the capella viewpoint
          if (contrib instanceof CapellaContribution) {
            contrib.update(event.getEditingDomain().getResourceSet());
          }
        }
      }
    };
  }

  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain)editingDomain).addResourceSetListener(this);
  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    //Nothing here
  }

}
