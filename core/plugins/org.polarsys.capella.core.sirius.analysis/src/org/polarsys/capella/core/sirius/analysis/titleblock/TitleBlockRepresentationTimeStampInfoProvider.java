/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.titleblock;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.refresh.RepresentationTimeStampInformationSupplier;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * This contribution allows that the representation timeStamp will NOT be updated if notifications are only about
 * updating the content title block and the corresponding DNodeListElement name.
 * 
 * @author lfasani
 *
 */
public class TitleBlockRepresentationTimeStampInfoProvider implements RepresentationTimeStampInformationSupplier {

  @Override
  public boolean preventTimeStampFromBeingUpdated(List<Notification> notifications) {
    boolean preventTimeStampFromBeingUpdated = false;
    int allNotifSize = notifications.size();
    List<EObject> updatedContentTitleBlock = notifications.stream().filter(notif -> {
      Object notifier = notif.getNotifier();
      EObject parent = notifier instanceof EObject ? ((EObject) notifier).eContainer() : null;
      return parent instanceof DAnnotation && TitleBlockHelper.isContentTitleBlock((DAnnotation) parent);
    }).map(notif -> ((EObject) notif.getNotifier()).eContainer()).collect(Collectors.toList());

    if (allNotifSize == updatedContentTitleBlock.size() * 2) {
      long notificationsOfRepresentationElementNameOfContentTitleBlockSize = notifications.stream().filter(notif -> {
        EObject notifier = (EObject) notif.getNotifier();
        Object feature = notif.getFeature();
        if (notifier instanceof DNodeListElement
            && ViewpointPackage.eINSTANCE.getDRepresentationElement_Name().equals(feature)) {
          return updatedContentTitleBlock.contains(((DSemanticDecorator) notifier).getTarget());
        }
        return false;
      }).count();

      // The representation timeStamp will NOT be updated if notifications are only about updating the content title
      // block and the corresponding DNodeListElement name
      preventTimeStampFromBeingUpdated = updatedContentTitleBlock.size()
          + notificationsOfRepresentationElementNameOfContentTitleBlockSize == allNotifSize;
    }

    return preventTimeStampFromBeingUpdated;
  }
}
