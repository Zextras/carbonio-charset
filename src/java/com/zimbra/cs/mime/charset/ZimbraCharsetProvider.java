// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.mime.charset;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dkarp
 */
public class ZimbraCharsetProvider extends CharsetProvider {

    private static final String   UTF7_NAME    = "utf-7";
    private static final String[] UTF7_ALIASES = new String[] { "utf7", "unicode-1-1-utf-7" };
    private static final Charset  UTF7         = new UTF7(UTF7_NAME, UTF7_ALIASES);

    private static final String   IMAP_UTF7_NAME    = "imap-utf-7";
    private static final String[] IMAP_UTF7_ALIASES = new String[] { "imap-utf7" };
    private static final Charset  IMAP_UTF7         = new ImapUTF7(IMAP_UTF7_NAME, IMAP_UTF7_ALIASES);

    private static final String   MACINTOSH_NAME    = "macintosh";
    private static final String[] MACINTOSH_ALIASES = new String[] { "MacOS_Roman", "mac" };
    private static final Charset  MACINTOSH         = new Macintosh(MACINTOSH_NAME, MACINTOSH_ALIASES);

    private static final String   MACINTOSH_CE_NAME    = "macintosh_ce";
    private static final String[] MACINTOSH_CE_ALIASES = new String[] { "MacOS_CentralEurope", "macce" };
    private static final Charset  MACINTOSH_CE         = new MacintoshCE(MACINTOSH_CE_NAME, MACINTOSH_CE_ALIASES);

    private static final List<Charset> ZIMBRA_LIST = new LinkedList<Charset>();
        static {
            ZIMBRA_LIST.add(UTF7);
            ZIMBRA_LIST.add(IMAP_UTF7);
            ZIMBRA_LIST.add(MACINTOSH);
            ZIMBRA_LIST.add(MACINTOSH_CE);
        }

	public Iterator<Charset> charsets() {
		return ZIMBRA_LIST.iterator();
	}

	public Charset charsetForName(String charsetName) {
        if (UTF7_NAME.equalsIgnoreCase(charsetName))
            return UTF7;
        for (int i = 0; i < UTF7_ALIASES.length; i++)
            if (UTF7_ALIASES[i].equalsIgnoreCase(charsetName))
                return UTF7;

        if (IMAP_UTF7_NAME.equalsIgnoreCase(charsetName))
            return IMAP_UTF7;
        for (int i = 0; i < IMAP_UTF7_ALIASES.length; i++)
            if (IMAP_UTF7_ALIASES[i].equalsIgnoreCase(charsetName))
                return IMAP_UTF7;

        if (MACINTOSH_NAME.equalsIgnoreCase(charsetName))
            return MACINTOSH;
        for (int i = 0; i < MACINTOSH_ALIASES.length; i++)
            if (MACINTOSH_ALIASES[i].equalsIgnoreCase(charsetName))
                return MACINTOSH;

        if (MACINTOSH_CE_NAME.equalsIgnoreCase(charsetName))
            return MACINTOSH_CE;
        for (int i = 0; i < MACINTOSH_CE_ALIASES.length; i++)
            if (MACINTOSH_CE_ALIASES[i].equalsIgnoreCase(charsetName))
                return MACINTOSH_CE;

        return null;
	}
}
