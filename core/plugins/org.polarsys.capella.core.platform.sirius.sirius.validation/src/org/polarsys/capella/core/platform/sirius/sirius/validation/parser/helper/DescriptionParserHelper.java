package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionParserHelper {

  public static String getLinkIdFromStatus(String statusMessage) {
    Pattern pattern = Pattern.compile("\\(id: (.+?)\\)");

    Matcher matcher = pattern.matcher(statusMessage);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;

  }
}
