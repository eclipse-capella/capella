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
package org.polarsys.capella.common.helpers.validation.xml;

/**
 * An enumeration of all allowed XHTML character entities.
 * 
 * In most cases, the name of the enum literal is the name of the
 * entity, but this cannot be guaranteed as there may be reserved
 * words in java that collide with entity names (such as int), so you should 
 * always use entityName() to get the exact html entity name.
 */
public enum XHTMLEntities {
  
  // NOTES
  // - I used the literal character value in constructors whenever
  //   the "Consolas" font can display the character, otherwise I use the numeric value.
  // - This file should always be encoded in a unicode format.

  quot     ('"', true),   // U+0022 (34)   HTML 2.0  HTMLspecial ISOnum quotation mark (APL quote)
  amp      ('&', true),   // U+0026 (38)   HTML 2.0  HTMLspecial ISOnum ampersand
  apos     ('\'', true),  // U+0027 (39)   XHTML 1.0 HTMLspecial ISOnum apostrophe
  lt       ('<', true),   // U+003C (60)   HTML 2.0  HTMLspecial ISOnum less-than sign
  gt       ('>', true),   // U+003E (62)   HTML 2.0  HTMLspecial ISOnum greater-than sign
  nbsp     ((char)160),   // U+00A0 (160)  HTML 3.2  HTMLspecial ISOnum no-break space (non-breaking space)
  iexcl    ('¡'),         // U+00A1 (161)  HTML 3.2  HTMLlat1  ISOnum  inverted exclamation mark
  cent     ('¢'),         // U+00A2 (162)  HTML 3.2  HTMLlat1  ISOnum  cent sign
  pound    ('£'),         // U+00A3 (163)  HTML 3.2  HTMLlat1  ISOnum  pound sign
  curren   ((char)164),   // U+00A4 (164)  HTML 3.2  HTMLlat1  ISOnum  currency sign
  yen      ('¥'),         // U+00A5 (165)  HTML 3.2  HTMLlat1  ISOnum  yen sign (yuan sign)
  brvbar   ('¦'),         // U+00A6 (166)  HTML 3.2  HTMLlat1  ISOnum  broken bar (broken vertical bar)
  sect     ('§'),         // U+00A7 (167)  HTML 3.2  HTMLlat1  ISOnum  section sign
  uml      ('¨'),         // U+00A8 (168)  HTML 3.2  HTMLlat1  ISOdia  diaeresis (spacing diaeresis); see Germanic umlaut
  copy     ('©'),         // U+00A9 (169)  HTML 3.2  HTMLlat1  ISOnum  copyright symbol
  ordf     ('ª'),         // U+00AA (170)  HTML 3.2  HTMLlat1  ISOnum  feminine ordinal indicator
  laquo    ('«'),         // U+00AB (171)  HTML 3.2  HTMLlat1  ISOnum  left-pointing double angle quotation mark (left pointing guillemet)
  not      ('¬'),         // U+00AC (172)  HTML 3.2  HTMLlat1  ISOnum  not sign
  shy      ((char)173),   // U+00AD (173)  HTML 3.2  HTMLlat1  ISOnum  soft hyphen (discretionary hyphen)
  reg      ('®'),         // U+00AE (174)  HTML 3.2  HTMLlat1  ISOnum  registered sign (registered trademark symbol)
  macr     ('¯'),         // U+00AF (175)  HTML 3.2  HTMLlat1  ISOdia  macron (spacing macron, overline, APL overbar)
  deg      ('°'),         // U+00B0 (176)  HTML 3.2  HTMLlat1  ISOnum  degree symbol
  plusmn   ('±'),         // U+00B1 (177)  HTML 3.2  HTMLlat1  ISOnum  plus-minus sign (plus-or-minus sign)
  sup2     ('²'),         // U+00B2 (178)  HTML 3.2  HTMLlat1  ISOnum  superscript two (superscript digit two, squared)
  sup3     ('³'),         // U+00B3 (179)  HTML 3.2  HTMLlat1  ISOnum  superscript three (superscript digit three, cubed)
  acute    ('´'),         // U+00B4 (180)  HTML 3.2  HTMLlat1  ISOdia  acute accent (spacing acute)
  micro    ('µ'),         // U+00B5 (181)  HTML 3.2  HTMLlat1  ISOnum  micro sign
  para     ('¶'),         // U+00B6 (182)  HTML 3.2  HTMLlat1  ISOnum  pilcrow sign (paragraph sign)
  middot   ('·'),         // U+00B7 (183)  HTML 3.2  HTMLlat1  ISOnum  middle dot (Georgian comma, Greek middle dot)
  cedil    ('¸'),         // U+00B8 (184)  HTML 3.2  HTMLlat1  ISOdia  cedilla (spacing cedilla)
  sup1     ('¹'),         // U+00B9 (185)  HTML 3.2  HTMLlat1  ISOnum  superscript one (superscript digit one)
  ordm     ('º'),         // U+00BA (186)  HTML 3.2  HTMLlat1  ISOnum  masculine ordinal indicator
  raquo    ('»'),         // U+00BB (187)  HTML 3.2  HTMLlat1  ISOnum  right-pointing double angle quotation mark (right pointing guillemet)
  frac14   ('¼'),         // U+00BC (188)  HTML 3.2  HTMLlat1  ISOnum  vulgar fraction one quarter (fraction one quarter)
  frac12   ('½'),         // U+00BD (189)  HTML 3.2  HTMLlat1  ISOnum  vulgar fraction one half (fraction one half)
  frac34   ('¾'),         // U+00BE (190)  HTML 3.2  HTMLlat1  ISOnum  vulgar fraction three quarters (fraction three quarters)
  iquest   ('¿'),         // U+00BF (191)  HTML 3.2  HTMLlat1  ISOnum  inverted question mark (turned question mark)
  Agrave   ('À'),         // U+00C0 (192)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with grave accent (Latin capital letter A grave)
  Aacute   ('Á'),         // U+00C1 (193)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with acute accent
  Acirc    ('Â'),         // U+00C2 (194)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with circumflex
  Atilde   ('Ã'),         // U+00C3 (195)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with tilde
  Auml     ('Ä'),         // U+00C4 (196)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with diaeresis
  Aring    ('Å'),         // U+00C5 (197)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter A with ring above (Latin capital letter A ring)
  AElig    ('Æ'),         // U+00C6 (198)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter AE (Latin capital ligature AE)
  Ccedil   ('Ç'),         // U+00C7 (199)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter C with cedilla
  Egrave   ('È'),         // U+00C8 (200)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter E with grave accent
  Eacute   ('É'),         // U+00C9 (201)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter E with acute accent
  Ecirc    ('Ê'),         // U+00CA (202)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter E with circumflex
  Euml     ('Ë'),         // U+00CB (203)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter E with diaeresis
  Igrave   ('Ì'),         // U+00CC (204)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter I with grave accent
  Iacute   ('Í'),         // U+00CD (205)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter I with acute accent
  Icirc    ('Î'),         // U+00CE (206)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter I with circumflex
  Iuml     ('Ï'),         // U+00CF (207)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter I with diaeresis
  ETH      ('Ð'),         // U+00D0 (208)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter Eth
  Ntilde   ('Ñ'),         // U+00D1 (209)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter N with tilde
  Ograve   ('Ò'),         // U+00D2 (210)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with grave accent
  Oacute   ('Ó'),         // U+00D3 (211)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with acute accent
  Ocirc    ('Ô'),         // U+00D4 (212)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with circumflex
  Otilde   ('Õ'),         // U+00D5 (213)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with tilde
  Ouml     ('Ö'),         // U+00D6 (214)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with diaeresis
  times    ('×'),         // U+00D7 (215)  HTML 3.2  HTMLlat1  ISOnum  multiplication sign
  Oslash   ('Ø'),         // U+00D8 (216)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter O with stroke (Latin capital letter O slash)
  Ugrave   ('Ù'),         // U+00D9 (217)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter U with grave accent
  Uacute   ('Ú'),         // U+00DA (218)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter U with acute accent
  Ucirc    ('Û'),         // U+00DB (219)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter U with circumflex
  Uuml     ('Ü'),         // U+00DC (220)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter U with diaeresis
  Yacute   ('Ý'),         // U+00DD (221)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter Y with acute accent
  THORN    ('Þ'),         // U+00DE (222)  HTML 2.0  HTMLlat1  ISOlat1 Latin capital letter THORN
  szlig    ('ß'),         // U+00DF (223)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter sharp s (ess-zed); see German Eszett
  agrave   ('à'),         // U+00E0 (224)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with grave accent
  aacute   ('á'),         // U+00E1 (225)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with acute accent
  acirc    ('â'),         // U+00E2 (226)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with circumflex
  atilde   ('ã'),         // U+00E3 (227)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with tilde
  auml     ('ä'),         // U+00E4 (228)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with diaeresis
  aring    ('å'),         // U+00E5 (229)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter a with ring above
  aelig    ('æ'),         // U+00E6 (230)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter ae (Latin small ligature ae)
  ccedil   ('ç'),         // U+00E7 (231)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter c with cedilla
  egrave   ('è'),         // U+00E8 (232)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter e with grave accent
  eacute   ('é'),         // U+00E9 (233)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter e with acute accent
  ecirc    ('ê'),         // U+00EA (234)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter e with circumflex
  euml     ('ë'),         // U+00EB (235)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter e with diaeresis
  igrave   ('ì'),         // U+00EC (236)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter i with grave accent
  iacute   ('í'),         // U+00ED (237)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter i with acute accent
  icirc    ('î'),         // U+00EE (238)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter i with circumflex
  iuml     ('ï'),         // U+00EF (239)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter i with diaeresis
  eth      ('ð'),         // U+00F0 (240)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter eth
  ntilde   ('ñ'),         // U+00F1 (241)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter n with tilde
  ograve   ('ò'),         // U+00F2 (242)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with grave accent
  oacute   ('ó'),         // U+00F3 (243)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with acute accent
  ocirc    ('ô'),         // U+00F4 (244)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with circumflex
  otilde   ('õ'),         // U+00F5 (245)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with tilde
  ouml     ('ö'),         // U+00F6 (246)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with diaeresis
  divide   ('÷'),         // U+00F7 (247)  HTML 3.2  HTMLlat1  ISOnum  division sign (obelus)
  oslash   ('ø'),         // U+00F8 (248)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter o with stroke (Latin small letter o slash)
  ugrave   ('ù'),         // U+00F9 (249)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter u with grave accent
  uacute   ('ú'),         // U+00FA (250)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter u with acute accent
  ucirc    ('û'),         // U+00FB (251)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter u with circumflex
  uuml     ('ü'),         // U+00FC (252)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter u with diaeresis
  yacute   ('ý'),         // U+00FD (253)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter y with acute accent
  thorn    ('þ'),         // U+00FE (254)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter thorn
  yuml     ('ÿ'),         // U+00FF (255)  HTML 2.0  HTMLlat1  ISOlat1 Latin small letter y with diaeresis
  OElig    ('Œ'),         // U+0152 (338)  HTML 4.0  HTMLspecial ISOlat2 Latin capital ligature oe
  oelig    ('œ'),         // U+0153 (339)  HTML 4.0  HTMLspecial ISOlat2 Latin small ligature oe
  Scaron   ('Š'),         // U+0160 (352)  HTML 4.0  HTMLspecial ISOlat2 Latin capital letter s with caron
  scaron   ('š'),         // U+0161 (353)  HTML 4.0  HTMLspecial ISOlat2 Latin small letter s with caron
  Yuml     ('Ÿ'),         // U+0178 (376)  HTML 4.0  HTMLspecial ISOlat2 Latin capital letter y with diaeresis
  fnof     ('ƒ'),         // U+0192 (402)  HTML 4.0  HTMLsymbol  ISOtech Latin small letter f with hook (function, florin)
  circ     ('ˆ'),         // U+02C6 (710)  HTML 4.0  HTMLspecial ISOpub  modifier letter circumflex accent
  tilde    ('˜'),         // U+02DC (732)  HTML 4.0  HTMLspecial ISOdia  small tilde
  Alpha    ('Α'),         // U+0391 (913)  HTML 4.0  HTMLsymbol    Greek capital letter Alpha
  Beta     ('Β'),         // U+0392 (914)  HTML 4.0  HTMLsymbol    Greek capital letter Beta
  Gamma    ('Γ'),         // U+0393 (915)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Gamma
  Delta    ('Δ'),         // U+0394 (916)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Delta
  Epsilon  ('Ε'),         // U+0395 (917)  HTML 4.0  HTMLsymbol    Greek capital letter Epsilon
  Zeta     ('Ζ'),         // U+0396 (918)  HTML 4.0  HTMLsymbol    Greek capital letter Zeta
  Eta      ('Η'),         // U+0397 (919)  HTML 4.0  HTMLsymbol    Greek capital letter Eta
  Theta    ('Θ'),         // U+0398 (920)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Theta
  Iota     ('Ι'),         // U+0399 (921)  HTML 4.0  HTMLsymbol    Greek capital letter Iota
  Kappa    ('Κ'),         // U+039A (922)  HTML 4.0  HTMLsymbol    Greek capital letter Kappa
  Lambda   ('Λ'),         // U+039B (923)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Lambda
  Mu       ('Μ'),         // U+039C (924)  HTML 4.0  HTMLsymbol    Greek capital letter Mu
  Nu       ('Ν'),         // U+039D (925)  HTML 4.0  HTMLsymbol    Greek capital letter Nu
  Xi       ('Ξ'),         // U+039E (926)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Xi
  Omicron  ('Ο'),         // U+039F (927)  HTML 4.0  HTMLsymbol    Greek capital letter Omicron
  Pi       ('Π'),         // U+03A0 (928)  HTML 4.0  HTMLsymbol    Greek capital letter Pi
  Rho      ('Ρ'),         // U+03A1 (929)  HTML 4.0  HTMLsymbol    Greek capital letter Rho
  Sigma    ('Σ'),         // U+03A3 (931)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Sigma
  Tau      ('Τ'),         // U+03A4 (932)  HTML 4.0  HTMLsymbol    Greek capital letter Tau
  Upsilon  ('Υ'),         // U+03A5 (933)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Upsilon
  Phi      ('Φ'),         // U+03A6 (934)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Phi
  Chi      ('Χ'),         // U+03A7 (935)  HTML 4.0  HTMLsymbol    Greek capital letter Chi
  Psi      ('Ψ'),         // U+03A8 (936)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Psi
  Omega    ('Ω'),         // U+03A9 (937)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek capital letter Omega
  alpha    ('α'),         // U+03B1 (945)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter alpha
  beta     ('β'),         // U+03B2 (946)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter beta
  gamma    ('γ'),         // U+03B3 (947)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter gamma
  delta    ('δ'),         // U+03B4 (948)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter delta
  epsilon  ('ε'),         // U+03B5 (949)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter epsilon
  zeta     ('ζ'),         // U+03B6 (950)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter zeta
  eta      ('η'),         // U+03B7 (951)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter eta
  theta    ('θ'),         // U+03B8 (952)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter theta
  iota     ('ι'),         // U+03B9 (953)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter iota
  kappa    ('κ'),         // U+03BA (954)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter kappa
  lambda   ('λ'),         // U+03BB (955)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter lambda
  mu       ('μ'),         // U+03BC (956)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter mu
  nu       ('ν'),         // U+03BD (957)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter nu
  xi       ('ξ'),         // U+03BE (958)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter xi
  omicron  ('ο'),         // U+03BF (959)  HTML 4.0  HTMLsymbol  NEW Greek small letter omicron
  pi       ('π'),         // U+03C0 (960)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter pi
  rho      ('ρ'),         // U+03C1 (961)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter rho
  sigmaf   ('ς'),         // U+03C2 (962)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter final sigma
  sigma    ('σ'),         // U+03C3 (963)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter sigma
  tau      ('τ'),         // U+03C4 (964)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter tau
  upsilon  ('υ'),         // U+03C5 (965)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter upsilon
  phi      ('φ'),         // U+03C6 (966)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter phi
  chi      ('χ'),         // U+03C7 (967)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter chi
  psi      ('ψ'),         // U+03C8 (968)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter psi
  omega    ('ω'),         // U+03C9 (969)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek small letter omega
  thetasym ('ϑ'),         // U+03D1 (977)  HTML 4.0  HTMLsymbol  NEW Greek theta symbol
  upsih    ('ϒ'),         // U+03D2 (978)  HTML 4.0  HTMLsymbol  NEW Greek Upsilon with hook symbol
  piv      ('ϖ'),         // U+03D6 (982)  HTML 4.0  HTMLsymbol  ISOgrk3 Greek pi symbol
  ensp     ((char)8194),  // U+2002 (8194) HTML 4.0  HTMLspecial ISOpub  en space
  emsp     ((char)8195),  // U+2003 (8195) HTML 4.0  HTMLspecial ISOpub  em space
  thinsp   ((char)8201),  // U+2009 (8201) HTML 4.0  HTMLspecial ISOpub  thin space
  zwnj     ((char)8204),  // U+200C (8204) HTML 4.0  HTMLspecial NEW RFC 2070  zero-width non-joiner
  zwj      ((char)8205),  // U+200D (8205) HTML 4.0  HTMLspecial NEW RFC 2070  zero-width joiner
  lrm      ((char)8206),  // U+200E (8206) HTML 4.0  HTMLspecial NEW RFC 2070  left-to-right mark
  rlm      ((char)8207),  // U+200F (8207) HTML 4.0  HTMLspecial NEW RFC 2070  right-to-left mark
  ndash    ('–'),         // U+2013 (8211) HTML 4.0  HTMLspecial ISOpub  en dash
  mdash    ('—'),         // U+2014 (8212) HTML 4.0  HTMLspecial ISOpub  em dash
  lsquo    ('‘'),         // U+2018 (8216) HTML 4.0  HTMLspecial ISOnum  left single quotation mark
  rsquo    ('’'),         // U+2019 (8217) HTML 4.0  HTMLspecial ISOnum  right single quotation mark
  sbquo    ('‚'),         // U+201A (8218) HTML 4.0  HTMLspecial NEW single low-9 quotation mark
  ldquo    ('“'),         // U+201C (8220) HTML 4.0  HTMLspecial ISOnum  left double quotation mark
  rdquo    ('”'),         // U+201D (8221) HTML 4.0  HTMLspecial ISOnum  right double quotation mark
  bdquo    ('„'),         // U+201E (8222) HTML 4.0  HTMLspecial NEW double low-9 quotation mark
  dagger   ('†'),         // U+2020 (8224) HTML 4.0  HTMLspecial ISOpub  dagger, obelisk
  Dagger   ('‡'),         // U+2021 (8225) HTML 4.0  HTMLspecial ISOpub  double dagger, double obelisk
  bull     ('•'),         // U+2022 (8226) HTML 4.0  HTMLspecial ISOpub  bullet (black small circle)
  hellip   ('…'),         // U+2026 (8230) HTML 4.0  HTMLsymbol  ISOpub  horizontal ellipsis (three dot leader)
  permil   ('‰'),         // U+2030 (8240) HTML 4.0  HTMLspecial ISOtech per mille sign
  prime    ('′'),         // U+2032 (8242) HTML 4.0  HTMLsymbol  ISOtech prime (minutes, feet)
  Prime    ('″'),         // U+2033 (8243) HTML 4.0  HTMLsymbol  ISOtech double prime (seconds, inches)
  lsaquo   ('‹'),         // U+2039 (8249) HTML 4.0  HTMLspecial ISO proposed  single left-pointing angle quotation mark
  rsaquo   ('›'),         // U+203A (8250) HTML 4.0  HTMLspecial ISO proposed  single right-pointing angle quotation mark
  oline    ((char)8254),  // U+203E (8254) HTML 4.0  HTMLsymbol  NEW overline (spacing overscore)
  frasl    ('⁄'),         // U+2044 (8260) HTML 4.0  HTMLsymbol  NEW fraction slash (solidus)
  euro     ('€'),         // U+20AC (8364) HTML 4.0  HTMLspecial NEW euro sign
  image    ('ℑ'),         // U+2111 (8465) HTML 4.0  HTMLsymbol  ISOamso black-letter capital I (imaginary part)
  weierp   ('℘'),         // U+2118 (8472) HTML 4.0  HTMLsymbol  ISOamso script capital P (power set, Weierstrass p)
  real     ('ℜ'),         // U+211C (8476) HTML 4.0  HTMLsymbol  ISOamso black-letter capital R (real part symbol)
  trade    ('™') ,        // U+2122 (8482) HTML 4.0  HTMLsymbol  ISOnum  trademark symbol
  alefsym  ('ℵ'),         // U+2135 (8501) HTML 4.0  HTMLsymbol  NEW alef symbol (first transfinite cardinal)
  larr     ('←'),         // U+2190 (8592) HTML 4.0  HTMLsymbol  ISOnum  leftwards arrow
  uarr     ('↑'),         // U+2191 (8593) HTML 4.0  HTMLsymbol  ISOnum  upwards arrow
  rarr     ('→'),         // U+2192 (8594) HTML 4.0  HTMLsymbol  ISOnum  rightwards arrow
  darr     ('↓'),         // U+2193 (8595) HTML 4.0  HTMLsymbol  ISOnum  downwards arrow
  harr     ('↔'),         // U+2194 (8596) HTML 4.0  HTMLsymbol  ISOamsa left right arrow
  crarr    ('↵'),         // U+21B5 (8629) HTML 4.0  HTMLsymbol  NEW downwards arrow with corner leftwards (carriage return)
  lArr     ('⇐'),         // U+21D0 (8656) HTML 4.0  HTMLsymbol  ISOtech leftwards double arrow
  uArr     ('⇑'),         // U+21D1 (8657) HTML 4.0  HTMLsymbol  ISOamsa upwards double arrow
  rArr     ('⇒'),        // U+21D2 (8658) HTML 4.0  HTMLsymbol  ISOnum  rightwards double arrow
  dArr     ('⇓'),         // U+21D3 (8659) HTML 4.0  HTMLsymbol  ISOamsa downwards double arrow
  hArr     ('⇔'),        // U+21D4 (8660) HTML 4.0  HTMLsymbol  ISOamsa left right double arrow
  forall   ('∀'),        // U+2200 (8704) HTML 4.0  HTMLsymbol  ISOtech for all
  part     ('∂'),         // U+2202 (8706) HTML 4.0  HTMLsymbol  ISOtech partial differential
  exist    ('∃'),        // U+2203 (8707) HTML 4.0  HTMLsymbol  ISOtech there exists
  empty    ('∅'),         // U+2205 (8709) HTML 4.0  HTMLsymbol  ISOamso empty set (null set); see also U+8960
  nabla    ('∇'),        // U+2207 (8711) HTML 4.0  HTMLsymbol  ISOtech del or nabla (vector differential operator)
  isin     ('∈'),        // U+2208 (8712) HTML 4.0  HTMLsymbol  ISOtech element of
  notin    ('∉'),         // U+2209 (8713) HTML 4.0  HTMLsymbol  ISOtech not an element of
  ni       ('∋'),        // U+220B (8715) HTML 4.0  HTMLsymbol  ISOtech contains as member
  prod     ('∏'),         // U+220F (8719) HTML 4.0  HTMLsymbol  ISOamsb n-ary product (product sign)
  sum      ('∑'),         // U+2211 (8721) HTML 4.0  HTMLsymbol  ISOamsb n-ary summation
  minus    ('−'),         // U+2212 (8722) HTML 4.0  HTMLsymbol  ISOtech minus sign
  lowast   ('∗'),         // U+2217 (8727) HTML 4.0  HTMLsymbol  ISOtech asterisk operator
  radic    ('√'),         // U+221A (8730) HTML 4.0  HTMLsymbol  ISOtech square root (radical sign)
  prop     ('∝'),        // U+221D (8733) HTML 4.0  HTMLsymbol  ISOtech proportional to
  infin    ('∞'),         // U+221E (8734) HTML 4.0  HTMLsymbol  ISOtech infinity
  ang      ('∠'),        // U+2220 (8736) HTML 4.0  HTMLsymbol  ISOamso angle
  and      ('∧'),        // U+2227 (8743) HTML 4.0  HTMLsymbol  ISOtech logical and (wedge)
  or       ('∨'),        // U+2228 (8744) HTML 4.0  HTMLsymbol  ISOtech logical or (vee)
  cap      ('∩'),         // U+2229 (8745) HTML 4.0  HTMLsymbol  ISOtech intersection (cap)
  cup      ('∪'),        // U+222A (8746) HTML 4.0  HTMLsymbol  ISOtech union (cup)
  _int     ('∫'),         // U+222B (8747) HTML 4.0  HTMLsymbol  ISOtech integral
  there4   ('∴'),        // U+2234 (8756) HTML 4.0  HTMLsymbol  ISOtech therefore sign
  sim      ('∼'),         // U+223C (8764) HTML 4.0  HTMLsymbol  ISOtech tilde operator (varies with, similar to)
  cong     ('≅'),         // U+2245 (8773) HTML 4.0  HTMLsymbol  ISOtech congruent to
  asymp    ('≈'),         // U+2248 (8776) HTML 4.0  HTMLsymbol  ISOamsr almost equal to (asymptotic to)
  ne       ('≠'),         // U+2260 (8800) HTML 4.0  HTMLsymbol  ISOtech not equal to
  equiv    ('≡'),         // U+2261 (8801) HTML 4.0  HTMLsymbol  ISOtech identical to; sometimes used for 'equivalent to'
  le       ('≤'),         // U+2264 (8804) HTML 4.0  HTMLsymbol  ISOtech less-than or equal to
  ge       ('≥'),         // U+2265 (8805) HTML 4.0  HTMLsymbol  ISOtech greater-than or equal to
  sub      ('⊂'),        // U+2282 (8834) HTML 4.0  HTMLsymbol  ISOtech subset of
  sup      ('⊃'),        // U+2283 (8835) HTML 4.0  HTMLsymbol  ISOtech superset of
  nsub     ('⊄'),         // U+2284 (8836) HTML 4.0  HTMLsymbol  ISOamsn not a subset of
  sube     ('⊆'),        // U+2286 (8838) HTML 4.0  HTMLsymbol  ISOtech subset of or equal to
  supe     ('⊇'),        // U+2287 (8839) HTML 4.0  HTMLsymbol  ISOtech superset of or equal to
  oplus    ('⊕'),         // U+2295 (8853) HTML 4.0  HTMLsymbol  ISOamsb circled plus (direct sum)
  otimes   ('⊗'),         // U+2297 (8855) HTML 4.0  HTMLsymbol  ISOamsb circled times (vector product)
  perp     ('⊥'),        // U+22A5 (8869) HTML 4.0  HTMLsymbol  ISOtech up tack (orthogonal to, perpendicular)
  sdot     ('⋅'),         // U+22C5 (8901) HTML 4.0  HTMLsymbol  ISOamsb dot operator
  vellip   ('⋮'),         // U+22EE (8942) HTML 5.0  ? ? vertical ellipsis
  lceil    ((char) 8968), // U+2308 (8968) HTML 4.0  HTMLsymbol  ISOamsc left ceiling (APL upstile)
  rceil    ((char) 8969), // U+2309 (8969) HTML 4.0  HTMLsymbol  ISOamsc right ceiling
  lfloor   ((char) 8970), // U+230A (8970) HTML 4.0  HTMLsymbol  ISOamsc left floor (APL downstile)
  rfloor   ((char) 8971), // U+230B (8971) HTML 4.0  HTMLsymbol  ISOamsc right floor
  lang     ((char)9001),  // U+2329 (9001) HTML 4.0  HTMLsymbol  ISOtech left-pointing angle bracket (bra)
  rang     ((char)9002),  // U+232A (9002) HTML 4.0  HTMLsymbol  ISOtech right-pointing angle bracket (ket)
  loz      ('◊'),         // U+25CA (9674) HTML 4.0  HTMLsymbol  ISOpub  lozenge
  spades   ('♠'),         // U+2660 (9824) HTML 4.0  HTMLsymbol  ISOpub  black spade suit
  clubs    ('♣'),         // U+2663 (9827) HTML 4.0  HTMLsymbol  ISOpub  black club suit (shamrock)
  hearts   ('♥'),         // U+2665 (9829) HTML 4.0  HTMLsymbol  ISOpub  black heart suit (valentine)
  diams    ('♦');         // U+2666 (9830) HTML 4.0  HTMLsymbol  ISOpub  black diamond suit

