/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.provider;

import java.util.Arrays;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.interaction.StateFragment;

public class StateFragmentItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public StateFragmentItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public String getText(Object object) {
    AbstractFunction function = ((StateFragment) object).getRelatedAbstractFunction();
    AbstractState state = ((StateFragment) object).getRelatedAbstractState();

    String label = ((StateFragment) object).getName();
    String text = label == null || label.length() == 0
        ? "[" + CapellaModellerEditPlugin.INSTANCE.getString("_UI_StateFragment_type") + "]" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        : label;

    if (state != null) {
      text = text + " to " + EObjectExt.getText(state); //$NON-NLS-1$
    } else if (function != null) {
      text = text + " to " + EObjectExt.getText(function); //$NON-NLS-1$
    }

    return text;
  }

  @Override
  public Object getImage(Object object) {
    AbstractFunction function = ((StateFragment) object).getRelatedAbstractFunction();
    AbstractState state = ((StateFragment) object).getRelatedAbstractState();

    final Object image;
    boolean withOverlay = true;
    if (state != null) {
      image = getImageForwarded(state);
    } else if (function != null) {
      image = getImageForwarded(function);
    } else {
      image = overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage("full/obj16/StateFragment")); //$NON-NLS-1$
      withOverlay = false;
    }
    if (withOverlay) {
      return new ComposedImage(
          Arrays.asList(image, CapellaModellerEditPlugin.INSTANCE.getImage("full/ovr16/StateFragmentOverlay"))); //$NON-NLS-1$
    }
    return image;
  }

  private Object getImageForwarded(EObject element) {
    IItemLabelProvider itemLabelProvider = EObjectExt.getItemLabelProvider(element);
    return itemLabelProvider.getImage(element);
  }
}
