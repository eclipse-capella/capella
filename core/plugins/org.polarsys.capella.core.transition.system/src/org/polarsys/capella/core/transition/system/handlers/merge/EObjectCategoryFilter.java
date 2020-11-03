/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class EObjectCategoryFilter extends CategoryFilter {

  public EObjectCategoryFilter(IContext context, EClass iconClazz, String name) {
    this(context, iconClazz);
    this.name = NLS.bind(Messages.EObjectCategoryFilter, name);
    this.description = NLS.bind(Messages.EObjectCategoryFilter_Description, name);
  }
  
  public EObjectCategoryFilter(IContext context, EClass iconClazz, String name, String description) {
    this(context, iconClazz);
    this.name = NLS.bind(Messages.EObjectCategoryFilter, name);
    this.description = NLS.bind(Messages.EObjectCategoryFilter_Description, description);
  }

  public EObjectCategoryFilter(IContext context, EClass iconClazz) {
    super(context, null, null, null, null);
    TransactionalEditingDomain domain = TransactionHelper
        .getEditingDomain(((EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT)));
    Resource res = HoldingResourceHelper.getHoldingResource(domain);
    if (iconClazz != null && !iconClazz.isAbstract()) {
      EObject obj = ((EPackage) iconClazz.eContainer()).getEFactoryInstance().create(iconClazz);
      adapt(obj);
      res.getContents().add(obj);
      this.image = EObjectLabelProviderHelper.getImage(obj);
      this.name = EObjectLabelProviderHelper.getMetaclassLabel(obj, false);
      this.description = NLS.bind(Messages.EObjectCategoryFilter_Description, this.name);
      this.id = getId() + "." + iconClazz.getName(); //$NON-NLS-1$
    }
    setCategorySet(ITransitionConstants.CATEGORY_SEMANTIC);
    setActive(false);
    setInFocusMode(true);
    setVisible(true);
  }

  protected void adapt(EObject obj) {

  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    if (difference instanceof EElementRelativePresence) {
      EObject o = ((EElementRelativePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (o != null && keepElement(o)) {
        return true;
      }
      o = ((EElementRelativePresence) difference).getElementMatch().get(Role.TARGET);
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