  final char c;
  final boolean isXML;
  final String characterEntityRef;
  final String hexadecimalEntityRef;
  
  XHTMLEntities(char c){
    this(c, false);
  }

  @SuppressWarnings("boxing")
  XHTMLEntities(char c, boolean isXML){
    this.c = c;
    characterEntityRef = "&" + entityName() + ";"; //$NON-NLS-1$ //$NON-NLS-2$
    hexadecimalEntityRef = String.format("&#x%04x;", (int) c); //$NON-NLS-1$
    this.isXML = isXML;
  }

  /**
   * @return the named entity reference, e.g. &uuml;
   */
  public String asCharacterEntityRef(){
    return characterEntityRef;
  }

  /**
   * @return the unicode ref in hexadecimal format (&#xHHHH;)
   */
  public String asHexadecimalEntityRef(){
    return hexadecimalEntityRef;
  }

  /**
   * @return the name of the character entity. This is preferrable to
   * name(), as there may be entity names that collide with valid java enum literal names. 
   * In such cases, the literal name is the entity name prefixed with an underscore "_";
   */
  public String entityName(){
    return name().startsWith("_") ? name().substring(1) : name(); //$NON-NLS-1$
  }

  /**
   * Returns the character that the entity represents.
   * @return
   */
  public char character(){
    return c;
  }

  /** 
   * Is this entity valid in any XML document, i.e. one of the following:
   * &amp;amp; &amp;quot; &amp;apos; &amp;lt; &amp;gt;
   */
  public boolean isXML(){
    return isXML;
  }

  /**
   * Replace all html entity references in input with their corresponding character.
   * References for which isXML returns true are left alone.
   * 
   * @return a new string with entity references replaced
   */
  public static String unescapeHTMLRefs(String input){
    return unescapeHTMLRefs(new StringBuilder(input)).toString();
  }

  /**
   * Replace all html entity references in input with their corresponding character.
   * References for which isXML returns true are left alone. 
   * 
   * @return the argument string builder
   */
  public static StringBuilder unescapeHTMLRefs(StringBuilder input){
    for (XHTMLEntities ent : values()){
      if (!ent.isXML()){
        int index;
        String entityRef = ent.asCharacterEntityRef();
        while ((index = input.indexOf(entityRef)) != -1){
          input.delete(index, index + entityRef.length());
          input.insert(index, ent.character());
        }
      }
    }
    return input;
  }
}
