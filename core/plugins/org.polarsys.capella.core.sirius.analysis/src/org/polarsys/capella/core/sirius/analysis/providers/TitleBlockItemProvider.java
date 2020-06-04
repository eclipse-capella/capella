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
package org.polarsys.capella.core.sirius.analysis.providers;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.provider.DAnnotationItemProvider;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

public class TitleBlockItemProvider extends DAnnotationItemProvider {
  public TitleBlockItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public String getString(String key) {
    return "Title Block";
  }

  @Override
  public Object getParent(Object object) {
    DAnnotation annotation = (DAnnotation) object;
    if (TitleBlockHelper.isElementTitleBlock(annotation)) {
      return TitleBlockHelper.getReferencedElement(annotation);

    } else if (TitleBlockHelper.isTitleBlockCell(annotation) || TitleBlockHelper.isTitleBlockLine(annotation)
        || TitleBlockHelper.isContentTitleBlock(annotation)) {
      Session session = SessionManager.INSTANCE.getSession(annotation);
      if (session != null) {
        Collection<Setting> referencer = session.getSemanticCrossReferencer().getInverseReferences(annotation,
            DescriptionPackage.Literals.DANNOTATION__REFERENCES, false);
        if (!referencer.isEmpty()) {
          return referencer.iterator().next().getEObject();
        }
      }
    }
    return super.getParent(object);
  }

}