package org.polarsys.capella.core.ui.search.searchfor.item;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * Represents a search item in the Capella search dialog
 */
public interface SearchForItem {
  /**
   * 
   * @return the object represented by this item
   */
  public Object getObject();
  /**
   * 
   * @return the text to display for this search item
   */
  public String getText();

  /**
   * 
   * @return the image to display for this search item
   */
  public Image getImage();
  
  /**
   * 
   * @param obj
   * @return does this search item cover the obj
   */
  public boolean cover(Object obj);
}
