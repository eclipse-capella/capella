/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.model;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class InternalLinks extends DiagramObjectFilterTestCase {

  public static final String SA_FUNCTIONALCHAIN_1 = "27bcfe37-4bc3-4a21-8c8d-de6fd5b7be96"; //$NON-NLS-1$
  public static final String SA_FUNCTIONALCHAIN_2 = "58237d12-f0d4-422f-820c-446f8802b9b8"; //$NON-NLS-1$
  public static final String SA_FUNCTIONALCHAIN_3 = "a5f0ee76-17d4-42f3-adf7-42af1859233f"; //$NON-NLS-1$
  
  public static final String SA_PHYSICALPATH_1 = "3f2fc7be-749f-498d-9217-8ca597c01b52"; //$NON-NLS-1$
  public static final String SA_PHYSICALPATH_2 = "49f7aa15-780f-4ac0-8ed5-7090f7c7085a"; //$NON-NLS-1$
  public static final String SA_PHYSICALPATH_3 = "c227d630-e0d5-477c-8755-73cb0611b405"; //$NON-NLS-1$
  
  public static final String LA_FUNCTIONALCHAIN_1 = "fd9cf224-a0d0-4995-a4d5-428b898a74c2"; //$NON-NLS-1$
  public static final String LA_FUNCTIONALCHAIN_2 = "765078f1-47b0-45e5-810d-da2b7d6c2ab2"; //$NON-NLS-1$
  public static final String LA_FUNCTIONALCHAIN_3 = "46cf15e0-2f26-4a5e-bdc1-9747a4eb512b"; //$NON-NLS-1$
  
  public static final String LA_PHYSICALPATH_1 = "8e16d32a-fa40-4185-9505-bcd852cf78e1"; //$NON-NLS-1$
  public static final String LA_PHYSICALPATH_2 = "7feb6f19-7f9c-4ce2-981e-624bcc3a74d9"; //$NON-NLS-1$
  public static final String LA_PHYSICALPATH_3 = "a05a2393-eabf-41a1-ac13-0c5008cda705"; //$NON-NLS-1$
  
  public static final String PA_FUNCTIONALCHAIN_1 = "5e282044-053f-4414-a37a-19145a6300e8"; //$NON-NLS-1$
  public static final String PA_FUNCTIONALCHAIN_2 = "0800f070-5897-426f-a326-121a604469bc"; //$NON-NLS-1$
  public static final String PA_FUNCTIONALCHAIN_3 = "0627753a-92c8-41e5-92e6-9138003c8b17"; //$NON-NLS-1$
  
  public static final String PA_PHYSICALPATH_1 = "42e00c0f-bafa-436a-a7aa-5966908f8f30"; //$NON-NLS-1$
  public static final String PA_PHYSICALPATH_2 = "0a2d802a-85f6-431d-9f44-8c5cab84fec8"; //$NON-NLS-1$
  public static final String PA_PHYSICALPATH_3 = "77608558-0c2e-48dd-b024-d7f7a3473317"; //$NON-NLS-1$
  @Override
  protected String getTestProjectName() {
    return InternalLinks.class.getSimpleName();
  }

}
