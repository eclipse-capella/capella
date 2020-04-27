/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param fragmentEnd
   * @return The fragment end type.
   */
  public static FRAGMENT_END_TYPE getFragmentEndType(FragmentEnd fragmentEnd) {
    AbstractFragment abstractFragment = fragmentEnd.getAbstractFragment();
    if (abstractFragment != null) {
      FragmentEnd start = (FragmentEnd) abstractFragment.getStart();
      FragmentEnd finish = (FragmentEnd) abstractFragment.getFinish();

      return (fragmentEnd.equals(start) ? FRAGMENT_END_TYPE.START :
        fragmentEnd.equals(finish) ? FRAGMENT_END_TYPE.FINISH : FRAGMENT_END_TYPE.UNDEFINED);
    }
    return FRAGMENT_END_TYPE.UNDEFINED;
  }

  /**
   * @param fragmentEnd
   * @return The opposite fragment end.
   */
  public static FragmentEnd getOppositeFragmentEnd(FragmentEnd fragmentEnd) {
    AbstractFragment abstractFragment = fragmentEnd.getAbstractFragment();
    if (abstractFragment != null) {
      FragmentEnd start = (FragmentEnd) abstractFragment.getStart();
      FragmentEnd finish = (FragmentEnd) abstractFragment.getFinish();

      return (fragmentEnd.equals(start) ? finish : fragmentEnd.equals(finish) ? start : null);
    }
    return null;
  }
}
