/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.interaction.services;

import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.FragmentEnd;

/**
 * FragmentEnd helpers
 *
 */
public class FragmentEndExt {

  /**
   * 
   */
  public enum FRAGMENT_END_TYPE {
    /**
     */
    UNDEFINED,
    /**
     */
    START,
    /**
     */
    FINISH
  }

  /**
   * @param fragmentEnd_p
   * @return The fragment end type.
   */
  public static FRAGMENT_END_TYPE getFragmentEndType(FragmentEnd fragmentEnd_p) {
    AbstractFragment abstractFragment = fragmentEnd_p.getAbstractFragment();
    if (abstractFragment != null) {
      FragmentEnd start = (FragmentEnd) abstractFragment.getStart();
      FragmentEnd finish = (FragmentEnd) abstractFragment.getFinish();

      return (fragmentEnd_p.equals(start) ? FRAGMENT_END_TYPE.START :
        fragmentEnd_p.equals(finish) ? FRAGMENT_END_TYPE.FINISH : FRAGMENT_END_TYPE.UNDEFINED);
    }
    return FRAGMENT_END_TYPE.UNDEFINED;
  }

  /**
   * @param fragmentEnd_p
   * @return The opposite fragment end.
   */
  public static FragmentEnd getOppositeFragmentEnd(FragmentEnd fragmentEnd_p) {
    AbstractFragment abstractFragment = fragmentEnd_p.getAbstractFragment();
    if (abstractFragment != null) {
      FragmentEnd start = (FragmentEnd) abstractFragment.getStart();
      FragmentEnd finish = (FragmentEnd) abstractFragment.getFinish();

      return (fragmentEnd_p.equals(start) ? finish : fragmentEnd_p.equals(finish) ? start : null);
    }
    return null;
  }
}
