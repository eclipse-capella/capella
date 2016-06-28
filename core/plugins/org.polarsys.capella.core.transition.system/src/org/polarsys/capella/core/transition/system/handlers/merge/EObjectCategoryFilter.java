/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class EObjectCategoryFilter extends CategoryFilter {

  public EObjectCategoryFilter(IContext context, String name, Image image) {
    super(context, name, image);
    setActive(true);
    setInFocusMode(true);
    setVisible(false);
  }

  public EObjectCategoryFilter(IContext context, EClass iconClazz, String name) {
    this(context, iconClazz);
    this.name = name;
  }

  public EObjectCategoryFilter(IContext context, EClass iconClazz) {
    super(context, null, null);
    TransactionalEditingDomain domain = (TransactionalEditingDomain) TransactionUtil
        .getEditingDomain(((EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT)));
    Resource res = HoldingResourceHelper.getHoldingResource(domain);
    if (iconClazz != null && !iconClazz.isAbstract()) {
      EObject obj = ((EPackage) iconClazz.eContainer()).getEFactoryInstance().create(iconClazz);
      adapt(obj);
      res.getContents().add(obj);
      this.image = EObjectLabelProviderHelper.getImage(obj);
      this.name = EObjectLabelProviderHelper.getMetaclassLabel(obj, false);
    }
    setActive(true);
    setInFocusMode(true);
    setVisible(false);
  }

  protected void adapt(EObject obj) {

  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IElementRelativePresence) {
      EObject o = ((IElementRelativePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (o != null && keepElement(o)) {
        return true;
      }
      o = ((IElementRelativePresence) difference).getElementMatch().get(Role.TARGET);
      if (o != null && keepElement(o)) {
        return true;
      }
    }
    return false;
  }

  public boolean keepElement(Object element) {
    return false;
  }
}
