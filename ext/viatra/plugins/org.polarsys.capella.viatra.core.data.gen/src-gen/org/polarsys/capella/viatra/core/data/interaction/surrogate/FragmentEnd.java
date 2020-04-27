/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.FragmentEnd__abstractFragment;

/**
 * A pattern group formed of all public patterns defined in FragmentEnd.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FragmentEnd.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FragmentEnd__abstractFragment</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FragmentEnd extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FragmentEnd instance() {
    if (INSTANCE == null) {
        INSTANCE = new FragmentEnd();
    }
    return INSTANCE;
  }
  
  private static FragmentEnd INSTANCE;
  
  private FragmentEnd() {
    querySpecifications.add(FragmentEnd__abstractFragment.instance());
  }
  
  public FragmentEnd__abstractFragment getFragmentEnd__abstractFragment() {
    return FragmentEnd__abstractFragment.instance();
  }
  
  public FragmentEnd__abstractFragment.Matcher getFragmentEnd__abstractFragment(final ViatraQueryEngine engine) {
    return FragmentEnd__abstractFragment.Matcher.on(engine);
  }
}
